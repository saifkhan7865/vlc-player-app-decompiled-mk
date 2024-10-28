package org.bouncycastle.pqc.crypto.falcon;

class ComplexNumberWrapper {
    FalconFPR im;
    FalconFPR re;

    ComplexNumberWrapper(FalconFPR falconFPR, FalconFPR falconFPR2) {
        this.re = falconFPR;
        this.im = falconFPR2;
    }
}
