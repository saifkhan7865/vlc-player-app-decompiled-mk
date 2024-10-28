package org.videolan.vlc.gui.audio;

import android.animation.TimeInterpolator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleObserver;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;
import androidx.window.layout.FoldingFeature;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.PlaybackServiceKt;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.AudioPlayerBinding;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.gui.view.HeaderMediaSwitcher;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010#\u001a\u00020$H\u0002J\b\u0010%\u001a\u00020\u001cH\u0016J\b\u0010&\u001a\u00020$H\u0016J\u0010\u0010'\u001a\u00020$2\u0006\u0010(\u001a\u00020\u001cH\u0016J\u0010\u0010)\u001a\u00020$2\u0006\u0010*\u001a\u00020+H\u0016J\b\u0010,\u001a\u00020$H\u0003J\u0010\u0010\u001d\u001a\u00020$2\u0006\u0010\u000e\u001a\u00020\u001cH\u0016J\b\u0010-\u001a\u00020\u001cH\u0002J\u0010\u0010.\u001a\u00020$2\u0006\u0010\u001d\u001a\u00020\u001cH\u0002J\b\u0010/\u001a\u00020$H\u0016J\u000e\u00100\u001a\u00020$H@¢\u0006\u0002\u00101J\u0014\u00102\u001a\u00020$*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX.¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\f\u001a\u00020\r8\u0002@\u0002X\u000e¢\u0006\u0002\n\u0000R(\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f@VX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0016X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0016X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0016X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0016X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u000e\u001a\u00020\u001c@BX\u000e¢\u0006\b\n\u0000\"\u0004\b\u001e\u0010\u001fR\u000e\u0010 \u001a\u00020\u0016X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0004¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lorg/videolan/vlc/gui/audio/AudioPlayerAnimator;", "Lorg/videolan/vlc/gui/audio/IAudioPlayerAnimator;", "Landroidx/lifecycle/LifecycleObserver;", "()V", "audioPlayer", "Lorg/videolan/vlc/gui/audio/AudioPlayer;", "binding", "Lorg/videolan/vlc/databinding/AudioPlayerBinding;", "cl", "Landroidx/constraintlayout/widget/ConstraintLayout;", "currentCoverArt", "", "defaultBackgroundId", "", "value", "Landroidx/window/layout/FoldingFeature;", "foldingFeature", "getFoldingFeature", "()Landroidx/window/layout/FoldingFeature;", "setFoldingFeature", "(Landroidx/window/layout/FoldingFeature;)V", "headerHidePlaylistConstraint", "Landroidx/constraintlayout/widget/ConstraintSet;", "headerHidePlaylistLandscapeConstraint", "headerShowPlaylistConstraint", "hidePlaylistConstraint", "hidePlaylistLandscapeConstraint", "inSearch", "", "showCover", "setShowCover", "(Z)V", "showPlaylistConstraint", "transition", "Landroidx/transition/AutoTransition;", "initConstraintSets", "", "isShowingCover", "manageHinge", "manageSearchVisibilities", "filter", "onSlide", "slideOffset", "", "setDefaultBackground", "showTabletControls", "startConstraintAnimation", "switchShowCover", "updateBackground", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setupAnimator", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioPlayerAnimator.kt */
public final class AudioPlayerAnimator implements IAudioPlayerAnimator, LifecycleObserver {
    private AudioPlayer audioPlayer;
    private AudioPlayerBinding binding;
    private ConstraintLayout cl;
    private String currentCoverArt;
    private int defaultBackgroundId = -1;
    private FoldingFeature foldingFeature;
    private final ConstraintSet headerHidePlaylistConstraint = new ConstraintSet();
    private final ConstraintSet headerHidePlaylistLandscapeConstraint = new ConstraintSet();
    private final ConstraintSet headerShowPlaylistConstraint = new ConstraintSet();
    private final ConstraintSet hidePlaylistConstraint = new ConstraintSet();
    private final ConstraintSet hidePlaylistLandscapeConstraint = new ConstraintSet();
    private boolean inSearch;
    private boolean showCover;
    private final ConstraintSet showPlaylistConstraint = new ConstraintSet();
    private final AutoTransition transition;

    public AudioPlayerAnimator() {
        AutoTransition autoTransition = new AutoTransition();
        autoTransition.setInterpolator((TimeInterpolator) new AccelerateDecelerateInterpolator());
        autoTransition.setDuration(300);
        this.transition = autoTransition;
    }

    public FoldingFeature getFoldingFeature() {
        return this.foldingFeature;
    }

    public void setFoldingFeature(FoldingFeature foldingFeature2) {
        this.foldingFeature = foldingFeature2;
        initConstraintSets();
        manageHinge();
    }

    public void setupAnimator(AudioPlayer audioPlayer2, AudioPlayerBinding audioPlayerBinding) {
        Intrinsics.checkNotNullParameter(audioPlayer2, "<this>");
        Intrinsics.checkNotNullParameter(audioPlayerBinding, "binding");
        this.audioPlayer = audioPlayer2;
        View root = audioPlayerBinding.getRoot();
        Intrinsics.checkNotNull(root, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout");
        ConstraintLayout constraintLayout = (ConstraintLayout) root;
        this.cl = constraintLayout;
        this.binding = audioPlayerBinding;
        ConstraintSet constraintSet = this.showPlaylistConstraint;
        ConstraintLayout constraintLayout2 = null;
        if (constraintLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cl");
            constraintLayout = null;
        }
        constraintSet.clone(constraintLayout);
        ConstraintSet constraintSet2 = this.hidePlaylistConstraint;
        ConstraintLayout constraintLayout3 = this.cl;
        if (constraintLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cl");
            constraintLayout3 = null;
        }
        constraintSet2.clone(constraintLayout3);
        ConstraintSet constraintSet3 = this.hidePlaylistLandscapeConstraint;
        ConstraintLayout constraintLayout4 = this.cl;
        if (constraintLayout4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cl");
        } else {
            constraintLayout2 = constraintLayout4;
        }
        constraintSet3.clone(constraintLayout2);
        this.headerShowPlaylistConstraint.clone(audioPlayerBinding.header);
        this.headerHidePlaylistConstraint.clone(audioPlayerBinding.header);
        this.headerHidePlaylistLandscapeConstraint.clone(audioPlayerBinding.header);
        UiTools uiTools = UiTools.INSTANCE;
        FragmentActivity requireActivity = audioPlayer2.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        this.defaultBackgroundId = uiTools.getResourceFromAttribute(requireActivity, R.attr.background_default);
        audioPlayer2.getLifecycle().addObserver(this);
        initConstraintSets();
        startConstraintAnimation(this.showCover);
    }

    private final void setShowCover(boolean z) {
        if (z != this.showCover) {
            startConstraintAnimation(z);
            AudioPlayer audioPlayer2 = this.audioPlayer;
            AudioPlayer audioPlayer3 = null;
            if (audioPlayer2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("audioPlayer");
                audioPlayer2 = null;
            }
            Button retrieveAbRepeatAddMarker = audioPlayer2.retrieveAbRepeatAddMarker();
            if (retrieveAbRepeatAddMarker != null) {
                AudioPlayer audioPlayer4 = this.audioPlayer;
                if (audioPlayer4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("audioPlayer");
                    audioPlayer4 = null;
                }
                PlaybackService service = audioPlayer4.getPlaylistModel().getService();
                if (service != null) {
                    AudioPlayerBinding audioPlayerBinding = this.binding;
                    if (audioPlayerBinding == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        audioPlayerBinding = null;
                    }
                    ImageView imageView = audioPlayerBinding.abRepeatReset;
                    Intrinsics.checkNotNullExpressionValue(imageView, "abRepeatReset");
                    View view = imageView;
                    AudioPlayerBinding audioPlayerBinding2 = this.binding;
                    if (audioPlayerBinding2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        audioPlayerBinding2 = null;
                    }
                    ImageView imageView2 = audioPlayerBinding2.abRepeatStop;
                    Intrinsics.checkNotNullExpressionValue(imageView2, "abRepeatStop");
                    View view2 = imageView2;
                    AudioPlayerBinding audioPlayerBinding3 = this.binding;
                    if (audioPlayerBinding3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        audioPlayerBinding3 = null;
                    }
                    View view3 = audioPlayerBinding3.abRepeatContainer;
                    Intrinsics.checkNotNullExpressionValue(view3, "abRepeatContainer");
                    PlaybackServiceKt.manageAbRepeatStep(service, view, view2, view3, retrieveAbRepeatAddMarker);
                }
            }
            this.showCover = z;
            onSlide(1.0f);
            AudioPlayerBinding audioPlayerBinding4 = this.binding;
            if (audioPlayerBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                audioPlayerBinding4 = null;
            }
            audioPlayerBinding4.playlistSwitch.setImageResource(this.showCover ? R.drawable.ic_playlist_audio : R.drawable.ic_playlist_audio_on);
            AudioPlayerBinding audioPlayerBinding5 = this.binding;
            if (audioPlayerBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                audioPlayerBinding5 = null;
            }
            ImageView imageView3 = audioPlayerBinding5.playlistSwitch;
            AudioPlayer audioPlayer5 = this.audioPlayer;
            if (audioPlayer5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("audioPlayer");
                audioPlayer5 = null;
            }
            imageView3.setContentDescription(audioPlayer5.getString(this.showCover ? R.string.hide_playlist : R.string.show_playlist));
            AudioPlayerBinding audioPlayerBinding6 = this.binding;
            if (audioPlayerBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                audioPlayerBinding6 = null;
            }
            ImageView imageView4 = audioPlayerBinding6.playlistSwitch;
            AudioPlayer audioPlayer6 = this.audioPlayer;
            if (audioPlayer6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("audioPlayer");
                audioPlayer6 = null;
            }
            imageView4.announceForAccessibility(audioPlayer6.getString(this.showCover ? R.string.hide_playlist : R.string.show_playlist));
            AudioPlayer audioPlayer7 = this.audioPlayer;
            if (audioPlayer7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("audioPlayer");
            } else {
                audioPlayer3 = audioPlayer7;
            }
            audioPlayer3.setBottomMargin();
        }
    }

    private final void startConstraintAnimation(boolean z) {
        ConstraintSet constraintSet;
        ConstraintSet constraintSet2;
        ConstraintLayout constraintLayout = this.cl;
        AudioPlayer audioPlayer2 = null;
        if (constraintLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cl");
            constraintLayout = null;
        }
        TransitionManager.beginDelayedTransition(constraintLayout, this.transition);
        if (!z) {
            constraintSet = this.showPlaylistConstraint;
        } else {
            AudioPlayer audioPlayer3 = this.audioPlayer;
            if (audioPlayer3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("audioPlayer");
                audioPlayer3 = null;
            }
            if (audioPlayer3.getResources().getConfiguration().orientation == 2) {
                constraintSet = this.hidePlaylistLandscapeConstraint;
            } else {
                constraintSet = this.hidePlaylistConstraint;
            }
        }
        ConstraintLayout constraintLayout2 = this.cl;
        if (constraintLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cl");
            constraintLayout2 = null;
        }
        constraintSet.applyTo(constraintLayout2);
        if (!z) {
            constraintSet2 = this.headerShowPlaylistConstraint;
        } else {
            AudioPlayer audioPlayer4 = this.audioPlayer;
            if (audioPlayer4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("audioPlayer");
                audioPlayer4 = null;
            }
            if (audioPlayer4.getResources().getConfiguration().orientation == 2) {
                constraintSet2 = this.headerHidePlaylistLandscapeConstraint;
            } else {
                constraintSet2 = this.headerHidePlaylistConstraint;
            }
        }
        AudioPlayerBinding audioPlayerBinding = this.binding;
        if (audioPlayerBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            audioPlayerBinding = null;
        }
        constraintSet2.applyTo(audioPlayerBinding.header);
        ConstraintSet constraintSet3 = this.headerShowPlaylistConstraint;
        AudioPlayerBinding audioPlayerBinding2 = this.binding;
        if (audioPlayerBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            audioPlayerBinding2 = null;
        }
        constraintSet3.applyTo(audioPlayerBinding2.header);
        AudioPlayer audioPlayer5 = this.audioPlayer;
        if (audioPlayer5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioPlayer");
        } else {
            audioPlayer2 = audioPlayer5;
        }
        audioPlayer2.showChips();
    }

    public void switchShowCover() {
        setShowCover(!this.showCover);
    }

    public boolean isShowingCover() {
        return this.showCover;
    }

    public void showCover(boolean z) {
        setShowCover(z);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0085  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0086 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void initConstraintSets() {
        /*
            r15 = this;
            r0 = 3
            androidx.constraintlayout.widget.ConstraintSet[] r1 = new androidx.constraintlayout.widget.ConstraintSet[r0]
            androidx.constraintlayout.widget.ConstraintSet r2 = r15.headerShowPlaylistConstraint
            r3 = 0
            r1[r3] = r2
            androidx.constraintlayout.widget.ConstraintSet r2 = r15.headerHidePlaylistConstraint
            r4 = 1
            r1[r4] = r2
            androidx.constraintlayout.widget.ConstraintSet r2 = r15.headerHidePlaylistLandscapeConstraint
            r5 = 2
            r1[r5] = r2
            r2 = 0
        L_0x0013:
            r6 = 0
            r7 = 8
            r8 = 4
            if (r2 >= r0) goto L_0x008c
            r9 = r1[r2]
            int r10 = org.videolan.vlc.R.id.header_shuffle
            boolean r11 = r15.showTabletControls()
            if (r11 == 0) goto L_0x003a
            org.videolan.vlc.gui.audio.AudioPlayer r11 = r15.audioPlayer
            if (r11 != 0) goto L_0x002d
            java.lang.String r11 = "audioPlayer"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r11)
            goto L_0x002e
        L_0x002d:
            r6 = r11
        L_0x002e:
            org.videolan.vlc.viewmodels.PlaylistModel r6 = r6.getPlaylistModel()
            boolean r6 = r6.getCanShuffle()
            if (r6 == 0) goto L_0x003a
            r6 = 0
            goto L_0x003c
        L_0x003a:
            r6 = 8
        L_0x003c:
            r9.setVisibility(r10, r6)
            int r6 = org.videolan.vlc.R.id.header_previous
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            int r10 = org.videolan.vlc.R.id.header_large_play_pause
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            int r11 = org.videolan.vlc.R.id.header_next
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            int r12 = org.videolan.vlc.R.id.header_repeat
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
            java.lang.Integer[] r13 = new java.lang.Integer[r8]
            r13[r3] = r6
            r13[r4] = r10
            r13[r5] = r11
            r13[r0] = r12
            r6 = 0
        L_0x0062:
            if (r6 >= r8) goto L_0x007c
            r10 = r13[r6]
            java.lang.Number r10 = (java.lang.Number) r10
            int r10 = r10.intValue()
            boolean r11 = r15.showTabletControls()
            if (r11 == 0) goto L_0x0074
            r11 = 0
            goto L_0x0076
        L_0x0074:
            r11 = 8
        L_0x0076:
            r9.setVisibility(r10, r11)
            int r6 = r6 + 1
            goto L_0x0062
        L_0x007c:
            int r6 = org.videolan.vlc.R.id.header_play_pause
            boolean r8 = r15.showTabletControls()
            if (r8 == 0) goto L_0x0085
            goto L_0x0086
        L_0x0085:
            r7 = 0
        L_0x0086:
            r9.setVisibility(r6, r7)
            int r2 = r2 + 1
            goto L_0x0013
        L_0x008c:
            androidx.constraintlayout.widget.ConstraintSet r1 = r15.hidePlaylistConstraint
            int r2 = org.videolan.vlc.R.id.songs_list
            r1.setVisibility(r2, r7)
            androidx.constraintlayout.widget.ConstraintSet r1 = r15.hidePlaylistConstraint
            int r2 = org.videolan.vlc.R.id.cover_media_switcher
            r1.setVisibility(r2, r3)
            androidx.constraintlayout.widget.ConstraintSet r1 = r15.hidePlaylistConstraint
            int r2 = org.videolan.vlc.R.id.audio_rewind_10
            r1.setVisibility(r2, r3)
            androidx.constraintlayout.widget.ConstraintSet r1 = r15.hidePlaylistConstraint
            int r2 = org.videolan.vlc.R.id.audio_rewind_text
            r1.setVisibility(r2, r3)
            androidx.constraintlayout.widget.ConstraintSet r1 = r15.hidePlaylistConstraint
            int r2 = org.videolan.vlc.R.id.audio_forward_10
            r1.setVisibility(r2, r3)
            androidx.constraintlayout.widget.ConstraintSet r1 = r15.hidePlaylistConstraint
            int r2 = org.videolan.vlc.R.id.audio_forward_text
            r1.setVisibility(r2, r3)
            androidx.constraintlayout.widget.ConstraintSet r1 = r15.headerHidePlaylistConstraint
            int r2 = org.videolan.vlc.R.id.playback_chips
            r1.clear(r2, r8)
            androidx.constraintlayout.widget.ConstraintSet r1 = r15.headerHidePlaylistConstraint
            int r2 = org.videolan.vlc.R.id.playback_chips
            r1.clear(r2, r0)
            androidx.constraintlayout.widget.ConstraintSet r1 = r15.headerHidePlaylistConstraint
            int r2 = org.videolan.vlc.R.id.playback_chips
            r1.connect(r2, r0, r3, r0)
            androidx.constraintlayout.widget.ConstraintSet r1 = r15.headerHidePlaylistConstraint
            int r2 = org.videolan.vlc.R.id.playback_chips
            int r9 = org.videolan.vlc.R.id.guideline_header_bottom
            r1.connect(r2, r8, r9, r8)
            androidx.constraintlayout.widget.ConstraintSet r1 = r15.hidePlaylistLandscapeConstraint
            int r2 = org.videolan.vlc.R.id.songs_list
            r1.setVisibility(r2, r7)
            androidx.constraintlayout.widget.ConstraintSet r1 = r15.hidePlaylistLandscapeConstraint
            int r2 = org.videolan.vlc.R.id.cover_media_switcher
            r1.setVisibility(r2, r3)
            androidx.constraintlayout.widget.ConstraintSet r1 = r15.hidePlaylistLandscapeConstraint
            int r2 = org.videolan.vlc.R.id.track_info_container
            r1.setVisibility(r2, r3)
            boolean r1 = r15.showTabletControls()
            if (r1 == 0) goto L_0x0150
            androidx.constraintlayout.widget.ConstraintSet r1 = r15.hidePlaylistLandscapeConstraint
            int r2 = org.videolan.vlc.R.id.track_info_container
            r7 = -2
            r1.constrainHeight(r2, r7)
            androidx.constraintlayout.widget.ConstraintSet r1 = r15.hidePlaylistLandscapeConstraint
            int r2 = org.videolan.vlc.R.id.cover_media_switcher
            r1.setDimensionRatio(r2, r6)
            androidx.constraintlayout.widget.ConstraintSet r1 = r15.hidePlaylistLandscapeConstraint
            int r2 = org.videolan.vlc.R.id.track_info_container
            r1.setMargin(r2, r0, r3)
            androidx.constraintlayout.widget.ConstraintSet r1 = r15.hidePlaylistLandscapeConstraint
            int r2 = org.videolan.vlc.R.id.cover_media_switcher
            int r6 = org.videolan.vlc.R.id.header
            r1.connect(r2, r0, r6, r8)
            androidx.constraintlayout.widget.ConstraintSet r1 = r15.hidePlaylistLandscapeConstraint
            int r2 = org.videolan.vlc.R.id.cover_media_switcher
            r6 = 7
            r1.connect(r2, r6, r3, r6)
            androidx.constraintlayout.widget.ConstraintSet r1 = r15.hidePlaylistLandscapeConstraint
            int r2 = org.videolan.vlc.R.id.track_info_container
            r7 = 6
            r1.connect(r2, r7, r3, r7)
            androidx.constraintlayout.widget.ConstraintSet r1 = r15.hidePlaylistLandscapeConstraint
            int r2 = org.videolan.vlc.R.id.audio_play_progress
            r1.connect(r2, r6, r3, r6)
            androidx.constraintlayout.widget.ConstraintSet r7 = r15.hidePlaylistLandscapeConstraint
            int r8 = org.videolan.vlc.R.id.header
            int r10 = org.videolan.vlc.R.id.time
            int r1 = org.videolan.vlc.R.id.cover_media_switcher
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            int r2 = org.videolan.vlc.R.id.track_info_container
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            int r6 = org.videolan.vlc.R.id.audio_play_progress
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            java.lang.Integer[] r0 = new java.lang.Integer[r0]
            r0[r3] = r1
            r0[r4] = r2
            r0[r5] = r6
            int[] r12 = kotlin.collections.ArraysKt.toIntArray(r0)
            r13 = 0
            r14 = 2
            r9 = 4
            r11 = 3
            r7.createVerticalChain(r8, r9, r10, r11, r12, r13, r14)
        L_0x0150:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.audio.AudioPlayerAnimator.initConstraintSets():void");
    }

    private final boolean showTabletControls() {
        AudioPlayer audioPlayer2 = this.audioPlayer;
        FoldingFeature.Orientation orientation = null;
        if (audioPlayer2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioPlayer");
            audioPlayer2 = null;
        }
        if (audioPlayer2.isTablet()) {
            FoldingFeature foldingFeature2 = getFoldingFeature();
            if (Intrinsics.areEqual((Object) foldingFeature2 != null ? foldingFeature2.getOcclusionType() : null, (Object) FoldingFeature.OcclusionType.FULL)) {
                FoldingFeature foldingFeature3 = getFoldingFeature();
                if (foldingFeature3 != null) {
                    orientation = foldingFeature3.getOrientation();
                }
                if (!Intrinsics.areEqual((Object) orientation, (Object) FoldingFeature.Orientation.VERTICAL)) {
                    return true;
                }
            }
            return true;
        }
        return false;
    }

    /* JADX WARNING: type inference failed for: r0v13, types: [androidx.fragment.app.FragmentActivity] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void manageHinge() {
        /*
            r13 = this;
            androidx.window.layout.FoldingFeature r0 = r13.getFoldingFeature()
            r1 = 0
            if (r0 == 0) goto L_0x000c
            androidx.window.layout.FoldingFeature$OcclusionType r0 = r0.getOcclusionType()
            goto L_0x000d
        L_0x000c:
            r0 = r1
        L_0x000d:
            androidx.window.layout.FoldingFeature$OcclusionType r2 = androidx.window.layout.FoldingFeature.OcclusionType.FULL
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r2)
            if (r0 == 0) goto L_0x0106
            androidx.window.layout.FoldingFeature r0 = r13.getFoldingFeature()
            if (r0 == 0) goto L_0x0020
            androidx.window.layout.FoldingFeature$Orientation r0 = r0.getOrientation()
            goto L_0x0021
        L_0x0020:
            r0 = r1
        L_0x0021:
            androidx.window.layout.FoldingFeature$Orientation r2 = androidx.window.layout.FoldingFeature.Orientation.VERTICAL
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r2)
            if (r0 != 0) goto L_0x002b
            goto L_0x0106
        L_0x002b:
            org.videolan.tools.Settings r0 = org.videolan.tools.Settings.INSTANCE
            org.videolan.vlc.gui.audio.AudioPlayer r2 = r13.audioPlayer
            java.lang.String r3 = "audioPlayer"
            if (r2 != 0) goto L_0x0037
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r2 = r1
        L_0x0037:
            androidx.fragment.app.FragmentActivity r2 = r2.requireActivity()
            java.lang.String r4 = "requireActivity(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)
            java.lang.Object r0 = r0.getInstance(r2)
            android.content.SharedPreferences r0 = (android.content.SharedPreferences) r0
            java.lang.String r2 = "audio_hinge_on_right"
            r4 = 1
            boolean r0 = r0.getBoolean(r2, r4)
            org.videolan.vlc.databinding.AudioPlayerBinding r2 = r13.binding
            java.lang.String r5 = "binding"
            if (r2 != 0) goto L_0x0057
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
            r2 = r1
        L_0x0057:
            androidx.constraintlayout.widget.Guideline r2 = r2.centerGuideline
            r6 = 2
            androidx.constraintlayout.widget.ConstraintSet[] r7 = new androidx.constraintlayout.widget.ConstraintSet[r6]
            androidx.constraintlayout.widget.ConstraintSet r8 = r13.showPlaylistConstraint
            r9 = 0
            r7[r9] = r8
            androidx.constraintlayout.widget.ConstraintSet r8 = r13.hidePlaylistConstraint
            r7[r4] = r8
            r4 = 0
        L_0x0066:
            if (r4 >= r6) goto L_0x00d8
            r8 = r7[r4]
            org.videolan.vlc.databinding.AudioPlayerBinding r10 = r13.binding
            if (r10 != 0) goto L_0x0072
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
            r10 = r1
        L_0x0072:
            android.widget.ImageView r10 = r10.shuffle
            int r10 = r10.getId()
            if (r0 != 0) goto L_0x007c
            r11 = 0
            goto L_0x0080
        L_0x007c:
            int r11 = r2.getId()
        L_0x0080:
            r12 = 6
            r8.connect(r10, r12, r11, r12)
            org.videolan.vlc.databinding.AudioPlayerBinding r10 = r13.binding
            if (r10 != 0) goto L_0x008c
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
            r10 = r1
        L_0x008c:
            android.widget.ImageView r10 = r10.repeat
            int r10 = r10.getId()
            if (r0 == 0) goto L_0x0096
            r11 = 0
            goto L_0x009a
        L_0x0096:
            int r11 = r2.getId()
        L_0x009a:
            r12 = 7
            r8.connect(r10, r12, r11, r12)
            org.videolan.vlc.databinding.AudioPlayerBinding r10 = r13.binding
            if (r10 != 0) goto L_0x00a6
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
            r10 = r1
        L_0x00a6:
            android.widget.ImageView r10 = r10.hingeGoLeft
            java.lang.String r11 = "hingeGoLeft"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r11)
            int r10 = r10.getId()
            r11 = 8
            if (r0 == 0) goto L_0x00b7
            r12 = 0
            goto L_0x00b9
        L_0x00b7:
            r12 = 8
        L_0x00b9:
            r8.setVisibility(r10, r12)
            org.videolan.vlc.databinding.AudioPlayerBinding r10 = r13.binding
            if (r10 != 0) goto L_0x00c4
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
            r10 = r1
        L_0x00c4:
            android.widget.ImageView r10 = r10.hingeGoRight
            java.lang.String r12 = "hingeGoRight"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r12)
            int r10 = r10.getId()
            if (r0 != 0) goto L_0x00d2
            r11 = 0
        L_0x00d2:
            r8.setVisibility(r10, r11)
            int r4 = r4 + 1
            goto L_0x0066
        L_0x00d8:
            boolean r0 = r13.showCover
            r13.startConstraintAnimation(r0)
            org.videolan.vlc.gui.audio.AudioPlayer r0 = r13.audioPlayer
            if (r0 != 0) goto L_0x00e5
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r0 = r1
        L_0x00e5:
            androidx.fragment.app.FragmentActivity r0 = r0.getActivity()
            boolean r2 = r0 instanceof org.videolan.vlc.gui.AudioPlayerContainerActivity
            if (r2 == 0) goto L_0x00f0
            r1 = r0
            org.videolan.vlc.gui.AudioPlayerContainerActivity r1 = (org.videolan.vlc.gui.AudioPlayerContainerActivity) r1
        L_0x00f0:
            if (r1 == 0) goto L_0x0106
            org.videolan.vlc.gui.helpers.PlayerBehavior r0 = r1.getBehavior()
            if (r0 == 0) goto L_0x0106
            int r0 = r0.getState()
            r1 = 4
            if (r0 != r1) goto L_0x0101
            r0 = 0
            goto L_0x0103
        L_0x0101:
            r0 = 1065353216(0x3f800000, float:1.0)
        L_0x0103:
            r13.onSlide(r0)
        L_0x0106:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.audio.AudioPlayerAnimator.manageHinge():void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0113  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0117  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0029  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object updateBackground(kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            r10 = this;
            boolean r0 = r11 instanceof org.videolan.vlc.gui.audio.AudioPlayerAnimator$updateBackground$1
            if (r0 == 0) goto L_0x0014
            r0 = r11
            org.videolan.vlc.gui.audio.AudioPlayerAnimator$updateBackground$1 r0 = (org.videolan.vlc.gui.audio.AudioPlayerAnimator$updateBackground$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.gui.audio.AudioPlayerAnimator$updateBackground$1 r0 = new org.videolan.vlc.gui.audio.AudioPlayerAnimator$updateBackground$1
            r0.<init>(r10, r11)
        L_0x0019:
            r6 = r0
            java.lang.Object r11 = r6.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r2 = 2
            java.lang.String r3 = "binding"
            r4 = 1
            r5 = 0
            if (r1 == 0) goto L_0x0047
            if (r1 == r4) goto L_0x003a
            if (r1 != r2) goto L_0x0032
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x0145
        L_0x0032:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L_0x003a:
            java.lang.Object r1 = r6.L$1
            org.videolan.vlc.gui.AudioPlayerContainerActivity r1 = (org.videolan.vlc.gui.AudioPlayerContainerActivity) r1
            java.lang.Object r4 = r6.L$0
            org.videolan.vlc.gui.audio.AudioPlayerAnimator r4 = (org.videolan.vlc.gui.audio.AudioPlayerAnimator) r4
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x010f
        L_0x0047:
            kotlin.ResultKt.throwOnFailure(r11)
            org.videolan.tools.Settings r11 = org.videolan.tools.Settings.INSTANCE
            org.videolan.vlc.gui.audio.AudioPlayer r1 = r10.audioPlayer
            java.lang.String r7 = "audioPlayer"
            if (r1 != 0) goto L_0x0056
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r7)
            r1 = r5
        L_0x0056:
            androidx.fragment.app.FragmentActivity r1 = r1.requireActivity()
            java.lang.String r8 = "requireActivity(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r8)
            java.lang.Object r11 = r11.getInstance(r1)
            android.content.SharedPreferences r11 = (android.content.SharedPreferences) r11
            java.lang.String r1 = "blurred_cover_background"
            boolean r11 = r11.getBoolean(r1, r4)
            if (r11 == 0) goto L_0x014f
            org.videolan.vlc.gui.audio.AudioPlayer r11 = r10.audioPlayer
            if (r11 != 0) goto L_0x0075
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r7)
            r11 = r5
        L_0x0075:
            org.videolan.vlc.viewmodels.PlaylistModel r11 = r11.getPlaylistModel()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r11 = r11.getCurrentMediaWrapper()
            if (r11 != 0) goto L_0x0082
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L_0x0082:
            java.lang.String r1 = r10.currentCoverArt
            java.lang.String r8 = r11.getArtworkMrl()
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r8)
            if (r1 == 0) goto L_0x0091
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L_0x0091:
            java.lang.String r1 = r11.getArtworkMrl()
            r10.currentCoverArt = r1
            java.lang.String r1 = r11.getArtworkMrl()
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            if (r1 == 0) goto L_0x014b
            int r1 = r1.length()
            if (r1 != 0) goto L_0x00a7
            goto L_0x014b
        L_0x00a7:
            org.videolan.vlc.databinding.AudioPlayerBinding r1 = r10.binding
            if (r1 != 0) goto L_0x00af
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r1 = r5
        L_0x00af:
            androidx.constraintlayout.widget.ConstraintLayout r1 = r1.contentLayout
            int r1 = r1.getWidth()
            if (r1 <= 0) goto L_0x00c6
            org.videolan.vlc.databinding.AudioPlayerBinding r1 = r10.binding
            if (r1 != 0) goto L_0x00bf
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r1 = r5
        L_0x00bf:
            androidx.constraintlayout.widget.ConstraintLayout r1 = r1.contentLayout
            int r1 = r1.getWidth()
            goto L_0x00da
        L_0x00c6:
            org.videolan.vlc.gui.audio.AudioPlayer r1 = r10.audioPlayer
            if (r1 != 0) goto L_0x00ce
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r7)
            r1 = r5
        L_0x00ce:
            androidx.fragment.app.FragmentActivity r1 = r1.getActivity()
            if (r1 == 0) goto L_0x0148
            android.app.Activity r1 = (android.app.Activity) r1
            int r1 = org.videolan.vlc.util.KextensionsKt.getScreenWidth(r1)
        L_0x00da:
            org.videolan.vlc.gui.audio.AudioPlayer r8 = r10.audioPlayer
            if (r8 != 0) goto L_0x00e2
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r7)
            r8 = r5
        L_0x00e2:
            androidx.fragment.app.FragmentActivity r7 = r8.getActivity()
            boolean r8 = r7 instanceof org.videolan.vlc.gui.AudioPlayerContainerActivity
            if (r8 == 0) goto L_0x00ed
            org.videolan.vlc.gui.AudioPlayerContainerActivity r7 = (org.videolan.vlc.gui.AudioPlayerContainerActivity) r7
            goto L_0x00ee
        L_0x00ed:
            r7 = r5
        L_0x00ee:
            if (r7 != 0) goto L_0x00f3
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L_0x00f3:
            kotlinx.coroutines.CoroutineDispatcher r8 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r8 = (kotlin.coroutines.CoroutineContext) r8
            org.videolan.vlc.gui.audio.AudioPlayerAnimator$updateBackground$cover$1 r9 = new org.videolan.vlc.gui.audio.AudioPlayerAnimator$updateBackground$cover$1
            r9.<init>(r11, r1, r5)
            kotlin.jvm.functions.Function2 r9 = (kotlin.jvm.functions.Function2) r9
            r6.L$0 = r10
            r6.L$1 = r7
            r6.label = r4
            java.lang.Object r11 = kotlinx.coroutines.BuildersKt.withContext(r8, r9, r6)
            if (r11 != r0) goto L_0x010d
            return r0
        L_0x010d:
            r4 = r10
            r1 = r7
        L_0x010f:
            android.graphics.Bitmap r11 = (android.graphics.Bitmap) r11
            if (r11 != 0) goto L_0x0117
            r4.setDefaultBackground()
            goto L_0x0154
        L_0x0117:
            org.videolan.vlc.gui.helpers.UiTools r7 = org.videolan.vlc.gui.helpers.UiTools.INSTANCE
            org.videolan.vlc.databinding.AudioPlayerBinding r4 = r4.binding
            if (r4 != 0) goto L_0x0121
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r4 = r5
        L_0x0121:
            android.widget.ImageView r3 = r4.backgroundView
            java.lang.String r4 = "backgroundView"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
            org.videolan.vlc.gui.helpers.UiTools r4 = org.videolan.vlc.gui.helpers.UiTools.INSTANCE
            android.content.Context r1 = (android.content.Context) r1
            int r8 = org.videolan.vlc.R.attr.audio_player_background_tint
            int r8 = r4.getColorFromAttribute(r1, r8)
            r6.L$0 = r5
            r6.L$1 = r5
            r6.label = r2
            r4 = 1097859072(0x41700000, float:15.0)
            r1 = r7
            r2 = r3
            r3 = r11
            r5 = r8
            java.lang.Object r11 = r1.blurView(r2, r3, r4, r5, r6)
            if (r11 != r0) goto L_0x0145
            return r0
        L_0x0145:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L_0x0148:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L_0x014b:
            r10.setDefaultBackground()
            goto L_0x0154
        L_0x014f:
            r10.currentCoverArt = r5
            r10.setDefaultBackground()
        L_0x0154:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.audio.AudioPlayerAnimator.updateBackground(kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void setDefaultBackground() {
        AudioPlayerBinding audioPlayerBinding = this.binding;
        if (audioPlayerBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            audioPlayerBinding = null;
        }
        audioPlayerBinding.backgroundView.setVisibility(4);
    }

    public void manageSearchVisibilities(boolean z) {
        this.inSearch = z;
        AudioPlayerBinding audioPlayerBinding = this.binding;
        AudioPlayerBinding audioPlayerBinding2 = null;
        if (audioPlayerBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            audioPlayerBinding = null;
        }
        float f = 0.0f;
        audioPlayerBinding.playlistSearch.setAlpha(z ? 0.0f : 1.0f);
        AudioPlayerBinding audioPlayerBinding3 = this.binding;
        if (audioPlayerBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            audioPlayerBinding3 = null;
        }
        audioPlayerBinding3.playlistSwitch.setAlpha(z ? 0.0f : 1.0f);
        AudioPlayerBinding audioPlayerBinding4 = this.binding;
        if (audioPlayerBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            audioPlayerBinding4 = null;
        }
        audioPlayerBinding4.advFunction.setAlpha(z ? 0.0f : 1.0f);
        AudioPlayerBinding audioPlayerBinding5 = this.binding;
        if (audioPlayerBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            audioPlayerBinding5 = null;
        }
        HeaderMediaSwitcher headerMediaSwitcher = audioPlayerBinding5.audioMediaSwitcher;
        if (!z) {
            f = 1.0f;
        }
        headerMediaSwitcher.setAlpha(f);
        AudioPlayerBinding audioPlayerBinding6 = this.binding;
        if (audioPlayerBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            audioPlayerBinding2 = audioPlayerBinding6;
        }
        audioPlayerBinding2.playlistSearchText.setVisibility(z ? 0 : 8);
    }

    public void onSlide(float f) {
        if (!this.inSearch) {
            AudioPlayerBinding audioPlayerBinding = this.binding;
            AudioPlayerBinding audioPlayerBinding2 = null;
            if (audioPlayerBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                audioPlayerBinding = null;
            }
            float f2 = (float) 1;
            float f3 = f2 - f;
            audioPlayerBinding.progressBar.setAlpha(f3);
            AudioPlayerBinding audioPlayerBinding3 = this.binding;
            if (audioPlayerBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                audioPlayerBinding3 = null;
            }
            audioPlayerBinding3.progressBar.getLayoutParams().height = (int) (((float) KotlinExtensionsKt.getDp(4)) * f3);
            AudioPlayerBinding audioPlayerBinding4 = this.binding;
            if (audioPlayerBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                audioPlayerBinding4 = null;
            }
            audioPlayerBinding4.progressBar.requestLayout();
            AudioPlayerBinding audioPlayerBinding5 = this.binding;
            if (audioPlayerBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                audioPlayerBinding5 = null;
            }
            audioPlayerBinding5.headerBackground.setAlpha(this.showCover ? 0.6f * f3 : (0.6f * f3) + 0.4f);
            AudioPlayerBinding audioPlayerBinding6 = this.binding;
            if (audioPlayerBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                audioPlayerBinding6 = null;
            }
            audioPlayerBinding6.headerDivider.setAlpha(this.showCover ? 0.0f : f);
            if (f != 1.0f) {
                AudioPlayer audioPlayer2 = this.audioPlayer;
                if (audioPlayer2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("audioPlayer");
                    audioPlayer2 = null;
                }
                audioPlayer2.clearSearch();
            }
            AudioPlayerBinding audioPlayerBinding7 = this.binding;
            if (audioPlayerBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                audioPlayerBinding7 = null;
            }
            audioPlayerBinding7.playlistSearch.setAlpha(f);
            AudioPlayerBinding audioPlayerBinding8 = this.binding;
            if (audioPlayerBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                audioPlayerBinding8 = null;
            }
            audioPlayerBinding8.playlistSwitch.setAlpha(f);
            AudioPlayerBinding audioPlayerBinding9 = this.binding;
            if (audioPlayerBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                audioPlayerBinding9 = null;
            }
            audioPlayerBinding9.advFunction.setAlpha(f);
            View[] viewArr = new View[7];
            AudioPlayerBinding audioPlayerBinding10 = this.binding;
            if (audioPlayerBinding10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                audioPlayerBinding10 = null;
            }
            ImageView imageView = audioPlayerBinding10.headerPlayPause;
            Intrinsics.checkNotNullExpressionValue(imageView, "headerPlayPause");
            viewArr[0] = imageView;
            AudioPlayerBinding audioPlayerBinding11 = this.binding;
            if (audioPlayerBinding11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                audioPlayerBinding11 = null;
            }
            TextView textView = audioPlayerBinding11.headerTime;
            Intrinsics.checkNotNullExpressionValue(textView, "headerTime");
            viewArr[1] = textView;
            AudioPlayerBinding audioPlayerBinding12 = this.binding;
            if (audioPlayerBinding12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                audioPlayerBinding12 = null;
            }
            ImageView imageView2 = audioPlayerBinding12.headerShuffle;
            Intrinsics.checkNotNullExpressionValue(imageView2, "headerShuffle");
            viewArr[2] = imageView2;
            AudioPlayerBinding audioPlayerBinding13 = this.binding;
            if (audioPlayerBinding13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                audioPlayerBinding13 = null;
            }
            ImageView imageView3 = audioPlayerBinding13.headerPrevious;
            Intrinsics.checkNotNullExpressionValue(imageView3, "headerPrevious");
            viewArr[3] = imageView3;
            AudioPlayerBinding audioPlayerBinding14 = this.binding;
            if (audioPlayerBinding14 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                audioPlayerBinding14 = null;
            }
            AppCompatImageView appCompatImageView = audioPlayerBinding14.headerLargePlayPause;
            Intrinsics.checkNotNullExpressionValue(appCompatImageView, "headerLargePlayPause");
            viewArr[4] = appCompatImageView;
            AudioPlayerBinding audioPlayerBinding15 = this.binding;
            if (audioPlayerBinding15 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                audioPlayerBinding15 = null;
            }
            ImageView imageView4 = audioPlayerBinding15.headerNext;
            Intrinsics.checkNotNullExpressionValue(imageView4, "headerNext");
            viewArr[5] = imageView4;
            AudioPlayerBinding audioPlayerBinding16 = this.binding;
            if (audioPlayerBinding16 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                audioPlayerBinding16 = null;
            }
            ImageView imageView5 = audioPlayerBinding16.headerRepeat;
            Intrinsics.checkNotNullExpressionValue(imageView5, "headerRepeat");
            viewArr[6] = imageView5;
            for (int i = 0; i < 7; i++) {
                viewArr[i].setAlpha(f3);
            }
            float min = Math.min(1.0f, Math.max(0.0f, (1.4f * f) - 0.2f));
            AudioPlayerBinding audioPlayerBinding17 = this.binding;
            if (audioPlayerBinding17 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                audioPlayerBinding17 = null;
            }
            float f4 = -(f2 - min);
            audioPlayerBinding17.playlistSearch.setTranslationY(((float) KotlinExtensionsKt.getDp(48)) * f4);
            AudioPlayerBinding audioPlayerBinding18 = this.binding;
            if (audioPlayerBinding18 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                audioPlayerBinding18 = null;
            }
            audioPlayerBinding18.playlistSwitch.setTranslationY(((float) KotlinExtensionsKt.getDp(48)) * f4);
            AudioPlayerBinding audioPlayerBinding19 = this.binding;
            if (audioPlayerBinding19 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                audioPlayerBinding19 = null;
            }
            audioPlayerBinding19.advFunction.setTranslationY(((float) KotlinExtensionsKt.getDp(48)) * f4);
            for (int i2 = 0; i2 < 7; i2++) {
                viewArr[i2].setTranslationY(((float) KotlinExtensionsKt.getDp(48)) * min);
            }
            AudioPlayerBinding audioPlayerBinding20 = this.binding;
            if (audioPlayerBinding20 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                audioPlayerBinding20 = null;
            }
            audioPlayerBinding20.abRepeatReset.setTranslationY(((float) KotlinExtensionsKt.getDp(48)) * f4);
            AudioPlayerBinding audioPlayerBinding21 = this.binding;
            if (audioPlayerBinding21 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                audioPlayerBinding21 = null;
            }
            audioPlayerBinding21.abRepeatStop.setTranslationY(((float) KotlinExtensionsKt.getDp(48)) * f4);
            if (this.showCover) {
                AudioPlayerBinding audioPlayerBinding22 = this.binding;
                if (audioPlayerBinding22 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    audioPlayerBinding22 = null;
                }
                audioPlayerBinding22.audioMediaSwitcher.setTranslationY(min * ((float) KotlinExtensionsKt.getDp(48)));
                AudioPlayerBinding audioPlayerBinding23 = this.binding;
                if (audioPlayerBinding23 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    audioPlayerBinding23 = null;
                }
                audioPlayerBinding23.audioMediaSwitcher.setAlpha(f3);
                AudioPlayerBinding audioPlayerBinding24 = this.binding;
                if (audioPlayerBinding24 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    audioPlayerBinding24 = null;
                }
                audioPlayerBinding24.playbackChips.setTranslationY(f4 * ((float) KotlinExtensionsKt.getDp(48)));
                AudioPlayerBinding audioPlayerBinding25 = this.binding;
                if (audioPlayerBinding25 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    audioPlayerBinding2 = audioPlayerBinding25;
                }
                audioPlayerBinding2.playbackChips.setAlpha(f);
                return;
            }
            AudioPlayerBinding audioPlayerBinding26 = this.binding;
            if (audioPlayerBinding26 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                audioPlayerBinding26 = null;
            }
            audioPlayerBinding26.audioMediaSwitcher.setTranslationY(0.0f);
            AudioPlayerBinding audioPlayerBinding27 = this.binding;
            if (audioPlayerBinding27 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                audioPlayerBinding2 = audioPlayerBinding27;
            }
            audioPlayerBinding2.audioMediaSwitcher.setAlpha(1.0f);
        }
    }
}
