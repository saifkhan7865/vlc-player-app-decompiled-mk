package org.videolan.vlc.media;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.google.android.material.snackbar.Snackbar;
import java.util.List;
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
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.AudioPlayerContainerActivity;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H@"}, d2 = {"<anonymous>", "", "service", "Lorg/videolan/vlc/PlaybackService;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.media.MediaUtils$appendMedia$1", f = "MediaUtils.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MediaUtils.kt */
final class MediaUtils$appendMedia$1 extends SuspendLambda implements Function2<PlaybackService, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ List<MediaWrapper> $media;
    /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaUtils$appendMedia$1(List<? extends MediaWrapper> list, Context context, Continuation<? super MediaUtils$appendMedia$1> continuation) {
        super(2, continuation);
        this.$media = list;
        this.$context = context;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MediaUtils$appendMedia$1 mediaUtils$appendMedia$1 = new MediaUtils$appendMedia$1(this.$media, this.$context, continuation);
        mediaUtils$appendMedia$1.L$0 = obj;
        return mediaUtils$appendMedia$1;
    }

    public final Object invoke(PlaybackService playbackService, Continuation<? super Unit> continuation) {
        return ((MediaUtils$appendMedia$1) create(playbackService, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            PlaybackService.append$default((PlaybackService) this.L$0, (List) this.$media, 0, 2, (Object) null);
            Context context = this.$context;
            List<MediaWrapper> list = this.$media;
            if (context instanceof Activity) {
                String quantityString = context.getResources().getQuantityString(R.plurals.tracks_appended, list.size(), new Object[]{Boxing.boxInt(list.size())});
                Intrinsics.checkNotNullExpressionValue(quantityString, "getQuantityString(...)");
                if (context instanceof AudioPlayerContainerActivity) {
                    Snackbar.make((View) ((AudioPlayerContainerActivity) context).getAppBarLayout(), (CharSequence) quantityString, 0).show();
                } else {
                    Snackbar.make(((Activity) context).findViewById(16908290), (CharSequence) quantityString, 0).show();
                }
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
