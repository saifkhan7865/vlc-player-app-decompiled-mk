package org.videolan.television.ui;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.FlowLiveDataConversions;
import androidx.lifecycle.MediatorLiveData;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.channels.ActorKt;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.flow.Flow;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.mediadb.models.BrowserFav;
import org.videolan.vlc.repository.BrowserFavRepository;
import org.videolan.vlc.util.BrowserutilsKt;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0012\u0010\u000b\u001a\u00020\fX\u0005¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\bX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u001bX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010 \u001a\u00020\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0012\"\u0004\b\"\u0010\u0014R\u001e\u0010#\u001a\u0012\u0012\u0004\u0012\u00020\b0$j\b\u0012\u0004\u0012\u00020\b`%X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010(\u001a\b\u0012\u0004\u0012\u00020\b0)X\u0004¢\u0006\b\n\u0000\u0012\u0004\b*\u0010+¨\u0006,"}, d2 = {"Lorg/videolan/television/ui/MediaItemDetailsModel;", "Landroidx/lifecycle/AndroidViewModel;", "Lkotlinx/coroutines/CoroutineScope;", "context", "Landroid/app/Application;", "(Landroid/app/Application;)V", "browserFavUpdated", "Landroidx/lifecycle/MediatorLiveData;", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "getBrowserFavUpdated", "()Landroidx/lifecycle/MediatorLiveData;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "listenForNetworkFav", "", "getListenForNetworkFav", "()Z", "setListenForNetworkFav", "(Z)V", "media", "getMedia", "()Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "setMedia", "(Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;)V", "mediaItemDetails", "Lorg/videolan/television/ui/MediaItemDetails;", "getMediaItemDetails", "()Lorg/videolan/television/ui/MediaItemDetails;", "setMediaItemDetails", "(Lorg/videolan/television/ui/MediaItemDetails;)V", "mediaStarted", "getMediaStarted", "setMediaStarted", "oldList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "repository", "Lorg/videolan/vlc/repository/BrowserFavRepository;", "updateActor", "Lkotlinx/coroutines/channels/SendChannel;", "getUpdateActor$annotations", "()V", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaItemDetailsFragment.kt */
public final class MediaItemDetailsModel extends AndroidViewModel implements CoroutineScope {
    private final /* synthetic */ CoroutineScope $$delegate_0 = CoroutineScopeKt.MainScope();
    private final MediatorLiveData<MediaWrapper> browserFavUpdated;
    private boolean listenForNetworkFav;
    public MediaWrapper media;
    public MediaItemDetails mediaItemDetails;
    private boolean mediaStarted;
    /* access modifiers changed from: private */
    public final ArrayList<MediaWrapper> oldList;
    private final BrowserFavRepository repository;
    /* access modifiers changed from: private */
    public final SendChannel<MediaWrapper> updateActor;

    private static /* synthetic */ void getUpdateActor$annotations() {
    }

    public CoroutineContext getCoroutineContext() {
        return this.$$delegate_0.getCoroutineContext();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MediaItemDetailsModel(Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "context");
        BrowserFavRepository browserFavRepository = (BrowserFavRepository) BrowserFavRepository.Companion.getInstance(application);
        this.repository = browserFavRepository;
        MediatorLiveData<MediaWrapper> mediatorLiveData = new MediatorLiveData<>();
        this.browserFavUpdated = mediatorLiveData;
        this.oldList = new ArrayList<>();
        this.updateActor = ActorKt.actor$default(this, (CoroutineContext) null, -1, (CoroutineStart) null, (Function1) null, new MediaItemDetailsModel$updateActor$1(this, (Continuation<? super MediaItemDetailsModel$updateActor$1>) null), 13, (Object) null);
        mediatorLiveData.addSource(FlowLiveDataConversions.asLiveData$default((Flow) browserFavRepository.getNetworkFavs(), (CoroutineContext) null, 0, 3, (Object) null), new MediaItemDetailsFragmentKt$sam$androidx_lifecycle_Observer$0(new Function1<List<? extends BrowserFav>, Unit>(this) {
            final /* synthetic */ MediaItemDetailsModel this$0;

            {
                this.this$0 = r1;
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((List<BrowserFav>) (List) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(List<BrowserFav> list) {
                List<MediaWrapper> convertFavorites = BrowserutilsKt.convertFavorites(list);
                if (this.this$0.oldList.isEmpty()) {
                    this.this$0.oldList.addAll(convertFavorites);
                }
                if (this.this$0.getListenForNetworkFav()) {
                    MediaItemDetailsModel mediaItemDetailsModel = this.this$0;
                    for (MediaWrapper mediaWrapper : convertFavorites) {
                        Iterable access$getOldList$p = mediaItemDetailsModel.oldList;
                        if (!(access$getOldList$p instanceof Collection) || !((Collection) access$getOldList$p).isEmpty()) {
                            Iterator it = access$getOldList$p.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    break;
                                }
                                MediaWrapper mediaWrapper2 = (MediaWrapper) it.next();
                                if (Intrinsics.areEqual((Object) mediaWrapper2.getUri(), (Object) mediaWrapper.getUri()) && Intrinsics.areEqual((Object) mediaWrapper2.getTitle(), (Object) mediaWrapper.getTitle())) {
                                    break;
                                }
                            }
                        }
                        mediaItemDetailsModel.oldList.clear();
                        mediaItemDetailsModel.oldList.addAll(convertFavorites);
                        mediaItemDetailsModel.setListenForNetworkFav(false);
                        SendChannel access$getUpdateActor$p = mediaItemDetailsModel.updateActor;
                        Intrinsics.checkNotNull(mediaWrapper);
                        access$getUpdateActor$p.m1139trySendJP2dKIU(mediaWrapper);
                    }
                }
            }
        }));
    }

    public final MediaItemDetails getMediaItemDetails() {
        MediaItemDetails mediaItemDetails2 = this.mediaItemDetails;
        if (mediaItemDetails2 != null) {
            return mediaItemDetails2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mediaItemDetails");
        return null;
    }

    public final void setMediaItemDetails(MediaItemDetails mediaItemDetails2) {
        Intrinsics.checkNotNullParameter(mediaItemDetails2, "<set-?>");
        this.mediaItemDetails = mediaItemDetails2;
    }

    public final MediaWrapper getMedia() {
        MediaWrapper mediaWrapper = this.media;
        if (mediaWrapper != null) {
            return mediaWrapper;
        }
        Intrinsics.throwUninitializedPropertyAccessException("media");
        return null;
    }

    public final void setMedia(MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(mediaWrapper, "<set-?>");
        this.media = mediaWrapper;
    }

    public final boolean getMediaStarted() {
        return this.mediaStarted;
    }

    public final void setMediaStarted(boolean z) {
        this.mediaStarted = z;
    }

    public final MediatorLiveData<MediaWrapper> getBrowserFavUpdated() {
        return this.browserFavUpdated;
    }

    public final boolean getListenForNetworkFav() {
        return this.listenForNetworkFav;
    }

    public final void setListenForNetworkFav(boolean z) {
        this.listenForNetworkFav = z;
    }
}
