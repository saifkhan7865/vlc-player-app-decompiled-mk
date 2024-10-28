package org.bouncycastle.oer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.util.BigIntegers;

public class OEROutputStream extends OutputStream {
    private static final int[] bits = {1, 2, 4, 8, 16, 32, 64, 128};
    protected PrintWriter debugOutput = null;
    private final OutputStream out;

    /* renamed from: org.bouncycastle.oer.OEROutputStream$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType;

        /* JADX WARNING: Can't wrap try/catch for region: R(28:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|(3:27|28|30)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(30:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|30) */
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
                org.bouncycastle.oer.OERDefinition$BaseType r1 = org.bouncycastle.oer.OERDefinition.BaseType.Supplier     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.bouncycastle.oer.OERDefinition$BaseType r1 = org.bouncycastle.oer.OERDefinition.BaseType.SEQ     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.bouncycastle.oer.OERDefinition$BaseType r1 = org.bouncycastle.oer.OERDefinition.BaseType.SEQ_OF     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.bouncycastle.oer.OERDefinition$BaseType r1 = org.bouncycastle.oer.OERDefinition.BaseType.CHOICE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType     // Catch:{ NoSuchFieldError -> 0x003e }
                org.bouncycastle.oer.OERDefinition$BaseType r1 = org.bouncycastle.oer.OERDefinition.BaseType.ENUM     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.bouncycastle.oer.OERDefinition$BaseType r1 = org.bouncycastle.oer.OERDefinition.BaseType.INT     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType     // Catch:{ NoSuchFieldError -> 0x0054 }
                org.bouncycastle.oer.OERDefinition$BaseType r1 = org.bouncycastle.oer.OERDefinition.BaseType.OCTET_STRING     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType     // Catch:{ NoSuchFieldError -> 0x0060 }
                org.bouncycastle.oer.OERDefinition$BaseType r1 = org.bouncycastle.oer.OERDefinition.BaseType.IA5String     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType     // Catch:{ NoSuchFieldError -> 0x006c }
                org.bouncycastle.oer.OERDefinition$BaseType r1 = org.bouncycastle.oer.OERDefinition.BaseType.UTF8_STRING     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType     // Catch:{ NoSuchFieldError -> 0x0078 }
                org.bouncycastle.oer.OERDefinition$BaseType r1 = org.bouncycastle.oer.OERDefinition.BaseType.BIT_STRING     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType     // Catch:{ NoSuchFieldError -> 0x0084 }
                org.bouncycastle.oer.OERDefinition$BaseType r1 = org.bouncycastle.oer.OERDefinition.BaseType.NULL     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r0 = $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType     // Catch:{ NoSuchFieldError -> 0x0090 }
                org.bouncycastle.oer.OERDefinition$BaseType r1 = org.bouncycastle.oer.OERDefinition.BaseType.EXTENSION     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                int[] r0 = $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType     // Catch:{ NoSuchFieldError -> 0x009c }
                org.bouncycastle.oer.OERDefinition$BaseType r1 = org.bouncycastle.oer.OERDefinition.BaseType.ENUM_ITEM     // Catch:{ NoSuchFieldError -> 0x009c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009c }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009c }
            L_0x009c:
                int[] r0 = $SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType     // Catch:{ NoSuchFieldError -> 0x00a8 }
                org.bouncycastle.oer.OERDefinition$BaseType r1 = org.bouncycastle.oer.OERDefinition.BaseType.BOOLEAN     // Catch:{ NoSuchFieldError -> 0x00a8 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a8 }
                r2 = 14
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00a8 }
            L_0x00a8:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.oer.OEROutputStream.AnonymousClass1.<clinit>():void");
        }
    }

    public OEROutputStream(OutputStream outputStream) {
        this.out = outputStream;
    }

    public static int byteLength(long j) {
        int i = 8;
        while (i > 0 && (-72057594037927936L & j) == 0) {
            j <<= 8;
            i--;
        }
        return i;
    }

    private void encodeLength(long j) throws IOException {
        if (j <= 127) {
            this.out.write((int) j);
            return;
        }
        byte[] asUnsignedByteArray = BigIntegers.asUnsignedByteArray(BigInteger.valueOf(j));
        this.out.write(asUnsignedByteArray.length | 128);
        this.out.write(asUnsignedByteArray);
    }

    private void encodeQuantity(long j) throws IOException {
        byte[] asUnsignedByteArray = BigIntegers.asUnsignedByteArray(BigInteger.valueOf(j));
        this.out.write(asUnsignedByteArray.length);
        this.out.write(asUnsignedByteArray);
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

    public void write(int i) throws IOException {
        this.out.write(i);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:122:0x0314, code lost:
        r12.out.flush();
        debugPrint(r14.appendLabel(""));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00ab, code lost:
        r13 = r14.appendLabel("");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00af, code lost:
        debugPrint(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00b2, code lost:
        r12.out.flush();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:261:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:262:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x011d, code lost:
        r13 = r14.appendLabel(r14.rangeExpression());
     */
    /* JADX WARNING: Removed duplicated region for block: B:184:0x042e  */
    /* JADX WARNING: Removed duplicated region for block: B:188:0x043e  */
    /* JADX WARNING: Removed duplicated region for block: B:205:0x048a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void write(org.bouncycastle.asn1.ASN1Encodable r13, org.bouncycastle.oer.Element r14) throws java.io.IOException {
        /*
            r12 = this;
            org.bouncycastle.oer.OEROptional r0 = org.bouncycastle.oer.OEROptional.ABSENT
            if (r13 != r0) goto L_0x0005
            return
        L_0x0005:
            boolean r0 = r13 instanceof org.bouncycastle.oer.OEROptional
            if (r0 == 0) goto L_0x0013
            org.bouncycastle.oer.OEROptional r13 = (org.bouncycastle.oer.OEROptional) r13
            org.bouncycastle.asn1.ASN1Encodable r13 = r13.get()
            r12.write(r13, r14)
            return
        L_0x0013:
            org.bouncycastle.asn1.ASN1Primitive r13 = r13.toASN1Primitive()
            int[] r0 = org.bouncycastle.oer.OEROutputStream.AnonymousClass1.$SwitchMap$org$bouncycastle$oer$OERDefinition$BaseType
            org.bouncycastle.oer.OERDefinition$BaseType r1 = r14.getBaseType()
            int r1 = r1.ordinal()
            r0 = r0[r1]
            java.lang.String r1 = " "
            r2 = 255(0xff, float:3.57E-43)
            r3 = 6
            java.lang.String r4 = ""
            r5 = 1
            r6 = 0
            switch(r0) {
                case 1: goto L_0x0502;
                case 2: goto L_0x032a;
                case 3: goto L_0x02da;
                case 4: goto L_0x025b;
                case 5: goto L_0x01c2;
                case 6: goto L_0x0126;
                case 7: goto L_0x0104;
                case 8: goto L_0x00b9;
                case 9: goto L_0x0095;
                case 10: goto L_0x0069;
                case 11: goto L_0x002f;
                case 12: goto L_0x004e;
                case 13: goto L_0x002f;
                case 14: goto L_0x0031;
                default: goto L_0x002f;
            }
        L_0x002f:
            goto L_0x050d
        L_0x0031:
            java.lang.String r14 = r14.getLabel()
            r12.debugPrint(r14)
            org.bouncycastle.asn1.ASN1Boolean r13 = org.bouncycastle.asn1.ASN1Boolean.getInstance((java.lang.Object) r13)
            boolean r13 = r13.isTrue()
            if (r13 == 0) goto L_0x0048
            java.io.OutputStream r13 = r12.out
            r13.write(r2)
            goto L_0x00b2
        L_0x0048:
            java.io.OutputStream r13 = r12.out
            r13.write(r6)
            goto L_0x00b2
        L_0x004e:
            org.bouncycastle.asn1.ASN1OctetString r13 = org.bouncycastle.asn1.ASN1OctetString.getInstance(r13)
            byte[] r13 = r13.getOctets()
            boolean r0 = r14.isFixedLength()
            if (r0 == 0) goto L_0x005d
            goto L_0x0062
        L_0x005d:
            int r0 = r13.length
            long r0 = (long) r0
            r12.encodeLength(r0)
        L_0x0062:
            java.io.OutputStream r0 = r12.out
            r0.write(r13)
            goto L_0x011d
        L_0x0069:
            org.bouncycastle.asn1.ASN1BitString r13 = org.bouncycastle.asn1.ASN1BitString.getInstance(r13)
            byte[] r0 = r13.getBytes()
            boolean r1 = r14.isFixedLength()
            if (r1 == 0) goto L_0x0078
            goto L_0x0087
        L_0x0078:
            int r13 = r13.getPadBits()
            int r1 = r0.length
            int r1 = r1 + r5
            long r1 = (long) r1
            r12.encodeLength(r1)
            java.io.OutputStream r1 = r12.out
            r1.write(r13)
        L_0x0087:
            java.io.OutputStream r13 = r12.out
            r13.write(r0)
            java.lang.String r13 = r14.rangeExpression()
            java.lang.String r13 = r14.appendLabel(r13)
            goto L_0x00af
        L_0x0095:
            org.bouncycastle.asn1.ASN1UTF8String r13 = org.bouncycastle.asn1.ASN1UTF8String.getInstance(r13)
            java.lang.String r13 = r13.getString()
            byte[] r13 = org.bouncycastle.util.Strings.toUTF8ByteArray((java.lang.String) r13)
            int r0 = r13.length
            long r0 = (long) r0
            r12.encodeLength(r0)
            java.io.OutputStream r0 = r12.out
            r0.write(r13)
        L_0x00ab:
            java.lang.String r13 = r14.appendLabel(r4)
        L_0x00af:
            r12.debugPrint(r13)
        L_0x00b2:
            java.io.OutputStream r13 = r12.out
            r13.flush()
            goto L_0x050d
        L_0x00b9:
            org.bouncycastle.asn1.ASN1IA5String r13 = org.bouncycastle.asn1.ASN1IA5String.getInstance(r13)
            byte[] r13 = r13.getOctets()
            boolean r0 = r14.isFixedLength()
            if (r0 == 0) goto L_0x00f2
            java.math.BigInteger r0 = r14.getUpperBound()
            int r0 = r0.intValue()
            int r2 = r13.length
            if (r0 != r2) goto L_0x00d3
            goto L_0x00f2
        L_0x00d3:
            java.io.IOException r0 = new java.io.IOException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "IA5String string length does not equal declared fixed length "
            r2.<init>(r3)
            int r13 = r13.length
            r2.append(r13)
            r2.append(r1)
            java.math.BigInteger r13 = r14.getUpperBound()
            r2.append(r13)
            java.lang.String r13 = r2.toString()
            r0.<init>(r13)
            throw r0
        L_0x00f2:
            boolean r0 = r14.isFixedLength()
            if (r0 == 0) goto L_0x00f9
            goto L_0x00fe
        L_0x00f9:
            int r0 = r13.length
            long r0 = (long) r0
            r12.encodeLength(r0)
        L_0x00fe:
            java.io.OutputStream r0 = r12.out
            r0.write(r13)
            goto L_0x00ab
        L_0x0104:
            org.bouncycastle.asn1.ASN1OctetString r13 = org.bouncycastle.asn1.ASN1OctetString.getInstance(r13)
            byte[] r13 = r13.getOctets()
            boolean r0 = r14.isFixedLength()
            if (r0 == 0) goto L_0x0113
            goto L_0x0118
        L_0x0113:
            int r0 = r13.length
            long r0 = (long) r0
            r12.encodeLength(r0)
        L_0x0118:
            java.io.OutputStream r0 = r12.out
            r0.write(r13)
        L_0x011d:
            java.lang.String r13 = r14.rangeExpression()
            java.lang.String r13 = r14.appendLabel(r13)
            goto L_0x00af
        L_0x0126:
            org.bouncycastle.asn1.ASN1Integer r13 = org.bouncycastle.asn1.ASN1Integer.getInstance(r13)
            int r0 = r14.intBytesForRange()
            if (r0 <= 0) goto L_0x015f
            java.math.BigInteger r13 = r13.getValue()
            byte[] r13 = org.bouncycastle.util.BigIntegers.asUnsignedByteArray(r0, r13)
            if (r0 == r5) goto L_0x0159
            r1 = 2
            if (r0 == r1) goto L_0x0159
            r1 = 4
            if (r0 == r1) goto L_0x0159
            r1 = 8
            if (r0 != r1) goto L_0x0145
            goto L_0x0159
        L_0x0145:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            java.lang.String r1 = "unknown uint length "
            r14.<init>(r1)
            r14.append(r0)
            java.lang.String r14 = r14.toString()
            r13.<init>(r14)
            throw r13
        L_0x0159:
            java.io.OutputStream r0 = r12.out
            r0.write(r13)
            goto L_0x011d
        L_0x015f:
            if (r0 >= 0) goto L_0x01a3
            java.math.BigInteger r13 = r13.getValue()
            r1 = -8
            if (r0 == r1) goto L_0x0194
            r1 = -4
            if (r0 == r1) goto L_0x018b
            r1 = -2
            if (r0 == r1) goto L_0x0182
            r1 = -1
            if (r0 != r1) goto L_0x017a
            byte[] r0 = new byte[r5]
            byte r13 = org.bouncycastle.util.BigIntegers.byteValueExact(r13)
            r0[r6] = r13
            goto L_0x019c
        L_0x017a:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "unknown twos compliment length"
            r13.<init>(r14)
            throw r13
        L_0x0182:
            short r13 = org.bouncycastle.util.BigIntegers.shortValueExact(r13)
            byte[] r0 = org.bouncycastle.util.Pack.shortToBigEndian(r13)
            goto L_0x019c
        L_0x018b:
            int r13 = org.bouncycastle.util.BigIntegers.intValueExact(r13)
            byte[] r0 = org.bouncycastle.util.Pack.intToBigEndian((int) r13)
            goto L_0x019c
        L_0x0194:
            long r0 = org.bouncycastle.util.BigIntegers.longValueExact(r13)
            byte[] r0 = org.bouncycastle.util.Pack.longToBigEndian((long) r0)
        L_0x019c:
            java.io.OutputStream r13 = r12.out
            r13.write(r0)
            goto L_0x011d
        L_0x01a3:
            boolean r0 = r14.isLowerRangeZero()
            java.math.BigInteger r13 = r13.getValue()
            if (r0 == 0) goto L_0x01b2
            byte[] r13 = org.bouncycastle.util.BigIntegers.asUnsignedByteArray(r13)
            goto L_0x01b6
        L_0x01b2:
            byte[] r13 = r13.toByteArray()
        L_0x01b6:
            int r0 = r13.length
            long r0 = (long) r0
            r12.encodeLength(r0)
            java.io.OutputStream r0 = r12.out
            r0.write(r13)
            goto L_0x011d
        L_0x01c2:
            boolean r0 = r13 instanceof org.bouncycastle.asn1.ASN1Integer
            if (r0 == 0) goto L_0x01cf
            org.bouncycastle.asn1.ASN1Integer r13 = org.bouncycastle.asn1.ASN1Integer.getInstance(r13)
            java.math.BigInteger r13 = r13.getValue()
            goto L_0x01d7
        L_0x01cf:
            org.bouncycastle.asn1.ASN1Enumerated r13 = org.bouncycastle.asn1.ASN1Enumerated.getInstance(r13)
            java.math.BigInteger r13 = r13.getValue()
        L_0x01d7:
            java.util.List r0 = r14.getChildren()
            java.util.Iterator r0 = r0.iterator()
        L_0x01df:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x0234
            java.lang.Object r3 = r0.next()
            org.bouncycastle.oer.Element r3 = (org.bouncycastle.oer.Element) r3
            org.bouncycastle.oer.Element r3 = org.bouncycastle.oer.Element.expandDeferredDefinition(r3, r14)
            java.math.BigInteger r3 = r3.getEnumValue()
            boolean r3 = r3.equals(r13)
            if (r3 == 0) goto L_0x01df
            r0 = 127(0x7f, double:6.27E-322)
            java.math.BigInteger r0 = java.math.BigInteger.valueOf(r0)
            int r0 = r13.compareTo(r0)
            if (r0 <= 0) goto L_0x0218
            byte[] r13 = r13.toByteArray()
            int r0 = r13.length
            r0 = r0 & r2
            r0 = r0 | 128(0x80, float:1.794E-43)
            java.io.OutputStream r1 = r12.out
            r1.write(r0)
            java.io.OutputStream r0 = r12.out
            r0.write(r13)
            goto L_0x0223
        L_0x0218:
            java.io.OutputStream r0 = r12.out
            int r13 = r13.intValue()
            r13 = r13 & 127(0x7f, float:1.78E-43)
            r0.write(r13)
        L_0x0223:
            java.io.OutputStream r13 = r12.out
            r13.flush()
            java.lang.String r13 = r14.rangeExpression()
            java.lang.String r13 = r14.appendLabel(r13)
            r12.debugPrint(r13)
            return
        L_0x0234:
            java.lang.IllegalArgumentException r14 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "enum value "
            r0.<init>(r2)
            r0.append(r13)
            r0.append(r1)
            byte[] r13 = r13.toByteArray()
            java.lang.String r13 = org.bouncycastle.util.encoders.Hex.toHexString(r13)
            r0.append(r13)
            java.lang.String r13 = " no in defined child list"
            r0.append(r13)
            java.lang.String r13 = r0.toString()
            r14.<init>(r13)
            throw r14
        L_0x025b:
            org.bouncycastle.asn1.ASN1Primitive r13 = r13.toASN1Primitive()
            org.bouncycastle.oer.BitBuilder r0 = new org.bouncycastle.oer.BitBuilder
            r0.<init>()
            boolean r1 = r13 instanceof org.bouncycastle.asn1.ASN1TaggedObject
            if (r1 == 0) goto L_0x02d2
            org.bouncycastle.asn1.ASN1TaggedObject r13 = (org.bouncycastle.asn1.ASN1TaggedObject) r13
            int r2 = r13.getTagClass()
            r4 = r2 & 128(0x80, float:1.794E-43)
            org.bouncycastle.oer.BitBuilder r4 = r0.writeBit(r4)
            r5 = 64
            r2 = r2 & r5
            r4.writeBit(r2)
            int r2 = r13.getTagNo()
            org.bouncycastle.asn1.ASN1Object r4 = r13.getBaseObject()
            org.bouncycastle.asn1.ASN1Primitive r4 = r4.toASN1Primitive()
            r6 = 63
            if (r2 > r6) goto L_0x028f
            long r6 = (long) r2
            r0.writeBits(r6, r3)
            goto L_0x0297
        L_0x028f:
            r6 = 255(0xff, double:1.26E-321)
            r0.writeBits(r6, r3)
            r0.write7BitBytes((int) r2)
        L_0x0297:
            java.io.PrintWriter r3 = r12.debugOutput
            if (r3 == 0) goto L_0x02af
            if (r1 == 0) goto L_0x02af
            int r13 = r13.getTagClass()
            if (r5 != r13) goto L_0x02a6
            java.lang.String r13 = "AS"
            goto L_0x02a8
        L_0x02a6:
            java.lang.String r13 = "CS"
        L_0x02a8:
            java.lang.String r13 = r14.appendLabel(r13)
            r12.debugPrint(r13)
        L_0x02af:
            java.io.OutputStream r13 = r12.out
            r0.writeAndClear(r13)
            java.util.List r13 = r14.getChildren()
            java.lang.Object r13 = r13.get(r2)
            org.bouncycastle.oer.Element r13 = (org.bouncycastle.oer.Element) r13
            org.bouncycastle.oer.Element r13 = org.bouncycastle.oer.Element.expandDeferredDefinition(r13, r14)
            int r14 = r13.getBlock()
            if (r14 <= 0) goto L_0x02cd
            r12.writePlainType(r4, r13)
            goto L_0x00b2
        L_0x02cd:
            r12.write(r4, r13)
            goto L_0x00b2
        L_0x02d2:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "only support tagged objects"
            r13.<init>(r14)
            throw r13
        L_0x02da:
            boolean r0 = r13 instanceof org.bouncycastle.asn1.ASN1Set
            if (r0 == 0) goto L_0x02ed
            org.bouncycastle.asn1.ASN1Set r13 = (org.bouncycastle.asn1.ASN1Set) r13
            java.util.Enumeration r0 = r13.getObjects()
            int r13 = r13.size()
        L_0x02e8:
            long r1 = (long) r13
            r12.encodeQuantity(r1)
            goto L_0x02fc
        L_0x02ed:
            boolean r0 = r13 instanceof org.bouncycastle.asn1.ASN1Sequence
            if (r0 == 0) goto L_0x0322
            org.bouncycastle.asn1.ASN1Sequence r13 = (org.bouncycastle.asn1.ASN1Sequence) r13
            java.util.Enumeration r0 = r13.getObjects()
            int r13 = r13.size()
            goto L_0x02e8
        L_0x02fc:
            org.bouncycastle.oer.Element r13 = r14.getFirstChid()
            org.bouncycastle.oer.Element r13 = org.bouncycastle.oer.Element.expandDeferredDefinition(r13, r14)
        L_0x0304:
            boolean r1 = r0.hasMoreElements()
            if (r1 == 0) goto L_0x0314
            java.lang.Object r1 = r0.nextElement()
            org.bouncycastle.asn1.ASN1Encodable r1 = (org.bouncycastle.asn1.ASN1Encodable) r1
            r12.write(r1, r13)
            goto L_0x0304
        L_0x0314:
            java.io.OutputStream r13 = r12.out
            r13.flush()
            java.lang.String r13 = r14.appendLabel(r4)
            r12.debugPrint(r13)
            goto L_0x050d
        L_0x0322:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "encodable at for SEQ_OF is not a container"
            r13.<init>(r14)
            throw r13
        L_0x032a:
            org.bouncycastle.asn1.ASN1Sequence r13 = org.bouncycastle.asn1.ASN1Sequence.getInstance(r13)
            boolean r0 = r14.isExtensionsInDefinition()
            r1 = 7
            if (r0 == 0) goto L_0x037a
            r0 = 0
        L_0x0336:
            java.util.List r2 = r14.getChildren()
            int r2 = r2.size()
            if (r0 >= r2) goto L_0x0370
            java.util.List r2 = r14.getChildren()
            java.lang.Object r2 = r2.get(r0)
            org.bouncycastle.oer.Element r2 = (org.bouncycastle.oer.Element) r2
            org.bouncycastle.oer.OERDefinition$BaseType r7 = r2.getBaseType()
            org.bouncycastle.oer.OERDefinition$BaseType r8 = org.bouncycastle.oer.OERDefinition.BaseType.EXTENSION
            if (r7 != r8) goto L_0x0353
            goto L_0x0370
        L_0x0353:
            int r2 = r2.getBlock()
            if (r2 <= 0) goto L_0x036d
            int r2 = r13.size()
            if (r0 >= r2) goto L_0x036d
            org.bouncycastle.oer.OEROptional r2 = org.bouncycastle.oer.OEROptional.ABSENT
            org.bouncycastle.asn1.ASN1Encodable r7 = r13.getObjectAt(r0)
            boolean r2 = r2.equals(r7)
            if (r2 != 0) goto L_0x036d
            r0 = 1
            goto L_0x0371
        L_0x036d:
            int r0 = r0 + 1
            goto L_0x0336
        L_0x0370:
            r0 = 0
        L_0x0371:
            if (r0 == 0) goto L_0x0378
            int[] r2 = bits
            r2 = r2[r1]
            goto L_0x037d
        L_0x0378:
            r2 = 0
            goto L_0x037d
        L_0x037a:
            r0 = 0
            r2 = 0
            r3 = 7
        L_0x037d:
            r7 = 0
        L_0x037e:
            java.util.List r8 = r14.getChildren()
            int r8 = r8.size()
            if (r7 >= r8) goto L_0x042c
            java.util.List r8 = r14.getChildren()
            java.lang.Object r8 = r8.get(r7)
            org.bouncycastle.oer.Element r8 = (org.bouncycastle.oer.Element) r8
            org.bouncycastle.oer.OERDefinition$BaseType r9 = r8.getBaseType()
            org.bouncycastle.oer.OERDefinition$BaseType r10 = org.bouncycastle.oer.OERDefinition.BaseType.EXTENSION
            if (r9 != r10) goto L_0x039c
            goto L_0x0428
        L_0x039c:
            int r9 = r8.getBlock()
            if (r9 <= 0) goto L_0x03a4
            goto L_0x042c
        L_0x03a4:
            org.bouncycastle.oer.Element r8 = org.bouncycastle.oer.Element.expandDeferredDefinition(r8, r14)
            org.bouncycastle.oer.Switch r9 = r14.getaSwitch()
            if (r9 == 0) goto L_0x03bf
            org.bouncycastle.oer.Switch r8 = r14.getaSwitch()
            org.bouncycastle.oer.SwitchIndexer$Asn1SequenceIndexer r9 = new org.bouncycastle.oer.SwitchIndexer$Asn1SequenceIndexer
            r9.<init>(r13)
            org.bouncycastle.oer.Element r8 = r8.result(r9)
            org.bouncycastle.oer.Element r8 = org.bouncycastle.oer.Element.expandDeferredDefinition(r8, r14)
        L_0x03bf:
            if (r3 >= 0) goto L_0x03c8
            java.io.OutputStream r3 = r12.out
            r3.write(r2)
            r2 = 0
            r3 = 7
        L_0x03c8:
            org.bouncycastle.asn1.ASN1Encodable r9 = r13.getObjectAt(r7)
            boolean r10 = r8.isExplicit()
            if (r10 == 0) goto L_0x03df
            boolean r10 = r9 instanceof org.bouncycastle.oer.OEROptional
            if (r10 != 0) goto L_0x03d7
            goto L_0x03df
        L_0x03d7:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "absent sequence element that is required by oer definition"
            r13.<init>(r14)
            throw r13
        L_0x03df:
            boolean r10 = r8.isExplicit()
            if (r10 != 0) goto L_0x0428
            org.bouncycastle.asn1.ASN1Encodable r10 = r13.getObjectAt(r7)
            org.bouncycastle.asn1.ASN1Encodable r11 = r8.getDefaultValue()
            if (r11 == 0) goto L_0x041d
            boolean r9 = r10 instanceof org.bouncycastle.oer.OEROptional
            if (r9 == 0) goto L_0x040e
            org.bouncycastle.oer.OEROptional r10 = (org.bouncycastle.oer.OEROptional) r10
            boolean r9 = r10.isDefined()
            if (r9 == 0) goto L_0x0426
            org.bouncycastle.asn1.ASN1Encodable r9 = r10.get()
            org.bouncycastle.asn1.ASN1Encodable r8 = r8.getDefaultValue()
            boolean r8 = r9.equals(r8)
            if (r8 != 0) goto L_0x0426
            int[] r8 = bits
            r8 = r8[r3]
            goto L_0x0425
        L_0x040e:
            org.bouncycastle.asn1.ASN1Encodable r8 = r8.getDefaultValue()
            boolean r8 = r8.equals(r10)
            if (r8 != 0) goto L_0x0426
            int[] r8 = bits
            r8 = r8[r3]
            goto L_0x0425
        L_0x041d:
            org.bouncycastle.oer.OEROptional r8 = org.bouncycastle.oer.OEROptional.ABSENT
            if (r9 == r8) goto L_0x0426
            int[] r8 = bits
            r8 = r8[r3]
        L_0x0425:
            r2 = r2 | r8
        L_0x0426:
            int r3 = r3 + -1
        L_0x0428:
            int r7 = r7 + 1
            goto L_0x037e
        L_0x042c:
            if (r3 == r1) goto L_0x0433
            java.io.OutputStream r3 = r12.out
            r3.write(r2)
        L_0x0433:
            java.util.List r2 = r14.getChildren()
            r3 = 0
        L_0x0438:
            int r7 = r2.size()
            if (r3 >= r7) goto L_0x0488
            java.util.List r7 = r14.getChildren()
            java.lang.Object r7 = r7.get(r3)
            org.bouncycastle.oer.Element r7 = (org.bouncycastle.oer.Element) r7
            org.bouncycastle.oer.OERDefinition$BaseType r8 = r7.getBaseType()
            org.bouncycastle.oer.OERDefinition$BaseType r9 = org.bouncycastle.oer.OERDefinition.BaseType.EXTENSION
            if (r8 != r9) goto L_0x0451
            goto L_0x0483
        L_0x0451:
            int r8 = r7.getBlock()
            if (r8 <= 0) goto L_0x0458
            goto L_0x0488
        L_0x0458:
            org.bouncycastle.asn1.ASN1Encodable r8 = r13.getObjectAt(r3)
            org.bouncycastle.oer.Switch r9 = r7.getaSwitch()
            if (r9 == 0) goto L_0x046f
            org.bouncycastle.oer.Switch r7 = r7.getaSwitch()
            org.bouncycastle.oer.SwitchIndexer$Asn1SequenceIndexer r9 = new org.bouncycastle.oer.SwitchIndexer$Asn1SequenceIndexer
            r9.<init>(r13)
            org.bouncycastle.oer.Element r7 = r7.result(r9)
        L_0x046f:
            org.bouncycastle.asn1.ASN1Encodable r9 = r7.getDefaultValue()
            if (r9 == 0) goto L_0x0480
            org.bouncycastle.asn1.ASN1Encodable r9 = r7.getDefaultValue()
            boolean r9 = r9.equals(r8)
            if (r9 == 0) goto L_0x0480
            goto L_0x0483
        L_0x0480:
            r12.write(r8, r7)     // Catch:{ all -> 0x0486 }
        L_0x0483:
            int r3 = r3 + 1
            goto L_0x0438
        L_0x0486:
            r13 = move-exception
            throw r13
        L_0x0488:
            if (r0 == 0) goto L_0x0314
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            r7 = r3
            r8 = 7
            r9 = 0
        L_0x0492:
            int r10 = r2.size()
            if (r7 >= r10) goto L_0x04bb
            if (r8 >= 0) goto L_0x049f
            r0.write(r9)
            r8 = 7
            r9 = 0
        L_0x049f:
            int r10 = r13.size()
            if (r7 >= r10) goto L_0x04b6
            org.bouncycastle.oer.OEROptional r10 = org.bouncycastle.oer.OEROptional.ABSENT
            org.bouncycastle.asn1.ASN1Encodable r11 = r13.getObjectAt(r7)
            boolean r10 = r10.equals(r11)
            if (r10 != 0) goto L_0x04b6
            int[] r10 = bits
            r10 = r10[r8]
            r9 = r9 | r10
        L_0x04b6:
            int r8 = r8 + -1
            int r7 = r7 + 1
            goto L_0x0492
        L_0x04bb:
            if (r8 == r1) goto L_0x04c0
            r0.write(r9)
        L_0x04c0:
            int r7 = r0.size()
            int r7 = r7 + r5
            long r9 = (long) r7
            r12.encodeLength(r9)
            if (r8 != r1) goto L_0x04cf
            r12.write(r6)
            goto L_0x04d3
        L_0x04cf:
            int r8 = r8 + r5
            r12.write(r8)
        L_0x04d3:
            byte[] r0 = r0.toByteArray()
            r12.write(r0)
        L_0x04da:
            int r0 = r2.size()
            if (r3 >= r0) goto L_0x0314
            int r0 = r13.size()
            if (r3 >= r0) goto L_0x04ff
            org.bouncycastle.oer.OEROptional r0 = org.bouncycastle.oer.OEROptional.ABSENT
            org.bouncycastle.asn1.ASN1Encodable r1 = r13.getObjectAt(r3)
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x04ff
            org.bouncycastle.asn1.ASN1Encodable r0 = r13.getObjectAt(r3)
            java.lang.Object r1 = r2.get(r3)
            org.bouncycastle.oer.Element r1 = (org.bouncycastle.oer.Element) r1
            r12.writePlainType(r0, r1)
        L_0x04ff:
            int r3 = r3 + 1
            goto L_0x04da
        L_0x0502:
            org.bouncycastle.oer.ElementSupplier r14 = r14.getElementSupplier()
            org.bouncycastle.oer.Element r14 = r14.build()
            r12.write(r13, r14)
        L_0x050d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.oer.OEROutputStream.write(org.bouncycastle.asn1.ASN1Encodable, org.bouncycastle.oer.Element):void");
    }

    public void writePlainType(ASN1Encodable aSN1Encodable, Element element) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        OEROutputStream oEROutputStream = new OEROutputStream(byteArrayOutputStream);
        oEROutputStream.write(aSN1Encodable, element);
        oEROutputStream.flush();
        oEROutputStream.close();
        encodeLength((long) byteArrayOutputStream.size());
        write(byteArrayOutputStream.toByteArray());
    }
}
