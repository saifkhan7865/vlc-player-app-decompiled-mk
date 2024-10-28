package org.videolan.vlc;

import android.content.Context;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.ArtworkProvider$getCategoryImage$mw$1", f = "ArtworkProvider.kt", i = {}, l = {644, 646}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ArtworkProvider.kt */
final class ArtworkProvider$getCategoryImage$mw$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super MediaLibraryItem>, Object> {
    final /* synthetic */ String $category;
    final /* synthetic */ Context $context;
    final /* synthetic */ long $id;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ArtworkProvider$getCategoryImage$mw$1(String str, Context context, long j, Continuation<? super ArtworkProvider$getCategoryImage$mw$1> continuation) {
        super(2, continuation);
        this.$category = str;
        this.$context = context;
        this.$id = j;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ArtworkProvider$getCategoryImage$mw$1(this.$category, this.$context, this.$id, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super MediaLibraryItem> continuation) {
        return ((ArtworkProvider$getCategoryImage$mw$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            String str = this.$category;
            if (Intrinsics.areEqual((Object) str, (Object) ArtworkProvider.ALBUM)) {
                Context context = this.$context;
                long j = this.$id;
                this.label = 1;
                obj = BuildersKt.withContext(Dispatchers.getIO(), new ArtworkProvider$getCategoryImage$mw$1$invokeSuspend$$inlined$getFromMl$1(context, (Continuation) null, j), this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (!Intrinsics.areEqual((Object) str, (Object) ArtworkProvider.ARTIST)) {
                return null;
            } else {
                Context context2 = this.$context;
                long j2 = this.$id;
                this.label = 2;
                obj = BuildersKt.withContext(Dispatchers.getIO(), new ArtworkProvider$getCategoryImage$mw$1$invokeSuspend$$inlined$getFromMl$2(context2, (Continuation) null, j2), this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return (MediaLibraryItem) obj;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else if (i == 2) {
            ResultKt.throwOnFailure(obj);
            return (MediaLibraryItem) obj;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return (MediaLibraryItem) obj;
    }
}
