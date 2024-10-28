package org.bouncycastle.oer;

import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import org.bouncycastle.asn1.ASN1Absent;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;

public class OEROptional extends ASN1Object {
    public static final OEROptional ABSENT = new OEROptional(false, (ASN1Encodable) null);
    private final boolean defined;
    /* access modifiers changed from: private */
    public final ASN1Encodable value;

    private OEROptional(boolean z, ASN1Encodable aSN1Encodable) {
        this.defined = z;
        this.value = aSN1Encodable;
    }

    public static OEROptional getInstance(Object obj) {
        return obj instanceof OEROptional ? (OEROptional) obj : obj instanceof ASN1Encodable ? new OEROptional(true, (ASN1Encodable) obj) : ABSENT;
    }

    public static <T> T getValue(Class<T> cls, Object obj) {
        OEROptional instance = getInstance(obj);
        if (!instance.defined) {
            return null;
        }
        return instance.getObject(cls);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        OEROptional oEROptional = (OEROptional) obj;
        if (this.defined != oEROptional.defined) {
            return false;
        }
        ASN1Encodable aSN1Encodable = this.value;
        ASN1Encodable aSN1Encodable2 = oEROptional.value;
        return aSN1Encodable != null ? aSN1Encodable.equals(aSN1Encodable2) : aSN1Encodable2 == null;
    }

    public ASN1Encodable get() {
        return !this.defined ? ABSENT : this.value;
    }

    public <T> T getObject(final Class<T> cls) {
        if (this.defined) {
            return this.value.getClass().isInstance(cls) ? cls.cast(this.value) : AccessController.doPrivileged(new PrivilegedAction<T>() {
                public T run() {
                    try {
                        Method method = cls.getMethod("getInstance", new Class[]{Object.class});
                        return cls.cast(method.invoke((Object) null, new Object[]{OEROptional.this.value}));
                    } catch (Exception e) {
                        throw new IllegalStateException("could not invoke getInstance on type " + e.getMessage(), e);
                    }
                }
            });
        }
        return null;
    }

    public int hashCode() {
        int hashCode = ((super.hashCode() * 31) + (this.defined ? 1 : 0)) * 31;
        ASN1Encodable aSN1Encodable = this.value;
        return hashCode + (aSN1Encodable != null ? aSN1Encodable.hashCode() : 0);
    }

    public boolean isDefined() {
        return this.defined;
    }

    public ASN1Primitive toASN1Primitive() {
        return !this.defined ? ASN1Absent.INSTANCE : get().toASN1Primitive();
    }

    public String toString() {
        if (!this.defined) {
            return "ABSENT";
        }
        return "OPTIONAL(" + this.value + ")";
    }
}
