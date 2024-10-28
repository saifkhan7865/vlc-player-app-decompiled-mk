package androidx.window.embedding;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import androidx.collection.ArraySet;
import androidx.core.util.Consumer;
import androidx.window.WindowProperties;
import androidx.window.core.BuildConfig;
import androidx.window.core.ConsumerAdapter;
import androidx.window.core.ExtensionsUtil;
import androidx.window.core.PredicateAdapter;
import androidx.window.core.VerificationMode;
import androidx.window.embedding.EmbeddingInterfaceCompat;
import androidx.window.embedding.SplitController;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000 ;2\u00020\u0001:\u0005:;<=>B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0017J,\u0010 \u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0012\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020(0'0&H\u0016J\b\u0010)\u001a\u00020*H\u0002J\b\u0010+\u001a\u00020\u001dH\u0016J\u000e\u0010,\u001a\b\u0012\u0004\u0012\u00020\u001f0-H\u0017J\u0010\u0010.\u001a\u00020*2\u0006\u0010!\u001a\u00020\"H\u0016J\b\u0010/\u001a\u00020*H\u0016J\u0010\u00100\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0017J\u001c\u00101\u001a\u00020\u001d2\u0012\u00102\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020(0'0&H\u0016J\u0016\u00103\u001a\u00020\u001d2\f\u00104\u001a\b\u0012\u0004\u0012\u00020\u001f0-H\u0017J\u001c\u00105\u001a\u00020\u001d2\u0012\u00106\u001a\u000e\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u00020907H\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u00020\f8\u0002X\u0004¢\u0006\u0002\n\u0000R\"\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0012\u0010\u0014\u001a\u00060\u0015R\u00020\u0000X\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0016\u001a\u00020\u00178VX\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u0018\u0010\u0019¨\u0006?"}, d2 = {"Landroidx/window/embedding/ExtensionEmbeddingBackend;", "Landroidx/window/embedding/EmbeddingBackend;", "applicationContext", "Landroid/content/Context;", "embeddingExtension", "Landroidx/window/embedding/EmbeddingInterfaceCompat;", "(Landroid/content/Context;Landroidx/window/embedding/EmbeddingInterfaceCompat;)V", "getEmbeddingExtension", "()Landroidx/window/embedding/EmbeddingInterfaceCompat;", "setEmbeddingExtension", "(Landroidx/window/embedding/EmbeddingInterfaceCompat;)V", "ruleTracker", "Landroidx/window/embedding/ExtensionEmbeddingBackend$RuleTracker;", "splitChangeCallbacks", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Landroidx/window/embedding/ExtensionEmbeddingBackend$SplitListenerWrapper;", "getSplitChangeCallbacks$annotations", "()V", "getSplitChangeCallbacks", "()Ljava/util/concurrent/CopyOnWriteArrayList;", "splitInfoEmbeddingCallback", "Landroidx/window/embedding/ExtensionEmbeddingBackend$EmbeddingCallbackImpl;", "splitSupportStatus", "Landroidx/window/embedding/SplitController$SplitSupportStatus;", "getSplitSupportStatus", "()Landroidx/window/embedding/SplitController$SplitSupportStatus;", "splitSupportStatus$delegate", "Lkotlin/Lazy;", "addRule", "", "rule", "Landroidx/window/embedding/EmbeddingRule;", "addSplitListenerForActivity", "activity", "Landroid/app/Activity;", "executor", "Ljava/util/concurrent/Executor;", "callback", "Landroidx/core/util/Consumer;", "", "Landroidx/window/embedding/SplitInfo;", "areExtensionsAvailable", "", "clearSplitAttributesCalculator", "getRules", "", "isActivityEmbedded", "isSplitAttributesCalculatorSupported", "removeRule", "removeSplitListenerForActivity", "consumer", "setRules", "rules", "setSplitAttributesCalculator", "calculator", "Lkotlin/Function1;", "Landroidx/window/embedding/SplitAttributesCalculatorParams;", "Landroidx/window/embedding/SplitAttributes;", "Api31Impl", "Companion", "EmbeddingCallbackImpl", "RuleTracker", "SplitListenerWrapper", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ExtensionEmbeddingBackend.kt */
public final class ExtensionEmbeddingBackend implements EmbeddingBackend {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "EmbeddingBackend";
    /* access modifiers changed from: private */
    public static volatile ExtensionEmbeddingBackend globalInstance;
    /* access modifiers changed from: private */
    public static final ReentrantLock globalLock = new ReentrantLock();
    /* access modifiers changed from: private */
    public final Context applicationContext;
    private EmbeddingInterfaceCompat embeddingExtension;
    private final RuleTracker ruleTracker;
    private final CopyOnWriteArrayList<SplitListenerWrapper> splitChangeCallbacks = new CopyOnWriteArrayList<>();
    private final EmbeddingCallbackImpl splitInfoEmbeddingCallback;
    private final Lazy splitSupportStatus$delegate;

    public static /* synthetic */ void getSplitChangeCallbacks$annotations() {
    }

    public ExtensionEmbeddingBackend(Context context, EmbeddingInterfaceCompat embeddingInterfaceCompat) {
        Intrinsics.checkNotNullParameter(context, "applicationContext");
        this.applicationContext = context;
        this.embeddingExtension = embeddingInterfaceCompat;
        EmbeddingCallbackImpl embeddingCallbackImpl = new EmbeddingCallbackImpl();
        this.splitInfoEmbeddingCallback = embeddingCallbackImpl;
        EmbeddingInterfaceCompat embeddingInterfaceCompat2 = this.embeddingExtension;
        if (embeddingInterfaceCompat2 != null) {
            embeddingInterfaceCompat2.setEmbeddingCallback(embeddingCallbackImpl);
        }
        this.ruleTracker = new RuleTracker();
        this.splitSupportStatus$delegate = LazyKt.lazy(new ExtensionEmbeddingBackend$splitSupportStatus$2(this));
    }

    public final EmbeddingInterfaceCompat getEmbeddingExtension() {
        return this.embeddingExtension;
    }

    public final void setEmbeddingExtension(EmbeddingInterfaceCompat embeddingInterfaceCompat) {
        this.embeddingExtension = embeddingInterfaceCompat;
    }

    public final CopyOnWriteArrayList<SplitListenerWrapper> getSplitChangeCallbacks() {
        return this.splitChangeCallbacks;
    }

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0012\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\fH\u0002J\u0017\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0007¢\u0006\u0002\u0010\u0014R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Landroidx/window/embedding/ExtensionEmbeddingBackend$Companion;", "", "()V", "TAG", "", "globalInstance", "Landroidx/window/embedding/ExtensionEmbeddingBackend;", "globalLock", "Ljava/util/concurrent/locks/ReentrantLock;", "getInstance", "Landroidx/window/embedding/EmbeddingBackend;", "context", "Landroid/content/Context;", "initAndVerifyEmbeddingExtension", "Landroidx/window/embedding/EmbeddingInterfaceCompat;", "applicationContext", "isExtensionVersionSupported", "", "extensionVersion", "", "(Ljava/lang/Integer;)Z", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ExtensionEmbeddingBackend.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final EmbeddingBackend getInstance(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (ExtensionEmbeddingBackend.globalInstance == null) {
                Lock access$getGlobalLock$cp = ExtensionEmbeddingBackend.globalLock;
                access$getGlobalLock$cp.lock();
                try {
                    if (ExtensionEmbeddingBackend.globalInstance == null) {
                        Context applicationContext = context.getApplicationContext();
                        Companion companion = ExtensionEmbeddingBackend.Companion;
                        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
                        EmbeddingInterfaceCompat initAndVerifyEmbeddingExtension = companion.initAndVerifyEmbeddingExtension(applicationContext);
                        Companion companion2 = ExtensionEmbeddingBackend.Companion;
                        ExtensionEmbeddingBackend.globalInstance = new ExtensionEmbeddingBackend(applicationContext, initAndVerifyEmbeddingExtension);
                    }
                    Unit unit = Unit.INSTANCE;
                } finally {
                    access$getGlobalLock$cp.unlock();
                }
            }
            ExtensionEmbeddingBackend access$getGlobalInstance$cp = ExtensionEmbeddingBackend.globalInstance;
            Intrinsics.checkNotNull(access$getGlobalInstance$cp);
            return access$getGlobalInstance$cp;
        }

        private final EmbeddingInterfaceCompat initAndVerifyEmbeddingExtension(Context context) {
            EmbeddingInterfaceCompat embeddingInterfaceCompat = null;
            try {
                if (isExtensionVersionSupported(Integer.valueOf(ExtensionsUtil.INSTANCE.getSafeVendorApiLevel())) && EmbeddingCompat.Companion.isEmbeddingAvailable()) {
                    ClassLoader classLoader = EmbeddingBackend.class.getClassLoader();
                    embeddingInterfaceCompat = classLoader != null ? new EmbeddingCompat(EmbeddingCompat.Companion.embeddingComponent(), new EmbeddingAdapter(new PredicateAdapter(classLoader)), new ConsumerAdapter(classLoader), context) : null;
                }
            } catch (Throwable th) {
                Log.d(ExtensionEmbeddingBackend.TAG, "Failed to load embedding extension: " + th);
            }
            if (embeddingInterfaceCompat == null) {
                Log.d(ExtensionEmbeddingBackend.TAG, "No supported embedding extension found");
            }
            return embeddingInterfaceCompat;
        }

        public final boolean isExtensionVersionSupported(Integer num) {
            return num != null && num.intValue() >= 1;
        }
    }

    public Set<EmbeddingRule> getRules() {
        Lock lock = globalLock;
        lock.lock();
        try {
            return this.ruleTracker.getSplitRules();
        } finally {
            lock.unlock();
        }
    }

    public void setRules(Set<? extends EmbeddingRule> set) {
        Intrinsics.checkNotNullParameter(set, "rules");
        Lock lock = globalLock;
        lock.lock();
        try {
            this.ruleTracker.setRules(set);
            EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
            if (embeddingInterfaceCompat != null) {
                embeddingInterfaceCompat.setRules(getRules());
                Unit unit = Unit.INSTANCE;
            }
        } finally {
            lock.unlock();
        }
    }

    public void addRule(EmbeddingRule embeddingRule) {
        Intrinsics.checkNotNullParameter(embeddingRule, "rule");
        Lock lock = globalLock;
        lock.lock();
        try {
            if (!this.ruleTracker.contains(embeddingRule)) {
                RuleTracker.addOrUpdateRule$default(this.ruleTracker, embeddingRule, false, 2, (Object) null);
                EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
                if (embeddingInterfaceCompat != null) {
                    embeddingInterfaceCompat.setRules(getRules());
                }
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            lock.unlock();
        }
    }

    public void removeRule(EmbeddingRule embeddingRule) {
        Intrinsics.checkNotNullParameter(embeddingRule, "rule");
        Lock lock = globalLock;
        lock.lock();
        try {
            if (this.ruleTracker.contains(embeddingRule)) {
                this.ruleTracker.removeRule(embeddingRule);
                EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
                if (embeddingInterfaceCompat != null) {
                    embeddingInterfaceCompat.setRules(getRules());
                }
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            lock.unlock();
        }
    }

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\"\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\rJ\u0011\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\u0005H\u0002J\u000e\u0010\u0013\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0005J\u0014\u0010\u0014\u001a\u00020\r2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\u0016R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R*\u0010\b\u001a\u001e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00050\tj\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0005`\u000bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Landroidx/window/embedding/ExtensionEmbeddingBackend$RuleTracker;", "", "()V", "splitRules", "Landroidx/collection/ArraySet;", "Landroidx/window/embedding/EmbeddingRule;", "getSplitRules", "()Landroidx/collection/ArraySet;", "tagRuleMap", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "addOrUpdateRule", "", "rule", "throwOnDuplicateTag", "", "clearRules", "contains", "removeRule", "setRules", "rules", "", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ExtensionEmbeddingBackend.kt */
    private static final class RuleTracker {
        private final ArraySet<EmbeddingRule> splitRules = new ArraySet<>();
        private final HashMap<String, EmbeddingRule> tagRuleMap = new HashMap<>();

        public final ArraySet<EmbeddingRule> getSplitRules() {
            return this.splitRules;
        }

        public final void setRules(Set<? extends EmbeddingRule> set) {
            Intrinsics.checkNotNullParameter(set, "rules");
            clearRules();
            for (EmbeddingRule addOrUpdateRule : set) {
                addOrUpdateRule(addOrUpdateRule, true);
            }
        }

        public final void clearRules() {
            this.splitRules.clear();
            this.tagRuleMap.clear();
        }

        public static /* synthetic */ void addOrUpdateRule$default(RuleTracker ruleTracker, EmbeddingRule embeddingRule, boolean z, int i, Object obj) {
            if ((i & 2) != 0) {
                z = false;
            }
            ruleTracker.addOrUpdateRule(embeddingRule, z);
        }

        public final void addOrUpdateRule(EmbeddingRule embeddingRule, boolean z) {
            Intrinsics.checkNotNullParameter(embeddingRule, "rule");
            if (!this.splitRules.contains(embeddingRule)) {
                String tag = embeddingRule.getTag();
                if (tag == null) {
                    this.splitRules.add(embeddingRule);
                } else if (!this.tagRuleMap.containsKey(tag)) {
                    this.tagRuleMap.put(tag, embeddingRule);
                    this.splitRules.add(embeddingRule);
                } else if (!z) {
                    this.splitRules.remove(this.tagRuleMap.get(tag));
                    this.tagRuleMap.put(tag, embeddingRule);
                    this.splitRules.add(embeddingRule);
                } else {
                    throw new IllegalArgumentException("Duplicated tag: " + tag + ". Tag must be unique among all registered rules");
                }
            }
        }

        public final void removeRule(EmbeddingRule embeddingRule) {
            Intrinsics.checkNotNullParameter(embeddingRule, "rule");
            if (this.splitRules.contains(embeddingRule)) {
                this.splitRules.remove(embeddingRule);
                if (embeddingRule.getTag() != null) {
                    this.tagRuleMap.remove(embeddingRule.getTag());
                }
            }
        }

        public final boolean contains(EmbeddingRule embeddingRule) {
            Intrinsics.checkNotNullParameter(embeddingRule, "rule");
            return this.splitRules.contains(embeddingRule);
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007¢\u0006\u0002\u0010\nJ\u0014\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Landroidx/window/embedding/ExtensionEmbeddingBackend$SplitListenerWrapper;", "", "activity", "Landroid/app/Activity;", "executor", "Ljava/util/concurrent/Executor;", "callback", "Landroidx/core/util/Consumer;", "", "Landroidx/window/embedding/SplitInfo;", "(Landroid/app/Activity;Ljava/util/concurrent/Executor;Landroidx/core/util/Consumer;)V", "getCallback", "()Landroidx/core/util/Consumer;", "lastValue", "accept", "", "splitInfoList", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ExtensionEmbeddingBackend.kt */
    public static final class SplitListenerWrapper {
        private final Activity activity;
        private final Consumer<List<SplitInfo>> callback;
        private final Executor executor;
        private List<SplitInfo> lastValue;

        public SplitListenerWrapper(Activity activity2, Executor executor2, Consumer<List<SplitInfo>> consumer) {
            Intrinsics.checkNotNullParameter(activity2, "activity");
            Intrinsics.checkNotNullParameter(executor2, "executor");
            Intrinsics.checkNotNullParameter(consumer, "callback");
            this.activity = activity2;
            this.executor = executor2;
            this.callback = consumer;
        }

        public final Consumer<List<SplitInfo>> getCallback() {
            return this.callback;
        }

        public final void accept(List<SplitInfo> list) {
            Intrinsics.checkNotNullParameter(list, "splitInfoList");
            Collection arrayList = new ArrayList();
            for (Object next : list) {
                if (((SplitInfo) next).contains(this.activity)) {
                    arrayList.add(next);
                }
            }
            List<SplitInfo> list2 = (List) arrayList;
            if (!Intrinsics.areEqual((Object) list2, (Object) this.lastValue)) {
                this.lastValue = list2;
                this.executor.execute(new ExtensionEmbeddingBackend$SplitListenerWrapper$$ExternalSyntheticLambda0(this, list2));
            }
        }

        /* access modifiers changed from: private */
        public static final void accept$lambda$1(SplitListenerWrapper splitListenerWrapper, List list) {
            Intrinsics.checkNotNullParameter(splitListenerWrapper, "this$0");
            Intrinsics.checkNotNullParameter(list, "$splitsWithActivity");
            splitListenerWrapper.callback.accept(list);
        }
    }

    public void addSplitListenerForActivity(Activity activity, Executor executor, Consumer<List<SplitInfo>> consumer) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(consumer, "callback");
        Lock lock = globalLock;
        lock.lock();
        try {
            if (this.embeddingExtension == null) {
                Log.v(TAG, "Extension not loaded, skipping callback registration.");
                consumer.accept(CollectionsKt.emptyList());
                return;
            }
            SplitListenerWrapper splitListenerWrapper = new SplitListenerWrapper(activity, executor, consumer);
            this.splitChangeCallbacks.add(splitListenerWrapper);
            if (this.splitInfoEmbeddingCallback.getLastInfo() != null) {
                List<SplitInfo> lastInfo = this.splitInfoEmbeddingCallback.getLastInfo();
                Intrinsics.checkNotNull(lastInfo);
                splitListenerWrapper.accept(lastInfo);
            } else {
                splitListenerWrapper.accept(CollectionsKt.emptyList());
            }
            Unit unit = Unit.INSTANCE;
            lock.unlock();
        } finally {
            lock.unlock();
        }
    }

    public void removeSplitListenerForActivity(Consumer<List<SplitInfo>> consumer) {
        Intrinsics.checkNotNullParameter(consumer, "consumer");
        Lock lock = globalLock;
        lock.lock();
        try {
            Iterator<SplitListenerWrapper> it = this.splitChangeCallbacks.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                SplitListenerWrapper next = it.next();
                if (Intrinsics.areEqual((Object) next.getCallback(), (Object) consumer)) {
                    this.splitChangeCallbacks.remove(next);
                    break;
                }
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            lock.unlock();
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016R\"\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\r"}, d2 = {"Landroidx/window/embedding/ExtensionEmbeddingBackend$EmbeddingCallbackImpl;", "Landroidx/window/embedding/EmbeddingInterfaceCompat$EmbeddingCallbackInterface;", "(Landroidx/window/embedding/ExtensionEmbeddingBackend;)V", "lastInfo", "", "Landroidx/window/embedding/SplitInfo;", "getLastInfo", "()Ljava/util/List;", "setLastInfo", "(Ljava/util/List;)V", "onSplitInfoChanged", "", "splitInfo", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ExtensionEmbeddingBackend.kt */
    public final class EmbeddingCallbackImpl implements EmbeddingInterfaceCompat.EmbeddingCallbackInterface {
        private List<SplitInfo> lastInfo;

        public EmbeddingCallbackImpl() {
        }

        public final List<SplitInfo> getLastInfo() {
            return this.lastInfo;
        }

        public final void setLastInfo(List<SplitInfo> list) {
            this.lastInfo = list;
        }

        public void onSplitInfoChanged(List<SplitInfo> list) {
            Intrinsics.checkNotNullParameter(list, "splitInfo");
            this.lastInfo = list;
            Iterator<SplitListenerWrapper> it = ExtensionEmbeddingBackend.this.getSplitChangeCallbacks().iterator();
            while (it.hasNext()) {
                it.next().accept(list);
            }
        }
    }

    /* access modifiers changed from: private */
    public final boolean areExtensionsAvailable() {
        return this.embeddingExtension != null;
    }

    public SplitController.SplitSupportStatus getSplitSupportStatus() {
        return (SplitController.SplitSupportStatus) this.splitSupportStatus$delegate.getValue();
    }

    public boolean isActivityEmbedded(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
        if (embeddingInterfaceCompat != null) {
            return embeddingInterfaceCompat.isActivityEmbedded(activity);
        }
        return false;
    }

    public void setSplitAttributesCalculator(Function1<? super SplitAttributesCalculatorParams, SplitAttributes> function1) {
        Intrinsics.checkNotNullParameter(function1, "calculator");
        Lock lock = globalLock;
        lock.lock();
        try {
            EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
            if (embeddingInterfaceCompat != null) {
                embeddingInterfaceCompat.setSplitAttributesCalculator(function1);
                Unit unit = Unit.INSTANCE;
            }
        } finally {
            lock.unlock();
        }
    }

    public void clearSplitAttributesCalculator() {
        Lock lock = globalLock;
        lock.lock();
        try {
            EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
            if (embeddingInterfaceCompat != null) {
                embeddingInterfaceCompat.clearSplitAttributesCalculator();
                Unit unit = Unit.INSTANCE;
            }
        } finally {
            lock.unlock();
        }
    }

    public boolean isSplitAttributesCalculatorSupported() {
        EmbeddingInterfaceCompat embeddingInterfaceCompat = this.embeddingExtension;
        if (embeddingInterfaceCompat != null) {
            return embeddingInterfaceCompat.isSplitAttributesCalculatorSupported();
        }
        return false;
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Landroidx/window/embedding/ExtensionEmbeddingBackend$Api31Impl;", "", "()V", "isSplitPropertyEnabled", "Landroidx/window/embedding/SplitController$SplitSupportStatus;", "context", "Landroid/content/Context;", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ExtensionEmbeddingBackend.kt */
    private static final class Api31Impl {
        public static final Api31Impl INSTANCE = new Api31Impl();

        private Api31Impl() {
        }

        public final SplitController.SplitSupportStatus isSplitPropertyEnabled(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            try {
                PackageManager.Property property = context.getPackageManager().getProperty(WindowProperties.PROPERTY_ACTIVITY_EMBEDDING_SPLITS_ENABLED, context.getPackageName());
                Intrinsics.checkNotNullExpressionValue(property, "try {\n                co…OT_DECLARED\n            }");
                if (!property.isBoolean()) {
                    if (BuildConfig.INSTANCE.getVerificationMode() == VerificationMode.LOG) {
                        Log.w(ExtensionEmbeddingBackend.TAG, "android.window.PROPERTY_ACTIVITY_EMBEDDING_SPLITS_ENABLED must have a boolean value");
                    }
                    return SplitController.SplitSupportStatus.SPLIT_ERROR_PROPERTY_NOT_DECLARED;
                } else if (property.getBoolean()) {
                    return SplitController.SplitSupportStatus.SPLIT_AVAILABLE;
                } else {
                    return SplitController.SplitSupportStatus.SPLIT_UNAVAILABLE;
                }
            } catch (PackageManager.NameNotFoundException unused) {
                if (BuildConfig.INSTANCE.getVerificationMode() == VerificationMode.LOG) {
                    Log.w(ExtensionEmbeddingBackend.TAG, "android.window.PROPERTY_ACTIVITY_EMBEDDING_SPLITS_ENABLED must be set and enabled in AndroidManifest.xml to use splits APIs.");
                }
                return SplitController.SplitSupportStatus.SPLIT_ERROR_PROPERTY_NOT_DECLARED;
            } catch (Exception e) {
                if (BuildConfig.INSTANCE.getVerificationMode() == VerificationMode.LOG) {
                    Log.e(ExtensionEmbeddingBackend.TAG, "PackageManager.getProperty is not supported", e);
                }
                return SplitController.SplitSupportStatus.SPLIT_ERROR_PROPERTY_NOT_DECLARED;
            }
        }
    }
}
