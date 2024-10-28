package io.netty.util.internal.shaded.org.jctools.queues;

import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue;
import io.netty.util.internal.shaded.org.jctools.util.Pow2;
import java.util.Iterator;

public class MpscChunkedArrayQueue<E> extends MpscChunkedArrayQueueColdProducerFields<E> {
    byte b000;
    byte b001;
    byte b002;
    byte b003;
    byte b004;
    byte b005;
    byte b006;
    byte b007;
    byte b010;
    byte b011;
    byte b012;
    byte b013;
    byte b014;
    byte b015;
    byte b016;
    byte b017;
    byte b020;
    byte b021;
    byte b022;
    byte b023;
    byte b024;
    byte b025;
    byte b026;
    byte b027;
    byte b030;
    byte b031;
    byte b032;
    byte b033;
    byte b034;
    byte b035;
    byte b036;
    byte b037;
    byte b040;
    byte b041;
    byte b042;
    byte b043;
    byte b044;
    byte b045;
    byte b046;
    byte b047;
    byte b050;
    byte b051;
    byte b052;
    byte b053;
    byte b054;
    byte b055;
    byte b056;
    byte b057;
    byte b060;
    byte b061;
    byte b062;
    byte b063;
    byte b064;
    byte b065;
    byte b066;
    byte b067;
    byte b070;
    byte b071;
    byte b072;
    byte b073;
    byte b074;
    byte b075;
    byte b076;
    byte b077;
    byte b100;
    byte b101;
    byte b102;
    byte b103;
    byte b104;
    byte b105;
    byte b106;
    byte b107;
    byte b110;
    byte b111;
    byte b112;
    byte b113;
    byte b114;
    byte b115;
    byte b116;
    byte b117;
    byte b120;
    byte b121;
    byte b122;
    byte b123;
    byte b124;
    byte b125;
    byte b126;
    byte b127;
    byte b130;
    byte b131;
    byte b132;
    byte b133;
    byte b134;
    byte b135;
    byte b136;
    byte b137;
    byte b140;
    byte b141;
    byte b142;
    byte b143;
    byte b144;
    byte b145;
    byte b146;
    byte b147;
    byte b150;
    byte b151;
    byte b152;
    byte b153;
    byte b154;
    byte b155;
    byte b156;
    byte b157;
    byte b160;
    byte b161;
    byte b162;
    byte b163;
    byte b164;
    byte b165;
    byte b166;
    byte b167;
    byte b170;
    byte b171;
    byte b172;
    byte b173;
    byte b174;
    byte b175;
    byte b176;
    byte b177;

    /* access modifiers changed from: protected */
    public long getCurrentBufferCapacity(long j) {
        return j;
    }

    public /* bridge */ /* synthetic */ long currentConsumerIndex() {
        return super.currentConsumerIndex();
    }

    public /* bridge */ /* synthetic */ long currentProducerIndex() {
        return super.currentProducerIndex();
    }

    public /* bridge */ /* synthetic */ int drain(MessagePassingQueue.Consumer consumer) {
        return super.drain(consumer);
    }

    public /* bridge */ /* synthetic */ int drain(MessagePassingQueue.Consumer consumer, int i) {
        return super.drain(consumer, i);
    }

    public /* bridge */ /* synthetic */ void drain(MessagePassingQueue.Consumer consumer, MessagePassingQueue.WaitStrategy waitStrategy, MessagePassingQueue.ExitCondition exitCondition) {
        super.drain(consumer, waitStrategy, exitCondition);
    }

    public /* bridge */ /* synthetic */ int fill(MessagePassingQueue.Supplier supplier) {
        return super.fill(supplier);
    }

    public /* bridge */ /* synthetic */ int fill(MessagePassingQueue.Supplier supplier, int i) {
        return super.fill(supplier, i);
    }

    public /* bridge */ /* synthetic */ void fill(MessagePassingQueue.Supplier supplier, MessagePassingQueue.WaitStrategy waitStrategy, MessagePassingQueue.ExitCondition exitCondition) {
        super.fill(supplier, waitStrategy, exitCondition);
    }

    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    public /* bridge */ /* synthetic */ Iterator iterator() {
        return super.iterator();
    }

    public /* bridge */ /* synthetic */ boolean offer(Object obj) {
        return super.offer(obj);
    }

    public /* bridge */ /* synthetic */ Object peek() {
        return super.peek();
    }

    public /* bridge */ /* synthetic */ Object poll() {
        return super.poll();
    }

    public /* bridge */ /* synthetic */ boolean relaxedOffer(Object obj) {
        return super.relaxedOffer(obj);
    }

    public /* bridge */ /* synthetic */ Object relaxedPeek() {
        return super.relaxedPeek();
    }

    public /* bridge */ /* synthetic */ Object relaxedPoll() {
        return super.relaxedPoll();
    }

    public /* bridge */ /* synthetic */ int size() {
        return super.size();
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public MpscChunkedArrayQueue(int i) {
        super(Math.max(2, Math.min(1024, Pow2.roundToPowerOfTwo(i / 8))), i);
    }

    public MpscChunkedArrayQueue(int i, int i2) {
        super(i, i2);
    }

    /* access modifiers changed from: protected */
    public long availableInQueue(long j, long j2) {
        return this.maxQueueCapacity - (j - j2);
    }

    public int capacity() {
        return (int) (this.maxQueueCapacity / 2);
    }

    /* access modifiers changed from: protected */
    public int getNextBufferSize(E[] eArr) {
        return LinkedArrayQueueUtil.length(eArr);
    }
}
