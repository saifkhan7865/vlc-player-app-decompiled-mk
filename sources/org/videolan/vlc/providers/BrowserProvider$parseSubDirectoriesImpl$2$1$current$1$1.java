package org.videolan.vlc.providers;

import android.text.format.Formatter;
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
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.providers.BrowserProvider$parseSubDirectoriesImpl$2$1$current$1$1", f = "BrowserProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: BrowserProvider.kt */
final class BrowserProvider$parseSubDirectoriesImpl$2$1$current$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.IntRef $currentParsedPosition;
    final /* synthetic */ long $it;
    final /* synthetic */ MediaLibraryItem $item;
    int label;
    final /* synthetic */ BrowserProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BrowserProvider$parseSubDirectoriesImpl$2$1$current$1$1(MediaLibraryItem mediaLibraryItem, long j, BrowserProvider browserProvider, Ref.IntRef intRef, Continuation<? super BrowserProvider$parseSubDirectoriesImpl$2$1$current$1$1> continuation) {
        super(2, continuation);
        this.$item = mediaLibraryItem;
        this.$it = j;
        this.this$0 = browserProvider;
        this.$currentParsedPosition = intRef;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BrowserProvider$parseSubDirectoriesImpl$2$1$current$1$1(this.$item, this.$it, this.this$0, this.$currentParsedPosition, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BrowserProvider$parseSubDirectoriesImpl$2$1$current$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            ((MediaWrapper) this.$item).setDescription(this.$it == 0 ? "" : Formatter.formatFileSize(this.this$0.getContext(), this.$it));
            this.this$0.getDescriptionUpdate().setValue(new Pair(Boxing.boxInt(this.$currentParsedPosition.element), ((MediaWrapper) this.$item).getDescription()));
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}