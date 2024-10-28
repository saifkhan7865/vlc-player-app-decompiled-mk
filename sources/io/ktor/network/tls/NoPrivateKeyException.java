package io.ktor.network.tls;

import java.security.KeyStore;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CopyableThrowable;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00060\u0001j\u0002`\u00022\b\u0012\u0004\u0012\u00020\u00000\u0003B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\n\u0010\t\u001a\u0004\u0018\u00010\u0000H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lio/ktor/network/tls/NoPrivateKeyException;", "Ljava/lang/IllegalStateException;", "Lkotlin/IllegalStateException;", "Lkotlinx/coroutines/CopyableThrowable;", "alias", "", "store", "Ljava/security/KeyStore;", "(Ljava/lang/String;Ljava/security/KeyStore;)V", "createCopy", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: TLSConfigBuilder.kt */
public final class NoPrivateKeyException extends IllegalStateException implements CopyableThrowable<NoPrivateKeyException> {
    private final String alias;
    private final KeyStore store;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NoPrivateKeyException(String str, KeyStore keyStore) {
        super("Failed to find private key for alias " + str + ". Please check your key store: " + keyStore);
        Intrinsics.checkNotNullParameter(str, "alias");
        Intrinsics.checkNotNullParameter(keyStore, "store");
        this.alias = str;
        this.store = keyStore;
    }

    public NoPrivateKeyException createCopy() {
        NoPrivateKeyException noPrivateKeyException = new NoPrivateKeyException(this.alias, this.store);
        noPrivateKeyException.initCause(this);
        return noPrivateKeyException;
    }
}
