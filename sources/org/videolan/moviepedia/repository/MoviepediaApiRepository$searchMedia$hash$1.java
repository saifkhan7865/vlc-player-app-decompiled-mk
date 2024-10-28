package org.videolan.moviepedia.repository;

import android.net.Uri;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.tools.FileUtils;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.moviepedia.repository.MoviepediaApiRepository$searchMedia$hash$1", f = "MoviepediaApiRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MoviepediaApiRepository.kt */
final class MoviepediaApiRepository$searchMedia$hash$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
    final /* synthetic */ Uri $uri;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MoviepediaApiRepository$searchMedia$hash$1(Uri uri, Continuation<? super MoviepediaApiRepository$searchMedia$hash$1> continuation) {
        super(2, continuation);
        this.$uri = uri;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MoviepediaApiRepository$searchMedia$hash$1(this.$uri, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
        return ((MoviepediaApiRepository$searchMedia$hash$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return FileUtils.INSTANCE.computeHash(new File(this.$uri.getPath()));
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
