package org.bouncycastle.asn1.x509.qualified;

import androidx.room.RoomDatabase;
import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1PrintableString;
import org.bouncycastle.asn1.DERPrintableString;

public class Iso4217CurrencyCode extends ASN1Object implements ASN1Choice {
    final int ALPHABETIC_MAXSIZE = 3;
    final int NUMERIC_MAXSIZE = RoomDatabase.MAX_BIND_PARAMETER_CNT;
    final int NUMERIC_MINSIZE = 1;
    int numeric;
    ASN1Encodable obj;

    public Iso4217CurrencyCode(int i) {
        if (i > 999 || i < 1) {
            throw new IllegalArgumentException("wrong size in numeric code : not in (1..999)");
        }
        this.obj = new ASN1Integer((long) i);
    }

    public Iso4217CurrencyCode(String str) {
        if (str.length() <= 3) {
            this.obj = new DERPrintableString(str);
            return;
        }
        throw new IllegalArgumentException("wrong size in alphabetic code : max size is 3");
    }

    public static Iso4217CurrencyCode getInstance(Object obj2) {
        if (obj2 == null || (obj2 instanceof Iso4217CurrencyCode)) {
            return (Iso4217CurrencyCode) obj2;
        }
        if (obj2 instanceof ASN1Integer) {
            return new Iso4217CurrencyCode(ASN1Integer.getInstance(obj2).intValueExact());
        }
        if (obj2 instanceof ASN1PrintableString) {
            return new Iso4217CurrencyCode(ASN1PrintableString.getInstance(obj2).getString());
        }
        throw new IllegalArgumentException("unknown object in getInstance");
    }

    public String getAlphabetic() {
        return ((ASN1PrintableString) this.obj).getString();
    }

    public int getNumeric() {
        return ((ASN1Integer) this.obj).intValueExact();
    }

    public boolean isAlphabetic() {
        return this.obj instanceof ASN1PrintableString;
    }

    public ASN1Primitive toASN1Primitive() {
        return this.obj.toASN1Primitive();
    }
}
