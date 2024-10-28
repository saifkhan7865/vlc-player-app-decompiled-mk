package io.netty.util.internal.shaded.org.jctools.queues;

import io.ktor.http.LinkHeader;
import io.netty.util.internal.shaded.org.jctools.util.UnsafeAccess;

final class LinkedQueueNode<E> {
    private static final long NEXT_OFFSET = UnsafeAccess.fieldOffset(LinkedQueueNode.class, LinkHeader.Rel.Next);
    private volatile LinkedQueueNode<E> next;
    private E value;

    LinkedQueueNode() {
        this((Object) null);
    }

    LinkedQueueNode(E e) {
        spValue(e);
    }

    public E getAndNullValue() {
        E lpValue = lpValue();
        spValue((Object) null);
        return lpValue;
    }

    public E lpValue() {
        return this.value;
    }

    public void spValue(E e) {
        this.value = e;
    }

    public void soNext(LinkedQueueNode<E> linkedQueueNode) {
        UnsafeAccess.UNSAFE.putOrderedObject(this, NEXT_OFFSET, linkedQueueNode);
    }

    public void spNext(LinkedQueueNode<E> linkedQueueNode) {
        UnsafeAccess.UNSAFE.putObject(this, NEXT_OFFSET, linkedQueueNode);
    }

    public LinkedQueueNode<E> lvNext() {
        return this.next;
    }
}
