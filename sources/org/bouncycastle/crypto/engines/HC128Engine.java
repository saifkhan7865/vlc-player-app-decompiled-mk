package org.bouncycastle.crypto.engines;

import androidx.core.app.FrameMetricsAggregator;
import com.google.android.material.internal.ViewUtils;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.videolan.libvlc.MediaDiscoverer;

public class HC128Engine implements StreamCipher {
    private byte[] buf = new byte[4];
    private int cnt = 0;
    private int idx = 0;
    private boolean initialised;
    private byte[] iv;
    private byte[] key;
    private int[] p = new int[512];
    private int[] q = new int[512];

    private static int dim(int i, int i2) {
        return mod512(i - i2);
    }

    private static int f1(int i) {
        return (i >>> 3) ^ (rotateRight(i, 7) ^ rotateRight(i, 18));
    }

    private static int f2(int i) {
        return (i >>> 10) ^ (rotateRight(i, 17) ^ rotateRight(i, 19));
    }

    private int g1(int i, int i2, int i3) {
        return (rotateRight(i, 10) ^ rotateRight(i3, 23)) + rotateRight(i2, 8);
    }

    private int g2(int i, int i2, int i3) {
        return (rotateLeft(i, 10) ^ rotateLeft(i3, 23)) + rotateLeft(i2, 8);
    }

    private byte getByte() {
        if (this.idx == 0) {
            int step = step();
            byte[] bArr = this.buf;
            bArr[0] = (byte) (step & 255);
            bArr[1] = (byte) ((step >> 8) & 255);
            bArr[2] = (byte) ((step >> 16) & 255);
            bArr[3] = (byte) ((step >> 24) & 255);
        }
        byte[] bArr2 = this.buf;
        int i = this.idx;
        byte b = bArr2[i];
        this.idx = 3 & (i + 1);
        return b;
    }

    private int h1(int i) {
        int[] iArr = this.q;
        return iArr[i & 255] + iArr[((i >> 16) & 255) + 256];
    }

    private int h2(int i) {
        int[] iArr = this.p;
        return iArr[i & 255] + iArr[((i >> 16) & 255) + 256];
    }

    private void init() {
        if (this.key.length != 16) {
            throw new IllegalArgumentException("The key must be 128 bits long");
        } else if (this.iv.length == 16) {
            this.idx = 0;
            this.cnt = 0;
            int[] iArr = new int[MediaDiscoverer.Event.Started];
            for (int i = 0; i < 16; i++) {
                int i2 = i >> 2;
                iArr[i2] = ((this.key[i] & 255) << ((i & 3) * 8)) | iArr[i2];
            }
            System.arraycopy(iArr, 0, iArr, 4, 4);
            int i3 = 0;
            while (true) {
                byte[] bArr = this.iv;
                if (i3 >= bArr.length || i3 >= 16) {
                    System.arraycopy(iArr, 8, iArr, 12, 4);
                } else {
                    int i4 = (i3 >> 2) + 8;
                    iArr[i4] = ((bArr[i3] & 255) << ((i3 & 3) * 8)) | iArr[i4];
                    i3++;
                }
            }
            System.arraycopy(iArr, 8, iArr, 12, 4);
            for (int i5 = 16; i5 < 1280; i5++) {
                iArr[i5] = f2(iArr[i5 - 2]) + iArr[i5 - 7] + f1(iArr[i5 - 15]) + iArr[i5 - 16] + i5;
            }
            System.arraycopy(iArr, 256, this.p, 0, 512);
            System.arraycopy(iArr, ViewUtils.EDGE_TO_EDGE_FLAGS, this.q, 0, 512);
            for (int i6 = 0; i6 < 512; i6++) {
                this.p[i6] = step();
            }
            for (int i7 = 0; i7 < 512; i7++) {
                this.q[i7] = step();
            }
            this.cnt = 0;
        } else {
            throw new IllegalArgumentException("The IV must be 128 bits long");
        }
    }

    private static int mod1024(int i) {
        return i & 1023;
    }

    private static int mod512(int i) {
        return i & FrameMetricsAggregator.EVERY_DURATION;
    }

    private static int rotateLeft(int i, int i2) {
        return (i >>> (-i2)) | (i << i2);
    }

    private static int rotateRight(int i, int i2) {
        return (i << (-i2)) | (i >>> i2);
    }

    private int step() {
        int i;
        int i2;
        int mod512 = mod512(this.cnt);
        if (this.cnt < 512) {
            int[] iArr = this.p;
            iArr[mod512] = iArr[mod512] + g1(iArr[dim(mod512, 3)], this.p[dim(mod512, 10)], this.p[dim(mod512, FrameMetricsAggregator.EVERY_DURATION)]);
            i = h1(this.p[dim(mod512, 12)]);
            i2 = this.p[mod512];
        } else {
            int[] iArr2 = this.q;
            iArr2[mod512] = iArr2[mod512] + g2(iArr2[dim(mod512, 3)], this.q[dim(mod512, 10)], this.q[dim(mod512, FrameMetricsAggregator.EVERY_DURATION)]);
            i = h2(this.q[dim(mod512, 12)]);
            i2 = this.q[mod512];
        }
        int i3 = i2 ^ i;
        this.cnt = mod1024(this.cnt + 1);
        return i3;
    }

    public String getAlgorithmName() {
        return "HC-128";
    }

    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            this.iv = parametersWithIV.getIV();
            CipherParameters parameters = parametersWithIV.getParameters();
            if (parameters instanceof KeyParameter) {
                this.key = ((KeyParameter) parameters).getKey();
                init();
                CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties(getAlgorithmName(), 128, cipherParameters, Utils.getPurpose(z)));
                this.initialised = true;
                return;
            }
            throw new IllegalArgumentException("Invalid parameter passed to HC128 init - " + cipherParameters.getClass().getName());
        }
        throw new IllegalArgumentException("no IV passed");
    }

    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException {
        if (!this.initialised) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        } else if (i + i2 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (i3 + i2 <= bArr2.length) {
            for (int i4 = 0; i4 < i2; i4++) {
                bArr2[i3 + i4] = (byte) (bArr[i + i4] ^ getByte());
            }
            return i2;
        } else {
            throw new OutputLengthException("output buffer too short");
        }
    }

    public void reset() {
        init();
    }

    public byte returnByte(byte b) {
        return (byte) (b ^ getByte());
    }
}
