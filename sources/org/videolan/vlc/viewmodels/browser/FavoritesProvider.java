package org.videolan.vlc.viewmodels.browser;

import android.content.Context;
import io.ktor.server.auth.OAuth2RequestParameters;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.FlowKt;
import org.videolan.libvlc.util.MediaBrowser;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.tools.livedata.LiveDataset;
import org.videolan.vlc.mediadb.models.BrowserFav;
import org.videolan.vlc.providers.BrowserProvider;
import org.videolan.vlc.repository.BrowserFavRepository;
import org.videolan.vlc.util.BrowserutilsKt;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u000e\u0010\f\u001a\u00020\rH@¢\u0006\u0002\u0010\u000eJ*\u0010\u000f\u001a\u0004\u0018\u00010\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H@¢\u0006\u0002\u0010\u0016R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lorg/videolan/vlc/viewmodels/browser/FavoritesProvider;", "Lorg/videolan/vlc/providers/BrowserProvider;", "context", "Landroid/content/Context;", "dataset", "Lorg/videolan/tools/livedata/LiveDataset;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "(Landroid/content/Context;Lorg/videolan/tools/livedata/LiveDataset;Lkotlinx/coroutines/CoroutineScope;)V", "browserFavRepository", "Lorg/videolan/vlc/repository/BrowserFavRepository;", "browseRootImpl", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "requestBrowsing", "url", "", "eventListener", "Lorg/videolan/libvlc/util/MediaBrowser$EventListener;", "interact", "", "(Ljava/lang/String;Lorg/videolan/libvlc/util/MediaBrowser$EventListener;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BrowserFavoritesModel.kt */
public final class FavoritesProvider extends BrowserProvider {
    private final BrowserFavRepository browserFavRepository;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FavoritesProvider(Context context, final LiveDataset<MediaLibraryItem> liveDataset, CoroutineScope coroutineScope) {
        super(context, liveDataset, (String) null, 10, false);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(liveDataset, "dataset");
        Intrinsics.checkNotNullParameter(coroutineScope, OAuth2RequestParameters.Scope);
        BrowserFavRepository browserFavRepository2 = (BrowserFavRepository) BrowserFavRepository.Companion.getInstance(context);
        this.browserFavRepository = browserFavRepository2;
        FlowKt.launchIn(FlowKt.flowOn(FlowKt.onEach(browserFavRepository2.getBrowserFavorites(), new AnonymousClass1((Continuation<? super AnonymousClass1>) null)), Dispatchers.getIO()), coroutineScope);
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H@"}, d2 = {"<anonymous>", "", "list", "", "Lorg/videolan/vlc/mediadb/models/BrowserFav;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.viewmodels.browser.FavoritesProvider$1", f = "BrowserFavoritesModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.viewmodels.browser.FavoritesProvider$1  reason: invalid class name */
    /* compiled from: BrowserFavoritesModel.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<List<? extends BrowserFav>, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(liveDataset, this, continuation);
            r0.L$0 = obj;
            return r0;
        }

        public final Object invoke(List<BrowserFav> list, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(list, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                List<MediaWrapper> convertFavorites = BrowserutilsKt.convertFavorites(CollectionsKt.sortedWith((List) this.L$0, ComparisonsKt.compareBy((Function1<? super T, ? extends Comparable<?>>[]) new Function1[]{AnonymousClass1.INSTANCE, AnonymousClass2.INSTANCE})));
                LiveDataset<MediaLibraryItem> liveDataset = liveDataset;
                FavoritesProvider favoritesProvider = this;
                Intrinsics.checkNotNull(convertFavorites, "null cannot be cast to non-null type kotlin.collections.MutableList<org.videolan.medialibrary.media.MediaLibraryItem>");
                liveDataset.postValue(TypeIntrinsics.asMutableList(convertFavorites));
                BrowserProvider.parseSubDirectories$vlc_android_release$default(favoritesProvider, (List) null, 1, (Object) null);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* access modifiers changed from: protected */
    public Object requestBrowsing(String str, MediaBrowser.EventListener eventListener, boolean z, Continuation<? super Unit> continuation) {
        return BuildersKt.withContext(getCoroutineContextProvider().getIO(), new FavoritesProvider$requestBrowsing$2(this, eventListener, str, z, (Continuation<? super FavoritesProvider$requestBrowsing$2>) null), continuation);
    }

    public Object browseRootImpl(Continuation<? super Unit> continuation) {
        return Unit.INSTANCE;
    }
}
