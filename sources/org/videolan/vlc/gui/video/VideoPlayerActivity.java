package org.videolan.vlc.gui.video;

import android.app.Activity;
import android.app.PictureInPictureParams;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.os.PowerManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Rational;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.inputmethod.BaseInputConnection;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.app.BaseContextWrappingDelegate;
import androidx.appcompat.widget.PopupMenu;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.DialogFragment;
import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import androidx.lifecycle.CoroutineLiveDataKt;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import androidx.window.layout.WindowLayoutInfo;
import com.google.android.material.textfield.TextInputLayout;
import io.netty.handler.codec.rtsp.RtspHeaders;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import okhttp3.internal.cache.DiskLruCache;
import org.videolan.libvlc.Dialog;
import org.videolan.libvlc.MediaDiscoverer;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.interfaces.IMedia;
import org.videolan.libvlc.interfaces.IVLCVout;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.libvlc.util.DisplayManager;
import org.videolan.libvlc.util.VLCVideoLayout;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.resources.AndroidDevices;
import org.videolan.resources.AppContextProvider;
import org.videolan.resources.Constants;
import org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.LocaleUtilsKt;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.R;
import org.videolan.vlc.VersionDependantKt;
import org.videolan.vlc.gui.DialogActivity;
import org.videolan.vlc.gui.audio.EqualizerFragment;
import org.videolan.vlc.gui.audio.PlaylistAdapter;
import org.videolan.vlc.gui.browser.FilePickerFragmentKt;
import org.videolan.vlc.gui.dialogs.PlaybackSpeedDialog;
import org.videolan.vlc.gui.dialogs.RenderersDialog;
import org.videolan.vlc.gui.dialogs.SleepTimerDialog;
import org.videolan.vlc.gui.dialogs.adapters.VlcTrack;
import org.videolan.vlc.gui.helpers.KeycodeListener;
import org.videolan.vlc.gui.helpers.OnRepeatListener;
import org.videolan.vlc.gui.helpers.PlayerKeyListenerDelegate;
import org.videolan.vlc.gui.helpers.PlayerOptionsDelegate;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.gui.helpers.hf.StoragePermissionsDelegate;
import org.videolan.vlc.interfaces.IPlaybackSettingsController;
import org.videolan.vlc.media.PlayerController;
import org.videolan.vlc.media.PlaylistManager;
import org.videolan.vlc.media.ResumeStatus;
import org.videolan.vlc.media.WaitConfirmation;
import org.videolan.vlc.mediadb.models.ExternalSub;
import org.videolan.vlc.repository.ExternalSubRepository;
import org.videolan.vlc.repository.SlaveRepository;
import org.videolan.vlc.util.DialogDelegate;
import org.videolan.vlc.util.FileUtils;
import org.videolan.vlc.util.FrameRateManager;
import org.videolan.vlc.util.IDialogManager;
import org.videolan.vlc.util.KextensionsKt;
import org.videolan.vlc.util.Permissions;
import org.videolan.vlc.util.Util;
import org.videolan.vlc.viewmodels.BookmarkModel;
import org.videolan.vlc.viewmodels.PlaylistModel;

@Metadata(d1 = {"\u0000û\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b5\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u001e*\u0003&Ñ\u0001\b\u0016\u0018\u0000 Ä\u00032\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u00072\u00020\b2\u00020\t:\u0002Ä\u0003B\u0005¢\u0006\u0002\u0010\nJ\u0016\u0010\u0002\u001a\u00030\u00022\n\u0010\u0002\u001a\u0005\u0018\u00010\u0002H\u0016J1\u0010\u0002\u001a\u00030\u00022\n\u0010\u0002\u001a\u0005\u0018\u00010\u00022\u0007\u0010\u0002\u001a\u00020\u00152\u0007\u0010\u0002\u001a\u00020\u00152\u0007\u0010\u0002\u001a\u00020\u0015H\u0016J\n\u0010\u0002\u001a\u00030\u0002H\u0016J\u0019\u0010\u0002\u001a\u00030\u00022\u0007\u0010\u0002\u001a\u00020MH\u0000¢\u0006\u0003\b\u0002J\n\u0010\u0002\u001a\u00030\u0002H\u0002J\u0014\u0010 \u0002\u001a\u00030¡\u00022\b\u0010¢\u0002\u001a\u00030£\u0002H\u0016J\n\u0010¤\u0002\u001a\u00030\u0002H\u0016J\u0016\u0010¥\u0002\u001a\u00030\u00022\n\u0010¦\u0002\u001a\u0005\u0018\u00010§\u0002H\u0016J\u0013\u0010¨\u0002\u001a\u00020\f2\b\u0010©\u0002\u001a\u00030ª\u0002H\u0016J\u0007\u0010«\u0002\u001a\u00020\fJ\b\u0010¬\u0002\u001a\u00030\u0002J\b\u0010­\u0002\u001a\u00030\u0002J\n\u0010®\u0002\u001a\u00030\u0002H\u0002J\n\u0010¯\u0002\u001a\u00030\u0002H\u0002J\u0013\u0010°\u0002\u001a\u00030\u00022\u0007\u0010±\u0002\u001a\u00020\u0015H\u0016J\n\u0010²\u0002\u001a\u00030\u0002H\u0002J\u0014\u0010³\u0002\u001a\u00030\u00022\b\u0010¦\u0002\u001a\u00030§\u0002H\u0016J\t\u0010´\u0002\u001a\u00020\u0015H\u0002J\n\u0010µ\u0002\u001a\u00030¡\u0002H\u0016J\u000f\u0010¶\u0002\u001a\b\u0012\u0004\u0012\u00020201H\u0002J\t\u0010·\u0002\u001a\u00020\u001eH\u0016J\n\u0010¸\u0002\u001a\u00030¹\u0002H\u0016J\t\u0010º\u0002\u001a\u00020\u0015H\u0002J\u0013\u0010»\u0002\u001a\u00020\u00152\b\u0010¼\u0002\u001a\u00030\u0001H\u0007J\u0013\u0010½\u0002\u001a\u00030\u00022\u0007\u0010¾\u0002\u001a\u00020\u0015H\u0002J\b\u0010¿\u0002\u001a\u00030\u0002J\t\u0010À\u0002\u001a\u00020\fH\u0002J\n\u0010Á\u0002\u001a\u00030\u0002H\u0016J\u0015\u0010Â\u0002\u001a\u0005\u0018\u00010\u0002H\u0000¢\u0006\u0006\bÃ\u0002\u0010Ä\u0002J\n\u0010Å\u0002\u001a\u00030\u0002H\u0002J\t\u0010Æ\u0002\u001a\u00020\fH\u0016J\t\u0010Ç\u0002\u001a\u00020\fH\u0016J \u0010È\u0002\u001a\u00030\u00022\t\b\u0002\u0010É\u0002\u001a\u00020\f2\t\b\u0002\u0010Ê\u0002\u001a\u00020\fH\u0015J\u0013\u0010Ë\u0002\u001a\u00030\u00022\u0007\u0010Ë\u0002\u001a\u00020\fH\u0002J\u0018\u0010Ì\u0002\u001a\u00020\f2\u0007\u0010Í\u0002\u001a\u00020\u0015H\u0000¢\u0006\u0003\bÎ\u0002J\n\u0010Ï\u0002\u001a\u00030\u0002H\u0016J\n\u0010Ð\u0002\u001a\u00030\u0002H\u0002J(\u0010Ñ\u0002\u001a\u00030\u00022\u0007\u0010Ò\u0002\u001a\u00020\u00152\u0007\u0010±\u0002\u001a\u00020\u00152\n\u0010Ó\u0002\u001a\u0005\u0018\u00010Ô\u0002H\u0014J\n\u0010Õ\u0002\u001a\u00030\u0002H\u0016J\u0016\u0010Ö\u0002\u001a\u00030\u00022\n\u0010×\u0002\u001a\u0005\u0018\u00010±\u0001H\u0016J\u0011\u0010Ø\u0002\u001a\u00030\u00022\u0007\u0010Ù\u0002\u001a\u00020)J\u0014\u0010Ú\u0002\u001a\u00030\u00022\b\u0010Û\u0002\u001a\u00030±\u0001H\u0016J\u0012\u0010Ü\u0002\u001a\u00030\u00022\b\u0010Û\u0002\u001a\u00030±\u0001J\u0014\u0010Ý\u0002\u001a\u00030\u00022\n\u0010Û\u0002\u001a\u0005\u0018\u00010±\u0001J\u0014\u0010Þ\u0002\u001a\u00030\u00022\b\u0010ß\u0002\u001a\u00030£\u0002H\u0016J\u0016\u0010à\u0002\u001a\u00030\u00022\n\u0010á\u0002\u001a\u0005\u0018\u00010â\u0002H\u0015J\n\u0010ã\u0002\u001a\u00030\u0002H\u0014J\u001c\u0010ä\u0002\u001a\u00020\f2\u0007\u0010Í\u0002\u001a\u00020\u00152\b\u0010©\u0002\u001a\u00030å\u0002H\u0016J\u0013\u0010æ\u0002\u001a\u00020\f2\b\u0010Û\u0002\u001a\u00030±\u0001H\u0016J\u0014\u0010ç\u0002\u001a\u00030\u00022\b\u0010©\u0002\u001a\u00030è\u0002H\u0016J\u0014\u0010é\u0002\u001a\u00030\u00022\b\u0010©\u0002\u001a\u00030ê\u0002H\u0016J\u0014\u0010ë\u0002\u001a\u00030\u00022\b\u0010ì\u0002\u001a\u00030Ô\u0002H\u0014J\n\u0010í\u0002\u001a\u00030\u0002H\u0015J\u0013\u0010î\u0002\u001a\u00030\u00022\u0007\u0010Æ\u0002\u001a\u00020\fH\u0016J\n\u0010ï\u0002\u001a\u00030\u0002H\u0002J)\u0010ð\u0002\u001a\u00030\u00022\b\u0010ñ\u0002\u001a\u00030±\u00012\u0007\u0010ò\u0002\u001a\u00020\u00152\n\u0010ó\u0002\u001a\u0005\u0018\u00010§\u0001H\u0016J\n\u0010ô\u0002\u001a\u00030\u0002H\u0014J\u0014\u0010õ\u0002\u001a\u00030\u00022\b\u0010ö\u0002\u001a\u00030â\u0002H\u0014J\u0013\u0010÷\u0002\u001a\u00030\u00022\u0007\u0010ò\u0002\u001a\u00020\u0015H\u0016J\u0016\u0010ø\u0002\u001a\u00030\u00022\n\u0010Ê\u0001\u001a\u0005\u0018\u00010Ë\u0001H\u0016J\n\u0010ù\u0002\u001a\u00030\u0002H\u0014J\n\u0010ú\u0002\u001a\u00030\u0002H\u0015J\n\u0010û\u0002\u001a\u00030\u0002H\u0016J1\u0010ü\u0002\u001a\u00030\u00022\n\u0010\u0002\u001a\u0005\u0018\u00010\u00022\u0007\u0010\u0002\u001a\u00020\u00152\u0007\u0010ý\u0002\u001a\u00020\u00152\u0007\u0010\u0002\u001a\u00020\u0015H\u0016J\u0013\u0010þ\u0002\u001a\u00020\f2\b\u0010©\u0002\u001a\u00030ª\u0002H\u0016J\u0013\u0010ÿ\u0002\u001a\u00020\f2\b\u0010©\u0002\u001a\u00030ª\u0002H\u0016J\n\u0010\u0003\u001a\u00030\u0002H\u0015J\n\u0010\u0003\u001a\u00030\u0002H\u0016J\u0013\u0010\u0003\u001a\u00030\u00022\u0007\u0010\u0003\u001a\u00020\fH\u0016J\n\u0010\u0003\u001a\u00030\u0002H\u0002J\b\u0010\u0003\u001a\u00030\u0002J\u001d\u0010\u0003\u001a\u00030\u00022\u0007\u0010ò\u0002\u001a\u00020\u00152\b\u0010ó\u0002\u001a\u00030§\u0001H\u0016J\n\u0010\u0003\u001a\u00030\u0002H\u0016J\n\u0010\u0003\u001a\u00030\u0002H\u0016J\n\u0010\u0003\u001a\u00030\u0002H\u0002J\n\u0010\u0003\u001a\u00030\u0002H\u0016J\u000f\u0010\u0003\u001a\u0004\u0018\u00010\f¢\u0006\u0003\u0010\u0003J\n\u0010\u0003\u001a\u00030\u0002H\u0002J\n\u0010\u0003\u001a\u00030\u0002H\u0002J\u0013\u0010\u0003\u001a\u00030\u00022\u0007\u0010\u0002\u001a\u00020\u0015H\u0016J\u001c\u0010\u0003\u001a\u00030\u00022\u0007\u0010ò\u0002\u001a\u00020K2\t\b\u0002\u0010\u0003\u001a\u00020\fJ'\u0010\u0003\u001a\u00030\u00022\u0007\u0010ò\u0002\u001a\u00020K2\t\b\u0002\u0010\u0003\u001a\u00020\f2\t\b\u0002\u0010\u0003\u001a\u00020\fJ8\u0010\u0003\u001a\u00030\u00022\u0007\u0010ò\u0002\u001a\u00020K2\u0007\u0010\u0003\u001a\u00020K2\t\b\u0002\u0010\u0003\u001a\u00020\f2\t\b\u0002\u0010\u0003\u001a\u00020\fH\u0000¢\u0006\u0003\b\u0003J+\u0010\u0003\u001a\u00030\u00022\u0007\u0010\u0003\u001a\u00020\u00152\u0007\u0010\u0003\u001a\u00020\u00152\u0007\u0010\u0003\u001a\u00020\u0015H\u0000¢\u0006\u0003\b\u0003J\u0019\u0010\u0003\u001a\u00030\u00022\u0007\u0010\u0002\u001a\u00020\u0015H\u0000¢\u0006\u0003\b\u0003J\n\u0010\u0003\u001a\u00030\u0002H\u0002J\u0011\u0010\u0003\u001a\u00030\u00022\u0007\u0010\u0003\u001a\u00020\u0015J\u0014\u0010\u0003\u001a\u00030\u00022\b\u0010\u0003\u001a\u00030 \u0003H\u0016J\n\u0010¡\u0003\u001a\u00030\u0002H\u0002J\u0013\u0010¢\u0003\u001a\u00030\u00022\u0007\u0010£\u0003\u001a\u00020MH\u0002J\n\u0010¤\u0003\u001a\u00030\u0002H\u0016J\u0014\u0010¥\u0003\u001a\u00030\u00022\b\u0010¦\u0003\u001a\u00030§\u0003H\u0002J\n\u0010¨\u0003\u001a\u00030\u0002H\u0016J\n\u0010©\u0003\u001a\u00030\u0002H\u0002J\n\u0010ª\u0003\u001a\u00030\u0002H\u0002J\u001d\u0010«\u0003\u001a\u00030\u00022\b\u0010¬\u0003\u001a\u00030¡\u00022\u0007\u0010Ù\u0002\u001a\u00020\u0015H\u0002J\n\u0010­\u0003\u001a\u00030\u0002H\u0002J\n\u0010®\u0003\u001a\u00030\u0002H\u0003J\n\u0010¯\u0003\u001a\u00030\u0002H\u0016J\n\u0010°\u0003\u001a\u00030\u0002H\u0002J\n\u0010±\u0003\u001a\u00030\u0002H\u0002J\u0011\u0010²\u0003\u001a\u00030\u00022\u0007\u0010³\u0003\u001a\u00020\fJ\n\u0010ã\u0001\u001a\u00030\u0002H\u0007J\b\u0010´\u0003\u001a\u00030\u0002J\u0013\u0010µ\u0003\u001a\u00030\u00022\u0007\u0010¶\u0003\u001a\u00020\fH\u0002J\b\u0010·\u0003\u001a\u00030\u0002J\b\u0010¸\u0003\u001a\u00030\u0002J\n\u0010¹\u0003\u001a\u00030\u0002H\u0016J\b\u0010º\u0003\u001a\u00030\u0002J\n\u0010»\u0003\u001a\u00030\u0002H\u0016J\n\u0010¼\u0003\u001a\u00030\u0002H\u0002J\b\u0010½\u0003\u001a\u00030\u0002J)\u0010¾\u0003\u001a\u00020\f2\u0007\u0010¿\u0003\u001a\u00020M2\u0007\u0010À\u0003\u001a\u00020M2\u0006\u0010L\u001a\u00020MH\u0000¢\u0006\u0003\bÁ\u0003J\n\u0010Â\u0003\u001a\u00030\u0002H\u0002J\n\u0010Ã\u0003\u001a\u00030\u0002H\u0002R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u0010X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u0015@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001e\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0014\u001a\u00020\u0019@BX.¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001f\u001a\u00020 X.¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u0010\u0010%\u001a\u00020&X\u0004¢\u0006\u0004\n\u0002\u0010'R\u000e\u0010(\u001a\u00020)X\u000e¢\u0006\u0002\n\u0000R\u0011\u0010*\u001a\u00020+8F¢\u0006\u0006\u001a\u0004\b,\u0010-R\u000e\u0010.\u001a\u00020)X\u000e¢\u0006\u0002\n\u0000R\"\u0010/\u001a\u0016\u0012\u0004\u0012\u00020)\u0012\n\u0012\b\u0012\u0004\u0012\u00020201\u0018\u000100X\u000e¢\u0006\u0002\n\u0000R\u001b\u00103\u001a\u0002048FX\u0002¢\u0006\f\n\u0004\b7\u00108\u001a\u0004\b5\u00106R\u000e\u00109\u001a\u00020:X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010;\u001a\u00020<X.¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R\u001c\u0010A\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f01\u0018\u00010BX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010C\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f010DX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010E\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010G\"\u0004\bH\u0010IR\u000e\u0010J\u001a\u00020KX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010L\u001a\u00020MX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bN\u0010O\"\u0004\bP\u0010QR\u0011\u0010R\u001a\u00020S¢\u0006\b\n\u0000\u001a\u0004\bT\u0010UR\u001a\u0010V\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bW\u0010G\"\u0004\bX\u0010IR\u001e\u0010Y\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\f@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\bZ\u0010GR\u001a\u0010[\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b[\u0010G\"\u0004\b\\\u0010IR\u000e\u0010]\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010^\u001a\u00020\f8CX\u0004¢\u0006\u0006\u001a\u0004\b^\u0010GR\u001e\u0010_\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\f@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b`\u0010GR\u001a\u0010a\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\ba\u0010G\"\u0004\bb\u0010IR\u000e\u0010c\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010d\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bd\u0010G\"\u0004\be\u0010IR\u0014\u0010f\u001a\u00020\f8@X\u0004¢\u0006\u0006\u001a\u0004\bg\u0010GR\u0014\u0010h\u001a\u00020\f8BX\u0004¢\u0006\u0006\u001a\u0004\bh\u0010GR\u0014\u0010i\u001a\u00020\f8@X\u0004¢\u0006\u0006\u001a\u0004\bj\u0010GR\u000e\u0010k\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u0011\u0010l\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\bl\u0010GR\u001a\u0010m\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bm\u0010G\"\u0004\bn\u0010IR\u001a\u0010o\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bo\u0010G\"\u0004\bp\u0010IR\u001a\u0010q\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bq\u0010G\"\u0004\br\u0010IR\u000e\u0010s\u001a\u00020)X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010t\u001a\u00020)X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010u\u001a\u00020KX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010v\u001a\u0004\u0018\u00010wX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010x\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\by\u0010G\"\u0004\bz\u0010IR\u001b\u0010{\u001a\u00020|X.¢\u0006\u000f\n\u0000\u001a\u0004\b}\u0010~\"\u0005\b\u0010\u0001R\u001e\u0010\u0001\u001a\u00020\u0015X\u000e¢\u0006\u0011\n\u0000\u001a\u0005\b\u0001\u0010\u0018\"\u0006\b\u0001\u0010\u0001R\u0012\u0010\u0001\u001a\u0005\u0018\u00010\u0001X\u000e¢\u0006\u0002\n\u0000R \u0010\u0001\u001a\u00030\u00018FX\u0002¢\u0006\u000f\n\u0005\b\u0001\u00108\u001a\u0006\b\u0001\u0010\u0001R \u0010\u0001\u001a\u00030\u0001X.¢\u0006\u0012\n\u0000\u001a\u0006\b\u0001\u0010\u0001\"\u0006\b\u0001\u0010\u0001R\u001d\u0010\u0001\u001a\u00020MX\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0001\u0010O\"\u0005\b\u0001\u0010QR \u0010\u0001\u001a\u00030\u00018FX\u0002¢\u0006\u000f\n\u0005\b\u0001\u00108\u001a\u0006\b\u0001\u0010\u0001R\u000f\u0010\u0001\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R \u0010\u0001\u001a\u00030\u00018BX\u0002¢\u0006\u000f\n\u0005\b\u0001\u00108\u001a\u0006\b\u0001\u0010\u0001R\"\u0010 \u0001\u001a\u0005\u0018\u00010¡\u0001X\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b¢\u0001\u0010£\u0001\"\u0006\b¤\u0001\u0010¥\u0001R!\u0010¦\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030§\u0001010D¢\u0006\n\n\u0000\u001a\u0006\b¨\u0001\u0010©\u0001R\u0011\u0010ª\u0001\u001a\u0004\u0018\u00010)X\u000e¢\u0006\u0002\n\u0000R \u0010«\u0001\u001a\u00030¬\u00018FX\u0002¢\u0006\u000f\n\u0005\b¯\u0001\u00108\u001a\u0006\b­\u0001\u0010®\u0001R\"\u0010°\u0001\u001a\u0005\u0018\u00010±\u0001X\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b²\u0001\u0010³\u0001\"\u0006\b´\u0001\u0010µ\u0001R\u001e\u0010¶\u0001\u001a\u00020\u0015X\u000e¢\u0006\u0011\n\u0000\u001a\u0005\b·\u0001\u0010\u0018\"\u0006\b¸\u0001\u0010\u0001R5\u0010¹\u0001\u001a\u0018\u0012\u0005\u0012\u00030§\u0001\u0018\u00010\u000ej\u000b\u0012\u0005\u0012\u00030§\u0001\u0018\u0001`\u0010X\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bº\u0001\u0010»\u0001\"\u0006\b¼\u0001\u0010½\u0001R\u000f\u0010¾\u0001\u001a\u00020KX\u000e¢\u0006\u0002\n\u0000R\u0016\u0010¿\u0001\u001a\u00020\u00158BX\u0004¢\u0006\u0007\u001a\u0005\bÀ\u0001\u0010\u0018R \u0010Á\u0001\u001a\u00030Â\u00018FX\u0002¢\u0006\u000f\n\u0005\bÅ\u0001\u00108\u001a\u0006\bÃ\u0001\u0010Ä\u0001R\u0015\u0010Æ\u0001\u001a\u00030Ç\u0001¢\u0006\n\n\u0000\u001a\u0006\bÈ\u0001\u0010É\u0001R\"\u0010Ê\u0001\u001a\u0005\u0018\u00010Ë\u0001X\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bÌ\u0001\u0010Í\u0001\"\u0006\bÎ\u0001\u0010Ï\u0001R\u0013\u0010Ð\u0001\u001a\u00030Ñ\u0001X\u0004¢\u0006\u0005\n\u0003\u0010Ò\u0001R \u0010Ó\u0001\u001a\u00030Ô\u0001X.¢\u0006\u0012\n\u0000\u001a\u0006\bÕ\u0001\u0010Ö\u0001\"\u0006\b×\u0001\u0010Ø\u0001R\u0010\u0010Ù\u0001\u001a\u00030Ú\u0001X.¢\u0006\u0002\n\u0000R \u0010Û\u0001\u001a\u00030Ü\u00018FX\u0002¢\u0006\u000f\n\u0005\bß\u0001\u00108\u001a\u0006\bÝ\u0001\u0010Þ\u0001R\u0011\u0010à\u0001\u001a\u0004\u0018\u00010)X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010á\u0001\u001a\u00030â\u0001X\u0004¢\u0006\u0002\n\u0000R\u000f\u0010ã\u0001\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000f\u0010ä\u0001\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010å\u0001\u001a\u00020K8F¢\u0006\b\u001a\u0006\bæ\u0001\u0010ç\u0001R \u0010è\u0001\u001a\u00030é\u00018FX\u0002¢\u0006\u000f\n\u0005\bì\u0001\u00108\u001a\u0006\bê\u0001\u0010ë\u0001R \u0010í\u0001\u001a\u00030î\u0001X.¢\u0006\u0012\n\u0000\u001a\u0006\bï\u0001\u0010ð\u0001\"\u0006\bñ\u0001\u0010ò\u0001R\"\u0010ó\u0001\u001a\u0005\u0018\u00010ô\u0001X\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bõ\u0001\u0010ö\u0001\"\u0006\b÷\u0001\u0010ø\u0001R\u0012\u0010ù\u0001\u001a\u0005\u0018\u00010ú\u0001X\u000e¢\u0006\u0002\n\u0000R\"\u0010û\u0001\u001a\u0005\u0018\u00010ü\u0001X\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bý\u0001\u0010þ\u0001\"\u0006\bÿ\u0001\u0010\u0002R\u000f\u0010\u0002\u001a\u00020\u0015X\u000e¢\u0006\u0002\n\u0000R\u001d\u0010\u0002\u001a\u00020MX\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0002\u0010O\"\u0005\b\u0002\u0010QR\u001d\u0010\u0002\u001a\u00020\fX\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0002\u0010G\"\u0005\b\u0002\u0010IR\u000f\u0010\u0002\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0002\u001a\u0005\u0018\u00010\u0002X\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0002\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R \u0010\u0002\u001a\u00030\u0002X.¢\u0006\u0012\n\u0000\u001a\u0006\b\u0002\u0010\u0002\"\u0006\b\u0002\u0010\u0002¨\u0006Å\u0003"}, d2 = {"Lorg/videolan/vlc/gui/video/VideoPlayerActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lorg/videolan/vlc/PlaybackService$Callback;", "Lorg/videolan/vlc/gui/audio/PlaylistAdapter$IPlayer;", "Landroid/view/View$OnClickListener;", "Landroid/view/View$OnLongClickListener;", "Lorg/videolan/vlc/gui/helpers/hf/StoragePermissionsDelegate$CustomActionController;", "Landroid/text/TextWatcher;", "Lorg/videolan/vlc/util/IDialogManager;", "Lorg/videolan/vlc/gui/helpers/KeycodeListener;", "()V", "addNextTrack", "", "addedExternalSubs", "Ljava/util/ArrayList;", "Lorg/videolan/vlc/mediadb/models/ExternalSub;", "Lkotlin/collections/ArrayList;", "alertDialog", "Landroidx/appcompat/app/AlertDialog;", "askResume", "<set-?>", "", "audioMax", "getAudioMax$vlc_android_release", "()I", "Landroid/media/AudioManager;", "audiomanager", "getAudiomanager$vlc_android_release", "()Landroid/media/AudioManager;", "baseContextWrappingDelegate", "Landroidx/appcompat/app/AppCompatDelegate;", "bookmarkModel", "Lorg/videolan/vlc/viewmodels/BookmarkModel;", "getBookmarkModel", "()Lorg/videolan/vlc/viewmodels/BookmarkModel;", "setBookmarkModel", "(Lorg/videolan/vlc/viewmodels/BookmarkModel;)V", "btReceiver", "org/videolan/vlc/gui/video/VideoPlayerActivity$btReceiver$1", "Lorg/videolan/vlc/gui/video/VideoPlayerActivity$btReceiver$1;", "currentAudioTrack", "", "currentScaleType", "Lorg/videolan/libvlc/MediaPlayer$ScaleType;", "getCurrentScaleType", "()Lorg/videolan/libvlc/MediaPlayer$ScaleType;", "currentSpuTrack", "currentTracks", "Lkotlin/Pair;", "", "Lorg/videolan/libvlc/interfaces/IMedia$Track;", "delayDelegate", "Lorg/videolan/vlc/gui/video/VideoDelayDelegate;", "getDelayDelegate", "()Lorg/videolan/vlc/gui/video/VideoDelayDelegate;", "delayDelegate$delegate", "Lkotlin/Lazy;", "dialogsDelegate", "Lorg/videolan/vlc/util/DialogDelegate;", "displayManager", "Lorg/videolan/libvlc/util/DisplayManager;", "getDisplayManager", "()Lorg/videolan/libvlc/util/DisplayManager;", "setDisplayManager", "(Lorg/videolan/libvlc/util/DisplayManager;)V", "downloadedSubtitleLiveData", "Landroidx/lifecycle/LiveData;", "downloadedSubtitleObserver", "Landroidx/lifecycle/Observer;", "enableCloneMode", "getEnableCloneMode", "()Z", "setEnableCloneMode", "(Z)V", "forcedTime", "", "fov", "", "getFov$vlc_android_release", "()F", "setFov$vlc_android_release", "(F)V", "handler", "Landroid/os/Handler;", "getHandler", "()Landroid/os/Handler;", "hasPhysicalNotch", "getHasPhysicalNotch", "setHasPhysicalNotch", "isAudioBoostEnabled", "isAudioBoostEnabled$vlc_android_release", "isBenchmark", "setBenchmark", "isDragging", "isInteractive", "isLoading", "isLoading$vlc_android_release", "isLocked", "setLocked", "isMute", "isNavMenu", "setNavMenu", "isOnPrimaryDisplay", "isOnPrimaryDisplay$vlc_android_release", "isOptionsListShowing", "isPlaybackSettingActive", "isPlaybackSettingActive$vlc_android_release", "isPlaying", "isPlaylistVisible", "isShowing", "setShowing", "isShowingDialog", "setShowingDialog", "isTv", "setTv", "lastAudioTrack", "lastSpuTrack", "lastTime", "loadingImageView", "Landroid/widget/ImageView;", "lockBackButton", "getLockBackButton", "setLockBackButton", "medialibrary", "Lorg/videolan/medialibrary/interfaces/Medialibrary;", "getMedialibrary", "()Lorg/videolan/medialibrary/interfaces/Medialibrary;", "setMedialibrary", "(Lorg/videolan/medialibrary/interfaces/Medialibrary;)V", "menuIdx", "getMenuIdx", "setMenuIdx", "(I)V", "optionsDelegate", "Lorg/videolan/vlc/gui/helpers/PlayerOptionsDelegate;", "orientationDelegate", "Lorg/videolan/vlc/gui/video/VideoPlayerOrientationDelegate;", "getOrientationDelegate", "()Lorg/videolan/vlc/gui/video/VideoPlayerOrientationDelegate;", "orientationDelegate$delegate", "orientationMode", "Lorg/videolan/vlc/gui/video/PlayerOrientationMode;", "getOrientationMode", "()Lorg/videolan/vlc/gui/video/PlayerOrientationMode;", "setOrientationMode", "(Lorg/videolan/vlc/gui/video/PlayerOrientationMode;)V", "originalVol", "getOriginalVol$vlc_android_release", "setOriginalVol$vlc_android_release", "overlayDelegate", "Lorg/videolan/vlc/gui/video/VideoPlayerOverlayDelegate;", "getOverlayDelegate", "()Lorg/videolan/vlc/gui/video/VideoPlayerOverlayDelegate;", "overlayDelegate$delegate", "playbackStarted", "playerKeyListenerDelegate", "Lorg/videolan/vlc/gui/helpers/PlayerKeyListenerDelegate;", "getPlayerKeyListenerDelegate", "()Lorg/videolan/vlc/gui/helpers/PlayerKeyListenerDelegate;", "playerKeyListenerDelegate$delegate", "playlistModel", "Lorg/videolan/vlc/viewmodels/PlaylistModel;", "getPlaylistModel", "()Lorg/videolan/vlc/viewmodels/PlaylistModel;", "setPlaylistModel", "(Lorg/videolan/vlc/viewmodels/PlaylistModel;)V", "playlistObserver", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "getPlaylistObserver", "()Landroidx/lifecycle/Observer;", "previousMediaPath", "resizeDelegate", "Lorg/videolan/vlc/gui/video/VideoPlayerResizeDelegate;", "getResizeDelegate", "()Lorg/videolan/vlc/gui/video/VideoPlayerResizeDelegate;", "resizeDelegate$delegate", "rootView", "Landroid/view/View;", "getRootView", "()Landroid/view/View;", "setRootView", "(Landroid/view/View;)V", "savedMediaIndex", "getSavedMediaIndex", "setSavedMediaIndex", "savedMediaList", "getSavedMediaList", "()Ljava/util/ArrayList;", "setSavedMediaList", "(Ljava/util/ArrayList;)V", "savedTime", "screenRotation", "getScreenRotation", "screenshotDelegate", "Lorg/videolan/vlc/gui/video/VideoPlayerScreenshotDelegate;", "getScreenshotDelegate", "()Lorg/videolan/vlc/gui/video/VideoPlayerScreenshotDelegate;", "screenshotDelegate$delegate", "seekListener", "Landroid/widget/SeekBar$OnSeekBarChangeListener;", "getSeekListener", "()Landroid/widget/SeekBar$OnSeekBarChangeListener;", "service", "Lorg/videolan/vlc/PlaybackService;", "getService", "()Lorg/videolan/vlc/PlaybackService;", "setService", "(Lorg/videolan/vlc/PlaybackService;)V", "serviceReceiver", "org/videolan/vlc/gui/video/VideoPlayerActivity$serviceReceiver$1", "Lorg/videolan/vlc/gui/video/VideoPlayerActivity$serviceReceiver$1;", "settings", "Landroid/content/SharedPreferences;", "getSettings", "()Landroid/content/SharedPreferences;", "setSettings", "(Landroid/content/SharedPreferences;)V", "startedScope", "Lkotlinx/coroutines/CoroutineScope;", "statsDelegate", "Lorg/videolan/vlc/gui/video/VideoStatsDelegate;", "getStatsDelegate", "()Lorg/videolan/vlc/gui/video/VideoStatsDelegate;", "statsDelegate$delegate", "subtitlesExtraPath", "switchAudioRunnable", "Ljava/lang/Runnable;", "switchToPopup", "switchingView", "time", "getTime", "()J", "tipsDelegate", "Lorg/videolan/vlc/gui/video/VideoTipsDelegate;", "getTipsDelegate", "()Lorg/videolan/vlc/gui/video/VideoTipsDelegate;", "tipsDelegate$delegate", "touchDelegate", "Lorg/videolan/vlc/gui/video/VideoTouchDelegate;", "getTouchDelegate", "()Lorg/videolan/vlc/gui/video/VideoTouchDelegate;", "setTouchDelegate", "(Lorg/videolan/vlc/gui/video/VideoTouchDelegate;)V", "videoLayout", "Lorg/videolan/libvlc/util/VLCVideoLayout;", "getVideoLayout", "()Lorg/videolan/libvlc/util/VLCVideoLayout;", "setVideoLayout", "(Lorg/videolan/libvlc/util/VLCVideoLayout;)V", "videoRemoteJob", "Lkotlinx/coroutines/Job;", "videoUri", "Landroid/net/Uri;", "getVideoUri", "()Landroid/net/Uri;", "setVideoUri", "(Landroid/net/Uri;)V", "volSave", "volume", "getVolume$vlc_android_release", "setVolume$vlc_android_release", "waitingForPin", "getWaitingForPin", "setWaitingForPin", "warnMetered", "warningToast", "Landroid/widget/Toast;", "wasPaused", "windowLayoutInfo", "Landroidx/window/layout/WindowLayoutInfo;", "getWindowLayoutInfo", "()Landroidx/window/layout/WindowLayoutInfo;", "setWindowLayoutInfo", "(Landroidx/window/layout/WindowLayoutInfo;)V", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "", "start", "count", "after", "bookmark", "changeBrightness", "delta", "changeBrightness$vlc_android_release", "cleanUI", "createConfigurationContext", "Landroid/content/Context;", "overrideConfiguration", "Landroid/content/res/Configuration;", "decreaseRate", "dialogCanceled", "dialog", "Lorg/videolan/libvlc/Dialog;", "dispatchGenericMotionEvent", "event", "Landroid/view/MotionEvent;", "displayResize", "displayWarningToast", "doPlayPause", "enableSubs", "encounteredError", "exit", "resultCode", "exitOK", "fireDialog", "generateTouchFlags", "getApplicationContext", "getCurrentMediaTracks", "getDelegate", "getLifeCycle", "Landroidx/lifecycle/Lifecycle;", "getOrientationForLock", "getScreenOrientation", "mode", "handleVout", "voutCount", "hideOptions", "hideSearchField", "increaseRate", "initAudioVolume", "initAudioVolume$vlc_android_release", "()Lkotlin/Unit;", "initUI", "isInPictureInPictureMode", "isReady", "loadMedia", "fromStart", "forceUsingNew", "mute", "navigateDvdMenu", "keyCode", "navigateDvdMenu$vlc_android_release", "next", "observeDownloadedSubtitles", "onActivityResult", "requestCode", "data", "Landroid/content/Intent;", "onAttachedToWindow", "onAudioSubClick", "anchor", "onChangedControlSetting", "key", "onClick", "v", "onClickDismissTips", "onClickNextTips", "onConfigurationChanged", "newConfig", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onKeyDown", "Landroid/view/KeyEvent;", "onLongClick", "onMediaEvent", "Lorg/videolan/libvlc/interfaces/IMedia$Event;", "onMediaPlayerEvent", "Lorg/videolan/libvlc/MediaPlayer$Event;", "onNewIntent", "intent", "onPause", "onPictureInPictureModeChanged", "onPlaying", "onPopupMenu", "view", "position", "item", "onResume", "onSaveInstanceState", "outState", "onSelectionSet", "onServiceChanged", "onStart", "onStop", "onStorageAccessGranted", "onTextChanged", "before", "onTouchEvent", "onTrackballEvent", "onUserLeaveHint", "onVisibleBehindCanceled", "onWindowFocusChanged", "hasFocus", "pause", "play", "playItem", "previous", "recreate", "removeDownloadedSubtitlesObserver", "resetRate", "resizeVideo", "()Ljava/lang/Boolean;", "restoreBrightness", "saveBrightness", "seek", "fromUser", "fast", "length", "seek$vlc_android_release", "sendMouseEvent", "action", "x", "y", "sendMouseEvent$vlc_android_release", "setAudioVolume", "setAudioVolume$vlc_android_release", "setESTracks", "setOrientation", "value", "setPictureInPictureParams", "params", "Landroid/app/PictureInPictureParams;", "setPlaybackParameters", "setWindowBrightness", "brightness", "showAdvancedOptions", "showConfirmResumeDialog", "confirmation", "Lorg/videolan/vlc/media/WaitConfirmation;", "showEqualizer", "showNavMenu", "showTitle", "simulateKeyPress", "context", "startLoading", "startPlayback", "stop", "stopLoading", "stopPlayback", "switchToAudioMode", "showUI", "takeScreenshot", "toggleBtDelay", "connected", "toggleLock", "toggleOrientationLock", "togglePlayPause", "toggleTimeDisplay", "update", "updateMute", "updateNavStatus", "updateViewpoint", "yaw", "pitch", "updateViewpoint$vlc_android_release", "volumeDown", "volumeUp", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoPlayerActivity.kt */
public class VideoPlayerActivity extends AppCompatActivity implements PlaybackService.Callback, PlaylistAdapter.IPlayer, View.OnClickListener, View.OnLongClickListener, StoragePermissionsDelegate.CustomActionController, TextWatcher, IDialogManager, KeycodeListener {
    private static final String ACTION_RESULT = Constants.buildPkgString("player.result");
    private static final int AUDIO_SERVICE_CONNECTION_FAILED = 4;
    private static final int CHECK_VIDEO_TRACKS = 6;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final float DEFAULT_FOV = 80.0f;
    private static final String EXTRA_DURATION = "extra_duration";
    private static final String EXTRA_POSITION = "extra_position";
    private static final String EXTRA_URI = "extra_uri";
    public static final int FADE_OUT = 1;
    public static final int FADE_OUT_BRIGHTNESS_INFO = 12;
    public static final int FADE_OUT_INFO = 2;
    public static final int FADE_OUT_SCREENSHOT = 14;
    public static final int FADE_OUT_VOLUME_INFO = 13;
    public static final String FROM_EXTERNAL = "from_external";
    public static final int HIDE_INFO = 9;
    public static final int HIDE_SEEK = 10;
    public static final int HIDE_SETTINGS = 11;
    public static final String KEY_BLUETOOTH_DELAY = "key_bluetooth_delay";
    private static final String KEY_LIST = "saved_list";
    private static final String KEY_MEDIA_INDEX = "media_index";
    private static final String KEY_MEDIA_LIST = "media_list";
    private static final String KEY_REMAINING_TIME_DISPLAY = "remaining_time_display";
    private static final String KEY_TIME = "saved_time";
    private static final String KEY_URI = "saved_uri";
    private static final int LOADING_ANIMATION = 7;
    private static final int LOADING_ANIMATION_DELAY = 1000;
    public static final int OVERLAY_INFINITE = -1;
    private static final int RESET_BACK_LOCK = 5;
    private static final int RESULT_CONNECTION_FAILED = 2;
    private static final int RESULT_PLAYBACK_ERROR = 3;
    private static final int RESULT_VIDEO_TRACK_LOST = 4;
    public static final int SHOW_INFO = 8;
    private static final int START_PLAYBACK = 3;
    private static final String TAG = "VLC/VideoPlayerActivity";
    private static Boolean clone;
    /* access modifiers changed from: private */
    public static volatile boolean sDisplayRemainingTime;
    /* access modifiers changed from: private */
    public static final MutableStateFlow<String> videoRemoteFlow = StateFlowKt.MutableStateFlow(null);
    /* access modifiers changed from: private */
    public boolean addNextTrack;
    private final ArrayList<ExternalSub> addedExternalSubs = new ArrayList<>();
    private AlertDialog alertDialog;
    private boolean askResume = true;
    private int audioMax;
    private AudioManager audiomanager;
    private AppCompatDelegate baseContextWrappingDelegate;
    public BookmarkModel bookmarkModel;
    private final VideoPlayerActivity$btReceiver$1 btReceiver = new VideoPlayerActivity$btReceiver$1(this);
    /* access modifiers changed from: private */
    public String currentAudioTrack = "-2";
    /* access modifiers changed from: private */
    public String currentSpuTrack = "-2";
    private Pair<String, ? extends List<? extends IMedia.Track>> currentTracks;
    private final Lazy delayDelegate$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new VideoPlayerActivity$delayDelegate$2(this));
    private final DialogDelegate dialogsDelegate = new DialogDelegate();
    public DisplayManager displayManager;
    private LiveData<List<ExternalSub>> downloadedSubtitleLiveData;
    private final Observer<List<ExternalSub>> downloadedSubtitleObserver = new VideoPlayerActivity$$ExternalSyntheticLambda20(this);
    private boolean enableCloneMode;
    private long forcedTime = -1;
    private float fov;
    private final Handler handler = new VideoPlayerActivity$handler$1(this, Looper.getMainLooper());
    private boolean hasPhysicalNotch;
    private boolean isAudioBoostEnabled;
    private boolean isBenchmark;
    /* access modifiers changed from: private */
    public boolean isDragging;
    private boolean isLoading;
    private boolean isLocked;
    private boolean isMute;
    private boolean isNavMenu;
    private boolean isPlaying;
    private boolean isShowing;
    private boolean isShowingDialog;
    private boolean isTv;
    private String lastAudioTrack = "-2";
    /* access modifiers changed from: private */
    public String lastSpuTrack = "-2";
    private long lastTime = -1;
    private ImageView loadingImageView;
    private boolean lockBackButton;
    public Medialibrary medialibrary;
    private int menuIdx = -1;
    /* access modifiers changed from: private */
    public PlayerOptionsDelegate optionsDelegate;
    private final Lazy orientationDelegate$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new VideoPlayerActivity$orientationDelegate$2(this));
    public PlayerOrientationMode orientationMode;
    private float originalVol;
    private final Lazy overlayDelegate$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new VideoPlayerActivity$overlayDelegate$2(this));
    private boolean playbackStarted;
    private final Lazy playerKeyListenerDelegate$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new VideoPlayerActivity$playerKeyListenerDelegate$2(this));
    private PlaylistModel playlistModel;
    private final Observer<List<MediaWrapper>> playlistObserver = new VideoPlayerActivity$$ExternalSyntheticLambda18(this);
    private String previousMediaPath;
    private final Lazy resizeDelegate$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new VideoPlayerActivity$resizeDelegate$2(this));
    private View rootView;
    private int savedMediaIndex;
    private ArrayList<MediaWrapper> savedMediaList;
    private long savedTime = -1;
    private final Lazy screenshotDelegate$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new VideoPlayerActivity$screenshotDelegate$2(this));
    private final SeekBar.OnSeekBarChangeListener seekListener = new VideoPlayerActivity$seekListener$1(this);
    private PlaybackService service;
    private final VideoPlayerActivity$serviceReceiver$1 serviceReceiver = new VideoPlayerActivity$serviceReceiver$1(this);
    public SharedPreferences settings;
    private CoroutineScope startedScope;
    private final Lazy statsDelegate$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new VideoPlayerActivity$statsDelegate$2(this));
    private String subtitlesExtraPath;
    private final Runnable switchAudioRunnable = new VideoPlayerActivity$$ExternalSyntheticLambda19(this);
    private boolean switchToPopup;
    private boolean switchingView;
    private final Lazy tipsDelegate$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new VideoPlayerActivity$tipsDelegate$2(this));
    public VideoTouchDelegate touchDelegate;
    private VLCVideoLayout videoLayout;
    private Job videoRemoteJob;
    private Uri videoUri;
    private int volSave;
    private float volume;
    private boolean waitingForPin;
    private boolean warnMetered;
    private Toast warningToast;
    private boolean wasPaused;
    public WindowLayoutInfo windowLayoutInfo;

    /* access modifiers changed from: private */
    public static final void takeScreenshot$lambda$17() {
    }

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public boolean isReady() {
        return true;
    }

    public final boolean getHasPhysicalNotch() {
        return this.hasPhysicalNotch;
    }

    public final void setHasPhysicalNotch(boolean z) {
        this.hasPhysicalNotch = z;
    }

    public final PlaybackService getService() {
        return this.service;
    }

    public final void setService(PlaybackService playbackService) {
        this.service = playbackService;
    }

    public final Medialibrary getMedialibrary() {
        Medialibrary medialibrary2 = this.medialibrary;
        if (medialibrary2 != null) {
            return medialibrary2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
        return null;
    }

    public final void setMedialibrary(Medialibrary medialibrary2) {
        Intrinsics.checkNotNullParameter(medialibrary2, "<set-?>");
        this.medialibrary = medialibrary2;
    }

    public final VLCVideoLayout getVideoLayout() {
        return this.videoLayout;
    }

    public final void setVideoLayout(VLCVideoLayout vLCVideoLayout) {
        this.videoLayout = vLCVideoLayout;
    }

    public final DisplayManager getDisplayManager() {
        DisplayManager displayManager2 = this.displayManager;
        if (displayManager2 != null) {
            return displayManager2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("displayManager");
        return null;
    }

    public final void setDisplayManager(DisplayManager displayManager2) {
        Intrinsics.checkNotNullParameter(displayManager2, "<set-?>");
        this.displayManager = displayManager2;
    }

    public final View getRootView() {
        return this.rootView;
    }

    public final void setRootView(View view) {
        this.rootView = view;
    }

    public final Uri getVideoUri() {
        return this.videoUri;
    }

    public final void setVideoUri(Uri uri) {
        this.videoUri = uri;
    }

    public final ArrayList<MediaWrapper> getSavedMediaList() {
        return this.savedMediaList;
    }

    public final void setSavedMediaList(ArrayList<MediaWrapper> arrayList) {
        this.savedMediaList = arrayList;
    }

    public final int getSavedMediaIndex() {
        return this.savedMediaIndex;
    }

    public final void setSavedMediaIndex(int i) {
        this.savedMediaIndex = i;
    }

    public final PlaylistModel getPlaylistModel() {
        return this.playlistModel;
    }

    public final void setPlaylistModel(PlaylistModel playlistModel2) {
        this.playlistModel = playlistModel2;
    }

    public final SharedPreferences getSettings() {
        SharedPreferences sharedPreferences = this.settings;
        if (sharedPreferences != null) {
            return sharedPreferences;
        }
        Intrinsics.throwUninitializedPropertyAccessException("settings");
        return null;
    }

    public final void setSettings(SharedPreferences sharedPreferences) {
        Intrinsics.checkNotNullParameter(sharedPreferences, "<set-?>");
        this.settings = sharedPreferences;
    }

    public final boolean isShowing() {
        return this.isShowing;
    }

    public final void setShowing(boolean z) {
        this.isShowing = z;
    }

    public final boolean isShowingDialog() {
        return this.isShowingDialog;
    }

    public final void setShowingDialog(boolean z) {
        this.isShowingDialog = z;
    }

    public final boolean isLoading$vlc_android_release() {
        return this.isLoading;
    }

    public final boolean getEnableCloneMode() {
        return this.enableCloneMode;
    }

    public final void setEnableCloneMode(boolean z) {
        this.enableCloneMode = z;
    }

    public final PlayerOrientationMode getOrientationMode() {
        PlayerOrientationMode playerOrientationMode = this.orientationMode;
        if (playerOrientationMode != null) {
            return playerOrientationMode;
        }
        Intrinsics.throwUninitializedPropertyAccessException("orientationMode");
        return null;
    }

    public final void setOrientationMode(PlayerOrientationMode playerOrientationMode) {
        Intrinsics.checkNotNullParameter(playerOrientationMode, "<set-?>");
        this.orientationMode = playerOrientationMode;
    }

    public final boolean isLocked() {
        return this.isLocked;
    }

    public final void setLocked(boolean z) {
        this.isLocked = z;
    }

    public final boolean getLockBackButton() {
        return this.lockBackButton;
    }

    public final void setLockBackButton(boolean z) {
        this.lockBackButton = z;
    }

    public final WindowLayoutInfo getWindowLayoutInfo() {
        WindowLayoutInfo windowLayoutInfo2 = this.windowLayoutInfo;
        if (windowLayoutInfo2 != null) {
            return windowLayoutInfo2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("windowLayoutInfo");
        return null;
    }

    public final void setWindowLayoutInfo(WindowLayoutInfo windowLayoutInfo2) {
        Intrinsics.checkNotNullParameter(windowLayoutInfo2, "<set-?>");
        this.windowLayoutInfo = windowLayoutInfo2;
    }

    public final AudioManager getAudiomanager$vlc_android_release() {
        AudioManager audioManager = this.audiomanager;
        if (audioManager != null) {
            return audioManager;
        }
        Intrinsics.throwUninitializedPropertyAccessException("audiomanager");
        return null;
    }

    public final int getAudioMax$vlc_android_release() {
        return this.audioMax;
    }

    public final boolean isAudioBoostEnabled$vlc_android_release() {
        return this.isAudioBoostEnabled;
    }

    public final float getVolume$vlc_android_release() {
        return this.volume;
    }

    public final void setVolume$vlc_android_release(float f) {
        this.volume = f;
    }

    public final float getOriginalVol$vlc_android_release() {
        return this.originalVol;
    }

    public final void setOriginalVol$vlc_android_release(float f) {
        this.originalVol = f;
    }

    public final float getFov$vlc_android_release() {
        return this.fov;
    }

    public final void setFov$vlc_android_release(float f) {
        this.fov = f;
    }

    public final VideoTouchDelegate getTouchDelegate() {
        VideoTouchDelegate videoTouchDelegate = this.touchDelegate;
        if (videoTouchDelegate != null) {
            return videoTouchDelegate;
        }
        Intrinsics.throwUninitializedPropertyAccessException("touchDelegate");
        return null;
    }

    public final void setTouchDelegate(VideoTouchDelegate videoTouchDelegate) {
        Intrinsics.checkNotNullParameter(videoTouchDelegate, "<set-?>");
        this.touchDelegate = videoTouchDelegate;
    }

    public final VideoStatsDelegate getStatsDelegate() {
        return (VideoStatsDelegate) this.statsDelegate$delegate.getValue();
    }

    public final VideoDelayDelegate getDelayDelegate() {
        return (VideoDelayDelegate) this.delayDelegate$delegate.getValue();
    }

    public final VideoPlayerScreenshotDelegate getScreenshotDelegate() {
        return (VideoPlayerScreenshotDelegate) this.screenshotDelegate$delegate.getValue();
    }

    public final VideoPlayerOverlayDelegate getOverlayDelegate() {
        return (VideoPlayerOverlayDelegate) this.overlayDelegate$delegate.getValue();
    }

    public final VideoPlayerResizeDelegate getResizeDelegate() {
        return (VideoPlayerResizeDelegate) this.resizeDelegate$delegate.getValue();
    }

    public final VideoPlayerOrientationDelegate getOrientationDelegate() {
        return (VideoPlayerOrientationDelegate) this.orientationDelegate$delegate.getValue();
    }

    private final PlayerKeyListenerDelegate getPlayerKeyListenerDelegate() {
        return (PlayerKeyListenerDelegate) this.playerKeyListenerDelegate$delegate.getValue();
    }

    public final VideoTipsDelegate getTipsDelegate() {
        return (VideoTipsDelegate) this.tipsDelegate$delegate.getValue();
    }

    public final boolean isTv() {
        return this.isTv;
    }

    public final void setTv(boolean z) {
        this.isTv = z;
    }

    public final boolean getWaitingForPin() {
        return this.waitingForPin;
    }

    public final void setWaitingForPin(boolean z) {
        this.waitingForPin = z;
    }

    public final int getMenuIdx() {
        return this.menuIdx;
    }

    public final void setMenuIdx(int i) {
        this.menuIdx = i;
    }

    public final boolean isNavMenu() {
        return this.isNavMenu;
    }

    public final void setNavMenu(boolean z) {
        this.isNavMenu = z;
    }

    public final boolean isBenchmark() {
        return this.isBenchmark;
    }

    public final void setBenchmark(boolean z) {
        this.isBenchmark = z;
    }

    private final boolean isInteractive() {
        Object systemService = ContextCompat.getSystemService(getApplicationContext(), PowerManager.class);
        Intrinsics.checkNotNull(systemService);
        PowerManager powerManager = (PowerManager) systemService;
        return AndroidUtil.isLolliPopOrLater ? powerManager.isInteractive() : powerManager.isScreenOn();
    }

    /* access modifiers changed from: private */
    public static final void playlistObserver$lambda$0(VideoPlayerActivity videoPlayerActivity, List list) {
        Intrinsics.checkNotNullParameter(videoPlayerActivity, "this$0");
        Intrinsics.checkNotNullParameter(list, "mediaWrappers");
        videoPlayerActivity.getOverlayDelegate().getPlaylistAdapter().update(list);
    }

    public final Observer<List<MediaWrapper>> getPlaylistObserver() {
        return this.playlistObserver;
    }

    public final boolean isPlaybackSettingActive$vlc_android_release() {
        return getDelayDelegate().getPlaybackSetting() != IPlaybackSettingsController.DelayState.OFF;
    }

    public final Handler getHandler() {
        return this.handler;
    }

    /* access modifiers changed from: private */
    public static final void switchAudioRunnable$lambda$1(VideoPlayerActivity videoPlayerActivity) {
        PlaybackService playbackService;
        PlaybackService playbackService2;
        Intrinsics.checkNotNullParameter(videoPlayerActivity, "this$0");
        if (videoPlayerActivity.getDisplayManager().isPrimary() && (playbackService = videoPlayerActivity.service) != null && playbackService.hasMedia() && (playbackService2 = videoPlayerActivity.service) != null && playbackService2.getVideoTracksCount() == 0) {
            Log.i(TAG, "Video track lost, switching to audio");
            videoPlayerActivity.switchingView = true;
            videoPlayerActivity.exit(4);
        }
    }

    public final SeekBar.OnSeekBarChangeListener getSeekListener() {
        return this.seekListener;
    }

    public final boolean isOnPrimaryDisplay$vlc_android_release() {
        return getDisplayManager().isPrimary();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r0.getMediaplayer();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final org.videolan.libvlc.MediaPlayer.ScaleType getCurrentScaleType() {
        /*
            r1 = this;
            org.videolan.vlc.PlaybackService r0 = r1.service
            if (r0 == 0) goto L_0x000f
            org.videolan.libvlc.MediaPlayer r0 = r0.getMediaplayer()
            if (r0 == 0) goto L_0x000f
            org.videolan.libvlc.MediaPlayer$ScaleType r0 = r0.getVideoScale()
            goto L_0x0010
        L_0x000f:
            r0 = 0
        L_0x0010:
            if (r0 != 0) goto L_0x0014
            org.videolan.libvlc.MediaPlayer$ScaleType r0 = org.videolan.libvlc.MediaPlayer.ScaleType.SURFACE_BEST_FIT
        L_0x0014:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.video.VideoPlayerActivity.getCurrentScaleType():org.videolan.libvlc.MediaPlayer$ScaleType");
    }

    private final boolean isOptionsListShowing() {
        PlayerOptionsDelegate playerOptionsDelegate = this.optionsDelegate;
        return playerOptionsDelegate != null && playerOptionsDelegate.isShowing();
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long getTime() {
        /*
            r11 = this;
            org.videolan.vlc.PlaybackService r0 = r11.service
            r1 = 0
            if (r0 == 0) goto L_0x000b
            long r3 = r0.getTime()
            goto L_0x000c
        L_0x000b:
            r3 = r1
        L_0x000c:
            long r5 = r11.forcedTime
            r7 = -1
            int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x003c
            long r9 = r11.lastTime
            int r0 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x003c
            int r0 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
            if (r0 <= 0) goto L_0x0033
            r0 = 1
            long r5 = r5 + r0
            int r0 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r0 > 0) goto L_0x002a
            int r0 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r0 > 0) goto L_0x002a
            goto L_0x002e
        L_0x002a:
            int r0 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r0 <= 0) goto L_0x004e
        L_0x002e:
            r11.forcedTime = r7
            r11.lastTime = r7
            goto L_0x004e
        L_0x0033:
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 <= 0) goto L_0x004e
            r11.forcedTime = r7
            r11.lastTime = r7
            goto L_0x004e
        L_0x003c:
            int r0 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r0 != 0) goto L_0x004e
            org.videolan.vlc.PlaybackService r0 = r11.service
            if (r0 == 0) goto L_0x004e
            org.videolan.medialibrary.interfaces.media.MediaWrapper r0 = r0.getCurrentMediaWrapper()
            if (r0 == 0) goto L_0x004e
            long r3 = r0.getTime()
        L_0x004e:
            long r0 = r11.forcedTime
            int r2 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r2 != 0) goto L_0x0055
            goto L_0x0056
        L_0x0055:
            r3 = r0
        L_0x0056:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.video.VideoPlayerActivity.getTime():long");
    }

    /* access modifiers changed from: private */
    public static final void downloadedSubtitleObserver$lambda$3(VideoPlayerActivity videoPlayerActivity, List list) {
        Intrinsics.checkNotNullParameter(videoPlayerActivity, "this$0");
        Intrinsics.checkNotNullParameter(list, "externalSubs");
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ExternalSub externalSub = (ExternalSub) it.next();
            if (!videoPlayerActivity.addedExternalSubs.contains(externalSub)) {
                PlaybackService playbackService = videoPlayerActivity.service;
                if (playbackService != null) {
                    playbackService.addSubtitleTrack(externalSub.getSubtitlePath(), Intrinsics.areEqual((Object) videoPlayerActivity.currentSpuTrack, (Object) "-2"));
                }
                videoPlayerActivity.addedExternalSubs.add(externalSub);
            }
        }
    }

    private final int getScreenRotation() {
        Object systemService = ContextCompat.getSystemService(getApplicationContext(), WindowManager.class);
        Intrinsics.checkNotNull(systemService);
        Display defaultDisplay = ((WindowManager) systemService).getDefaultDisplay();
        if (defaultDisplay != null) {
            return defaultDisplay.getRotation();
        }
        return 0;
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

    public final boolean isPlaylistVisible() {
        return getOverlayDelegate().getPlaylistContainer().getVisibility() == 0;
    }

    public Context getApplicationContext() {
        Context applicationContext = super.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        return LocaleUtilsKt.getContextWithLocale(applicationContext, AppContextProvider.INSTANCE.getLocale());
    }

    public AppCompatDelegate getDelegate() {
        AppCompatDelegate appCompatDelegate = this.baseContextWrappingDelegate;
        if (appCompatDelegate != null) {
            return appCompatDelegate;
        }
        AppCompatDelegate delegate = super.getDelegate();
        Intrinsics.checkNotNullExpressionValue(delegate, "getDelegate(...)");
        AppCompatDelegate baseContextWrappingDelegate2 = new BaseContextWrappingDelegate(delegate);
        this.baseContextWrappingDelegate = baseContextWrappingDelegate2;
        return baseContextWrappingDelegate2;
    }

    public Context createConfigurationContext(Configuration configuration) {
        Intrinsics.checkNotNullParameter(configuration, "overrideConfiguration");
        Context createConfigurationContext = super.createConfigurationContext(configuration);
        Intrinsics.checkNotNullExpressionValue(createConfigurationContext, "createConfigurationContext(...)");
        return LocaleUtilsKt.getContextWithLocale(createConfigurationContext, AppContextProvider.INSTANCE.getLocale());
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        PlayerOrientationMode playerOrientationMode;
        ArrayList<MediaWrapper> arrayList;
        Parcelable parcelable;
        Bundle bundle2 = bundle;
        super.onCreate(bundle);
        LifecycleOwner lifecycleOwner = this;
        this.dialogsDelegate.observeDialogs(lifecycleOwner, this);
        Context context = this;
        Util.INSTANCE.checkCpuCompatibility(context);
        setSettings((SharedPreferences) Settings.INSTANCE.getInstance(this));
        Object systemService = ContextCompat.getSystemService(getApplicationContext(), AudioManager.class);
        Intrinsics.checkNotNull(systemService);
        this.audiomanager = (AudioManager) systemService;
        this.audioMax = getAudiomanager$vlc_android_release().getStreamMaxVolume(3);
        this.isAudioBoostEnabled = getSettings().getBoolean(SettingsKt.AUDIO_BOOST, true);
        Boolean bool = clone;
        this.enableCloneMode = bool != null ? bool.booleanValue() : getSettings().getBoolean("enable_clone_mode", false);
        Activity activity = this;
        setDisplayManager(new DisplayManager(activity, PlaybackService.Companion.getRenderer(), false, this.enableCloneMode, this.isBenchmark));
        setContentView(getDisplayManager().isPrimary() ? R.layout.player : R.layout.player_remote_control);
        this.rootView = findViewById(R.id.player_root);
        VideoPlayerOverlayDelegate overlayDelegate = getOverlayDelegate();
        View findViewById = findViewById(R.id.video_playlist);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        overlayDelegate.setPlaylist((RecyclerView) findViewById);
        VideoPlayerOverlayDelegate overlayDelegate2 = getOverlayDelegate();
        View findViewById2 = findViewById(R.id.playlist_search_text);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        overlayDelegate2.setPlaylistSearchText((TextInputLayout) findViewById2);
        VideoPlayerOverlayDelegate overlayDelegate3 = getOverlayDelegate();
        View findViewById3 = findViewById(R.id.video_playlist_container);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        overlayDelegate3.setPlaylistContainer(findViewById3);
        VideoPlayerOverlayDelegate overlayDelegate4 = getOverlayDelegate();
        View findViewById4 = findViewById(R.id.close_button);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        overlayDelegate4.setCloseButton(findViewById4);
        getOverlayDelegate().setHingeArrowRight((ImageView) findViewById(R.id.hinge_go_right));
        getOverlayDelegate().setHingeArrowLeft((ImageView) findViewById(R.id.hinge_go_left));
        EditText editText = getOverlayDelegate().getPlaylistSearchText().getEditText();
        if (editText != null) {
            editText.addTextChangedListener(this);
        }
        VideoPlayerOverlayDelegate overlayDelegate5 = getOverlayDelegate();
        View findViewById5 = findViewById(R.id.player_ui_container);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        overlayDelegate5.setPlayerUiContainer((ViewGroup) findViewById5);
        String string = getSettings().getString(SettingsKt.SCREEN_ORIENTATION, "99");
        Intrinsics.checkNotNull(string);
        Integer valueOf = Integer.valueOf(string);
        boolean z = getSettings().getBoolean(SettingsKt.LOCK_USE_SENSOR, true);
        if (valueOf != null && valueOf.intValue() == 99) {
            playerOrientationMode = new PlayerOrientationMode(false, 0, 2, (DefaultConstructorMarker) null);
        } else if (valueOf != null && valueOf.intValue() == 101) {
            playerOrientationMode = new PlayerOrientationMode(true, z ? 6 : 0);
        } else if (valueOf != null && valueOf.intValue() == 102) {
            playerOrientationMode = new PlayerOrientationMode(true, z ? 7 : 1);
        } else if (valueOf != null && valueOf.intValue() == 103) {
            playerOrientationMode = new PlayerOrientationMode(true, 8);
        } else if (valueOf != null && valueOf.intValue() == 98) {
            playerOrientationMode = new PlayerOrientationMode(true, getSettings().getInt(SettingsKt.LAST_LOCK_ORIENTATION, 6));
        } else {
            playerOrientationMode = new PlayerOrientationMode(true, getOrientationForLock());
        }
        setOrientationMode(playerOrientationMode);
        this.videoLayout = (VLCVideoLayout) findViewById(R.id.video_layout);
        this.loadingImageView = (ImageView) findViewById(R.id.player_overlay_loading);
        getOverlayDelegate().dimStatusBar(true);
        Activity activity2 = activity;
        this.handler.sendEmptyMessageDelayed(7, 1000);
        this.switchingView = false;
        this.askResume = Intrinsics.areEqual((Object) getSettings().getString(SettingsKt.KEY_VIDEO_CONFIRM_RESUME, Constants.GROUP_VIDEOS_FOLDER), (Object) "2");
        sDisplayRemainingTime = getSettings().getBoolean(KEY_REMAINING_TIME_DISPLAY, false);
        SettingsKt.putSingle(getSettings(), SettingsKt.VIDEO_RESUME_TIME, -1L);
        setVolumeControlStream(3);
        try {
            setRequestedOrientation(getScreenOrientation(getOrientationMode()));
            if (valueOf != null) {
                if (valueOf.intValue() == 103 && z) {
                    setOrientationMode(new PlayerOrientationMode(true, 6));
                    setRequestedOrientation(getScreenOrientation(getOrientationMode()));
                }
            }
            if (getOrientationMode().getLocked()) {
                SettingsKt.putSingle(getSettings(), SettingsKt.LAST_LOCK_ORIENTATION, Integer.valueOf(getRequestedOrientation()));
            }
        } catch (IllegalStateException unused) {
            Log.w(TAG, "onCreate: failed to set orientation");
        }
        getOverlayDelegate().updateOrientationIcon();
        this.isTv = Settings.INSTANCE.getShowTvUi();
        if (getDisplayManager().isPrimary() && !this.isTv && !getSettings().getBoolean(SettingsKt.PREF_TIPS_SHOWN, false) && !this.isBenchmark) {
            getTipsDelegate().init();
        }
        Medialibrary instance = Medialibrary.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(...)");
        setMedialibrary(instance);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        setTouchDelegate(new VideoTouchDelegate(this, generateTouchFlags(), new ScreenConfig(displayMetrics, RangesKt.coerceAtLeast(displayMetrics.widthPixels, displayMetrics.heightPixels), RangesKt.coerceAtMost(displayMetrics.widthPixels, displayMetrics.heightPixels), getResources().getConfiguration().orientation), this.isTv));
        UiTools.INSTANCE.setRotationAnimation(activity2);
        if (bundle2 != null) {
            this.savedTime = bundle2.getLong(KEY_TIME);
            if (Build.VERSION.SDK_INT >= 33) {
                arrayList = AppUtils$$ExternalSyntheticApiModelOutline0.m(bundle2, "media_list", MediaWrapper.class);
            } else {
                arrayList = bundle2.getParcelableArrayList("media_list");
            }
            this.savedMediaList = arrayList;
            this.savedMediaIndex = bundle2.getInt(KEY_MEDIA_INDEX);
            if (bundle2.getBoolean(KEY_LIST, false)) {
                getIntent().removeExtra(Constants.PLAY_EXTRA_ITEM_LOCATION);
            } else {
                if (Build.VERSION.SDK_INT >= 33) {
                    parcelable = (Parcelable) AppUtils$$ExternalSyntheticApiModelOutline0.m(bundle2, KEY_URI, Parcelable.class);
                } else {
                    parcelable = bundle2.getParcelable(KEY_URI);
                    if (!(parcelable instanceof Parcelable)) {
                        parcelable = null;
                    }
                }
                this.videoUri = (Uri) parcelable;
            }
        }
        setBookmarkModel(BookmarkModel.Companion.get(this));
        VideoPlayerOverlayDelegate overlayDelegate6 = getOverlayDelegate();
        AnimatedVectorDrawableCompat create = AnimatedVectorDrawableCompat.create(context, R.drawable.anim_play_pause_video);
        Intrinsics.checkNotNull(create);
        overlayDelegate6.setPlayToPause(create);
        VideoPlayerOverlayDelegate overlayDelegate7 = getOverlayDelegate();
        AnimatedVectorDrawableCompat create2 = AnimatedVectorDrawableCompat.create(context, R.drawable.anim_pause_play_video);
        Intrinsics.checkNotNull(create2);
        overlayDelegate7.setPauseToPlay(create2);
        WindowInsetsControllerCompat windowInsetsController = ViewCompat.getWindowInsetsController(getWindow().getDecorView());
        if (windowInsetsController != null) {
            windowInsetsController.setSystemBarsBehavior(1);
        }
        Job unused2 = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(lifecycleOwner), Dispatchers.getMain(), (CoroutineStart) null, new VideoPlayerActivity$onCreate$2(this, (Continuation<? super VideoPlayerActivity$onCreate$2>) null), 2, (Object) null);
        getOnBackPressedDispatcher().addCallback(lifecycleOwner, new VideoPlayerActivity$onCreate$3(this));
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        boolean hasNotch = KextensionsKt.hasNotch(this);
        this.hasPhysicalNotch = hasNotch;
        if (hasNotch) {
            getWindow().getAttributes().layoutInDisplayCutoutMode = getSettings().getInt(SettingsKt.DISPLAY_UNDER_NOTCH, 1);
        }
    }

    private final int generateTouchFlags() {
        int i = 0;
        if (this.isTv) {
            return 0;
        }
        int i2 = (((!AndroidUtil.isLolliPopOrLater || !AppUtils$$ExternalSyntheticApiModelOutline0.m(getAudiomanager$vlc_android_release())) && getSettings().getBoolean(SettingsKt.ENABLE_VOLUME_GESTURE, true)) ? 1 : 0) + (!AndroidDevices.INSTANCE.isChromeBook() && getSettings().getBoolean(SettingsKt.ENABLE_BRIGHTNESS_GESTURE, true) ? 2 : 0) + (getSettings().getBoolean(SettingsKt.ENABLE_DOUBLE_TAP_SEEK, true) ? 4 : 0) + (getSettings().getBoolean(SettingsKt.ENABLE_DOUBLE_TAP_PLAY, true) ? 8 : 0) + (getSettings().getBoolean(SettingsKt.ENABLE_SWIPE_SEEK, true) ? 16 : 0) + (ArraysKt.contains((T[]) new String[]{"2", "3"}, getSettings().getString(SettingsKt.SCREENSHOT_MODE, Constants.GROUP_VIDEOS_FOLDER)) ? 32 : 0) + (getSettings().getBoolean(SettingsKt.ENABLE_SCALE_GESTURE, true) ? 64 : 0);
        if (getSettings().getBoolean(SettingsKt.ENABLE_FASTPLAY, false)) {
            i = 128;
        }
        return i + i2;
    }

    public void fireDialog(Dialog dialog) {
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        DialogActivity.Companion.setDialog(dialog);
        startActivity(new Intent(DialogActivity.KEY_DIALOG, (Uri) null, this, DialogActivity.class));
    }

    public void dialogCanceled(Dialog dialog) {
        DialogFragment dialogFragment = null;
        Object context = dialog != null ? dialog.getContext() : null;
        if (context instanceof DialogFragment) {
            dialogFragment = (DialogFragment) context;
        }
        if (dialogFragment != null) {
            dialogFragment.dismiss();
        }
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (charSequence != null) {
            if (charSequence.length() > 0) {
                PlaylistModel playlistModel2 = this.playlistModel;
                if (playlistModel2 != null) {
                    playlistModel2.filter(charSequence);
                    return;
                }
                return;
            }
            PlaylistModel playlistModel3 = this.playlistModel;
            if (playlistModel3 != null) {
                playlistModel3.filter((CharSequence) null);
            }
        }
    }

    private final boolean hideSearchField() {
        if (getOverlayDelegate().getPlaylistSearchText().getVisibility() != 0) {
            return false;
        }
        EditText editText = getOverlayDelegate().getPlaylistSearchText().getEditText();
        if (editText != null) {
            TextWatcher textWatcher = this;
            editText.removeTextChangedListener(textWatcher);
            editText.setText("");
            editText.addTextChangedListener(textWatcher);
        }
        UiTools.INSTANCE.setKeyboardVisibility(getOverlayDelegate().getPlaylistSearchText(), false);
        return true;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        overridePendingTransition(0, 0);
        super.onResume();
        this.isShowingDialog = false;
        this.waitingForPin = false;
        getOverlayDelegate().setListeners(true);
        if (this.isLocked && !getOrientationMode().getLocked()) {
            setRequestedOrientation(getOrientationMode().getOrientation());
        }
        getOverlayDelegate().updateOrientationIcon();
        Integer[] numArr = {13, 12, 1, 2};
        for (int i = 0; i < 4; i++) {
            int intValue = numArr[i].intValue();
            this.handler.removeMessages(intValue);
            this.handler.sendEmptyMessage(intValue);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x005f, code lost:
        if ((r8 instanceof android.os.Parcelable) == false) goto L_0x0061;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onNewIntent(android.content.Intent r8) {
        /*
            r7 = this;
            java.lang.String r0 = "intent"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            super.onNewIntent(r8)
            r7.setIntent(r8)
            boolean r0 = r7.playbackStarted
            if (r0 == 0) goto L_0x00eb
            org.videolan.vlc.PlaybackService r0 = r7.service
            if (r0 == 0) goto L_0x00eb
            org.videolan.vlc.gui.video.VideoPlayerOverlayDelegate r1 = r7.getOverlayDelegate()
            boolean r1 = r1.isHudRightBindingInitialized()
            if (r1 == 0) goto L_0x0038
            org.videolan.vlc.gui.video.VideoPlayerOverlayDelegate r1 = r7.getOverlayDelegate()
            org.videolan.vlc.databinding.PlayerHudRightBinding r1 = r1.getHudRightBinding()
            android.widget.TextView r1 = r1.playerOverlayTitle
            org.videolan.medialibrary.interfaces.media.MediaWrapper r2 = r0.getCurrentMediaWrapper()
            if (r2 == 0) goto L_0x00eb
            java.lang.String r2 = r2.getTitle()
            if (r2 == 0) goto L_0x00eb
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r1.setText(r2)
        L_0x0038:
            java.lang.String r1 = "item_location"
            boolean r2 = r8.hasExtra(r1)
            r3 = 0
            if (r2 == 0) goto L_0x0065
            android.os.Bundle r8 = r8.getExtras()
            if (r8 == 0) goto L_0x0061
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8)
            int r2 = android.os.Build.VERSION.SDK_INT
            r4 = 33
            if (r2 < r4) goto L_0x0059
            java.lang.Class<android.os.Parcelable> r2 = android.os.Parcelable.class
            java.lang.Object r8 = org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0.m((android.os.Bundle) r8, (java.lang.String) r1, (java.lang.Class) r2)
            android.os.Parcelable r8 = (android.os.Parcelable) r8
            goto L_0x0062
        L_0x0059:
            android.os.Parcelable r8 = r8.getParcelable(r1)
            boolean r1 = r8 instanceof android.os.Parcelable
            if (r1 != 0) goto L_0x0062
        L_0x0061:
            r8 = r3
        L_0x0062:
            android.net.Uri r8 = (android.net.Uri) r8
            goto L_0x0069
        L_0x0065:
            android.net.Uri r8 = r8.getData()
        L_0x0069:
            if (r8 == 0) goto L_0x00eb
            android.net.Uri r1 = r7.videoUri
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r1)
            if (r1 == 0) goto L_0x0075
            goto L_0x00eb
        L_0x0075:
            java.lang.String r1 = "file"
            java.lang.String r2 = r8.getScheme()
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r2)
            r2 = 0
            r4 = 1
            if (r1 == 0) goto L_0x00a4
            java.lang.String r1 = r8.getPath()
            if (r1 == 0) goto L_0x00a4
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            java.lang.String r5 = "/sdcard"
            r6 = 2
            boolean r1 = kotlin.text.StringsKt.startsWith$default(r1, r5, r2, r6, r3)
            if (r1 != r4) goto L_0x00a4
            org.videolan.vlc.util.FileUtils r1 = org.videolan.vlc.util.FileUtils.INSTANCE
            android.net.Uri r8 = r1.convertLocalUri(r8)
            android.net.Uri r1 = r7.videoUri
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r1)
            if (r1 == 0) goto L_0x00a4
            return
        L_0x00a4:
            r7.videoUri = r8
            boolean r8 = r7.isPlaylistVisible()
            if (r8 == 0) goto L_0x00c6
            org.videolan.vlc.gui.video.VideoPlayerOverlayDelegate r8 = r7.getOverlayDelegate()
            org.videolan.vlc.gui.audio.PlaylistAdapter r8 = r8.getPlaylistAdapter()
            int r1 = r0.getCurrentMediaPosition()
            r8.setCurrentIndex(r1)
            org.videolan.vlc.gui.video.VideoPlayerOverlayDelegate r8 = r7.getOverlayDelegate()
            android.view.View r8 = r8.getPlaylistContainer()
            org.videolan.tools.KotlinExtensionsKt.setGone(r8)
        L_0x00c6:
            android.content.SharedPreferences r8 = r0.getSettings$vlc_android_release()
            java.lang.String r1 = "video_transition_show"
            boolean r8 = r8.getBoolean(r1, r4)
            if (r8 == 0) goto L_0x00d5
            r7.showTitle()
        L_0x00d5:
            r7.initUI()
            r5 = -1
            r7.lastTime = r5
            r7.forcedTime = r5
            r7.enableSubs()
            boolean r8 = r0.isPlaying()
            if (r8 == 0) goto L_0x00eb
            loadMedia$default(r7, r2, r4, r4, r3)
        L_0x00eb:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.video.VideoPlayerActivity.onNewIntent(android.content.Intent):void");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        boolean isFinishing = isFinishing();
        if (isFinishing) {
            overridePendingTransition(0, 0);
        } else {
            VideoPlayerOverlayDelegate.hideOverlay$default(getOverlayDelegate(), true, false, 2, (Object) null);
        }
        super.onPause();
        getOverlayDelegate().setListeners(false);
        if (isInPictureInPictureMode()) {
            return;
        }
        if (isFinishing || (AndroidUtil.isNougatOrLater && !AndroidUtil.isOOrLater && AndroidDevices.INSTANCE.isAndroidTv() && !requestVisibleBehind(true))) {
            stopPlayback();
        }
    }

    /* access modifiers changed from: protected */
    public void onUserLeaveHint() {
        PlaybackService playbackService;
        if (!isInPictureInPictureMode() && getDisplayManager().isPrimary() && !this.isShowingDialog && Intrinsics.areEqual((Object) "2", (Object) getSettings().getString(SettingsKt.KEY_VIDEO_APP_SWITCH, Constants.GROUP_VIDEOS_FOLDER)) && isInteractive() && (playbackService = this.service) != null && !playbackService.hasRenderer()) {
            switchToPopup();
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        List<MediaWrapper> list;
        PlaylistManager playlistManager;
        PlaylistManager playlistManager2;
        Intrinsics.checkNotNullParameter(bundle, "outState");
        super.onSaveInstanceState(bundle);
        Uri uri = this.videoUri;
        if (uri != null) {
            if (!Intrinsics.areEqual((Object) "content", (Object) uri != null ? uri.getScheme() : null)) {
                bundle.putLong(KEY_TIME, this.savedTime);
                if (this.playlistModel == null) {
                    bundle.putParcelable(KEY_URI, this.videoUri);
                }
            }
        }
        PlaybackService playbackService = this.service;
        if (playbackService == null || (playlistManager2 = playbackService.getPlaylistManager()) == null || (list = playlistManager2.getMediaList()) == null) {
            list = this.savedMediaList;
        }
        PlaybackService playbackService2 = this.service;
        int currentIndex = (playbackService2 == null || (playlistManager = playbackService2.getPlaylistManager()) == null) ? 0 : playlistManager.getCurrentIndex();
        if (list != null) {
            bundle.putParcelableArrayList("media_list", new ArrayList(list));
            bundle.putInt(KEY_MEDIA_INDEX, currentIndex);
            this.savedMediaList = null;
        }
        this.videoUri = null;
        bundle.putBoolean(KEY_LIST, getOverlayDelegate().getHasPlaylist());
    }

    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v1, types: [org.videolan.medialibrary.interfaces.media.MediaWrapper] */
    /* JADX WARNING: type inference failed for: r2v5, types: [androidx.fragment.app.DialogFragment] */
    /* JADX WARNING: type inference failed for: r2v8 */
    /* JADX WARNING: type inference failed for: r2v9 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void switchToPopup() {
        /*
            r5 = this;
            boolean r0 = r5.isBenchmark
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            org.videolan.vlc.gui.helpers.PlayerOptionsDelegate r0 = r5.optionsDelegate
            if (r0 == 0) goto L_0x000c
            r0.hide()
        L_0x000c:
            androidx.fragment.app.FragmentManager r0 = r5.getSupportFragmentManager()
            java.util.List r0 = r0.getFragments()
            java.lang.String r1 = "getFragments(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Iterator r0 = r0.iterator()
        L_0x001f:
            boolean r1 = r0.hasNext()
            r2 = 0
            if (r1 == 0) goto L_0x0039
            java.lang.Object r1 = r0.next()
            androidx.fragment.app.Fragment r1 = (androidx.fragment.app.Fragment) r1
            boolean r3 = r1 instanceof androidx.fragment.app.DialogFragment
            if (r3 == 0) goto L_0x0033
            r2 = r1
            androidx.fragment.app.DialogFragment r2 = (androidx.fragment.app.DialogFragment) r2
        L_0x0033:
            if (r2 == 0) goto L_0x001f
            r2.dismiss()
            goto L_0x001f
        L_0x0039:
            org.videolan.vlc.PlaybackService r0 = r5.service
            if (r0 == 0) goto L_0x0041
            org.videolan.medialibrary.interfaces.media.MediaWrapper r2 = r0.getCurrentMediaWrapper()
        L_0x0041:
            if (r2 == 0) goto L_0x011b
            org.videolan.resources.AndroidDevices r0 = org.videolan.resources.AndroidDevices.INSTANCE
            boolean r0 = r0.getPipAllowed()
            if (r0 == 0) goto L_0x011b
            r0 = r5
            androidx.lifecycle.LifecycleOwner r0 = (androidx.lifecycle.LifecycleOwner) r0
            boolean r0 = org.videolan.tools.KotlinExtensionsKt.isStarted(r0)
            if (r0 != 0) goto L_0x0056
            goto L_0x011b
        L_0x0056:
            org.videolan.tools.Settings r0 = org.videolan.tools.Settings.INSTANCE
            java.lang.Object r0 = r0.getInstance(r5)
            android.content.SharedPreferences r0 = (android.content.SharedPreferences) r0
            java.lang.String r1 = "popup_force_legacy"
            r3 = 0
            boolean r0 = r0.getBoolean(r1, r3)
            org.videolan.resources.AndroidDevices r1 = org.videolan.resources.AndroidDevices.INSTANCE
            boolean r1 = r1.getHasPiP()
            if (r1 == 0) goto L_0x00ed
            if (r0 != 0) goto L_0x00ed
            org.videolan.vlc.util.Permissions r0 = org.videolan.vlc.util.Permissions.INSTANCE
            r1 = r5
            androidx.fragment.app.FragmentActivity r1 = (androidx.fragment.app.FragmentActivity) r1
            r0.checkPiPPermission(r1)
            boolean r0 = org.videolan.libvlc.util.AndroidUtil.isOOrLater
            if (r0 == 0) goto L_0x00e9
            org.videolan.vlc.PlaybackService r0 = r5.service     // Catch:{ IllegalArgumentException -> 0x00d9 }
            if (r0 == 0) goto L_0x00d8
            org.videolan.vlc.media.PlaylistManager r0 = r0.getPlaylistManager()     // Catch:{ IllegalArgumentException -> 0x00d9 }
            if (r0 == 0) goto L_0x00d8
            org.videolan.vlc.media.PlayerController r0 = r0.getPlayer()     // Catch:{ IllegalArgumentException -> 0x00d9 }
            if (r0 == 0) goto L_0x00d8
            org.videolan.libvlc.MediaPlayer r0 = r0.getMediaplayer()     // Catch:{ IllegalArgumentException -> 0x00d9 }
            if (r0 == 0) goto L_0x00d8
            org.videolan.vlc.gui.dialogs.adapters.VlcTrack r0 = org.videolan.vlc.VersionDependantKt.getSelectedVideoTrack(r0)     // Catch:{ IllegalArgumentException -> 0x00d9 }
            if (r0 != 0) goto L_0x0098
            goto L_0x00d8
        L_0x0098:
            int r1 = r0.getWidth()     // Catch:{ IllegalArgumentException -> 0x00d9 }
            int r0 = r0.getHeight()     // Catch:{ IllegalArgumentException -> 0x00d9 }
            android.app.PictureInPictureParams$Builder r2 = new android.app.PictureInPictureParams$Builder     // Catch:{ IllegalArgumentException -> 0x00d9 }
            r2.<init>()     // Catch:{ IllegalArgumentException -> 0x00d9 }
            if (r1 == 0) goto L_0x00c2
            if (r0 == 0) goto L_0x00c2
            float r3 = (float) r1     // Catch:{ IllegalArgumentException -> 0x00d9 }
            float r4 = (float) r0     // Catch:{ IllegalArgumentException -> 0x00d9 }
            float r3 = r3 / r4
            r4 = 1054226902(0x3ed639d6, float:0.41841)
            int r4 = (r4 > r3 ? 1 : (r4 == r3 ? 0 : -1))
            if (r4 > 0) goto L_0x00c2
            r4 = 1075377603(0x4018f5c3, float:2.39)
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 > 0) goto L_0x00c2
            android.util.Rational r3 = new android.util.Rational     // Catch:{ IllegalArgumentException -> 0x00d9 }
            r3.<init>(r1, r0)     // Catch:{ IllegalArgumentException -> 0x00d9 }
            android.app.PictureInPictureParams.Builder unused = r2.setAspectRatio(r3)     // Catch:{ IllegalArgumentException -> 0x00d9 }
        L_0x00c2:
            java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()     // Catch:{ IllegalArgumentException -> 0x00d9 }
            android.app.PictureInPictureParams.Builder unused = r2.setActions(r0)     // Catch:{ IllegalArgumentException -> 0x00d9 }
            org.videolan.vlc.PlaybackService r0 = r5.service     // Catch:{ IllegalArgumentException -> 0x00d9 }
            if (r0 == 0) goto L_0x00d0
            r0.updateWidgetState()     // Catch:{ IllegalArgumentException -> 0x00d9 }
        L_0x00d0:
            android.app.PictureInPictureParams r0 = r2.build()     // Catch:{ IllegalArgumentException -> 0x00d9 }
            boolean unused = r5.enterPictureInPictureMode(r0)     // Catch:{ IllegalArgumentException -> 0x00d9 }
            goto L_0x011b
        L_0x00d8:
            return
        L_0x00d9:
            r0 = move-exception
            java.lang.String r1 = r0.getMessage()
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            java.lang.String r2 = "VLC/VideoPlayerActivity"
            android.util.Log.w(r2, r1, r0)
            r5.enterPictureInPictureMode()
            goto L_0x011b
        L_0x00e9:
            r5.enterPictureInPictureMode()
            goto L_0x011b
        L_0x00ed:
            org.videolan.vlc.util.Permissions r0 = org.videolan.vlc.util.Permissions.INSTANCE
            r1 = r5
            android.content.Context r1 = (android.content.Context) r1
            boolean r0 = r0.canDrawOverlays(r1)
            if (r0 == 0) goto L_0x0113
            r0 = 1
            r5.switchingView = r0
            r5.switchToPopup = r0
            org.videolan.vlc.PlaybackService r1 = r5.service
            if (r1 == 0) goto L_0x0108
            boolean r1 = r1.isPlaying()
            if (r1 != r0) goto L_0x0108
            goto L_0x010c
        L_0x0108:
            r0 = 4
            r2.addFlags(r0)
        L_0x010c:
            r5.cleanUI()
            r5.exitOK()
            goto L_0x011b
        L_0x0113:
            org.videolan.vlc.util.Permissions r0 = org.videolan.vlc.util.Permissions.INSTANCE
            r1 = r5
            androidx.fragment.app.FragmentActivity r1 = (androidx.fragment.app.FragmentActivity) r1
            r0.checkDrawOverlaysPermission(r1)
        L_0x011b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.video.VideoPlayerActivity.switchToPopup():void");
    }

    public void onVisibleBehindCanceled() {
        super.onVisibleBehindCanceled();
        stopPlayback();
        exitOK();
    }

    public void onConfigurationChanged(Configuration configuration) {
        Intrinsics.checkNotNullParameter(configuration, "newConfig");
        getResources().getConfiguration().orientation = configuration.orientation;
        super.onConfigurationChanged(configuration);
        if (this.touchDelegate != null) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            getTouchDelegate().setScreenConfig(new ScreenConfig(displayMetrics, RangesKt.coerceAtLeast(displayMetrics.widthPixels, displayMetrics.heightPixels), RangesKt.coerceAtMost(displayMetrics.widthPixels, displayMetrics.heightPixels), configuration.orientation));
        }
        getOverlayDelegate().resetHudLayout();
        getOverlayDelegate().showControls(this.isShowing);
        getStatsDelegate().onConfigurationChanged();
        getOverlayDelegate().updateHudMargins();
        getOverlayDelegate().updateTitleConstraints();
        getOverlayDelegate().rotateBookmarks();
        getScreenshotDelegate().hide();
    }

    public void onWindowFocusChanged(boolean z) {
        if (z) {
            WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView()).setSystemBarsBehavior(2);
        }
        super.onWindowFocusChanged(z);
    }

    /* access modifiers changed from: private */
    public final void simulateKeyPress(Context context, int i) {
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
        Activity activity = (Activity) context;
        activity.getWindow().getDecorView().getRootView();
        BaseInputConnection baseInputConnection = new BaseInputConnection(activity.getWindow().getDecorView().getRootView(), true);
        KeyEvent keyEvent = new KeyEvent(0, i);
        KeyEvent keyEvent2 = new KeyEvent(1, i);
        baseInputConnection.sendKeyEvent(keyEvent);
        baseInputConnection.sendKeyEvent(keyEvent2);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        getMedialibrary().pauseBackgroundOperations();
        super.onStart();
        this.startedScope = CoroutineScopeKt.MainScope();
        Context context = this;
        PlaybackService.Companion.start(context);
        Flow onEach = FlowKt.onEach(PlaybackService.Companion.getServiceFlow(), new VideoPlayerActivity$onStart$1(this, (Continuation<? super VideoPlayerActivity$onStart$1>) null));
        CoroutineScope coroutineScope = this.startedScope;
        if (coroutineScope == null) {
            Intrinsics.throwUninitializedPropertyAccessException("startedScope");
            coroutineScope = null;
        }
        FlowKt.launchIn(onEach, coroutineScope);
        this.videoRemoteJob = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new VideoPlayerActivity$onStart$2(this, (Continuation<? super VideoPlayerActivity$onStart$2>) null), 3, (Object) null);
        restoreBrightness();
        IntentFilter intentFilter = new IntentFilter(Constants.PLAY_FROM_SERVICE);
        intentFilter.addAction(Constants.EXIT_PLAYER);
        LocalBroadcastManager.getInstance(context).registerReceiver(this.serviceReceiver, intentFilter);
        IntentFilter intentFilter2 = new IntentFilter("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter2.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
        registerReceiver(this.btReceiver, intentFilter2);
        KotlinExtensionsKt.setInvisible(getOverlayDelegate().getOverlayInfo());
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        PlaylistManager playlistManager;
        PlaylistManager playlistManager2;
        MutableLiveData<Boolean> videoStatsOn;
        PlaybackService playbackService;
        PlaylistManager playlistManager3;
        List<MediaWrapper> mediaList;
        super.onStop();
        PlaybackService playbackService2 = this.service;
        if (!(playbackService2 == null || (playlistManager3 = playbackService2.getPlaylistManager()) == null || (mediaList = playlistManager3.getMediaList()) == null)) {
            this.savedMediaList = new ArrayList<>(mediaList);
        }
        CoroutineScope coroutineScope = this.startedScope;
        if (coroutineScope == null) {
            Intrinsics.throwUninitializedPropertyAccessException("startedScope");
            coroutineScope = null;
        }
        CoroutineScopeKt.cancel$default(coroutineScope, (CancellationException) null, 1, (Object) null);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.serviceReceiver);
        unregisterReceiver(this.btReceiver);
        AlertDialog alertDialog2 = this.alertDialog;
        if (alertDialog2 != null) {
            alertDialog2.dismiss();
        }
        if (getDisplayManager().isPrimary() && !isFinishing() && (playbackService = this.service) != null && playbackService.isPlaying() && Intrinsics.areEqual((Object) DiskLruCache.VERSION_1, (Object) getSettings().getString(SettingsKt.KEY_VIDEO_APP_SWITCH, Constants.GROUP_VIDEOS_FOLDER)) && !PlaybackService.Companion.hasRenderer()) {
            switchToAudioMode(false);
        }
        cleanUI();
        stopPlayback();
        PlaybackService playbackService3 = this.service;
        if (!(playbackService3 == null || (playlistManager2 = playbackService3.getPlaylistManager()) == null || (videoStatsOn = playlistManager2.getVideoStatsOn()) == null)) {
            videoStatsOn.postValue(false);
        }
        if (isInteractive()) {
            PlaybackService playbackService4 = this.service;
            MutableLiveData<Boolean> isInPiPMode = playbackService4 != null ? playbackService4.isInPiPMode() : null;
            if (isInPiPMode != null) {
                isInPiPMode.setValue(false);
            }
        }
        if (this.savedTime != -1) {
            SettingsKt.putSingle(getSettings(), SettingsKt.VIDEO_RESUME_TIME, Long.valueOf(this.savedTime));
        }
        saveBrightness();
        PlaybackService playbackService5 = this.service;
        if (!(playbackService5 == null || (playlistManager = playbackService5.getPlaylistManager()) == null)) {
            playlistManager.resetResumeStatus();
        }
        PlaybackService playbackService6 = this.service;
        if (playbackService6 != null) {
            ChannelResult.m2336boximpl(playbackService6.m2457removeCallbackJP2dKIU(this));
        }
        this.service = null;
        setIntent(new Intent());
        this.handler.removeCallbacksAndMessages((Object) null);
        removeDownloadedSubtitlesObserver();
        this.previousMediaPath = null;
        this.addedExternalSubs.clear();
        getMedialibrary().resumeBackgroundOperations();
        Job job = this.videoRemoteJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
    }

    private final void saveBrightness() {
        if (getSettings().getBoolean(SettingsKt.SAVE_BRIGHTNESS, false)) {
            float f = getWindow().getAttributes().screenBrightness;
            if (f != -1.0f) {
                SettingsKt.putSingle(getSettings(), SettingsKt.BRIGHTNESS_VALUE, Float.valueOf(f));
            }
        }
    }

    private final void restoreBrightness() {
        if (getSettings().getBoolean(SettingsKt.SAVE_BRIGHTNESS, false)) {
            float f = getSettings().getFloat(SettingsKt.BRIGHTNESS_VALUE, -1.0f);
            if (f != -1.0f) {
                setWindowBrightness(f);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        PlaylistModel playlistModel2 = this.playlistModel;
        if (playlistModel2 != null) {
            playlistModel2.getDataset().removeObserver(this.playlistObserver);
            playlistModel2.onCleared();
        }
        this.optionsDelegate = null;
        getOverlayDelegate().onDestroy();
        getDisplayManager().release();
    }

    /* access modifiers changed from: private */
    public final void startPlayback() {
        PlaybackService playbackService;
        VLCVideoLayout vLCVideoLayout;
        if (!this.playbackStarted && (playbackService = this.service) != null) {
            this.playbackStarted = true;
            IVLCVout vout = playbackService.getVout();
            if (vout != null && vout.areViewsAttached()) {
                if (playbackService.isPlayingPopup()) {
                    PlaybackService.stop$default(playbackService, false, true, 1, (Object) null);
                } else {
                    vout.detachViews();
                }
            }
            MediaPlayer mediaplayer = playbackService.getMediaplayer();
            if (!getDisplayManager().isOnRenderer() && (vLCVideoLayout = this.videoLayout) != null) {
                mediaplayer.attachViews(vLCVideoLayout, getDisplayManager(), true, false);
                mediaplayer.setVideoScale(this.isBenchmark ? MediaPlayer.ScaleType.SURFACE_FILL : MediaPlayer.ScaleType.values()[playbackService.getSettings$vlc_android_release().getInt(SettingsKt.VIDEO_RATIO, MediaPlayer.ScaleType.SURFACE_BEST_FIT.ordinal())]);
            }
            initUI();
            loadMedia$default(this, false, false, 3, (Object) null);
        }
    }

    private final void initUI() {
        if (!this.isBenchmark) {
            getDisplayManager().setMediaRouterCallback();
        }
        View view = this.rootView;
        if (view != null) {
            view.setKeepScreenOn(true);
        }
    }

    private final void setPlaybackParameters() {
        PlaybackService playbackService = this.service;
        if (playbackService != null) {
            if (playbackService.getAudioDelay() != 0 && playbackService.getAudioDelay() != playbackService.getAudioDelay()) {
                playbackService.setAudioDelay(playbackService.getAudioDelay());
            } else if (getAudiomanager$vlc_android_release().isBluetoothA2dpOn() || getAudiomanager$vlc_android_release().isBluetoothScoOn()) {
                toggleBtDelay(true);
            }
            if (playbackService.getSpuDelay() != 0 && playbackService.getSpuDelay() != playbackService.getSpuDelay()) {
                playbackService.setSpuDelay(playbackService.getSpuDelay());
            }
        }
    }

    private final void stopPlayback() {
        PlaybackService playbackService;
        if (this.playbackStarted) {
            if ((!getDisplayManager().isPrimary() && !isFinishing()) || (playbackService = this.service) == null) {
                this.playbackStarted = false;
            } else if (playbackService != null) {
                boolean showTvUi = Settings.INSTANCE.getShowTvUi();
                boolean z = !playbackService.isPlaying() || (!showTvUi && !isInteractive());
                this.wasPaused = z;
                if (z) {
                    SettingsKt.putSingle(playbackService.getSettings$vlc_android_release(), SettingsKt.VIDEO_PAUSED, true);
                }
                if (!isFinishing()) {
                    this.currentAudioTrack = playbackService.getAudioTrack();
                    this.currentSpuTrack = playbackService.getSpuTrack();
                    if (showTvUi && !this.waitingForPin) {
                        finish();
                    }
                }
                if (this.isMute) {
                    mute(false);
                }
                this.playbackStarted = false;
                this.handler.removeCallbacksAndMessages((Object) null);
                if (!playbackService.hasMedia() || !this.switchingView) {
                    if (playbackService.isSeekable()) {
                        this.savedTime = getTime();
                        long length = playbackService.getLength();
                        long j = this.savedTime;
                        if (length - j < CoroutineLiveDataKt.DEFAULT_TIMEOUT) {
                            this.savedTime = 0;
                        } else {
                            this.savedTime = j - ((long) OnRepeatListener.DEFAULT_SPEEDUP_DELAY);
                        }
                    }
                    PlaybackService.stop$default(playbackService, false, true, 1, (Object) null);
                } else if (this.switchToPopup) {
                    playbackService.switchToPopup(playbackService.getCurrentMediaPosition());
                } else {
                    playbackService.getMediaplayer().detachViews();
                    MediaWrapper currentMediaWrapper = playbackService.getCurrentMediaWrapper();
                    if (currentMediaWrapper != null) {
                        currentMediaWrapper.addFlags(8);
                    }
                    PlaybackService.showWithoutParse$default(playbackService, playbackService.getCurrentMediaPosition(), false, 2, (Object) null);
                }
            }
        }
    }

    public final void takeScreenshot() {
        if (!AndroidUtil.isOOrLater || Permissions.INSTANCE.canWriteStorage(this)) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new VideoPlayerActivity$takeScreenshot$2(this, (Continuation<? super VideoPlayerActivity$takeScreenshot$2>) null), 3, (Object) null);
        } else {
            Permissions.INSTANCE.askWriteStoragePermission(this, false, new VideoPlayerActivity$$ExternalSyntheticLambda22());
        }
    }

    private final void cleanUI() {
        PlaybackService playbackService;
        MediaPlayer mediaplayer;
        View view = this.rootView;
        if (view != null) {
            view.setKeepScreenOn(false);
        }
        if (!this.isBenchmark) {
            getDisplayManager().removeMediaRouterCallback();
        }
        if (!getDisplayManager().isSecondary() && (playbackService = this.service) != null && (mediaplayer = playbackService.getMediaplayer()) != null) {
            mediaplayer.detachViews();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        MediaWrapper currentMediaWrapper;
        super.onActivityResult(i, i2, intent);
        if (intent != null && intent.hasExtra(FilePickerFragmentKt.EXTRA_MRL)) {
            String stringExtra = intent.getStringExtra(FilePickerFragmentKt.EXTRA_MRL);
            Intrinsics.checkNotNull(stringExtra);
            Uri parse = Uri.parse(stringExtra);
            PlaybackService playbackService = this.service;
            if (playbackService != null) {
                Uri uri = FileUtils.INSTANCE.getUri(parse);
                if (uri != null) {
                    parse = uri;
                }
                playbackService.addSubtitleTrack(parse, false);
            }
            PlaybackService playbackService2 = this.service;
            if (!(playbackService2 == null || (currentMediaWrapper = playbackService2.getCurrentMediaWrapper()) == null)) {
                String location = currentMediaWrapper.getLocation();
                Intrinsics.checkNotNullExpressionValue(location, "getLocation(...)");
                String stringExtra2 = intent.getStringExtra(FilePickerFragmentKt.EXTRA_MRL);
                Intrinsics.checkNotNull(stringExtra2);
                ((SlaveRepository) SlaveRepository.Companion.getInstance(this)).saveSlave(location, 0, 2, stringExtra2);
            }
            this.addNextTrack = true;
        }
    }

    public void exit(int i) {
        if (!isFinishing()) {
            Intent intent = new Intent(ACTION_RESULT);
            Uri uri = this.videoUri;
            if (uri != null) {
                PlaybackService playbackService = this.service;
                if (playbackService != null) {
                    if (AndroidUtil.isNougatOrLater) {
                        intent.putExtra(EXTRA_URI, uri.toString());
                    } else {
                        intent.setData(this.videoUri);
                    }
                    intent.putExtra(EXTRA_POSITION, getTime());
                    intent.putExtra(EXTRA_DURATION, playbackService.getLength());
                }
                setResult(i, intent);
                finish();
            }
        }
    }

    /* access modifiers changed from: private */
    public final void exitOK() {
        exit(-1);
    }

    public boolean onTrackballEvent(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, NotificationCompat.CATEGORY_EVENT);
        if (this.isLoading) {
            return false;
        }
        VideoPlayerOverlayDelegate.showOverlay$default(getOverlayDelegate(), false, 1, (Object) null);
        return true;
    }

    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, NotificationCompat.CATEGORY_EVENT);
        if (this.isLoading || this.touchDelegate == null || !getTouchDelegate().dispatchGenericMotionEvent(motionEvent)) {
            return super.dispatchGenericMotionEvent(motionEvent);
        }
        return true;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        int i2 = i;
        KeyEvent keyEvent2 = keyEvent;
        Intrinsics.checkNotNullParameter(keyEvent2, NotificationCompat.CATEGORY_EVENT);
        if (this.service == null || i2 == 4 || i2 == 97) {
            return super.onKeyDown(i, keyEvent);
        }
        if (isOptionsListShowing()) {
            return false;
        }
        if (isPlaybackSettingActive$vlc_android_release() && i2 != 38 && i2 != 39 && i2 != 35 && i2 != 36) {
            return false;
        }
        if (!this.isLoading) {
            if (this.isShowing || (this.fov == 0.0f && i2 == 20 && !KotlinExtensionsKt.isVisible(getOverlayDelegate().getPlaylistContainer()))) {
                getOverlayDelegate().showOverlayTimeout(Settings.INSTANCE.getVideoHudDelay() * 1000);
            }
            if (i2 != 35) {
                ImageView imageView = null;
                if (i2 == 36) {
                    if (keyEvent.isCtrlPressed()) {
                        VideoPlayerOverlayDelegate.showOverlay$default(getOverlayDelegate(), false, 1, (Object) null);
                    } else {
                        VideoDelayDelegate.delayAudioOrSpu$default(getDelayDelegate(), 50000, false, IPlaybackSettingsController.DelayState.SUBS, 2, (Object) null);
                        this.handler.removeMessages(11);
                        this.handler.sendEmptyMessageDelayed(11, 4000);
                    }
                    return true;
                } else if (i2 == 38) {
                    VideoDelayDelegate.delayAudioOrSpu$default(getDelayDelegate(), -50000, false, IPlaybackSettingsController.DelayState.AUDIO, 2, (Object) null);
                    this.handler.removeMessages(11);
                    this.handler.sendEmptyMessageDelayed(11, 4000);
                    return true;
                } else if (i2 != 39) {
                    if (i2 != 47) {
                        if (i2 != 48) {
                            if (i2 != 85) {
                                if (i2 != 86) {
                                    if (i2 == 89) {
                                        getTouchDelegate().seekDelta$vlc_android_release((-Settings.INSTANCE.getVideoDoubleTapJumpDelay()) * 1000);
                                        return true;
                                    } else if (i2 == 90) {
                                        getTouchDelegate().seekDelta$vlc_android_release(Settings.INSTANCE.getVideoDoubleTapJumpDelay() * 1000);
                                        return true;
                                    } else if (!(i2 == 126 || i2 == 127)) {
                                        switch (i2) {
                                            case 19:
                                                if (this.isNavMenu) {
                                                    return navigateDvdMenu$vlc_android_release(i);
                                                }
                                                if (this.isLocked) {
                                                    getOverlayDelegate().showOverlayTimeout(Settings.INSTANCE.getVideoHudDelay() * 1000);
                                                    break;
                                                } else if (keyEvent.isCtrlPressed()) {
                                                    volumeUp();
                                                    return true;
                                                } else if (!this.isShowing && !KotlinExtensionsKt.isVisible(getOverlayDelegate().getPlaylistContainer())) {
                                                    if (this.fov == 0.0f) {
                                                        showAdvancedOptions();
                                                    } else {
                                                        PlaybackService playbackService = this.service;
                                                        if (playbackService != null) {
                                                            playbackService.updateViewpoint(0.0f, -5.0f, 0.0f, 0.0f, false);
                                                        }
                                                    }
                                                    return true;
                                                }
                                            case 20:
                                                if (this.isNavMenu) {
                                                    return navigateDvdMenu$vlc_android_release(i);
                                                }
                                                if (this.isLocked) {
                                                    getOverlayDelegate().showOverlayTimeout(Settings.INSTANCE.getVideoHudDelay() * 1000);
                                                    break;
                                                } else if (keyEvent.isCtrlPressed()) {
                                                    volumeDown();
                                                    return true;
                                                } else if (!this.isShowing && this.fov != 0.0f) {
                                                    PlaybackService playbackService2 = this.service;
                                                    if (playbackService2 != null) {
                                                        playbackService2.updateViewpoint(0.0f, 5.0f, 0.0f, 0.0f, false);
                                                    }
                                                    return true;
                                                }
                                            case 21:
                                                if (this.isNavMenu) {
                                                    return navigateDvdMenu$vlc_android_release(i);
                                                }
                                                if (this.isLocked) {
                                                    getOverlayDelegate().showOverlayTimeout(Settings.INSTANCE.getVideoHudDelay() * 1000);
                                                    break;
                                                } else if (!this.isShowing && !KotlinExtensionsKt.isVisible(getOverlayDelegate().getPlaylistContainer()) && !getResizeDelegate().isShowing()) {
                                                    if (keyEvent.isAltPressed() && keyEvent.isCtrlPressed()) {
                                                        getTouchDelegate().seekDelta$vlc_android_release(-300000);
                                                    } else if (keyEvent.isShiftPressed() && keyEvent.isCtrlPressed()) {
                                                        getTouchDelegate().seekDelta$vlc_android_release(-30000);
                                                    } else if (keyEvent.isShiftPressed()) {
                                                        getTouchDelegate().seekDelta$vlc_android_release(-5000);
                                                    } else if (keyEvent.isCtrlPressed()) {
                                                        getTouchDelegate().seekDelta$vlc_android_release(-60000);
                                                    } else if (this.fov == 0.0f) {
                                                        getTouchDelegate().seekDelta$vlc_android_release((-Settings.INSTANCE.getVideoDoubleTapJumpDelay()) * 1000);
                                                    } else {
                                                        PlaybackService playbackService3 = this.service;
                                                        if (playbackService3 != null) {
                                                            playbackService3.updateViewpoint(-5.0f, 0.0f, 0.0f, 0.0f, false);
                                                        }
                                                    }
                                                    return true;
                                                }
                                                break;
                                            case 22:
                                                if (this.isNavMenu) {
                                                    return navigateDvdMenu$vlc_android_release(i);
                                                }
                                                if (this.isLocked) {
                                                    getOverlayDelegate().showOverlayTimeout(Settings.INSTANCE.getVideoHudDelay() * 1000);
                                                    break;
                                                } else if (!this.isShowing && !KotlinExtensionsKt.isVisible(getOverlayDelegate().getPlaylistContainer()) && !getResizeDelegate().isShowing()) {
                                                    if (keyEvent.isAltPressed() && keyEvent.isCtrlPressed()) {
                                                        getTouchDelegate().seekDelta$vlc_android_release(300000);
                                                    } else if (keyEvent.isShiftPressed() && keyEvent.isCtrlPressed()) {
                                                        getTouchDelegate().seekDelta$vlc_android_release(30000);
                                                    } else if (keyEvent.isShiftPressed()) {
                                                        getTouchDelegate().seekDelta$vlc_android_release(5000);
                                                    } else if (keyEvent.isCtrlPressed()) {
                                                        getTouchDelegate().seekDelta$vlc_android_release(60000);
                                                    } else if (this.fov == 0.0f) {
                                                        getTouchDelegate().seekDelta$vlc_android_release(Settings.INSTANCE.getVideoDoubleTapJumpDelay() * 1000);
                                                    } else {
                                                        PlaybackService playbackService4 = this.service;
                                                        if (playbackService4 != null) {
                                                            playbackService4.updateViewpoint(5.0f, 0.0f, 0.0f, 0.0f, false);
                                                        }
                                                    }
                                                    return true;
                                                }
                                                break;
                                            case 23:
                                                if (this.isNavMenu) {
                                                    return navigateDvdMenu$vlc_android_release(i);
                                                }
                                                if (this.isLocked) {
                                                    getOverlayDelegate().showOverlayTimeout(Settings.INSTANCE.getVideoHudDelay() * 1000);
                                                    break;
                                                } else if (!this.isShowing && !getResizeDelegate().isShowing()) {
                                                    doPlayPause();
                                                    return true;
                                                }
                                            case 24:
                                                volumeUp();
                                                return true;
                                            case 25:
                                                volumeDown();
                                                return true;
                                            default:
                                                switch (i2) {
                                                    case 29:
                                                        resizeVideo();
                                                        return true;
                                                    case 31:
                                                        resizeVideo();
                                                        return true;
                                                    case 41:
                                                    case 164:
                                                        updateMute();
                                                        return true;
                                                    case 50:
                                                    case 99:
                                                    case 222:
                                                        if (getOverlayDelegate().isHudBindingInitialized()) {
                                                            imageView = getOverlayDelegate().getHudBinding().playerOverlayTracks;
                                                        }
                                                        onAudioSubClick(imageView);
                                                        return true;
                                                    case 54:
                                                        resizeVideo();
                                                        return true;
                                                    case 62:
                                                        break;
                                                    case 66:
                                                        if (this.isNavMenu) {
                                                            return navigateDvdMenu$vlc_android_release(i);
                                                        }
                                                        return super.onKeyDown(i, keyEvent);
                                                    case 96:
                                                        if (getOverlayDelegate().isHudBindingInitialized()) {
                                                            ConstraintLayout constraintLayout = getOverlayDelegate().getHudBinding().progressOverlay;
                                                            Intrinsics.checkNotNullExpressionValue(constraintLayout, "progressOverlay");
                                                            if (KotlinExtensionsKt.isVisible(constraintLayout)) {
                                                                return false;
                                                            }
                                                        }
                                                        if (this.isNavMenu) {
                                                            return navigateDvdMenu$vlc_android_release(i);
                                                        }
                                                        if (i2 == 85) {
                                                            return super.onKeyDown(i, keyEvent);
                                                        }
                                                        doPlayPause();
                                                        return true;
                                                    case 175:
                                                        if (getOverlayDelegate().isHudBindingInitialized()) {
                                                            imageView = getOverlayDelegate().getHudBinding().playerOverlayTracks;
                                                        }
                                                        onAudioSubClick(imageView);
                                                        return true;
                                                }
                                        }
                                    }
                                }
                            }
                            if (this.isNavMenu) {
                                return navigateDvdMenu$vlc_android_release(i);
                            }
                            if (i2 == 85) {
                                return super.onKeyDown(i, keyEvent);
                            }
                            doPlayPause();
                            return true;
                        }
                        VideoPlayerOverlayDelegate.showOverlay$default(getOverlayDelegate(), false, 1, (Object) null);
                        if (getPlayerKeyListenerDelegate().onKeyDown(i2, keyEvent2)) {
                            return true;
                        }
                        return super.onKeyDown(i, keyEvent);
                    }
                    exitOK();
                    return true;
                } else {
                    getDelayDelegate().showDelayControls();
                    VideoDelayDelegate.delayAudioOrSpu$default(getDelayDelegate(), 50000, false, IPlaybackSettingsController.DelayState.AUDIO, 2, (Object) null);
                    this.handler.removeMessages(11);
                    this.handler.sendEmptyMessageDelayed(11, 4000);
                    return true;
                }
            } else {
                VideoDelayDelegate.delayAudioOrSpu$default(getDelayDelegate(), -50000, false, IPlaybackSettingsController.DelayState.SUBS, 2, (Object) null);
                this.handler.removeMessages(11);
                this.handler.sendEmptyMessageDelayed(11, 4000);
                return true;
            }
        } else if (i2 != 47 && i2 != 86) {
            return false;
        } else {
            exitOK();
            return true;
        }
    }

    public void showEqualizer() {
        EqualizerFragment equalizerFragment = new EqualizerFragment();
        equalizerFragment.setOnDismissListener(new VideoPlayerActivity$$ExternalSyntheticLambda21(this));
        equalizerFragment.show(getSupportFragmentManager(), "equalizer");
    }

    /* access modifiers changed from: private */
    public static final void showEqualizer$lambda$22(VideoPlayerActivity videoPlayerActivity, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(videoPlayerActivity, "this$0");
        videoPlayerActivity.getOverlayDelegate().dimStatusBar(true);
    }

    public void next() {
        PlaybackService playbackService = this.service;
        if (playbackService != null && playbackService.hasNext()) {
            PlaybackService playbackService2 = this.service;
            if (playbackService2 != null) {
                PlaybackService.next$default(playbackService2, false, 1, (Object) null);
            }
            VideoPlayerOverlayDelegate overlayDelegate = getOverlayDelegate();
            String string = getString(R.string.next);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            VideoPlayerOverlayDelegate.showInfo$default(overlayDelegate, string, 1000, (String) null, 4, (Object) null);
        }
    }

    public void previous() {
        PlaybackService playbackService = this.service;
        if (playbackService != null) {
            playbackService.previous(false);
            VideoPlayerOverlayDelegate overlayDelegate = getOverlayDelegate();
            String string = getString(R.string.previous);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            VideoPlayerOverlayDelegate.showInfo$default(overlayDelegate, string, 1000, (String) null, 4, (Object) null);
        }
    }

    public void stop() {
        PlaybackService playbackService = this.service;
        if (playbackService != null) {
            PlaybackService.stop$default(playbackService, false, false, 3, (Object) null);
            VideoPlayerOverlayDelegate overlayDelegate = getOverlayDelegate();
            String string = getString(R.string.stop);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            VideoPlayerOverlayDelegate.showInfo$default(overlayDelegate, string, 1000, (String) null, 4, (Object) null);
        }
    }

    public void seek(int i) {
        getTouchDelegate().seekDelta$vlc_android_release(i);
    }

    public void togglePlayPause() {
        doPlayPause();
    }

    public void increaseRate() {
        PlaybackService playbackService = this.service;
        if (playbackService != null) {
            playbackService.increaseRate();
        }
    }

    public void decreaseRate() {
        PlaybackService playbackService = this.service;
        if (playbackService != null) {
            playbackService.decreaseRate();
        }
    }

    public void resetRate() {
        PlaybackService playbackService = this.service;
        if (playbackService != null) {
            playbackService.resetRate();
        }
    }

    public void bookmark() {
        getBookmarkModel().addBookmark(this);
        String string = getString(R.string.bookmark_added);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        UiTools.snackerConfirm$default(UiTools.INSTANCE, this, string, false, R.string.show, new VideoPlayerActivity$bookmark$1(this), 4, (Object) null);
    }

    public void showAdvancedOptions() {
        PlaybackService playbackService;
        if (this.optionsDelegate == null && (playbackService = this.service) != null) {
            PlayerOptionsDelegate playerOptionsDelegate = new PlayerOptionsDelegate(this, playbackService, false, 4, (DefaultConstructorMarker) null);
            this.optionsDelegate = playerOptionsDelegate;
            Intrinsics.checkNotNull(playerOptionsDelegate);
            playerOptionsDelegate.setBookmarkClickedListener(new VideoPlayerActivity$showAdvancedOptions$1$1(this));
        }
        PlayerOptionsDelegate playerOptionsDelegate2 = this.optionsDelegate;
        if (playerOptionsDelegate2 != null) {
            playerOptionsDelegate2.show();
        }
        getOverlayDelegate().hideOverlay(false, true);
    }

    private final void volumeUp() {
        int i;
        if (this.isMute) {
            updateMute();
            return;
        }
        PlaybackService playbackService = this.service;
        if (playbackService != null) {
            int i2 = 1;
            if (getAudiomanager$vlc_android_release().getStreamVolume(3) < this.audioMax) {
                i = getAudiomanager$vlc_android_release().getStreamVolume(3) + 1;
            } else {
                i = MathKt.roundToInt(((((float) playbackService.getVolume()) * ((float) this.audioMax)) / ((float) 100)) + ((float) 1));
            }
            int coerceAtLeast = RangesKt.coerceAtLeast(i, 0);
            int i3 = this.audioMax;
            if (this.isAudioBoostEnabled) {
                i2 = 2;
            }
            setAudioVolume$vlc_android_release(RangesKt.coerceAtMost(coerceAtLeast, i3 * i2));
        }
    }

    private final void volumeDown() {
        int i;
        PlaybackService playbackService = this.service;
        if (playbackService != null) {
            int i2 = 1;
            if (playbackService.getVolume() > 100) {
                i = MathKt.roundToInt(((((float) playbackService.getVolume()) * ((float) this.audioMax)) / ((float) 100)) - ((float) 1));
            } else {
                i = getAudiomanager$vlc_android_release().getStreamVolume(3) - 1;
            }
            int coerceAtLeast = RangesKt.coerceAtLeast(i, 0);
            int i3 = this.audioMax;
            if (this.isAudioBoostEnabled) {
                i2 = 2;
            }
            int coerceAtMost = RangesKt.coerceAtMost(coerceAtLeast, i3 * i2);
            this.originalVol = (float) coerceAtMost;
            setAudioVolume$vlc_android_release(coerceAtMost);
        }
    }

    public final boolean navigateDvdMenu$vlc_android_release(int i) {
        if (!(i == 66 || i == 96 || i == 99)) {
            switch (i) {
                case 19:
                    PlaybackService playbackService = this.service;
                    if (playbackService != null) {
                        playbackService.navigate(1);
                    }
                    return true;
                case 20:
                    PlaybackService playbackService2 = this.service;
                    if (playbackService2 != null) {
                        playbackService2.navigate(2);
                    }
                    return true;
                case 21:
                    PlaybackService playbackService3 = this.service;
                    if (playbackService3 != null) {
                        playbackService3.navigate(3);
                    }
                    return true;
                case 22:
                    PlaybackService playbackService4 = this.service;
                    if (playbackService4 != null) {
                        playbackService4.navigate(4);
                    }
                    return true;
                case 23:
                    break;
                default:
                    return false;
            }
        }
        PlaybackService playbackService5 = this.service;
        if (playbackService5 != null) {
            playbackService5.navigate(0);
        }
        return true;
    }

    public void update() {
        PlaylistModel playlistModel2;
        if (this.service != null && getOverlayDelegate().isPlaylistAdapterInitialized() && (playlistModel2 = this.playlistModel) != null) {
            playlistModel2.update();
        }
    }

    public void onMediaEvent(IMedia.Event event) {
        Intrinsics.checkNotNullParameter(event, NotificationCompat.CATEGORY_EVENT);
        if (event.type == 3) {
            updateNavStatus();
        }
    }

    public void onMediaPlayerEvent(MediaPlayer.Event event) {
        VlcTrack currentVideoTrack;
        MediaPlayer.Event event2 = event;
        Intrinsics.checkNotNullParameter(event2, NotificationCompat.CATEGORY_EVENT);
        PlaybackService playbackService = this.service;
        if (playbackService != null) {
            int i = event2.type;
            if (i == 269) {
                getOverlayDelegate().updateSeekable(event.getSeekable());
            } else if (i == 270) {
                getOverlayDelegate().updatePausable(event.getPausable());
            } else if (i != 274) {
                switch (i) {
                    case MediaPlayer.Event.Opening:
                        this.forcedTime = -1;
                        CharSequence charSequence = this.subtitlesExtraPath;
                        if (charSequence != null && charSequence.length() != 0) {
                            String str = this.subtitlesExtraPath;
                            Intrinsics.checkNotNull(str);
                            playbackService.addSubtitleTrack(str, true);
                            this.subtitlesExtraPath = null;
                            return;
                        }
                        return;
                    case MediaPlayer.Event.Buffering:
                        if (!this.isPlaying) {
                            return;
                        }
                        if (event.getBuffering() == 100.0f) {
                            stopLoading();
                            return;
                        } else if (!this.handler.hasMessages(7) && !this.isLoading) {
                            if ((this.touchDelegate == null || !getTouchDelegate().isSeeking()) && !this.isDragging) {
                                this.handler.sendEmptyMessageDelayed(7, 1000);
                                return;
                            }
                            return;
                        } else {
                            return;
                        }
                    case MediaPlayer.Event.Playing:
                        onPlaying();
                        return;
                    case MediaPlayer.Event.Paused:
                        VideoPlayerOverlayDelegate.updateOverlayPausePlay$default(getOverlayDelegate(), false, 1, (Object) null);
                        return;
                    default:
                        switch (i) {
                            case MediaPlayer.Event.ESAdded:
                                if (this.menuIdx == -1) {
                                    MediaWrapper currentMediaWrapper = playbackService.getCurrentMediaWrapper();
                                    if (currentMediaWrapper != null) {
                                        if (event.getEsChangedType() == 0) {
                                            Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), (CoroutineStart) null, new VideoPlayerActivity$onMediaPlayerEvent$1$1(this, currentMediaWrapper, playbackService, (Continuation<? super VideoPlayerActivity$onMediaPlayerEvent$1$1>) null), 2, (Object) null);
                                        } else if (event.getEsChangedType() == 2) {
                                            Job unused2 = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), (CoroutineStart) null, new VideoPlayerActivity$onMediaPlayerEvent$1$2(this, currentMediaWrapper, playbackService, (Continuation<? super VideoPlayerActivity$onMediaPlayerEvent$1$2>) null), 2, (Object) null);
                                        }
                                    } else {
                                        return;
                                    }
                                }
                                if (this.menuIdx == -1 && event.getEsChangedType() == 1) {
                                    this.handler.removeMessages(6);
                                    this.handler.sendEmptyMessageDelayed(6, 1000);
                                    Job unused3 = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), (CoroutineStart) null, new VideoPlayerActivity$onMediaPlayerEvent$1$3(playbackService, this, (Continuation<? super VideoPlayerActivity$onMediaPlayerEvent$1$3>) null), 2, (Object) null);
                                    return;
                                }
                                return;
                            case MediaPlayer.Event.ESDeleted:
                                if (this.menuIdx == -1 && event.getEsChangedType() == 1) {
                                    this.handler.removeMessages(6);
                                    this.handler.sendEmptyMessageDelayed(6, 1000);
                                    return;
                                }
                                return;
                            case MediaPlayer.Event.ESSelected:
                                if (event.getEsChangedType() == 1 && (currentVideoTrack = playbackService.getCurrentVideoTrack()) != null) {
                                    this.fov = currentVideoTrack.getProjection() == 0 ? 0.0f : 80.0f;
                                    return;
                                }
                                return;
                            default:
                                return;
                        }
                }
            } else {
                updateNavStatus();
                if (event.getVoutCount() > 0) {
                    playbackService.getMediaplayer().updateVideoSurfaces();
                }
                if (this.menuIdx == -1) {
                    handleVout(event.getVoutCount());
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public final List<IMedia.Track> getCurrentMediaTracks() {
        PlaybackService playbackService = this.service;
        if (playbackService == null) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        IMedia media = playbackService.getMediaplayer().getMedia();
        if (media != null) {
            Pair<String, ? extends List<? extends IMedia.Track>> pair = this.currentTracks;
            if (Intrinsics.areEqual((Object) pair != null ? pair.getFirst() : null, (Object) media.getUri().toString())) {
                Pair<String, ? extends List<? extends IMedia.Track>> pair2 = this.currentTracks;
                Intrinsics.checkNotNull(pair2);
                return (List) pair2.getSecond();
            }
            Intrinsics.checkNotNull(media);
            arrayList.addAll(VersionDependantKt.getAllTracks(media));
            String uri = media.getUri().toString();
            Intrinsics.checkNotNullExpressionValue(uri, "toString(...)");
            this.currentTracks = new Pair<>(uri, arrayList);
        }
        return arrayList;
    }

    private final void onPlaying() {
        MediaWrapper currentMediaWrapper;
        PlaylistManager playlistManager;
        PlayerController player;
        MediaPlayer mediaplayer;
        VlcTrack selectedVideoTrack;
        PlaybackService playbackService = this.service;
        if (playbackService != null && (currentMediaWrapper = playbackService.getCurrentMediaWrapper()) != null) {
            this.isPlaying = true;
            VideoPlayerOverlayDelegate overlayDelegate = getOverlayDelegate();
            PlaybackService playbackService2 = this.service;
            overlayDelegate.setHasPlaylist(playbackService2 != null && playbackService2.hasPlaylist());
            setPlaybackParameters();
            stopLoading();
            View view = null;
            VideoPlayerOverlayDelegate.updateOverlayPausePlay$default(getOverlayDelegate(), false, 1, (Object) null);
            updateNavStatus();
            if (currentMediaWrapper.hasFlag(4) || Settings.INSTANCE.getVideoHudDelay() == -1) {
                currentMediaWrapper.removeFlags(4);
                this.wasPaused = false;
            } else {
                this.handler.sendEmptyMessageDelayed(1, ((long) Settings.INSTANCE.getVideoHudDelay()) * ((long) 1000));
            }
            setESTracks();
            if (getOverlayDelegate().isHudRightBindingInitialized() && getOverlayDelegate().getHudRightBinding().playerOverlayTitle.length() == 0) {
                getOverlayDelegate().getHudRightBinding().playerOverlayTitle.setText(currentMediaWrapper.getTitle());
            }
            observeDownloadedSubtitles();
            PlayerOptionsDelegate playerOptionsDelegate = this.optionsDelegate;
            if (playerOptionsDelegate != null) {
                playerOptionsDelegate.setup();
            }
            SharedPreferences.Editor edit = getSettings().edit();
            edit.remove(SettingsKt.VIDEO_PAUSED);
            edit.apply();
            if (isInPictureInPictureMode() && Build.VERSION.SDK_INT >= 26) {
                PlaybackService playbackService3 = this.service;
                if (playbackService3 != null && (playlistManager = playbackService3.getPlaylistManager()) != null && (player = playlistManager.getPlayer()) != null && (mediaplayer = player.getMediaplayer()) != null && (selectedVideoTrack = VersionDependantKt.getSelectedVideoTrack(mediaplayer)) != null) {
                    Rational rational = new Rational(RangesKt.coerceAtMost(selectedVideoTrack.getWidth(), (int) (((float) selectedVideoTrack.getHeight()) * 2.39f)), selectedVideoTrack.getHeight());
                    if (rational.isFinite() && !rational.isZero()) {
                        PictureInPictureParams m = new PictureInPictureParams.Builder().setAspectRatio(rational).build();
                        Intrinsics.checkNotNullExpressionValue(m, "build(...)");
                        setPictureInPictureParams(m);
                    }
                } else {
                    return;
                }
            }
            if (getTipsDelegate().getCurrentTip() != null) {
                pause();
            }
            if (getSettings().getBoolean("video_match_frame_rate", false)) {
                View view2 = this.rootView;
                if (view2 != null) {
                    view = view2.findViewById(R.id.surface_video);
                }
                Intrinsics.checkNotNull(view, "null cannot be cast to non-null type android.view.SurfaceView");
                PlaybackService playbackService4 = this.service;
                Intrinsics.checkNotNull(playbackService4);
                FrameRateManager frameRateManager = new FrameRateManager(this, playbackService4);
                Window window = getWindow();
                Intrinsics.checkNotNullExpressionValue(window, "getWindow(...)");
                frameRateManager.matchFrameRate((SurfaceView) view, window);
            }
        }
    }

    private final void encounteredError() {
        if (!isFinishing()) {
            PlaybackService playbackService = this.service;
            if (playbackService == null || !playbackService.hasNext()) {
                AlertDialog create = new AlertDialog.Builder(this).setOnCancelListener(new VideoPlayerActivity$$ExternalSyntheticLambda12(this)).setPositiveButton(R.string.ok, (DialogInterface.OnClickListener) new VideoPlayerActivity$$ExternalSyntheticLambda13(this)).setTitle(R.string.encountered_error_title).setMessage(R.string.encountered_error_message).create();
                create.show();
                this.alertDialog = create;
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void encounteredError$lambda$32(VideoPlayerActivity videoPlayerActivity, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(videoPlayerActivity, "this$0");
        videoPlayerActivity.exit(3);
    }

    /* access modifiers changed from: private */
    public static final void encounteredError$lambda$33(VideoPlayerActivity videoPlayerActivity, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(videoPlayerActivity, "this$0");
        videoPlayerActivity.exit(3);
    }

    private final void handleVout(int i) {
        IVLCVout vout;
        this.handler.removeCallbacks(this.switchAudioRunnable);
        PlaybackService playbackService = this.service;
        if (playbackService != null && (vout = playbackService.getVout()) != null && getDisplayManager().isPrimary() && vout.areViewsAttached() && i == 0) {
            this.handler.postDelayed(this.switchAudioRunnable, 4000);
        }
    }

    public void recreate() {
        this.handler.removeCallbacks(this.switchAudioRunnable);
        super.recreate();
    }

    public final void switchToAudioMode(boolean z) {
        if (this.service != null) {
            this.switchingView = true;
            PlaylistManager.Companion.setPlayingAsAudio(z);
            if (z && getIntent().getBooleanExtra(FROM_EXTERNAL, false)) {
                Intent intent = new Intent();
                intent.setClassName(getApplicationContext(), this.isTv ? Constants.TV_AUDIOPLAYER_ACTIVITY : Constants.MOBILE_MAIN_ACTIVITY);
                startActivity(intent);
            }
            exitOK();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r0.isInPiPMode();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isInPictureInPictureMode() {
        /*
            r1 = this;
            org.videolan.vlc.PlaybackService r0 = r1.service
            if (r0 == 0) goto L_0x0011
            androidx.lifecycle.MutableLiveData r0 = r0.isInPiPMode()
            if (r0 == 0) goto L_0x0011
            java.lang.Object r0 = r0.getValue()
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            goto L_0x0012
        L_0x0011:
            r0 = 0
        L_0x0012:
            if (r0 != 0) goto L_0x0016
            r0 = 0
            goto L_0x001a
        L_0x0016:
            boolean r0 = r0.booleanValue()
        L_0x001a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.video.VideoPlayerActivity.isInPictureInPictureMode():boolean");
    }

    public void setPictureInPictureParams(PictureInPictureParams pictureInPictureParams) {
        Intrinsics.checkNotNullParameter(pictureInPictureParams, "params");
        try {
            super.setPictureInPictureParams(pictureInPictureParams);
        } catch (IllegalArgumentException unused) {
        }
    }

    public void onPictureInPictureModeChanged(boolean z) {
        MediaPlayer mediaplayer;
        super.onPictureInPictureModeChanged(z);
        PlaybackService playbackService = this.service;
        MutableLiveData<Boolean> isInPiPMode = playbackService != null ? playbackService.isInPiPMode() : null;
        if (isInPiPMode != null) {
            isInPiPMode.setValue(Boolean.valueOf(z));
        }
        PlaybackService playbackService2 = this.service;
        if (playbackService2 != null && (mediaplayer = playbackService2.getMediaplayer()) != null) {
            mediaplayer.updateVideoSurfaces();
        }
    }

    public final void sendMouseEvent$vlc_android_release(int i, int i2, int i3) {
        IVLCVout vout;
        PlaybackService playbackService = this.service;
        if (playbackService != null && (vout = playbackService.getVout()) != null) {
            vout.sendMouseEvent(i, 0, i2, i3);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, NotificationCompat.CATEGORY_EVENT);
        return this.service != null && getTouchDelegate().onTouchEvent(motionEvent);
    }

    public final boolean updateViewpoint$vlc_android_release(float f, float f2, float f3) {
        PlaybackService playbackService = this.service;
        if (playbackService != null) {
            return playbackService.updateViewpoint(f, f2, 0.0f, f3, false);
        }
        return false;
    }

    public final Unit initAudioVolume$vlc_android_release() {
        PlaybackService playbackService = this.service;
        if (playbackService == null) {
            return null;
        }
        if (playbackService.getVolume() <= 100) {
            this.volume = (float) getAudiomanager$vlc_android_release().getStreamVolume(3);
            this.originalVol = (float) getAudiomanager$vlc_android_release().getStreamVolume(3);
        } else {
            this.volume = (((float) playbackService.getVolume()) * ((float) this.audioMax)) / ((float) 100);
        }
        return Unit.INSTANCE;
    }

    public final void displayWarningToast() {
        Toast toast = this.warningToast;
        if (toast != null) {
            toast.cancel();
        }
        Toast makeText = Toast.makeText(getApplication(), R.string.audio_boost_warning, 0);
        makeText.setGravity(83, KotlinExtensionsKt.getDp(16), 0);
        makeText.show();
        this.warningToast = makeText;
    }

    public final void setAudioVolume$vlc_android_release(int i) {
        int i2;
        if (AndroidUtil.isNougatOrLater) {
            boolean z = i <= 0;
            boolean z2 = this.isMute;
            if (z ^ z2) {
                mute(!z2);
                return;
            }
        }
        PlaybackService playbackService = this.service;
        if (playbackService != null) {
            int i3 = this.audioMax;
            if (i <= i3) {
                playbackService.setVolume(100);
                if (i != getAudiomanager$vlc_android_release().getStreamVolume(3)) {
                    try {
                        getAudiomanager$vlc_android_release().setStreamVolume(3, i, 0);
                        if (getAudiomanager$vlc_android_release().getStreamVolume(3) != i) {
                            getAudiomanager$vlc_android_release().setStreamVolume(3, i, 1);
                        }
                    } catch (RuntimeException unused) {
                    }
                }
                i2 = MathKt.roundToInt(((float) (i * 100)) / ((float) this.audioMax));
            } else {
                i2 = MathKt.roundToInt(((float) (i * 100)) / ((float) i3));
                playbackService.setVolume(MathKt.roundToInt((float) i2));
            }
            getOverlayDelegate().showVolumeBar(i2);
            this.volSave = playbackService.getVolume();
        }
    }

    private final void mute(boolean z) {
        PlaybackService playbackService = this.service;
        if (playbackService != null) {
            this.isMute = z;
            if (z) {
                this.volSave = playbackService.getVolume();
            }
            playbackService.setVolume(this.isMute ? 0 : this.volSave);
        }
    }

    private final void updateMute() {
        mute(!this.isMute);
        VideoPlayerOverlayDelegate.showInfo$default(getOverlayDelegate(), this.isMute ? R.string.sound_off : R.string.sound_on, 1000, 0, 4, (Object) null);
    }

    public final void changeBrightness$vlc_android_release(float f) {
        float coerceIn = RangesKt.coerceIn(getWindow().getAttributes().screenBrightness + f, 0.01f, 1.0f);
        setWindowBrightness(coerceIn);
        getOverlayDelegate().showBrightnessBar((int) ((float) MathKt.roundToInt(coerceIn * ((float) 100))));
    }

    private final void setWindowBrightness(float f) {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.screenBrightness = f;
        getWindow().setAttributes(attributes);
    }

    public void onAudioSubClick(View view) {
        getOverlayDelegate().showTracks();
        VideoPlayerOverlayDelegate.hideOverlay$default(getOverlayDelegate(), false, false, 2, (Object) null);
    }

    public void onPopupMenu(View view, int i, MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(view, "view");
        Context context = this;
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.getMenuInflater().inflate(R.menu.video_playqueue_item, popupMenu.getMenu());
        if (UiTools.INSTANCE.isTablet(context) || AndroidDevices.INSTANCE.isTv()) {
            popupMenu.getMenu().removeGroup(R.id.phone_only);
        }
        popupMenu.setOnMenuItemClickListener(new VideoPlayerActivity$$ExternalSyntheticLambda17(this, i));
        popupMenu.show();
    }

    /* access modifiers changed from: private */
    public static final boolean onPopupMenu$lambda$41(VideoPlayerActivity videoPlayerActivity, int i, MenuItem menuItem) {
        PlaybackService service2;
        PlaylistManager playlistManager;
        Intrinsics.checkNotNullParameter(videoPlayerActivity, "this$0");
        int itemId = menuItem.getItemId();
        if (itemId == R.id.audio_player_mini_remove) {
            PlaybackService playbackService = videoPlayerActivity.service;
            if (playbackService == null) {
                return false;
            }
            playbackService.remove(i);
            return true;
        } else if (itemId != R.id.stop_after) {
            return false;
        } else {
            PlaylistModel playlistModel2 = videoPlayerActivity.playlistModel;
            if (!(playlistModel2 == null || (service2 = playlistModel2.getService()) == null || (playlistManager = service2.getPlaylistManager()) == null || playlistManager.getStopAfter() != i)) {
                i = -1;
            }
            PlaylistModel playlistModel3 = videoPlayerActivity.playlistModel;
            if (playlistModel3 != null) {
                playlistModel3.stopAfter(i);
            }
            videoPlayerActivity.getOverlayDelegate().getPlaylistAdapter().setStopAfter(i);
            menuItem.setChecked(true);
            return false;
        }
    }

    public Lifecycle getLifeCycle() {
        Lifecycle lifecycle = getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle, "<get-lifecycle>(...)");
        return lifecycle;
    }

    public void onSelectionSet(int i) {
        getOverlayDelegate().getPlaylist().scrollToPosition(i);
    }

    public void playItem(int i, MediaWrapper mediaWrapper) {
        PlaylistManager playlistManager;
        List<MediaWrapper> mediaList;
        PlaybackService playbackService;
        PlaylistManager playlistManager2;
        Intrinsics.checkNotNullParameter(mediaWrapper, "item");
        PlaybackService playbackService2 = this.service;
        if (playbackService2 != null) {
            playbackService2.saveMediaMeta();
        }
        PlaybackService playbackService3 = this.service;
        if (!(playbackService3 == null || (playlistManager2 = playbackService3.getPlaylistManager()) == null)) {
            playlistManager2.getMedia(i);
        }
        PlaybackService playbackService4 = this.service;
        if (!(playbackService4 == null || (playlistManager = playbackService4.getPlaylistManager()) == null || (mediaList = playlistManager.getMediaList()) == null)) {
            if (Intrinsics.areEqual((Object) mediaList.get(i), (Object) mediaWrapper)) {
                PlaybackService playbackService5 = this.service;
                if (playbackService5 != null) {
                    PlaybackService.playIndex$default(playbackService5, i, 0, 2, (Object) null);
                }
            } else {
                int i2 = 0;
                for (MediaWrapper areEqual : mediaList) {
                    int i3 = i2 + 1;
                    if (Intrinsics.areEqual((Object) mediaWrapper, (Object) areEqual) && (playbackService = this.service) != null) {
                        PlaybackService.playIndex$default(playbackService, i2, 0, 2, (Object) null);
                    }
                    i2 = i3;
                }
            }
        }
        getOverlayDelegate().togglePlaylist();
    }

    public void onClick(View view) {
        PlaylistManager playlistManager;
        PlaylistManager playlistManager2;
        PlaylistManager playlistManager3;
        PlaylistManager playlistManager4;
        PlaylistManager playlistManager5;
        PlaylistManager playlistManager6;
        PlaylistManager playlistManager7;
        Intrinsics.checkNotNullParameter(view, "v");
        int id = view.getId();
        if (id == R.id.orientation_toggle) {
            toggleOrientationLock();
        } else if (id == R.id.playlist_toggle) {
            getOverlayDelegate().togglePlaylist();
        } else if (id == R.id.player_overlay_forward) {
            getTouchDelegate().seekDelta$vlc_android_release(Settings.INSTANCE.getVideoJumpDelay() * 1000);
        } else if (id == R.id.player_overlay_rewind) {
            getTouchDelegate().seekDelta$vlc_android_release((-Settings.INSTANCE.getVideoJumpDelay()) * 1000);
        } else {
            MediaWrapper mediaWrapper = null;
            if (id == R.id.ab_repeat_add_marker) {
                PlaybackService playbackService = this.service;
                if (playbackService != null && (playlistManager6 = playbackService.getPlaylistManager()) != null) {
                    PlaybackService playbackService2 = this.service;
                    if (!(playbackService2 == null || (playlistManager7 = playbackService2.getPlaylistManager()) == null)) {
                        mediaWrapper = playlistManager7.getCurrentMedia();
                    }
                    playlistManager6.setABRepeatValue(mediaWrapper, (long) getOverlayDelegate().getHudBinding().playerOverlaySeekbar.getProgress());
                }
            } else if (id == R.id.ab_repeat_reset) {
                PlaybackService playbackService3 = this.service;
                if (playbackService3 != null && (playlistManager4 = playbackService3.getPlaylistManager()) != null) {
                    PlaybackService playbackService4 = this.service;
                    if (!(playbackService4 == null || (playlistManager5 = playbackService4.getPlaylistManager()) == null)) {
                        mediaWrapper = playlistManager5.getCurrentMedia();
                    }
                    playlistManager4.resetABRepeatValues(mediaWrapper);
                }
            } else if (id == R.id.ab_repeat_stop) {
                PlaybackService playbackService5 = this.service;
                if (!(playbackService5 == null || (playlistManager2 = playbackService5.getPlaylistManager()) == null)) {
                    PlaybackService playbackService6 = this.service;
                    if (!(playbackService6 == null || (playlistManager3 = playbackService6.getPlaylistManager()) == null)) {
                        mediaWrapper = playlistManager3.getCurrentMedia();
                    }
                    playlistManager2.resetABRepeatValues(mediaWrapper);
                }
                PlaybackService playbackService7 = this.service;
                if (playbackService7 != null && (playlistManager = playbackService7.getPlaylistManager()) != null) {
                    playlistManager.clearABRepeat();
                }
            } else if (id == R.id.player_overlay_navmenu) {
                showNavMenu();
            } else if (id == R.id.player_overlay_length || id == R.id.player_overlay_time) {
                toggleTimeDisplay();
            } else if (id == R.id.video_renderer) {
                if (getSupportFragmentManager().findFragmentByTag("renderers") == null) {
                    new RenderersDialog().show(getSupportFragmentManager(), "renderers");
                }
            } else if (id == R.id.video_secondary_display) {
                clone = Boolean.valueOf(getDisplayManager().isSecondary());
                recreate();
            } else if (id == R.id.playback_speed_quick_action) {
                PlaybackSpeedDialog newInstance = PlaybackSpeedDialog.Companion.newInstance();
                newInstance.setOnDismissListener(new VideoPlayerActivity$$ExternalSyntheticLambda10(this));
                newInstance.show(getSupportFragmentManager(), SettingsKt.KEY_PLAYBACK_SPEED_PERSIST);
                VideoPlayerOverlayDelegate.hideOverlay$default(getOverlayDelegate(), false, false, 2, (Object) null);
            } else if (id == R.id.sleep_quick_action) {
                SleepTimerDialog newInstance$default = SleepTimerDialog.Companion.newInstance$default(SleepTimerDialog.Companion, false, 1, (Object) null);
                newInstance$default.setOnDismissListener(new VideoPlayerActivity$$ExternalSyntheticLambda11(this));
                newInstance$default.show(getSupportFragmentManager(), RtspHeaders.Values.TIME);
                VideoPlayerOverlayDelegate.hideOverlay$default(getOverlayDelegate(), false, false, 2, (Object) null);
            } else if (id == R.id.audio_delay_quick_action) {
                getDelayDelegate().showAudioDelaySetting();
                VideoPlayerOverlayDelegate.hideOverlay$default(getOverlayDelegate(), false, false, 2, (Object) null);
            } else if (id == R.id.spu_delay_quick_action) {
                getDelayDelegate().showSubsDelaySetting();
                VideoPlayerOverlayDelegate.hideOverlay$default(getOverlayDelegate(), false, false, 2, (Object) null);
            } else if (id == R.id.player_screenshot) {
                VideoPlayerOverlayDelegate.hideOverlay$default(getOverlayDelegate(), false, false, 2, (Object) null);
                takeScreenshot();
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void onClick$lambda$43(VideoPlayerActivity videoPlayerActivity, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(videoPlayerActivity, "this$0");
        videoPlayerActivity.getOverlayDelegate().dimStatusBar(true);
    }

    /* access modifiers changed from: private */
    public static final void onClick$lambda$44(VideoPlayerActivity videoPlayerActivity, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(videoPlayerActivity, "this$0");
        videoPlayerActivity.getOverlayDelegate().dimStatusBar(true);
    }

    public boolean onLongClick(View view) {
        Intrinsics.checkNotNullParameter(view, "v");
        int id = view.getId();
        if (id == R.id.orientation_toggle) {
            getOrientationDelegate().displayOrientation();
            return true;
        } else if (id == R.id.player_overlay_forward) {
            getTouchDelegate().seekDelta$vlc_android_release(Settings.INSTANCE.getVideoLongJumpDelay() * 1000);
            return true;
        } else if (id != R.id.player_overlay_rewind) {
            return false;
        } else {
            getTouchDelegate().seekDelta$vlc_android_release((-Settings.INSTANCE.getVideoLongJumpDelay()) * 1000);
            return true;
        }
    }

    public final void toggleTimeDisplay() {
        sDisplayRemainingTime = !sDisplayRemainingTime;
        VideoPlayerOverlayDelegate.showOverlay$default(getOverlayDelegate(), false, 1, (Object) null);
        SettingsKt.putSingle(getSettings(), KEY_REMAINING_TIME_DISPLAY, Boolean.valueOf(sDisplayRemainingTime));
    }

    public final void toggleLock() {
        if (this.isLocked) {
            getOverlayDelegate().unlockScreen();
        } else {
            getOverlayDelegate().lockScreen();
        }
        getOverlayDelegate().updateRendererVisibility();
    }

    public void onStorageAccessGranted() {
        this.handler.sendEmptyMessage(3);
    }

    public final void hideOptions() {
        PlayerOptionsDelegate playerOptionsDelegate = this.optionsDelegate;
        if (playerOptionsDelegate != null) {
            playerOptionsDelegate.hide();
        }
    }

    private final void showNavMenu() {
        PlaybackService playbackService;
        int i = this.menuIdx;
        if (i >= 0 && (playbackService = this.service) != null) {
            playbackService.setTitleIdx(i);
        }
    }

    public final void doPlayPause() {
        PlaybackService playbackService = this.service;
        if (playbackService != null && playbackService.isPausable()) {
            PlaybackService playbackService2 = this.service;
            if (playbackService2 == null || !playbackService2.isPlaying()) {
                if (Settings.INSTANCE.getVideoHudDelay() != -1) {
                    this.handler.sendEmptyMessageDelayed(1, 800);
                }
                play();
                return;
            }
            getOverlayDelegate().showOverlayTimeout(-1);
            pause();
        }
    }

    public static /* synthetic */ void seek$default(VideoPlayerActivity videoPlayerActivity, long j, boolean z, int i, Object obj) {
        if (obj == null) {
            if ((i & 2) != 0) {
                z = false;
            }
            videoPlayerActivity.seek(j, z);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: seek");
    }

    public final void seek(long j, boolean z) {
        PlaybackService playbackService = this.service;
        if (playbackService != null) {
            seek$vlc_android_release$default(this, j, playbackService.getLength(), z, false, 8, (Object) null);
        }
    }

    public static /* synthetic */ void seek$default(VideoPlayerActivity videoPlayerActivity, long j, boolean z, boolean z2, int i, Object obj) {
        if (obj == null) {
            if ((i & 2) != 0) {
                z = false;
            }
            if ((i & 4) != 0) {
                z2 = false;
            }
            videoPlayerActivity.seek(j, z, z2);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: seek");
    }

    public final void seek(long j, boolean z, boolean z2) {
        PlaybackService playbackService = this.service;
        if (playbackService != null) {
            seek$vlc_android_release(j, playbackService.getLength(), z, z2);
        }
    }

    public static /* synthetic */ void seek$vlc_android_release$default(VideoPlayerActivity videoPlayerActivity, long j, long j2, boolean z, boolean z2, int i, Object obj) {
        if (obj == null) {
            videoPlayerActivity.seek$vlc_android_release(j, j2, (i & 4) != 0 ? false : z, (i & 8) != 0 ? false : z2);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: seek");
    }

    public final void seek$vlc_android_release(long j, long j2, boolean z, boolean z2) {
        PlaybackService playbackService = this.service;
        if (playbackService != null) {
            this.forcedTime = j;
            this.lastTime = playbackService.getTime();
            long j3 = j;
            playbackService.seek(j3, (double) j2, z, z2);
            PlayerController.updateProgress$default(playbackService.getPlaylistManager().getPlayer(), j3, 0, 2, (Object) null);
        }
    }

    public final Boolean resizeVideo() {
        return getResizeDelegate().resizeVideo();
    }

    public final boolean displayResize() {
        return getResizeDelegate().displayResize();
    }

    private final void showTitle() {
        if (!this.isNavMenu) {
            getWindow().getDecorView().setSystemUiVisibility(AndroidDevices.INSTANCE.getHasNavBar() ? 1794 : MediaDiscoverer.Event.Started);
        }
    }

    /* access modifiers changed from: private */
    public final void setESTracks() {
        if (this.lastAudioTrack.compareTo(Constants.GROUP_VIDEOS_NONE) >= 0) {
            PlaybackService playbackService = this.service;
            if (playbackService != null) {
                playbackService.setAudioTrack(this.lastAudioTrack);
            }
            this.lastAudioTrack = "-2";
        }
        if (this.lastSpuTrack.compareTo(" -1") >= 0) {
            PlaybackService playbackService2 = this.service;
            if (playbackService2 != null) {
                playbackService2.setSpuTrack(this.lastSpuTrack);
            }
            this.lastSpuTrack = "-2";
        }
    }

    public final void play() {
        PlaybackService playbackService = this.service;
        if (playbackService != null) {
            playbackService.play();
        }
        View view = this.rootView;
        if (view != null) {
            view.setKeepScreenOn(true);
        }
    }

    private final void pause() {
        PlaybackService playbackService = this.service;
        if (playbackService != null) {
            playbackService.pause();
        }
        View view = this.rootView;
        if (view != null) {
            view.setKeepScreenOn(false);
        }
    }

    public static /* synthetic */ void loadMedia$default(VideoPlayerActivity videoPlayerActivity, boolean z, boolean z2, int i, Object obj) {
        if (obj == null) {
            if ((i & 1) != 0) {
                z = false;
            }
            if ((i & 2) != 0) {
                z2 = false;
            }
            videoPlayerActivity.loadMedia(z, z2);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: loadMedia");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x02ba  */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x02d7  */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x02e2  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x02f5  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0126  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0132  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0146  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x016c  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0173  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void loadMedia(boolean r27, boolean r28) {
        /*
            r26 = this;
            r0 = r26
            r1 = r27
            java.lang.String r2 = "from_start"
            r3 = 0
            if (r1 == 0) goto L_0x0012
            r0.askResume = r3
            android.content.Intent r4 = r26.getIntent()
            r4.putExtra(r2, r1)
        L_0x0012:
            org.videolan.vlc.PlaybackService r1 = r0.service
            if (r1 == 0) goto L_0x0304
            r0.isPlaying = r3
            android.content.SharedPreferences r4 = r26.getSettings()
            java.lang.String r5 = "video_confirm_resume"
            java.lang.String r6 = "0"
            java.lang.String r4 = r4.getString(r5, r6)
            java.lang.String r5 = "1"
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            android.content.Intent r5 = r26.getIntent()
            android.os.Bundle r6 = r5.getExtras()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r7 = r1.getCurrentMediaWrapper()
            r8 = 1
            if (r7 == 0) goto L_0x003b
            r9 = 1
            goto L_0x003c
        L_0x003b:
            r9 = 0
        L_0x003c:
            boolean r10 = r1.isPlaying()
            android.content.Context r11 = r26.getApplicationContext()
            java.lang.Class<android.app.KeyguardManager> r12 = android.app.KeyguardManager.class
            java.lang.Object r11 = androidx.core.content.ContextCompat.getSystemService(r11, r12)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r11)
            android.app.KeyguardManager r11 = (android.app.KeyguardManager) r11
            boolean r11 = r11.inKeyguardRestrictedInputMode()
            if (r11 == 0) goto L_0x0057
            r0.wasPaused = r8
        L_0x0057:
            android.net.Uri r11 = r5.getData()
            java.lang.String r12 = ""
            if (r11 == 0) goto L_0x007a
            org.videolan.vlc.util.FileUtils r13 = org.videolan.vlc.util.FileUtils.INSTANCE     // Catch:{ IllegalStateException -> 0x0069 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r11)     // Catch:{ IllegalStateException -> 0x0069 }
            java.lang.String r13 = r13.getPathFromURI(r11)     // Catch:{ IllegalStateException -> 0x0069 }
            goto L_0x006b
        L_0x0069:
            r13 = r12
        L_0x006b:
            r14 = r13
            java.lang.CharSequence r14 = (java.lang.CharSequence) r14
            int r14 = r14.length()
            if (r14 <= 0) goto L_0x0078
            android.net.Uri r11 = android.net.Uri.parse(r13)
        L_0x0078:
            r0.videoUri = r11
        L_0x007a:
            r14 = 0
            if (r6 == 0) goto L_0x0108
            java.lang.String r13 = "item_location"
            boolean r16 = r5.hasExtra(r13)
            if (r16 == 0) goto L_0x00a9
            int r8 = android.os.Build.VERSION.SDK_INT
            r11 = 33
            if (r8 < r11) goto L_0x0095
            java.lang.Class<android.net.Uri> r8 = android.net.Uri.class
            java.lang.Object r8 = org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0.m((android.os.Bundle) r6, (java.lang.String) r13, (java.lang.Class) r8)
            android.os.Parcelable r8 = (android.os.Parcelable) r8
            goto L_0x00a2
        L_0x0095:
            android.os.Parcelable r8 = r6.getParcelable(r13)
            boolean r11 = r8 instanceof android.net.Uri
            if (r11 != 0) goto L_0x009e
            r8 = 0
        L_0x009e:
            android.net.Uri r8 = (android.net.Uri) r8
            android.os.Parcelable r8 = (android.os.Parcelable) r8
        L_0x00a2:
            android.net.Uri r8 = (android.net.Uri) r8
            r0.videoUri = r8
            r5.removeExtra(r13)
        L_0x00a9:
            boolean r8 = r6.getBoolean(r2, r3)
            r4 = r4 | r8
            r5.putExtra(r2, r3)
            boolean r2 = r0.askResume
            r8 = r4 ^ 1
            r2 = r2 & r8
            r0.askResume = r2
            java.lang.String r2 = "position"
            if (r4 == 0) goto L_0x00bf
            r18 = r14
            goto L_0x00c3
        L_0x00bf:
            long r18 = r6.getLong(r2)
        L_0x00c3:
            if (r4 != 0) goto L_0x00d2
            int r8 = (r18 > r14 ? 1 : (r18 == r14 ? 0 : -1))
            if (r8 != 0) goto L_0x00d2
            int r2 = r6.getInt(r2)
            r11 = r4
            long r3 = (long) r2
            r18 = r3
            goto L_0x00d3
        L_0x00d2:
            r11 = r4
        L_0x00d3:
            java.lang.String r2 = "opened_position"
            r3 = -1
            int r2 = r6.getInt(r2, r3)
            java.lang.String r3 = "subtitles_location"
            java.lang.String r3 = r6.getString(r3)
            r0.subtitlesExtraPath = r3
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            if (r3 == 0) goto L_0x00f6
            int r3 = r3.length()
            if (r3 != 0) goto L_0x00ed
            goto L_0x00f6
        L_0x00ed:
            java.lang.String r3 = r0.subtitlesExtraPath
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            r4 = 1
            r1.addSubtitleTrack((java.lang.String) r3, (boolean) r4)
        L_0x00f6:
            java.lang.String r3 = "title"
            boolean r4 = r5.hasExtra(r3)
            if (r4 == 0) goto L_0x0104
            java.lang.String r3 = r6.getString(r3)
            r4 = r11
            goto L_0x0106
        L_0x0104:
            r4 = r11
            r3 = 0
        L_0x0106:
            r11 = r2
            goto L_0x010d
        L_0x0108:
            r3 = -1
            r18 = r14
            r3 = 0
            r11 = -1
        L_0x010d:
            if (r9 == 0) goto L_0x0121
            if (r7 == 0) goto L_0x0116
            android.net.Uri r2 = r7.getUri()
            goto L_0x0117
        L_0x0116:
            r2 = 0
        L_0x0117:
            android.net.Uri r6 = r0.videoUri
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r6)
            if (r2 == 0) goto L_0x0121
            r2 = 1
            goto L_0x0122
        L_0x0121:
            r2 = 0
        L_0x0122:
            int r6 = (r18 > r14 ? 1 : (r18 == r14 ? 0 : -1))
            if (r6 != 0) goto L_0x0132
            r6 = r9
            long r8 = r0.savedTime
            int r17 = (r8 > r14 ? 1 : (r8 == r14 ? 0 : -1))
            if (r17 <= 0) goto L_0x0133
            if (r2 == 0) goto L_0x0133
            r18 = r8
            goto L_0x0133
        L_0x0132:
            r6 = r9
        L_0x0133:
            boolean r9 = r1.isValidIndex(r11)
            if (r10 == 0) goto L_0x0143
            if (r2 != 0) goto L_0x0141
            int r2 = r1.getCurrentMediaPosition()
            if (r11 != r2) goto L_0x0143
        L_0x0141:
            r2 = 1
            goto L_0x0144
        L_0x0143:
            r2 = 0
        L_0x0144:
            if (r9 == 0) goto L_0x016c
            java.util.List r3 = r1.getMedia()
            java.lang.Object r3 = r3.get(r11)
            org.videolan.medialibrary.interfaces.media.MediaWrapper r3 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r3
            java.lang.String r8 = r3.getTitle()
            org.videolan.vlc.gui.video.VideoPlayerOverlayDelegate r10 = r26.getOverlayDelegate()
            boolean r13 = r1.isSeekable()
            r10.updateSeekable(r13)
            org.videolan.vlc.gui.video.VideoPlayerOverlayDelegate r10 = r26.getOverlayDelegate()
            boolean r13 = r1.isPausable()
            r10.updatePausable(r13)
            r10 = r8
            goto L_0x016e
        L_0x016c:
            r10 = r3
            r3 = 0
        L_0x016e:
            android.net.Uri r13 = r0.videoUri
            r8 = 2
            if (r13 == 0) goto L_0x02ba
            if (r13 != 0) goto L_0x0176
            return
        L_0x0176:
            if (r2 != 0) goto L_0x021c
            if (r9 != 0) goto L_0x01c7
            org.videolan.medialibrary.interfaces.Medialibrary r3 = r26.getMedialibrary()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r3 = r3.getMedia((android.net.Uri) r13)
            if (r3 != 0) goto L_0x01bf
            java.lang.String r14 = r13.getScheme()
            java.lang.String r15 = "file"
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r15)
            if (r14 == 0) goto L_0x01bf
            java.lang.String r14 = r13.getPath()
            if (r14 == 0) goto L_0x01bf
            kotlin.jvm.internal.Intrinsics.checkNotNull(r14)
            java.lang.String r15 = "/sdcard"
            r22 = r3
            r17 = r7
            r3 = 0
            r7 = 0
            boolean r14 = kotlin.text.StringsKt.startsWith$default(r14, r15, r7, r8, r3)
            r3 = 1
            r7 = 2
            if (r14 != r3) goto L_0x01c4
            org.videolan.vlc.util.FileUtils r3 = org.videolan.vlc.util.FileUtils.INSTANCE
            android.net.Uri r3 = r3.convertLocalUri(r13)
            r0.videoUri = r3
            org.videolan.medialibrary.interfaces.Medialibrary r13 = r26.getMedialibrary()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r13 = r13.getMedia((android.net.Uri) r3)
            r25 = r13
            r13 = r3
            r3 = r25
            goto L_0x01ca
        L_0x01bf:
            r22 = r3
            r17 = r7
            r7 = 2
        L_0x01c4:
            r3 = r22
            goto L_0x01ca
        L_0x01c7:
            r17 = r7
            r7 = 2
        L_0x01ca:
            if (r3 == 0) goto L_0x01e1
            int r12 = r3.getAudioTrack()
            java.lang.String r12 = java.lang.String.valueOf(r12)
            r0.lastAudioTrack = r12
            int r12 = r3.getSpuTrack()
            java.lang.String r12 = java.lang.String.valueOf(r12)
            r0.lastSpuTrack = r12
            goto L_0x0219
        L_0x01e1:
            if (r4 != 0) goto L_0x0219
            android.content.SharedPreferences r14 = r26.getSettings()
            java.lang.String r15 = "VideoResumeTime"
            r22 = r9
            r8 = -1
            long r23 = r14.getLong(r15, r8)
            android.content.SharedPreferences r14 = r26.getSettings()
            java.lang.String r7 = "VideoResumeUri"
            java.lang.String r7 = r14.getString(r7, r12)
            r20 = 0
            int r12 = (r23 > r20 ? 1 : (r23 == r20 ? 0 : -1))
            if (r12 <= 0) goto L_0x0221
            java.lang.String r12 = r1.getCurrentMediaLocation()
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r7)
            if (r7 == 0) goto L_0x0221
            android.content.SharedPreferences r7 = r26.getSettings()
            java.lang.Long r12 = java.lang.Long.valueOf(r8)
            org.videolan.tools.SettingsKt.putSingle(r7, r15, r12)
            r18 = r23
            goto L_0x0221
        L_0x0219:
            r22 = r9
            goto L_0x0221
        L_0x021c:
            r17 = r7
            r22 = r9
            r3 = 0
        L_0x0221:
            if (r3 == 0) goto L_0x0224
            goto L_0x0230
        L_0x0224:
            if (r6 == 0) goto L_0x022b
            if (r28 != 0) goto L_0x022b
            r7 = r17
            goto L_0x022f
        L_0x022b:
            org.videolan.medialibrary.interfaces.media.MediaWrapper r7 = org.videolan.medialibrary.MLServiceLocator.getAbstractMediaWrapper((android.net.Uri) r13)
        L_0x022f:
            r3 = r7
        L_0x0230:
            if (r10 == 0) goto L_0x023c
            if (r3 != 0) goto L_0x0235
            goto L_0x023c
        L_0x0235:
            java.lang.String r6 = android.net.Uri.decode(r10)
            r3.setTitle(r6)
        L_0x023c:
            boolean r6 = r0.wasPaused
            if (r6 == 0) goto L_0x0246
            if (r3 == 0) goto L_0x0246
            r6 = 4
            r3.addFlags(r6)
        L_0x0246:
            java.lang.String r6 = "disable_hardware"
            boolean r5 = r5.hasExtra(r6)
            if (r5 == 0) goto L_0x0254
            if (r3 == 0) goto L_0x0254
            r5 = 2
            r3.addFlags(r5)
        L_0x0254:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            r5 = 8
            r3.removeFlags(r5)
            r5 = 1
            r3.addFlags(r5)
            if (r4 == 0) goto L_0x0267
            r5 = 32
            r3.addFlags(r5)
        L_0x0267:
            if (r2 != 0) goto L_0x0286
            if (r4 != 0) goto L_0x0286
            r4 = 0
            int r6 = (r18 > r4 ? 1 : (r18 == r4 ? 0 : -1))
            if (r6 > 0) goto L_0x027d
            long r6 = r3.getTime()
            int r8 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r8 <= 0) goto L_0x027d
            long r18 = r3.getTime()
        L_0x027d:
            r6 = r18
            int r8 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r8 <= 0) goto L_0x0286
            r1.saveStartTime(r6)
        L_0x0286:
            if (r22 == 0) goto L_0x02a3
            if (r2 == 0) goto L_0x029c
            org.videolan.libvlc.util.DisplayManager r2 = r26.getDisplayManager()
            boolean r2 = r2.isPrimary()
            if (r2 == 0) goto L_0x0297
            r1.flush()
        L_0x0297:
            r26.onPlaying()
            r4 = 0
            goto L_0x02a7
        L_0x029c:
            r2 = 2
            r3 = 0
            r4 = 0
            org.videolan.vlc.PlaybackService.playIndex$default(r1, r11, r3, r2, r4)
            goto L_0x02a7
        L_0x02a3:
            r4 = 0
            r1.load((org.videolan.medialibrary.interfaces.media.MediaWrapper) r3, (int) r11)
        L_0x02a7:
            if (r10 != 0) goto L_0x02d3
            java.lang.String r1 = "content"
            java.lang.String r2 = r13.getScheme()
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r2)
            if (r1 != 0) goto L_0x02d3
            java.lang.String r13 = r13.getLastPathSegment()
            goto L_0x02d4
        L_0x02ba:
            r4 = 0
            boolean r2 = r1.hasMedia()
            if (r2 == 0) goto L_0x02cf
            org.videolan.libvlc.util.DisplayManager r2 = r26.getDisplayManager()
            boolean r2 = r2.isPrimary()
            if (r2 != 0) goto L_0x02cf
            r26.onPlaying()
            goto L_0x02d3
        L_0x02cf:
            r2 = 2
            r1.loadLastPlaylist(r2)
        L_0x02d3:
            r13 = r4
        L_0x02d4:
            if (r10 == 0) goto L_0x02d7
            goto L_0x02d8
        L_0x02d7:
            r10 = r13
        L_0x02d8:
            org.videolan.vlc.gui.video.VideoPlayerOverlayDelegate r1 = r26.getOverlayDelegate()
            boolean r1 = r1.isHudRightBindingInitialized()
            if (r1 == 0) goto L_0x02f1
            org.videolan.vlc.gui.video.VideoPlayerOverlayDelegate r1 = r26.getOverlayDelegate()
            org.videolan.vlc.databinding.PlayerHudRightBinding r1 = r1.getHudRightBinding()
            android.widget.TextView r1 = r1.playerOverlayTitle
            java.lang.CharSequence r10 = (java.lang.CharSequence) r10
            r1.setText(r10)
        L_0x02f1:
            boolean r1 = r0.wasPaused
            if (r1 == 0) goto L_0x0301
            r1 = -1
            r0.forcedTime = r1
            org.videolan.vlc.gui.video.VideoPlayerOverlayDelegate r1 = r26.getOverlayDelegate()
            r2 = 1
            r1.showOverlay(r2)
        L_0x0301:
            r26.enableSubs()
        L_0x0304:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.video.VideoPlayerActivity.loadMedia(boolean, boolean):void");
    }

    private final void enableSubs() {
        String lastPathSegment;
        Uri uri = this.videoUri;
        if (uri != null && (lastPathSegment = uri.getLastPathSegment()) != null) {
            Intrinsics.checkNotNull(lastPathSegment);
            VideoPlayerOverlayDelegate overlayDelegate = getOverlayDelegate();
            boolean z = false;
            if (lastPathSegment.length() > 0 && !StringsKt.endsWith$default(lastPathSegment, ".ts", false, 2, (Object) null) && !StringsKt.endsWith$default(lastPathSegment, ".m2ts", false, 2, (Object) null) && !StringsKt.endsWith$default(lastPathSegment, ".TS", false, 2, (Object) null) && !StringsKt.endsWith$default(lastPathSegment, ".M2TS", false, 2, (Object) null)) {
                z = true;
            }
            overlayDelegate.setEnableSubs(z);
        }
    }

    private final void removeDownloadedSubtitlesObserver() {
        LiveData<List<ExternalSub>> liveData = this.downloadedSubtitleLiveData;
        if (liveData != null) {
            liveData.removeObserver(this.downloadedSubtitleObserver);
        }
        this.downloadedSubtitleLiveData = null;
    }

    private final void observeDownloadedSubtitles() {
        PlaybackService playbackService = this.service;
        if (playbackService != null) {
            MediaWrapper currentMediaWrapper = playbackService.getCurrentMediaWrapper();
            Uri uri = currentMediaWrapper != null ? currentMediaWrapper.getUri() : null;
            if (uri != null) {
                Intrinsics.checkNotNull(uri);
                String path = uri.getPath();
                if (path != null) {
                    Intrinsics.checkNotNull(path);
                    String str = this.previousMediaPath;
                    if (str == null || !Intrinsics.areEqual((Object) path, (Object) str)) {
                        this.previousMediaPath = path;
                        removeDownloadedSubtitlesObserver();
                        LiveData<List<ExternalSub>> downloadedSubtitles = ((ExternalSubRepository) ExternalSubRepository.Companion.getInstance(this)).getDownloadedSubtitles(uri);
                        downloadedSubtitles.observe(this, this.downloadedSubtitleObserver);
                        this.downloadedSubtitleLiveData = downloadedSubtitles;
                    }
                }
            }
        }
    }

    public final int getScreenOrientation(PlayerOrientationMode playerOrientationMode) {
        Intrinsics.checkNotNullParameter(playerOrientationMode, RtspHeaders.Values.MODE);
        if (!playerOrientationMode.getLocked()) {
            return AndroidUtil.isJellyBeanMR2OrLater ? 10 : 4;
        }
        return playerOrientationMode.getOrientation();
    }

    private final int getOrientationForLock() {
        Object systemService = ContextCompat.getSystemService(getApplicationContext(), WindowManager.class);
        Intrinsics.checkNotNull(systemService);
        Display defaultDisplay = ((WindowManager) systemService).getDefaultDisplay();
        int screenRotation = getScreenRotation();
        boolean z = defaultDisplay.getWidth() > defaultDisplay.getHeight();
        if (screenRotation == 1 || screenRotation == 3) {
            z = !z;
        }
        if (!z) {
            if (screenRotation != 0) {
                if (screenRotation != 1) {
                    if (screenRotation != 2) {
                        if (screenRotation != 3) {
                            return 0;
                        }
                    }
                }
            }
            return 7;
        } else if (screenRotation != 0) {
            if (screenRotation != 1) {
                if (screenRotation != 2) {
                    if (screenRotation != 3) {
                        return 0;
                    }
                }
            }
            return 7;
        }
        return 6;
    }

    /* access modifiers changed from: private */
    public final void showConfirmResumeDialog(WaitConfirmation waitConfirmation) {
        if (!isFinishing()) {
            if (isInPictureInPictureMode()) {
                Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new VideoPlayerActivity$showConfirmResumeDialog$1(this, waitConfirmation, (Continuation<? super VideoPlayerActivity$showConfirmResumeDialog$1>) null), 3, (Object) null);
                return;
            }
            PlaybackService playbackService = this.service;
            if (playbackService != null) {
                playbackService.pause();
            }
            LayoutInflater layoutInflater = getLayoutInflater();
            Intrinsics.checkNotNullExpressionValue(layoutInflater, "getLayoutInflater(...)");
            View inflate = layoutInflater.inflate(R.layout.dialog_video_resume, (ViewGroup) null);
            CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.video_resume_checkbox);
            AlertDialog create = new AlertDialog.Builder(this).setTitle((CharSequence) waitConfirmation.getTitle()).setView(inflate).setPositiveButton(R.string.resume, (DialogInterface.OnClickListener) new VideoPlayerActivity$$ExternalSyntheticLambda14(checkBox, this, waitConfirmation)).setNegativeButton(R.string.no, (DialogInterface.OnClickListener) new VideoPlayerActivity$$ExternalSyntheticLambda15(checkBox, this, waitConfirmation)).create();
            create.setCancelable(false);
            create.setOnKeyListener(new VideoPlayerActivity$$ExternalSyntheticLambda16(this));
            create.show();
            this.alertDialog = create;
        }
    }

    /* access modifiers changed from: private */
    public static final void showConfirmResumeDialog$lambda$56(CheckBox checkBox, VideoPlayerActivity videoPlayerActivity, WaitConfirmation waitConfirmation, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(videoPlayerActivity, "this$0");
        Intrinsics.checkNotNullParameter(waitConfirmation, "$confirmation");
        if (checkBox.isChecked()) {
            PlaybackService playbackService = videoPlayerActivity.service;
            PlaylistManager playlistManager = playbackService != null ? playbackService.getPlaylistManager() : null;
            if (playlistManager != null) {
                playlistManager.setVideoResumeStatus(ResumeStatus.ALWAYS);
            }
        }
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(videoPlayerActivity), (CoroutineContext) null, (CoroutineStart) null, new VideoPlayerActivity$showConfirmResumeDialog$2$1(videoPlayerActivity, waitConfirmation, (Continuation<? super VideoPlayerActivity$showConfirmResumeDialog$2$1>) null), 3, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final void showConfirmResumeDialog$lambda$57(CheckBox checkBox, VideoPlayerActivity videoPlayerActivity, WaitConfirmation waitConfirmation, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(videoPlayerActivity, "this$0");
        Intrinsics.checkNotNullParameter(waitConfirmation, "$confirmation");
        if (checkBox.isChecked()) {
            PlaybackService playbackService = videoPlayerActivity.service;
            PlaylistManager playlistManager = playbackService != null ? playbackService.getPlaylistManager() : null;
            if (playlistManager != null) {
                playlistManager.setVideoResumeStatus(ResumeStatus.NEVER);
            }
        }
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(videoPlayerActivity), (CoroutineContext) null, (CoroutineStart) null, new VideoPlayerActivity$showConfirmResumeDialog$3$1(videoPlayerActivity, waitConfirmation, (Continuation<? super VideoPlayerActivity$showConfirmResumeDialog$3$1>) null), 3, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final boolean showConfirmResumeDialog$lambda$59$lambda$58(VideoPlayerActivity videoPlayerActivity, DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(videoPlayerActivity, "this$0");
        if (i != 4) {
            return false;
        }
        dialogInterface.dismiss();
        videoPlayerActivity.finish();
        return true;
    }

    public final void toggleOrientationLock() {
        getOrientationMode().setLocked(!getOrientationMode().getLocked());
        getOrientationMode().setOrientation(getOrientationForLock());
        setRequestedOrientation(getScreenOrientation(getOrientationMode()));
        if (getOrientationMode().getLocked()) {
            SettingsKt.putSingle(getSettings(), SettingsKt.LAST_LOCK_ORIENTATION, Integer.valueOf(getRequestedOrientation()));
        }
        getOverlayDelegate().updateOrientationIcon();
    }

    /* access modifiers changed from: private */
    public final void toggleBtDelay(boolean z) {
        PlaybackService playbackService = this.service;
        if (playbackService != null) {
            long j = 0;
            if (z) {
                j = getSettings().getLong(KEY_BLUETOOTH_DELAY, 0);
            }
            playbackService.setAudioDelay(j);
        }
    }

    public final void setOrientation(int i) {
        if (i != -1) {
            getOrientationMode().setLocked(true);
            getOrientationMode().setOrientation(i);
            setRequestedOrientation(i);
            getOverlayDelegate().updateOrientationIcon();
        } else if (getOrientationMode().getLocked()) {
            toggleOrientationLock();
        }
    }

    /* access modifiers changed from: private */
    public final void startLoading() {
        if (!this.isLoading) {
            this.isLoading = true;
            AnimationSet animationSet = new AnimationSet(true);
            RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
            rotateAnimation.setDuration(800);
            rotateAnimation.setInterpolator(new DecelerateInterpolator());
            rotateAnimation.setRepeatCount(-1);
            animationSet.addAnimation(rotateAnimation);
            KotlinExtensionsKt.setVisible(this.loadingImageView);
            ImageView imageView = this.loadingImageView;
            if (imageView != null) {
                imageView.startAnimation(animationSet);
            }
        }
    }

    private final void stopLoading() {
        this.handler.removeMessages(7);
        if (this.isLoading) {
            this.isLoading = false;
            KotlinExtensionsKt.setInvisible(this.loadingImageView);
            ImageView imageView = this.loadingImageView;
            if (imageView != null) {
                imageView.clearAnimation();
            }
        }
    }

    public final void onClickDismissTips(View view) {
        Intrinsics.checkNotNullParameter(view, "v");
        getTipsDelegate().close();
    }

    public final void onClickNextTips(View view) {
        getTipsDelegate().next();
    }

    public final void updateNavStatus() {
        if (this.service != null) {
            this.menuIdx = -1;
            LifecycleOwnerKt.getLifecycleScope(this).launchWhenStarted(new VideoPlayerActivity$updateNavStatus$1(this, (Continuation<? super VideoPlayerActivity$updateNavStatus$1>) null));
        }
    }

    public void onServiceChanged(PlaybackService playbackService) {
        if (playbackService != null) {
            this.service = playbackService;
            if (this.savedMediaList != null && playbackService.getCurrentMediaWrapper() == null) {
                ArrayList<MediaWrapper> arrayList = this.savedMediaList;
                Intrinsics.checkNotNull(arrayList);
                playbackService.append((List<? extends MediaWrapper>) arrayList, this.savedMediaIndex);
                this.savedMediaList = null;
            }
            if (!this.switchingView) {
                this.handler.sendEmptyMessage(3);
            }
            this.switchingView = false;
            this.handler.post(new VideoPlayerActivity$$ExternalSyntheticLambda23(playbackService, this));
            playbackService.m2455addCallbackJP2dKIU(this);
            playbackService.getPlaylistManager().getWaitForConfirmation().observe(this, new VideoPlayerActivityKt$sam$androidx_lifecycle_Observer$0(new VideoPlayerActivity$onServiceChanged$2(this)));
            return;
        }
        PlaybackService playbackService2 = this.service;
        if (playbackService2 != null) {
            if (playbackService2 != null) {
                ChannelResult.m2336boximpl(playbackService2.m2457removeCallbackJP2dKIU(this));
            }
            this.service = null;
            this.handler.sendEmptyMessage(4);
            removeDownloadedSubtitlesObserver();
            this.previousMediaPath = null;
        }
    }

    /* access modifiers changed from: private */
    public static final void onServiceChanged$lambda$60(PlaybackService playbackService, VideoPlayerActivity videoPlayerActivity) {
        int i;
        Intrinsics.checkNotNullParameter(videoPlayerActivity, "this$0");
        if (playbackService.getVolume() > 100 && !videoPlayerActivity.isAudioBoostEnabled) {
            playbackService.setVolume(100);
        }
        if (videoPlayerActivity.volSave > 100 && playbackService.getVolume() != (i = videoPlayerActivity.volSave)) {
            playbackService.setVolume(i);
        }
    }

    public final void onChangedControlSetting(String str) {
        Intrinsics.checkNotNullParameter(str, LeanbackPreferenceDialogFragment.ARG_KEY);
        switch (str.hashCode()) {
            case -2027333566:
                if (!str.equals(SettingsKt.ENABLE_DOUBLE_TAP_PLAY)) {
                    return;
                }
                break;
            case -2027250810:
                if (!str.equals(SettingsKt.ENABLE_DOUBLE_TAP_SEEK)) {
                    return;
                }
                break;
            case -1429929065:
                if (!str.equals(SettingsKt.ENABLE_BRIGHTNESS_GESTURE)) {
                    return;
                }
                break;
            case -389389472:
                if (!str.equals(SettingsKt.ENABLE_VOLUME_GESTURE)) {
                    return;
                }
                break;
            case 739096984:
                if (!str.equals(SettingsKt.ENABLE_SCALE_GESTURE)) {
                    return;
                }
                break;
            case 769157626:
                if (str.equals(SettingsKt.AUDIO_BOOST)) {
                    this.isAudioBoostEnabled = getSettings().getBoolean(SettingsKt.AUDIO_BOOST, true);
                    return;
                }
                return;
            case 878366198:
                if (str.equals(SettingsKt.ENABLE_SEEK_BUTTONS)) {
                    getOverlayDelegate().setSeekButtons(getSettings().getBoolean(SettingsKt.ENABLE_SEEK_BUTTONS, false));
                    return;
                }
                return;
            case 888790236:
                if (str.equals(SettingsKt.SCREENSHOT_MODE)) {
                    getTouchDelegate().setTouchControls(generateTouchFlags());
                    getOverlayDelegate().updateScreenshotButton();
                    return;
                }
                return;
            case 1126655180:
                if (!str.equals(SettingsKt.ENABLE_FASTPLAY)) {
                    return;
                }
                break;
            case 1185180825:
                if (!str.equals(SettingsKt.ENABLE_SWIPE_SEEK)) {
                    return;
                }
                break;
            default:
                return;
        }
        getTouchDelegate().setTouchControls(generateTouchFlags());
    }

    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J0\u00103\u001a\u0002042\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u0002082\b\u00109\u001a\u0004\u0018\u00010\u00042\u0006\u0010:\u001a\u00020(2\u0006\u0010;\u001a\u00020\u0006J8\u00103\u001a\u0002042\u0006\u0010<\u001a\u00020\u00042\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u0002082\b\u00109\u001a\u0004\u0018\u00010\u00042\u0006\u0010:\u001a\u00020(2\u0006\u0010;\u001a\u00020\u0006J&\u00103\u001a\u0002042\u0006\u0010<\u001a\u00020\u00042\u0006\u0010=\u001a\u00020>2\u0006\u0010:\u001a\u00020(2\u0006\u0010;\u001a\u00020\u0006J\u0016\u0010?\u001a\u00020@2\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u000208J\u001e\u0010?\u001a\u00020@2\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u0002082\u0006\u0010:\u001a\u00020(J\u001e\u0010?\u001a\u00020@2\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020\u0004J2\u0010?\u001a\u00020@2\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u0002082\b\u00109\u001a\u0004\u0018\u00010\u00042\u0006\u0010:\u001a\u00020(2\u0006\u0010;\u001a\u00020\u0006H\u0002J\u001e\u0010A\u001a\u00020@2\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u0002082\u0006\u0010;\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0012\u0010'\u001a\u0004\u0018\u00010(X\u000e¢\u0006\u0004\n\u0002\u0010)R\u001a\u0010*\u001a\u00020(X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u0019\u0010/\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000400¢\u0006\b\n\u0000\u001a\u0004\b1\u00102¨\u0006B"}, d2 = {"Lorg/videolan/vlc/gui/video/VideoPlayerActivity$Companion;", "", "()V", "ACTION_RESULT", "", "AUDIO_SERVICE_CONNECTION_FAILED", "", "CHECK_VIDEO_TRACKS", "DEFAULT_FOV", "", "EXTRA_DURATION", "EXTRA_POSITION", "EXTRA_URI", "FADE_OUT", "FADE_OUT_BRIGHTNESS_INFO", "FADE_OUT_INFO", "FADE_OUT_SCREENSHOT", "FADE_OUT_VOLUME_INFO", "FROM_EXTERNAL", "HIDE_INFO", "HIDE_SEEK", "HIDE_SETTINGS", "KEY_BLUETOOTH_DELAY", "KEY_LIST", "KEY_MEDIA_INDEX", "KEY_MEDIA_LIST", "KEY_REMAINING_TIME_DISPLAY", "KEY_TIME", "KEY_URI", "LOADING_ANIMATION", "LOADING_ANIMATION_DELAY", "OVERLAY_INFINITE", "RESET_BACK_LOCK", "RESULT_CONNECTION_FAILED", "RESULT_PLAYBACK_ERROR", "RESULT_VIDEO_TRACK_LOST", "SHOW_INFO", "START_PLAYBACK", "TAG", "clone", "", "Ljava/lang/Boolean;", "sDisplayRemainingTime", "getSDisplayRemainingTime$vlc_android_release", "()Z", "setSDisplayRemainingTime$vlc_android_release", "(Z)V", "videoRemoteFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "getVideoRemoteFlow", "()Lkotlinx/coroutines/flow/MutableStateFlow;", "getIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "title", "fromStart", "openedPosition", "action", "mw", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "start", "", "startOpened", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: VideoPlayerActivity.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MutableStateFlow<String> getVideoRemoteFlow() {
            return VideoPlayerActivity.videoRemoteFlow;
        }

        public final boolean getSDisplayRemainingTime$vlc_android_release() {
            return VideoPlayerActivity.sDisplayRemainingTime;
        }

        public final void setSDisplayRemainingTime$vlc_android_release(boolean z) {
            VideoPlayerActivity.sDisplayRemainingTime = z;
        }

        public final void start(Context context, Uri uri) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(uri, Constants.KEY_URI);
            start(context, uri, (String) null, false, -1);
        }

        public final void start(Context context, Uri uri, boolean z) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(uri, Constants.KEY_URI);
            start(context, uri, (String) null, z, -1);
        }

        public final void start(Context context, Uri uri, String str) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(uri, Constants.KEY_URI);
            Intrinsics.checkNotNullParameter(str, "title");
            start(context, uri, str, false, -1);
        }

        public final void startOpened(Context context, Uri uri, int i) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(uri, Constants.KEY_URI);
            start(context, uri, (String) null, false, i);
        }

        private final void start(Context context, Uri uri, String str, boolean z, int i) {
            context.startActivity(getIntent(context, uri, str, z, i), Util.INSTANCE.getFullScreenBundle());
        }

        public final Intent getIntent(String str, MediaWrapper mediaWrapper, boolean z, int i) {
            Intrinsics.checkNotNullParameter(str, "action");
            Intrinsics.checkNotNullParameter(mediaWrapper, "mw");
            Context appContext = AppContextProvider.INSTANCE.getAppContext();
            Uri uri = mediaWrapper.getUri();
            Intrinsics.checkNotNullExpressionValue(uri, "getUri(...)");
            return getIntent(str, appContext, uri, mediaWrapper.getTitle(), z, i);
        }

        public final Intent getIntent(Context context, Uri uri, String str, boolean z, int i) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(uri, Constants.KEY_URI);
            return getIntent(Constants.PLAY_FROM_VIDEOGRID, context, uri, str, z, i);
        }

        public final Intent getIntent(String str, Context context, Uri uri, String str2, boolean z, int i) {
            Intrinsics.checkNotNullParameter(str, "action");
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(uri, Constants.KEY_URI);
            Intent intent = new Intent(context, VideoPlayerActivity.class);
            intent.setAction(str);
            intent.putExtra(Constants.PLAY_EXTRA_ITEM_LOCATION, uri);
            intent.putExtra("title", str2);
            intent.putExtra(Constants.PLAY_EXTRA_FROM_START, z);
            if (i != -1 || !(context instanceof Activity)) {
                if (i != -1) {
                    intent.putExtra(Constants.PLAY_EXTRA_OPENED_POSITION, i);
                }
                intent.addFlags(268435456);
            }
            return intent;
        }
    }
}
