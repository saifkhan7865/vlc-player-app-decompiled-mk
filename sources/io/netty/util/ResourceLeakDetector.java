package io.netty.util;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import j$.util.concurrent.ConcurrentHashMap;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public class ResourceLeakDetector<T> {
    /* access modifiers changed from: private */
    public static final Level DEFAULT_LEVEL;
    private static final int DEFAULT_SAMPLING_INTERVAL = 128;
    private static final int DEFAULT_TARGET_RECORDS = 4;
    private static final String PROP_LEVEL = "io.netty.leakDetection.level";
    private static final String PROP_LEVEL_OLD = "io.netty.leakDetectionLevel";
    private static final String PROP_SAMPLING_INTERVAL = "io.netty.leakDetection.samplingInterval";
    private static final String PROP_TARGET_RECORDS = "io.netty.leakDetection.targetRecords";
    static final int SAMPLING_INTERVAL = SystemPropertyUtil.getInt(PROP_SAMPLING_INTERVAL, 128);
    /* access modifiers changed from: private */
    public static final int TARGET_RECORDS;
    /* access modifiers changed from: private */
    public static final AtomicReference<String[]> excludedMethods = new AtomicReference<>(EmptyArrays.EMPTY_STRINGS);
    private static Level level;
    private static final InternalLogger logger;
    private final Set<DefaultResourceLeak<?>> allLeaks;
    private volatile LeakListener leakListener;
    private final ReferenceQueue<Object> refQueue;
    private final Set<String> reportedLeaks;
    private final String resourceType;
    private final int samplingInterval;

    public interface LeakListener {
        void onLeak(String str, String str2);
    }

    /* access modifiers changed from: protected */
    public Object getInitialHint(String str) {
        return null;
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public void reportInstancesLeak(String str) {
    }

    static {
        Level level2 = Level.SIMPLE;
        DEFAULT_LEVEL = level2;
        InternalLogger instance = InternalLoggerFactory.getInstance((Class<?>) ResourceLeakDetector.class);
        logger = instance;
        if (SystemPropertyUtil.get("io.netty.noResourceLeakDetection") != null) {
            boolean z = SystemPropertyUtil.getBoolean("io.netty.noResourceLeakDetection", false);
            instance.debug("-Dio.netty.noResourceLeakDetection: {}", (Object) Boolean.valueOf(z));
            instance.warn("-Dio.netty.noResourceLeakDetection is deprecated. Use '-D{}={}' instead.", PROP_LEVEL, Level.DISABLED.name().toLowerCase());
            if (z) {
                level2 = Level.DISABLED;
            }
        }
        Level parseLevel = Level.parseLevel(SystemPropertyUtil.get(PROP_LEVEL, SystemPropertyUtil.get(PROP_LEVEL_OLD, level2.name())));
        int i = SystemPropertyUtil.getInt(PROP_TARGET_RECORDS, 4);
        TARGET_RECORDS = i;
        level = parseLevel;
        if (instance.isDebugEnabled()) {
            instance.debug("-D{}: {}", PROP_LEVEL, parseLevel.name().toLowerCase());
            instance.debug("-D{}: {}", PROP_TARGET_RECORDS, Integer.valueOf(i));
        }
    }

    public enum Level {
        DISABLED,
        SIMPLE,
        ADVANCED,
        PARANOID;

        static Level parseLevel(String str) {
            String trim = str.trim();
            for (Level level : values()) {
                if (trim.equalsIgnoreCase(level.name()) || trim.equals(String.valueOf(level.ordinal()))) {
                    return level;
                }
            }
            return ResourceLeakDetector.DEFAULT_LEVEL;
        }
    }

    @Deprecated
    public static void setEnabled(boolean z) {
        setLevel(z ? Level.SIMPLE : Level.DISABLED);
    }

    public static boolean isEnabled() {
        return getLevel().ordinal() > Level.DISABLED.ordinal();
    }

    public static void setLevel(Level level2) {
        level = (Level) ObjectUtil.checkNotNull(level2, "level");
    }

    public static Level getLevel() {
        return level;
    }

    @Deprecated
    public ResourceLeakDetector(Class<?> cls) {
        this(StringUtil.simpleClassName(cls));
    }

    @Deprecated
    public ResourceLeakDetector(String str) {
        this(str, 128, Long.MAX_VALUE);
    }

    @Deprecated
    public ResourceLeakDetector(Class<?> cls, int i, long j) {
        this(cls, i);
    }

    public ResourceLeakDetector(Class<?> cls, int i) {
        this(StringUtil.simpleClassName(cls), i, Long.MAX_VALUE);
    }

    @Deprecated
    public ResourceLeakDetector(String str, int i, long j) {
        this.allLeaks = Collections.newSetFromMap(new ConcurrentHashMap());
        this.refQueue = new ReferenceQueue<>();
        this.reportedLeaks = Collections.newSetFromMap(new ConcurrentHashMap());
        this.resourceType = (String) ObjectUtil.checkNotNull(str, "resourceType");
        this.samplingInterval = i;
    }

    @Deprecated
    public final ResourceLeak open(T t) {
        return track0(t, false);
    }

    public final ResourceLeakTracker<T> track(T t) {
        return track0(t, false);
    }

    public ResourceLeakTracker<T> trackForcibly(T t) {
        return track0(t, true);
    }

    private DefaultResourceLeak track0(T t, boolean z) {
        Level level2 = level;
        if (!z && level2 != Level.PARANOID && (level2 == Level.DISABLED || PlatformDependent.threadLocalRandom().nextInt(this.samplingInterval) != 0)) {
            return null;
        }
        reportLeak();
        return new DefaultResourceLeak(t, this.refQueue, this.allLeaks, getInitialHint(this.resourceType));
    }

    private void clearRefQueue() {
        while (true) {
            DefaultResourceLeak defaultResourceLeak = (DefaultResourceLeak) this.refQueue.poll();
            if (defaultResourceLeak != null) {
                defaultResourceLeak.dispose();
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean needReport() {
        return logger.isErrorEnabled();
    }

    private void reportLeak() {
        if (!needReport()) {
            clearRefQueue();
            return;
        }
        while (true) {
            DefaultResourceLeak defaultResourceLeak = (DefaultResourceLeak) this.refQueue.poll();
            if (defaultResourceLeak != null) {
                if (defaultResourceLeak.dispose()) {
                    String reportAndClearRecords = defaultResourceLeak.getReportAndClearRecords();
                    if (this.reportedLeaks.add(reportAndClearRecords)) {
                        if (reportAndClearRecords.isEmpty()) {
                            reportUntracedLeak(this.resourceType);
                        } else {
                            reportTracedLeak(this.resourceType, reportAndClearRecords);
                        }
                        LeakListener leakListener2 = this.leakListener;
                        if (leakListener2 != null) {
                            leakListener2.onLeak(this.resourceType, reportAndClearRecords);
                        }
                    }
                }
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void reportTracedLeak(String str, String str2) {
        logger.error("LEAK: {}.release() was not called before it's garbage-collected. See https://netty.io/wiki/reference-counted-objects.html for more information.{}", str, str2);
    }

    /* access modifiers changed from: protected */
    public void reportUntracedLeak(String str) {
        logger.error("LEAK: {}.release() was not called before it's garbage-collected. Enable advanced leak reporting to find out where the leak occurred. To enable advanced leak reporting, specify the JVM option '-D{}={}' or call {}.setLevel() See https://netty.io/wiki/reference-counted-objects.html for more information.", str, PROP_LEVEL, Level.ADVANCED.name().toLowerCase(), StringUtil.simpleClassName((Object) this));
    }

    public void setLeakListener(LeakListener leakListener2) {
        this.leakListener = leakListener2;
    }

    private static final class DefaultResourceLeak<T> extends WeakReference<Object> implements ResourceLeakTracker<T>, ResourceLeak {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final AtomicIntegerFieldUpdater<DefaultResourceLeak<?>> droppedRecordsUpdater;
        private static final AtomicReferenceFieldUpdater<DefaultResourceLeak<?>, TraceRecord> headUpdater;
        private final Set<DefaultResourceLeak<?>> allLeaks;
        private volatile int droppedRecords;
        private volatile TraceRecord head;
        private final int trackedHash;

        static {
            Class<ResourceLeakDetector> cls = ResourceLeakDetector.class;
            Class<DefaultResourceLeak> cls2 = DefaultResourceLeak.class;
            headUpdater = AtomicReferenceFieldUpdater.newUpdater(cls2, TraceRecord.class, "head");
            droppedRecordsUpdater = AtomicIntegerFieldUpdater.newUpdater(cls2, "droppedRecords");
        }

        DefaultResourceLeak(Object obj, ReferenceQueue<Object> referenceQueue, Set<DefaultResourceLeak<?>> set, Object obj2) {
            super(obj, referenceQueue);
            this.trackedHash = System.identityHashCode(obj);
            set.add(this);
            headUpdater.set(this, obj2 == null ? new TraceRecord(TraceRecord.BOTTOM) : new TraceRecord(TraceRecord.BOTTOM, obj2));
            this.allLeaks = set;
        }

        public void record() {
            record0((Object) null);
        }

        public void record(Object obj) {
            record0(obj);
        }

        private void record0(Object obj) {
            AtomicReferenceFieldUpdater<DefaultResourceLeak<?>, TraceRecord> atomicReferenceFieldUpdater;
            TraceRecord traceRecord;
            boolean z;
            TraceRecord traceRecord2;
            TraceRecord traceRecord3;
            if (ResourceLeakDetector.TARGET_RECORDS > 0) {
                do {
                    atomicReferenceFieldUpdater = headUpdater;
                    traceRecord = atomicReferenceFieldUpdater.get(this);
                    if (traceRecord != null) {
                        boolean z2 = true;
                        int access$300 = traceRecord.pos + 1;
                        z = false;
                        if (access$300 >= ResourceLeakDetector.TARGET_RECORDS) {
                            if (PlatformDependent.threadLocalRandom().nextInt(1 << Math.min(access$300 - ResourceLeakDetector.TARGET_RECORDS, 30)) == 0) {
                                z2 = false;
                            }
                            traceRecord2 = z2 ? traceRecord.next : traceRecord;
                            z = z2;
                        } else {
                            traceRecord2 = traceRecord;
                        }
                        if (obj == null) {
                            traceRecord3 = new TraceRecord(traceRecord2);
                        }
                    } else {
                        return;
                    }
                } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, traceRecord, traceRecord3));
                if (z) {
                    droppedRecordsUpdater.incrementAndGet(this);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean dispose() {
            clear();
            return this.allLeaks.remove(this);
        }

        public boolean close() {
            if (!this.allLeaks.remove(this)) {
                return false;
            }
            clear();
            headUpdater.set(this, (Object) null);
            return true;
        }

        public boolean close(T t) {
            try {
                return close();
            } finally {
                reachabilityFence0(t);
            }
        }

        private static void reachabilityFence0(Object obj) {
            if (obj != null) {
                synchronized (obj) {
                }
            }
        }

        public String toString() {
            return generateReport(headUpdater.get(this));
        }

        /* access modifiers changed from: package-private */
        public String getReportAndClearRecords() {
            return generateReport(headUpdater.getAndSet(this, (Object) null));
        }

        private String generateReport(TraceRecord traceRecord) {
            if (traceRecord == null) {
                return "";
            }
            int i = droppedRecordsUpdater.get(this);
            int i2 = 1;
            int access$300 = traceRecord.pos + 1;
            StringBuilder sb = new StringBuilder(access$300 * 2048);
            sb.append(StringUtil.NEWLINE);
            sb.append("Recent access records: ");
            sb.append(StringUtil.NEWLINE);
            HashSet hashSet = new HashSet(access$300);
            int i3 = 0;
            while (traceRecord != TraceRecord.BOTTOM) {
                String traceRecord2 = traceRecord.toString();
                if (!hashSet.add(traceRecord2)) {
                    i3++;
                } else if (traceRecord.next == TraceRecord.BOTTOM) {
                    sb.append("Created at:");
                    sb.append(StringUtil.NEWLINE);
                    sb.append(traceRecord2);
                } else {
                    sb.append('#');
                    sb.append(i2);
                    sb.append(AbstractJsonLexerKt.COLON);
                    sb.append(StringUtil.NEWLINE);
                    sb.append(traceRecord2);
                    i2++;
                }
                traceRecord = traceRecord.next;
            }
            if (i3 > 0) {
                sb.append(": ");
                sb.append(i3);
                sb.append(" leak records were discarded because they were duplicates");
                sb.append(StringUtil.NEWLINE);
            }
            if (i > 0) {
                sb.append(": ");
                sb.append(i);
                sb.append(" leak records were discarded because the leak record count is targeted to ");
                sb.append(ResourceLeakDetector.TARGET_RECORDS);
                sb.append(". Use system property io.netty.leakDetection.targetRecords to increase the limit.");
                sb.append(StringUtil.NEWLINE);
            }
            sb.setLength(sb.length() - StringUtil.NEWLINE.length());
            return sb.toString();
        }
    }

    public static void addExclusions(Class cls, String... strArr) {
        String[] strArr2;
        String[] strArr3;
        HashSet hashSet = new HashSet(Arrays.asList(strArr));
        Method[] declaredMethods = cls.getDeclaredMethods();
        int length = declaredMethods.length;
        int i = 0;
        while (i < length && (!hashSet.remove(declaredMethods[i].getName()) || !hashSet.isEmpty())) {
            i++;
        }
        if (hashSet.isEmpty()) {
            do {
                strArr2 = excludedMethods.get();
                strArr3 = (String[]) Arrays.copyOf(strArr2, strArr2.length + (strArr.length * 2));
                for (int i2 = 0; i2 < strArr.length; i2++) {
                    int i3 = i2 * 2;
                    strArr3[strArr2.length + i3] = cls.getName();
                    strArr3[strArr2.length + i3 + 1] = strArr[i2];
                }
            } while (!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(excludedMethods, strArr2, strArr3));
            return;
        }
        throw new IllegalArgumentException("Can't find '" + hashSet + "' in " + cls.getName());
    }

    private static class TraceRecord extends Throwable {
        /* access modifiers changed from: private */
        public static final TraceRecord BOTTOM = new TraceRecord() {
            private static final long serialVersionUID = 7396077602074694571L;

            public Throwable fillInStackTrace() {
                return this;
            }
        };
        private static final long serialVersionUID = 6065153674892850720L;
        private final String hintString;
        /* access modifiers changed from: private */
        public final TraceRecord next;
        /* access modifiers changed from: private */
        public final int pos;

        TraceRecord(TraceRecord traceRecord, Object obj) {
            this.hintString = obj instanceof ResourceLeakHint ? ((ResourceLeakHint) obj).toHintString() : obj.toString();
            this.next = traceRecord;
            this.pos = traceRecord.pos + 1;
        }

        TraceRecord(TraceRecord traceRecord) {
            this.hintString = null;
            this.next = traceRecord;
            this.pos = traceRecord.pos + 1;
        }

        private TraceRecord() {
            this.hintString = null;
            this.next = null;
            this.pos = -1;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(2048);
            if (this.hintString != null) {
                sb.append("\tHint: ");
                sb.append(this.hintString);
                sb.append(StringUtil.NEWLINE);
            }
            StackTraceElement[] stackTrace = getStackTrace();
            for (int i = 3; i < stackTrace.length; i++) {
                StackTraceElement stackTraceElement = stackTrace[i];
                String[] strArr = (String[]) ResourceLeakDetector.excludedMethods.get();
                int i2 = 0;
                while (true) {
                    if (i2 < strArr.length) {
                        if (strArr[i2].equals(stackTraceElement.getClassName()) && strArr[i2 + 1].equals(stackTraceElement.getMethodName())) {
                            break;
                        }
                        i2 += 2;
                    } else {
                        sb.append(9);
                        sb.append(stackTraceElement.toString());
                        sb.append(StringUtil.NEWLINE);
                        break;
                    }
                }
            }
            return sb.toString();
        }
    }
}
