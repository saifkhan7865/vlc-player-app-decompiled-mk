package io.ktor.network.tls;

import io.ktor.network.tls.cipher.TLSCipher;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/ktor/network/tls/cipher/TLSCipher;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: TLSClientHandshake.kt */
final class TLSClientHandshake$cipher$2 extends Lambda implements Function0<TLSCipher> {
    final /* synthetic */ TLSClientHandshake this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TLSClientHandshake$cipher$2(TLSClientHandshake tLSClientHandshake) {
        super(0);
        this.this$0 = tLSClientHandshake;
    }

    public final TLSCipher invoke() {
        TLSCipher.Companion companion = TLSCipher.Companion;
        TLSServerHello access$getServerHello$p = this.this$0.serverHello;
        if (access$getServerHello$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("serverHello");
            access$getServerHello$p = null;
        }
        return companion.fromSuite(access$getServerHello$p.getCipherSuite(), this.this$0.getKeyMaterial());
    }
}
