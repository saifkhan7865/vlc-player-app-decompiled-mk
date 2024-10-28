package io.netty.util;

import io.netty.handler.codec.http.HttpConstants;

public interface ByteProcessor {
    public static final ByteProcessor FIND_ASCII_SPACE = new IndexOfProcessor((byte) 32);
    public static final ByteProcessor FIND_COMMA = new IndexOfProcessor(HttpConstants.COMMA);
    public static final ByteProcessor FIND_CR = new IndexOfProcessor((byte) 13);
    public static final ByteProcessor FIND_CRLF = new ByteProcessor() {
        public boolean process(byte b) {
            return (b == 13 || b == 10) ? false : true;
        }
    };
    public static final ByteProcessor FIND_LF = new IndexOfProcessor((byte) 10);
    public static final ByteProcessor FIND_LINEAR_WHITESPACE = new ByteProcessor() {
        public boolean process(byte b) {
            return (b == 32 || b == 9) ? false : true;
        }
    };
    public static final ByteProcessor FIND_NON_CR = new IndexNotOfProcessor((byte) 13);
    public static final ByteProcessor FIND_NON_CRLF = new ByteProcessor() {
        public boolean process(byte b) {
            return b == 13 || b == 10;
        }
    };
    public static final ByteProcessor FIND_NON_LF = new IndexNotOfProcessor((byte) 10);
    public static final ByteProcessor FIND_NON_LINEAR_WHITESPACE = new ByteProcessor() {
        public boolean process(byte b) {
            return b == 32 || b == 9;
        }
    };
    public static final ByteProcessor FIND_NON_NUL = new IndexNotOfProcessor((byte) 0);
    public static final ByteProcessor FIND_NUL = new IndexOfProcessor((byte) 0);
    public static final ByteProcessor FIND_SEMI_COLON = new IndexOfProcessor(HttpConstants.SEMICOLON);

    boolean process(byte b) throws Exception;

    public static class IndexOfProcessor implements ByteProcessor {
        private final byte byteToFind;

        public IndexOfProcessor(byte b) {
            this.byteToFind = b;
        }

        public boolean process(byte b) {
            return b != this.byteToFind;
        }
    }

    public static class IndexNotOfProcessor implements ByteProcessor {
        private final byte byteToNotFind;

        public IndexNotOfProcessor(byte b) {
            this.byteToNotFind = b;
        }

        public boolean process(byte b) {
            return b == this.byteToNotFind;
        }
    }
}
