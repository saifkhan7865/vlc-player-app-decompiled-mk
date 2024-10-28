package org.bouncycastle.crypto.modes.gcm;

public class BasicGCMMultiplier implements GCMMultiplier {
    private long[] H;

    public void init(byte[] bArr) {
        this.H = GCMUtil.asLongs(bArr);
    }

    public void multiplyH(byte[] bArr) {
        GCMUtil.multiply(bArr, this.H);
    }
}
