package org.bouncycastle.asn1.x9;

import org.bouncycastle.math.ec.ECCurve;

public abstract class X9ECParametersHolder {
    private ECCurve curve;
    private X9ECParameters params;

    /* access modifiers changed from: protected */
    public ECCurve createCurve() {
        return createParameters().getCurve();
    }

    /* access modifiers changed from: protected */
    public abstract X9ECParameters createParameters();

    public synchronized ECCurve getCurve() {
        if (this.curve == null) {
            this.curve = createCurve();
        }
        return this.curve;
    }

    public synchronized X9ECParameters getParameters() {
        if (this.params == null) {
            this.params = createParameters();
        }
        return this.params;
    }
}
