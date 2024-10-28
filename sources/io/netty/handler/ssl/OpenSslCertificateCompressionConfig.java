package io.netty.handler.ssl;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.netty.util.internal.ObjectUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class OpenSslCertificateCompressionConfig implements Iterable<AlgorithmConfig> {
    private final List<AlgorithmConfig> pairList;

    public enum AlgorithmMode {
        Compress,
        Decompress,
        Both
    }

    private OpenSslCertificateCompressionConfig(AlgorithmConfig... algorithmConfigArr) {
        this.pairList = Collections.unmodifiableList(Arrays.asList(algorithmConfigArr));
    }

    public Iterator<AlgorithmConfig> iterator() {
        return this.pairList.iterator();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private final List<AlgorithmConfig> algorithmList;

        private Builder() {
            this.algorithmList = new ArrayList();
        }

        public Builder addAlgorithm(OpenSslCertificateCompressionAlgorithm openSslCertificateCompressionAlgorithm, AlgorithmMode algorithmMode) {
            this.algorithmList.add(new AlgorithmConfig(openSslCertificateCompressionAlgorithm, algorithmMode));
            return this;
        }

        public OpenSslCertificateCompressionConfig build() {
            return new OpenSslCertificateCompressionConfig((AlgorithmConfig[]) this.algorithmList.toArray(new AlgorithmConfig[0]));
        }
    }

    public static final class AlgorithmConfig {
        private final OpenSslCertificateCompressionAlgorithm algorithm;
        private final AlgorithmMode mode;

        private AlgorithmConfig(OpenSslCertificateCompressionAlgorithm openSslCertificateCompressionAlgorithm, AlgorithmMode algorithmMode) {
            this.algorithm = (OpenSslCertificateCompressionAlgorithm) ObjectUtil.checkNotNull(openSslCertificateCompressionAlgorithm, "algorithm");
            this.mode = (AlgorithmMode) ObjectUtil.checkNotNull(algorithmMode, RtspHeaders.Values.MODE);
        }

        public AlgorithmMode mode() {
            return this.mode;
        }

        public OpenSslCertificateCompressionAlgorithm algorithm() {
            return this.algorithm;
        }
    }
}
