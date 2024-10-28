package org.bouncycastle.asn1;

abstract class ASN1Type {
    final Class javaClass;

    ASN1Type(Class cls) {
        this.javaClass = cls;
    }

    public final boolean equals(Object obj) {
        return this == obj;
    }

    /* access modifiers changed from: package-private */
    public final Class getJavaClass() {
        return this.javaClass;
    }

    public final int hashCode() {
        return super.hashCode();
    }
}
