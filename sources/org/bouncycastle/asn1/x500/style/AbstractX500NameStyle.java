package org.bouncycastle.asn1.x500.style;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1ParsingException;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.asn1.x500.AttributeTypeAndValue;
import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameStyle;

public abstract class AbstractX500NameStyle implements X500NameStyle {
    private int calcHashCode(ASN1Encodable aSN1Encodable) {
        return IETFUtils.canonicalString(aSN1Encodable).hashCode();
    }

    public static Hashtable copyHashTable(Hashtable hashtable) {
        Hashtable hashtable2 = new Hashtable();
        Enumeration keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            Object nextElement = keys.nextElement();
            hashtable2.put(nextElement, hashtable.get(nextElement));
        }
        return hashtable2;
    }

    private boolean foundMatch(boolean z, RDN rdn, RDN[] rdnArr) {
        if (z) {
            int length = rdnArr.length - 1;
            while (length >= 0) {
                RDN rdn2 = rdnArr[length];
                if (rdn2 == null || !rdnAreEqual(rdn, rdn2)) {
                    length--;
                } else {
                    rdnArr[length] = null;
                    return true;
                }
            }
        } else {
            int i = 0;
            while (i != rdnArr.length) {
                RDN rdn3 = rdnArr[i];
                if (rdn3 == null || !rdnAreEqual(rdn, rdn3)) {
                    i++;
                } else {
                    rdnArr[i] = null;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean areEqual(X500Name x500Name, X500Name x500Name2) {
        if (x500Name.size() != x500Name2.size()) {
            return false;
        }
        RDN[] rDNs = x500Name.getRDNs();
        RDN[] rDNs2 = x500Name2.getRDNs();
        AttributeTypeAndValue first = rDNs[0].getFirst();
        AttributeTypeAndValue first2 = rDNs2[0].getFirst();
        boolean z = (first == null || first2 == null) ? false : !first.getType().equals((ASN1Primitive) first2.getType());
        for (int i = 0; i != rDNs.length; i++) {
            if (!foundMatch(z, rDNs[i], rDNs2)) {
                return false;
            }
        }
        return true;
    }

    public int calculateHashCode(X500Name x500Name) {
        RDN[] rDNs = x500Name.getRDNs();
        int i = 0;
        for (int i2 = 0; i2 != rDNs.length; i2++) {
            if (rDNs[i2].isMultiValued()) {
                AttributeTypeAndValue[] typesAndValues = rDNs[i2].getTypesAndValues();
                for (int i3 = 0; i3 != typesAndValues.length; i3++) {
                    i = (i ^ typesAndValues[i3].getType().hashCode()) ^ calcHashCode(typesAndValues[i3].getValue());
                }
            } else {
                i = (i ^ rDNs[i2].getFirst().getType().hashCode()) ^ calcHashCode(rDNs[i2].getFirst().getValue());
            }
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public ASN1Encodable encodeStringValue(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        return new DERUTF8String(str);
    }

    /* access modifiers changed from: protected */
    public boolean rdnAreEqual(RDN rdn, RDN rdn2) {
        return IETFUtils.rDNAreEqual(rdn, rdn2);
    }

    public ASN1Encodable stringToValue(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        if (str.length() == 0 || str.charAt(0) != '#') {
            if (str.length() != 0 && str.charAt(0) == '\\') {
                str = str.substring(1);
            }
            return encodeStringValue(aSN1ObjectIdentifier, str);
        }
        try {
            return IETFUtils.valueFromHexString(str, 1);
        } catch (IOException unused) {
            throw new ASN1ParsingException("can't recode value for oid " + aSN1ObjectIdentifier.getId());
        }
    }
}
