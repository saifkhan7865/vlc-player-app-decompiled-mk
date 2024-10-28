package org.bouncycastle.est;

import io.netty.handler.codec.http.HttpHeaders;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.DERPrintableString;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCS10CertificationRequestBuilder;
import org.bouncycastle.util.Selector;
import org.bouncycastle.util.Store;
import org.bouncycastle.util.encoders.Base64;

public class ESTService {
    protected static final String CACERTS = "/cacerts";
    protected static final String CSRATTRS = "/csrattrs";
    protected static final String FULLCMC = "/fullcmc";
    protected static final String SERVERGEN = "/serverkeygen";
    protected static final String SIMPLE_ENROLL = "/simpleenroll";
    protected static final String SIMPLE_REENROLL = "/simplereenroll";
    protected static final Set<String> illegalParts;
    private static final Pattern pathInValid = Pattern.compile("^[0-9a-zA-Z_\\-.~!$&'()*+,;:=]+");
    private final ESTClientProvider clientProvider;
    private final String server;

    static {
        HashSet hashSet = new HashSet();
        illegalParts = hashSet;
        hashSet.add("cacerts");
        hashSet.add("simpleenroll");
        hashSet.add("simplereenroll");
        hashSet.add("fullcmc");
        hashSet.add("serverkeygen");
        hashSet.add("csrattrs");
    }

    ESTService(String str, String str2, ESTClientProvider eSTClientProvider) {
        String str3;
        String verifyServer = verifyServer(str);
        if (str2 != null) {
            str3 = "https://" + verifyServer + "/.well-known/est/" + verifyLabel(str2);
        } else {
            str3 = "https://" + verifyServer + "/.well-known/est";
        }
        this.server = str3;
        this.clientProvider = eSTClientProvider;
    }

    /* access modifiers changed from: private */
    public String annotateRequest(byte[] bArr) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        int i = 0;
        do {
            int i2 = i + 48;
            if (i2 < bArr.length) {
                printWriter.print(Base64.toBase64String(bArr, i, 48));
                i = i2;
            } else {
                printWriter.print(Base64.toBase64String(bArr, i, bArr.length - i));
                i = bArr.length;
            }
            printWriter.print(10);
        } while (i < bArr.length);
        printWriter.flush();
        return stringWriter.toString();
    }

    private ASN1InputStream getASN1InputStream(InputStream inputStream, Long l) {
        return l == null ? new ASN1InputStream(inputStream) : ((long) l.intValue()) == l.longValue() ? new ASN1InputStream(inputStream, l.intValue()) : new ASN1InputStream(inputStream);
    }

    public static X509CertificateHolder[] storeToArray(Store<X509CertificateHolder> store) {
        return storeToArray(store, (Selector<X509CertificateHolder>) null);
    }

    public static X509CertificateHolder[] storeToArray(Store<X509CertificateHolder> store, Selector<X509CertificateHolder> selector) {
        Collection<X509CertificateHolder> matches = store.getMatches(selector);
        return (X509CertificateHolder[]) matches.toArray(new X509CertificateHolder[matches.size()]);
    }

    private String verifyLabel(String str) {
        while (str.endsWith("/") && str.length() > 0) {
            str = str.substring(0, str.length() - 1);
        }
        while (str.startsWith("/") && str.length() > 0) {
            str = str.substring(1);
        }
        if (str.length() == 0) {
            throw new IllegalArgumentException("Label set but after trimming '/' is not zero length string.");
        } else if (!pathInValid.matcher(str).matches()) {
            throw new IllegalArgumentException("Server path " + str + " contains invalid characters");
        } else if (!illegalParts.contains(str)) {
            return str;
        } else {
            throw new IllegalArgumentException("Label " + str + " is a reserved path segment.");
        }
    }

    private String verifyServer(String str) {
        while (str.endsWith("/") && str.length() > 0) {
            try {
                str = str.substring(0, str.length() - 1);
            } catch (Exception e) {
                if (e instanceof IllegalArgumentException) {
                    throw ((IllegalArgumentException) e);
                }
                throw new IllegalArgumentException("Scheme and host is invalid: " + e.getMessage(), e);
            }
        }
        if (!str.contains("://")) {
            URL url = new URL("https://" + str);
            if (url.getPath().length() != 0) {
                if (!url.getPath().equals("/")) {
                    throw new IllegalArgumentException("Server contains path, must only be <dnsname/ipaddress>:port, a path of '/.well-known/est/<label>' will be added arbitrarily.");
                }
            }
            return str;
        }
        throw new IllegalArgumentException("Server contains scheme, must only be <dnsname/ipaddress>:port, https:// will be added arbitrarily.");
    }

    /* access modifiers changed from: protected */
    public EnrollmentResponse enroll(boolean z, PKCS10CertificationRequest pKCS10CertificationRequest, ESTAuth eSTAuth, boolean z2) throws IOException {
        if (this.clientProvider.isTrusted()) {
            ESTResponse eSTResponse = null;
            try {
                byte[] bytes = annotateRequest(pKCS10CertificationRequest.getEncoded()).getBytes();
                StringBuilder sb = new StringBuilder();
                sb.append(this.server);
                sb.append(z2 ? SERVERGEN : z ? SIMPLE_REENROLL : SIMPLE_ENROLL);
                URL url = new URL(sb.toString());
                ESTClient makeClient = this.clientProvider.makeClient();
                ESTRequestBuilder withClient = new ESTRequestBuilder("POST", url).withData(bytes).withClient(makeClient);
                withClient.addHeader("Content-Type", "application/pkcs10");
                withClient.addHeader("Content-Length", "" + bytes.length);
                withClient.addHeader(HttpHeaders.Names.CONTENT_TRANSFER_ENCODING, HttpHeaders.Values.BASE64);
                if (eSTAuth != null) {
                    eSTAuth.applyAuth(withClient);
                }
                ESTResponse doRequest = makeClient.doRequest(withClient.build());
                EnrollmentResponse handleEnrollResponse = handleEnrollResponse(doRequest);
                if (doRequest != null) {
                    doRequest.close();
                }
                return handleEnrollResponse;
            } catch (Throwable th) {
                if (eSTResponse != null) {
                    eSTResponse.close();
                }
                throw th;
            }
        } else {
            throw new IllegalStateException("No trust anchors.");
        }
    }

    public EnrollmentResponse enrollPop(boolean z, final PKCS10CertificationRequestBuilder pKCS10CertificationRequestBuilder, final ContentSigner contentSigner, ESTAuth eSTAuth, boolean z2) throws IOException {
        if (this.clientProvider.isTrusted()) {
            ESTResponse eSTResponse = null;
            try {
                StringBuilder sb = new StringBuilder();
                sb.append(this.server);
                sb.append(z ? SIMPLE_REENROLL : SIMPLE_ENROLL);
                URL url = new URL(sb.toString());
                ESTClient makeClient = this.clientProvider.makeClient();
                ESTRequestBuilder withConnectionListener = new ESTRequestBuilder("POST", url).withClient(makeClient).withConnectionListener(new ESTSourceConnectionListener() {
                    public ESTRequest onConnection(Source source, ESTRequest eSTRequest) throws IOException {
                        if (source instanceof TLSUniqueProvider) {
                            TLSUniqueProvider tLSUniqueProvider = (TLSUniqueProvider) source;
                            if (tLSUniqueProvider.isTLSUniqueAvailable()) {
                                PKCS10CertificationRequestBuilder pKCS10CertificationRequestBuilder = new PKCS10CertificationRequestBuilder(pKCS10CertificationRequestBuilder);
                                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                pKCS10CertificationRequestBuilder.setAttribute(PKCSObjectIdentifiers.pkcs_9_at_challengePassword, (ASN1Encodable) new DERPrintableString(Base64.toBase64String(tLSUniqueProvider.getTLSUnique())));
                                byteArrayOutputStream.write(ESTService.this.annotateRequest(pKCS10CertificationRequestBuilder.build(contentSigner).getEncoded()).getBytes());
                                byteArrayOutputStream.flush();
                                ESTRequestBuilder withData = new ESTRequestBuilder(eSTRequest).withData(byteArrayOutputStream.toByteArray());
                                withData.setHeader("Content-Type", "application/pkcs10");
                                withData.setHeader(HttpHeaders.Names.CONTENT_TRANSFER_ENCODING, HttpHeaders.Values.BASE64);
                                withData.setHeader("Content-Length", Long.toString((long) byteArrayOutputStream.size()));
                                return withData.build();
                            }
                        }
                        throw new IOException("Source does not supply TLS unique.");
                    }
                });
                if (eSTAuth != null) {
                    eSTAuth.applyAuth(withConnectionListener);
                }
                ESTResponse doRequest = makeClient.doRequest(withConnectionListener.build());
                EnrollmentResponse handleEnrollResponse = handleEnrollResponse(doRequest);
                if (doRequest != null) {
                    doRequest.close();
                }
                return handleEnrollResponse;
            } catch (Throwable th) {
                if (eSTResponse != null) {
                    eSTResponse.close();
                }
                throw th;
            }
        } else {
            throw new IllegalStateException("No trust anchors.");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:50:0x014a A[Catch:{ all -> 0x0157 }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x014d A[Catch:{ all -> 0x0157 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.bouncycastle.est.CACertsResponse getCACerts() throws org.bouncycastle.est.ESTException {
        /*
            r14 = this;
            java.lang.String r0 = "Decoding CACerts: "
            java.lang.String r1 = "Response : "
            java.lang.String r2 = " got "
            r3 = 0
            java.net.URL r4 = new java.net.URL     // Catch:{ all -> 0x0145 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0145 }
            r5.<init>()     // Catch:{ all -> 0x0145 }
            java.lang.String r6 = r14.server     // Catch:{ all -> 0x0145 }
            r5.append(r6)     // Catch:{ all -> 0x0145 }
            java.lang.String r6 = "/cacerts"
            r5.append(r6)     // Catch:{ all -> 0x0145 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0145 }
            r4.<init>(r5)     // Catch:{ all -> 0x0145 }
            org.bouncycastle.est.ESTClientProvider r5 = r14.clientProvider     // Catch:{ all -> 0x0145 }
            org.bouncycastle.est.ESTClient r5 = r5.makeClient()     // Catch:{ all -> 0x0145 }
            org.bouncycastle.est.ESTRequestBuilder r6 = new org.bouncycastle.est.ESTRequestBuilder     // Catch:{ all -> 0x0145 }
            java.lang.String r7 = "GET"
            r6.<init>(r7, r4)     // Catch:{ all -> 0x0145 }
            org.bouncycastle.est.ESTRequestBuilder r6 = r6.withClient(r5)     // Catch:{ all -> 0x0145 }
            org.bouncycastle.est.ESTRequest r10 = r6.build()     // Catch:{ all -> 0x0145 }
            org.bouncycastle.est.ESTResponse r5 = r5.doRequest(r10)     // Catch:{ all -> 0x0145 }
            int r6 = r5.getStatusCode()     // Catch:{ all -> 0x0142 }
            r7 = 200(0xc8, float:2.8E-43)
            java.lang.String r13 = "Get CACerts: "
            if (r6 != r7) goto L_0x00dd
            org.bouncycastle.est.HttpUtil$Headers r6 = r5.getHeaders()     // Catch:{ all -> 0x0142 }
            java.lang.String r7 = "Content-Type"
            java.lang.String r6 = r6.getFirstValue(r7)     // Catch:{ all -> 0x0142 }
            if (r6 == 0) goto L_0x00a6
            java.lang.String r7 = "application/pkcs7-mime"
            boolean r7 = r6.startsWith(r7)     // Catch:{ all -> 0x0142 }
            if (r7 != 0) goto L_0x0057
            goto L_0x00a6
        L_0x0057:
            java.io.InputStream r1 = r5.getInputStream()     // Catch:{ all -> 0x007b }
            java.lang.Long r2 = r5.getContentLength()     // Catch:{ all -> 0x007b }
            org.bouncycastle.asn1.ASN1InputStream r1 = r14.getASN1InputStream(r1, r2)     // Catch:{ all -> 0x007b }
            org.bouncycastle.cmc.SimplePKIResponse r2 = new org.bouncycastle.cmc.SimplePKIResponse     // Catch:{ all -> 0x007b }
            org.bouncycastle.asn1.ASN1Primitive r1 = r1.readObject()     // Catch:{ all -> 0x007b }
            org.bouncycastle.asn1.cms.ContentInfo r1 = org.bouncycastle.asn1.cms.ContentInfo.getInstance(r1)     // Catch:{ all -> 0x007b }
            r2.<init>((org.bouncycastle.asn1.cms.ContentInfo) r1)     // Catch:{ all -> 0x007b }
            org.bouncycastle.util.Store r1 = r2.getCertificates()     // Catch:{ all -> 0x007b }
            org.bouncycastle.util.Store r0 = r2.getCRLs()     // Catch:{ all -> 0x007b }
            r9 = r0
            r8 = r1
            goto L_0x00e7
        L_0x007b:
            r1 = move-exception
            org.bouncycastle.est.ESTException r2 = new org.bouncycastle.est.ESTException     // Catch:{ all -> 0x0142 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0142 }
            r3.<init>(r0)     // Catch:{ all -> 0x0142 }
            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x0142 }
            r3.append(r0)     // Catch:{ all -> 0x0142 }
            java.lang.String r0 = " "
            r3.append(r0)     // Catch:{ all -> 0x0142 }
            java.lang.String r0 = r1.getMessage()     // Catch:{ all -> 0x0142 }
            r3.append(r0)     // Catch:{ all -> 0x0142 }
            java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x0142 }
            int r3 = r5.getStatusCode()     // Catch:{ all -> 0x0142 }
            java.io.InputStream r4 = r5.getInputStream()     // Catch:{ all -> 0x0142 }
            r2.<init>(r0, r1, r3, r4)     // Catch:{ all -> 0x0142 }
            throw r2     // Catch:{ all -> 0x0142 }
        L_0x00a6:
            if (r6 == 0) goto L_0x00b5
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0142 }
            r0.<init>(r2)     // Catch:{ all -> 0x0142 }
            r0.append(r6)     // Catch:{ all -> 0x0142 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0142 }
            goto L_0x00b7
        L_0x00b5:
            java.lang.String r0 = " but was not present."
        L_0x00b7:
            org.bouncycastle.est.ESTException r2 = new org.bouncycastle.est.ESTException     // Catch:{ all -> 0x0142 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0142 }
            r6.<init>(r1)     // Catch:{ all -> 0x0142 }
            java.lang.String r1 = r4.toString()     // Catch:{ all -> 0x0142 }
            r6.append(r1)     // Catch:{ all -> 0x0142 }
            java.lang.String r1 = "Expecting application/pkcs7-mime "
            r6.append(r1)     // Catch:{ all -> 0x0142 }
            r6.append(r0)     // Catch:{ all -> 0x0142 }
            java.lang.String r0 = r6.toString()     // Catch:{ all -> 0x0142 }
            int r1 = r5.getStatusCode()     // Catch:{ all -> 0x0142 }
            java.io.InputStream r4 = r5.getInputStream()     // Catch:{ all -> 0x0142 }
            r2.<init>(r0, r3, r1, r4)     // Catch:{ all -> 0x0142 }
            throw r2     // Catch:{ all -> 0x0142 }
        L_0x00dd:
            int r0 = r5.getStatusCode()     // Catch:{ all -> 0x0142 }
            r1 = 204(0xcc, float:2.86E-43)
            if (r0 != r1) goto L_0x0124
            r8 = r3
            r9 = r8
        L_0x00e7:
            org.bouncycastle.est.CACertsResponse r0 = new org.bouncycastle.est.CACertsResponse     // Catch:{ all -> 0x0142 }
            org.bouncycastle.est.Source r11 = r5.getSource()     // Catch:{ all -> 0x0142 }
            org.bouncycastle.est.ESTClientProvider r1 = r14.clientProvider     // Catch:{ all -> 0x0142 }
            boolean r12 = r1.isTrusted()     // Catch:{ all -> 0x0142 }
            r7 = r0
            r7.<init>(r8, r9, r10, r11, r12)     // Catch:{ all -> 0x0142 }
            if (r5 == 0) goto L_0x00ff
            r5.close()     // Catch:{ Exception -> 0x00fd }
            goto L_0x00ff
        L_0x00fd:
            r1 = move-exception
            goto L_0x0100
        L_0x00ff:
            r1 = r3
        L_0x0100:
            if (r1 == 0) goto L_0x0123
            boolean r0 = r1 instanceof org.bouncycastle.est.ESTException
            if (r0 == 0) goto L_0x0109
            org.bouncycastle.est.ESTException r1 = (org.bouncycastle.est.ESTException) r1
            throw r1
        L_0x0109:
            org.bouncycastle.est.ESTException r0 = new org.bouncycastle.est.ESTException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r13)
            java.lang.String r4 = r4.toString()
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            int r4 = r5.getStatusCode()
            r0.<init>(r2, r1, r4, r3)
            throw r0
        L_0x0123:
            return r0
        L_0x0124:
            org.bouncycastle.est.ESTException r0 = new org.bouncycastle.est.ESTException     // Catch:{ all -> 0x0142 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0142 }
            r1.<init>(r13)     // Catch:{ all -> 0x0142 }
            java.lang.String r2 = r4.toString()     // Catch:{ all -> 0x0142 }
            r1.append(r2)     // Catch:{ all -> 0x0142 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0142 }
            int r2 = r5.getStatusCode()     // Catch:{ all -> 0x0142 }
            java.io.InputStream r4 = r5.getInputStream()     // Catch:{ all -> 0x0142 }
            r0.<init>(r1, r3, r2, r4)     // Catch:{ all -> 0x0142 }
            throw r0     // Catch:{ all -> 0x0142 }
        L_0x0142:
            r0 = move-exception
            r3 = r5
            goto L_0x0146
        L_0x0145:
            r0 = move-exception
        L_0x0146:
            boolean r1 = r0 instanceof org.bouncycastle.est.ESTException     // Catch:{ all -> 0x0157 }
            if (r1 == 0) goto L_0x014d
            org.bouncycastle.est.ESTException r0 = (org.bouncycastle.est.ESTException) r0     // Catch:{ all -> 0x0157 }
            throw r0     // Catch:{ all -> 0x0157 }
        L_0x014d:
            org.bouncycastle.est.ESTException r1 = new org.bouncycastle.est.ESTException     // Catch:{ all -> 0x0157 }
            java.lang.String r2 = r0.getMessage()     // Catch:{ all -> 0x0157 }
            r1.<init>(r2, r0)     // Catch:{ all -> 0x0157 }
            throw r1     // Catch:{ all -> 0x0157 }
        L_0x0157:
            r0 = move-exception
            if (r3 == 0) goto L_0x015d
            r3.close()     // Catch:{ Exception -> 0x015d }
        L_0x015d:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.est.ESTService.getCACerts():org.bouncycastle.est.CACertsResponse");
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x00ed A[Catch:{ all -> 0x00fa }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00f0 A[Catch:{ all -> 0x00fa }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.bouncycastle.est.CSRRequestResponse getCSRAttributes() throws org.bouncycastle.est.ESTException {
        /*
            r8 = this;
            java.lang.String r0 = "Decoding CACerts: "
            java.lang.String r1 = "CSR Attribute request: "
            org.bouncycastle.est.ESTClientProvider r2 = r8.clientProvider
            boolean r2 = r2.isTrusted()
            if (r2 == 0) goto L_0x0101
            r2 = 0
            java.net.URL r3 = new java.net.URL     // Catch:{ all -> 0x00e8 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00e8 }
            r4.<init>()     // Catch:{ all -> 0x00e8 }
            java.lang.String r5 = r8.server     // Catch:{ all -> 0x00e8 }
            r4.append(r5)     // Catch:{ all -> 0x00e8 }
            java.lang.String r5 = "/csrattrs"
            r4.append(r5)     // Catch:{ all -> 0x00e8 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x00e8 }
            r3.<init>(r4)     // Catch:{ all -> 0x00e8 }
            org.bouncycastle.est.ESTClientProvider r4 = r8.clientProvider     // Catch:{ all -> 0x00e8 }
            org.bouncycastle.est.ESTClient r4 = r4.makeClient()     // Catch:{ all -> 0x00e8 }
            org.bouncycastle.est.ESTRequestBuilder r5 = new org.bouncycastle.est.ESTRequestBuilder     // Catch:{ all -> 0x00e8 }
            java.lang.String r6 = "GET"
            r5.<init>(r6, r3)     // Catch:{ all -> 0x00e8 }
            org.bouncycastle.est.ESTRequestBuilder r5 = r5.withClient(r4)     // Catch:{ all -> 0x00e8 }
            org.bouncycastle.est.ESTRequest r5 = r5.build()     // Catch:{ all -> 0x00e8 }
            org.bouncycastle.est.ESTResponse r4 = r4.doRequest(r5)     // Catch:{ all -> 0x00e8 }
            int r6 = r4.getStatusCode()     // Catch:{ all -> 0x00e5 }
            r7 = 200(0xc8, float:2.8E-43)
            if (r6 == r7) goto L_0x0073
            r0 = 204(0xcc, float:2.86E-43)
            if (r6 == r0) goto L_0x0071
            r0 = 404(0x194, float:5.66E-43)
            if (r6 != r0) goto L_0x004f
            goto L_0x0071
        L_0x004f:
            org.bouncycastle.est.ESTException r0 = new org.bouncycastle.est.ESTException     // Catch:{ all -> 0x00e5 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00e5 }
            r3.<init>(r1)     // Catch:{ all -> 0x00e5 }
            java.net.URL r1 = r5.getURL()     // Catch:{ all -> 0x00e5 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00e5 }
            r3.append(r1)     // Catch:{ all -> 0x00e5 }
            java.lang.String r1 = r3.toString()     // Catch:{ all -> 0x00e5 }
            int r3 = r4.getStatusCode()     // Catch:{ all -> 0x00e5 }
            java.io.InputStream r5 = r4.getInputStream()     // Catch:{ all -> 0x00e5 }
            r0.<init>(r1, r2, r3, r5)     // Catch:{ all -> 0x00e5 }
            throw r0     // Catch:{ all -> 0x00e5 }
        L_0x0071:
            r5 = r2
            goto L_0x0090
        L_0x0073:
            java.io.InputStream r1 = r4.getInputStream()     // Catch:{ all -> 0x00ba }
            java.lang.Long r5 = r4.getContentLength()     // Catch:{ all -> 0x00ba }
            org.bouncycastle.asn1.ASN1InputStream r1 = r8.getASN1InputStream(r1, r5)     // Catch:{ all -> 0x00ba }
            org.bouncycastle.asn1.ASN1Primitive r1 = r1.readObject()     // Catch:{ all -> 0x00ba }
            org.bouncycastle.asn1.ASN1Sequence r1 = org.bouncycastle.asn1.ASN1Sequence.getInstance(r1)     // Catch:{ all -> 0x00ba }
            org.bouncycastle.est.CSRAttributesResponse r5 = new org.bouncycastle.est.CSRAttributesResponse     // Catch:{ all -> 0x00ba }
            org.bouncycastle.asn1.est.CsrAttrs r1 = org.bouncycastle.asn1.est.CsrAttrs.getInstance(r1)     // Catch:{ all -> 0x00ba }
            r5.<init>((org.bouncycastle.asn1.est.CsrAttrs) r1)     // Catch:{ all -> 0x00ba }
        L_0x0090:
            if (r4 == 0) goto L_0x0098
            r4.close()     // Catch:{ Exception -> 0x0096 }
            goto L_0x0098
        L_0x0096:
            r0 = move-exception
            goto L_0x0099
        L_0x0098:
            r0 = r2
        L_0x0099:
            if (r0 == 0) goto L_0x00b0
            boolean r1 = r0 instanceof org.bouncycastle.est.ESTException
            if (r1 == 0) goto L_0x00a2
            org.bouncycastle.est.ESTException r0 = (org.bouncycastle.est.ESTException) r0
            throw r0
        L_0x00a2:
            org.bouncycastle.est.ESTException r1 = new org.bouncycastle.est.ESTException
            java.lang.String r3 = r0.getMessage()
            int r4 = r4.getStatusCode()
            r1.<init>(r3, r0, r4, r2)
            throw r1
        L_0x00b0:
            org.bouncycastle.est.CSRRequestResponse r0 = new org.bouncycastle.est.CSRRequestResponse
            org.bouncycastle.est.Source r1 = r4.getSource()
            r0.<init>(r5, r1)
            return r0
        L_0x00ba:
            r1 = move-exception
            org.bouncycastle.est.ESTException r2 = new org.bouncycastle.est.ESTException     // Catch:{ all -> 0x00e5 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x00e5 }
            r5.<init>(r0)     // Catch:{ all -> 0x00e5 }
            java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x00e5 }
            r5.append(r0)     // Catch:{ all -> 0x00e5 }
            java.lang.String r0 = " "
            r5.append(r0)     // Catch:{ all -> 0x00e5 }
            java.lang.String r0 = r1.getMessage()     // Catch:{ all -> 0x00e5 }
            r5.append(r0)     // Catch:{ all -> 0x00e5 }
            java.lang.String r0 = r5.toString()     // Catch:{ all -> 0x00e5 }
            int r3 = r4.getStatusCode()     // Catch:{ all -> 0x00e5 }
            java.io.InputStream r5 = r4.getInputStream()     // Catch:{ all -> 0x00e5 }
            r2.<init>(r0, r1, r3, r5)     // Catch:{ all -> 0x00e5 }
            throw r2     // Catch:{ all -> 0x00e5 }
        L_0x00e5:
            r0 = move-exception
            r2 = r4
            goto L_0x00e9
        L_0x00e8:
            r0 = move-exception
        L_0x00e9:
            boolean r1 = r0 instanceof org.bouncycastle.est.ESTException     // Catch:{ all -> 0x00fa }
            if (r1 == 0) goto L_0x00f0
            org.bouncycastle.est.ESTException r0 = (org.bouncycastle.est.ESTException) r0     // Catch:{ all -> 0x00fa }
            throw r0     // Catch:{ all -> 0x00fa }
        L_0x00f0:
            org.bouncycastle.est.ESTException r1 = new org.bouncycastle.est.ESTException     // Catch:{ all -> 0x00fa }
            java.lang.String r3 = r0.getMessage()     // Catch:{ all -> 0x00fa }
            r1.<init>(r3, r0)     // Catch:{ all -> 0x00fa }
            throw r1     // Catch:{ all -> 0x00fa }
        L_0x00fa:
            r0 = move-exception
            if (r2 == 0) goto L_0x0100
            r2.close()     // Catch:{ Exception -> 0x0100 }
        L_0x0100:
            throw r0
        L_0x0101:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "No trust anchors."
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.est.ESTService.getCSRAttributes():org.bouncycastle.est.CSRRequestResponse");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x004b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x007b, code lost:
        throw new org.bouncycastle.est.ESTException("Unable to parse Retry-After header:" + r4.getURL().toString() + org.fusesource.jansi.AnsiRenderer.CODE_TEXT_SEPARATOR + r0.getMessage(), (java.lang.Throwable) null, r11.getStatusCode(), r11.getInputStream());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:?, code lost:
        r1 = new java.text.SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", java.util.Locale.US);
        r1.setTimeZone(j$.util.DesugarTimeZone.getTimeZone("GMT"));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x003e, code lost:
        r2 = r1.parse(r0).getTime();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0024 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.bouncycastle.est.EnrollmentResponse handleEnrollResponse(org.bouncycastle.est.ESTResponse r11) throws java.io.IOException {
        /*
            r10 = this;
            org.bouncycastle.est.ESTRequest r4 = r11.getOriginalRequest()
            int r0 = r11.getStatusCode()
            r1 = 202(0xca, float:2.83E-43)
            r2 = 0
            if (r0 != r1) goto L_0x0098
            java.lang.String r0 = "Retry-After"
            java.lang.String r0 = r11.getHeader(r0)
            if (r0 == 0) goto L_0x007c
            long r5 = java.lang.System.currentTimeMillis()     // Catch:{ NumberFormatException -> 0x0024 }
            long r0 = java.lang.Long.parseLong(r0)     // Catch:{ NumberFormatException -> 0x0024 }
            r2 = 1000(0x3e8, double:4.94E-321)
            long r0 = r0 * r2
            long r5 = r5 + r0
            r2 = r5
            goto L_0x003f
        L_0x0024:
            java.text.SimpleDateFormat r1 = new java.text.SimpleDateFormat     // Catch:{ Exception -> 0x004b }
            java.lang.String r3 = "EEE, dd MMM yyyy HH:mm:ss z"
            java.util.Locale r5 = java.util.Locale.US     // Catch:{ Exception -> 0x004b }
            r1.<init>(r3, r5)     // Catch:{ Exception -> 0x004b }
            java.lang.String r3 = "GMT"
            java.util.TimeZone r3 = j$.util.DesugarTimeZone.getTimeZone(r3)     // Catch:{ Exception -> 0x004b }
            r1.setTimeZone(r3)     // Catch:{ Exception -> 0x004b }
            java.util.Date r0 = r1.parse(r0)     // Catch:{ Exception -> 0x004b }
            long r0 = r0.getTime()     // Catch:{ Exception -> 0x004b }
            r2 = r0
        L_0x003f:
            org.bouncycastle.est.EnrollmentResponse r6 = new org.bouncycastle.est.EnrollmentResponse
            r1 = 0
            org.bouncycastle.est.Source r5 = r11.getSource()
            r0 = r6
            r0.<init>(r1, r2, r4, r5)
            return r6
        L_0x004b:
            r0 = move-exception
            org.bouncycastle.est.ESTException r1 = new org.bouncycastle.est.ESTException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r5 = "Unable to parse Retry-After header:"
            r3.<init>(r5)
            java.net.URL r4 = r4.getURL()
            java.lang.String r4 = r4.toString()
            r3.append(r4)
            java.lang.String r4 = " "
            r3.append(r4)
            java.lang.String r0 = r0.getMessage()
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            int r3 = r11.getStatusCode()
            java.io.InputStream r11 = r11.getInputStream()
            r1.<init>(r0, r2, r3, r11)
            throw r1
        L_0x007c:
            org.bouncycastle.est.ESTException r11 = new org.bouncycastle.est.ESTException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Got Status 202 but not Retry-After header from: "
            r0.<init>(r1)
            java.net.URL r1 = r4.getURL()
            java.lang.String r1 = r1.toString()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r11.<init>(r0)
            throw r11
        L_0x0098:
            int r0 = r11.getStatusCode()
            r1 = 200(0xc8, float:2.8E-43)
            if (r0 != r1) goto L_0x00f9
            java.lang.String r0 = "content-type"
            java.lang.String r3 = r11.getHeaderOrEmpty(r0)
            java.lang.String r5 = "multipart/mixed"
            boolean r3 = r3.contains(r5)
            if (r3 == 0) goto L_0x00f9
            org.bouncycastle.mime.Headers r1 = new org.bouncycastle.mime.Headers
            java.lang.String r0 = r11.getHeaderOrEmpty(r0)
            java.lang.String r2 = "base64"
            r1.<init>((java.lang.String) r0, (java.lang.String) r2)
            org.bouncycastle.mime.BasicMimeParser r0 = new org.bouncycastle.mime.BasicMimeParser
            java.io.InputStream r2 = r11.getInputStream()
            r0.<init>((org.bouncycastle.mime.Headers) r1, (java.io.InputStream) r2)
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]
            org.bouncycastle.est.ESTService$2 r2 = new org.bouncycastle.est.ESTService$2
            r2.<init>(r1)
            r0.parse(r2)
            r0 = 0
            r2 = r1[r0]
            if (r2 == 0) goto L_0x00f1
            r2 = 1
            r2 = r1[r2]
            if (r2 == 0) goto L_0x00f1
            org.bouncycastle.cmc.SimplePKIResponse r2 = (org.bouncycastle.cmc.SimplePKIResponse) r2
            org.bouncycastle.util.Store r4 = r2.getCertificates()
            org.bouncycastle.est.EnrollmentResponse r2 = new org.bouncycastle.est.EnrollmentResponse
            org.bouncycastle.est.Source r8 = r11.getSource()
            r11 = r1[r0]
            org.bouncycastle.asn1.pkcs.PrivateKeyInfo r9 = org.bouncycastle.asn1.pkcs.PrivateKeyInfo.getInstance(r11)
            r5 = -1
            r7 = 0
            r3 = r2
            r3.<init>(r4, r5, r7, r8, r9)
            return r2
        L_0x00f1:
            org.bouncycastle.est.ESTException r11 = new org.bouncycastle.est.ESTException
            java.lang.String r0 = "received neither private key info and certificates"
            r11.<init>(r0)
            throw r11
        L_0x00f9:
            int r0 = r11.getStatusCode()
            if (r0 != r1) goto L_0x0136
            org.bouncycastle.asn1.ASN1InputStream r0 = new org.bouncycastle.asn1.ASN1InputStream
            java.io.InputStream r1 = r11.getInputStream()
            r0.<init>((java.io.InputStream) r1)
            org.bouncycastle.cmc.SimplePKIResponse r1 = new org.bouncycastle.cmc.SimplePKIResponse     // Catch:{ CMCException -> 0x0127 }
            org.bouncycastle.asn1.ASN1Primitive r0 = r0.readObject()     // Catch:{ CMCException -> 0x0127 }
            org.bouncycastle.asn1.cms.ContentInfo r0 = org.bouncycastle.asn1.cms.ContentInfo.getInstance(r0)     // Catch:{ CMCException -> 0x0127 }
            r1.<init>((org.bouncycastle.asn1.cms.ContentInfo) r0)     // Catch:{ CMCException -> 0x0127 }
            org.bouncycastle.util.Store r3 = r1.getCertificates()
            org.bouncycastle.est.EnrollmentResponse r0 = new org.bouncycastle.est.EnrollmentResponse
            r6 = 0
            org.bouncycastle.est.Source r7 = r11.getSource()
            r4 = -1
            r2 = r0
            r2.<init>(r3, r4, r6, r7)
            return r0
        L_0x0127:
            r11 = move-exception
            org.bouncycastle.est.ESTException r0 = new org.bouncycastle.est.ESTException
            java.lang.String r1 = r11.getMessage()
            java.lang.Throwable r11 = r11.getCause()
            r0.<init>(r1, r11)
            throw r0
        L_0x0136:
            org.bouncycastle.est.ESTException r0 = new org.bouncycastle.est.ESTException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "Simple Enroll: "
            r1.<init>(r3)
            java.net.URL r3 = r4.getURL()
            java.lang.String r3 = r3.toString()
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            int r3 = r11.getStatusCode()
            java.io.InputStream r11 = r11.getInputStream()
            r0.<init>(r1, r2, r3, r11)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.est.ESTService.handleEnrollResponse(org.bouncycastle.est.ESTResponse):org.bouncycastle.est.EnrollmentResponse");
    }

    public EnrollmentResponse simpleEnroll(EnrollmentResponse enrollmentResponse) throws Exception {
        if (this.clientProvider.isTrusted()) {
            ESTResponse eSTResponse = null;
            try {
                ESTClient makeClient = this.clientProvider.makeClient();
                ESTResponse doRequest = makeClient.doRequest(new ESTRequestBuilder(enrollmentResponse.getRequestToRetry()).withClient(makeClient).build());
                EnrollmentResponse handleEnrollResponse = handleEnrollResponse(doRequest);
                if (doRequest != null) {
                    doRequest.close();
                }
                return handleEnrollResponse;
            } catch (Throwable th) {
                if (eSTResponse != null) {
                    eSTResponse.close();
                }
                throw th;
            }
        } else {
            throw new IllegalStateException("No trust anchors.");
        }
    }

    public EnrollmentResponse simpleEnroll(boolean z, PKCS10CertificationRequest pKCS10CertificationRequest, ESTAuth eSTAuth) throws IOException {
        return enroll(z, pKCS10CertificationRequest, eSTAuth, false);
    }

    public EnrollmentResponse simpleEnrollPoP(boolean z, PKCS10CertificationRequestBuilder pKCS10CertificationRequestBuilder, ContentSigner contentSigner, ESTAuth eSTAuth) throws IOException {
        return enrollPop(z, pKCS10CertificationRequestBuilder, contentSigner, eSTAuth, false);
    }

    public EnrollmentResponse simpleEnrollPopWithServersideCreation(PKCS10CertificationRequestBuilder pKCS10CertificationRequestBuilder, ContentSigner contentSigner, ESTAuth eSTAuth) throws IOException {
        return enrollPop(false, pKCS10CertificationRequestBuilder, contentSigner, eSTAuth, true);
    }

    public EnrollmentResponse simpleEnrollWithServersideCreation(PKCS10CertificationRequest pKCS10CertificationRequest, ESTAuth eSTAuth) throws IOException {
        return enroll(false, pKCS10CertificationRequest, eSTAuth, true);
    }
}
