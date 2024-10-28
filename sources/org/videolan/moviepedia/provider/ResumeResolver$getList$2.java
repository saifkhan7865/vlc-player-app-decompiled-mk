package org.videolan.moviepedia.provider;

import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\u0010\u0000\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0004\u0012\u00020\u00040\u0001*\u00020\u0005H@"}, d2 = {"<anonymous>", "Lkotlin/Pair;", "", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.moviepedia.provider.ResumeResolver$getList$2", f = "MediaScrapingTvshowProvider.kt", i = {}, l = {143}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MediaScrapingTvshowProvider.kt */
final class ResumeResolver$getList$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Pair<? extends List<? extends MediaWrapper>, ? extends Integer>>, Object> {
    final /* synthetic */ String $id;
    final /* synthetic */ MediaScrapingTvshowProvider $provider;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ResumeResolver$getList$2(MediaScrapingTvshowProvider mediaScrapingTvshowProvider, String str, Continuation<? super ResumeResolver$getList$2> continuation) {
        super(2, continuation);
        this.$provider = mediaScrapingTvshowProvider;
        this.$id = str;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ResumeResolver$getList$2(this.$provider, this.$id, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Pair<? extends List<? extends MediaWrapper>, Integer>> continuation) {
        return ((ResumeResolver$getList$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = this.$provider.getResumeMediasById(StringsKt.substringAfter$default(this.$id, ResumeResolver.INSTANCE.getPrefix(), (String) null, 2, (Object) null), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return new Pair(obj, Boxing.boxInt(0));
    }
}
