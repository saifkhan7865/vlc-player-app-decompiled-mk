package org.bouncycastle.crypto.constraints;

import java.util.Collections;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bouncycastle.crypto.CryptoServiceConstraintsException;
import org.bouncycastle.crypto.CryptoServiceProperties;
import org.bouncycastle.crypto.CryptoServicePurpose;

public class LegacyBitsOfSecurityConstraint extends ServicesConstraint {
    private final int legacyRequiredBitsOfSecurity;
    private final int requiredBitsOfSecurity;

    /* renamed from: org.bouncycastle.crypto.constraints.LegacyBitsOfSecurityConstraint$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$bouncycastle$crypto$CryptoServicePurpose;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                org.bouncycastle.crypto.CryptoServicePurpose[] r0 = org.bouncycastle.crypto.CryptoServicePurpose.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$bouncycastle$crypto$CryptoServicePurpose = r0
                org.bouncycastle.crypto.CryptoServicePurpose r1 = org.bouncycastle.crypto.CryptoServicePurpose.ANY     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$bouncycastle$crypto$CryptoServicePurpose     // Catch:{ NoSuchFieldError -> 0x001d }
                org.bouncycastle.crypto.CryptoServicePurpose r1 = org.bouncycastle.crypto.CryptoServicePurpose.VERIFYING     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$bouncycastle$crypto$CryptoServicePurpose     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.bouncycastle.crypto.CryptoServicePurpose r1 = org.bouncycastle.crypto.CryptoServicePurpose.DECRYPTION     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$bouncycastle$crypto$CryptoServicePurpose     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.bouncycastle.crypto.CryptoServicePurpose r1 = org.bouncycastle.crypto.CryptoServicePurpose.VERIFICATION     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.constraints.LegacyBitsOfSecurityConstraint.AnonymousClass1.<clinit>():void");
        }
    }

    public LegacyBitsOfSecurityConstraint(int i) {
        this(i, 0);
    }

    public LegacyBitsOfSecurityConstraint(int i, int i2) {
        super(Collections.EMPTY_SET);
        this.requiredBitsOfSecurity = i;
        this.legacyRequiredBitsOfSecurity = i2;
    }

    public LegacyBitsOfSecurityConstraint(int i, int i2, Set<String> set) {
        super(set);
        this.requiredBitsOfSecurity = i;
        this.legacyRequiredBitsOfSecurity = i2;
    }

    public LegacyBitsOfSecurityConstraint(int i, Set<String> set) {
        this(i, 0, set);
    }

    public void check(CryptoServiceProperties cryptoServiceProperties) {
        if (!isException(cryptoServiceProperties.getServiceName())) {
            CryptoServicePurpose purpose = cryptoServiceProperties.getPurpose();
            int i = AnonymousClass1.$SwitchMap$org$bouncycastle$crypto$CryptoServicePurpose[purpose.ordinal()];
            if (i == 1 || i == 2 || i == 3 || i == 4) {
                if (cryptoServiceProperties.bitsOfSecurity() < this.legacyRequiredBitsOfSecurity) {
                    throw new CryptoServiceConstraintsException("service does not provide " + this.legacyRequiredBitsOfSecurity + " bits of security only " + cryptoServiceProperties.bitsOfSecurity());
                } else if (purpose != CryptoServicePurpose.ANY && LOG.isLoggable(Level.FINE)) {
                    Logger logger = LOG;
                    logger.fine("usage of legacy cryptography service for algorithm " + cryptoServiceProperties.getServiceName());
                }
            } else if (cryptoServiceProperties.bitsOfSecurity() < this.requiredBitsOfSecurity) {
                throw new CryptoServiceConstraintsException("service does not provide " + this.requiredBitsOfSecurity + " bits of security only " + cryptoServiceProperties.bitsOfSecurity());
            }
        }
    }
}
