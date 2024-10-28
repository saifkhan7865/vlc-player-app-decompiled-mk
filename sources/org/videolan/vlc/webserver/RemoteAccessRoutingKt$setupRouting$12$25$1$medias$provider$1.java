package org.videolan.vlc.webserver;

import android.content.Context;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.tools.livedata.LiveDataset;
import org.videolan.vlc.providers.FileBrowserProvider;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "Lorg/videolan/vlc/providers/FileBrowserProvider;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$12$25$1$medias$provider$1", f = "RemoteAccessRouting.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: RemoteAccessRouting.kt */
final class RemoteAccessRoutingKt$setupRouting$12$25$1$medias$provider$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super FileBrowserProvider>, Object> {
    final /* synthetic */ Context $appContext;
    final /* synthetic */ LiveDataset<MediaLibraryItem> $dataset;
    final /* synthetic */ String $decodedPath;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteAccessRoutingKt$setupRouting$12$25$1$medias$provider$1(Context context, LiveDataset<MediaLibraryItem> liveDataset, String str, Continuation<? super RemoteAccessRoutingKt$setupRouting$12$25$1$medias$provider$1> continuation) {
        super(2, continuation);
        this.$appContext = context;
        this.$dataset = liveDataset;
        this.$decodedPath = str;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RemoteAccessRoutingKt$setupRouting$12$25$1$medias$provider$1(this.$appContext, this.$dataset, this.$decodedPath, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super FileBrowserProvider> continuation) {
        return ((RemoteAccessRoutingKt$setupRouting$12$25$1$medias$provider$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return new FileBrowserProvider(this.$appContext, this.$dataset, this.$decodedPath, false, false, 10, false);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}