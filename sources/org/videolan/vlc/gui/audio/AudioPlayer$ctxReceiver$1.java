package org.videolan.vlc.gui.audio;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.dialogs.CtxActionReceiver;
import org.videolan.vlc.gui.dialogs.DuplicationWarningDialog;
import org.videolan.vlc.gui.helpers.AudioUtil;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.media.PlaylistManager;
import org.videolan.vlc.util.ContextOption;
import org.videolan.vlc.util.KextensionsKt;

@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"org/videolan/vlc/gui/audio/AudioPlayer$ctxReceiver$1", "Lorg/videolan/vlc/gui/dialogs/CtxActionReceiver;", "onCtxAction", "", "position", "", "option", "Lorg/videolan/vlc/util/ContextOption;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioPlayer.kt */
public final class AudioPlayer$ctxReceiver$1 implements CtxActionReceiver {
    final /* synthetic */ AudioPlayer this$0;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: AudioPlayer.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(17:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|17) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0034 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
        static {
            /*
                org.videolan.vlc.util.ContextOption[] r0 = org.videolan.vlc.util.ContextOption.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_SET_RINGTONE     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_ADD_TO_PLAYLIST     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_REMOVE_FROM_PLAYLIST     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_STOP_AFTER_THIS     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_INFORMATION     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_GO_TO_FOLDER     // Catch:{ NoSuchFieldError -> 0x003d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003d }
            L_0x003d:
                org.videolan.vlc.util.ContextOption r1 = org.videolan.vlc.util.ContextOption.CTX_SHARE     // Catch:{ NoSuchFieldError -> 0x0046 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0046 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0046 }
            L_0x0046:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.audio.AudioPlayer$ctxReceiver$1.WhenMappings.<clinit>():void");
        }
    }

    AudioPlayer$ctxReceiver$1(AudioPlayer audioPlayer) {
        this.this$0 = audioPlayer;
    }

    public void onCtxAction(int i, ContextOption contextOption) {
        PlaylistManager playlistManager;
        Intrinsics.checkNotNullParameter(contextOption, DuplicationWarningDialog.OPTION_KEY);
        if (i >= 0) {
            PlaylistAdapter access$getPlaylistAdapter$p = this.this$0.playlistAdapter;
            PlaylistAdapter playlistAdapter = null;
            if (access$getPlaylistAdapter$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("playlistAdapter");
                access$getPlaylistAdapter$p = null;
            }
            if (i < access$getPlaylistAdapter$p.getItemCount()) {
                switch (WhenMappings.$EnumSwitchMapping$0[contextOption.ordinal()]) {
                    case 1:
                        FragmentActivity activity = this.this$0.getActivity();
                        if (activity != null) {
                            AudioUtil audioUtil = AudioUtil.INSTANCE;
                            PlaylistAdapter access$getPlaylistAdapter$p2 = this.this$0.playlistAdapter;
                            if (access$getPlaylistAdapter$p2 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("playlistAdapter");
                            } else {
                                playlistAdapter = access$getPlaylistAdapter$p2;
                            }
                            audioUtil.setRingtone(activity, playlistAdapter.getItem(i));
                            return;
                        }
                        return;
                    case 2:
                        PlaylistAdapter access$getPlaylistAdapter$p3 = this.this$0.playlistAdapter;
                        if (access$getPlaylistAdapter$p3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("playlistAdapter");
                        } else {
                            playlistAdapter = access$getPlaylistAdapter$p3;
                        }
                        MediaWrapper item = playlistAdapter.getItem(i);
                        UiTools uiTools = UiTools.INSTANCE;
                        FragmentActivity requireActivity = this.this$0.requireActivity();
                        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
                        uiTools.addToPlaylist(requireActivity, CollectionsKt.listOf(item));
                        return;
                    case 3:
                        if (this.this$0.getView() != null) {
                            AudioPlayer audioPlayer = this.this$0;
                            PlaylistAdapter access$getPlaylistAdapter$p4 = audioPlayer.playlistAdapter;
                            if (access$getPlaylistAdapter$p4 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("playlistAdapter");
                            } else {
                                playlistAdapter = access$getPlaylistAdapter$p4;
                            }
                            MediaWrapper item2 = playlistAdapter.getItem(i);
                            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                            String string = audioPlayer.getString(R.string.remove_playlist_item);
                            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                            String format = String.format(string, Arrays.copyOf(new Object[]{item2.getTitle()}, 1));
                            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                            UiTools uiTools2 = UiTools.INSTANCE;
                            FragmentActivity requireActivity2 = audioPlayer.requireActivity();
                            Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity(...)");
                            uiTools2.snackerWithCancel(requireActivity2, format, true, AudioPlayer$ctxReceiver$1$onCtxAction$1$1.INSTANCE, new AudioPlayer$ctxReceiver$1$onCtxAction$1$2(audioPlayer, i, item2));
                            audioPlayer.getPlaylistModel().remove(i);
                            return;
                        }
                        return;
                    case 4:
                        PlaybackService service = this.this$0.getPlaylistModel().getService();
                        if (!(service == null || (playlistManager = service.getPlaylistManager()) == null || playlistManager.getStopAfter() != i)) {
                            i = -1;
                        }
                        this.this$0.getPlaylistModel().stopAfter(i);
                        PlaylistAdapter access$getPlaylistAdapter$p5 = this.this$0.playlistAdapter;
                        if (access$getPlaylistAdapter$p5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("playlistAdapter");
                        } else {
                            playlistAdapter = access$getPlaylistAdapter$p5;
                        }
                        playlistAdapter.setStopAfter(i);
                        return;
                    case 5:
                        AudioPlayer audioPlayer2 = this.this$0;
                        PlaylistAdapter access$getPlaylistAdapter$p6 = audioPlayer2.playlistAdapter;
                        if (access$getPlaylistAdapter$p6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("playlistAdapter");
                        } else {
                            playlistAdapter = access$getPlaylistAdapter$p6;
                        }
                        audioPlayer2.showInfoDialog(playlistAdapter.getItem(i));
                        return;
                    case 6:
                        AudioPlayer audioPlayer3 = this.this$0;
                        Fragment fragment = audioPlayer3;
                        PlaylistAdapter access$getPlaylistAdapter$p7 = audioPlayer3.playlistAdapter;
                        if (access$getPlaylistAdapter$p7 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("playlistAdapter");
                        } else {
                            playlistAdapter = access$getPlaylistAdapter$p7;
                        }
                        KextensionsKt.showParentFolder(fragment, playlistAdapter.getItem(i));
                        return;
                    case 7:
                        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this.this$0), (CoroutineContext) null, (CoroutineStart) null, new AudioPlayer$ctxReceiver$1$onCtxAction$2(this.this$0, i, (Continuation<? super AudioPlayer$ctxReceiver$1$onCtxAction$2>) null), 3, (Object) null);
                        return;
                    default:
                        return;
                }
            }
        }
    }
}
