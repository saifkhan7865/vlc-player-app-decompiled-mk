package io.ktor.network.tls;

import io.ktor.http.IpParserKt;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\u001a\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0000\u001a\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0000\u001a\u0018\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0000\u001a\u0012\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u000f*\u00020\u000bH\u0002\u001a\u0012\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u000f*\u00020\u000bH\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"DNS_NAME_TYPE", "", "IP_ADDRESS_TYPE", "matchHostnameWithCertificate", "", "serverName", "", "certificateHost", "verifyHostnameInCertificate", "", "certificate", "Ljava/security/cert/X509Certificate;", "verifyIpInCertificate", "ipString", "hosts", "", "ips", "ktor-network-tls"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: HostnameUtils.kt */
public final class HostnameUtilsKt {
    private static final int DNS_NAME_TYPE = 2;
    private static final int IP_ADDRESS_TYPE = 7;

    public static final void verifyHostnameInCertificate(String str, X509Certificate x509Certificate) {
        Intrinsics.checkNotNullParameter(str, "serverName");
        Intrinsics.checkNotNullParameter(x509Certificate, "certificate");
        if (IpParserKt.hostIsIp(str)) {
            verifyIpInCertificate(str, x509Certificate);
            return;
        }
        Iterable<String> hosts = hosts(x509Certificate);
        if (!(hosts instanceof Collection) || !((Collection) hosts).isEmpty()) {
            for (String matchHostnameWithCertificate : hosts) {
                if (matchHostnameWithCertificate(str, matchHostnameWithCertificate)) {
                    return;
                }
            }
        }
        throw new TLSException("No server host: " + str + " in the server certificate. Provided in certificate: " + CollectionsKt.joinToString$default(hosts, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 63, (Object) null), (Throwable) null, 2, (DefaultConstructorMarker) null);
    }

    public static final void verifyIpInCertificate(String str, X509Certificate x509Certificate) {
        Intrinsics.checkNotNullParameter(str, "ipString");
        Intrinsics.checkNotNullParameter(x509Certificate, "certificate");
        Collection<List<?>> subjectAlternativeNames = x509Certificate.getSubjectAlternativeNames();
        Intrinsics.checkNotNullExpressionValue(subjectAlternativeNames, "certificate.subjectAlternativeNames");
        Collection arrayList = new ArrayList();
        for (Object next : subjectAlternativeNames) {
            Object obj = ((List) next).get(0);
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Int");
            if (((Integer) obj).intValue() == 7) {
                arrayList.add(next);
            }
        }
        Iterable<List> iterable = (List) arrayList;
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (List list : iterable) {
            Object obj2 = list.get(1);
            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.String");
            arrayList2.add((String) obj2);
        }
        Iterable<String> iterable2 = (List) arrayList2;
        if (!(iterable2 instanceof Collection) || !((Collection) iterable2).isEmpty()) {
            for (String areEqual : iterable2) {
                if (Intrinsics.areEqual((Object) areEqual, (Object) str)) {
                    return;
                }
            }
        }
        throw new TLSException("No server host: " + str + " in the server certificate. The certificate was issued for: " + CollectionsKt.joinToString$default(iterable2, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 63, (Object) null) + '.', (Throwable) null, 2, (DefaultConstructorMarker) null);
    }

    public static final boolean matchHostnameWithCertificate(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "serverName");
        Intrinsics.checkNotNullParameter(str2, "certificateHost");
        if (StringsKt.equals(str, str2, true)) {
            return true;
        }
        List asReversed = CollectionsKt.asReversed(StringsKt.split$default((CharSequence) str, new char[]{'.'}, false, 0, 6, (Object) null));
        List asReversed2 = CollectionsKt.asReversed(StringsKt.split$default((CharSequence) str2, new char[]{'.'}, false, 0, 6, (Object) null));
        int i = 0;
        int i2 = 0;
        boolean z = false;
        int i3 = 0;
        while (i < asReversed.size() && i2 < asReversed2.size()) {
            String str3 = (String) asReversed.get(i);
            if (i == 0 && str3.length() == 0) {
                i++;
            } else {
                String str4 = (String) asReversed2.get(i2);
                if (!(i2 == 0 && str4.length() == 0)) {
                    if (!z && StringsKt.equals(str3, str4, true)) {
                        i3++;
                        i++;
                    } else if (!Intrinsics.areEqual((Object) str4, (Object) "*")) {
                        return false;
                    } else {
                        i++;
                        i2++;
                        z = true;
                    }
                }
                i2++;
            }
        }
        boolean z2 = !z || i3 >= 2;
        if (i == asReversed.size() && i2 == asReversed2.size() && z2) {
            return true;
        }
        return false;
    }

    private static final List<String> hosts(X509Certificate x509Certificate) {
        Collection<List<?>> subjectAlternativeNames = x509Certificate.getSubjectAlternativeNames();
        Intrinsics.checkNotNullExpressionValue(subjectAlternativeNames, "subjectAlternativeNames");
        Collection arrayList = new ArrayList();
        for (Object next : subjectAlternativeNames) {
            Object obj = ((List) next).get(0);
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Int");
            if (((Integer) obj).intValue() == 2) {
                arrayList.add(next);
            }
        }
        Iterable<List> iterable = (List) arrayList;
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (List list : iterable) {
            Object obj2 = list.get(1);
            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.String");
            arrayList2.add((String) obj2);
        }
        return (List) arrayList2;
    }

    private static final List<String> ips(X509Certificate x509Certificate) {
        Collection<List<?>> subjectAlternativeNames = x509Certificate.getSubjectAlternativeNames();
        Intrinsics.checkNotNullExpressionValue(subjectAlternativeNames, "subjectAlternativeNames");
        Collection arrayList = new ArrayList();
        for (Object next : subjectAlternativeNames) {
            Object obj = ((List) next).get(0);
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Int");
            if (((Integer) obj).intValue() == 7) {
                arrayList.add(next);
            }
        }
        Iterable<List> iterable = (List) arrayList;
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (List list : iterable) {
            Object obj2 = list.get(1);
            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.String");
            arrayList2.add((String) obj2);
        }
        return (List) arrayList2;
    }
}
