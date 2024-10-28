package org.bouncycastle.pqc.crypto.gemss;

class SecretKeyHFE {
    public Pointer F_HFEv;
    complete_sparse_monic_gf2nx F_struct;
    public Pointer S;
    public Pointer T;
    public Pointer sk_uncomp;

    static class complete_sparse_monic_gf2nx {
        public int[] L;
        public Pointer poly;
    }

    public SecretKeyHFE(GeMSSEngine geMSSEngine) {
        complete_sparse_monic_gf2nx complete_sparse_monic_gf2nx2 = new complete_sparse_monic_gf2nx();
        this.F_struct = complete_sparse_monic_gf2nx2;
        complete_sparse_monic_gf2nx2.L = new int[geMSSEngine.NB_COEFS_HFEPOLY];
    }
}
