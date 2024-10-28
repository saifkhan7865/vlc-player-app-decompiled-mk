package org.videolan.vlc.webserver.websockets;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import com.google.gson.Gson;
import io.ktor.server.routing.Route;
import io.ktor.server.routing.Routing;
import io.ktor.server.websocket.DefaultWebSocketServerSession;
import io.ktor.server.websocket.RoutingKt;
import j$.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import org.videolan.resources.Constants;
import org.videolan.tools.AppScope;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.webserver.RemoteAccessServer;
import org.videolan.vlc.webserver.ssl.SecretGenerator;

@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0007H\u0002J\u000e\u0010\u0016\u001a\u00020\u0014H@¢\u0006\u0002\u0010\u0017J\u0006\u0010\u0018\u001a\u00020\u0004J\u001a\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0002J(\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001a\u001a\u00020\u001bJ\u0010\u0010$\u001a\u00020\u001f2\u0006\u0010\"\u001a\u00020#H\u0002J\u0016\u0010%\u001a\u00020\u00142\u0006\u0010&\u001a\u00020\u0007H@¢\u0006\u0002\u0010'J\u0012\u0010(\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0002J\u001a\u0010)\u001a\u00020\u0014*\u00020*2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020#R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R!\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001e\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00100\u0006j\b\u0012\u0004\u0012\u00020\u0010`\bX\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u00120\u0006j\b\u0012\u0004\u0012\u00020\u0012`\bX\u000e¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lorg/videolan/vlc/webserver/websockets/RemoteAccessWebSockets;", "", "()V", "TAG", "", "messageQueue", "Ljava/util/ArrayList;", "Lorg/videolan/vlc/webserver/RemoteAccessServer$WSMessage;", "Lkotlin/collections/ArrayList;", "getMessageQueue", "()Ljava/util/ArrayList;", "onPlaybackEventChannel", "Lkotlinx/coroutines/channels/Channel;", "getOnPlaybackEventChannel", "()Lkotlinx/coroutines/channels/Channel;", "tickets", "Lorg/videolan/vlc/webserver/websockets/WSAuthTicket;", "websocketSession", "Lio/ktor/server/websocket/DefaultWebSocketServerSession;", "addToQueue", "", "wsMessage", "closeAllSessions", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createTicket", "getVolumeMessage", "context", "Landroid/content/Context;", "service", "Lorg/videolan/vlc/PlaybackService;", "manageIncomingMessages", "", "incomingMessage", "Lorg/videolan/vlc/webserver/websockets/WSIncomingMessage;", "settings", "Landroid/content/SharedPreferences;", "playbackControlAllowedOrSend", "sendToAll", "messageObj", "(Lorg/videolan/vlc/webserver/RemoteAccessServer$WSMessage;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "verifyWebsocketAuth", "setupWebSockets", "Lio/ktor/server/routing/Routing;", "webserver_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: RemoteAccessWebSockets.kt */
public final class RemoteAccessWebSockets {
    public static final RemoteAccessWebSockets INSTANCE = new RemoteAccessWebSockets();
    private static final String TAG = "HttpSharingServerWS";
    private static final ArrayList<RemoteAccessServer.WSMessage> messageQueue = new ArrayList<>();
    private static final Channel<String> onPlaybackEventChannel = ChannelKt.Channel$default(0, (BufferOverflow) null, (Function1) null, 7, (Object) null);
    private static final ArrayList<WSAuthTicket> tickets = new ArrayList<>();
    /* access modifiers changed from: private */
    public static ArrayList<DefaultWebSocketServerSession> websocketSession = new ArrayList<>();

    private RemoteAccessWebSockets() {
    }

    public final ArrayList<RemoteAccessServer.WSMessage> getMessageQueue() {
        return messageQueue;
    }

    public final Channel<String> getOnPlaybackEventChannel() {
        return onPlaybackEventChannel;
    }

    public final void setupWebSockets(Routing routing, Context context, SharedPreferences sharedPreferences) {
        Intrinsics.checkNotNullParameter(routing, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sharedPreferences, "settings");
        RoutingKt.webSocket((Route) routing, "/echo", "player", (Function2<? super DefaultWebSocketServerSession, ? super Continuation<? super Unit>, ? extends Object>) new RemoteAccessWebSockets$setupWebSockets$1(context, sharedPreferences, (Continuation<? super RemoteAccessWebSockets$setupWebSockets$1>) null));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v47, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v8, resolved type: android.media.AudioManager} */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x023d, code lost:
        if (r3.equals("hello") == false) goto L_0x045c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:203:0x045a, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:205:0x045c, code lost:
        android.util.Log.w(TAG, "Unrecognized message", new java.lang.IllegalStateException("Unrecognized message: " + r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:206:0x0478, code lost:
        return false;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean manageIncomingMessages(org.videolan.vlc.webserver.websockets.WSIncomingMessage r19, android.content.SharedPreferences r20, org.videolan.vlc.PlaybackService r21, android.content.Context r22) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = r20
            r6 = r21
            r5 = r22
            java.lang.String r3 = "incomingMessage"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r3)
            java.lang.String r3 = "settings"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r3)
            java.lang.String r3 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r3)
            java.lang.String r3 = r19.getMessage()
            int r4 = r3.hashCode()
            java.lang.String r7 = "true"
            r8 = 10000(0x2710, float:1.4013E-41)
            r9 = 2
            r10 = 1
            r11 = 0
            r12 = 0
            switch(r4) {
                case -1854808766: goto L_0x043b;
                case -1273775369: goto L_0x0425;
                case -1196730568: goto L_0x03fc;
                case -1048797102: goto L_0x03cc;
                case -934610874: goto L_0x039f;
                case -934531685: goto L_0x0366;
                case -725928219: goto L_0x0327;
                case -458847025: goto L_0x02c0;
                case -155716239: goto L_0x02a0;
                case -32448682: goto L_0x0271;
                case 3377907: goto L_0x0259;
                case 3443508: goto L_0x0241;
                case 99162322: goto L_0x0237;
                case 106440182: goto L_0x021f;
                case 109641799: goto L_0x01fa;
                case 132437218: goto L_0x01bf;
                case 488495115: goto L_0x019c;
                case 859181232: goto L_0x0168;
                case 1052562200: goto L_0x0132;
                case 1090614964: goto L_0x010b;
                case 1355243760: goto L_0x00e4;
                case 1375184593: goto L_0x00b4;
                case 1568526259: goto L_0x0084;
                case 1781088997: goto L_0x0046;
                case 2072332025: goto L_0x002e;
                default: goto L_0x002c;
            }
        L_0x002c:
            goto L_0x045c
        L_0x002e:
            java.lang.String r4 = "shuffle"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0038
            goto L_0x045c
        L_0x0038:
            boolean r1 = r0.playbackControlAllowedOrSend(r2)
            if (r1 == 0) goto L_0x0045
            if (r6 == 0) goto L_0x045a
            r21.shuffle()
            goto L_0x045a
        L_0x0045:
            return r12
        L_0x0046:
            java.lang.String r4 = "set-volume"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0050
            goto L_0x045c
        L_0x0050:
            boolean r2 = r0.playbackControlAllowedOrSend(r2)
            if (r2 == 0) goto L_0x0083
            java.lang.String r2 = "audio"
            java.lang.Object r2 = r5.getSystemService(r2)
            boolean r3 = r2 instanceof android.media.AudioManager
            if (r3 == 0) goto L_0x0063
            r11 = r2
            android.media.AudioManager r11 = (android.media.AudioManager) r11
        L_0x0063:
            if (r11 == 0) goto L_0x045a
            r2 = 3
            int r3 = r11.getStreamMaxVolume(r2)
            java.lang.Integer r1 = r19.getId()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            int r1 = r1.intValue()
            float r1 = (float) r1
            r4 = 100
            float r4 = (float) r4
            float r1 = r1 / r4
            float r3 = (float) r3
            float r1 = r1 * r3
            int r1 = (int) r1
            r11.setStreamVolume(r2, r1, r10)
            goto L_0x045a
        L_0x0083:
            return r12
        L_0x0084:
            java.lang.String r2 = "sleep-timer-wait"
            boolean r2 = r3.equals(r2)
            if (r2 != 0) goto L_0x008e
            goto L_0x045c
        L_0x008e:
            java.lang.String r1 = r19.getStringValue()
            if (r1 == 0) goto L_0x045a
            if (r6 != 0) goto L_0x0097
            goto L_0x009e
        L_0x0097:
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r7)
            r6.setWaitForMediaEnd(r1)
        L_0x009e:
            org.videolan.tools.AppScope r1 = org.videolan.tools.AppScope.INSTANCE
            r2 = r1
            kotlinx.coroutines.CoroutineScope r2 = (kotlinx.coroutines.CoroutineScope) r2
            org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets$manageIncomingMessages$10$1 r1 = new org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets$manageIncomingMessages$10$1
            r1.<init>(r5, r11)
            r5 = r1
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
            r6 = 3
            r7 = 0
            r3 = 0
            r4 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r2, r3, r4, r5, r6, r7)
            goto L_0x045a
        L_0x00b4:
            java.lang.String r2 = "sleep-timer-reset"
            boolean r2 = r3.equals(r2)
            if (r2 != 0) goto L_0x00be
            goto L_0x045c
        L_0x00be:
            java.lang.String r1 = r19.getStringValue()
            if (r1 == 0) goto L_0x045a
            if (r6 != 0) goto L_0x00c7
            goto L_0x00ce
        L_0x00c7:
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r7)
            r6.setResetOnInteraction(r1)
        L_0x00ce:
            org.videolan.tools.AppScope r1 = org.videolan.tools.AppScope.INSTANCE
            r2 = r1
            kotlinx.coroutines.CoroutineScope r2 = (kotlinx.coroutines.CoroutineScope) r2
            org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets$manageIncomingMessages$11$1 r1 = new org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets$manageIncomingMessages$11$1
            r1.<init>(r5, r11)
            r5 = r1
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
            r6 = 3
            r7 = 0
            r3 = 0
            r4 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r2, r3, r4, r5, r6, r7)
            goto L_0x045a
        L_0x00e4:
            java.lang.String r4 = "move-media-top"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x00ee
            goto L_0x045c
        L_0x00ee:
            boolean r2 = r0.playbackControlAllowedOrSend(r2)
            if (r2 == 0) goto L_0x010a
            java.lang.Integer r1 = r19.getId()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            int r1 = r1.intValue()
            if (r1 <= 0) goto L_0x045a
            if (r6 == 0) goto L_0x045a
            int r2 = r1 + -1
            r6.moveItem(r1, r2)
            goto L_0x045a
        L_0x010a:
            return r12
        L_0x010b:
            java.lang.String r4 = "play-chapter"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0115
            goto L_0x045c
        L_0x0115:
            java.lang.Integer r1 = r19.getId()
            if (r1 == 0) goto L_0x045a
            java.lang.Number r1 = (java.lang.Number) r1
            int r1 = r1.intValue()
            org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets r3 = INSTANCE
            boolean r2 = r3.playbackControlAllowedOrSend(r2)
            if (r2 == 0) goto L_0x045a
            if (r6 != 0) goto L_0x012d
            goto L_0x045a
        L_0x012d:
            r6.setChapterIdx(r1)
            goto L_0x045a
        L_0x0132:
            java.lang.String r2 = "delete-bookmark"
            boolean r2 = r3.equals(r2)
            if (r2 != 0) goto L_0x013c
            goto L_0x045c
        L_0x013c:
            java.lang.Long r1 = r19.getLongValue()
            if (r1 == 0) goto L_0x045a
            java.lang.Number r1 = (java.lang.Number) r1
            long r1 = r1.longValue()
            if (r6 == 0) goto L_0x045a
            org.videolan.medialibrary.interfaces.media.MediaWrapper r3 = r21.getCurrentMediaWrapper()
            if (r3 == 0) goto L_0x045a
            org.videolan.tools.AppScope r4 = org.videolan.tools.AppScope.INSTANCE
            r12 = r4
            kotlinx.coroutines.CoroutineScope r12 = (kotlinx.coroutines.CoroutineScope) r12
            org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets$manageIncomingMessages$13$1$1 r4 = new org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets$manageIncomingMessages$13$1$1
            r4.<init>(r3, r1, r11)
            r15 = r4
            kotlin.jvm.functions.Function2 r15 = (kotlin.jvm.functions.Function2) r15
            r16 = 3
            r17 = 0
            r13 = 0
            r14 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r12, r13, r14, r15, r16, r17)
            goto L_0x045a
        L_0x0168:
            java.lang.String r4 = "move-media-bottom"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0172
            goto L_0x045c
        L_0x0172:
            boolean r2 = r0.playbackControlAllowedOrSend(r2)
            if (r2 == 0) goto L_0x019b
            java.lang.Integer r1 = r19.getId()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            int r1 = r1.intValue()
            if (r6 == 0) goto L_0x018f
            org.videolan.vlc.media.PlaylistManager r2 = r21.getPlaylistManager()
            if (r2 == 0) goto L_0x018f
            int r12 = r2.getMediaListSize()
        L_0x018f:
            int r12 = r12 - r10
            if (r1 >= r12) goto L_0x045a
            if (r6 == 0) goto L_0x045a
            int r2 = r1 + 2
            r6.moveItem(r1, r2)
            goto L_0x045a
        L_0x019b:
            return r12
        L_0x019c:
            java.lang.String r4 = "play-media"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x01a6
            goto L_0x045c
        L_0x01a6:
            boolean r2 = r0.playbackControlAllowedOrSend(r2)
            if (r2 == 0) goto L_0x01be
            if (r6 == 0) goto L_0x045a
            java.lang.Integer r1 = r19.getId()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            int r1 = r1.intValue()
            org.videolan.vlc.PlaybackService.playIndex$default(r6, r1, r12, r9, r11)
            goto L_0x045a
        L_0x01be:
            return r12
        L_0x01bf:
            java.lang.String r2 = "add-bookmark"
            boolean r2 = r3.equals(r2)
            if (r2 != 0) goto L_0x01c9
            goto L_0x045c
        L_0x01c9:
            java.lang.Long r1 = r19.getLongValue()
            if (r1 == 0) goto L_0x045a
            java.lang.Number r1 = (java.lang.Number) r1
            long r3 = r1.longValue()
            if (r6 == 0) goto L_0x045a
            org.videolan.medialibrary.interfaces.media.MediaWrapper r2 = r21.getCurrentMediaWrapper()
            if (r2 == 0) goto L_0x045a
            org.videolan.tools.AppScope r1 = org.videolan.tools.AppScope.INSTANCE
            r11 = r1
            kotlinx.coroutines.CoroutineScope r11 = (kotlinx.coroutines.CoroutineScope) r11
            org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets$manageIncomingMessages$12$1$1 r8 = new org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets$manageIncomingMessages$12$1$1
            r7 = 0
            r1 = r8
            r5 = r22
            r6 = r21
            r1.<init>(r2, r3, r5, r6, r7)
            r14 = r8
            kotlin.jvm.functions.Function2 r14 = (kotlin.jvm.functions.Function2) r14
            r15 = 3
            r16 = 0
            r12 = 0
            r13 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r11, r12, r13, r14, r15, r16)
            goto L_0x045a
        L_0x01fa:
            java.lang.String r4 = "speed"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0204
            goto L_0x045c
        L_0x0204:
            java.lang.Float r1 = r19.getFloatValue()
            if (r1 == 0) goto L_0x045a
            java.lang.Number r1 = (java.lang.Number) r1
            float r1 = r1.floatValue()
            org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets r3 = INSTANCE
            boolean r2 = r3.playbackControlAllowedOrSend(r2)
            if (r2 == 0) goto L_0x045a
            if (r6 == 0) goto L_0x045a
            r6.setRate(r1, r10)
            goto L_0x045a
        L_0x021f:
            java.lang.String r4 = "pause"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0229
            goto L_0x045c
        L_0x0229:
            boolean r1 = r0.playbackControlAllowedOrSend(r2)
            if (r1 == 0) goto L_0x0236
            if (r6 == 0) goto L_0x045a
            r21.pause()
            goto L_0x045a
        L_0x0236:
            return r12
        L_0x0237:
            java.lang.String r2 = "hello"
            boolean r2 = r3.equals(r2)
            if (r2 != 0) goto L_0x045a
            goto L_0x045c
        L_0x0241:
            java.lang.String r4 = "play"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x024b
            goto L_0x045c
        L_0x024b:
            boolean r1 = r0.playbackControlAllowedOrSend(r2)
            if (r1 == 0) goto L_0x0258
            if (r6 == 0) goto L_0x045a
            r21.play()
            goto L_0x045a
        L_0x0258:
            return r12
        L_0x0259:
            java.lang.String r4 = "next"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0263
            goto L_0x045c
        L_0x0263:
            boolean r1 = r0.playbackControlAllowedOrSend(r2)
            if (r1 == 0) goto L_0x0270
            if (r6 == 0) goto L_0x045a
            org.videolan.vlc.PlaybackService.next$default(r6, r12, r10, r11)
            goto L_0x045a
        L_0x0270:
            return r12
        L_0x0271:
            java.lang.String r4 = "previous10"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x027b
            goto L_0x045c
        L_0x027b:
            boolean r1 = r0.playbackControlAllowedOrSend(r2)
            if (r1 == 0) goto L_0x029f
            if (r6 == 0) goto L_0x045a
            long r1 = r21.getTime()
            long r3 = (long) r8
            long r1 = r1 - r3
            r3 = 0
            long r2 = kotlin.ranges.RangesKt.coerceAtLeast((long) r1, (long) r3)
            r8 = 10
            r9 = 0
            r4 = 0
            r7 = 1
            r11 = 0
            r1 = r21
            r6 = r7
            r7 = r11
            org.videolan.vlc.PlaybackService.seek$default(r1, r2, r4, r6, r7, r8, r9)
            goto L_0x045a
        L_0x029f:
            return r12
        L_0x02a0:
            java.lang.String r2 = "get-volume"
            boolean r2 = r3.equals(r2)
            if (r2 != 0) goto L_0x02aa
            goto L_0x045c
        L_0x02aa:
            org.videolan.tools.AppScope r1 = org.videolan.tools.AppScope.INSTANCE
            r2 = r1
            kotlinx.coroutines.CoroutineScope r2 = (kotlinx.coroutines.CoroutineScope) r2
            org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets$manageIncomingMessages$4 r1 = new org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets$manageIncomingMessages$4
            r1.<init>(r5, r6, r11)
            r5 = r1
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
            r6 = 3
            r7 = 0
            r3 = 0
            r4 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r2, r3, r4, r5, r6, r7)
            goto L_0x045a
        L_0x02c0:
            java.lang.String r4 = "sleep-timer"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x02ca
            goto L_0x045c
        L_0x02ca:
            java.lang.Long r1 = r19.getLongValue()
            if (r1 == 0) goto L_0x045a
            java.lang.Number r1 = (java.lang.Number) r1
            long r3 = r1.longValue()
            java.util.Calendar r1 = java.util.Calendar.getInstance()
            long r7 = r1.getTimeInMillis()
            long r7 = r7 + r3
            r1.setTimeInMillis(r7)
            r7 = 13
            r1.set(r7, r12)
            org.videolan.tools.AppScope r7 = org.videolan.tools.AppScope.INSTANCE
            r12 = r7
            kotlinx.coroutines.CoroutineScope r12 = (kotlinx.coroutines.CoroutineScope) r12
            kotlinx.coroutines.MainCoroutineDispatcher r7 = kotlinx.coroutines.Dispatchers.getMain()
            r13 = r7
            kotlin.coroutines.CoroutineContext r13 = (kotlin.coroutines.CoroutineContext) r13
            org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets$manageIncomingMessages$9$1 r7 = new org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets$manageIncomingMessages$9$1
            r7.<init>(r6, r1, r11)
            r15 = r7
            kotlin.jvm.functions.Function2 r15 = (kotlin.jvm.functions.Function2) r15
            r16 = 2
            r17 = 0
            r14 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r12, r13, r14, r15, r16, r17)
            org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets r1 = INSTANCE
            boolean r1 = r1.playbackControlAllowedOrSend(r2)
            if (r1 == 0) goto L_0x0311
            if (r6 != 0) goto L_0x030e
            goto L_0x0311
        L_0x030e:
            r6.setSleepTimerInterval(r3)
        L_0x0311:
            org.videolan.tools.AppScope r1 = org.videolan.tools.AppScope.INSTANCE
            r2 = r1
            kotlinx.coroutines.CoroutineScope r2 = (kotlinx.coroutines.CoroutineScope) r2
            org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets$manageIncomingMessages$9$2 r1 = new org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets$manageIncomingMessages$9$2
            r1.<init>(r5, r11)
            r5 = r1
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
            r6 = 3
            r7 = 0
            r3 = 0
            r4 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r2, r3, r4, r5, r6, r7)
            goto L_0x045a
        L_0x0327:
            java.lang.String r2 = "rename-bookmark"
            boolean r2 = r3.equals(r2)
            if (r2 != 0) goto L_0x0331
            goto L_0x045c
        L_0x0331:
            java.lang.Long r2 = r19.getLongValue()
            if (r2 == 0) goto L_0x045a
            java.lang.Number r2 = (java.lang.Number) r2
            long r7 = r2.longValue()
            java.lang.String r5 = r19.getStringValue()
            if (r5 == 0) goto L_0x045a
            if (r6 == 0) goto L_0x045a
            org.videolan.medialibrary.interfaces.media.MediaWrapper r4 = r21.getCurrentMediaWrapper()
            if (r4 == 0) goto L_0x045a
            org.videolan.tools.AppScope r1 = org.videolan.tools.AppScope.INSTANCE
            r11 = r1
            kotlinx.coroutines.CoroutineScope r11 = (kotlinx.coroutines.CoroutineScope) r11
            org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets$manageIncomingMessages$14$1$1$1 r1 = new org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets$manageIncomingMessages$14$1$1$1
            r2 = 0
            r3 = r1
            r6 = r7
            r8 = r2
            r3.<init>(r4, r5, r6, r8)
            r14 = r1
            kotlin.jvm.functions.Function2 r14 = (kotlin.jvm.functions.Function2) r14
            r15 = 3
            r16 = 0
            r12 = 0
            r13 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r11, r12, r13, r14, r15, r16)
            goto L_0x045a
        L_0x0366:
            java.lang.String r4 = "repeat"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0370
            goto L_0x045c
        L_0x0370:
            boolean r1 = r0.playbackControlAllowedOrSend(r2)
            if (r1 == 0) goto L_0x039e
            if (r6 == 0) goto L_0x045a
            int r1 = r21.getRepeatType()
            if (r1 == 0) goto L_0x0399
            if (r1 == r10) goto L_0x0389
            if (r1 == r9) goto L_0x0384
            goto L_0x045a
        L_0x0384:
            r6.setRepeatType(r12)
            goto L_0x045a
        L_0x0389:
            boolean r1 = r21.hasPlaylist()
            if (r1 == 0) goto L_0x0394
            r6.setRepeatType(r9)
            goto L_0x045a
        L_0x0394:
            r6.setRepeatType(r12)
            goto L_0x045a
        L_0x0399:
            r6.setRepeatType(r10)
            goto L_0x045a
        L_0x039e:
            return r12
        L_0x039f:
            java.lang.String r4 = "remote"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x03a9
            goto L_0x045c
        L_0x03a9:
            boolean r2 = r0.playbackControlAllowedOrSend(r2)
            if (r2 == 0) goto L_0x03cb
            java.lang.String r1 = r19.getStringValue()
            if (r1 == 0) goto L_0x045a
            org.videolan.tools.AppScope r2 = org.videolan.tools.AppScope.INSTANCE
            r3 = r2
            kotlinx.coroutines.CoroutineScope r3 = (kotlinx.coroutines.CoroutineScope) r3
            org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets$manageIncomingMessages$15$1 r2 = new org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets$manageIncomingMessages$15$1
            r2.<init>(r1, r11)
            r6 = r2
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
            r7 = 3
            r8 = 0
            r4 = 0
            r5 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r3, r4, r5, r6, r7, r8)
            goto L_0x045a
        L_0x03cb:
            return r12
        L_0x03cc:
            java.lang.String r4 = "next10"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x03d6
            goto L_0x045c
        L_0x03d6:
            boolean r1 = r0.playbackControlAllowedOrSend(r2)
            if (r1 == 0) goto L_0x03fb
            if (r6 == 0) goto L_0x045a
            long r1 = r21.getTime()
            long r3 = (long) r8
            long r1 = r1 + r3
            long r3 = r21.getLength()
            long r2 = kotlin.ranges.RangesKt.coerceAtMost((long) r1, (long) r3)
            r8 = 10
            r9 = 0
            r4 = 0
            r7 = 1
            r11 = 0
            r1 = r21
            r6 = r7
            r7 = r11
            org.videolan.vlc.PlaybackService.seek$default(r1, r2, r4, r6, r7, r8, r9)
            goto L_0x045a
        L_0x03fb:
            return r12
        L_0x03fc:
            java.lang.String r4 = "set-progress"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0405
            goto L_0x045c
        L_0x0405:
            boolean r2 = r0.playbackControlAllowedOrSend(r2)
            if (r2 == 0) goto L_0x0424
            java.lang.Integer r1 = r19.getId()
            if (r1 == 0) goto L_0x045a
            java.lang.Number r1 = (java.lang.Number) r1
            int r1 = r1.intValue()
            if (r6 == 0) goto L_0x045a
            long r2 = (long) r1
            r5 = 2
            r7 = 0
            r4 = 0
            r1 = r21
            r6 = r7
            org.videolan.vlc.PlaybackService.setTime$default(r1, r2, r4, r5, r6)
            goto L_0x045a
        L_0x0424:
            return r12
        L_0x0425:
            java.lang.String r4 = "previous"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x042e
            goto L_0x045c
        L_0x042e:
            boolean r1 = r0.playbackControlAllowedOrSend(r2)
            if (r1 == 0) goto L_0x043a
            if (r6 == 0) goto L_0x045a
            r6.previous(r12)
            goto L_0x045a
        L_0x043a:
            return r12
        L_0x043b:
            java.lang.String r4 = "delete-media"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0444
            goto L_0x045c
        L_0x0444:
            boolean r2 = r0.playbackControlAllowedOrSend(r2)
            if (r2 == 0) goto L_0x045b
            if (r6 == 0) goto L_0x045a
            java.lang.Integer r1 = r19.getId()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            int r1 = r1.intValue()
            r6.remove(r1)
        L_0x045a:
            return r10
        L_0x045b:
            return r12
        L_0x045c:
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Unrecognized message: "
            r3.<init>(r4)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r2.<init>(r1)
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            java.lang.String r1 = "HttpSharingServerWS"
            java.lang.String r3 = "Unrecognized message"
            android.util.Log.w(r1, r3, r2)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets.manageIncomingMessages(org.videolan.vlc.webserver.websockets.WSIncomingMessage, android.content.SharedPreferences, org.videolan.vlc.PlaybackService, android.content.Context):boolean");
    }

    /* access modifiers changed from: private */
    public final boolean verifyWebsocketAuth(WSIncomingMessage wSIncomingMessage) {
        String str;
        Object obj = null;
        if (wSIncomingMessage != null) {
            str = wSIncomingMessage.getAuthTicket();
        } else {
            str = null;
        }
        if (str != null) {
            Iterator it = tickets.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                WSAuthTicket wSAuthTicket = (WSAuthTicket) next;
                if (Intrinsics.areEqual((Object) wSIncomingMessage.getAuthTicket(), (Object) wSAuthTicket.getId()) && System.currentTimeMillis() < wSAuthTicket.getExpiration()) {
                    obj = next;
                    break;
                }
            }
            if (obj != null) {
                return true;
            }
        }
        return false;
    }

    private final boolean playbackControlAllowedOrSend(SharedPreferences sharedPreferences) {
        boolean z = sharedPreferences.getBoolean(SettingsKt.REMOTE_ACCESS_PLAYBACK_CONTROL, true);
        String json = new Gson().toJson((Object) new RemoteAccessServer.PlaybackControlForbidden(false, 1, (DefaultConstructorMarker) null));
        if (!z) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(AppScope.INSTANCE, (CoroutineContext) null, (CoroutineStart) null, new RemoteAccessWebSockets$playbackControlAllowedOrSend$1(json, (Continuation<? super RemoteAccessWebSockets$playbackControlAllowedOrSend$1>) null), 3, (Object) null);
        }
        return z;
    }

    /* access modifiers changed from: private */
    public final String getVolumeMessage(Context context, PlaybackService playbackService) {
        int i;
        Gson gson = new Gson();
        if (playbackService == null || !playbackService.isVideoPlaying() || playbackService.getVolume() <= 100) {
            Object systemService = context.getSystemService(Constants.ID_AUDIO);
            AudioManager audioManager = systemService instanceof AudioManager ? (AudioManager) systemService : null;
            i = audioManager != null ? (int) ((((float) audioManager.getStreamVolume(3)) / ((float) audioManager.getStreamMaxVolume(3))) * ((float) 100)) : 0;
        } else {
            i = playbackService.getVolume();
        }
        String json = gson.toJson((Object) new RemoteAccessServer.Volume(i));
        Intrinsics.checkNotNullExpressionValue(json, "toJson(...)");
        return json;
    }

    public final String createTicket() {
        WSAuthTicket wSAuthTicket = new WSAuthTicket(SecretGenerator.INSTANCE.generateRandomAlphanumericString(45), System.currentTimeMillis() + 60000);
        tickets.add(wSAuthTicket);
        return wSAuthTicket.getId();
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0078  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object sendToAll(org.videolan.vlc.webserver.RemoteAccessServer.WSMessage r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets$sendToAll$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets$sendToAll$1 r0 = (org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets$sendToAll$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets$sendToAll$1 r0 = new org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets$sendToAll$1
            r0.<init>(r6, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0042
            if (r2 != r3) goto L_0x003a
            java.lang.Object r7 = r0.L$3
            io.ktor.server.websocket.DefaultWebSocketServerSession r7 = (io.ktor.server.websocket.DefaultWebSocketServerSession) r7
            java.lang.Object r2 = r0.L$2
            java.util.HashSet r2 = (java.util.HashSet) r2
            java.lang.Object r4 = r0.L$1
            java.util.Iterator r4 = (java.util.Iterator) r4
            java.lang.Object r5 = r0.L$0
            java.lang.String r5 = (java.lang.String) r5
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ Exception -> 0x0099 }
            goto L_0x0072
        L_0x003a:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0042:
            kotlin.ResultKt.throwOnFailure(r8)
            com.google.gson.Gson r8 = new com.google.gson.Gson
            r8.<init>()
            java.lang.String r8 = r8.toJson((java.lang.Object) r7)
            r6.addToQueue(r7)
            kotlinx.coroutines.channels.Channel<java.lang.String> r7 = onPlaybackEventChannel
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8)
            r7.m1139trySendJP2dKIU(r8)
            java.util.ArrayList r7 = new java.util.ArrayList
            java.util.ArrayList<io.ktor.server.websocket.DefaultWebSocketServerSession> r2 = websocketSession
            java.util.Collection r2 = (java.util.Collection) r2
            r7.<init>(r2)
            java.util.Iterator r7 = r7.iterator()
            java.lang.String r2 = "iterator(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r2)
            java.util.HashSet r2 = new java.util.HashSet
            r2.<init>()
            r4 = r7
            r5 = r8
        L_0x0072:
            boolean r7 = r4.hasNext()
            if (r7 == 0) goto L_0x009d
            java.lang.Object r7 = r4.next()
            io.ktor.server.websocket.DefaultWebSocketServerSession r7 = (io.ktor.server.websocket.DefaultWebSocketServerSession) r7
            io.ktor.websocket.Frame$Text r8 = new io.ktor.websocket.Frame$Text     // Catch:{ Exception -> 0x0099 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)     // Catch:{ Exception -> 0x0099 }
            r8.<init>(r5)     // Catch:{ Exception -> 0x0099 }
            io.ktor.websocket.Frame r8 = (io.ktor.websocket.Frame) r8     // Catch:{ Exception -> 0x0099 }
            r0.L$0 = r5     // Catch:{ Exception -> 0x0099 }
            r0.L$1 = r4     // Catch:{ Exception -> 0x0099 }
            r0.L$2 = r2     // Catch:{ Exception -> 0x0099 }
            r0.L$3 = r7     // Catch:{ Exception -> 0x0099 }
            r0.label = r3     // Catch:{ Exception -> 0x0099 }
            java.lang.Object r7 = r7.send(r8, r0)     // Catch:{ Exception -> 0x0099 }
            if (r7 != r1) goto L_0x0072
            return r1
        L_0x0099:
            r2.add(r7)
            goto L_0x0072
        L_0x009d:
            java.util.ArrayList<io.ktor.server.websocket.DefaultWebSocketServerSession> r7 = websocketSession
            java.util.Collection r2 = (java.util.Collection) r2
            r7.removeAll(r2)
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets.sendToAll(org.videolan.vlc.webserver.RemoteAccessServer$WSMessage, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void addToQueue(RemoteAccessServer.WSMessage wSMessage) {
        if (ArraysKt.contains((T[]) new String[]{"now-playing", "play-queue", "auth", "volume", "player-status", "login-needed", "ml-refresh-needed", "playback-control-forbidden"}, wSMessage.getType())) {
            Collection.EL.removeIf(messageQueue, new RemoteAccessWebSockets$$ExternalSyntheticLambda0(new RemoteAccessWebSockets$addToQueue$1(wSMessage)));
        }
        messageQueue.add(wSMessage);
    }

    /* access modifiers changed from: private */
    public static final boolean addToQueue$lambda$20(Function1 function1, Object obj) {
        Intrinsics.checkNotNullParameter(function1, "$tmp0");
        return ((Boolean) function1.invoke(obj)).booleanValue();
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object closeAllSessions(kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets$closeAllSessions$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets$closeAllSessions$1 r0 = (org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets$closeAllSessions$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets$closeAllSessions$1 r0 = new org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets$closeAllSessions$1
            r0.<init>(r5, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r2 = r0.L$0
            java.util.Iterator r2 = (java.util.Iterator) r2
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x004c
        L_0x002e:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r6)
            java.util.ArrayList r6 = new java.util.ArrayList
            java.util.ArrayList<io.ktor.server.websocket.DefaultWebSocketServerSession> r2 = websocketSession
            java.util.Collection r2 = (java.util.Collection) r2
            r6.<init>(r2)
            java.util.Iterator r6 = r6.iterator()
            java.lang.String r2 = "iterator(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r2)
            r2 = r6
        L_0x004c:
            boolean r6 = r2.hasNext()
            if (r6 == 0) goto L_0x0069
            java.lang.Object r6 = r2.next()
            io.ktor.server.websocket.DefaultWebSocketServerSession r6 = (io.ktor.server.websocket.DefaultWebSocketServerSession) r6
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            io.ktor.websocket.WebSocketSession r6 = (io.ktor.websocket.WebSocketSession) r6
            r0.L$0 = r2
            r0.label = r3
            r4 = 0
            java.lang.Object r6 = io.ktor.websocket.WebSocketSessionKt.close$default(r6, r4, r0, r3, r4)
            if (r6 != r1) goto L_0x004c
            return r1
        L_0x0069:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets.closeAllSessions(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
