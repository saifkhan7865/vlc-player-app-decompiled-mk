package io.netty.util.internal.shaded.org.jctools.queues;

import com.google.common.util.concurrent.Striped$SmallLazyStriped$$ExternalSyntheticBackportWithForwarding0;
import io.netty.util.internal.shaded.org.jctools.util.UnsafeAccess;

/* compiled from: BaseLinkedQueue */
abstract class BaseLinkedQueueProducerNodeRef<E> extends BaseLinkedQueuePad0<E> {
    static final long P_NODE_OFFSET = UnsafeAccess.fieldOffset(BaseLinkedQueueProducerNodeRef.class, "producerNode");
    private volatile LinkedQueueNode<E> producerNode;

    BaseLinkedQueueProducerNodeRef() {
    }

    /* access modifiers changed from: package-private */
    public final void spProducerNode(LinkedQueueNode<E> linkedQueueNode) {
        UnsafeAccess.UNSAFE.putObject(this, P_NODE_OFFSET, linkedQueueNode);
    }

    /* access modifiers changed from: package-private */
    public final void soProducerNode(LinkedQueueNode<E> linkedQueueNode) {
        UnsafeAccess.UNSAFE.putOrderedObject(this, P_NODE_OFFSET, linkedQueueNode);
    }

    /* access modifiers changed from: package-private */
    public final LinkedQueueNode<E> lvProducerNode() {
        return this.producerNode;
    }

    /* access modifiers changed from: package-private */
    public final boolean casProducerNode(LinkedQueueNode<E> linkedQueueNode, LinkedQueueNode<E> linkedQueueNode2) {
        return Striped$SmallLazyStriped$$ExternalSyntheticBackportWithForwarding0.m(UnsafeAccess.UNSAFE, this, P_NODE_OFFSET, linkedQueueNode, linkedQueueNode2);
    }

    /* access modifiers changed from: package-private */
    public final LinkedQueueNode<E> lpProducerNode() {
        return this.producerNode;
    }
}
