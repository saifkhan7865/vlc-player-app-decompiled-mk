package org.videolan.vlc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import io.ktor.server.auth.OAuth2RequestParameters;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.videolan.resources.Constants;
import org.videolan.vlc.widget.MiniPlayerAppWidgetProvider;
import org.videolan.vlc.widget.VLCAppWidgetProvider;

@Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"org/videolan/vlc/PlaybackService$receiver$1", "Landroid/content/BroadcastReceiver;", "wasPlaying", "", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlaybackService.kt */
public final class PlaybackService$receiver$1 extends BroadcastReceiver {
    final /* synthetic */ PlaybackService this$0;
    private boolean wasPlaying;

    PlaybackService$receiver$1(PlaybackService playbackService) {
        this.this$0 = playbackService;
    }

    public void onReceive(Context context, Intent intent) {
        Intent launchIntentForPackage;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        String action = intent.getAction();
        if (action != null) {
            int intExtra = intent.getIntExtra(OAuth2RequestParameters.State, 0);
            Object systemService = context.getSystemService(Constants.ID_AUDIO);
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.media.AudioManager");
            if (((AudioManager) systemService).getMode() != 2) {
                if (StringsKt.startsWith$default(action, Constants.ACTION_REMOTE_GENERIC, false, 2, (Object) null) && !this.this$0.isPlaying() && !this.this$0.getPlaylistManager().hasCurrentMedia() && (launchIntentForPackage = this.this$0.getPackageManager().getLaunchIntentForPackage(this.this$0.getPackageName())) != null) {
                    context.startActivity(launchIntentForPackage);
                }
                if (Intrinsics.areEqual((Object) action, (Object) Constants.CUSTOM_ACTION)) {
                    String stringExtra = intent.getStringExtra(Constants.EXTRA_CUSTOM_ACTION_ID);
                    if (stringExtra != null) {
                        PlaybackService playbackService = this.this$0;
                        playbackService.getMediaSession$vlc_android_release().getController().getTransportControls().sendCustomAction(stringExtra, (Bundle) null);
                        PlaybackService.executeUpdate$default(playbackService, false, 1, (Object) null);
                        playbackService.showNotification();
                    }
                } else if (Intrinsics.areEqual((Object) action, (Object) VLCAppWidgetProvider.Companion.getACTION_WIDGET_INIT())) {
                    this.this$0.updateWidget();
                } else if (Intrinsics.areEqual((Object) action, (Object) VLCAppWidgetProvider.Companion.getACTION_WIDGET_ENABLED()) || Intrinsics.areEqual((Object) action, (Object) VLCAppWidgetProvider.Companion.getACTION_WIDGET_DISABLED())) {
                    this.this$0.updateHasWidget();
                } else if (Intrinsics.areEqual((Object) action, (Object) MiniPlayerAppWidgetProvider.Companion.getACTION_WIDGET_INIT())) {
                    this.this$0.updateWidget();
                } else if (Intrinsics.areEqual((Object) action, (Object) MiniPlayerAppWidgetProvider.Companion.getACTION_WIDGET_ENABLED()) || Intrinsics.areEqual((Object) action, (Object) MiniPlayerAppWidgetProvider.Companion.getACTION_WIDGET_DISABLED())) {
                    this.this$0.updateHasWidget();
                } else if (Intrinsics.areEqual((Object) action, (Object) "android.media.AUDIO_BECOMING_NOISY")) {
                    if (this.this$0.getDetectHeadset()) {
                        this.this$0.setHeadsetInserted(false);
                        boolean isPlaying = this.this$0.isPlaying();
                        this.wasPlaying = isPlaying;
                        if (isPlaying && this.this$0.getPlaylistManager().hasCurrentMedia()) {
                            this.this$0.pause();
                        }
                    }
                } else if (Intrinsics.areEqual((Object) action, (Object) "android.intent.action.HEADSET_PLUG") && this.this$0.getDetectHeadset() && intExtra != 0) {
                    this.this$0.setHeadsetInserted(true);
                    if (this.wasPlaying && this.this$0.getPlaylistManager().hasCurrentMedia() && this.this$0.getSettings$vlc_android_release().getBoolean("enable_play_on_headset_insertion", false)) {
                        this.this$0.play();
                    }
                }
            }
        }
    }
}
