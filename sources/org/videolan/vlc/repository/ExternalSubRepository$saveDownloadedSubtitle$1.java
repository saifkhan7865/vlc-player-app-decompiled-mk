package org.videolan.vlc.repository;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.vlc.mediadb.models.ExternalSub;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.repository.ExternalSubRepository$saveDownloadedSubtitle$1", f = "ExternalSubRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ExternalSubRepository.kt */
final class ExternalSubRepository$saveDownloadedSubtitle$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $idSubtitle;
    final /* synthetic */ String $language;
    final /* synthetic */ String $mediaPath;
    final /* synthetic */ String $movieReleaseName;
    final /* synthetic */ String $subtitlePath;
    int label;
    final /* synthetic */ ExternalSubRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ExternalSubRepository$saveDownloadedSubtitle$1(ExternalSubRepository externalSubRepository, String str, String str2, String str3, String str4, String str5, Continuation<? super ExternalSubRepository$saveDownloadedSubtitle$1> continuation) {
        super(2, continuation);
        this.this$0 = externalSubRepository;
        this.$idSubtitle = str;
        this.$subtitlePath = str2;
        this.$mediaPath = str3;
        this.$language = str4;
        this.$movieReleaseName = str5;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ExternalSubRepository$saveDownloadedSubtitle$1(this.this$0, this.$idSubtitle, this.$subtitlePath, this.$mediaPath, this.$language, this.$movieReleaseName, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ExternalSubRepository$saveDownloadedSubtitle$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0.externalSubDao.insert(new ExternalSub(this.$idSubtitle, this.$subtitlePath, this.$mediaPath, this.$language, this.$movieReleaseName));
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
