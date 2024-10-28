package org.bouncycastle.cms;

import com.google.common.base.Ascii;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;

public class PKCS7TypedStream extends CMSTypedStream {
    private final ASN1Encodable content;

    public PKCS7TypedStream(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) throws IOException {
        super(aSN1ObjectIdentifier);
        this.content = aSN1Encodable;
    }

    private InputStream getContentStream(ASN1Encodable aSN1Encodable) throws IOException {
        byte b;
        byte[] encoded = aSN1Encodable.toASN1Primitive().getEncoded(ASN1Encoding.DER);
        int i = 1;
        if ((encoded[0] & Ascii.US) == 31) {
            do {
                b = encoded[i] & 128;
                i++;
            } while (b != 0);
        }
        int i2 = i + 1;
        byte b2 = encoded[i];
        if ((b2 & 128) != 0) {
            i2 += b2 & Byte.MAX_VALUE;
        }
        return new ByteArrayInputStream(encoded, i2, encoded.length - i2);
    }

    public void drain() throws IOException {
        this.content.toASN1Primitive();
    }

    public ASN1Encodable getContent() {
        return this.content;
    }

    public InputStream getContentStream() {
        try {
            return getContentStream(this.content);
        } catch (IOException e) {
            throw new CMSRuntimeException("unable to convert content to stream: " + e.getMessage(), e);
        }
    }
}
