package org.bouncycastle.pqc.legacy.crypto.gmss;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.pqc.legacy.crypto.gmss.util.GMSSRandom;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Hex;
import org.fusesource.jansi.AnsiRenderer;

public class GMSSLeaf {
    private byte[] concHashs;
    private GMSSRandom gmssRandom;
    private int i;
    private int j;
    private int keysize;
    private byte[] leaf;
    private int mdsize;
    private Digest messDigestOTS;
    byte[] privateKeyOTS;
    private byte[] seed;
    private int steps;
    private int two_power_w;
    private int w;

    GMSSLeaf(Digest digest, int i2, int i3) {
        this.w = i2;
        this.messDigestOTS = digest;
        this.gmssRandom = new GMSSRandom(digest);
        int digestSize = this.messDigestOTS.getDigestSize();
        this.mdsize = digestSize;
        double d = (double) (digestSize << 3);
        double d2 = (double) i2;
        Double.isNaN(d);
        Double.isNaN(d2);
        int ceil = (int) Math.ceil(d / d2);
        double log = (double) getLog((ceil << i2) + 1);
        Double.isNaN(log);
        Double.isNaN(d2);
        int ceil2 = ceil + ((int) Math.ceil(log / d2));
        this.keysize = ceil2;
        int i4 = 1 << i2;
        this.two_power_w = i4;
        double d3 = (double) (((i4 - 1) * ceil2) + 1 + ceil2);
        double d4 = (double) i3;
        Double.isNaN(d3);
        Double.isNaN(d4);
        this.steps = (int) Math.ceil(d3 / d4);
        int i5 = this.mdsize;
        this.seed = new byte[i5];
        this.leaf = new byte[i5];
        this.privateKeyOTS = new byte[i5];
        this.concHashs = new byte[(i5 * this.keysize)];
    }

    public GMSSLeaf(Digest digest, int i2, int i3, byte[] bArr) {
        this.w = i2;
        this.messDigestOTS = digest;
        this.gmssRandom = new GMSSRandom(digest);
        int digestSize = this.messDigestOTS.getDigestSize();
        this.mdsize = digestSize;
        double d = (double) (digestSize << 3);
        double d2 = (double) i2;
        Double.isNaN(d);
        Double.isNaN(d2);
        int ceil = (int) Math.ceil(d / d2);
        double log = (double) getLog((ceil << i2) + 1);
        Double.isNaN(log);
        Double.isNaN(d2);
        int ceil2 = ceil + ((int) Math.ceil(log / d2));
        this.keysize = ceil2;
        int i4 = 1 << i2;
        this.two_power_w = i4;
        double d3 = (double) (((i4 - 1) * ceil2) + 1 + ceil2);
        double d4 = (double) i3;
        Double.isNaN(d3);
        Double.isNaN(d4);
        this.steps = (int) Math.ceil(d3 / d4);
        int i5 = this.mdsize;
        this.seed = new byte[i5];
        this.leaf = new byte[i5];
        this.privateKeyOTS = new byte[i5];
        this.concHashs = new byte[(i5 * this.keysize)];
        initLeafCalc(bArr);
    }

    public GMSSLeaf(Digest digest, byte[][] bArr, int[] iArr) {
        this.i = iArr[0];
        this.j = iArr[1];
        this.steps = iArr[2];
        this.w = iArr[3];
        this.messDigestOTS = digest;
        this.gmssRandom = new GMSSRandom(digest);
        int digestSize = this.messDigestOTS.getDigestSize();
        this.mdsize = digestSize;
        double d = (double) (digestSize << 3);
        double d2 = (double) this.w;
        Double.isNaN(d);
        Double.isNaN(d2);
        int ceil = (int) Math.ceil(d / d2);
        double log = (double) getLog((ceil << this.w) + 1);
        double d3 = (double) this.w;
        Double.isNaN(log);
        Double.isNaN(d3);
        this.keysize = ceil + ((int) Math.ceil(log / d3));
        this.two_power_w = 1 << this.w;
        this.privateKeyOTS = bArr[0];
        this.seed = bArr[1];
        this.concHashs = bArr[2];
        this.leaf = bArr[3];
    }

    private GMSSLeaf(GMSSLeaf gMSSLeaf) {
        this.messDigestOTS = gMSSLeaf.messDigestOTS;
        this.mdsize = gMSSLeaf.mdsize;
        this.keysize = gMSSLeaf.keysize;
        this.gmssRandom = gMSSLeaf.gmssRandom;
        this.leaf = Arrays.clone(gMSSLeaf.leaf);
        this.concHashs = Arrays.clone(gMSSLeaf.concHashs);
        this.i = gMSSLeaf.i;
        this.j = gMSSLeaf.j;
        this.two_power_w = gMSSLeaf.two_power_w;
        this.w = gMSSLeaf.w;
        this.steps = gMSSLeaf.steps;
        this.seed = Arrays.clone(gMSSLeaf.seed);
        this.privateKeyOTS = Arrays.clone(gMSSLeaf.privateKeyOTS);
    }

    private int getLog(int i2) {
        int i3 = 1;
        int i4 = 2;
        while (i4 < i2) {
            i4 <<= 1;
            i3++;
        }
        return i3;
    }

    private void updateLeafCalc() {
        byte[] bArr = new byte[this.messDigestOTS.getDigestSize()];
        for (int i2 = 0; i2 < this.steps + 10000; i2++) {
            int i3 = this.i;
            if (i3 == this.keysize && this.j == this.two_power_w - 1) {
                Digest digest = this.messDigestOTS;
                byte[] bArr2 = this.concHashs;
                digest.update(bArr2, 0, bArr2.length);
                byte[] bArr3 = new byte[this.messDigestOTS.getDigestSize()];
                this.leaf = bArr3;
                this.messDigestOTS.doFinal(bArr3, 0);
                return;
            }
            if (i3 == 0 || this.j == this.two_power_w - 1) {
                this.i = i3 + 1;
                this.j = 0;
                this.privateKeyOTS = this.gmssRandom.nextSeed(this.seed);
            } else {
                Digest digest2 = this.messDigestOTS;
                byte[] bArr4 = this.privateKeyOTS;
                digest2.update(bArr4, 0, bArr4.length);
                this.privateKeyOTS = bArr;
                this.messDigestOTS.doFinal(bArr, 0);
                int i4 = this.j + 1;
                this.j = i4;
                if (i4 == this.two_power_w - 1) {
                    byte[] bArr5 = this.privateKeyOTS;
                    byte[] bArr6 = this.concHashs;
                    int i5 = this.mdsize;
                    System.arraycopy(bArr5, 0, bArr6, (this.i - 1) * i5, i5);
                }
            }
        }
        throw new IllegalStateException("unable to updateLeaf in steps: " + this.steps + AnsiRenderer.CODE_TEXT_SEPARATOR + this.i + AnsiRenderer.CODE_TEXT_SEPARATOR + this.j);
    }

    public byte[] getLeaf() {
        return Arrays.clone(this.leaf);
    }

    public byte[][] getStatByte() {
        return new byte[][]{this.privateKeyOTS, this.seed, this.concHashs, this.leaf};
    }

    public int[] getStatInt() {
        return new int[]{this.i, this.j, this.steps, this.w};
    }

    /* access modifiers changed from: package-private */
    public void initLeafCalc(byte[] bArr) {
        this.i = 0;
        this.j = 0;
        byte[] bArr2 = new byte[this.mdsize];
        System.arraycopy(bArr, 0, bArr2, 0, this.seed.length);
        this.seed = this.gmssRandom.nextSeed(bArr2);
    }

    /* access modifiers changed from: package-private */
    public GMSSLeaf nextLeaf() {
        GMSSLeaf gMSSLeaf = new GMSSLeaf(this);
        gMSSLeaf.updateLeafCalc();
        return gMSSLeaf;
    }

    public String toString() {
        StringBuilder sb;
        String str = "";
        for (int i2 = 0; i2 < 4; i2++) {
            str = str + getStatInt()[i2] + AnsiRenderer.CODE_TEXT_SEPARATOR;
        }
        String str2 = str + AnsiRenderer.CODE_TEXT_SEPARATOR + this.mdsize + AnsiRenderer.CODE_TEXT_SEPARATOR + this.keysize + AnsiRenderer.CODE_TEXT_SEPARATOR + this.two_power_w + AnsiRenderer.CODE_TEXT_SEPARATOR;
        byte[][] statByte = getStatByte();
        for (int i3 = 0; i3 < 4; i3++) {
            if (statByte[i3] != null) {
                sb = new StringBuilder();
                sb.append(str2);
                sb.append(new String(Hex.encode(statByte[i3])));
                sb.append(AnsiRenderer.CODE_TEXT_SEPARATOR);
            } else {
                sb = new StringBuilder();
                sb.append(str2);
                sb.append("null ");
            }
            str2 = sb.toString();
        }
        return str2;
    }
}
