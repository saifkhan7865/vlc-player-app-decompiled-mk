package okhttp3;

import java.security.Principal;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.net.ssl.SSLPeerUnverifiedException;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt;
import okhttp3.HttpUrl;
import okhttp3.internal.tls.CertificateChainCleaner;
import okio.ByteString;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u0000 !2\u00020\u0001:\u0003 !\"B\u001f\b\u0000\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J)\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000fH\u0000¢\u0006\u0002\b\u0012J)\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u0013\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00150\u0014\"\u00020\u0015H\u0007¢\u0006\u0002\u0010\u0016J\u001c\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0010J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u0002J\u001b\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00040\u00102\u0006\u0010\f\u001a\u00020\rH\u0000¢\u0006\u0002\b\u001bJ\b\u0010\u001c\u001a\u00020\u001dH\u0016J\u0017\u0010\u001e\u001a\u00020\u00002\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0000¢\u0006\u0002\b\u001fR\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lokhttp3/CertificatePinner;", "", "pins", "", "Lokhttp3/CertificatePinner$Pin;", "certificateChainCleaner", "Lokhttp3/internal/tls/CertificateChainCleaner;", "(Ljava/util/Set;Lokhttp3/internal/tls/CertificateChainCleaner;)V", "getCertificateChainCleaner$okhttp", "()Lokhttp3/internal/tls/CertificateChainCleaner;", "check", "", "hostname", "", "cleanedPeerCertificatesFn", "Lkotlin/Function0;", "", "Ljava/security/cert/X509Certificate;", "check$okhttp", "peerCertificates", "", "Ljava/security/cert/Certificate;", "(Ljava/lang/String;[Ljava/security/cert/Certificate;)V", "equals", "", "other", "findMatchingPins", "findMatchingPins$okhttp", "hashCode", "", "withCertificateChainCleaner", "withCertificateChainCleaner$okhttp", "Builder", "Companion", "Pin", "okhttp"}, k = 1, mv = {1, 1, 15})
/* compiled from: CertificatePinner.kt */
public final class CertificatePinner {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final CertificatePinner DEFAULT = new Builder().build();
    public static final String WILDCARD = "*.";
    private final CertificateChainCleaner certificateChainCleaner;
    private final Set<Pin> pins;

    @JvmStatic
    public static final String pin(Certificate certificate) {
        return Companion.pin(certificate);
    }

    public CertificatePinner(Set<Pin> set, CertificateChainCleaner certificateChainCleaner2) {
        Intrinsics.checkParameterIsNotNull(set, "pins");
        this.pins = set;
        this.certificateChainCleaner = certificateChainCleaner2;
    }

    public final CertificateChainCleaner getCertificateChainCleaner$okhttp() {
        return this.certificateChainCleaner;
    }

    public final void check(String str, List<? extends Certificate> list) throws SSLPeerUnverifiedException {
        Intrinsics.checkParameterIsNotNull(str, "hostname");
        Intrinsics.checkParameterIsNotNull(list, "peerCertificates");
        check$okhttp(str, new CertificatePinner$check$1(this, list, str));
    }

    public final void check$okhttp(String str, Function0<? extends List<? extends X509Certificate>> function0) {
        Pin next;
        Intrinsics.checkParameterIsNotNull(str, "hostname");
        Intrinsics.checkParameterIsNotNull(function0, "cleanedPeerCertificatesFn");
        List<Pin> findMatchingPins$okhttp = findMatchingPins$okhttp(str);
        if (!findMatchingPins$okhttp.isEmpty()) {
            List<X509Certificate> list = (List) function0.invoke();
            loop0:
            for (X509Certificate x509Certificate : list) {
                ByteString byteString = null;
                ByteString byteString2 = null;
                Iterator<Pin> it = findMatchingPins$okhttp.iterator();
                ByteString byteString3 = null;
                while (true) {
                    if (it.hasNext()) {
                        next = it.next();
                        String hashAlgorithm = next.getHashAlgorithm();
                        int hashCode = hashAlgorithm.hashCode();
                        if (hashCode == 109397962) {
                            if (!hashAlgorithm.equals("sha1/")) {
                                break loop0;
                            }
                            if (byteString == null) {
                                byteString = Companion.toSha1ByteString$okhttp(x509Certificate);
                            }
                            if (Intrinsics.areEqual((Object) next.getHash(), (Object) byteString)) {
                                return;
                            }
                        } else if (hashCode != 2052263656 || !hashAlgorithm.equals("sha256/")) {
                            break loop0;
                        } else {
                            if (byteString3 == null) {
                                byteString3 = Companion.toSha256ByteString$okhttp(x509Certificate);
                            }
                            if (Intrinsics.areEqual((Object) next.getHash(), (Object) byteString3)) {
                                return;
                            }
                        }
                    }
                }
                throw new AssertionError("unsupported hashAlgorithm: " + next.getHashAlgorithm());
            }
            StringBuilder sb = new StringBuilder("Certificate pinning failure!\n  Peer certificate chain:");
            for (X509Certificate x509Certificate2 : list) {
                if (x509Certificate2 != null) {
                    sb.append("\n    ");
                    sb.append(Companion.pin(x509Certificate2));
                    sb.append(": ");
                    Principal subjectDN = x509Certificate2.getSubjectDN();
                    Intrinsics.checkExpressionValueIsNotNull(subjectDN, "x509Certificate.subjectDN");
                    sb.append(subjectDN.getName());
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type java.security.cert.X509Certificate");
                }
            }
            sb.append("\n  Pinned certificates for ");
            sb.append(str);
            sb.append(":");
            for (Pin append : findMatchingPins$okhttp) {
                sb.append("\n    ");
                sb.append(append);
            }
            String sb2 = sb.toString();
            Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
            throw new SSLPeerUnverifiedException(sb2);
        }
    }

    @Deprecated(message = "replaced with {@link #check(String, List)}.", replaceWith = @ReplaceWith(expression = "check(hostname, peerCertificates.toList())", imports = {}))
    public final void check(String str, Certificate... certificateArr) throws SSLPeerUnverifiedException {
        Intrinsics.checkParameterIsNotNull(str, "hostname");
        Intrinsics.checkParameterIsNotNull(certificateArr, "peerCertificates");
        check(str, (List<? extends Certificate>) ArraysKt.toList((T[]) certificateArr));
    }

    public final List<Pin> findMatchingPins$okhttp(String str) {
        Intrinsics.checkParameterIsNotNull(str, "hostname");
        List<Pin> emptyList = CollectionsKt.emptyList();
        for (Pin next : this.pins) {
            if (next.matches(str)) {
                if (emptyList.isEmpty()) {
                    emptyList = new ArrayList<>();
                }
                if (emptyList != null) {
                    TypeIntrinsics.asMutableList(emptyList).add(next);
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableList<okhttp3.CertificatePinner.Pin>");
                }
            }
        }
        return emptyList;
    }

    public final CertificatePinner withCertificateChainCleaner$okhttp(CertificateChainCleaner certificateChainCleaner2) {
        if (Intrinsics.areEqual((Object) this.certificateChainCleaner, (Object) certificateChainCleaner2)) {
            return this;
        }
        return new CertificatePinner(this.pins, certificateChainCleaner2);
    }

    public boolean equals(Object obj) {
        if (obj instanceof CertificatePinner) {
            CertificatePinner certificatePinner = (CertificatePinner) obj;
            return Intrinsics.areEqual((Object) certificatePinner.pins, (Object) this.pins) && Intrinsics.areEqual((Object) certificatePinner.certificateChainCleaner, (Object) this.certificateChainCleaner);
        }
    }

    public int hashCode() {
        int hashCode = (1517 + this.pins.hashCode()) * 41;
        CertificateChainCleaner certificateChainCleaner2 = this.certificateChainCleaner;
        return hashCode + (certificateChainCleaner2 != null ? certificateChainCleaner2.hashCode() : 0);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÂ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J1\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\u000e\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u0003J\b\u0010\u001a\u001a\u00020\u0003H\u0016R\u000e\u0010\u0004\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u001b"}, d2 = {"Lokhttp3/CertificatePinner$Pin;", "", "pattern", "", "canonicalHostname", "hashAlgorithm", "hash", "Lokio/ByteString;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lokio/ByteString;)V", "getHash", "()Lokio/ByteString;", "getHashAlgorithm", "()Ljava/lang/String;", "getPattern", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "matches", "hostname", "toString", "okhttp"}, k = 1, mv = {1, 1, 15})
    /* compiled from: CertificatePinner.kt */
    public static final class Pin {
        private final String canonicalHostname;
        private final ByteString hash;
        private final String hashAlgorithm;
        private final String pattern;

        private final String component2() {
            return this.canonicalHostname;
        }

        public static /* synthetic */ Pin copy$default(Pin pin, String str, String str2, String str3, ByteString byteString, int i, Object obj) {
            if ((i & 1) != 0) {
                str = pin.pattern;
            }
            if ((i & 2) != 0) {
                str2 = pin.canonicalHostname;
            }
            if ((i & 4) != 0) {
                str3 = pin.hashAlgorithm;
            }
            if ((i & 8) != 0) {
                byteString = pin.hash;
            }
            return pin.copy(str, str2, str3, byteString);
        }

        public final String component1() {
            return this.pattern;
        }

        public final String component3() {
            return this.hashAlgorithm;
        }

        public final ByteString component4() {
            return this.hash;
        }

        public final Pin copy(String str, String str2, String str3, ByteString byteString) {
            Intrinsics.checkParameterIsNotNull(str, "pattern");
            Intrinsics.checkParameterIsNotNull(str2, "canonicalHostname");
            Intrinsics.checkParameterIsNotNull(str3, "hashAlgorithm");
            Intrinsics.checkParameterIsNotNull(byteString, "hash");
            return new Pin(str, str2, str3, byteString);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Pin)) {
                return false;
            }
            Pin pin = (Pin) obj;
            return Intrinsics.areEqual((Object) this.pattern, (Object) pin.pattern) && Intrinsics.areEqual((Object) this.canonicalHostname, (Object) pin.canonicalHostname) && Intrinsics.areEqual((Object) this.hashAlgorithm, (Object) pin.hashAlgorithm) && Intrinsics.areEqual((Object) this.hash, (Object) pin.hash);
        }

        public int hashCode() {
            String str = this.pattern;
            int i = 0;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.canonicalHostname;
            int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            String str3 = this.hashAlgorithm;
            int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
            ByteString byteString = this.hash;
            if (byteString != null) {
                i = byteString.hashCode();
            }
            return hashCode3 + i;
        }

        public Pin(String str, String str2, String str3, ByteString byteString) {
            Intrinsics.checkParameterIsNotNull(str, "pattern");
            Intrinsics.checkParameterIsNotNull(str2, "canonicalHostname");
            Intrinsics.checkParameterIsNotNull(str3, "hashAlgorithm");
            Intrinsics.checkParameterIsNotNull(byteString, "hash");
            this.pattern = str;
            this.canonicalHostname = str2;
            this.hashAlgorithm = str3;
            this.hash = byteString;
        }

        public final String getPattern() {
            return this.pattern;
        }

        public final String getHashAlgorithm() {
            return this.hashAlgorithm;
        }

        public final ByteString getHash() {
            return this.hash;
        }

        public final boolean matches(String str) {
            Intrinsics.checkParameterIsNotNull(str, "hostname");
            if (!StringsKt.startsWith$default(this.pattern, CertificatePinner.WILDCARD, false, 2, (Object) null)) {
                return Intrinsics.areEqual((Object) str, (Object) this.canonicalHostname);
            }
            int indexOf$default = StringsKt.indexOf$default((CharSequence) str, '.', 0, false, 6, (Object) null);
            if ((str.length() - indexOf$default) - 1 != this.canonicalHostname.length()) {
                return false;
            }
            if (StringsKt.startsWith$default(str, this.canonicalHostname, indexOf$default + 1, false, 4, (Object) null)) {
                return true;
            }
            return false;
        }

        public String toString() {
            return this.hashAlgorithm + this.hash.base64();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J'\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\b2\u0012\u0010\u0003\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\t\"\u00020\b¢\u0006\u0002\u0010\nJ\u0006\u0010\u000b\u001a\u00020\fR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lokhttp3/CertificatePinner$Builder;", "", "()V", "pins", "", "Lokhttp3/CertificatePinner$Pin;", "add", "pattern", "", "", "(Ljava/lang/String;[Ljava/lang/String;)Lokhttp3/CertificatePinner$Builder;", "build", "Lokhttp3/CertificatePinner;", "okhttp"}, k = 1, mv = {1, 1, 15})
    /* compiled from: CertificatePinner.kt */
    public static final class Builder {
        private final List<Pin> pins = new ArrayList();

        public final Builder add(String str, String... strArr) {
            Intrinsics.checkParameterIsNotNull(str, "pattern");
            Intrinsics.checkParameterIsNotNull(strArr, "pins");
            Builder builder = this;
            for (String newPin$okhttp : strArr) {
                this.pins.add(CertificatePinner.Companion.newPin$okhttp(str, newPin$okhttp));
            }
            return this;
        }

        public final CertificatePinner build() {
            return new CertificatePinner(CollectionsKt.toSet(this.pins), (CertificateChainCleaner) null);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001d\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\u000bJ\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rH\u0007J\u0011\u0010\u000e\u001a\u00020\u000f*\u00020\u0010H\u0000¢\u0006\u0002\b\u0011J\u0011\u0010\u0012\u001a\u00020\u000f*\u00020\u0010H\u0000¢\u0006\u0002\b\u0013R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lokhttp3/CertificatePinner$Companion;", "", "()V", "DEFAULT", "Lokhttp3/CertificatePinner;", "WILDCARD", "", "newPin", "Lokhttp3/CertificatePinner$Pin;", "pattern", "pin", "newPin$okhttp", "certificate", "Ljava/security/cert/Certificate;", "toSha1ByteString", "Lokio/ByteString;", "Ljava/security/cert/X509Certificate;", "toSha1ByteString$okhttp", "toSha256ByteString", "toSha256ByteString$okhttp", "okhttp"}, k = 1, mv = {1, 1, 15})
    /* compiled from: CertificatePinner.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final String pin(Certificate certificate) {
            Intrinsics.checkParameterIsNotNull(certificate, "certificate");
            if (certificate instanceof X509Certificate) {
                StringBuilder sb = new StringBuilder("sha256/");
                Companion companion = this;
                sb.append(toSha256ByteString$okhttp((X509Certificate) certificate).base64());
                return sb.toString();
            }
            throw new IllegalArgumentException("Certificate pinning requires X509 certificates".toString());
        }

        public final ByteString toSha1ByteString$okhttp(X509Certificate x509Certificate) {
            Intrinsics.checkParameterIsNotNull(x509Certificate, "$this$toSha1ByteString");
            ByteString.Companion companion = ByteString.Companion;
            PublicKey publicKey = x509Certificate.getPublicKey();
            Intrinsics.checkExpressionValueIsNotNull(publicKey, "publicKey");
            byte[] encoded = publicKey.getEncoded();
            Intrinsics.checkExpressionValueIsNotNull(encoded, "publicKey.encoded");
            return ByteString.Companion.of$default(companion, encoded, 0, 0, 3, (Object) null).sha1();
        }

        public final ByteString toSha256ByteString$okhttp(X509Certificate x509Certificate) {
            Intrinsics.checkParameterIsNotNull(x509Certificate, "$this$toSha256ByteString");
            ByteString.Companion companion = ByteString.Companion;
            PublicKey publicKey = x509Certificate.getPublicKey();
            Intrinsics.checkExpressionValueIsNotNull(publicKey, "publicKey");
            byte[] encoded = publicKey.getEncoded();
            Intrinsics.checkExpressionValueIsNotNull(encoded, "publicKey.encoded");
            return ByteString.Companion.of$default(companion, encoded, 0, 0, 3, (Object) null).sha256();
        }

        public final Pin newPin$okhttp(String str, String str2) {
            String str3;
            Intrinsics.checkParameterIsNotNull(str, "pattern");
            Intrinsics.checkParameterIsNotNull(str2, "pin");
            if (StringsKt.startsWith$default(str, CertificatePinner.WILDCARD, false, 2, (Object) null)) {
                HttpUrl.Companion companion = HttpUrl.Companion;
                StringBuilder sb = new StringBuilder("http://");
                String substring = str.substring(2);
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
                sb.append(substring);
                str3 = companion.get(sb.toString()).host();
            } else {
                HttpUrl.Companion companion2 = HttpUrl.Companion;
                str3 = companion2.get("http://" + str).host();
            }
            if (StringsKt.startsWith$default(str2, "sha1/", false, 2, (Object) null)) {
                ByteString.Companion companion3 = ByteString.Companion;
                String substring2 = str2.substring(5);
                Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.String).substring(startIndex)");
                ByteString decodeBase64 = companion3.decodeBase64(substring2);
                if (decodeBase64 == null) {
                    Intrinsics.throwNpe();
                }
                return new Pin(str, str3, "sha1/", decodeBase64);
            } else if (StringsKt.startsWith$default(str2, "sha256/", false, 2, (Object) null)) {
                ByteString.Companion companion4 = ByteString.Companion;
                String substring3 = str2.substring(7);
                Intrinsics.checkExpressionValueIsNotNull(substring3, "(this as java.lang.String).substring(startIndex)");
                ByteString decodeBase642 = companion4.decodeBase64(substring3);
                if (decodeBase642 == null) {
                    Intrinsics.throwNpe();
                }
                return new Pin(str, str3, "sha256/", decodeBase642);
            } else {
                throw new IllegalArgumentException("pins must start with 'sha256/' or 'sha1/': " + str2);
            }
        }
    }
}
