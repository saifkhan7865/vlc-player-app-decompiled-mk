package org.videolan.television.viewmodel;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.videolan.medialibrary.interfaces.media.Playlist;
import org.videolan.medialibrary.media.DummyItem;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.television.viewmodel.MainTvModel$updatePlaylists$1", f = "MainTvModel.kt", i = {}, l = {340}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MainTvModel.kt */
final class MainTvModel$updatePlaylists$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ MainTvModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MainTvModel$updatePlaylists$1(MainTvModel mainTvModel, Continuation<? super MainTvModel$updatePlaylists$1> continuation) {
        super(2, continuation);
        this.this$0 = mainTvModel;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MainTvModel$updatePlaylists$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MainTvModel$updatePlaylists$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Context context = this.this$0.getContext();
            this.label = 1;
            obj = BuildersKt.withContext(Dispatchers.getIO(), new MainTvModel$updatePlaylists$1$invokeSuspend$$inlined$getFromMl$1(context, (Continuation) null), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        MainTvModel mainTvModel = this.this$0;
        Playlist[] playlistArr = (Playlist[]) obj;
        LiveData<List<MediaLibraryItem>> playlist = mainTvModel.getPlaylist();
        Intrinsics.checkNotNull(playlist, "null cannot be cast to non-null type androidx.lifecycle.MutableLiveData<kotlin.collections.List<org.videolan.medialibrary.media.MediaLibraryItem>>");
        List arrayList = new ArrayList();
        arrayList.add(new DummyItem(8, mainTvModel.getContext().getString(R.string.playlists), ""));
        Intrinsics.checkNotNull(playlistArr);
        CollectionsKt.addAll(arrayList, (T[]) playlistArr);
        ((MutableLiveData) playlist).setValue(arrayList);
        return Unit.INSTANCE;
    }
}
