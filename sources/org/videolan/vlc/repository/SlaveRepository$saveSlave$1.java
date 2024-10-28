package org.videolan.vlc.repository;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.vlc.mediadb.models.Slave;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.repository.SlaveRepository$saveSlave$1", f = "SlaveRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: SlaveRepository.kt */
final class SlaveRepository$saveSlave$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $mediaPath;
    final /* synthetic */ int $priority;
    final /* synthetic */ int $type;
    final /* synthetic */ String $uriString;
    int label;
    final /* synthetic */ SlaveRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SlaveRepository$saveSlave$1(SlaveRepository slaveRepository, String str, int i, int i2, String str2, Continuation<? super SlaveRepository$saveSlave$1> continuation) {
        super(2, continuation);
        this.this$0 = slaveRepository;
        this.$mediaPath = str;
        this.$type = i;
        this.$priority = i2;
        this.$uriString = str2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SlaveRepository$saveSlave$1(this.this$0, this.$mediaPath, this.$type, this.$priority, this.$uriString, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SlaveRepository$saveSlave$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0.slaveDao.insert(new Slave(this.$mediaPath, this.$type, this.$priority, this.$uriString));
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
