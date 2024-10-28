package io.ktor.network.selector;

import androidx.tvprovider.media.tv.TvContractCompat;
import java.nio.channels.SelectableChannel;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\b\u0010\u0018\u00002\u00020!B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\b\u0010\u0007J\u001f\u0010\r\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\r\u0010\u000eR\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0002\u001a\u00020\u00018\u0016X\u0004¢\u0006\f\n\u0004\b\u0002\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0018\u001a\u00020\u00158VX\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0019\u001a\u00020\u000b8VX\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001c\u001a\u00020\u001b8\u0016X\u0004¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f¨\u0006 "}, d2 = {"Lio/ktor/network/selector/SelectableBase;", "Ljava/nio/channels/SelectableChannel;", "channel", "<init>", "(Ljava/nio/channels/SelectableChannel;)V", "", "close", "()V", "dispose", "Lio/ktor/network/selector/SelectInterest;", "interest", "", "state", "interestOp", "(Lio/ktor/network/selector/SelectInterest;Z)V", "Ljava/util/concurrent/atomic/AtomicBoolean;", "_isClosed", "Ljava/util/concurrent/atomic/AtomicBoolean;", "Ljava/nio/channels/SelectableChannel;", "getChannel", "()Ljava/nio/channels/SelectableChannel;", "", "getInterestedOps", "()I", "interestedOps", "isClosed", "()Z", "Lio/ktor/network/selector/InterestSuspensionsMap;", "suspensions", "Lio/ktor/network/selector/InterestSuspensionsMap;", "getSuspensions", "()Lio/ktor/network/selector/InterestSuspensionsMap;", "ktor-network", "Lio/ktor/network/selector/Selectable;"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SelectableJvm.kt */
public class SelectableBase implements Selectable {
    private static final /* synthetic */ AtomicIntegerFieldUpdater _interestedOps$FU = AtomicIntegerFieldUpdater.newUpdater(SelectableBase.class, "_interestedOps");
    private volatile /* synthetic */ int _interestedOps = 0;
    private final AtomicBoolean _isClosed = new AtomicBoolean(false);
    private final SelectableChannel channel;
    private final InterestSuspensionsMap suspensions = new InterestSuspensionsMap();

    public SelectableBase(SelectableChannel selectableChannel) {
        Intrinsics.checkNotNullParameter(selectableChannel, TvContractCompat.PARAM_CHANNEL);
        this.channel = selectableChannel;
    }

    public SelectableChannel getChannel() {
        return this.channel;
    }

    public InterestSuspensionsMap getSuspensions() {
        return this.suspensions;
    }

    public boolean isClosed() {
        return this._isClosed.get();
    }

    public int getInterestedOps() {
        return this._interestedOps;
    }

    public void interestOp(SelectInterest selectInterest, boolean z) {
        int i;
        Intrinsics.checkNotNullParameter(selectInterest, "interest");
        int flag = selectInterest.getFlag();
        do {
            i = this._interestedOps;
        } while (!_interestedOps$FU.compareAndSet(this, i, z ? i | flag : (flag ^ -1) & i));
    }

    public void close() {
        if (this._isClosed.compareAndSet(false, true)) {
            this._interestedOps = 0;
            InterestSuspensionsMap suspensions2 = getSuspensions();
            for (SelectInterest removeSuspension : SelectInterest.Companion.getAllInterests()) {
                CancellableContinuation<Unit> removeSuspension2 = suspensions2.removeSuspension(removeSuspension);
                if (removeSuspension2 != null) {
                    Result.Companion companion = Result.Companion;
                    removeSuspension2.resumeWith(Result.m1774constructorimpl(ResultKt.createFailure(new ClosedChannelCancellationException())));
                }
            }
        }
    }

    public void dispose() {
        close();
    }
}
