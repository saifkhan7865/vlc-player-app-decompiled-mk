package org.bouncycastle.oer;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import okhttp3.internal.cache.DiskLruCache;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.oer.OERDefinition;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.encoders.Hex;
import org.bouncycastle.util.io.Streams;
import org.videolan.resources.Constants;

public class OERInputStream extends FilterInputStream {
    /* access modifiers changed from: private */
    public static final int[] bits = {1, 2, 4, 8, 16, 32, 64, 128};
    private static final int[] bitsR = {128, 64, 32, 16, 8, 4, 2, 1};
    protected PrintWriter debugOutput;
    protected PrintWriter debugStream;
    private int maxByteAllocation;

    /* renamed from: org.bouncycastle.oer.OERInputStream$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType;

        /* JADX WARNING: Can't wrap try/catch for region: R(32:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|32) */
        /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0090 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x009c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x00a8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.bouncycastle.oer.OERDefinition$BaseType[] r0 = org.bouncycastle.oer.OERDefinition.BaseType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType = r0
                org.bouncycastle.oer.OERDefinition$BaseType r1 = org.bouncycastle.oer.OERDefinition.BaseType.OPAQUE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.bouncycastle.oer.OERDefinition$BaseType r1 = org.bouncycastle.oer.OERDefinition.BaseType.Switch     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.bouncycastle.oer.OERDefinition$BaseType r1 = org.bouncycastle.oer.OERDefinition.BaseType.Supplier     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.bouncycastle.oer.OERDefinition$BaseType r1 = org.bouncycastle.oer.OERDefinition.BaseType.SEQ_OF     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType     // Catch:{ NoSuchFieldError -> 0x003e }
                org.bouncycastle.oer.OERDefinition$BaseType r1 = org.bouncycastle.oer.OERDefinition.BaseType.SEQ     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.bouncycastle.oer.OERDefinition$BaseType r1 = org.bouncycastle.oer.OERDefinition.BaseType.CHOICE     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType     // Catch:{ NoSuchFieldError -> 0x0054 }
                org.bouncycastle.oer.OERDefinition$BaseType r1 = org.bouncycastle.oer.OERDefinition.BaseType.ENUM     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType     // Catch:{ NoSuchFieldError -> 0x0060 }
                org.bouncycastle.oer.OERDefinition$BaseType r1 = org.bouncycastle.oer.OERDefinition.BaseType.INT     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType     // Catch:{ NoSuchFieldError -> 0x006c }
                org.bouncycastle.oer.OERDefinition$BaseType r1 = org.bouncycastle.oer.OERDefinition.BaseType.OCTET_STRING     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType     // Catch:{ NoSuchFieldError -> 0x0078 }
                org.bouncycastle.oer.OERDefinition$BaseType r1 = org.bouncycastle.oer.OERDefinition.BaseType.IA5String     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType     // Catch:{ NoSuchFieldError -> 0x0084 }
                org.bouncycastle.oer.OERDefinition$BaseType r1 = org.bouncycastle.oer.OERDefinition.BaseType.UTF8_STRING     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r0 = $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType     // Catch:{ NoSuchFieldError -> 0x0090 }
                org.bouncycastle.oer.OERDefinition$BaseType r1 = org.bouncycastle.oer.OERDefinition.BaseType.BIT_STRING     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                int[] r0 = $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType     // Catch:{ NoSuchFieldError -> 0x009c }
                org.bouncycastle.oer.OERDefinition$BaseType r1 = org.bouncycastle.oer.OERDefinition.BaseType.NULL     // Catch:{ NoSuchFieldError -> 0x009c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009c }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009c }
            L_0x009c:
                int[] r0 = $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType     // Catch:{ NoSuchFieldError -> 0x00a8 }
                org.bouncycastle.oer.OERDefinition$BaseType r1 = org.bouncycastle.oer.OERDefinition.BaseType.EXTENSION     // Catch:{ NoSuchFieldError -> 0x00a8 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a8 }
                r2 = 14
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00a8 }
            L_0x00a8:
                int[] r0 = $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType     // Catch:{ NoSuchFieldError -> 0x00b4 }
                org.bouncycastle.oer.OERDefinition$BaseType r1 = org.bouncycastle.oer.OERDefinition.BaseType.BOOLEAN     // Catch:{ NoSuchFieldError -> 0x00b4 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b4 }
                r2 = 15
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00b4 }
            L_0x00b4:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.oer.OERInputStream.AnonymousClass1.<clinit>():void");
        }
    }

    public static class Choice extends OERInputStream {
        final int preamble;
        final int tag;
        final int tagClass;

        public Choice(InputStream inputStream) throws IOException {
            super(inputStream);
            int read;
            int read2 = read();
            this.preamble = read2;
            if (read2 >= 0) {
                this.tagClass = read2 & 192;
                int i = read2 & 63;
                if (i >= 63) {
                    i = 0;
                    do {
                        read = inputStream.read();
                        if (read >= 0) {
                            i = (i << 7) | (read & 127);
                        } else {
                            throw new EOFException("expecting further tag bytes");
                        }
                    } while ((read & 128) != 0);
                }
                this.tag = i;
                return;
            }
            throw new EOFException("expecting preamble byte of choice");
        }

        public int getTag() {
            return this.tag;
        }

        public int getTagClass() {
            return this.tagClass;
        }

        public boolean isApplicationTagClass() {
            return this.tagClass == 64;
        }

        public boolean isContextSpecific() {
            return this.tagClass == 128;
        }

        public boolean isPrivateTagClass() {
            return this.tagClass == 192;
        }

        public boolean isUniversalTagClass() {
            return this.tagClass == 0;
        }

        public String toString() {
            String str;
            StringBuilder sb = new StringBuilder("CHOICE(");
            int i = this.tagClass;
            if (i == 0) {
                str = "Universal ";
            } else if (i == 64) {
                str = "Application ";
            } else if (i != 128) {
                if (i == 192) {
                    str = "Private ";
                }
                sb.append("Tag = " + this.tag);
                sb.append(")");
                return sb.toString();
            } else {
                str = "ContextSpecific ";
            }
            sb.append(str);
            sb.append("Tag = " + this.tag);
            sb.append(")");
            return sb.toString();
        }
    }

    private static final class LengthInfo {
        private final BigInteger length;
        private final boolean shortForm;

        public LengthInfo(BigInteger bigInteger, boolean z) {
            this.length = bigInteger;
            this.shortForm = z;
        }

        /* access modifiers changed from: private */
        public int intLength() {
            return BigIntegers.intValueExact(this.length);
        }
    }

    public static class Sequence extends OERInputStream {
        /* access modifiers changed from: private */
        public final boolean extensionFlagSet;
        private final int preamble;
        /* access modifiers changed from: private */
        public final boolean[] valuePresent;

        public Sequence(InputStream inputStream, Element element) throws IOException {
            super(inputStream);
            int i;
            if (element.hasPopulatedExtension() || element.getOptionals() > 0 || element.hasDefaultChildren()) {
                int read = this.in.read();
                this.preamble = read;
                if (read >= 0) {
                    this.extensionFlagSet = element.hasPopulatedExtension() && (read & 128) == 128;
                    this.valuePresent = new boolean[element.getChildren().size()];
                    int i2 = element.hasPopulatedExtension() ? 6 : 7;
                    int i3 = 0;
                    for (Element next : element.getChildren()) {
                        if (next.getBaseType() != OERDefinition.BaseType.EXTENSION) {
                            if (next.getBlock() == 0) {
                                if (next.isExplicit()) {
                                    i = i3 + 1;
                                    this.valuePresent[i3] = true;
                                } else {
                                    if (i2 < 0) {
                                        read = inputStream.read();
                                        if (read >= 0) {
                                            i2 = 7;
                                        } else {
                                            throw new EOFException("expecting mask byte sequence");
                                        }
                                    }
                                    i = i3 + 1;
                                    this.valuePresent[i3] = (OERInputStream.bits[i2] & read) > 0;
                                    i2--;
                                }
                                i3 = i;
                            } else {
                                return;
                            }
                        }
                    }
                    return;
                }
                throw new EOFException("expecting preamble byte of sequence");
            }
            this.preamble = 0;
            this.extensionFlagSet = false;
            this.valuePresent = null;
        }

        public boolean hasExtension() {
            return this.extensionFlagSet;
        }

        public boolean hasOptional(int i) {
            return this.valuePresent[i];
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("SEQ(");
            sb.append(hasExtension() ? "Ext " : "");
            if (this.valuePresent != null) {
                int i = 0;
                while (true) {
                    boolean[] zArr = this.valuePresent;
                    if (i >= zArr.length) {
                        break;
                    }
                    sb.append(zArr[i] ? DiskLruCache.VERSION_1 : Constants.GROUP_VIDEOS_FOLDER);
                    i++;
                }
            } else {
                sb.append("*");
            }
            sb.append(")");
            return sb.toString();
        }
    }

    public OERInputStream(InputStream inputStream) {
        super(inputStream);
        this.debugOutput = null;
        this.maxByteAllocation = 1048576;
        this.debugStream = null;
    }

    public OERInputStream(InputStream inputStream, int i) {
        super(inputStream);
        this.debugOutput = null;
        this.debugStream = null;
        this.maxByteAllocation = i;
    }

    private ASN1Encodable absent(Element element) {
        debugPrint(element + "Absent");
        return OEROptional.ABSENT;
    }

    private byte[] allocateArray(int i) {
        if (i <= this.maxByteAllocation) {
            return new byte[i];
        }
        throw new IllegalArgumentException("required byte array size " + i + " was greater than " + this.maxByteAllocation);
    }

    private int countOptionalChildTypes(Element element) {
        int i = 0;
        for (Element isExplicit : element.getChildren()) {
            i += isExplicit.isExplicit() ^ true ? 1 : 0;
        }
        return i;
    }

    public static ASN1Encodable parse(byte[] bArr, Element element) throws IOException {
        return new OERInputStream(new ByteArrayInputStream(bArr)).parse(element);
    }

    public Choice choice() throws IOException {
        return new Choice(this);
    }

    /* access modifiers changed from: protected */
    public void debugPrint(String str) {
        if (this.debugOutput != null) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            int i = -1;
            for (int i2 = 0; i2 != stackTrace.length; i2++) {
                StackTraceElement stackTraceElement = stackTrace[i2];
                if (stackTraceElement.getMethodName().equals("debugPrint")) {
                    i = 0;
                } else if (stackTraceElement.getClassName().contains("OERInput")) {
                    i++;
                }
            }
            while (true) {
                PrintWriter printWriter = this.debugOutput;
                if (i > 0) {
                    printWriter.append("    ");
                    i--;
                } else {
                    printWriter.append(str).append("\n");
                    this.debugOutput.flush();
                    return;
                }
            }
        }
    }

    public BigInteger enumeration() throws IOException {
        int read = read();
        if (read == -1) {
            throw new EOFException("expecting prefix of enumeration");
        } else if ((read & 128) != 128) {
            return BigInteger.valueOf((long) read);
        } else {
            int i = read & 127;
            if (i == 0) {
                return BigInteger.ZERO;
            }
            byte[] bArr = new byte[i];
            if (Streams.readFully(this, bArr) == i) {
                return new BigInteger(1, bArr);
            }
            throw new EOFException("unable to fully read integer component of enumeration");
        }
    }

    public BigInteger int16() throws Exception {
        return parseInt(false, 2);
    }

    public BigInteger int32() throws Exception {
        return parseInt(false, 4);
    }

    public BigInteger int64() throws Exception {
        return parseInt(false, 8);
    }

    public BigInteger int8() throws Exception {
        return parseInt(false, 1);
    }

    /* JADX WARNING: Removed duplicated region for block: B:160:0x045e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.bouncycastle.asn1.ASN1Object parse(org.bouncycastle.oer.Element r10) throws java.io.IOException {
        /*
            r9 = this;
            int[] r0 = org.bouncycastle.oer.OERInputStream.AnonymousClass1.$SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType
            org.bouncycastle.oer.OERDefinition$BaseType r1 = r10.getBaseType()
            int r1 = r1.ordinal()
            r0 = r0[r1]
            java.lang.String r1 = ")"
            java.lang.String r2 = " "
            java.lang.String r3 = ") = "
            r4 = 0
            r5 = 8
            switch(r0) {
                case 1: goto L_0x057f;
                case 2: goto L_0x0577;
                case 3: goto L_0x0565;
                case 4: goto L_0x04ea;
                case 5: goto L_0x03af;
                case 6: goto L_0x030c;
                case 7: goto L_0x02d3;
                case 8: goto L_0x0248;
                case 9: goto L_0x01c0;
                case 10: goto L_0x016c;
                case 11: goto L_0x0125;
                case 12: goto L_0x0094;
                case 13: goto L_0x007d;
                case 14: goto L_0x003c;
                case 15: goto L_0x0030;
                default: goto L_0x0018;
            }
        L_0x0018:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Unhandled type "
            r1.<init>(r2)
            org.bouncycastle.oer.OERDefinition$BaseType r10 = r10.getBaseType()
            r1.append(r10)
            java.lang.String r10 = r1.toString()
            r0.<init>(r10)
            throw r0
        L_0x0030:
            int r10 = r9.read()
            if (r10 != 0) goto L_0x0039
            org.bouncycastle.asn1.ASN1Boolean r10 = org.bouncycastle.asn1.ASN1Boolean.FALSE
            return r10
        L_0x0039:
            org.bouncycastle.asn1.ASN1Boolean r10 = org.bouncycastle.asn1.ASN1Boolean.TRUE
            return r10
        L_0x003c:
            org.bouncycastle.oer.OERInputStream$LengthInfo r10 = r9.readLength()
            int r0 = r10.intLength()
            byte[] r0 = new byte[r0]
            int r1 = org.bouncycastle.util.io.Streams.readFully(r9, r0)
            int r3 = r10.intLength()
            if (r1 != r3) goto L_0x0075
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "ext "
            r1.<init>(r3)
            int r10 = r10.intLength()
            r1.append(r10)
            r1.append(r2)
            java.lang.String r10 = org.bouncycastle.util.encoders.Hex.toHexString(r0)
            r1.append(r10)
            java.lang.String r10 = r1.toString()
            r9.debugPrint(r10)
            org.bouncycastle.asn1.DEROctetString r10 = new org.bouncycastle.asn1.DEROctetString
            r10.<init>((byte[]) r0)
            return r10
        L_0x0075:
            java.io.IOException r10 = new java.io.IOException
            java.lang.String r0 = "could not read all of count of open value in choice (...) "
            r10.<init>(r0)
            throw r10
        L_0x007d:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r10)
            java.lang.String r10 = "NULL"
            r0.append(r10)
            java.lang.String r10 = r0.toString()
            r9.debugPrint(r10)
            org.bouncycastle.asn1.DERNull r10 = org.bouncycastle.asn1.DERNull.INSTANCE
            return r10
        L_0x0094:
            boolean r0 = r10.isFixedLength()
            if (r0 == 0) goto L_0x00a6
            java.math.BigInteger r0 = r10.getLowerBound()
            int r0 = r0.intValue()
            int r0 = r0 / r5
            byte[] r0 = new byte[r0]
            goto L_0x00c8
        L_0x00a6:
            java.math.BigInteger r0 = java.math.BigInteger.ZERO
            java.math.BigInteger r1 = r10.getUpperBound()
            int r0 = r0.compareTo(r1)
            if (r0 <= 0) goto L_0x00bb
            java.math.BigInteger r0 = r10.getUpperBound()
            int r0 = r0.intValue()
            goto L_0x00c3
        L_0x00bb:
            org.bouncycastle.oer.OERInputStream$LengthInfo r0 = r9.readLength()
            int r0 = r0.intLength()
        L_0x00c3:
            int r0 = r0 / r5
            byte[] r0 = r9.allocateArray(r0)
        L_0x00c8:
            org.bouncycastle.util.io.Streams.readFully(r9, r0)
            java.io.PrintWriter r1 = r9.debugOutput
            if (r1 == 0) goto L_0x011f
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r6 = "BIT STRING("
            r2.<init>(r6)
            int r6 = r0.length
            int r6 = r6 * 8
            r2.append(r6)
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.append(r2)
            r2 = 0
        L_0x00ec:
            int r3 = r0.length
            if (r2 == r3) goto L_0x0109
            byte r3 = r0[r2]
            r6 = 0
        L_0x00f2:
            if (r6 >= r5) goto L_0x0106
            r7 = r3 & 128(0x80, float:1.794E-43)
            if (r7 <= 0) goto L_0x00fb
            java.lang.String r7 = "1"
            goto L_0x00fd
        L_0x00fb:
            java.lang.String r7 = "0"
        L_0x00fd:
            r1.append(r7)
            int r3 = r3 << 1
            byte r3 = (byte) r3
            int r6 = r6 + 1
            goto L_0x00f2
        L_0x0106:
            int r2 = r2 + 1
            goto L_0x00ec
        L_0x0109:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r10)
            java.lang.String r10 = r1.toString()
            r2.append(r10)
            java.lang.String r10 = r2.toString()
            r9.debugPrint(r10)
        L_0x011f:
            org.bouncycastle.asn1.DERBitString r10 = new org.bouncycastle.asn1.DERBitString
            r10.<init>((byte[]) r0)
            return r10
        L_0x0125:
            org.bouncycastle.oer.OERInputStream$LengthInfo r0 = r9.readLength()
            int r0 = r0.intLength()
            byte[] r0 = r9.allocateArray(r0)
            int r1 = org.bouncycastle.util.io.Streams.readFully(r9, r0)
            int r2 = r0.length
            if (r1 != r2) goto L_0x0164
            java.lang.String r1 = org.bouncycastle.util.Strings.fromUTF8ByteArray(r0)
            java.io.PrintWriter r2 = r9.debugOutput
            if (r2 == 0) goto L_0x015e
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r10)
            java.lang.String r10 = "UTF8 String ("
            r2.append(r10)
            int r10 = r0.length
            r2.append(r10)
            r2.append(r3)
            r2.append(r1)
            java.lang.String r10 = r2.toString()
            r9.debugPrint(r10)
        L_0x015e:
            org.bouncycastle.asn1.DERUTF8String r10 = new org.bouncycastle.asn1.DERUTF8String
            r10.<init>(r1)
            return r10
        L_0x0164:
            java.io.IOException r10 = new java.io.IOException
            java.lang.String r0 = "could not read all of utf 8 string"
            r10.<init>(r0)
            throw r10
        L_0x016c:
            boolean r0 = r10.isFixedLength()
            if (r0 == 0) goto L_0x017b
            java.math.BigInteger r0 = r10.getUpperBound()
            int r0 = r0.intValue()
            goto L_0x0183
        L_0x017b:
            org.bouncycastle.oer.OERInputStream$LengthInfo r0 = r9.readLength()
            int r0 = r0.intLength()
        L_0x0183:
            byte[] r0 = r9.allocateArray(r0)
            int r1 = org.bouncycastle.util.io.Streams.readFully(r9, r0)
            int r2 = r0.length
            if (r1 != r2) goto L_0x01b8
            java.lang.String r1 = org.bouncycastle.util.Strings.fromByteArray(r0)
            java.io.PrintWriter r2 = r9.debugOutput
            if (r2 == 0) goto L_0x01b2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r4 = "IA5 String ("
            r2.<init>(r4)
            int r0 = r0.length
            r2.append(r0)
            r2.append(r3)
            r2.append(r1)
            java.lang.String r0 = r2.toString()
            java.lang.String r10 = r10.appendLabel(r0)
            r9.debugPrint(r10)
        L_0x01b2:
            org.bouncycastle.asn1.DERIA5String r10 = new org.bouncycastle.asn1.DERIA5String
            r10.<init>(r1)
            return r10
        L_0x01b8:
            java.io.IOException r10 = new java.io.IOException
            java.lang.String r0 = "could not read all of IA5 string"
            r10.<init>(r0)
            throw r10
        L_0x01c0:
            java.math.BigInteger r0 = r10.getUpperBound()
            if (r0 == 0) goto L_0x01dd
            java.math.BigInteger r0 = r10.getUpperBound()
            java.math.BigInteger r1 = r10.getLowerBound()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01dd
            java.math.BigInteger r0 = r10.getUpperBound()
            int r0 = r0.intValue()
            goto L_0x01e5
        L_0x01dd:
            org.bouncycastle.oer.OERInputStream$LengthInfo r0 = r9.readLength()
            int r0 = r0.intLength()
        L_0x01e5:
            byte[] r1 = r9.allocateArray(r0)
            int r5 = org.bouncycastle.util.io.Streams.readFully(r9, r1)
            if (r5 != r0) goto L_0x0230
            java.io.PrintWriter r0 = r9.debugOutput
            if (r0 == 0) goto L_0x022a
            int r0 = r1.length
            r5 = 32
            int r0 = java.lang.Math.min(r0, r5)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r10)
            java.lang.String r10 = "OCTET STRING ("
            r6.append(r10)
            int r10 = r1.length
            r6.append(r10)
            r6.append(r3)
            java.lang.String r10 = org.bouncycastle.util.encoders.Hex.toHexString(r1, r4, r0)
            r6.append(r10)
            r6.append(r2)
            int r10 = r1.length
            if (r10 <= r5) goto L_0x021e
            java.lang.String r10 = "..."
            goto L_0x0220
        L_0x021e:
            java.lang.String r10 = ""
        L_0x0220:
            r6.append(r10)
            java.lang.String r10 = r6.toString()
            r9.debugPrint(r10)
        L_0x022a:
            org.bouncycastle.asn1.DEROctetString r10 = new org.bouncycastle.asn1.DEROctetString
            r10.<init>((byte[]) r1)
            return r10
        L_0x0230:
            java.io.IOException r0 = new java.io.IOException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "did not read all of "
            r1.<init>(r2)
            java.lang.String r10 = r10.getLabel()
            r1.append(r10)
            java.lang.String r10 = r1.toString()
            r0.<init>(r10)
            throw r0
        L_0x0248:
            int r0 = r10.intBytesForRange()
            if (r0 == 0) goto L_0x0266
            int r2 = java.lang.Math.abs(r0)
            byte[] r2 = r9.allocateArray(r2)
            org.bouncycastle.util.io.Streams.readFully(r9, r2)
            if (r0 >= 0) goto L_0x0261
            java.math.BigInteger r0 = new java.math.BigInteger
            r0.<init>(r2)
            goto L_0x02a0
        L_0x0261:
            java.math.BigInteger r0 = org.bouncycastle.util.BigIntegers.fromUnsignedByteArray(r2)
            goto L_0x02a0
        L_0x0266:
            boolean r0 = r10.isLowerRangeZero()
            if (r0 == 0) goto L_0x0286
            org.bouncycastle.oer.OERInputStream$LengthInfo r0 = r9.readLength()
            int r0 = r0.intLength()
            byte[] r2 = r9.allocateArray(r0)
            org.bouncycastle.util.io.Streams.readFully(r9, r2)
            int r0 = r2.length
            if (r0 != 0) goto L_0x027f
            goto L_0x0298
        L_0x027f:
            java.math.BigInteger r0 = new java.math.BigInteger
            r3 = 1
            r0.<init>(r3, r2)
            goto L_0x02a0
        L_0x0286:
            org.bouncycastle.oer.OERInputStream$LengthInfo r0 = r9.readLength()
            int r0 = r0.intLength()
            byte[] r2 = r9.allocateArray(r0)
            org.bouncycastle.util.io.Streams.readFully(r9, r2)
            int r0 = r2.length
            if (r0 != 0) goto L_0x029b
        L_0x0298:
            java.math.BigInteger r0 = java.math.BigInteger.ZERO
            goto L_0x02a0
        L_0x029b:
            java.math.BigInteger r0 = new java.math.BigInteger
            r0.<init>(r2)
        L_0x02a0:
            java.io.PrintWriter r3 = r9.debugOutput
            if (r3 == 0) goto L_0x02cd
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r10)
            java.lang.String r10 = "INTEGER byteLen= "
            r3.append(r10)
            int r10 = r2.length
            r3.append(r10)
            java.lang.String r10 = " hex= "
            r3.append(r10)
            r10 = 16
            java.lang.String r10 = r0.toString(r10)
            r3.append(r10)
            r3.append(r1)
            java.lang.String r10 = r3.toString()
            r9.debugPrint(r10)
        L_0x02cd:
            org.bouncycastle.asn1.ASN1Integer r10 = new org.bouncycastle.asn1.ASN1Integer
            r10.<init>((java.math.BigInteger) r0)
            return r10
        L_0x02d3:
            java.math.BigInteger r0 = r9.enumeration()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r10)
            java.lang.String r2 = "ENUM("
            r1.append(r2)
            r1.append(r0)
            r1.append(r3)
            java.util.List r10 = r10.getChildren()
            int r2 = r0.intValue()
            java.lang.Object r10 = r10.get(r2)
            org.bouncycastle.oer.Element r10 = (org.bouncycastle.oer.Element) r10
            java.lang.String r10 = r10.getLabel()
            r1.append(r10)
            java.lang.String r10 = r1.toString()
            r9.debugPrint(r10)
            org.bouncycastle.asn1.ASN1Enumerated r10 = new org.bouncycastle.asn1.ASN1Enumerated
            r10.<init>((java.math.BigInteger) r0)
            return r10
        L_0x030c:
            org.bouncycastle.oer.OERInputStream$Choice r0 = r9.choice()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = r0.toString()
            r1.append(r3)
            r1.append(r2)
            int r2 = r0.tag
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r9.debugPrint(r1)
            boolean r1 = r0.isContextSpecific()
            if (r1 == 0) goto L_0x0383
            java.util.List r1 = r10.getChildren()
            int r2 = r0.getTag()
            java.lang.Object r1 = r1.get(r2)
            org.bouncycastle.oer.Element r1 = (org.bouncycastle.oer.Element) r1
            org.bouncycastle.oer.Element r10 = org.bouncycastle.oer.Element.expandDeferredDefinition(r1, r10)
            int r1 = r10.getBlock()
            if (r1 <= 0) goto L_0x0366
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Chosen (Ext): "
            r1.<init>(r2)
            r1.append(r10)
            java.lang.String r1 = r1.toString()
            r9.debugPrint(r1)
            org.bouncycastle.asn1.DERTaggedObject r1 = new org.bouncycastle.asn1.DERTaggedObject
            int r0 = r0.tag
            org.bouncycastle.asn1.ASN1Encodable r10 = r9.parseOpenType(r10)
            r1.<init>(r0, r10)
            return r1
        L_0x0366:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Chosen: "
            r1.<init>(r2)
            r1.append(r10)
            java.lang.String r1 = r1.toString()
            r9.debugPrint(r1)
            org.bouncycastle.asn1.DERTaggedObject r1 = new org.bouncycastle.asn1.DERTaggedObject
            int r0 = r0.tag
            org.bouncycastle.asn1.ASN1Object r10 = r9.parse(r10)
            r1.<init>(r0, r10)
            return r1
        L_0x0383:
            boolean r10 = r0.isApplicationTagClass()
            java.lang.String r1 = "Unimplemented tag type"
            if (r10 != 0) goto L_0x03a9
            boolean r10 = r0.isPrivateTagClass()
            if (r10 != 0) goto L_0x03a3
            boolean r10 = r0.isUniversalTagClass()
            if (r10 == 0) goto L_0x039d
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            r10.<init>(r1)
            throw r10
        L_0x039d:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            r10.<init>(r1)
            throw r10
        L_0x03a3:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            r10.<init>(r1)
            throw r10
        L_0x03a9:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            r10.<init>(r1)
            throw r10
        L_0x03af:
            org.bouncycastle.oer.OERInputStream$Sequence r0 = new org.bouncycastle.oer.OERInputStream$Sequence
            java.io.InputStream r1 = r9.in
            r0.<init>(r1, r10)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r10)
            java.lang.String r2 = r0.toString()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r9.debugPrint(r1)
            org.bouncycastle.asn1.ASN1EncodableVector r1 = new org.bouncycastle.asn1.ASN1EncodableVector
            r1.<init>()
            java.util.List r2 = r10.getChildren()
            r3 = 0
        L_0x03d6:
            int r6 = r2.size()
            if (r3 >= r6) goto L_0x0458
            java.lang.Object r6 = r2.get(r3)
            org.bouncycastle.oer.Element r6 = (org.bouncycastle.oer.Element) r6
            org.bouncycastle.oer.OERDefinition$BaseType r7 = r6.getBaseType()
            org.bouncycastle.oer.OERDefinition$BaseType r8 = org.bouncycastle.oer.OERDefinition.BaseType.EXTENSION
            if (r7 != r8) goto L_0x03ec
            goto L_0x0454
        L_0x03ec:
            int r7 = r6.getBlock()
            if (r7 <= 0) goto L_0x03f3
            goto L_0x0458
        L_0x03f3:
            org.bouncycastle.oer.Element r6 = org.bouncycastle.oer.Element.expandDeferredDefinition(r6, r10)
            org.bouncycastle.oer.Switch r7 = r6.getaSwitch()
            if (r7 == 0) goto L_0x0417
            org.bouncycastle.oer.Switch r7 = r6.getaSwitch()
            org.bouncycastle.oer.SwitchIndexer$Asn1EncodableVectorIndexer r8 = new org.bouncycastle.oer.SwitchIndexer$Asn1EncodableVectorIndexer
            r8.<init>(r1)
            org.bouncycastle.oer.Element r7 = r7.result(r8)
            org.bouncycastle.oer.Element r8 = r7.getParent()
            if (r8 == r10) goto L_0x0418
            org.bouncycastle.oer.Element r8 = new org.bouncycastle.oer.Element
            r8.<init>(r7, r10)
            r7 = r8
            goto L_0x0418
        L_0x0417:
            r7 = r6
        L_0x0418:
            boolean[] r8 = r0.valuePresent
            if (r8 != 0) goto L_0x0426
            org.bouncycastle.asn1.ASN1Object r6 = r9.parse(r7)
        L_0x0422:
            r1.add(r6)
            goto L_0x0454
        L_0x0426:
            boolean[] r8 = r0.valuePresent
            boolean r8 = r8[r3]
            if (r8 == 0) goto L_0x0444
            boolean r6 = r7.isExplicit()
            if (r6 == 0) goto L_0x043b
            org.bouncycastle.asn1.ASN1Object r6 = r9.parse(r7)     // Catch:{ all -> 0x0439 }
            goto L_0x0422
        L_0x0439:
            r10 = move-exception
            throw r10
        L_0x043b:
            org.bouncycastle.asn1.ASN1Object r6 = r9.parse(r7)
            org.bouncycastle.oer.OEROptional r6 = org.bouncycastle.oer.OEROptional.getInstance(r6)
            goto L_0x0422
        L_0x0444:
            org.bouncycastle.asn1.ASN1Encodable r7 = r7.getDefaultValue()
            if (r7 == 0) goto L_0x044f
            org.bouncycastle.asn1.ASN1Encodable r6 = r6.getDefaultValue()
            goto L_0x0422
        L_0x044f:
            org.bouncycastle.asn1.ASN1Encodable r6 = r9.absent(r6)
            goto L_0x0422
        L_0x0454:
            int r3 = r3 + 1
            goto L_0x03d6
        L_0x0458:
            boolean r10 = r0.extensionFlagSet
            if (r10 == 0) goto L_0x04e4
            org.bouncycastle.oer.OERInputStream$LengthInfo r10 = r9.readLength()
            int r10 = r10.intLength()
            byte[] r10 = r9.allocateArray(r10)
            java.io.InputStream r0 = r9.in
            int r0 = org.bouncycastle.util.io.Streams.readFully(r0, r10)
            int r6 = r10.length
            if (r0 != r6) goto L_0x04dc
            int r0 = r10.length
            int r0 = r0 * 8
            byte r4 = r10[r4]
            int r0 = r0 - r4
        L_0x0479:
            int r4 = r2.size()
            if (r3 < r4) goto L_0x0481
            if (r5 >= r0) goto L_0x04e4
        L_0x0481:
            int r4 = r2.size()
            if (r3 >= r4) goto L_0x048e
            java.lang.Object r4 = r2.get(r3)
            org.bouncycastle.oer.Element r4 = (org.bouncycastle.oer.Element) r4
            goto L_0x048f
        L_0x048e:
            r4 = 0
        L_0x048f:
            if (r4 != 0) goto L_0x04b0
            int r4 = r5 / 8
            byte r4 = r10[r4]
            int[] r6 = bitsR
            int r7 = r5 % 8
            r6 = r6[r7]
            r4 = r4 & r6
            if (r4 == 0) goto L_0x04cf
            org.bouncycastle.oer.OERInputStream$LengthInfo r4 = r9.readLength()
            int r4 = r4.intLength()
        L_0x04a6:
            int r4 = r4 + -1
            if (r4 < 0) goto L_0x04cf
            java.io.InputStream r6 = r9.in
            r6.read()
            goto L_0x04a6
        L_0x04b0:
            if (r5 >= r0) goto L_0x04c4
            int r6 = r5 / 8
            byte r6 = r10[r6]
            int[] r7 = bitsR
            int r8 = r5 % 8
            r7 = r7[r8]
            r6 = r6 & r7
            if (r6 == 0) goto L_0x04c4
            org.bouncycastle.asn1.ASN1Encodable r4 = r9.parseOpenType(r4)
            goto L_0x04cc
        L_0x04c4:
            boolean r4 = r4.isExplicit()
            if (r4 != 0) goto L_0x04d4
            org.bouncycastle.oer.OEROptional r4 = org.bouncycastle.oer.OEROptional.ABSENT
        L_0x04cc:
            r1.add(r4)
        L_0x04cf:
            int r5 = r5 + 1
            int r3 = r3 + 1
            goto L_0x0479
        L_0x04d4:
            java.io.IOException r10 = new java.io.IOException
            java.lang.String r0 = "extension is marked as explicit but is not defined in presence list"
            r10.<init>(r0)
            throw r10
        L_0x04dc:
            java.io.IOException r10 = new java.io.IOException
            java.lang.String r0 = "did not fully read presence list."
            r10.<init>(r0)
            throw r10
        L_0x04e4:
            org.bouncycastle.asn1.DERSequence r10 = new org.bouncycastle.asn1.DERSequence
            r10.<init>((org.bouncycastle.asn1.ASN1EncodableVector) r1)
            return r10
        L_0x04ea:
            org.bouncycastle.oer.OERInputStream$LengthInfo r0 = r9.readLength()
            int r0 = r0.intLength()
            byte[] r0 = r9.allocateArray(r0)
            int r2 = org.bouncycastle.util.io.Streams.readFully(r9, r0)
            int r3 = r0.length
            if (r2 != r3) goto L_0x055d
            java.math.BigInteger r0 = org.bouncycastle.util.BigIntegers.fromUnsignedByteArray(r0)
            int r0 = r0.intValue()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r10)
            java.lang.String r3 = "(len = "
            r2.append(r3)
            r2.append(r0)
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            r9.debugPrint(r1)
            org.bouncycastle.asn1.ASN1EncodableVector r1 = new org.bouncycastle.asn1.ASN1EncodableVector
            r1.<init>()
            java.util.List r2 = r10.getChildren()
            java.lang.Object r2 = r2.get(r4)
            org.bouncycastle.oer.Element r2 = (org.bouncycastle.oer.Element) r2
            org.bouncycastle.oer.Switch r2 = r2.getaSwitch()
            if (r2 != 0) goto L_0x0555
            r2 = 0
        L_0x0535:
            if (r2 >= r0) goto L_0x054f
            java.util.List r3 = r10.getChildren()
            java.lang.Object r3 = r3.get(r4)
            org.bouncycastle.oer.Element r3 = (org.bouncycastle.oer.Element) r3
            org.bouncycastle.oer.Element r3 = org.bouncycastle.oer.Element.expandDeferredDefinition(r3, r10)
            org.bouncycastle.asn1.ASN1Object r3 = r9.parse(r3)
            r1.add(r3)
            int r2 = r2 + 1
            goto L_0x0535
        L_0x054f:
            org.bouncycastle.asn1.DERSequence r10 = new org.bouncycastle.asn1.DERSequence
            r10.<init>((org.bouncycastle.asn1.ASN1EncodableVector) r1)
            return r10
        L_0x0555:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "element def for item in SEQ OF has a switch, switches only supported in sequences"
            r10.<init>(r0)
            throw r10
        L_0x055d:
            java.io.IOException r10 = new java.io.IOException
            java.lang.String r0 = "could not read all of count of seq-of values"
            r10.<init>(r0)
            throw r10
        L_0x0565:
            org.bouncycastle.oer.Element r0 = new org.bouncycastle.oer.Element
            org.bouncycastle.oer.ElementSupplier r1 = r10.getElementSupplier()
            org.bouncycastle.oer.Element r1 = r1.build()
            r0.<init>(r1, r10)
            org.bouncycastle.asn1.ASN1Object r10 = r9.parse(r0)
            return r10
        L_0x0577:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "A switch element should only be found within a sequence."
            r10.<init>(r0)
            throw r10
        L_0x057f:
            org.bouncycastle.oer.ElementSupplier r0 = r10.resolveSupplier()
            org.bouncycastle.oer.Element r1 = new org.bouncycastle.oer.Element
            org.bouncycastle.oer.Element r0 = r0.build()
            r1.<init>(r0, r10)
            org.bouncycastle.asn1.ASN1Object r10 = r9.parse(r1)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.oer.OERInputStream.parse(org.bouncycastle.oer.Element):org.bouncycastle.asn1.ASN1Object");
    }

    public BigInteger parseInt(boolean z, int i) throws Exception {
        byte[] bArr = new byte[i];
        if (Streams.readFully(this, bArr) == i) {
            return z ? new BigInteger(1, bArr) : new BigInteger(bArr);
        }
        throw new IllegalStateException("integer not fully read");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x002e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.bouncycastle.asn1.ASN1Encodable parseOpenType(org.bouncycastle.oer.Element r4) throws java.io.IOException {
        /*
            r3 = this;
            org.bouncycastle.oer.OERInputStream$LengthInfo r0 = r3.readLength()
            int r0 = r0.intLength()
            byte[] r0 = r3.allocateArray(r0)
            java.io.InputStream r1 = r3.in
            int r1 = org.bouncycastle.util.io.Streams.readFully(r1, r0)
            int r2 = r0.length
            if (r1 != r2) goto L_0x0032
            r1 = 0
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream     // Catch:{ all -> 0x002b }
            r2.<init>(r0)     // Catch:{ all -> 0x002b }
            org.bouncycastle.oer.OERInputStream r0 = new org.bouncycastle.oer.OERInputStream     // Catch:{ all -> 0x002b }
            r0.<init>(r2)     // Catch:{ all -> 0x002b }
            org.bouncycastle.asn1.ASN1Object r4 = r0.parse(r4)     // Catch:{ all -> 0x0028 }
            r0.close()
            return r4
        L_0x0028:
            r4 = move-exception
            r1 = r0
            goto L_0x002c
        L_0x002b:
            r4 = move-exception
        L_0x002c:
            if (r1 == 0) goto L_0x0031
            r1.close()
        L_0x0031:
            throw r4
        L_0x0032:
            java.io.IOException r4 = new java.io.IOException
            java.lang.String r0 = "did not fully read open type as raw bytes"
            r4.<init>(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.oer.OERInputStream.parseOpenType(org.bouncycastle.oer.Element):org.bouncycastle.asn1.ASN1Encodable");
    }

    public LengthInfo readLength() throws IOException {
        int read = read();
        if (read == -1) {
            throw new EOFException("expecting length");
        } else if ((read & 128) == 0) {
            StringBuilder sb = new StringBuilder("Len (Short form): ");
            int i = read & 127;
            sb.append(i);
            debugPrint(sb.toString());
            return new LengthInfo(BigInteger.valueOf((long) i), true);
        } else {
            int i2 = read & 127;
            byte[] bArr = new byte[i2];
            if (Streams.readFully(this, bArr) == i2) {
                debugPrint("Len (Long Form): " + i2 + " actual len: " + Hex.toHexString(bArr));
                return new LengthInfo(BigIntegers.fromUnsignedByteArray(bArr), false);
            }
            throw new EOFException("did not read all bytes of length definition");
        }
    }

    public BigInteger uint16() throws Exception {
        return parseInt(true, 2);
    }

    public BigInteger uint32() throws Exception {
        return parseInt(true, 4);
    }

    public BigInteger uint64() throws Exception {
        return parseInt(false, 8);
    }

    public BigInteger uint8() throws Exception {
        return parseInt(true, 1);
    }
}
