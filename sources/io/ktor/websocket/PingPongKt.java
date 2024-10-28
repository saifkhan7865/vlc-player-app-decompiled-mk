package io.ktor.websocket;

import io.ktor.websocket.Frame;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.SendChannel;

@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\\\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004*\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\"\u0010\f\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00110\rH\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a \u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u0004*\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"}, d2 = {"PingerCoroutineName", "Lkotlinx/coroutines/CoroutineName;", "PongerCoroutineName", "pinger", "Lkotlinx/coroutines/channels/SendChannel;", "Lio/ktor/websocket/Frame$Pong;", "Lkotlinx/coroutines/CoroutineScope;", "outgoing", "Lio/ktor/websocket/Frame;", "periodMillis", "", "timeoutMillis", "onTimeout", "Lkotlin/Function2;", "Lio/ktor/websocket/CloseReason;", "Lkotlin/coroutines/Continuation;", "", "", "(Lkotlinx/coroutines/CoroutineScope;Lkotlinx/coroutines/channels/SendChannel;JJLkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/channels/SendChannel;", "ponger", "Lio/ktor/websocket/Frame$Ping;", "ktor-websockets"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: PingPong.kt */
public final class PingPongKt {
    private static final CoroutineName PingerCoroutineName = new CoroutineName("ws-pinger");
    private static final CoroutineName PongerCoroutineName = new CoroutineName("ws-ponger");

    public static final SendChannel<Frame.Ping> ponger(CoroutineScope coroutineScope, SendChannel<? super Frame.Pong> sendChannel) {
        Intrinsics.checkNotNullParameter(coroutineScope, "<this>");
        Intrinsics.checkNotNullParameter(sendChannel, "outgoing");
        Channel Channel$default = ChannelKt.Channel$default(5, (BufferOverflow) null, (Function1) null, 6, (Object) null);
        Job unused = BuildersKt__Builders_commonKt.launch$default(coroutineScope, PongerCoroutineName, (CoroutineStart) null, new PingPongKt$ponger$1(Channel$default, sendChannel, (Continuation<? super PingPongKt$ponger$1>) null), 2, (Object) null);
        return Channel$default;
    }

    public static final SendChannel<Frame.Pong> pinger(CoroutineScope coroutineScope, SendChannel<? super Frame> sendChannel, long j, long j2, Function2<? super CloseReason, ? super Continuation<? super Unit>, ? extends Object> function2) {
        CoroutineScope coroutineScope2 = coroutineScope;
        Intrinsics.checkNotNullParameter(coroutineScope, "<this>");
        Intrinsics.checkNotNullParameter(sendChannel, "outgoing");
        Function2<? super CloseReason, ? super Continuation<? super Unit>, ? extends Object> function22 = function2;
        Intrinsics.checkNotNullParameter(function22, "onTimeout");
        CompletableJob Job$default = JobKt.Job$default((Job) null, 1, (Object) null);
        Channel Channel$default = ChannelKt.Channel$default(Integer.MAX_VALUE, (BufferOverflow) null, (Function1) null, 6, (Object) null);
        Job unused = BuildersKt__Builders_commonKt.launch$default(coroutineScope, Job$default.plus(PingerCoroutineName), (CoroutineStart) null, new PingPongKt$pinger$1(j, j2, function22, Channel$default, sendChannel, (Continuation<? super PingPongKt$pinger$1>) null), 2, (Object) null);
        CoroutineContext.Element element = coroutineScope.getCoroutineContext().get(Job.Key);
        Intrinsics.checkNotNull(element);
        ((Job) element).invokeOnCompletion(new PingPongKt$pinger$2(Job$default));
        return Channel$default;
    }
}
