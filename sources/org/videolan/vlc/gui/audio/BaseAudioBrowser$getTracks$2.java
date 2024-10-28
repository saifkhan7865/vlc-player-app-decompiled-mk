package org.videolan.vlc.gui.audio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004*\u00020\u0005H@"}, d2 = {"<anonymous>", "Ljava/util/ArrayList;", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "T", "Lorg/videolan/vlc/viewmodels/MedialibraryViewModel;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.audio.BaseAudioBrowser$getTracks$2", f = "BaseAudioBrowser.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: BaseAudioBrowser.kt */
final class BaseAudioBrowser$getTracks$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<MediaWrapper>>, Object> {
    final /* synthetic */ List<MediaLibraryItem> $this_getTracks;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseAudioBrowser$getTracks$2(List<? extends MediaLibraryItem> list, Continuation<? super BaseAudioBrowser$getTracks$2> continuation) {
        super(2, continuation);
        this.$this_getTracks = list;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BaseAudioBrowser$getTracks$2(this.$this_getTracks, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ArrayList<MediaWrapper>> continuation) {
        return ((BaseAudioBrowser$getTracks$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            ArrayList arrayList = new ArrayList();
            for (MediaLibraryItem tracks : this.$this_getTracks) {
                MediaWrapper[] tracks2 = tracks.getTracks();
                arrayList.addAll(Arrays.asList(Arrays.copyOf(tracks2, tracks2.length)));
            }
            return arrayList;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
