package org.bouncycastle.asn1;

final class ASN1Tag {
    private final int tagClass;
    private final int tagNumber;

    private ASN1Tag(int i, int i2) {
        this.tagClass = i;
        this.tagNumber = i2;
    }

    static ASN1Tag create(int i, int i2) {
        return new ASN1Tag(i, i2);
    }

    /* access modifiers changed from: package-private */
    public int getTagClass() {
        return this.tagClass;
    }

    /* access modifiers changed from: package-private */
    public int getTagNumber() {
        return this.tagNumber;
    }
}
