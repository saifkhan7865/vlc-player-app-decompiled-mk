package com.typesafe.config.impl;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.view.PointerIconCompat;
import com.google.zxing.pdf417.PDF417Common;
import org.bouncycastle.math.Primes;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

final class BadMap<K, V> {
    private static final Entry[] emptyEntries = new Entry[0];
    private static final int[] primes = {2, 5, 11, 17, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, MediaWrapper.META_GAIN, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, Primes.SMALL_FACTOR_LIMIT, 223, 227, 229, 233, 239, 241, MediaWrapper.META_METADATA_RETRIEVED, 257, 263, MediaPlayer.Event.SeekableChanged, 271, MediaPlayer.Event.ESDeleted, 281, 283, 293, 307, 311, 313, TypedValues.AttributesType.TYPE_EASING, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, TypedValues.CycleType.TYPE_CURVE_FIT, 409, 419, TypedValues.CycleType.TYPE_WAVE_SHAPE, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, TypedValues.PositionType.TYPE_PERCENT_WIDTH, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, TypedValues.MotionType.TYPE_PATHMOTION_ARC, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881, 883, 887, 907, 911, 919, PDF417Common.NUMBER_OF_CODEWORDS, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997, PointerIconCompat.TYPE_VERTICAL_TEXT, 2053, 3079, 4057, 7103, 10949, 16069, 32609, 65867, 104729};
    private final Entry[] entries;
    private final int size;

    static final class Entry {
        final int hash;
        final Object key;
        final Entry next;
        final Object value;

        Entry(int i, Object obj, Object obj2, Entry entry) {
            this.hash = i;
            this.key = obj;
            this.value = obj2;
            this.next = entry;
        }

        /* access modifiers changed from: package-private */
        public Object find(Object obj) {
            if (this.key.equals(obj)) {
                return this.value;
            }
            Entry entry = this.next;
            if (entry != null) {
                return entry.find(obj);
            }
            return null;
        }
    }

    BadMap() {
        this(0, emptyEntries);
    }

    private BadMap(int i, Entry[] entryArr) {
        this.size = i;
        this.entries = entryArr;
    }

    /* access modifiers changed from: package-private */
    public BadMap<K, V> copyingPut(K k, V v) {
        Entry[] entryArr;
        int i = this.size + 1;
        Entry[] entryArr2 = this.entries;
        if (i > entryArr2.length) {
            entryArr = new Entry[nextPrime((i * 2) - 1)];
        } else {
            entryArr = new Entry[entryArr2.length];
        }
        int length = entryArr.length;
        Entry[] entryArr3 = this.entries;
        if (length == entryArr3.length) {
            System.arraycopy(entryArr3, 0, entryArr, 0, entryArr3.length);
        } else {
            rehash(entryArr3, entryArr);
        }
        store(entryArr, Math.abs(k.hashCode()), k, v);
        return new BadMap<>(i, entryArr);
    }

    private static <K, V> void store(Entry[] entryArr, int i, K k, V v) {
        int length = i % entryArr.length;
        entryArr[length] = new Entry(i, k, v, entryArr[length]);
    }

    private static void store(Entry[] entryArr, Entry entry) {
        int length = entry.hash % entryArr.length;
        Entry entry2 = entryArr[length];
        if (entry2 == null && entry.next == null) {
            entryArr[length] = entry;
        } else {
            entryArr[length] = new Entry(entry.hash, entry.key, entry.value, entry2);
        }
    }

    private static void rehash(Entry[] entryArr, Entry[] entryArr2) {
        for (Entry entry : entryArr) {
            while (entry != null) {
                store(entryArr2, entry);
                entry = entry.next;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public V get(K k) {
        if (this.entries.length == 0) {
            return null;
        }
        int abs = Math.abs(k.hashCode());
        Entry[] entryArr = this.entries;
        Entry entry = entryArr[abs % entryArr.length];
        if (entry == null) {
            return null;
        }
        return entry.find(k);
    }

    private static int nextPrime(int i) {
        for (int i2 : primes) {
            if (i2 > i) {
                return i2;
            }
        }
        int[] iArr = primes;
        return iArr[iArr.length - 1];
    }
}
