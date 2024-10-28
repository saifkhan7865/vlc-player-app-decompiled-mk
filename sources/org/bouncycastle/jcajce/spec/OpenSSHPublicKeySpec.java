package org.bouncycastle.jcajce.spec;

import com.google.common.base.Ascii;
import java.security.spec.EncodedKeySpec;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;

public class OpenSSHPublicKeySpec extends EncodedKeySpec {
    private static final String[] allowedTypes = {"ssh-rsa", "ssh-ed25519", "ssh-dss"};
    private final String type;

    public OpenSSHPublicKeySpec(byte[] bArr) {
        super(bArr);
        int i = 0;
        int i2 = (((bArr[0] & 255) << Ascii.CAN) | ((bArr[1] & 255) << Ascii.DLE) | ((bArr[2] & 255) << 8) | (bArr[3] & 255)) + 4;
        if (i2 < bArr.length) {
            String fromByteArray = Strings.fromByteArray(Arrays.copyOfRange(bArr, 4, i2));
            this.type = fromByteArray;
            if (!fromByteArray.startsWith("ecdsa")) {
                while (true) {
                    String[] strArr = allowedTypes;
                    if (i >= strArr.length) {
                        throw new IllegalArgumentException("unrecognised public key type " + this.type);
                    } else if (!strArr[i].equals(this.type)) {
                        i++;
                    } else {
                        return;
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("invalid public key blob: type field longer than blob");
        }
    }

    public String getFormat() {
        return "OpenSSH";
    }

    public String getType() {
        return this.type;
    }
}
