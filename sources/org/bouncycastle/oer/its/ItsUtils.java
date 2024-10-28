package org.bouncycastle.oer.its;

import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.util.Arrays;

public class ItsUtils {
    @Deprecated
    public static <T> List<T> fillList(final Class<T> cls, final ASN1Sequence aSN1Sequence) {
        return (List) AccessController.doPrivileged(new PrivilegedAction<List<T>>() {
            public List<T> run() {
                try {
                    ArrayList arrayList = new ArrayList();
                    Iterator<ASN1Encodable> it = aSN1Sequence.iterator();
                    while (it.hasNext()) {
                        Method method = cls.getMethod("getInstance", new Class[]{Object.class});
                        arrayList.add(cls.cast(method.invoke((Object) null, new Object[]{it.next()})));
                    }
                    return arrayList;
                } catch (Exception e) {
                    throw new IllegalStateException("could not invoke getInstance on type " + e.getMessage(), e);
                }
            }
        });
    }

    public static byte[] octetStringFixed(byte[] bArr) {
        if (bArr.length >= 1 && bArr.length <= 32) {
            return Arrays.clone(bArr);
        }
        throw new IllegalArgumentException("octet string out of range");
    }

    public static byte[] octetStringFixed(byte[] bArr, int i) {
        if (bArr.length == i) {
            return bArr;
        }
        throw new IllegalArgumentException("octet string out of range");
    }

    public static ASN1Sequence toSequence(List list) {
        return new DERSequence((ASN1Encodable[]) list.toArray(new ASN1Encodable[0]));
    }

    public static ASN1Sequence toSequence(ASN1Encodable... aSN1EncodableArr) {
        return new DERSequence(aSN1EncodableArr);
    }
}
