package org.videolan.vlc.gui.video;

import android.animation.Animator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.DisplayCutout;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.ViewStubCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewGroupKt;
import androidx.core.view.WindowInsetsCompat$$ExternalSyntheticApiModelOutline0;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.WindowIdApi18$$ExternalSyntheticApiModelOutline0;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import androidx.window.layout.FoldingFeature;
import com.google.android.material.textfield.TextInputLayout;
import java.util.Calendar;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.internal.cache.DiskLruCache;
import org.videolan.libvlc.MediaDiscoverer;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.libvlc.util.VLCVideoLayout;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaWrapperImpl;
import org.videolan.resources.AndroidDevices;
import org.videolan.resources.Constants;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.tools.livedata.LiveDataset;
import org.videolan.vlc.ArtworkProvider;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.R;
import org.videolan.vlc.RendererDelegate;
import org.videolan.vlc.databinding.PlayerHudBinding;
import org.videolan.vlc.databinding.PlayerHudRightBinding;
import org.videolan.vlc.gui.audio.PlaylistAdapter;
import org.videolan.vlc.gui.browser.BaseBrowserFragmentKt;
import org.videolan.vlc.gui.browser.FilePickerActivity;
import org.videolan.vlc.gui.helpers.BookmarkListDelegate;
import org.videolan.vlc.gui.helpers.OnRepeatListenerKey;
import org.videolan.vlc.gui.helpers.SwipeDragItemTouchHelperCallback;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.gui.view.AccessibleSeekBar;
import org.videolan.vlc.gui.view.FocusableTextView;
import org.videolan.vlc.gui.view.PlayerProgress;
import org.videolan.vlc.media.MediaUtils;
import org.videolan.vlc.util.AccessibilityHelperKt;
import org.videolan.vlc.util.BrowserutilsKt;
import org.videolan.vlc.util.FileUtils;
import org.videolan.vlc.util.KextensionsKt;
import org.videolan.vlc.viewmodels.PlaylistModel;

@Metadata(d1 = {"\u0000Ì\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0002\b\u0017\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J$\u0010\u001a\u00030\u00012\u0007\u0010\u0001\u001a\u00020\f2\u0007\u0010\u0001\u001a\u00020I2\u0007\u0010\u0001\u001a\u00020\u0012H\u0002J\u001c\u0010\u0001\u001a\u00030\u00012\u0007\u0010\u0001\u001a\u00020\f2\u0007\u0010\u0001\u001a\u00020IH\u0002J\u0013\u0010\u0001\u001a\u00030\u00012\u0007\u0010\u0001\u001a\u00020\u0012H\u0007J\u0012\u0010\u0001\u001a\u0005\u0018\u00010\u0001H\u0002¢\u0006\u0003\u0010\u0001JA\u0010\u0001\u001a\u00030\u00012\u0010\u0010\u0001\u001a\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\u00012\b\u0010\u0001\u001a\u00030\u00012\u0013\b\u0002\u0010\u0001\u001a\f\u0012\u0005\u0012\u00030\u0001\u0018\u00010\u0001H\u0002¢\u0006\u0003\u0010\u0001J,\u0010\u0001\u001a\u00030\u00012\u0010\u0010\u0001\u001a\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\u00012\b\u0010\u0001\u001a\u00030\u0001H\u0002¢\u0006\u0003\u0010\u0001J\u0013\u0010\u0001\u001a\u00030\u00012\t\u0010\u0001\u001a\u0004\u0018\u00010\fJ\b\u0010\u0001\u001a\u00030\u0001J\t\u0010\u0001\u001a\u0004\u0018\u00010VJ\t\u0010\u0001\u001a\u0004\u0018\u00010VJ\b\u0010\u0001\u001a\u00030\u0001J\b\u0010\u0001\u001a\u00030\u0001J\u001c\u0010\u0001\u001a\u00030\u00012\u0007\u0010\u0001\u001a\u00020\u00122\t\b\u0002\u0010\u0001\u001a\u00020\u0012J\b\u0010\u0001\u001a\u00030\u0001J\n\u0010\u0001\u001a\u00030\u0001H\u0003J\n\u0010 \u0001\u001a\u00030\u0001H\u0002J\n\u0010¡\u0001\u001a\u00030\u0001H\u0003J\u0007\u0010¢\u0001\u001a\u00020\u0012J\u0007\u0010£\u0001\u001a\u00020\u0012J\u0007\u0010¤\u0001\u001a\u00020\u0012J\u0007\u0010¥\u0001\u001a\u00020\u0012J\b\u0010¦\u0001\u001a\u00030\u0001J\b\u0010§\u0001\u001a\u00030\u0001J\n\u0010¨\u0001\u001a\u00030\u0001H\u0002J\b\u0010©\u0001\u001a\u00030\u0001J\n\u0010ª\u0001\u001a\u00030\u0001H\u0002J\n\u0010«\u0001\u001a\u00030\u0001H\u0002J\n\u0010¬\u0001\u001a\u00030\u0001H\u0007J\u0014\u0010­\u0001\u001a\u00030\u00012\b\u0010®\u0001\u001a\u00030¯\u0001H\u0002J\b\u0010°\u0001\u001a\u00030\u0001J\u0011\u0010±\u0001\u001a\u00030\u00012\u0007\u0010²\u0001\u001a\u00020\u0012J\b\u0010³\u0001\u001a\u00030\u0001J\u0011\u0010´\u0001\u001a\u00030\u00012\u0007\u0010µ\u0001\u001a\u00020IJ\u0011\u0010¶\u0001\u001a\u00030\u00012\u0007\u0010·\u0001\u001a\u00020\u0012J\n\u0010¸\u0001\u001a\u00030\u0001H\u0002J'\u0010¹\u0001\u001a\u00030\u00012\t\b\u0001\u0010º\u0001\u001a\u00020I2\u0007\u0010»\u0001\u001a\u00020I2\t\b\u0003\u0010¼\u0001\u001a\u00020IJ'\u0010¹\u0001\u001a\u00030\u00012\b\u0010½\u0001\u001a\u00030¾\u00012\u0007\u0010»\u0001\u001a\u00020I2\n\b\u0002\u0010¿\u0001\u001a\u00030¾\u0001J\u0013\u0010À\u0001\u001a\u00030\u00012\t\b\u0002\u0010Á\u0001\u001a\u00020\u0012J\u0011\u0010Â\u0001\u001a\u00030\u00012\u0007\u0010Ã\u0001\u001a\u00020IJ\b\u0010Ä\u0001\u001a\u00030\u0001J\u0011\u0010Å\u0001\u001a\u00030\u00012\u0007\u0010Æ\u0001\u001a\u00020IJ\b\u0010Ç\u0001\u001a\u00030\u0001J\b\u0010È\u0001\u001a\u00030\u0001J\b\u0010É\u0001\u001a\u00030\u0001J\b\u0010Ê\u0001\u001a\u00030\u0001J\b\u0010Ë\u0001\u001a\u00030\u0001J\u0013\u0010Ì\u0001\u001a\u00030\u00012\t\b\u0002\u0010Í\u0001\u001a\u00020\u0012J\u0011\u0010Î\u0001\u001a\u00030\u00012\u0007\u0010Ï\u0001\u001a\u00020\u0012J\b\u0010Ð\u0001\u001a\u00030\u0001J\b\u0010Ñ\u0001\u001a\u00030\u0001J\u0011\u0010Ò\u0001\u001a\u00030\u00012\u0007\u0010Ó\u0001\u001a\u00020\u0012J\b\u0010Ô\u0001\u001a\u00030\u0001R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX.¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\fX.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R(\u0010\u0019\u001a\u0004\u0018\u00010\u00182\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0014\"\u0004\b \u0010\u0016R\u001c\u0010!\u001a\u0004\u0018\u00010\"X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001c\u0010'\u001a\u0004\u0018\u00010\"X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010$\"\u0004\b)\u0010&R\u000e\u0010*\u001a\u00020\u0012X\u000e¢\u0006\u0002\n\u0000R\u001d\u0010+\u001a\u0004\u0018\u00010\f8BX\u0002¢\u0006\f\n\u0004\b-\u0010.\u001a\u0004\b,\u0010\u000eR\u001a\u0010/\u001a\u000200X.¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u001d\u00105\u001a\u0004\u0018\u00010\f8BX\u0002¢\u0006\f\n\u0004\b7\u0010.\u001a\u0004\b6\u0010\u000eR\u001a\u00108\u001a\u000209X.¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\u001c\u0010>\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR\u000e\u0010C\u001a\u00020\u0012X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010D\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000R\u001c\u0010E\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010\u000e\"\u0004\bG\u0010\u0010R\u000e\u0010H\u001a\u00020IX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010J\u001a\u00020KX.¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010M\"\u0004\bN\u0010OR\u001a\u0010P\u001a\u00020KX.¢\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010M\"\u0004\bR\u0010OR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010S\u001a\u00020TX.¢\u0006\u0002\n\u0000R\u000e\u0010U\u001a\u00020VX.¢\u0006\u0002\n\u0000R\u000e\u0010W\u001a\u00020VX.¢\u0006\u0002\n\u0000R\u001a\u0010X\u001a\u00020YX.¢\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010[\"\u0004\b\\\u0010]R\u000e\u0010^\u001a\u00020TX.¢\u0006\u0002\n\u0000R\u001a\u0010_\u001a\u00020`X.¢\u0006\u000e\n\u0000\u001a\u0004\ba\u0010b\"\u0004\bc\u0010dR\u001a\u0010e\u001a\u00020fX.¢\u0006\u000e\n\u0000\u001a\u0004\bg\u0010h\"\u0004\bi\u0010jR\u001a\u0010k\u001a\u00020\fX.¢\u0006\u000e\n\u0000\u001a\u0004\bl\u0010\u000e\"\u0004\bm\u0010\u0010R\u001a\u0010n\u001a\u00020oX.¢\u0006\u000e\n\u0000\u001a\u0004\bp\u0010q\"\u0004\br\u0010sR\u001a\u0010t\u001a\u00020\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bu\u0010\u0014\"\u0004\bv\u0010\u0016R\u001c\u0010w\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bx\u0010@\"\u0004\by\u0010BR\u000e\u0010z\u001a\u00020{X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010|\u001a\u00020{X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010}\u001a\u00020\nX.¢\u0006\u0002\n\u0000R\u000e\u0010~\u001a\u00020\u0012X\u000e¢\u0006\u0002\n\u0000¨\u0006Õ\u0001"}, d2 = {"Lorg/videolan/vlc/gui/video/VideoPlayerOverlayDelegate;", "", "player", "Lorg/videolan/vlc/gui/video/VideoPlayerActivity;", "(Lorg/videolan/vlc/gui/video/VideoPlayerActivity;)V", "abRepeatAddMarker", "Landroid/widget/Button;", "bookmarkListDelegate", "Lorg/videolan/vlc/gui/helpers/BookmarkListDelegate;", "brightnessValueText", "Landroid/widget/TextView;", "closeButton", "Landroid/view/View;", "getCloseButton", "()Landroid/view/View;", "setCloseButton", "(Landroid/view/View;)V", "enableSubs", "", "getEnableSubs", "()Z", "setEnableSubs", "(Z)V", "value", "Landroidx/window/layout/FoldingFeature;", "foldingFeature", "getFoldingFeature", "()Landroidx/window/layout/FoldingFeature;", "setFoldingFeature", "(Landroidx/window/layout/FoldingFeature;)V", "hasPlaylist", "getHasPlaylist", "setHasPlaylist", "hingeArrowLeft", "Landroid/widget/ImageView;", "getHingeArrowLeft", "()Landroid/widget/ImageView;", "setHingeArrowLeft", "(Landroid/widget/ImageView;)V", "hingeArrowRight", "getHingeArrowRight", "setHingeArrowRight", "hingeSnackShown", "hudBackground", "getHudBackground", "hudBackground$delegate", "Lkotlin/Lazy;", "hudBinding", "Lorg/videolan/vlc/databinding/PlayerHudBinding;", "getHudBinding", "()Lorg/videolan/vlc/databinding/PlayerHudBinding;", "setHudBinding", "(Lorg/videolan/vlc/databinding/PlayerHudBinding;)V", "hudRightBackground", "getHudRightBackground", "hudRightBackground$delegate", "hudRightBinding", "Lorg/videolan/vlc/databinding/PlayerHudRightBinding;", "getHudRightBinding", "()Lorg/videolan/vlc/databinding/PlayerHudRightBinding;", "setHudRightBinding", "(Lorg/videolan/vlc/databinding/PlayerHudRightBinding;)V", "info", "getInfo", "()Landroid/widget/TextView;", "setInfo", "(Landroid/widget/TextView;)V", "orientationLockedBeforeLock", "overlayBackground", "overlayInfo", "getOverlayInfo", "setOverlayInfo", "overlayTimeout", "", "pauseToPlay", "Landroidx/vectordrawable/graphics/drawable/AnimatedVectorDrawableCompat;", "getPauseToPlay", "()Landroidx/vectordrawable/graphics/drawable/AnimatedVectorDrawableCompat;", "setPauseToPlay", "(Landroidx/vectordrawable/graphics/drawable/AnimatedVectorDrawableCompat;)V", "playToPause", "getPlayToPause", "setPlayToPause", "playerBrightnessProgress", "Lorg/videolan/vlc/gui/view/PlayerProgress;", "playerOverlayBrightness", "Landroidx/constraintlayout/widget/ConstraintLayout;", "playerOverlayVolume", "playerUiContainer", "Landroid/view/ViewGroup;", "getPlayerUiContainer", "()Landroid/view/ViewGroup;", "setPlayerUiContainer", "(Landroid/view/ViewGroup;)V", "playerVolumeProgress", "playlist", "Landroidx/recyclerview/widget/RecyclerView;", "getPlaylist", "()Landroidx/recyclerview/widget/RecyclerView;", "setPlaylist", "(Landroidx/recyclerview/widget/RecyclerView;)V", "playlistAdapter", "Lorg/videolan/vlc/gui/audio/PlaylistAdapter;", "getPlaylistAdapter", "()Lorg/videolan/vlc/gui/audio/PlaylistAdapter;", "setPlaylistAdapter", "(Lorg/videolan/vlc/gui/audio/PlaylistAdapter;)V", "playlistContainer", "getPlaylistContainer", "setPlaylistContainer", "playlistSearchText", "Lcom/google/android/material/textfield/TextInputLayout;", "getPlaylistSearchText", "()Lcom/google/android/material/textfield/TextInputLayout;", "setPlaylistSearchText", "(Lcom/google/android/material/textfield/TextInputLayout;)V", "seekButtons", "getSeekButtons", "setSeekButtons", "subinfo", "getSubinfo", "setSubinfo", "titleConstraintSetLandscape", "Landroidx/constraintlayout/widget/ConstraintSet;", "titleConstraintSetPortrait", "volumeValueText", "wasPlaying", "applyMargin", "Landroidx/constraintlayout/widget/ConstraintLayout$LayoutParams;", "view", "margin", "isEnd", "applyVerticalMargin", "dimStatusBar", "", "dim", "downloadSubtitles", "()Lkotlin/Unit;", "enterAnimate", "views", "", "translationStart", "", "endListener", "Lkotlin/Function0;", "([Landroid/view/View;FLkotlin/jvm/functions/Function0;)V", "exitAnimate", "translationEnd", "([Landroid/view/View;F)V", "fadeOutInfo", "focusPlayPause", "getOverlayBrightness", "getOverlayVolume", "hideBookmarks", "hideInfo", "hideOverlay", "fromUser", "forceTalkback", "initInfoOverlay", "initOverlay", "initPlaylistUi", "initSeekButton", "isBookmarkShown", "isHudBindingInitialized", "isHudRightBindingInitialized", "isPlaylistAdapterInitialized", "lockScreen", "manageHinge", "manageTitleConstraints", "onDestroy", "pickSubtitles", "resetHingeLayout", "resetHudLayout", "resetSleepTimer", "service", "Lorg/videolan/vlc/PlaybackService;", "rotateBookmarks", "setListeners", "enabled", "showBookmarks", "showBrightnessBar", "brightness", "showControls", "show", "showHingeSnackIfNeeded", "showInfo", "textId", "duration", "subtextId", "text", "", "subText", "showOverlay", "forceCheck", "showOverlayTimeout", "timeout", "showTracks", "showVolumeBar", "volume", "toggleOverlay", "togglePlaylist", "unlockScreen", "updateHudMargins", "updateOrientationIcon", "updateOverlayPausePlay", "skipAnim", "updatePausable", "pausable", "updateRendererVisibility", "updateScreenshotButton", "updateSeekable", "seekable", "updateTitleConstraints", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoPlayerOverlayDelegate.kt */
public final class VideoPlayerOverlayDelegate {
    /* access modifiers changed from: private */
    public Button abRepeatAddMarker;
    /* access modifiers changed from: private */
    public BookmarkListDelegate bookmarkListDelegate;
    private TextView brightnessValueText;
    public View closeButton;
    private boolean enableSubs = true;
    private FoldingFeature foldingFeature;
    private boolean hasPlaylist;
    private ImageView hingeArrowLeft;
    private ImageView hingeArrowRight;
    private boolean hingeSnackShown;
    private final Lazy hudBackground$delegate = LazyKt.lazy(new VideoPlayerOverlayDelegate$hudBackground$2(this));
    public PlayerHudBinding hudBinding;
    private final Lazy hudRightBackground$delegate = LazyKt.lazy(new VideoPlayerOverlayDelegate$hudRightBackground$2(this));
    public PlayerHudRightBinding hudRightBinding;
    private TextView info;
    private boolean orientationLockedBeforeLock;
    private View overlayBackground;
    private View overlayInfo;
    /* access modifiers changed from: private */
    public int overlayTimeout;
    public AnimatedVectorDrawableCompat pauseToPlay;
    public AnimatedVectorDrawableCompat playToPause;
    /* access modifiers changed from: private */
    public final VideoPlayerActivity player;
    private PlayerProgress playerBrightnessProgress;
    private ConstraintLayout playerOverlayBrightness;
    private ConstraintLayout playerOverlayVolume;
    public ViewGroup playerUiContainer;
    private PlayerProgress playerVolumeProgress;
    public RecyclerView playlist;
    public PlaylistAdapter playlistAdapter;
    public View playlistContainer;
    public TextInputLayout playlistSearchText;
    private boolean seekButtons;
    private TextView subinfo;
    private final ConstraintSet titleConstraintSetLandscape = new ConstraintSet();
    private final ConstraintSet titleConstraintSetPortrait = new ConstraintSet();
    private TextView volumeValueText;
    private boolean wasPlaying = true;

    public VideoPlayerOverlayDelegate(VideoPlayerActivity videoPlayerActivity) {
        Intrinsics.checkNotNullParameter(videoPlayerActivity, "player");
        this.player = videoPlayerActivity;
    }

    public final TextView getInfo() {
        return this.info;
    }

    public final void setInfo(TextView textView) {
        this.info = textView;
    }

    public final TextView getSubinfo() {
        return this.subinfo;
    }

    public final void setSubinfo(TextView textView) {
        this.subinfo = textView;
    }

    public final View getOverlayInfo() {
        return this.overlayInfo;
    }

    public final void setOverlayInfo(View view) {
        this.overlayInfo = view;
    }

    public final ViewGroup getPlayerUiContainer() {
        ViewGroup viewGroup = this.playerUiContainer;
        if (viewGroup != null) {
            return viewGroup;
        }
        Intrinsics.throwUninitializedPropertyAccessException("playerUiContainer");
        return null;
    }

    public final void setPlayerUiContainer(ViewGroup viewGroup) {
        Intrinsics.checkNotNullParameter(viewGroup, "<set-?>");
        this.playerUiContainer = viewGroup;
    }

    public final PlayerHudBinding getHudBinding() {
        PlayerHudBinding playerHudBinding = this.hudBinding;
        if (playerHudBinding != null) {
            return playerHudBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("hudBinding");
        return null;
    }

    public final void setHudBinding(PlayerHudBinding playerHudBinding) {
        Intrinsics.checkNotNullParameter(playerHudBinding, "<set-?>");
        this.hudBinding = playerHudBinding;
    }

    public final PlayerHudRightBinding getHudRightBinding() {
        PlayerHudRightBinding playerHudRightBinding = this.hudRightBinding;
        if (playerHudRightBinding != null) {
            return playerHudRightBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("hudRightBinding");
        return null;
    }

    public final void setHudRightBinding(PlayerHudRightBinding playerHudRightBinding) {
        Intrinsics.checkNotNullParameter(playerHudRightBinding, "<set-?>");
        this.hudRightBinding = playerHudRightBinding;
    }

    public final AnimatedVectorDrawableCompat getPlayToPause() {
        AnimatedVectorDrawableCompat animatedVectorDrawableCompat = this.playToPause;
        if (animatedVectorDrawableCompat != null) {
            return animatedVectorDrawableCompat;
        }
        Intrinsics.throwUninitializedPropertyAccessException("playToPause");
        return null;
    }

    public final void setPlayToPause(AnimatedVectorDrawableCompat animatedVectorDrawableCompat) {
        Intrinsics.checkNotNullParameter(animatedVectorDrawableCompat, "<set-?>");
        this.playToPause = animatedVectorDrawableCompat;
    }

    public final AnimatedVectorDrawableCompat getPauseToPlay() {
        AnimatedVectorDrawableCompat animatedVectorDrawableCompat = this.pauseToPlay;
        if (animatedVectorDrawableCompat != null) {
            return animatedVectorDrawableCompat;
        }
        Intrinsics.throwUninitializedPropertyAccessException("pauseToPlay");
        return null;
    }

    public final void setPauseToPlay(AnimatedVectorDrawableCompat animatedVectorDrawableCompat) {
        Intrinsics.checkNotNullParameter(animatedVectorDrawableCompat, "<set-?>");
        this.pauseToPlay = animatedVectorDrawableCompat;
    }

    private final View getHudBackground() {
        return (View) this.hudBackground$delegate.getValue();
    }

    private final View getHudRightBackground() {
        return (View) this.hudRightBackground$delegate.getValue();
    }

    public final boolean getSeekButtons() {
        return this.seekButtons;
    }

    public final void setSeekButtons(boolean z) {
        this.seekButtons = z;
    }

    public final boolean getHasPlaylist() {
        return this.hasPlaylist;
    }

    public final void setHasPlaylist(boolean z) {
        this.hasPlaylist = z;
    }

    public final boolean getEnableSubs() {
        return this.enableSubs;
    }

    public final void setEnableSubs(boolean z) {
        this.enableSubs = z;
    }

    public final boolean isHudBindingInitialized() {
        return this.hudBinding != null;
    }

    public final boolean isHudRightBindingInitialized() {
        return this.hudRightBinding != null;
    }

    public final boolean isPlaylistAdapterInitialized() {
        return this.playlistAdapter != null;
    }

    public final View getCloseButton() {
        View view = this.closeButton;
        if (view != null) {
            return view;
        }
        Intrinsics.throwUninitializedPropertyAccessException("closeButton");
        return null;
    }

    public final void setCloseButton(View view) {
        Intrinsics.checkNotNullParameter(view, "<set-?>");
        this.closeButton = view;
    }

    public final View getPlaylistContainer() {
        View view = this.playlistContainer;
        if (view != null) {
            return view;
        }
        Intrinsics.throwUninitializedPropertyAccessException("playlistContainer");
        return null;
    }

    public final void setPlaylistContainer(View view) {
        Intrinsics.checkNotNullParameter(view, "<set-?>");
        this.playlistContainer = view;
    }

    public final ImageView getHingeArrowRight() {
        return this.hingeArrowRight;
    }

    public final void setHingeArrowRight(ImageView imageView) {
        this.hingeArrowRight = imageView;
    }

    public final ImageView getHingeArrowLeft() {
        return this.hingeArrowLeft;
    }

    public final void setHingeArrowLeft(ImageView imageView) {
        this.hingeArrowLeft = imageView;
    }

    public final RecyclerView getPlaylist() {
        RecyclerView recyclerView = this.playlist;
        if (recyclerView != null) {
            return recyclerView;
        }
        Intrinsics.throwUninitializedPropertyAccessException(ArtworkProvider.PLAYLIST);
        return null;
    }

    public final void setPlaylist(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "<set-?>");
        this.playlist = recyclerView;
    }

    public final TextInputLayout getPlaylistSearchText() {
        TextInputLayout textInputLayout = this.playlistSearchText;
        if (textInputLayout != null) {
            return textInputLayout;
        }
        Intrinsics.throwUninitializedPropertyAccessException("playlistSearchText");
        return null;
    }

    public final void setPlaylistSearchText(TextInputLayout textInputLayout) {
        Intrinsics.checkNotNullParameter(textInputLayout, "<set-?>");
        this.playlistSearchText = textInputLayout;
    }

    public final PlaylistAdapter getPlaylistAdapter() {
        PlaylistAdapter playlistAdapter2 = this.playlistAdapter;
        if (playlistAdapter2 != null) {
            return playlistAdapter2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("playlistAdapter");
        return null;
    }

    public final void setPlaylistAdapter(PlaylistAdapter playlistAdapter2) {
        Intrinsics.checkNotNullParameter(playlistAdapter2, "<set-?>");
        this.playlistAdapter = playlistAdapter2;
    }

    public final FoldingFeature getFoldingFeature() {
        return this.foldingFeature;
    }

    public final void setFoldingFeature(FoldingFeature foldingFeature2) {
        this.foldingFeature = foldingFeature2;
        manageHinge();
    }

    public final void manageHinge() {
        MediaPlayer mediaplayer;
        MediaPlayer mediaplayer2;
        PlaybackService service = this.player.getService();
        int i = 0;
        if (!(service == null || (mediaplayer2 = service.getMediaplayer()) == null)) {
            mediaplayer2.setUseOrientationFromBounds(false);
        }
        resetHingeLayout();
        if (this.foldingFeature != null && ((SharedPreferences) Settings.INSTANCE.getInstance(this.player)).getBoolean(SettingsKt.ALLOW_FOLD_AUTO_LAYOUT, true)) {
            FoldingFeature foldingFeature2 = this.foldingFeature;
            Intrinsics.checkNotNull(foldingFeature2);
            if (Intrinsics.areEqual((Object) foldingFeature2.getOcclusionType(), (Object) FoldingFeature.OcclusionType.FULL) && Intrinsics.areEqual((Object) foldingFeature2.getOrientation(), (Object) FoldingFeature.Orientation.VERTICAL)) {
                boolean z = ((SharedPreferences) Settings.INSTANCE.getInstance(this.player)).getBoolean(SettingsKt.HINGE_ON_RIGHT, true);
                ImageView imageView = this.hingeArrowLeft;
                int i2 = 8;
                if (imageView != null) {
                    imageView.setVisibility((!z || this.hudBinding == null) ? 8 : 0);
                }
                ImageView imageView2 = this.hingeArrowRight;
                if (imageView2 != null) {
                    if (!z && this.hudBinding != null) {
                        i2 = 0;
                    }
                    imageView2.setVisibility(i2);
                }
                int screenWidth = KextensionsKt.getScreenWidth(this.player) - foldingFeature2.getBounds().right;
                View[] viewArr = {getPlayerUiContainer(), getHudBackground(), getHudRightBackground(), getPlaylistContainer()};
                while (i < 4) {
                    View view = viewArr[i];
                    if (view != null) {
                        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
                        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
                        layoutParams2.width = screenWidth;
                        layoutParams2.gravity = (layoutParams2.gravity & 112) | (z ? GravityCompat.END : GravityCompat.START);
                        view.setLayoutParams(layoutParams2);
                    }
                    i++;
                }
                showHingeSnackIfNeeded();
            } else if (!Intrinsics.areEqual((Object) foldingFeature2.getState(), (Object) FoldingFeature.State.HALF_OPENED)) {
            } else {
                if (!Intrinsics.areEqual((Object) foldingFeature2.getOcclusionType(), (Object) FoldingFeature.OcclusionType.NONE) || !Intrinsics.areEqual((Object) foldingFeature2.getOrientation(), (Object) FoldingFeature.Orientation.VERTICAL)) {
                    VLCVideoLayout videoLayout = this.player.getVideoLayout();
                    Intrinsics.checkNotNull(videoLayout);
                    ViewGroup.LayoutParams layoutParams3 = videoLayout.getLayoutParams();
                    Intrinsics.checkNotNull(layoutParams3, "null cannot be cast to non-null type android.view.ViewGroup.LayoutParams");
                    int i3 = foldingFeature2.getBounds().top;
                    layoutParams3.height = i3;
                    VLCVideoLayout videoLayout2 = this.player.getVideoLayout();
                    Intrinsics.checkNotNull(videoLayout2);
                    videoLayout2.setLayoutParams(layoutParams3);
                    PlaybackService service2 = this.player.getService();
                    if (!(service2 == null || (mediaplayer = service2.getMediaplayer()) == null)) {
                        mediaplayer.setUseOrientationFromBounds(true);
                    }
                    View findViewById = this.player.findViewById(R.id.player_surface_frame);
                    Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
                    for (View requestLayout : ViewGroupKt.getChildren((ViewGroup) findViewById)) {
                        requestLayout.requestLayout();
                    }
                    View[] viewArr2 = {getPlayerUiContainer(), getPlaylistContainer()};
                    for (int i4 = 0; i4 < 2; i4++) {
                        View view2 = viewArr2[i4];
                        ViewGroup.LayoutParams layoutParams4 = view2.getLayoutParams();
                        Intrinsics.checkNotNull(layoutParams4, "null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
                        FrameLayout.LayoutParams layoutParams5 = (FrameLayout.LayoutParams) layoutParams4;
                        layoutParams5.height = i3;
                        layoutParams5.gravity = 80;
                        view2.setLayoutParams(layoutParams5);
                    }
                    View[] viewArr3 = {getHudBackground(), getHudRightBackground()};
                    while (i < 2) {
                        View view3 = viewArr3[i];
                        if (view3 != null) {
                            KotlinExtensionsKt.setGone(view3);
                        }
                        i++;
                    }
                    showHingeSnackIfNeeded();
                }
            }
        }
    }

    private final void showHingeSnackIfNeeded() {
        if (!this.hingeSnackShown) {
            UiTools uiTools = UiTools.INSTANCE;
            VideoPlayerActivity videoPlayerActivity = this.player;
            String string = videoPlayerActivity.getString(R.string.fold_optimized);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            UiTools.snackerConfirm$default(uiTools, videoPlayerActivity, string, false, R.string.undo, new VideoPlayerOverlayDelegate$showHingeSnackIfNeeded$1(this), 4, (Object) null);
            this.hingeSnackShown = true;
        }
    }

    private final void resetHingeLayout() {
        View[] viewArr = {getPlayerUiContainer(), getHudBackground(), getHudRightBackground(), getPlaylistContainer()};
        for (int i = 0; i < 4; i++) {
            View view = viewArr[i];
            if (view != null) {
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.LayoutParams");
                layoutParams.width = -1;
                view.setLayoutParams(layoutParams);
            }
        }
        View[] viewArr2 = {getPlayerUiContainer(), getPlaylistContainer()};
        for (int i2 = 0; i2 < 2; i2++) {
            View view2 = viewArr2[i2];
            ViewGroup.LayoutParams layoutParams2 = view2.getLayoutParams();
            Intrinsics.checkNotNull(layoutParams2, "null cannot be cast to non-null type android.view.ViewGroup.LayoutParams");
            layoutParams2.height = -1;
            view2.setLayoutParams(layoutParams2);
        }
        if (this.hudBinding != null) {
            View[] viewArr3 = {getHudBackground(), getHudRightBackground()};
            for (int i3 = 0; i3 < 2; i3++) {
                View view3 = viewArr3[i3];
                if (view3 != null) {
                    KotlinExtensionsKt.setVisible(view3);
                }
            }
        }
        ImageView imageView = this.hingeArrowLeft;
        if (imageView != null) {
            imageView.setVisibility(8);
        }
        ImageView imageView2 = this.hingeArrowRight;
        if (imageView2 != null) {
            imageView2.setVisibility(8);
        }
        VLCVideoLayout videoLayout = this.player.getVideoLayout();
        Intrinsics.checkNotNull(videoLayout);
        ViewGroup.LayoutParams layoutParams3 = videoLayout.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams3, "null cannot be cast to non-null type android.view.ViewGroup.LayoutParams");
        layoutParams3.height = -1;
        VLCVideoLayout videoLayout2 = this.player.getVideoLayout();
        Intrinsics.checkNotNull(videoLayout2);
        videoLayout2.setLayoutParams(layoutParams3);
        View findViewById = this.player.findViewById(R.id.player_surface_frame);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        for (View requestLayout : ViewGroupKt.getChildren((ViewGroup) findViewById)) {
            requestLayout.requestLayout();
        }
    }

    public final void showTracks() {
        UiTools.INSTANCE.showVideoTrack(this.player, new VideoPlayerOverlayDelegate$showTracks$1(this), new VideoPlayerOverlayDelegate$showTracks$2(this));
    }

    public static /* synthetic */ void showInfo$default(VideoPlayerOverlayDelegate videoPlayerOverlayDelegate, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i3 = -1;
        }
        videoPlayerOverlayDelegate.showInfo(i, i2, i3);
    }

    public final void showInfo(int i, int i2, int i3) {
        String str;
        String string = this.player.getString(i);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        if (i3 == -1) {
            str = "";
        } else {
            str = this.player.getString(i3);
            Intrinsics.checkNotNullExpressionValue(str, "getString(...)");
        }
        showInfo(string, i2, str);
    }

    public static /* synthetic */ void showInfo$default(VideoPlayerOverlayDelegate videoPlayerOverlayDelegate, String str, int i, String str2, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            str2 = "";
        }
        videoPlayerOverlayDelegate.showInfo(str, i, str2);
    }

    public final void showInfo(String str, int i, String str2) {
        Intrinsics.checkNotNullParameter(str, "text");
        Intrinsics.checkNotNullParameter(str2, "subText");
        if (!this.player.isInPictureInPictureMode()) {
            initInfoOverlay();
            KotlinExtensionsKt.setVisible(this.overlayInfo);
            KotlinExtensionsKt.setVisible(this.info);
            TextView textView = this.info;
            if (textView != null) {
                textView.setText(str);
            }
            CharSequence charSequence = str2;
            if (!StringsKt.isBlank(charSequence)) {
                TextView textView2 = this.subinfo;
                if (textView2 != null) {
                    textView2.setText(charSequence);
                }
                KotlinExtensionsKt.setVisible(this.subinfo);
            } else {
                KotlinExtensionsKt.setGone(this.subinfo);
            }
            this.player.getHandler().removeMessages(2);
            this.player.getHandler().sendEmptyMessageDelayed(2, (long) i);
            View rootView = this.player.getRootView();
            if (rootView != null) {
                rootView.announceForAccessibility(str + '.' + str2);
            }
        }
    }

    public final void hideInfo() {
        this.player.getHandler().sendEmptyMessage(2);
    }

    public final void fadeOutInfo(View view) {
        if (view != null && view.getVisibility() == 0) {
            view.startAnimation(AnimationUtils.loadAnimation(this.player, 17432577));
            KotlinExtensionsKt.setInvisible(view);
        }
    }

    public final void initInfoOverlay() {
        ViewStubCompat viewStubCompat = (ViewStubCompat) this.player.findViewById(R.id.player_info_stub);
        if (viewStubCompat != null) {
            KotlinExtensionsKt.setVisible(viewStubCompat);
            this.info = (TextView) this.player.findViewById(R.id.player_overlay_textinfo);
            this.subinfo = (TextView) this.player.findViewById(R.id.player_overlay_subtextinfo);
            this.overlayInfo = this.player.findViewById(R.id.player_overlay_info);
        }
    }

    public final void showBrightnessBar(int i) {
        this.player.getHandler().sendEmptyMessage(13);
        ViewStubCompat viewStubCompat = (ViewStubCompat) this.player.findViewById(R.id.player_brightness_stub);
        if (viewStubCompat != null) {
            KotlinExtensionsKt.setVisible(viewStubCompat);
        }
        View findViewById = this.player.findViewById(R.id.player_overlay_brightness);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.playerOverlayBrightness = (ConstraintLayout) findViewById;
        View findViewById2 = this.player.findViewById(R.id.brightness_value_text);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.brightnessValueText = (TextView) findViewById2;
        View findViewById3 = this.player.findViewById(R.id.playerBrightnessProgress);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.playerBrightnessProgress = (PlayerProgress) findViewById3;
        ConstraintLayout constraintLayout = this.playerOverlayBrightness;
        ConstraintLayout constraintLayout2 = null;
        if (constraintLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playerOverlayBrightness");
            constraintLayout = null;
        }
        KotlinExtensionsKt.setVisible(constraintLayout);
        TextView textView = this.brightnessValueText;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("brightnessValueText");
            textView = null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        sb.append('%');
        textView.setText(sb.toString());
        PlayerProgress playerProgress = this.playerBrightnessProgress;
        if (playerProgress == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playerBrightnessProgress");
            playerProgress = null;
        }
        playerProgress.setValue(i);
        ConstraintLayout constraintLayout3 = this.playerOverlayBrightness;
        if (constraintLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playerOverlayBrightness");
        } else {
            constraintLayout2 = constraintLayout3;
        }
        KotlinExtensionsKt.setVisible(constraintLayout2);
        this.player.getHandler().removeMessages(12);
        this.player.getHandler().sendEmptyMessageDelayed(12, 1000);
        dimStatusBar(true);
    }

    public final void showVolumeBar(int i) {
        this.player.getHandler().sendEmptyMessage(12);
        ViewStubCompat viewStubCompat = (ViewStubCompat) this.player.findViewById(R.id.player_volume_stub);
        if (viewStubCompat != null) {
            KotlinExtensionsKt.setVisible(viewStubCompat);
        }
        View findViewById = this.player.findViewById(R.id.player_overlay_volume);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.playerOverlayVolume = (ConstraintLayout) findViewById;
        View findViewById2 = this.player.findViewById(R.id.volume_value_text);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.volumeValueText = (TextView) findViewById2;
        View findViewById3 = this.player.findViewById(R.id.playerVolumeProgress);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.playerVolumeProgress = (PlayerProgress) findViewById3;
        TextView textView = this.volumeValueText;
        ConstraintLayout constraintLayout = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("volumeValueText");
            textView = null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        sb.append('%');
        textView.setText(sb.toString());
        PlayerProgress playerProgress = this.playerVolumeProgress;
        if (playerProgress == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playerVolumeProgress");
            playerProgress = null;
        }
        playerProgress.setDouble(this.player.isAudioBoostEnabled$vlc_android_release());
        PlayerProgress playerProgress2 = this.playerVolumeProgress;
        if (playerProgress2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playerVolumeProgress");
            playerProgress2 = null;
        }
        playerProgress2.setValue(i);
        ConstraintLayout constraintLayout2 = this.playerOverlayVolume;
        if (constraintLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playerOverlayVolume");
        } else {
            constraintLayout = constraintLayout2;
        }
        KotlinExtensionsKt.setVisible(constraintLayout);
        this.player.getHandler().removeMessages(13);
        this.player.getHandler().sendEmptyMessageDelayed(13, 1000);
        dimStatusBar(true);
        PlaybackService service = this.player.getService();
        if (service != null) {
            resetSleepTimer(service);
        }
    }

    public final void dimStatusBar(boolean z) {
        int i;
        int i2;
        if (!this.player.isNavMenu()) {
            if (z || this.player.isLocked()) {
                this.player.getWindow().addFlags(1024);
                i = (AndroidUtil.isKitKatOrLater ? 3328 : MediaDiscoverer.Event.Started) | 4;
                i2 = 515;
            } else {
                this.player.getWindow().clearFlags(1024);
                i = 1792;
                i2 = 0;
            }
            getPlayerUiContainer().setPadding(0, 0, 0, 0);
            getPlayerUiContainer().setFitsSystemWindows(!this.player.isLocked());
            if (AndroidDevices.INSTANCE.getHasNavBar()) {
                i |= i2;
            }
            this.player.getWindow().getDecorView().setSystemUiVisibility(i);
        }
    }

    public static /* synthetic */ void showOverlay$default(VideoPlayerOverlayDelegate videoPlayerOverlayDelegate, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        videoPlayerOverlayDelegate.showOverlay(z);
    }

    public final void showOverlay(boolean z) {
        if (z) {
            this.overlayTimeout = 0;
        }
        showOverlayTimeout(0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0085  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void showOverlayTimeout(int r14) {
        /*
            r13 = this;
            org.videolan.vlc.gui.video.VideoPlayerActivity r0 = r13.player
            org.videolan.vlc.PlaybackService r0 = r0.getService()
            if (r0 == 0) goto L_0x0146
            org.videolan.vlc.gui.video.VideoPlayerActivity r1 = r13.player
            org.videolan.vlc.gui.video.VideoTipsDelegate r1 = r1.getTipsDelegate()
            org.videolan.vlc.gui.video.VideoPlayerTipsStep r1 = r1.getCurrentTip()
            if (r1 == 0) goto L_0x0015
            return
        L_0x0015:
            org.videolan.vlc.gui.video.VideoPlayerActivity r1 = r13.player
            boolean r1 = r1.isInPictureInPictureMode()
            if (r1 == 0) goto L_0x001e
            return
        L_0x001e:
            r13.initOverlay()
            org.videolan.vlc.databinding.PlayerHudBinding r1 = r13.hudBinding
            if (r1 != 0) goto L_0x0026
            return
        L_0x0026:
            org.videolan.vlc.media.PlaylistManager r1 = r0.getPlaylistManager()
            androidx.lifecycle.MutableLiveData r1 = r1.getVideoStatsOn()
            java.lang.Object r1 = r1.getValue()
            r2 = 1
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r2)
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r3)
            r3 = -1
            if (r1 == 0) goto L_0x0040
        L_0x003e:
            r14 = -1
            goto L_0x0075
        L_0x0040:
            org.videolan.vlc.gui.video.VideoPlayerActivity r1 = r13.player
            android.app.Activity r1 = (android.app.Activity) r1
            boolean r1 = org.videolan.vlc.util.AccessibilityHelperKt.isTalkbackIsEnabled(r1)
            if (r1 == 0) goto L_0x004b
            goto L_0x003e
        L_0x004b:
            org.videolan.tools.Settings r1 = org.videolan.tools.Settings.INSTANCE
            int r1 = r1.getVideoHudDelay()
            if (r1 != r3) goto L_0x0054
            goto L_0x003e
        L_0x0054:
            boolean r1 = r13.isBookmarkShown()
            if (r1 == 0) goto L_0x005b
            goto L_0x003e
        L_0x005b:
            if (r14 == 0) goto L_0x005e
            goto L_0x0075
        L_0x005e:
            boolean r14 = r0.isPlaying()
            if (r14 == 0) goto L_0x003e
            org.videolan.tools.Settings r14 = org.videolan.tools.Settings.INSTANCE
            int r14 = r14.getVideoHudDelay()
            if (r14 != r3) goto L_0x006d
            goto L_0x003e
        L_0x006d:
            org.videolan.tools.Settings r14 = org.videolan.tools.Settings.INSTANCE
            int r14 = r14.getVideoHudDelay()
            int r14 = r14 * 1000
        L_0x0075:
            r13.overlayTimeout = r14
            org.videolan.vlc.gui.video.VideoPlayerActivity r14 = r13.player
            boolean r14 = r14.isNavMenu()
            if (r14 == 0) goto L_0x0085
            org.videolan.vlc.gui.video.VideoPlayerActivity r14 = r13.player
            r14.setShowing(r2)
            return
        L_0x0085:
            org.videolan.vlc.gui.video.VideoPlayerActivity r14 = r13.player
            boolean r14 = r14.isShowing()
            if (r14 != 0) goto L_0x0120
            org.videolan.vlc.gui.video.VideoPlayerActivity r14 = r13.player
            r14.setShowing(r2)
            org.videolan.vlc.gui.video.VideoPlayerActivity r14 = r13.player
            boolean r14 = r14.isLocked()
            if (r14 != 0) goto L_0x009d
            r13.showControls(r2)
        L_0x009d:
            boolean r14 = r13.isBookmarkShown()
            r1 = 0
            if (r14 != 0) goto L_0x00a7
            r13.dimStatusBar(r1)
        L_0x00a7:
            r14 = 2
            android.view.View[] r3 = new android.view.View[r14]
            org.videolan.vlc.databinding.PlayerHudBinding r4 = r13.getHudBinding()
            androidx.constraintlayout.widget.ConstraintLayout r4 = r4.progressOverlay
            r3[r1] = r4
            android.view.View r4 = r13.getHudBackground()
            r3[r2] = r4
            r4 = 100
            int r5 = org.videolan.tools.KotlinExtensionsKt.getDp(r4)
            float r5 = (float) r5
            org.videolan.vlc.gui.video.VideoPlayerOverlayDelegate$showOverlayTimeout$1$1 r6 = new org.videolan.vlc.gui.video.VideoPlayerOverlayDelegate$showOverlayTimeout$1$1
            r6.<init>(r13)
            kotlin.jvm.functions.Function0 r6 = (kotlin.jvm.functions.Function0) r6
            r13.enterAnimate(r3, r5, r6)
            android.view.View[] r8 = new android.view.View[r14]
            org.videolan.vlc.databinding.PlayerHudRightBinding r14 = r13.getHudRightBinding()
            androidx.constraintlayout.widget.ConstraintLayout r14 = r14.hudRightOverlay
            r8[r1] = r14
            android.view.View r14 = r13.getHudRightBackground()
            r8[r2] = r14
            int r14 = org.videolan.tools.KotlinExtensionsKt.getDp(r4)
            float r14 = (float) r14
            float r9 = -r14
            r11 = 4
            r12 = 0
            r10 = 0
            r7 = r13
            enterAnimate$default(r7, r8, r9, r10, r11, r12)
            android.widget.ImageView r14 = r13.hingeArrowLeft
            r1 = 1065353216(0x3f800000, float:1.0)
            if (r14 == 0) goto L_0x00f5
            android.view.ViewPropertyAnimator r14 = r14.animate()
            if (r14 == 0) goto L_0x00f5
            r14.alpha(r1)
        L_0x00f5:
            android.widget.ImageView r14 = r13.hingeArrowRight
            if (r14 == 0) goto L_0x0102
            android.view.ViewPropertyAnimator r14 = r14.animate()
            if (r14 == 0) goto L_0x0102
            r14.alpha(r1)
        L_0x0102:
            org.videolan.vlc.gui.video.VideoPlayerActivity r14 = r13.player
            org.videolan.libvlc.util.DisplayManager r14 = r14.getDisplayManager()
            boolean r14 = r14.isPrimary()
            if (r14 != 0) goto L_0x0113
            android.view.View r14 = r13.overlayBackground
            org.videolan.tools.KotlinExtensionsKt.setVisible(r14)
        L_0x0113:
            r13.updateOverlayPausePlay(r2)
            org.videolan.vlc.gui.video.VideoPlayerActivity r14 = r13.player
            android.os.Handler r14 = r14.getHandler()
            r14.removeMessages(r2)
            goto L_0x0143
        L_0x0120:
            org.videolan.vlc.gui.video.VideoPlayerActivity r14 = r13.player
            android.os.Handler r14 = r14.getHandler()
            r14.removeMessages(r2)
            int r14 = r13.overlayTimeout
            if (r14 == r3) goto L_0x0143
            org.videolan.vlc.gui.video.VideoPlayerActivity r14 = r13.player
            android.os.Handler r14 = r14.getHandler()
            org.videolan.vlc.gui.video.VideoPlayerActivity r1 = r13.player
            android.os.Handler r1 = r1.getHandler()
            android.os.Message r1 = r1.obtainMessage(r2)
            int r2 = r13.overlayTimeout
            long r2 = (long) r2
            r14.sendMessageDelayed(r1, r2)
        L_0x0143:
            r13.resetSleepTimer(r0)
        L_0x0146:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.video.VideoPlayerOverlayDelegate.showOverlayTimeout(int):void");
    }

    private final void resetSleepTimer(PlaybackService playbackService) {
        if (playbackService.getResetOnInteraction()) {
            Calendar instance = Calendar.getInstance();
            instance.setTimeInMillis(System.currentTimeMillis() + playbackService.getSleepTimerInterval());
            PlaybackService.Companion.getPlayerSleepTime().setValue(instance);
        }
    }

    public static /* synthetic */ void updateOverlayPausePlay$default(VideoPlayerOverlayDelegate videoPlayerOverlayDelegate, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        videoPlayerOverlayDelegate.updateOverlayPausePlay(z);
    }

    public final void updateOverlayPausePlay(boolean z) {
        PlaybackService service;
        int i;
        if (this.hudBinding != null && (service = this.player.getService()) != null) {
            if (service.isPausable()) {
                if (z) {
                    ImageView imageView = getHudBinding().playerOverlayPlay;
                    if (service.isPlaying()) {
                        i = R.drawable.ic_pause_player;
                    } else {
                        i = R.drawable.ic_play_player;
                    }
                    imageView.setImageResource(i);
                } else {
                    AnimatedVectorDrawableCompat playToPause2 = service.isPlaying() ? getPlayToPause() : getPauseToPlay();
                    getHudBinding().playerOverlayPlay.setImageDrawable(playToPause2);
                    if (service.isPlaying() != this.wasPlaying) {
                        playToPause2.start();
                    }
                }
                getHudBinding().playerOverlayPlay.setContentDescription(this.player.getString(service.isPlaying() ? R.string.pause : R.string.play));
                this.wasPlaying = service.isPlaying();
            }
            getHudBinding().playerOverlayPlay.requestFocus();
            if (this.playlistAdapter != null) {
                getPlaylistAdapter().setCurrentlyPlaying(service.isPlaying());
            }
        }
    }

    static /* synthetic */ void enterAnimate$default(VideoPlayerOverlayDelegate videoPlayerOverlayDelegate, View[] viewArr, float f, Function0 function0, int i, Object obj) {
        if ((i & 4) != 0) {
            function0 = null;
        }
        videoPlayerOverlayDelegate.enterAnimate(viewArr, f, function0);
    }

    /* access modifiers changed from: private */
    public static final void enterAnimate$lambda$14$lambda$13(Function0 function0) {
        if (function0 != null) {
            function0.invoke();
        }
    }

    private final void initOverlay() {
        PlaybackService service = this.player.getService();
        if (service != null) {
            ViewStubCompat viewStubCompat = (ViewStubCompat) this.player.findViewById(R.id.player_hud_right_stub);
            if (viewStubCompat != null) {
                Intrinsics.checkNotNull(viewStubCompat);
                KotlinExtensionsKt.setVisible(viewStubCompat);
                PlayerHudRightBinding playerHudRightBinding = (PlayerHudRightBinding) DataBindingUtil.bind(this.player.findViewById(R.id.hud_right_overlay));
                if (playerHudRightBinding != null) {
                    Intrinsics.checkNotNull(playerHudRightBinding);
                    setHudRightBinding(playerHudRightBinding);
                    if (!this.player.isBenchmark() && this.player.getEnableCloneMode() && !this.player.getSettings().contains("enable_clone_mode")) {
                        UiTools uiTools = UiTools.INSTANCE;
                        VideoPlayerActivity videoPlayerActivity = this.player;
                        String string = videoPlayerActivity.getString(R.string.video_save_clone_mode);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                        UiTools.snackerConfirm$default(uiTools, videoPlayerActivity, string, false, 0, new VideoPlayerOverlayDelegate$initOverlay$1$1$1(this), 12, (Object) null);
                    }
                } else {
                    return;
                }
            }
            ViewStubCompat viewStubCompat2 = (ViewStubCompat) this.player.findViewById(R.id.player_hud_stub);
            if (viewStubCompat2 != null) {
                this.seekButtons = this.player.getSettings().getBoolean(SettingsKt.ENABLE_SEEK_BUTTONS, false);
                KotlinExtensionsKt.setVisible(viewStubCompat2);
                PlayerHudBinding playerHudBinding = (PlayerHudBinding) DataBindingUtil.bind(this.player.findViewById(R.id.progress_overlay));
                if (playerHudBinding != null) {
                    Intrinsics.checkNotNull(playerHudBinding);
                    setHudBinding(playerHudBinding);
                    getHudBinding().setPlayer(this.player);
                    getHudBinding().setProgress(service.getPlaylistManager().getPlayer().getProgress());
                    View findViewById = getHudBinding().abRepeatContainer.findViewById(R.id.ab_repeat_add_marker);
                    Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
                    this.abRepeatAddMarker = (Button) findViewById;
                    service.getPlaylistManager().getAbRepeat().observe(this.player, new VideoPlayerOverlayDelegate$sam$androidx_lifecycle_Observer$0(new VideoPlayerOverlayDelegate$initOverlay$1$2(this, service)));
                    service.getPlaylistManager().getAbRepeatOn().observe(this.player, new VideoPlayerOverlayDelegate$sam$androidx_lifecycle_Observer$0(new VideoPlayerOverlayDelegate$initOverlay$1$3(this, service)));
                    service.getPlaylistManager().getDelayValue().observe(this.player, new VideoPlayerOverlayDelegate$sam$androidx_lifecycle_Observer$0(new VideoPlayerOverlayDelegate$initOverlay$1$4(this, service)));
                    service.getPlaylistManager().getVideoStatsOn().observe(this.player, new VideoPlayerOverlayDelegate$sam$androidx_lifecycle_Observer$0(new VideoPlayerOverlayDelegate$initOverlay$1$5(this)));
                    getHudBinding().statsClose.setOnClickListener(new VideoPlayerOverlayDelegate$$ExternalSyntheticLambda5(service));
                    getHudBinding().setLifecycleOwner(this.player);
                    updateOrientationIcon();
                    this.overlayBackground = this.player.findViewById(R.id.player_overlay_background);
                    if (!AndroidDevices.INSTANCE.isChromeBook() && !this.player.isTv() && this.player.getSettings().getBoolean("enable_casting", true)) {
                        PlaybackService.Companion.getRenderer().observe(this.player, new VideoPlayerOverlayDelegate$sam$androidx_lifecycle_Observer$0(new VideoPlayerOverlayDelegate$initOverlay$1$7(this)));
                        RendererDelegate.INSTANCE.getRenderers().observe(this.player, new VideoPlayerOverlayDelegate$sam$androidx_lifecycle_Observer$0(new VideoPlayerOverlayDelegate$initOverlay$1$8(this)));
                    }
                    TextView textView = getHudRightBinding().playerOverlayTitle;
                    MediaWrapper currentMediaWrapper = service.getCurrentMediaWrapper();
                    textView.setText(currentMediaWrapper != null ? currentMediaWrapper.getTitle() : null);
                    manageTitleConstraints();
                    updateTitleConstraints();
                    updateHudMargins();
                    initSeekButton();
                    resetHudLayout();
                    updateOverlayPausePlay(true);
                    updateSeekable(service.isSeekable());
                    updatePausable(service.isPausable());
                    this.player.updateNavStatus();
                    setListeners(true);
                    initPlaylistUi();
                    updateScreenshotButton();
                    if (this.foldingFeature != null) {
                        manageHinge();
                    }
                }
            } else if (this.hudBinding != null) {
                getHudBinding().setProgress(service.getPlaylistManager().getPlayer().getProgress());
                getHudBinding().setLifecycleOwner(this.player);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void initOverlay$lambda$18$lambda$17(PlaybackService playbackService, View view) {
        Intrinsics.checkNotNullParameter(playbackService, "$service");
        playbackService.getPlaylistManager().getVideoStatsOn().postValue(false);
    }

    public final void updateSeekable(boolean z) {
        int i;
        int i2;
        if (this.hudBinding != null) {
            getHudBinding().playerOverlayRewind.setEnabled(z);
            ImageView imageView = getHudBinding().playerOverlayRewind;
            if (z) {
                i = R.drawable.ic_player_rewind_10;
            } else {
                i = R.drawable.ic_player_rewind_10_disabled;
            }
            imageView.setImageResource(i);
            getHudBinding().playerOverlayForward.setEnabled(z);
            ImageView imageView2 = getHudBinding().playerOverlayForward;
            if (z) {
                i2 = R.drawable.ic_player_forward_10;
            } else {
                i2 = R.drawable.ic_player_forward_10_disabled;
            }
            imageView2.setImageResource(i2);
            if (!this.player.isLocked()) {
                getHudBinding().playerOverlaySeekbar.setEnabled(z);
            }
        }
    }

    public final void setListeners(boolean z) {
        VideoPlayerActivity videoPlayerActivity = null;
        if (this.hudBinding != null) {
            getHudBinding().playerOverlaySeekbar.setOnSeekBarChangeListener(z ? this.player.getSeekListener() : null);
            getHudBinding().abRepeatReset.setOnClickListener(this.player);
            getHudBinding().abRepeatStop.setOnClickListener(this.player);
            Button button = this.abRepeatAddMarker;
            if (button == null) {
                Intrinsics.throwUninitializedPropertyAccessException("abRepeatAddMarker");
                button = null;
            }
            button.setOnClickListener(this.player);
            getHudBinding().orientationToggle.setOnClickListener(z ? this.player : null);
            getHudBinding().orientationToggle.setOnLongClickListener(z ? this.player : null);
            getHudBinding().swipeToUnlock.setOnStartTouchingListener(new VideoPlayerOverlayDelegate$setListeners$1(this));
            getHudBinding().swipeToUnlock.setOnStopTouchingListener(new VideoPlayerOverlayDelegate$setListeners$2(this));
            getHudBinding().swipeToUnlock.setOnUnlockListener(new VideoPlayerOverlayDelegate$setListeners$3(this));
        }
        if (this.hudRightBinding != null) {
            getHudRightBinding().playerOverlayNavmenu.setOnClickListener(z ? this.player : null);
            UiTools uiTools = UiTools.INSTANCE;
            View view = getHudRightBinding().videoRenderer;
            if (z) {
                videoPlayerActivity = this.player;
            }
            uiTools.setViewOnClickListener(view, videoPlayerActivity);
            getHudRightBinding().playbackSpeedQuickAction.setOnLongClickListener(new VideoPlayerOverlayDelegate$$ExternalSyntheticLambda10(this));
            getHudRightBinding().sleepQuickAction.setOnLongClickListener(new VideoPlayerOverlayDelegate$$ExternalSyntheticLambda0(this));
            getHudRightBinding().audioDelayQuickAction.setOnLongClickListener(new VideoPlayerOverlayDelegate$$ExternalSyntheticLambda1(this));
            getHudRightBinding().spuDelayQuickAction.setOnLongClickListener(new VideoPlayerOverlayDelegate$$ExternalSyntheticLambda2(this));
            getHudRightBinding().quickActionsContainer.setOnTouchListener(new VideoPlayerOverlayDelegate$$ExternalSyntheticLambda3(this));
        }
    }

    /* access modifiers changed from: private */
    public static final boolean setListeners$lambda$19(VideoPlayerOverlayDelegate videoPlayerOverlayDelegate, View view) {
        Intrinsics.checkNotNullParameter(videoPlayerOverlayDelegate, "this$0");
        PlaybackService service = videoPlayerOverlayDelegate.player.getService();
        if (service != null) {
            service.setRate(1.0f, true);
        }
        videoPlayerOverlayDelegate.showControls(true);
        return true;
    }

    /* access modifiers changed from: private */
    public static final boolean setListeners$lambda$20(VideoPlayerOverlayDelegate videoPlayerOverlayDelegate, View view) {
        Intrinsics.checkNotNullParameter(videoPlayerOverlayDelegate, "this$0");
        PlaybackService service = videoPlayerOverlayDelegate.player.getService();
        if (service != null) {
            service.setSleepTimer((Calendar) null);
        }
        videoPlayerOverlayDelegate.showControls(true);
        return true;
    }

    /* access modifiers changed from: private */
    public static final boolean setListeners$lambda$21(VideoPlayerOverlayDelegate videoPlayerOverlayDelegate, View view) {
        Intrinsics.checkNotNullParameter(videoPlayerOverlayDelegate, "this$0");
        PlaybackService service = videoPlayerOverlayDelegate.player.getService();
        if (service != null) {
            service.setAudioDelay(0);
        }
        videoPlayerOverlayDelegate.showControls(true);
        return true;
    }

    /* access modifiers changed from: private */
    public static final boolean setListeners$lambda$22(VideoPlayerOverlayDelegate videoPlayerOverlayDelegate, View view) {
        Intrinsics.checkNotNullParameter(videoPlayerOverlayDelegate, "this$0");
        PlaybackService service = videoPlayerOverlayDelegate.player.getService();
        if (service != null) {
            service.setSpuDelay(0);
        }
        videoPlayerOverlayDelegate.showControls(true);
        return true;
    }

    /* access modifiers changed from: private */
    public static final boolean setListeners$lambda$23(VideoPlayerOverlayDelegate videoPlayerOverlayDelegate, View view, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(videoPlayerOverlayDelegate, "this$0");
        showOverlay$default(videoPlayerOverlayDelegate, false, 1, (Object) null);
        return false;
    }

    public final void updatePausable(boolean z) {
        if (this.hudBinding != null) {
            getHudBinding().playerOverlayPlay.setEnabled(z);
            if (!z) {
                getHudBinding().playerOverlayPlay.setImageResource(R.drawable.ic_play_player_disabled);
            }
        }
    }

    public final void resetHudLayout() {
        if (this.hudBinding != null && !this.player.isTv() && !AndroidDevices.INSTANCE.isChromeBook()) {
            KotlinExtensionsKt.setVisible(getHudBinding().orientationToggle);
        }
    }

    private final void initSeekButton() {
        if (this.hudBinding != null) {
            getHudBinding().playerOverlayRewind.setOnClickListener(this.player);
            getHudBinding().playerOverlayForward.setOnClickListener(this.player);
            getHudBinding().playerOverlayRewind.setOnLongClickListener(this.player);
            getHudBinding().playerOverlayForward.setOnLongClickListener(this.player);
            ImageView imageView = getHudBinding().playerOverlayRewind;
            VideoPlayerActivity videoPlayerActivity = this.player;
            Lifecycle lifecycle = videoPlayerActivity.getLifecycle();
            Intrinsics.checkNotNullExpressionValue(lifecycle, "<get-lifecycle>(...)");
            imageView.setOnKeyListener(new OnRepeatListenerKey(0, 0, 0, videoPlayerActivity, lifecycle, 7, (DefaultConstructorMarker) null));
            ImageView imageView2 = getHudBinding().playerOverlayForward;
            VideoPlayerActivity videoPlayerActivity2 = this.player;
            Lifecycle lifecycle2 = videoPlayerActivity2.getLifecycle();
            Intrinsics.checkNotNullExpressionValue(lifecycle2, "<get-lifecycle>(...)");
            imageView2.setOnKeyListener(new OnRepeatListenerKey(0, 0, 0, videoPlayerActivity2, lifecycle2, 7, (DefaultConstructorMarker) null));
        }
    }

    public final void updateOrientationIcon() {
        int i;
        if (this.hudBinding != null) {
            if (!this.player.getOrientationMode().getLocked()) {
                i = R.drawable.ic_player_rotate;
            } else if (this.player.getOrientationMode().getOrientation() == 0 || this.player.getOrientationMode().getOrientation() == 8 || this.player.getOrientationMode().getOrientation() == 6) {
                i = R.drawable.ic_player_lock_landscape;
            } else {
                i = R.drawable.ic_player_lock_portrait;
            }
            getHudBinding().orientationToggle.setImageDrawable(ContextCompat.getDrawable(this.player, i));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0012, code lost:
        r1 = org.videolan.vlc.RendererDelegate.INSTANCE.getRenderers().getValue();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void updateRendererVisibility() {
        /*
            r2 = this;
            org.videolan.vlc.databinding.PlayerHudRightBinding r0 = r2.hudRightBinding
            if (r0 == 0) goto L_0x002e
            org.videolan.vlc.databinding.PlayerHudRightBinding r0 = r2.getHudRightBinding()
            android.widget.ImageView r0 = r0.videoRenderer
            org.videolan.vlc.gui.video.VideoPlayerActivity r1 = r2.player
            boolean r1 = r1.isLocked()
            if (r1 != 0) goto L_0x0029
            org.videolan.vlc.RendererDelegate r1 = org.videolan.vlc.RendererDelegate.INSTANCE
            org.videolan.tools.livedata.LiveDataset r1 = r1.getRenderers()
            java.util.List r1 = r1.getValue()
            java.util.Collection r1 = (java.util.Collection) r1
            if (r1 == 0) goto L_0x0029
            boolean r1 = r1.isEmpty()
            if (r1 == 0) goto L_0x0027
            goto L_0x0029
        L_0x0027:
            r1 = 0
            goto L_0x002b
        L_0x0029:
            r1 = 8
        L_0x002b:
            r0.setVisibility(r1)
        L_0x002e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.video.VideoPlayerOverlayDelegate.updateRendererVisibility():void");
    }

    private final void manageTitleConstraints() {
        this.titleConstraintSetLandscape.clone(getHudRightBinding().hudRightOverlay);
        this.titleConstraintSetPortrait.clone(getHudRightBinding().hudRightOverlay);
        this.titleConstraintSetPortrait.setMargin(getHudRightBinding().playerOverlayTitle.getId(), 6, KotlinExtensionsKt.getDp(16));
        this.titleConstraintSetPortrait.setMargin(getHudRightBinding().playerOverlayTitle.getId(), 7, KotlinExtensionsKt.getDp(16));
        this.titleConstraintSetPortrait.connect(getHudRightBinding().playerOverlayTitle.getId(), 3, getHudRightBinding().iconBarrier.getId(), 4, KotlinExtensionsKt.getDp(0));
    }

    public final void updateTitleConstraints() {
        ConstraintSet constraintSet;
        if (this.hudRightBinding != null) {
            if (this.player.getResources().getConfiguration().orientation == 1) {
                constraintSet = this.titleConstraintSetPortrait;
            } else {
                constraintSet = this.titleConstraintSetLandscape;
            }
            constraintSet.applyTo(getHudRightBinding().hudRightOverlay);
        }
    }

    public final void updateHudMargins() {
        int dp = this.player.isTv() ? KotlinExtensionsKt.getDp(32) : KotlinExtensionsKt.getDp(8);
        int dimension = this.player.isTv() ? (int) this.player.getResources().getDimension(R.dimen.tv_overscan_vertical) : KotlinExtensionsKt.getDp(8);
        if (this.hudBinding != null) {
            float dimension2 = this.player.getResources().getDimension(R.dimen.large_margins_center);
            float dimension3 = this.player.getResources().getDimension(R.dimen.small_margins_sides);
            ImageView imageView = getHudBinding().playerOverlayTracks;
            Intrinsics.checkNotNullExpressionValue(imageView, "playerOverlayTracks");
            applyMargin(imageView, !this.player.isTv() ? (int) dimension3 : dp, false);
            ImageView imageView2 = getHudBinding().playerOverlayAdvFunction;
            Intrinsics.checkNotNullExpressionValue(imageView2, "playerOverlayAdvFunction");
            applyMargin(imageView2, !this.player.isTv() ? (int) dimension3 : dp, true);
            getHudBinding().playerOverlaySeekbar.setPadding(dp, 0, dp, 0);
            getHudBinding().bookmarkMarkerContainer.setPadding(dp, 0, dp, 0);
            if (this.player.isTv()) {
                FocusableTextView focusableTextView = getHudBinding().playerOverlayTime;
                Intrinsics.checkNotNullExpressionValue(focusableTextView, "playerOverlayTime");
                applyMargin(focusableTextView, dp, false);
                FocusableTextView focusableTextView2 = getHudBinding().playerOverlayLength;
                Intrinsics.checkNotNullExpressionValue(focusableTextView2, "playerOverlayLength");
                applyMargin(focusableTextView2, dp, true);
            }
            if (this.player.getResources().getConfiguration().orientation == 1) {
                KotlinExtensionsKt.setGone(getHudBinding().playerSpaceLeft);
                KotlinExtensionsKt.setGone(getHudBinding().playerSpaceRight);
                AccessibleSeekBar accessibleSeekBar = getHudBinding().playerOverlaySeekbar;
                Intrinsics.checkNotNullExpressionValue(accessibleSeekBar, "playerOverlaySeekbar");
                applyMargin(accessibleSeekBar, 0, true);
                AccessibleSeekBar accessibleSeekBar2 = getHudBinding().playerOverlaySeekbar;
                Intrinsics.checkNotNullExpressionValue(accessibleSeekBar2, "playerOverlaySeekbar");
                applyMargin(accessibleSeekBar2, 0, false);
                ImageView imageView3 = getHudBinding().playlistPrevious;
                Intrinsics.checkNotNullExpressionValue(imageView3, "playlistPrevious");
                applyMargin(imageView3, 0, true);
                ImageView imageView4 = getHudBinding().playerOverlayRewind;
                Intrinsics.checkNotNullExpressionValue(imageView4, "playerOverlayRewind");
                applyMargin(imageView4, 0, true);
                ImageView imageView5 = getHudBinding().playlistNext;
                Intrinsics.checkNotNullExpressionValue(imageView5, "playlistNext");
                applyMargin(imageView5, 0, false);
                ImageView imageView6 = getHudBinding().playerOverlayForward;
                Intrinsics.checkNotNullExpressionValue(imageView6, "playerOverlayForward");
                applyMargin(imageView6, 0, false);
                ImageView imageView7 = getHudBinding().orientationToggle;
                Intrinsics.checkNotNullExpressionValue(imageView7, "orientationToggle");
                applyMargin(imageView7, 0, false);
                ImageView imageView8 = getHudBinding().playerResize;
                Intrinsics.checkNotNullExpressionValue(imageView8, "playerResize");
                applyMargin(imageView8, 0, true);
            } else {
                KotlinExtensionsKt.setVisible(getHudBinding().playerSpaceLeft);
                KotlinExtensionsKt.setVisible(getHudBinding().playerSpaceRight);
                AccessibleSeekBar accessibleSeekBar3 = getHudBinding().playerOverlaySeekbar;
                Intrinsics.checkNotNullExpressionValue(accessibleSeekBar3, "playerOverlaySeekbar");
                applyMargin(accessibleSeekBar3, KotlinExtensionsKt.getDp(20), true);
                AccessibleSeekBar accessibleSeekBar4 = getHudBinding().playerOverlaySeekbar;
                Intrinsics.checkNotNullExpressionValue(accessibleSeekBar4, "playerOverlaySeekbar");
                applyMargin(accessibleSeekBar4, KotlinExtensionsKt.getDp(20), false);
                ImageView imageView9 = getHudBinding().playlistPrevious;
                Intrinsics.checkNotNullExpressionValue(imageView9, "playlistPrevious");
                int i = (int) dimension2;
                applyMargin(imageView9, i, true);
                ImageView imageView10 = getHudBinding().playerOverlayRewind;
                Intrinsics.checkNotNullExpressionValue(imageView10, "playerOverlayRewind");
                applyMargin(imageView10, i, true);
                ImageView imageView11 = getHudBinding().playlistNext;
                Intrinsics.checkNotNullExpressionValue(imageView11, "playlistNext");
                applyMargin(imageView11, i, false);
                ImageView imageView12 = getHudBinding().playerOverlayForward;
                Intrinsics.checkNotNullExpressionValue(imageView12, "playerOverlayForward");
                applyMargin(imageView12, i, false);
                ImageView imageView13 = getHudBinding().orientationToggle;
                Intrinsics.checkNotNullExpressionValue(imageView13, "orientationToggle");
                int i2 = (int) dimension3;
                applyMargin(imageView13, i2, false);
                ImageView imageView14 = getHudBinding().playerResize;
                Intrinsics.checkNotNullExpressionValue(imageView14, "playerResize");
                applyMargin(imageView14, i2, true);
            }
        }
        if (this.hudRightBinding != null) {
            TextView textView = getHudRightBinding().playerOverlayTitle;
            Intrinsics.checkNotNullExpressionValue(textView, "playerOverlayTitle");
            applyVerticalMargin(textView, dimension);
        }
    }

    private final ConstraintLayout.LayoutParams applyMargin(View view, int i, boolean z) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
        if (z) {
            layoutParams2.setMarginEnd(i);
        } else {
            layoutParams2.setMarginStart(i);
        }
        view.setLayoutParams(layoutParams2);
        return layoutParams2;
    }

    private final ConstraintLayout.LayoutParams applyVerticalMargin(View view, int i) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
        layoutParams2.bottomMargin = i;
        view.setLayoutParams(layoutParams2);
        return layoutParams2;
    }

    public final void updateScreenshotButton() {
        ImageView imageView = getHudRightBinding().playerScreenshot;
        int i = 0;
        if (!ArraysKt.contains((T[]) new String[]{DiskLruCache.VERSION_1, "3"}, ((SharedPreferences) Settings.INSTANCE.getInstance(this.player)).getString(SettingsKt.SCREENSHOT_MODE, Constants.GROUP_VIDEOS_FOLDER))) {
            i = 8;
        }
        imageView.setVisibility(i);
        getHudRightBinding().playerScreenshot.setOnClickListener(this.player);
    }

    private final void initPlaylistUi() {
        if (this.playlistAdapter == null) {
            setPlaylistAdapter(new PlaylistAdapter(this.player));
            getPlaylist().setLayoutManager(new LinearLayoutManager(this.player, 1, false));
        }
        if (this.player.getPlaylistModel() == null) {
            VideoPlayerActivity videoPlayerActivity = this.player;
            PlaylistModel playlistModel = (PlaylistModel) new ViewModelProvider(this.player).get(PlaylistModel.class);
            getPlaylistAdapter().setModel(playlistModel);
            LiveDataset<MediaWrapper> dataset = playlistModel.getDataset();
            VideoPlayerActivity videoPlayerActivity2 = this.player;
            dataset.observe(videoPlayerActivity2, videoPlayerActivity2.getPlaylistObserver());
            videoPlayerActivity.setPlaylistModel(playlistModel);
        }
        PlaybackService service = this.player.getService();
        if (service == null || !service.hasPlaylist()) {
            KotlinExtensionsKt.setGone(getHudRightBinding().playlistToggle);
        } else {
            KotlinExtensionsKt.setVisible(getHudRightBinding().playlistToggle);
            if (this.hudBinding != null) {
                KotlinExtensionsKt.setVisible(getHudBinding().playlistPrevious);
                KotlinExtensionsKt.setVisible(getHudBinding().playlistNext);
            }
        }
        getHudRightBinding().playlistToggle.setOnClickListener(this.player);
        getCloseButton().setOnClickListener(new VideoPlayerOverlayDelegate$$ExternalSyntheticLambda7(this));
        ImageView imageView = this.hingeArrowLeft;
        if (imageView != null) {
            imageView.setOnClickListener(new VideoPlayerOverlayDelegate$$ExternalSyntheticLambda8(this));
        }
        ImageView imageView2 = this.hingeArrowRight;
        if (imageView2 != null) {
            imageView2.setOnClickListener(new VideoPlayerOverlayDelegate$$ExternalSyntheticLambda9(this));
        }
        new ItemTouchHelper(new SwipeDragItemTouchHelperCallback(getPlaylistAdapter(), true, false, 4, (DefaultConstructorMarker) null)).attachToRecyclerView(getPlaylist());
    }

    /* access modifiers changed from: private */
    public static final void initPlaylistUi$lambda$27(VideoPlayerOverlayDelegate videoPlayerOverlayDelegate, View view) {
        Intrinsics.checkNotNullParameter(videoPlayerOverlayDelegate, "this$0");
        videoPlayerOverlayDelegate.togglePlaylist();
    }

    /* access modifiers changed from: private */
    public static final void initPlaylistUi$lambda$28(VideoPlayerOverlayDelegate videoPlayerOverlayDelegate, View view) {
        Intrinsics.checkNotNullParameter(videoPlayerOverlayDelegate, "this$0");
        SettingsKt.putSingle((SharedPreferences) Settings.INSTANCE.getInstance(videoPlayerOverlayDelegate.player), SettingsKt.HINGE_ON_RIGHT, false);
        videoPlayerOverlayDelegate.manageHinge();
        showOverlay$default(videoPlayerOverlayDelegate, false, 1, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final void initPlaylistUi$lambda$29(VideoPlayerOverlayDelegate videoPlayerOverlayDelegate, View view) {
        Intrinsics.checkNotNullParameter(videoPlayerOverlayDelegate, "this$0");
        SettingsKt.putSingle((SharedPreferences) Settings.INSTANCE.getInstance(videoPlayerOverlayDelegate.player), SettingsKt.HINGE_ON_RIGHT, true);
        videoPlayerOverlayDelegate.manageHinge();
        showOverlay$default(videoPlayerOverlayDelegate, false, 1, (Object) null);
    }

    public final void togglePlaylist() {
        EditText editText;
        if (this.player.isPlaylistVisible()) {
            KotlinExtensionsKt.setGone(getPlaylistContainer());
            getPlaylist().setOnClickListener((View.OnClickListener) null);
            UiTools.INSTANCE.setKeyboardVisibility(getPlaylistContainer(), false);
            return;
        }
        hideOverlay$default(this, true, false, 2, (Object) null);
        KotlinExtensionsKt.setVisible(getPlaylistContainer());
        getPlaylist().setAdapter(getPlaylistAdapter());
        this.player.onSelectionSet(getPlaylistAdapter().getCurrentIndex());
        this.player.update();
        if (AccessibilityHelperKt.isTalkbackIsEnabled(this.player) && (editText = getPlaylistSearchText().getEditText()) != null) {
            editText.sendAccessibilityEvent(8);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:115:0x0256  */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x0258  */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x0276  */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x0278  */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x028c  */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x029f  */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x02a8  */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x02d1  */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x02fc  */
    /* JADX WARNING: Removed duplicated region for block: B:140:0x035b  */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x0360  */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x0388  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void showControls(boolean r12) {
        /*
            r11 = this;
            if (r12 == 0) goto L_0x000b
            org.videolan.vlc.gui.video.VideoPlayerActivity r0 = r11.player
            boolean r0 = r0.isInPictureInPictureMode()
            if (r0 == 0) goto L_0x000b
            return
        L_0x000b:
            org.videolan.vlc.databinding.PlayerHudBinding r0 = r11.hudBinding
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x013e
            org.videolan.vlc.databinding.PlayerHudBinding r0 = r11.getHudBinding()
            android.widget.ImageView r0 = r0.playerOverlayPlay
            r3 = 4
            if (r12 == 0) goto L_0x001c
            r4 = 0
            goto L_0x001d
        L_0x001c:
            r4 = 4
        L_0x001d:
            r0.setVisibility(r4)
            boolean r0 = r11.seekButtons
            if (r0 == 0) goto L_0x00c8
            org.videolan.vlc.databinding.PlayerHudBinding r0 = r11.getHudBinding()
            android.widget.ImageView r0 = r0.playerOverlayRewind
            if (r12 == 0) goto L_0x002e
            r4 = 0
            goto L_0x002f
        L_0x002e:
            r4 = 4
        L_0x002f:
            r0.setVisibility(r4)
            org.videolan.vlc.databinding.PlayerHudBinding r0 = r11.getHudBinding()
            android.widget.TextView r0 = r0.playerOverlayRewindText
            org.videolan.tools.Settings r4 = org.videolan.tools.Settings.INSTANCE
            int r4 = r4.getVideoJumpDelay()
            java.lang.String r4 = java.lang.String.valueOf(r4)
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            r0.setText(r4)
            org.videolan.vlc.databinding.PlayerHudBinding r0 = r11.getHudBinding()
            android.widget.ImageView r0 = r0.playerOverlayRewind
            org.videolan.vlc.gui.video.VideoPlayerActivity r4 = r11.player
            int r5 = org.videolan.vlc.R.string.talkback_action_rewind
            org.videolan.tools.Settings r6 = org.videolan.tools.Settings.INSTANCE
            int r6 = r6.getVideoJumpDelay()
            java.lang.String r6 = java.lang.String.valueOf(r6)
            java.lang.Object[] r7 = new java.lang.Object[r1]
            r7[r2] = r6
            java.lang.String r4 = r4.getString(r5, r7)
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            r0.setContentDescription(r4)
            org.videolan.vlc.databinding.PlayerHudBinding r0 = r11.getHudBinding()
            android.widget.TextView r0 = r0.playerOverlayRewindText
            if (r12 == 0) goto L_0x0072
            r4 = 0
            goto L_0x0073
        L_0x0072:
            r4 = 4
        L_0x0073:
            r0.setVisibility(r4)
            org.videolan.vlc.databinding.PlayerHudBinding r0 = r11.getHudBinding()
            android.widget.ImageView r0 = r0.playerOverlayForward
            if (r12 == 0) goto L_0x0080
            r4 = 0
            goto L_0x0081
        L_0x0080:
            r4 = 4
        L_0x0081:
            r0.setVisibility(r4)
            org.videolan.vlc.databinding.PlayerHudBinding r0 = r11.getHudBinding()
            android.widget.TextView r0 = r0.playerOverlayForwardText
            org.videolan.tools.Settings r4 = org.videolan.tools.Settings.INSTANCE
            int r4 = r4.getVideoJumpDelay()
            java.lang.String r4 = java.lang.String.valueOf(r4)
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            r0.setText(r4)
            org.videolan.vlc.databinding.PlayerHudBinding r0 = r11.getHudBinding()
            android.widget.ImageView r0 = r0.playerOverlayForward
            org.videolan.vlc.gui.video.VideoPlayerActivity r4 = r11.player
            int r5 = org.videolan.vlc.R.string.talkback_action_forward
            org.videolan.tools.Settings r6 = org.videolan.tools.Settings.INSTANCE
            int r6 = r6.getVideoJumpDelay()
            java.lang.String r6 = java.lang.String.valueOf(r6)
            java.lang.Object[] r7 = new java.lang.Object[r1]
            r7[r2] = r6
            java.lang.String r4 = r4.getString(r5, r7)
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            r0.setContentDescription(r4)
            org.videolan.vlc.databinding.PlayerHudBinding r0 = r11.getHudBinding()
            android.widget.TextView r0 = r0.playerOverlayForwardText
            if (r12 == 0) goto L_0x00c4
            r4 = 0
            goto L_0x00c5
        L_0x00c4:
            r4 = 4
        L_0x00c5:
            r0.setVisibility(r4)
        L_0x00c8:
            org.videolan.vlc.databinding.PlayerHudBinding r0 = r11.getHudBinding()
            android.widget.ImageView r0 = r0.playerOverlayTracks
            if (r12 == 0) goto L_0x00d2
            r4 = 0
            goto L_0x00d3
        L_0x00d2:
            r4 = 4
        L_0x00d3:
            r0.setVisibility(r4)
            org.videolan.vlc.databinding.PlayerHudBinding r0 = r11.getHudBinding()
            android.widget.ImageView r0 = r0.playerOverlayAdvFunction
            if (r12 == 0) goto L_0x00e0
            r4 = 0
            goto L_0x00e1
        L_0x00e0:
            r4 = 4
        L_0x00e1:
            r0.setVisibility(r4)
            org.videolan.vlc.databinding.PlayerHudBinding r0 = r11.getHudBinding()
            android.widget.ImageView r0 = r0.playerResize
            if (r12 == 0) goto L_0x00ee
            r4 = 0
            goto L_0x00ef
        L_0x00ee:
            r4 = 4
        L_0x00ef:
            r0.setVisibility(r4)
            boolean r0 = r11.hasPlaylist
            if (r0 == 0) goto L_0x0112
            org.videolan.vlc.databinding.PlayerHudBinding r0 = r11.getHudBinding()
            android.widget.ImageView r0 = r0.playlistPrevious
            if (r12 == 0) goto L_0x0100
            r4 = 0
            goto L_0x0101
        L_0x0100:
            r4 = 4
        L_0x0101:
            r0.setVisibility(r4)
            org.videolan.vlc.databinding.PlayerHudBinding r0 = r11.getHudBinding()
            android.widget.ImageView r0 = r0.playlistNext
            if (r12 == 0) goto L_0x010e
            r4 = 0
            goto L_0x010f
        L_0x010e:
            r4 = 4
        L_0x010f:
            r0.setVisibility(r4)
        L_0x0112:
            org.videolan.vlc.databinding.PlayerHudBinding r0 = r11.getHudBinding()
            android.widget.ImageView r0 = r0.orientationToggle
            org.videolan.vlc.gui.video.VideoPlayerActivity r4 = r11.player
            boolean r4 = r4.isTv()
            if (r4 != 0) goto L_0x012c
            org.videolan.resources.AndroidDevices r4 = org.videolan.resources.AndroidDevices.INSTANCE
            boolean r4 = r4.isChromeBook()
            if (r4 == 0) goto L_0x0129
            goto L_0x012c
        L_0x0129:
            if (r12 == 0) goto L_0x012c
            r3 = 0
        L_0x012c:
            r0.setVisibility(r3)
            org.videolan.vlc.databinding.PlayerHudBinding r0 = r11.getHudBinding()
            org.videolan.vlc.gui.view.AccessibleSeekBar r0 = r0.playerOverlaySeekbar
            if (r12 != 0) goto L_0x013b
            r0.disableAccessibilityEvents()
            goto L_0x013e
        L_0x013b:
            r0.enableAccessibilityEvents()
        L_0x013e:
            org.videolan.vlc.databinding.PlayerHudRightBinding r0 = r11.hudRightBinding
            if (r0 == 0) goto L_0x039c
            org.videolan.vlc.gui.video.VideoPlayerActivity r0 = r11.player
            org.videolan.libvlc.util.DisplayManager r0 = r0.getDisplayManager()
            boolean r0 = r0.isSecondary()
            if (r0 == 0) goto L_0x0159
            org.videolan.vlc.databinding.PlayerHudRightBinding r3 = r11.getHudRightBinding()
            android.widget.ImageView r3 = r3.videoSecondaryDisplay
            int r4 = org.videolan.vlc.R.drawable.ic_player_screenshare_stop
            r3.setImageResource(r4)
        L_0x0159:
            org.videolan.vlc.databinding.PlayerHudRightBinding r3 = r11.getHudRightBinding()
            android.widget.ImageView r3 = r3.videoSecondaryDisplay
            r4 = 8
            if (r12 != 0) goto L_0x0166
        L_0x0163:
            r5 = 8
            goto L_0x0175
        L_0x0166:
            org.videolan.vlc.gui.helpers.UiTools r5 = org.videolan.vlc.gui.helpers.UiTools.INSTANCE
            org.videolan.vlc.gui.video.VideoPlayerActivity r6 = r11.player
            android.content.Context r6 = r6.getApplicationContext()
            boolean r5 = r5.hasSecondaryDisplay(r6)
            if (r5 == 0) goto L_0x0163
            r5 = 0
        L_0x0175:
            r3.setVisibility(r5)
            org.videolan.vlc.databinding.PlayerHudRightBinding r3 = r11.getHudRightBinding()
            android.widget.ImageView r3 = r3.videoSecondaryDisplay
            org.videolan.vlc.gui.video.VideoPlayerActivity r5 = r11.player
            android.content.res.Resources r5 = r5.getResources()
            if (r0 == 0) goto L_0x0189
            int r0 = org.videolan.vlc.R.string.video_remote_disable
            goto L_0x018b
        L_0x0189:
            int r0 = org.videolan.vlc.R.string.video_remote_enable
        L_0x018b:
            java.lang.String r0 = r5.getString(r0)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r3.setContentDescription(r0)
            org.videolan.vlc.databinding.PlayerHudRightBinding r0 = r11.getHudRightBinding()
            android.widget.ImageView r0 = r0.playlistToggle
            if (r12 == 0) goto L_0x01ac
            org.videolan.vlc.gui.video.VideoPlayerActivity r3 = r11.player
            org.videolan.vlc.PlaybackService r3 = r3.getService()
            if (r3 == 0) goto L_0x01ac
            boolean r3 = r3.hasPlaylist()
            if (r3 != r1) goto L_0x01ac
            r3 = 0
            goto L_0x01ae
        L_0x01ac:
            r3 = 8
        L_0x01ae:
            r0.setVisibility(r3)
            org.videolan.vlc.databinding.PlayerHudRightBinding r0 = r11.getHudRightBinding()
            android.widget.ImageView r0 = r0.playerScreenshot
            r3 = 2
            java.lang.String[] r3 = new java.lang.String[r3]
            java.lang.String r5 = "1"
            r3[r2] = r5
            java.lang.String r5 = "3"
            r3[r1] = r5
            org.videolan.tools.Settings r1 = org.videolan.tools.Settings.INSTANCE
            org.videolan.vlc.gui.video.VideoPlayerActivity r5 = r11.player
            java.lang.Object r1 = r1.getInstance(r5)
            android.content.SharedPreferences r1 = (android.content.SharedPreferences) r1
            java.lang.String r5 = "screenshot_mode"
            java.lang.String r6 = "0"
            java.lang.String r1 = r1.getString(r5, r6)
            boolean r1 = kotlin.collections.ArraysKt.contains((T[]) r3, r1)
            if (r1 == 0) goto L_0x01dc
            r1 = 0
            goto L_0x01de
        L_0x01dc:
            r1 = 8
        L_0x01de:
            r0.setVisibility(r1)
            org.videolan.vlc.databinding.PlayerHudRightBinding r0 = r11.getHudRightBinding()
            android.widget.ImageView r0 = r0.playerOverlayNavmenu
            org.videolan.vlc.gui.video.VideoPlayerActivity r1 = r11.player
            int r1 = r1.getMenuIdx()
            if (r1 < 0) goto L_0x01f1
            r1 = 0
            goto L_0x01f3
        L_0x01f1:
            r1 = 8
        L_0x01f3:
            r0.setVisibility(r1)
            org.videolan.vlc.databinding.PlayerHudRightBinding r0 = r11.getHudRightBinding()
            com.google.android.material.chip.Chip r0 = r0.sleepQuickAction
            if (r12 == 0) goto L_0x020c
            org.videolan.vlc.PlaybackService$Companion r1 = org.videolan.vlc.PlaybackService.Companion
            androidx.lifecycle.MutableLiveData r1 = r1.getPlayerSleepTime()
            java.lang.Object r1 = r1.getValue()
            if (r1 == 0) goto L_0x020c
            r1 = 0
            goto L_0x020e
        L_0x020c:
            r1 = 8
        L_0x020e:
            r0.setVisibility(r1)
            org.videolan.vlc.databinding.PlayerHudRightBinding r0 = r11.getHudRightBinding()
            com.google.android.material.chip.Chip r0 = r0.playbackSpeedQuickAction
            r1 = 0
            if (r12 == 0) goto L_0x0236
            org.videolan.vlc.gui.video.VideoPlayerActivity r3 = r11.player
            org.videolan.vlc.PlaybackService r3 = r3.getService()
            if (r3 == 0) goto L_0x022b
            float r3 = r3.getRate()
            java.lang.Float r3 = java.lang.Float.valueOf(r3)
            goto L_0x022c
        L_0x022b:
            r3 = r1
        L_0x022c:
            r5 = 1065353216(0x3f800000, float:1.0)
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Float) r3, (float) r5)
            if (r3 != 0) goto L_0x0236
            r3 = 0
            goto L_0x0238
        L_0x0236:
            r3 = 8
        L_0x0238:
            r0.setVisibility(r3)
            org.videolan.vlc.databinding.PlayerHudRightBinding r0 = r11.getHudRightBinding()
            com.google.android.material.chip.Chip r0 = r0.spuDelayQuickAction
            r5 = 0
            if (r12 == 0) goto L_0x0258
            org.videolan.vlc.gui.video.VideoPlayerActivity r3 = r11.player
            org.videolan.vlc.PlaybackService r3 = r3.getService()
            if (r3 == 0) goto L_0x0256
            long r7 = r3.getSpuDelay()
            int r3 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r3 != 0) goto L_0x0256
            goto L_0x0258
        L_0x0256:
            r3 = 0
            goto L_0x025a
        L_0x0258:
            r3 = 8
        L_0x025a:
            r0.setVisibility(r3)
            org.videolan.vlc.databinding.PlayerHudRightBinding r0 = r11.getHudRightBinding()
            com.google.android.material.chip.Chip r0 = r0.audioDelayQuickAction
            if (r12 == 0) goto L_0x0278
            org.videolan.vlc.gui.video.VideoPlayerActivity r12 = r11.player
            org.videolan.vlc.PlaybackService r12 = r12.getService()
            if (r12 == 0) goto L_0x0276
            long r7 = r12.getAudioDelay()
            int r12 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r12 != 0) goto L_0x0276
            goto L_0x0278
        L_0x0276:
            r12 = 0
            goto L_0x027a
        L_0x0278:
            r12 = 8
        L_0x027a:
            r0.setVisibility(r12)
            org.videolan.vlc.databinding.PlayerHudRightBinding r12 = r11.getHudRightBinding()
            android.widget.TextClock r12 = r12.clock
            org.videolan.tools.Settings r0 = org.videolan.tools.Settings.INSTANCE
            boolean r0 = r0.getShowTvUi()
            if (r0 == 0) goto L_0x028c
            goto L_0x028e
        L_0x028c:
            r2 = 8
        L_0x028e:
            r12.setVisibility(r2)
            org.videolan.vlc.databinding.PlayerHudRightBinding r12 = r11.getHudRightBinding()
            com.google.android.material.chip.Chip r12 = r12.playbackSpeedQuickAction
            org.videolan.vlc.gui.video.VideoPlayerActivity r0 = r11.player
            org.videolan.vlc.PlaybackService r0 = r0.getService()
            if (r0 == 0) goto L_0x02a8
            float r0 = r0.getRate()
            java.lang.String r0 = org.videolan.tools.Strings.formatRateString(r0)
            goto L_0x02a9
        L_0x02a8:
            r0 = r1
        L_0x02a9:
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r12.setText(r0)
            org.videolan.vlc.databinding.PlayerHudRightBinding r12 = r11.getHudRightBinding()
            com.google.android.material.chip.Chip r12 = r12.playbackSpeedQuickAction
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            org.videolan.vlc.gui.video.VideoPlayerActivity r2 = r11.player
            int r3 = org.videolan.vlc.R.string.playback_speed
            java.lang.String r2 = r2.getString(r3)
            r0.append(r2)
            java.lang.String r2 = ". "
            r0.append(r2)
            org.videolan.vlc.gui.video.VideoPlayerActivity r2 = r11.player
            org.videolan.vlc.PlaybackService r2 = r2.getService()
            if (r2 == 0) goto L_0x02d9
            float r1 = r2.getRate()
            java.lang.String r1 = org.videolan.tools.Strings.formatRateString(r1)
        L_0x02d9:
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r12.setContentDescription(r0)
            r12 = 3
            java.util.Locale r0 = java.util.Locale.getDefault()
            java.text.DateFormat r12 = java.text.DateFormat.getTimeInstance(r12, r0)
            org.videolan.vlc.PlaybackService$Companion r0 = org.videolan.vlc.PlaybackService.Companion
            androidx.lifecycle.MutableLiveData r0 = r0.getPlayerSleepTime()
            java.lang.Object r0 = r0.getValue()
            java.util.Calendar r0 = (java.util.Calendar) r0
            if (r0 == 0) goto L_0x0348
            org.videolan.vlc.databinding.PlayerHudRightBinding r1 = r11.getHudRightBinding()
            com.google.android.material.chip.Chip r1 = r1.sleepQuickAction
            java.util.Date r2 = r0.getTime()
            java.lang.String r12 = r12.format(r2)
            java.lang.CharSequence r12 = (java.lang.CharSequence) r12
            r1.setText(r12)
            org.videolan.vlc.databinding.PlayerHudRightBinding r12 = r11.getHudRightBinding()
            com.google.android.material.chip.Chip r12 = r12.sleepQuickAction
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            org.videolan.vlc.gui.video.VideoPlayerActivity r2 = r11.player
            int r3 = org.videolan.vlc.R.string.sleep_in
            java.lang.String r2 = r2.getString(r3)
            r1.append(r2)
            org.videolan.vlc.gui.helpers.TalkbackUtil r2 = org.videolan.vlc.gui.helpers.TalkbackUtil.INSTANCE
            org.videolan.vlc.gui.video.VideoPlayerActivity r3 = r11.player
            android.content.Context r3 = (android.content.Context) r3
            long r7 = java.lang.System.currentTimeMillis()
            java.util.Date r0 = r0.getTime()
            long r9 = r0.getTime()
            long r7 = r7 - r9
            java.lang.String r0 = r2.millisToString(r3, r7)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r12.setContentDescription(r0)
        L_0x0348:
            org.videolan.vlc.databinding.PlayerHudRightBinding r12 = r11.getHudRightBinding()
            com.google.android.material.chip.Chip r12 = r12.spuDelayQuickAction
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            org.videolan.vlc.gui.video.VideoPlayerActivity r1 = r11.player
            org.videolan.vlc.PlaybackService r1 = r1.getService()
            if (r1 == 0) goto L_0x0360
            long r1 = r1.getSpuDelay()
            goto L_0x0361
        L_0x0360:
            r1 = r5
        L_0x0361:
            r3 = 1000(0x3e8, double:4.94E-321)
            long r1 = r1 / r3
            r0.append(r1)
            java.lang.String r1 = " ms"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r12.setText(r0)
            org.videolan.vlc.databinding.PlayerHudRightBinding r12 = r11.getHudRightBinding()
            com.google.android.material.chip.Chip r12 = r12.audioDelayQuickAction
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            org.videolan.vlc.gui.video.VideoPlayerActivity r2 = r11.player
            org.videolan.vlc.PlaybackService r2 = r2.getService()
            if (r2 == 0) goto L_0x038c
            long r5 = r2.getAudioDelay()
        L_0x038c:
            long r5 = r5 / r3
            r0.append(r5)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r12.setText(r0)
        L_0x039c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.video.VideoPlayerOverlayDelegate.showControls(boolean):void");
    }

    public static /* synthetic */ void hideOverlay$default(VideoPlayerOverlayDelegate videoPlayerOverlayDelegate, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z2 = false;
        }
        videoPlayerOverlayDelegate.hideOverlay(z, z2);
    }

    public final void hideOverlay(boolean z, boolean z2) {
        ViewPropertyAnimator animate;
        ViewPropertyAnimator animate2;
        if (!z && AccessibilityHelperKt.isTalkbackIsEnabled(this.player) && !z2) {
            return;
        }
        if (this.player.isShowing()) {
            if (isBookmarkShown()) {
                hideBookmarks();
            }
            this.player.getHandler().removeMessages(1);
            if (!this.player.getDisplayManager().isPrimary()) {
                View view = this.overlayBackground;
                if (view != null) {
                    view.startAnimation(AnimationUtils.loadAnimation(this.player, 17432577));
                }
                KotlinExtensionsKt.setInvisible(this.overlayBackground);
            }
            exitAnimate(new View[]{getHudBinding().progressOverlay, getHudBackground()}, (float) KotlinExtensionsKt.getDp(100));
            exitAnimate(new View[]{getHudRightBinding().hudRightOverlay, getHudRightBackground()}, -((float) KotlinExtensionsKt.getDp(100)));
            ImageView imageView = this.hingeArrowLeft;
            if (!(imageView == null || (animate2 = imageView.animate()) == null)) {
                animate2.alpha(0.0f);
            }
            ImageView imageView2 = this.hingeArrowRight;
            if (!(imageView2 == null || (animate = imageView2.animate()) == null)) {
                animate.alpha(0.0f);
            }
            showControls(false);
            this.player.setShowing(false);
            dimStatusBar(true);
            EditText editText = getPlaylistSearchText().getEditText();
            if (editText != null) {
                editText.setText("");
            }
        } else if (!z) {
            dimStatusBar(true);
        }
    }

    public final void focusPlayPause() {
        if (this.hudBinding != null) {
            getHudBinding().playerOverlayPlay.requestFocus();
        }
    }

    public final void toggleOverlay() {
        if (!this.player.isShowing()) {
            showOverlay$default(this, false, 1, (Object) null);
        } else {
            hideOverlay$default(this, true, false, 2, (Object) null);
        }
    }

    public final void lockScreen() {
        this.orientationLockedBeforeLock = this.player.getOrientationMode().getLocked();
        if (!this.player.getOrientationMode().getLocked()) {
            this.player.toggleOrientationLock();
        }
        if (isHudBindingInitialized()) {
            getHudBinding().playerOverlayTime.setEnabled(false);
            getHudBinding().playerOverlaySeekbar.setEnabled(false);
            getHudBinding().playerOverlayLength.setEnabled(false);
            getHudBinding().playlistNext.setEnabled(false);
            getHudBinding().playlistPrevious.setEnabled(false);
            KotlinExtensionsKt.setVisible(getHudBinding().swipeToUnlock);
            ViewGroup.LayoutParams layoutParams = getPlayerUiContainer().getLayoutParams();
            FrameLayout.LayoutParams layoutParams2 = layoutParams instanceof FrameLayout.LayoutParams ? (FrameLayout.LayoutParams) layoutParams : null;
            if (layoutParams2 != null) {
                DisplayCutout m = WindowInsetsCompat$$ExternalSyntheticApiModelOutline0.m(WindowIdApi18$$ExternalSyntheticApiModelOutline0.m(this.player.getWindow().getDecorView()));
                layoutParams2.topMargin = m != null ? m.getSafeInsetTop() : 0;
                DisplayCutout m2 = WindowInsetsCompat$$ExternalSyntheticApiModelOutline0.m(WindowIdApi18$$ExternalSyntheticApiModelOutline0.m(this.player.getWindow().getDecorView()));
                layoutParams2.bottomMargin = (m2 != null ? m2.getSafeInsetBottom() : 0) + KotlinExtensionsKt.getDp(24);
            }
        }
        hideOverlay$default(this, true, false, 2, (Object) null);
        this.player.setLockBackButton(true);
        this.player.setLocked(true);
    }

    public final void unlockScreen() {
        ViewGroup.LayoutParams layoutParams = getPlayerUiContainer().getLayoutParams();
        FrameLayout.LayoutParams layoutParams2 = layoutParams instanceof FrameLayout.LayoutParams ? (FrameLayout.LayoutParams) layoutParams : null;
        if (layoutParams2 != null) {
            layoutParams2.topMargin = 0;
            layoutParams2.bottomMargin = 0;
        }
        this.player.getOrientationMode().setLocked(this.orientationLockedBeforeLock);
        VideoPlayerActivity videoPlayerActivity = this.player;
        videoPlayerActivity.setRequestedOrientation(videoPlayerActivity.getScreenOrientation(videoPlayerActivity.getOrientationMode()));
        if (isHudBindingInitialized()) {
            getHudBinding().playerOverlayTime.setEnabled(true);
            AccessibleSeekBar accessibleSeekBar = getHudBinding().playerOverlaySeekbar;
            PlaybackService service = this.player.getService();
            accessibleSeekBar.setEnabled(!(service != null && !service.isSeekable()));
            getHudBinding().playerOverlayLength.setEnabled(true);
            getHudBinding().playlistNext.setEnabled(true);
            getHudBinding().playlistPrevious.setEnabled(true);
        }
        updateOrientationIcon();
        this.player.setShowing(false);
        this.player.setLocked(false);
        showOverlay$default(this, false, 1, (Object) null);
        this.player.setLockBackButton(false);
    }

    /* access modifiers changed from: private */
    public final void pickSubtitles() {
        MediaWrapperImpl mediaWrapperImpl;
        Uri videoUri = this.player.getVideoUri();
        if (videoUri != null) {
            if (BrowserutilsKt.isSchemeFile(videoUri.getScheme()) || BrowserutilsKt.isSchemeNetwork(videoUri.getScheme())) {
                String parent = FileUtils.INSTANCE.getParent(videoUri.toString());
                Intrinsics.checkNotNull(parent);
                mediaWrapperImpl = new MediaWrapperImpl(Uri.parse(parent));
            } else {
                mediaWrapperImpl = null;
            }
            this.player.setShowingDialog(true);
            Intent intent = new Intent(this.player, FilePickerActivity.class);
            intent.putExtra(BaseBrowserFragmentKt.KEY_MEDIA, mediaWrapperImpl);
            this.player.startActivityForResult(intent, 0);
        }
    }

    /* access modifiers changed from: private */
    public final Unit downloadSubtitles() {
        MediaWrapper currentMediaWrapper;
        PlaybackService service = this.player.getService();
        if (service == null || (currentMediaWrapper = service.getCurrentMediaWrapper()) == null) {
            return null;
        }
        MediaUtils.INSTANCE.getSubs((FragmentActivity) this.player, currentMediaWrapper);
        return Unit.INSTANCE;
    }

    public final void showBookmarks() {
        PlaybackService service = this.player.getService();
        if (service != null) {
            if (this.bookmarkListDelegate == null) {
                VideoPlayerActivity videoPlayerActivity = this.player;
                BookmarkListDelegate bookmarkListDelegate2 = new BookmarkListDelegate(videoPlayerActivity, service, videoPlayerActivity.getBookmarkModel());
                this.bookmarkListDelegate = bookmarkListDelegate2;
                ConstraintLayout constraintLayout = getHudBinding().bookmarkMarkerContainer;
                Intrinsics.checkNotNullExpressionValue(constraintLayout, "bookmarkMarkerContainer");
                bookmarkListDelegate2.setMarkerContainer(constraintLayout);
                BookmarkListDelegate bookmarkListDelegate3 = this.bookmarkListDelegate;
                if (bookmarkListDelegate3 != null) {
                    bookmarkListDelegate3.setVisibilityListener(new VideoPlayerOverlayDelegate$showBookmarks$1$1(this));
                }
            }
            BookmarkListDelegate bookmarkListDelegate4 = this.bookmarkListDelegate;
            if (bookmarkListDelegate4 != null) {
                bookmarkListDelegate4.show();
            }
            int top = getHudBinding().playerOverlaySeekbar.getTop();
            BookmarkListDelegate bookmarkListDelegate5 = this.bookmarkListDelegate;
            if (bookmarkListDelegate5 != null) {
                bookmarkListDelegate5.setProgressHeight((float) (top + KotlinExtensionsKt.getDp(12)));
            }
        }
    }

    public final void rotateBookmarks() {
        if (this.bookmarkListDelegate != null && isBookmarkShown()) {
            getHudBinding().progressOverlay.post(new VideoPlayerOverlayDelegate$$ExternalSyntheticLambda6(this));
        }
    }

    /* access modifiers changed from: private */
    public static final void rotateBookmarks$lambda$35(VideoPlayerOverlayDelegate videoPlayerOverlayDelegate) {
        Intrinsics.checkNotNullParameter(videoPlayerOverlayDelegate, "this$0");
        BookmarkListDelegate bookmarkListDelegate2 = videoPlayerOverlayDelegate.bookmarkListDelegate;
        if (bookmarkListDelegate2 != null) {
            bookmarkListDelegate2.hide();
        }
        videoPlayerOverlayDelegate.showBookmarks();
    }

    public final boolean isBookmarkShown() {
        BookmarkListDelegate bookmarkListDelegate2 = this.bookmarkListDelegate;
        return (bookmarkListDelegate2 == null || bookmarkListDelegate2 == null || !bookmarkListDelegate2.getVisible()) ? false : true;
    }

    public final void hideBookmarks() {
        BookmarkListDelegate bookmarkListDelegate2 = this.bookmarkListDelegate;
        if (bookmarkListDelegate2 != null) {
            bookmarkListDelegate2.hide();
        }
    }

    public final ConstraintLayout getOverlayBrightness() {
        ConstraintLayout constraintLayout = this.playerOverlayBrightness;
        if (constraintLayout != null) {
            if (constraintLayout != null) {
                return constraintLayout;
            }
            Intrinsics.throwUninitializedPropertyAccessException("playerOverlayBrightness");
        }
        return null;
    }

    public final ConstraintLayout getOverlayVolume() {
        ConstraintLayout constraintLayout = this.playerOverlayVolume;
        if (constraintLayout != null) {
            if (constraintLayout != null) {
                return constraintLayout;
            }
            Intrinsics.throwUninitializedPropertyAccessException("playerOverlayVolume");
        }
        return null;
    }

    public final void onDestroy() {
        this.bookmarkListDelegate = null;
    }

    private final void enterAnimate(View[] viewArr, float f, Function0<Unit> function0) {
        ViewPropertyAnimator animate;
        ViewPropertyAnimator alpha;
        ViewPropertyAnimator translationY;
        ViewPropertyAnimator duration;
        ViewPropertyAnimator listener;
        for (View view : viewArr) {
            KotlinExtensionsKt.setVisible(view);
            if (view != null) {
                view.setAlpha(0.0f);
            }
            if (view != null) {
                view.setTranslationY(f);
            }
            if (!(view == null || (animate = view.animate()) == null || (alpha = animate.alpha(1.0f)) == null || (translationY = alpha.translationY(0.0f)) == null || (duration = translationY.setDuration(150)) == null || (listener = duration.setListener((Animator.AnimatorListener) null)) == null)) {
                listener.withEndAction(new VideoPlayerOverlayDelegate$$ExternalSyntheticLambda4(function0));
            }
        }
    }

    private final void exitAnimate(View[] viewArr, float f) {
        ViewPropertyAnimator animate;
        ViewPropertyAnimator alpha;
        ViewPropertyAnimator translationY;
        ViewPropertyAnimator duration;
        for (View view : viewArr) {
            if (!(view == null || (animate = view.animate()) == null || (alpha = animate.alpha(0.0f)) == null || (translationY = alpha.translationY(f)) == null || (duration = translationY.setDuration(150)) == null)) {
                duration.setListener(new VideoPlayerOverlayDelegate$exitAnimate$1$1(view));
            }
        }
    }
}
