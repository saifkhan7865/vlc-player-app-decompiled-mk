package io.netty.handler.ssl;

import io.netty.util.AbstractConstant;
import io.netty.util.ConstantPool;
import io.netty.util.internal.ObjectUtil;

public class SslContextOption<T> extends AbstractConstant<SslContextOption<T>> {
    private static final ConstantPool<SslContextOption<Object>> pool = new ConstantPool<SslContextOption<Object>>() {
        /* access modifiers changed from: protected */
        public SslContextOption<Object> newConstant(int i, String str) {
            return new SslContextOption<>(i, str);
        }
    };

    public static <T> SslContextOption<T> valueOf(String str) {
        return pool.valueOf(str);
    }

    public static <T> SslContextOption<T> valueOf(Class<?> cls, String str) {
        return pool.valueOf(cls, str);
    }

    public static boolean exists(String str) {
        return pool.exists(str);
    }

    private SslContextOption(int i, String str) {
        super(i, str);
    }

    protected SslContextOption(String str) {
        this(pool.nextId(), str);
    }

    public void validate(T t) {
        ObjectUtil.checkNotNull(t, "value");
    }
}
