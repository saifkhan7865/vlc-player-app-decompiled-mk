package org.videolan.television.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.DummyItem;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.PlaybackService;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.television.viewmodel.MainTvModel$updateNowPlaying$1", f = "MainTvModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MainTvModel.kt */
final class MainTvModel$updateNowPlaying$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ MainTvModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MainTvModel$updateNowPlaying$1(MainTvModel mainTvModel, Continuation<? super MainTvModel$updateNowPlaying$1> continuation) {
        super(2, continuation);
        this.this$0 = mainTvModel;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MainTvModel$updateNowPlaying$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MainTvModel$updateNowPlaying$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        DummyItem dummyItem;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            List arrayList = new ArrayList();
            PlaybackService instance = PlaybackService.Companion.getInstance();
            if (instance != null) {
                MediaWrapper currentMediaWrapper = instance.getCurrentMediaWrapper();
                if (currentMediaWrapper == null) {
                    dummyItem = null;
                } else if (instance.getPlaylistManager().getPlayer().isVideoPlaying()) {
                    dummyItem = new DummyItem(26, currentMediaWrapper.getTitle(), currentMediaWrapper.getArtist());
                    dummyItem.setArtWork(instance.getCoverArt());
                } else {
                    dummyItem = new DummyItem(20, currentMediaWrapper.getTitle(), currentMediaWrapper.getArtist());
                    dummyItem.setArtWork(instance.getCoverArt());
                }
                if (dummyItem != null) {
                    arrayList.add(0, dummyItem);
                }
            }
            LiveData<List<MediaLibraryItem>> nowPlaying = this.this$0.getNowPlaying();
            Intrinsics.checkNotNull(nowPlaying, "null cannot be cast to non-null type androidx.lifecycle.MutableLiveData<kotlin.collections.List<org.videolan.medialibrary.media.MediaLibraryItem>>");
            ((MutableLiveData) nowPlaying).setValue(arrayList);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
