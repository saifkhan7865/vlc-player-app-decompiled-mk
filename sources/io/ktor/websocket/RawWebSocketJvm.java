package io.ktor.websocket;

import androidx.tvprovider.media.tv.TvContractCompat;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteWriteChannel;
import io.ktor.utils.io.pool.ObjectPool;
import io.ktor.websocket.WebSocketSession;
import java.nio.ByteBuffer;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.channels.SendChannel;

@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\u0002\u0010\u000fJ\u0011\u00108\u001a\u000209H@ø\u0001\u0000¢\u0006\u0002\u0010:J\b\u0010;\u001a\u000209H\u0017R\u0014\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0012\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00140\u00138VX\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u001b8VX\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR+\u0010\b\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\t8V@VX\u0002¢\u0006\u0012\n\u0004\b#\u0010$\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R+\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u00078V@VX\u0002¢\u0006\u0012\n\u0004\b)\u0010$\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001a\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00190+8VX\u0004¢\u0006\u0006\u001a\u0004\b,\u0010-R\u0014\u0010.\u001a\u00020/X\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u000e\u00102\u001a\u000203X\u0004¢\u0006\u0002\n\u0000R\u0014\u00104\u001a\u000205X\u0004¢\u0006\b\n\u0000\u001a\u0004\b6\u00107\u0002\u0004\n\u0002\b\u0019¨\u0006<"}, d2 = {"Lio/ktor/websocket/RawWebSocketJvm;", "Lio/ktor/websocket/WebSocketSession;", "input", "Lio/ktor/utils/io/ByteReadChannel;", "output", "Lio/ktor/utils/io/ByteWriteChannel;", "maxFrameSize", "", "masking", "", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "pool", "Lio/ktor/utils/io/pool/ObjectPool;", "Ljava/nio/ByteBuffer;", "(Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/ByteWriteChannel;JZLkotlin/coroutines/CoroutineContext;Lio/ktor/utils/io/pool/ObjectPool;)V", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "extensions", "", "Lio/ktor/websocket/WebSocketExtension;", "getExtensions", "()Ljava/util/List;", "filtered", "Lkotlinx/coroutines/channels/Channel;", "Lio/ktor/websocket/Frame;", "incoming", "Lkotlinx/coroutines/channels/ReceiveChannel;", "getIncoming", "()Lkotlinx/coroutines/channels/ReceiveChannel;", "<set-?>", "getMasking", "()Z", "setMasking", "(Z)V", "masking$delegate", "Lkotlin/properties/ReadWriteProperty;", "getMaxFrameSize", "()J", "setMaxFrameSize", "(J)V", "maxFrameSize$delegate", "outgoing", "Lkotlinx/coroutines/channels/SendChannel;", "getOutgoing", "()Lkotlinx/coroutines/channels/SendChannel;", "reader", "Lio/ktor/websocket/WebSocketReader;", "getReader$ktor_websockets", "()Lio/ktor/websocket/WebSocketReader;", "socketJob", "Lkotlinx/coroutines/CompletableJob;", "writer", "Lio/ktor/websocket/WebSocketWriter;", "getWriter$ktor_websockets", "()Lio/ktor/websocket/WebSocketWriter;", "flush", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "terminate", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RawWebSocketJvm.kt */
public final class RawWebSocketJvm implements WebSocketSession {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    private final CoroutineContext coroutineContext;
    /* access modifiers changed from: private */
    public final Channel<Frame> filtered;
    private final ReadWriteProperty masking$delegate;
    private final ReadWriteProperty maxFrameSize$delegate;
    private final WebSocketReader reader;
    private final CompletableJob socketJob;
    private final WebSocketWriter writer;

    public RawWebSocketJvm(ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, long j, boolean z, CoroutineContext coroutineContext2, ObjectPool<ByteBuffer> objectPool) {
        ByteWriteChannel byteWriteChannel2 = byteWriteChannel;
        CoroutineContext coroutineContext3 = coroutineContext2;
        ObjectPool<ByteBuffer> objectPool2 = objectPool;
        ByteReadChannel byteReadChannel2 = byteReadChannel;
        Intrinsics.checkNotNullParameter(byteReadChannel, TvContractCompat.PARAM_INPUT);
        Intrinsics.checkNotNullParameter(byteWriteChannel, "output");
        Intrinsics.checkNotNullParameter(coroutineContext3, "coroutineContext");
        Intrinsics.checkNotNullParameter(objectPool2, "pool");
        CompletableJob Job = JobKt.Job((Job) coroutineContext3.get(Job.Key));
        this.socketJob = Job;
        this.filtered = ChannelKt.Channel$default(0, (BufferOverflow) null, (Function1) null, 6, (Object) null);
        this.coroutineContext = coroutineContext3.plus(Job).plus(new CoroutineName("raw-ws"));
        Delegates delegates = Delegates.INSTANCE;
        this.maxFrameSize$delegate = new RawWebSocketJvm$special$$inlined$observable$1(Long.valueOf(j), this);
        Delegates delegates2 = Delegates.INSTANCE;
        this.masking$delegate = new RawWebSocketJvm$special$$inlined$observable$2(Boolean.valueOf(z), this);
        boolean z2 = z;
        this.writer = new WebSocketWriter(byteWriteChannel, getCoroutineContext(), z, objectPool2);
        this.reader = new WebSocketReader(byteReadChannel, getCoroutineContext(), j, objectPool2);
        Job unused = BuildersKt__Builders_commonKt.launch$default(this, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(this, (Continuation<? super AnonymousClass1>) null), 3, (Object) null);
        Job.complete();
    }

    public Object send(Frame frame, Continuation<? super Unit> continuation) {
        return WebSocketSession.DefaultImpls.send(this, frame, continuation);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ RawWebSocketJvm(io.ktor.utils.io.ByteReadChannel r11, io.ktor.utils.io.ByteWriteChannel r12, long r13, boolean r15, kotlin.coroutines.CoroutineContext r16, io.ktor.utils.io.pool.ObjectPool r17, int r18, kotlin.jvm.internal.DefaultConstructorMarker r19) {
        /*
            r10 = this;
            r0 = r18 & 4
            if (r0 == 0) goto L_0x0009
            r0 = 2147483647(0x7fffffff, double:1.060997895E-314)
            r5 = r0
            goto L_0x000a
        L_0x0009:
            r5 = r13
        L_0x000a:
            r0 = r18 & 8
            if (r0 == 0) goto L_0x0011
            r0 = 0
            r7 = 0
            goto L_0x0012
        L_0x0011:
            r7 = r15
        L_0x0012:
            r0 = r18 & 32
            if (r0 == 0) goto L_0x001c
            io.ktor.utils.io.pool.ObjectPool r0 = io.ktor.util.cio.ByteBufferPoolKt.getKtorDefaultPool()
            r9 = r0
            goto L_0x001e
        L_0x001c:
            r9 = r17
        L_0x001e:
            r2 = r10
            r3 = r11
            r4 = r12
            r8 = r16
            r2.<init>(r3, r4, r5, r7, r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.RawWebSocketJvm.<init>(io.ktor.utils.io.ByteReadChannel, io.ktor.utils.io.ByteWriteChannel, long, boolean, kotlin.coroutines.CoroutineContext, io.ktor.utils.io.pool.ObjectPool, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }

    public ReceiveChannel<Frame> getIncoming() {
        return this.filtered;
    }

    public SendChannel<Frame> getOutgoing() {
        return this.writer.getOutgoing();
    }

    public List<WebSocketExtension<?>> getExtensions() {
        return CollectionsKt.emptyList();
    }

    static {
        Class<RawWebSocketJvm> cls = RawWebSocketJvm.class;
        $$delegatedProperties = new KProperty[]{Reflection.mutableProperty1(new MutablePropertyReference1Impl(cls, "maxFrameSize", "getMaxFrameSize()J", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(cls, "masking", "getMasking()Z", 0))};
    }

    public long getMaxFrameSize() {
        return ((Number) this.maxFrameSize$delegate.getValue(this, $$delegatedProperties[0])).longValue();
    }

    public void setMaxFrameSize(long j) {
        this.maxFrameSize$delegate.setValue(this, $$delegatedProperties[0], Long.valueOf(j));
    }

    public boolean getMasking() {
        return ((Boolean) this.masking$delegate.getValue(this, $$delegatedProperties[1])).booleanValue();
    }

    public void setMasking(boolean z) {
        this.masking$delegate.setValue(this, $$delegatedProperties[1], Boolean.valueOf(z));
    }

    public final WebSocketWriter getWriter$ktor_websockets() {
        return this.writer;
    }

    public final WebSocketReader getReader$ktor_websockets() {
        return this.reader;
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.websocket.RawWebSocketJvm$1", f = "RawWebSocketJvm.kt", i = {2, 3}, l = {67, 68, 71, 74}, m = "invokeSuspend", n = {"cause", "cause"}, s = {"L$0", "L$0"})
    /* renamed from: io.ktor.websocket.RawWebSocketJvm$1  reason: invalid class name */
    /* compiled from: RawWebSocketJvm.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;
        final /* synthetic */ RawWebSocketJvm this$0;

        {
            this.this$0 = r1;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:14:0x002f, code lost:
            r11 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x0087, code lost:
            r11 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
            io.ktor.websocket.RawWebSocketJvm.access$getFiltered$p(r10.this$0).close(r11);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:38:0x009e, code lost:
            r11 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
            r10.this$0.getReader$ktor_websockets().getIncoming().cancel(r11);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:41:0x00ad, code lost:
            r11 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:42:0x00ae, code lost:
            r10.L$0 = r11;
            r10.label = 4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:43:0x00cf, code lost:
            if (r10.this$0.getOutgoing().send(new io.ktor.websocket.Frame.Close(new io.ktor.websocket.CloseReason(io.ktor.websocket.CloseReason.Codes.PROTOCOL_ERROR, r11.getMessage())), r10) == r0) goto L_0x00d1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:44:0x00d1, code lost:
            return r0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:45:0x00d2, code lost:
            r0 = r11;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:47:0x00df, code lost:
            r11 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:48:0x00e0, code lost:
            r10.L$0 = r11;
            r10.label = 3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:49:0x0101, code lost:
            if (r10.this$0.getOutgoing().send(new io.ktor.websocket.Frame.Close(new io.ktor.websocket.CloseReason(io.ktor.websocket.CloseReason.Codes.TOO_BIG, r11.getMessage())), r10) == r0) goto L_0x0103;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:50:0x0103, code lost:
            return r0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:51:0x0104, code lost:
            r0 = r11;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:55:0x0114, code lost:
            kotlinx.coroutines.channels.SendChannel.DefaultImpls.close$default(io.ktor.websocket.RawWebSocketJvm.access$getFiltered$p(r10.this$0), (java.lang.Throwable) null, 1, (java.lang.Object) null);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:56:0x0120, code lost:
            throw r11;
         */
        /* JADX WARNING: Exception block dominator not found, dom blocks: [B:7:0x0019, B:16:0x0036] */
        /* JADX WARNING: Removed duplicated region for block: B:28:0x0062 A[Catch:{ FrameTooBigException -> 0x00df, ProtocolViolationException -> 0x00ad, CancellationException -> 0x009e, all -> 0x0087, all -> 0x002f }] */
        /* JADX WARNING: Removed duplicated region for block: B:31:0x006d A[Catch:{ FrameTooBigException -> 0x00df, ProtocolViolationException -> 0x00ad, CancellationException -> 0x009e, all -> 0x0087, all -> 0x002f }] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object invokeSuspend(java.lang.Object r11) {
            /*
                r10 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r10.label
                r2 = 4
                r3 = 3
                r4 = 2
                r5 = 1
                r6 = 0
                if (r1 == 0) goto L_0x0043
                if (r1 == r5) goto L_0x003b
                if (r1 == r4) goto L_0x0032
                if (r1 == r3) goto L_0x0026
                if (r1 != r2) goto L_0x001e
                java.lang.Object r0 = r10.L$0
                io.ktor.websocket.ProtocolViolationException r0 = (io.ktor.websocket.ProtocolViolationException) r0
                kotlin.ResultKt.throwOnFailure(r11)     // Catch:{ all -> 0x002f }
                goto L_0x00d3
            L_0x001e:
                java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r11.<init>(r0)
                throw r11
            L_0x0026:
                java.lang.Object r0 = r10.L$0
                io.ktor.websocket.FrameTooBigException r0 = (io.ktor.websocket.FrameTooBigException) r0
                kotlin.ResultKt.throwOnFailure(r11)     // Catch:{ all -> 0x002f }
                goto L_0x0105
            L_0x002f:
                r11 = move-exception
                goto L_0x0114
            L_0x0032:
                java.lang.Object r1 = r10.L$0
                kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
                kotlin.ResultKt.throwOnFailure(r11)     // Catch:{ FrameTooBigException -> 0x00df, ProtocolViolationException -> 0x00ad, CancellationException -> 0x009e, all -> 0x0087 }
            L_0x0039:
                r11 = r1
                goto L_0x0054
            L_0x003b:
                java.lang.Object r1 = r10.L$0
                kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
                kotlin.ResultKt.throwOnFailure(r11)     // Catch:{ FrameTooBigException -> 0x00df, ProtocolViolationException -> 0x00ad, CancellationException -> 0x009e, all -> 0x0087 }
                goto L_0x0065
            L_0x0043:
                kotlin.ResultKt.throwOnFailure(r11)
                io.ktor.websocket.RawWebSocketJvm r11 = r10.this$0     // Catch:{ FrameTooBigException -> 0x00df, ProtocolViolationException -> 0x00ad, CancellationException -> 0x009e, all -> 0x0087 }
                io.ktor.websocket.WebSocketReader r11 = r11.getReader$ktor_websockets()     // Catch:{ FrameTooBigException -> 0x00df, ProtocolViolationException -> 0x00ad, CancellationException -> 0x009e, all -> 0x0087 }
                kotlinx.coroutines.channels.ReceiveChannel r11 = r11.getIncoming()     // Catch:{ FrameTooBigException -> 0x00df, ProtocolViolationException -> 0x00ad, CancellationException -> 0x009e, all -> 0x0087 }
                kotlinx.coroutines.channels.ChannelIterator r11 = r11.iterator()     // Catch:{ FrameTooBigException -> 0x00df, ProtocolViolationException -> 0x00ad, CancellationException -> 0x009e, all -> 0x0087 }
            L_0x0054:
                r1 = r10
                kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1     // Catch:{ FrameTooBigException -> 0x00df, ProtocolViolationException -> 0x00ad, CancellationException -> 0x009e, all -> 0x0087 }
                r10.L$0 = r11     // Catch:{ FrameTooBigException -> 0x00df, ProtocolViolationException -> 0x00ad, CancellationException -> 0x009e, all -> 0x0087 }
                r10.label = r5     // Catch:{ FrameTooBigException -> 0x00df, ProtocolViolationException -> 0x00ad, CancellationException -> 0x009e, all -> 0x0087 }
                java.lang.Object r1 = r11.hasNext(r1)     // Catch:{ FrameTooBigException -> 0x00df, ProtocolViolationException -> 0x00ad, CancellationException -> 0x009e, all -> 0x0087 }
                if (r1 != r0) goto L_0x0062
                return r0
            L_0x0062:
                r9 = r1
                r1 = r11
                r11 = r9
            L_0x0065:
                java.lang.Boolean r11 = (java.lang.Boolean) r11     // Catch:{ FrameTooBigException -> 0x00df, ProtocolViolationException -> 0x00ad, CancellationException -> 0x009e, all -> 0x0087 }
                boolean r11 = r11.booleanValue()     // Catch:{ FrameTooBigException -> 0x00df, ProtocolViolationException -> 0x00ad, CancellationException -> 0x009e, all -> 0x0087 }
                if (r11 == 0) goto L_0x0091
                java.lang.Object r11 = r1.next()     // Catch:{ FrameTooBigException -> 0x00df, ProtocolViolationException -> 0x00ad, CancellationException -> 0x009e, all -> 0x0087 }
                io.ktor.websocket.Frame r11 = (io.ktor.websocket.Frame) r11     // Catch:{ FrameTooBigException -> 0x00df, ProtocolViolationException -> 0x00ad, CancellationException -> 0x009e, all -> 0x0087 }
                io.ktor.websocket.RawWebSocketJvm r7 = r10.this$0     // Catch:{ FrameTooBigException -> 0x00df, ProtocolViolationException -> 0x00ad, CancellationException -> 0x009e, all -> 0x0087 }
                kotlinx.coroutines.channels.Channel r7 = r7.filtered     // Catch:{ FrameTooBigException -> 0x00df, ProtocolViolationException -> 0x00ad, CancellationException -> 0x009e, all -> 0x0087 }
                r8 = r10
                kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8     // Catch:{ FrameTooBigException -> 0x00df, ProtocolViolationException -> 0x00ad, CancellationException -> 0x009e, all -> 0x0087 }
                r10.L$0 = r1     // Catch:{ FrameTooBigException -> 0x00df, ProtocolViolationException -> 0x00ad, CancellationException -> 0x009e, all -> 0x0087 }
                r10.label = r4     // Catch:{ FrameTooBigException -> 0x00df, ProtocolViolationException -> 0x00ad, CancellationException -> 0x009e, all -> 0x0087 }
                java.lang.Object r11 = r7.send(r11, r8)     // Catch:{ FrameTooBigException -> 0x00df, ProtocolViolationException -> 0x00ad, CancellationException -> 0x009e, all -> 0x0087 }
                if (r11 != r0) goto L_0x0039
                return r0
            L_0x0087:
                r11 = move-exception
                io.ktor.websocket.RawWebSocketJvm r0 = r10.this$0     // Catch:{ all -> 0x002f }
                kotlinx.coroutines.channels.Channel r0 = r0.filtered     // Catch:{ all -> 0x002f }
                r0.close(r11)     // Catch:{ all -> 0x002f }
            L_0x0091:
                io.ktor.websocket.RawWebSocketJvm r11 = r10.this$0
                kotlinx.coroutines.channels.Channel r11 = r11.filtered
                kotlinx.coroutines.channels.SendChannel r11 = (kotlinx.coroutines.channels.SendChannel) r11
                kotlinx.coroutines.channels.SendChannel.DefaultImpls.close$default(r11, r6, r5, r6)
                goto L_0x0111
            L_0x009e:
                r11 = move-exception
                io.ktor.websocket.RawWebSocketJvm r0 = r10.this$0     // Catch:{ all -> 0x002f }
                io.ktor.websocket.WebSocketReader r0 = r0.getReader$ktor_websockets()     // Catch:{ all -> 0x002f }
                kotlinx.coroutines.channels.ReceiveChannel r0 = r0.getIncoming()     // Catch:{ all -> 0x002f }
                r0.cancel((java.util.concurrent.CancellationException) r11)     // Catch:{ all -> 0x002f }
                goto L_0x0091
            L_0x00ad:
                r11 = move-exception
                io.ktor.websocket.RawWebSocketJvm r1 = r10.this$0     // Catch:{ all -> 0x002f }
                kotlinx.coroutines.channels.SendChannel r1 = r1.getOutgoing()     // Catch:{ all -> 0x002f }
                io.ktor.websocket.Frame$Close r3 = new io.ktor.websocket.Frame$Close     // Catch:{ all -> 0x002f }
                io.ktor.websocket.CloseReason r4 = new io.ktor.websocket.CloseReason     // Catch:{ all -> 0x002f }
                io.ktor.websocket.CloseReason$Codes r7 = io.ktor.websocket.CloseReason.Codes.PROTOCOL_ERROR     // Catch:{ all -> 0x002f }
                java.lang.String r8 = r11.getMessage()     // Catch:{ all -> 0x002f }
                r4.<init>((io.ktor.websocket.CloseReason.Codes) r7, (java.lang.String) r8)     // Catch:{ all -> 0x002f }
                r3.<init>((io.ktor.websocket.CloseReason) r4)     // Catch:{ all -> 0x002f }
                r4 = r10
                kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4     // Catch:{ all -> 0x002f }
                r10.L$0 = r11     // Catch:{ all -> 0x002f }
                r10.label = r2     // Catch:{ all -> 0x002f }
                java.lang.Object r1 = r1.send(r3, r4)     // Catch:{ all -> 0x002f }
                if (r1 != r0) goto L_0x00d2
                return r0
            L_0x00d2:
                r0 = r11
            L_0x00d3:
                io.ktor.websocket.RawWebSocketJvm r11 = r10.this$0     // Catch:{ all -> 0x002f }
                kotlinx.coroutines.channels.Channel r11 = r11.filtered     // Catch:{ all -> 0x002f }
                java.lang.Throwable r0 = (java.lang.Throwable) r0     // Catch:{ all -> 0x002f }
                r11.close(r0)     // Catch:{ all -> 0x002f }
                goto L_0x0091
            L_0x00df:
                r11 = move-exception
                io.ktor.websocket.RawWebSocketJvm r1 = r10.this$0     // Catch:{ all -> 0x002f }
                kotlinx.coroutines.channels.SendChannel r1 = r1.getOutgoing()     // Catch:{ all -> 0x002f }
                io.ktor.websocket.Frame$Close r2 = new io.ktor.websocket.Frame$Close     // Catch:{ all -> 0x002f }
                io.ktor.websocket.CloseReason r4 = new io.ktor.websocket.CloseReason     // Catch:{ all -> 0x002f }
                io.ktor.websocket.CloseReason$Codes r7 = io.ktor.websocket.CloseReason.Codes.TOO_BIG     // Catch:{ all -> 0x002f }
                java.lang.String r8 = r11.getMessage()     // Catch:{ all -> 0x002f }
                r4.<init>((io.ktor.websocket.CloseReason.Codes) r7, (java.lang.String) r8)     // Catch:{ all -> 0x002f }
                r2.<init>((io.ktor.websocket.CloseReason) r4)     // Catch:{ all -> 0x002f }
                r4 = r10
                kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4     // Catch:{ all -> 0x002f }
                r10.L$0 = r11     // Catch:{ all -> 0x002f }
                r10.label = r3     // Catch:{ all -> 0x002f }
                java.lang.Object r1 = r1.send(r2, r4)     // Catch:{ all -> 0x002f }
                if (r1 != r0) goto L_0x0104
                return r0
            L_0x0104:
                r0 = r11
            L_0x0105:
                io.ktor.websocket.RawWebSocketJvm r11 = r10.this$0     // Catch:{ all -> 0x002f }
                kotlinx.coroutines.channels.Channel r11 = r11.filtered     // Catch:{ all -> 0x002f }
                java.lang.Throwable r0 = (java.lang.Throwable) r0     // Catch:{ all -> 0x002f }
                r11.close(r0)     // Catch:{ all -> 0x002f }
                goto L_0x0091
            L_0x0111:
                kotlin.Unit r11 = kotlin.Unit.INSTANCE
                return r11
            L_0x0114:
                io.ktor.websocket.RawWebSocketJvm r0 = r10.this$0
                kotlinx.coroutines.channels.Channel r0 = r0.filtered
                kotlinx.coroutines.channels.SendChannel r0 = (kotlinx.coroutines.channels.SendChannel) r0
                kotlinx.coroutines.channels.SendChannel.DefaultImpls.close$default(r0, r6, r5, r6)
                goto L_0x0121
            L_0x0120:
                throw r11
            L_0x0121:
                goto L_0x0120
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.RawWebSocketJvm.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public Object flush(Continuation<? super Unit> continuation) {
        Object flush = this.writer.flush(continuation);
        return flush == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? flush : Unit.INSTANCE;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use cancel() instead.", replaceWith = @ReplaceWith(expression = "cancel()", imports = {"kotlinx.coroutines.cancel"}))
    public void terminate() {
        SendChannel.DefaultImpls.close$default(getOutgoing(), (Throwable) null, 1, (Object) null);
        this.socketJob.complete();
    }
}
