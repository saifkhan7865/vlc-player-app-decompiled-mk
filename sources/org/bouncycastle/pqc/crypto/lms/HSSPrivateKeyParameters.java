package org.bouncycastle.pqc.crypto.lms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Encodable;

public class HSSPrivateKeyParameters extends LMSKeyParameters implements LMSContextBasedSigner {
    private long index = 0;
    private final long indexLimit;
    private final boolean isShard;
    private List<LMSPrivateKeyParameters> keys;
    private final int l;
    private HSSPublicKeyParameters publicKey;
    private List<LMSSignature> sig;

    public HSSPrivateKeyParameters(int i, List<LMSPrivateKeyParameters> list, List<LMSSignature> list2, long j, long j2) {
        super(true);
        this.l = i;
        this.keys = Collections.unmodifiableList(list);
        this.sig = Collections.unmodifiableList(list2);
        this.index = j;
        this.indexLimit = j2;
        this.isShard = false;
        resetKeyToIndex();
    }

    private HSSPrivateKeyParameters(int i, List<LMSPrivateKeyParameters> list, List<LMSSignature> list2, long j, long j2, boolean z) {
        super(true);
        this.l = i;
        this.keys = Collections.unmodifiableList(list);
        this.sig = Collections.unmodifiableList(list2);
        this.index = j;
        this.indexLimit = j2;
        this.isShard = z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x007a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.bouncycastle.pqc.crypto.lms.HSSPrivateKeyParameters getInstance(java.lang.Object r11) throws java.io.IOException {
        /*
            boolean r0 = r11 instanceof org.bouncycastle.pqc.crypto.lms.HSSPrivateKeyParameters
            if (r0 == 0) goto L_0x0007
            org.bouncycastle.pqc.crypto.lms.HSSPrivateKeyParameters r11 = (org.bouncycastle.pqc.crypto.lms.HSSPrivateKeyParameters) r11
            return r11
        L_0x0007:
            boolean r0 = r11 instanceof java.io.DataInputStream
            if (r0 == 0) goto L_0x0059
            r0 = r11
            java.io.DataInputStream r0 = (java.io.DataInputStream) r0
            int r1 = r0.readInt()
            if (r1 != 0) goto L_0x0051
            int r3 = r0.readInt()
            long r6 = r0.readLong()
            long r8 = r0.readLong()
            boolean r10 = r0.readBoolean()
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            r0 = 0
            r1 = 0
        L_0x0030:
            if (r1 >= r3) goto L_0x003c
            org.bouncycastle.pqc.crypto.lms.LMSPrivateKeyParameters r2 = org.bouncycastle.pqc.crypto.lms.LMSPrivateKeyParameters.getInstance(r11)
            r4.add(r2)
            int r1 = r1 + 1
            goto L_0x0030
        L_0x003c:
            int r1 = r3 + -1
            if (r0 >= r1) goto L_0x004a
            org.bouncycastle.pqc.crypto.lms.LMSSignature r1 = org.bouncycastle.pqc.crypto.lms.LMSSignature.getInstance(r11)
            r5.add(r1)
            int r0 = r0 + 1
            goto L_0x003c
        L_0x004a:
            org.bouncycastle.pqc.crypto.lms.HSSPrivateKeyParameters r11 = new org.bouncycastle.pqc.crypto.lms.HSSPrivateKeyParameters
            r2 = r11
            r2.<init>(r3, r4, r5, r6, r8, r10)
            return r11
        L_0x0051:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "unknown version for hss private key"
            r11.<init>(r0)
            throw r11
        L_0x0059:
            boolean r0 = r11 instanceof byte[]
            if (r0 == 0) goto L_0x007e
            r0 = 0
            java.io.DataInputStream r1 = new java.io.DataInputStream     // Catch:{ all -> 0x0077 }
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream     // Catch:{ all -> 0x0077 }
            byte[] r11 = (byte[]) r11     // Catch:{ all -> 0x0077 }
            byte[] r11 = (byte[]) r11     // Catch:{ all -> 0x0077 }
            r2.<init>(r11)     // Catch:{ all -> 0x0077 }
            r1.<init>(r2)     // Catch:{ all -> 0x0077 }
            org.bouncycastle.pqc.crypto.lms.HSSPrivateKeyParameters r11 = getInstance(r1)     // Catch:{ all -> 0x0074 }
            r1.close()
            return r11
        L_0x0074:
            r11 = move-exception
            r0 = r1
            goto L_0x0078
        L_0x0077:
            r11 = move-exception
        L_0x0078:
            if (r0 == 0) goto L_0x007d
            r0.close()
        L_0x007d:
            throw r11
        L_0x007e:
            boolean r0 = r11 instanceof java.io.InputStream
            if (r0 == 0) goto L_0x008d
            java.io.InputStream r11 = (java.io.InputStream) r11
            byte[] r11 = org.bouncycastle.util.io.Streams.readAll(r11)
            org.bouncycastle.pqc.crypto.lms.HSSPrivateKeyParameters r11 = getInstance(r11)
            return r11
        L_0x008d:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "cannot parse "
            r1.<init>(r2)
            r1.append(r11)
            java.lang.String r11 = r1.toString()
            r0.<init>(r11)
            goto L_0x00a2
        L_0x00a1:
            throw r0
        L_0x00a2:
            goto L_0x00a1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pqc.crypto.lms.HSSPrivateKeyParameters.getInstance(java.lang.Object):org.bouncycastle.pqc.crypto.lms.HSSPrivateKeyParameters");
    }

    public static HSSPrivateKeyParameters getInstance(byte[] bArr, byte[] bArr2) throws IOException {
        HSSPrivateKeyParameters instance = getInstance(bArr);
        instance.publicKey = HSSPublicKeyParameters.getInstance(bArr2);
        return instance;
    }

    private static HSSPrivateKeyParameters makeCopy(HSSPrivateKeyParameters hSSPrivateKeyParameters) {
        try {
            return getInstance(hSSPrivateKeyParameters.getEncoded());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /* access modifiers changed from: protected */
    public Object clone() throws CloneNotSupportedException {
        return makeCopy(this);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        HSSPrivateKeyParameters hSSPrivateKeyParameters = (HSSPrivateKeyParameters) obj;
        if (this.l == hSSPrivateKeyParameters.l && this.isShard == hSSPrivateKeyParameters.isShard && this.indexLimit == hSSPrivateKeyParameters.indexLimit && this.index == hSSPrivateKeyParameters.index && this.keys.equals(hSSPrivateKeyParameters.keys)) {
            return this.sig.equals(hSSPrivateKeyParameters.sig);
        }
        return false;
    }

    public HSSPrivateKeyParameters extractKeyShard(int i) {
        HSSPrivateKeyParameters makeCopy;
        synchronized (this) {
            long j = (long) i;
            if (getUsagesRemaining() >= j) {
                long j2 = this.index;
                this.index = j + j2;
                ArrayList arrayList = new ArrayList(getKeys());
                ArrayList arrayList2 = new ArrayList(getSig());
                makeCopy = makeCopy(new HSSPrivateKeyParameters(this.l, arrayList, arrayList2, j2, j2 + j, true));
                resetKeyToIndex();
            } else {
                throw new IllegalArgumentException("usageCount exceeds usages remaining in current leaf");
            }
        }
        return makeCopy;
    }

    public LMSContext generateLMSContext() {
        LMSPrivateKeyParameters lMSPrivateKeyParameters;
        LMSSignedPubKey[] lMSSignedPubKeyArr;
        int l2 = getL();
        synchronized (this) {
            HSS.rangeTestKeys(this);
            List<LMSPrivateKeyParameters> keys2 = getKeys();
            List<LMSSignature> sig2 = getSig();
            int i = l2 - 1;
            lMSPrivateKeyParameters = getKeys().get(i);
            lMSSignedPubKeyArr = new LMSSignedPubKey[i];
            int i2 = 0;
            while (i2 < i) {
                int i3 = i2 + 1;
                lMSSignedPubKeyArr[i2] = new LMSSignedPubKey(sig2.get(i2), keys2.get(i3).getPublicKey());
                i2 = i3;
            }
            incIndex();
        }
        return lMSPrivateKeyParameters.generateLMSContext().withSignedPublicKeys(lMSSignedPubKeyArr);
    }

    public byte[] generateSignature(LMSContext lMSContext) {
        try {
            return HSS.generateSignature(getL(), lMSContext).getEncoded();
        } catch (IOException e) {
            throw new IllegalStateException("unable to encode signature: " + e.getMessage(), e);
        }
    }

    public synchronized byte[] getEncoded() throws IOException {
        Composer bool;
        bool = Composer.compose().u32str(0).u32str(this.l).u64str(this.index).u64str(this.indexLimit).bool(this.isShard);
        for (LMSPrivateKeyParameters bytes : this.keys) {
            bool.bytes((Encodable) bytes);
        }
        for (LMSSignature bytes2 : this.sig) {
            bool.bytes((Encodable) bytes2);
        }
        return bool.build();
    }

    public synchronized long getIndex() {
        return this.index;
    }

    /* access modifiers changed from: package-private */
    public long getIndexLimit() {
        return this.indexLimit;
    }

    /* access modifiers changed from: package-private */
    public synchronized List<LMSPrivateKeyParameters> getKeys() {
        return this.keys;
    }

    public int getL() {
        return this.l;
    }

    public synchronized LMSParameters[] getLMSParameters() {
        LMSParameters[] lMSParametersArr;
        int size = this.keys.size();
        lMSParametersArr = new LMSParameters[size];
        for (int i = 0; i < size; i++) {
            LMSPrivateKeyParameters lMSPrivateKeyParameters = this.keys.get(i);
            lMSParametersArr[i] = new LMSParameters(lMSPrivateKeyParameters.getSigParameters(), lMSPrivateKeyParameters.getOtsParameters());
        }
        return lMSParametersArr;
    }

    public synchronized HSSPublicKeyParameters getPublicKey() {
        return new HSSPublicKeyParameters(this.l, getRootKey().getPublicKey());
    }

    /* access modifiers changed from: package-private */
    public LMSPrivateKeyParameters getRootKey() {
        return this.keys.get(0);
    }

    /* access modifiers changed from: package-private */
    public synchronized List<LMSSignature> getSig() {
        return this.sig;
    }

    public long getUsagesRemaining() {
        return this.indexLimit - this.index;
    }

    public int hashCode() {
        long j = this.indexLimit;
        long j2 = this.index;
        return (((((((((this.l * 31) + (this.isShard ? 1 : 0)) * 31) + this.keys.hashCode()) * 31) + this.sig.hashCode()) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + ((int) (j2 ^ (j2 >>> 32)));
    }

    /* access modifiers changed from: package-private */
    public synchronized void incIndex() {
        this.index++;
    }

    /* access modifiers changed from: package-private */
    public boolean isShard() {
        return this.isShard;
    }

    /* access modifiers changed from: package-private */
    public void replaceConsumedKey(int i) {
        int i2 = i - 1;
        LMOtsPrivateKey currentOTSKey = this.keys.get(i2).getCurrentOTSKey();
        int n = currentOTSKey.getParameter().getN();
        SeedDerive derivationFunction = currentOTSKey.getDerivationFunction();
        derivationFunction.setJ(-2);
        byte[] bArr = new byte[n];
        derivationFunction.deriveSeed(bArr, true);
        byte[] bArr2 = new byte[n];
        derivationFunction.deriveSeed(bArr2, false);
        byte[] bArr3 = new byte[16];
        System.arraycopy(bArr2, 0, bArr3, 0, 16);
        ArrayList arrayList = new ArrayList(this.keys);
        LMSPrivateKeyParameters lMSPrivateKeyParameters = this.keys.get(i);
        arrayList.set(i, LMS.generateKeys(lMSPrivateKeyParameters.getSigParameters(), lMSPrivateKeyParameters.getOtsParameters(), 0, bArr3, bArr));
        ArrayList arrayList2 = new ArrayList(this.sig);
        arrayList2.set(i2, LMS.generateSign((LMSPrivateKeyParameters) arrayList.get(i2), ((LMSPrivateKeyParameters) arrayList.get(i)).getPublicKey().toByteArray()));
        this.keys = Collections.unmodifiableList(arrayList);
        this.sig = Collections.unmodifiableList(arrayList2);
    }

    /* access modifiers changed from: package-private */
    public void resetKeyToIndex() {
        boolean z;
        List<LMSPrivateKeyParameters> keys2 = getKeys();
        int size = keys2.size();
        long[] jArr = new long[size];
        long index2 = getIndex();
        for (int size2 = keys2.size() - 1; size2 >= 0; size2--) {
            LMSigParameters sigParameters = keys2.get(size2).getSigParameters();
            jArr[size2] = ((long) ((1 << sigParameters.getH()) - 1)) & index2;
            index2 >>>= sigParameters.getH();
        }
        LMSPrivateKeyParameters[] lMSPrivateKeyParametersArr = (LMSPrivateKeyParameters[]) keys2.toArray(new LMSPrivateKeyParameters[keys2.size()]);
        List<LMSSignature> list = this.sig;
        LMSSignature[] lMSSignatureArr = (LMSSignature[]) list.toArray(new LMSSignature[list.size()]);
        LMSPrivateKeyParameters rootKey = getRootKey();
        if (((long) (lMSPrivateKeyParametersArr[0].getIndex() - 1)) != jArr[0]) {
            lMSPrivateKeyParametersArr[0] = LMS.generateKeys(rootKey.getSigParameters(), rootKey.getOtsParameters(), (int) jArr[0], rootKey.getI(), rootKey.getMasterSecret());
            z = true;
        } else {
            z = false;
        }
        int i = 1;
        while (i < size) {
            int i2 = i - 1;
            LMSPrivateKeyParameters lMSPrivateKeyParameters = lMSPrivateKeyParametersArr[i2];
            int n = lMSPrivateKeyParameters.getOtsParameters().getN();
            byte[] bArr = new byte[16];
            byte[] bArr2 = new byte[n];
            SeedDerive seedDerive = new SeedDerive(lMSPrivateKeyParameters.getI(), lMSPrivateKeyParameters.getMasterSecret(), DigestUtil.getDigest(lMSPrivateKeyParameters.getOtsParameters()));
            seedDerive.setQ((int) jArr[i2]);
            seedDerive.setJ(-2);
            seedDerive.deriveSeed(bArr2, true);
            byte[] bArr3 = new byte[n];
            seedDerive.deriveSeed(bArr3, false);
            System.arraycopy(bArr3, 0, bArr, 0, 16);
            boolean z2 = i >= size + -1 ? jArr[i] == ((long) lMSPrivateKeyParametersArr[i].getIndex()) : jArr[i] == ((long) (lMSPrivateKeyParametersArr[i].getIndex() - 1));
            if (!Arrays.areEqual(bArr, lMSPrivateKeyParametersArr[i].getI()) || !Arrays.areEqual(bArr2, lMSPrivateKeyParametersArr[i].getMasterSecret())) {
                LMSPrivateKeyParameters generateKeys = LMS.generateKeys(keys2.get(i).getSigParameters(), keys2.get(i).getOtsParameters(), (int) jArr[i], bArr, bArr2);
                lMSPrivateKeyParametersArr[i] = generateKeys;
                lMSSignatureArr[i2] = LMS.generateSign(lMSPrivateKeyParametersArr[i2], generateKeys.getPublicKey().toByteArray());
            } else if (!z2) {
                lMSPrivateKeyParametersArr[i] = LMS.generateKeys(keys2.get(i).getSigParameters(), keys2.get(i).getOtsParameters(), (int) jArr[i], bArr, bArr2);
            } else {
                i++;
            }
            z = true;
            i++;
        }
        if (z) {
            updateHierarchy(lMSPrivateKeyParametersArr, lMSSignatureArr);
        }
    }

    /* access modifiers changed from: protected */
    public void updateHierarchy(LMSPrivateKeyParameters[] lMSPrivateKeyParametersArr, LMSSignature[] lMSSignatureArr) {
        synchronized (this) {
            this.keys = Collections.unmodifiableList(java.util.Arrays.asList(lMSPrivateKeyParametersArr));
            this.sig = Collections.unmodifiableList(java.util.Arrays.asList(lMSSignatureArr));
        }
    }
}
