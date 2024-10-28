package org.videolan.television.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.fragment.app.FragmentActivity;
import androidx.leanback.widget.DiffCallback;
import androidx.leanback.widget.ListRow;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.Playlist;
import org.videolan.medialibrary.media.DummyItem;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.moviepedia.database.models.MediaMetadataWithImages;
import org.videolan.resources.Constants;
import org.videolan.television.ui.audioplayer.AudioPlayerActivity;
import org.videolan.television.ui.browser.TVActivity;
import org.videolan.television.ui.browser.VerticalGridActivity;
import org.videolan.television.ui.details.MediaListActivity;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.ArtworkProvider;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.DialogActivity;
import org.videolan.vlc.media.MediaUtils;
import org.videolan.vlc.providers.medialibrary.MedialibraryProvider;
import org.videolan.vlc.viewmodels.browser.BrowserModel;

@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u0016\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0015\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0007J\u0018\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001J \u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u00012\u0006\u0010 \u001a\u00020!J0\u0010\"\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u00012\u000e\u0010#\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070$H@¢\u0006\u0002\u0010%J&\u0010&\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u001a2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020)0(2\u0006\u0010*\u001a\u00020\u0014H\u0002J&\u0010+\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u001a2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020)0(2\b\b\u0002\u0010*\u001a\u00020\u0014J\u0016\u0010+\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u001a2\u0006\u0010,\u001a\u00020)J \u0010-\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u001a2\u0006\u0010.\u001a\u00020/2\b\b\u0002\u0010*\u001a\u00020\u0014J \u00100\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u00162\u0006\u00101\u001a\u00020)2\b\b\u0002\u00102\u001a\u000203R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\tR \u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\t\"\u0004\b\u0012\u0010\u000b¨\u00064"}, d2 = {"Lorg/videolan/television/ui/TvUtil;", "", "()V", "TAG", "", "diffCallback", "Landroidx/leanback/widget/DiffCallback;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "getDiffCallback", "()Landroidx/leanback/widget/DiffCallback;", "setDiffCallback", "(Landroidx/leanback/widget/DiffCallback;)V", "listDiffCallback", "Landroidx/leanback/widget/ListRow;", "getListDiffCallback", "metadataDiffCallback", "Lorg/videolan/moviepedia/database/models/MediaMetadataWithImages;", "getMetadataDiffCallback", "setMetadataDiffCallback", "getOverscanHorizontal", "", "context", "Landroid/content/Context;", "getOverscanVertical", "openAudioCategory", "", "Landroid/app/Activity;", "mediaLibraryItem", "openMedia", "activity", "Landroidx/fragment/app/FragmentActivity;", "item", "model", "Lorg/videolan/vlc/viewmodels/browser/BrowserModel;", "openMediaFromPaged", "provider", "Lorg/videolan/vlc/providers/medialibrary/MedialibraryProvider;", "(Landroidx/fragment/app/FragmentActivity;Ljava/lang/Object;Lorg/videolan/vlc/providers/medialibrary/MedialibraryProvider;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "playAudioList", "list", "", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "position", "playMedia", "media", "playPlaylist", "playlist", "Lorg/videolan/medialibrary/interfaces/media/Playlist;", "showMediaDetail", "mediaWrapper", "fromHistory", "", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: TvUtil.kt */
public final class TvUtil {
    public static final TvUtil INSTANCE = new TvUtil();
    private static final String TAG = "VLC/TvUtil";
    private static DiffCallback<MediaLibraryItem> diffCallback = new TvUtil$diffCallback$1();
    private static final DiffCallback<ListRow> listDiffCallback = new TvUtil$listDiffCallback$1();
    private static DiffCallback<MediaMetadataWithImages> metadataDiffCallback = new TvUtil$metadataDiffCallback$1();

    private TvUtil() {
    }

    public final DiffCallback<MediaLibraryItem> getDiffCallback() {
        return diffCallback;
    }

    public final void setDiffCallback(DiffCallback<MediaLibraryItem> diffCallback2) {
        Intrinsics.checkNotNullParameter(diffCallback2, "<set-?>");
        diffCallback = diffCallback2;
    }

    public final DiffCallback<MediaMetadataWithImages> getMetadataDiffCallback() {
        return metadataDiffCallback;
    }

    public final void setMetadataDiffCallback(DiffCallback<MediaMetadataWithImages> diffCallback2) {
        Intrinsics.checkNotNullParameter(diffCallback2, "<set-?>");
        metadataDiffCallback = diffCallback2;
    }

    public final DiffCallback<ListRow> getListDiffCallback() {
        return listDiffCallback;
    }

    public final int getOverscanHorizontal(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return context.getResources().getDimensionPixelSize(R.dimen.tv_overscan_horizontal);
    }

    public final int getOverscanVertical(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return context.getResources().getDimensionPixelSize(R.dimen.tv_overscan_vertical);
    }

    public final void playMedia(Activity activity, MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(mediaWrapper, "media");
        if (mediaWrapper.getType() == 1) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(mediaWrapper);
            playMedia$default(this, activity, arrayList, 0, 4, (Object) null);
            return;
        }
        MediaUtils.INSTANCE.openMedia(activity, mediaWrapper);
    }

    public static /* synthetic */ void playMedia$default(TvUtil tvUtil, Activity activity, List list, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 0;
        }
        tvUtil.playMedia(activity, list, i);
    }

    public final void playMedia(Activity activity, List<? extends MediaWrapper> list, int i) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(list, "media");
        Intent intent = new Intent(activity, AudioPlayerActivity.class);
        intent.putExtra("media_list", new ArrayList(list));
        intent.putExtra(AudioPlayerActivity.MEDIA_POSITION, i);
        activity.startActivity(intent);
    }

    public static /* synthetic */ void playPlaylist$default(TvUtil tvUtil, Activity activity, Playlist playlist, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 0;
        }
        tvUtil.playPlaylist(activity, playlist, i);
    }

    public final void playPlaylist(Activity activity, Playlist playlist, int i) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(playlist, ArtworkProvider.PLAYLIST);
        Intent intent = new Intent(activity, AudioPlayerActivity.class);
        intent.putExtra(AudioPlayerActivity.MEDIA_PLAYLIST, playlist.getId());
        intent.putExtra(AudioPlayerActivity.MEDIA_POSITION, i);
        activity.startActivity(intent);
    }

    public final void openMedia(FragmentActivity fragmentActivity, Object obj) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "activity");
        if (obj instanceof MediaWrapper) {
            MediaWrapper mediaWrapper = (MediaWrapper) obj;
            if (mediaWrapper.getType() == 3) {
                Intent intent = new Intent(fragmentActivity, VerticalGridActivity.class);
                intent.putExtra(MainTvActivity.BROWSER_TYPE, Intrinsics.areEqual((Object) "file", (Object) mediaWrapper.getUri().getScheme()) ? 4 : 3);
                intent.setData(mediaWrapper.getUri());
                fragmentActivity.startActivity(intent);
                return;
            }
            MediaUtils.INSTANCE.openMedia(fragmentActivity, mediaWrapper);
        } else if (obj instanceof DummyItem) {
            DummyItem dummyItem = (DummyItem) obj;
            long id = dummyItem.getId();
            if (id == 6) {
                Intent intent2 = new Intent(fragmentActivity, TVActivity.class);
                intent2.putExtra(MainTvActivity.BROWSER_TYPE, 6);
                fragmentActivity.startActivity(intent2);
            } else if (id == 7) {
                fragmentActivity.startActivity(new Intent(fragmentActivity, DialogActivity.class).setAction(DialogActivity.KEY_SERVER).addFlags(268435456));
            } else {
                Intent intent3 = new Intent(fragmentActivity, VerticalGridActivity.class);
                intent3.putExtra(MainTvActivity.BROWSER_TYPE, dummyItem.getId());
                fragmentActivity.startActivity(intent3);
            }
        } else if (obj instanceof MediaLibraryItem) {
            openAudioCategory(fragmentActivity, (MediaLibraryItem) obj);
        }
    }

    public final void openMedia(FragmentActivity fragmentActivity, Object obj, BrowserModel browserModel) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "activity");
        Intrinsics.checkNotNullParameter(browserModel, "model");
        if (obj instanceof MediaWrapper) {
            MediaWrapper mediaWrapper = (MediaWrapper) obj;
            int type = mediaWrapper.getType();
            if (type == 1) {
                Collection arrayList = new ArrayList();
                for (Object next : browserModel.getDataset().getList()) {
                    if (next instanceof MediaWrapper) {
                        arrayList.add(next);
                    }
                }
                Collection arrayList2 = new ArrayList();
                for (Object next2 : (List) arrayList) {
                    if (((MediaWrapper) next2).getType() != 3) {
                        arrayList2.add(next2);
                    }
                }
                List list = (List) arrayList2;
                playAudioList(fragmentActivity, list, KotlinExtensionsKt.getposition(list, obj));
            } else if (type == 3) {
                Intent intent = new Intent(fragmentActivity, VerticalGridActivity.class);
                intent.putExtra(MainTvActivity.BROWSER_TYPE, Intrinsics.areEqual((Object) "file", (Object) mediaWrapper.getUri().getScheme()) ? 4 : 3);
                intent.setData(mediaWrapper.getUri());
                fragmentActivity.startActivity(intent);
            } else if (!((SharedPreferences) Settings.INSTANCE.getInstance(fragmentActivity)).getBoolean(SettingsKt.FORCE_PLAY_ALL_VIDEO, Settings.INSTANCE.getTvUI())) {
                MediaUtils.INSTANCE.openMedia(fragmentActivity, mediaWrapper);
            } else {
                Collection arrayList3 = new ArrayList();
                for (Object next3 : browserModel.getDataset().getList()) {
                    if (next3 instanceof MediaWrapper) {
                        arrayList3.add(next3);
                    }
                }
                Collection arrayList4 = new ArrayList();
                for (Object next4 : (List) arrayList3) {
                    if (((MediaWrapper) next4).getType() != 3) {
                        arrayList4.add(next4);
                    }
                }
                List list2 = (List) arrayList4;
                MediaUtils.openList$default(MediaUtils.INSTANCE, fragmentActivity, list2, KotlinExtensionsKt.getposition(list2, obj), false, 8, (Object) null);
            }
        } else if (obj instanceof DummyItem) {
            DummyItem dummyItem = (DummyItem) obj;
            long id = dummyItem.getId();
            if (id == 6) {
                Intent intent2 = new Intent(fragmentActivity, TVActivity.class);
                intent2.putExtra(MainTvActivity.BROWSER_TYPE, 6);
                fragmentActivity.startActivity(intent2);
            } else if (id == 7) {
                fragmentActivity.startActivity(new Intent(fragmentActivity, DialogActivity.class).setAction(DialogActivity.KEY_SERVER).addFlags(268435456));
            } else {
                Intent intent3 = new Intent(fragmentActivity, VerticalGridActivity.class);
                intent3.putExtra(MainTvActivity.BROWSER_TYPE, dummyItem.getId());
                fragmentActivity.startActivity(intent3);
            }
        } else if (obj instanceof MediaLibraryItem) {
            openAudioCategory(fragmentActivity, (MediaLibraryItem) obj);
        }
    }

    public final Object openMediaFromPaged(FragmentActivity fragmentActivity, Object obj, MedialibraryProvider<? extends MediaLibraryItem> medialibraryProvider, Continuation<? super Unit> continuation) {
        if (obj instanceof MediaWrapper) {
            MediaWrapper mediaWrapper = (MediaWrapper) obj;
            int type = mediaWrapper.getType();
            if (type == 1) {
                Object loadPagedList = medialibraryProvider.loadPagedList(fragmentActivity, new TvUtil$openMediaFromPaged$2(medialibraryProvider), new TvUtil$openMediaFromPaged$3(fragmentActivity, obj), continuation);
                return loadPagedList == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? loadPagedList : Unit.INSTANCE;
            } else if (type != 3) {
                Object loadPagedList2 = medialibraryProvider.loadPagedList(fragmentActivity, new TvUtil$openMediaFromPaged$4(medialibraryProvider), new TvUtil$openMediaFromPaged$5(fragmentActivity, obj), continuation);
                return loadPagedList2 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? loadPagedList2 : Unit.INSTANCE;
            } else {
                Intent intent = new Intent(fragmentActivity, VerticalGridActivity.class);
                intent.putExtra(MainTvActivity.BROWSER_TYPE, Intrinsics.areEqual((Object) "file", (Object) mediaWrapper.getUri().getScheme()) ? 4 : 3);
                intent.setData(mediaWrapper.getUri());
                fragmentActivity.startActivity(intent);
            }
        } else if (obj instanceof DummyItem) {
            DummyItem dummyItem = (DummyItem) obj;
            long id = dummyItem.getId();
            if (id == 6) {
                Intent intent2 = new Intent(fragmentActivity, TVActivity.class);
                intent2.putExtra(MainTvActivity.BROWSER_TYPE, 6);
                fragmentActivity.startActivity(intent2);
            } else if (id == 7) {
                fragmentActivity.startActivity(new Intent(fragmentActivity, DialogActivity.class).setAction(DialogActivity.KEY_SERVER).addFlags(268435456));
            } else {
                Intent intent3 = new Intent(fragmentActivity, VerticalGridActivity.class);
                intent3.putExtra(MainTvActivity.BROWSER_TYPE, dummyItem.getId());
                fragmentActivity.startActivity(intent3);
            }
        } else if (obj instanceof MediaLibraryItem) {
            openAudioCategory(fragmentActivity, (MediaLibraryItem) obj);
        }
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void showMediaDetail$default(TvUtil tvUtil, Context context, MediaWrapper mediaWrapper, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        tvUtil.showMediaDetail(context, mediaWrapper, z);
    }

    public final void showMediaDetail(Context context, MediaWrapper mediaWrapper, boolean z) {
        Intrinsics.checkNotNullParameter(context, "activity");
        Intrinsics.checkNotNullParameter(mediaWrapper, "mediaWrapper");
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra("media", mediaWrapper);
        intent.putExtra("item", new MediaItemDetails(mediaWrapper.getTitle(), mediaWrapper.getArtist(), mediaWrapper.getAlbum(), mediaWrapper.getLocation(), mediaWrapper.getArtworkURL()));
        if (z) {
            intent.putExtra(MediaItemDetailsFragmentKt.EXTRA_FROM_HISTORY, z);
        }
        context.startActivity(intent);
    }

    /* access modifiers changed from: private */
    public final void playAudioList(Activity activity, List<? extends MediaWrapper> list, int i) {
        Context context = activity;
        MediaUtils.openList$default(MediaUtils.INSTANCE, context, list, i, false, 8, (Object) null);
        activity.startActivity(new Intent(context, AudioPlayerActivity.class));
    }

    public final void openAudioCategory(Activity activity, MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(activity, "context");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "mediaLibraryItem");
        int itemType = mediaLibraryItem.getItemType();
        if (itemType == 2 || itemType == 16) {
            Intent intent = new Intent(activity, MediaListActivity.class);
            intent.putExtra("item", mediaLibraryItem);
            activity.startActivity(intent);
        } else if (itemType != 32) {
            Intent intent2 = new Intent(activity, VerticalGridActivity.class);
            intent2.putExtra("item", mediaLibraryItem);
            intent2.putExtra(Constants.CATEGORY, 22);
            intent2.putExtra(MainTvActivity.BROWSER_TYPE, 1);
            activity.startActivity(intent2);
        } else {
            ArrayList arrayList = new ArrayList();
            arrayList.add((MediaWrapper) mediaLibraryItem);
            playAudioList(activity, arrayList, 0);
        }
    }
}
