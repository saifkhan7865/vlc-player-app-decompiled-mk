package org.videolan.vlc.repository;

import android.net.Uri;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.vlc.mediadb.models.BrowserFav;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.repository.BrowserFavRepository$isFavNetwork$2", f = "BrowserFavRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: BrowserFavRepository.kt */
final class BrowserFavRepository$isFavNetwork$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    final /* synthetic */ Uri $searchUri;
    int label;
    final /* synthetic */ BrowserFavRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BrowserFavRepository$isFavNetwork$2(BrowserFavRepository browserFavRepository, Uri uri, Continuation<? super BrowserFavRepository$isFavNetwork$2> continuation) {
        super(2, continuation);
        this.this$0 = browserFavRepository;
        this.$searchUri = uri;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BrowserFavRepository$isFavNetwork$2(this.this$0, this.$searchUri, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((BrowserFavRepository$isFavNetwork$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            Iterable iterable = this.this$0.browserFavDao.get(this.$searchUri);
            Uri uri = this.$searchUri;
            boolean z = false;
            if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                Iterator it = iterable.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    BrowserFav browserFav = (BrowserFav) it.next();
                    if (browserFav.getType() == 0 && Intrinsics.areEqual((Object) browserFav.getUri(), (Object) uri)) {
                        z = true;
                        break;
                    }
                }
            }
            return Boxing.boxBoolean(z);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
