package io.ktor.network.tls;

import io.ktor.network.tls.extensions.HashAndSign;
import io.ktor.network.tls.extensions.SignatureAlgorithmKt;
import io.ktor.network.tls.extensions.TLSExtension;
import io.ktor.network.tls.extensions.TLSExtensionType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0002\u0010\rR\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u0006\u001e"}, d2 = {"Lio/ktor/network/tls/TLSServerHello;", "", "version", "Lio/ktor/network/tls/TLSVersion;", "serverSeed", "", "sessionId", "suite", "", "compressionMethod", "extensions", "", "Lio/ktor/network/tls/extensions/TLSExtension;", "(Lio/ktor/network/tls/TLSVersion;[B[BSSLjava/util/List;)V", "cipherSuite", "Lio/ktor/network/tls/CipherSuite;", "getCipherSuite", "()Lio/ktor/network/tls/CipherSuite;", "getCompressionMethod", "()S", "getExtensions", "()Ljava/util/List;", "hashAndSignAlgorithms", "Lio/ktor/network/tls/extensions/HashAndSign;", "getHashAndSignAlgorithms", "getServerSeed", "()[B", "getSessionId", "getVersion", "()Lio/ktor/network/tls/TLSVersion;", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: TLSHandshakeType.kt */
public final class TLSServerHello {
    private final CipherSuite cipherSuite;
    private final short compressionMethod;
    private final List<TLSExtension> extensions;
    private final List<HashAndSign> hashAndSignAlgorithms;
    private final byte[] serverSeed;
    private final byte[] sessionId;
    private final TLSVersion version;

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: TLSHandshakeType.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TLSExtensionType.values().length];
            try {
                iArr[TLSExtensionType.SIGNATURE_ALGORITHMS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public TLSServerHello(TLSVersion tLSVersion, byte[] bArr, byte[] bArr2, short s, short s2, List<TLSExtension> list) {
        Object obj;
        Intrinsics.checkNotNullParameter(tLSVersion, "version");
        Intrinsics.checkNotNullParameter(bArr, "serverSeed");
        Intrinsics.checkNotNullParameter(bArr2, "sessionId");
        Intrinsics.checkNotNullParameter(list, "extensions");
        this.version = tLSVersion;
        this.serverSeed = bArr;
        this.sessionId = bArr2;
        this.compressionMethod = s2;
        this.extensions = list;
        Iterator it = CIOCipherSuites.INSTANCE.getSupportedSuites().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (((CipherSuite) obj).getCode() == s) {
                break;
            }
        }
        CipherSuite cipherSuite2 = (CipherSuite) obj;
        if (cipherSuite2 != null) {
            this.cipherSuite = cipherSuite2;
            List<HashAndSign> arrayList = new ArrayList<>();
            for (TLSExtension tLSExtension : this.extensions) {
                if (WhenMappings.$EnumSwitchMapping$0[tLSExtension.getType().ordinal()] == 1) {
                    CollectionsKt.addAll(arrayList, SignatureAlgorithmKt.parseSignatureAlgorithms(tLSExtension.getPacket()));
                }
            }
            this.hashAndSignAlgorithms = arrayList;
            return;
        }
        throw new IllegalStateException(("Server cipher suite is not supported: " + s).toString());
    }

    public final TLSVersion getVersion() {
        return this.version;
    }

    public final byte[] getServerSeed() {
        return this.serverSeed;
    }

    public final byte[] getSessionId() {
        return this.sessionId;
    }

    public final short getCompressionMethod() {
        return this.compressionMethod;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ TLSServerHello(TLSVersion tLSVersion, byte[] bArr, byte[] bArr2, short s, short s2, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(tLSVersion, bArr, bArr2, s, s2, (i & 32) != 0 ? CollectionsKt.emptyList() : list);
    }

    public final List<TLSExtension> getExtensions() {
        return this.extensions;
    }

    public final CipherSuite getCipherSuite() {
        return this.cipherSuite;
    }

    public final List<HashAndSign> getHashAndSignAlgorithms() {
        return this.hashAndSignAlgorithms;
    }
}
