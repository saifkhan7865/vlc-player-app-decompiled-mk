package io.netty.channel.nio;

import java.nio.channels.SelectionKey;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

final class SelectedSelectionKeySet extends AbstractSet<SelectionKey> {
    SelectionKey[] keys = new SelectionKey[1024];
    int size;

    public boolean remove(Object obj) {
        return false;
    }

    SelectedSelectionKeySet() {
    }

    public boolean add(SelectionKey selectionKey) {
        if (selectionKey == null) {
            return false;
        }
        if (this.size == this.keys.length) {
            increaseCapacity();
        }
        SelectionKey[] selectionKeyArr = this.keys;
        int i = this.size;
        this.size = i + 1;
        selectionKeyArr[i] = selectionKey;
        return true;
    }

    public boolean contains(Object obj) {
        SelectionKey[] selectionKeyArr = this.keys;
        int i = this.size;
        for (int i2 = 0; i2 < i; i2++) {
            if (selectionKeyArr[i2].equals(obj)) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return this.size;
    }

    public Iterator<SelectionKey> iterator() {
        return new Iterator<SelectionKey>() {
            private int idx;

            public boolean hasNext() {
                return this.idx < SelectedSelectionKeySet.this.size;
            }

            public SelectionKey next() {
                if (hasNext()) {
                    SelectionKey[] selectionKeyArr = SelectedSelectionKeySet.this.keys;
                    int i = this.idx;
                    this.idx = i + 1;
                    return selectionKeyArr[i];
                }
                throw new NoSuchElementException();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    /* access modifiers changed from: package-private */
    public void reset() {
        reset(0);
    }

    /* access modifiers changed from: package-private */
    public void reset(int i) {
        Arrays.fill(this.keys, i, this.size, (Object) null);
        this.size = 0;
    }

    private void increaseCapacity() {
        SelectionKey[] selectionKeyArr = this.keys;
        SelectionKey[] selectionKeyArr2 = new SelectionKey[(selectionKeyArr.length << 1)];
        System.arraycopy(selectionKeyArr, 0, selectionKeyArr2, 0, this.size);
        this.keys = selectionKeyArr2;
    }
}
