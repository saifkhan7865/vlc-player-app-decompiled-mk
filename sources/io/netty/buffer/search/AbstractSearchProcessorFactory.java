package io.netty.buffer.search;

public abstract class AbstractSearchProcessorFactory implements SearchProcessorFactory {
    public static KmpSearchProcessorFactory newKmpSearchProcessorFactory(byte[] bArr) {
        return new KmpSearchProcessorFactory(bArr);
    }

    public static BitapSearchProcessorFactory newBitapSearchProcessorFactory(byte[] bArr) {
        return new BitapSearchProcessorFactory(bArr);
    }
}
