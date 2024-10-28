package org.videolan.vlc.gui.helpers;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.widget.ViewStubCompat;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.leanback.widget.BrowseFrameLayout;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.window.layout.DisplayFeature;
import androidx.window.layout.FoldingFeature;
import androidx.window.layout.WindowLayoutInfo;
import io.netty.handler.codec.rtsp.RtspHeaders;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.resources.AndroidDevices;
import org.videolan.resources.Constants;
import org.videolan.resources.VLCOptions;
import org.videolan.tools.AppScope;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.PlayerOptionItemBinding;
import org.videolan.vlc.gui.AudioPlayerContainerActivity;
import org.videolan.vlc.gui.BaseActivity;
import org.videolan.vlc.gui.DiffUtilAdapter;
import org.videolan.vlc.gui.audio.EqualizerFragment;
import org.videolan.vlc.gui.dialogs.AudioControlsSettingsDialog;
import org.videolan.vlc.gui.dialogs.DuplicationWarningDialog;
import org.videolan.vlc.gui.dialogs.JumpToTimeDialog;
import org.videolan.vlc.gui.dialogs.PlaybackSpeedDialog;
import org.videolan.vlc.gui.dialogs.SelectChapterDialog;
import org.videolan.vlc.gui.dialogs.SleepTimerDialog;
import org.videolan.vlc.gui.dialogs.VLCBottomSheetDialogFragment;
import org.videolan.vlc.gui.dialogs.VideoControlsSettingsDialog;
import org.videolan.vlc.gui.helpers.hf.PinCodeDelegate;
import org.videolan.vlc.gui.video.VideoDelayDelegate;
import org.videolan.vlc.gui.video.VideoPlayerActivity;
import org.videolan.vlc.gui.video.VideoPlayerOverlayDelegate;
import org.videolan.vlc.media.PlayerController;
import org.videolan.vlc.util.AccessibilityHelperKt;
import org.videolan.vlc.util.KextensionsKt;

@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001:\u0001BB\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010.\u001a\u00020\u000fJ\u0010\u0010/\u001a\u00020\u000f2\u0006\u00100\u001a\u00020\nH\u0002J\u0010\u00101\u001a\u00020\u000f2\u0006\u00100\u001a\u00020\nH\u0002J\u0006\u00102\u001a\u00020\u0007J\u000e\u00103\u001a\u00020\u000f2\u0006\u00104\u001a\u000205J\u0014\u00106\u001a\u00020\u000f2\f\u00107\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eJ\b\u00108\u001a\u00020\u000fH\u0002J\b\u00109\u001a\u00020\u000fH\u0002J\u0006\u0010:\u001a\u00020\u000fJ\u0006\u0010;\u001a\u00020\u000fJ\u0010\u0010<\u001a\u00020\u000f2\u0006\u0010=\u001a\u00020\u0011H\u0002J\u0010\u0010>\u001a\u00020\u000f2\u0006\u0010?\u001a\u00020@H\u0002J\b\u0010A\u001a\u00020\u000fH\u0002R\u000e\u0010\t\u001a\u00020\nX.¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX.¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u00020\u0011X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\nX.¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX.¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\nX.¢\u0006\u0002\n\u0000R\u0016\u0010\u001c\u001a\n \u001e*\u0004\u0018\u00010\u001d0\u001dX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X.¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u000e\u0010#\u001a\u00020$X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\nX.¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\nX.¢\u0006\u0002\n\u0000R#\u0010'\u001a\n \u001e*\u0004\u0018\u00010(0(8BX\u0002¢\u0006\f\n\u0004\b+\u0010,\u001a\u0004\b)\u0010*R\u000e\u0010-\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006C"}, d2 = {"Lorg/videolan/vlc/gui/helpers/PlayerOptionsDelegate;", "", "activity", "Landroidx/fragment/app/FragmentActivity;", "service", "Lorg/videolan/vlc/PlaybackService;", "showABReapeat", "", "(Landroidx/fragment/app/FragmentActivity;Lorg/videolan/vlc/PlaybackService;Z)V", "abrBinding", "Lorg/videolan/vlc/databinding/PlayerOptionItemBinding;", "getActivity", "()Landroidx/fragment/app/FragmentActivity;", "bookmarkClickedListener", "Lkotlin/Function0;", "", "flags", "", "getFlags", "()J", "setFlags", "(J)V", "isChromecast", "primary", "ptBinding", "recyclerview", "Landroidx/recyclerview/widget/RecyclerView;", "repeatBinding", "res", "Landroid/content/res/Resources;", "kotlin.jvm.PlatformType", "rootView", "Landroid/widget/FrameLayout;", "getService", "()Lorg/videolan/vlc/PlaybackService;", "settings", "Landroid/content/SharedPreferences;", "shuffleBinding", "sleepBinding", "toast", "Landroid/widget/Toast;", "getToast", "()Landroid/widget/Toast;", "toast$delegate", "Lkotlin/Lazy;", "video", "hide", "initRepeat", "binding", "initShuffle", "isShowing", "onClick", "option", "Lorg/videolan/vlc/gui/helpers/PlayerOption;", "setBookmarkClickedListener", "listener", "setRepeatMode", "setShuffle", "setup", "show", "showFragment", "id", "showValueControls", "action", "", "togglePassthrough", "OptionsAdapter", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlayerOptionsDelegate.kt */
public final class PlayerOptionsDelegate {
    /* access modifiers changed from: private */
    public PlayerOptionItemBinding abrBinding;
    private final FragmentActivity activity;
    private Function0<Unit> bookmarkClickedListener;
    private long flags;
    private final boolean isChromecast;
    private final boolean primary;
    /* access modifiers changed from: private */
    public PlayerOptionItemBinding ptBinding;
    /* access modifiers changed from: private */
    public RecyclerView recyclerview;
    /* access modifiers changed from: private */
    public PlayerOptionItemBinding repeatBinding;
    private final Resources res;
    private FrameLayout rootView;
    private final PlaybackService service;
    private final SharedPreferences settings;
    private final boolean showABReapeat;
    /* access modifiers changed from: private */
    public PlayerOptionItemBinding shuffleBinding;
    /* access modifiers changed from: private */
    public PlayerOptionItemBinding sleepBinding;
    private final Lazy toast$delegate;
    private final boolean video;

    public PlayerOptionsDelegate(FragmentActivity fragmentActivity, PlaybackService playbackService, boolean z) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "activity");
        Intrinsics.checkNotNullParameter(playbackService, NotificationCompat.CATEGORY_SERVICE);
        this.activity = fragmentActivity;
        this.service = playbackService;
        this.showABReapeat = z;
        this.toast$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new PlayerOptionsDelegate$toast$2(this));
        boolean z2 = true;
        this.primary = (fragmentActivity instanceof VideoPlayerActivity) && ((VideoPlayerActivity) fragmentActivity).getDisplayManager().isPrimary();
        this.isChromecast = (!(fragmentActivity instanceof VideoPlayerActivity) || !((VideoPlayerActivity) fragmentActivity).getDisplayManager().isOnRenderer()) ? false : z2;
        this.video = fragmentActivity instanceof VideoPlayerActivity;
        this.res = fragmentActivity.getResources();
        this.settings = (SharedPreferences) Settings.INSTANCE.getInstance(fragmentActivity);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ PlayerOptionsDelegate(FragmentActivity fragmentActivity, PlaybackService playbackService, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(fragmentActivity, playbackService, (i & 4) != 0 ? true : z);
    }

    public final FragmentActivity getActivity() {
        return this.activity;
    }

    public final PlaybackService getService() {
        return this.service;
    }

    public final long getFlags() {
        return this.flags;
    }

    public final void setFlags(long j) {
        this.flags = j;
    }

    private final Toast getToast() {
        return (Toast) this.toast$delegate.getValue();
    }

    public final void setup() {
        if (this.recyclerview != null && PlayerController.Companion.getPlaybackState() != 1) {
            List arrayList = new ArrayList();
            if (this.video) {
                int i = R.drawable.ic_lock_player;
                String string = this.res.getString(R.string.lock);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                arrayList.add(new PlayerOption(14, i, string));
            }
            int i2 = R.drawable.ic_sleep;
            String string2 = this.res.getString(R.string.sleep_title);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            arrayList.add(new PlayerOption(1, i2, string2));
            if (!this.isChromecast) {
                int i3 = R.drawable.ic_speed;
                String string3 = this.res.getString(R.string.playback_speed);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                arrayList.add(new PlayerOption(6, i3, string3));
            }
            int i4 = R.drawable.ic_jumpto;
            String string4 = this.res.getString(R.string.jump_to_time);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
            arrayList.add(new PlayerOption(2, i4, string4));
            int i5 = R.drawable.ic_equalizer;
            String string5 = this.res.getString(R.string.equalizer);
            Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
            arrayList.add(new PlayerOption(7, i5, string5));
            if (this.video) {
                if (this.primary && !Settings.INSTANCE.getShowTvUi() && this.service.getAudioTracksCount() > 0) {
                    int i6 = R.drawable.ic_playasaudio_on;
                    String string6 = this.res.getString(R.string.play_as_audio);
                    Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
                    arrayList.add(new PlayerOption(0, i6, string6));
                }
                if (this.primary && AndroidDevices.INSTANCE.getPipAllowed() && !AndroidDevices.INSTANCE.isDex(this.activity)) {
                    int i7 = R.drawable.ic_popup_dim;
                    String string7 = this.res.getString(R.string.ctx_pip_title);
                    Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
                    arrayList.add(new PlayerOption(9, i7, string7));
                }
                if (this.primary) {
                    int i8 = R.drawable.ic_repeat;
                    String string8 = this.res.getString(R.string.repeat_title);
                    Intrinsics.checkNotNullExpressionValue(string8, "getString(...)");
                    arrayList.add(new PlayerOption(10, i8, string8));
                }
                if (this.service.canShuffle()) {
                    int i9 = R.drawable.ic_player_shuffle;
                    String string9 = this.res.getString(R.string.shuffle_title);
                    Intrinsics.checkNotNullExpressionValue(string9, "getString(...)");
                    arrayList.add(new PlayerOption(11, i9, string9));
                }
                int i10 = R.drawable.ic_video_stats;
                String string10 = this.res.getString(R.string.video_information);
                Intrinsics.checkNotNullExpressionValue(string10, "getString(...)");
                arrayList.add(new PlayerOption(15, i10, string10));
            } else if (this.service.getVideoTracksCount() > 0) {
                int i11 = R.drawable.ic_playasaudio_off;
                String string11 = this.res.getString(R.string.play_as_video);
                Intrinsics.checkNotNullExpressionValue(string11, "getString(...)");
                arrayList.add(new PlayerOption(3, i11, string11));
            }
            MediaPlayer.Chapter[] chapters = this.service.getChapters(-1);
            if (chapters != null && chapters.length > 1) {
                int i12 = R.drawable.ic_chapter;
                String string12 = this.res.getString(R.string.go_to_chapter);
                Intrinsics.checkNotNullExpressionValue(string12, "getString(...)");
                arrayList.add(new PlayerOption(5, i12, string12));
            }
            if (this.bookmarkClickedListener != null) {
                int i13 = R.drawable.ic_bookmark;
                String string13 = this.res.getString(R.string.bookmarks);
                Intrinsics.checkNotNullExpressionValue(string13, "getString(...)");
                arrayList.add(new PlayerOption(4, i13, string13));
            }
            if (this.showABReapeat) {
                int i14 = R.drawable.ic_abrepeat;
                String string14 = this.res.getString(R.string.ab_repeat);
                Intrinsics.checkNotNullExpressionValue(string14, "getString(...)");
                arrayList.add(new PlayerOption(13, i14, string14));
            }
            int i15 = R.drawable.ic_addtoplaylist;
            String string15 = this.res.getString(R.string.playlist_save);
            Intrinsics.checkNotNullExpressionValue(string15, "getString(...)");
            arrayList.add(new PlayerOption(8, i15, string15));
            if (this.service.getPlaylistManager().getPlayer().canDoPassthrough() && !Intrinsics.areEqual((Object) this.settings.getString("aout", Constants.GROUP_VIDEOS_FOLDER), (Object) "2")) {
                int i16 = R.drawable.ic_passthrough;
                String string16 = this.res.getString(R.string.audio_digital_title);
                Intrinsics.checkNotNullExpressionValue(string16, "getString(...)");
                arrayList.add(new PlayerOption(12, i16, string16));
            }
            if (this.video) {
                if (Intrinsics.areEqual((Object) PinCodeDelegate.Companion.getPinUnlocked().getValue(), (Object) true)) {
                    int i17 = R.drawable.ic_pin_lock;
                    String string17 = this.res.getString(R.string.lock_with_pin);
                    Intrinsics.checkNotNullExpressionValue(string17, "getString(...)");
                    arrayList.add(new PlayerOption(21, i17, string17));
                }
                if (Settings.INSTANCE.getSafeMode() && Intrinsics.areEqual((Object) PinCodeDelegate.Companion.getPinUnlocked().getValue(), (Object) false)) {
                    int i18 = R.drawable.ic_pin_unlock;
                    String string18 = this.res.getString(R.string.unlock_with_pin);
                    Intrinsics.checkNotNullExpressionValue(string18, "getString(...)");
                    arrayList.add(new PlayerOption(22, i18, string18));
                }
                int i19 = R.drawable.ic_video_controls;
                String string19 = this.res.getString(R.string.control_setting);
                Intrinsics.checkNotNullExpressionValue(string19, "getString(...)");
                arrayList.add(new PlayerOption(19, i19, string19));
            } else {
                int i20 = R.drawable.ic_share;
                String string20 = this.res.getString(R.string.share_track_info);
                Intrinsics.checkNotNullExpressionValue(string20, "getString(...)");
                arrayList.add(new PlayerOption(23, i20, string20));
            }
            if (!Settings.INSTANCE.getShowTvUi()) {
                if (this.video) {
                    int i21 = R.drawable.ic_videotips;
                    String string21 = this.res.getString(R.string.tips_title);
                    Intrinsics.checkNotNullExpressionValue(string21, "getString(...)");
                    arrayList.add(new PlayerOption(16, i21, string21));
                } else {
                    int i22 = R.drawable.ic_audio_controls;
                    String string22 = this.res.getString(R.string.control_setting);
                    Intrinsics.checkNotNullExpressionValue(string22, "getString(...)");
                    arrayList.add(new PlayerOption(20, i22, string22));
                    int i23 = R.drawable.ic_audiotips;
                    String string23 = this.res.getString(R.string.audio_player_tips);
                    Intrinsics.checkNotNullExpressionValue(string23, "getString(...)");
                    arrayList.add(new PlayerOption(17, i23, string23));
                    int i24 = R.drawable.ic_playlisttips;
                    String string24 = this.res.getString(R.string.playlist_tips);
                    Intrinsics.checkNotNullExpressionValue(string24, "getString(...)");
                    arrayList.add(new PlayerOption(18, i24, string24));
                }
            }
            RecyclerView recyclerView = this.recyclerview;
            if (recyclerView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("recyclerview");
                recyclerView = null;
            }
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            Intrinsics.checkNotNull(adapter, "null cannot be cast to non-null type org.videolan.vlc.gui.helpers.PlayerOptionsDelegate.OptionsAdapter");
            ((OptionsAdapter) adapter).update(arrayList);
        }
    }

    public final void show() {
        List<DisplayFeature> displayFeatures;
        ViewStubCompat viewStubCompat = (ViewStubCompat) this.activity.findViewById(R.id.player_options_stub);
        if (viewStubCompat != null) {
            View inflate = viewStubCompat.inflate();
            Intrinsics.checkNotNull(inflate, "null cannot be cast to non-null type android.widget.FrameLayout");
            FrameLayout frameLayout = (FrameLayout) inflate;
            this.rootView = frameLayout;
            if (frameLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rootView");
                frameLayout = null;
            }
            View findViewById = frameLayout.findViewById(R.id.options_list);
            Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
            this.recyclerview = (RecyclerView) findViewById;
            FrameLayout frameLayout2 = this.rootView;
            if (frameLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rootView");
                frameLayout2 = null;
            }
            ((BrowseFrameLayout) frameLayout2.findViewById(R.id.options_background)).setOnFocusSearchListener(new PlayerOptionsDelegate$$ExternalSyntheticLambda1(this));
            RecyclerView recyclerView = this.recyclerview;
            if (recyclerView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("recyclerview");
                recyclerView = null;
            }
            if (recyclerView.getLayoutManager() == null) {
                RecyclerView recyclerView2 = this.recyclerview;
                if (recyclerView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("recyclerview");
                    recyclerView2 = null;
                }
                recyclerView2.setLayoutManager(new LinearLayoutManager(this.activity, 1, false));
            }
            RecyclerView recyclerView3 = this.recyclerview;
            if (recyclerView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("recyclerview");
                recyclerView3 = null;
            }
            recyclerView3.setAdapter(new OptionsAdapter());
            RecyclerView recyclerView4 = this.recyclerview;
            if (recyclerView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("recyclerview");
                recyclerView4 = null;
            }
            recyclerView4.setItemAnimator((RecyclerView.ItemAnimator) null);
            FrameLayout frameLayout3 = this.rootView;
            if (frameLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rootView");
                frameLayout3 = null;
            }
            frameLayout3.setOnClickListener(new PlayerOptionsDelegate$$ExternalSyntheticLambda2(this));
        }
        FragmentActivity fragmentActivity = this.activity;
        WindowLayoutInfo windowLayoutInfo = fragmentActivity instanceof VideoPlayerActivity ? ((VideoPlayerActivity) fragmentActivity).getWindowLayoutInfo() : fragmentActivity instanceof BaseActivity ? ((BaseActivity) fragmentActivity).getWindowLayoutInfo() : null;
        DisplayFeature displayFeature = (windowLayoutInfo == null || (displayFeatures = windowLayoutInfo.getDisplayFeatures()) == null) ? null : (DisplayFeature) CollectionsKt.firstOrNull(displayFeatures);
        FoldingFeature foldingFeature = displayFeature instanceof FoldingFeature ? (FoldingFeature) displayFeature : null;
        if (foldingFeature == null || !foldingFeature.isSeparating() || !Intrinsics.areEqual((Object) foldingFeature.getOcclusionType(), (Object) FoldingFeature.OcclusionType.FULL) || !Intrinsics.areEqual((Object) foldingFeature.getOrientation(), (Object) FoldingFeature.Orientation.HORIZONTAL)) {
            FrameLayout frameLayout4 = this.rootView;
            if (frameLayout4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rootView");
                frameLayout4 = null;
            }
            ViewGroup.LayoutParams layoutParams = frameLayout4.getLayoutParams();
            Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            marginLayoutParams.height = -1;
            if (marginLayoutParams instanceof FrameLayout.LayoutParams) {
                ((FrameLayout.LayoutParams) marginLayoutParams).gravity = 80;
            }
            FrameLayout frameLayout5 = this.rootView;
            if (frameLayout5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rootView");
                frameLayout5 = null;
            }
            frameLayout5.setLayoutParams(marginLayoutParams);
        } else {
            int screenHeight = KextensionsKt.getScreenHeight(this.activity) - foldingFeature.getBounds().bottom;
            FrameLayout frameLayout6 = this.rootView;
            if (frameLayout6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rootView");
                frameLayout6 = null;
            }
            ViewGroup.LayoutParams layoutParams2 = frameLayout6.getLayoutParams();
            Intrinsics.checkNotNull(layoutParams2, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) layoutParams2;
            marginLayoutParams2.height = screenHeight;
            if (marginLayoutParams2 instanceof FrameLayout.LayoutParams) {
                ((FrameLayout.LayoutParams) marginLayoutParams2).gravity = 80;
            }
            FrameLayout frameLayout7 = this.rootView;
            if (frameLayout7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rootView");
                frameLayout7 = null;
            }
            frameLayout7.setLayoutParams(marginLayoutParams2);
        }
        setup();
        FrameLayout frameLayout8 = this.rootView;
        if (frameLayout8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
            frameLayout8 = null;
        }
        frameLayout8.setVisibility(0);
        if (Settings.INSTANCE.getShowTvUi()) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(AppScope.INSTANCE, (CoroutineContext) null, (CoroutineStart) null, new PlayerOptionsDelegate$show$2(this, (Continuation<? super PlayerOptionsDelegate$show$2>) null), 3, (Object) null);
        } else if (AccessibilityHelperKt.isTalkbackIsEnabled(this.activity)) {
            Job unused2 = BuildersKt__Builders_commonKt.launch$default(AppScope.INSTANCE, (CoroutineContext) null, (CoroutineStart) null, new PlayerOptionsDelegate$show$3(this, (Continuation<? super PlayerOptionsDelegate$show$3>) null), 3, (Object) null);
        }
    }

    /* access modifiers changed from: private */
    public static final View show$lambda$2$lambda$0(PlayerOptionsDelegate playerOptionsDelegate, View view, int i) {
        Intrinsics.checkNotNullParameter(playerOptionsDelegate, "this$0");
        RecyclerView recyclerView = playerOptionsDelegate.recyclerview;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerview");
            recyclerView = null;
        }
        if (recyclerView.hasFocus()) {
            return view;
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static final void show$lambda$2$lambda$1(PlayerOptionsDelegate playerOptionsDelegate, View view) {
        Intrinsics.checkNotNullParameter(playerOptionsDelegate, "this$0");
        playerOptionsDelegate.hide();
    }

    public final void hide() {
        FrameLayout frameLayout = this.rootView;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
            frameLayout = null;
        }
        frameLayout.setVisibility(8);
    }

    public final void setBookmarkClickedListener(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "listener");
        this.bookmarkClickedListener = function0;
    }

    public final void onClick(PlayerOption playerOption) {
        VideoPlayerOverlayDelegate overlayDelegate;
        boolean z;
        Intrinsics.checkNotNullParameter(playerOption, DuplicationWarningDialog.OPTION_KEY);
        long id = playerOption.getId();
        if (id == 1) {
            showFragment(1);
        } else if (id == 0) {
            FragmentActivity fragmentActivity = this.activity;
            Intrinsics.checkNotNull(fragmentActivity, "null cannot be cast to non-null type org.videolan.vlc.gui.video.VideoPlayerActivity");
            ((VideoPlayerActivity) fragmentActivity).switchToAudioMode(true);
        } else if (id == 3) {
            FragmentActivity fragmentActivity2 = this.activity;
            Intrinsics.checkNotNull(fragmentActivity2, "null cannot be cast to non-null type org.videolan.vlc.gui.AudioPlayerContainerActivity");
            ((AudioPlayerContainerActivity) fragmentActivity2).getAudioPlayer().onResumeToVideoClick();
        } else if (id == 9) {
            FragmentActivity fragmentActivity3 = this.activity;
            Intrinsics.checkNotNull(fragmentActivity3, "null cannot be cast to non-null type org.videolan.vlc.gui.video.VideoPlayerActivity");
            ((VideoPlayerActivity) fragmentActivity3).switchToPopup();
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.HOME");
            intent.setFlags(268435456);
            this.activity.startActivity(intent);
            hide();
        } else if (id == 10) {
            setRepeatMode();
        } else if (id == 11) {
            this.service.shuffle();
            setShuffle();
        } else if (id == 12) {
            togglePassthrough();
        } else if (id == 13) {
            hide();
            this.service.getPlaylistManager().toggleABRepeat();
        } else if (id == 14) {
            hide();
            FragmentActivity fragmentActivity4 = this.activity;
            Intrinsics.checkNotNull(fragmentActivity4, "null cannot be cast to non-null type org.videolan.vlc.gui.video.VideoPlayerActivity");
            ((VideoPlayerActivity) fragmentActivity4).toggleLock();
        } else if (id == 15) {
            hide();
            this.service.getPlaylistManager().toggleStats();
        } else if (id == 16) {
            hide();
            FragmentActivity fragmentActivity5 = this.activity;
            Intrinsics.checkNotNull(fragmentActivity5, "null cannot be cast to non-null type org.videolan.vlc.gui.video.VideoPlayerActivity");
            ((VideoPlayerActivity) fragmentActivity5).getTipsDelegate().init();
        } else if (id == 17) {
            hide();
            FragmentActivity fragmentActivity6 = this.activity;
            Intrinsics.checkNotNull(fragmentActivity6, "null cannot be cast to non-null type org.videolan.vlc.gui.AudioPlayerContainerActivity");
            AudioPlayerContainerActivity audioPlayerContainerActivity = (AudioPlayerContainerActivity) fragmentActivity6;
            ViewStubCompat viewStubCompat = (ViewStubCompat) audioPlayerContainerActivity.findViewById(R.id.audio_player_tips);
            if (viewStubCompat != null) {
                audioPlayerContainerActivity.getTipsDelegate().init(viewStubCompat);
            }
        } else if (id == 18) {
            hide();
            FragmentActivity fragmentActivity7 = this.activity;
            Intrinsics.checkNotNull(fragmentActivity7, "null cannot be cast to non-null type org.videolan.vlc.gui.AudioPlayerContainerActivity");
            AudioPlayerContainerActivity audioPlayerContainerActivity2 = (AudioPlayerContainerActivity) fragmentActivity7;
            ViewStubCompat viewStubCompat2 = (ViewStubCompat) audioPlayerContainerActivity2.findViewById(R.id.audio_playlist_tips);
            if (viewStubCompat2 != null) {
                audioPlayerContainerActivity2.getPlaylistTipsDelegate().init(viewStubCompat2);
            }
        } else {
            Function0<Unit> function0 = null;
            if (id == 4) {
                hide();
                Function0<Unit> function02 = this.bookmarkClickedListener;
                if (function02 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("bookmarkClickedListener");
                } else {
                    function0 = function02;
                }
                function0.invoke();
            } else if (id == 19) {
                hide();
                new VideoControlsSettingsDialog().show(this.activity.getSupportFragmentManager(), "fragment_video_controls_settings");
            } else if (id == 23) {
                hide();
                MediaWrapper currentMedia = this.service.getPlaylistManager().getCurrentMedia();
                if (currentMedia != null) {
                    StringBuilder sb = new StringBuilder();
                    String title = currentMedia.getTitle();
                    Intrinsics.checkNotNullExpressionValue(title, "getTitle(...)");
                    if (!StringsKt.isBlank(title)) {
                        sb.append(currentMedia.getTitle());
                        z = true;
                    } else {
                        z = false;
                    }
                    String album = currentMedia.getAlbum();
                    Intrinsics.checkNotNullExpressionValue(album, "getAlbum(...)");
                    if (!StringsKt.isBlank(album)) {
                        if (z) {
                            sb.append(" · ");
                        }
                        sb.append(currentMedia.getAlbum());
                        z = true;
                    }
                    String artist = currentMedia.getArtist();
                    Intrinsics.checkNotNullExpressionValue(artist, "getArtist(...)");
                    if (!StringsKt.isBlank(artist)) {
                        if (z) {
                            sb.append(" · ");
                        }
                        sb.append(currentMedia.getArtist());
                    }
                    String sb2 = sb.toString();
                    Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
                    FragmentActivity fragmentActivity8 = this.activity;
                    String string = fragmentActivity8.getString(R.string.share_track, new Object[]{sb2});
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    KextensionsKt.share(fragmentActivity8, "", string);
                }
            } else if (id == 20) {
                hide();
                new AudioControlsSettingsDialog().show(this.activity.getSupportFragmentManager(), "fragment_audio_controls_settings");
            } else if (id == 21) {
                hide();
                PinCodeDelegate.Companion.getPinUnlocked().postValue(false);
                FragmentActivity fragmentActivity9 = this.activity;
                VideoPlayerActivity videoPlayerActivity = fragmentActivity9 instanceof VideoPlayerActivity ? (VideoPlayerActivity) fragmentActivity9 : null;
                if (!(videoPlayerActivity == null || (overlayDelegate = videoPlayerActivity.getOverlayDelegate()) == null)) {
                    VideoPlayerOverlayDelegate.showOverlay$default(overlayDelegate, false, 1, (Object) null);
                }
                UiTools.snacker$default(UiTools.INSTANCE, this.activity, R.string.safe_mode_enabled, false, 4, (Object) null);
            } else if (id == 22) {
                hide();
                Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this.activity), (CoroutineContext) null, (CoroutineStart) null, new PlayerOptionsDelegate$onClick$4(this, (Continuation<? super PlayerOptionsDelegate$onClick$4>) null), 3, (Object) null);
            } else {
                showFragment(playerOption.getId());
            }
        }
    }

    private final void showFragment(long j) {
        String str;
        DialogFragment dialogFragment;
        if (j == 6) {
            dialogFragment = PlaybackSpeedDialog.Companion.newInstance();
            str = SettingsKt.KEY_PLAYBACK_SPEED_PERSIST;
        } else {
            if (j == 2) {
                dialogFragment = JumpToTimeDialog.Companion.newInstance();
            } else if (j == 1) {
                dialogFragment = SleepTimerDialog.Companion.newInstance$default(SleepTimerDialog.Companion, false, 1, (Object) null);
            } else if (j == 5) {
                dialogFragment = SelectChapterDialog.Companion.newInstance();
                str = "select_chapter";
            } else if (j == 7) {
                dialogFragment = EqualizerFragment.Companion.newInstance();
                str = "equalizer";
            } else if (j == 8) {
                UiTools.INSTANCE.addToPlaylist(this.activity, this.service.getMedia());
                hide();
                return;
            } else {
                return;
            }
            str = RtspHeaders.Values.TIME;
        }
        if ((dialogFragment instanceof VLCBottomSheetDialogFragment) && (this.activity instanceof VideoPlayerActivity)) {
            ((VLCBottomSheetDialogFragment) dialogFragment).setOnDismissListener(new PlayerOptionsDelegate$$ExternalSyntheticLambda0(this));
        }
        dialogFragment.show(this.activity.getSupportFragmentManager(), str);
        hide();
    }

    /* access modifiers changed from: private */
    public static final void showFragment$lambda$7(PlayerOptionsDelegate playerOptionsDelegate, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(playerOptionsDelegate, "this$0");
        ((VideoPlayerActivity) playerOptionsDelegate.activity).getOverlayDelegate().dimStatusBar(true);
    }

    private final void showValueControls(int i) {
        VideoDelayDelegate delayDelegate;
        FragmentActivity fragmentActivity = this.activity;
        VideoPlayerActivity videoPlayerActivity = fragmentActivity instanceof VideoPlayerActivity ? (VideoPlayerActivity) fragmentActivity : null;
        if (videoPlayerActivity != null && (delayDelegate = videoPlayerActivity.getDelayDelegate()) != null) {
            if (i == 2) {
                delayDelegate.showAudioDelaySetting();
            } else if (i == 3) {
                delayDelegate.showSubsDelaySetting();
            } else {
                return;
            }
            hide();
        }
    }

    private final void setRepeatMode() {
        int repeatType = this.service.getRepeatType();
        PlayerOptionItemBinding playerOptionItemBinding = null;
        if (repeatType == 0) {
            PlayerOptionItemBinding playerOptionItemBinding2 = this.repeatBinding;
            if (playerOptionItemBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("repeatBinding");
                playerOptionItemBinding2 = null;
            }
            playerOptionItemBinding2.optionIcon.setImageResource(R.drawable.ic_repeat_one);
            this.service.setRepeatType(1);
            PlayerOptionItemBinding playerOptionItemBinding3 = this.repeatBinding;
            if (playerOptionItemBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("repeatBinding");
                playerOptionItemBinding3 = null;
            }
            View root = playerOptionItemBinding3.getRoot();
            PlayerOptionItemBinding playerOptionItemBinding4 = this.repeatBinding;
            if (playerOptionItemBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("repeatBinding");
            } else {
                playerOptionItemBinding = playerOptionItemBinding4;
            }
            root.setContentDescription(playerOptionItemBinding.getRoot().getContext().getString(R.string.repeat_single));
        } else if (repeatType != 1) {
            if (repeatType == 2) {
                PlayerOptionItemBinding playerOptionItemBinding5 = this.repeatBinding;
                if (playerOptionItemBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("repeatBinding");
                    playerOptionItemBinding5 = null;
                }
                playerOptionItemBinding5.optionIcon.setImageResource(R.drawable.ic_repeat);
                this.service.setRepeatType(0);
                PlayerOptionItemBinding playerOptionItemBinding6 = this.repeatBinding;
                if (playerOptionItemBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("repeatBinding");
                    playerOptionItemBinding6 = null;
                }
                View root2 = playerOptionItemBinding6.getRoot();
                PlayerOptionItemBinding playerOptionItemBinding7 = this.repeatBinding;
                if (playerOptionItemBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("repeatBinding");
                } else {
                    playerOptionItemBinding = playerOptionItemBinding7;
                }
                root2.setContentDescription(playerOptionItemBinding.getRoot().getContext().getString(R.string.repeat_none));
            }
        } else if (this.service.hasPlaylist()) {
            PlayerOptionItemBinding playerOptionItemBinding8 = this.repeatBinding;
            if (playerOptionItemBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("repeatBinding");
                playerOptionItemBinding8 = null;
            }
            playerOptionItemBinding8.optionIcon.setImageResource(R.drawable.ic_repeat_all);
            this.service.setRepeatType(2);
            PlayerOptionItemBinding playerOptionItemBinding9 = this.repeatBinding;
            if (playerOptionItemBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("repeatBinding");
                playerOptionItemBinding9 = null;
            }
            View root3 = playerOptionItemBinding9.getRoot();
            PlayerOptionItemBinding playerOptionItemBinding10 = this.repeatBinding;
            if (playerOptionItemBinding10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("repeatBinding");
            } else {
                playerOptionItemBinding = playerOptionItemBinding10;
            }
            root3.setContentDescription(playerOptionItemBinding.getRoot().getContext().getString(R.string.repeat_all));
        } else {
            PlayerOptionItemBinding playerOptionItemBinding11 = this.repeatBinding;
            if (playerOptionItemBinding11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("repeatBinding");
                playerOptionItemBinding11 = null;
            }
            playerOptionItemBinding11.optionIcon.setImageResource(R.drawable.ic_repeat);
            this.service.setRepeatType(0);
            PlayerOptionItemBinding playerOptionItemBinding12 = this.repeatBinding;
            if (playerOptionItemBinding12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("repeatBinding");
                playerOptionItemBinding12 = null;
            }
            View root4 = playerOptionItemBinding12.getRoot();
            PlayerOptionItemBinding playerOptionItemBinding13 = this.repeatBinding;
            if (playerOptionItemBinding13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("repeatBinding");
            } else {
                playerOptionItemBinding = playerOptionItemBinding13;
            }
            root4.setContentDescription(playerOptionItemBinding.getRoot().getContext().getString(R.string.repeat_none));
        }
    }

    private final void setShuffle() {
        PlayerOptionItemBinding playerOptionItemBinding = this.shuffleBinding;
        PlayerOptionItemBinding playerOptionItemBinding2 = null;
        if (playerOptionItemBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("shuffleBinding");
            playerOptionItemBinding = null;
        }
        playerOptionItemBinding.optionIcon.setImageResource(this.service.isShuffling() ? R.drawable.ic_shuffle_on_48dp : R.drawable.ic_player_shuffle);
        PlayerOptionItemBinding playerOptionItemBinding3 = this.shuffleBinding;
        if (playerOptionItemBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("shuffleBinding");
            playerOptionItemBinding3 = null;
        }
        View root = playerOptionItemBinding3.getRoot();
        PlayerOptionItemBinding playerOptionItemBinding4 = this.shuffleBinding;
        if (playerOptionItemBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("shuffleBinding");
        } else {
            playerOptionItemBinding2 = playerOptionItemBinding4;
        }
        root.setContentDescription(playerOptionItemBinding2.getRoot().getContext().getString(this.service.isShuffling() ? R.string.shuffle_on : R.string.shuffle));
    }

    /* access modifiers changed from: private */
    public final void initShuffle(PlayerOptionItemBinding playerOptionItemBinding) {
        this.shuffleBinding = playerOptionItemBinding;
        Job unused = BuildersKt__Builders_commonKt.launch$default(AppScope.INSTANCE, Dispatchers.getMain(), (CoroutineStart) null, new PlayerOptionsDelegate$initShuffle$1(this, (Continuation<? super PlayerOptionsDelegate$initShuffle$1>) null), 2, (Object) null);
    }

    /* access modifiers changed from: private */
    public final void initRepeat(PlayerOptionItemBinding playerOptionItemBinding) {
        this.repeatBinding = playerOptionItemBinding;
        Job unused = BuildersKt__Builders_commonKt.launch$default(AppScope.INSTANCE, Dispatchers.getMain(), (CoroutineStart) null, new PlayerOptionsDelegate$initRepeat$1(this, (Continuation<? super PlayerOptionsDelegate$initRepeat$1>) null), 2, (Object) null);
    }

    private final void togglePassthrough() {
        int i;
        boolean z = !VLCOptions.INSTANCE.isAudioDigitalOutputEnabled(this.settings);
        if (this.service.setAudioDigitalOutputEnabled(z)) {
            PlayerOptionItemBinding playerOptionItemBinding = this.ptBinding;
            if (playerOptionItemBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ptBinding");
                playerOptionItemBinding = null;
            }
            ImageView imageView = playerOptionItemBinding.optionIcon;
            if (z) {
                i = R.drawable.ic_passthrough_on;
            } else {
                i = UiTools.INSTANCE.getResourceFromAttribute(this.activity, R.attr.ic_passthrough);
            }
            imageView.setImageResource(i);
            VLCOptions.INSTANCE.setAudioDigitalOutputEnabled(this.settings, z);
            getToast().setText(this.res.getString(z ? R.string.audio_digital_output_enabled : R.string.audio_digital_output_disabled));
        } else {
            getToast().setText(R.string.audio_digital_failed);
        }
        getToast().show();
    }

    public final boolean isShowing() {
        FrameLayout frameLayout = this.rootView;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
            frameLayout = null;
        }
        return frameLayout.getVisibility() == 0;
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0004\u0018\u00002\u0016\u0012\u0004\u0012\u00020\u0002\u0012\f\u0012\n0\u0003R\u00060\u0000R\u00020\u00040\u0001:\u0001\u0011B\u0005¢\u0006\u0002\u0010\u0005J \u0010\b\u001a\u00020\t2\u000e\u0010\n\u001a\n0\u0003R\u00060\u0000R\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fH\u0016J \u0010\r\u001a\n0\u0003R\u00060\u0000R\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\fH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lorg/videolan/vlc/gui/helpers/PlayerOptionsDelegate$OptionsAdapter;", "Lorg/videolan/vlc/gui/DiffUtilAdapter;", "Lorg/videolan/vlc/gui/helpers/PlayerOption;", "Lorg/videolan/vlc/gui/helpers/PlayerOptionsDelegate$OptionsAdapter$ViewHolder;", "Lorg/videolan/vlc/gui/helpers/PlayerOptionsDelegate;", "(Lorg/videolan/vlc/gui/helpers/PlayerOptionsDelegate;)V", "layountInflater", "Landroid/view/LayoutInflater;", "onBindViewHolder", "", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ViewHolder", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: PlayerOptionsDelegate.kt */
    private final class OptionsAdapter extends DiffUtilAdapter<PlayerOption, ViewHolder> {
        private LayoutInflater layountInflater;

        public OptionsAdapter() {
        }

        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            Intrinsics.checkNotNullParameter(viewGroup, "parent");
            if (this.layountInflater == null) {
                LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
                Intrinsics.checkNotNullExpressionValue(from, "from(...)");
                this.layountInflater = from;
            }
            LayoutInflater layoutInflater = this.layountInflater;
            if (layoutInflater == null) {
                Intrinsics.throwUninitializedPropertyAccessException("layountInflater");
                layoutInflater = null;
            }
            PlayerOptionItemBinding inflate = PlayerOptionItemBinding.inflate(layoutInflater, viewGroup, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
            return new ViewHolder(this, inflate);
        }

        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            Intrinsics.checkNotNullParameter(viewHolder, "holder");
            PlayerOption playerOption = (PlayerOption) getDataset().get(i);
            viewHolder.getBinding().setOption(playerOption);
            long id = playerOption.getId();
            if (id == 13) {
                PlayerOptionsDelegate.this.abrBinding = viewHolder.getBinding();
            } else if (id == 12) {
                PlayerOptionsDelegate.this.ptBinding = viewHolder.getBinding();
            } else if (id == 10) {
                PlayerOptionsDelegate.this.initRepeat(viewHolder.getBinding());
            } else if (id == 11) {
                PlayerOptionsDelegate.this.initShuffle(viewHolder.getBinding());
            } else if (id == 1) {
                PlayerOptionsDelegate.this.sleepBinding = viewHolder.getBinding();
            }
            viewHolder.getBinding().optionIcon.setImageResource(playerOption.getIcon());
        }

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lorg/videolan/vlc/gui/helpers/PlayerOptionsDelegate$OptionsAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lorg/videolan/vlc/databinding/PlayerOptionItemBinding;", "(Lorg/videolan/vlc/gui/helpers/PlayerOptionsDelegate$OptionsAdapter;Lorg/videolan/vlc/databinding/PlayerOptionItemBinding;)V", "getBinding", "()Lorg/videolan/vlc/databinding/PlayerOptionItemBinding;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        /* compiled from: PlayerOptionsDelegate.kt */
        public final class ViewHolder extends RecyclerView.ViewHolder {
            private final PlayerOptionItemBinding binding;
            final /* synthetic */ OptionsAdapter this$0;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public ViewHolder(OptionsAdapter optionsAdapter, PlayerOptionItemBinding playerOptionItemBinding) {
                super(playerOptionItemBinding.getRoot());
                Intrinsics.checkNotNullParameter(playerOptionItemBinding, "binding");
                this.this$0 = optionsAdapter;
                this.binding = playerOptionItemBinding;
                this.itemView.setOnClickListener(new PlayerOptionsDelegate$OptionsAdapter$ViewHolder$$ExternalSyntheticLambda0(PlayerOptionsDelegate.this, optionsAdapter, this));
            }

            public final PlayerOptionItemBinding getBinding() {
                return this.binding;
            }

            /* access modifiers changed from: private */
            public static final void _init_$lambda$0(PlayerOptionsDelegate playerOptionsDelegate, OptionsAdapter optionsAdapter, ViewHolder viewHolder, View view) {
                Intrinsics.checkNotNullParameter(playerOptionsDelegate, "this$0");
                Intrinsics.checkNotNullParameter(optionsAdapter, "this$1");
                Intrinsics.checkNotNullParameter(viewHolder, "this$2");
                playerOptionsDelegate.onClick((PlayerOption) optionsAdapter.getDataset().get(viewHolder.getLayoutPosition()));
            }
        }
    }
}
