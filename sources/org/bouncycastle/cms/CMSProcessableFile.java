package org.bouncycastle.cms;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.cms.CMSObjectIdentifiers;
import org.bouncycastle.util.io.Streams;

public class CMSProcessableFile implements CMSTypedData, CMSReadable {
    private static final int DEFAULT_BUF_SIZE = 32768;
    private final int bufSize;
    private final File file;
    private final ASN1ObjectIdentifier type;

    public CMSProcessableFile(File file2) {
        this(file2, 32768);
    }

    public CMSProcessableFile(File file2, int i) {
        this(CMSObjectIdentifiers.data, file2, i);
    }

    public CMSProcessableFile(ASN1ObjectIdentifier aSN1ObjectIdentifier, File file2, int i) {
        this.type = aSN1ObjectIdentifier;
        this.file = file2;
        this.bufSize = i;
    }

    public Object getContent() {
        return this.file;
    }

    public ASN1ObjectIdentifier getContentType() {
        return this.type;
    }

    public InputStream getInputStream() throws IOException, CMSException {
        return new BufferedInputStream(new FileInputStream(this.file), this.bufSize);
    }

    public void write(OutputStream outputStream) throws IOException, CMSException {
        FileInputStream fileInputStream = new FileInputStream(this.file);
        Streams.pipeAll(fileInputStream, outputStream, this.bufSize);
        fileInputStream.close();
    }
}
