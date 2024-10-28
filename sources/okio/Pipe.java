package okio;

import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0011\u001a\u00020\nJ\r\u0010\u0011\u001a\u00020\nH\u0007¢\u0006\u0002\b J\r\u0010\u0018\u001a\u00020\u0019H\u0007¢\u0006\u0002\b!J&\u0010\"\u001a\u00020\u001f*\u00020\n2\u0017\u0010#\u001a\u0013\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u001f0$¢\u0006\u0002\b%H\bR\u0014\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0011\u001a\u00020\n8\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u001a\u0010\u0012\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0013\u0010\u0018\u001a\u00020\u00198\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0015\"\u0004\b\u001d\u0010\u0017¨\u0006&"}, d2 = {"Lokio/Pipe;", "", "maxBufferSize", "", "(J)V", "buffer", "Lokio/Buffer;", "getBuffer$jvm", "()Lokio/Buffer;", "foldedSink", "Lokio/Sink;", "getFoldedSink$jvm", "()Lokio/Sink;", "setFoldedSink$jvm", "(Lokio/Sink;)V", "getMaxBufferSize$jvm", "()J", "sink", "sinkClosed", "", "getSinkClosed$jvm", "()Z", "setSinkClosed$jvm", "(Z)V", "source", "Lokio/Source;", "()Lokio/Source;", "sourceClosed", "getSourceClosed$jvm", "setSourceClosed$jvm", "fold", "", "-deprecated_sink", "-deprecated_source", "forward", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "jvm"}, k = 1, mv = {1, 1, 11})
/* compiled from: Pipe.kt */
public final class Pipe {
    private final Buffer buffer = new Buffer();
    private Sink foldedSink;
    private final long maxBufferSize;
    private final Sink sink;
    private boolean sinkClosed;
    private final Source source;
    private boolean sourceClosed;

    public Pipe(long j) {
        this.maxBufferSize = j;
        if (j >= 1) {
            this.sink = new Pipe$sink$1(this);
            this.source = new Pipe$source$1(this);
            return;
        }
        throw new IllegalArgumentException(("maxBufferSize < 1: " + j).toString());
    }

    public final long getMaxBufferSize$jvm() {
        return this.maxBufferSize;
    }

    public final Buffer getBuffer$jvm() {
        return this.buffer;
    }

    public final boolean getSinkClosed$jvm() {
        return this.sinkClosed;
    }

    public final void setSinkClosed$jvm(boolean z) {
        this.sinkClosed = z;
    }

    public final boolean getSourceClosed$jvm() {
        return this.sourceClosed;
    }

    public final void setSourceClosed$jvm(boolean z) {
        this.sourceClosed = z;
    }

    public final Sink getFoldedSink$jvm() {
        return this.foldedSink;
    }

    public final void setFoldedSink$jvm(Sink sink2) {
        this.foldedSink = sink2;
    }

    public final Sink sink() {
        return this.sink;
    }

    public final Source source() {
        return this.source;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r8.write(r3, r3.size());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003e, code lost:
        if (r1 == false) goto L_0x0044;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0040, code lost:
        r8.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0044, code lost:
        r8.flush();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0048, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004b, code lost:
        monitor-enter(r7.buffer);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r7.sourceClosed = true;
        r1 = r7.buffer;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0050, code lost:
        if (r1 == null) goto L_0x0052;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0059, code lost:
        throw new kotlin.TypeCastException("null cannot be cast to non-null type java.lang.Object");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x005a, code lost:
        r1.notifyAll();
        r1 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0062, code lost:
        throw r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void fold(okio.Sink r8) throws java.io.IOException {
        /*
            r7 = this;
            java.lang.String r0 = "sink"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r0)
        L_0x0005:
            okio.Buffer r0 = r7.buffer
            monitor-enter(r0)
            okio.Sink r1 = r7.foldedSink     // Catch:{ all -> 0x007c }
            if (r1 != 0) goto L_0x006e
            okio.Buffer r1 = r7.buffer     // Catch:{ all -> 0x007c }
            boolean r1 = r1.exhausted()     // Catch:{ all -> 0x007c }
            r2 = 1
            if (r1 == 0) goto L_0x001b
            r7.sourceClosed = r2     // Catch:{ all -> 0x007c }
            r7.foldedSink = r8     // Catch:{ all -> 0x007c }
            monitor-exit(r0)
            return
        L_0x001b:
            boolean r1 = r7.sinkClosed     // Catch:{ all -> 0x007c }
            okio.Buffer r3 = new okio.Buffer     // Catch:{ all -> 0x007c }
            r3.<init>()     // Catch:{ all -> 0x007c }
            okio.Buffer r4 = r7.buffer     // Catch:{ all -> 0x007c }
            long r5 = r4.size()     // Catch:{ all -> 0x007c }
            r3.write((okio.Buffer) r4, (long) r5)     // Catch:{ all -> 0x007c }
            okio.Buffer r4 = r7.buffer     // Catch:{ all -> 0x007c }
            if (r4 == 0) goto L_0x0066
            java.lang.Object r4 = (java.lang.Object) r4     // Catch:{ all -> 0x007c }
            r4.notifyAll()     // Catch:{ all -> 0x007c }
            kotlin.Unit r4 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x007c }
            monitor-exit(r0)
            long r4 = r3.size()     // Catch:{ all -> 0x0048 }
            r8.write(r3, r4)     // Catch:{ all -> 0x0048 }
            if (r1 == 0) goto L_0x0044
            r8.close()     // Catch:{ all -> 0x0048 }
            goto L_0x0005
        L_0x0044:
            r8.flush()     // Catch:{ all -> 0x0048 }
            goto L_0x0005
        L_0x0048:
            r8 = move-exception
            okio.Buffer r0 = r7.buffer
            monitor-enter(r0)
            r7.sourceClosed = r2     // Catch:{ all -> 0x0063 }
            okio.Buffer r1 = r7.buffer     // Catch:{ all -> 0x0063 }
            if (r1 != 0) goto L_0x005a
            kotlin.TypeCastException r8 = new kotlin.TypeCastException     // Catch:{ all -> 0x0063 }
            java.lang.String r1 = "null cannot be cast to non-null type java.lang.Object"
            r8.<init>(r1)     // Catch:{ all -> 0x0063 }
            throw r8     // Catch:{ all -> 0x0063 }
        L_0x005a:
            java.lang.Object r1 = (java.lang.Object) r1     // Catch:{ all -> 0x0063 }
            r1.notifyAll()     // Catch:{ all -> 0x0063 }
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0063 }
            monitor-exit(r0)
            throw r8
        L_0x0063:
            r8 = move-exception
            monitor-exit(r0)
            throw r8
        L_0x0066:
            kotlin.TypeCastException r8 = new kotlin.TypeCastException     // Catch:{ all -> 0x007c }
            java.lang.String r1 = "null cannot be cast to non-null type java.lang.Object"
            r8.<init>(r1)     // Catch:{ all -> 0x007c }
            throw r8     // Catch:{ all -> 0x007c }
        L_0x006e:
            java.lang.String r8 = "sink already folded"
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException     // Catch:{ all -> 0x007c }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x007c }
            r1.<init>(r8)     // Catch:{ all -> 0x007c }
            java.lang.Throwable r1 = (java.lang.Throwable) r1     // Catch:{ all -> 0x007c }
            throw r1     // Catch:{ all -> 0x007c }
        L_0x007c:
            r8 = move-exception
            monitor-exit(r0)
            goto L_0x0080
        L_0x007f:
            throw r8
        L_0x0080:
            goto L_0x007f
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Pipe.fold(okio.Sink):void");
    }

    /* access modifiers changed from: private */
    public final void forward(Sink sink2, Function1<? super Sink, Unit> function1) {
        Timeout timeout = sink2.timeout();
        Timeout timeout2 = sink().timeout();
        long timeoutNanos = timeout.timeoutNanos();
        timeout.timeout(Timeout.Companion.minTimeout(timeout2.timeoutNanos(), timeout.timeoutNanos()), TimeUnit.NANOSECONDS);
        if (timeout.hasDeadline()) {
            long deadlineNanoTime = timeout.deadlineNanoTime();
            if (timeout2.hasDeadline()) {
                timeout.deadlineNanoTime(Math.min(timeout.deadlineNanoTime(), timeout2.deadlineNanoTime()));
            }
            try {
                function1.invoke(sink2);
            } finally {
                InlineMarker.finallyStart(1);
                timeout.timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                if (timeout2.hasDeadline()) {
                    timeout.deadlineNanoTime(deadlineNanoTime);
                }
                InlineMarker.finallyEnd(1);
            }
        } else {
            if (timeout2.hasDeadline()) {
                timeout.deadlineNanoTime(timeout2.deadlineNanoTime());
            }
            try {
                function1.invoke(sink2);
            } finally {
                InlineMarker.finallyStart(1);
                timeout.timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                if (timeout2.hasDeadline()) {
                    timeout.clearDeadline();
                }
                InlineMarker.finallyEnd(1);
            }
        }
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "sink", imports = {}))
    /* renamed from: -deprecated_sink  reason: not valid java name */
    public final Sink m1297deprecated_sink() {
        return this.sink;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "source", imports = {}))
    /* renamed from: -deprecated_source  reason: not valid java name */
    public final Source m1298deprecated_source() {
        return this.source;
    }
}
