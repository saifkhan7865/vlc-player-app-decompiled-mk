package io.netty.util.internal.shaded.org.jctools.queues.atomic;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* compiled from: BaseLinkedAtomicQueue */
abstract class BaseLinkedAtomicQueueConsumerNodeRef<E> extends BaseLinkedAtomicQueuePad1<E> {
    private static final AtomicReferenceFieldUpdater<BaseLinkedAtomicQueueConsumerNodeRef, LinkedQueueAtomicNode> C_NODE_UPDATER = AtomicReferenceFieldUpdater.newUpdater(BaseLinkedAtomicQueueConsumerNodeRef.class, LinkedQueueAtomicNode.class, "consumerNode");
    private volatile LinkedQueueAtomicNode<E> consumerNode;

    BaseLinkedAtomicQueueConsumerNodeRef() {
    }

    /* access modifiers changed from: package-private */
    public final void spConsumerNode(LinkedQueueAtomicNode<E> linkedQueueAtomicNode) {
        C_NODE_UPDATER.lazySet(this, linkedQueueAtomicNode);
    }

    /* access modifiers changed from: package-private */
    public final LinkedQueueAtomicNode<E> lvConsumerNode() {
        return this.consumerNode;
    }

    /* access modifiers changed from: package-private */
    public final LinkedQueueAtomicNode<E> lpConsumerNode() {
        return this.consumerNode;
    }
}
