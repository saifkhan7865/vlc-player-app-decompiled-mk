package org.videolan.vlc.media;

import android.net.Uri;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.videolan.medialibrary.MLServiceLocator;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.R;
import org.videolan.vlc.util.KextensionsKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.media.PlaylistManager$loadLocations$2", f = "PlaylistManager.kt", i = {0}, l = {207, 226}, m = "invokeSuspend", n = {"mediaList"}, s = {"L$0"})
/* compiled from: PlaylistManager.kt */
final class PlaylistManager$loadLocations$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ List<String> $mediaPathList;
    final /* synthetic */ int $position;
    Object L$0;
    int label;
    final /* synthetic */ PlaylistManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaylistManager$loadLocations$2(PlaylistManager playlistManager, int i, List<String> list, Continuation<? super PlaylistManager$loadLocations$2> continuation) {
        super(2, continuation);
        this.this$0 = playlistManager;
        this.$position = i;
        this.$mediaPathList = list;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlaylistManager$loadLocations$2(this.this$0, this.$position, this.$mediaPathList, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlaylistManager$loadLocations$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        final ArrayList arrayList;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            arrayList = new ArrayList();
            final List<String> list = this.$mediaPathList;
            final PlaylistManager playlistManager = this.this$0;
            this.L$0 = arrayList;
            this.label = 1;
            if (BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass1((Continuation<? super AnonymousClass1>) null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            arrayList = (ArrayList) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else if (i == 2) {
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.L$0 = null;
        this.label = 2;
        if (PlaylistManager.load$default(this.this$0, arrayList, this.$position, false, false, this, 12, (Object) null) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.media.PlaylistManager$loadLocations$2$1", f = "PlaylistManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.media.PlaylistManager$loadLocations$2$1  reason: invalid class name */
    /* compiled from: PlaylistManager.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(list, playlistManager, arrayList, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            String str;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                for (String next : list) {
                    MediaWrapper media = playlistManager.getMedialibrary().getMedia(next);
                    if (media != null) {
                        Log.d("VLC/PlaylistManager", "Adding " + media + " to the queue");
                        arrayList.add(media);
                    } else if (!KextensionsKt.validateLocation(next)) {
                        Log.w("VLC/PlaylistManager", "Invalid location " + next);
                        PlaybackService service = playlistManager.getService();
                        if (Intrinsics.areEqual((Object) Uri.parse(next).getScheme(), (Object) "missing")) {
                            str = playlistManager.getService().getResources().getString(R.string.missing_location);
                        } else {
                            str = playlistManager.getService().getResources().getString(R.string.invalid_location, new Object[]{next});
                        }
                        String str2 = str;
                        Intrinsics.checkNotNull(str2);
                        PlaybackService.showToast$default(service, str2, 0, false, 4, (Object) null);
                    } else {
                        Log.v("VLC/PlaylistManager", "Creating on-the-fly Media object for " + next);
                        MediaWrapper abstractMediaWrapper = MLServiceLocator.getAbstractMediaWrapper(Uri.parse(next));
                        Log.d("VLC/PlaylistManager", "Adding " + abstractMediaWrapper + " to the queue");
                        arrayList.add(abstractMediaWrapper);
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
