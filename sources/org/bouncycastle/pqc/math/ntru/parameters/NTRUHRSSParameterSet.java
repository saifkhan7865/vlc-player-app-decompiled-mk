package org.bouncycastle.pqc.math.ntru.parameters;

import org.bouncycastle.pqc.math.ntru.HRSSPolynomial;
import org.bouncycastle.pqc.math.ntru.Polynomial;

public abstract class NTRUHRSSParameterSet extends NTRUParameterSet {
    NTRUHRSSParameterSet(int i, int i2, int i3, int i4, int i5) {
        super(i, i2, i3, i4, i5);
    }

    public Polynomial createPolynomial() {
        return new HRSSPolynomial(this);
    }

    public int sampleFgBytes() {
        return sampleIidBytes() * 2;
    }

    public int sampleRmBytes() {
        return sampleIidBytes() * 2;
    }
}
