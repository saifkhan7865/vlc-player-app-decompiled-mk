package com.google.common.base;

import j$.util.Objects;
import java.io.Serializable;
import javax.annotation.CheckForNull;
import org.bouncycastle.pqc.legacy.math.linearalgebra.Matrix;

@ElementTypesAreNonnullByDefault
public enum CaseFormat {
    LOWER_HYPHEN(CharMatcher.is('-'), "-") {
        /* access modifiers changed from: package-private */
        public String normalizeWord(String str) {
            return Ascii.toLowerCase(str);
        }

        /* access modifiers changed from: package-private */
        public String convert(CaseFormat caseFormat, String str) {
            if (caseFormat == LOWER_UNDERSCORE) {
                return str.replace('-', '_');
            }
            if (caseFormat == UPPER_UNDERSCORE) {
                return Ascii.toUpperCase(str.replace('-', '_'));
            }
            return CaseFormat.super.convert(caseFormat, str);
        }
    },
    LOWER_UNDERSCORE(CharMatcher.is('_'), "_") {
        /* access modifiers changed from: package-private */
        public String normalizeWord(String str) {
            return Ascii.toLowerCase(str);
        }

        /* access modifiers changed from: package-private */
        public String convert(CaseFormat caseFormat, String str) {
            if (caseFormat == LOWER_HYPHEN) {
                return str.replace('_', '-');
            }
            if (caseFormat == UPPER_UNDERSCORE) {
                return Ascii.toUpperCase(str);
            }
            return CaseFormat.super.convert(caseFormat, str);
        }
    },
    LOWER_CAMEL(CharMatcher.inRange('A', Matrix.MATRIX_TYPE_ZERO), "") {
        /* access modifiers changed from: package-private */
        public String normalizeWord(String str) {
            return CaseFormat.firstCharOnlyToUpper(str);
        }

        /* access modifiers changed from: package-private */
        public String normalizeFirstWord(String str) {
            return Ascii.toLowerCase(str);
        }
    },
    UPPER_CAMEL(CharMatcher.inRange('A', Matrix.MATRIX_TYPE_ZERO), "") {
        /* access modifiers changed from: package-private */
        public String normalizeWord(String str) {
            return CaseFormat.firstCharOnlyToUpper(str);
        }
    },
    UPPER_UNDERSCORE(CharMatcher.is('_'), "_") {
        /* access modifiers changed from: package-private */
        public String normalizeWord(String str) {
            return Ascii.toUpperCase(str);
        }

        /* access modifiers changed from: package-private */
        public String convert(CaseFormat caseFormat, String str) {
            if (caseFormat == LOWER_HYPHEN) {
                return Ascii.toLowerCase(str.replace('_', '-'));
            }
            if (caseFormat == LOWER_UNDERSCORE) {
                return Ascii.toLowerCase(str);
            }
            return CaseFormat.super.convert(caseFormat, str);
        }
    };
    
    private final CharMatcher wordBoundary;
    private final String wordSeparator;

    /* access modifiers changed from: package-private */
    public abstract String normalizeWord(String str);

    private CaseFormat(CharMatcher charMatcher, String str) {
        this.wordBoundary = charMatcher;
        this.wordSeparator = str;
    }

    public final String to(CaseFormat caseFormat, String str) {
        Preconditions.checkNotNull(caseFormat);
        Preconditions.checkNotNull(str);
        return caseFormat == this ? str : convert(caseFormat, str);
    }

    /* access modifiers changed from: package-private */
    public String convert(CaseFormat caseFormat, String str) {
        StringBuilder sb = null;
        int i = 0;
        int i2 = -1;
        while (true) {
            i2 = this.wordBoundary.indexIn(str, i2 + 1);
            if (i2 == -1) {
                break;
            }
            if (i == 0) {
                sb = new StringBuilder(str.length() + (caseFormat.wordSeparator.length() * 4));
                sb.append(caseFormat.normalizeFirstWord(str.substring(i, i2)));
            } else {
                ((StringBuilder) Objects.requireNonNull(sb)).append(caseFormat.normalizeWord(str.substring(i, i2)));
            }
            sb.append(caseFormat.wordSeparator);
            i = this.wordSeparator.length() + i2;
        }
        if (i == 0) {
            return caseFormat.normalizeFirstWord(str);
        }
        StringBuilder sb2 = (StringBuilder) Objects.requireNonNull(sb);
        sb2.append(caseFormat.normalizeWord(str.substring(i)));
        return sb2.toString();
    }

    public Converter<String, String> converterTo(CaseFormat caseFormat) {
        return new StringConverter(this, caseFormat);
    }

    private static final class StringConverter extends Converter<String, String> implements Serializable {
        private static final long serialVersionUID = 0;
        private final CaseFormat sourceFormat;
        private final CaseFormat targetFormat;

        StringConverter(CaseFormat caseFormat, CaseFormat caseFormat2) {
            this.sourceFormat = (CaseFormat) Preconditions.checkNotNull(caseFormat);
            this.targetFormat = (CaseFormat) Preconditions.checkNotNull(caseFormat2);
        }

        /* access modifiers changed from: protected */
        public String doForward(String str) {
            return this.sourceFormat.to(this.targetFormat, str);
        }

        /* access modifiers changed from: protected */
        public String doBackward(String str) {
            return this.targetFormat.to(this.sourceFormat, str);
        }

        public boolean equals(@CheckForNull Object obj) {
            if (!(obj instanceof StringConverter)) {
                return false;
            }
            StringConverter stringConverter = (StringConverter) obj;
            if (!this.sourceFormat.equals(stringConverter.sourceFormat) || !this.targetFormat.equals(stringConverter.targetFormat)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return this.sourceFormat.hashCode() ^ this.targetFormat.hashCode();
        }

        public String toString() {
            String valueOf = String.valueOf(this.sourceFormat);
            String valueOf2 = String.valueOf(this.targetFormat);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 14 + String.valueOf(valueOf2).length());
            sb.append(valueOf);
            sb.append(".converterTo(");
            sb.append(valueOf2);
            sb.append(")");
            return sb.toString();
        }
    }

    /* access modifiers changed from: package-private */
    public String normalizeFirstWord(String str) {
        return normalizeWord(str);
    }

    /* access modifiers changed from: private */
    public static String firstCharOnlyToUpper(String str) {
        if (str.isEmpty()) {
            return str;
        }
        char upperCase = Ascii.toUpperCase(str.charAt(0));
        String lowerCase = Ascii.toLowerCase(str.substring(1));
        StringBuilder sb = new StringBuilder(String.valueOf(lowerCase).length() + 1);
        sb.append(upperCase);
        sb.append(lowerCase);
        return sb.toString();
    }
}
