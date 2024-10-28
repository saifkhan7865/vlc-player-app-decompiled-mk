package io.ktor.network.selector;

import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import java.io.Closeable;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.ClosedSelectorException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.YieldKt;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003:\u0001*B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0018\u001a\u00020\u000bH\u0016J\u0011\u0010\u0019\u001a\u00020\u000bHHø\u0001\u0000¢\u0006\u0002\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u0013H\u0016J'\u0010\u001d\u001a\u00020\u000b2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u001f\u001a\u00020\u0015H@ø\u0001\u0000¢\u0006\u0002\u0010 J\u001e\u0010!\u001a\u00020\u000b2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u001f\u001a\u00020\u0015H\u0002J\u0010\u0010\"\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u0013H\u0014J\u0019\u0010#\u001a\u00020$2\u0006\u0010\u001f\u001a\u00020\u0015H@ø\u0001\u0000¢\u0006\u0002\u0010%J\b\u0010&\u001a\u00020\u000bH\u0002J\u001d\u0010'\u001a\u0004\u0018\u00010\u0013*\b\u0012\u0004\u0012\u00020\u00130\u0012H@ø\u0001\u0000¢\u0006\u0002\u0010(J\u001d\u0010)\u001a\u0004\u0018\u00010\u0013*\b\u0012\u0004\u0012\u00020\u00130\u0012H@ø\u0001\u0000¢\u0006\u0002\u0010(R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R \u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\f0\nX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006+"}, d2 = {"Lio/ktor/network/selector/ActorSelectorManager;", "Lio/ktor/network/selector/SelectorManagerSupport;", "Ljava/io/Closeable;", "Lkotlinx/coroutines/CoroutineScope;", "context", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/coroutines/CoroutineContext;)V", "closed", "", "continuation", "Lio/ktor/network/selector/ActorSelectorManager$ContinuationHolder;", "", "Lkotlin/coroutines/Continuation;", "coroutineContext", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "inSelect", "selectionQueue", "Lio/ktor/network/selector/LockFreeMPSCQueue;", "Lio/ktor/network/selector/Selectable;", "selectorRef", "Ljava/nio/channels/Selector;", "wakeup", "Ljava/util/concurrent/atomic/AtomicLong;", "close", "dispatchIfNeeded", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "notifyClosed", "selectable", "process", "mb", "selector", "(Lio/ktor/network/selector/LockFreeMPSCQueue;Ljava/nio/channels/Selector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processInterests", "publishInterest", "select", "", "(Ljava/nio/channels/Selector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "selectWakeup", "receiveOrNull", "(Lio/ktor/network/selector/LockFreeMPSCQueue;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "receiveOrNullSuspend", "ContinuationHolder", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ActorSelectorManager.kt */
public final class ActorSelectorManager extends SelectorManagerSupport implements Closeable, CoroutineScope {
    /* access modifiers changed from: private */
    public volatile boolean closed;
    private final ContinuationHolder<Unit, Continuation<Unit>> continuation = new ContinuationHolder<>();
    private final CoroutineContext coroutineContext;
    private volatile boolean inSelect;
    /* access modifiers changed from: private */
    public final LockFreeMPSCQueue<Selectable> selectionQueue = new LockFreeMPSCQueue<>();
    /* access modifiers changed from: private */
    public volatile Selector selectorRef;
    private final AtomicLong wakeup = new AtomicLong();

    public ActorSelectorManager(CoroutineContext coroutineContext2) {
        Intrinsics.checkNotNullParameter(coroutineContext2, "context");
        this.coroutineContext = coroutineContext2.plus(new CoroutineName("selector"));
        Job unused = BuildersKt__Builders_commonKt.launch$default(this, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(this, (Continuation<? super AnonymousClass1>) null), 3, (Object) null);
    }

    public CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.network.selector.ActorSelectorManager$1", f = "ActorSelectorManager.kt", i = {0}, l = {43}, m = "invokeSuspend", n = {"currentSelector"}, s = {"L$2"})
    /* renamed from: io.ktor.network.selector.ActorSelectorManager$1  reason: invalid class name */
    /* compiled from: ActorSelectorManager.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        final /* synthetic */ ActorSelectorManager this$0;

        {
            this.this$0 = r1;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:41:0x00ca, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:42:0x00cb, code lost:
            kotlin.io.CloseableKt.closeFinally(r4, r9);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:43:0x00ce, code lost:
            throw r0;
         */
        /* JADX WARNING: Removed duplicated region for block: B:33:0x00a7 A[SYNTHETIC, Splitter:B:33:0x00a7] */
        /* JADX WARNING: Removed duplicated region for block: B:46:0x009f A[SYNTHETIC] */
        /* JADX WARNING: Unknown top exception splitter block from list: {B:24:0x0073=Splitter:B:24:0x0073, B:19:0x005c=Splitter:B:19:0x005c} */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object invokeSuspend(java.lang.Object r9) {
            /*
                r8 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r8.label
                r2 = 1
                r3 = 0
                if (r1 == 0) goto L_0x0026
                if (r1 != r2) goto L_0x001e
                java.lang.Object r0 = r8.L$2
                java.nio.channels.spi.AbstractSelector r0 = (java.nio.channels.spi.AbstractSelector) r0
                java.lang.Object r1 = r8.L$1
                io.ktor.network.selector.ActorSelectorManager r1 = (io.ktor.network.selector.ActorSelectorManager) r1
                java.lang.Object r4 = r8.L$0
                java.io.Closeable r4 = (java.io.Closeable) r4
                kotlin.ResultKt.throwOnFailure(r9)     // Catch:{ all -> 0x001c }
                goto L_0x005c
            L_0x001c:
                r9 = move-exception
                goto L_0x0073
            L_0x001e:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r0)
                throw r9
            L_0x0026:
                kotlin.ResultKt.throwOnFailure(r9)
                io.ktor.network.selector.ActorSelectorManager r9 = r8.this$0
                java.nio.channels.spi.SelectorProvider r9 = r9.getProvider()
                java.nio.channels.spi.AbstractSelector r9 = r9.openSelector()
                if (r9 == 0) goto L_0x00cf
                io.ktor.network.selector.ActorSelectorManager r1 = r8.this$0
                r4 = r9
                java.nio.channels.Selector r4 = (java.nio.channels.Selector) r4
                r1.selectorRef = r4
                r4 = r9
                java.io.Closeable r4 = (java.io.Closeable) r4
                io.ktor.network.selector.ActorSelectorManager r1 = r8.this$0
                r9 = r4
                java.nio.channels.spi.AbstractSelector r9 = (java.nio.channels.spi.AbstractSelector) r9     // Catch:{ all -> 0x00c8 }
                io.ktor.network.selector.LockFreeMPSCQueue r5 = r1.selectionQueue     // Catch:{ all -> 0x006f }
                r6 = r9
                java.nio.channels.Selector r6 = (java.nio.channels.Selector) r6     // Catch:{ all -> 0x006f }
                r8.L$0 = r4     // Catch:{ all -> 0x006f }
                r8.L$1 = r1     // Catch:{ all -> 0x006f }
                r8.L$2 = r9     // Catch:{ all -> 0x006f }
                r8.label = r2     // Catch:{ all -> 0x006f }
                java.lang.Object r5 = r1.process(r5, r6, r8)     // Catch:{ all -> 0x006f }
                if (r5 != r0) goto L_0x005b
                return r0
            L_0x005b:
                r0 = r9
            L_0x005c:
                r1.closed = r2     // Catch:{ all -> 0x00c8 }
                io.ktor.network.selector.LockFreeMPSCQueue r9 = r1.selectionQueue     // Catch:{ all -> 0x00c8 }
                r9.close()     // Catch:{ all -> 0x00c8 }
                r1.selectorRef = r3     // Catch:{ all -> 0x00c8 }
                java.nio.channels.Selector r0 = (java.nio.channels.Selector) r0     // Catch:{ all -> 0x00c8 }
            L_0x006b:
                r1.cancelAllSuspensions((java.nio.channels.Selector) r0, (java.lang.Throwable) r3)     // Catch:{ all -> 0x00c8 }
                goto L_0x0093
            L_0x006f:
                r0 = move-exception
                r7 = r0
                r0 = r9
                r9 = r7
            L_0x0073:
                r1.closed = r2     // Catch:{ all -> 0x00b4 }
                io.ktor.network.selector.LockFreeMPSCQueue r5 = r1.selectionQueue     // Catch:{ all -> 0x00b4 }
                r5.close()     // Catch:{ all -> 0x00b4 }
                r5 = r0
                java.nio.channels.Selector r5 = (java.nio.channels.Selector) r5     // Catch:{ all -> 0x00b4 }
                r1.cancelAllSuspensions((java.nio.channels.Selector) r5, (java.lang.Throwable) r9)     // Catch:{ all -> 0x00b4 }
                r1.closed = r2     // Catch:{ all -> 0x00c8 }
                io.ktor.network.selector.LockFreeMPSCQueue r9 = r1.selectionQueue     // Catch:{ all -> 0x00c8 }
                r9.close()     // Catch:{ all -> 0x00c8 }
                r1.selectorRef = r3     // Catch:{ all -> 0x00c8 }
                java.nio.channels.Selector r0 = (java.nio.channels.Selector) r0     // Catch:{ all -> 0x00c8 }
                goto L_0x006b
            L_0x0093:
                io.ktor.network.selector.LockFreeMPSCQueue r9 = r1.selectionQueue     // Catch:{ all -> 0x00c8 }
                java.lang.Object r9 = r9.removeFirstOrNull()     // Catch:{ all -> 0x00c8 }
                io.ktor.network.selector.Selectable r9 = (io.ktor.network.selector.Selectable) r9     // Catch:{ all -> 0x00c8 }
                if (r9 != 0) goto L_0x00a7
                kotlin.Unit r9 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00c8 }
                kotlin.io.CloseableKt.closeFinally(r4, r3)
                kotlin.Unit r9 = kotlin.Unit.INSTANCE
                return r9
            L_0x00a7:
                kotlinx.coroutines.channels.ClosedSendChannelException r0 = new kotlinx.coroutines.channels.ClosedSendChannelException     // Catch:{ all -> 0x00c8 }
                java.lang.String r2 = "Failed to apply interest: selector closed"
                r0.<init>(r2)     // Catch:{ all -> 0x00c8 }
                java.lang.Throwable r0 = (java.lang.Throwable) r0     // Catch:{ all -> 0x00c8 }
                r1.cancelAllSuspensions((io.ktor.network.selector.Selectable) r9, (java.lang.Throwable) r0)     // Catch:{ all -> 0x00c8 }
                goto L_0x0093
            L_0x00b4:
                r9 = move-exception
                r1.closed = r2     // Catch:{ all -> 0x00c8 }
                io.ktor.network.selector.LockFreeMPSCQueue r2 = r1.selectionQueue     // Catch:{ all -> 0x00c8 }
                r2.close()     // Catch:{ all -> 0x00c8 }
                r1.selectorRef = r3     // Catch:{ all -> 0x00c8 }
                java.nio.channels.Selector r0 = (java.nio.channels.Selector) r0     // Catch:{ all -> 0x00c8 }
                r1.cancelAllSuspensions((java.nio.channels.Selector) r0, (java.lang.Throwable) r3)     // Catch:{ all -> 0x00c8 }
                throw r9     // Catch:{ all -> 0x00c8 }
            L_0x00c8:
                r9 = move-exception
                throw r9     // Catch:{ all -> 0x00ca }
            L_0x00ca:
                r0 = move-exception
                kotlin.io.CloseableKt.closeFinally(r4, r9)
                throw r0
            L_0x00cf:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r0 = "openSelector() = null"
                java.lang.String r0 = r0.toString()
                r9.<init>(r0)
                goto L_0x00dc
            L_0x00db:
                throw r9
            L_0x00dc:
                goto L_0x00db
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.selector.ActorSelectorManager.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0083, code lost:
        r0.L$0 = r2;
        r0.L$1 = r11;
        r0.L$2 = r12;
        r0.label = 1;
        r13 = r2.select(r12, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x008f, code lost:
        if (r13 != r1) goto L_0x0092;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0091, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00ed, code lost:
        r0.L$0 = r2;
        r0.L$1 = r11;
        r0.L$2 = r12;
        r0.label = 3;
        r13 = r2.receiveOrNull(r11, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00f9, code lost:
        if (r13 != r1) goto L_0x00fc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00fb, code lost:
        return r1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0106 A[EDGE_INSN: B:49:0x0106->B:45:0x0106 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object process(io.ktor.network.selector.LockFreeMPSCQueue<io.ktor.network.selector.Selectable> r11, java.nio.channels.Selector r12, kotlin.coroutines.Continuation<? super kotlin.Unit> r13) {
        /*
            r10 = this;
            boolean r0 = r13 instanceof io.ktor.network.selector.ActorSelectorManager$process$1
            if (r0 == 0) goto L_0x0014
            r0 = r13
            io.ktor.network.selector.ActorSelectorManager$process$1 r0 = (io.ktor.network.selector.ActorSelectorManager$process$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L_0x0019
        L_0x0014:
            io.ktor.network.selector.ActorSelectorManager$process$1 r0 = new io.ktor.network.selector.ActorSelectorManager$process$1
            r0.<init>(r10, r13)
        L_0x0019:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            java.lang.String r3 = "selector.keys()"
            java.lang.String r4 = "selector.selectedKeys()"
            r5 = 3
            r6 = 2
            r7 = 1
            if (r2 == 0) goto L_0x0072
            if (r2 == r7) goto L_0x005f
            if (r2 == r6) goto L_0x004c
            if (r2 != r5) goto L_0x0044
            java.lang.Object r11 = r0.L$2
            java.nio.channels.Selector r11 = (java.nio.channels.Selector) r11
            java.lang.Object r12 = r0.L$1
            io.ktor.network.selector.LockFreeMPSCQueue r12 = (io.ktor.network.selector.LockFreeMPSCQueue) r12
            java.lang.Object r2 = r0.L$0
            io.ktor.network.selector.ActorSelectorManager r2 = (io.ktor.network.selector.ActorSelectorManager) r2
            kotlin.ResultKt.throwOnFailure(r13)
            r9 = r12
            r12 = r11
            r11 = r9
            goto L_0x00fc
        L_0x0044:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x004c:
            java.lang.Object r11 = r0.L$2
            java.nio.channels.Selector r11 = (java.nio.channels.Selector) r11
            java.lang.Object r12 = r0.L$1
            io.ktor.network.selector.LockFreeMPSCQueue r12 = (io.ktor.network.selector.LockFreeMPSCQueue) r12
            java.lang.Object r2 = r0.L$0
            io.ktor.network.selector.ActorSelectorManager r2 = (io.ktor.network.selector.ActorSelectorManager) r2
            kotlin.ResultKt.throwOnFailure(r13)
            r9 = r12
            r12 = r11
            r11 = r9
            goto L_0x0076
        L_0x005f:
            java.lang.Object r11 = r0.L$2
            java.nio.channels.Selector r11 = (java.nio.channels.Selector) r11
            java.lang.Object r12 = r0.L$1
            io.ktor.network.selector.LockFreeMPSCQueue r12 = (io.ktor.network.selector.LockFreeMPSCQueue) r12
            java.lang.Object r2 = r0.L$0
            io.ktor.network.selector.ActorSelectorManager r2 = (io.ktor.network.selector.ActorSelectorManager) r2
            kotlin.ResultKt.throwOnFailure(r13)
            r9 = r12
            r12 = r11
            r11 = r9
            goto L_0x0092
        L_0x0072:
            kotlin.ResultKt.throwOnFailure(r13)
            r2 = r10
        L_0x0076:
            boolean r13 = r2.closed
            if (r13 != 0) goto L_0x0106
            r2.processInterests(r11, r12)
            int r13 = r2.getPending()
            if (r13 <= 0) goto L_0x00c7
            r0.L$0 = r2
            r0.L$1 = r11
            r0.L$2 = r12
            r0.label = r7
            java.lang.Object r13 = r2.select(r12, r0)
            if (r13 != r1) goto L_0x0092
            return r1
        L_0x0092:
            java.lang.Number r13 = (java.lang.Number) r13
            int r13 = r13.intValue()
            if (r13 <= 0) goto L_0x00ac
            java.util.Set r13 = r12.selectedKeys()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r4)
            java.util.Set r8 = r12.keys()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r3)
            r2.handleSelectedKeys(r13, r8)
            goto L_0x0076
        L_0x00ac:
            java.lang.Object r13 = r11.removeFirstOrNull()
            io.ktor.network.selector.Selectable r13 = (io.ktor.network.selector.Selectable) r13
            if (r13 == 0) goto L_0x00b8
            r2.applyInterest(r12, r13)
            goto L_0x0076
        L_0x00b8:
            r0.L$0 = r2
            r0.L$1 = r11
            r0.L$2 = r12
            r0.label = r6
            java.lang.Object r13 = kotlinx.coroutines.YieldKt.yield(r0)
            if (r13 != r1) goto L_0x0076
            return r1
        L_0x00c7:
            int r13 = r2.getCancelled()
            if (r13 <= 0) goto L_0x00ed
            r12.selectNow()
            int r13 = r2.getPending()
            if (r13 <= 0) goto L_0x00e8
            java.util.Set r13 = r12.selectedKeys()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r4)
            java.util.Set r8 = r12.keys()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r3)
            r2.handleSelectedKeys(r13, r8)
            goto L_0x0076
        L_0x00e8:
            r13 = 0
            r2.setCancelled(r13)
            goto L_0x0076
        L_0x00ed:
            r0.L$0 = r2
            r0.L$1 = r11
            r0.L$2 = r12
            r0.label = r5
            java.lang.Object r13 = r2.receiveOrNull(r11, r0)
            if (r13 != r1) goto L_0x00fc
            return r1
        L_0x00fc:
            io.ktor.network.selector.Selectable r13 = (io.ktor.network.selector.Selectable) r13
            if (r13 != 0) goto L_0x0101
            goto L_0x0106
        L_0x0101:
            r2.applyInterest(r12, r13)
            goto L_0x0076
        L_0x0106:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.selector.ActorSelectorManager.process(io.ktor.network.selector.LockFreeMPSCQueue, java.nio.channels.Selector, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object select(java.nio.channels.Selector r7, kotlin.coroutines.Continuation<? super java.lang.Integer> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof io.ktor.network.selector.ActorSelectorManager$select$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            io.ktor.network.selector.ActorSelectorManager$select$1 r0 = (io.ktor.network.selector.ActorSelectorManager$select$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            io.ktor.network.selector.ActorSelectorManager$select$1 r0 = new io.ktor.network.selector.ActorSelectorManager$select$1
            r0.<init>(r6, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003a
            if (r2 != r3) goto L_0x0032
            java.lang.Object r7 = r0.L$1
            java.nio.channels.Selector r7 = (java.nio.channels.Selector) r7
            java.lang.Object r0 = r0.L$0
            io.ktor.network.selector.ActorSelectorManager r0 = (io.ktor.network.selector.ActorSelectorManager) r0
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x004d
        L_0x0032:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x003a:
            kotlin.ResultKt.throwOnFailure(r8)
            r6.inSelect = r3
            r0.L$0 = r6
            r0.L$1 = r7
            r0.label = r3
            java.lang.Object r8 = kotlinx.coroutines.YieldKt.yield(r0)
            if (r8 != r1) goto L_0x004c
            return r1
        L_0x004c:
            r0 = r6
        L_0x004d:
            java.util.concurrent.atomic.AtomicLong r8 = r0.wakeup
            long r1 = r8.get()
            r8 = 0
            r3 = 0
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 != 0) goto L_0x0063
            r1 = 500(0x1f4, double:2.47E-321)
            int r7 = r7.select(r1)
            r0.inSelect = r8
            goto L_0x006e
        L_0x0063:
            r0.inSelect = r8
            java.util.concurrent.atomic.AtomicLong r8 = r0.wakeup
            r8.set(r3)
            int r7 = r7.selectNow()
        L_0x006e:
            java.lang.Integer r7 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r7)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.selector.ActorSelectorManager.select(java.nio.channels.Selector, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final Object dispatchIfNeeded(Continuation<? super Unit> continuation2) {
        InlineMarker.mark(0);
        YieldKt.yield(continuation2);
        InlineMarker.mark(1);
        return Unit.INSTANCE;
    }

    private final void selectWakeup() {
        Selector selector;
        if (this.wakeup.incrementAndGet() == 1 && this.inSelect && (selector = this.selectorRef) != null) {
            selector.wakeup();
        }
    }

    private final void processInterests(LockFreeMPSCQueue<Selectable> lockFreeMPSCQueue, Selector selector) {
        while (true) {
            Selectable removeFirstOrNull = lockFreeMPSCQueue.removeFirstOrNull();
            if (removeFirstOrNull != null) {
                applyInterest(selector, removeFirstOrNull);
            } else {
                return;
            }
        }
    }

    public void notifyClosed(Selectable selectable) {
        SelectionKey keyFor;
        Intrinsics.checkNotNullParameter(selectable, "selectable");
        cancelAllSuspensions(selectable, (Throwable) new ClosedChannelException());
        Selector selector = this.selectorRef;
        if (selector != null && (keyFor = selectable.getChannel().keyFor(selector)) != null) {
            Intrinsics.checkNotNullExpressionValue(keyFor, "keyFor(selector)");
            keyFor.cancel();
            selectWakeup();
        }
    }

    /* access modifiers changed from: protected */
    public void publishInterest(Selectable selectable) {
        Intrinsics.checkNotNullParameter(selectable, "selectable");
        try {
            if (this.selectionQueue.addLast(selectable)) {
                this.continuation.resume(Unit.INSTANCE);
                selectWakeup();
            } else if (selectable.getChannel().isOpen()) {
                throw new ClosedSelectorException();
            } else {
                throw new ClosedChannelException();
            }
        } catch (Throwable th) {
            cancelAllSuspensions(selectable, th);
        }
    }

    /* access modifiers changed from: private */
    public final Object receiveOrNull(LockFreeMPSCQueue<Selectable> lockFreeMPSCQueue, Continuation<? super Selectable> continuation2) {
        Selectable removeFirstOrNull = lockFreeMPSCQueue.removeFirstOrNull();
        return removeFirstOrNull == null ? receiveOrNullSuspend(lockFreeMPSCQueue, continuation2) : removeFirstOrNull;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0046 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object receiveOrNullSuspend(io.ktor.network.selector.LockFreeMPSCQueue<io.ktor.network.selector.Selectable> r8, kotlin.coroutines.Continuation<? super io.ktor.network.selector.Selectable> r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof io.ktor.network.selector.ActorSelectorManager$receiveOrNullSuspend$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            io.ktor.network.selector.ActorSelectorManager$receiveOrNullSuspend$1 r0 = (io.ktor.network.selector.ActorSelectorManager$receiveOrNullSuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            io.ktor.network.selector.ActorSelectorManager$receiveOrNullSuspend$1 r0 = new io.ktor.network.selector.ActorSelectorManager$receiveOrNullSuspend$1
            r0.<init>(r7, r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003a
            if (r2 != r3) goto L_0x0032
            java.lang.Object r8 = r0.L$1
            io.ktor.network.selector.LockFreeMPSCQueue r8 = (io.ktor.network.selector.LockFreeMPSCQueue) r8
            java.lang.Object r2 = r0.L$0
            io.ktor.network.selector.ActorSelectorManager r2 = (io.ktor.network.selector.ActorSelectorManager) r2
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x003e
        L_0x0032:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x003a:
            kotlin.ResultKt.throwOnFailure(r9)
            r2 = r7
        L_0x003e:
            java.lang.Object r9 = r8.removeFirstOrNull()
            io.ktor.network.selector.Selectable r9 = (io.ktor.network.selector.Selectable) r9
            if (r9 == 0) goto L_0x0047
            return r9
        L_0x0047:
            boolean r9 = r2.closed
            r4 = 0
            if (r9 == 0) goto L_0x004d
            return r4
        L_0x004d:
            r0.L$0 = r2
            r0.L$1 = r8
            r0.label = r3
            r9 = r0
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
            io.ktor.network.selector.ActorSelectorManager$ContinuationHolder<kotlin.Unit, kotlin.coroutines.Continuation<kotlin.Unit>> r5 = r2.continuation
            boolean r6 = r8.isEmpty()
            if (r6 == 0) goto L_0x008f
            boolean r6 = r2.closed
            if (r6 != 0) goto L_0x008f
            java.util.concurrent.atomic.AtomicReference r6 = r5.ref
            boolean r6 = androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(r6, r4, r9)
            if (r6 == 0) goto L_0x0087
            boolean r6 = r8.isEmpty()
            if (r6 == 0) goto L_0x0077
            boolean r6 = r2.closed
            if (r6 != 0) goto L_0x0077
            goto L_0x0082
        L_0x0077:
            java.util.concurrent.atomic.AtomicReference r5 = r5.ref
            boolean r5 = androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(r5, r9, r4)
            if (r5 == 0) goto L_0x0082
            goto L_0x008f
        L_0x0082:
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            goto L_0x008f
        L_0x0087:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "Continuation is already set"
            r8.<init>(r9)
            throw r8
        L_0x008f:
            if (r4 != 0) goto L_0x0093
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
        L_0x0093:
            java.lang.Object r5 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r4 != r5) goto L_0x009c
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r9)
        L_0x009c:
            if (r4 != r1) goto L_0x003e
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.selector.ActorSelectorManager.receiveOrNullSuspend(io.ktor.network.selector.LockFreeMPSCQueue, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public void close() {
        this.closed = true;
        this.selectionQueue.close();
        if (!this.continuation.resume(Unit.INSTANCE)) {
            selectWakeup();
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u000e\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00010\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\u0013\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00028\u0000¢\u0006\u0002\u0010\u000bJ)\u0010\f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\r\u001a\u00028\u00012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\u000fH\bø\u0001\u0000¢\u0006\u0002\u0010\u0010R\u0016\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00010\u0007X\u0004¢\u0006\u0002\n\u0000\u0002\u0007\n\u0005\b20\u0001¨\u0006\u0011"}, d2 = {"Lio/ktor/network/selector/ActorSelectorManager$ContinuationHolder;", "R", "C", "Lkotlin/coroutines/Continuation;", "", "()V", "ref", "Ljava/util/concurrent/atomic/AtomicReference;", "resume", "", "value", "(Ljava/lang/Object;)Z", "suspendIf", "continuation", "condition", "Lkotlin/Function0;", "(Lkotlin/coroutines/Continuation;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ActorSelectorManager.kt */
    private static final class ContinuationHolder<R, C extends Continuation<? super R>> {
        /* access modifiers changed from: private */
        public final AtomicReference<C> ref = new AtomicReference<>((Object) null);

        public final boolean resume(R r) {
            Continuation continuation = (Continuation) this.ref.getAndSet((Object) null);
            if (continuation == null) {
                return false;
            }
            Result.Companion companion = Result.Companion;
            continuation.resumeWith(Result.m1774constructorimpl(r));
            return true;
        }

        public final Object suspendIf(C c, Function0<Boolean> function0) {
            Intrinsics.checkNotNullParameter(c, "continuation");
            Intrinsics.checkNotNullParameter(function0, "condition");
            if (!function0.invoke().booleanValue()) {
                return null;
            }
            if (!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.ref, (Object) null, c)) {
                throw new IllegalStateException("Continuation is already set");
            } else if (function0.invoke().booleanValue() || !LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.ref, c, (Object) null)) {
                return IntrinsicsKt.getCOROUTINE_SUSPENDED();
            } else {
                return null;
            }
        }
    }
}
