package io.ktor.network.tls.cipher;

import io.ktor.network.tls.CipherSuite;
import io.ktor.network.tls.TLSRecord;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b`\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H&J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H&¨\u0006\u0007"}, d2 = {"Lio/ktor/network/tls/cipher/TLSCipher;", "", "decrypt", "Lio/ktor/network/tls/TLSRecord;", "record", "encrypt", "Companion", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Cipher.kt */
public interface TLSCipher {
    public static final Companion Companion = Companion.$$INSTANCE;

    TLSRecord decrypt(TLSRecord tLSRecord);

    TLSRecord encrypt(TLSRecord tLSRecord);

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lio/ktor/network/tls/cipher/TLSCipher$Companion;", "", "()V", "fromSuite", "Lio/ktor/network/tls/cipher/TLSCipher;", "suite", "Lio/ktor/network/tls/CipherSuite;", "keyMaterial", "", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: Cipher.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* compiled from: Cipher.kt */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
            static {
                /*
                    io.ktor.network.tls.CipherType[] r0 = io.ktor.network.tls.CipherType.values()
                    int r0 = r0.length
                    int[] r0 = new int[r0]
                    io.ktor.network.tls.CipherType r1 = io.ktor.network.tls.CipherType.GCM     // Catch:{ NoSuchFieldError -> 0x0010 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                    r2 = 1
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
                L_0x0010:
                    io.ktor.network.tls.CipherType r1 = io.ktor.network.tls.CipherType.CBC     // Catch:{ NoSuchFieldError -> 0x0019 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                    r2 = 2
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
                L_0x0019:
                    $EnumSwitchMapping$0 = r0
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.tls.cipher.TLSCipher.Companion.WhenMappings.<clinit>():void");
            }
        }

        private Companion() {
        }

        public final TLSCipher fromSuite(CipherSuite cipherSuite, byte[] bArr) {
            Intrinsics.checkNotNullParameter(cipherSuite, "suite");
            Intrinsics.checkNotNullParameter(bArr, "keyMaterial");
            int i = WhenMappings.$EnumSwitchMapping$0[cipherSuite.getCipherType().ordinal()];
            if (i == 1) {
                return new GCMCipher(cipherSuite, bArr);
            }
            if (i == 2) {
                return new CBCCipher(cipherSuite, bArr);
            }
            throw new NoWhenBranchMatchedException();
        }
    }
}
