package org.videolan.vlc.media;

import android.content.Context;
import android.content.Intent;
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
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.videolan.medialibrary.interfaces.media.Album;
import org.videolan.medialibrary.interfaces.media.Artist;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.resources.Constants;
import org.videolan.tools.KotlinExtensionsKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.media.MediaUtils$openMediaNoUiFromTvContent$1", f = "MediaUtils.kt", i = {}, l = {594}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MediaUtils.kt */
final class MediaUtils$openMediaNoUiFromTvContent$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ Uri $data;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaUtils$openMediaNoUiFromTvContent$1(Uri uri, Context context, Continuation<? super MediaUtils$openMediaNoUiFromTvContent$1> continuation) {
        super(2, continuation);
        this.$data = uri;
        this.$context = context;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MediaUtils$openMediaNoUiFromTvContent$1(this.$data, this.$context, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MediaUtils$openMediaNoUiFromTvContent$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        String lastPathSegment;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Uri uri = this.$data;
            if (uri == null || (lastPathSegment = uri.getLastPathSegment()) == null) {
                return Unit.INSTANCE;
            }
            if (StringsKt.startsWith$default(lastPathSegment, Constants.CONTENT_PREFIX, false, 2, (Object) null)) {
                Intent putExtra = new Intent(Constants.ACTION_OPEN_CONTENT).putExtra(Constants.EXTRA_CONTENT_ID, lastPathSegment);
                Intrinsics.checkNotNullExpressionValue(putExtra, "putExtra(...)");
                KotlinExtensionsKt.getLocalBroadcastManager(this.$context).sendBroadcast(putExtra);
                return Unit.INSTANCE;
            }
            Context context = this.$context;
            this.label = 1;
            obj = BuildersKt.withContext(Dispatchers.getIO(), new MediaUtils$openMediaNoUiFromTvContent$1$invokeSuspend$$inlined$getFromMl$1(context, (Continuation) null, lastPathSegment), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        MediaLibraryItem mediaLibraryItem = (MediaLibraryItem) obj;
        if (mediaLibraryItem == null) {
            return Unit.INSTANCE;
        }
        if (mediaLibraryItem instanceof MediaWrapper) {
            MediaUtils mediaUtils = MediaUtils.INSTANCE;
            Uri uri2 = ((MediaWrapper) mediaLibraryItem).getUri();
            Intrinsics.checkNotNullExpressionValue(uri2, "getUri(...)");
            mediaUtils.openMediaNoUi(uri2);
        } else if (mediaLibraryItem instanceof Album) {
            MediaUtils.INSTANCE.playAlbum(this.$context, (Album) mediaLibraryItem);
        } else if (mediaLibraryItem instanceof Artist) {
            MediaUtils.INSTANCE.playArtist(this.$context, (Artist) mediaLibraryItem);
        }
        return Unit.INSTANCE;
    }
}
