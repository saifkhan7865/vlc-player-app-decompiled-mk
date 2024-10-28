package org.videolan.vlc.webserver.websockets;

import android.content.Context;
import android.content.SharedPreferences;
import io.ktor.server.websocket.DefaultWebSocketServerSession;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lio/ktor/server/websocket/DefaultWebSocketServerSession;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets$setupWebSockets$1", f = "RemoteAccessWebSockets.kt", i = {0, 1}, l = {67, 75}, m = "invokeSuspend", n = {"$this$webSocket", "$this$webSocket"}, s = {"L$0", "L$0"})
/* compiled from: RemoteAccessWebSockets.kt */
final class RemoteAccessWebSockets$setupWebSockets$1 extends SuspendLambda implements Function2<DefaultWebSocketServerSession, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ SharedPreferences $settings;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteAccessWebSockets$setupWebSockets$1(Context context, SharedPreferences sharedPreferences, Continuation<? super RemoteAccessWebSockets$setupWebSockets$1> continuation) {
        super(2, continuation);
        this.$context = context;
        this.$settings = sharedPreferences;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        RemoteAccessWebSockets$setupWebSockets$1 remoteAccessWebSockets$setupWebSockets$1 = new RemoteAccessWebSockets$setupWebSockets$1(this.$context, this.$settings, continuation);
        remoteAccessWebSockets$setupWebSockets$1.L$0 = obj;
        return remoteAccessWebSockets$setupWebSockets$1;
    }

    public final Object invoke(DefaultWebSocketServerSession defaultWebSocketServerSession, Continuation<? super Unit> continuation) {
        return ((RemoteAccessWebSockets$setupWebSockets$1) create(defaultWebSocketServerSession, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0063  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r10.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0032
            if (r1 == r3) goto L_0x0026
            if (r1 != r2) goto L_0x001e
            java.lang.Object r1 = r10.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r4 = r10.L$0
            io.ktor.server.websocket.DefaultWebSocketServerSession r4 = (io.ktor.server.websocket.DefaultWebSocketServerSession) r4
            kotlin.ResultKt.throwOnFailure(r11)     // Catch:{ Exception -> 0x001b }
            goto L_0x00c5
        L_0x001b:
            r11 = move-exception
            goto L_0x00e3
        L_0x001e:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L_0x0026:
            java.lang.Object r1 = r10.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r4 = r10.L$0
            io.ktor.server.websocket.DefaultWebSocketServerSession r4 = (io.ktor.server.websocket.DefaultWebSocketServerSession) r4
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x005b
        L_0x0032:
            kotlin.ResultKt.throwOnFailure(r11)
            java.lang.Object r11 = r10.L$0
            io.ktor.server.websocket.DefaultWebSocketServerSession r11 = (io.ktor.server.websocket.DefaultWebSocketServerSession) r11
            java.util.ArrayList r1 = org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets.websocketSession
            r1.add(r11)
            kotlinx.coroutines.channels.ReceiveChannel r1 = r11.getIncoming()
            kotlinx.coroutines.channels.ChannelIterator r1 = r1.iterator()
        L_0x0048:
            r4 = r10
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r10.L$0 = r11
            r10.L$1 = r1
            r10.label = r3
            java.lang.Object r4 = r1.hasNext(r4)
            if (r4 != r0) goto L_0x0058
            return r0
        L_0x0058:
            r9 = r4
            r4 = r11
            r11 = r9
        L_0x005b:
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r11 == 0) goto L_0x00f1
            java.lang.Object r11 = r1.next()
            io.ktor.websocket.Frame r11 = (io.ktor.websocket.Frame) r11
            boolean r5 = r11 instanceof io.ktor.websocket.Frame.Text     // Catch:{ Exception -> 0x001b }
            if (r5 == 0) goto L_0x0071
            r5 = r11
            io.ktor.websocket.Frame$Text r5 = (io.ktor.websocket.Frame.Text) r5     // Catch:{ Exception -> 0x001b }
            goto L_0x0072
        L_0x0071:
            r5 = 0
        L_0x0072:
            if (r5 != 0) goto L_0x0076
            goto L_0x00ee
        L_0x0076:
            io.ktor.websocket.Frame$Text r11 = (io.ktor.websocket.Frame.Text) r11     // Catch:{ Exception -> 0x001b }
            java.lang.String r11 = io.ktor.websocket.FrameCommonKt.readText(r11)     // Catch:{ Exception -> 0x001b }
            com.google.gson.Gson r5 = new com.google.gson.Gson     // Catch:{ Exception -> 0x001b }
            r5.<init>()     // Catch:{ Exception -> 0x001b }
            java.lang.Class<org.videolan.vlc.webserver.websockets.WSIncomingMessage> r6 = org.videolan.vlc.webserver.websockets.WSIncomingMessage.class
            java.lang.Object r5 = r5.fromJson((java.lang.String) r11, r6)     // Catch:{ Exception -> 0x001b }
            org.videolan.vlc.webserver.websockets.WSIncomingMessage r5 = (org.videolan.vlc.webserver.websockets.WSIncomingMessage) r5     // Catch:{ Exception -> 0x001b }
            java.lang.Boolean r6 = org.videolan.vlc.webserver.BuildConfig.VLC_REMOTE_ACCESS_DEBUG     // Catch:{ Exception -> 0x001b }
            boolean r6 = r6.booleanValue()     // Catch:{ Exception -> 0x001b }
            if (r6 != 0) goto L_0x00c8
            org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets r6 = org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets.INSTANCE     // Catch:{ Exception -> 0x001b }
            boolean r6 = r6.verifyWebsocketAuth(r5)     // Catch:{ Exception -> 0x001b }
            if (r6 != 0) goto L_0x00c8
            io.ktor.websocket.Frame$Text r5 = new io.ktor.websocket.Frame$Text     // Catch:{ Exception -> 0x001b }
            com.google.gson.Gson r6 = new com.google.gson.Gson     // Catch:{ Exception -> 0x001b }
            r6.<init>()     // Catch:{ Exception -> 0x001b }
            org.videolan.vlc.webserver.RemoteAccessServer$WebSocketAuthorization r7 = new org.videolan.vlc.webserver.RemoteAccessServer$WebSocketAuthorization     // Catch:{ Exception -> 0x001b }
            java.lang.String r8 = "forbidden"
            r7.<init>(r8, r11)     // Catch:{ Exception -> 0x001b }
            java.lang.String r11 = r6.toJson((java.lang.Object) r7)     // Catch:{ Exception -> 0x001b }
            java.lang.String r6 = "toJson(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r6)     // Catch:{ Exception -> 0x001b }
            r5.<init>(r11)     // Catch:{ Exception -> 0x001b }
            io.ktor.websocket.Frame r5 = (io.ktor.websocket.Frame) r5     // Catch:{ Exception -> 0x001b }
            r11 = r10
            kotlin.coroutines.Continuation r11 = (kotlin.coroutines.Continuation) r11     // Catch:{ Exception -> 0x001b }
            r10.L$0 = r4     // Catch:{ Exception -> 0x001b }
            r10.L$1 = r1     // Catch:{ Exception -> 0x001b }
            r10.label = r2     // Catch:{ Exception -> 0x001b }
            java.lang.Object r11 = r4.send(r5, r11)     // Catch:{ Exception -> 0x001b }
            if (r11 != r0) goto L_0x00c5
            return r0
        L_0x00c5:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE     // Catch:{ Exception -> 0x001b }
            return r11
        L_0x00c8:
            org.videolan.vlc.webserver.RemoteAccessServer$Companion r11 = org.videolan.vlc.webserver.RemoteAccessServer.Companion     // Catch:{ Exception -> 0x001b }
            android.content.Context r6 = r10.$context     // Catch:{ Exception -> 0x001b }
            java.lang.Object r11 = r11.getInstance(r6)     // Catch:{ Exception -> 0x001b }
            org.videolan.vlc.webserver.RemoteAccessServer r11 = (org.videolan.vlc.webserver.RemoteAccessServer) r11     // Catch:{ Exception -> 0x001b }
            org.videolan.vlc.PlaybackService r11 = r11.getService()     // Catch:{ Exception -> 0x001b }
            org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets r6 = org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets.INSTANCE     // Catch:{ Exception -> 0x001b }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)     // Catch:{ Exception -> 0x001b }
            android.content.SharedPreferences r7 = r10.$settings     // Catch:{ Exception -> 0x001b }
            android.content.Context r8 = r10.$context     // Catch:{ Exception -> 0x001b }
            r6.manageIncomingMessages(r5, r7, r11, r8)     // Catch:{ Exception -> 0x001b }
            goto L_0x00ee
        L_0x00e3:
            java.lang.String r5 = r11.getMessage()
            java.lang.Throwable r11 = (java.lang.Throwable) r11
            java.lang.String r6 = "HttpSharingServerWS"
            android.util.Log.e(r6, r5, r11)
        L_0x00ee:
            r11 = r4
            goto L_0x0048
        L_0x00f1:
            java.util.ArrayList r11 = org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets.websocketSession
            r11.remove(r4)
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets$setupWebSockets$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
