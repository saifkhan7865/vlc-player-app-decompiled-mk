package org.bouncycastle.pqc.crypto.crystals.dilithium;

class PolyVecMatrix {
    private final int dilithiumK;
    private final int dilithiumL;
    private final PolyVecL[] mat;

    public PolyVecMatrix(DilithiumEngine dilithiumEngine) {
        int dilithiumK2 = dilithiumEngine.getDilithiumK();
        this.dilithiumK = dilithiumK2;
        this.dilithiumL = dilithiumEngine.getDilithiumL();
        this.mat = new PolyVecL[dilithiumK2];
        for (int i = 0; i < this.dilithiumK; i++) {
            this.mat[i] = new PolyVecL(dilithiumEngine);
        }
    }

    private String addString() {
        StringBuilder sb;
        String str = "[";
        for (int i = 0; i < this.dilithiumK; i++) {
            String str2 = (str + "Outer Matrix " + i + " [") + this.mat[i].toString();
            if (i == this.dilithiumK - 1) {
                sb = new StringBuilder();
                sb.append(str2);
                sb.append("]\n");
            } else {
                sb = new StringBuilder();
                sb.append(str2);
                sb.append("],\n");
            }
            str = sb.toString();
        }
        return str + "]\n";
    }

    public void expandMatrix(byte[] bArr) {
        for (int i = 0; i < this.dilithiumK; i++) {
            for (int i2 = 0; i2 < this.dilithiumL; i2++) {
                this.mat[i].getVectorIndex(i2).uniformBlocks(bArr, (short) ((i << 8) + i2));
            }
        }
    }

    public void pointwiseMontgomery(PolyVecK polyVecK, PolyVecL polyVecL) {
        for (int i = 0; i < this.dilithiumK; i++) {
            polyVecK.getVectorIndex(i).pointwiseAccountMontgomery(this.mat[i], polyVecL);
        }
    }

    public String toString(String str) {
        return str.concat(": \n" + addString());
    }
}
