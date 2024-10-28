package org.videolan.vlc.gui.helpers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaRouter;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RSInvalidStateException;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.os.BundleKt;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import androidx.lifecycle.LifecycleOwnerKt;
import com.google.android.material.snackbar.Snackbar;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import nl.dionsegijn.konfetti.core.Angle;
import nl.dionsegijn.konfetti.core.Party;
import nl.dionsegijn.konfetti.core.Position;
import nl.dionsegijn.konfetti.core.Rotation;
import nl.dionsegijn.konfetti.core.emitter.Emitter;
import nl.dionsegijn.konfetti.core.models.Shape;
import nl.dionsegijn.konfetti.core.models.Size;
import nl.dionsegijn.konfetti.xml.KonfettiView;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.medialibrary.MLServiceLocator;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.resources.AppContextProvider;
import org.videolan.resources.Constants;
import org.videolan.resources.util.ExtensionsKt;
import org.videolan.television.ui.dialogs.ConfirmationTvActivity;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.Settings;
import org.videolan.vlc.BuildConfig;
import org.videolan.vlc.MediaParsingService;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.AuthorsActivity;
import org.videolan.vlc.gui.BaseActivity;
import org.videolan.vlc.gui.InfoActivity;
import org.videolan.vlc.gui.LibrariesActivity;
import org.videolan.vlc.gui.LibraryWithLicense;
import org.videolan.vlc.gui.dialogs.AboutVersionDialog;
import org.videolan.vlc.gui.dialogs.AddToGroupDialog;
import org.videolan.vlc.gui.dialogs.LicenseDialog;
import org.videolan.vlc.gui.dialogs.SavePlaylistDialog;
import org.videolan.vlc.gui.dialogs.VideoTracksDialog;
import org.videolan.vlc.gui.helpers.hf.PinCodeDelegate;
import org.videolan.vlc.media.MediaUtils;
import org.videolan.vlc.providers.medialibrary.MedialibraryProvider;
import org.videolan.vlc.util.FileUtils;
import org.videolan.vlc.util.UrlUtilsKt;

@Metadata(d1 = {"\u0000Ø\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010$\u001a\u0004\u0018\u00010%2\b\u0010&\u001a\u0004\u0018\u00010%2\b\b\u0002\u0010'\u001a\u00020(H\u0007J0\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\b\u0010&\u001a\u0004\u0018\u00010%2\u0006\u0010'\u001a\u00020(2\u0006\u0010-\u001a\u00020\u0018H@¢\u0006\u0002\u0010.J\u000e\u0010/\u001a\u00020*2\u0006\u00100\u001a\u000201J\u001e\u00102\u001a\u00020*2\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u000206J\u0016\u00108\u001a\u00020*2\u0006\u00100\u001a\u0002092\u0006\u0010:\u001a\u00020;J\u0016\u0010<\u001a\u00020\u00182\u0006\u00103\u001a\u0002042\u0006\u0010=\u001a\u00020\u0018J\u000e\u0010>\u001a\u00020\u00042\u0006\u00103\u001a\u000204J\u000e\u0010?\u001a\u00020\u00042\u0006\u00103\u001a\u000204J\u000e\u0010@\u001a\u00020\u00042\u0006\u00103\u001a\u000204J\u000e\u0010A\u001a\u00020\u00042\u0006\u00103\u001a\u000204J\u000e\u0010B\u001a\u00020\u00042\u0006\u00103\u001a\u000204J\u000e\u0010C\u001a\u00020\u00042\u0006\u00103\u001a\u000204J\u000e\u0010D\u001a\u00020\u00042\u0006\u00103\u001a\u000204J\u0016\u0010E\u001a\u00020\u00042\u0006\u00103\u001a\u0002042\u0006\u0010F\u001a\u00020GJ\u000e\u0010H\u001a\u00020\u00042\u0006\u00103\u001a\u000204J\u000e\u0010I\u001a\u00020\u00042\u0006\u00103\u001a\u000204J\u000e\u0010J\u001a\u00020\u00042\u0006\u00103\u001a\u000204J\u000e\u0010K\u001a\u00020\u00042\u0006\u00103\u001a\u000204J\u000e\u0010L\u001a\u00020\u00042\u0006\u00103\u001a\u000204J\u000e\u0010M\u001a\u00020\u00042\u0006\u00103\u001a\u000204J\u000e\u0010N\u001a\u00020\u00042\u0006\u00103\u001a\u000204J\u000e\u0010O\u001a\u00020\u00042\u0006\u00103\u001a\u000204J\u000e\u0010P\u001a\u00020\u00042\u0006\u00103\u001a\u000204J\u000e\u0010Q\u001a\u00020\u00042\u0006\u00103\u001a\u000204J\u000e\u0010R\u001a\u00020\u00042\u0006\u00103\u001a\u000204J\u000e\u0010S\u001a\u00020\u00042\u0006\u00103\u001a\u000204J\u0016\u0010T\u001a\u00020\u00182\u0006\u00103\u001a\u0002042\u0006\u0010=\u001a\u00020\u0018J\u001c\u0010U\u001a\u0004\u0018\u00010;2\u0006\u00100\u001a\u0002012\b\b\u0002\u0010V\u001a\u00020!H\u0002J\u000e\u0010W\u001a\u00020!2\u0006\u00103\u001a\u000204J\u0006\u0010X\u001a\u00020*J\u001a\u0010Y\u001a\u00020*2\b\u00100\u001a\u0004\u0018\u0001012\b\u0010Z\u001a\u0004\u0018\u00010\u001aJ.\u0010[\u001a\u00020*2\u0006\u00100\u001a\u0002012\b\b\u0002\u0010\\\u001a\u00020!2\b\b\u0002\u0010]\u001a\u00020\u00182\n\b\u0002\u0010^\u001a\u0004\u0018\u00010\u0001J\u0018\u0010_\u001a\u00020*2\b\u0010:\u001a\u0004\u0018\u00010;2\u0006\u0010`\u001a\u00020!J\u0010\u0010a\u001a\u00020*2\u0006\u00100\u001a\u000201H\u0007J\u0010\u0010b\u001a\u00020*2\u0006\u00100\u001a\u000201H\u0007J\u001a\u0010c\u001a\u00020*2\b\u0010:\u001a\u0004\u0018\u00010;2\b\u0010d\u001a\u0004\u0018\u00010eJ \u0010f\u001a\u00020*2\u0006\u00100\u001a\u0002012\u0006\u0010g\u001a\u00020\u00182\b\b\u0002\u0010V\u001a\u00020!J\u0018\u0010f\u001a\u00020*2\u0006\u00100\u001a\u0002012\u0006\u0010h\u001a\u00020\u001aH\u0007J:\u0010i\u001a\u00020*2\u0006\u00100\u001a\u0002012\u0006\u0010h\u001a\u00020\u001a2\b\b\u0002\u0010V\u001a\u00020!2\b\b\u0003\u0010j\u001a\u00020\u00182\f\u0010k\u001a\b\u0012\u0004\u0012\u00020*0lH\u0007J\u0018\u0010m\u001a\u0004\u0018\u00010n2\u0006\u00100\u001a\u0002012\u0006\u0010h\u001a\u00020\u001aJ\u0010\u0010o\u001a\u00020*2\u0006\u00100\u001a\u000209H\u0007J>\u0010p\u001a\u00020*2\u0006\u00100\u001a\u0002012\u0006\u0010h\u001a\u00020\u001a2\b\b\u0002\u0010V\u001a\u00020!2\f\u0010k\u001a\b\u0012\u0004\u0012\u00020*0l2\f\u0010q\u001a\b\u0012\u0004\u0012\u00020*0lH\u0007J\u001a\u0010r\u001a\u00020*2\u0006\u0010s\u001a\u00020t2\n\u0010u\u001a\u0006\u0012\u0002\b\u00030vJ\u0012\u0010r\u001a\u00020*2\n\u0010w\u001a\u0006\u0012\u0002\b\u00030xJ\n\u0010y\u001a\u00020**\u00020zJ/\u0010{\u001a\u00020**\u0002092\f\u0010|\u001a\b\u0012\u0004\u0012\u00020~0}2\u0006\u0010\u001a\u00020!2\r\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020*0lJ)\u0010\u0001\u001a\u00020**\u0002092\r\u0010|\u001a\t\u0012\u0004\u0012\u00020~0\u00012\u0007\u0010\u0001\u001a\u00020\u001a¢\u0006\u0003\u0010\u0001J\u001a\u0010\u0001\u001a\u00020**\u0002092\r\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020~0}J*\u0010\u0001\u001a\u00020**\u0002092\u0007\u0010\u0001\u001a\u00020\u001a2\t\b\u0002\u0010\u0001\u001a\u00020!2\t\b\u0002\u0010\u0001\u001a\u00020\u001aJE\u0010\u0001\u001a\u0004\u0018\u00010**\u00020t2\u0006\u00103\u001a\u0002042\t\b\u0001\u0010\u0001\u001a\u00020\u00182\t\b\u0001\u0010\u0001\u001a\u00020\u00182\u0007\u0010\u0001\u001a\u00020!2\u0007\u0010\u0001\u001a\u00020!H\u0002¢\u0006\u0003\u0010\u0001J\u001d\u0010\u0001\u001a\u00020**\u0002092\u0007\u0010\u0001\u001a\u00020GH@¢\u0006\u0003\u0010\u0001J\u000b\u0010\u0001\u001a\u00020!*\u000204J\u000b\u0010\u0001\u001a\u00020**\u00020zJ\u000b\u0010\u0001\u001a\u00020**\u000209J\u0014\u0010\u0001\u001a\u00020**\u0002092\u0007\u0010\u0001\u001a\u00020~J\u000b\u0010\u0001\u001a\u00020!*\u000209J?\u0010\u0001\u001a\u00020**\u0002092\u0015\u0010\u0001\u001a\u0010\u0012\u0005\u0012\u00030\u0001\u0012\u0004\u0012\u00020*0\u00012\u001b\u0010\u0001\u001a\u0016\u0012\u0004\u0012\u00020\u001a\u0012\u0005\u0012\u00030\u0001\u0012\u0004\u0012\u00020*0\u0001JC\u0010i\u001a\u00020**\u00030 \u00012\u0006\u00100\u001a\u0002012\u0006\u0010h\u001a\u00020\u001a2\u001e\u0010k\u001a\u001a\b\u0001\u0012\u000b\u0012\t\u0012\u0004\u0012\u00020*0¡\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0001H\u0007¢\u0006\u0003\u0010¢\u0001R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aXT¢\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\u00020\u0018X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u000e\u0010 \u001a\u00020!X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0004¢\u0006\u0002\n\u0000¨\u0006£\u0001"}, d2 = {"Lorg/videolan/vlc/gui/helpers/UiTools;", "", "()V", "DEFAULT_COVER_ALBUM_DRAWABLE", "Landroid/graphics/drawable/BitmapDrawable;", "DEFAULT_COVER_ALBUM_DRAWABLE_BIG", "DEFAULT_COVER_ARTIST_DRAWABLE", "DEFAULT_COVER_ARTIST_DRAWABLE_BIG", "DEFAULT_COVER_AUDIO_AUTO_DRAWABLE", "DEFAULT_COVER_AUDIO_DRAWABLE", "DEFAULT_COVER_AUDIO_DRAWABLE_BIG", "DEFAULT_COVER_FOLDER_DRAWABLE", "DEFAULT_COVER_FOLDER_DRAWABLE_BIG", "DEFAULT_COVER_GENRE_DRAWABLE", "DEFAULT_COVER_GENRE_DRAWABLE_BIG", "DEFAULT_COVER_MOVIE_DRAWABLE", "DEFAULT_COVER_MOVIE_DRAWABLE_BIG", "DEFAULT_COVER_PLAYLIST_DRAWABLE", "DEFAULT_COVER_PLAYLIST_DRAWABLE_BIG", "DEFAULT_COVER_TVSHOW_DRAWABLE", "DEFAULT_COVER_TVSHOW_DRAWABLE_BIG", "DEFAULT_COVER_VIDEO_DRAWABLE", "DEFAULT_COVER_VIDEO_DRAWABLE_BIG", "DELETE_DURATION", "", "TAG", "", "currentNightMode", "getCurrentNightMode", "()I", "setCurrentNightMode", "(I)V", "logoAnimationRunning", "", "sHandler", "Landroid/os/Handler;", "blurBitmap", "Landroid/graphics/Bitmap;", "bitmap", "radius", "", "blurView", "", "imageView", "Landroid/widget/ImageView;", "colorFilter", "(Landroid/widget/ImageView;Landroid/graphics/Bitmap;FILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "confirmExit", "activity", "Landroid/app/Activity;", "deleteSubtitleDialog", "context", "Landroid/content/Context;", "positiveListener", "Landroid/content/DialogInterface$OnClickListener;", "negativeListener", "fillAboutView", "Landroidx/fragment/app/FragmentActivity;", "v", "Landroid/view/View;", "getColorFromAttribute", "attrId", "getDefaultAlbumDrawable", "getDefaultAlbumDrawableBig", "getDefaultArtistDrawable", "getDefaultArtistDrawableBig", "getDefaultAudioAutoDrawable", "getDefaultAudioDrawable", "getDefaultAudioDrawableBig", "getDefaultCover", "item", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "getDefaultFolderDrawable", "getDefaultFolderDrawableBig", "getDefaultGenreDrawable", "getDefaultGenreDrawableBig", "getDefaultMovieDrawable", "getDefaultMovieDrawableBig", "getDefaultPlaylistDrawable", "getDefaultPlaylistDrawableBig", "getDefaultTvshowDrawable", "getDefaultTvshowDrawableBig", "getDefaultVideoDrawable", "getDefaultVideoDrawableBig", "getResourceFromAttribute", "getSnackAnchorView", "overAudioPlayer", "hasSecondaryDisplay", "invalidateBitmaps", "newStorageDetected", "path", "restartDialog", "fromLeanback", "leanbackResultCode", "leanbackCaller", "setKeyboardVisibility", "show", "setOnDragListener", "setRotationAnimation", "setViewOnClickListener", "ocl", "Landroid/view/View$OnClickListener;", "snacker", "stringId", "message", "snackerConfirm", "confirmMessage", "action", "Lkotlin/Function0;", "snackerMessageInfinite", "Lcom/google/android/material/snackbar/Snackbar;", "snackerMissing", "snackerWithCancel", "cancelAction", "updateSortTitles", "menu", "Landroid/view/Menu;", "provider", "Lorg/videolan/vlc/providers/medialibrary/MedialibraryProvider;", "sortable", "Lorg/videolan/vlc/gui/browser/MediaBrowserFragment;", "addFavoritesIcon", "Landroid/widget/TextView;", "addToGroup", "tracks", "", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "forbidNewGroup", "newGroupListener", "addToPlaylist", "", "key", "(Landroidx/fragment/app/FragmentActivity;[Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;Ljava/lang/String;)V", "list", "addToPlaylistAsync", "parent", "includeSubfolders", "defaultTitle", "appendSortOrder", "id", "titleRes", "isCurrent", "desc", "(Landroid/view/Menu;Landroid/content/Context;IIZZ)Lkotlin/Unit;", "createShortcut", "mediaLibraryItem", "(Landroidx/fragment/app/FragmentActivity;Lorg/videolan/medialibrary/media/MediaLibraryItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isTablet", "removeDrawables", "showDonations", "showMediaInfo", "mediaWrapper", "showPinIfNeeded", "showVideoTrack", "menuListener", "Lkotlin/Function1;", "Lorg/videolan/vlc/gui/dialogs/VideoTracksDialog$VideoTrackOption;", "trackSelectionListener", "Lkotlin/Function2;", "Lorg/videolan/vlc/gui/dialogs/VideoTracksDialog$TrackType;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/Continuation;", "(Lkotlinx/coroutines/CoroutineScope;Landroid/app/Activity;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: UiTools.kt */
public final class UiTools {
    private static BitmapDrawable DEFAULT_COVER_ALBUM_DRAWABLE = null;
    private static BitmapDrawable DEFAULT_COVER_ALBUM_DRAWABLE_BIG = null;
    private static BitmapDrawable DEFAULT_COVER_ARTIST_DRAWABLE = null;
    private static BitmapDrawable DEFAULT_COVER_ARTIST_DRAWABLE_BIG = null;
    private static BitmapDrawable DEFAULT_COVER_AUDIO_AUTO_DRAWABLE = null;
    private static BitmapDrawable DEFAULT_COVER_AUDIO_DRAWABLE = null;
    private static BitmapDrawable DEFAULT_COVER_AUDIO_DRAWABLE_BIG = null;
    private static BitmapDrawable DEFAULT_COVER_FOLDER_DRAWABLE = null;
    private static BitmapDrawable DEFAULT_COVER_FOLDER_DRAWABLE_BIG = null;
    private static BitmapDrawable DEFAULT_COVER_GENRE_DRAWABLE = null;
    private static BitmapDrawable DEFAULT_COVER_GENRE_DRAWABLE_BIG = null;
    private static BitmapDrawable DEFAULT_COVER_MOVIE_DRAWABLE = null;
    private static BitmapDrawable DEFAULT_COVER_MOVIE_DRAWABLE_BIG = null;
    private static BitmapDrawable DEFAULT_COVER_PLAYLIST_DRAWABLE = null;
    private static BitmapDrawable DEFAULT_COVER_PLAYLIST_DRAWABLE_BIG = null;
    private static BitmapDrawable DEFAULT_COVER_TVSHOW_DRAWABLE = null;
    private static BitmapDrawable DEFAULT_COVER_TVSHOW_DRAWABLE_BIG = null;
    private static BitmapDrawable DEFAULT_COVER_VIDEO_DRAWABLE = null;
    private static BitmapDrawable DEFAULT_COVER_VIDEO_DRAWABLE_BIG = null;
    private static final int DELETE_DURATION = 3000;
    public static final UiTools INSTANCE = new UiTools();
    private static final String TAG = "VLC/UiTools";
    private static int currentNightMode;
    private static boolean logoAnimationRunning;
    private static final Handler sHandler = new Handler(Looper.getMainLooper());

    public final Bitmap blurBitmap(Bitmap bitmap) {
        return blurBitmap$default(this, bitmap, 0.0f, 2, (Object) null);
    }

    private UiTools() {
    }

    public final int getCurrentNightMode() {
        return currentNightMode;
    }

    public final void setCurrentNightMode(int i) {
        currentNightMode = i;
    }

    public final BitmapDrawable getDefaultVideoDrawable(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (DEFAULT_COVER_VIDEO_DRAWABLE == null) {
            DEFAULT_COVER_VIDEO_DRAWABLE = new BitmapDrawable(context.getResources(), ImageLoaderKt.getBitmapFromDrawable(context, R.drawable.ic_no_thumbnail_1610));
        }
        BitmapDrawable bitmapDrawable = DEFAULT_COVER_VIDEO_DRAWABLE;
        Intrinsics.checkNotNull(bitmapDrawable);
        return bitmapDrawable;
    }

    public final BitmapDrawable getDefaultAudioDrawable(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (DEFAULT_COVER_AUDIO_DRAWABLE == null) {
            DEFAULT_COVER_AUDIO_DRAWABLE = new BitmapDrawable(context.getResources(), ImageLoaderKt.getBitmapFromDrawable(context, R.drawable.ic_no_song));
        }
        BitmapDrawable bitmapDrawable = DEFAULT_COVER_AUDIO_DRAWABLE;
        Intrinsics.checkNotNull(bitmapDrawable);
        return bitmapDrawable;
    }

    public final BitmapDrawable getDefaultAudioAutoDrawable(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (DEFAULT_COVER_AUDIO_AUTO_DRAWABLE == null) {
            DEFAULT_COVER_AUDIO_AUTO_DRAWABLE = new BitmapDrawable(context.getResources(), ImageLoaderKt.getBitmapFromDrawable(context, R.drawable.ic_auto_nothumb));
        }
        BitmapDrawable bitmapDrawable = DEFAULT_COVER_AUDIO_AUTO_DRAWABLE;
        Intrinsics.checkNotNull(bitmapDrawable);
        return bitmapDrawable;
    }

    public final BitmapDrawable getDefaultFolderDrawable(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (DEFAULT_COVER_FOLDER_DRAWABLE == null) {
            DEFAULT_COVER_FOLDER_DRAWABLE = new BitmapDrawable(context.getResources(), ImageLoaderKt.getBitmapFromDrawable(context, R.drawable.ic_folder));
        }
        BitmapDrawable bitmapDrawable = DEFAULT_COVER_FOLDER_DRAWABLE;
        Intrinsics.checkNotNull(bitmapDrawable);
        return bitmapDrawable;
    }

    public final BitmapDrawable getDefaultAlbumDrawable(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (DEFAULT_COVER_ALBUM_DRAWABLE == null) {
            DEFAULT_COVER_ALBUM_DRAWABLE = new BitmapDrawable(context.getResources(), ImageLoaderKt.getBitmapFromDrawable(context, R.drawable.ic_no_album));
        }
        BitmapDrawable bitmapDrawable = DEFAULT_COVER_ALBUM_DRAWABLE;
        Intrinsics.checkNotNull(bitmapDrawable);
        return bitmapDrawable;
    }

    public final BitmapDrawable getDefaultArtistDrawable(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (DEFAULT_COVER_ARTIST_DRAWABLE == null) {
            DEFAULT_COVER_ARTIST_DRAWABLE = new BitmapDrawable(context.getResources(), ImageLoaderKt.getBitmapFromDrawable(context, R.drawable.ic_no_artist));
        }
        BitmapDrawable bitmapDrawable = DEFAULT_COVER_ARTIST_DRAWABLE;
        Intrinsics.checkNotNull(bitmapDrawable);
        return bitmapDrawable;
    }

    public final BitmapDrawable getDefaultPlaylistDrawable(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (DEFAULT_COVER_PLAYLIST_DRAWABLE == null) {
            DEFAULT_COVER_PLAYLIST_DRAWABLE = new BitmapDrawable(context.getResources(), ImageLoaderKt.getBitmapFromDrawable(context, R.drawable.ic_playlist));
        }
        BitmapDrawable bitmapDrawable = DEFAULT_COVER_PLAYLIST_DRAWABLE;
        Intrinsics.checkNotNull(bitmapDrawable);
        return bitmapDrawable;
    }

    public final BitmapDrawable getDefaultGenreDrawable(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (DEFAULT_COVER_GENRE_DRAWABLE == null) {
            DEFAULT_COVER_GENRE_DRAWABLE = new BitmapDrawable(context.getResources(), ImageLoaderKt.getBitmapFromDrawable(context, R.drawable.ic_genre));
        }
        BitmapDrawable bitmapDrawable = DEFAULT_COVER_GENRE_DRAWABLE;
        Intrinsics.checkNotNull(bitmapDrawable);
        return bitmapDrawable;
    }

    public final BitmapDrawable getDefaultMovieDrawable(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (DEFAULT_COVER_MOVIE_DRAWABLE == null) {
            DEFAULT_COVER_MOVIE_DRAWABLE = new BitmapDrawable(context.getResources(), ImageLoaderKt.getBitmapFromDrawable(context, R.drawable.ic_browser_movie));
        }
        BitmapDrawable bitmapDrawable = DEFAULT_COVER_MOVIE_DRAWABLE;
        Intrinsics.checkNotNull(bitmapDrawable);
        return bitmapDrawable;
    }

    public final BitmapDrawable getDefaultTvshowDrawable(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (DEFAULT_COVER_TVSHOW_DRAWABLE == null) {
            DEFAULT_COVER_TVSHOW_DRAWABLE = new BitmapDrawable(context.getResources(), ImageLoaderKt.getBitmapFromDrawable(context, R.drawable.ic_browser_tvshow));
        }
        BitmapDrawable bitmapDrawable = DEFAULT_COVER_TVSHOW_DRAWABLE;
        Intrinsics.checkNotNull(bitmapDrawable);
        return bitmapDrawable;
    }

    public final BitmapDrawable getDefaultVideoDrawableBig(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (DEFAULT_COVER_VIDEO_DRAWABLE_BIG == null) {
            DEFAULT_COVER_VIDEO_DRAWABLE_BIG = new BitmapDrawable(context.getResources(), ImageLoaderKt.getBitmapFromDrawable(context, R.drawable.ic_video_big));
        }
        BitmapDrawable bitmapDrawable = DEFAULT_COVER_VIDEO_DRAWABLE_BIG;
        Intrinsics.checkNotNull(bitmapDrawable);
        return bitmapDrawable;
    }

    public final BitmapDrawable getDefaultAudioDrawableBig(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (DEFAULT_COVER_AUDIO_DRAWABLE_BIG == null) {
            DEFAULT_COVER_AUDIO_DRAWABLE_BIG = new BitmapDrawable(context.getResources(), ImageLoaderKt.getBitmapFromDrawable(context, R.drawable.ic_song_big));
        }
        BitmapDrawable bitmapDrawable = DEFAULT_COVER_AUDIO_DRAWABLE_BIG;
        Intrinsics.checkNotNull(bitmapDrawable);
        return bitmapDrawable;
    }

    public final BitmapDrawable getDefaultAlbumDrawableBig(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (DEFAULT_COVER_ALBUM_DRAWABLE_BIG == null) {
            DEFAULT_COVER_ALBUM_DRAWABLE_BIG = new BitmapDrawable(context.getResources(), ImageLoaderKt.getBitmapFromDrawable(context, R.drawable.ic_album_big));
        }
        BitmapDrawable bitmapDrawable = DEFAULT_COVER_ALBUM_DRAWABLE_BIG;
        Intrinsics.checkNotNull(bitmapDrawable);
        return bitmapDrawable;
    }

    public final BitmapDrawable getDefaultArtistDrawableBig(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (DEFAULT_COVER_ARTIST_DRAWABLE_BIG == null) {
            DEFAULT_COVER_ARTIST_DRAWABLE_BIG = new BitmapDrawable(context.getResources(), ImageLoaderKt.getBitmapFromDrawable(context, R.drawable.ic_artist_big));
        }
        BitmapDrawable bitmapDrawable = DEFAULT_COVER_ARTIST_DRAWABLE_BIG;
        Intrinsics.checkNotNull(bitmapDrawable);
        return bitmapDrawable;
    }

    public final BitmapDrawable getDefaultPlaylistDrawableBig(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (DEFAULT_COVER_PLAYLIST_DRAWABLE_BIG == null) {
            DEFAULT_COVER_PLAYLIST_DRAWABLE_BIG = new BitmapDrawable(context.getResources(), ImageLoaderKt.getBitmapFromDrawable(context, R.drawable.ic_playlist_big));
        }
        BitmapDrawable bitmapDrawable = DEFAULT_COVER_PLAYLIST_DRAWABLE_BIG;
        Intrinsics.checkNotNull(bitmapDrawable);
        return bitmapDrawable;
    }

    public final BitmapDrawable getDefaultGenreDrawableBig(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (DEFAULT_COVER_GENRE_DRAWABLE_BIG == null) {
            DEFAULT_COVER_GENRE_DRAWABLE_BIG = new BitmapDrawable(context.getResources(), ImageLoaderKt.getBitmapFromDrawable(context, R.drawable.ic_genre_big));
        }
        BitmapDrawable bitmapDrawable = DEFAULT_COVER_GENRE_DRAWABLE_BIG;
        Intrinsics.checkNotNull(bitmapDrawable);
        return bitmapDrawable;
    }

    public final BitmapDrawable getDefaultMovieDrawableBig(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (DEFAULT_COVER_MOVIE_DRAWABLE_BIG == null) {
            DEFAULT_COVER_MOVIE_DRAWABLE_BIG = new BitmapDrawable(context.getResources(), ImageLoaderKt.getBitmapFromDrawable(context, R.drawable.ic_browser_movie_big));
        }
        BitmapDrawable bitmapDrawable = DEFAULT_COVER_MOVIE_DRAWABLE_BIG;
        Intrinsics.checkNotNull(bitmapDrawable);
        return bitmapDrawable;
    }

    public final BitmapDrawable getDefaultTvshowDrawableBig(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (DEFAULT_COVER_TVSHOW_DRAWABLE_BIG == null) {
            DEFAULT_COVER_TVSHOW_DRAWABLE_BIG = new BitmapDrawable(context.getResources(), ImageLoaderKt.getBitmapFromDrawable(context, R.drawable.ic_browser_tvshow_big));
        }
        BitmapDrawable bitmapDrawable = DEFAULT_COVER_TVSHOW_DRAWABLE_BIG;
        Intrinsics.checkNotNull(bitmapDrawable);
        return bitmapDrawable;
    }

    public final BitmapDrawable getDefaultFolderDrawableBig(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (DEFAULT_COVER_FOLDER_DRAWABLE_BIG == null) {
            DEFAULT_COVER_FOLDER_DRAWABLE_BIG = new BitmapDrawable(context.getResources(), ImageLoaderKt.getBitmapFromDrawable(context, R.drawable.ic_folder_big));
        }
        BitmapDrawable bitmapDrawable = DEFAULT_COVER_FOLDER_DRAWABLE_BIG;
        Intrinsics.checkNotNull(bitmapDrawable);
        return bitmapDrawable;
    }

    static /* synthetic */ View getSnackAnchorView$default(UiTools uiTools, Activity activity, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return uiTools.getSnackAnchorView(activity, z);
    }

    private final View getSnackAnchorView(Activity activity, boolean z) {
        if (activity instanceof BaseActivity) {
            BaseActivity baseActivity = (BaseActivity) activity;
            if (baseActivity.getSnackAnchorView(z) != null) {
                return baseActivity.getSnackAnchorView(z);
            }
        }
        return activity.findViewById(16908290);
    }

    public static /* synthetic */ void snacker$default(UiTools uiTools, Activity activity, int i, boolean z, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = false;
        }
        uiTools.snacker(activity, i, z);
    }

    public final void snacker(Activity activity, int i, boolean z) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        View snackAnchorView = getSnackAnchorView(activity, z);
        if (snackAnchorView != null) {
            Snackbar make = Snackbar.make(snackAnchorView, i, -1);
            Intrinsics.checkNotNullExpressionValue(make, "make(...)");
            if (z) {
                make.setAnchorView(R.id.audio_play_progress);
            }
            make.show();
        }
    }

    public final void snacker(Activity activity, String str) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(str, "message");
        View snackAnchorView$default = getSnackAnchorView$default(this, activity, false, 2, (Object) null);
        if (snackAnchorView$default != null) {
            Snackbar make = Snackbar.make(snackAnchorView$default, (CharSequence) str, -1);
            Intrinsics.checkNotNullExpressionValue(make, "make(...)");
            if (AndroidUtil.isLolliPopOrLater) {
                make.getView().setElevation((float) snackAnchorView$default.getResources().getDimensionPixelSize(R.dimen.audio_player_elevation));
            }
            make.show();
        }
    }

    public static /* synthetic */ void snackerConfirm$default(UiTools uiTools, Activity activity, String str, boolean z, int i, Function0 function0, int i2, Object obj) {
        boolean z2 = (i2 & 4) != 0 ? false : z;
        if ((i2 & 8) != 0) {
            i = R.string.ok;
        }
        uiTools.snackerConfirm(activity, str, z2, i, function0);
    }

    public final void snackerConfirm(Activity activity, String str, boolean z, int i, Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(str, "message");
        Intrinsics.checkNotNullParameter(function0, "action");
        View snackAnchorView = getSnackAnchorView(activity, z);
        if (snackAnchorView != null) {
            Snackbar action = Snackbar.make(snackAnchorView, (CharSequence) str, 0).setAction(i, (View.OnClickListener) new UiTools$$ExternalSyntheticLambda25(function0));
            Intrinsics.checkNotNullExpressionValue(action, "setAction(...)");
            if (z) {
                action.setAnchorView(R.id.time);
            }
            if (AndroidUtil.isLolliPopOrLater) {
                action.getView().setElevation((float) snackAnchorView.getResources().getDimensionPixelSize(R.dimen.audio_player_elevation));
            }
            action.show();
        }
    }

    /* access modifiers changed from: private */
    public static final void snackerConfirm$lambda$0(Function0 function0, View view) {
        Intrinsics.checkNotNullParameter(function0, "$action");
        function0.invoke();
    }

    public final void snackerConfirm(CoroutineScope coroutineScope, Activity activity, String str, Function1<? super Continuation<? super Unit>, ? extends Object> function1) {
        Intrinsics.checkNotNullParameter(coroutineScope, "<this>");
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(str, "message");
        Intrinsics.checkNotNullParameter(function1, "action");
        View snackAnchorView$default = getSnackAnchorView$default(this, activity, false, 2, (Object) null);
        if (snackAnchorView$default != null) {
            Snackbar action = Snackbar.make(snackAnchorView$default, (CharSequence) str, 0).setAction(R.string.ok, (View.OnClickListener) new UiTools$$ExternalSyntheticLambda12(coroutineScope, function1));
            Intrinsics.checkNotNullExpressionValue(action, "setAction(...)");
            if (AndroidUtil.isLolliPopOrLater) {
                action.getView().setElevation((float) snackAnchorView$default.getResources().getDimensionPixelSize(R.dimen.audio_player_elevation));
            }
            action.show();
        }
    }

    /* access modifiers changed from: private */
    public static final void snackerConfirm$lambda$1(CoroutineScope coroutineScope, Function1 function1, View view) {
        Intrinsics.checkNotNullParameter(coroutineScope, "$this_snackerConfirm");
        Intrinsics.checkNotNullParameter(function1, "$action");
        Job unused = BuildersKt__Builders_commonKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new UiTools$snackerConfirm$snack$2$1(function1, (Continuation<? super UiTools$snackerConfirm$snack$2$1>) null), 3, (Object) null);
    }

    public static /* synthetic */ void snackerWithCancel$default(UiTools uiTools, Activity activity, String str, boolean z, Function0 function0, Function0 function02, int i, Object obj) {
        uiTools.snackerWithCancel(activity, str, (i & 4) != 0 ? false : z, function0, function02);
    }

    public final void snackerWithCancel(Activity activity, String str, boolean z, Function0<Unit> function0, Function0<Unit> function02) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(str, "message");
        Intrinsics.checkNotNullParameter(function0, "action");
        Intrinsics.checkNotNullParameter(function02, "cancelAction");
        View snackAnchorView = getSnackAnchorView(activity, z);
        if (snackAnchorView != null) {
            Snackbar action = Snackbar.make(snackAnchorView, (CharSequence) str, 3000).setAction(R.string.cancel, (View.OnClickListener) new UiTools$$ExternalSyntheticLambda13(function0, function02));
            Intrinsics.checkNotNullExpressionValue(action, "setAction(...)");
            if (AndroidUtil.isLolliPopOrLater) {
                action.getView().setElevation((float) snackAnchorView.getResources().getDimensionPixelSize(R.dimen.audio_player_elevation));
            }
            if (z) {
                action.setAnchorView(R.id.time);
            }
            action.show();
            sHandler.postDelayed(new UiTools$$ExternalSyntheticLambda14(function0), 3000);
        }
    }

    /* access modifiers changed from: private */
    public static final void snackerWithCancel$lambda$3(Function0 function0, Function0 function02, View view) {
        Intrinsics.checkNotNullParameter(function0, "$action");
        Intrinsics.checkNotNullParameter(function02, "$cancelAction");
        sHandler.removeCallbacks(new UiTools$$ExternalSyntheticLambda24(function0));
        function02.invoke();
    }

    /* access modifiers changed from: private */
    public static final void snackerWithCancel$lambda$3$lambda$2(Function0 function0) {
        Intrinsics.checkNotNullParameter(function0, "$tmp0");
        function0.invoke();
    }

    /* access modifiers changed from: private */
    public static final void snackerWithCancel$lambda$4(Function0 function0) {
        Intrinsics.checkNotNullParameter(function0, "$tmp0");
        function0.invoke();
    }

    public final Snackbar snackerMessageInfinite(Activity activity, String str) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(str, "message");
        View snackAnchorView$default = getSnackAnchorView$default(this, activity, false, 2, (Object) null);
        if (snackAnchorView$default == null) {
            return null;
        }
        return Snackbar.make(snackAnchorView$default, (CharSequence) str, -2);
    }

    public final void snackerMissing(FragmentActivity fragmentActivity) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "activity");
        View snackAnchorView$default = getSnackAnchorView$default(this, fragmentActivity, false, 2, (Object) null);
        if (snackAnchorView$default != null) {
            Snackbar action = Snackbar.make(snackAnchorView$default, (CharSequence) fragmentActivity.getString(R.string.missing_media_snack), 0).setAction(R.string.ok, (View.OnClickListener) new UiTools$$ExternalSyntheticLambda23(fragmentActivity));
            Intrinsics.checkNotNullExpressionValue(action, "setAction(...)");
            if (AndroidUtil.isLolliPopOrLater) {
                action.getView().setElevation((float) snackAnchorView$default.getResources().getDimensionPixelSize(R.dimen.audio_player_elevation));
            }
            action.show();
        }
    }

    /* access modifiers changed from: private */
    public static final void snackerMissing$lambda$5(FragmentActivity fragmentActivity, View view) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "$activity");
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(fragmentActivity), (CoroutineContext) null, (CoroutineStart) null, new UiTools$snackerMissing$snack$1$1(fragmentActivity, (Continuation<? super UiTools$snackerMissing$snack$1$1>) null), 3, (Object) null);
    }

    public final int getResourceFromAttribute(Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{i});
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "obtainStyledAttributes(...)");
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
        return resourceId;
    }

    public final int getColorFromAttribute(Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        return ContextCompat.getColor(context, getResourceFromAttribute(context, i));
    }

    public final void setViewOnClickListener(View view, View.OnClickListener onClickListener) {
        if (view != null) {
            view.setOnClickListener(onClickListener);
        }
    }

    public final void fillAboutView(FragmentActivity fragmentActivity, View view) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "activity");
        Intrinsics.checkNotNullParameter(view, "v");
        String string = view.getContext().getString(R.string.build_time);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        ((TextView) view.findViewById(R.id.app_version)).setText(BuildConfig.VLC_VERSION_NAME);
        ((TextView) view.findViewById(R.id.app_version_date)).setText(string);
        KotlinExtensionsKt.setGone(view.findViewById(R.id.sliding_tabs));
        ImageView imageView = (ImageView) view.findViewById(R.id.logo);
        imageView.setOnClickListener(new UiTools$$ExternalSyntheticLambda28(imageView, fragmentActivity, (KonfettiView) view.findViewById(R.id.konfetti)));
        view.findViewById(R.id.version_card).setOnClickListener(new UiTools$$ExternalSyntheticLambda29(fragmentActivity));
        view.findViewById(R.id.about_website_container).setOnClickListener(new UiTools$$ExternalSyntheticLambda4(fragmentActivity));
        view.findViewById(R.id.about_forum_container).setOnClickListener(new UiTools$$ExternalSyntheticLambda5(fragmentActivity));
        view.findViewById(R.id.about_sources_container).setOnClickListener(new UiTools$$ExternalSyntheticLambda6(fragmentActivity));
        view.findViewById(R.id.about_authors_container).setOnClickListener(new UiTools$$ExternalSyntheticLambda7(fragmentActivity));
        view.findViewById(R.id.about_libraries_container).setOnClickListener(new UiTools$$ExternalSyntheticLambda8(fragmentActivity));
        view.findViewById(R.id.about_vlc_card).setOnClickListener(new UiTools$$ExternalSyntheticLambda9(fragmentActivity));
        ((CardView) view.findViewById(R.id.donationsButton)).setOnClickListener(new UiTools$$ExternalSyntheticLambda10(fragmentActivity));
    }

    /* access modifiers changed from: private */
    public static final void fillAboutView$lambda$8(ImageView imageView, FragmentActivity fragmentActivity, KonfettiView konfettiView, View view) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "$activity");
        if (!logoAnimationRunning) {
            logoAnimationRunning = true;
            int nextInt = new SecureRandom().nextInt(4) + 1;
            imageView.animate().rotationBy(((float) nextInt) * 360.0f).translationY(-((float) KotlinExtensionsKt.getDp(24))).setDuration(((long) 600) + (((long) nextInt) * 100)).setInterpolator(new AccelerateDecelerateInterpolator()).withEndAction(new UiTools$$ExternalSyntheticLambda11(imageView, fragmentActivity, nextInt, konfettiView));
        }
    }

    /* access modifiers changed from: private */
    public static final void fillAboutView$lambda$8$lambda$7(ImageView imageView, FragmentActivity fragmentActivity, int i, KonfettiView konfettiView) {
        FragmentActivity fragmentActivity2 = fragmentActivity;
        KonfettiView konfettiView2 = konfettiView;
        Intrinsics.checkNotNullParameter(fragmentActivity2, "$activity");
        imageView.animate().translationY(0.0f).setStartDelay(75).setDuration(300).setInterpolator(new OvershootInterpolator(3.0f)).withEndAction(new UiTools$$ExternalSyntheticLambda17());
        Context context = fragmentActivity2;
        List listOf = CollectionsKt.listOf(Integer.valueOf(ContextCompat.getColor(context, R.color.orange200)), Integer.valueOf(ContextCompat.getColor(context, R.color.orange800)), Integer.valueOf(ContextCompat.getColor(context, R.color.orange500)));
        Rotation rotation = r23;
        Rotation rotation2 = new Rotation(false, 0.0f, 0.0f, 0.0f, 0.0f, 30, (DefaultConstructorMarker) null);
        List listOf2 = CollectionsKt.listOf(Shape.Circle.INSTANCE);
        int i2 = i * 30;
        konfettiView2.start(new Party(0, 60, 3.0f, 18.0f, 0.0f, CollectionsKt.listOf(new Size(4, 0.0f, 0.0f, 6, (DefaultConstructorMarker) null), new Size(3, 0.0f, 0.0f, 6, (DefaultConstructorMarker) null), new Size(2, 0.0f, 0.0f, 6, (DefaultConstructorMarker) null)), listOf, listOf2, 2000, true, new Position.Absolute((imageView.getX() + ((float) imageView.getWidth())) - ((float) KotlinExtensionsKt.getDp(12)), (imageView.getY() + ((float) imageView.getHeight())) - ((float) KotlinExtensionsKt.getDp(24))).between(new Position.Absolute((imageView.getX() + ((float) imageView.getWidth())) - ((float) KotlinExtensionsKt.getDp(12)), imageView.getY() + ((float) imageView.getHeight()) + ((float) KotlinExtensionsKt.getDp(24)))), 275, rotation, new Emitter(200, TimeUnit.MILLISECONDS).max(i2), 16, (DefaultConstructorMarker) null));
        List listOf3 = CollectionsKt.listOf(Integer.valueOf(ContextCompat.getColor(context, R.color.orange200)), Integer.valueOf(ContextCompat.getColor(context, R.color.orange800)), Integer.valueOf(ContextCompat.getColor(context, R.color.orange500)));
        Rotation rotation3 = r8;
        Rotation rotation4 = new Rotation(false, 0.0f, 0.0f, 0.0f, 0.0f, 30, (DefaultConstructorMarker) null);
        konfettiView2.start(new Party(Angle.LEFT, 60, 3.0f, 18.0f, 0.0f, CollectionsKt.listOf(new Size(4, 0.0f, 0.0f, 6, (DefaultConstructorMarker) null), new Size(3, 0.0f, 0.0f, 6, (DefaultConstructorMarker) null), new Size(2, 0.0f, 0.0f, 6, (DefaultConstructorMarker) null)), listOf3, CollectionsKt.listOf(Shape.Circle.INSTANCE), 2000, true, new Position.Absolute(imageView.getX() + ((float) KotlinExtensionsKt.getDp(12)), (imageView.getY() + ((float) imageView.getHeight())) - ((float) KotlinExtensionsKt.getDp(24))).between(new Position.Absolute(imageView.getX() + ((float) KotlinExtensionsKt.getDp(12)), imageView.getY() + ((float) imageView.getHeight()) + ((float) KotlinExtensionsKt.getDp(24)))), 275, rotation3, new Emitter(200, TimeUnit.MILLISECONDS).max(i2), 16, (DefaultConstructorMarker) null));
    }

    /* access modifiers changed from: private */
    public static final void fillAboutView$lambda$8$lambda$7$lambda$6() {
        logoAnimationRunning = false;
    }

    /* access modifiers changed from: private */
    public static final void fillAboutView$lambda$9(FragmentActivity fragmentActivity, View view) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "$activity");
        AboutVersionDialog.Companion.newInstance().show(fragmentActivity.getSupportFragmentManager(), "AboutVersionDialog");
    }

    /* access modifiers changed from: private */
    public static final void fillAboutView$lambda$10(FragmentActivity fragmentActivity, View view) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "$activity");
        UrlUtilsKt.openLinkIfPossible$default(fragmentActivity, "https://www.videolan.org/vlc/", 0, 2, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final void fillAboutView$lambda$11(FragmentActivity fragmentActivity, View view) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "$activity");
        UrlUtilsKt.openLinkIfPossible$default(fragmentActivity, "https://forum.videolan.org/viewforum.php?f=35", 0, 2, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final void fillAboutView$lambda$12(FragmentActivity fragmentActivity, View view) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "$activity");
        UrlUtilsKt.openLinkIfPossible$default(fragmentActivity, "https://code.videolan.org/videolan/vlc-android", 0, 2, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final void fillAboutView$lambda$13(FragmentActivity fragmentActivity, View view) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "$activity");
        fragmentActivity.startActivity(new Intent(fragmentActivity, AuthorsActivity.class));
    }

    /* access modifiers changed from: private */
    public static final void fillAboutView$lambda$14(FragmentActivity fragmentActivity, View view) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "$activity");
        fragmentActivity.startActivity(new Intent(fragmentActivity, LibrariesActivity.class));
    }

    /* access modifiers changed from: private */
    public static final void fillAboutView$lambda$15(FragmentActivity fragmentActivity, View view) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "$activity");
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        LifecycleOwnerKt.getLifecycleScope(fragmentActivity).launchWhenStarted(new UiTools$fillAboutView$8$1(objectRef, (Continuation<? super UiTools$fillAboutView$8$1>) null));
        LicenseDialog.Companion companion = LicenseDialog.Companion;
        String string = fragmentActivity.getString(R.string.app_name);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String string2 = fragmentActivity.getString(R.string.about_copyright);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        String string3 = fragmentActivity.getString(R.string.about_license);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        companion.newInstance(new LibraryWithLicense(string, string2, string3, (String) objectRef.element, "https://www.gnu.org/licenses/old-licenses/gpl-2.0.txt")).show(fragmentActivity.getSupportFragmentManager(), "LicenseDialog");
    }

    /* access modifiers changed from: private */
    public static final void fillAboutView$lambda$16(FragmentActivity fragmentActivity, View view) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "$activity");
        INSTANCE.showDonations(fragmentActivity);
    }

    public final void setKeyboardVisibility(View view, boolean z) {
        if (view != null) {
            Object systemService = view.getContext().getApplicationContext().getSystemService("input_method");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
            sHandler.post(new UiTools$$ExternalSyntheticLambda15(z, (InputMethodManager) systemService, view));
        }
    }

    /* access modifiers changed from: private */
    public static final void setKeyboardVisibility$lambda$17(boolean z, InputMethodManager inputMethodManager, View view) {
        Intrinsics.checkNotNullParameter(inputMethodManager, "$inputMethodManager");
        if (z) {
            inputMethodManager.showSoftInput(view, 1);
        } else {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static /* synthetic */ void addToPlaylistAsync$default(UiTools uiTools, FragmentActivity fragmentActivity, String str, boolean z, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            str2 = "";
        }
        uiTools.addToPlaylistAsync(fragmentActivity, str, z, str2);
    }

    public final void addToPlaylistAsync(FragmentActivity fragmentActivity, String str, boolean z, String str2) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "<this>");
        Intrinsics.checkNotNullParameter(str, "parent");
        Intrinsics.checkNotNullParameter(str2, "defaultTitle");
        if (KotlinExtensionsKt.isStarted(fragmentActivity)) {
            SavePlaylistDialog savePlaylistDialog = new SavePlaylistDialog();
            savePlaylistDialog.setArguments(BundleKt.bundleOf(TuplesKt.to(SavePlaylistDialog.KEY_FOLDER, str), TuplesKt.to(SavePlaylistDialog.KEY_SUB_FOLDERS, Boolean.valueOf(z)), TuplesKt.to(SavePlaylistDialog.KEY_DEFAULT_TITLE, str2)));
            savePlaylistDialog.show(fragmentActivity.getSupportFragmentManager(), "fragment_add_to_playlist");
        }
    }

    public final void addToPlaylist(FragmentActivity fragmentActivity, List<? extends MediaWrapper> list) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "<this>");
        Intrinsics.checkNotNullParameter(list, "list");
        addToPlaylist(fragmentActivity, (MediaWrapper[]) list.toArray(new MediaWrapper[0]), SavePlaylistDialog.KEY_NEW_TRACKS);
    }

    public final void addToPlaylist(FragmentActivity fragmentActivity, MediaWrapper[] mediaWrapperArr, String str) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "<this>");
        Intrinsics.checkNotNullParameter(mediaWrapperArr, "tracks");
        Intrinsics.checkNotNullParameter(str, LeanbackPreferenceDialogFragment.ARG_KEY);
        if (KotlinExtensionsKt.isStarted(fragmentActivity)) {
            SavePlaylistDialog savePlaylistDialog = new SavePlaylistDialog();
            savePlaylistDialog.setArguments(BundleKt.bundleOf(TuplesKt.to(str, mediaWrapperArr)));
            savePlaylistDialog.show(fragmentActivity.getSupportFragmentManager(), "fragment_add_to_playlist");
        }
    }

    public final boolean showPinIfNeeded(FragmentActivity fragmentActivity) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "<this>");
        if (!Settings.INSTANCE.getSafeMode() || Intrinsics.areEqual((Object) PinCodeDelegate.Companion.getPinUnlocked().getValue(), (Object) true)) {
            return false;
        }
        if (Settings.INSTANCE.getTvUI()) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(fragmentActivity), (CoroutineContext) null, (CoroutineStart) null, new UiTools$showPinIfNeeded$1(fragmentActivity, (Continuation<? super UiTools$showPinIfNeeded$1>) null), 3, (Object) null);
        } else {
            String string = fragmentActivity.getString(R.string.restricted_access);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            snackerConfirm(fragmentActivity, string, false, R.string.unlock, new UiTools$showPinIfNeeded$2(fragmentActivity));
        }
        return true;
    }

    public final void addToGroup(FragmentActivity fragmentActivity, List<? extends MediaWrapper> list, boolean z, Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "<this>");
        Intrinsics.checkNotNullParameter(list, "tracks");
        Intrinsics.checkNotNullParameter(function0, "newGroupListener");
        if (KotlinExtensionsKt.isStarted(fragmentActivity)) {
            AddToGroupDialog addToGroupDialog = new AddToGroupDialog();
            addToGroupDialog.setArguments(BundleKt.bundleOf(TuplesKt.to(AddToGroupDialog.KEY_TRACKS, list.toArray(new MediaWrapper[0])), TuplesKt.to(AddToGroupDialog.FORBID_NEW_GROUP, Boolean.valueOf(z))));
            addToGroupDialog.show(fragmentActivity.getSupportFragmentManager(), "fragment_add_to_group");
            addToGroupDialog.setNewGroupListener(function0);
        }
    }

    public final Object createShortcut(FragmentActivity fragmentActivity, MediaLibraryItem mediaLibraryItem, Continuation<? super Unit> continuation) {
        if (!KotlinExtensionsKt.isStarted(fragmentActivity)) {
            return Unit.INSTANCE;
        }
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new UiTools$createShortcut$2(mediaLibraryItem, fragmentActivity, (Continuation<? super UiTools$createShortcut$2>) null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    public final void showVideoTrack(FragmentActivity fragmentActivity, Function1<? super VideoTracksDialog.VideoTrackOption, Unit> function1, Function2<? super String, ? super VideoTracksDialog.TrackType, Unit> function2) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "<this>");
        Intrinsics.checkNotNullParameter(function1, "menuListener");
        Intrinsics.checkNotNullParameter(function2, "trackSelectionListener");
        if (KotlinExtensionsKt.isStarted(fragmentActivity)) {
            VideoTracksDialog videoTracksDialog = new VideoTracksDialog();
            videoTracksDialog.setArguments(BundleKt.bundleOf());
            videoTracksDialog.show(fragmentActivity.getSupportFragmentManager(), "fragment_video_tracks");
            videoTracksDialog.setMenuItemListener(function1);
            videoTracksDialog.setTrackSelectionListener(function2);
        }
    }

    public final void showDonations(FragmentActivity fragmentActivity) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "<this>");
        KotlinExtensionsKt.isStarted(fragmentActivity);
    }

    public final void showMediaInfo(FragmentActivity fragmentActivity, MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "<this>");
        Intrinsics.checkNotNullParameter(mediaWrapper, "mediaWrapper");
        Intent intent = new Intent(fragmentActivity, InfoActivity.class);
        intent.putExtra("ML_ITEM", mediaWrapper);
        fragmentActivity.startActivity(intent);
    }

    public final boolean isTablet(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return context.getResources().getBoolean(R.bool.is_tablet);
    }

    public final BitmapDrawable getDefaultCover(Context context, MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
        int itemType = mediaLibraryItem.getItemType();
        if (itemType == 2) {
            return getDefaultAlbumDrawable(context);
        }
        if (itemType == 4) {
            return getDefaultArtistDrawable(context);
        }
        if (itemType != 32) {
            return getDefaultAudioDrawable(context);
        }
        return ((MediaWrapper) mediaLibraryItem).getType() == 0 ? getDefaultVideoDrawable(context) : getDefaultAudioDrawable(context);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x008f A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object blurView(android.widget.ImageView r7, android.graphics.Bitmap r8, float r9, int r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            r6 = this;
            boolean r0 = r11 instanceof org.videolan.vlc.gui.helpers.UiTools$blurView$1
            if (r0 == 0) goto L_0x0014
            r0 = r11
            org.videolan.vlc.gui.helpers.UiTools$blurView$1 r0 = (org.videolan.vlc.gui.helpers.UiTools$blurView$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.gui.helpers.UiTools$blurView$1 r0 = new org.videolan.vlc.gui.helpers.UiTools$blurView$1
            r0.<init>(r6, r11)
        L_0x0019:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L_0x003e
            if (r2 == r4) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x0090
        L_0x002e:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0036:
            java.lang.Object r7 = r0.L$0
            android.widget.ImageView r7 = (android.widget.ImageView) r7
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x0076
        L_0x003e:
            kotlin.ResultKt.throwOnFailure(r11)
            r7.setColorFilter(r10)
            int r10 = android.os.Build.VERSION.SDK_INT
            r11 = 31
            if (r10 < r11) goto L_0x005e
            android.graphics.Shader$TileMode r10 = android.graphics.Shader.TileMode.CLAMP
            android.graphics.RenderEffect r9 = android.graphics.RenderEffect.createBlurEffect(r9, r9, r10)
            java.lang.String r10 = "createBlurEffect(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r10)
            r7.setRenderEffect(r9)
            r7.setImageBitmap(r8)
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        L_0x005e:
            kotlinx.coroutines.CoroutineDispatcher r10 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r10 = (kotlin.coroutines.CoroutineContext) r10
            org.videolan.vlc.gui.helpers.UiTools$blurView$blurred$1 r11 = new org.videolan.vlc.gui.helpers.UiTools$blurView$blurred$1
            r11.<init>(r8, r9, r5)
            kotlin.jvm.functions.Function2 r11 = (kotlin.jvm.functions.Function2) r11
            r0.L$0 = r7
            r0.label = r4
            java.lang.Object r11 = kotlinx.coroutines.BuildersKt.withContext(r10, r11, r0)
            if (r11 != r1) goto L_0x0076
            return r1
        L_0x0076:
            android.graphics.Bitmap r11 = (android.graphics.Bitmap) r11
            kotlinx.coroutines.MainCoroutineDispatcher r8 = kotlinx.coroutines.Dispatchers.getMain()
            kotlin.coroutines.CoroutineContext r8 = (kotlin.coroutines.CoroutineContext) r8
            org.videolan.vlc.gui.helpers.UiTools$blurView$2 r9 = new org.videolan.vlc.gui.helpers.UiTools$blurView$2
            r9.<init>(r7, r11, r5)
            kotlin.jvm.functions.Function2 r9 = (kotlin.jvm.functions.Function2) r9
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r7 = kotlinx.coroutines.BuildersKt.withContext(r8, r9, r0)
            if (r7 != r1) goto L_0x0090
            return r1
        L_0x0090:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.helpers.UiTools.blurView(android.widget.ImageView, android.graphics.Bitmap, float, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Bitmap blurBitmap$default(UiTools uiTools, Bitmap bitmap, float f, int i, Object obj) {
        if ((i & 2) != 0) {
            f = 15.0f;
        }
        return uiTools.blurBitmap(bitmap, f);
    }

    public final Bitmap blurBitmap(Bitmap bitmap, float f) {
        if (!(bitmap == null || bitmap.getConfig() == null)) {
            try {
                Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
                Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
                RenderScript create = RenderScript.create(AppContextProvider.INSTANCE.getAppContext());
                ScriptIntrinsicBlur create2 = ScriptIntrinsicBlur.create(create, Element.U8_4(create));
                if (bitmap.getConfig() != Bitmap.Config.ARGB_8888) {
                    bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
                }
                Allocation createFromBitmap = Allocation.createFromBitmap(create, bitmap);
                Allocation createFromBitmap2 = Allocation.createFromBitmap(create, createBitmap);
                create2.setRadius(f);
                create2.setInput(createFromBitmap);
                create2.forEach(createFromBitmap2);
                createFromBitmap2.copyTo(createBitmap);
                create.destroy();
                return createBitmap;
            } catch (RSInvalidStateException unused) {
            }
        }
        return null;
    }

    public final void updateSortTitles(Menu menu, MedialibraryProvider<?> medialibraryProvider) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        Intrinsics.checkNotNullParameter(medialibraryProvider, "provider");
        int sort = medialibraryProvider.getSort();
        boolean desc = medialibraryProvider.getDesc();
        appendSortOrder(menu, medialibraryProvider.getContext(), R.id.ml_menu_sortby_name, R.string.sortby_name, sort == 1 || sort == 0, desc);
        appendSortOrder(menu, medialibraryProvider.getContext(), R.id.ml_menu_sortby_filename, R.string.sortby_filename, sort == 10, desc);
        appendSortOrder(menu, medialibraryProvider.getContext(), R.id.ml_menu_sortby_artist_name, R.string.sortby_artist_name, sort == 7, desc);
        appendSortOrder(menu, medialibraryProvider.getContext(), R.id.ml_menu_sortby_album_name, R.string.sortby_album_name, sort == 9, desc);
        appendSortOrder(menu, medialibraryProvider.getContext(), R.id.ml_menu_sortby_length, R.string.sortby_length, sort == 2, desc);
        appendSortOrder(menu, medialibraryProvider.getContext(), R.id.ml_menu_sortby_date, R.string.sortby_date_release, sort == 5, desc);
        appendSortOrder(menu, medialibraryProvider.getContext(), R.id.ml_menu_sortby_last_modified, R.string.sortby_date_last_modified, sort == 4, desc);
        appendSortOrder(menu, medialibraryProvider.getContext(), R.id.ml_menu_sortby_insertion_date, R.string.sortby_date_insertion, sort == 3, desc);
    }

    /* JADX WARNING: type inference failed for: r15v0, types: [org.videolan.vlc.gui.browser.MediaBrowserFragment<?>, java.lang.Object, org.videolan.vlc.gui.browser.MediaBrowserFragment] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void updateSortTitles(org.videolan.vlc.gui.browser.MediaBrowserFragment<?> r15) {
        /*
            r14 = this;
            java.lang.String r0 = "sortable"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r15, r0)
            android.view.Menu r0 = r15.getMenu()
            if (r0 != 0) goto L_0x000c
            return
        L_0x000c:
            org.videolan.vlc.viewmodels.SortableModel r1 = r15.getViewModel()
            int r8 = r1.getSort()
            boolean r9 = r1.getDesc()
            androidx.fragment.app.FragmentActivity r1 = r15.requireActivity()
            java.lang.String r10 = "requireActivity(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r10)
            r3 = r1
            android.content.Context r3 = (android.content.Context) r3
            int r4 = org.videolan.vlc.R.id.ml_menu_sortby_name
            int r5 = org.videolan.vlc.R.string.sortby_name
            r11 = 0
            r12 = 1
            if (r8 != r12) goto L_0x002e
            r6 = 1
            goto L_0x002f
        L_0x002e:
            r6 = 0
        L_0x002f:
            r1 = r14
            r2 = r0
            r7 = r9
            r1.appendSortOrder(r2, r3, r4, r5, r6, r7)
            androidx.fragment.app.FragmentActivity r1 = r15.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r10)
            r3 = r1
            android.content.Context r3 = (android.content.Context) r3
            int r4 = org.videolan.vlc.R.id.ml_menu_sortby_filename
            int r5 = org.videolan.vlc.R.string.sortby_filename
            r1 = 10
            if (r8 == r1) goto L_0x004c
            if (r8 != 0) goto L_0x004a
            goto L_0x004c
        L_0x004a:
            r6 = 0
            goto L_0x004d
        L_0x004c:
            r6 = 1
        L_0x004d:
            r1 = r14
            r2 = r0
            r7 = r9
            r1.appendSortOrder(r2, r3, r4, r5, r6, r7)
            androidx.fragment.app.FragmentActivity r1 = r15.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r10)
            r3 = r1
            android.content.Context r3 = (android.content.Context) r3
            int r4 = org.videolan.vlc.R.id.ml_menu_sortby_artist_name
            int r5 = org.videolan.vlc.R.string.sortby_artist_name
            r1 = 7
            if (r8 != r1) goto L_0x0066
            r6 = 1
            goto L_0x0067
        L_0x0066:
            r6 = 0
        L_0x0067:
            r1 = r14
            r2 = r0
            r7 = r9
            r1.appendSortOrder(r2, r3, r4, r5, r6, r7)
            androidx.fragment.app.FragmentActivity r1 = r15.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r10)
            r3 = r1
            android.content.Context r3 = (android.content.Context) r3
            int r4 = org.videolan.vlc.R.id.ml_menu_sortby_album_name
            int r5 = org.videolan.vlc.R.string.sortby_album_name
            r1 = 9
            if (r8 != r1) goto L_0x0081
            r6 = 1
            goto L_0x0082
        L_0x0081:
            r6 = 0
        L_0x0082:
            r1 = r14
            r2 = r0
            r7 = r9
            r1.appendSortOrder(r2, r3, r4, r5, r6, r7)
            androidx.fragment.app.FragmentActivity r1 = r15.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r10)
            r3 = r1
            android.content.Context r3 = (android.content.Context) r3
            int r4 = org.videolan.vlc.R.id.ml_menu_sortby_length
            int r5 = org.videolan.vlc.R.string.sortby_length
            r1 = 2
            if (r8 != r1) goto L_0x009b
            r6 = 1
            goto L_0x009c
        L_0x009b:
            r6 = 0
        L_0x009c:
            r1 = r14
            r2 = r0
            r7 = r9
            r1.appendSortOrder(r2, r3, r4, r5, r6, r7)
            androidx.fragment.app.FragmentActivity r1 = r15.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r10)
            r3 = r1
            android.content.Context r3 = (android.content.Context) r3
            int r4 = org.videolan.vlc.R.id.ml_menu_sortby_date
            int r5 = org.videolan.vlc.R.string.sortby_date_release
            r13 = 5
            if (r8 != r13) goto L_0x00b5
            r6 = 1
            goto L_0x00b6
        L_0x00b5:
            r6 = 0
        L_0x00b6:
            r1 = r14
            r2 = r0
            r7 = r9
            r1.appendSortOrder(r2, r3, r4, r5, r6, r7)
            androidx.fragment.app.FragmentActivity r15 = r15.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r15, r10)
            r3 = r15
            android.content.Context r3 = (android.content.Context) r3
            int r4 = org.videolan.vlc.R.id.ml_menu_sortby_last_modified
            int r5 = org.videolan.vlc.R.string.sortby_date_last_modified
            if (r8 != r13) goto L_0x00ce
            r6 = 1
            goto L_0x00cf
        L_0x00ce:
            r6 = 0
        L_0x00cf:
            r1 = r14
            r2 = r0
            r7 = r9
            r1.appendSortOrder(r2, r3, r4, r5, r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.helpers.UiTools.updateSortTitles(org.videolan.vlc.gui.browser.MediaBrowserFragment):void");
    }

    private final Unit appendSortOrder(Menu menu, Context context, int i, int i2, boolean z, boolean z2) {
        CharSequence charSequence;
        MenuItem findItem = menu.findItem(i);
        if (findItem == null) {
            return null;
        }
        String string = context.getString(i2);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        if (!z) {
            charSequence = string;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(string);
            sb.append(' ');
            sb.append(z2 ? "▼" : "▲");
            charSequence = sb.toString();
        }
        findItem.setTitle(charSequence);
        if (z) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(string);
            sb2.append(". ");
            sb2.append(context.getString(z2 ? R.string.descending : R.string.ascending));
            string = sb2.toString();
        }
        MenuItemCompat.setContentDescription(findItem, string);
        return Unit.INSTANCE;
    }

    public final void confirmExit(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        new AlertDialog.Builder(activity).setMessage(R.string.exit_app_msg).setTitle(R.string.exit_app).setPositiveButton(R.string.ok, (DialogInterface.OnClickListener) new UiTools$$ExternalSyntheticLambda26(activity)).setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) new UiTools$$ExternalSyntheticLambda27()).create().show();
    }

    /* access modifiers changed from: private */
    public static final void confirmExit$lambda$19(Activity activity, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(activity, "$activity");
        activity.finish();
    }

    /* access modifiers changed from: private */
    public static final void confirmExit$lambda$20(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }

    public final void newStorageDetected(Activity activity, String str) {
        if (activity != null) {
            String fileNameFromPath = FileUtils.INSTANCE.getFileNameFromPath(str);
            String storageTag = FileUtils.INSTANCE.getStorageTag(fileNameFromPath);
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string = activity.getString(R.string.ml_external_storage_msg);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            if (storageTag != null) {
                fileNameFromPath = storageTag;
            }
            String format = String.format(string, Arrays.copyOf(new Object[]{fileNameFromPath}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            Context context = activity;
            Intent putExtra = new Intent(Constants.ACTION_DISCOVER_DEVICE, (Uri) null, context, MediaParsingService.class).putExtra("extra_path", str);
            Intrinsics.checkNotNullExpressionValue(putExtra, "putExtra(...)");
            if (activity instanceof AppCompatActivity) {
                new AlertDialog.Builder(context).setTitle(R.string.ml_external_storage_title).setCancelable(false).setMessage((CharSequence) format).setPositiveButton(R.string.ml_external_storage_accept, (DialogInterface.OnClickListener) new UiTools$$ExternalSyntheticLambda18(activity, putExtra)).setNegativeButton(R.string.ml_external_storage_decline, (DialogInterface.OnClickListener) new UiTools$$ExternalSyntheticLambda19()).show();
            } else {
                new AlertDialog.Builder(context).setTitle(R.string.ml_external_storage_title).setCancelable(false).setMessage(format).setPositiveButton(R.string.ml_external_storage_accept, new UiTools$$ExternalSyntheticLambda20(activity, putExtra)).setNegativeButton(R.string.ml_external_storage_decline, new UiTools$$ExternalSyntheticLambda21()).show();
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void newStorageDetected$lambda$21(Activity activity, Intent intent, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(intent, "$si");
        ExtensionsKt.launchForeground$default(activity, intent, (Function0) null, 2, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final void newStorageDetected$lambda$22(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }

    /* access modifiers changed from: private */
    public static final void newStorageDetected$lambda$23(Activity activity, Intent intent, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(intent, "$si");
        ExtensionsKt.launchForeground$default(activity, intent, (Function0) null, 2, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final void newStorageDetected$lambda$24(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }

    public final void setOnDragListener(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        View peekDecorView = AndroidUtil.isNougatOrLater ? activity.getWindow().peekDecorView() : null;
        if (peekDecorView != null) {
            peekDecorView.setOnDragListener(new UiTools$$ExternalSyntheticLambda16(activity));
        }
    }

    /* access modifiers changed from: private */
    public static final boolean setOnDragListener$lambda$25(Activity activity, View view, DragEvent dragEvent) {
        ClipData clipData;
        Intrinsics.checkNotNullParameter(activity, "$activity");
        int action = dragEvent.getAction();
        if (action == 1) {
            return true;
        }
        if (action != 3 || (clipData = dragEvent.getClipData()) == null) {
            return false;
        }
        int itemCount = clipData.getItemCount();
        List arrayList = new ArrayList();
        for (int i = 0; i < itemCount; i++) {
            if (activity.requestDragAndDropPermissions(dragEvent) != null) {
                ClipData.Item itemAt = clipData.getItemAt(i);
                Uri uri = itemAt.getUri();
                if (uri == null) {
                    uri = Uri.parse(itemAt.getText().toString());
                }
                MediaWrapper abstractMediaWrapper = MLServiceLocator.getAbstractMediaWrapper(uri);
                if (!Intrinsics.areEqual((Object) "file", (Object) uri.getScheme())) {
                    abstractMediaWrapper.setType(6);
                }
                Intrinsics.checkNotNull(abstractMediaWrapper);
                arrayList.add(abstractMediaWrapper);
            }
        }
        MediaUtils.openList$default(MediaUtils.INSTANCE, activity, arrayList, 0, false, 8, (Object) null);
        return false;
    }

    public final void setRotationAnimation(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (AndroidUtil.isJellyBeanMR2OrLater) {
            Window window = activity.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.rotationAnimation = Build.VERSION.SDK_INT >= 26 ? 3 : 2;
            window.setAttributes(attributes);
        }
    }

    public static /* synthetic */ void restartDialog$default(UiTools uiTools, Activity activity, boolean z, int i, Object obj, int i2, Object obj2) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        if ((i2 & 8) != 0) {
            obj = null;
        }
        uiTools.restartDialog(activity, z, i, obj);
    }

    public final void restartDialog(Activity activity, boolean z, int i, Object obj) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (z) {
            Intent className = new Intent("android.intent.action.VIEW").setClassName(activity, Constants.TV_CONFIRMATION_ACTIVITY);
            Intrinsics.checkNotNullExpressionValue(className, "setClassName(...)");
            className.putExtra(ConfirmationTvActivity.CONFIRMATION_DIALOG_TITLE, activity.getString(R.string.restart_vlc));
            className.putExtra(ConfirmationTvActivity.CONFIRMATION_DIALOG_TEXT, activity.getString(R.string.restart_message));
            if (obj instanceof Activity) {
                ((Activity) obj).startActivityForResult(className, i);
            } else if (obj instanceof Fragment) {
                ((Fragment) obj).startActivityForResult(className, i);
            } else if (obj instanceof android.app.Fragment) {
                ((android.app.Fragment) obj).startActivityForResult(className, i);
            } else {
                throw new IllegalStateException("Invalid caller");
            }
        } else {
            new AlertDialog.Builder(activity).setTitle((CharSequence) activity.getResources().getString(R.string.restart_vlc)).setMessage((CharSequence) activity.getResources().getString(R.string.restart_message)).setPositiveButton(R.string.restart_message_OK, (DialogInterface.OnClickListener) new UiTools$$ExternalSyntheticLambda22()).setNegativeButton(R.string.restart_message_Later, (DialogInterface.OnClickListener) null).create().show();
        }
    }

    /* access modifiers changed from: private */
    public static final void restartDialog$lambda$26(DialogInterface dialogInterface, int i) {
        Process.killProcess(Process.myPid());
    }

    public final void deleteSubtitleDialog(Context context, DialogInterface.OnClickListener onClickListener, DialogInterface.OnClickListener onClickListener2) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(onClickListener, "positiveListener");
        Intrinsics.checkNotNullParameter(onClickListener2, "negativeListener");
        new AlertDialog.Builder(context).setTitle((CharSequence) context.getResources().getString(R.string.delete_sub_title)).setMessage((CharSequence) context.getResources().getString(R.string.delete_sub_message)).setPositiveButton(R.string.delete, onClickListener).setNegativeButton(R.string.cancel, onClickListener2).create().show();
    }

    public final void invalidateBitmaps() {
        DEFAULT_COVER_VIDEO_DRAWABLE = null;
    }

    public final void addFavoritesIcon(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        textView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, ContextCompat.getDrawable(textView.getContext(), R.drawable.ic_emoji_favorite), (Drawable) null);
    }

    public final void removeDrawables(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        textView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
    }

    public final boolean hasSecondaryDisplay(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = ContextCompat.getSystemService(context, MediaRouter.class);
        Intrinsics.checkNotNull(systemService);
        MediaRouter.RouteInfo selectedRoute = ((MediaRouter) systemService).getSelectedRoute(2);
        return (selectedRoute != null ? selectedRoute.getPresentationDisplay() : null) != null;
    }
}
