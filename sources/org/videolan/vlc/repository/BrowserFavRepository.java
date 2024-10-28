package org.videolan.vlc.repository;

import android.content.Context;
import android.net.Uri;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.resources.AppContextProvider;
import org.videolan.tools.NetworkMonitor;
import org.videolan.tools.SingletonHolder;
import org.videolan.vlc.database.BrowserFavDao;
import org.videolan.vlc.mediadb.models.BrowserFav;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u0000 -2\u00020\u0001:\u0001-B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J*\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\"H@¢\u0006\u0002\u0010$J(\u0010%\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\"H@¢\u0006\u0002\u0010$J\u0016\u0010&\u001a\u00020'2\u0006\u0010\u001f\u001a\u00020 H@¢\u0006\u0002\u0010(J\u0016\u0010)\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H@¢\u0006\u0002\u0010(J\u0016\u0010*\u001a\u00020'2\u0006\u0010+\u001a\u00020 H@¢\u0006\u0002\u0010(J\u0018\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00140\u0007*\b\u0012\u0004\u0012\u00020\u00140\u0007H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R'\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00068FX\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR'\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u000e8FX\u0002¢\u0006\f\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u000f\u0010\u0010R'\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00070\u00138FX\u0002¢\u0006\f\n\u0004\b\u0017\u0010\f\u001a\u0004\b\u0015\u0010\u0016R'\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00068FX\u0002¢\u0006\f\n\u0004\b\u001a\u0010\f\u001a\u0004\b\u0019\u0010\nR\u000e\u0010\u001b\u001a\u00020\u001cX\u0004¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lorg/videolan/vlc/repository/BrowserFavRepository;", "", "browserFavDao", "Lorg/videolan/vlc/database/BrowserFavDao;", "(Lorg/videolan/vlc/database/BrowserFavDao;)V", "browserFavorites", "Lkotlinx/coroutines/flow/Flow;", "", "Lorg/videolan/vlc/mediadb/models/BrowserFav;", "getBrowserFavorites", "()Lkotlinx/coroutines/flow/Flow;", "browserFavorites$delegate", "Lkotlin/Lazy;", "localFavorites", "Landroidx/lifecycle/LiveData;", "getLocalFavorites", "()Landroidx/lifecycle/LiveData;", "localFavorites$delegate", "networkFavorites", "Landroidx/lifecycle/MediatorLiveData;", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "getNetworkFavorites", "()Landroidx/lifecycle/MediatorLiveData;", "networkFavorites$delegate", "networkFavs", "getNetworkFavs", "networkFavs$delegate", "networkMonitor", "Lorg/videolan/tools/NetworkMonitor;", "addLocalFavItem", "", "uri", "Landroid/net/Uri;", "title", "", "iconUrl", "(Landroid/net/Uri;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addNetworkFavItem", "browserFavExists", "", "(Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteBrowserFav", "isFavNetwork", "searchUri", "filterNetworkFavs", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BrowserFavRepository.kt */
public final class BrowserFavRepository {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public final BrowserFavDao browserFavDao;
    private final Lazy browserFavorites$delegate = LazyKt.lazy(new BrowserFavRepository$browserFavorites$2(this));
    private final Lazy localFavorites$delegate = LazyKt.lazy(new BrowserFavRepository$localFavorites$2(this));
    private final Lazy networkFavorites$delegate = LazyKt.lazy(new BrowserFavRepository$networkFavorites$2(this));
    private final Lazy networkFavs$delegate = LazyKt.lazy(new BrowserFavRepository$networkFavs$2(this));
    /* access modifiers changed from: private */
    public final NetworkMonitor networkMonitor = ((NetworkMonitor) NetworkMonitor.Companion.getInstance(AppContextProvider.INSTANCE.getAppContext()));

    public BrowserFavRepository(BrowserFavDao browserFavDao2) {
        Intrinsics.checkNotNullParameter(browserFavDao2, "browserFavDao");
        this.browserFavDao = browserFavDao2;
    }

    public final Flow<List<BrowserFav>> getNetworkFavs() {
        return (Flow) this.networkFavs$delegate.getValue();
    }

    public final Flow<List<BrowserFav>> getBrowserFavorites() {
        return (Flow) this.browserFavorites$delegate.getValue();
    }

    public final LiveData<List<BrowserFav>> getLocalFavorites() {
        return (LiveData) this.localFavorites$delegate.getValue();
    }

    public final Object addNetworkFavItem(Uri uri, String str, String str2, Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new BrowserFavRepository$addNetworkFavItem$2(this, uri, str, str2, (Continuation<? super BrowserFavRepository$addNetworkFavItem$2>) null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    public static /* synthetic */ Object addLocalFavItem$default(BrowserFavRepository browserFavRepository, Uri uri, String str, String str2, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            str2 = null;
        }
        return browserFavRepository.addLocalFavItem(uri, str, str2, continuation);
    }

    public final Object addLocalFavItem(Uri uri, String str, String str2, Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new BrowserFavRepository$addLocalFavItem$2(this, uri, str, str2, (Continuation<? super BrowserFavRepository$addLocalFavItem$2>) null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    public final MediatorLiveData<List<MediaWrapper>> getNetworkFavorites() {
        return (MediatorLiveData) this.networkFavorites$delegate.getValue();
    }

    public final Object browserFavExists(Uri uri, Continuation<? super Boolean> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new BrowserFavRepository$browserFavExists$2(this, uri, (Continuation<? super BrowserFavRepository$browserFavExists$2>) null), continuation);
    }

    public final Object isFavNetwork(Uri uri, Continuation<? super Boolean> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new BrowserFavRepository$isFavNetwork$2(this, uri, (Continuation<? super BrowserFavRepository$isFavNetwork$2>) null), continuation);
    }

    public final Object deleteBrowserFav(Uri uri, Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new BrowserFavRepository$deleteBrowserFav$2(this, uri, (Continuation<? super BrowserFavRepository$deleteBrowserFav$2>) null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public final List<MediaWrapper> filterNetworkFavs(List<? extends MediaWrapper> list) {
        if (list.isEmpty()) {
            return list;
        }
        if (!this.networkMonitor.getConnected()) {
            return CollectionsKt.emptyList();
        }
        if (((NetworkMonitor) NetworkMonitor.Companion.getInstance(AppContextProvider.INSTANCE.getAppContext())).getLanAllowed()) {
            return list;
        }
        List asList = Arrays.asList(new String[]{"ftp", "sftp", "ftps", "ftpes", "http", "https"});
        List<MediaWrapper> arrayList = new ArrayList<>();
        for (Object next : list) {
            if (asList.contains(((MediaWrapper) next).getUri().getScheme())) {
                arrayList.add(next);
            }
        }
        Collection collection = arrayList;
        return arrayList;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/videolan/vlc/repository/BrowserFavRepository$Companion;", "Lorg/videolan/tools/SingletonHolder;", "Lorg/videolan/vlc/repository/BrowserFavRepository;", "Landroid/content/Context;", "()V", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: BrowserFavRepository.kt */
    public static final class Companion extends SingletonHolder<BrowserFavRepository, Context> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
            super(AnonymousClass1.INSTANCE);
        }
    }
}
