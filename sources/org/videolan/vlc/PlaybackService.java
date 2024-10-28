package org.videolan.vlc;

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.UiModeManager;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import android.widget.Toast;
import androidx.car.app.connection.CarConnection;
import androidx.car.app.notification.CarPendingIntent;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.ServiceCompat;
import androidx.core.content.ContextCompat;
import androidx.core.os.BundleKt;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ServiceLifecycleDispatcher;
import androidx.media.MediaBrowserServiceCompat;
import androidx.media.session.MediaButtonReceiver;
import androidx.media.utils.MediaConstants;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.channels.ActorKt;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import okhttp3.internal.cache.DiskLruCache;
import org.videolan.libvlc.FactoryManager;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.RendererItem;
import org.videolan.libvlc.interfaces.IComponentFactory;
import org.videolan.libvlc.interfaces.IMedia;
import org.videolan.libvlc.interfaces.IMediaFactory;
import org.videolan.libvlc.interfaces.IVLCVout;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.resources.AndroidDevices;
import org.videolan.resources.AppContextProvider;
import org.videolan.resources.Constants;
import org.videolan.resources.VLCOptions;
import org.videolan.resources.util.ExtensionsKt;
import org.videolan.tools.DrawableCache;
import org.videolan.tools.LocaleUtilsKt;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.tools.Strings;
import org.videolan.vlc.car.VLCCarService;
import org.videolan.vlc.gui.AudioPlayerContainerActivity;
import org.videolan.vlc.gui.dialogs.VideoTracksDialog;
import org.videolan.vlc.gui.dialogs.adapters.VlcTrack;
import org.videolan.vlc.gui.helpers.NotificationHelper;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.gui.video.PopupManager;
import org.videolan.vlc.gui.video.VideoPlayerActivity;
import org.videolan.vlc.media.ABRepeat;
import org.videolan.vlc.media.MediaSessionBrowser;
import org.videolan.vlc.media.MediaUtils;
import org.videolan.vlc.media.PlayerController;
import org.videolan.vlc.media.PlaylistManager;
import org.videolan.vlc.util.AccessControl;
import org.videolan.vlc.util.BrowserutilsKt;
import org.videolan.vlc.util.FlagSet;
import org.videolan.vlc.util.LifecycleAwareScheduler;
import org.videolan.vlc.util.NetworkConnectionManager;
import org.videolan.vlc.util.Permissions;
import org.videolan.vlc.util.PlaybackAction;
import org.videolan.vlc.util.RendererLiveData;
import org.videolan.vlc.util.SchedulerCallback;
import org.videolan.vlc.util.TextUtils;
import org.videolan.vlc.util.Util;
import org.videolan.vlc.util.VLCAudioFocusHelper;
import org.videolan.vlc.widget.MiniPlayerAppWidgetProvider;
import org.videolan.vlc.widget.VLCAppWidgetProvider;
import org.videolan.vlc.widget.VLCAppWidgetProviderBlack;
import org.videolan.vlc.widget.VLCAppWidgetProviderWhite;
import videolan.org.commontools.LiveEvent;

@Metadata(d1 = {"\u0000ö\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0010\u0006\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0002È\u0001\u0018\u0000 û\u00032\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0006ú\u0003û\u0003ü\u0003B\u0005¢\u0006\u0002\u0010\u0005J)\u0010\u0002\u001a\n\u0012\u0005\u0012\u00030\u00020\u00022\u0007\u0010\u0002\u001a\u000202H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b\u0002\u0010\u0002J\u001f\u0010\u0002\u001a\u00030\u00022\b\u0010 \u0002\u001a\u00030¡\u00022\t\b\u0002\u0010¢\u0002\u001a\u00020VH\u0002J\u001f\u0010£\u0002\u001a\u00030\u00022\b\u0010 \u0002\u001a\u00030¡\u00022\t\b\u0002\u0010¤\u0002\u001a\u00020VH\u0002J\u001b\u0010¥\u0002\u001a\u00020V2\u0007\u0010¦\u0002\u001a\u00020\u00162\u0007\u0010§\u0002\u001a\u00020VH\u0007J\u001b\u0010¥\u0002\u001a\u00020V2\u0007\u0010¨\u0002\u001a\u00020\u00072\u0007\u0010§\u0002\u001a\u00020VH\u0007J*\u0010©\u0002\u001a\u00030ç\u00012\r\u0010ª\u0002\u001a\b\u0012\u0004\u0012\u00020H0$2\t\b\u0002\u0010«\u0002\u001a\u00020)H\u0007¢\u0006\u0003\u0010¬\u0002J%\u0010©\u0002\u001a\u00030ç\u00012\u000e\u0010ª\u0002\u001a\t\u0012\u0004\u0012\u00020H0\u00012\t\b\u0002\u0010«\u0002\u001a\u00020)H\u0007J\u0013\u0010©\u0002\u001a\u00030ç\u00012\u0007\u0010\u0001\u001a\u00020HH\u0007J\u0016\u0010­\u0002\u001a\u00030\u00022\n\u0010®\u0002\u001a\u0005\u0018\u00010¯\u0002H\u0014J\n\u0010°\u0002\u001a\u00030\u0002H\u0002J0\u0010±\u0002\u001a\u00030ç\u00012\u000e\u0010ª\u0002\u001a\t\u0012\u0004\u0012\u00020H0\u00012\t\b\u0002\u0010²\u0002\u001a\u00020)2\t\b\u0002\u0010³\u0002\u001a\u00020)H\u0002J\t\u0010´\u0002\u001a\u00020VH\u0007J\t\u0010µ\u0002\u001a\u00020VH\u0002J\u0013\u0010¶\u0002\u001a\u00030\u00022\u0007\u0010·\u0002\u001a\u00020VH\u0002J\u0012\u0010¸\u0002\u001a\u00020V2\u0007\u0010¹\u0002\u001a\u00020)H\u0002J\n\u0010º\u0002\u001a\u00030\u0002H\u0007J\u0012\u0010U\u001a\u00030\u00022\u0007\u0010»\u0002\u001a\u00020VH\u0007J\u0013\u0010¼\u0002\u001a\u00030\u00022\t\b\u0001\u0010½\u0002\u001a\u00020)J.\u0010¾\u0002\u001a\u00030\u00022\t\b\u0001\u0010½\u0002\u001a\u00020)2\u0013\u0010¿\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070$\"\u00020\u0007¢\u0006\u0003\u0010À\u0002J\u0013\u0010Á\u0002\u001a\u00030\u00022\t\b\u0002\u0010Â\u0002\u001a\u00020VJ\n\u0010Ã\u0002\u001a\u00030\u0002H\u0007J\u0015\u0010Ä\u0002\u001a\u00030\u00022\t\b\u0002\u0010Å\u0002\u001a\u00020VH\u0003J\n\u0010Æ\u0002\u001a\u00030¯\u0002H\u0016J#\u0010Ç\u0002\u001a\r\u0012\u0007\b\u0001\u0012\u00030È\u0002\u0018\u00010$2\u0007\u0010ø\u0001\u001a\u00020)H\u0007¢\u0006\u0003\u0010É\u0002J\t\u0010Ê\u0002\u001a\u0004\u0018\u00010\u0007J\u0007\u0010Ë\u0002\u001a\u00020\u0018J\u0010\u0010Ë\u0002\u001a\u00020)2\u0007\u0010Ì\u0002\u001a\u00020\u0018J\t\u0010Í\u0002\u001a\u00020VH\u0007J\n\u0010Î\u0002\u001a\u00020VH\u0002J\t\u0010Ï\u0002\u001a\u00020VH\u0007J\t\u0010Ð\u0002\u001a\u00020VH\u0007J\t\u0010Ñ\u0002\u001a\u00020VH\u0007J\u0012\u0010Ò\u0002\u001a\u00020V2\u0007\u0010Ó\u0002\u001a\u00020VH\u0002J\u0013\u0010Ô\u0002\u001a\u00030\u00022\u0007\u0010Ó\u0002\u001a\u00020VH\u0002J\n\u0010Õ\u0002\u001a\u00030\u0002H\u0007J\n\u0010Ö\u0002\u001a\u00030\u0002H\u0002J\u001c\u0010×\u0002\u001a\u00030\u00022\u0007\u0010½\u0001\u001a\u00020)2\u0007\u0010Ø\u0002\u001a\u00020HH\u0007J\u001f\u0010Ù\u0002\u001a\u00030\u00022\r\u0010ª\u0002\u001a\b\u0012\u0004\u0012\u00020H0$H\u0007¢\u0006\u0003\u0010Ú\u0002J\u001a\u0010Ù\u0002\u001a\u00030\u00022\u000e\u0010ª\u0002\u001a\t\u0012\u0004\u0012\u00020H0\u0001H\u0003J\u0013\u0010Ù\u0002\u001a\u00030\u00022\u0007\u0010\u0001\u001a\u00020HH\u0007J\u0007\u0010Û\u0002\u001a\u00020VJ\u0010\u0010Ü\u0002\u001a\u00020V2\u0007\u0010Ý\u0002\u001a\u00020)J*\u0010Þ\u0002\u001a\u00030\u00022\u000f\u0010ª\u0002\u001a\n\u0012\u0004\u0012\u00020H\u0018\u00010$2\u0007\u0010½\u0001\u001a\u00020)H\u0007¢\u0006\u0003\u0010ß\u0002J#\u0010Þ\u0002\u001a\u00030ç\u00012\u000e\u0010ª\u0002\u001a\t\u0012\u0004\u0012\u00020H0\u00012\u0007\u0010½\u0001\u001a\u00020)H\u0007J\u001e\u0010Þ\u0002\u001a\u00030ç\u00012\u0007\u0010\u0001\u001a\u00020H2\t\b\u0002\u0010½\u0001\u001a\u00020)H\u0007J\n\u0010à\u0002\u001a\u00030\u0002H\u0002J\u0011\u0010á\u0002\u001a\u00030\u00022\u0007\u0010â\u0002\u001a\u00020)J\u0013\u0010ã\u0002\u001a\u00030\u00022\u0007\u0010ä\u0002\u001a\u00020\u0007H\u0007J#\u0010å\u0002\u001a\u00030\u00022\u000e\u0010æ\u0002\u001a\t\u0012\u0004\u0012\u00020\u00070\u00012\u0007\u0010½\u0001\u001a\u00020)H\u0003J\u0015\u0010ç\u0002\u001a\u00030\u00022\t\u0010¦\u0002\u001a\u0004\u0018\u00010\u0016H\u0007J,\u0010è\u0002\u001a\u00030\u00022\r\u0010é\u0002\u001a\b\u0012\u0004\u0012\u00020_0^2\b\u0010 \u0002\u001a\u00030¡\u00022\u0007\u0010Ê\u0001\u001a\u00020)H\u0002J\u001c\u0010ê\u0002\u001a\u00030\u00022\u0007\u0010ë\u0002\u001a\u00020)2\u0007\u0010ì\u0002\u001a\u00020)H\u0007J\u0013\u0010í\u0002\u001a\u00030\u00022\u0007\u0010î\u0002\u001a\u00020)H\u0007J\u0015\u0010ï\u0002\u001a\u00030\u00022\t\b\u0002\u0010ð\u0002\u001a\u00020VH\u0007J\u0012\u0010ñ\u0002\u001a\u0004\u0018\u00010\u0007H@¢\u0006\u0003\u0010ò\u0002J\b\u0010ó\u0002\u001a\u00030\u0002J\u0016\u0010ô\u0002\u001a\u0005\u0018\u00010õ\u00022\b\u0010ö\u0002\u001a\u00030÷\u0002H\u0016J\n\u0010ø\u0002\u001a\u00030\u0002H\u0017J\n\u0010ù\u0002\u001a\u00030\u0002H\u0016J*\u0010ú\u0002\u001a\u0005\u0018\u00010û\u00022\u0007\u0010ü\u0002\u001a\u00020\u00072\u0007\u0010ý\u0002\u001a\u00020)2\n\u0010þ\u0002\u001a\u0005\u0018\u00010ÿ\u0002H\u0016J+\u0010\u0003\u001a\u00030\u00022\u0007\u0010\u0003\u001a\u00020\u00072\u0016\u0010\u0003\u001a\u0011\u0012\f\u0012\n\u0012\u0005\u0012\u00030\u00030\u00010\u0003H\u0016J(\u0010\u0003\u001a\n\u0012\u0005\u0012\u00030\u00020\u00022\b\u0010\u0003\u001a\u00030\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b\u0003\u0010\u0003J\b\u0010\u0003\u001a\u00030\u0002J\u0012\u0010\u0003\u001a\u00030\u00022\b\u0010\u0003\u001a\u00030\u0003J\b\u0010\u0003\u001a\u00030\u0002J\u0011\u0010\u0003\u001a\u00030\u00022\u0007\u0010\u0003\u001a\u00020VJ\b\u0010\u0003\u001a\u00030\u0002J7\u0010\u0003\u001a\u00030\u00022\u0007\u0010\u0003\u001a\u00020\u00072\n\u0010\u0003\u001a\u0005\u0018\u00010ÿ\u00022\u0016\u0010\u0003\u001a\u0011\u0012\f\u0012\n\u0012\u0005\u0012\u00030\u00030\u00010\u0003H\u0016J'\u0010\u0003\u001a\u00020)2\n\u0010ö\u0002\u001a\u0005\u0018\u00010÷\u00022\u0007\u0010\u0003\u001a\u00020)2\u0007\u0010\u0003\u001a\u00020)H\u0016J\u0014\u0010\u0003\u001a\u00030\u00022\b\u0010\u0003\u001a\u00030÷\u0002H\u0016J\u001d\u0010\u0003\u001a\u00030\u00022\u0007\u0010\u0003\u001a\u00020\u00072\b\u0010\u0003\u001a\u00030ÿ\u0002H\u0016J\n\u0010\u0003\u001a\u00030\u0002H\u0007J\n\u0010\u0003\u001a\u00030\u0002H\u0007J\u001e\u0010\u0003\u001a\u00030\u00022\u0007\u0010«\u0002\u001a\u00020)2\t\b\u0002\u0010\u0003\u001a\u00020)H\u0007J\u0011\u0010\u0003\u001a\u00030\u00022\u0007\u0010«\u0002\u001a\u00020)J\u0012\u0010 \u0003\u001a\u0004\u0018\u00010\u0007H@¢\u0006\u0003\u0010ò\u0002J\u0013\u0010¡\u0003\u001a\u00030\u00022\u0007\u0010ð\u0002\u001a\u00020VH\u0007J\u001d\u0010¢\u0003\u001a\u00030\u00022\u000b\b\u0002\u0010½\u0001\u001a\u0004\u0018\u00010\u0018H\u0002¢\u0006\u0003\u0010£\u0003J\u0013\u0010Ó\u0002\u001a\u00030\u00022\u0007\u0010½\u0001\u001a\u00020)H\u0007J)\u0010¤\u0003\u001a\n\u0012\u0005\u0012\u00030\u00020\u00022\u0007\u0010\u0002\u001a\u000202H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b¥\u0003\u0010\u0002J\u0013\u0010¦\u0003\u001a\u00030\u00022\u0007\u0010§\u0003\u001a\u00020\u0007H\u0007J\n\u0010¨\u0003\u001a\u00030\u0002H\u0007J\n\u0010©\u0003\u001a\u00030\u0002H\u0007J\b\u0010ª\u0003\u001a\u00030\u0002J\n\u0010«\u0003\u001a\u00030\u0002H\u0002J\b\u0010¬\u0003\u001a\u00030ç\u0001J\u0013\u0010­\u0003\u001a\u00030\u00022\u0007\u0010®\u0003\u001a\u00020\u0018H\u0007J5\u0010¯\u0003\u001a\u00030\u00022\u0007\u0010®\u0003\u001a\u00020\u00182\n\b\u0002\u0010\u0001\u001a\u00030°\u00032\t\b\u0002\u0010±\u0003\u001a\u00020V2\t\b\u0002\u0010²\u0003\u001a\u00020VH\u0007J\n\u0010³\u0003\u001a\u00030\u0002H\u0002J\n\u0010´\u0003\u001a\u00030\u0002H\u0002J\u0014\u0010µ\u0003\u001a\u00030\u00022\b\u0010ö\u0002\u001a\u00030÷\u0002H\u0002J\u0013\u0010¶\u0003\u001a\u00030\u00022\u0007\u0010·\u0003\u001a\u00020\u0018H\u0007J\u0012\u0010¸\u0003\u001a\u00020V2\u0007\u0010¹\u0003\u001a\u00020VH\u0007J\u0012\u0010º\u0003\u001a\u00020V2\u0007\u0010«\u0002\u001a\u00020\u0007H\u0007J\b\u0010»\u0003\u001a\u00030\u0002J\u0015\u0010¼\u0003\u001a\u00020V2\n\u0010½\u0003\u001a\u0005\u0018\u00010¾\u0003H\u0007J\b\u0010¿\u0003\u001a\u00030\u0002J\u0014\u0010À\u0003\u001a\u00030\u00022\b\u0010Á\u0003\u001a\u00030Ä\u0001H\u0003J\u001d\u0010Â\u0003\u001a\u00030\u00022\b\u0010Ã\u0001\u001a\u00030Ä\u00012\u0007\u0010Ã\u0003\u001a\u00020VH\u0007J\u0016\u0010Ä\u0003\u001a\u00030\u00022\n\u0010Å\u0003\u001a\u0005\u0018\u00010Æ\u0003H\u0007J\u0014\u0010Ç\u0003\u001a\u00030\u00022\n\u0010®\u0003\u001a\u0005\u0018\u00010È\u0003J\u0013\u0010É\u0003\u001a\u00030\u00022\u0007\u0010·\u0003\u001a\u00020\u0018H\u0007J\u0013\u0010Ê\u0003\u001a\u00030\u00022\u0007\u0010«\u0002\u001a\u00020\u0007H\u0007J\u001c\u0010Ë\u0003\u001a\u00030\u00022\u0007\u0010®\u0003\u001a\u00020\u00182\t\b\u0002\u0010²\u0003\u001a\u00020VJ\u0015\u0010Ì\u0003\u001a\u00030\u00022\t\u0010Í\u0003\u001a\u0004\u0018\u00010\u0007H\u0007J\u0014\u0010Î\u0003\u001a\u00030\u00022\b\u0010Ï\u0003\u001a\u00030Ä\u0001H\u0007J\u0012\u0010Ð\u0003\u001a\u00020V2\u0007\u0010«\u0002\u001a\u00020\u0007H\u0007J\u0011\u0010Ñ\u0003\u001a\u00030\u00022\u0007\u0010¹\u0003\u001a\u00020VJ\u0012\u0010Ò\u0003\u001a\u00020)2\u0007\u0010\u0002\u001a\u00020)H\u0007J\n\u0010Ó\u0003\u001a\u00030\u0002H\u0002J\u0007\u0010Ô\u0003\u001a\u00020VJ\n\u0010Õ\u0003\u001a\u00030\u0002H\u0003J\n\u0010Ö\u0003\u001a\u00030\u0002H\u0003J%\u0010×\u0003\u001a\u00030\u00022\u0007\u0010Ø\u0003\u001a\u00020\u00072\u0007\u0010Ù\u0003\u001a\u00020)2\t\b\u0002\u0010Ú\u0003\u001a\u00020VJ\u001e\u0010Û\u0003\u001a\u00030\u00022\u0007\u0010«\u0002\u001a\u00020)2\t\b\u0002\u0010Ü\u0003\u001a\u00020VH\u0007J\n\u0010Ý\u0003\u001a\u00030\u0002H\u0007J\n\u0010Þ\u0003\u001a\u00030\u0002H\u0002J \u0010ß\u0003\u001a\u00030\u00022\t\b\u0002\u0010\u0003\u001a\u00020V2\t\b\u0002\u0010à\u0003\u001a\u00020VH\u0007J\n\u0010á\u0003\u001a\u00030\u0002H\u0002J\u0013\u0010â\u0003\u001a\u00030\u00022\u0007\u0010«\u0002\u001a\u00020)H\u0007J\u0007\u0010ã\u0003\u001a\u00020VJ\u0012\u0010ä\u0003\u001a\u0004\u0018\u00010\u0007H@¢\u0006\u0003\u0010ò\u0002J\u0014\u0010å\u0003\u001a\u00030\u00022\b\u0010æ\u0003\u001a\u00030ç\u0003H\u0007J\n\u0010è\u0003\u001a\u00030\u0002H\u0002J\n\u0010é\u0003\u001a\u00030ç\u0001H\u0002J\u0015\u0010ê\u0003\u001a\u00030ç\u00012\t\b\u0002\u0010ë\u0003\u001a\u00020VH\u0002J\n\u0010ì\u0003\u001a\u00030\u0002H\u0002J\u0011\u0010í\u0003\u001a\u00030\u0002H@¢\u0006\u0003\u0010ò\u0002J:\u0010î\u0003\u001a\u00020V2\b\u0010ï\u0003\u001a\u00030Ä\u00012\b\u0010ð\u0003\u001a\u00030Ä\u00012\b\u0010ñ\u0003\u001a\u00030Ä\u00012\b\u0010ò\u0003\u001a\u00030Ä\u00012\u0007\u0010ó\u0003\u001a\u00020VH\u0007J\n\u0010ô\u0003\u001a\u00030\u0002H\u0002J\u0014\u0010õ\u0003\u001a\u00030\u00022\b\u0010Á\u0003\u001a\u00030Ä\u0001H\u0002J\b\u0010ö\u0003\u001a\u00030\u0002J\u0016\u0010÷\u0003\u001a\u00020\u0007*\u00030ø\u00032\b\u0010ù\u0003\u001a\u00030¯\u0002R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u00078G¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0013\u0010\n\u001a\u0004\u0018\u00010\u00078G¢\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u0013\u0010\f\u001a\u0004\u0018\u00010\u00078G¢\u0006\u0006\u001a\u0004\b\r\u0010\tR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u00078G¢\u0006\u0006\u001a\u0004\b\u000f\u0010\tR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u00078G¢\u0006\u0006\u001a\u0004\b\u0011\u0010\tR\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u00078G¢\u0006\u0006\u001a\u0004\b\u0013\u0010\tR\u001a\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00160\u0015X.¢\u0006\u0002\n\u0000R\u0011\u0010\u0017\u001a\u00020\u00188G¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u001b\u0010\u001b\u001a\u00020\u001c8BX\u0002¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010!\u001a\u00020\u00078G¢\u0006\u0006\u001a\u0004\b\"\u0010\tR\u001b\u0010#\u001a\f\u0012\u0006\b\u0001\u0012\u00020%\u0018\u00010$8G¢\u0006\u0006\u001a\u0004\b&\u0010'R\u0011\u0010(\u001a\u00020)8G¢\u0006\u0006\u001a\u0004\b*\u0010+R\u0012\u0010,\u001a\u00060-R\u00020\u0000X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020/X.¢\u0006\u0002\n\u0000R\u0014\u00100\u001a\b\u0012\u0004\u0012\u00020201X\u0004¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u000204X.¢\u0006\u0002\n\u0000R\u0014\u00105\u001a\b\u0012\u0004\u0012\u00020706X.¢\u0006\u0002\n\u0000R$\u00109\u001a\u00020)2\u0006\u00108\u001a\u00020)8G@GX\u000e¢\u0006\f\u001a\u0004\b:\u0010+\"\u0004\b;\u0010<R\u0014\u0010=\u001a\u00020>X\u0004¢\u0006\b\n\u0000\u001a\u0004\b?\u0010@R\u0013\u0010A\u001a\u0004\u0018\u00010\u00078G¢\u0006\u0006\u001a\u0004\bB\u0010\tR\u0013\u0010C\u001a\u0004\u0018\u00010\u00078G¢\u0006\u0006\u001a\u0004\bD\u0010\tR\u0011\u0010E\u001a\u00020)8G¢\u0006\u0006\u001a\u0004\bF\u0010+R\u0013\u0010G\u001a\u0004\u0018\u00010H8G¢\u0006\u0006\u001a\u0004\bI\u0010JR\u001c\u0010K\u001a\u0004\u0018\u00010LX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010N\"\u0004\bO\u0010PR\u0013\u0010Q\u001a\u0004\u0018\u00010%8G¢\u0006\u0006\u001a\u0004\bR\u0010SR\u0010\u0010T\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010U\u001a\u00020VX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bW\u0010X\"\u0004\bY\u0010ZR\u000e\u0010[\u001a\u00020\\X\u0004¢\u0006\u0002\n\u0000R \u0010]\u001a\b\u0012\u0004\u0012\u00020_0^X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b`\u0010a\"\u0004\bb\u0010cR\u001a\u0010d\u001a\u00020VX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\be\u0010X\"\u0004\bf\u0010ZR\u000e\u0010g\u001a\u00020VX\u000e¢\u0006\u0002\n\u0000R \u0010h\u001a\b\u0012\u0004\u0012\u00020V0iX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bh\u0010j\"\u0004\bk\u0010lR\u0011\u0010m\u001a\u00020V8G¢\u0006\u0006\u001a\u0004\bm\u0010XR\u0011\u0010n\u001a\u00020V8G¢\u0006\u0006\u001a\u0004\bn\u0010XR\u0011\u0010o\u001a\u00020V8G¢\u0006\u0006\u001a\u0004\bo\u0010XR\u0011\u0010p\u001a\u00020V8G¢\u0006\u0006\u001a\u0004\bp\u0010XR\u0011\u0010q\u001a\u00020V8G¢\u0006\u0006\u001a\u0004\bq\u0010XR\u0011\u0010r\u001a\u00020V8G¢\u0006\u0006\u001a\u0004\br\u0010XR\u0011\u0010s\u001a\u00020V8G¢\u0006\u0006\u001a\u0004\bs\u0010XR\u0011\u0010t\u001a\u00020V8G¢\u0006\u0006\u001a\u0004\bt\u0010XR\u0011\u0010u\u001a\u00020V8G¢\u0006\u0006\u001a\u0004\bu\u0010XR\u000e\u0010v\u001a\u00020wX.¢\u0006\u0002\n\u0000R\u000e\u0010x\u001a\u00020)X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010y\u001a\u00020)X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010z\u001a\u00020\u0018X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b{\u0010\u001a\"\u0004\b|\u0010}R\u000e\u0010~\u001a\u00020\u0018X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u0001\u001a\u0005\u0018\u00010\u00018F¢\u0006\b\u001a\u0006\b\u0001\u0010\u0001R\u000f\u0010\u0001\u001a\u00020\u0018X\u000e¢\u0006\u0002\n\u0000R\u0013\u0010\u0001\u001a\u00020\u00188G¢\u0006\u0007\u001a\u0005\b\u0001\u0010\u001aR\u0018\u0010\u0001\u001a\u00030\u00018VX\u0004¢\u0006\b\u001a\u0006\b\u0001\u0010\u0001R\u001b\u0010\u0001\u001a\t\u0012\u0004\u0012\u00020H0\u00018G¢\u0006\b\u001a\u0006\b\u0001\u0010\u0001R \u0010\u0001\u001a\u00030\u0001X.¢\u0006\u0012\n\u0000\u001a\u0006\b\u0001\u0010\u0001\"\u0006\b\u0001\u0010\u0001R\u000f\u0010\u0001\u001a\u00020VX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0001\u001a\u00030\u0001X\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0001\u001a\u00020)8F¢\u0006\u0007\u001a\u0005\b\u0001\u0010+R\u001b\u0010\u0001\u001a\t\u0012\u0004\u0012\u00020\u00070\u00018G¢\u0006\b\u001a\u0006\b\u0001\u0010\u0001R\u0010\u0010\u0001\u001a\u00030\u0001X\u0004¢\u0006\u0002\n\u0000R \u0010\u0001\u001a\u00030\u0001X.¢\u0006\u0012\n\u0000\u001a\u0006\b \u0001\u0010¡\u0001\"\u0006\b¢\u0001\u0010£\u0001R \u0010¤\u0001\u001a\u00030¥\u0001X.¢\u0006\u0012\n\u0000\u001a\u0006\b¦\u0001\u0010§\u0001\"\u0006\b¨\u0001\u0010©\u0001R\u0015\u0010ª\u0001\u001a\u00030«\u00018F¢\u0006\b\u001a\u0006\b¬\u0001\u0010­\u0001R\u001d\u0010®\u0001\u001a\u00020)X\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¯\u0001\u0010+\"\u0005\b°\u0001\u0010<R\u0015\u0010±\u0001\u001a\u0004\u0018\u00010\u00078G¢\u0006\u0007\u001a\u0005\b²\u0001\u0010\tR\u0010\u0010³\u0001\u001a\u00030´\u0001X.¢\u0006\u0002\n\u0000R\u000f\u0010µ\u0001\u001a\u00020VX\u000e¢\u0006\u0002\n\u0000R$\u0010¸\u0001\u001a\u00030·\u00012\b\u0010¶\u0001\u001a\u00030·\u0001@BX.¢\u0006\n\n\u0000\u001a\u0006\b¹\u0001\u0010º\u0001R\u0012\u0010»\u0001\u001a\u0005\u0018\u00010¼\u0001X\u000e¢\u0006\u0002\n\u0000R\u000f\u0010½\u0001\u001a\u00020\u0018X\u000e¢\u0006\u0002\n\u0000R\u0015\u0010¾\u0001\u001a\u0004\u0018\u00010\u00078G¢\u0006\u0007\u001a\u0005\b¿\u0001\u0010\tR\u000f\u0010À\u0001\u001a\u00020VX\u000e¢\u0006\u0002\n\u0000R\u0013\u0010Á\u0001\u001a\u00020\u00188G¢\u0006\u0007\u001a\u0005\bÂ\u0001\u0010\u001aR\u0015\u0010Ã\u0001\u001a\u00030Ä\u00018G¢\u0006\b\u001a\u0006\bÅ\u0001\u0010Æ\u0001R\u0013\u0010Ç\u0001\u001a\u00030È\u0001X\u0004¢\u0006\u0005\n\u0003\u0010É\u0001R(\u0010Ê\u0001\u001a\u00020)2\u0007\u0010Ê\u0001\u001a\u00020)8G@GX\u000e¢\u0006\u000e\u001a\u0005\bË\u0001\u0010+\"\u0005\bÌ\u0001\u0010<R\u001d\u0010Í\u0001\u001a\u00020VX\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÎ\u0001\u0010X\"\u0005\bÏ\u0001\u0010ZR \u0010Ð\u0001\u001a\u00030Ñ\u0001X.¢\u0006\u0012\n\u0000\u001a\u0006\bÒ\u0001\u0010Ó\u0001\"\u0006\bÔ\u0001\u0010Õ\u0001R\u0015\u0010Ö\u0001\u001a\u00030×\u00018F¢\u0006\b\u001a\u0006\bØ\u0001\u0010Ù\u0001R \u0010Ú\u0001\u001a\u00030Û\u0001X.¢\u0006\u0012\n\u0000\u001a\u0006\bÜ\u0001\u0010Ý\u0001\"\u0006\bÞ\u0001\u0010ß\u0001R(\u0010à\u0001\u001a\u00020)2\u0007\u0010à\u0001\u001a\u00020)8G@GX\u000e¢\u0006\u000e\u001a\u0005\bá\u0001\u0010+\"\u0005\bâ\u0001\u0010<R\u001d\u0010ã\u0001\u001a\u00020\u0018X\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bä\u0001\u0010\u001a\"\u0005\bå\u0001\u0010}R\"\u0010æ\u0001\u001a\u0005\u0018\u00010ç\u0001X\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bè\u0001\u0010é\u0001\"\u0006\bê\u0001\u0010ë\u0001R\u0015\u0010ì\u0001\u001a\u00030Ä\u00018G¢\u0006\b\u001a\u0006\bí\u0001\u0010Æ\u0001R\u0013\u0010î\u0001\u001a\u00020\u00188G¢\u0006\u0007\u001a\u0005\bï\u0001\u0010\u001aR\u0013\u0010ð\u0001\u001a\u00020\u00078G¢\u0006\u0007\u001a\u0005\bñ\u0001\u0010\tR\u001d\u0010ò\u0001\u001a\f\u0012\u0006\b\u0001\u0012\u00020%\u0018\u00010$8G¢\u0006\u0007\u001a\u0005\bó\u0001\u0010'R\u0013\u0010ô\u0001\u001a\u00020)8G¢\u0006\u0007\u001a\u0005\bõ\u0001\u0010+R\u0016\u0010ö\u0001\u001a\t\u0012\u0004\u0012\u00020\u00070÷\u0001X\u0004¢\u0006\u0002\n\u0000R\u0015\u0010ø\u0001\u001a\u0004\u0018\u00010\u00078G¢\u0006\u0007\u001a\u0005\bù\u0001\u0010\tR(\u0010ú\u0001\u001a\u00020)2\u0007\u0010ø\u0001\u001a\u00020)8G@GX\u000e¢\u0006\u000e\u001a\u0005\bû\u0001\u0010+\"\u0005\bü\u0001\u0010<R\u0015\u0010ý\u0001\u001a\u0004\u0018\u00010\u00078G¢\u0006\u0007\u001a\u0005\bþ\u0001\u0010\tR\u0015\u0010ÿ\u0001\u001a\u0004\u0018\u00010\u00078G¢\u0006\u0007\u001a\u0005\b\u0002\u0010\tR\u001f\u0010\u0002\u001a\r\u0012\u0007\b\u0001\u0012\u00030\u0002\u0018\u00010$8G¢\u0006\b\u001a\u0006\b\u0002\u0010\u0002R\u0013\u0010\u0002\u001a\u00020\u00078G¢\u0006\u0007\u001a\u0005\b\u0002\u0010\tR\u001d\u0010\u0002\u001a\f\u0012\u0006\b\u0001\u0012\u00020%\u0018\u00010$8G¢\u0006\u0007\u001a\u0005\b\u0002\u0010'R\u0013\u0010\u0002\u001a\u00020)8G¢\u0006\u0007\u001a\u0005\b\u0002\u0010+R\u0013\u0010\u0002\u001a\u00020)8G¢\u0006\u0007\u001a\u0005\b\u0002\u0010+R\u0017\u0010\u0002\u001a\u0005\u0018\u00010\u00028F¢\u0006\b\u001a\u0006\b\u0002\u0010\u0002R\u001d\u0010\u0002\u001a\u00020VX\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0002\u0010X\"\u0005\b\u0002\u0010ZR\u0015\u0010\u0002\u001a\b0\u0002R\u00030\u0002X.¢\u0006\u0002\n\u0000R\u000f\u0010\u0002\u001a\u00020)X\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0002\u001a\u00020\u0018X\u000e¢\u0006\u0002\n\u0000\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006ý\u0003"}, d2 = {"Lorg/videolan/vlc/PlaybackService;", "Landroidx/media/MediaBrowserServiceCompat;", "Landroidx/lifecycle/LifecycleOwner;", "Lkotlinx/coroutines/CoroutineScope;", "Lorg/videolan/vlc/util/SchedulerCallback;", "()V", "album", "", "getAlbum", "()Ljava/lang/String;", "albumNext", "getAlbumNext", "albumPrev", "getAlbumPrev", "artist", "getArtist", "artistNext", "getArtistNext", "artistPrev", "getArtistPrev", "artworkMap", "", "Landroid/net/Uri;", "audioDelay", "", "getAudioDelay", "()J", "audioFocusHelper", "Lorg/videolan/vlc/util/VLCAudioFocusHelper;", "getAudioFocusHelper", "()Lorg/videolan/vlc/util/VLCAudioFocusHelper;", "audioFocusHelper$delegate", "Lkotlin/Lazy;", "audioTrack", "getAudioTrack", "audioTracks", "", "Lorg/videolan/vlc/gui/dialogs/adapters/VlcTrack;", "getAudioTracks", "()[Lorg/videolan/vlc/gui/dialogs/adapters/VlcTrack;", "audioTracksCount", "", "getAudioTracksCount", "()I", "binder", "Lorg/videolan/vlc/PlaybackService$LocalBinder;", "browserCallback", "Lorg/videolan/vlc/MediaBrowserCallback;", "callbacks", "", "Lorg/videolan/vlc/PlaybackService$Callback;", "carConnection", "Landroidx/car/app/connection/CarConnection;", "cbActor", "Lkotlinx/coroutines/channels/SendChannel;", "Lorg/videolan/vlc/CbAction;", "chapter", "chapterIdx", "getChapterIdx", "setChapterIdx", "(I)V", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "coverArt", "getCoverArt", "currentMediaLocation", "getCurrentMediaLocation", "currentMediaPosition", "getCurrentMediaPosition", "currentMediaWrapper", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "getCurrentMediaWrapper", "()Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "currentToast", "Landroid/widget/Toast;", "getCurrentToast", "()Landroid/widget/Toast;", "setCurrentToast", "(Landroid/widget/Toast;)V", "currentVideoTrack", "getCurrentVideoTrack", "()Lorg/videolan/vlc/gui/dialogs/adapters/VlcTrack;", "currentWidgetCover", "detectHeadset", "", "getDetectHeadset", "()Z", "setDetectHeadset", "(Z)V", "dispatcher", "Landroidx/lifecycle/ServiceLifecycleDispatcher;", "enabledActions", "Lorg/videolan/vlc/util/FlagSet;", "Lorg/videolan/vlc/util/PlaybackAction;", "getEnabledActions$vlc_android_release", "()Lorg/videolan/vlc/util/FlagSet;", "setEnabledActions$vlc_android_release", "(Lorg/videolan/vlc/util/FlagSet;)V", "headsetInserted", "getHeadsetInserted", "setHeadsetInserted", "isForeground", "isInPiPMode", "Landroidx/lifecycle/MutableLiveData;", "()Landroidx/lifecycle/MutableLiveData;", "setInPiPMode", "(Landroidx/lifecycle/MutableLiveData;)V", "isPausable", "isPaused", "isPlaying", "isPlayingPopup", "isPodcastMode", "isPodcastPlaying", "isSeekable", "isShuffling", "isVideoPlaying", "keyguardManager", "Landroid/app/KeyguardManager;", "lastChapter", "lastChaptersCount", "lastErrorTime", "getLastErrorTime", "setLastErrorTime", "(J)V", "lastLength", "lastParentId", "lastStats", "Lorg/videolan/libvlc/interfaces/IMedia$Stats;", "getLastStats", "()Lorg/videolan/libvlc/interfaces/IMedia$Stats;", "lastTime", "length", "getLength", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "getLifecycle", "()Landroidx/lifecycle/Lifecycle;", "media", "", "getMedia", "()Ljava/util/List;", "mediaBrowserCompat", "Landroid/support/v4/media/MediaBrowserCompat;", "getMediaBrowserCompat", "()Landroid/support/v4/media/MediaBrowserCompat;", "setMediaBrowserCompat", "(Landroid/support/v4/media/MediaBrowserCompat;)V", "mediaEndReached", "mediaFactory", "Lorg/videolan/libvlc/interfaces/IMediaFactory;", "mediaListSize", "getMediaListSize", "mediaLocations", "getMediaLocations", "mediaPlayerListener", "Lorg/videolan/libvlc/MediaPlayer$EventListener;", "mediaSession", "Landroid/support/v4/media/session/MediaSessionCompat;", "getMediaSession$vlc_android_release", "()Landroid/support/v4/media/session/MediaSessionCompat;", "setMediaSession$vlc_android_release", "(Landroid/support/v4/media/session/MediaSessionCompat;)V", "medialibrary", "Lorg/videolan/medialibrary/interfaces/Medialibrary;", "getMedialibrary$vlc_android_release", "()Lorg/videolan/medialibrary/interfaces/Medialibrary;", "setMedialibrary$vlc_android_release", "(Lorg/videolan/medialibrary/interfaces/Medialibrary;)V", "mediaplayer", "Lorg/videolan/libvlc/MediaPlayer;", "getMediaplayer", "()Lorg/videolan/libvlc/MediaPlayer;", "nbErrors", "getNbErrors", "setNbErrors", "nextCoverArt", "getNextCoverArt", "notification", "Landroid/app/Notification;", "notificationShowing", "<set-?>", "Lorg/videolan/vlc/media/PlaylistManager;", "playlistManager", "getPlaylistManager", "()Lorg/videolan/vlc/media/PlaylistManager;", "popupManager", "Lorg/videolan/vlc/gui/video/PopupManager;", "position", "prevCoverArt", "getPrevCoverArt", "prevUpdateInCarMode", "previousTotalTime", "getPreviousTotalTime", "rate", "", "getRate", "()F", "receiver", "org/videolan/vlc/PlaybackService$receiver$1", "Lorg/videolan/vlc/PlaybackService$receiver$1;", "repeatType", "getRepeatType", "setRepeatType", "resetOnInteraction", "getResetOnInteraction", "setResetOnInteraction", "scheduler", "Lorg/videolan/vlc/util/LifecycleAwareScheduler;", "getScheduler", "()Lorg/videolan/vlc/util/LifecycleAwareScheduler;", "setScheduler", "(Lorg/videolan/vlc/util/LifecycleAwareScheduler;)V", "sessionPendingIntent", "Landroid/app/PendingIntent;", "getSessionPendingIntent", "()Landroid/app/PendingIntent;", "settings", "Landroid/content/SharedPreferences;", "getSettings$vlc_android_release", "()Landroid/content/SharedPreferences;", "setSettings$vlc_android_release", "(Landroid/content/SharedPreferences;)V", "shuffleType", "getShuffleType", "setShuffleType", "sleepTimerInterval", "getSleepTimerInterval", "setSleepTimerInterval", "sleepTimerJob", "Lkotlinx/coroutines/Job;", "getSleepTimerJob", "()Lkotlinx/coroutines/Job;", "setSleepTimerJob", "(Lkotlinx/coroutines/Job;)V", "speed", "getSpeed", "spuDelay", "getSpuDelay", "spuTrack", "getSpuTrack", "spuTracks", "getSpuTracks", "spuTracksCount", "getSpuTracksCount", "subtitleMessage", "Ljava/util/ArrayDeque;", "title", "getTitle", "titleIdx", "getTitleIdx", "setTitleIdx", "titleNext", "getTitleNext", "titlePrev", "getTitlePrev", "titles", "Lorg/videolan/libvlc/MediaPlayer$Title;", "getTitles", "()[Lorg/videolan/libvlc/MediaPlayer$Title;", "videoTrack", "getVideoTrack", "videoTracks", "getVideoTracks", "videoTracksCount", "getVideoTracksCount", "volume", "getVolume", "vout", "Lorg/videolan/libvlc/interfaces/IVLCVout;", "getVout", "()Lorg/videolan/libvlc/interfaces/IVLCVout;", "waitForMediaEnd", "getWaitForMediaEnd", "setWaitForMediaEnd", "wakeLock", "Landroid/os/PowerManager$WakeLock;", "Landroid/os/PowerManager;", "widget", "widgetPositionTimestamp", "addCallback", "Lkotlinx/coroutines/channels/ChannelResult;", "", "cb", "addCallback-JP2dKIU", "(Lorg/videolan/vlc/PlaybackService$Callback;)Ljava/lang/Object;", "addCustomSeekActions", "pscb", "Landroid/support/v4/media/session/PlaybackStateCompat$Builder;", "showSeekActions", "addCustomSpeedActions", "showSpeedActions", "addSubtitleTrack", "uri", "select", "path", "append", "mediaList", "index", "([Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;I)Lkotlinx/coroutines/Job;", "attachBaseContext", "newBase", "Landroid/content/Context;", "broadcastMetadata", "buildQueue", "fromIndex", "toIndex", "canShuffle", "canSwitchToVideo", "checkMetered", "metered", "currentMediaHasFlag", "flag", "decreaseRate", "enable", "displayPlaybackError", "resId", "displayPlaybackMessage", "formatArgs", "(I[Ljava/lang/String;)V", "executeUpdate", "pubState", "flush", "forceForeground", "launchedInForeground", "getApplicationContext", "getChapters", "Lorg/videolan/libvlc/MediaPlayer$Chapter;", "(I)[Lorg/videolan/libvlc/MediaPlayer$Chapter;", "getCurrentChapter", "getTime", "realTime", "hasMedia", "hasNext", "hasPlaylist", "hasPrevious", "hasRenderer", "hideNotification", "remove", "hideNotificationInternal", "increaseRate", "initMediaSession", "insertItem", "mw", "insertNext", "([Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;)V", "isCarMode", "isValidIndex", "positionInPlaylist", "load", "([Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;I)V", "loadLastAudioPlaylist", "loadLastPlaylist", "type", "loadLocation", "mediaPath", "loadLocations", "mediaPathList", "loadUri", "manageAutoActions", "actions", "moveItem", "positionStart", "positionEnd", "navigate", "where", "next", "force", "nextTrackInfo", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "notifyTrackChanged", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "onDestroy", "onGetRoot", "Landroidx/media/MediaBrowserServiceCompat$BrowserRoot;", "clientPackageName", "clientUid", "rootHints", "Landroid/os/Bundle;", "onLoadChildren", "parentId", "result", "Landroidx/media/MediaBrowserServiceCompat$Result;", "Landroid/support/v4/media/MediaBrowserCompat$MediaItem;", "onMediaEvent", "event", "Lorg/videolan/libvlc/interfaces/IMedia$Event;", "onMediaEvent-JP2dKIU", "(Lorg/videolan/libvlc/interfaces/IMedia$Event;)Ljava/lang/Object;", "onMediaListChanged", "onMediaPlayerEvent", "Lorg/videolan/libvlc/MediaPlayer$Event;", "onNewPlayback", "onPlaybackStopped", "systemExit", "onPlaylistLoaded", "onSearch", "query", "extras", "onStartCommand", "flags", "startId", "onTaskRemoved", "rootIntent", "onTaskTriggered", "id", "data", "pause", "play", "playIndex", "playIndexOrLoadLastPlaylist", "prevTrackInfo", "previous", "publishState", "(Ljava/lang/Long;)V", "removeCallback", "removeCallback-JP2dKIU", "removeLocation", "location", "removePopup", "resetRate", "restartMediaPlayer", "restartPlaylistManager", "saveMediaMeta", "saveStartTime", "time", "seek", "", "fromUser", "fast", "sendStartSessionIdIntent", "sendStopSessionIdIntent", "sendWidgetBroadcast", "setAudioDelay", "delay", "setAudioDigitalOutputEnabled", "enabled", "setAudioTrack", "setBenchmark", "setEqualizer", "equalizer", "Lorg/videolan/libvlc/MediaPlayer$Equalizer;", "setHardware", "setPosition", "pos", "setRate", "save", "setRenderer", "item", "Lorg/videolan/libvlc/RendererItem;", "setSleepTimer", "Ljava/util/Calendar;", "setSpuDelay", "setSpuTrack", "setTime", "setVideoAspectRatio", "aspect", "setVideoScale", "scale", "setVideoTrack", "setVideoTrackEnabled", "setVolume", "setupScope", "showNotification", "showNotificationInternal", "showPopup", "showToast", "text", "duration", "isError", "showWithoutParse", "forPopup", "shuffle", "startSleepTimerJob", "stop", "video", "stopSleepTimerJob", "switchToPopup", "switchToVideo", "trackInfo", "unselectTrackType", "trackType", "Lorg/videolan/vlc/gui/dialogs/VideoTracksDialog$TrackType;", "updateHasWidget", "updateMediaQueue", "updateMediaQueueSlidingWindow", "mediaListChanged", "updateMetadata", "updateMetadataInternal", "updateViewpoint", "yaw", "pitch", "roll", "fov", "absolute", "updateWidget", "updateWidgetPosition", "updateWidgetState", "formatTrackInfoString", "Lorg/videolan/libvlc/interfaces/IMedia$AudioTrack;", "context", "Callback", "Companion", "LocalBinder", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlaybackService.kt */
public final class PlaybackService extends MediaBrowserServiceCompat implements LifecycleOwner, CoroutineScope, SchedulerCallback {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String END_MEDIASESSION = "end_mediasession";
    private static final String SHOW_TOAST = "show_toast";
    /* access modifiers changed from: private */
    public static final LiveEvent<MediaPlayer.Equalizer> equalizer = new LiveEvent<>();
    /* access modifiers changed from: private */
    public static final LiveEvent<Boolean> headSetDetection = new LiveEvent<>();
    /* access modifiers changed from: private */
    public static final Lazy<MutableLiveData<Calendar>> playerSleepTime$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, PlaybackService$Companion$playerSleepTime$2.INSTANCE);
    /* access modifiers changed from: private */
    public static final RendererLiveData renderer = new RendererLiveData();
    /* access modifiers changed from: private */
    public static final LiveEvent<Boolean> restartPlayer = new LiveEvent<>();
    /* access modifiers changed from: private */
    public static final MutableStateFlow<PlaybackService> serviceFlow = StateFlowKt.MutableStateFlow(null);
    /* access modifiers changed from: private */
    public Map<String, Uri> artworkMap;
    private final Lazy audioFocusHelper$delegate = LazyKt.lazy(new PlaybackService$audioFocusHelper$2(this));
    private final LocalBinder binder = new LocalBinder();
    private MediaBrowserCallback browserCallback;
    /* access modifiers changed from: private */
    public final List<Callback> callbacks = new ArrayList();
    private CarConnection carConnection;
    /* access modifiers changed from: private */
    public SendChannel<? super CbAction> cbActor;
    private final CoroutineContext coroutineContext = Dispatchers.getIO().plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null));
    private Toast currentToast;
    private String currentWidgetCover;
    private boolean detectHeadset = true;
    private final ServiceLifecycleDispatcher dispatcher = new ServiceLifecycleDispatcher(this);
    private FlagSet<PlaybackAction> enabledActions = PlaybackAction.Companion.createBaseActions();
    private boolean headsetInserted;
    /* access modifiers changed from: private */
    public volatile boolean isForeground;
    private MutableLiveData<Boolean> isInPiPMode = new MutableLiveData<>(false);
    private KeyguardManager keyguardManager;
    private int lastChapter;
    /* access modifiers changed from: private */
    public int lastChaptersCount;
    private long lastErrorTime;
    private long lastLength;
    /* access modifiers changed from: private */
    public String lastParentId = "";
    private long lastTime;
    public MediaBrowserCompat mediaBrowserCompat;
    /* access modifiers changed from: private */
    public boolean mediaEndReached;
    /* access modifiers changed from: private */
    public final IMediaFactory mediaFactory;
    private final MediaPlayer.EventListener mediaPlayerListener;
    public MediaSessionCompat mediaSession;
    public Medialibrary medialibrary;
    private int nbErrors;
    /* access modifiers changed from: private */
    public Notification notification;
    /* access modifiers changed from: private */
    public volatile boolean notificationShowing;
    private PlaylistManager playlistManager;
    private PopupManager popupManager;
    private long position = -1;
    /* access modifiers changed from: private */
    public boolean prevUpdateInCarMode = true;
    private final PlaybackService$receiver$1 receiver;
    private boolean resetOnInteraction;
    public LifecycleAwareScheduler scheduler;
    public SharedPreferences settings;
    private long sleepTimerInterval;
    private Job sleepTimerJob;
    private final ArrayDeque<String> subtitleMessage = new ArrayDeque<>(1);
    private boolean waitForMediaEnd;
    private PowerManager.WakeLock wakeLock;
    private int widget;
    private long widgetPositionTimestamp = System.currentTimeMillis();

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\u0003H&¨\u0006\t"}, d2 = {"Lorg/videolan/vlc/PlaybackService$Callback;", "", "onMediaEvent", "", "event", "Lorg/videolan/libvlc/interfaces/IMedia$Event;", "onMediaPlayerEvent", "Lorg/videolan/libvlc/MediaPlayer$Event;", "update", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: PlaybackService.kt */
    public interface Callback {
        void onMediaEvent(IMedia.Event event);

        void onMediaPlayerEvent(MediaPlayer.Event event);

        void update();
    }

    public final void playIndex(int i) {
        playIndex$default(this, i, 0, 2, (Object) null);
    }

    public final void seek(long j) {
        seek$default(this, j, 0.0d, false, false, 14, (Object) null);
    }

    public final void seek(long j, double d) {
        seek$default(this, j, d, false, false, 12, (Object) null);
    }

    public final void seek(long j, double d, boolean z) {
        seek$default(this, j, d, z, false, 8, (Object) null);
    }

    public final void stop() {
        stop$default(this, false, false, 3, (Object) null);
    }

    public final void stop(boolean z) {
        stop$default(this, z, false, 2, (Object) null);
    }

    public PlaybackService() {
        IComponentFactory factory = FactoryManager.getFactory(IMediaFactory.factoryId);
        Intrinsics.checkNotNull(factory, "null cannot be cast to non-null type org.videolan.libvlc.interfaces.IMediaFactory");
        this.mediaFactory = (IMediaFactory) factory;
        this.receiver = new PlaybackService$receiver$1(this);
        this.mediaPlayerListener = new PlaybackService$$ExternalSyntheticLambda4(this);
    }

    public void onTaskCancelled(String str) {
        SchedulerCallback.DefaultImpls.onTaskCancelled(this, str);
    }

    public CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }

    public final LifecycleAwareScheduler getScheduler() {
        LifecycleAwareScheduler lifecycleAwareScheduler = this.scheduler;
        if (lifecycleAwareScheduler != null) {
            return lifecycleAwareScheduler;
        }
        Intrinsics.throwUninitializedPropertyAccessException("scheduler");
        return null;
    }

    public final void setScheduler(LifecycleAwareScheduler lifecycleAwareScheduler) {
        Intrinsics.checkNotNullParameter(lifecycleAwareScheduler, "<set-?>");
        this.scheduler = lifecycleAwareScheduler;
    }

    public final FlagSet<PlaybackAction> getEnabledActions$vlc_android_release() {
        return this.enabledActions;
    }

    public final void setEnabledActions$vlc_android_release(FlagSet<PlaybackAction> flagSet) {
        Intrinsics.checkNotNullParameter(flagSet, "<set-?>");
        this.enabledActions = flagSet;
    }

    public final PlaylistManager getPlaylistManager() {
        PlaylistManager playlistManager2 = this.playlistManager;
        if (playlistManager2 != null) {
            return playlistManager2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("playlistManager");
        return null;
    }

    public final MediaPlayer getMediaplayer() {
        return getPlaylistManager().getPlayer().getMediaplayer();
    }

    public final SharedPreferences getSettings$vlc_android_release() {
        SharedPreferences sharedPreferences = this.settings;
        if (sharedPreferences != null) {
            return sharedPreferences;
        }
        Intrinsics.throwUninitializedPropertyAccessException("settings");
        return null;
    }

    public final void setSettings$vlc_android_release(SharedPreferences sharedPreferences) {
        Intrinsics.checkNotNullParameter(sharedPreferences, "<set-?>");
        this.settings = sharedPreferences;
    }

    public final Medialibrary getMedialibrary$vlc_android_release() {
        Medialibrary medialibrary2 = this.medialibrary;
        if (medialibrary2 != null) {
            return medialibrary2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
        return null;
    }

    public final void setMedialibrary$vlc_android_release(Medialibrary medialibrary2) {
        Intrinsics.checkNotNullParameter(medialibrary2, "<set-?>");
        this.medialibrary = medialibrary2;
    }

    public final boolean getDetectHeadset() {
        return this.detectHeadset;
    }

    public final void setDetectHeadset(boolean z) {
        this.detectHeadset = z;
    }

    public final boolean getHeadsetInserted() {
        return this.headsetInserted;
    }

    public final void setHeadsetInserted(boolean z) {
        this.headsetInserted = z;
    }

    /* access modifiers changed from: private */
    public final VLCAudioFocusHelper getAudioFocusHelper() {
        return (VLCAudioFocusHelper) this.audioFocusHelper$delegate.getValue();
    }

    public final Job getSleepTimerJob() {
        return this.sleepTimerJob;
    }

    public final void setSleepTimerJob(Job job) {
        this.sleepTimerJob = job;
    }

    public final boolean getWaitForMediaEnd() {
        return this.waitForMediaEnd;
    }

    public final void setWaitForMediaEnd(boolean z) {
        this.waitForMediaEnd = z;
    }

    public final boolean getResetOnInteraction() {
        return this.resetOnInteraction;
    }

    public final void setResetOnInteraction(boolean z) {
        this.resetOnInteraction = z;
    }

    public final long getSleepTimerInterval() {
        return this.sleepTimerInterval;
    }

    public final void setSleepTimerInterval(long j) {
        this.sleepTimerInterval = j;
    }

    public final MutableLiveData<Boolean> isInPiPMode() {
        return this.isInPiPMode;
    }

    public final void setInPiPMode(MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.isInPiPMode = mutableLiveData;
    }

    public final MediaSessionCompat getMediaSession$vlc_android_release() {
        MediaSessionCompat mediaSessionCompat = this.mediaSession;
        if (mediaSessionCompat != null) {
            return mediaSessionCompat;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mediaSession");
        return null;
    }

    public final void setMediaSession$vlc_android_release(MediaSessionCompat mediaSessionCompat) {
        Intrinsics.checkNotNullParameter(mediaSessionCompat, "<set-?>");
        this.mediaSession = mediaSessionCompat;
    }

    public final Toast getCurrentToast() {
        return this.currentToast;
    }

    public final void setCurrentToast(Toast toast) {
        this.currentToast = toast;
    }

    public final long getLastErrorTime() {
        return this.lastErrorTime;
    }

    public final void setLastErrorTime(long j) {
        this.lastErrorTime = j;
    }

    public final int getNbErrors() {
        return this.nbErrors;
    }

    public final void setNbErrors(int i) {
        this.nbErrors = i;
    }

    public final MediaBrowserCompat getMediaBrowserCompat() {
        MediaBrowserCompat mediaBrowserCompat2 = this.mediaBrowserCompat;
        if (mediaBrowserCompat2 != null) {
            return mediaBrowserCompat2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mediaBrowserCompat");
        return null;
    }

    public final void setMediaBrowserCompat(MediaBrowserCompat mediaBrowserCompat2) {
        Intrinsics.checkNotNullParameter(mediaBrowserCompat2, "<set-?>");
        this.mediaBrowserCompat = mediaBrowserCompat2;
    }

    /* access modifiers changed from: private */
    public static final void mediaPlayerListener$lambda$1(PlaybackService playbackService, MediaPlayer.Event event) {
        Boolean value;
        Intrinsics.checkNotNullParameter(playbackService, "this$0");
        int i = event.type;
        int i2 = 0;
        SendChannel<? super CbAction> sendChannel = null;
        if (i == 260) {
            playbackService.executeUpdate(true);
            playbackService.lastTime = playbackService.getTime();
            playbackService.getAudioFocusHelper().changeAudioFocus$vlc_android_release(true);
            PowerManager.WakeLock wakeLock2 = playbackService.wakeLock;
            if (wakeLock2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("wakeLock");
                wakeLock2 = null;
            }
            if (!wakeLock2.isHeld()) {
                PowerManager.WakeLock wakeLock3 = playbackService.wakeLock;
                if (wakeLock3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("wakeLock");
                    wakeLock3 = null;
                }
                wakeLock3.acquire();
            }
            playbackService.showNotification();
            playbackService.nbErrors = 0;
            if (Build.VERSION.SDK_INT >= 21 && (value = NetworkConnectionManager.INSTANCE.isMetered().getValue()) != null) {
                playbackService.checkMetered(value.booleanValue());
            }
        } else if (i == 261) {
            playbackService.executeUpdate(true);
            playbackService.showNotification();
            PowerManager.WakeLock wakeLock4 = playbackService.wakeLock;
            if (wakeLock4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("wakeLock");
                wakeLock4 = null;
            }
            if (wakeLock4.isHeld()) {
                PowerManager.WakeLock wakeLock5 = playbackService.wakeLock;
                if (wakeLock5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("wakeLock");
                    wakeLock5 = null;
                }
                wakeLock5.release();
            }
        } else if (i == 265) {
            playbackService.mediaEndReached = true;
        } else if (i == 266) {
            executeUpdate$default(playbackService, false, 1, (Object) null);
        } else if (i == 268) {
            if (playbackService.getLength() == 0) {
                playbackService.position = (long) (((float) 1000) * event.getPositionChanged());
            }
            if (playbackService.getTime() < 1000 && playbackService.getTime() < playbackService.lastTime) {
                publishState$default(playbackService, (Long) null, 1, (Object) null);
            }
            playbackService.lastTime = playbackService.getTime();
            if (playbackService.widget != 0) {
                playbackService.updateWidgetPosition(event.getPositionChanged());
            }
            int chapterIdx = playbackService.getChapterIdx();
            if (playbackService.lastChapter != chapterIdx) {
                executeUpdate$default(playbackService, false, 1, (Object) null);
                playbackService.showNotification();
            }
            playbackService.lastChapter = chapterIdx;
        } else if (i == 273) {
            MediaPlayer.Chapter[] chapters = playbackService.getChapters(-1);
            if (chapters != null) {
                i2 = chapters.length;
            }
            playbackService.lastChaptersCount = i2;
            if (playbackService.lastLength == 0) {
                playbackService.executeUpdate(true);
            }
        } else if (i == 276 && event.getEsChangedType() == 1 && (playbackService.getPlaylistManager().getVideoBackground() || !playbackService.getPlaylistManager().switchToVideo())) {
            playbackService.updateMetadata();
        }
        SendChannel<? super CbAction> sendChannel2 = playbackService.cbActor;
        if (sendChannel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cbActor");
        } else {
            sendChannel = sendChannel2;
        }
        Intrinsics.checkNotNull(event);
        sendChannel.m1139trySendJP2dKIU(new CbMediaPlayerEvent(event));
    }

    public final PendingIntent getSessionPendingIntent() {
        if (getPlaylistManager().getPlayer().isVideoPlaying()) {
            Context context = this;
            Intent intent = new Intent(context, VideoPlayerActivity.class);
            intent.putExtra(VideoPlayerActivity.FROM_EXTERNAL, true);
            PendingIntent activity = PendingIntent.getActivity(context, 0, intent, 201326592);
            Intrinsics.checkNotNull(activity);
            return activity;
        } else if (getPlaylistManager().getVideoBackground() || (canSwitchToVideo() && !currentMediaHasFlag(8))) {
            PendingIntent broadcast = PendingIntent.getBroadcast(this, 0, new Intent(Constants.ACTION_REMOTE_SWITCH_VIDEO), 201326592);
            Intrinsics.checkNotNull(broadcast);
            return broadcast;
        } else {
            Context context2 = this;
            PendingIntent activity2 = PendingIntent.getActivity(context2, 0, new Intent(context2, StartActivity.class), 201326592);
            Intrinsics.checkNotNull(activity2);
            return activity2;
        }
    }

    public final boolean isPodcastMode() {
        return getPlaylistManager().getMediaListSize() == 1 && isPodcastPlaying();
    }

    public final boolean isPodcastPlaying() {
        MediaWrapper currentMedia = getPlaylistManager().getCurrentMedia();
        return currentMedia != null && currentMedia.isPodcast();
    }

    public final float getSpeed() {
        Float value = getPlaylistManager().getPlayer().getSpeed().getValue();
        if (value == null) {
            return 1.0f;
        }
        return value.floatValue();
    }

    public final boolean isPlaying() {
        return getPlaylistManager().getPlayer().isPlaying();
    }

    public final boolean isSeekable() {
        return getPlaylistManager().getPlayer().getSeekable();
    }

    public final boolean isPausable() {
        return getPlaylistManager().getPlayer().getPausable();
    }

    public final boolean isPaused() {
        return getPlaylistManager().getPlayer().isPaused();
    }

    public final boolean isShuffling() {
        return getPlaylistManager().getShuffling();
    }

    public final int getShuffleType() {
        return getPlaylistManager().getShuffling() ? 1 : 0;
    }

    public final void setShuffleType(int i) {
        if (i == 1 && !isShuffling()) {
            shuffle();
        } else if (i == 0 && isShuffling()) {
            shuffle();
        } else if (i == 2 && !isShuffling()) {
            shuffle();
        } else if (i == 2 && isShuffling()) {
            publishState$default(this, (Long) null, 1, (Object) null);
        }
    }

    public final int getRepeatType() {
        return PlaylistManager.Companion.getRepeating().getValue().intValue();
    }

    public final void setRepeatType(int i) {
        PlaylistManager playlistManager2 = getPlaylistManager();
        if (i == 3) {
            i = 2;
        }
        playlistManager2.setRepeatType(i);
        publishState$default(this, (Long) null, 1, (Object) null);
    }

    public final boolean isVideoPlaying() {
        return getPlaylistManager().getPlayer().isVideoPlaying();
    }

    public final String getAlbum() {
        MediaWrapper currentMedia = getPlaylistManager().getCurrentMedia();
        if (currentMedia != null) {
            return MediaUtils.INSTANCE.getMediaAlbum(this, currentMedia);
        }
        return null;
    }

    public final String getAlbumPrev() {
        MediaWrapper prevMedia = getPlaylistManager().getPrevMedia();
        if (prevMedia != null) {
            return MediaUtils.INSTANCE.getMediaAlbum(this, prevMedia);
        }
        return null;
    }

    public final String getAlbumNext() {
        MediaWrapper nextMedia = getPlaylistManager().getNextMedia();
        if (nextMedia != null) {
            return MediaUtils.INSTANCE.getMediaAlbum(this, nextMedia);
        }
        return null;
    }

    public final String getArtist() {
        MediaWrapper currentMedia = getPlaylistManager().getCurrentMedia();
        if (currentMedia != null) {
            return MediaUtils.INSTANCE.getMediaArtist(this, currentMedia);
        }
        return null;
    }

    public final String getArtistPrev() {
        MediaWrapper prevMedia = getPlaylistManager().getPrevMedia();
        if (prevMedia != null) {
            return MediaUtils.INSTANCE.getMediaArtist(this, prevMedia);
        }
        return null;
    }

    public final String getArtistNext() {
        MediaWrapper nextMedia = getPlaylistManager().getNextMedia();
        if (nextMedia != null) {
            return MediaUtils.INSTANCE.getMediaArtist(this, nextMedia);
        }
        return null;
    }

    public final String getTitle() {
        MediaWrapper currentMedia = getPlaylistManager().getCurrentMedia();
        if (currentMedia != null) {
            return currentMedia.getNowPlaying() != null ? currentMedia.getNowPlaying() : currentMedia.getTitle();
        }
        return null;
    }

    public final String getTitlePrev() {
        MediaWrapper prevMedia = getPlaylistManager().getPrevMedia();
        if (prevMedia != null) {
            return prevMedia.getTitle();
        }
        return null;
    }

    public final String getTitleNext() {
        MediaWrapper nextMedia = getPlaylistManager().getNextMedia();
        if (nextMedia != null) {
            return nextMedia.getTitle();
        }
        return null;
    }

    public final String getCoverArt() {
        MediaWrapper currentMedia = getPlaylistManager().getCurrentMedia();
        if (currentMedia != null) {
            return currentMedia.getArtworkMrl();
        }
        return null;
    }

    public final String getPrevCoverArt() {
        MediaWrapper prevMedia = getPlaylistManager().getPrevMedia();
        if (prevMedia != null) {
            return prevMedia.getArtworkMrl();
        }
        return null;
    }

    public final String getNextCoverArt() {
        MediaWrapper nextMedia = getPlaylistManager().getNextMedia();
        if (nextMedia != null) {
            return nextMedia.getArtworkMrl();
        }
        return null;
    }

    public final String getCurrentChapter() {
        int chapterIdx;
        MediaPlayer.Chapter[] chapters = getChapters(-1);
        if (chapters == null || (chapterIdx = getChapterIdx()) < 0) {
            return null;
        }
        if (!(chapters.length == 0)) {
            return TextUtils.INSTANCE.formatChapterTitle(this, chapterIdx + 1, chapters[chapterIdx].name);
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object trackInfo(kotlin.coroutines.Continuation<? super java.lang.String> r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof org.videolan.vlc.PlaybackService$trackInfo$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            org.videolan.vlc.PlaybackService$trackInfo$1 r0 = (org.videolan.vlc.PlaybackService$trackInfo$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.PlaybackService$trackInfo$1 r0 = new org.videolan.vlc.PlaybackService$trackInfo$1
            r0.<init>(r6, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x0037
            if (r2 != r4) goto L_0x002f
            java.lang.Object r0 = r0.L$0
            org.videolan.vlc.PlaybackService r0 = (org.videolan.vlc.PlaybackService) r0
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x005e
        L_0x002f:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L_0x0037:
            kotlin.ResultKt.throwOnFailure(r7)
            org.videolan.vlc.media.PlaylistManager r7 = r6.getPlaylistManager()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r7 = r7.getCurrentMedia()
            if (r7 != 0) goto L_0x0045
            return r3
        L_0x0045:
            kotlinx.coroutines.CoroutineDispatcher r2 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r2 = (kotlin.coroutines.CoroutineContext) r2
            org.videolan.vlc.PlaybackService$trackInfo$media$1 r5 = new org.videolan.vlc.PlaybackService$trackInfo$media$1
            r5.<init>(r6, r7, r3)
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
            r0.L$0 = r6
            r0.label = r4
            java.lang.Object r7 = kotlinx.coroutines.BuildersKt.withContext(r2, r5, r0)
            if (r7 != r1) goto L_0x005d
            return r1
        L_0x005d:
            r0 = r6
        L_0x005e:
            org.videolan.libvlc.interfaces.IMedia r7 = (org.videolan.libvlc.interfaces.IMedia) r7
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
            java.util.List r1 = org.videolan.vlc.VersionDependantKt.getAudioTracks(r7)
            r7.release()
            int r7 = r1.size()
            if (r7 != r4) goto L_0x007d
            java.lang.Object r7 = kotlin.collections.CollectionsKt.first(r1)
            org.videolan.libvlc.interfaces.IMedia$AudioTrack r7 = (org.videolan.libvlc.interfaces.IMedia.AudioTrack) r7
            r1 = r0
            android.content.Context r1 = (android.content.Context) r1
            java.lang.String r3 = r0.formatTrackInfoString(r7, r1)
        L_0x007d:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.PlaybackService.trackInfo(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object prevTrackInfo(kotlin.coroutines.Continuation<? super java.lang.String> r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof org.videolan.vlc.PlaybackService$prevTrackInfo$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            org.videolan.vlc.PlaybackService$prevTrackInfo$1 r0 = (org.videolan.vlc.PlaybackService$prevTrackInfo$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.PlaybackService$prevTrackInfo$1 r0 = new org.videolan.vlc.PlaybackService$prevTrackInfo$1
            r0.<init>(r6, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x0037
            if (r2 != r4) goto L_0x002f
            java.lang.Object r0 = r0.L$0
            org.videolan.vlc.PlaybackService r0 = (org.videolan.vlc.PlaybackService) r0
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x005e
        L_0x002f:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L_0x0037:
            kotlin.ResultKt.throwOnFailure(r7)
            org.videolan.vlc.media.PlaylistManager r7 = r6.getPlaylistManager()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r7 = r7.getPrevMedia()
            if (r7 != 0) goto L_0x0045
            return r3
        L_0x0045:
            kotlinx.coroutines.CoroutineDispatcher r2 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r2 = (kotlin.coroutines.CoroutineContext) r2
            org.videolan.vlc.PlaybackService$prevTrackInfo$media$1 r5 = new org.videolan.vlc.PlaybackService$prevTrackInfo$media$1
            r5.<init>(r6, r7, r3)
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
            r0.L$0 = r6
            r0.label = r4
            java.lang.Object r7 = kotlinx.coroutines.BuildersKt.withContext(r2, r5, r0)
            if (r7 != r1) goto L_0x005d
            return r1
        L_0x005d:
            r0 = r6
        L_0x005e:
            org.videolan.libvlc.interfaces.IMedia r7 = (org.videolan.libvlc.interfaces.IMedia) r7
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
            java.util.List r1 = org.videolan.vlc.VersionDependantKt.getAudioTracks(r7)
            r7.release()
            int r7 = r1.size()
            if (r7 != r4) goto L_0x007d
            java.lang.Object r7 = kotlin.collections.CollectionsKt.first(r1)
            org.videolan.libvlc.interfaces.IMedia$AudioTrack r7 = (org.videolan.libvlc.interfaces.IMedia.AudioTrack) r7
            r1 = r0
            android.content.Context r1 = (android.content.Context) r1
            java.lang.String r3 = r0.formatTrackInfoString(r7, r1)
        L_0x007d:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.PlaybackService.prevTrackInfo(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object nextTrackInfo(kotlin.coroutines.Continuation<? super java.lang.String> r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof org.videolan.vlc.PlaybackService$nextTrackInfo$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            org.videolan.vlc.PlaybackService$nextTrackInfo$1 r0 = (org.videolan.vlc.PlaybackService$nextTrackInfo$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.PlaybackService$nextTrackInfo$1 r0 = new org.videolan.vlc.PlaybackService$nextTrackInfo$1
            r0.<init>(r6, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x0037
            if (r2 != r4) goto L_0x002f
            java.lang.Object r0 = r0.L$0
            org.videolan.vlc.PlaybackService r0 = (org.videolan.vlc.PlaybackService) r0
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x005e
        L_0x002f:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L_0x0037:
            kotlin.ResultKt.throwOnFailure(r7)
            org.videolan.vlc.media.PlaylistManager r7 = r6.getPlaylistManager()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r7 = r7.getNextMedia()
            if (r7 != 0) goto L_0x0045
            return r3
        L_0x0045:
            kotlinx.coroutines.CoroutineDispatcher r2 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r2 = (kotlin.coroutines.CoroutineContext) r2
            org.videolan.vlc.PlaybackService$nextTrackInfo$media$1 r5 = new org.videolan.vlc.PlaybackService$nextTrackInfo$media$1
            r5.<init>(r6, r7, r3)
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
            r0.L$0 = r6
            r0.label = r4
            java.lang.Object r7 = kotlinx.coroutines.BuildersKt.withContext(r2, r5, r0)
            if (r7 != r1) goto L_0x005d
            return r1
        L_0x005d:
            r0 = r6
        L_0x005e:
            org.videolan.libvlc.interfaces.IMedia r7 = (org.videolan.libvlc.interfaces.IMedia) r7
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
            java.util.List r1 = org.videolan.vlc.VersionDependantKt.getAudioTracks(r7)
            r7.release()
            int r7 = r1.size()
            if (r7 != r4) goto L_0x007d
            java.lang.Object r7 = kotlin.collections.CollectionsKt.first(r1)
            org.videolan.libvlc.interfaces.IMedia$AudioTrack r7 = (org.videolan.libvlc.interfaces.IMedia.AudioTrack) r7
            r1 = r0
            android.content.Context r1 = (android.content.Context) r1
            java.lang.String r3 = r0.formatTrackInfoString(r7, r1)
        L_0x007d:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.PlaybackService.nextTrackInfo(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final String formatTrackInfoString(IMedia.AudioTrack audioTrack, Context context) {
        Intrinsics.checkNotNullParameter(audioTrack, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        List arrayList = new ArrayList();
        if (audioTrack.bitrate > 0) {
            String string = context.getString(R.string.track_bitrate_info, new Object[]{Strings.readableSize((long) audioTrack.bitrate)});
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            arrayList.add(string);
        }
        String string2 = context.getString(R.string.track_codec_info, new Object[]{audioTrack.codec});
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        arrayList.add(string2);
        String string3 = context.getString(R.string.track_samplerate_info, new Object[]{Integer.valueOf(audioTrack.rate)});
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        arrayList.add(string3);
        return StringsKt.replace$default(TextUtils.INSTANCE.separatedString((String[]) arrayList.toArray(new String[0])), "\n", "", false, 4, (Object) null);
    }

    public final long getLength() {
        return getPlaylistManager().getPlayer().getLength();
    }

    public final IMedia.Stats getLastStats() {
        return getPlaylistManager().getPlayer().getPreviousMediaStats();
    }

    public final boolean isPlayingPopup() {
        return this.popupManager != null;
    }

    public final int getMediaListSize() {
        return getPlaylistManager().getMediaListSize();
    }

    public final List<MediaWrapper> getMedia() {
        return getPlaylistManager().getMediaList();
    }

    public final long getPreviousTotalTime() {
        return getPlaylistManager().previousTotalTime();
    }

    public final List<String> getMediaLocations() {
        List<String> arrayList = new ArrayList<>();
        for (MediaWrapper location : getPlaylistManager().getMediaList()) {
            String location2 = location.getLocation();
            Intrinsics.checkNotNullExpressionValue(location2, "getLocation(...)");
            arrayList.add(location2);
        }
        return arrayList;
    }

    public final String getCurrentMediaLocation() {
        MediaWrapper currentMedia = getPlaylistManager().getCurrentMedia();
        if (currentMedia != null) {
            return currentMedia.getLocation();
        }
        return null;
    }

    public final int getCurrentMediaPosition() {
        return getPlaylistManager().getCurrentIndex();
    }

    public final MediaWrapper getCurrentMediaWrapper() {
        return getPlaylistManager().getCurrentMedia();
    }

    public final float getRate() {
        return getPlaylistManager().getPlayer().getRate();
    }

    public final MediaPlayer.Title[] getTitles() {
        return getPlaylistManager().getPlayer().getTitles();
    }

    public final int getChapterIdx() {
        return getPlaylistManager().getPlayer().getChapterIdx();
    }

    public final void setChapterIdx(int i) {
        getPlaylistManager().getPlayer().setChapterIdx(i);
        MediaPlayer.Chapter[] chapters = getChapters(-1);
        if (chapters != null) {
            publishState(Long.valueOf(chapters[i].timeOffset));
        }
    }

    public final int getTitleIdx() {
        return getPlaylistManager().getPlayer().getTitleIdx();
    }

    public final void setTitleIdx(int i) {
        getPlaylistManager().getPlayer().setTitleIdx(i);
    }

    public final int getVolume() {
        return getPlaylistManager().getPlayer().getVolume();
    }

    public final int getAudioTracksCount() {
        return getPlaylistManager().getPlayer().getAudioTracksCount();
    }

    public final VlcTrack[] getAudioTracks() {
        return getPlaylistManager().getPlayer().getAudioTracks();
    }

    public final String getAudioTrack() {
        return getPlaylistManager().getPlayer().getAudioTrack();
    }

    public final int getVideoTracksCount() {
        if (hasMedia()) {
            return getPlaylistManager().getPlayer().getVideoTracksCount();
        }
        return 0;
    }

    public final VlcTrack[] getVideoTracks() {
        return getPlaylistManager().getPlayer().getVideoTracks();
    }

    public final VlcTrack getCurrentVideoTrack() {
        return getPlaylistManager().getPlayer().getCurrentVideoTrack();
    }

    public final String getVideoTrack() {
        return getPlaylistManager().getPlayer().getVideoTrack();
    }

    public final VlcTrack[] getSpuTracks() {
        return getPlaylistManager().getPlayer().getSpuTracks();
    }

    public final String getSpuTrack() {
        return getPlaylistManager().getPlayer().getSpuTrack();
    }

    public final int getSpuTracksCount() {
        return getPlaylistManager().getPlayer().getSpuTracksCount();
    }

    public final long getAudioDelay() {
        return getPlaylistManager().getPlayer().getAudioDelay();
    }

    public final long getSpuDelay() {
        return getPlaylistManager().getPlayer().getSpuDelay();
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u00048@X\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lorg/videolan/vlc/PlaybackService$LocalBinder;", "Landroid/os/Binder;", "(Lorg/videolan/vlc/PlaybackService;)V", "service", "Lorg/videolan/vlc/PlaybackService;", "getService$vlc_android_release", "()Lorg/videolan/vlc/PlaybackService;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: PlaybackService.kt */
    private final class LocalBinder extends Binder {
        public LocalBinder() {
        }

        public final PlaybackService getService$vlc_android_release() {
            return PlaybackService.this;
        }
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context != null ? LocaleUtilsKt.getContextWithLocale(context, AppContextProvider.INSTANCE.getLocale()) : null);
    }

    public Context getApplicationContext() {
        Context applicationContext = super.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        return LocaleUtilsKt.getContextWithLocale(applicationContext, AppContextProvider.INSTANCE.getLocale());
    }

    public void onCreate() {
        setScheduler(new LifecycleAwareScheduler(this));
        this.dispatcher.onServicePreSuperOnCreate();
        setupScope();
        MediaBrowserCallback mediaBrowserCallback = null;
        forceForeground$default(this, false, 1, (Object) null);
        super.onCreate();
        NotificationHelper.INSTANCE.createNotificationChannels(getApplicationContext());
        setSettings$vlc_android_release((SharedPreferences) Settings.INSTANCE.getInstance(this));
        this.playlistManager = new PlaylistManager(this);
        Context context = this;
        Util.INSTANCE.checkCpuCompatibility(context);
        Medialibrary instance = Medialibrary.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(...)");
        setMedialibrary$vlc_android_release(instance);
        this.artworkMap = new HashMap();
        MediaBrowserCallback mediaBrowserCallback2 = new MediaBrowserCallback(this);
        this.browserCallback = mediaBrowserCallback2;
        mediaBrowserCallback2.registerMediaCallback(new PlaybackService$onCreate$1(this));
        MediaBrowserCallback mediaBrowserCallback3 = this.browserCallback;
        if (mediaBrowserCallback3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("browserCallback");
        } else {
            mediaBrowserCallback = mediaBrowserCallback3;
        }
        mediaBrowserCallback.registerHistoryCallback(new PlaybackService$onCreate$2(this));
        this.detectHeadset = getSettings$vlc_android_release().getBoolean("enable_headset_detection", true);
        Object systemService = ContextCompat.getSystemService(getApplicationContext(), PowerManager.class);
        Intrinsics.checkNotNull(systemService);
        PowerManager.WakeLock newWakeLock = ((PowerManager) systemService).newWakeLock(1, "VLC/PlaybackService");
        Intrinsics.checkNotNullExpressionValue(newWakeLock, "newWakeLock(...)");
        this.wakeLock = newWakeLock;
        updateHasWidget();
        if (this.mediaSession == null) {
            initMediaSession();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.setPriority(Integer.MAX_VALUE);
        intentFilter.addAction(VLCAppWidgetProvider.Companion.getACTION_WIDGET_INIT());
        intentFilter.addAction(VLCAppWidgetProvider.Companion.getACTION_WIDGET_ENABLED());
        intentFilter.addAction(VLCAppWidgetProvider.Companion.getACTION_WIDGET_DISABLED());
        intentFilter.addAction(MiniPlayerAppWidgetProvider.Companion.getACTION_WIDGET_INIT());
        intentFilter.addAction(MiniPlayerAppWidgetProvider.Companion.getACTION_WIDGET_ENABLED());
        intentFilter.addAction(MiniPlayerAppWidgetProvider.Companion.getACTION_WIDGET_DISABLED());
        intentFilter.addAction("android.intent.action.HEADSET_PLUG");
        intentFilter.addAction("android.media.AUDIO_BECOMING_NOISY");
        intentFilter.addAction(Constants.CUSTOM_ACTION);
        ExtensionsKt.registerReceiverCompat(context, this.receiver, intentFilter, false);
        if (Build.VERSION.SDK_INT >= 23) {
            CarConnection carConnection2 = new CarConnection(context);
            this.carConnection = carConnection2;
            carConnection2.getType().observeForever(new PlaybackServiceKt$sam$androidx_lifecycle_Observer$0(new PlaybackService$onCreate$3(this)));
        }
        Object systemService2 = ContextCompat.getSystemService(context, KeyguardManager.class);
        Intrinsics.checkNotNull(systemService2);
        this.keyguardManager = (KeyguardManager) systemService2;
        LifecycleOwner lifecycleOwner = this;
        renderer.observe(lifecycleOwner, new PlaybackService$$ExternalSyntheticLambda0(this));
        restartPlayer.observe(lifecycleOwner, new PlaybackService$$ExternalSyntheticLambda1(this));
        headSetDetection.observe(lifecycleOwner, new PlaybackService$$ExternalSyntheticLambda2(this));
        equalizer.observe(lifecycleOwner, new PlaybackService$$ExternalSyntheticLambda3(this));
        serviceFlow.setValue(this);
        setMediaBrowserCompat((MediaBrowserCompat) MediaBrowserInstance.INSTANCE.getInstance(this));
        PlaylistManager.Companion.getPlayingState().setValue(true);
        if (Build.VERSION.SDK_INT >= 21) {
            NetworkConnectionManager.INSTANCE.isMetered().observe(lifecycleOwner, new PlaybackServiceKt$sam$androidx_lifecycle_Observer$0(new PlaybackService$onCreate$8(this)));
        }
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$7(PlaybackService playbackService, RendererItem rendererItem) {
        Intrinsics.checkNotNullParameter(playbackService, "this$0");
        playbackService.setRenderer(rendererItem);
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$8(PlaybackService playbackService, boolean z) {
        Intrinsics.checkNotNullParameter(playbackService, "this$0");
        playbackService.restartPlaylistManager();
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$9(PlaybackService playbackService, boolean z) {
        Intrinsics.checkNotNullParameter(playbackService, "this$0");
        playbackService.detectHeadset(z);
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$10(PlaybackService playbackService, MediaPlayer.Equalizer equalizer2) {
        Intrinsics.checkNotNullParameter(playbackService, "this$0");
        playbackService.setEqualizer(equalizer2);
    }

    /* access modifiers changed from: private */
    public final void checkMetered(boolean z) {
        if (z) {
            SharedPreferences settings$vlc_android_release = getSettings$vlc_android_release();
            String str = Constants.GROUP_VIDEOS_FOLDER;
            String string = settings$vlc_android_release.getString("metered_connection", str);
            if (string != null) {
                str = string;
            }
            int parseInt = Integer.parseInt(str);
            if (parseInt != 0 && BrowserutilsKt.isSchemeStreaming(getCurrentMediaLocation())) {
                Unit unit = null;
                if (parseInt == 1) {
                    stop$default(this, false, false, 3, (Object) null);
                    Activity currentActivity = AppContextProvider.INSTANCE.getCurrentActivity();
                    AudioPlayerContainerActivity audioPlayerContainerActivity = currentActivity instanceof AudioPlayerContainerActivity ? (AudioPlayerContainerActivity) currentActivity : null;
                    if (audioPlayerContainerActivity != null) {
                        String string2 = getString(R.string.metered_connection_stopped);
                        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                        UiTools.INSTANCE.snackerConfirm(audioPlayerContainerActivity, string2, audioPlayerContainerActivity.isAudioPlayerExpanded(), R.string.preferences, new PlaybackService$checkMetered$1$1(this, audioPlayerContainerActivity));
                        unit = Unit.INSTANCE;
                    }
                    if (unit == null) {
                        PlaybackService playbackService = this;
                        Toast.makeText(this, R.string.metered_connection_stopped, 1).show();
                        return;
                    }
                    return;
                }
                Activity currentActivity2 = AppContextProvider.INSTANCE.getCurrentActivity();
                if (currentActivity2 != null) {
                    UiTools uiTools = UiTools.INSTANCE;
                    String string3 = getString(R.string.metered_connection_warning);
                    Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                    uiTools.snackerConfirm(currentActivity2, string3, (currentActivity2 instanceof AudioPlayerContainerActivity) && ((AudioPlayerContainerActivity) currentActivity2).isAudioPlayerExpanded(), R.string.preferences, new PlaybackService$checkMetered$3$1(this, currentActivity2));
                    unit = Unit.INSTANCE;
                }
                if (unit == null) {
                    PlaybackService playbackService2 = this;
                    Toast.makeText(this, R.string.metered_connection_warning, 1).show();
                }
            }
        }
    }

    private final void setupScope() {
        this.cbActor = ActorKt.actor$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, Integer.MAX_VALUE, (CoroutineStart) null, (Function1) null, new PlaybackService$setupScope$1(this, (Continuation<? super PlaybackService$setupScope$1>) null), 13, (Object) null);
    }

    /* access modifiers changed from: private */
    public final void updateHasWidget() {
        Context context = this;
        AppWidgetManager instance = AppWidgetManager.getInstance(context);
        if (instance != null) {
            int[] appWidgetIds = instance.getAppWidgetIds(new ComponentName(context, VLCAppWidgetProviderWhite.class));
            Intrinsics.checkNotNullExpressionValue(appWidgetIds, "getAppWidgetIds(...)");
            int i = 0;
            if (!(appWidgetIds.length == 0)) {
                i = 1;
            } else {
                int[] appWidgetIds2 = instance.getAppWidgetIds(new ComponentName(context, VLCAppWidgetProviderBlack.class));
                Intrinsics.checkNotNullExpressionValue(appWidgetIds2, "getAppWidgetIds(...)");
                if (!(appWidgetIds2.length == 0)) {
                    i = 2;
                } else {
                    int[] appWidgetIds3 = instance.getAppWidgetIds(new ComponentName(context, MiniPlayerAppWidgetProvider.class));
                    Intrinsics.checkNotNullExpressionValue(appWidgetIds3, "getAppWidgetIds(...)");
                    if (!(appWidgetIds3.length == 0)) {
                        i = 3;
                    }
                }
            }
            this.widget = i;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0003, code lost:
        r2 = r10.getExtras();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int onStartCommand(android.content.Intent r10, int r11, int r12) {
        /*
            r9 = this;
            r1 = 0
            if (r10 == 0) goto L_0x0010
            android.os.Bundle r2 = r10.getExtras()
            if (r2 == 0) goto L_0x0010
            java.lang.String r3 = "foreground"
            boolean r2 = r2.getBoolean(r3, r1)
            goto L_0x0011
        L_0x0010:
            r2 = 0
        L_0x0011:
            r9.forceForeground(r2)
            androidx.lifecycle.ServiceLifecycleDispatcher r2 = r9.dispatcher
            r2.onServicePreSuperOnStart()
            r9.setupScope()
            r2 = 0
            if (r10 == 0) goto L_0x0024
            java.lang.String r3 = r10.getAction()
            goto L_0x0025
        L_0x0024:
            r3 = r2
        L_0x0025:
            java.lang.String r4 = "android.intent.action.MEDIA_BUTTON"
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r4)
            if (r4 == 0) goto L_0x0046
            org.videolan.resources.AndroidDevices r1 = org.videolan.resources.AndroidDevices.INSTANCE
            boolean r1 = r1.getHasTsp()
            if (r1 != 0) goto L_0x003d
            org.videolan.resources.AndroidDevices r1 = org.videolan.resources.AndroidDevices.INSTANCE
            boolean r1 = r1.getHasPlayServices()
            if (r1 == 0) goto L_0x0140
        L_0x003d:
            android.support.v4.media.session.MediaSessionCompat r1 = r9.getMediaSession$vlc_android_release()
            androidx.media.session.MediaButtonReceiver.handleIntent(r1, r10)
            goto L_0x0140
        L_0x0046:
            java.lang.String r4 = org.videolan.resources.Constants.ACTION_REMOTE_PLAYPAUSE
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r4)
            if (r4 == 0) goto L_0x004f
            goto L_0x0060
        L_0x004f:
            java.lang.String r4 = org.videolan.resources.Constants.ACTION_REMOTE_PLAY
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r4)
            if (r4 == 0) goto L_0x0058
            goto L_0x0060
        L_0x0058:
            java.lang.String r4 = org.videolan.resources.Constants.ACTION_REMOTE_LAST_PLAYLIST
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r4)
            if (r4 == 0) goto L_0x007f
        L_0x0060:
            org.videolan.vlc.media.PlaylistManager r0 = r9.getPlaylistManager()
            boolean r0 = r0.hasCurrentMedia()
            if (r0 == 0) goto L_0x007a
            boolean r0 = r9.isPlaying()
            if (r0 == 0) goto L_0x0075
            r9.pause()
            goto L_0x0140
        L_0x0075:
            r9.play()
            goto L_0x0140
        L_0x007a:
            r9.loadLastAudioPlaylist()
            goto L_0x0140
        L_0x007f:
            java.lang.String r4 = org.videolan.resources.Constants.ACTION_REMOTE_BACKWARD
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r4)
            if (r4 == 0) goto L_0x008c
            r9.previous(r1)
            goto L_0x0140
        L_0x008c:
            java.lang.String r4 = org.videolan.resources.Constants.ACTION_REMOTE_FORWARD
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r4)
            if (r4 == 0) goto L_0x009a
            r0 = 1
            next$default(r9, r1, r0, r2)
            goto L_0x0140
        L_0x009a:
            java.lang.String r4 = org.videolan.resources.Constants.ACTION_REMOTE_STOP
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r4)
            if (r4 == 0) goto L_0x00a8
            r0 = 3
            stop$default(r9, r1, r1, r0, r2)
            goto L_0x0140
        L_0x00a8:
            java.lang.String r1 = org.videolan.resources.Constants.ACTION_REMOTE_SEEK_FORWARD
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r1)
            r4 = 1000(0x3e8, double:4.94E-321)
            r6 = 0
            if (r1 == 0) goto L_0x00ce
            long r1 = r9.getTime()
            java.lang.String r3 = org.videolan.resources.Constants.EXTRA_SEEK_DELAY
            long r6 = r10.getLongExtra(r3, r6)
            long r6 = r6 * r4
            long r1 = r1 + r6
            r7 = 14
            r8 = 0
            r3 = 0
            r5 = 0
            r6 = 0
            r0 = r9
            seek$default(r0, r1, r3, r5, r6, r7, r8)
            goto L_0x0140
        L_0x00ce:
            java.lang.String r1 = org.videolan.resources.Constants.ACTION_REMOTE_SEEK_BACKWARD
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r1)
            if (r1 == 0) goto L_0x00ef
            long r1 = r9.getTime()
            java.lang.String r3 = org.videolan.resources.Constants.EXTRA_SEEK_DELAY
            long r6 = r10.getLongExtra(r3, r6)
            long r6 = r6 * r4
            long r1 = r1 - r6
            r7 = 14
            r8 = 0
            r3 = 0
            r5 = 0
            r6 = 0
            r0 = r9
            seek$default(r0, r1, r3, r5, r6, r7, r8)
            goto L_0x0140
        L_0x00ef:
            java.lang.String r1 = org.videolan.resources.Constants.ACTION_PLAY_FROM_SEARCH
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r1)
            if (r1 == 0) goto L_0x011c
            android.support.v4.media.session.MediaSessionCompat r1 = r9.mediaSession
            if (r1 != 0) goto L_0x00fe
            r9.initMediaSession()
        L_0x00fe:
            java.lang.String r1 = org.videolan.resources.Constants.EXTRA_SEARCH_BUNDLE
            android.os.Bundle r0 = r10.getBundleExtra(r1)
            if (r0 == 0) goto L_0x0140
            android.support.v4.media.session.MediaSessionCompat r1 = r9.getMediaSession$vlc_android_release()
            android.support.v4.media.session.MediaControllerCompat r1 = r1.getController()
            android.support.v4.media.session.MediaControllerCompat$TransportControls r1 = r1.getTransportControls()
            java.lang.String r2 = "query"
            java.lang.String r2 = r0.getString(r2)
            r1.playFromSearch(r2, r0)
            goto L_0x0140
        L_0x011c:
            java.lang.String r0 = org.videolan.resources.Constants.ACTION_REMOTE_SWITCH_VIDEO
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r0)
            if (r0 == 0) goto L_0x0140
            r9.removePopup()
            boolean r0 = r9.hasMedia()
            if (r0 == 0) goto L_0x0140
            org.videolan.medialibrary.interfaces.media.MediaWrapper r0 = r9.getCurrentMediaWrapper()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            r1 = 8
            r0.removeFlags(r1)
            org.videolan.vlc.media.PlaylistManager r0 = r9.getPlaylistManager()
            r0.switchToVideo()
        L_0x0140:
            r0 = 2
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.PlaybackService.onStartCommand(android.content.Intent, int, int):int");
    }

    public void onTaskRemoved(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "rootIntent");
        if (getSettings$vlc_android_release().getBoolean("audio_task_removed", false)) {
            stop$default(this, false, false, 3, (Object) null);
        }
    }

    public void onDestroy() {
        serviceFlow.setValue(null);
        this.dispatcher.onServicePreSuperOnDestroy();
        PlaylistManager.Companion.getPlayingState().setValue(false);
        super.onDestroy();
        MediaBrowserCallback mediaBrowserCallback = this.browserCallback;
        if (mediaBrowserCallback == null) {
            Intrinsics.throwUninitializedPropertyAccessException("browserCallback");
            mediaBrowserCallback = null;
        }
        mediaBrowserCallback.removeCallbacks();
        if (!getSettings$vlc_android_release().getBoolean(SettingsKt.AUDIO_RESUME_PLAYBACK, true)) {
            Object systemService = getSystemService("notification");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
            ((NotificationManager) systemService).cancel(3);
        }
        if (this.mediaSession != null) {
            getMediaSession$vlc_android_release().release();
        }
        stop$default(this, true, false, 2, (Object) null);
        unregisterReceiver(this.receiver);
        getPlaylistManager().onServiceDestroyed();
    }

    public IBinder onBind(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        this.dispatcher.onServicePreSuperOnBind();
        return Intrinsics.areEqual((Object) MediaBrowserServiceCompat.SERVICE_INTERFACE, (Object) intent.getAction()) ? super.onBind(intent) : this.binder;
    }

    public final IVLCVout getVout() {
        return getPlaylistManager().getPlayer().getVout();
    }

    static /* synthetic */ void forceForeground$default(PlaybackService playbackService, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        playbackService.forceForeground(z);
    }

    private final void forceForeground(boolean z) {
        if (AndroidUtil.isOOrLater && !this.isForeground) {
            Context applicationContext = getApplicationContext();
            boolean z2 = PlayerController.Companion.getPlaybackState() == 1 || PlayerController.Companion.getPlaybackState() == 0;
            if (!z2 || z) {
                Notification notification2 = this.notification;
                if (notification2 == null || z2) {
                    PendingIntent sessionPendingIntent = this.playlistManager != null ? getSessionPendingIntent() : null;
                    NotificationHelper notificationHelper = NotificationHelper.INSTANCE;
                    String string = applicationContext.getResources().getString(R.string.loading);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    notification2 = notificationHelper.createPlaybackNotification(applicationContext, false, string, "", "", (Bitmap) null, false, true, true, getSpeed(), isPodcastMode(), false, this.enabledActions, (MediaSessionCompat.Token) null, sessionPendingIntent);
                } else if (notification2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("notification");
                    notification2 = null;
                }
                ExtensionsKt.startForegroundCompat(this, 3, notification2, 2);
                this.isForeground = true;
                if (z2) {
                    Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new PlaybackService$forceForeground$1(this, (Continuation<? super PlaybackService$forceForeground$1>) null), 3, (Object) null);
                }
            }
        }
    }

    private final void sendStartSessionIdIntent() {
        int audiotrackSessionId = VLCOptions.INSTANCE.getAudiotrackSessionId();
        if (audiotrackSessionId != 0) {
            Intent intent = new Intent("android.media.action.OPEN_AUDIO_EFFECT_CONTROL_SESSION");
            intent.putExtra("android.media.extra.AUDIO_SESSION", audiotrackSessionId);
            intent.putExtra("android.media.extra.PACKAGE_NAME", getPackageName());
            if (isVideoPlaying()) {
                intent.putExtra("android.media.extra.CONTENT_TYPE", 1);
            } else {
                intent.putExtra("android.media.extra.CONTENT_TYPE", 0);
            }
            sendBroadcast(intent);
        }
    }

    private final void sendStopSessionIdIntent() {
        int audiotrackSessionId = VLCOptions.INSTANCE.getAudiotrackSessionId();
        if (audiotrackSessionId != 0) {
            Intent intent = new Intent("android.media.action.CLOSE_AUDIO_EFFECT_CONTROL_SESSION");
            intent.putExtra("android.media.extra.AUDIO_SESSION", audiotrackSessionId);
            intent.putExtra("android.media.extra.PACKAGE_NAME", getPackageName());
            sendBroadcast(intent);
        }
    }

    public final void setBenchmark() {
        getPlaylistManager().setBenchmark(true);
    }

    public final void setHardware() {
        getPlaylistManager().setHardware(true);
    }

    public static /* synthetic */ void setTime$default(PlaybackService playbackService, long j, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        playbackService.setTime(j, z);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0017, code lost:
        if (getSettings$vlc_android_release().getBoolean("always_fast_seek", false) != false) goto L_0x0019;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setTime(long r3, boolean r5) {
        /*
            r2 = this;
            if (r5 != 0) goto L_0x0019
            org.videolan.vlc.media.PlaylistManager r5 = r2.getPlaylistManager()
            boolean r5 = r5.isBenchmark()
            r0 = 0
            if (r5 != 0) goto L_0x001a
            android.content.SharedPreferences r5 = r2.getSettings$vlc_android_release()
            java.lang.String r1 = "always_fast_seek"
            boolean r5 = r5.getBoolean(r1, r0)
            if (r5 == 0) goto L_0x001a
        L_0x0019:
            r0 = 1
        L_0x001a:
            org.videolan.vlc.media.PlaylistManager r5 = r2.getPlaylistManager()
            org.videolan.vlc.media.PlayerController r5 = r5.getPlayer()
            r5.setTime(r3, r0)
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            r2.publishState(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.PlaybackService.setTime(long, boolean):void");
    }

    public final long getTime() {
        return getPlaylistManager().getPlayer().getCurrentTime();
    }

    public final void onMediaPlayerEvent(MediaPlayer.Event event) {
        Intrinsics.checkNotNullParameter(event, NotificationCompat.CATEGORY_EVENT);
        this.mediaPlayerListener.onEvent(event);
    }

    public final void onPlaybackStopped(boolean z) {
        if (!z) {
            hideNotification(this.isForeground);
        }
        removePopup();
        PowerManager.WakeLock wakeLock2 = this.wakeLock;
        if (wakeLock2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("wakeLock");
            wakeLock2 = null;
        }
        if (wakeLock2.isHeld()) {
            PowerManager.WakeLock wakeLock3 = this.wakeLock;
            if (wakeLock3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("wakeLock");
                wakeLock3 = null;
            }
            wakeLock3.release();
        }
        getAudioFocusHelper().changeAudioFocus$vlc_android_release(false);
        publishState$default(this, (Long) null, 1, (Object) null);
        executeUpdate$default(this, false, 1, (Object) null);
    }

    /* access modifiers changed from: private */
    public final boolean canSwitchToVideo() {
        return getPlaylistManager().getPlayer().canSwitchToVideo();
    }

    /* renamed from: onMediaEvent-JP2dKIU  reason: not valid java name */
    public final Object m2456onMediaEventJP2dKIU(IMedia.Event event) {
        Intrinsics.checkNotNullParameter(event, NotificationCompat.CATEGORY_EVENT);
        SendChannel<? super CbAction> sendChannel = this.cbActor;
        if (sendChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cbActor");
            sendChannel = null;
        }
        return sendChannel.m1139trySendJP2dKIU(new CbMediaEvent(event));
    }

    public static /* synthetic */ void executeUpdate$default(PlaybackService playbackService, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        playbackService.executeUpdate(z);
    }

    public final void executeUpdate(boolean z) {
        SendChannel<? super CbAction> sendChannel = this.cbActor;
        if (sendChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cbActor");
            sendChannel = null;
        }
        sendChannel.m1139trySendJP2dKIU(CbUpdate.INSTANCE);
        updateWidget();
        updateMetadata();
        broadcastMetadata();
        if (z) {
            publishState$default(this, (Long) null, 1, (Object) null);
        }
    }

    public final boolean showNotification() {
        this.notificationShowing = true;
        SendChannel<? super CbAction> sendChannel = this.cbActor;
        if (sendChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cbActor");
            sendChannel = null;
        }
        return ChannelResult.m2346isSuccessimpl(sendChannel.m1139trySendJP2dKIU(ShowNotification.INSTANCE));
    }

    /* access modifiers changed from: private */
    public final void showNotificationInternal() {
        if (!AndroidDevices.INSTANCE.isAndroidTv() && Settings.INSTANCE.getShowTvUi()) {
            return;
        }
        if (isPlayingPopup() || (!hasRenderer() && isVideoPlaying())) {
            hideNotificationInternal(true);
            return;
        }
        MediaWrapper currentMedia = getPlaylistManager().getCurrentMedia();
        if (currentMedia != null) {
            boolean z = getSettings$vlc_android_release().getBoolean(SettingsKt.LOCKSCREEN_COVER, true);
            boolean z2 = getSettings$vlc_android_release().getBoolean(SettingsKt.SHOW_SEEK_IN_COMPACT_NOTIFICATION, false);
            boolean isPlaying = isPlaying();
            MediaSessionCompat.Token sessionToken = getMediaSession$vlc_android_release().getSessionToken();
            Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getDefault(), (CoroutineStart) null, new PlaybackService$showNotificationInternal$1(this, getMediaSession$vlc_android_release().getController().getMetadata(), currentMedia, z, this, isPlaying, z2, sessionToken, (Continuation<? super PlaybackService$showNotificationInternal$1>) null), 2, (Object) null);
        }
    }

    private final boolean currentMediaHasFlag(int i) {
        MediaWrapper currentMedia = getPlaylistManager().getCurrentMedia();
        return currentMedia != null && currentMedia.hasFlag(i);
    }

    /* access modifiers changed from: private */
    public final boolean hideNotification(boolean z) {
        this.notificationShowing = false;
        SendChannel<? super CbAction> sendChannel = this.cbActor;
        if (sendChannel == null) {
            return false;
        }
        if (sendChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cbActor");
            sendChannel = null;
        }
        return ChannelResult.m2346isSuccessimpl(sendChannel.m1139trySendJP2dKIU(new HideNotification(z)));
    }

    /* access modifiers changed from: private */
    public final void hideNotificationInternal(boolean z) {
        if (!isPlayingPopup() && this.isForeground) {
            ServiceCompat.stopForeground(this, z ? 1 : 2);
            this.isForeground = false;
        }
        NotificationManagerCompat.from(this).cancel(3);
    }

    public final void onNewPlayback() {
        getMediaSession$vlc_android_release().setSessionActivity(getSessionPendingIntent());
    }

    public final void onPlaylistLoaded() {
        notifyTrackChanged();
        updateMediaQueue();
    }

    public final void pause() {
        getPlaylistManager().pause();
    }

    public final void play() {
        getPlaylistManager().play();
    }

    public static /* synthetic */ void stop$default(PlaybackService playbackService, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = false;
        }
        playbackService.stop(z, z2);
    }

    public final void stop(boolean z, boolean z2) {
        getPlaylistManager().stop(z, z2);
    }

    /* access modifiers changed from: private */
    public final void initMediaSession() {
        Context context = this;
        PendingIntent buildMediaButtonPendingIntent = MediaButtonReceiver.buildMediaButtonPendingIntent(context, 512);
        ComponentName componentName = new ComponentName(context, MediaButtonReceiver.class);
        PlaybackStateCompat build = new PlaybackStateCompat.Builder().setActions(this.enabledActions.getCapabilities()).setState(0, 0, 0.0f).build();
        MediaSessionCompat mediaSessionCompat = new MediaSessionCompat(context, "VLC", componentName, buildMediaButtonPendingIntent);
        mediaSessionCompat.setFlags(3);
        mediaSessionCompat.setCallback(new MediaSessionCallback(this));
        mediaSessionCompat.setPlaybackState(build);
        setMediaSession$vlc_android_release(mediaSessionCompat);
        try {
            getMediaSession$vlc_android_release().setActive(true);
        } catch (NullPointerException unused) {
            getMediaSession$vlc_android_release().setActive(false);
            getMediaSession$vlc_android_release().setFlags(2);
            getMediaSession$vlc_android_release().setActive(true);
        }
        setSessionToken(getMediaSession$vlc_android_release().getSessionToken());
    }

    private final void updateMetadata() {
        SendChannel<? super CbAction> sendChannel = this.cbActor;
        if (sendChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cbActor");
            sendChannel = null;
        }
        sendChannel.m1139trySendJP2dKIU(UpdateMeta.INSTANCE);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object updateMetadataInternal(kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            r13 = this;
            boolean r0 = r14 instanceof org.videolan.vlc.PlaybackService$updateMetadataInternal$1
            if (r0 == 0) goto L_0x0014
            r0 = r14
            org.videolan.vlc.PlaybackService$updateMetadataInternal$1 r0 = (org.videolan.vlc.PlaybackService$updateMetadataInternal$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.PlaybackService$updateMetadataInternal$1 r0 = new org.videolan.vlc.PlaybackService$updateMetadataInternal$1
            r0.<init>(r13, r14)
        L_0x0019:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r0 = r0.L$0
            org.videolan.vlc.PlaybackService r0 = (org.videolan.vlc.PlaybackService) r0
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x0084
        L_0x002e:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r0)
            throw r14
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r14)
            org.videolan.vlc.media.PlaylistManager r14 = r13.getPlaylistManager()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r6 = r14.getCurrentMedia()
            if (r6 != 0) goto L_0x0046
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            return r14
        L_0x0046:
            android.support.v4.media.session.MediaSessionCompat r14 = r13.mediaSession
            if (r14 != 0) goto L_0x004d
            r13.initMediaSession()
        L_0x004d:
            long r10 = r13.getLength()
            r13.lastLength = r10
            int r14 = r13.lastChaptersCount
            if (r14 <= 0) goto L_0x005c
            java.lang.String r14 = r13.getCurrentChapter()
            goto L_0x005d
        L_0x005c:
            r14 = 0
        L_0x005d:
            r7 = r14
            java.util.ArrayDeque<java.lang.String> r14 = r13.subtitleMessage
            java.lang.Object r14 = r14.poll()
            r8 = r14
            java.lang.String r8 = (java.lang.String) r8
            kotlinx.coroutines.CoroutineDispatcher r14 = kotlinx.coroutines.Dispatchers.getDefault()
            kotlin.coroutines.CoroutineContext r14 = (kotlin.coroutines.CoroutineContext) r14
            org.videolan.vlc.PlaybackService$updateMetadataInternal$bob$1 r2 = new org.videolan.vlc.PlaybackService$updateMetadataInternal$bob$1
            r12 = 0
            r4 = r2
            r5 = r13
            r9 = r13
            r4.<init>(r5, r6, r7, r8, r9, r10, r12)
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            r0.L$0 = r13
            r0.label = r3
            java.lang.Object r14 = kotlinx.coroutines.BuildersKt.withContext(r14, r2, r0)
            if (r14 != r1) goto L_0x0083
            return r1
        L_0x0083:
            r0 = r13
        L_0x0084:
            android.support.v4.media.MediaMetadataCompat r14 = (android.support.v4.media.MediaMetadataCompat) r14
            android.support.v4.media.session.MediaSessionCompat r1 = r0.mediaSession
            if (r1 == 0) goto L_0x0091
            android.support.v4.media.session.MediaSessionCompat r0 = r0.getMediaSession$vlc_android_release()
            r0.setMetadata(r14)
        L_0x0091:
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.PlaybackService.updateMetadataInternal(kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ void publishState$default(PlaybackService playbackService, Long l, int i, Object obj) {
        if ((i & 1) != 0) {
            l = null;
        }
        playbackService.publishState(l);
    }

    private final void publishState(Long l) {
        if (this.mediaSession != null) {
            if (AndroidDevices.INSTANCE.isAndroidTv()) {
                getScheduler().cancelAction(END_MEDIASESSION);
            }
            PlaybackStateCompat.Builder builder = new PlaybackStateCompat.Builder();
            FlagSet<PlaybackAction> createActivePlaybackActions = PlaybackAction.Companion.createActivePlaybackActions();
            boolean hasCurrentMedia = getPlaylistManager().hasCurrentMedia();
            long longValue = l != null ? l.longValue() : getTime();
            int playbackState = PlayerController.Companion.getPlaybackState();
            float f = 0.0f;
            if (playbackState == 2) {
                createActivePlaybackActions.addAll((Enum[]) new PlaybackAction[]{PlaybackAction.ACTION_PLAY, PlaybackAction.ACTION_STOP});
            } else if (playbackState != 3) {
                createActivePlaybackActions.add(PlaybackAction.ACTION_PLAY);
                MediaWrapper currentMedia = (!AndroidDevices.INSTANCE.isAndroidTv() || AndroidUtil.isOOrLater || !hasCurrentMedia) ? null : getPlaylistManager().getCurrentMedia();
                if (currentMedia != null) {
                    long length = currentMedia.getLength();
                    long time = currentMedia.getTime();
                    if ((length <= 0 ? 0.0f : ((float) time) / ((float) length)) < 0.95f) {
                        LifecycleAwareScheduler.scheduleAction$default(getScheduler(), END_MEDIASESSION, 900000, (Bundle) null, 4, (Object) null);
                        longValue = time;
                        playbackState = 2;
                    } else {
                        longValue = time;
                    }
                }
            } else {
                createActivePlaybackActions.addAll((Enum[]) new PlaybackAction[]{PlaybackAction.ACTION_PAUSE, PlaybackAction.ACTION_STOP});
            }
            if (!isPaused()) {
                f = getPlaylistManager().getPlayer().getRate();
            }
            builder.setState(playbackState, longValue, f);
            builder.setActiveQueueItemId((long) getPlaylistManager().getCurrentIndex());
            int intValue = PlaylistManager.Companion.getRepeating().getValue().intValue();
            boolean isPodcastMode = isPodcastMode();
            if (intValue != 0 || hasNext()) {
                createActivePlaybackActions.add(PlaybackAction.ACTION_SKIP_TO_NEXT);
            }
            if (intValue != 0 || hasPrevious() || (isSeekable() && !isPodcastMode)) {
                createActivePlaybackActions.add(PlaybackAction.ACTION_SKIP_TO_PREVIOUS);
            }
            if (isPodcastMode) {
                addCustomSeekActions$default(this, builder, false, 2, (Object) null);
                addCustomSpeedActions$default(this, builder, false, 2, (Object) null);
                builder.addCustomAction(Constants.CUSTOM_ACTION_BOOKMARK, getString(R.string.add_bookmark), R.drawable.ic_bookmark_add);
            } else if (Build.VERSION.SDK_INT < 33) {
                manageAutoActions(createActivePlaybackActions, builder, intValue);
            } else if (!isCarMode()) {
                addCustomSeekActions$default(this, builder, false, 2, (Object) null);
            } else {
                manageAutoActions(createActivePlaybackActions, builder, intValue);
            }
            builder.setActions(createActivePlaybackActions.getCapabilities());
            getMediaSession$vlc_android_release().setRepeatMode(intValue);
            getMediaSession$vlc_android_release().setShuffleMode(isShuffling() ? 1 : 0);
            MediaSessionCompat mediaSession$vlc_android_release = getMediaSession$vlc_android_release();
            Bundle bundle = new Bundle();
            bundle.putBoolean(Constants.WEARABLE_RESERVE_SLOT_SKIP_TO_NEXT, !isPodcastMode);
            bundle.putBoolean(Constants.WEARABLE_RESERVE_SLOT_SKIP_TO_PREV, !isPodcastMode);
            bundle.putBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_NEXT", !isPodcastMode);
            bundle.putBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_PREVIOUS", !isPodcastMode);
            mediaSession$vlc_android_release.setExtras(bundle);
            MediaWrapper currentMediaWrapper = getCurrentMediaWrapper();
            if (currentMediaWrapper != null) {
                if (currentMediaWrapper.getId() == 0) {
                    currentMediaWrapper = null;
                }
                if (currentMediaWrapper != null) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putString(MediaConstants.PLAYBACK_STATE_EXTRAS_KEY_MEDIA_ID, MediaSessionBrowser.Companion.generateMediaId(currentMediaWrapper));
                    builder.setExtras(bundle2);
                }
            }
            boolean z = playbackState != 1;
            boolean z2 = getMediaSession$vlc_android_release().isActive() != z;
            updateMediaQueueSlidingWindow$default(this, false, 1, (Object) null);
            getMediaSession$vlc_android_release().setPlaybackState(builder.build());
            this.enabledActions = createActivePlaybackActions;
            getMediaSession$vlc_android_release().setActive(z);
            getMediaSession$vlc_android_release().setQueueTitle(getString(R.string.music_now_playing));
            if (!z2) {
                return;
            }
            if (z) {
                sendStartSessionIdIntent();
            } else {
                sendStopSessionIdIntent();
            }
        }
    }

    private final void manageAutoActions(FlagSet<PlaybackAction> flagSet, PlaybackStateCompat.Builder builder, int i) {
        int i2;
        int i3;
        if (getPlaylistManager().canRepeat()) {
            flagSet.add(PlaybackAction.ACTION_SET_REPEAT_MODE);
        }
        if (getPlaylistManager().canShuffle()) {
            flagSet.add(PlaybackAction.ACTION_SET_SHUFFLE_MODE);
        }
        if (isShuffling()) {
            i2 = R.drawable.ic_auto_shuffle_enabled;
        } else {
            i2 = R.drawable.ic_auto_shuffle_disabled;
        }
        builder.addCustomAction(Constants.CUSTOM_ACTION_SHUFFLE, getString(R.string.shuffle_title), i2);
        if (i == 1) {
            i3 = R.drawable.ic_auto_repeat_one_pressed;
        } else if (i != 2) {
            i3 = R.drawable.ic_auto_repeat_normal;
        } else {
            i3 = R.drawable.ic_auto_repeat_pressed;
        }
        builder.addCustomAction(Constants.CUSTOM_ACTION_REPEAT, getString(R.string.repeat_title), i3);
        addCustomSpeedActions(builder, getSettings$vlc_android_release().getBoolean(SettingsKt.ENABLE_ANDROID_AUTO_SPEED_BUTTONS, false));
        addCustomSeekActions(builder, getSettings$vlc_android_release().getBoolean(SettingsKt.ENABLE_ANDROID_AUTO_SEEK_BUTTONS, false));
    }

    static /* synthetic */ void addCustomSeekActions$default(PlaybackService playbackService, PlaybackStateCompat.Builder builder, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        playbackService.addCustomSeekActions(builder, z);
    }

    private final void addCustomSeekActions(PlaybackStateCompat.Builder builder, boolean z) {
        if (z) {
            Context applicationContext = getApplicationContext();
            DrawableCache drawableCache = DrawableCache.INSTANCE;
            PlaybackStateCompat.CustomAction.Builder builder2 = new PlaybackStateCompat.CustomAction.Builder(Constants.CUSTOM_ACTION_REWIND, getString(R.string.playback_rewind), drawableCache.getDrawableFromMemCache(applicationContext, "ic_auto_rewind_" + Settings.INSTANCE.getAudioJumpDelay(), R.drawable.ic_auto_rewind));
            Bundle bundle = new Bundle();
            bundle.putBoolean(Constants.WEARABLE_SHOW_CUSTOM_ACTION, true);
            Unit unit = Unit.INSTANCE;
            builder.addCustomAction(builder2.setExtras(bundle).build());
            DrawableCache drawableCache2 = DrawableCache.INSTANCE;
            PlaybackStateCompat.CustomAction.Builder builder3 = new PlaybackStateCompat.CustomAction.Builder(Constants.CUSTOM_ACTION_FAST_FORWARD, getString(R.string.playback_forward), drawableCache2.getDrawableFromMemCache(applicationContext, "ic_auto_forward_" + Settings.INSTANCE.getAudioJumpDelay(), R.drawable.ic_auto_forward));
            Bundle bundle2 = new Bundle();
            bundle2.putBoolean(Constants.WEARABLE_SHOW_CUSTOM_ACTION, true);
            Unit unit2 = Unit.INSTANCE;
            builder.addCustomAction(builder3.setExtras(bundle2).build());
        }
    }

    static /* synthetic */ void addCustomSpeedActions$default(PlaybackService playbackService, PlaybackStateCompat.Builder builder, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        playbackService.addCustomSpeedActions(builder, z);
    }

    private final void addCustomSpeedActions(PlaybackStateCompat.Builder builder, boolean z) {
        Object obj;
        int i;
        if (getSpeed() != 1.0f || z) {
            HashMap hashMapOf = MapsKt.hashMapOf(TuplesKt.to(Float.valueOf(0.5f), Integer.valueOf(R.drawable.ic_auto_speed_0_50)), TuplesKt.to(Float.valueOf(0.8f), Integer.valueOf(R.drawable.ic_auto_speed_0_80)), TuplesKt.to(Float.valueOf(1.0f), Integer.valueOf(R.drawable.ic_auto_speed_1_00)), TuplesKt.to(Float.valueOf(1.1f), Integer.valueOf(R.drawable.ic_auto_speed_1_10)), TuplesKt.to(Float.valueOf(1.2f), Integer.valueOf(R.drawable.ic_auto_speed_1_20)), TuplesKt.to(Float.valueOf(1.5f), Integer.valueOf(R.drawable.ic_auto_speed_1_50)), TuplesKt.to(Float.valueOf(2.0f), Integer.valueOf(R.drawable.ic_auto_speed_2_00)));
            Map map = hashMapOf;
            Set keySet = hashMapOf.keySet();
            Intrinsics.checkNotNullExpressionValue(keySet, "<get-keys>(...)");
            Iterator it = keySet.iterator();
            if (!it.hasNext()) {
                obj = null;
            } else {
                Object next = it.next();
                if (!it.hasNext()) {
                    obj = next;
                } else {
                    Float f = (Float) next;
                    float speed = getSpeed();
                    Intrinsics.checkNotNull(f);
                    float abs = Math.abs(speed - f.floatValue());
                    do {
                        Object next2 = it.next();
                        Float f2 = (Float) next2;
                        float speed2 = getSpeed();
                        Intrinsics.checkNotNull(f2);
                        float abs2 = Math.abs(speed2 - f2.floatValue());
                        if (Float.compare(abs, abs2) > 0) {
                            next = next2;
                            abs = abs2;
                        }
                    } while (it.hasNext());
                }
                obj = next;
            }
            Integer num = (Integer) map.get(obj);
            if (num != null) {
                i = num.intValue();
            } else {
                i = R.drawable.ic_auto_speed;
            }
            builder.addCustomAction(Constants.CUSTOM_ACTION_SPEED, getString(R.string.playback_speed), i);
        }
    }

    public final void notifyTrackChanged() {
        updateMetadata();
        updateWidget();
        broadcastMetadata();
    }

    public final void onMediaListChanged() {
        executeUpdate$default(this, false, 1, (Object) null);
        updateMediaQueue();
    }

    public static /* synthetic */ void next$default(PlaybackService playbackService, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        playbackService.next(z);
    }

    public final void next(boolean z) {
        getPlaylistManager().next(z);
    }

    public final void previous(boolean z) {
        getPlaylistManager().previous(z);
    }

    public final void shuffle() {
        getPlaylistManager().shuffle();
        MediaBrowserCallback mediaBrowserCallback = null;
        publishState$default(this, (Long) null, 1, (Object) null);
        MediaBrowserCallback mediaBrowserCallback2 = this.browserCallback;
        if (mediaBrowserCallback2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("browserCallback");
        } else {
            mediaBrowserCallback = mediaBrowserCallback2;
        }
        mediaBrowserCallback.onShuffleChanged();
    }

    /* access modifiers changed from: private */
    public final void updateWidget() {
        if (this.widget != 0 && !isVideoPlaying()) {
            updateWidgetState();
        }
    }

    /* access modifiers changed from: private */
    public final void sendWidgetBroadcast(Intent intent) {
        Context context = this;
        int i = this.widget;
        intent.setComponent(new ComponentName(context, i == 1 ? VLCAppWidgetProviderWhite.class : i == 3 ? MiniPlayerAppWidgetProvider.class : VLCAppWidgetProviderBlack.class));
        sendBroadcast(intent);
    }

    public final void updateWidgetState() {
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getDefault(), (CoroutineStart) null, new PlaybackService$updateWidgetState$1(new Intent[]{new Intent(VLCAppWidgetProvider.Companion.getACTION_WIDGET_UPDATE()), new Intent(MiniPlayerAppWidgetProvider.Companion.getACTION_WIDGET_UPDATE())}, this, (Continuation<? super PlaybackService$updateWidgetState$1>) null), 2, (Object) null);
    }

    private final void updateWidgetPosition(float f) {
        if (getPlaylistManager().getCurrentMedia() != null && this.widget != 0 && !isVideoPlaying()) {
            long currentTimeMillis = System.currentTimeMillis();
            if (getPlaylistManager().hasCurrentMedia() && currentTimeMillis - this.widgetPositionTimestamp >= 500) {
                this.widgetPositionTimestamp = currentTimeMillis;
                Intent putExtra = new Intent(MiniPlayerAppWidgetProvider.Companion.getACTION_WIDGET_UPDATE_POSITION()).putExtra(Constants.PLAY_EXTRA_START_TIME, f);
                Intrinsics.checkNotNullExpressionValue(putExtra, "putExtra(...)");
                sendWidgetBroadcast(putExtra);
            }
        }
    }

    private final void broadcastMetadata() {
        MediaWrapper currentMedia = getPlaylistManager().getCurrentMedia();
        if (!isVideoPlaying()) {
            LifecycleOwner lifecycleOwner = this;
            if (CoroutineScopeKt.isActive(LifecycleOwnerKt.getLifecycleScope(lifecycleOwner))) {
                Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(lifecycleOwner), Dispatchers.getDefault(), (CoroutineStart) null, new PlaybackService$broadcastMetadata$1(this, currentMedia, (Continuation<? super PlaybackService$broadcastMetadata$1>) null), 2, (Object) null);
            }
        }
    }

    private final void loadLastAudioPlaylist() {
        if (!AndroidDevices.INSTANCE.isAndroidTv()) {
            loadLastPlaylist(Intrinsics.areEqual((Object) getSettings$vlc_android_release().getString(SettingsKt.KEY_VIDEO_APP_SWITCH, Constants.GROUP_VIDEOS_FOLDER), (Object) DiskLruCache.VERSION_1) ? 2 : 0);
        }
    }

    public final void loadLastPlaylist(int i) {
        forceForeground(true);
        if (!getPlaylistManager().loadLastPlaylist(i)) {
            Toast.makeText(this, getString(R.string.resume_playback_error), 1).show();
            stopService(new Intent(getApplicationContext(), PlaybackService.class));
        }
    }

    public static /* synthetic */ void showToast$default(PlaybackService playbackService, String str, int i, boolean z, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = false;
        }
        playbackService.showToast(str, i, z);
    }

    public final void showToast(String str, int i, boolean z) {
        Intrinsics.checkNotNullParameter(str, "text");
        getScheduler().cancelAction(SHOW_TOAST);
        getScheduler().startAction(SHOW_TOAST, BundleKt.bundleOf(TuplesKt.to("text", str), TuplesKt.to(TypedValues.TransitionType.S_DURATION, Integer.valueOf(i)), TuplesKt.to("isError", Boolean.valueOf(z))));
    }

    public final boolean canShuffle() {
        return getPlaylistManager().canShuffle();
    }

    public final boolean hasMedia() {
        return PlaylistManager.Companion.hasMedia();
    }

    public final boolean hasPlaylist() {
        return getPlaylistManager().hasPlaylist();
    }

    /* renamed from: addCallback-JP2dKIU  reason: not valid java name */
    public final Object m2455addCallbackJP2dKIU(Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "cb");
        SendChannel<? super CbAction> sendChannel = this.cbActor;
        if (sendChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cbActor");
            sendChannel = null;
        }
        return sendChannel.m1139trySendJP2dKIU(new CbAdd(callback));
    }

    /* renamed from: removeCallback-JP2dKIU  reason: not valid java name */
    public final Object m2457removeCallbackJP2dKIU(Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "cb");
        SendChannel<? super CbAction> sendChannel = this.cbActor;
        if (sendChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cbActor");
            sendChannel = null;
        }
        return sendChannel.m1139trySendJP2dKIU(new CbRemove(callback));
    }

    private final void restartPlaylistManager() {
        getPlaylistManager().restart();
    }

    public final void restartMediaPlayer() {
        getPlaylistManager().getPlayer().restart();
    }

    public final Job saveMediaMeta() {
        return PlaylistManager.saveMediaMeta$default(getPlaylistManager(), false, 1, (Object) null);
    }

    public final boolean isValidIndex(int i) {
        return getPlaylistManager().isValidPosition(i);
    }

    private final void loadLocations(List<String> list, int i) {
        getPlaylistManager().loadLocations(list, i);
    }

    public final void loadUri(Uri uri) {
        Intrinsics.checkNotNull(uri);
        String uri2 = uri.toString();
        Intrinsics.checkNotNullExpressionValue(uri2, "toString(...)");
        loadLocation(uri2);
    }

    public final void loadLocation(String str) {
        Intrinsics.checkNotNullParameter(str, "mediaPath");
        loadLocations(CollectionsKt.listOf(str), 0);
    }

    public final void load(MediaWrapper[] mediaWrapperArr, int i) {
        if (mediaWrapperArr != null) {
            load((List<? extends MediaWrapper>) ArraysKt.toList((T[]) mediaWrapperArr), i);
        }
    }

    public final Job load(List<? extends MediaWrapper> list, int i) {
        Intrinsics.checkNotNullParameter(list, "mediaList");
        return BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new PlaybackService$load$2(this, list, i, (Continuation<? super PlaybackService$load$2>) null), 3, (Object) null);
    }

    private final Job updateMediaQueue() {
        return BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, CoroutineStart.UNDISPATCHED, new PlaybackService$updateMediaQueue$1(this, (Continuation<? super PlaybackService$updateMediaQueue$1>) null), 1, (Object) null);
    }

    /* access modifiers changed from: private */
    public final Job updateMediaQueueSlidingWindow(boolean z) {
        return BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, CoroutineStart.UNDISPATCHED, new PlaybackService$updateMediaQueueSlidingWindow$1(this, z, (Continuation<? super PlaybackService$updateMediaQueueSlidingWindow$1>) null), 1, (Object) null);
    }

    static /* synthetic */ Job updateMediaQueueSlidingWindow$default(PlaybackService playbackService, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return playbackService.updateMediaQueueSlidingWindow(z);
    }

    /* access modifiers changed from: private */
    public final Job buildQueue(List<? extends MediaWrapper> list, int i, int i2) {
        return BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, CoroutineStart.UNDISPATCHED, new PlaybackService$buildQueue$1(this, i2, i, list, (Continuation<? super PlaybackService$buildQueue$1>) null), 1, (Object) null);
    }

    static /* synthetic */ Job buildQueue$default(PlaybackService playbackService, List list, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = list.size();
        }
        return playbackService.buildQueue(list, i, i2);
    }

    public final void displayPlaybackError(int i) {
        if (this.mediaSession == null) {
            initMediaSession();
        }
        if (isPlaying()) {
            stop$default(this, false, false, 3, (Object) null);
        }
        getMediaSession$vlc_android_release().setPlaybackState(new PlaybackStateCompat.Builder().setState(7, 0, 0.0f).setErrorMessage(2, getString(i)).build());
    }

    public final void displayPlaybackMessage(int i, String... strArr) {
        Intrinsics.checkNotNullParameter(strArr, "formatArgs");
        this.subtitleMessage.push(getString(i, Arrays.copyOf(strArr, strArr.length)));
        updateMetadata();
    }

    public static /* synthetic */ Job load$default(PlaybackService playbackService, MediaWrapper mediaWrapper, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return playbackService.load(mediaWrapper, i);
    }

    public final Job load(MediaWrapper mediaWrapper, int i) {
        Intrinsics.checkNotNullParameter(mediaWrapper, "media");
        return load((List<? extends MediaWrapper>) CollectionsKt.listOf(mediaWrapper), i);
    }

    public static /* synthetic */ void playIndex$default(PlaybackService playbackService, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        playbackService.playIndex(i, i2);
    }

    public final void playIndex(int i, int i2) {
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, CoroutineStart.UNDISPATCHED, new PlaybackService$playIndex$1(this, i, i2, (Continuation<? super PlaybackService$playIndex$1>) null), 1, (Object) null);
    }

    public final void playIndexOrLoadLastPlaylist(int i) {
        if (hasMedia()) {
            playIndex$default(this, i, 0, 2, (Object) null);
            return;
        }
        SharedPreferences.Editor edit = getSettings$vlc_android_release().edit();
        edit.putLong(SettingsKt.POSITION_IN_SONG, 0);
        edit.putInt(SettingsKt.POSITION_IN_AUDIO_LIST, i);
        edit.apply();
        loadLastPlaylist(0);
    }

    public final void flush() {
        if (isSeekable()) {
            long time = getTime();
            if (time > 0) {
                seek$default(this, time, 0.0d, false, false, 14, (Object) null);
            }
        }
    }

    public static /* synthetic */ void showWithoutParse$default(PlaybackService playbackService, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        playbackService.showWithoutParse(i, z);
    }

    public final void showWithoutParse(int i, boolean z) {
        boolean z2 = false;
        getPlaylistManager().setVideoTrackEnabled(false);
        if (getPlaylistManager().getMedia(i) != null) {
            getPlaylistManager().setCurrentIndex(i);
            notifyTrackChanged();
            MutableLiveData<Boolean> showAudioPlayer = PlaylistManager.Companion.getShowAudioPlayer();
            if (!isVideoPlaying() && !z) {
                z2 = true;
            }
            showAudioPlayer.setValue(Boolean.valueOf(z2));
            showNotification();
        }
    }

    public final void setVideoTrackEnabled(boolean z) {
        getPlaylistManager().setVideoTrackEnabled(z);
    }

    public final boolean switchToVideo() {
        return getPlaylistManager().switchToVideo();
    }

    public final void switchToPopup(int i) {
        PlaylistManager.saveMediaMeta$default(getPlaylistManager(), false, 1, (Object) null);
        showWithoutParse(i, true);
        showPopup();
    }

    public final void removePopup() {
        PopupManager popupManager2 = this.popupManager;
        if (popupManager2 != null) {
            popupManager2.removePopup();
        }
        this.popupManager = null;
    }

    private final void showPopup() {
        if (this.popupManager == null) {
            this.popupManager = new PopupManager(this);
        }
        PopupManager popupManager2 = this.popupManager;
        Intrinsics.checkNotNull(popupManager2);
        popupManager2.showPopup();
        hideNotification(true);
    }

    public static /* synthetic */ Job append$default(PlaybackService playbackService, MediaWrapper[] mediaWrapperArr, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return playbackService.append(mediaWrapperArr, i);
    }

    public final Job append(MediaWrapper[] mediaWrapperArr, int i) {
        Intrinsics.checkNotNullParameter(mediaWrapperArr, "mediaList");
        return append((List<? extends MediaWrapper>) ArraysKt.toList((T[]) mediaWrapperArr), i);
    }

    public static /* synthetic */ Job append$default(PlaybackService playbackService, List list, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return playbackService.append((List<? extends MediaWrapper>) list, i);
    }

    public final Job append(List<? extends MediaWrapper> list, int i) {
        Intrinsics.checkNotNullParameter(list, "mediaList");
        return BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, CoroutineStart.UNDISPATCHED, new PlaybackService$append$1(this, list, i, (Continuation<? super PlaybackService$append$1>) null), 1, (Object) null);
    }

    public final Job append(MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(mediaWrapper, "media");
        return append$default(this, CollectionsKt.listOf(mediaWrapper), 0, 2, (Object) null);
    }

    public final void insertNext(MediaWrapper[] mediaWrapperArr) {
        Intrinsics.checkNotNullParameter(mediaWrapperArr, "mediaList");
        insertNext((List<? extends MediaWrapper>) ArraysKt.toList((T[]) mediaWrapperArr));
    }

    private final void insertNext(List<? extends MediaWrapper> list) {
        getPlaylistManager().insertNext(list);
        onMediaListChanged();
    }

    public final void insertNext(MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(mediaWrapper, "media");
        insertNext((List<? extends MediaWrapper>) CollectionsKt.listOf(mediaWrapper));
    }

    public final void moveItem(int i, int i2) {
        getPlaylistManager().moveItem(i, i2);
    }

    public final void insertItem(int i, MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(mediaWrapper, "mw");
        getPlaylistManager().insertItem(i, mediaWrapper);
    }

    public final void remove(int i) {
        getPlaylistManager().remove(i);
    }

    public final void removeLocation(String str) {
        Intrinsics.checkNotNullParameter(str, "location");
        getPlaylistManager().removeLocation(str);
    }

    public final boolean hasNext() {
        return getPlaylistManager().hasNext();
    }

    public final boolean hasPrevious() {
        return getPlaylistManager().hasPrevious();
    }

    public final void detectHeadset(boolean z) {
        this.detectHeadset = z;
    }

    public final void setRate(float f, boolean z) {
        getPlaylistManager().getPlayer().setRate(f, z);
        publishState$default(this, (Long) null, 1, (Object) null);
    }

    public final void increaseRate() {
        if (getRate() < 4.0f) {
            setRate(getRate() + 0.2f, true);
        }
    }

    public final void decreaseRate() {
        if (((double) getRate()) > 0.4d) {
            setRate(getRate() - 0.2f, true);
        }
    }

    public final void resetRate() {
        setRate(1.0f, true);
    }

    public final void navigate(int i) {
        getPlaylistManager().getPlayer().navigate(i);
    }

    public final MediaPlayer.Chapter[] getChapters(int i) {
        return getPlaylistManager().getPlayer().getChapters(i);
    }

    public final int setVolume(int i) {
        return getPlaylistManager().getPlayer().setVolume(i);
    }

    public static /* synthetic */ void seek$default(PlaybackService playbackService, long j, double d, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            d = (double) playbackService.getLength();
        }
        playbackService.seek(j, d, (i & 4) != 0 ? false : z, (i & 8) != 0 ? false : z2);
    }

    public final void seek(long j, double d, boolean z, boolean z2) {
        if (d > 0.0d) {
            setTime(j, z2);
        } else {
            setPosition(((float) j) / 1000.0f);
            if (z) {
                publishState(Long.valueOf(j));
            }
        }
        if (z && isPaused()) {
            showNotification();
        }
    }

    public final boolean updateViewpoint(float f, float f2, float f3, float f4, boolean z) {
        return getPlaylistManager().getPlayer().updateViewpoint(f, f2, f3, f4, z);
    }

    public final void saveStartTime(long j) {
        getPlaylistManager().setSavedTime(j);
    }

    private final void setPosition(float f) {
        getPlaylistManager().getPlayer().setPosition(f);
    }

    public final boolean setAudioTrack(String str) {
        Intrinsics.checkNotNullParameter(str, "index");
        return getPlaylistManager().getPlayer().setAudioTrack(str);
    }

    public final void unselectTrackType(VideoTracksDialog.TrackType trackType) {
        Intrinsics.checkNotNullParameter(trackType, "trackType");
        getPlaylistManager().getPlayer().unselectTrackType(trackType);
    }

    public final boolean setAudioDigitalOutputEnabled(boolean z) {
        return getPlaylistManager().getPlayer().setAudioDigitalOutputEnabled(z);
    }

    public final boolean setVideoTrack(String str) {
        Intrinsics.checkNotNullParameter(str, "index");
        return getPlaylistManager().getPlayer().setVideoTrack(str);
    }

    public final boolean addSubtitleTrack(String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        return getPlaylistManager().getPlayer().addSubtitleTrack(str, z);
    }

    public final boolean addSubtitleTrack(Uri uri, boolean z) {
        Intrinsics.checkNotNullParameter(uri, Constants.KEY_URI);
        return getPlaylistManager().getPlayer().addSubtitleTrack(uri, z);
    }

    public final void setSpuTrack(String str) {
        Intrinsics.checkNotNullParameter(str, "index");
        getPlaylistManager().setSpuTrack(str);
    }

    public final void setAudioDelay(long j) {
        getPlaylistManager().setAudioDelay(j);
    }

    public final void setSpuDelay(long j) {
        getPlaylistManager().setSpuDelay(j);
    }

    public final boolean hasRenderer() {
        return getPlaylistManager().getPlayer().getHasRenderer();
    }

    public final void setRenderer(RendererItem rendererItem) {
        boolean hasRenderer = hasRenderer();
        if (hasRenderer && !hasRenderer() && canSwitchToVideo()) {
            VideoPlayerActivity.Companion companion = VideoPlayerActivity.Companion;
            Context applicationContext = getApplicationContext();
            MediaWrapper currentMedia = getPlaylistManager().getCurrentMedia();
            Intrinsics.checkNotNull(currentMedia);
            Uri uri = currentMedia.getUri();
            Intrinsics.checkNotNullExpressionValue(uri, "getUri(...)");
            companion.startOpened(applicationContext, uri, getPlaylistManager().getCurrentIndex());
        }
        getPlaylistManager().setRenderer(rendererItem);
        if (!hasRenderer && rendererItem != null) {
            getAudioFocusHelper().changeAudioFocus$vlc_android_release(false);
        } else if (hasRenderer && rendererItem == null && isPlaying()) {
            getAudioFocusHelper().changeAudioFocus$vlc_android_release(true);
        }
    }

    public final boolean setEqualizer(MediaPlayer.Equalizer equalizer2) {
        return getPlaylistManager().getPlayer().setEqualizer(equalizer2);
    }

    public final void setVideoScale(float f) {
        getPlaylistManager().getPlayer().setVideoScale(f);
    }

    public final void setVideoAspectRatio(String str) {
        getPlaylistManager().getPlayer().setVideoAspectRatio(str);
    }

    public void onTaskTriggered(String str, Bundle bundle) {
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(bundle, "data");
        if (Intrinsics.areEqual((Object) str, (Object) SHOW_TOAST)) {
            String string = bundle.getString("text");
            int i = bundle.getInt(TypedValues.TransitionType.S_DURATION);
            if (bundle.getBoolean("isError")) {
                if (this.nbErrors <= 2 || System.currentTimeMillis() - this.lastErrorTime >= 500) {
                    if (this.nbErrors >= 2) {
                        string = getString(R.string.playback_multiple_errors);
                    }
                    Toast toast = this.currentToast;
                    if (toast != null) {
                        toast.cancel();
                    }
                    this.nbErrors++;
                    this.lastErrorTime = System.currentTimeMillis();
                } else {
                    return;
                }
            }
            Toast makeText = Toast.makeText(getApplicationContext(), string, i);
            this.currentToast = makeText;
            if (makeText != null) {
                makeText.show();
            }
        } else if (Intrinsics.areEqual((Object) str, (Object) END_MEDIASESSION) && this.mediaSession != null) {
            getMediaSession$vlc_android_release().setActive(false);
        }
    }

    public Lifecycle getLifecycle() {
        return this.dispatcher.getLifecycle();
    }

    public MediaBrowserServiceCompat.BrowserRoot onGetRoot(String str, int i, Bundle bundle) {
        String str2;
        Intrinsics.checkNotNullParameter(str, "clientPackageName");
        AccessControl.INSTANCE.logCaller(i, str);
        Context context = this;
        if (!Permissions.INSTANCE.canReadStorage(context)) {
            Log.w("VLC/PlaybackService", "Returning null MediaBrowserService root. READ_EXTERNAL_STORAGE permission not granted.");
            return null;
        } else if (bundle != null && bundle.containsKey(MediaBrowserServiceCompat.BrowserRoot.EXTRA_SUGGESTED)) {
            return new MediaBrowserServiceCompat.BrowserRoot(MediaSessionBrowser.ID_SUGGESTED, (Bundle) null);
        } else {
            if (Intrinsics.areEqual((Object) str, (Object) Constants.DRIVING_MODE_APP_PKG)) {
                str2 = MediaSessionBrowser.ID_ROOT_NO_TABS;
            } else {
                str2 = MediaSessionBrowser.ID_ROOT;
            }
            Bundle contentStyle$default = MediaSessionBrowser.Companion.getContentStyle$default(MediaSessionBrowser.Companion, 0, 0, 3, (Object) null);
            contentStyle$default.putBoolean(MediaConstants.BROWSER_SERVICE_EXTRAS_KEY_SEARCH_SUPPORTED, true);
            if (Build.VERSION.SDK_INT > 26 && Intrinsics.areEqual((Object) str, (Object) Constants.ANDROID_AUTO_APP_PKG)) {
                Intent intent = new Intent(Constants.CAR_SETTINGS);
                intent.setComponent(new ComponentName(context, VLCCarService.class));
                PendingIntent carApp = CarPendingIntent.getCarApp(context, 0, intent, 33554432);
                Intrinsics.checkNotNullExpressionValue(carApp, "getCarApp(...)");
                contentStyle$default.putParcelable(MediaConstants.BROWSER_SERVICE_EXTRAS_KEY_APPLICATION_PREFERENCES_USING_CAR_APP_LIBRARY_INTENT, carApp);
            }
            return new MediaBrowserServiceCompat.BrowserRoot(str2, contentStyle$default);
        }
    }

    public void onLoadChildren(String str, MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result) {
        Intrinsics.checkNotNullParameter(str, "parentId");
        Intrinsics.checkNotNullParameter(result, "result");
        result.detach();
        Bundle browserRootHints = getBrowserRootHints();
        boolean z = Intrinsics.areEqual((Object) str, (Object) MediaSessionBrowser.ID_LAST_ADDED) && !Intrinsics.areEqual((Object) str, (Object) this.lastParentId);
        this.lastParentId = str;
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, CoroutineStart.UNDISPATCHED, new PlaybackService$onLoadChildren$1(this, result, str, browserRootHints, z, (Continuation<? super PlaybackService$onLoadChildren$1>) null), 1, (Object) null);
    }

    public void onSearch(String str, Bundle bundle, MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result) {
        Intrinsics.checkNotNullParameter(str, "query");
        Intrinsics.checkNotNullParameter(result, "result");
        result.detach();
        Bundle browserRootHints = getBrowserRootHints();
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, CoroutineStart.UNDISPATCHED, new PlaybackService$onSearch$1(this, result, str, browserRootHints, (Continuation<? super PlaybackService$onSearch$1>) null), 1, (Object) null);
    }

    private final void startSleepTimerJob() {
        stopSleepTimerJob();
        this.sleepTimerJob = BuildersKt__Builders_commonKt.launch$default(this, (CoroutineContext) null, (CoroutineStart) null, new PlaybackService$startSleepTimerJob$1(this, (Continuation<? super PlaybackService$startSleepTimerJob$1>) null), 3, (Object) null);
    }

    private final void stopSleepTimerJob() {
        Job job = this.sleepTimerJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.sleepTimerJob = null;
    }

    public final void setSleepTimer(Calendar calendar) {
        if (calendar == null || calendar.getTimeInMillis() >= System.currentTimeMillis()) {
            Companion.getPlayerSleepTime().setValue(calendar);
            if (calendar == null) {
                stopSleepTimerJob();
            } else {
                startSleepTimerJob();
            }
        }
    }

    @Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010#\u001a\u00020\fJ\u000e\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'J\u000e\u0010(\u001a\u00020%2\u0006\u0010&\u001a\u00020'J\u0006\u0010)\u001a\u00020%R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0019\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R#\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u00138FX\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0019\u001a\u00020\u001a¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\f0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\nR\u0019\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0 ¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"¨\u0006*"}, d2 = {"Lorg/videolan/vlc/PlaybackService$Companion;", "", "()V", "END_MEDIASESSION", "", "SHOW_TOAST", "equalizer", "Lvideolan/org/commontools/LiveEvent;", "Lorg/videolan/libvlc/MediaPlayer$Equalizer;", "getEqualizer", "()Lvideolan/org/commontools/LiveEvent;", "headSetDetection", "", "getHeadSetDetection", "instance", "Lorg/videolan/vlc/PlaybackService;", "getInstance", "()Lorg/videolan/vlc/PlaybackService;", "playerSleepTime", "Landroidx/lifecycle/MutableLiveData;", "Ljava/util/Calendar;", "getPlayerSleepTime", "()Landroidx/lifecycle/MutableLiveData;", "playerSleepTime$delegate", "Lkotlin/Lazy;", "renderer", "Lorg/videolan/vlc/util/RendererLiveData;", "getRenderer", "()Lorg/videolan/vlc/util/RendererLiveData;", "restartPlayer", "getRestartPlayer", "serviceFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "getServiceFlow", "()Lkotlinx/coroutines/flow/MutableStateFlow;", "hasRenderer", "loadLastAudio", "", "context", "Landroid/content/Context;", "start", "updateState", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: PlaybackService.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MutableStateFlow<PlaybackService> getServiceFlow() {
            return PlaybackService.serviceFlow;
        }

        public final PlaybackService getInstance() {
            return getServiceFlow().getValue();
        }

        public final RendererLiveData getRenderer() {
            return PlaybackService.renderer;
        }

        public final LiveEvent<Boolean> getRestartPlayer() {
            return PlaybackService.restartPlayer;
        }

        public final LiveEvent<Boolean> getHeadSetDetection() {
            return PlaybackService.headSetDetection;
        }

        public final LiveEvent<MediaPlayer.Equalizer> getEqualizer() {
            return PlaybackService.equalizer;
        }

        public final MutableLiveData<Calendar> getPlayerSleepTime() {
            return (MutableLiveData) PlaybackService.playerSleepTime$delegate.getValue();
        }

        public final void start(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (getInstance() == null) {
                ExtensionsKt.launchForeground$default(context, new Intent(context, PlaybackService.class), (Function0) null, 2, (Object) null);
            }
        }

        public final void loadLastAudio(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            ExtensionsKt.launchForeground$default(context, new Intent(Constants.ACTION_REMOTE_LAST_PLAYLIST, (Uri) null, context, PlaybackService.class), (Function0) null, 2, (Object) null);
        }

        public final boolean hasRenderer() {
            return getRenderer().getValue() != null;
        }

        public final void updateState() {
            PlaybackService instance = getInstance();
            if (instance != null) {
                SendChannel access$getCbActor$p = instance.cbActor;
                if (access$getCbActor$p == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("cbActor");
                    access$getCbActor$p = null;
                }
                ChannelResult.m2336boximpl(access$getCbActor$p.m1139trySendJP2dKIU(UpdateState.INSTANCE));
            }
        }
    }

    public final int getTime(long j) {
        ABRepeat value = getPlaylistManager().getAbRepeat().getValue();
        if (value == null || value.getStart() == -1 || value.getStop() == -1) {
            if (getLength() == 0) {
                j = this.position;
            }
            return (int) j;
        }
        Boolean value2 = getPlaylistManager().getAbRepeatOn().getValue();
        Intrinsics.checkNotNull(value2);
        if (value2.booleanValue()) {
            long start = value.getStart();
            long stop = value.getStop();
            if (start != -1 && j < start) {
                return (int) start;
            }
            if (stop != -1 && j > value.getStop()) {
                return (int) stop;
            }
        }
        return (int) j;
    }

    public final boolean isCarMode() {
        if (Build.VERSION.SDK_INT >= 23) {
            CarConnection carConnection2 = this.carConnection;
            if (carConnection2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("carConnection");
                carConnection2 = null;
            }
            Integer value = carConnection2.getType().getValue();
            if (value == null || value.intValue() <= 0) {
                return false;
            }
            return true;
        }
        Object systemService = getSystemService("uimode");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.UiModeManager");
        if (((UiModeManager) systemService).getCurrentModeType() == 3) {
            return true;
        }
        return false;
    }
}
