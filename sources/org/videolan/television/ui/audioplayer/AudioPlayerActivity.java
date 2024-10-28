package org.videolan.television.ui.audioplayer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.view.InputDeviceCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import io.netty.handler.codec.rtsp.RtspHeaders;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.resources.AndroidDevices;
import org.videolan.television.R;
import org.videolan.television.databinding.TvAudioPlayerBinding;
import org.videolan.television.ui.browser.BaseTvActivity;
import org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.tools.Strings;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.gui.audio.EqualizerFragment;
import org.videolan.vlc.gui.dialogs.PlaybackSpeedDialog;
import org.videolan.vlc.gui.dialogs.SleepTimerDialog;
import org.videolan.vlc.gui.helpers.BookmarkListDelegate;
import org.videolan.vlc.gui.helpers.KeycodeListener;
import org.videolan.vlc.gui.helpers.MediaComparators;
import org.videolan.vlc.gui.helpers.PlayerKeyListenerDelegate;
import org.videolan.vlc.gui.helpers.PlayerOptionsDelegate;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.media.MediaUtils;
import org.videolan.vlc.viewmodels.BookmarkModel;
import org.videolan.vlc.viewmodels.PlaylistModel;

@Metadata(d1 = {"\u0000 \u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 R2\u00020\u00012\u00020\u0002:\u0001RB\u0005¢\u0006\u0002\u0010\u0003J\b\u0010(\u001a\u00020)H\u0016J\b\u0010*\u001a\u00020)H\u0016J\u0010\u0010+\u001a\u00020$2\u0006\u0010,\u001a\u00020-H\u0016J\b\u0010.\u001a\u00020)H\u0016J\b\u0010/\u001a\u00020$H\u0016J\b\u00100\u001a\u00020)H\u0016J\u000e\u00101\u001a\u00020)2\u0006\u00102\u001a\u000203J\u0012\u00104\u001a\u00020)2\b\u00105\u001a\u0004\u0018\u000106H\u0014J\b\u00107\u001a\u00020)H\u0014J\u0018\u00108\u001a\u00020$2\u0006\u00109\u001a\u00020:2\u0006\u0010,\u001a\u00020;H\u0016J\u0006\u0010<\u001a\u00020)J\u0006\u0010=\u001a\u00020)J\b\u0010>\u001a\u00020)H\u0016J\b\u0010?\u001a\u00020)H\u0014J\b\u0010@\u001a\u00020)H\u0016J\u0010\u0010A\u001a\u00020)2\u0006\u0010B\u001a\u00020:H\u0016J\u0010\u0010C\u001a\u00020)2\u0006\u0010D\u001a\u00020$H\u0002J\b\u0010E\u001a\u00020)H\u0016J\u0012\u0010E\u001a\u00020)2\b\u00102\u001a\u0004\u0018\u000103H\u0002J\b\u0010F\u001a\u00020)H\u0002J\b\u0010G\u001a\u00020)H\u0002J\b\u0010H\u001a\u00020)H\u0016J\b\u0010I\u001a\u00020)H\u0016J\b\u0010J\u001a\u00020)H\u0002J\b\u0010K\u001a\u00020)H\u0016J\u0010\u0010L\u001a\u00020)2\b\u0010M\u001a\u0004\u0018\u00010NJ\b\u0010O\u001a\u00020PH\u0002J\b\u0010Q\u001a\u00020)H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX.¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\u000bX.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X.¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X.¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0019X.¢\u0006\u0002\n\u0000R\u001b\u0010\u001b\u001a\u00020\u001c8BX\u0002¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b\u001d\u0010\u001eR\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020$X\u000e¢\u0006\u0002\n\u0000¨\u0006S"}, d2 = {"Lorg/videolan/television/ui/audioplayer/AudioPlayerActivity;", "Lorg/videolan/television/ui/browser/BaseTvActivity;", "Lorg/videolan/vlc/gui/helpers/KeycodeListener;", "()V", "adapter", "Lorg/videolan/television/ui/audioplayer/PlaylistAdapter;", "binding", "Lorg/videolan/television/databinding/TvAudioPlayerBinding;", "bookmarkListDelegate", "Lorg/videolan/vlc/gui/helpers/BookmarkListDelegate;", "bookmarkModel", "Lorg/videolan/vlc/viewmodels/BookmarkModel;", "getBookmarkModel", "()Lorg/videolan/vlc/viewmodels/BookmarkModel;", "setBookmarkModel", "(Lorg/videolan/vlc/viewmodels/BookmarkModel;)V", "currentCoverArt", "", "lastMove", "", "model", "Lorg/videolan/vlc/viewmodels/PlaylistModel;", "optionsDelegate", "Lorg/videolan/vlc/gui/helpers/PlayerOptionsDelegate;", "pauseToPlay", "Landroidx/vectordrawable/graphics/drawable/AnimatedVectorDrawableCompat;", "playToPause", "playerKeyListenerDelegate", "Lorg/videolan/vlc/gui/helpers/PlayerKeyListenerDelegate;", "getPlayerKeyListenerDelegate", "()Lorg/videolan/vlc/gui/helpers/PlayerKeyListenerDelegate;", "playerKeyListenerDelegate$delegate", "Lkotlin/Lazy;", "settings", "Landroid/content/SharedPreferences;", "shuffling", "", "timelineListener", "Landroid/widget/SeekBar$OnSeekBarChangeListener;", "wasPlaying", "bookmark", "", "decreaseRate", "dispatchGenericMotionEvent", "event", "Landroid/view/MotionEvent;", "increaseRate", "isReady", "next", "onClick", "v", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onKeyDown", "keyCode", "", "Landroid/view/KeyEvent;", "onUpdateFinished", "playSelection", "previous", "refresh", "resetRate", "seek", "delta", "setShuffleMode", "shuffle", "showAdvancedOptions", "showBookmarks", "showChips", "showEqualizer", "stop", "switchRepeatMode", "togglePlayPause", "update", "state", "Lorg/videolan/vlc/viewmodels/PlayerState;", "updateBackground", "Lkotlinx/coroutines/Job;", "updateRepeatMode", "Companion", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioPlayerActivity.kt */
public final class AudioPlayerActivity extends BaseTvActivity implements KeycodeListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int JOYSTICK_INPUT_DELAY = 300;
    public static final String MEDIA_LIST = "media_list";
    public static final String MEDIA_PLAYLIST = "media_playlist";
    public static final String MEDIA_POSITION = "media_position";
    public static final String TAG = "VLC/AudioPlayerActivity";
    /* access modifiers changed from: private */
    public PlaylistAdapter adapter;
    /* access modifiers changed from: private */
    public TvAudioPlayerBinding binding;
    /* access modifiers changed from: private */
    public BookmarkListDelegate bookmarkListDelegate;
    public BookmarkModel bookmarkModel;
    /* access modifiers changed from: private */
    public String currentCoverArt;
    private long lastMove;
    /* access modifiers changed from: private */
    public PlaylistModel model;
    /* access modifiers changed from: private */
    public PlayerOptionsDelegate optionsDelegate;
    private AnimatedVectorDrawableCompat pauseToPlay;
    private AnimatedVectorDrawableCompat playToPause;
    private final Lazy playerKeyListenerDelegate$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new AudioPlayerActivity$playerKeyListenerDelegate$2(this));
    private SharedPreferences settings;
    /* access modifiers changed from: private */
    public boolean shuffling;
    private SeekBar.OnSeekBarChangeListener timelineListener = new AudioPlayerActivity$timelineListener$1(this);
    private boolean wasPlaying;

    public boolean isReady() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void refresh() {
    }

    public final BookmarkModel getBookmarkModel() {
        BookmarkModel bookmarkModel2 = this.bookmarkModel;
        if (bookmarkModel2 != null) {
            return bookmarkModel2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bookmarkModel");
        return null;
    }

    public final void setBookmarkModel(BookmarkModel bookmarkModel2) {
        Intrinsics.checkNotNullParameter(bookmarkModel2, "<set-?>");
        this.bookmarkModel = bookmarkModel2;
    }

    private final PlayerKeyListenerDelegate getPlayerKeyListenerDelegate() {
        return (PlayerKeyListenerDelegate) this.playerKeyListenerDelegate$delegate.getValue();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        ArrayList arrayList;
        super.onCreate(bundle);
        ViewDataBinding contentView = DataBindingUtil.setContentView(this, R.layout.tv_audio_player);
        Intrinsics.checkNotNullExpressionValue(contentView, "setContentView(...)");
        this.binding = (TvAudioPlayerBinding) contentView;
        this.settings = (SharedPreferences) Settings.INSTANCE.getInstance(this);
        this.model = (PlaylistModel) new ViewModelProvider(this).get(PlaylistModel.class);
        TvAudioPlayerBinding tvAudioPlayerBinding = this.binding;
        TvAudioPlayerBinding tvAudioPlayerBinding2 = null;
        if (tvAudioPlayerBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            tvAudioPlayerBinding = null;
        }
        Context context = this;
        tvAudioPlayerBinding.playlist.setLayoutManager(new LinearLayoutManager(context));
        PlaylistModel playlistModel = this.model;
        if (playlistModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("model");
            playlistModel = null;
        }
        this.adapter = new PlaylistAdapter(this, playlistModel);
        TvAudioPlayerBinding tvAudioPlayerBinding3 = this.binding;
        if (tvAudioPlayerBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            tvAudioPlayerBinding3 = null;
        }
        RecyclerView recyclerView = tvAudioPlayerBinding3.playlist;
        PlaylistAdapter playlistAdapter = this.adapter;
        if (playlistAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            playlistAdapter = null;
        }
        recyclerView.setAdapter(playlistAdapter);
        TvAudioPlayerBinding tvAudioPlayerBinding4 = this.binding;
        if (tvAudioPlayerBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            tvAudioPlayerBinding4 = null;
        }
        LifecycleOwner lifecycleOwner = this;
        tvAudioPlayerBinding4.setLifecycleOwner(lifecycleOwner);
        TvAudioPlayerBinding tvAudioPlayerBinding5 = this.binding;
        if (tvAudioPlayerBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            tvAudioPlayerBinding5 = null;
        }
        PlaylistModel playlistModel2 = this.model;
        if (playlistModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("model");
            playlistModel2 = null;
        }
        tvAudioPlayerBinding5.setProgress(playlistModel2.getProgress());
        PlaylistModel playlistModel3 = this.model;
        if (playlistModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("model");
            playlistModel3 = null;
        }
        playlistModel3.getDataset().observe(lifecycleOwner, new AudioPlayerActivity$sam$androidx_lifecycle_Observer$0(new AudioPlayerActivity$onCreate$1(this)));
        PlaylistModel playlistModel4 = this.model;
        if (playlistModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("model");
            playlistModel4 = null;
        }
        playlistModel4.getSpeed().observe(lifecycleOwner, new AudioPlayerActivity$sam$androidx_lifecycle_Observer$0(new AudioPlayerActivity$onCreate$2(this)));
        PlaybackService.Companion.getPlayerSleepTime().observe(lifecycleOwner, new AudioPlayerActivity$sam$androidx_lifecycle_Observer$0(new AudioPlayerActivity$onCreate$3(this)));
        TvAudioPlayerBinding tvAudioPlayerBinding6 = this.binding;
        if (tvAudioPlayerBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            tvAudioPlayerBinding6 = null;
        }
        tvAudioPlayerBinding6.mediaProgress.setOnSeekBarChangeListener(this.timelineListener);
        PlaylistModel playlistModel5 = this.model;
        if (playlistModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("model");
            playlistModel5 = null;
        }
        playlistModel5.getPlayerState().observe(lifecycleOwner, new AudioPlayerActivity$sam$androidx_lifecycle_Observer$0(new AudioPlayerActivity$onCreate$4(this)));
        int intExtra = getIntent().getIntExtra(MEDIA_POSITION, 0);
        if (getIntent().hasExtra(MEDIA_PLAYLIST)) {
            MediaUtils.openPlaylist$default(MediaUtils.INSTANCE, context, getIntent().getLongExtra(MEDIA_PLAYLIST, -1), intExtra, false, 8, (Object) null);
        } else {
            Intent intent = getIntent();
            Intrinsics.checkNotNullExpressionValue(intent, "getIntent(...)");
            if (Build.VERSION.SDK_INT >= 33) {
                arrayList = AppUtils$$ExternalSyntheticApiModelOutline0.m(intent, "media_list", MediaWrapper.class);
            } else {
                arrayList = intent.getParcelableArrayListExtra("media_list");
            }
            if (arrayList != null) {
                MediaUtils.openList$default(MediaUtils.INSTANCE, context, arrayList, intExtra, false, 8, (Object) null);
            }
        }
        AnimatedVectorDrawableCompat create = AnimatedVectorDrawableCompat.create(context, R.drawable.anim_play_pause_video);
        Intrinsics.checkNotNull(create);
        this.playToPause = create;
        AnimatedVectorDrawableCompat create2 = AnimatedVectorDrawableCompat.create(context, R.drawable.anim_pause_play_video);
        Intrinsics.checkNotNull(create2);
        this.pauseToPlay = create2;
        TvAudioPlayerBinding tvAudioPlayerBinding7 = this.binding;
        if (tvAudioPlayerBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            tvAudioPlayerBinding7 = null;
        }
        tvAudioPlayerBinding7.playbackSpeedQuickAction.setOnClickListener(new AudioPlayerActivity$$ExternalSyntheticLambda1(this));
        TvAudioPlayerBinding tvAudioPlayerBinding8 = this.binding;
        if (tvAudioPlayerBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            tvAudioPlayerBinding8 = null;
        }
        tvAudioPlayerBinding8.playbackSpeedQuickAction.setOnLongClickListener(new AudioPlayerActivity$$ExternalSyntheticLambda2(this));
        TvAudioPlayerBinding tvAudioPlayerBinding9 = this.binding;
        if (tvAudioPlayerBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            tvAudioPlayerBinding9 = null;
        }
        tvAudioPlayerBinding9.sleepQuickAction.setOnClickListener(new AudioPlayerActivity$$ExternalSyntheticLambda3(this));
        TvAudioPlayerBinding tvAudioPlayerBinding10 = this.binding;
        if (tvAudioPlayerBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            tvAudioPlayerBinding2 = tvAudioPlayerBinding10;
        }
        tvAudioPlayerBinding2.sleepQuickAction.setOnLongClickListener(new AudioPlayerActivity$$ExternalSyntheticLambda4(this));
        setBookmarkModel(BookmarkModel.Companion.get(this));
        getOnBackPressedDispatcher().addCallback(lifecycleOwner, new AudioPlayerActivity$onCreate$11(this));
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$2(AudioPlayerActivity audioPlayerActivity, View view) {
        Intrinsics.checkNotNullParameter(audioPlayerActivity, "this$0");
        PlaybackSpeedDialog.Companion.newInstance().show(audioPlayerActivity.getSupportFragmentManager(), SettingsKt.KEY_PLAYBACK_SPEED_PERSIST);
    }

    /* access modifiers changed from: private */
    public static final boolean onCreate$lambda$3(AudioPlayerActivity audioPlayerActivity, View view) {
        Intrinsics.checkNotNullParameter(audioPlayerActivity, "this$0");
        PlaylistModel playlistModel = audioPlayerActivity.model;
        if (playlistModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("model");
            playlistModel = null;
        }
        PlaybackService service = playlistModel.getService();
        if (service != null) {
            service.setRate(1.0f, true);
        }
        audioPlayerActivity.showChips();
        return true;
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$4(AudioPlayerActivity audioPlayerActivity, View view) {
        Intrinsics.checkNotNullParameter(audioPlayerActivity, "this$0");
        SleepTimerDialog.Companion.newInstance$default(SleepTimerDialog.Companion, false, 1, (Object) null).show(audioPlayerActivity.getSupportFragmentManager(), RtspHeaders.Values.TIME);
    }

    /* access modifiers changed from: private */
    public static final boolean onCreate$lambda$5(AudioPlayerActivity audioPlayerActivity, View view) {
        Intrinsics.checkNotNullParameter(audioPlayerActivity, "this$0");
        PlaylistModel playlistModel = audioPlayerActivity.model;
        if (playlistModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("model");
            playlistModel = null;
        }
        PlaybackService service = playlistModel.getService();
        if (service != null) {
            service.setSleepTimer((Calendar) null);
        }
        audioPlayerActivity.showChips();
        return true;
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.optionsDelegate = null;
    }

    /* access modifiers changed from: private */
    public final void showChips() {
        TvAudioPlayerBinding tvAudioPlayerBinding = this.binding;
        TvAudioPlayerBinding tvAudioPlayerBinding2 = null;
        if (tvAudioPlayerBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            tvAudioPlayerBinding = null;
        }
        KotlinExtensionsKt.setGone(tvAudioPlayerBinding.playbackSpeedQuickAction);
        TvAudioPlayerBinding tvAudioPlayerBinding3 = this.binding;
        if (tvAudioPlayerBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            tvAudioPlayerBinding3 = null;
        }
        KotlinExtensionsKt.setGone(tvAudioPlayerBinding3.sleepQuickAction);
        PlaylistModel playlistModel = this.model;
        if (playlistModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("model");
            playlistModel = null;
        }
        Float value = playlistModel.getSpeed().getValue();
        if (value != null) {
            if (value.floatValue() != 1.0f) {
                TvAudioPlayerBinding tvAudioPlayerBinding4 = this.binding;
                if (tvAudioPlayerBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    tvAudioPlayerBinding4 = null;
                }
                KotlinExtensionsKt.setVisible(tvAudioPlayerBinding4.playbackSpeedQuickAction);
            }
            TvAudioPlayerBinding tvAudioPlayerBinding5 = this.binding;
            if (tvAudioPlayerBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                tvAudioPlayerBinding5 = null;
            }
            tvAudioPlayerBinding5.playbackSpeedQuickActionText.setText(Strings.formatRateString(value.floatValue()));
        }
        Calendar value2 = PlaybackService.Companion.getPlayerSleepTime().getValue();
        if (value2 != null) {
            TvAudioPlayerBinding tvAudioPlayerBinding6 = this.binding;
            if (tvAudioPlayerBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                tvAudioPlayerBinding6 = null;
            }
            KotlinExtensionsKt.setVisible(tvAudioPlayerBinding6.sleepQuickAction);
            TvAudioPlayerBinding tvAudioPlayerBinding7 = this.binding;
            if (tvAudioPlayerBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                tvAudioPlayerBinding2 = tvAudioPlayerBinding7;
            }
            tvAudioPlayerBinding2.sleepQuickActionText.setText(DateFormat.getTimeFormat(this).format(value2.getTime()));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0021  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x006f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void update(org.videolan.vlc.viewmodels.PlayerState r10) {
        /*
            r9 = this;
            if (r10 != 0) goto L_0x0003
            return
        L_0x0003:
            boolean r0 = r10.getPlaying()
            r1 = 0
            if (r0 == 0) goto L_0x0011
            androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat r0 = r9.playToPause
            if (r0 != 0) goto L_0x001b
            java.lang.String r0 = "playToPause"
            goto L_0x0017
        L_0x0011:
            androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat r0 = r9.pauseToPlay
            if (r0 != 0) goto L_0x001b
            java.lang.String r0 = "pauseToPlay"
        L_0x0017:
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r0 = r1
        L_0x001b:
            org.videolan.television.databinding.TvAudioPlayerBinding r2 = r9.binding
            java.lang.String r3 = "binding"
            if (r2 != 0) goto L_0x0025
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r2 = r1
        L_0x0025:
            androidx.appcompat.widget.AppCompatImageView r2 = r2.buttonPlay
            r4 = r0
            android.graphics.drawable.Drawable r4 = (android.graphics.drawable.Drawable) r4
            r2.setImageDrawable(r4)
            boolean r2 = r10.getPlaying()
            boolean r4 = r9.wasPlaying
            if (r2 == r4) goto L_0x0047
            org.videolan.television.databinding.TvAudioPlayerBinding r2 = r9.binding
            if (r2 != 0) goto L_0x003d
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r2 = r1
        L_0x003d:
            androidx.appcompat.widget.AppCompatImageView r2 = r2.buttonPlay
            org.videolan.television.ui.audioplayer.AudioPlayerActivity$$ExternalSyntheticLambda5 r4 = new org.videolan.television.ui.audioplayer.AudioPlayerActivity$$ExternalSyntheticLambda5
            r4.<init>(r0)
            r2.post(r4)
        L_0x0047:
            boolean r0 = r10.getPlaying()
            r9.wasPlaying = r0
            org.videolan.television.databinding.TvAudioPlayerBinding r0 = r9.binding
            if (r0 != 0) goto L_0x0055
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r0 = r1
        L_0x0055:
            androidx.appcompat.widget.AppCompatImageView r0 = r0.buttonPlay
            boolean r2 = r10.getPlaying()
            if (r2 == 0) goto L_0x0060
            int r2 = org.videolan.vlc.R.string.pause
            goto L_0x0062
        L_0x0060:
            int r2 = org.videolan.vlc.R.string.play
        L_0x0062:
            java.lang.String r2 = r9.getString(r2)
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r0.setContentDescription(r2)
            org.videolan.vlc.viewmodels.PlaylistModel r0 = r9.model
            if (r0 != 0) goto L_0x0075
            java.lang.String r0 = "model"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r0 = r1
        L_0x0075:
            org.videolan.medialibrary.interfaces.media.MediaWrapper r0 = r0.getCurrentMediaWrapper()
            r2 = r9
            androidx.lifecycle.LifecycleOwner r2 = (androidx.lifecycle.LifecycleOwner) r2
            androidx.lifecycle.LifecycleCoroutineScope r2 = androidx.lifecycle.LifecycleOwnerKt.getLifecycleScope(r2)
            r3 = r2
            kotlinx.coroutines.CoroutineScope r3 = (kotlinx.coroutines.CoroutineScope) r3
            org.videolan.television.ui.audioplayer.AudioPlayerActivity$update$2 r2 = new org.videolan.television.ui.audioplayer.AudioPlayerActivity$update$2
            r2.<init>(r9, r10, r0, r1)
            r6 = r2
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
            r7 = 3
            r8 = 0
            r4 = 0
            r5 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r3, r4, r5, r6, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.ui.audioplayer.AudioPlayerActivity.update(org.videolan.vlc.viewmodels.PlayerState):void");
    }

    /* access modifiers changed from: private */
    public static final void update$lambda$8(AnimatedVectorDrawableCompat animatedVectorDrawableCompat) {
        Intrinsics.checkNotNullParameter(animatedVectorDrawableCompat, "$drawable");
        animatedVectorDrawableCompat.start();
    }

    /* access modifiers changed from: private */
    public final Job updateBackground() {
        return LifecycleOwnerKt.getLifecycleScope(this).launchWhenStarted(new AudioPlayerActivity$updateBackground$1(this, (Continuation<? super AudioPlayerActivity$updateBackground$1>) null));
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(keyEvent, NotificationCompat.CATEGORY_EVENT);
        if (getPlayerKeyListenerDelegate().onKeyDown(i, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void stop() {
        PlaylistModel playlistModel = this.model;
        if (playlistModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("model");
            playlistModel = null;
        }
        playlistModel.stop();
        finish();
    }

    public void seek(int i) {
        PlaylistModel playlistModel;
        PlaylistModel playlistModel2 = this.model;
        if (playlistModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("model");
            playlistModel2 = null;
        }
        int time = ((int) playlistModel2.getTime()) + i;
        if (time >= 0) {
            long j = (long) time;
            PlaylistModel playlistModel3 = this.model;
            if (playlistModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("model");
                playlistModel3 = null;
            }
            if (j <= playlistModel3.getLength()) {
                PlaylistModel playlistModel4 = this.model;
                if (playlistModel4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("model");
                    playlistModel = null;
                } else {
                    playlistModel = playlistModel4;
                }
                PlaylistModel.setTime$default(playlistModel, j, false, 2, (Object) null);
            }
        }
    }

    public void showAdvancedOptions() {
        showAdvancedOptions((View) null);
    }

    public void previous() {
        PlaylistModel playlistModel = this.model;
        if (playlistModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("model");
            playlistModel = null;
        }
        playlistModel.previous(false);
    }

    public void next() {
        PlaylistModel playlistModel = this.model;
        if (playlistModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("model");
            playlistModel = null;
        }
        playlistModel.next();
    }

    public void togglePlayPause() {
        PlaylistModel playlistModel = this.model;
        if (playlistModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("model");
            playlistModel = null;
        }
        playlistModel.togglePlayPause();
    }

    public void showEqualizer() {
        new EqualizerFragment().show(getSupportFragmentManager(), "equalizer");
    }

    public void increaseRate() {
        PlaylistModel playlistModel = this.model;
        if (playlistModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("model");
            playlistModel = null;
        }
        PlaybackService service = playlistModel.getService();
        if (service != null) {
            service.increaseRate();
        }
    }

    public void decreaseRate() {
        PlaylistModel playlistModel = this.model;
        if (playlistModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("model");
            playlistModel = null;
        }
        PlaybackService service = playlistModel.getService();
        if (service != null) {
            service.decreaseRate();
        }
    }

    public void resetRate() {
        PlaylistModel playlistModel = this.model;
        if (playlistModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("model");
            playlistModel = null;
        }
        PlaybackService service = playlistModel.getService();
        if (service != null) {
            service.resetRate();
        }
    }

    public void bookmark() {
        getBookmarkModel().addBookmark(this);
        String string = getString(org.videolan.vlc.R.string.bookmark_added);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        UiTools.snackerConfirm$default(UiTools.INSTANCE, this, string, false, org.videolan.vlc.R.string.show, new AudioPlayerActivity$bookmark$1(this), 4, (Object) null);
    }

    public final void playSelection() {
        PlaylistModel playlistModel = this.model;
        PlaylistAdapter playlistAdapter = null;
        if (playlistModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("model");
            playlistModel = null;
        }
        PlaylistAdapter playlistAdapter2 = this.adapter;
        if (playlistAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            playlistAdapter = playlistAdapter2;
        }
        playlistModel.play(playlistAdapter.getSelectedItem());
    }

    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, NotificationCompat.CATEGORY_EVENT);
        if ((motionEvent.getSource() & InputDeviceCompat.SOURCE_JOYSTICK) == 16777232 && motionEvent.getAction() == 2) {
            InputDevice device = motionEvent.getDevice();
            float axisValue = motionEvent.getAxisValue(15);
            float axisValue2 = motionEvent.getAxisValue(16);
            if (!(device == null || Math.abs(axisValue) == 1.0f || Math.abs(axisValue2) == 1.0f)) {
                float centeredAxis = AndroidDevices.INSTANCE.getCenteredAxis(motionEvent, device, 0);
                if (((double) Math.abs(centeredAxis)) > 0.3d && System.currentTimeMillis() - this.lastMove > 300) {
                    seek(centeredAxis > 0.0f ? 10000 : -10000);
                    this.lastMove = System.currentTimeMillis();
                }
                return true;
            }
        }
        return false;
    }

    public final void onClick(View view) {
        Intrinsics.checkNotNullParameter(view, "v");
        int id = view.getId();
        if (id == R.id.button_play) {
            togglePlayPause();
        } else if (id == R.id.button_next) {
            next();
        } else if (id == R.id.button_previous) {
            previous();
        } else if (id == R.id.button_repeat) {
            switchRepeatMode();
        } else if (id == R.id.button_shuffle) {
            setShuffleMode(!this.shuffling);
        } else if (id == R.id.button_more) {
            showAdvancedOptions(view);
        }
    }

    private final void showAdvancedOptions(View view) {
        if (this.optionsDelegate == null) {
            PlaylistModel playlistModel = this.model;
            if (playlistModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("model");
                playlistModel = null;
            }
            PlaybackService service = playlistModel.getService();
            if (service != null) {
                PlayerOptionsDelegate playerOptionsDelegate = new PlayerOptionsDelegate(this, service, false);
                this.optionsDelegate = playerOptionsDelegate;
                playerOptionsDelegate.setBookmarkClickedListener(new AudioPlayerActivity$showAdvancedOptions$1(this));
            } else {
                return;
            }
        }
        PlayerOptionsDelegate playerOptionsDelegate2 = this.optionsDelegate;
        if (playerOptionsDelegate2 != null) {
            playerOptionsDelegate2.show();
        }
    }

    /* access modifiers changed from: private */
    public final void showBookmarks() {
        PlaylistModel playlistModel = this.model;
        BookmarkListDelegate bookmarkListDelegate2 = null;
        if (playlistModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("model");
            playlistModel = null;
        }
        PlaybackService service = playlistModel.getService();
        if (service != null) {
            if (this.bookmarkListDelegate == null) {
                BookmarkListDelegate bookmarkListDelegate3 = new BookmarkListDelegate(this, service, getBookmarkModel());
                this.bookmarkListDelegate = bookmarkListDelegate3;
                bookmarkListDelegate3.setVisibilityListener(new AudioPlayerActivity$showBookmarks$1$1(this));
                BookmarkListDelegate bookmarkListDelegate4 = this.bookmarkListDelegate;
                if (bookmarkListDelegate4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("bookmarkListDelegate");
                    bookmarkListDelegate4 = null;
                }
                TvAudioPlayerBinding tvAudioPlayerBinding = this.binding;
                if (tvAudioPlayerBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    tvAudioPlayerBinding = null;
                }
                ConstraintLayout constraintLayout = tvAudioPlayerBinding.bookmarkMarkerContainer;
                Intrinsics.checkNotNullExpressionValue(constraintLayout, "bookmarkMarkerContainer");
                bookmarkListDelegate4.setMarkerContainer(constraintLayout);
            }
            BookmarkListDelegate bookmarkListDelegate5 = this.bookmarkListDelegate;
            if (bookmarkListDelegate5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bookmarkListDelegate");
            } else {
                bookmarkListDelegate2 = bookmarkListDelegate5;
            }
            bookmarkListDelegate2.show();
        }
    }

    private final void setShuffleMode(boolean z) {
        List mutableList;
        this.shuffling = z;
        PlaylistModel playlistModel = this.model;
        PlaylistModel playlistModel2 = null;
        if (playlistModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("model");
            playlistModel = null;
        }
        List<MediaWrapper> medias = playlistModel.getMedias();
        if (medias != null && (mutableList = CollectionsKt.toMutableList(medias)) != null) {
            if (z) {
                Collections.shuffle(mutableList);
            } else {
                CollectionsKt.sortWith(mutableList, MediaComparators.INSTANCE.getBY_TRACK_NUMBER());
            }
            PlaylistModel playlistModel3 = this.model;
            if (playlistModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("model");
            } else {
                playlistModel2 = playlistModel3;
            }
            playlistModel2.load(mutableList, 0);
        }
    }

    /* access modifiers changed from: private */
    public final void updateRepeatMode() {
        PlaylistModel playlistModel = this.model;
        TvAudioPlayerBinding tvAudioPlayerBinding = null;
        if (playlistModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("model");
            playlistModel = null;
        }
        int repeatType = playlistModel.getRepeatType();
        if (repeatType == 0) {
            PlaylistModel playlistModel2 = this.model;
            if (playlistModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("model");
                playlistModel2 = null;
            }
            playlistModel2.setRepeatType(0);
            TvAudioPlayerBinding tvAudioPlayerBinding2 = this.binding;
            if (tvAudioPlayerBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                tvAudioPlayerBinding2 = null;
            }
            tvAudioPlayerBinding2.buttonRepeat.setImageResource(R.drawable.ic_repeat_audio);
            TvAudioPlayerBinding tvAudioPlayerBinding3 = this.binding;
            if (tvAudioPlayerBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                tvAudioPlayerBinding = tvAudioPlayerBinding3;
            }
            tvAudioPlayerBinding.buttonRepeat.setContentDescription(getString(R.string.repeat_none));
        } else if (repeatType == 1) {
            TvAudioPlayerBinding tvAudioPlayerBinding4 = this.binding;
            if (tvAudioPlayerBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                tvAudioPlayerBinding4 = null;
            }
            tvAudioPlayerBinding4.buttonRepeat.setImageResource(R.drawable.ic_repeat_one_audio);
            TvAudioPlayerBinding tvAudioPlayerBinding5 = this.binding;
            if (tvAudioPlayerBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                tvAudioPlayerBinding = tvAudioPlayerBinding5;
            }
            tvAudioPlayerBinding.buttonRepeat.setContentDescription(getString(R.string.repeat_single));
        } else if (repeatType == 2) {
            TvAudioPlayerBinding tvAudioPlayerBinding6 = this.binding;
            if (tvAudioPlayerBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                tvAudioPlayerBinding6 = null;
            }
            tvAudioPlayerBinding6.buttonRepeat.setImageResource(R.drawable.ic_repeat_all_audio);
            TvAudioPlayerBinding tvAudioPlayerBinding7 = this.binding;
            if (tvAudioPlayerBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                tvAudioPlayerBinding = tvAudioPlayerBinding7;
            }
            tvAudioPlayerBinding.buttonRepeat.setContentDescription(getString(R.string.repeat_all));
        }
    }

    private final void switchRepeatMode() {
        PlaylistModel playlistModel = this.model;
        TvAudioPlayerBinding tvAudioPlayerBinding = null;
        if (playlistModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("model");
            playlistModel = null;
        }
        int repeatType = playlistModel.getRepeatType();
        if (repeatType == 0) {
            PlaylistModel playlistModel2 = this.model;
            if (playlistModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("model");
                playlistModel2 = null;
            }
            playlistModel2.setRepeatType(2);
            TvAudioPlayerBinding tvAudioPlayerBinding2 = this.binding;
            if (tvAudioPlayerBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                tvAudioPlayerBinding2 = null;
            }
            tvAudioPlayerBinding2.buttonRepeat.setImageResource(R.drawable.ic_repeat_all_audio);
            TvAudioPlayerBinding tvAudioPlayerBinding3 = this.binding;
            if (tvAudioPlayerBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                tvAudioPlayerBinding = tvAudioPlayerBinding3;
            }
            tvAudioPlayerBinding.buttonRepeat.setContentDescription(getString(R.string.repeat_all));
        } else if (repeatType == 1) {
            PlaylistModel playlistModel3 = this.model;
            if (playlistModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("model");
                playlistModel3 = null;
            }
            playlistModel3.setRepeatType(0);
            TvAudioPlayerBinding tvAudioPlayerBinding4 = this.binding;
            if (tvAudioPlayerBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                tvAudioPlayerBinding4 = null;
            }
            tvAudioPlayerBinding4.buttonRepeat.setImageResource(R.drawable.ic_repeat_audio);
            TvAudioPlayerBinding tvAudioPlayerBinding5 = this.binding;
            if (tvAudioPlayerBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                tvAudioPlayerBinding = tvAudioPlayerBinding5;
            }
            tvAudioPlayerBinding.buttonRepeat.setContentDescription(getString(R.string.repeat_none));
        } else if (repeatType == 2) {
            PlaylistModel playlistModel4 = this.model;
            if (playlistModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("model");
                playlistModel4 = null;
            }
            playlistModel4.setRepeatType(1);
            TvAudioPlayerBinding tvAudioPlayerBinding6 = this.binding;
            if (tvAudioPlayerBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                tvAudioPlayerBinding6 = null;
            }
            tvAudioPlayerBinding6.buttonRepeat.setImageResource(R.drawable.ic_repeat_one_audio);
            TvAudioPlayerBinding tvAudioPlayerBinding7 = this.binding;
            if (tvAudioPlayerBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                tvAudioPlayerBinding = tvAudioPlayerBinding7;
            }
            tvAudioPlayerBinding.buttonRepeat.setContentDescription(getString(R.string.repeat_single));
        }
    }

    public final void onUpdateFinished() {
        TvAudioPlayerBinding tvAudioPlayerBinding = this.binding;
        if (tvAudioPlayerBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            tvAudioPlayerBinding = null;
        }
        tvAudioPlayerBinding.getRoot().post(new AudioPlayerActivity$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: private */
    public static final void onUpdateFinished$lambda$10(AudioPlayerActivity audioPlayerActivity) {
        Intrinsics.checkNotNullParameter(audioPlayerActivity, "this$0");
        PlaylistModel playlistModel = audioPlayerActivity.model;
        TvAudioPlayerBinding tvAudioPlayerBinding = null;
        if (playlistModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("model");
            playlistModel = null;
        }
        int currentMediaPosition = playlistModel.getCurrentMediaPosition();
        if (currentMediaPosition >= 0) {
            PlaylistAdapter playlistAdapter = audioPlayerActivity.adapter;
            if (playlistAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                playlistAdapter = null;
            }
            playlistAdapter.setSelection(currentMediaPosition);
            TvAudioPlayerBinding tvAudioPlayerBinding2 = audioPlayerActivity.binding;
            if (tvAudioPlayerBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                tvAudioPlayerBinding2 = null;
            }
            RecyclerView.LayoutManager layoutManager = tvAudioPlayerBinding2.playlist.getLayoutManager();
            Intrinsics.checkNotNull(layoutManager, "null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
            int findFirstCompletelyVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition();
            TvAudioPlayerBinding tvAudioPlayerBinding3 = audioPlayerActivity.binding;
            if (tvAudioPlayerBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                tvAudioPlayerBinding3 = null;
            }
            RecyclerView.LayoutManager layoutManager2 = tvAudioPlayerBinding3.playlist.getLayoutManager();
            Intrinsics.checkNotNull(layoutManager2, "null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
            int findLastCompletelyVisibleItemPosition = ((LinearLayoutManager) layoutManager2).findLastCompletelyVisibleItemPosition();
            if (currentMediaPosition < findFirstCompletelyVisibleItemPosition || currentMediaPosition > findLastCompletelyVisibleItemPosition) {
                TvAudioPlayerBinding tvAudioPlayerBinding4 = audioPlayerActivity.binding;
                if (tvAudioPlayerBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    tvAudioPlayerBinding = tvAudioPlayerBinding4;
                }
                tvAudioPlayerBinding.playlist.smoothScrollToPosition(currentMediaPosition);
            }
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lorg/videolan/television/ui/audioplayer/AudioPlayerActivity$Companion;", "", "()V", "JOYSTICK_INPUT_DELAY", "", "MEDIA_LIST", "", "MEDIA_PLAYLIST", "MEDIA_POSITION", "TAG", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: AudioPlayerActivity.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
