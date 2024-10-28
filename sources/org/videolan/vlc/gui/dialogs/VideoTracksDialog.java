package org.videolan.vlc.gui.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowKt;
import org.videolan.tools.DependencyProvider;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.vlc.MainVersionKt;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.R;
import org.videolan.vlc.VersionDependantKt;
import org.videolan.vlc.databinding.PlayerOverlayTracksBinding;
import org.videolan.vlc.gui.dialogs.adapters.TrackAdapter;
import org.videolan.vlc.gui.dialogs.adapters.VlcTrack;
import org.videolan.vlc.gui.helpers.BitmapUtilKt;
import org.videolan.vlc.gui.view.CollapsibleLinearLayout;
import org.videolan.vlc.util.AccessibilityHelperKt;

@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 42\u00020\u0001:\u0003456B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0016J*\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u000f2\b\b\u0001\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0007H\u0002J\u001a\u0010\u001e\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001f\u001a\u00020\u0016H\u0002J#\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!2\u000e\u0010#\u001a\n\u0012\u0006\b\u0001\u0012\u00020\"0!H\u0002¢\u0006\u0002\u0010$J\b\u0010%\u001a\u00020\u001cH\u0016J\b\u0010&\u001a\u00020'H\u0016J\b\u0010(\u001a\u00020\u0016H\u0016J&\u0010)\u001a\u0004\u0018\u00010'2\u0006\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010\u00192\b\u0010-\u001a\u0004\u0018\u00010.H\u0016J\u0012\u0010/\u001a\u00020\b2\b\u00100\u001a\u0004\u0018\u000101H\u0002J\u001a\u00102\u001a\u00020\b2\u0006\u00103\u001a\u00020'2\b\u0010-\u001a\u0004\u0018\u00010.H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R&\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR,\u0010\r\u001a\u0014\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\b0\u000eX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u00067"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/VideoTracksDialog;", "Lorg/videolan/vlc/gui/dialogs/VLCBottomSheetDialogFragment;", "()V", "binding", "Lorg/videolan/vlc/databinding/PlayerOverlayTracksBinding;", "menuItemListener", "Lkotlin/Function1;", "Lorg/videolan/vlc/gui/dialogs/VideoTracksDialog$VideoTrackOption;", "", "getMenuItemListener", "()Lkotlin/jvm/functions/Function1;", "setMenuItemListener", "(Lkotlin/jvm/functions/Function1;)V", "trackSelectionListener", "Lkotlin/Function2;", "", "Lorg/videolan/vlc/gui/dialogs/VideoTracksDialog$TrackType;", "getTrackSelectionListener", "()Lkotlin/jvm/functions/Function2;", "setTrackSelectionListener", "(Lkotlin/jvm/functions/Function2;)V", "allowRemote", "", "generateOptionItem", "parent", "Landroid/view/ViewGroup;", "title", "icon", "", "optionId", "generateSeparator", "margin", "generateTrackList", "", "Lorg/videolan/vlc/gui/dialogs/adapters/VlcTrack;", "trackList", "([Lorg/videolan/vlc/gui/dialogs/adapters/VlcTrack;)[Lorg/videolan/vlc/gui/dialogs/adapters/VlcTrack;", "getDefaultState", "initialFocusedView", "Landroid/view/View;", "needToManageOrientation", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "savedInstanceState", "Landroid/os/Bundle;", "onServiceChanged", "service", "Lorg/videolan/vlc/PlaybackService;", "onViewCreated", "view", "Companion", "TrackType", "VideoTrackOption", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoTracksDialog.kt */
public final class VideoTracksDialog extends VLCBottomSheetDialogFragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String TAG = "VLC/SavePlaylistDialog";
    /* access modifiers changed from: private */
    public PlayerOverlayTracksBinding binding;
    public Function1<? super VideoTrackOption, Unit> menuItemListener;
    public Function2<? super String, ? super TrackType, Unit> trackSelectionListener;

    public boolean allowRemote() {
        return true;
    }

    public int getDefaultState() {
        return 3;
    }

    public boolean needToManageOrientation() {
        return true;
    }

    public View initialFocusedView() {
        PlayerOverlayTracksBinding playerOverlayTracksBinding = this.binding;
        if (playerOverlayTracksBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            playerOverlayTracksBinding = null;
        }
        TextView textView = playerOverlayTracksBinding.subtitleTracks.emptyView;
        Intrinsics.checkNotNullExpressionValue(textView, "emptyView");
        return textView;
    }

    public final Function1<VideoTrackOption, Unit> getMenuItemListener() {
        Function1<? super VideoTrackOption, Unit> function1 = this.menuItemListener;
        if (function1 != null) {
            return function1;
        }
        Intrinsics.throwUninitializedPropertyAccessException("menuItemListener");
        return null;
    }

    public final void setMenuItemListener(Function1<? super VideoTrackOption, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.menuItemListener = function1;
    }

    public final Function2<String, TrackType, Unit> getTrackSelectionListener() {
        Function2<? super String, ? super TrackType, Unit> function2 = this.trackSelectionListener;
        if (function2 != null) {
            return function2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("trackSelectionListener");
        return null;
    }

    public final void setTrackSelectionListener(Function2<? super String, ? super TrackType, Unit> function2) {
        Intrinsics.checkNotNullParameter(function2, "<set-?>");
        this.trackSelectionListener = function2;
    }

    /* access modifiers changed from: private */
    public final void onServiceChanged(PlaybackService playbackService) {
        VlcTrack vlcTrack;
        VlcTrack vlcTrack2;
        VlcTrack vlcTrack3;
        if (playbackService != null) {
            PlayerOverlayTracksBinding playerOverlayTracksBinding = null;
            if ((MainVersionKt.isVLC4() && playbackService.getVideoTracksCount() < 2) || (!MainVersionKt.isVLC4() && playbackService.getVideoTracksCount() <= 2)) {
                PlayerOverlayTracksBinding playerOverlayTracksBinding2 = this.binding;
                if (playerOverlayTracksBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    playerOverlayTracksBinding2 = null;
                }
                KotlinExtensionsKt.setGone(playerOverlayTracksBinding2.videoTracks.trackContainer);
                PlayerOverlayTracksBinding playerOverlayTracksBinding3 = this.binding;
                if (playerOverlayTracksBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    playerOverlayTracksBinding3 = null;
                }
                KotlinExtensionsKt.setGone(playerOverlayTracksBinding3.tracksSeparator3);
            }
            if (playbackService.getAudioTracksCount() <= 0) {
                PlayerOverlayTracksBinding playerOverlayTracksBinding4 = this.binding;
                if (playerOverlayTracksBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    playerOverlayTracksBinding4 = null;
                }
                KotlinExtensionsKt.setGone(playerOverlayTracksBinding4.audioTracks.trackContainer);
                PlayerOverlayTracksBinding playerOverlayTracksBinding5 = this.binding;
                if (playerOverlayTracksBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    playerOverlayTracksBinding5 = null;
                }
                KotlinExtensionsKt.setGone(playerOverlayTracksBinding5.tracksSeparator2);
            }
            VlcTrack[] videoTracks = playbackService.getVideoTracks();
            int i = 0;
            if (videoTracks != null) {
                VlcTrack[] generateTrackList = generateTrackList(videoTracks);
                int length = videoTracks.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        vlcTrack3 = null;
                        break;
                    }
                    vlcTrack3 = videoTracks[i2];
                    if (Intrinsics.areEqual((Object) vlcTrack3.getId(), (Object) playbackService.getVideoTrack())) {
                        break;
                    }
                    i2++;
                }
                String string = getString(R.string.track_video);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                TrackAdapter trackAdapter = new TrackAdapter(generateTrackList, vlcTrack3, string);
                trackAdapter.setOnTrackSelectedListener(new VideoTracksDialog$onServiceChanged$1$1$1(this));
                PlayerOverlayTracksBinding playerOverlayTracksBinding6 = this.binding;
                if (playerOverlayTracksBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    playerOverlayTracksBinding6 = null;
                }
                playerOverlayTracksBinding6.videoTracks.trackList.setAdapter(trackAdapter);
                PlayerOverlayTracksBinding playerOverlayTracksBinding7 = this.binding;
                if (playerOverlayTracksBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    playerOverlayTracksBinding7 = null;
                }
                playerOverlayTracksBinding7.videoTracks.trackTitle.setContentDescription(getString(R.string.talkback_video_tracks));
            }
            VlcTrack[] audioTracks = playbackService.getAudioTracks();
            if (audioTracks != null) {
                VlcTrack[] generateTrackList2 = generateTrackList(audioTracks);
                int length2 = audioTracks.length;
                int i3 = 0;
                while (true) {
                    if (i3 >= length2) {
                        vlcTrack2 = null;
                        break;
                    }
                    vlcTrack2 = audioTracks[i3];
                    if (Intrinsics.areEqual((Object) vlcTrack2.getId(), (Object) playbackService.getAudioTrack())) {
                        break;
                    }
                    i3++;
                }
                String string2 = getString(R.string.track_audio);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                TrackAdapter trackAdapter2 = new TrackAdapter(generateTrackList2, vlcTrack2, string2);
                trackAdapter2.setOnTrackSelectedListener(new VideoTracksDialog$onServiceChanged$1$2$1(this));
                PlayerOverlayTracksBinding playerOverlayTracksBinding8 = this.binding;
                if (playerOverlayTracksBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    playerOverlayTracksBinding8 = null;
                }
                playerOverlayTracksBinding8.audioTracks.trackList.setAdapter(trackAdapter2);
                PlayerOverlayTracksBinding playerOverlayTracksBinding9 = this.binding;
                if (playerOverlayTracksBinding9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    playerOverlayTracksBinding9 = null;
                }
                playerOverlayTracksBinding9.audioTracks.trackTitle.setContentDescription(getString(R.string.talkback_audio_tracks));
            }
            VlcTrack[] spuTracks = playbackService.getSpuTracks();
            if (spuTracks != null) {
                if (!playbackService.hasRenderer()) {
                    VlcTrack[] generateTrackList3 = generateTrackList(spuTracks);
                    int length3 = spuTracks.length;
                    while (true) {
                        if (i >= length3) {
                            vlcTrack = null;
                            break;
                        }
                        vlcTrack = spuTracks[i];
                        if (Intrinsics.areEqual((Object) vlcTrack.getId(), (Object) playbackService.getSpuTrack())) {
                            break;
                        }
                        i++;
                    }
                    String string3 = getString(R.string.track_text);
                    Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                    TrackAdapter trackAdapter3 = new TrackAdapter(generateTrackList3, vlcTrack, string3);
                    trackAdapter3.setOnTrackSelectedListener(new VideoTracksDialog$onServiceChanged$1$3$1(this));
                    PlayerOverlayTracksBinding playerOverlayTracksBinding10 = this.binding;
                    if (playerOverlayTracksBinding10 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        playerOverlayTracksBinding10 = null;
                    }
                    playerOverlayTracksBinding10.subtitleTracks.trackList.setAdapter(trackAdapter3);
                } else {
                    PlayerOverlayTracksBinding playerOverlayTracksBinding11 = this.binding;
                    if (playerOverlayTracksBinding11 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        playerOverlayTracksBinding11 = null;
                    }
                    playerOverlayTracksBinding11.subtitleTracks.emptyView.setText(getString(R.string.no_sub_renderer));
                    PlayerOverlayTracksBinding playerOverlayTracksBinding12 = this.binding;
                    if (playerOverlayTracksBinding12 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        playerOverlayTracksBinding12 = null;
                    }
                    KotlinExtensionsKt.setVisible(playerOverlayTracksBinding12.subtitleTracks.emptyView);
                    PlayerOverlayTracksBinding playerOverlayTracksBinding13 = this.binding;
                    if (playerOverlayTracksBinding13 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        playerOverlayTracksBinding13 = null;
                    }
                    KotlinExtensionsKt.setGone(playerOverlayTracksBinding13.subtitleTracks.trackMore);
                }
                if (spuTracks.length == 0) {
                    PlayerOverlayTracksBinding playerOverlayTracksBinding14 = this.binding;
                    if (playerOverlayTracksBinding14 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        playerOverlayTracksBinding14 = null;
                    }
                    KotlinExtensionsKt.setVisible(playerOverlayTracksBinding14.subtitleTracks.emptyView);
                }
                PlayerOverlayTracksBinding playerOverlayTracksBinding15 = this.binding;
                if (playerOverlayTracksBinding15 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    playerOverlayTracksBinding15 = null;
                }
                playerOverlayTracksBinding15.subtitleTracks.trackTitle.setContentDescription(getString(R.string.talkback_subtitle_tracks));
            }
            if (playbackService.getSpuTracks() == null) {
                PlayerOverlayTracksBinding playerOverlayTracksBinding16 = this.binding;
                if (playerOverlayTracksBinding16 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    playerOverlayTracksBinding = playerOverlayTracksBinding16;
                }
                KotlinExtensionsKt.setVisible(playerOverlayTracksBinding.subtitleTracks.emptyView);
            }
        }
    }

    private final VlcTrack[] generateTrackList(VlcTrack[] vlcTrackArr) {
        if (!MainVersionKt.isVLC4()) {
            return vlcTrackArr;
        }
        ArrayList arrayList = new ArrayList();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        arrayList.add(VersionDependantKt.getDisableTrack(requireActivity));
        for (VlcTrack add : vlcTrackArr) {
            arrayList.add(add);
        }
        return (VlcTrack[]) CollectionsKt.toList(arrayList).toArray(new VlcTrack[0]);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        PlayerOverlayTracksBinding inflate = PlayerOverlayTracksBinding.inflate(getLayoutInflater(), viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.binding = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        return inflate.getRoot();
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        PlayerOverlayTracksBinding playerOverlayTracksBinding = this.binding;
        if (playerOverlayTracksBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            playerOverlayTracksBinding = null;
        }
        playerOverlayTracksBinding.audioTracks.trackTitle.setText(getString(R.string.audio));
        PlayerOverlayTracksBinding playerOverlayTracksBinding2 = this.binding;
        if (playerOverlayTracksBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            playerOverlayTracksBinding2 = null;
        }
        playerOverlayTracksBinding2.videoTracks.trackTitle.setText(getString(R.string.video));
        PlayerOverlayTracksBinding playerOverlayTracksBinding3 = this.binding;
        if (playerOverlayTracksBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            playerOverlayTracksBinding3 = null;
        }
        playerOverlayTracksBinding3.subtitleTracks.trackTitle.setText(getString(R.string.subtitles));
        PlayerOverlayTracksBinding playerOverlayTracksBinding4 = this.binding;
        if (playerOverlayTracksBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            playerOverlayTracksBinding4 = null;
        }
        playerOverlayTracksBinding4.audioTracks.trackList.setLayoutManager(new LinearLayoutManager(requireActivity()));
        PlayerOverlayTracksBinding playerOverlayTracksBinding5 = this.binding;
        if (playerOverlayTracksBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            playerOverlayTracksBinding5 = null;
        }
        playerOverlayTracksBinding5.videoTracks.trackList.setLayoutManager(new LinearLayoutManager(requireActivity()));
        PlayerOverlayTracksBinding playerOverlayTracksBinding6 = this.binding;
        if (playerOverlayTracksBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            playerOverlayTracksBinding6 = null;
        }
        playerOverlayTracksBinding6.subtitleTracks.trackList.setLayoutManager(new LinearLayoutManager(requireActivity()));
        PlayerOverlayTracksBinding playerOverlayTracksBinding7 = this.binding;
        if (playerOverlayTracksBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            playerOverlayTracksBinding7 = null;
        }
        KotlinExtensionsKt.setGone(playerOverlayTracksBinding7.videoTracks.trackMore);
        PlayerOverlayTracksBinding playerOverlayTracksBinding8 = this.binding;
        if (playerOverlayTracksBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            playerOverlayTracksBinding8 = null;
        }
        playerOverlayTracksBinding8.tracksSeparator3.setEnabled(false);
        PlayerOverlayTracksBinding playerOverlayTracksBinding9 = this.binding;
        if (playerOverlayTracksBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            playerOverlayTracksBinding9 = null;
        }
        playerOverlayTracksBinding9.tracksSeparator2.setEnabled(false);
        PlayerOverlayTracksBinding playerOverlayTracksBinding10 = this.binding;
        if (playerOverlayTracksBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            playerOverlayTracksBinding10 = null;
        }
        CollapsibleLinearLayout collapsibleLinearLayout = playerOverlayTracksBinding10.audioTracks.options;
        Intrinsics.checkNotNullExpressionValue(collapsibleLinearLayout, "options");
        generateSeparator$default(this, collapsibleLinearLayout, false, 2, (Object) null);
        PlayerOverlayTracksBinding playerOverlayTracksBinding11 = this.binding;
        if (playerOverlayTracksBinding11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            playerOverlayTracksBinding11 = null;
        }
        CollapsibleLinearLayout collapsibleLinearLayout2 = playerOverlayTracksBinding11.audioTracks.options;
        Intrinsics.checkNotNullExpressionValue(collapsibleLinearLayout2, "options");
        String string = getString(R.string.audio_delay);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        generateOptionItem(collapsibleLinearLayout2, string, R.drawable.ic_delay, VideoTrackOption.AUDIO_DELAY);
        PlayerOverlayTracksBinding playerOverlayTracksBinding12 = this.binding;
        if (playerOverlayTracksBinding12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            playerOverlayTracksBinding12 = null;
        }
        CollapsibleLinearLayout collapsibleLinearLayout3 = playerOverlayTracksBinding12.audioTracks.options;
        Intrinsics.checkNotNullExpressionValue(collapsibleLinearLayout3, "options");
        generateSeparator(collapsibleLinearLayout3, true);
        PlayerOverlayTracksBinding playerOverlayTracksBinding13 = this.binding;
        if (playerOverlayTracksBinding13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            playerOverlayTracksBinding13 = null;
        }
        playerOverlayTracksBinding13.audioTracks.options.setAnimationUpdateListener(new VideoTracksDialog$onViewCreated$1(this));
        PlayerOverlayTracksBinding playerOverlayTracksBinding14 = this.binding;
        if (playerOverlayTracksBinding14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            playerOverlayTracksBinding14 = null;
        }
        CollapsibleLinearLayout collapsibleLinearLayout4 = playerOverlayTracksBinding14.subtitleTracks.options;
        Intrinsics.checkNotNullExpressionValue(collapsibleLinearLayout4, "options");
        generateSeparator$default(this, collapsibleLinearLayout4, false, 2, (Object) null);
        PlayerOverlayTracksBinding playerOverlayTracksBinding15 = this.binding;
        if (playerOverlayTracksBinding15 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            playerOverlayTracksBinding15 = null;
        }
        CollapsibleLinearLayout collapsibleLinearLayout5 = playerOverlayTracksBinding15.subtitleTracks.options;
        Intrinsics.checkNotNullExpressionValue(collapsibleLinearLayout5, "options");
        String string2 = getString(R.string.spu_delay);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        generateOptionItem(collapsibleLinearLayout5, string2, R.drawable.ic_delay, VideoTrackOption.SUB_DELAY);
        PlayerOverlayTracksBinding playerOverlayTracksBinding16 = this.binding;
        if (playerOverlayTracksBinding16 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            playerOverlayTracksBinding16 = null;
        }
        CollapsibleLinearLayout collapsibleLinearLayout6 = playerOverlayTracksBinding16.subtitleTracks.options;
        Intrinsics.checkNotNullExpressionValue(collapsibleLinearLayout6, "options");
        String string3 = getString(R.string.subtitle_select);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        generateOptionItem(collapsibleLinearLayout6, string3, R.drawable.ic_subtitles_file, VideoTrackOption.SUB_PICK);
        PlayerOverlayTracksBinding playerOverlayTracksBinding17 = this.binding;
        if (playerOverlayTracksBinding17 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            playerOverlayTracksBinding17 = null;
        }
        CollapsibleLinearLayout collapsibleLinearLayout7 = playerOverlayTracksBinding17.subtitleTracks.options;
        Intrinsics.checkNotNullExpressionValue(collapsibleLinearLayout7, "options");
        String string4 = getString(R.string.download_subtitles);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
        generateOptionItem(collapsibleLinearLayout7, string4, R.drawable.ic_download_subtitles, VideoTrackOption.SUB_DOWNLOAD);
        PlayerOverlayTracksBinding playerOverlayTracksBinding18 = this.binding;
        if (playerOverlayTracksBinding18 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            playerOverlayTracksBinding18 = null;
        }
        CollapsibleLinearLayout collapsibleLinearLayout8 = playerOverlayTracksBinding18.subtitleTracks.options;
        Intrinsics.checkNotNullExpressionValue(collapsibleLinearLayout8, "options");
        generateSeparator(collapsibleLinearLayout8, true);
        PlayerOverlayTracksBinding playerOverlayTracksBinding19 = this.binding;
        if (playerOverlayTracksBinding19 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            playerOverlayTracksBinding19 = null;
        }
        playerOverlayTracksBinding19.subtitleTracks.options.setAnimationUpdateListener(new VideoTracksDialog$onViewCreated$2(this));
        PlayerOverlayTracksBinding playerOverlayTracksBinding20 = this.binding;
        if (playerOverlayTracksBinding20 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            playerOverlayTracksBinding20 = null;
        }
        playerOverlayTracksBinding20.audioTracks.trackMore.setOnClickListener(new VideoTracksDialog$$ExternalSyntheticLambda1(this));
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        if (AccessibilityHelperKt.isTalkbackIsEnabled(requireActivity)) {
            PlayerOverlayTracksBinding playerOverlayTracksBinding21 = this.binding;
            if (playerOverlayTracksBinding21 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                playerOverlayTracksBinding21 = null;
            }
            playerOverlayTracksBinding21.subtitleTracks.options.onReady(new VideoTracksDialog$onViewCreated$4(this));
            PlayerOverlayTracksBinding playerOverlayTracksBinding22 = this.binding;
            if (playerOverlayTracksBinding22 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                playerOverlayTracksBinding22 = null;
            }
            playerOverlayTracksBinding22.audioTracks.options.onReady(new VideoTracksDialog$onViewCreated$5(this));
        }
        PlayerOverlayTracksBinding playerOverlayTracksBinding23 = this.binding;
        if (playerOverlayTracksBinding23 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            playerOverlayTracksBinding23 = null;
        }
        playerOverlayTracksBinding23.subtitleTracks.trackMore.setOnClickListener(new VideoTracksDialog$$ExternalSyntheticLambda2(this));
        super.onViewCreated(view, bundle);
        FlowKt.launchIn(FlowKt.onEach(PlaybackService.Companion.getServiceFlow(), new VideoTracksDialog$onViewCreated$7(this, (Continuation<? super VideoTracksDialog$onViewCreated$7>) null)), LifecycleOwnerKt.getLifecycleScope(this));
    }

    /* access modifiers changed from: private */
    public static final void onViewCreated$lambda$8(VideoTracksDialog videoTracksDialog, View view) {
        Intrinsics.checkNotNullParameter(videoTracksDialog, "this$0");
        PlayerOverlayTracksBinding playerOverlayTracksBinding = videoTracksDialog.binding;
        PlayerOverlayTracksBinding playerOverlayTracksBinding2 = null;
        if (playerOverlayTracksBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            playerOverlayTracksBinding = null;
        }
        playerOverlayTracksBinding.audioTracks.options.toggle();
        PlayerOverlayTracksBinding playerOverlayTracksBinding3 = videoTracksDialog.binding;
        if (playerOverlayTracksBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            playerOverlayTracksBinding2 = playerOverlayTracksBinding3;
        }
        playerOverlayTracksBinding2.subtitleTracks.options.collapse();
    }

    /* access modifiers changed from: private */
    public static final void onViewCreated$lambda$9(VideoTracksDialog videoTracksDialog, View view) {
        Intrinsics.checkNotNullParameter(videoTracksDialog, "this$0");
        PlayerOverlayTracksBinding playerOverlayTracksBinding = videoTracksDialog.binding;
        PlayerOverlayTracksBinding playerOverlayTracksBinding2 = null;
        if (playerOverlayTracksBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            playerOverlayTracksBinding = null;
        }
        playerOverlayTracksBinding.subtitleTracks.options.toggle();
        PlayerOverlayTracksBinding playerOverlayTracksBinding3 = videoTracksDialog.binding;
        if (playerOverlayTracksBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            playerOverlayTracksBinding2 = playerOverlayTracksBinding3;
        }
        playerOverlayTracksBinding2.audioTracks.options.collapse();
    }

    static /* synthetic */ void generateSeparator$default(VideoTracksDialog videoTracksDialog, ViewGroup viewGroup, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        videoTracksDialog.generateSeparator(viewGroup, z);
    }

    private final void generateSeparator(ViewGroup viewGroup, boolean z) {
        View view = new View(getContext());
        view.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white_transparent_50));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, KotlinExtensionsKt.getDp(1));
        layoutParams.setMarginStart(z ? KotlinExtensionsKt.getDp(56) : KotlinExtensionsKt.getDp(16));
        layoutParams.setMarginEnd(KotlinExtensionsKt.getDp(16));
        layoutParams.topMargin = KotlinExtensionsKt.getDp(8);
        layoutParams.bottomMargin = KotlinExtensionsKt.getDp(8);
        view.setLayoutParams(layoutParams);
        viewGroup.addView(view);
    }

    private final void generateOptionItem(ViewGroup viewGroup, String str, int i, VideoTrackOption videoTrackOption) {
        View inflate = getLayoutInflater().inflate(R.layout.player_overlay_track_option_item, (ViewGroup) null);
        ((TextView) inflate.findViewById(R.id.option_title)).setText(str);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        ((ImageView) inflate.findViewById(R.id.option_icon)).setImageBitmap(BitmapUtilKt.getBitmapFromDrawable$default(requireContext, i, 0, 0, 6, (Object) null));
        inflate.setOnClickListener(new VideoTracksDialog$$ExternalSyntheticLambda0(this, videoTrackOption));
        viewGroup.addView(inflate);
    }

    /* access modifiers changed from: private */
    public static final void generateOptionItem$lambda$10(VideoTracksDialog videoTracksDialog, VideoTrackOption videoTrackOption, View view) {
        Intrinsics.checkNotNullParameter(videoTracksDialog, "this$0");
        Intrinsics.checkNotNullParameter(videoTrackOption, "$optionId");
        videoTracksDialog.getMenuItemListener().invoke(videoTrackOption);
        videoTracksDialog.dismiss();
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/VideoTracksDialog$Companion;", "Lorg/videolan/tools/DependencyProvider;", "", "()V", "TAG", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: VideoTracksDialog.kt */
    public static final class Companion extends DependencyProvider<Object> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/VideoTracksDialog$TrackType;", "", "(Ljava/lang/String;I)V", "VIDEO", "AUDIO", "SPU", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: VideoTracksDialog.kt */
    public enum TrackType {
        VIDEO,
        AUDIO,
        SPU;

        public static EnumEntries<TrackType> getEntries() {
            return $ENTRIES;
        }

        static {
            TrackType[] $values;
            $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/VideoTracksDialog$VideoTrackOption;", "", "(Ljava/lang/String;I)V", "SUB_DELAY", "SUB_PICK", "SUB_DOWNLOAD", "AUDIO_DELAY", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: VideoTracksDialog.kt */
    public enum VideoTrackOption {
        SUB_DELAY,
        SUB_PICK,
        SUB_DOWNLOAD,
        AUDIO_DELAY;

        public static EnumEntries<VideoTrackOption> getEntries() {
            return $ENTRIES;
        }

        static {
            VideoTrackOption[] $values;
            $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
        }
    }
}
