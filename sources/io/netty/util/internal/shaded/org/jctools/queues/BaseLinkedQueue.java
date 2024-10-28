package io.netty.util.internal.shaded.org.jctools.queues;

import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue;
import java.util.Iterator;

abstract class BaseLinkedQueue<E> extends BaseLinkedQueuePad2<E> {
    public int capacity() {
        return -1;
    }

    BaseLinkedQueue() {
    }

    public final Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        return getClass().getName();
    }

    /* access modifiers changed from: protected */
    public final LinkedQueueNode<E> newNode() {
        return new LinkedQueueNode<>();
    }

    /* access modifiers changed from: protected */
    public final LinkedQueueNode<E> newNode(E e) {
        return new LinkedQueueNode<>(e);
    }

    public final int size() {
        LinkedQueueNode lvConsumerNode = lvConsumerNode();
        LinkedQueueNode lvProducerNode = lvProducerNode();
        int i = 0;
        while (lvConsumerNode != lvProducerNode && lvConsumerNode != null && i < Integer.MAX_VALUE) {
            LinkedQueueNode lvNext = lvConsumerNode.lvNext();
            if (lvNext == lvConsumerNode) {
                return i;
            }
            i++;
            lvConsumerNode = lvNext;
        }
        return i;
    }

    public boolean isEmpty() {
        return lvConsumerNode() == lvProducerNode();
    }

    /* access modifiers changed from: protected */
    public E getSingleConsumerNodeValue(LinkedQueueNode<E> linkedQueueNode, LinkedQueueNode<E> linkedQueueNode2) {
        E andNullValue = linkedQueueNode2.getAndNullValue();
        linkedQueueNode.soNext(linkedQueueNode);
        spConsumerNode(linkedQueueNode2);
        return andNullValue;
    }

    public E poll() {
        LinkedQueueNode lpConsumerNode = lpConsumerNode();
        LinkedQueueNode lvNext = lpConsumerNode.lvNext();
        if (lvNext != null) {
            return getSingleConsumerNodeValue(lpConsumerNode, lvNext);
        }
        if (lpConsumerNode != lvProducerNode()) {
            return getSingleConsumerNodeValue(lpConsumerNode, spinWaitForNextNode(lpConsumerNode));
        }
        return null;
    }

    public E peek() {
        LinkedQueueNode lpConsumerNode = lpConsumerNode();
        LinkedQueueNode lvNext = lpConsumerNode.lvNext();
        if (lvNext != null) {
            return lvNext.lpValue();
        }
        if (lpConsumerNode != lvProducerNode()) {
            return spinWaitForNextNode(lpConsumerNode).lpValue();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public LinkedQueueNode<E> spinWaitForNextNode(LinkedQueueNode<E> linkedQueueNode) {
        LinkedQueueNode<E> lvNext;
        do {
            lvNext = linkedQueueNode.lvNext();
        } while (lvNext == null);
        return lvNext;
    }

    public E relaxedPoll() {
        LinkedQueueNode lpConsumerNode = lpConsumerNode();
        LinkedQueueNode lvNext = lpConsumerNode.lvNext();
        if (lvNext != null) {
            return getSingleConsumerNodeValue(lpConsumerNode, lvNext);
        }
        return null;
    }

    public E relaxedPeek() {
        LinkedQueueNode lvNext = lpConsumerNode().lvNext();
        if (lvNext != null) {
            return lvNext.lpValue();
        }
        return null;
    }

    public boolean relaxedOffer(E e) {
        return offer(e);
    }

    public int drain(MessagePassingQueue.Consumer<E> consumer, int i) {
        if (consumer == null) {
            throw new IllegalArgumentException("c is null");
        } else if (i >= 0) {
            int i2 = 0;
            if (i == 0) {
                return 0;
            }
            LinkedQueueNode lpConsumerNode = lpConsumerNode();
            while (i2 < i) {
                LinkedQueueNode lvNext = lpConsumerNode.lvNext();
                if (lvNext == null) {
                    return i2;
                }
                consumer.accept(getSingleConsumerNodeValue(lpConsumerNode, lvNext));
                i2++;
                lpConsumerNode = lvNext;
            }
            return i;
        } else {
            throw new IllegalArgumentException("limit is negative: " + i);
        }
    }

    public int drain(MessagePassingQueue.Consumer<E> consumer) {
        return MessagePassingQueueUtil.drain(this, consumer);
    }

    public void drain(MessagePassingQueue.Consumer<E> consumer, MessagePassingQueue.WaitStrategy waitStrategy, MessagePassingQueue.ExitCondition exitCondition) {
        MessagePassingQueueUtil.drain(this, consumer, waitStrategy, exitCondition);
    }
}
