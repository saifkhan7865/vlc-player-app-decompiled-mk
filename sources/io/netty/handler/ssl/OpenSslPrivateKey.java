package io.netty.handler.ssl;

import androidx.core.os.EnvironmentCompat;
import io.netty.internal.tcnative.SSL;
import io.netty.util.AbstractReferenceCounted;
import io.netty.util.IllegalReferenceCountException;
import io.netty.util.internal.EmptyArrays;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

final class OpenSslPrivateKey extends AbstractReferenceCounted implements PrivateKey {
    private long privateKeyAddress;

    public byte[] getEncoded() {
        return null;
    }

    public String getFormat() {
        return null;
    }

    public OpenSslPrivateKey touch(Object obj) {
        return this;
    }

    OpenSslPrivateKey(long j) {
        this.privateKeyAddress = j;
    }

    public String getAlgorithm() {
        return EnvironmentCompat.MEDIA_UNKNOWN;
    }

    /* access modifiers changed from: private */
    public long privateKeyAddress() {
        if (refCnt() > 0) {
            return this.privateKeyAddress;
        }
        throw new IllegalReferenceCountException();
    }

    /* access modifiers changed from: protected */
    public void deallocate() {
        SSL.freePrivateKey(this.privateKeyAddress);
        this.privateKeyAddress = 0;
    }

    public OpenSslPrivateKey retain() {
        super.retain();
        return this;
    }

    public OpenSslPrivateKey retain(int i) {
        super.retain(i);
        return this;
    }

    public OpenSslPrivateKey touch() {
        super.touch();
        return this;
    }

    public void destroy() {
        release(refCnt());
    }

    public boolean isDestroyed() {
        return refCnt() == 0;
    }

    /* access modifiers changed from: package-private */
    public OpenSslKeyMaterial newKeyMaterial(long j, X509Certificate[] x509CertificateArr) {
        return new OpenSslPrivateKeyMaterial(j, x509CertificateArr);
    }

    final class OpenSslPrivateKeyMaterial extends AbstractReferenceCounted implements OpenSslKeyMaterial {
        long certificateChain;
        private final X509Certificate[] x509CertificateChain;

        OpenSslPrivateKeyMaterial(long j, X509Certificate[] x509CertificateArr) {
            this.certificateChain = j;
            this.x509CertificateChain = x509CertificateArr == null ? EmptyArrays.EMPTY_X509_CERTIFICATES : x509CertificateArr;
            OpenSslPrivateKey.this.retain();
        }

        public X509Certificate[] certificateChain() {
            return (X509Certificate[]) this.x509CertificateChain.clone();
        }

        public long certificateChainAddress() {
            if (refCnt() > 0) {
                return this.certificateChain;
            }
            throw new IllegalReferenceCountException();
        }

        public long privateKeyAddress() {
            if (refCnt() > 0) {
                return OpenSslPrivateKey.this.privateKeyAddress();
            }
            throw new IllegalReferenceCountException();
        }

        public OpenSslKeyMaterial touch(Object obj) {
            OpenSslPrivateKey.this.touch(obj);
            return this;
        }

        public OpenSslKeyMaterial retain() {
            super.retain();
            return this;
        }

        public OpenSslKeyMaterial retain(int i) {
            super.retain(i);
            return this;
        }

        public OpenSslKeyMaterial touch() {
            OpenSslPrivateKey.this.touch();
            return this;
        }

        /* access modifiers changed from: protected */
        public void deallocate() {
            releaseChain();
            OpenSslPrivateKey.this.release();
        }

        private void releaseChain() {
            SSL.freeX509Chain(this.certificateChain);
            this.certificateChain = 0;
        }
    }
}
