package org.videolan.vlc.viewmodels.browser;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.viewmodels.browser.BrowserModel$updateMediaPlayed$2", f = "BrowserModel.kt", i = {}, l = {166}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: BrowserModel.kt */
final class BrowserModel$updateMediaPlayed$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MediaWrapper $mw;
    int label;
    final /* synthetic */ BrowserModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BrowserModel$updateMediaPlayed$2(BrowserModel browserModel, MediaWrapper mediaWrapper, Continuation<? super BrowserModel$updateMediaPlayed$2> continuation) {
        super(2, continuation);
        this.this$0 = browserModel;
        this.$mw = mediaWrapper;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BrowserModel$updateMediaPlayed$2(this.this$0, this.$mw, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BrowserModel$updateMediaPlayed$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        MediaWrapper media;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.this$0.getDataset().getList().contains(this.$mw) && (media = this.this$0.getProvider().getMedialibrary$vlc_android_release().getMedia(this.$mw.getId())) != null) {
                BrowserModel browserModel = this.this$0;
                this.label = 1;
                if (BuildersKt.withContext(Dispatchers.getMain(), new BrowserModel$updateMediaPlayed$2$1$1(browserModel, media, (Continuation<? super BrowserModel$updateMediaPlayed$2$1$1>) null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
