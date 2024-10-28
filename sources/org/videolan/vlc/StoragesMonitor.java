package org.videolan.vlc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.channels.ActorKt;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.SendChannel;
import org.videolan.tools.AppScope;
import org.videolan.tools.KotlinExtensionsKt;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0002¨\u0006\r"}, d2 = {"Lorg/videolan/vlc/StoragesMonitor;", "Landroid/content/BroadcastReceiver;", "()V", "actor", "Lkotlinx/coroutines/channels/SendChannel;", "Lorg/videolan/vlc/MediaEvent;", "getActor$annotations", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: StoragesMonitor.kt */
public final class StoragesMonitor extends BroadcastReceiver {
    private final SendChannel<MediaEvent> actor = ActorKt.actor$default(AppScope.INSTANCE, (CoroutineContext) null, Integer.MAX_VALUE, (CoroutineStart) null, (Function1) null, new StoragesMonitor$actor$1((Continuation<? super StoragesMonitor$actor$1>) null), 13, (Object) null);

    private static /* synthetic */ void getActor$annotations() {
    }

    public void onReceive(Context context, Intent intent) {
        Uri data;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        String action = intent.getAction();
        if (action == null || KotlinExtensionsKt.isAppStarted()) {
            return;
        }
        if (Intrinsics.areEqual((Object) action, (Object) "android.intent.action.MEDIA_MOUNTED")) {
            Uri data2 = intent.getData();
            if (data2 != null) {
                ChannelResult.m2336boximpl(this.actor.m1139trySendJP2dKIU(new Mount(context, data2, (String) null, (String) null, 12, (DefaultConstructorMarker) null)));
            }
        } else if (Intrinsics.areEqual((Object) action, (Object) "android.intent.action.MEDIA_UNMOUNTED") && (data = intent.getData()) != null) {
            ChannelResult.m2336boximpl(this.actor.m1139trySendJP2dKIU(new Unmount(context, data, (String) null, (String) null, 12, (DefaultConstructorMarker) null)));
        }
    }
}
