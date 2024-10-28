package org.videolan.vlc.providers;

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
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H@"}, d2 = {"<anonymous>", "", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.providers.BrowserProvider$parseSubDirectoriesImpl$currentMediaList$1", f = "BrowserProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: BrowserProvider.kt */
final class BrowserProvider$parseSubDirectoriesImpl$currentMediaList$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends MediaLibraryItem>>, Object> {
    int label;
    final /* synthetic */ BrowserProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BrowserProvider$parseSubDirectoriesImpl$currentMediaList$1(BrowserProvider browserProvider, Continuation<? super BrowserProvider$parseSubDirectoriesImpl$currentMediaList$1> continuation) {
        super(2, continuation);
        this.this$0 = browserProvider;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BrowserProvider$parseSubDirectoriesImpl$currentMediaList$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends MediaLibraryItem>> continuation) {
        return ((BrowserProvider$parseSubDirectoriesImpl$currentMediaList$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return CollectionsKt.toList(this.this$0.getDataset().getValue());
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
