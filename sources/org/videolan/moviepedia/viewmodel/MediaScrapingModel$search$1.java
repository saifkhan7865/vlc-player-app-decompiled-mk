package org.videolan.moviepedia.viewmodel;

import androidx.lifecycle.MutableLiveData;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.moviepedia.MediaScraper;
import org.videolan.moviepedia.models.resolver.ResolverResult;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.moviepedia.viewmodel.MediaScrapingModel$search$1", f = "MediaScrapingModel.kt", i = {}, l = {59}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MediaScrapingModel.kt */
final class MediaScrapingModel$search$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $query;
    Object L$0;
    int label;
    final /* synthetic */ MediaScrapingModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaScrapingModel$search$1(MediaScrapingModel mediaScrapingModel, String str, Continuation<? super MediaScrapingModel$search$1> continuation) {
        super(2, continuation);
        this.this$0 = mediaScrapingModel;
        this.$query = str;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MediaScrapingModel$search$1(this.this$0, this.$query, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MediaScrapingModel$search$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        MutableLiveData<ResolverResult> mutableLiveData;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MutableLiveData<ResolverResult> apiResult = this.this$0.getApiResult();
            this.L$0 = apiResult;
            this.label = 1;
            Object searchTitle = MediaScraper.INSTANCE.getMediaResolverApi().searchTitle(this.$query, this);
            if (searchTitle == coroutine_suspended) {
                return coroutine_suspended;
            }
            mutableLiveData = apiResult;
            obj = searchTitle;
        } else if (i == 1) {
            mutableLiveData = (MutableLiveData) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Exception e) {
                this.this$0.getExceptionLiveData().setValue(e);
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        mutableLiveData.setValue(obj);
        return Unit.INSTANCE;
    }
}
