package org.bouncycastle.asn1.cmp;

import okhttp3.internal.cache.DiskLruCache;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.fusesource.jansi.AnsiConsole;
import org.videolan.resources.Constants;
import org.videolan.vlc.gui.dialogs.NetworkServerDialog;

public interface CMPObjectIdentifiers {
    public static final ASN1ObjectIdentifier ct_encKeyWithID = new ASN1ObjectIdentifier("1.2.840.113549.1.9.16.1.21");
    public static final ASN1ObjectIdentifier dhBasedMac = new ASN1ObjectIdentifier("1.2.840.113533.7.66.30");
    public static final ASN1ObjectIdentifier id_it;
    public static final ASN1ObjectIdentifier id_it_caCerts;
    public static final ASN1ObjectIdentifier id_it_certProfile;
    public static final ASN1ObjectIdentifier id_it_certReqTemplate;
    public static final ASN1ObjectIdentifier id_it_crlStatusList;
    public static final ASN1ObjectIdentifier id_it_crls;
    public static final ASN1ObjectIdentifier id_it_rootCaCert;
    public static final ASN1ObjectIdentifier id_it_rootCaKeyUpdate;
    public static final ASN1ObjectIdentifier id_pkip;
    public static final ASN1ObjectIdentifier id_regCtrl = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.5.1");
    public static final ASN1ObjectIdentifier id_regCtrl_algId;
    public static final ASN1ObjectIdentifier id_regCtrl_rsaKeyLen;
    public static final ASN1ObjectIdentifier id_regInfo = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.5.2");
    public static final ASN1ObjectIdentifier it_caKeyUpdateInfo;
    public static final ASN1ObjectIdentifier it_caProtEncCert;
    public static final ASN1ObjectIdentifier it_confirmWaitTime;
    public static final ASN1ObjectIdentifier it_currentCRL;
    public static final ASN1ObjectIdentifier it_encKeyPairTypes;
    public static final ASN1ObjectIdentifier it_implicitConfirm;
    public static final ASN1ObjectIdentifier it_keyPairParamRep;
    public static final ASN1ObjectIdentifier it_keyPairParamReq;
    public static final ASN1ObjectIdentifier it_origPKIMessage;
    public static final ASN1ObjectIdentifier it_preferredSymAlg;
    public static final ASN1ObjectIdentifier it_revPassphrase;
    public static final ASN1ObjectIdentifier it_signKeyPairTypes;
    public static final ASN1ObjectIdentifier it_suppLangTags;
    public static final ASN1ObjectIdentifier it_unsupportedOIDs;
    public static final ASN1ObjectIdentifier passwordBasedMac = new ASN1ObjectIdentifier("1.2.840.113533.7.66.13");
    public static final ASN1ObjectIdentifier regCtrl_altCertTemplate = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.5.1.7");
    public static final ASN1ObjectIdentifier regCtrl_authenticator = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.5.1.2");
    public static final ASN1ObjectIdentifier regCtrl_oldCertID = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.5.1.5");
    public static final ASN1ObjectIdentifier regCtrl_pkiArchiveOptions = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.5.1.4");
    public static final ASN1ObjectIdentifier regCtrl_pkiPublicationInfo = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.5.1.3");
    public static final ASN1ObjectIdentifier regCtrl_protocolEncrKey = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.5.1.6");
    public static final ASN1ObjectIdentifier regCtrl_regToken = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.5.1.1");
    public static final ASN1ObjectIdentifier regInfo_certReq = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.5.2.2");
    public static final ASN1ObjectIdentifier regInfo_utf8Pairs = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.5.2.1");

    static {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.4");
        id_it = aSN1ObjectIdentifier;
        it_caProtEncCert = aSN1ObjectIdentifier.branch(DiskLruCache.VERSION_1);
        it_signKeyPairTypes = aSN1ObjectIdentifier.branch("2");
        it_encKeyPairTypes = aSN1ObjectIdentifier.branch("3");
        it_preferredSymAlg = aSN1ObjectIdentifier.branch("4");
        it_caKeyUpdateInfo = aSN1ObjectIdentifier.branch("5");
        it_currentCRL = aSN1ObjectIdentifier.branch(Constants.GROUP_VIDEOS_NAME);
        it_unsupportedOIDs = aSN1ObjectIdentifier.branch("7");
        it_keyPairParamReq = aSN1ObjectIdentifier.branch("10");
        it_keyPairParamRep = aSN1ObjectIdentifier.branch("11");
        it_revPassphrase = aSN1ObjectIdentifier.branch("12");
        it_implicitConfirm = aSN1ObjectIdentifier.branch("13");
        it_confirmWaitTime = aSN1ObjectIdentifier.branch("14");
        it_origPKIMessage = aSN1ObjectIdentifier.branch("15");
        it_suppLangTags = aSN1ObjectIdentifier.branch(AnsiConsole.JANSI_COLORS_16);
        id_it_caCerts = aSN1ObjectIdentifier.branch("17");
        id_it_rootCaKeyUpdate = aSN1ObjectIdentifier.branch("18");
        id_it_certReqTemplate = aSN1ObjectIdentifier.branch("19");
        id_it_rootCaCert = aSN1ObjectIdentifier.branch("20");
        id_it_certProfile = aSN1ObjectIdentifier.branch("21");
        id_it_crlStatusList = aSN1ObjectIdentifier.branch(NetworkServerDialog.SFTP_DEFAULT_PORT);
        id_it_crls = aSN1ObjectIdentifier.branch("23");
        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.5");
        id_pkip = aSN1ObjectIdentifier2;
        id_regCtrl_algId = aSN1ObjectIdentifier2.branch("1.11");
        id_regCtrl_rsaKeyLen = aSN1ObjectIdentifier2.branch("1.12");
    }
}
