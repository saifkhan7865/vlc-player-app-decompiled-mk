package org.bouncycastle.pqc.crypto.sphincsplus;

class SIG_FORS {
    final byte[][] authPath;
    final byte[] sk;

    SIG_FORS(byte[] bArr, byte[][] bArr2) {
        this.authPath = bArr2;
        this.sk = bArr;
    }

    public byte[][] getAuthPath() {
        return this.authPath;
    }

    /* access modifiers changed from: package-private */
    public byte[] getSK() {
        return this.sk;
    }
}
