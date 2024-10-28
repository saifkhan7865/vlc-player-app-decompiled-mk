package org.bouncycastle.pqc.math.ntru.parameters;

import org.bouncycastle.pqc.math.ntru.HPSPolynomial;
import org.bouncycastle.pqc.math.ntru.Polynomial;

public abstract class NTRUHPSParameterSet extends NTRUParameterSet {
    NTRUHPSParameterSet(int i, int i2, int i3, int i4, int i5) {
        super(i, i2, i3, i4, i5);
    }

    public Polynomial createPolynomial() {
        return new HPSPolynomial(this);
    }

    public int sampleFgBytes() {
        return sampleIidBytes() + sampleFixedTypeBytes();
    }

    public int sampleRmBytes() {
        return sampleIidBytes() + sampleFixedTypeBytes();
    }

    public int weight() {
        return (q() / 8) - 2;
    }
}
