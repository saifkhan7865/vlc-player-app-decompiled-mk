package org.bouncycastle.mime.smime;

import com.google.common.net.HttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import org.bouncycastle.cms.CMSAttributeTableGenerator;
import org.bouncycastle.cms.CMSEnvelopedDataStreamGenerator;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.OriginatorInformation;
import org.bouncycastle.cms.RecipientInfoGenerator;
import org.bouncycastle.mime.Headers;
import org.bouncycastle.mime.MimeIOException;
import org.bouncycastle.mime.MimeWriter;
import org.bouncycastle.mime.encoding.Base64OutputStream;
import org.bouncycastle.operator.OutputEncryptor;
import org.bouncycastle.util.Strings;

public class SMIMEEnvelopedWriter extends MimeWriter {
    private final String contentTransferEncoding;
    private final CMSEnvelopedDataStreamGenerator envGen;
    private final OutputStream mimeOut;
    private final OutputEncryptor outEnc;

    public static class Builder {
        private static final String[] stdHeaders = {"Content-Type", HttpHeaders.CONTENT_DISPOSITION, HttpHeaders.Names.CONTENT_TRANSFER_ENCODING, "Content-Description"};
        private static final String[] stdValues = {"application/pkcs7-mime; name=\"smime.p7m\"; smime-type=enveloped-data", "attachment; filename=\"smime.p7m\"", HttpHeaders.Values.BASE64, "S/MIME Encrypted Message"};
        String contentTransferEncoding = HttpHeaders.Values.BASE64;
        /* access modifiers changed from: private */
        public final CMSEnvelopedDataStreamGenerator envGen = new CMSEnvelopedDataStreamGenerator();
        /* access modifiers changed from: private */
        public final Map<String, String> headers = new LinkedHashMap();

        public Builder() {
            int i = 0;
            while (true) {
                String[] strArr = stdHeaders;
                if (i != strArr.length) {
                    this.headers.put(strArr[i], stdValues[i]);
                    i++;
                } else {
                    return;
                }
            }
        }

        public Builder addRecipientInfoGenerator(RecipientInfoGenerator recipientInfoGenerator) {
            this.envGen.addRecipientInfoGenerator(recipientInfoGenerator);
            return this;
        }

        public SMIMEEnvelopedWriter build(OutputStream outputStream, OutputEncryptor outputEncryptor) {
            return new SMIMEEnvelopedWriter(this, outputEncryptor, SMimeUtils.autoBuffer(outputStream));
        }

        public Builder setBufferSize(int i) {
            this.envGen.setBufferSize(i);
            return this;
        }

        public Builder setOriginatorInfo(OriginatorInformation originatorInformation) {
            this.envGen.setOriginatorInfo(originatorInformation);
            return this;
        }

        public Builder setUnprotectedAttributeGenerator(CMSAttributeTableGenerator cMSAttributeTableGenerator) {
            this.envGen.setUnprotectedAttributeGenerator(cMSAttributeTableGenerator);
            return this;
        }

        public Builder withHeader(String str, String str2) {
            this.headers.put(str, str2);
            return this;
        }
    }

    private static class ContentOutputStream extends OutputStream {
        private final OutputStream backing;
        private final OutputStream main;

        ContentOutputStream(OutputStream outputStream, OutputStream outputStream2) {
            this.main = outputStream;
            this.backing = outputStream2;
        }

        public void close() throws IOException {
            this.main.close();
            OutputStream outputStream = this.backing;
            if (outputStream != null) {
                outputStream.close();
            }
        }

        public void write(int i) throws IOException {
            this.main.write(i);
        }

        public void write(byte[] bArr) throws IOException {
            this.main.write(bArr);
        }

        public void write(byte[] bArr, int i, int i2) throws IOException {
            this.main.write(bArr, i, i2);
        }
    }

    private SMIMEEnvelopedWriter(Builder builder, OutputEncryptor outputEncryptor, OutputStream outputStream) {
        super(new Headers(mapToLines(builder.headers), builder.contentTransferEncoding));
        this.envGen = builder.envGen;
        this.contentTransferEncoding = builder.contentTransferEncoding;
        this.outEnc = outputEncryptor;
        this.mimeOut = outputStream;
    }

    public OutputStream getContentStream() throws IOException {
        this.headers.dumpHeaders(this.mimeOut);
        this.mimeOut.write(Strings.toByteArray("\r\n"));
        try {
            OutputStream outputStream = this.mimeOut;
            if (HttpHeaders.Values.BASE64.equals(this.contentTransferEncoding)) {
                outputStream = new Base64OutputStream(outputStream);
            }
            return new ContentOutputStream(this.envGen.open(SMimeUtils.createUnclosable(outputStream), this.outEnc), outputStream);
        } catch (CMSException e) {
            throw new MimeIOException(e.getMessage(), e);
        }
    }
}
