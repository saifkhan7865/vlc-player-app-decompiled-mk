package org.videolan.vlc.providers;

import android.content.Context;
import androidx.lifecycle.Observer;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import org.videolan.libvlc.util.MediaBrowser;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.DummyItem;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.tools.NetworkMonitor;
import org.videolan.tools.livedata.LiveDataset;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002B'\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fJ\u000e\u0010\r\u001a\u00020\u000eH@¢\u0006\u0002\u0010\u000fJ\b\u0010\u0010\u001a\u00020\u000eH\u0016J \u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u00122\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\u0002J\u0016\u0010\u0014\u001a\u00020\u000e2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0016J\u001d\u0010\u0015\u001a\u00020\u000e2\u000e\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0003H\u0010¢\u0006\u0002\b\u0017J\b\u0010\u0018\u001a\u00020\u000eH\u0016J*\u0010\u0019\u001a\u0004\u0018\u00010\u000e2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH@¢\u0006\u0002\u0010\u001eJ\b\u0010\u001f\u001a\u00020\u000eH\u0016¨\u0006 ²\u0006\u0012\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0012X\u0002"}, d2 = {"Lorg/videolan/vlc/providers/NetworkProvider;", "Lorg/videolan/vlc/providers/BrowserProvider;", "Landroidx/lifecycle/Observer;", "", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "context", "Landroid/content/Context;", "dataset", "Lorg/videolan/tools/livedata/LiveDataset;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "url", "", "(Landroid/content/Context;Lorg/videolan/tools/livedata/LiveDataset;Ljava/lang/String;)V", "browseRootImpl", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetch", "getFavoritesList", "", "favs", "onChanged", "parseSubDirectories", "list", "parseSubDirectories$vlc_android_release", "refresh", "requestBrowsing", "eventListener", "Lorg/videolan/libvlc/util/MediaBrowser$EventListener;", "interact", "", "(Ljava/lang/String;Lorg/videolan/libvlc/util/MediaBrowser$EventListener;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "stop", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: NetworkProvider.kt */
public final class NetworkProvider extends BrowserProvider implements Observer<List<? extends MediaWrapper>> {
    public void fetch() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NetworkProvider(Context context, LiveDataset<MediaLibraryItem> liveDataset, String str) {
        super(context, liveDataset, str, 10, false);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(liveDataset, "dataset");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ NetworkProvider(Context context, LiveDataset liveDataset, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, liveDataset, (i & 4) != 0 ? null : str);
    }

    public Object browseRootImpl(Continuation<? super Unit> continuation) {
        getDataset().clear();
        getDataset().setValue(new ArrayList());
        if (((NetworkMonitor) NetworkMonitor.Companion.getInstance(getContext())).getLanAllowed()) {
            BrowserProvider.browse$default(this, (String) null, 1, (Object) null);
        }
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: protected */
    public Object requestBrowsing(String str, MediaBrowser.EventListener eventListener, boolean z, Continuation<? super Unit> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new NetworkProvider$requestBrowsing$2(this, eventListener, str, z, (Continuation<? super NetworkProvider$requestBrowsing$2>) null), continuation);
    }

    private static final List<MediaLibraryItem> refresh$lambda$0(Lazy<? extends List<MediaLibraryItem>> lazy) {
        return (List) lazy.getValue();
    }

    public void refresh() {
        Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, new NetworkProvider$refresh$list$2(this));
        if (getUrl() == null) {
            browseRoot();
        } else if (refresh$lambda$0(lazy) != null) {
            LiveDataset<MediaLibraryItem> dataset = getDataset();
            List<MediaLibraryItem> refresh$lambda$0 = refresh$lambda$0(lazy);
            Intrinsics.checkNotNull(refresh$lambda$0, "null cannot be cast to non-null type kotlin.collections.MutableList<org.videolan.medialibrary.media.MediaLibraryItem>");
            dataset.setValue((List<MediaLibraryItem>) TypeIntrinsics.asMutableList(refresh$lambda$0));
            removeList(getUrl());
            BrowserProvider.parseSubDirectories$vlc_android_release$default(this, (List) null, 1, (Object) null);
            List<MediaLibraryItem> refresh$lambda$02 = refresh$lambda$0(lazy);
            Intrinsics.checkNotNull(refresh$lambda$02, "null cannot be cast to non-null type kotlin.collections.MutableList<org.videolan.medialibrary.media.MediaLibraryItem>");
            computeHeaders(TypeIntrinsics.asMutableList(refresh$lambda$02));
        } else {
            super.refresh();
        }
    }

    public void parseSubDirectories$vlc_android_release(List<? extends MediaLibraryItem> list) {
        if (getUrl() != null) {
            super.parseSubDirectories$vlc_android_release(list);
        }
    }

    public void stop() {
        if (getUrl() == null) {
            clearListener();
        }
        super.stop();
    }

    public void onChanged(List<? extends MediaWrapper> list) {
        Intrinsics.checkNotNullParameter(list, "favs");
        List mutableList = CollectionsKt.toMutableList(getDataset().getValue());
        ListIterator listIterator = mutableList.listIterator();
        while (listIterator.hasNext()) {
            MediaLibraryItem mediaLibraryItem = (MediaLibraryItem) listIterator.next();
            if (mediaLibraryItem.hasStateFlags(2) || (mediaLibraryItem instanceof DummyItem)) {
                listIterator.remove();
            }
        }
        LiveDataset<MediaLibraryItem> dataset = getDataset();
        List<MediaLibraryItem> favoritesList = getFavoritesList(list);
        if (favoritesList != null) {
            mutableList.addAll(0, favoritesList);
        }
        dataset.setValue((List<MediaLibraryItem>) mutableList);
    }

    private final List<MediaLibraryItem> getFavoritesList(List<? extends MediaWrapper> list) {
        if (list == null || !(!list.isEmpty())) {
            return null;
        }
        List<MediaLibraryItem> arrayList = new ArrayList<>();
        int i = 0;
        arrayList.add(0, new DummyItem(getContext().getString(R.string.network_favorites)));
        for (MediaWrapper add : list) {
            i++;
            arrayList.add(i, add);
        }
        arrayList.add(new DummyItem(getContext().getString(R.string.network_shared_folders)));
        return arrayList;
    }
}
