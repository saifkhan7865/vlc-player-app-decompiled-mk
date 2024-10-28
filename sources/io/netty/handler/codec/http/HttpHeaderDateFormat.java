package io.netty.handler.codec.http;

import io.netty.util.concurrent.FastThreadLocal;
import j$.util.DesugarTimeZone;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Deprecated
public final class HttpHeaderDateFormat extends SimpleDateFormat {
    private static final FastThreadLocal<HttpHeaderDateFormat> dateFormatThreadLocal = new FastThreadLocal<HttpHeaderDateFormat>() {
        /* access modifiers changed from: protected */
        public HttpHeaderDateFormat initialValue() {
            return new HttpHeaderDateFormat();
        }
    };
    private static final long serialVersionUID = -925286159755905325L;
    private final SimpleDateFormat format1;
    private final SimpleDateFormat format2;

    public static HttpHeaderDateFormat get() {
        return dateFormatThreadLocal.get();
    }

    private HttpHeaderDateFormat() {
        super("E, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
        this.format1 = new HttpHeaderDateFormatObsolete1();
        this.format2 = new HttpHeaderDateFormatObsolete2();
        setTimeZone(DesugarTimeZone.getTimeZone("GMT"));
    }

    public Date parse(String str, ParsePosition parsePosition) {
        Date parse = super.parse(str, parsePosition);
        if (parse == null) {
            parse = this.format1.parse(str, parsePosition);
        }
        return parse == null ? this.format2.parse(str, parsePosition) : parse;
    }

    private static final class HttpHeaderDateFormatObsolete1 extends SimpleDateFormat {
        private static final long serialVersionUID = -3178072504225114298L;

        HttpHeaderDateFormatObsolete1() {
            super("E, dd-MMM-yy HH:mm:ss z", Locale.ENGLISH);
            setTimeZone(DesugarTimeZone.getTimeZone("GMT"));
        }
    }

    private static final class HttpHeaderDateFormatObsolete2 extends SimpleDateFormat {
        private static final long serialVersionUID = 3010674519968303714L;

        HttpHeaderDateFormatObsolete2() {
            super("E MMM d HH:mm:ss yyyy", Locale.ENGLISH);
            setTimeZone(DesugarTimeZone.getTimeZone("GMT"));
        }
    }
}