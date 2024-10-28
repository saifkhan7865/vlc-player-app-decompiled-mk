package io.netty.buffer;

import java.util.Arrays;

final class IntPriorityQueue {
    public static final int NO_VALUE = -1;
    private int[] array = new int[9];
    private int size;

    IntPriorityQueue() {
    }

    public void offer(int i) {
        if (i != -1) {
            int i2 = this.size + 1;
            this.size = i2;
            int[] iArr = this.array;
            if (i2 == iArr.length) {
                this.array = Arrays.copyOf(iArr, ((iArr.length - 1) * 2) + 1);
            }
            int[] iArr2 = this.array;
            int i3 = this.size;
            iArr2[i3] = i;
            lift(i3);
            return;
        }
        throw new IllegalArgumentException("The NO_VALUE (-1) cannot be added to the queue.");
    }

    public void remove(int i) {
        int i2 = 1;
        while (true) {
            int i3 = this.size;
            if (i2 <= i3) {
                int[] iArr = this.array;
                if (iArr[i2] == i) {
                    this.size = i3 - 1;
                    iArr[i2] = iArr[i3];
                    lift(i2);
                    sink(i2);
                    return;
                }
                i2++;
            } else {
                return;
            }
        }
    }

    public int peek() {
        if (this.size == 0) {
            return -1;
        }
        return this.array[1];
    }

    public int poll() {
        int i = this.size;
        if (i == 0) {
            return -1;
        }
        int[] iArr = this.array;
        int i2 = iArr[1];
        iArr[1] = iArr[i];
        iArr[i] = 0;
        this.size = i - 1;
        sink(1);
        return i2;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    private void lift(int i) {
        while (i > 1) {
            int i2 = i >> 1;
            if (subord(i2, i)) {
                swap(i, i2);
                i = i2;
            } else {
                return;
            }
        }
    }

    private void sink(int i) {
        while (true) {
            int i2 = i << 1;
            int i3 = this.size;
            if (i2 <= i3) {
                if (i2 < i3) {
                    int i4 = i2 + 1;
                    if (subord(i2, i4)) {
                        i2 = i4;
                    }
                }
                if (subord(i, i2)) {
                    swap(i, i2);
                    i = i2;
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    private boolean subord(int i, int i2) {
        int[] iArr = this.array;
        return iArr[i] > iArr[i2];
    }

    private void swap(int i, int i2) {
        int[] iArr = this.array;
        int i3 = iArr[i];
        iArr[i] = iArr[i2];
        iArr[i2] = i3;
    }
}
