package io.ktor.network.tls;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0012\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: TLSClientHandshake.kt */
final class TLSClientHandshake$keyMaterial$2 extends Lambda implements Function0<byte[]> {
    final /* synthetic */ TLSClientHandshake this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TLSClientHandshake$keyMaterial$2(TLSClientHandshake tLSClientHandshake) {
        super(0);
        this.this$0 = tLSClientHandshake;
    }

    public final byte[] invoke() {
        TLSServerHello access$getServerHello$p = this.this$0.serverHello;
        TLSServerHello tLSServerHello = null;
        if (access$getServerHello$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("serverHello");
            access$getServerHello$p = null;
        }
        CipherSuite cipherSuite = access$getServerHello$p.getCipherSuite();
        TLSClientHandshake tLSClientHandshake = this.this$0;
        SecretKeySpec access$getMasterSecret$p = tLSClientHandshake.masterSecret;
        if (access$getMasterSecret$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("masterSecret");
            access$getMasterSecret$p = null;
        }
        SecretKey secretKey = access$getMasterSecret$p;
        TLSServerHello access$getServerHello$p2 = tLSClientHandshake.serverHello;
        if (access$getServerHello$p2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("serverHello");
        } else {
            tLSServerHello = access$getServerHello$p2;
        }
        return KeysKt.keyMaterial(secretKey, ArraysKt.plus(tLSServerHello.getServerSeed(), tLSClientHandshake.clientSeed), cipherSuite.getKeyStrengthInBytes(), cipherSuite.getMacStrengthInBytes(), cipherSuite.getFixedIvLength());
    }
}
