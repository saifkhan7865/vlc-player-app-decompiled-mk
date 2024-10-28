package io.netty.handler.codec.http2;

final class HpackDynamicTable {
    private long capacity = -1;
    int head;
    HpackHeaderField[] hpackHeaderFields;
    private long size;
    int tail;

    HpackDynamicTable(long j) {
        setCapacity(j);
    }

    public int length() {
        int i = this.head;
        int i2 = this.tail;
        return i < i2 ? (this.hpackHeaderFields.length - i2) + i : i - i2;
    }

    public long size() {
        return this.size;
    }

    public long capacity() {
        return this.capacity;
    }

    public HpackHeaderField getEntry(int i) {
        if (i <= 0 || i > length()) {
            throw new IndexOutOfBoundsException("Index " + i + " out of bounds for length " + length());
        }
        int i2 = this.head - i;
        if (i2 >= 0) {
            return this.hpackHeaderFields[i2];
        }
        HpackHeaderField[] hpackHeaderFieldArr = this.hpackHeaderFields;
        return hpackHeaderFieldArr[i2 + hpackHeaderFieldArr.length];
    }

    public void add(HpackHeaderField hpackHeaderField) {
        long j;
        long size2 = (long) hpackHeaderField.size();
        if (size2 > this.capacity) {
            clear();
            return;
        }
        while (true) {
            long j2 = this.capacity;
            j = this.size;
            if (j2 - j >= size2) {
                break;
            }
            remove();
        }
        HpackHeaderField[] hpackHeaderFieldArr = this.hpackHeaderFields;
        int i = this.head;
        int i2 = i + 1;
        this.head = i2;
        hpackHeaderFieldArr[i] = hpackHeaderField;
        this.size = j + size2;
        if (i2 == hpackHeaderFieldArr.length) {
            this.head = 0;
        }
    }

    public HpackHeaderField remove() {
        HpackHeaderField hpackHeaderField = this.hpackHeaderFields[this.tail];
        if (hpackHeaderField == null) {
            return null;
        }
        this.size -= (long) hpackHeaderField.size();
        HpackHeaderField[] hpackHeaderFieldArr = this.hpackHeaderFields;
        int i = this.tail;
        int i2 = i + 1;
        this.tail = i2;
        hpackHeaderFieldArr[i] = null;
        if (i2 == hpackHeaderFieldArr.length) {
            this.tail = 0;
        }
        return hpackHeaderField;
    }

    public void clear() {
        while (true) {
            int i = this.tail;
            if (i != this.head) {
                HpackHeaderField[] hpackHeaderFieldArr = this.hpackHeaderFields;
                int i2 = i + 1;
                this.tail = i2;
                hpackHeaderFieldArr[i] = null;
                if (i2 == hpackHeaderFieldArr.length) {
                    this.tail = 0;
                }
            } else {
                this.head = 0;
                this.tail = 0;
                this.size = 0;
                return;
            }
        }
    }

    public void setCapacity(long j) {
        if (j < 0 || j > 4294967295L) {
            throw new IllegalArgumentException("capacity is invalid: " + j);
        } else if (this.capacity != j) {
            this.capacity = j;
            if (j == 0) {
                clear();
            } else {
                while (this.size > j) {
                    remove();
                }
            }
            int i = (int) (j / 32);
            if (j % 32 != 0) {
                i++;
            }
            HpackHeaderField[] hpackHeaderFieldArr = this.hpackHeaderFields;
            if (hpackHeaderFieldArr == null || hpackHeaderFieldArr.length != i) {
                HpackHeaderField[] hpackHeaderFieldArr2 = new HpackHeaderField[i];
                int length = length();
                if (this.hpackHeaderFields != null) {
                    int i2 = this.tail;
                    for (int i3 = 0; i3 < length; i3++) {
                        HpackHeaderField[] hpackHeaderFieldArr3 = this.hpackHeaderFields;
                        int i4 = i2 + 1;
                        hpackHeaderFieldArr2[i3] = hpackHeaderFieldArr3[i2];
                        i2 = i4 == hpackHeaderFieldArr3.length ? 0 : i4;
                    }
                }
                this.tail = 0;
                this.head = length;
                this.hpackHeaderFields = hpackHeaderFieldArr2;
            }
        }
    }
}
