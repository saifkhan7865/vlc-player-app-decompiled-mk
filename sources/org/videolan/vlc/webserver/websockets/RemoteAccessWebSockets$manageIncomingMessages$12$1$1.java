package org.videolan.vlc.webserver.websockets;

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
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.videolan.medialibrary.Tools;
import org.videolan.medialibrary.interfaces.media.Bookmark;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets$manageIncomingMessages$12$1$1", f = "RemoteAccessWebSockets.kt", i = {}, l = {228}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: RemoteAccessWebSockets.kt */
final class RemoteAccessWebSockets$manageIncomingMessages$12$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ MediaWrapper $it;
    final /* synthetic */ PlaybackService $service;
    final /* synthetic */ long $time;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteAccessWebSockets$manageIncomingMessages$12$1$1(MediaWrapper mediaWrapper, long j, Context context, PlaybackService playbackService, Continuation<? super RemoteAccessWebSockets$manageIncomingMessages$12$1$1> continuation) {
        super(2, continuation);
        this.$it = mediaWrapper;
        this.$time = j;
        this.$context = context;
        this.$service = playbackService;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RemoteAccessWebSockets$manageIncomingMessages$12$1$1(this.$it, this.$time, this.$context, this.$service, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RemoteAccessWebSockets$manageIncomingMessages$12$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets$manageIncomingMessages$12$1$1$1", f = "RemoteAccessWebSockets.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets$manageIncomingMessages$12$1$1$1  reason: invalid class name */
    /* compiled from: RemoteAccessWebSockets.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(mediaWrapper, j, context, playbackService, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                Bookmark addBookmark = mediaWrapper.addBookmark(j);
                if (addBookmark != null) {
                    Context context = context;
                    int i = R.string.bookmark_default_name;
                    PlaybackService playbackService = playbackService;
                    Intrinsics.checkNotNull(playbackService);
                    Boxing.boxBoolean(addBookmark.setName(context.getString(i, new Object[]{Tools.millisToString(playbackService.getTime())})));
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final MediaWrapper mediaWrapper = this.$it;
            final long j = this.$time;
            final Context context = this.$context;
            final PlaybackService playbackService = this.$service;
            this.label = 1;
            if (BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass1((Continuation<? super AnonymousClass1>) null), this) == coroutine_suspended) {
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
