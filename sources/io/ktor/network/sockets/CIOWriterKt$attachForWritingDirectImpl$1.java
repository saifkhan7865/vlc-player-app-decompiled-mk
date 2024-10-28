package io.ktor.network.sockets;

import io.ktor.network.selector.SelectInterest;
import io.ktor.network.selector.Selectable;
import io.ktor.network.selector.SelectorManager;
import io.ktor.network.sockets.SocketOptions;
import io.ktor.utils.io.ByteChannel;
import io.ktor.utils.io.LookAheadSuspendSession;
import io.ktor.utils.io.ReaderScope;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lio/ktor/utils/io/ReaderScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.network.sockets.CIOWriterKt$attachForWritingDirectImpl$1", f = "CIOWriter.kt", i = {}, l = {88}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: CIOWriter.kt */
final class CIOWriterKt$attachForWritingDirectImpl$1 extends SuspendLambda implements Function2<ReaderScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ByteChannel $channel;
    final /* synthetic */ WritableByteChannel $nioChannel;
    final /* synthetic */ Selectable $selectable;
    final /* synthetic */ SelectorManager $selector;
    final /* synthetic */ SocketOptions.TCPClientSocketOptions $socketOptions;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CIOWriterKt$attachForWritingDirectImpl$1(Selectable selectable, ByteChannel byteChannel, WritableByteChannel writableByteChannel, SocketOptions.TCPClientSocketOptions tCPClientSocketOptions, SelectorManager selectorManager, Continuation<? super CIOWriterKt$attachForWritingDirectImpl$1> continuation) {
        super(2, continuation);
        this.$selectable = selectable;
        this.$channel = byteChannel;
        this.$nioChannel = writableByteChannel;
        this.$socketOptions = tCPClientSocketOptions;
        this.$selector = selectorManager;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        CIOWriterKt$attachForWritingDirectImpl$1 cIOWriterKt$attachForWritingDirectImpl$1 = new CIOWriterKt$attachForWritingDirectImpl$1(this.$selectable, this.$channel, this.$nioChannel, this.$socketOptions, this.$selector, continuation);
        cIOWriterKt$attachForWritingDirectImpl$1.L$0 = obj;
        return cIOWriterKt$attachForWritingDirectImpl$1;
    }

    public final Object invoke(ReaderScope readerScope, Continuation<? super Unit> continuation) {
        return ((CIOWriterKt$attachForWritingDirectImpl$1) create(readerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ReaderScope readerScope = (ReaderScope) this.L$0;
            this.$selectable.interestOp(SelectInterest.WRITE, false);
            ByteChannel byteChannel = this.$channel;
            final SocketOptions.TCPClientSocketOptions tCPClientSocketOptions = this.$socketOptions;
            final ByteChannel byteChannel2 = this.$channel;
            final WritableByteChannel writableByteChannel = this.$nioChannel;
            final Selectable selectable = this.$selectable;
            final SelectorManager selectorManager = this.$selector;
            this.label = 1;
            if (byteChannel.lookAheadSuspend(new AnonymousClass1((Continuation<? super AnonymousClass1>) null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable th) {
                this.$selectable.interestOp(SelectInterest.WRITE, false);
                if (this.$nioChannel instanceof SocketChannel) {
                    try {
                        if (JavaSocketOptionsKt.getJava7NetworkApisAvailable()) {
                            SocketChannel unused = ((SocketChannel) this.$nioChannel).shutdownOutput();
                        } else {
                            ((SocketChannel) this.$nioChannel).socket().shutdownOutput();
                        }
                    } catch (ClosedChannelException unused2) {
                    }
                }
                throw th;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.$selectable.interestOp(SelectInterest.WRITE, false);
        if (this.$nioChannel instanceof SocketChannel) {
            try {
                if (JavaSocketOptionsKt.getJava7NetworkApisAvailable()) {
                    SocketChannel unused3 = ((SocketChannel) this.$nioChannel).shutdownOutput();
                } else {
                    ((SocketChannel) this.$nioChannel).socket().shutdownOutput();
                }
            } catch (ClosedChannelException unused4) {
            }
        }
        return Unit.INSTANCE;
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lio/ktor/utils/io/LookAheadSuspendSession;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.network.sockets.CIOWriterKt$attachForWritingDirectImpl$1$1", f = "CIOWriter.kt", i = {0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 2}, l = {100, 112, 112}, m = "invokeSuspend", n = {"$this$lookAheadSuspend", "timeout", "$this$lookAheadSuspend", "timeout", "buffer", "rc", "$this$lookAheadSuspend", "timeout", "buffer", "rc", "$this$withTimeout$iv"}, s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4"})
    /* renamed from: io.ktor.network.sockets.CIOWriterKt$attachForWritingDirectImpl$1$1  reason: invalid class name */
    /* compiled from: CIOWriter.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<LookAheadSuspendSession, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(tCPClientSocketOptions, readerScope, byteChannel2, writableByteChannel, selectable, selectorManager, continuation);
            r0.L$0 = obj;
            return r0;
        }

        public final Object invoke(LookAheadSuspendSession lookAheadSuspendSession, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(lookAheadSuspendSession, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
            	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
            */
        /* JADX WARNING: Removed duplicated region for block: B:23:0x00b6  */
        /* JADX WARNING: Removed duplicated region for block: B:28:0x00db  */
        /* JADX WARNING: Removed duplicated region for block: B:32:0x00e9  */
        /* JADX WARNING: Removed duplicated region for block: B:37:0x010c  */
        public final java.lang.Object invokeSuspend(java.lang.Object r19) {
            /*
                r18 = this;
                r1 = r18
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r1.label
                r3 = 3
                r4 = 2
                r5 = 1
                r6 = 0
                if (r2 == 0) goto L_0x0073
                if (r2 == r5) goto L_0x0065
                if (r2 == r4) goto L_0x0044
                if (r2 != r3) goto L_0x003c
                java.lang.Object r2 = r1.L$7
                io.ktor.network.selector.SelectorManager r2 = (io.ktor.network.selector.SelectorManager) r2
                java.lang.Object r7 = r1.L$6
                io.ktor.network.selector.Selectable r7 = (io.ktor.network.selector.Selectable) r7
                java.lang.Object r8 = r1.L$5
                java.nio.channels.WritableByteChannel r8 = (java.nio.channels.WritableByteChannel) r8
                java.lang.Object r9 = r1.L$4
                io.ktor.network.util.Timeout r9 = (io.ktor.network.util.Timeout) r9
                java.lang.Object r10 = r1.L$3
                kotlin.jvm.internal.Ref$IntRef r10 = (kotlin.jvm.internal.Ref.IntRef) r10
                java.lang.Object r11 = r1.L$2
                java.nio.ByteBuffer r11 = (java.nio.ByteBuffer) r11
                java.lang.Object r12 = r1.L$1
                io.ktor.network.util.Timeout r12 = (io.ktor.network.util.Timeout) r12
                java.lang.Object r13 = r1.L$0
                io.ktor.utils.io.LookAheadSuspendSession r13 = (io.ktor.utils.io.LookAheadSuspendSession) r13
                kotlin.ResultKt.throwOnFailure(r19)     // Catch:{ all -> 0x0039 }
                goto L_0x016f
            L_0x0039:
                r0 = move-exception
                goto L_0x0189
            L_0x003c:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
                r0.<init>(r2)
                throw r0
            L_0x0044:
                java.lang.Object r2 = r1.L$6
                io.ktor.network.selector.SelectorManager r2 = (io.ktor.network.selector.SelectorManager) r2
                java.lang.Object r7 = r1.L$5
                io.ktor.network.selector.Selectable r7 = (io.ktor.network.selector.Selectable) r7
                java.lang.Object r8 = r1.L$4
                java.nio.channels.WritableByteChannel r8 = (java.nio.channels.WritableByteChannel) r8
                java.lang.Object r9 = r1.L$3
                kotlin.jvm.internal.Ref$IntRef r9 = (kotlin.jvm.internal.Ref.IntRef) r9
                java.lang.Object r10 = r1.L$2
                java.nio.ByteBuffer r10 = (java.nio.ByteBuffer) r10
                java.lang.Object r11 = r1.L$1
                io.ktor.network.util.Timeout r11 = (io.ktor.network.util.Timeout) r11
                java.lang.Object r12 = r1.L$0
                io.ktor.utils.io.LookAheadSuspendSession r12 = (io.ktor.utils.io.LookAheadSuspendSession) r12
                kotlin.ResultKt.throwOnFailure(r19)
                goto L_0x012c
            L_0x0065:
                java.lang.Object r2 = r1.L$1
                io.ktor.network.util.Timeout r2 = (io.ktor.network.util.Timeout) r2
                java.lang.Object r7 = r1.L$0
                io.ktor.utils.io.LookAheadSuspendSession r7 = (io.ktor.utils.io.LookAheadSuspendSession) r7
                kotlin.ResultKt.throwOnFailure(r19)
                r8 = r19
                goto L_0x00d2
            L_0x0073:
                kotlin.ResultKt.throwOnFailure(r19)
                java.lang.Object r2 = r1.L$0
                io.ktor.utils.io.LookAheadSuspendSession r2 = (io.ktor.utils.io.LookAheadSuspendSession) r2
                io.ktor.network.sockets.SocketOptions$TCPClientSocketOptions r7 = r5
                if (r7 == 0) goto L_0x0087
                long r7 = r7.getSocketTimeout()
                java.lang.Long r7 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r7)
                goto L_0x0088
            L_0x0087:
                r7 = r6
            L_0x0088:
                if (r7 == 0) goto L_0x00a9
                io.ktor.utils.io.ReaderScope r7 = r6
                r8 = r7
                kotlinx.coroutines.CoroutineScope r8 = (kotlinx.coroutines.CoroutineScope) r8
                io.ktor.network.sockets.SocketOptions$TCPClientSocketOptions r7 = r5
                long r10 = r7.getSocketTimeout()
                io.ktor.network.sockets.CIOWriterKt$attachForWritingDirectImpl$1$1$timeout$1 r7 = new io.ktor.network.sockets.CIOWriterKt$attachForWritingDirectImpl$1$1$timeout$1
                io.ktor.utils.io.ByteChannel r9 = r7
                r7.<init>(r9, r6)
                r13 = r7
                kotlin.jvm.functions.Function1 r13 = (kotlin.jvm.functions.Function1) r13
                r14 = 4
                r15 = 0
                java.lang.String r9 = "writing-direct"
                r12 = 0
                io.ktor.network.util.Timeout r7 = io.ktor.network.util.UtilsKt.createTimeout$default(r8, r9, r10, r12, r13, r14, r15)
                goto L_0x00aa
            L_0x00a9:
                r7 = r6
            L_0x00aa:
                r16 = r7
                r7 = r2
                r2 = r16
            L_0x00af:
                r8 = 0
                java.nio.ByteBuffer r8 = r7.request(r8, r5)
                if (r8 != 0) goto L_0x00e3
                r8 = r1
                kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
                r1.L$0 = r7
                r1.L$1 = r2
                r1.L$2 = r6
                r1.L$3 = r6
                r1.L$4 = r6
                r1.L$5 = r6
                r1.L$6 = r6
                r1.L$7 = r6
                r1.label = r5
                java.lang.Object r8 = r7.awaitAtLeast(r5, r8)
                if (r8 != r0) goto L_0x00d2
                return r0
            L_0x00d2:
                java.lang.Boolean r8 = (java.lang.Boolean) r8
                boolean r8 = r8.booleanValue()
                if (r8 == 0) goto L_0x00db
                goto L_0x00af
            L_0x00db:
                if (r2 == 0) goto L_0x00e2
                r2.finish()
                kotlin.Unit r6 = kotlin.Unit.INSTANCE
            L_0x00e2:
                return r6
            L_0x00e3:
                boolean r9 = r8.hasRemaining()
                if (r9 == 0) goto L_0x00af
                kotlin.jvm.internal.Ref$IntRef r9 = new kotlin.jvm.internal.Ref$IntRef
                r9.<init>()
                java.nio.channels.WritableByteChannel r10 = r8
                io.ktor.network.selector.Selectable r11 = r9
                io.ktor.network.selector.SelectorManager r12 = r10
                if (r2 != 0) goto L_0x013a
                r16 = r11
                r11 = r2
                r2 = r12
                r12 = r7
                r7 = r16
                r17 = r10
                r10 = r8
                r8 = r17
            L_0x0102:
                int r13 = r8.write(r10)
                r9.element = r13
                int r13 = r9.element
                if (r13 != 0) goto L_0x012c
                io.ktor.network.selector.SelectInterest r13 = io.ktor.network.selector.SelectInterest.WRITE
                r7.interestOp(r13, r5)
                io.ktor.network.selector.SelectInterest r13 = io.ktor.network.selector.SelectInterest.WRITE
                r1.L$0 = r12
                r1.L$1 = r11
                r1.L$2 = r10
                r1.L$3 = r9
                r1.L$4 = r8
                r1.L$5 = r7
                r1.L$6 = r2
                r1.L$7 = r6
                r1.label = r4
                java.lang.Object r13 = r2.select(r7, r13, r1)
                if (r13 != r0) goto L_0x012c
                return r0
            L_0x012c:
                boolean r13 = r10.hasRemaining()
                if (r13 == 0) goto L_0x0136
                int r13 = r9.element
                if (r13 == 0) goto L_0x0102
            L_0x0136:
                r8 = r10
                r2 = r11
                r7 = r12
                goto L_0x0182
            L_0x013a:
                r2.start()
                r13 = r7
                r7 = r11
                r11 = r8
                r8 = r10
                r10 = r9
                r9 = r2
                r2 = r12
                r12 = r9
            L_0x0145:
                int r14 = r8.write(r11)     // Catch:{ all -> 0x0039 }
                r10.element = r14     // Catch:{ all -> 0x0039 }
                int r14 = r10.element     // Catch:{ all -> 0x0039 }
                if (r14 != 0) goto L_0x016f
                io.ktor.network.selector.SelectInterest r14 = io.ktor.network.selector.SelectInterest.WRITE     // Catch:{ all -> 0x0039 }
                r7.interestOp(r14, r5)     // Catch:{ all -> 0x0039 }
                io.ktor.network.selector.SelectInterest r14 = io.ktor.network.selector.SelectInterest.WRITE     // Catch:{ all -> 0x0039 }
                r1.L$0 = r13     // Catch:{ all -> 0x0039 }
                r1.L$1 = r12     // Catch:{ all -> 0x0039 }
                r1.L$2 = r11     // Catch:{ all -> 0x0039 }
                r1.L$3 = r10     // Catch:{ all -> 0x0039 }
                r1.L$4 = r9     // Catch:{ all -> 0x0039 }
                r1.L$5 = r8     // Catch:{ all -> 0x0039 }
                r1.L$6 = r7     // Catch:{ all -> 0x0039 }
                r1.L$7 = r2     // Catch:{ all -> 0x0039 }
                r1.label = r3     // Catch:{ all -> 0x0039 }
                java.lang.Object r14 = r2.select(r7, r14, r1)     // Catch:{ all -> 0x0039 }
                if (r14 != r0) goto L_0x016f
                return r0
            L_0x016f:
                boolean r14 = r11.hasRemaining()     // Catch:{ all -> 0x0039 }
                if (r14 == 0) goto L_0x0179
                int r14 = r10.element     // Catch:{ all -> 0x0039 }
                if (r14 == 0) goto L_0x0145
            L_0x0179:
                kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0039 }
                r9.stop()
                r9 = r10
                r8 = r11
                r2 = r12
                r7 = r13
            L_0x0182:
                int r9 = r9.element
                r7.consumed(r9)
                goto L_0x00e3
            L_0x0189:
                r9.stop()
                goto L_0x018e
            L_0x018d:
                throw r0
            L_0x018e:
                goto L_0x018d
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.sockets.CIOWriterKt$attachForWritingDirectImpl$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }
}
