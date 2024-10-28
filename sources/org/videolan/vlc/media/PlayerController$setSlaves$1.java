package org.videolan.vlc.media;

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
import kotlinx.coroutines.CoroutineScope;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.interfaces.IMedia;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.resources.Constants;
import org.videolan.vlc.repository.SlaveRepository;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.media.PlayerController$setSlaves$1", f = "PlayerController.kt", i = {0}, l = {216}, m = "invokeSuspend", n = {"slaves"}, s = {"L$0"})
/* compiled from: PlayerController.kt */
final class PlayerController$setSlaves$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ IMedia $media;
    final /* synthetic */ MediaWrapper $mw;
    Object L$0;
    int label;
    final /* synthetic */ PlayerController this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlayerController$setSlaves$1(PlayerController playerController, MediaWrapper mediaWrapper, IMedia iMedia, Continuation<? super PlayerController$setSlaves$1> continuation) {
        super(2, continuation);
        this.this$0 = playerController;
        this.$mw = mediaWrapper;
        this.$media = iMedia;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlayerController$setSlaves$1(this.this$0, this.$mw, this.$media, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlayerController$setSlaves$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IMedia.Slave[] slaveArr;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.this$0.getMediaplayer().isReleased()) {
                return Unit.INSTANCE;
            }
            IMedia.Slave[] slaves = this.$mw.getSlaves();
            if (slaves != null) {
                IMedia iMedia = this.$media;
                for (IMedia.Slave addSlave : slaves) {
                    iMedia.addSlave(addSlave);
                }
            }
            this.$media.release();
            SlaveRepository access$getSlaveRepository = this.this$0.getSlaveRepository();
            String location = this.$mw.getLocation();
            Intrinsics.checkNotNullExpressionValue(location, "getLocation(...)");
            this.L$0 = slaves;
            this.label = 1;
            Object slaves2 = access$getSlaveRepository.getSlaves(location, this);
            if (slaves2 == coroutine_suspended) {
                return coroutine_suspended;
            }
            slaveArr = slaves;
            obj = slaves2;
        } else if (i == 1) {
            slaveArr = (IMedia.Slave[]) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        PlayerController playerController = this.this$0;
        for (IMedia.Slave slave : (Iterable) obj) {
            if (!PlayerControllerKt.contains(slaveArr, slave)) {
                MediaPlayer mediaplayer = playerController.getMediaplayer();
                int i2 = slave.type;
                String str = slave.uri;
                Intrinsics.checkNotNullExpressionValue(str, Constants.KEY_URI);
                mediaplayer.addSlave(i2, Uri.parse(str), false);
            }
        }
        if (slaveArr != null) {
            this.this$0.getSlaveRepository().saveSlaves(this.$mw);
        }
        return Unit.INSTANCE;
    }
}
