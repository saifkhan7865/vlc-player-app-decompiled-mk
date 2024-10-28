package org.videolan.vlc.webserver;

import android.content.Context;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.tools.livedata.LiveDataset;
import org.videolan.vlc.providers.BrowserProvider;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.webserver.RemoteAccessRoutingKt$getProviderDescriptions$1", f = "RemoteAccessRouting.kt", i = {}, l = {1495}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: RemoteAccessRouting.kt */
final class RemoteAccessRoutingKt$getProviderDescriptions$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ LiveDataset<MediaLibraryItem> $dataset;
    final /* synthetic */ ArrayList<Pair<Integer, String>> $descriptions;
    final /* synthetic */ BrowserProvider $provider;
    final /* synthetic */ CoroutineScope $scope;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteAccessRoutingKt$getProviderDescriptions$1(BrowserProvider browserProvider, LiveDataset<MediaLibraryItem> liveDataset, ArrayList<Pair<Integer, String>> arrayList, Context context, CoroutineScope coroutineScope, Continuation<? super RemoteAccessRoutingKt$getProviderDescriptions$1> continuation) {
        super(2, continuation);
        this.$provider = browserProvider;
        this.$dataset = liveDataset;
        this.$descriptions = arrayList;
        this.$context = context;
        this.$scope = coroutineScope;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RemoteAccessRoutingKt$getProviderDescriptions$1(this.$provider, this.$dataset, this.$descriptions, this.$context, this.$scope, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RemoteAccessRoutingKt$getProviderDescriptions$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LiveDataset<MediaLibraryItem> liveDataset = this.$dataset;
            ArrayList<Pair<Integer, String>> arrayList = this.$descriptions;
            Context context = this.$context;
            CoroutineScope coroutineScope = this.$scope;
            this.label = 1;
            if (BuildersKt.withContext(Dispatchers.getMain().getImmediate(), new RemoteAccessRoutingKt$getProviderDescriptions$1$invokeSuspend$$inlined$observeLiveDataUntil$1(20000, this.$provider.getDescriptionUpdate(), (Continuation) null, liveDataset, arrayList, context, coroutineScope), this) == coroutine_suspended) {
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
