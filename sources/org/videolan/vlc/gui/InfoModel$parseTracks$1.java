package org.videolan.vlc.gui;

import android.content.Context;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import org.videolan.libvlc.interfaces.IMedia;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.VersionDependantKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.InfoModel$parseTracks$1", f = "InfoActivity.kt", i = {0}, l = {264}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
/* compiled from: InfoActivity.kt */
final class InfoModel$parseTracks$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ MediaWrapper $mw;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ InfoModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    InfoModel$parseTracks$1(InfoModel infoModel, Context context, MediaWrapper mediaWrapper, Continuation<? super InfoModel$parseTracks$1> continuation) {
        super(2, continuation);
        this.this$0 = infoModel;
        this.$context = context;
        this.$mw = mediaWrapper;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        InfoModel$parseTracks$1 infoModel$parseTracks$1 = new InfoModel$parseTracks$1(this.this$0, this.$context, this.$mw, continuation);
        infoModel$parseTracks$1.L$0 = obj;
        return infoModel$parseTracks$1;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((InfoModel$parseTracks$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        boolean z = true;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            this.L$0 = coroutineScope2;
            this.label = 1;
            Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new InfoModel$parseTracks$1$media$1(this.$context, this.this$0, this.$mw, (Continuation<? super InfoModel$parseTracks$1$media$1>) null), this);
            if (withContext == coroutine_suspended) {
                return coroutine_suspended;
            }
            coroutineScope = coroutineScope2;
            obj = withContext;
        } else if (i == 1) {
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        IMedia iMedia = (IMedia) obj;
        if (!CoroutineScopeKt.isActive(coroutineScope)) {
            return Unit.INSTANCE;
        }
        Intrinsics.checkNotNull(iMedia);
        List<IMedia.Track> allTracks = VersionDependantKt.getAllTracks(iMedia);
        Iterable asReversed = CollectionsKt.asReversed(allTracks);
        if (!(asReversed instanceof Collection) || !((Collection) asReversed).isEmpty()) {
            Iterator it = asReversed.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (((IMedia.Track) it.next()).type == 2) {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        z = false;
        iMedia.release();
        this.this$0.getHasSubs().setValue(Boxing.boxBoolean(z));
        this.this$0.getMediaTracks().setValue(allTracks);
        return Unit.INSTANCE;
    }
}
