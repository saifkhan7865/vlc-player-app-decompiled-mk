package androidx.room;

import androidx.room.InvalidationTracker;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlinx.coroutines.channels.ProducerScope;

@Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0016¨\u0006\u0007"}, d2 = {"androidx/room/RoomDatabaseKt$invalidationTrackerFlow$1$observer$1", "Landroidx/room/InvalidationTracker$Observer;", "onInvalidated", "", "tables", "", "", "room-ktx_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RoomDatabaseExt.kt */
public final class RoomDatabaseKt$invalidationTrackerFlow$1$observer$1 extends InvalidationTracker.Observer {
    final /* synthetic */ ProducerScope<Set<String>> $$this$callbackFlow;
    final /* synthetic */ AtomicBoolean $ignoreInvalidation;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RoomDatabaseKt$invalidationTrackerFlow$1$observer$1(String[] strArr, AtomicBoolean atomicBoolean, ProducerScope<? super Set<String>> producerScope) {
        super(strArr);
        this.$ignoreInvalidation = atomicBoolean;
        this.$$this$callbackFlow = producerScope;
    }

    public void onInvalidated(Set<String> set) {
        if (!this.$ignoreInvalidation.get()) {
            this.$$this$callbackFlow.m1139trySendJP2dKIU(set);
        }
    }
}
