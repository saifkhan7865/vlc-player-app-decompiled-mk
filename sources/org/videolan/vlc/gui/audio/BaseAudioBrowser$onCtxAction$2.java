package org.videolan.vlc.gui.audio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
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
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.util.KextensionsKt;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u0004H@"}, d2 = {"<anonymous>", "", "T", "Lorg/videolan/vlc/viewmodels/MedialibraryViewModel;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.audio.BaseAudioBrowser$onCtxAction$2", f = "BaseAudioBrowser.kt", i = {}, l = {450}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: BaseAudioBrowser.kt */
final class BaseAudioBrowser$onCtxAction$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MediaLibraryItem $media;
    int label;
    final /* synthetic */ BaseAudioBrowser<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseAudioBrowser$onCtxAction$2(BaseAudioBrowser<T> baseAudioBrowser, MediaLibraryItem mediaLibraryItem, Continuation<? super BaseAudioBrowser$onCtxAction$2> continuation) {
        super(2, continuation);
        this.this$0 = baseAudioBrowser;
        this.$media = mediaLibraryItem;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BaseAudioBrowser$onCtxAction$2(this.this$0, this.$media, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BaseAudioBrowser$onCtxAction$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FragmentActivity requireActivity = this.this$0.requireActivity();
            Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
            MediaLibraryItem mediaLibraryItem = this.$media;
            Intrinsics.checkNotNull(mediaLibraryItem, "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper");
            this.label = 1;
            if (KextensionsKt.share((AppCompatActivity) requireActivity, (MediaWrapper) mediaLibraryItem, (Continuation<? super Unit>) this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
