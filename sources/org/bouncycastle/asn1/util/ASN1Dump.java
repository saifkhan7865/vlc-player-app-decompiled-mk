package org.bouncycastle.asn1.util;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1BMPString;
import org.bouncycastle.asn1.ASN1BitString;
import org.bouncycastle.asn1.ASN1Boolean;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Enumerated;
import org.bouncycastle.asn1.ASN1External;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1GraphicString;
import org.bouncycastle.asn1.ASN1IA5String;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Null;
import org.bouncycastle.asn1.ASN1NumericString;
import org.bouncycastle.asn1.ASN1ObjectDescriptor;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1PrintableString;
import org.bouncycastle.asn1.ASN1RelativeOID;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.ASN1T61String;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.ASN1UTCTime;
import org.bouncycastle.asn1.ASN1UTF8String;
import org.bouncycastle.asn1.ASN1Util;
import org.bouncycastle.asn1.ASN1VideotexString;
import org.bouncycastle.asn1.ASN1VisibleString;
import org.bouncycastle.asn1.BEROctetString;
import org.bouncycastle.asn1.BERSequence;
import org.bouncycastle.asn1.BERSet;
import org.bouncycastle.asn1.BERTaggedObject;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.DLBitString;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;

public class ASN1Dump {
    private static final int SAMPLE_SIZE = 32;
    private static final String TAB = "    ";

    static void _dumpAsString(String str, boolean z, ASN1Primitive aSN1Primitive, StringBuffer stringBuffer) {
        String str2;
        StringBuilder sb;
        String sb2;
        ASN1Primitive externalContent;
        StringBuilder sb3;
        String string;
        StringBuilder sb4;
        BigInteger value;
        StringBuilder sb5;
        String str3;
        String id;
        StringBuilder sb6;
        int i;
        String lineSeparator = Strings.lineSeparator();
        if (aSN1Primitive instanceof ASN1Null) {
            stringBuffer.append(str);
            stringBuffer.append("NULL");
        } else {
            int i2 = 0;
            if (aSN1Primitive instanceof ASN1Sequence) {
                stringBuffer.append(str);
                stringBuffer.append(aSN1Primitive instanceof BERSequence ? "BER Sequence" : aSN1Primitive instanceof DERSequence ? "DER Sequence" : "Sequence");
                stringBuffer.append(lineSeparator);
                ASN1Sequence aSN1Sequence = (ASN1Sequence) aSN1Primitive;
                String str4 = str + TAB;
                int size = aSN1Sequence.size();
                while (i2 < size) {
                    _dumpAsString(str4, z, aSN1Sequence.getObjectAt(i2).toASN1Primitive(), stringBuffer);
                    i2++;
                }
                return;
            } else if (aSN1Primitive instanceof ASN1Set) {
                stringBuffer.append(str);
                stringBuffer.append(aSN1Primitive instanceof BERSet ? "BER Set" : aSN1Primitive instanceof DERSet ? "DER Set" : "Set");
                stringBuffer.append(lineSeparator);
                ASN1Set aSN1Set = (ASN1Set) aSN1Primitive;
                String str5 = str + TAB;
                int size2 = aSN1Set.size();
                while (i2 < size2) {
                    _dumpAsString(str5, z, aSN1Set.getObjectAt(i2).toASN1Primitive(), stringBuffer);
                    i2++;
                }
                return;
            } else {
                if (aSN1Primitive instanceof ASN1TaggedObject) {
                    stringBuffer.append(str);
                    stringBuffer.append(aSN1Primitive instanceof BERTaggedObject ? "BER Tagged " : aSN1Primitive instanceof DERTaggedObject ? "DER Tagged " : "Tagged ");
                    ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Primitive;
                    stringBuffer.append(ASN1Util.getTagText(aSN1TaggedObject));
                    if (!aSN1TaggedObject.isExplicit()) {
                        stringBuffer.append(" IMPLICIT ");
                    }
                    stringBuffer.append(lineSeparator);
                    sb2 = str + TAB;
                    externalContent = aSN1TaggedObject.getBaseObject().toASN1Primitive();
                } else {
                    if (aSN1Primitive instanceof ASN1OctetString) {
                        ASN1OctetString aSN1OctetString = (ASN1OctetString) aSN1Primitive;
                        if (aSN1Primitive instanceof BEROctetString) {
                            sb6 = new StringBuilder();
                            sb6.append(str);
                            sb6.append("BER Constructed Octet String[");
                            i = aSN1OctetString.getOctets().length;
                        } else {
                            sb6 = new StringBuilder();
                            sb6.append(str);
                            sb6.append("DER Octet String[");
                            i = aSN1OctetString.getOctets().length;
                        }
                        sb6.append(i);
                        sb6.append("] ");
                        stringBuffer.append(sb6.toString());
                        if (z) {
                            str2 = dumpBinaryDataAsString(str, aSN1OctetString.getOctets());
                        }
                    } else {
                        if (aSN1Primitive instanceof ASN1ObjectIdentifier) {
                            sb = new StringBuilder();
                            sb.append(str);
                            sb.append("ObjectIdentifier(");
                            id = ((ASN1ObjectIdentifier) aSN1Primitive).getId();
                        } else if (aSN1Primitive instanceof ASN1RelativeOID) {
                            sb = new StringBuilder();
                            sb.append(str);
                            sb.append("RelativeOID(");
                            id = ((ASN1RelativeOID) aSN1Primitive).getId();
                        } else {
                            if (aSN1Primitive instanceof ASN1Boolean) {
                                sb = new StringBuilder();
                                sb.append(str);
                                sb.append("Boolean(");
                                sb.append(((ASN1Boolean) aSN1Primitive).isTrue());
                            } else {
                                if (aSN1Primitive instanceof ASN1Integer) {
                                    sb4 = new StringBuilder();
                                    sb4.append(str);
                                    sb4.append("Integer(");
                                    value = ((ASN1Integer) aSN1Primitive).getValue();
                                } else if (aSN1Primitive instanceof ASN1BitString) {
                                    ASN1BitString aSN1BitString = (ASN1BitString) aSN1Primitive;
                                    byte[] bytes = aSN1BitString.getBytes();
                                    int padBits = aSN1BitString.getPadBits();
                                    if (aSN1BitString instanceof DERBitString) {
                                        sb5 = new StringBuilder();
                                        sb5.append(str);
                                        str3 = "DER Bit String[";
                                    } else if (aSN1BitString instanceof DLBitString) {
                                        sb5 = new StringBuilder();
                                        sb5.append(str);
                                        str3 = "DL Bit String[";
                                    } else {
                                        sb5 = new StringBuilder();
                                        sb5.append(str);
                                        str3 = "BER Bit String[";
                                    }
                                    sb5.append(str3);
                                    sb5.append(bytes.length);
                                    sb5.append(", ");
                                    sb5.append(padBits);
                                    sb5.append("] ");
                                    stringBuffer.append(sb5.toString());
                                    if (z) {
                                        str2 = dumpBinaryDataAsString(str, bytes);
                                    }
                                } else {
                                    if (aSN1Primitive instanceof ASN1IA5String) {
                                        sb3 = new StringBuilder();
                                        sb3.append(str);
                                        sb3.append("IA5String(");
                                        string = ((ASN1IA5String) aSN1Primitive).getString();
                                    } else if (aSN1Primitive instanceof ASN1UTF8String) {
                                        sb3 = new StringBuilder();
                                        sb3.append(str);
                                        sb3.append("UTF8String(");
                                        string = ((ASN1UTF8String) aSN1Primitive).getString();
                                    } else if (aSN1Primitive instanceof ASN1NumericString) {
                                        sb3 = new StringBuilder();
                                        sb3.append(str);
                                        sb3.append("NumericString(");
                                        string = ((ASN1NumericString) aSN1Primitive).getString();
                                    } else if (aSN1Primitive instanceof ASN1PrintableString) {
                                        sb3 = new StringBuilder();
                                        sb3.append(str);
                                        sb3.append("PrintableString(");
                                        string = ((ASN1PrintableString) aSN1Primitive).getString();
                                    } else if (aSN1Primitive instanceof ASN1VisibleString) {
                                        sb3 = new StringBuilder();
                                        sb3.append(str);
                                        sb3.append("VisibleString(");
                                        string = ((ASN1VisibleString) aSN1Primitive).getString();
                                    } else if (aSN1Primitive instanceof ASN1BMPString) {
                                        sb3 = new StringBuilder();
                                        sb3.append(str);
                                        sb3.append("BMPString(");
                                        string = ((ASN1BMPString) aSN1Primitive).getString();
                                    } else if (aSN1Primitive instanceof ASN1T61String) {
                                        sb3 = new StringBuilder();
                                        sb3.append(str);
                                        sb3.append("T61String(");
                                        string = ((ASN1T61String) aSN1Primitive).getString();
                                    } else if (aSN1Primitive instanceof ASN1GraphicString) {
                                        sb3 = new StringBuilder();
                                        sb3.append(str);
                                        sb3.append("GraphicString(");
                                        string = ((ASN1GraphicString) aSN1Primitive).getString();
                                    } else if (aSN1Primitive instanceof ASN1VideotexString) {
                                        sb3 = new StringBuilder();
                                        sb3.append(str);
                                        sb3.append("VideotexString(");
                                        string = ((ASN1VideotexString) aSN1Primitive).getString();
                                    } else if (aSN1Primitive instanceof ASN1UTCTime) {
                                        sb3 = new StringBuilder();
                                        sb3.append(str);
                                        sb3.append("UTCTime(");
                                        string = ((ASN1UTCTime) aSN1Primitive).getTime();
                                    } else if (aSN1Primitive instanceof ASN1GeneralizedTime) {
                                        sb3 = new StringBuilder();
                                        sb3.append(str);
                                        sb3.append("GeneralizedTime(");
                                        string = ((ASN1GeneralizedTime) aSN1Primitive).getTime();
                                    } else if (aSN1Primitive instanceof ASN1Enumerated) {
                                        sb4 = new StringBuilder();
                                        sb4.append(str);
                                        sb4.append("DER Enumerated(");
                                        value = ((ASN1Enumerated) aSN1Primitive).getValue();
                                    } else if (aSN1Primitive instanceof ASN1ObjectDescriptor) {
                                        sb3 = new StringBuilder();
                                        sb3.append(str);
                                        sb3.append("ObjectDescriptor(");
                                        string = ((ASN1ObjectDescriptor) aSN1Primitive).getBaseGraphicString().getString();
                                    } else if (aSN1Primitive instanceof ASN1External) {
                                        ASN1External aSN1External = (ASN1External) aSN1Primitive;
                                        stringBuffer.append(str + "External " + lineSeparator);
                                        StringBuilder sb7 = new StringBuilder();
                                        sb7.append(str);
                                        sb7.append(TAB);
                                        sb2 = sb7.toString();
                                        if (aSN1External.getDirectReference() != null) {
                                            stringBuffer.append(sb2 + "Direct Reference: " + aSN1External.getDirectReference().getId() + lineSeparator);
                                        }
                                        if (aSN1External.getIndirectReference() != null) {
                                            stringBuffer.append(sb2 + "Indirect Reference: " + aSN1External.getIndirectReference().toString() + lineSeparator);
                                        }
                                        if (aSN1External.getDataValueDescriptor() != null) {
                                            _dumpAsString(sb2, z, aSN1External.getDataValueDescriptor(), stringBuffer);
                                        }
                                        stringBuffer.append(sb2 + "Encoding: " + aSN1External.getEncoding() + lineSeparator);
                                        externalContent = aSN1External.getExternalContent();
                                    } else {
                                        sb = new StringBuilder();
                                        sb.append(str);
                                        sb.append(aSN1Primitive.toString());
                                        sb.append(lineSeparator);
                                        str2 = sb.toString();
                                    }
                                    sb.append(string);
                                    sb.append(") ");
                                    sb.append(lineSeparator);
                                    str2 = sb.toString();
                                }
                                sb.append(value);
                            }
                            sb.append(")");
                            sb.append(lineSeparator);
                            str2 = sb.toString();
                        }
                        sb.append(id);
                        sb.append(")");
                        sb.append(lineSeparator);
                        str2 = sb.toString();
                    }
                    stringBuffer.append(str2);
                    return;
                }
                _dumpAsString(sb2, z, externalContent, stringBuffer);
                return;
            }
        }
        stringBuffer.append(lineSeparator);
    }

    private static String calculateAscString(byte[] bArr, int i, int i2) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i3 = i; i3 != i + i2; i3++) {
            byte b = bArr[i3];
            if (b >= 32 && b <= 126) {
                stringBuffer.append((char) b);
            }
        }
        return stringBuffer.toString();
    }

    public static String dumpAsString(Object obj) {
        return dumpAsString(obj, false);
    }

    public static String dumpAsString(Object obj, boolean z) {
        ASN1Primitive aSN1Primitive;
        if (obj instanceof ASN1Primitive) {
            aSN1Primitive = (ASN1Primitive) obj;
        } else if (obj instanceof ASN1Encodable) {
            aSN1Primitive = ((ASN1Encodable) obj).toASN1Primitive();
        } else {
            return "unknown object type " + obj.toString();
        }
        StringBuffer stringBuffer = new StringBuffer();
        _dumpAsString("", z, aSN1Primitive, stringBuffer);
        return stringBuffer.toString();
    }

    private static String dumpBinaryDataAsString(String str, byte[] bArr) {
        String calculateAscString;
        String lineSeparator = Strings.lineSeparator();
        StringBuffer stringBuffer = new StringBuffer();
        String str2 = str + TAB;
        stringBuffer.append(lineSeparator);
        for (int i = 0; i < bArr.length; i += 32) {
            int length = bArr.length - i;
            stringBuffer.append(str2);
            if (length > 32) {
                stringBuffer.append(Strings.fromByteArray(Hex.encode(bArr, i, 32)));
                stringBuffer.append(TAB);
                calculateAscString = calculateAscString(bArr, i, 32);
            } else {
                stringBuffer.append(Strings.fromByteArray(Hex.encode(bArr, i, bArr.length - i)));
                for (int length2 = bArr.length - i; length2 != 32; length2++) {
                    stringBuffer.append("  ");
                }
                stringBuffer.append(TAB);
                calculateAscString = calculateAscString(bArr, i, bArr.length - i);
            }
            stringBuffer.append(calculateAscString);
            stringBuffer.append(lineSeparator);
        }
        return stringBuffer.toString();
    }
}
