package org.videolan.vlc.media;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.channels.ActorKt;
import kotlinx.coroutines.channels.SendChannel;
import org.videolan.medialibrary.MLServiceLocator;
import org.videolan.medialibrary.Tools;
import org.videolan.medialibrary.interfaces.media.Album;
import org.videolan.medialibrary.interfaces.media.Artist;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.Playlist;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.resources.AppContextProvider;
import org.videolan.resources.Constants;
import org.videolan.tools.AppScope;
import org.videolan.tools.Strings;
import org.videolan.vlc.ArtworkProvider;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.DialogActivity;
import org.videolan.vlc.gui.dialogs.SubtitleDownloaderDialogFragment;
import org.videolan.vlc.providers.medialibrary.FoldersProvider;
import org.videolan.vlc.providers.medialibrary.MedialibraryProvider;
import org.videolan.vlc.providers.medialibrary.VideoGroupsProvider;
import org.videolan.vlc.util.BrowserutilsKt;
import org.videolan.vlc.util.FileUtils;
import org.videolan.vlc.util.KextensionsKt;
import org.videolan.vlc.util.Permissions;
import org.videolan.vlc.util.TextUtils;

@Metadata(d1 = {"\u0000¦\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002[\\B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J!\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\u0010\nJ \u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\fJ\u001a\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\tJ*\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00040\u0013J(\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00112\u0010\b\u0002\u0010\u0017\u001a\n\u0018\u00010\u0018j\u0004\u0018\u0001`\u0019H@¢\u0006\u0002\u0010\u001aJ\u000e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eJ\u0010\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010!\u001a\u00020 J&\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&J\u0018\u0010(\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\tJ\u0018\u0010)\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\tJ\u0018\u0010*\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\tJ\u0018\u0010+\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\tJ\u0018\u0010,\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\tJ\u001a\u0010-\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u00062\u0006\u0010.\u001a\u00020&H\u0002J\u000e\u0010/\u001a\u00020#2\u0006\u0010\u000b\u001a\u00020\tJ\u000e\u00100\u001a\u00020#2\u0006\u00101\u001a\u00020\tJ\u001c\u00102\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\f\u00103\u001a\b\u0012\u0004\u0012\u00020\t0\fJ\u0016\u00102\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\tJ%\u00104\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\u0002\u0010\nJ\u001a\u00104\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\tJ\u0018\u00105\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u00106\u001a\u00020&J2\u00107\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\f\u00108\u001a\b\u0012\u0004\u0012\u00020\t0\f2\u0006\u00109\u001a\u00020&2\b\b\u0002\u0010:\u001a\u00020\u0015H\u0007J\u001a\u0010;\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\tJ\u0016\u0010<\u001a\u00020\u001c2\u0006\u0010$\u001a\u00020\u00062\u0006\u0010.\u001a\u00020=J\u001a\u0010<\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\tJ\u000e\u0010<\u001a\u00020\u00042\u0006\u0010>\u001a\u00020 J\u0018\u0010?\u001a\u00020\u001c2\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010!\u001a\u0004\u0018\u00010 J.\u0010@\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010A\u001a\u00020=2\b\b\u0002\u00109\u001a\u00020&2\b\b\u0002\u0010:\u001a\u00020\u0015H\u0007J\u001a\u0010B\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010>\u001a\u0004\u0018\u00010#J\u001a\u0010C\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010>\u001a\u0004\u0018\u00010 J\u001a\u0010D\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010E\u001a\u00020FH\u0002J0\u0010G\u001a\u0004\u0018\u00010\u001c2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\f\u0010H\u001a\b\u0012\u0004\u0012\u00020F0I2\u0006\u00109\u001a\u00020&2\u0006\u0010:\u001a\u00020\u0015J0\u0010J\u001a\u0004\u0018\u00010\u001c2\b\u0010\u0005\u001a\u0004\u0018\u00010K2\f\u0010H\u001a\b\u0012\u0004\u0012\u00020\t0I2\u0006\u00109\u001a\u00020&2\u0006\u0010:\u001a\u00020\u0015J*\u0010L\u001a\u0004\u0018\u00010\u001c2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010H\u001a\u00020M2\u0006\u00109\u001a\u00020&2\u0006\u0010:\u001a\u00020\u0015J,\u0010L\u001a\u0004\u0018\u00010\u001c2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010H\u001a\u00020N2\b\u0010O\u001a\u0004\u0018\u00010\t2\u0006\u0010:\u001a\u00020\u0015J\u001a\u0010P\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010Q\u001a\u00020RH\u0002J(\u0010S\u001a\u00020\u001c2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u00109\u001a\u00020&2\b\b\u0002\u0010:\u001a\u00020\u0015J.\u0010S\u001a\u00020\u001c2\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010H\u001a\b\u0012\u0004\u0012\u00020\t0I2\u0006\u00109\u001a\u00020&2\b\b\u0002\u0010:\u001a\u00020\u0015J\u0015\u0010T\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0016\u001a\u00020\t¢\u0006\u0002\u0010UJ*\u0010V\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010W\u001a\b\u0012\u0004\u0012\u00020 0\f2\f\u0010X\u001a\b\u0012\u0004\u0012\u00020#0\fJ\u001e\u0010Y\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010>\u001a\u00020 H@¢\u0006\u0002\u0010Z¨\u0006]"}, d2 = {"Lorg/videolan/vlc/media/MediaUtils;", "", "()V", "appendMedia", "", "context", "Landroid/content/Context;", "array", "", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "(Landroid/content/Context;[Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;)V", "media", "", "deleteItem", "activity", "Landroidx/fragment/app/FragmentActivity;", "item", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "onDeleteFailed", "Lkotlin/Function1;", "deleteMedia", "", "mw", "failCB", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "(Lorg/videolan/medialibrary/media/MediaLibraryItem;Ljava/lang/Runnable;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deletePlaylist", "Lkotlinx/coroutines/Job;", "playlist", "Lorg/videolan/medialibrary/interfaces/media/Playlist;", "getContentMediaUri", "Landroid/net/Uri;", "data", "getDisplaySubtitle", "", "ctx", "mediaPosition", "", "mediaSize", "getMediaAlbum", "getMediaAlbumArtist", "getMediaArtist", "getMediaGenre", "getMediaReferenceArtist", "getMediaString", "id", "getMediaSubtitle", "getMediaTitle", "mediaWrapper", "getSubs", "mediaList", "insertNext", "loadlastPlaylist", "type", "openList", "list", "position", "shuffle", "openMedia", "openMediaNoUi", "", "uri", "openMediaNoUiFromTvContent", "openPlaylist", "playlistId", "openStream", "openUri", "playAlbum", "album", "Lorg/videolan/medialibrary/interfaces/media/Album;", "playAlbums", "provider", "Lorg/videolan/vlc/providers/medialibrary/MedialibraryProvider;", "playAll", "Landroid/app/Activity;", "playAllTracks", "Lorg/videolan/vlc/providers/medialibrary/FoldersProvider;", "Lorg/videolan/vlc/providers/medialibrary/VideoGroupsProvider;", "mediaToPlay", "playArtist", "artist", "Lorg/videolan/medialibrary/interfaces/media/Artist;", "playTracks", "retrieveMediaTitle", "(Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;)Lkotlin/Unit;", "showSubtitleDownloaderDialogFragment", "mediaUris", "mediaTitles", "useAsSoundFont", "(Landroid/content/Context;Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "BaseCallBack", "SuspendDialogCallback", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaUtils.kt */
public final class MediaUtils {
    public static final MediaUtils INSTANCE = new MediaUtils();

    public final void openList(Context context, List<? extends MediaWrapper> list, int i) {
        Intrinsics.checkNotNullParameter(list, "list");
        openList$default(this, context, list, i, false, 8, (Object) null);
    }

    public final void openPlaylist(Context context, long j) {
        openPlaylist$default(this, context, j, 0, false, 12, (Object) null);
    }

    public final void openPlaylist(Context context, long j, int i) {
        openPlaylist$default(this, context, j, i, false, 8, (Object) null);
    }

    private MediaUtils() {
    }

    public final void getSubs(FragmentActivity fragmentActivity, List<? extends MediaWrapper> list) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "activity");
        Intrinsics.checkNotNullParameter(list, "mediaList");
        if (fragmentActivity instanceof AppCompatActivity) {
            Iterable<MediaWrapper> iterable = list;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (MediaWrapper uri : iterable) {
                arrayList.add(uri.getUri());
            }
            List list2 = (List) arrayList;
            Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (MediaWrapper title : iterable) {
                arrayList2.add(title.getTitle());
            }
            showSubtitleDownloaderDialogFragment(fragmentActivity, list2, (List) arrayList2);
            return;
        }
        Context context = fragmentActivity;
        Intent addFlags = new Intent(context, DialogActivity.class).setAction(DialogActivity.KEY_SUBS_DL).addFlags(268435456);
        Intrinsics.checkNotNullExpressionValue(addFlags, "addFlags(...)");
        ArrayList arrayList3 = list instanceof ArrayList ? (ArrayList) list : null;
        if (arrayList3 == null) {
            arrayList3 = new ArrayList(list);
        }
        addFlags.putParcelableArrayListExtra(DialogActivity.EXTRA_MEDIALIST, arrayList3);
        ContextCompat.startActivity(context, addFlags, (Bundle) null);
    }

    public final void getSubs(FragmentActivity fragmentActivity, MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "activity");
        Intrinsics.checkNotNullParameter(mediaWrapper, "media");
        getSubs(fragmentActivity, (List<? extends MediaWrapper>) CollectionsKt.listOf(mediaWrapper));
    }

    public final void showSubtitleDownloaderDialogFragment(FragmentActivity fragmentActivity, List<? extends Uri> list, List<String> list2) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "activity");
        Intrinsics.checkNotNullParameter(list, "mediaUris");
        Intrinsics.checkNotNullParameter(list2, "mediaTitles");
        SubtitleDownloaderDialogFragment.Companion.newInstance(list, list2).show(fragmentActivity.getSupportFragmentManager(), "Subtitle_downloader");
    }

    public final void deleteItem(FragmentActivity fragmentActivity, MediaLibraryItem mediaLibraryItem, Function1<? super MediaLibraryItem, Unit> function1) {
        Runnable runnable;
        Intrinsics.checkNotNullParameter(fragmentActivity, "activity");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
        Intrinsics.checkNotNullParameter(function1, "onDeleteFailed");
        boolean z = mediaLibraryItem instanceof MediaWrapper;
        if (z || (mediaLibraryItem instanceof Album)) {
            runnable = new MediaUtils$deleteItem$$inlined$Runnable$1(fragmentActivity, mediaLibraryItem, function1);
        } else if (mediaLibraryItem instanceof Playlist) {
            runnable = new MediaUtils$deleteItem$$inlined$Runnable$2(mediaLibraryItem);
        } else {
            runnable = new MediaUtils$deleteItem$$inlined$Runnable$3(function1, mediaLibraryItem);
        }
        if (!z) {
            runnable.run();
        } else if (Permissions.INSTANCE.checkWritePermission(fragmentActivity, (MediaWrapper) mediaLibraryItem, runnable)) {
            runnable.run();
        }
    }

    public static /* synthetic */ Object deleteMedia$default(MediaUtils mediaUtils, MediaLibraryItem mediaLibraryItem, Runnable runnable, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            runnable = null;
        }
        return mediaUtils.deleteMedia(mediaLibraryItem, runnable, continuation);
    }

    public final Object deleteMedia(MediaLibraryItem mediaLibraryItem, Runnable runnable, Continuation<? super Boolean> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new MediaUtils$deleteMedia$2(mediaLibraryItem, runnable, (Continuation<? super MediaUtils$deleteMedia$2>) null), continuation);
    }

    public final void loadlastPlaylist(Context context, int i) {
        if (context != null) {
            new SuspendDialogCallback(context, new MediaUtils$loadlastPlaylist$1(i, (Continuation<? super MediaUtils$loadlastPlaylist$1>) null));
        }
    }

    public final void appendMedia(Context context, List<? extends MediaWrapper> list) {
        if (list != null && !list.isEmpty() && context != null) {
            new SuspendDialogCallback(context, new MediaUtils$appendMedia$1(list, context, (Continuation<? super MediaUtils$appendMedia$1>) null));
        }
    }

    public final void appendMedia(Context context, MediaWrapper mediaWrapper) {
        if (mediaWrapper != null) {
            appendMedia(context, (List<? extends MediaWrapper>) CollectionsKt.arrayListOf(mediaWrapper));
        }
    }

    public final void appendMedia(Context context, MediaWrapper[] mediaWrapperArr) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mediaWrapperArr, "array");
        appendMedia(context, (List<? extends MediaWrapper>) ArraysKt.asList((T[]) mediaWrapperArr));
    }

    public final void insertNext(Context context, MediaWrapper[] mediaWrapperArr) {
        if (mediaWrapperArr != null && context != null) {
            new SuspendDialogCallback(context, new MediaUtils$insertNext$1(mediaWrapperArr, context, (Continuation<? super MediaUtils$insertNext$1>) null));
        }
    }

    public final void insertNext(Context context, MediaWrapper mediaWrapper) {
        if (mediaWrapper != null && context != null) {
            insertNext(context, new MediaWrapper[]{mediaWrapper});
        }
    }

    public final void openMedia(Context context, MediaWrapper mediaWrapper) {
        if (mediaWrapper != null && context != null) {
            new SuspendDialogCallback(context, new MediaUtils$openMedia$1(mediaWrapper, (Continuation<? super MediaUtils$openMedia$1>) null));
        }
    }

    public final Job openMediaNoUi(Context context, long j) {
        Intrinsics.checkNotNullParameter(context, "ctx");
        return BuildersKt__Builders_commonKt.launch$default(AppScope.INSTANCE, (CoroutineContext) null, (CoroutineStart) null, new MediaUtils$openMediaNoUi$1(context, j, (Continuation<? super MediaUtils$openMediaNoUi$1>) null), 3, (Object) null);
    }

    public final void openMediaNoUi(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, Constants.KEY_URI);
        openMediaNoUi(AppContextProvider.INSTANCE.getAppContext(), MLServiceLocator.getAbstractMediaWrapper(uri));
    }

    public final void openMediaNoUi(Context context, MediaWrapper mediaWrapper) {
        if (mediaWrapper != null && context != null) {
            new MediaUtils$openMediaNoUi$2(context, mediaWrapper);
        }
    }

    public static /* synthetic */ Job playTracks$default(MediaUtils mediaUtils, Context context, MediaLibraryItem mediaLibraryItem, int i, boolean z, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            z = false;
        }
        return mediaUtils.playTracks(context, mediaLibraryItem, i, z);
    }

    public final Job playTracks(Context context, MediaLibraryItem mediaLibraryItem, int i, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
        return BuildersKt__Builders_commonKt.launch$default(MediaUtilsKt.getScope(context), (CoroutineContext) null, (CoroutineStart) null, new MediaUtils$playTracks$1(context, i, z, mediaLibraryItem, (Continuation<? super MediaUtils$playTracks$1>) null), 3, (Object) null);
    }

    public static /* synthetic */ Job playTracks$default(MediaUtils mediaUtils, Context context, MedialibraryProvider medialibraryProvider, int i, boolean z, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            z = false;
        }
        return mediaUtils.playTracks(context, (MedialibraryProvider<MediaWrapper>) medialibraryProvider, i, z);
    }

    public final Job playTracks(Context context, MedialibraryProvider<MediaWrapper> medialibraryProvider, int i, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(medialibraryProvider, "provider");
        return BuildersKt__Builders_commonKt.launch$default(MediaUtilsKt.getScope(context), (CoroutineContext) null, (CoroutineStart) null, new MediaUtils$playTracks$2(medialibraryProvider, context, i, z, (Continuation<? super MediaUtils$playTracks$2>) null), 3, (Object) null);
    }

    public final Job playAlbums(Context context, MedialibraryProvider<Album> medialibraryProvider, int i, boolean z) {
        CoroutineScope access$getScope;
        Intrinsics.checkNotNullParameter(medialibraryProvider, "provider");
        if (context == null || (access$getScope = MediaUtilsKt.getScope(context)) == null) {
            return null;
        }
        return BuildersKt__Builders_commonKt.launch$default(access$getScope, (CoroutineContext) null, (CoroutineStart) null, new MediaUtils$playAlbums$1(medialibraryProvider, context, z, i, (Continuation<? super MediaUtils$playAlbums$1>) null), 3, (Object) null);
    }

    public final Job playAll(Activity activity, MedialibraryProvider<MediaWrapper> medialibraryProvider, int i, boolean z) {
        CoroutineScope access$getScope;
        Intrinsics.checkNotNullParameter(medialibraryProvider, "provider");
        if (activity == null || (access$getScope = MediaUtilsKt.getScope(activity)) == null) {
            return null;
        }
        return BuildersKt__Builders_commonKt.launch$default(access$getScope, (CoroutineContext) null, (CoroutineStart) null, new MediaUtils$playAll$1(medialibraryProvider, activity, z, i, (Continuation<? super MediaUtils$playAll$1>) null), 3, (Object) null);
    }

    public final Job playAllTracks(Context context, VideoGroupsProvider videoGroupsProvider, MediaWrapper mediaWrapper, boolean z) {
        CoroutineScope access$getScope;
        Intrinsics.checkNotNullParameter(videoGroupsProvider, "provider");
        if (context == null || (access$getScope = MediaUtilsKt.getScope(context)) == null) {
            return null;
        }
        return BuildersKt__Builders_commonKt.launch$default(access$getScope, (CoroutineContext) null, (CoroutineStart) null, new MediaUtils$playAllTracks$1(videoGroupsProvider, context, z, mediaWrapper, (Continuation<? super MediaUtils$playAllTracks$1>) null), 3, (Object) null);
    }

    public final Job playAllTracks(Context context, FoldersProvider foldersProvider, int i, boolean z) {
        CoroutineScope access$getScope;
        Intrinsics.checkNotNullParameter(foldersProvider, "provider");
        if (context == null || (access$getScope = MediaUtilsKt.getScope(context)) == null) {
            return null;
        }
        return BuildersKt__Builders_commonKt.launch$default(access$getScope, (CoroutineContext) null, (CoroutineStart) null, new MediaUtils$playAllTracks$2(context, foldersProvider, z, i, (Continuation<? super MediaUtils$playAllTracks$2>) null), 3, (Object) null);
    }

    public static /* synthetic */ void openList$default(MediaUtils mediaUtils, Context context, List list, int i, boolean z, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            z = false;
        }
        mediaUtils.openList(context, list, i, z);
    }

    public final void openList(Context context, List<? extends MediaWrapper> list, int i, boolean z) {
        Intrinsics.checkNotNullParameter(list, "list");
        if (!list.isEmpty() && context != null) {
            new SuspendDialogCallback(context, new MediaUtils$openList$1(list, i, z, (Continuation<? super MediaUtils$openList$1>) null));
        }
    }

    public static /* synthetic */ void openPlaylist$default(MediaUtils mediaUtils, Context context, long j, int i, boolean z, int i2, Object obj) {
        mediaUtils.openPlaylist(context, j, (i2 & 4) != 0 ? 0 : i, (i2 & 8) != 0 ? false : z);
    }

    public final void openPlaylist(Context context, long j, int i, boolean z) {
        if (j != -1 && context != null) {
            new SuspendDialogCallback(context, new MediaUtils$openPlaylist$1(context, i, z, j, (Continuation<? super MediaUtils$openPlaylist$1>) null));
        }
    }

    public final void openUri(Context context, Uri uri) {
        if (uri != null && context != null) {
            new SuspendDialogCallback(context, new MediaUtils$openUri$1(uri, (Continuation<? super MediaUtils$openUri$1>) null));
        }
    }

    public final void openStream(Context context, String str) {
        if (str != null && context != null) {
            new SuspendDialogCallback(context, new MediaUtils$openStream$1(str, (Continuation<? super MediaUtils$openStream$1>) null));
        }
    }

    public final String getMediaArtist(Context context, MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(context, "ctx");
        if (mediaWrapper == null) {
            return getMediaString(context, R.string.unknown_artist);
        }
        if (mediaWrapper.getType() != 0) {
            if (mediaWrapper.getArtist() != null) {
                String artist = mediaWrapper.getArtist();
                Intrinsics.checkNotNullExpressionValue(artist, "getArtist(...)");
                return artist;
            } else if (mediaWrapper.getNowPlaying() != null) {
                String title = mediaWrapper.getTitle();
                Intrinsics.checkNotNullExpressionValue(title, "getTitle(...)");
                return title;
            } else if (!BrowserutilsKt.isSchemeStreaming(mediaWrapper.getUri().getScheme())) {
                return getMediaString(context, R.string.unknown_artist);
            }
        }
        return "";
    }

    public final String getMediaReferenceArtist(Context context, MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(context, "ctx");
        return getMediaArtist(context, mediaWrapper);
    }

    public final String getMediaAlbumArtist(Context context, MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(context, "ctx");
        String albumArtist = mediaWrapper != null ? mediaWrapper.getAlbumArtist() : null;
        return albumArtist == null ? getMediaString(context, R.string.unknown_artist) : albumArtist;
    }

    public final String getMediaAlbum(Context context, MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(context, "ctx");
        if (mediaWrapper == null) {
            return getMediaString(context, R.string.unknown_album);
        }
        if (mediaWrapper.getAlbum() != null) {
            String album = mediaWrapper.getAlbum();
            Intrinsics.checkNotNullExpressionValue(album, "getAlbum(...)");
            return album;
        } else if (mediaWrapper.getNowPlaying() == null && !BrowserutilsKt.isSchemeStreaming(mediaWrapper.getUri().getScheme())) {
            return getMediaString(context, R.string.unknown_album);
        } else {
            return "";
        }
    }

    public final String getMediaGenre(Context context, MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(context, "ctx");
        String genre = mediaWrapper != null ? mediaWrapper.getGenre() : null;
        return genre == null ? getMediaString(context, R.string.unknown_genre) : genre;
    }

    public final String getMediaSubtitle(MediaWrapper mediaWrapper) {
        String str;
        String str2;
        Intrinsics.checkNotNullParameter(mediaWrapper, "media");
        if (mediaWrapper.getLength() <= 0) {
            str = null;
        } else if (mediaWrapper.getType() == 0) {
            str = Tools.millisToText(mediaWrapper.getLength());
        } else {
            str = Tools.millisToString(mediaWrapper.getLength());
        }
        if (mediaWrapper.getType() == 0) {
            str2 = KextensionsKt.generateResolutionClass(mediaWrapper.getWidth(), mediaWrapper.getHeight());
        } else if (mediaWrapper.getLength() > 0) {
            str2 = mediaWrapper.getArtist();
        } else if (BrowserutilsKt.isSchemeStreaming(mediaWrapper.getUri().getScheme())) {
            str2 = mediaWrapper.getUri().toString();
        } else {
            str2 = mediaWrapper.getArtist();
        }
        return TextUtils.INSTANCE.separatedStringArgs(str, str2);
    }

    public final String getDisplaySubtitle(Context context, MediaWrapper mediaWrapper, int i, int i2) {
        String str;
        Intrinsics.checkNotNullParameter(context, "ctx");
        Intrinsics.checkNotNullParameter(mediaWrapper, "media");
        String mediaAlbum = getMediaAlbum(context, mediaWrapper);
        String mediaArtist = getMediaArtist(context, mediaWrapper);
        boolean areEqual = Intrinsics.areEqual((Object) mediaAlbum, (Object) getMediaString(context, R.string.unknown_album));
        boolean areEqual2 = Intrinsics.areEqual((Object) mediaArtist, (Object) getMediaString(context, R.string.unknown_artist));
        String str2 = null;
        if (i2 > 1) {
            str = (i + 1) + " / " + i2;
        } else {
            str = null;
        }
        if (!areEqual2 && !areEqual) {
            str2 = TextUtils.INSTANCE.separatedStringArgs('-', Strings.markBidi(mediaArtist), Strings.markBidi(mediaAlbum));
        }
        return TextUtils.INSTANCE.separatedStringArgs(str, str2);
    }

    public final String getMediaTitle(MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(mediaWrapper, "mediaWrapper");
        String title = mediaWrapper.getTitle();
        return title == null ? FileUtils.INSTANCE.getFileNameFromPath(mediaWrapper.getLocation()) : title;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0048, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004c, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.net.Uri getContentMediaUri(android.net.Uri r9) {
        /*
            r8 = this;
            java.lang.String r0 = "_data"
            java.lang.String r1 = "data"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r1)
            org.videolan.resources.AppContextProvider r1 = org.videolan.resources.AppContextProvider.INSTANCE     // Catch:{ IllegalArgumentException | NullPointerException | SecurityException -> 0x004e }
            android.content.Context r1 = r1.getAppContext()     // Catch:{ IllegalArgumentException | NullPointerException | SecurityException -> 0x004e }
            android.content.ContentResolver r2 = r1.getContentResolver()     // Catch:{ IllegalArgumentException | NullPointerException | SecurityException -> 0x004e }
            r1 = 1
            java.lang.String[] r4 = new java.lang.String[r1]     // Catch:{ IllegalArgumentException | NullPointerException | SecurityException -> 0x004e }
            r1 = 0
            r4[r1] = r0     // Catch:{ IllegalArgumentException | NullPointerException | SecurityException -> 0x004e }
            r6 = 0
            r7 = 0
            r5 = 0
            r3 = r9
            android.database.Cursor r1 = r2.query(r3, r4, r5, r6, r7)     // Catch:{ IllegalArgumentException | NullPointerException | SecurityException -> 0x004e }
            r2 = 0
            if (r1 == 0) goto L_0x004d
            java.io.Closeable r1 = (java.io.Closeable) r1     // Catch:{ IllegalArgumentException | NullPointerException | SecurityException -> 0x004e }
            r3 = r1
            android.database.Cursor r3 = (android.database.Cursor) r3     // Catch:{ all -> 0x0046 }
            int r0 = r3.getColumnIndexOrThrow(r0)     // Catch:{ all -> 0x0046 }
            boolean r4 = r3.moveToFirst()     // Catch:{ all -> 0x0046 }
            if (r4 == 0) goto L_0x0040
            java.lang.String r0 = r3.getString(r0)     // Catch:{ all -> 0x0046 }
            android.net.Uri r0 = org.videolan.libvlc.util.AndroidUtil.PathToUri(r0)     // Catch:{ all -> 0x0046 }
            if (r0 != 0) goto L_0x003c
            goto L_0x0040
        L_0x003c:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)     // Catch:{ all -> 0x0046 }
            goto L_0x0041
        L_0x0040:
            r0 = r9
        L_0x0041:
            kotlin.io.CloseableKt.closeFinally(r1, r2)     // Catch:{ IllegalArgumentException | NullPointerException | SecurityException -> 0x004e }
            r9 = r0
            goto L_0x004e
        L_0x0046:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0048 }
        L_0x0048:
            r2 = move-exception
            kotlin.io.CloseableKt.closeFinally(r1, r0)     // Catch:{ IllegalArgumentException | NullPointerException | SecurityException -> 0x004e }
            throw r2     // Catch:{ IllegalArgumentException | NullPointerException | SecurityException -> 0x004e }
        L_0x004d:
            r9 = r2
        L_0x004e:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.media.MediaUtils.getContentMediaUri(android.net.Uri):android.net.Uri");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:1:0x0002, code lost:
        r1 = r1.getResources();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.String getMediaString(android.content.Context r1, int r2) {
        /*
            r0 = this;
            if (r1 == 0) goto L_0x000d
            android.content.res.Resources r1 = r1.getResources()
            if (r1 == 0) goto L_0x000d
            java.lang.String r1 = r1.getString(r2)
            goto L_0x000e
        L_0x000d:
            r1 = 0
        L_0x000e:
            if (r1 != 0) goto L_0x0027
            int r1 = org.videolan.vlc.R.string.unknown_artist
            if (r2 != r1) goto L_0x0017
            java.lang.String r1 = "Unknown Artist"
            goto L_0x0027
        L_0x0017:
            int r1 = org.videolan.vlc.R.string.unknown_album
            if (r2 != r1) goto L_0x001e
            java.lang.String r1 = "Unknown Album"
            goto L_0x0027
        L_0x001e:
            int r1 = org.videolan.vlc.R.string.unknown_genre
            if (r2 != r1) goto L_0x0025
            java.lang.String r1 = "Unknown Genre"
            goto L_0x0027
        L_0x0025:
            java.lang.String r1 = ""
        L_0x0027:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.media.MediaUtils.getMediaString(android.content.Context, int):java.lang.String");
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\"\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, d2 = {"Lorg/videolan/vlc/media/MediaUtils$BaseCallBack;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "onServiceReady", "", "service", "Lorg/videolan/vlc/PlaybackService;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MediaUtils.kt */
    private static abstract class BaseCallBack {
        public abstract void onServiceReady(PlaybackService playbackService);

        public BaseCallBack(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Job unused = BuildersKt__Builders_commonKt.launch$default(AppScope.INSTANCE, (CoroutineContext) null, (CoroutineStart) null, new Function2<CoroutineScope, Continuation<? super Unit>, Object>(this, (Continuation<? super AnonymousClass1>) null) {
                Object L$0;
                int label;
                final /* synthetic */ BaseCallBack this$0;

                {
                    this.this$0 = r1;
                }

                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return 

                    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B@\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00121\u0010\u0004\u001a-\b\u0001\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005¢\u0006\u0002\u0010\fJ\b\u0010!\u001a\u00020\u000bH\u0002R\u001d\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X.¢\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u00020\u0017X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR;\u0010\u0004\u001a-\b\u0001\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005X\u0004¢\u0006\u0004\n\u0002\u0010 ¨\u0006\""}, d2 = {"Lorg/videolan/vlc/media/MediaUtils$SuspendDialogCallback;", "", "context", "Landroid/content/Context;", "task", "Lkotlin/Function2;", "Lorg/videolan/vlc/PlaybackService;", "Lkotlin/ParameterName;", "name", "service", "Lkotlin/coroutines/Continuation;", "", "(Landroid/content/Context;Lkotlin/jvm/functions/Function2;)V", "actor", "Lkotlinx/coroutines/channels/SendChannel;", "Lorg/videolan/vlc/media/Action;", "getActor$annotations", "()V", "getActor", "()Lkotlinx/coroutines/channels/SendChannel;", "dialog", "Landroid/app/ProgressDialog;", "job", "Lkotlinx/coroutines/Job;", "getJob", "()Lkotlinx/coroutines/Job;", "setJob", "(Lkotlinx/coroutines/Job;)V", "scope", "Lkotlinx/coroutines/CoroutineScope;", "getScope", "()Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/jvm/functions/Function2;", "dismiss", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
                    /* compiled from: MediaUtils.kt */
                    public static final class SuspendDialogCallback {
                        private final SendChannel<Action> actor;
                        /* access modifiers changed from: private */
                        public ProgressDialog dialog;
                        private Job job = JobKt.Job$default((Job) null, 1, (Object) null);
                        private final CoroutineScope scope;
                        /* access modifiers changed from: private */
                        public final Function2<PlaybackService, Continuation<? super Unit>, Object> task;

                        public static /* synthetic */ void getActor$annotations() {
                        }

                        public SuspendDialogCallback(final Context context, Function2<? super PlaybackService, ? super Continuation<? super Unit>, ? extends Object> function2) {
                            Intrinsics.checkNotNullParameter(context, "context");
                            Intrinsics.checkNotNullParameter(function2, "task");
                            this.task = function2;
                            CoroutineScope access$getScope = MediaUtilsKt.getScope(context);
                            this.scope = access$getScope;
                            SendChannel<Action> actor$default = ActorKt.actor$default(access$getScope, (CoroutineContext) null, Integer.MAX_VALUE, (CoroutineStart) null, (Function1) null, new MediaUtils$SuspendDialogCallback$actor$1(this, context, (Continuation<? super MediaUtils$SuspendDialogCallback$actor$1>) null), 13, (Object) null);
                            this.actor = actor$default;
                            this.job = BuildersKt__Builders_commonKt.launch$default(access$getScope, (CoroutineContext) null, (CoroutineStart) null, new Function2<CoroutineScope, Continuation<? super Unit>, Object>(this, (Continuation<? super AnonymousClass1>) null) {
                                int label;
                                final /* synthetic */ SuspendDialogCallback this$0;

                                {
                                    this.this$0 = r1;
                                }

                                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                    return 

                                    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004c, code lost:
                                        r1 = move-exception;
                                     */
                                    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
                                        kotlin.io.CloseableKt.closeFinally(r0, r8);
                                     */
                                    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0050, code lost:
                                        throw r1;
                                     */
                                    /* Code decompiled incorrectly, please refer to instructions dump. */
                                    public final kotlin.Unit retrieveMediaTitle(org.videolan.medialibrary.interfaces.media.MediaWrapper r8) {
                                        /*
                                            r7 = this;
                                            java.lang.String r0 = "mw"
                                            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
                                            org.videolan.resources.AppContextProvider r0 = org.videolan.resources.AppContextProvider.INSTANCE     // Catch:{ UnsupportedOperationException -> 0x005d, IllegalArgumentException -> 0x005a, NullPointerException -> 0x0057, IllegalStateException -> 0x0054, SecurityException -> 0x0051 }
                                            android.content.Context r0 = r0.getAppContext()     // Catch:{ UnsupportedOperationException -> 0x005d, IllegalArgumentException -> 0x005a, NullPointerException -> 0x0057, IllegalStateException -> 0x0054, SecurityException -> 0x0051 }
                                            android.content.ContentResolver r1 = r0.getContentResolver()     // Catch:{ UnsupportedOperationException -> 0x005d, IllegalArgumentException -> 0x005a, NullPointerException -> 0x0057, IllegalStateException -> 0x0054, SecurityException -> 0x0051 }
                                            android.net.Uri r2 = r8.getUri()     // Catch:{ UnsupportedOperationException -> 0x005d, IllegalArgumentException -> 0x005a, NullPointerException -> 0x0057, IllegalStateException -> 0x0054, SecurityException -> 0x0051 }
                                            r5 = 0
                                            r6 = 0
                                            r3 = 0
                                            r4 = 0
                                            android.database.Cursor r0 = r1.query(r2, r3, r4, r5, r6)     // Catch:{ UnsupportedOperationException -> 0x005d, IllegalArgumentException -> 0x005a, NullPointerException -> 0x0057, IllegalStateException -> 0x0054, SecurityException -> 0x0051 }
                                            r1 = 0
                                            if (r0 == 0) goto L_0x005f
                                            java.io.Closeable r0 = (java.io.Closeable) r0     // Catch:{ UnsupportedOperationException -> 0x005d, IllegalArgumentException -> 0x005a, NullPointerException -> 0x0057, IllegalStateException -> 0x0054, SecurityException -> 0x0051 }
                                            r2 = r0
                                            android.database.Cursor r2 = (android.database.Cursor) r2     // Catch:{ all -> 0x004a }
                                            java.lang.String r3 = "_display_name"
                                            int r3 = r2.getColumnIndex(r3)     // Catch:{ all -> 0x004a }
                                            r4 = -1
                                            if (r3 <= r4) goto L_0x0042
                                            int r4 = r2.getCount()     // Catch:{ all -> 0x004a }
                                            if (r4 <= 0) goto L_0x0042
                                            r2.moveToFirst()     // Catch:{ all -> 0x004a }
                                            boolean r4 = r2.isNull(r3)     // Catch:{ all -> 0x004a }
                                            if (r4 != 0) goto L_0x0042
                                            java.lang.String r2 = r2.getString(r3)     // Catch:{ all -> 0x004a }
                                            r8.setTitle(r2)     // Catch:{ all -> 0x004a }
                                        L_0x0042:
                                            kotlin.Unit r8 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x004a }
                                            kotlin.io.CloseableKt.closeFinally(r0, r1)     // Catch:{ UnsupportedOperationException -> 0x005d, IllegalArgumentException -> 0x005a, NullPointerException -> 0x0057, IllegalStateException -> 0x0054, SecurityException -> 0x0051 }
                                            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ UnsupportedOperationException -> 0x005d, IllegalArgumentException -> 0x005a, NullPointerException -> 0x0057, IllegalStateException -> 0x0054, SecurityException -> 0x0051 }
                                            goto L_0x005f
                                        L_0x004a:
                                            r8 = move-exception
                                            throw r8     // Catch:{ all -> 0x004c }
                                        L_0x004c:
                                            r1 = move-exception
                                            kotlin.io.CloseableKt.closeFinally(r0, r8)     // Catch:{ UnsupportedOperationException -> 0x005d, IllegalArgumentException -> 0x005a, NullPointerException -> 0x0057, IllegalStateException -> 0x0054, SecurityException -> 0x0051 }
                                            throw r1     // Catch:{ UnsupportedOperationException -> 0x005d, IllegalArgumentException -> 0x005a, NullPointerException -> 0x0057, IllegalStateException -> 0x0054, SecurityException -> 0x0051 }
                                        L_0x0051:
                                            kotlin.Unit r1 = kotlin.Unit.INSTANCE
                                            goto L_0x005f
                                        L_0x0054:
                                            kotlin.Unit r1 = kotlin.Unit.INSTANCE
                                            goto L_0x005f
                                        L_0x0057:
                                            kotlin.Unit r1 = kotlin.Unit.INSTANCE
                                            goto L_0x005f
                                        L_0x005a:
                                            kotlin.Unit r1 = kotlin.Unit.INSTANCE
                                            goto L_0x005f
                                        L_0x005d:
                                            kotlin.Unit r1 = kotlin.Unit.INSTANCE
                                        L_0x005f:
                                            return r1
                                        */
                                        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.media.MediaUtils.retrieveMediaTitle(org.videolan.medialibrary.interfaces.media.MediaWrapper):kotlin.Unit");
                                    }

                                    public final Job deletePlaylist(Playlist playlist) {
                                        Intrinsics.checkNotNullParameter(playlist, ArtworkProvider.PLAYLIST);
                                        return BuildersKt__Builders_commonKt.launch$default(AppScope.INSTANCE, Dispatchers.getIO(), (CoroutineStart) null, new MediaUtils$deletePlaylist$1(playlist, (Continuation<? super MediaUtils$deletePlaylist$1>) null), 2, (Object) null);
                                    }

                                    public final Job openMediaNoUiFromTvContent(Context context, Uri uri) {
                                        Intrinsics.checkNotNullParameter(context, "context");
                                        return BuildersKt__Builders_commonKt.launch$default(AppScope.INSTANCE, (CoroutineContext) null, (CoroutineStart) null, new MediaUtils$openMediaNoUiFromTvContent$1(uri, context, (Continuation<? super MediaUtils$openMediaNoUiFromTvContent$1>) null), 3, (Object) null);
                                    }

                                    /* access modifiers changed from: private */
                                    public final void playAlbum(Context context, Album album) {
                                        if (context != null) {
                                            new SuspendDialogCallback(context, new MediaUtils$playAlbum$1(album, (Continuation<? super MediaUtils$playAlbum$1>) null));
                                        }
                                    }

                                    /* access modifiers changed from: private */
                                    public final void playArtist(Context context, Artist artist) {
                                        if (context != null) {
                                            new SuspendDialogCallback(context, new MediaUtils$playArtist$1(artist, (Continuation<? super MediaUtils$playArtist$1>) null));
                                        }
                                    }

                                    public final Object useAsSoundFont(Context context, Uri uri, Continuation<? super Unit> continuation) {
                                        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new MediaUtils$useAsSoundFont$2(uri, context, (Continuation<? super MediaUtils$useAsSoundFont$2>) null), continuation);
                                        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
                                    }
                                }
