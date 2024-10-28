package org.videolan.vlc;

import android.content.Context;
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
import org.videolan.medialibrary.Tools;
import org.videolan.medialibrary.interfaces.media.Bookmark;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.MediaSessionCallback$onCustomAction$1", f = "MediaSessionCallback.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MediaSessionCallback.kt */
final class MediaSessionCallback$onCustomAction$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ MediaSessionCallback this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaSessionCallback$onCustomAction$1(MediaSessionCallback mediaSessionCallback, Continuation<? super MediaSessionCallback$onCustomAction$1> continuation) {
        super(2, continuation);
        this.this$0 = mediaSessionCallback;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MediaSessionCallback$onCustomAction$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MediaSessionCallback$onCustomAction$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            Context applicationContext = this.this$0.playbackService.getApplicationContext();
            MediaWrapper currentMediaWrapper = this.this$0.playbackService.getCurrentMediaWrapper();
            if (currentMediaWrapper != null) {
                MediaSessionCallback mediaSessionCallback = this.this$0;
                Bookmark addBookmark = currentMediaWrapper.addBookmark(mediaSessionCallback.playbackService.getTime());
                String string = applicationContext.getString(R.string.bookmark_default_name, new Object[]{Tools.millisToString(mediaSessionCallback.playbackService.getTime())});
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                if (addBookmark != null) {
                    Boxing.boxBoolean(addBookmark.setName(string));
                }
                mediaSessionCallback.playbackService.displayPlaybackMessage(R.string.saved, string);
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
