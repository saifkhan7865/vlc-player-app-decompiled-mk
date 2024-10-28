package org.bouncycastle.crypto.examples;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.engines.DESedeEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Hex;

public class DESExample {
    private PaddedBufferedBlockCipher cipher;
    private boolean encrypt;
    private BufferedInputStream in;
    private byte[] key;
    private BufferedOutputStream out;

    public DESExample() {
        this.encrypt = true;
        this.cipher = null;
        this.in = null;
        this.out = null;
        this.key = null;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:18|19) */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        java.lang.System.err.println("Hmmm, no SHA1PRNG, you need the Sun implementation");
        java.lang.System.exit(1);
        r8 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00ab, code lost:
        r7 = java.lang.System.err;
        r8 = new java.lang.StringBuilder("Could not decryption create key file [");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00d8, code lost:
        r8.append(r9);
        r8.append("]");
        r7.println(r8.toString());
        java.lang.System.exit(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x0070 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DESExample(java.lang.String r7, java.lang.String r8, java.lang.String r9, boolean r10) {
        /*
            r6 = this;
            java.lang.String r0 = "]"
            r6.<init>()
            r1 = 0
            r6.cipher = r1
            r6.in = r1
            r6.out = r1
            r6.key = r1
            r6.encrypt = r10
            r2 = 1
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch:{ FileNotFoundException -> 0x001e }
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x001e }
            r4.<init>(r7)     // Catch:{ FileNotFoundException -> 0x001e }
            r3.<init>(r4)     // Catch:{ FileNotFoundException -> 0x001e }
            r6.in = r3     // Catch:{ FileNotFoundException -> 0x001e }
            goto L_0x0037
        L_0x001e:
            java.io.PrintStream r3 = java.lang.System.err
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "Input file not found ["
            r4.<init>(r5)
            r4.append(r7)
            r4.append(r0)
            java.lang.String r7 = r4.toString()
            r3.println(r7)
            java.lang.System.exit(r2)
        L_0x0037:
            java.io.BufferedOutputStream r7 = new java.io.BufferedOutputStream     // Catch:{ IOException -> 0x0044 }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0044 }
            r3.<init>(r8)     // Catch:{ IOException -> 0x0044 }
            r7.<init>(r3)     // Catch:{ IOException -> 0x0044 }
            r6.out = r7     // Catch:{ IOException -> 0x0044 }
            goto L_0x005d
        L_0x0044:
            java.io.PrintStream r7 = java.lang.System.err
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Output file not created ["
            r3.<init>(r4)
            r3.append(r8)
            r3.append(r0)
            java.lang.String r8 = r3.toString()
            r7.println(r8)
            java.lang.System.exit(r2)
        L_0x005d:
            r7 = 0
            if (r10 == 0) goto L_0x00b5
            java.security.SecureRandom r8 = new java.security.SecureRandom     // Catch:{ Exception -> 0x0070 }
            r8.<init>()     // Catch:{ Exception -> 0x0070 }
            java.lang.String r10 = "www.bouncycastle.org"
            byte[] r10 = r10.getBytes()     // Catch:{ Exception -> 0x006f }
            r8.setSeed(r10)     // Catch:{ Exception -> 0x006f }
            goto L_0x007b
        L_0x006f:
            r1 = r8
        L_0x0070:
            java.io.PrintStream r8 = java.lang.System.err     // Catch:{ IOException -> 0x00ab }
            java.lang.String r10 = "Hmmm, no SHA1PRNG, you need the Sun implementation"
            r8.println(r10)     // Catch:{ IOException -> 0x00ab }
            java.lang.System.exit(r2)     // Catch:{ IOException -> 0x00ab }
            r8 = r1
        L_0x007b:
            org.bouncycastle.crypto.KeyGenerationParameters r10 = new org.bouncycastle.crypto.KeyGenerationParameters     // Catch:{ IOException -> 0x00ab }
            r1 = 192(0xc0, float:2.69E-43)
            r10.<init>(r8, r1)     // Catch:{ IOException -> 0x00ab }
            org.bouncycastle.crypto.generators.DESedeKeyGenerator r8 = new org.bouncycastle.crypto.generators.DESedeKeyGenerator     // Catch:{ IOException -> 0x00ab }
            r8.<init>()     // Catch:{ IOException -> 0x00ab }
            r8.init(r10)     // Catch:{ IOException -> 0x00ab }
            byte[] r8 = r8.generateKey()     // Catch:{ IOException -> 0x00ab }
            r6.key = r8     // Catch:{ IOException -> 0x00ab }
            java.io.BufferedOutputStream r8 = new java.io.BufferedOutputStream     // Catch:{ IOException -> 0x00ab }
            java.io.FileOutputStream r10 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x00ab }
            r10.<init>(r9)     // Catch:{ IOException -> 0x00ab }
            r8.<init>(r10)     // Catch:{ IOException -> 0x00ab }
            byte[] r10 = r6.key     // Catch:{ IOException -> 0x00ab }
            byte[] r10 = org.bouncycastle.util.encoders.Hex.encode(r10)     // Catch:{ IOException -> 0x00ab }
            int r1 = r10.length     // Catch:{ IOException -> 0x00ab }
            r8.write(r10, r7, r1)     // Catch:{ IOException -> 0x00ab }
            r8.flush()     // Catch:{ IOException -> 0x00ab }
            r8.close()     // Catch:{ IOException -> 0x00ab }
            goto L_0x00e8
        L_0x00ab:
            java.io.PrintStream r7 = java.lang.System.err
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r10 = "Could not decryption create key file ["
            r8.<init>(r10)
            goto L_0x00d8
        L_0x00b5:
            java.io.BufferedInputStream r8 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x00cf }
            java.io.FileInputStream r10 = new java.io.FileInputStream     // Catch:{ IOException -> 0x00cf }
            r10.<init>(r9)     // Catch:{ IOException -> 0x00cf }
            r8.<init>(r10)     // Catch:{ IOException -> 0x00cf }
            int r10 = r8.available()     // Catch:{ IOException -> 0x00cf }
            byte[] r1 = new byte[r10]     // Catch:{ IOException -> 0x00cf }
            r8.read(r1, r7, r10)     // Catch:{ IOException -> 0x00cf }
            byte[] r7 = org.bouncycastle.util.encoders.Hex.decode((byte[]) r1)     // Catch:{ IOException -> 0x00cf }
            r6.key = r7     // Catch:{ IOException -> 0x00cf }
            goto L_0x00e8
        L_0x00cf:
            java.io.PrintStream r7 = java.lang.System.err
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r10 = "Decryption key file not found, or not valid ["
            r8.<init>(r10)
        L_0x00d8:
            r8.append(r9)
            r8.append(r0)
            java.lang.String r8 = r8.toString()
            r7.println(r8)
            java.lang.System.exit(r2)
        L_0x00e8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.examples.DESExample.<init>(java.lang.String, java.lang.String, java.lang.String, boolean):void");
    }

    public static void main(String[] strArr) {
        String str;
        boolean z = true;
        if (strArr.length < 2) {
            DESExample dESExample = new DESExample();
            PrintStream printStream = System.err;
            printStream.println("Usage: java " + dESExample.getClass().getName() + " infile outfile [keyfile]");
            System.exit(1);
        }
        String str2 = strArr[0];
        String str3 = strArr[1];
        if (strArr.length > 2) {
            str = strArr[2];
            z = false;
        } else {
            str = "deskey.dat";
        }
        new DESExample(str2, str3, str, z).process();
    }

    private void performDecrypt(byte[] bArr) {
        this.cipher.init(false, new KeyParameter(bArr));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.in));
        byte[] bArr2 = null;
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    byte[] decode = Hex.decode(readLine);
                    bArr2 = new byte[this.cipher.getOutputSize(decode.length)];
                    int processBytes = this.cipher.processBytes(decode, 0, decode.length, bArr2, 0);
                    if (processBytes > 0) {
                        this.out.write(bArr2, 0, processBytes);
                    }
                } else {
                    try {
                        break;
                    } catch (CryptoException unused) {
                        return;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        int doFinal = this.cipher.doFinal(bArr2, 0);
        if (doFinal > 0) {
            this.out.write(bArr2, 0, doFinal);
        }
    }

    private void performEncrypt(byte[] bArr) {
        this.cipher.init(true, new KeyParameter(bArr));
        byte[] bArr2 = new byte[47];
        byte[] bArr3 = new byte[this.cipher.getOutputSize(47)];
        while (true) {
            try {
                int read = this.in.read(bArr2, 0, 47);
                if (read > 0) {
                    int processBytes = this.cipher.processBytes(bArr2, 0, read, bArr3, 0);
                    if (processBytes > 0) {
                        byte[] encode = Hex.encode(bArr3, 0, processBytes);
                        this.out.write(encode, 0, encode.length);
                        this.out.write(10);
                    }
                } else {
                    try {
                        break;
                    } catch (CryptoException unused) {
                        return;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        int doFinal = this.cipher.doFinal(bArr3, 0);
        if (doFinal > 0) {
            byte[] encode2 = Hex.encode(bArr3, 0, doFinal);
            this.out.write(encode2, 0, encode2.length);
            this.out.write(10);
        }
    }

    private void process() {
        this.cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new DESedeEngine()));
        if (this.encrypt) {
            performEncrypt(this.key);
        } else {
            performDecrypt(this.key);
        }
        try {
            this.in.close();
            this.out.flush();
            this.out.close();
        } catch (IOException e) {
            PrintStream printStream = System.err;
            printStream.println("exception closing resources: " + e.getMessage());
        }
    }
}
