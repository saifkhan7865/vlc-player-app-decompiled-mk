package org.videolan.vlc.repository;

import android.net.Uri;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.vlc.mediadb.models.BrowserFav;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.repository.BrowserFavRepository$addNetworkFavItem$2", f = "BrowserFavRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: BrowserFavRepository.kt */
final class BrowserFavRepository$addNetworkFavItem$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $iconUrl;
    final /* synthetic */ String $title;
    final /* synthetic */ Uri $uri;
    int label;
    final /* synthetic */ BrowserFavRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BrowserFavRepository$addNetworkFavItem$2(BrowserFavRepository browserFavRepository, Uri uri, String str, String str2, Continuation<? super BrowserFavRepository$addNetworkFavItem$2> continuation) {
        super(2, continuation);
        this.this$0 = browserFavRepository;
        this.$uri = uri;
        this.$title = str;
        this.$iconUrl = str2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BrowserFavRepository$addNetworkFavItem$2(this.this$0, this.$uri, this.$title, this.$iconUrl, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BrowserFavRepository$addNetworkFavItem$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0.browserFavDao.insert(new BrowserFav(this.$uri, 0, this.$title, this.$iconUrl));
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}