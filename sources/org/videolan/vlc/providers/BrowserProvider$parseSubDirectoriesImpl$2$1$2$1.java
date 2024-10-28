package org.videolan.vlc.providers;

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
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.providers.BrowserProvider$parseSubDirectoriesImpl$2$1$2$1", f = "BrowserProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: BrowserProvider.kt */
final class BrowserProvider$parseSubDirectoriesImpl$2$1$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $it;
    final /* synthetic */ MediaLibraryItem $item;
    final /* synthetic */ int $position;
    int label;
    final /* synthetic */ BrowserProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BrowserProvider$parseSubDirectoriesImpl$2$1$2$1(MediaLibraryItem mediaLibraryItem, String str, BrowserProvider browserProvider, int i, Continuation<? super BrowserProvider$parseSubDirectoriesImpl$2$1$2$1> continuation) {
        super(2, continuation);
        this.$item = mediaLibraryItem;
        this.$it = str;
        this.this$0 = browserProvider;
        this.$position = i;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BrowserProvider$parseSubDirectoriesImpl$2$1$2$1(this.$item, this.$it, this.this$0, this.$position, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BrowserProvider$parseSubDirectoriesImpl$2$1$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            this.$item.setDescription(this.$it);
            this.this$0.getDescriptionUpdate().setValue(new Pair(Boxing.boxInt(this.$position), this.$it));
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
