package io.ktor.websocket;

import io.ktor.websocket.CloseReason;
import io.ktor.websocket.DefaultWebSocketSession;
import io.ktor.websocket.Frame;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.channels.SendChannel;
import org.slf4j.Logger;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\b\u0000\u0018\u0000 e2\u00020g2\u00020\u0001:\u0001eB\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J%\u0010\r\u001a\u00020\f2\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000b\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eJ\u0013\u0010\u000f\u001a\u00020\fH@ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\u001d\u0010\u0013\u001a\u00020\f2\b\b\u0002\u0010\u0012\u001a\u00020\u0011H@ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\fH@ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0010J\u0017\u0010\u0016\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u0018\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\u0018\u0010\u0017J\u001d\u0010\u001d\u001a\u00020\u001c2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019H\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u0010\u001f\u001a\u00020\fH\u0002¢\u0006\u0004\b\u001f\u0010 J\u000f\u0010!\u001a\u00020\u001cH\u0002¢\u0006\u0004\b!\u0010\"J)\u0010'\u001a\u00020\f2\b\u0010$\u001a\u0004\u0018\u00010#2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010%H@ø\u0001\u0000¢\u0006\u0004\b'\u0010(J!\u0010,\u001a\u00020\f2\u0010\u0010+\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030*0)H\u0016¢\u0006\u0004\b,\u0010-J\u000f\u0010.\u001a\u00020\fH\u0017¢\u0006\u0004\b.\u0010 J\u000f\u00100\u001a\u00020/H\u0002¢\u0006\u0004\b0\u00101R\u001e\u00103\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030*028\u0002X\u0004¢\u0006\u0006\n\u0004\b3\u00104R\"\u00106\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010#058\u0016X\u0004¢\u0006\f\n\u0004\b6\u00107\u001a\u0004\b8\u00109R\u001a\u0010;\u001a\b\u0012\u0004\u0012\u00020#0:8\u0002X\u0004¢\u0006\u0006\n\u0004\b;\u0010<R\u0014\u0010>\u001a\u00020=8\u0002X\u0004¢\u0006\u0006\n\u0004\b>\u0010?R\u001a\u0010A\u001a\u00020@8\u0016X\u0004¢\u0006\f\n\u0004\bA\u0010B\u001a\u0004\bC\u0010DR\u001e\u0010G\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030*0)8VX\u0004¢\u0006\u0006\u001a\u0004\bE\u0010FR\u001a\u0010I\u001a\b\u0012\u0004\u0012\u00020\n0H8\u0002X\u0004¢\u0006\u0006\n\u0004\bI\u0010JR\u001a\u0010N\u001a\b\u0012\u0004\u0012\u00020\n0K8VX\u0004¢\u0006\u0006\u001a\u0004\bL\u0010MR$\u0010S\u001a\u00020/2\u0006\u0010O\u001a\u00020/8V@VX\u000e¢\u0006\f\u001a\u0004\bP\u00101\"\u0004\bQ\u0010RR$\u0010X\u001a\u00020\u00032\u0006\u0010O\u001a\u00020\u00038V@VX\u000e¢\u0006\f\u001a\u0004\bT\u0010U\"\u0004\bV\u0010WR\u001a\u0010[\u001a\b\u0012\u0004\u0012\u00020\n0\u00198VX\u0004¢\u0006\u0006\u001a\u0004\bY\u0010ZR\u001a\u0010\\\u001a\b\u0012\u0004\u0012\u00020\n0H8\u0002X\u0004¢\u0006\u0006\n\u0004\b\\\u0010JR*\u0010^\u001a\u00020\u00032\u0006\u0010]\u001a\u00020\u00038\u0016@VX\u000e¢\u0006\u0012\n\u0004\b^\u0010_\u001a\u0004\b`\u0010U\"\u0004\ba\u0010WR\u0014\u0010\u0002\u001a\u00020\u00018\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0002\u0010bR*\u0010\u0005\u001a\u00020\u00032\u0006\u0010]\u001a\u00020\u00038\u0016@VX\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010_\u001a\u0004\bc\u0010U\"\u0004\bd\u0010W\u0002\u0004\n\u0002\b\u0019¨\u0006f"}, d2 = {"Lio/ktor/websocket/DefaultWebSocketSessionImpl;", "Lio/ktor/websocket/WebSocketSession;", "raw", "", "pingInterval", "timeoutMillis", "<init>", "(Lio/ktor/websocket/WebSocketSession;JJ)V", "Lio/ktor/utils/io/core/BytePacketBuilder;", "packet", "Lio/ktor/websocket/Frame;", "frame", "", "checkMaxFrameSize", "(Lio/ktor/utils/io/core/BytePacketBuilder;Lio/ktor/websocket/Frame;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "flush", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "message", "goingAway", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "outgoingProcessorLoop", "processIncomingExtensions", "(Lio/ktor/websocket/Frame;)Lio/ktor/websocket/Frame;", "processOutgoingExtensions", "Lkotlinx/coroutines/channels/SendChannel;", "Lio/ktor/websocket/Frame$Ping;", "ponger", "Lkotlinx/coroutines/Job;", "runIncomingProcessor", "(Lkotlinx/coroutines/channels/SendChannel;)Lkotlinx/coroutines/Job;", "runOrCancelPinger", "()V", "runOutgoingProcessor", "()Lkotlinx/coroutines/Job;", "Lio/ktor/websocket/CloseReason;", "reason", "", "exception", "sendCloseSequence", "(Lio/ktor/websocket/CloseReason;Ljava/lang/Throwable;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "Lio/ktor/websocket/WebSocketExtension;", "negotiatedExtensions", "start", "(Ljava/util/List;)V", "terminate", "", "tryClose", "()Z", "", "_extensions", "Ljava/util/List;", "Lkotlinx/coroutines/Deferred;", "closeReason", "Lkotlinx/coroutines/Deferred;", "getCloseReason", "()Lkotlinx/coroutines/Deferred;", "Lkotlinx/coroutines/CompletableDeferred;", "closeReasonRef", "Lkotlinx/coroutines/CompletableDeferred;", "Lkotlinx/coroutines/CompletableJob;", "context", "Lkotlinx/coroutines/CompletableJob;", "Lkotlin/coroutines/CoroutineContext;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "getExtensions", "()Ljava/util/List;", "extensions", "Lkotlinx/coroutines/channels/Channel;", "filtered", "Lkotlinx/coroutines/channels/Channel;", "Lkotlinx/coroutines/channels/ReceiveChannel;", "getIncoming", "()Lkotlinx/coroutines/channels/ReceiveChannel;", "incoming", "value", "getMasking", "setMasking", "(Z)V", "masking", "getMaxFrameSize", "()J", "setMaxFrameSize", "(J)V", "maxFrameSize", "getOutgoing", "()Lkotlinx/coroutines/channels/SendChannel;", "outgoing", "outgoingToBeProcessed", "newValue", "pingIntervalMillis", "J", "getPingIntervalMillis", "setPingIntervalMillis", "Lio/ktor/websocket/WebSocketSession;", "getTimeoutMillis", "setTimeoutMillis", "Companion", "ktor-websockets", "Lio/ktor/websocket/DefaultWebSocketSession;"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: DefaultWebSocketSession.kt */
public final class DefaultWebSocketSessionImpl implements DefaultWebSocketSession, WebSocketSession {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final Frame.Pong EmptyPong = new Frame.Pong(new byte[0], (DisposableHandle) NonDisposableHandle.INSTANCE);
    private static final /* synthetic */ AtomicIntegerFieldUpdater closed$FU;
    static final /* synthetic */ AtomicReferenceFieldUpdater pinger$FU;
    private static final /* synthetic */ AtomicIntegerFieldUpdater started$FU;
    private final List<WebSocketExtension<?>> _extensions;
    private final Deferred<CloseReason> closeReason;
    private final CompletableDeferred<CloseReason> closeReasonRef;
    private volatile /* synthetic */ int closed;
    private final CompletableJob context;
    private final CoroutineContext coroutineContext;
    /* access modifiers changed from: private */
    public final Channel<Frame> filtered;
    /* access modifiers changed from: private */
    public final Channel<Frame> outgoingToBeProcessed;
    private long pingIntervalMillis;
    volatile /* synthetic */ Object pinger = null;
    /* access modifiers changed from: private */
    public final WebSocketSession raw;
    private volatile /* synthetic */ int started;
    private long timeoutMillis;

    public DefaultWebSocketSessionImpl(WebSocketSession webSocketSession, long j, long j2) {
        Intrinsics.checkNotNullParameter(webSocketSession, "raw");
        this.raw = webSocketSession;
        CompletableDeferred<CloseReason> CompletableDeferred$default = CompletableDeferredKt.CompletableDeferred$default((Job) null, 1, (Object) null);
        this.closeReasonRef = CompletableDeferred$default;
        this.filtered = ChannelKt.Channel$default(8, (BufferOverflow) null, (Function1) null, 6, (Object) null);
        this.outgoingToBeProcessed = ChannelKt.Channel$default(UtilsKt.getOUTGOING_CHANNEL_CAPACITY(), (BufferOverflow) null, (Function1) null, 6, (Object) null);
        this.closed = 0;
        CompletableJob Job = JobKt.Job((Job) webSocketSession.getCoroutineContext().get(Job.Key));
        this.context = Job;
        this._extensions = new ArrayList();
        this.started = 0;
        this.coroutineContext = webSocketSession.getCoroutineContext().plus(Job).plus(new CoroutineName("ws-default"));
        this.pingIntervalMillis = j;
        this.timeoutMillis = j2;
        this.closeReason = CompletableDeferred$default;
    }

    public Object send(Frame frame, Continuation<? super Unit> continuation) {
        return DefaultWebSocketSession.DefaultImpls.send(this, frame, continuation);
    }

    public ReceiveChannel<Frame> getIncoming() {
        return this.filtered;
    }

    public SendChannel<Frame> getOutgoing() {
        return this.outgoingToBeProcessed;
    }

    public List<WebSocketExtension<?>> getExtensions() {
        return this._extensions;
    }

    public CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }

    public boolean getMasking() {
        return this.raw.getMasking();
    }

    public void setMasking(boolean z) {
        this.raw.setMasking(z);
    }

    public long getMaxFrameSize() {
        return this.raw.getMaxFrameSize();
    }

    public void setMaxFrameSize(long j) {
        this.raw.setMaxFrameSize(j);
    }

    public long getPingIntervalMillis() {
        return this.pingIntervalMillis;
    }

    public void setPingIntervalMillis(long j) {
        this.pingIntervalMillis = j;
        runOrCancelPinger();
    }

    public long getTimeoutMillis() {
        return this.timeoutMillis;
    }

    public void setTimeoutMillis(long j) {
        this.timeoutMillis = j;
        runOrCancelPinger();
    }

    public Deferred<CloseReason> getCloseReason() {
        return this.closeReason;
    }

    public void start(List<? extends WebSocketExtension<?>> list) {
        Intrinsics.checkNotNullParameter(list, "negotiatedExtensions");
        if (started$FU.compareAndSet(this, 0, 1)) {
            Logger logger = DefaultWebSocketSessionKt.getLOGGER();
            logger.trace("Starting default WebSocketSession(" + this + ") with negotiated extensions: " + CollectionsKt.joinToString$default(list, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 63, (Object) null));
            this._extensions.addAll(list);
            runOrCancelPinger();
            runIncomingProcessor(PingPongKt.ponger(this, getOutgoing()));
            runOutgoingProcessor();
            return;
        }
        throw new IllegalStateException(("WebSocket session " + this + " is already started.").toString());
    }

    public static /* synthetic */ Object goingAway$default(DefaultWebSocketSessionImpl defaultWebSocketSessionImpl, String str, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "Server is going down";
        }
        return defaultWebSocketSessionImpl.goingAway(str, continuation);
    }

    public final Object goingAway(String str, Continuation<? super Unit> continuation) {
        Object sendCloseSequence$default = sendCloseSequence$default(this, new CloseReason(CloseReason.Codes.GOING_AWAY, str), (Throwable) null, continuation, 2, (Object) null);
        return sendCloseSequence$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? sendCloseSequence$default : Unit.INSTANCE;
    }

    public Object flush(Continuation<? super Unit> continuation) {
        Object flush = this.raw.flush(continuation);
        return flush == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? flush : Unit.INSTANCE;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use cancel() instead.", replaceWith = @ReplaceWith(expression = "cancel()", imports = {"kotlinx.coroutines.cancel"}))
    public void terminate() {
        Job.DefaultImpls.cancel$default((Job) this.context, (CancellationException) null, 1, (Object) null);
        CoroutineScopeKt.cancel$default(this.raw, (CancellationException) null, 1, (Object) null);
    }

    private final Job runIncomingProcessor(SendChannel<? super Frame.Ping> sendChannel) {
        return BuildersKt__Builders_commonKt.launch$default(this, DefaultWebSocketSessionKt.IncomingProcessorCoroutineName.plus(Dispatchers.getUnconfined()), (CoroutineStart) null, new DefaultWebSocketSessionImpl$runIncomingProcessor$1(this, sendChannel, (Continuation<? super DefaultWebSocketSessionImpl$runIncomingProcessor$1>) null), 2, (Object) null);
    }

    private final Job runOutgoingProcessor() {
        return BuildersKt.launch(this, DefaultWebSocketSessionKt.OutgoingProcessorCoroutineName.plus(Dispatchers.getUnconfined()), CoroutineStart.UNDISPATCHED, new DefaultWebSocketSessionImpl$runOutgoingProcessor$1(this, (Continuation<? super DefaultWebSocketSessionImpl$runOutgoingProcessor$1>) null));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0078  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object outgoingProcessorLoop(kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            r13 = this;
            boolean r0 = r14 instanceof io.ktor.websocket.DefaultWebSocketSessionImpl$outgoingProcessorLoop$1
            if (r0 == 0) goto L_0x0014
            r0 = r14
            io.ktor.websocket.DefaultWebSocketSessionImpl$outgoingProcessorLoop$1 r0 = (io.ktor.websocket.DefaultWebSocketSessionImpl$outgoingProcessorLoop$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L_0x0019
        L_0x0014:
            io.ktor.websocket.DefaultWebSocketSessionImpl$outgoingProcessorLoop$1 r0 = new io.ktor.websocket.DefaultWebSocketSessionImpl$outgoingProcessorLoop$1
            r0.<init>(r13, r14)
        L_0x0019:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x0054
            if (r2 == r5) goto L_0x0047
            if (r2 == r4) goto L_0x0042
            if (r2 != r3) goto L_0x003a
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.channels.ChannelIterator r2 = (kotlinx.coroutines.channels.ChannelIterator) r2
            java.lang.Object r6 = r0.L$0
            io.ktor.websocket.DefaultWebSocketSessionImpl r6 = (io.ktor.websocket.DefaultWebSocketSessionImpl) r6
            kotlin.ResultKt.throwOnFailure(r14)
            r14 = r2
            r2 = r6
            goto L_0x005e
        L_0x003a:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r0)
            throw r14
        L_0x0042:
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x00da
        L_0x0047:
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.channels.ChannelIterator r2 = (kotlinx.coroutines.channels.ChannelIterator) r2
            java.lang.Object r6 = r0.L$0
            io.ktor.websocket.DefaultWebSocketSessionImpl r6 = (io.ktor.websocket.DefaultWebSocketSessionImpl) r6
            kotlin.ResultKt.throwOnFailure(r14)
            r9 = r0
            goto L_0x0070
        L_0x0054:
            kotlin.ResultKt.throwOnFailure(r14)
            kotlinx.coroutines.channels.Channel<io.ktor.websocket.Frame> r14 = r13.outgoingToBeProcessed
            kotlinx.coroutines.channels.ChannelIterator r14 = r14.iterator()
            r2 = r13
        L_0x005e:
            r0.L$0 = r2
            r0.L$1 = r14
            r0.label = r5
            java.lang.Object r6 = r14.hasNext(r0)
            if (r6 != r1) goto L_0x006b
            return r1
        L_0x006b:
            r9 = r0
            r12 = r2
            r2 = r14
            r14 = r6
            r6 = r12
        L_0x0070:
            java.lang.Boolean r14 = (java.lang.Boolean) r14
            boolean r14 = r14.booleanValue()
            if (r14 == 0) goto L_0x00da
            java.lang.Object r14 = r2.next()
            io.ktor.websocket.Frame r14 = (io.ktor.websocket.Frame) r14
            org.slf4j.Logger r0 = io.ktor.websocket.DefaultWebSocketSessionKt.getLOGGER()
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "Sending "
            r7.<init>(r8)
            r7.append(r14)
            java.lang.String r8 = " from session "
            r7.append(r8)
            r7.append(r6)
            java.lang.String r7 = r7.toString()
            r0.trace(r7)
            boolean r0 = r14 instanceof io.ktor.websocket.Frame.Close
            if (r0 == 0) goto L_0x00b6
            io.ktor.websocket.Frame$Close r14 = (io.ktor.websocket.Frame.Close) r14
            io.ktor.websocket.CloseReason r7 = io.ktor.websocket.FrameCommonKt.readReason(r14)
            r14 = 0
            r9.L$0 = r14
            r9.L$1 = r14
            r9.label = r4
            r8 = 0
            r10 = 2
            r11 = 0
            java.lang.Object r14 = sendCloseSequence$default(r6, r7, r8, r9, r10, r11)
            if (r14 != r1) goto L_0x00da
            return r1
        L_0x00b6:
            boolean r0 = r14 instanceof io.ktor.websocket.Frame.Text
            if (r0 == 0) goto L_0x00bb
            goto L_0x00bf
        L_0x00bb:
            boolean r0 = r14 instanceof io.ktor.websocket.Frame.Binary
            if (r0 == 0) goto L_0x00c3
        L_0x00bf:
            io.ktor.websocket.Frame r14 = r6.processOutgoingExtensions(r14)
        L_0x00c3:
            io.ktor.websocket.WebSocketSession r0 = r6.raw
            kotlinx.coroutines.channels.SendChannel r0 = r0.getOutgoing()
            r9.L$0 = r6
            r9.L$1 = r2
            r9.label = r3
            java.lang.Object r14 = r0.send(r14, r9)
            if (r14 != r1) goto L_0x00d6
            return r1
        L_0x00d6:
            r14 = r2
            r2 = r6
            r0 = r9
            goto L_0x005e
        L_0x00da:
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.DefaultWebSocketSessionImpl.outgoingProcessorLoop(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object sendCloseSequence(io.ktor.websocket.CloseReason r6, java.lang.Throwable r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof io.ktor.websocket.DefaultWebSocketSessionImpl$sendCloseSequence$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            io.ktor.websocket.DefaultWebSocketSessionImpl$sendCloseSequence$1 r0 = (io.ktor.websocket.DefaultWebSocketSessionImpl$sendCloseSequence$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            io.ktor.websocket.DefaultWebSocketSessionImpl$sendCloseSequence$1 r0 = new io.ktor.websocket.DefaultWebSocketSessionImpl$sendCloseSequence$1
            r0.<init>(r5, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0042
            if (r2 != r3) goto L_0x003a
            java.lang.Object r6 = r0.L$2
            io.ktor.websocket.CloseReason r6 = (io.ktor.websocket.CloseReason) r6
            java.lang.Object r7 = r0.L$1
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.lang.Object r0 = r0.L$0
            io.ktor.websocket.DefaultWebSocketSessionImpl r0 = (io.ktor.websocket.DefaultWebSocketSessionImpl) r0
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ all -> 0x0037 }
            goto L_0x00ad
        L_0x0037:
            r8 = move-exception
            goto L_0x00c3
        L_0x003a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0042:
            kotlin.ResultKt.throwOnFailure(r8)
            boolean r8 = r5.tryClose()
            if (r8 != 0) goto L_0x004e
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L_0x004e:
            org.slf4j.Logger r8 = io.ktor.websocket.DefaultWebSocketSessionKt.getLOGGER()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r4 = "Sending Close Sequence for session "
            r2.<init>(r4)
            r2.append(r5)
            java.lang.String r4 = " with reason "
            r2.append(r4)
            r2.append(r6)
            java.lang.String r4 = " and exception "
            r2.append(r4)
            r2.append(r7)
            java.lang.String r2 = r2.toString()
            r8.trace(r2)
            kotlinx.coroutines.CompletableJob r8 = r5.context
            r8.complete()
            if (r6 != 0) goto L_0x0083
            io.ktor.websocket.CloseReason r6 = new io.ktor.websocket.CloseReason
            io.ktor.websocket.CloseReason$Codes r8 = io.ktor.websocket.CloseReason.Codes.NORMAL
            java.lang.String r2 = ""
            r6.<init>((io.ktor.websocket.CloseReason.Codes) r8, (java.lang.String) r2)
        L_0x0083:
            r5.runOrCancelPinger()     // Catch:{ all -> 0x00c1 }
            short r8 = r6.getCode()     // Catch:{ all -> 0x00c1 }
            io.ktor.websocket.CloseReason$Codes r2 = io.ktor.websocket.CloseReason.Codes.CLOSED_ABNORMALLY     // Catch:{ all -> 0x00c1 }
            short r2 = r2.getCode()     // Catch:{ all -> 0x00c1 }
            if (r8 == r2) goto L_0x00ac
            io.ktor.websocket.WebSocketSession r8 = r5.raw     // Catch:{ all -> 0x00c1 }
            kotlinx.coroutines.channels.SendChannel r8 = r8.getOutgoing()     // Catch:{ all -> 0x00c1 }
            io.ktor.websocket.Frame$Close r2 = new io.ktor.websocket.Frame$Close     // Catch:{ all -> 0x00c1 }
            r2.<init>((io.ktor.websocket.CloseReason) r6)     // Catch:{ all -> 0x00c1 }
            r0.L$0 = r5     // Catch:{ all -> 0x00c1 }
            r0.L$1 = r7     // Catch:{ all -> 0x00c1 }
            r0.L$2 = r6     // Catch:{ all -> 0x00c1 }
            r0.label = r3     // Catch:{ all -> 0x00c1 }
            java.lang.Object r8 = r8.send(r2, r0)     // Catch:{ all -> 0x00c1 }
            if (r8 != r1) goto L_0x00ac
            return r1
        L_0x00ac:
            r0 = r5
        L_0x00ad:
            kotlinx.coroutines.CompletableDeferred<io.ktor.websocket.CloseReason> r8 = r0.closeReasonRef
            r8.complete(r6)
            if (r7 == 0) goto L_0x00be
            kotlinx.coroutines.channels.Channel<io.ktor.websocket.Frame> r6 = r0.outgoingToBeProcessed
            r6.close(r7)
            kotlinx.coroutines.channels.Channel<io.ktor.websocket.Frame> r6 = r0.filtered
            r6.close(r7)
        L_0x00be:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L_0x00c1:
            r8 = move-exception
            r0 = r5
        L_0x00c3:
            kotlinx.coroutines.CompletableDeferred<io.ktor.websocket.CloseReason> r1 = r0.closeReasonRef
            r1.complete(r6)
            if (r7 == 0) goto L_0x00d4
            kotlinx.coroutines.channels.Channel<io.ktor.websocket.Frame> r6 = r0.outgoingToBeProcessed
            r6.close(r7)
            kotlinx.coroutines.channels.Channel<io.ktor.websocket.Frame> r6 = r0.filtered
            r6.close(r7)
        L_0x00d4:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.DefaultWebSocketSessionImpl.sendCloseSequence(io.ktor.websocket.CloseReason, java.lang.Throwable, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object sendCloseSequence$default(DefaultWebSocketSessionImpl defaultWebSocketSessionImpl, CloseReason closeReason2, Throwable th, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            th = null;
        }
        return defaultWebSocketSessionImpl.sendCloseSequence(closeReason2, th, continuation);
    }

    private final boolean tryClose() {
        return closed$FU.compareAndSet(this, 0, 1);
    }

    private final void runOrCancelPinger() {
        SendChannel<Frame.Pong> sendChannel;
        long pingIntervalMillis2 = getPingIntervalMillis();
        if (this.closed == 0 && pingIntervalMillis2 > 0) {
            sendChannel = PingPongKt.pinger(this, this.raw.getOutgoing(), pingIntervalMillis2, getTimeoutMillis(), new DefaultWebSocketSessionImpl$runOrCancelPinger$newPinger$1(this, (Continuation<? super DefaultWebSocketSessionImpl$runOrCancelPinger$newPinger$1>) null));
        } else {
            sendChannel = null;
        }
        SendChannel sendChannel2 = (SendChannel) pinger$FU.getAndSet(this, sendChannel);
        if (sendChannel2 != null) {
            SendChannel.DefaultImpls.close$default(sendChannel2, (Throwable) null, 1, (Object) null);
        }
        if (sendChannel != null) {
            ChannelResult.m2346isSuccessimpl(sendChannel.m1139trySendJP2dKIU(EmptyPong));
        }
        if (this.closed != 0 && sendChannel != null) {
            runOrCancelPinger();
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object checkMaxFrameSize(io.ktor.utils.io.core.BytePacketBuilder r9, io.ktor.websocket.Frame r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            r8 = this;
            boolean r0 = r11 instanceof io.ktor.websocket.DefaultWebSocketSessionImpl$checkMaxFrameSize$1
            if (r0 == 0) goto L_0x0014
            r0 = r11
            io.ktor.websocket.DefaultWebSocketSessionImpl$checkMaxFrameSize$1 r0 = (io.ktor.websocket.DefaultWebSocketSessionImpl$checkMaxFrameSize$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L_0x0019
        L_0x0014:
            io.ktor.websocket.DefaultWebSocketSessionImpl$checkMaxFrameSize$1 r0 = new io.ktor.websocket.DefaultWebSocketSessionImpl$checkMaxFrameSize$1
            r0.<init>(r8, r11)
        L_0x0019:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0034
            if (r2 == r3) goto L_0x002e
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x002e:
            int r9 = r0.I$0
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x0083
        L_0x0034:
            kotlin.ResultKt.throwOnFailure(r11)
            byte[] r10 = r10.getData()
            int r10 = r10.length
            if (r9 == 0) goto L_0x0043
            int r11 = r9.getSize()
            goto L_0x0044
        L_0x0043:
            r11 = 0
        L_0x0044:
            int r10 = r10 + r11
            long r4 = (long) r10
            long r6 = r8.getMaxFrameSize()
            int r11 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r11 <= 0) goto L_0x008a
            if (r9 == 0) goto L_0x0053
            r9.release()
        L_0x0053:
            r9 = r8
            io.ktor.websocket.WebSocketSession r9 = (io.ktor.websocket.WebSocketSession) r9
            io.ktor.websocket.CloseReason r11 = new io.ktor.websocket.CloseReason
            io.ktor.websocket.CloseReason$Codes r2 = io.ktor.websocket.CloseReason.Codes.TOO_BIG
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "Frame is too big: "
            r4.<init>(r5)
            r4.append(r10)
            java.lang.String r5 = ". Max size is "
            r4.append(r5)
            long r5 = r8.getMaxFrameSize()
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r11.<init>((io.ktor.websocket.CloseReason.Codes) r2, (java.lang.String) r4)
            r0.I$0 = r10
            r0.label = r3
            java.lang.Object r9 = io.ktor.websocket.WebSocketSessionKt.close((io.ktor.websocket.WebSocketSession) r9, (io.ktor.websocket.CloseReason) r11, (kotlin.coroutines.Continuation<? super kotlin.Unit>) r0)
            if (r9 != r1) goto L_0x0082
            return r1
        L_0x0082:
            r9 = r10
        L_0x0083:
            io.ktor.websocket.FrameTooBigException r10 = new io.ktor.websocket.FrameTooBigException
            long r0 = (long) r9
            r10.<init>(r0)
            throw r10
        L_0x008a:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.websocket.DefaultWebSocketSessionImpl.checkMaxFrameSize(io.ktor.utils.io.core.BytePacketBuilder, io.ktor.websocket.Frame, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public final Frame processIncomingExtensions(Frame frame) {
        for (WebSocketExtension processIncomingFrame : getExtensions()) {
            frame = processIncomingFrame.processIncomingFrame(frame);
        }
        return frame;
    }

    private final Frame processOutgoingExtensions(Frame frame) {
        for (WebSocketExtension processOutgoingFrame : getExtensions()) {
            frame = processOutgoingFrame.processOutgoingFrame(frame);
        }
        return frame;
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lio/ktor/websocket/DefaultWebSocketSessionImpl$Companion;", "", "()V", "EmptyPong", "Lio/ktor/websocket/Frame$Pong;", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: DefaultWebSocketSession.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        Class<DefaultWebSocketSessionImpl> cls = DefaultWebSocketSessionImpl.class;
        pinger$FU = AtomicReferenceFieldUpdater.newUpdater(cls, Object.class, "pinger");
        closed$FU = AtomicIntegerFieldUpdater.newUpdater(cls, "closed");
        started$FU = AtomicIntegerFieldUpdater.newUpdater(cls, "started");
    }
}
