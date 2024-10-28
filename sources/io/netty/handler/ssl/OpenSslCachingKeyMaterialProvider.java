package io.netty.handler.ssl;

import io.netty.buffer.ByteBufAllocator;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentMap;
import javax.net.ssl.X509KeyManager;

final class OpenSslCachingKeyMaterialProvider extends OpenSslKeyMaterialProvider {
    private final ConcurrentMap<String, OpenSslKeyMaterial> cache = new ConcurrentHashMap();
    private volatile boolean full;
    private final int maxCachedEntries;

    OpenSslCachingKeyMaterialProvider(X509KeyManager x509KeyManager, String str, int i) {
        super(x509KeyManager, str);
        this.maxCachedEntries = i;
    }

    /* access modifiers changed from: package-private */
    public OpenSslKeyMaterial chooseKeyMaterial(ByteBufAllocator byteBufAllocator, String str) throws Exception {
        OpenSslKeyMaterial openSslKeyMaterial = (OpenSslKeyMaterial) this.cache.get(str);
        if (openSslKeyMaterial == null) {
            openSslKeyMaterial = super.chooseKeyMaterial(byteBufAllocator, str);
            if (openSslKeyMaterial == null) {
                return null;
            }
            if (this.full) {
                return openSslKeyMaterial;
            }
            if (this.cache.size() > this.maxCachedEntries) {
                this.full = true;
                return openSslKeyMaterial;
            }
            OpenSslKeyMaterial putIfAbsent = this.cache.putIfAbsent(str, openSslKeyMaterial);
            if (putIfAbsent != null) {
                openSslKeyMaterial.release();
                openSslKeyMaterial = putIfAbsent;
            }
        }
        return openSslKeyMaterial.retain();
    }

    /* access modifiers changed from: package-private */
    public void destroy() {
        do {
            Iterator it = this.cache.values().iterator();
            while (it.hasNext()) {
                ((OpenSslKeyMaterial) it.next()).release();
                it.remove();
            }
        } while (!this.cache.isEmpty());
    }
}
