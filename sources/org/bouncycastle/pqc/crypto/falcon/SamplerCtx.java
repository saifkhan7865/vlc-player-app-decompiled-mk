package org.bouncycastle.pqc.crypto.falcon;

class SamplerCtx {
    FalconRNG p = new FalconRNG();
    FalconFPR sigma_min = new FalconFPR(0.0d);

    SamplerCtx() {
    }
}
