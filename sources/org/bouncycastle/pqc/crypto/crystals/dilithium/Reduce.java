package org.bouncycastle.pqc.crypto.crystals.dilithium;

class Reduce {
    Reduce() {
    }

    static int conditionalAddQ(int i) {
        return i + ((i >> 31) & DilithiumEngine.DilithiumQ);
    }

    static int montgomeryReduce(long j) {
        return (int) ((j - (((long) ((int) (58728449 * j))) * 8380417)) >>> 32);
    }

    static int reduce32(int i) {
        return i - (((4194304 + i) >> 23) * DilithiumEngine.DilithiumQ);
    }
}
