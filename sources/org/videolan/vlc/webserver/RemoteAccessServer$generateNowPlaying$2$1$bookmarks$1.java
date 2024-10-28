package org.videolan.vlc.webserver;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.Bookmark;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a&\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002 \u0003*\u0012\u0012\u000e\b\u0001\u0012\n \u0003*\u0004\u0018\u00010\u00020\u00020\u00010\u0001*\u00020\u0004H@"}, d2 = {"<anonymous>", "", "Lorg/videolan/medialibrary/interfaces/media/Bookmark;", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.webserver.RemoteAccessServer$generateNowPlaying$2$1$bookmarks$1", f = "RemoteAccessServer.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: RemoteAccessServer.kt */
final class RemoteAccessServer$generateNowPlaying$2$1$bookmarks$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Bookmark[]>, Object> {
    final /* synthetic */ MediaWrapper $media;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteAccessServer$generateNowPlaying$2$1$bookmarks$1(MediaWrapper mediaWrapper, Continuation<? super RemoteAccessServer$generateNowPlaying$2$1$bookmarks$1> continuation) {
        super(2, continuation);
        this.$media = mediaWrapper;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RemoteAccessServer$generateNowPlaying$2$1$bookmarks$1(this.$media, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Bookmark[]> continuation) {
        return ((RemoteAccessServer$generateNowPlaying$2$1$bookmarks$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            Bookmark[] bookmarks = this.$media.getBookmarks();
            return bookmarks == null ? new Bookmark[0] : bookmarks;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
