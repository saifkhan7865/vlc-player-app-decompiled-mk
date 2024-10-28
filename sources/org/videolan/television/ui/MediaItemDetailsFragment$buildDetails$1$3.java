package org.videolan.television.ui;

import android.net.Uri;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.vlc.repository.BrowserFavRepository;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.television.ui.MediaItemDetailsFragment$buildDetails$1$3", f = "MediaItemDetailsFragment.kt", i = {}, l = {349, 352}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MediaItemDetailsFragment.kt */
final class MediaItemDetailsFragment$buildDetails$1$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $local;
    final /* synthetic */ Uri $uri;
    int label;
    final /* synthetic */ MediaItemDetailsFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaItemDetailsFragment$buildDetails$1$3(boolean z, MediaItemDetailsFragment mediaItemDetailsFragment, Uri uri, Continuation<? super MediaItemDetailsFragment$buildDetails$1$3> continuation) {
        super(2, continuation);
        this.$local = z;
        this.this$0 = mediaItemDetailsFragment;
        this.$uri = uri;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MediaItemDetailsFragment$buildDetails$1$3(this.$local, this.this$0, this.$uri, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MediaItemDetailsFragment$buildDetails$1$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            String str = "";
            BrowserFavRepository browserFavRepository = null;
            if (this.$local) {
                BrowserFavRepository access$getBrowserFavRepository$p = this.this$0.browserFavRepository;
                if (access$getBrowserFavRepository$p == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("browserFavRepository");
                } else {
                    browserFavRepository = access$getBrowserFavRepository$p;
                }
                Uri uri = this.$uri;
                String title = this.this$0.getViewModel().getMediaItemDetails().getTitle();
                if (title != null) {
                    str = title;
                }
                this.label = 1;
                if (browserFavRepository.addLocalFavItem(uri, str, this.this$0.getViewModel().getMediaItemDetails().getArtworkUrl(), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                BrowserFavRepository access$getBrowserFavRepository$p2 = this.this$0.browserFavRepository;
                if (access$getBrowserFavRepository$p2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("browserFavRepository");
                } else {
                    browserFavRepository = access$getBrowserFavRepository$p2;
                }
                Uri uri2 = this.$uri;
                String title2 = this.this$0.getViewModel().getMediaItemDetails().getTitle();
                if (title2 != null) {
                    str = title2;
                }
                this.label = 2;
                if (browserFavRepository.addNetworkFavItem(uri2, str, this.this$0.getViewModel().getMediaItemDetails().getArtworkUrl(), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else if (i == 1 || i == 2) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
