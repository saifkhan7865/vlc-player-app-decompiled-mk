package io.ktor.network.tls;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.fusesource.jansi.AnsiRenderer;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lio/ktor/network/tls/OID;", "", "identifier", "", "(Ljava/lang/String;)V", "asArray", "", "getAsArray", "()[I", "getIdentifier", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: OID.kt */
public final class OID {
    /* access modifiers changed from: private */
    public static final OID BasicConstraints = new OID("2.5.29.19");
    /* access modifiers changed from: private */
    public static final OID ClientAuth = new OID("1.3.6.1.5.5.7.3.2");
    /* access modifiers changed from: private */
    public static final OID CommonName = new OID("2.5.4.3");
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final OID CountryName = new OID("2.5.4.6");
    /* access modifiers changed from: private */
    public static final OID ECDSAwithSHA256Encryption = new OID("1.2.840.10045.4.3.2");
    /* access modifiers changed from: private */
    public static final OID ECDSAwithSHA384Encryption = new OID("1.2.840.10045.4.3.3");
    /* access modifiers changed from: private */
    public static final OID ECEncryption = new OID("1.2.840.10045.2.1");
    /* access modifiers changed from: private */
    public static final OID ExtKeyUsage = new OID("2.5.29.37");
    /* access modifiers changed from: private */
    public static final OID KeyUsage = new OID("2.5.29.15");
    /* access modifiers changed from: private */
    public static final OID OrganizationName = new OID("2.5.4.10");
    /* access modifiers changed from: private */
    public static final OID OrganizationalUnitName = new OID("2.5.4.11");
    /* access modifiers changed from: private */
    public static final OID RSAEncryption = new OID("1 2 840 113549 1 1 1");
    /* access modifiers changed from: private */
    public static final OID RSAwithSHA1Encryption = new OID("1.2.840.113549.1.1.5");
    /* access modifiers changed from: private */
    public static final OID RSAwithSHA256Encryption = new OID("1.2.840.113549.1.1.11");
    /* access modifiers changed from: private */
    public static final OID RSAwithSHA384Encryption = new OID("1.2.840.113549.1.1.12");
    /* access modifiers changed from: private */
    public static final OID RSAwithSHA512Encryption = new OID("1.2.840.113549.1.1.13");
    /* access modifiers changed from: private */
    public static final OID ServerAuth = new OID("1.3.6.1.5.5.7.3.1");
    /* access modifiers changed from: private */
    public static final OID SubjectAltName = new OID("2.5.29.17");
    /* access modifiers changed from: private */
    public static final OID secp256r1 = new OID("1.2.840.10045.3.1.7");
    private final int[] asArray;
    private final String identifier;

    public static /* synthetic */ OID copy$default(OID oid, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = oid.identifier;
        }
        return oid.copy(str);
    }

    public final String component1() {
        return this.identifier;
    }

    public final OID copy(String str) {
        Intrinsics.checkNotNullParameter(str, "identifier");
        return new OID(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof OID) && Intrinsics.areEqual((Object) this.identifier, (Object) ((OID) obj).identifier);
    }

    public int hashCode() {
        return this.identifier.hashCode();
    }

    public String toString() {
        return "OID(identifier=" + this.identifier + ')';
    }

    public OID(String str) {
        Intrinsics.checkNotNullParameter(str, "identifier");
        this.identifier = str;
        Iterable<String> split$default = StringsKt.split$default((CharSequence) str, new String[]{".", AnsiRenderer.CODE_TEXT_SEPARATOR}, false, 0, 6, (Object) null);
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(split$default, 10));
        for (String trim : split$default) {
            arrayList.add(Integer.valueOf(Integer.parseInt(StringsKt.trim((CharSequence) trim).toString())));
        }
        this.asArray = CollectionsKt.toIntArray((List) arrayList);
    }

    public final String getIdentifier() {
        return this.identifier;
    }

    public final int[] getAsArray() {
        return this.asArray;
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b(\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010+\u001a\u00020\u00042\u0006\u0010,\u001a\u00020-R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0011\u0010\u000f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0011\u0010\u0011\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006R\u0011\u0010\u0013\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0006R\u0011\u0010\u0015\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0006R\u0011\u0010\u0017\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0006R\u0011\u0010\u0019\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0006R\u0011\u0010\u001b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0006R\u0011\u0010\u001d\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0006R\u0011\u0010\u001f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0006R\u0011\u0010!\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0006R\u0011\u0010#\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0006R\u0011\u0010%\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0006R\u0011\u0010'\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0006R\u0011\u0010)\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0006¨\u0006."}, d2 = {"Lio/ktor/network/tls/OID$Companion;", "", "()V", "BasicConstraints", "Lio/ktor/network/tls/OID;", "getBasicConstraints", "()Lio/ktor/network/tls/OID;", "ClientAuth", "getClientAuth", "CommonName", "getCommonName", "CountryName", "getCountryName", "ECDSAwithSHA256Encryption", "getECDSAwithSHA256Encryption", "ECDSAwithSHA384Encryption", "getECDSAwithSHA384Encryption", "ECEncryption", "getECEncryption", "ExtKeyUsage", "getExtKeyUsage", "KeyUsage", "getKeyUsage", "OrganizationName", "getOrganizationName", "OrganizationalUnitName", "getOrganizationalUnitName", "RSAEncryption", "getRSAEncryption", "RSAwithSHA1Encryption", "getRSAwithSHA1Encryption", "RSAwithSHA256Encryption", "getRSAwithSHA256Encryption", "RSAwithSHA384Encryption", "getRSAwithSHA384Encryption", "RSAwithSHA512Encryption", "getRSAwithSHA512Encryption", "ServerAuth", "getServerAuth", "SubjectAltName", "getSubjectAltName", "secp256r1", "getSecp256r1", "fromAlgorithm", "algorithm", "", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: OID.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final OID getOrganizationName() {
            return OID.OrganizationName;
        }

        public final OID getOrganizationalUnitName() {
            return OID.OrganizationalUnitName;
        }

        public final OID getCountryName() {
            return OID.CountryName;
        }

        public final OID getCommonName() {
            return OID.CommonName;
        }

        public final OID getSubjectAltName() {
            return OID.SubjectAltName;
        }

        public final OID getBasicConstraints() {
            return OID.BasicConstraints;
        }

        public final OID getKeyUsage() {
            return OID.KeyUsage;
        }

        public final OID getExtKeyUsage() {
            return OID.ExtKeyUsage;
        }

        public final OID getServerAuth() {
            return OID.ServerAuth;
        }

        public final OID getClientAuth() {
            return OID.ClientAuth;
        }

        public final OID getRSAEncryption() {
            return OID.RSAEncryption;
        }

        public final OID getECEncryption() {
            return OID.ECEncryption;
        }

        public final OID getECDSAwithSHA384Encryption() {
            return OID.ECDSAwithSHA384Encryption;
        }

        public final OID getECDSAwithSHA256Encryption() {
            return OID.ECDSAwithSHA256Encryption;
        }

        public final OID getRSAwithSHA512Encryption() {
            return OID.RSAwithSHA512Encryption;
        }

        public final OID getRSAwithSHA384Encryption() {
            return OID.RSAwithSHA384Encryption;
        }

        public final OID getRSAwithSHA256Encryption() {
            return OID.RSAwithSHA256Encryption;
        }

        public final OID getRSAwithSHA1Encryption() {
            return OID.RSAwithSHA1Encryption;
        }

        public final OID getSecp256r1() {
            return OID.secp256r1;
        }

        public final OID fromAlgorithm(String str) {
            Intrinsics.checkNotNullParameter(str, "algorithm");
            switch (str.hashCode()) {
                case -794853417:
                    if (str.equals("SHA384withRSA")) {
                        return getRSAwithSHA384Encryption();
                    }
                    break;
                case -754115883:
                    if (str.equals("SHA1withRSA")) {
                        return getRSAwithSHA1Encryption();
                    }
                    break;
                case -280290445:
                    if (str.equals("SHA256withRSA")) {
                        return getRSAwithSHA256Encryption();
                    }
                    break;
                case 637568043:
                    if (str.equals("SHA384withECDSA")) {
                        return getECDSAwithSHA384Encryption();
                    }
                    break;
                case 1211345095:
                    if (str.equals("SHA256withECDSA")) {
                        return getECDSAwithSHA256Encryption();
                    }
                    break;
            }
            throw new IllegalStateException(("Could't find OID for " + str).toString());
        }
    }
}
