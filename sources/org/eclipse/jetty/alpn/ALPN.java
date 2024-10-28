package org.eclipse.jetty.alpn;

import j$.util.concurrent.ConcurrentHashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSocket;

public class ALPN {
    public static boolean debug = false;
    private static Map<Object, Provider> objects = new ConcurrentHashMap();

    public interface ClientProvider extends Provider {
        List<String> protocols();

        void selected(String str) throws SSLException;

        void unsupported();
    }

    public interface Provider {
    }

    public interface ServerProvider extends Provider {
        String select(List<String> list) throws SSLException;

        void unsupported();
    }

    private ALPN() {
    }

    public static void put(SSLSocket sSLSocket, Provider provider) {
        objects.put(sSLSocket, provider);
    }

    public static Provider get(SSLSocket sSLSocket) {
        return objects.get(sSLSocket);
    }

    public static Provider remove(SSLSocket sSLSocket) {
        return objects.remove(sSLSocket);
    }

    public static void put(SSLEngine sSLEngine, Provider provider) {
        objects.put(sSLEngine, provider);
    }

    public static Provider get(SSLEngine sSLEngine) {
        return objects.get(sSLEngine);
    }

    public static Provider remove(SSLEngine sSLEngine) {
        return objects.remove(sSLEngine);
    }
}
