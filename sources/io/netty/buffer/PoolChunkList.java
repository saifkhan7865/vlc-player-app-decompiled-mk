package io.netty.buffer;

import io.netty.util.internal.StringUtil;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

final class PoolChunkList<T> implements PoolChunkListMetric {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Iterator<PoolChunkMetric> EMPTY_METRICS = Collections.emptyList().iterator();
    private final PoolArena<T> arena;
    private final int freeMaxThreshold;
    private final int freeMinThreshold;
    private PoolChunk<T> head;
    private final int maxCapacity;
    private final int maxUsage;
    private final int minUsage;
    private final PoolChunkList<T> nextList;
    private PoolChunkList<T> prevList;

    PoolChunkList(PoolArena<T> poolArena, PoolChunkList<T> poolChunkList, int i, int i2, int i3) {
        int i4;
        this.arena = poolArena;
        this.nextList = poolChunkList;
        this.minUsage = i;
        this.maxUsage = i2;
        this.maxCapacity = calculateMaxCapacity(i, i3);
        int i5 = 0;
        if (i2 == 100) {
            i4 = 0;
        } else {
            double d = (double) i3;
            double d2 = (double) i2;
            Double.isNaN(d2);
            Double.isNaN(d);
            i4 = (int) ((d * ((100.0d - d2) + 0.99999999d)) / 100.0d);
        }
        this.freeMinThreshold = i4;
        if (i != 100) {
            double d3 = (double) i3;
            double d4 = (double) i;
            Double.isNaN(d4);
            Double.isNaN(d3);
            i5 = (int) ((d3 * ((100.0d - d4) + 0.99999999d)) / 100.0d);
        }
        this.freeMaxThreshold = i5;
    }

    private static int calculateMaxCapacity(int i, int i2) {
        int minUsage0 = minUsage0(i);
        if (minUsage0 == 100) {
            return 0;
        }
        return (int) ((((long) i2) * (100 - ((long) minUsage0))) / 100);
    }

    /* access modifiers changed from: package-private */
    public void prevList(PoolChunkList<T> poolChunkList) {
        this.prevList = poolChunkList;
    }

    /* access modifiers changed from: package-private */
    public boolean allocate(PooledByteBuf<T> pooledByteBuf, int i, int i2, PoolThreadCache poolThreadCache) {
        if (this.arena.sizeIdx2size(i2) > this.maxCapacity) {
            return false;
        }
        PoolChunk<T> poolChunk = this.head;
        while (poolChunk != null) {
            if (!poolChunk.allocate(pooledByteBuf, i, i2, poolThreadCache)) {
                poolChunk = poolChunk.next;
            } else if (poolChunk.freeBytes > this.freeMinThreshold) {
                return true;
            } else {
                remove(poolChunk);
                this.nextList.add(poolChunk);
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean free(PoolChunk<T> poolChunk, long j, int i, ByteBuffer byteBuffer) {
        poolChunk.free(j, i, byteBuffer);
        if (poolChunk.freeBytes <= this.freeMaxThreshold) {
            return true;
        }
        remove(poolChunk);
        return move0(poolChunk);
    }

    private boolean move(PoolChunk<T> poolChunk) {
        if (poolChunk.freeBytes > this.freeMaxThreshold) {
            return move0(poolChunk);
        }
        add0(poolChunk);
        return true;
    }

    private boolean move0(PoolChunk<T> poolChunk) {
        PoolChunkList<T> poolChunkList = this.prevList;
        if (poolChunkList == null) {
            return false;
        }
        return poolChunkList.move(poolChunk);
    }

    /* access modifiers changed from: package-private */
    public void add(PoolChunk<T> poolChunk) {
        if (poolChunk.freeBytes <= this.freeMinThreshold) {
            this.nextList.add(poolChunk);
        } else {
            add0(poolChunk);
        }
    }

    /* access modifiers changed from: package-private */
    public void add0(PoolChunk<T> poolChunk) {
        poolChunk.parent = this;
        if (this.head == null) {
            this.head = poolChunk;
            poolChunk.prev = null;
            poolChunk.next = null;
            return;
        }
        poolChunk.prev = null;
        poolChunk.next = this.head;
        this.head.prev = poolChunk;
        this.head = poolChunk;
    }

    private void remove(PoolChunk<T> poolChunk) {
        if (poolChunk == this.head) {
            PoolChunk<T> poolChunk2 = poolChunk.next;
            this.head = poolChunk2;
            if (poolChunk2 != null) {
                poolChunk2.prev = null;
                return;
            }
            return;
        }
        PoolChunk<T> poolChunk3 = poolChunk.next;
        poolChunk.prev.next = poolChunk3;
        if (poolChunk3 != null) {
            poolChunk3.prev = poolChunk.prev;
        }
    }

    public int minUsage() {
        return minUsage0(this.minUsage);
    }

    public int maxUsage() {
        return Math.min(this.maxUsage, 100);
    }

    private static int minUsage0(int i) {
        return Math.max(1, i);
    }

    public Iterator<PoolChunkMetric> iterator() {
        Iterator<PoolChunkMetric> it;
        this.arena.lock();
        try {
            if (this.head == null) {
                it = EMPTY_METRICS;
            } else {
                ArrayList arrayList = new ArrayList();
                PoolChunk<T> poolChunk = this.head;
                do {
                    arrayList.add(poolChunk);
                    poolChunk = poolChunk.next;
                } while (poolChunk != null);
                it = arrayList.iterator();
            }
            return it;
        } finally {
            this.arena.unlock();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        this.arena.lock();
        try {
            PoolChunk<T> poolChunk = this.head;
            if (poolChunk == null) {
                return "none";
            }
            while (true) {
                sb.append(poolChunk);
                poolChunk = poolChunk.next;
                if (poolChunk == null) {
                    this.arena.unlock();
                    return sb.toString();
                }
                sb.append(StringUtil.NEWLINE);
            }
        } finally {
            this.arena.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public void destroy(PoolArena<T> poolArena) {
        for (PoolChunk<T> poolChunk = this.head; poolChunk != null; poolChunk = poolChunk.next) {
            poolArena.destroyChunk(poolChunk);
        }
        this.head = null;
    }
}
