package io.ktor.network.selector;

import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CancellationException;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0010#\n\u0000\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001:\u0001.B\u0007\b\u0000¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0011H\u0004J\u0018\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020 H\u0004J\u001a\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0004J\u0010\u0010!\u001a\u00020\u00192\u0006\u0010\"\u001a\u00020\u0013H\u0004J$\u0010#\u001a\u00020\u00192\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00130%2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00130'H\u0004J \u0010(\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u0011H\u0004J\u0010\u0010)\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u0011H$J!\u0010*\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u00112\u0006\u0010+\u001a\u00020,H@ø\u0001\u0000¢\u0006\u0002\u0010-R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR,\u0010\u0012\u001a\u0004\u0018\u00010\u0011*\u00020\u00132\b\u0010\u0010\u001a\u0004\u0018\u00010\u00118B@BX\u000e¢\u0006\f\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017\u0002\u0004\n\u0002\b\u0019¨\u0006/"}, d2 = {"Lio/ktor/network/selector/SelectorManagerSupport;", "Lio/ktor/network/selector/SelectorManager;", "()V", "cancelled", "", "getCancelled", "()I", "setCancelled", "(I)V", "pending", "getPending", "setPending", "provider", "Ljava/nio/channels/spi/SelectorProvider;", "getProvider", "()Ljava/nio/channels/spi/SelectorProvider;", "newValue", "Lio/ktor/network/selector/Selectable;", "subject", "Ljava/nio/channels/SelectionKey;", "getSubject", "(Ljava/nio/channels/SelectionKey;)Lio/ktor/network/selector/Selectable;", "setSubject", "(Ljava/nio/channels/SelectionKey;Lio/ktor/network/selector/Selectable;)V", "applyInterest", "", "selector", "Ljava/nio/channels/Selector;", "selectable", "cancelAllSuspensions", "attachment", "cause", "", "handleSelectedKey", "key", "handleSelectedKeys", "selectedKeys", "", "keys", "", "notifyClosedImpl", "publishInterest", "select", "interest", "Lio/ktor/network/selector/SelectInterest;", "(Lio/ktor/network/selector/Selectable;Lio/ktor/network/selector/SelectInterest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ClosedSelectorCancellationException", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SelectorManagerSupport.kt */
public abstract class SelectorManagerSupport implements SelectorManager {
    private int cancelled;
    private int pending;
    private final SelectorProvider provider;

    /* access modifiers changed from: protected */
    public abstract void publishInterest(Selectable selectable);

    public SelectorManagerSupport() {
        SelectorProvider provider2 = SelectorProvider.provider();
        Intrinsics.checkNotNullExpressionValue(provider2, "provider()");
        this.provider = provider2;
    }

    public final SelectorProvider getProvider() {
        return this.provider;
    }

    /* access modifiers changed from: protected */
    public final int getPending() {
        return this.pending;
    }

    /* access modifiers changed from: protected */
    public final void setPending(int i) {
        this.pending = i;
    }

    /* access modifiers changed from: protected */
    public final int getCancelled() {
        return this.cancelled;
    }

    /* access modifiers changed from: protected */
    public final void setCancelled(int i) {
        this.cancelled = i;
    }

    public final Object select(Selectable selectable, SelectInterest selectInterest, Continuation<? super Unit> continuation) {
        int interestedOps = selectable.getInterestedOps();
        int flag = selectInterest.getFlag();
        if (selectable.isClosed()) {
            Void unused = SelectorManagerSupportKt.selectableIsClosed();
            throw new KotlinNothingValueException();
        } else if ((interestedOps & flag) != 0) {
            CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
            cancellableContinuationImpl.initCancellability();
            CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
            cancellableContinuation.invokeOnCancellation(SelectorManagerSupport$select$2$1.INSTANCE);
            selectable.getSuspensions().addSuspension(selectInterest, cancellableContinuation);
            if (!cancellableContinuation.isCancelled()) {
                publishInterest(selectable);
            }
            Object result = cancellableContinuationImpl.getResult();
            if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return result;
            }
            return Unit.INSTANCE;
        } else {
            Void unused2 = SelectorManagerSupportKt.selectableIsInvalid(interestedOps, flag);
            throw new KotlinNothingValueException();
        }
    }

    /* access modifiers changed from: protected */
    public final void handleSelectedKeys(Set<SelectionKey> set, Set<? extends SelectionKey> set2) {
        Intrinsics.checkNotNullParameter(set, "selectedKeys");
        Intrinsics.checkNotNullParameter(set2, "keys");
        int size = set.size();
        this.pending = set2.size() - size;
        this.cancelled = 0;
        if (size > 0) {
            Iterator<SelectionKey> it = set.iterator();
            while (it.hasNext()) {
                handleSelectedKey(it.next());
                it.remove();
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void handleSelectedKey(SelectionKey selectionKey) {
        CancellableContinuation<Unit> removeSuspension;
        Intrinsics.checkNotNullParameter(selectionKey, LeanbackPreferenceDialogFragment.ARG_KEY);
        try {
            int readyOps = selectionKey.readyOps();
            int interestOps = selectionKey.interestOps();
            Selectable subject = getSubject(selectionKey);
            if (subject == null) {
                selectionKey.cancel();
                this.cancelled++;
                return;
            }
            InterestSuspensionsMap suspensions = subject.getSuspensions();
            int[] flags = SelectInterest.Companion.getFlags();
            int length = flags.length;
            for (int i = 0; i < length; i++) {
                if (!((flags[i] & readyOps) == 0 || (removeSuspension = suspensions.removeSuspension(i)) == null)) {
                    Result.Companion companion = Result.Companion;
                    removeSuspension.resumeWith(Result.m1774constructorimpl(Unit.INSTANCE));
                }
            }
            int i2 = (readyOps ^ -1) & interestOps;
            if (i2 != interestOps) {
                selectionKey.interestOps(i2);
            }
            if (i2 != 0) {
                this.pending++;
            }
        } catch (Throwable th) {
            selectionKey.cancel();
            this.cancelled++;
            Selectable subject2 = getSubject(selectionKey);
            if (subject2 != null) {
                cancelAllSuspensions(subject2, th);
                setSubject(selectionKey, (Selectable) null);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void applyInterest(Selector selector, Selectable selectable) {
        Intrinsics.checkNotNullParameter(selector, "selector");
        Intrinsics.checkNotNullParameter(selectable, "selectable");
        try {
            SelectableChannel channel = selectable.getChannel();
            SelectionKey keyFor = channel.keyFor(selector);
            int interestedOps = selectable.getInterestedOps();
            if (keyFor == null) {
                if (interestedOps != 0) {
                    channel.register(selector, interestedOps, selectable);
                }
            } else if (keyFor.interestOps() != interestedOps) {
                keyFor.interestOps(interestedOps);
            }
            if (interestedOps != 0) {
                this.pending++;
            }
        } catch (Throwable th) {
            SelectionKey keyFor2 = selectable.getChannel().keyFor(selector);
            if (keyFor2 != null) {
                keyFor2.cancel();
            }
            cancelAllSuspensions(selectable, th);
        }
    }

    /* access modifiers changed from: protected */
    public final void notifyClosedImpl(Selector selector, SelectionKey selectionKey, Selectable selectable) {
        Intrinsics.checkNotNullParameter(selector, "selector");
        Intrinsics.checkNotNullParameter(selectionKey, LeanbackPreferenceDialogFragment.ARG_KEY);
        Intrinsics.checkNotNullParameter(selectable, "attachment");
        cancelAllSuspensions(selectable, (Throwable) new ClosedChannelException());
        setSubject(selectionKey, (Selectable) null);
        selector.wakeup();
    }

    /* access modifiers changed from: protected */
    public final void cancelAllSuspensions(Selectable selectable, Throwable th) {
        Intrinsics.checkNotNullParameter(selectable, "attachment");
        Intrinsics.checkNotNullParameter(th, "cause");
        InterestSuspensionsMap suspensions = selectable.getSuspensions();
        for (SelectInterest removeSuspension : SelectInterest.Companion.getAllInterests()) {
            CancellableContinuation<Unit> removeSuspension2 = suspensions.removeSuspension(removeSuspension);
            if (removeSuspension2 != null) {
                Result.Companion companion = Result.Companion;
                removeSuspension2.resumeWith(Result.m1774constructorimpl(ResultKt.createFailure(th)));
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void cancelAllSuspensions(Selector selector, Throwable th) {
        Intrinsics.checkNotNullParameter(selector, "selector");
        if (th == null) {
            th = new ClosedSelectorCancellationException();
        }
        Set<SelectionKey> keys = selector.keys();
        Intrinsics.checkNotNullExpressionValue(keys, "selector.keys()");
        for (SelectionKey selectionKey : keys) {
            try {
                if (selectionKey.isValid()) {
                    selectionKey.interestOps(0);
                }
            } catch (CancelledKeyException unused) {
            }
            Object attachment = selectionKey.attachment();
            Selectable selectable = attachment instanceof Selectable ? (Selectable) attachment : null;
            if (selectable != null) {
                cancelAllSuspensions(selectable, th);
            }
            selectionKey.cancel();
        }
    }

    private final Selectable getSubject(SelectionKey selectionKey) {
        Object attachment = selectionKey.attachment();
        if (attachment instanceof Selectable) {
            return (Selectable) attachment;
        }
        return null;
    }

    private final void setSubject(SelectionKey selectionKey, Selectable selectable) {
        selectionKey.attach(selectable);
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0005¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lio/ktor/network/selector/SelectorManagerSupport$ClosedSelectorCancellationException;", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "()V", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: SelectorManagerSupport.kt */
    public static final class ClosedSelectorCancellationException extends CancellationException {
        public ClosedSelectorCancellationException() {
            super("Closed selector");
        }
    }
}
