package io.netty.handler.ssl;

import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import j$.util.function.BiFunction$CC;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import javax.net.ssl.SSLEngine;

final class BouncyCastleAlpnSslUtils {
    private static final Class BC_APPLICATION_PROTOCOL_SELECTOR;
    /* access modifiers changed from: private */
    public static final Method BC_APPLICATION_PROTOCOL_SELECTOR_SELECT;
    private static final Method GET_APPLICATION_PROTOCOL;
    private static final Method GET_HANDSHAKE_APPLICATION_PROTOCOL;
    private static final Method GET_HANDSHAKE_APPLICATION_PROTOCOL_SELECTOR;
    private static final Method GET_PARAMETERS;
    private static final Method SET_APPLICATION_PROTOCOLS;
    private static final Method SET_HANDSHAKE_APPLICATION_PROTOCOL_SELECTOR;
    private static final Method SET_PARAMETERS;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) BouncyCastleAlpnSslUtils.class);

    static {
        Method method;
        Method method2;
        Method method3;
        Method method4;
        Method method5;
        Method method6;
        final Class<?> cls;
        Method method7;
        Method method8 = null;
        try {
            final Class<?> cls2 = Class.forName("org.bouncycastle.jsse.BCSSLEngine");
            cls = Class.forName("org.bouncycastle.jsse.BCApplicationProtocolSelector");
            method6 = (Method) AccessController.doPrivileged(new PrivilegedExceptionAction<Method>() {
                public Method run() throws Exception {
                    return cls.getMethod("select", new Class[]{Object.class, List.class});
                }
            });
            SSLEngine createSSLEngine = SslUtils.getSSLContext("BCJSSE").createSSLEngine();
            method5 = (Method) AccessController.doPrivileged(new PrivilegedExceptionAction<Method>() {
                public Method run() throws Exception {
                    return cls2.getMethod("getParameters", (Class[]) null);
                }
            });
            Object invoke = method5.invoke(createSSLEngine, (Object[]) null);
            final Class<?> cls3 = invoke.getClass();
            Method method9 = (Method) AccessController.doPrivileged(new PrivilegedExceptionAction<Method>() {
                public Method run() throws Exception {
                    return cls2.getMethod("setParameters", new Class[]{cls3});
                }
            });
            method9.invoke(createSSLEngine, new Object[]{invoke});
            method3 = (Method) AccessController.doPrivileged(new PrivilegedExceptionAction<Method>() {
                public Method run() throws Exception {
                    return cls3.getMethod("setApplicationProtocols", new Class[]{String[].class});
                }
            });
            method3.invoke(invoke, new Object[]{EmptyArrays.EMPTY_STRINGS});
            method4 = (Method) AccessController.doPrivileged(new PrivilegedExceptionAction<Method>() {
                public Method run() throws Exception {
                    return cls2.getMethod("getApplicationProtocol", (Class[]) null);
                }
            });
            method4.invoke(createSSLEngine, (Object[]) null);
            method2 = (Method) AccessController.doPrivileged(new PrivilegedExceptionAction<Method>() {
                public Method run() throws Exception {
                    return cls2.getMethod("getHandshakeApplicationProtocol", (Class[]) null);
                }
            });
            method2.invoke(createSSLEngine, (Object[]) null);
            method = (Method) AccessController.doPrivileged(new PrivilegedExceptionAction<Method>() {
                public Method run() throws Exception {
                    return cls2.getMethod("setBCHandshakeApplicationProtocolSelector", new Class[]{cls});
                }
            });
            method7 = (Method) AccessController.doPrivileged(new PrivilegedExceptionAction<Method>() {
                public Method run() throws Exception {
                    return cls2.getMethod("getBCHandshakeApplicationProtocolSelector", (Class[]) null);
                }
            });
            method7.invoke(createSSLEngine, (Object[]) null);
            method8 = method9;
        } catch (Throwable th) {
            logger.error("Unable to initialize BouncyCastleAlpnSslUtils.", th);
            method7 = null;
            cls = null;
            method6 = null;
            method5 = null;
            method4 = null;
            method3 = null;
            method2 = null;
            method = null;
        }
        SET_PARAMETERS = method8;
        GET_PARAMETERS = method5;
        SET_APPLICATION_PROTOCOLS = method3;
        GET_APPLICATION_PROTOCOL = method4;
        GET_HANDSHAKE_APPLICATION_PROTOCOL = method2;
        SET_HANDSHAKE_APPLICATION_PROTOCOL_SELECTOR = method;
        GET_HANDSHAKE_APPLICATION_PROTOCOL_SELECTOR = method7;
        BC_APPLICATION_PROTOCOL_SELECTOR_SELECT = method6;
        BC_APPLICATION_PROTOCOL_SELECTOR = cls;
    }

    private BouncyCastleAlpnSslUtils() {
    }

    static String getApplicationProtocol(SSLEngine sSLEngine) {
        try {
            return (String) GET_APPLICATION_PROTOCOL.invoke(sSLEngine, (Object[]) null);
        } catch (UnsupportedOperationException e) {
            throw e;
        } catch (Exception e2) {
            throw new IllegalStateException(e2);
        }
    }

    static void setApplicationProtocols(SSLEngine sSLEngine, List<String> list) {
        Object obj = (String[]) list.toArray(EmptyArrays.EMPTY_STRINGS);
        try {
            Object invoke = GET_PARAMETERS.invoke(sSLEngine, (Object[]) null);
            SET_APPLICATION_PROTOCOLS.invoke(invoke, new Object[]{obj});
            SET_PARAMETERS.invoke(sSLEngine, new Object[]{invoke});
            if (PlatformDependent.javaVersion() >= 9) {
                JdkAlpnSslUtils.setApplicationProtocols(sSLEngine, list);
            }
        } catch (UnsupportedOperationException e) {
            throw e;
        } catch (Exception e2) {
            throw new IllegalStateException(e2);
        }
    }

    static String getHandshakeApplicationProtocol(SSLEngine sSLEngine) {
        try {
            return (String) GET_HANDSHAKE_APPLICATION_PROTOCOL.invoke(sSLEngine, (Object[]) null);
        } catch (UnsupportedOperationException e) {
            throw e;
        } catch (Exception e2) {
            throw new IllegalStateException(e2);
        }
    }

    static void setHandshakeApplicationProtocolSelector(SSLEngine sSLEngine, final BiFunction<SSLEngine, List<String>, String> biFunction) {
        try {
            Object newProxyInstance = Proxy.newProxyInstance(BouncyCastleAlpnSslUtils.class.getClassLoader(), new Class[]{BC_APPLICATION_PROTOCOL_SELECTOR}, new InvocationHandler() {
                public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
                    if (method.getName().equals("select")) {
                        try {
                            return biFunction.apply(objArr[0], objArr[1]);
                        } catch (ClassCastException e) {
                            throw new RuntimeException("BCApplicationProtocolSelector select method parameter of invalid type.", e);
                        }
                    } else {
                        throw new UnsupportedOperationException(String.format("Method '%s' not supported.", new Object[]{method.getName()}));
                    }
                }
            });
            SET_HANDSHAKE_APPLICATION_PROTOCOL_SELECTOR.invoke(sSLEngine, new Object[]{newProxyInstance});
        } catch (UnsupportedOperationException e) {
            throw e;
        } catch (Exception e2) {
            throw new IllegalStateException(e2);
        }
    }

    static BiFunction<SSLEngine, List<String>, String> getHandshakeApplicationProtocolSelector(SSLEngine sSLEngine) {
        try {
            final Object invoke = GET_HANDSHAKE_APPLICATION_PROTOCOL_SELECTOR.invoke(sSLEngine, (Object[]) null);
            return new BiFunction<SSLEngine, List<String>, String>() {
                public /* synthetic */ BiFunction andThen(Function function) {
                    return BiFunction$CC.$default$andThen(this, function);
                }

                public String apply(SSLEngine sSLEngine, List<String> list) {
                    try {
                        return (String) BouncyCastleAlpnSslUtils.BC_APPLICATION_PROTOCOL_SELECTOR_SELECT.invoke(invoke, new Object[]{sSLEngine, list});
                    } catch (Exception e) {
                        throw new RuntimeException("Could not call getHandshakeApplicationProtocolSelector", e);
                    }
                }
            };
        } catch (UnsupportedOperationException e) {
            throw e;
        } catch (Exception e2) {
            throw new IllegalStateException(e2);
        }
    }
}
