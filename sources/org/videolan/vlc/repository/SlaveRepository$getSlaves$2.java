package org.videolan.vlc.repository;

import android.database.sqlite.SQLiteException;
import android.net.Uri;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.libvlc.interfaces.IMedia;
import org.videolan.vlc.mediadb.models.Slave;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H@"}, d2 = {"<anonymous>", "", "Lorg/videolan/libvlc/interfaces/IMedia$Slave;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.repository.SlaveRepository$getSlaves$2", f = "SlaveRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: SlaveRepository.kt */
final class SlaveRepository$getSlaves$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends IMedia.Slave>>, Object> {
    final /* synthetic */ String $mrl;
    int label;
    final /* synthetic */ SlaveRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SlaveRepository$getSlaves$2(SlaveRepository slaveRepository, String str, Continuation<? super SlaveRepository$getSlaves$2> continuation) {
        super(2, continuation);
        this.this$0 = slaveRepository;
        this.$mrl = str;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SlaveRepository$getSlaves$2(this.this$0, this.$mrl, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends IMedia.Slave>> continuation) {
        return ((SlaveRepository$getSlaves$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        List<Slave> list;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            try {
                list = this.this$0.slaveDao.get(this.$mrl);
            } catch (SQLiteException unused) {
                list = CollectionsKt.emptyList();
            }
            Iterable<Slave> iterable = list;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (Slave slave : iterable) {
                String uri = slave.getUri();
                if (uri.length() > 0) {
                    uri = Uri.decode(slave.getUri());
                    Intrinsics.checkNotNullExpressionValue(uri, "decode(...)");
                }
                arrayList.add(new IMedia.Slave(slave.getType(), slave.getPriority(), uri));
            }
            return (List) arrayList;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
