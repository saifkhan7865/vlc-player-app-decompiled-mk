package org.bouncycastle.oer.its.ieee1609dot2;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.oer.Element;
import org.bouncycastle.oer.OERInputStream;
import org.bouncycastle.util.Arrays;

public class Opaque extends ASN1Object {
    /* access modifiers changed from: private */
    public final byte[] content;

    private Opaque(ASN1OctetString aSN1OctetString) {
        this(aSN1OctetString.getOctets());
    }

    public Opaque(byte[] bArr) {
        this.content = Arrays.clone(bArr);
    }

    public static Opaque getInstance(Object obj) {
        if (obj instanceof Opaque) {
            return (Opaque) obj;
        }
        if (obj != null) {
            return new Opaque(ASN1OctetString.getInstance(obj));
        }
        return null;
    }

    public static <T> T getValue(final Class<T> cls, final Element element, Opaque opaque) {
        return AccessController.doPrivileged(new PrivilegedAction<T>(opaque) {
            final /* synthetic */ Opaque val$src;

            {
                this.val$src = r1;
            }

            public T run() {
                try {
                    ASN1Encodable parse = OERInputStream.parse(this.val$src.content, element);
                    Method method = cls.getMethod("getInstance", new Class[]{Object.class});
                    return cls.cast(method.invoke((Object) null, new Object[]{parse}));
                } catch (Exception e) {
                    throw new IllegalStateException("could not invoke getInstance on type " + e.getMessage(), e);
                }
            }
        });
    }

    public byte[] getContent() {
        return this.content;
    }

    public InputStream getInputStream() {
        return new ByteArrayInputStream(this.content);
    }

    public ASN1Primitive toASN1Primitive() {
        return new DEROctetString(this.content);
    }
}
