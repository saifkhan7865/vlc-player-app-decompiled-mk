package org.bouncycastle.crypto.kems;

import java.util.concurrent.atomic.AtomicBoolean;
import javax.security.auth.DestroyFailedException;
import org.bouncycastle.crypto.SecretWithEncapsulation;
import org.bouncycastle.util.Arrays;

class SecretWithEncapsulationImpl implements SecretWithEncapsulation {
    private final byte[] cipher_text;
    private final AtomicBoolean hasBeenDestroyed = new AtomicBoolean(false);
    private final byte[] sessionKey;

    public SecretWithEncapsulationImpl(byte[] bArr, byte[] bArr2) {
        this.sessionKey = bArr;
        this.cipher_text = bArr2;
    }

    /* access modifiers changed from: package-private */
    public void checkDestroyed() {
        if (isDestroyed()) {
            throw new IllegalStateException("data has been destroyed");
        }
    }

    public void destroy() throws DestroyFailedException {
        if (!this.hasBeenDestroyed.getAndSet(true)) {
            Arrays.clear(this.sessionKey);
            Arrays.clear(this.cipher_text);
        }
    }

    public byte[] getEncapsulation() {
        byte[] clone = Arrays.clone(this.cipher_text);
        checkDestroyed();
        return clone;
    }

    public byte[] getSecret() {
        byte[] clone = Arrays.clone(this.sessionKey);
        checkDestroyed();
        return clone;
    }

    public boolean isDestroyed() {
        return this.hasBeenDestroyed.get();
    }
}
