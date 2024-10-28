package io.netty.handler.codec.http.multipart;

import io.ktor.util.date.GMTDateParser;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.HttpConstants;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.multipart.HttpPostBodyUtil;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.InternalThreadLocalMap;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.StringUtil;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.fusesource.jansi.AnsiRenderer;

public class HttpPostMultipartRequestDecoder implements InterfaceHttpPostRequestDecoder {
    private static final String FILENAME_ENCODED = (HttpHeaderValues.FILENAME.toString() + GMTDateParser.ANY);
    private final List<InterfaceHttpData> bodyListHttpData;
    private int bodyListHttpDataRank;
    private final Map<String, List<InterfaceHttpData>> bodyMapHttpData;
    private Charset charset;
    private Attribute currentAttribute;
    private Map<CharSequence, Attribute> currentFieldAttributes;
    private FileUpload currentFileUpload;
    private HttpPostRequestDecoder.MultiPartStatus currentStatus;
    private boolean destroyed;
    private int discardThreshold;
    private final HttpDataFactory factory;
    private boolean isLastChunk;
    private final String multipartDataBoundary;
    private String multipartMixedBoundary;
    private final HttpRequest request;
    private ByteBuf undecodedChunk;

    public HttpPostMultipartRequestDecoder(HttpRequest httpRequest) {
        this(new DefaultHttpDataFactory(16384), httpRequest, HttpConstants.DEFAULT_CHARSET);
    }

    public HttpPostMultipartRequestDecoder(HttpDataFactory httpDataFactory, HttpRequest httpRequest) {
        this(httpDataFactory, httpRequest, HttpConstants.DEFAULT_CHARSET);
    }

    public HttpPostMultipartRequestDecoder(HttpDataFactory httpDataFactory, HttpRequest httpRequest, Charset charset2) {
        String str;
        this.bodyListHttpData = new ArrayList();
        this.bodyMapHttpData = new TreeMap(CaseIgnoringComparator.INSTANCE);
        this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.NOTSTARTED;
        this.discardThreshold = 10485760;
        HttpRequest httpRequest2 = (HttpRequest) ObjectUtil.checkNotNull(httpRequest, "request");
        this.request = httpRequest2;
        this.charset = (Charset) ObjectUtil.checkNotNull(charset2, "charset");
        this.factory = (HttpDataFactory) ObjectUtil.checkNotNull(httpDataFactory, "factory");
        String str2 = httpRequest2.headers().get((CharSequence) HttpHeaderNames.CONTENT_TYPE);
        if (str2 != null) {
            String[] multipartDataBoundary2 = HttpPostRequestDecoder.getMultipartDataBoundary(str2);
            if (multipartDataBoundary2 != null) {
                this.multipartDataBoundary = multipartDataBoundary2[0];
                if (multipartDataBoundary2.length > 1 && (str = multipartDataBoundary2[1]) != null) {
                    try {
                        this.charset = Charset.forName(str);
                    } catch (IllegalCharsetNameException e) {
                        throw new HttpPostRequestDecoder.ErrorDataDecoderException((Throwable) e);
                    }
                }
            } else {
                this.multipartDataBoundary = null;
            }
            this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.HEADERDELIMITER;
            try {
                if (httpRequest instanceof HttpContent) {
                    offer((HttpContent) httpRequest);
                } else {
                    parseBody();
                }
            } catch (Throwable th) {
                destroy();
                PlatformDependent.throwException(th);
            }
        } else {
            throw new HttpPostRequestDecoder.ErrorDataDecoderException("No '" + HttpHeaderNames.CONTENT_TYPE + "' header present.");
        }
    }

    private void checkDestroyed() {
        if (this.destroyed) {
            throw new IllegalStateException("HttpPostMultipartRequestDecoder was destroyed already");
        }
    }

    public boolean isMultipart() {
        checkDestroyed();
        return true;
    }

    public void setDiscardThreshold(int i) {
        this.discardThreshold = ObjectUtil.checkPositiveOrZero(i, "discardThreshold");
    }

    public int getDiscardThreshold() {
        return this.discardThreshold;
    }

    public List<InterfaceHttpData> getBodyHttpDatas() {
        checkDestroyed();
        if (this.isLastChunk) {
            return this.bodyListHttpData;
        }
        throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
    }

    public List<InterfaceHttpData> getBodyHttpDatas(String str) {
        checkDestroyed();
        if (this.isLastChunk) {
            return this.bodyMapHttpData.get(str);
        }
        throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
    }

    public InterfaceHttpData getBodyHttpData(String str) {
        checkDestroyed();
        if (this.isLastChunk) {
            List list = this.bodyMapHttpData.get(str);
            if (list != null) {
                return (InterfaceHttpData) list.get(0);
            }
            return null;
        }
        throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
    }

    public HttpPostMultipartRequestDecoder offer(HttpContent httpContent) {
        checkDestroyed();
        if (httpContent instanceof LastHttpContent) {
            this.isLastChunk = true;
        }
        ByteBuf content = httpContent.content();
        ByteBuf byteBuf = this.undecodedChunk;
        if (byteBuf == null) {
            this.undecodedChunk = content.alloc().buffer(content.readableBytes()).writeBytes(content);
        } else {
            byteBuf.writeBytes(content);
        }
        parseBody();
        ByteBuf byteBuf2 = this.undecodedChunk;
        if (byteBuf2 != null && byteBuf2.writerIndex() > this.discardThreshold) {
            if (this.undecodedChunk.refCnt() == 1) {
                this.undecodedChunk.discardReadBytes();
            } else {
                ByteBuf buffer = this.undecodedChunk.alloc().buffer(this.undecodedChunk.readableBytes());
                buffer.writeBytes(this.undecodedChunk);
                this.undecodedChunk.release();
                this.undecodedChunk = buffer;
            }
        }
        return this;
    }

    public boolean hasNext() {
        checkDestroyed();
        if (this.currentStatus != HttpPostRequestDecoder.MultiPartStatus.EPILOGUE || this.bodyListHttpDataRank < this.bodyListHttpData.size()) {
            return !this.bodyListHttpData.isEmpty() && this.bodyListHttpDataRank < this.bodyListHttpData.size();
        }
        throw new HttpPostRequestDecoder.EndOfDataDecoderException();
    }

    public InterfaceHttpData next() {
        checkDestroyed();
        if (!hasNext()) {
            return null;
        }
        List<InterfaceHttpData> list = this.bodyListHttpData;
        int i = this.bodyListHttpDataRank;
        this.bodyListHttpDataRank = i + 1;
        return list.get(i);
    }

    public InterfaceHttpData currentPartialHttpData() {
        FileUpload fileUpload = this.currentFileUpload;
        if (fileUpload != null) {
            return fileUpload;
        }
        return this.currentAttribute;
    }

    private void parseBody() {
        if (this.currentStatus != HttpPostRequestDecoder.MultiPartStatus.PREEPILOGUE && this.currentStatus != HttpPostRequestDecoder.MultiPartStatus.EPILOGUE) {
            parseBodyMultipart();
        } else if (this.isLastChunk) {
            this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.EPILOGUE;
        }
    }

    /* access modifiers changed from: protected */
    public void addHttpData(InterfaceHttpData interfaceHttpData) {
        if (interfaceHttpData != null) {
            List list = this.bodyMapHttpData.get(interfaceHttpData.getName());
            if (list == null) {
                list = new ArrayList(1);
                this.bodyMapHttpData.put(interfaceHttpData.getName(), list);
            }
            list.add(interfaceHttpData);
            this.bodyListHttpData.add(interfaceHttpData);
        }
    }

    private void parseBodyMultipart() {
        ByteBuf byteBuf = this.undecodedChunk;
        if (byteBuf != null && byteBuf.readableBytes() != 0) {
            InterfaceHttpData decodeMultipart = decodeMultipart(this.currentStatus);
            while (decodeMultipart != null) {
                addHttpData(decodeMultipart);
                if (this.currentStatus != HttpPostRequestDecoder.MultiPartStatus.PREEPILOGUE && this.currentStatus != HttpPostRequestDecoder.MultiPartStatus.EPILOGUE) {
                    decodeMultipart = decodeMultipart(this.currentStatus);
                } else {
                    return;
                }
            }
        }
    }

    /* renamed from: io.netty.handler.codec.http.multipart.HttpPostMultipartRequestDecoder$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$http$multipart$HttpPostRequestDecoder$MultiPartStatus;

        /* JADX WARNING: Can't wrap try/catch for region: R(22:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|(3:21|22|24)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(24:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|24) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$MultiPartStatus[] r0 = io.netty.handler.codec.http.multipart.HttpPostRequestDecoder.MultiPartStatus.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$io$netty$handler$codec$http$multipart$HttpPostRequestDecoder$MultiPartStatus = r0
                io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$MultiPartStatus r1 = io.netty.handler.codec.http.multipart.HttpPostRequestDecoder.MultiPartStatus.NOTSTARTED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$io$netty$handler$codec$http$multipart$HttpPostRequestDecoder$MultiPartStatus     // Catch:{ NoSuchFieldError -> 0x001d }
                io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$MultiPartStatus r1 = io.netty.handler.codec.http.multipart.HttpPostRequestDecoder.MultiPartStatus.PREAMBLE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$io$netty$handler$codec$http$multipart$HttpPostRequestDecoder$MultiPartStatus     // Catch:{ NoSuchFieldError -> 0x0028 }
                io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$MultiPartStatus r1 = io.netty.handler.codec.http.multipart.HttpPostRequestDecoder.MultiPartStatus.HEADERDELIMITER     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$io$netty$handler$codec$http$multipart$HttpPostRequestDecoder$MultiPartStatus     // Catch:{ NoSuchFieldError -> 0x0033 }
                io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$MultiPartStatus r1 = io.netty.handler.codec.http.multipart.HttpPostRequestDecoder.MultiPartStatus.DISPOSITION     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$io$netty$handler$codec$http$multipart$HttpPostRequestDecoder$MultiPartStatus     // Catch:{ NoSuchFieldError -> 0x003e }
                io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$MultiPartStatus r1 = io.netty.handler.codec.http.multipart.HttpPostRequestDecoder.MultiPartStatus.FIELD     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$io$netty$handler$codec$http$multipart$HttpPostRequestDecoder$MultiPartStatus     // Catch:{ NoSuchFieldError -> 0x0049 }
                io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$MultiPartStatus r1 = io.netty.handler.codec.http.multipart.HttpPostRequestDecoder.MultiPartStatus.FILEUPLOAD     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$io$netty$handler$codec$http$multipart$HttpPostRequestDecoder$MultiPartStatus     // Catch:{ NoSuchFieldError -> 0x0054 }
                io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$MultiPartStatus r1 = io.netty.handler.codec.http.multipart.HttpPostRequestDecoder.MultiPartStatus.MIXEDDELIMITER     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$io$netty$handler$codec$http$multipart$HttpPostRequestDecoder$MultiPartStatus     // Catch:{ NoSuchFieldError -> 0x0060 }
                io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$MultiPartStatus r1 = io.netty.handler.codec.http.multipart.HttpPostRequestDecoder.MultiPartStatus.MIXEDDISPOSITION     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$io$netty$handler$codec$http$multipart$HttpPostRequestDecoder$MultiPartStatus     // Catch:{ NoSuchFieldError -> 0x006c }
                io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$MultiPartStatus r1 = io.netty.handler.codec.http.multipart.HttpPostRequestDecoder.MultiPartStatus.MIXEDFILEUPLOAD     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$io$netty$handler$codec$http$multipart$HttpPostRequestDecoder$MultiPartStatus     // Catch:{ NoSuchFieldError -> 0x0078 }
                io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$MultiPartStatus r1 = io.netty.handler.codec.http.multipart.HttpPostRequestDecoder.MultiPartStatus.PREEPILOGUE     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$io$netty$handler$codec$http$multipart$HttpPostRequestDecoder$MultiPartStatus     // Catch:{ NoSuchFieldError -> 0x0084 }
                io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$MultiPartStatus r1 = io.netty.handler.codec.http.multipart.HttpPostRequestDecoder.MultiPartStatus.EPILOGUE     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.http.multipart.HttpPostMultipartRequestDecoder.AnonymousClass1.<clinit>():void");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x008c A[SYNTHETIC, Splitter:B:37:0x008c] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x009f A[Catch:{ NullPointerException -> 0x00c7, IllegalArgumentException -> 0x00c0, IOException -> 0x00b9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00b3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private io.netty.handler.codec.http.multipart.InterfaceHttpData decodeMultipart(io.netty.handler.codec.http.multipart.HttpPostRequestDecoder.MultiPartStatus r8) {
        /*
            r7 = this;
            int[] r0 = io.netty.handler.codec.http.multipart.HttpPostMultipartRequestDecoder.AnonymousClass1.$SwitchMap$io$netty$handler$codec$http$multipart$HttpPostRequestDecoder$MultiPartStatus
            int r8 = r8.ordinal()
            r8 = r0[r8]
            java.lang.String r0 = "Should not be called with the current getStatus"
            r1 = 0
            switch(r8) {
                case 1: goto L_0x00fc;
                case 2: goto L_0x00f6;
                case 3: goto L_0x00eb;
                case 4: goto L_0x00e6;
                case 5: goto L_0x0035;
                case 6: goto L_0x002e;
                case 7: goto L_0x0023;
                case 8: goto L_0x001e;
                case 9: goto L_0x0017;
                case 10: goto L_0x0016;
                case 11: goto L_0x0016;
                default: goto L_0x000e;
            }
        L_0x000e:
            io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$ErrorDataDecoderException r8 = new io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$ErrorDataDecoderException
            java.lang.String r0 = "Shouldn't reach here."
            r8.<init>((java.lang.String) r0)
            throw r8
        L_0x0016:
            return r1
        L_0x0017:
            java.lang.String r8 = r7.multipartMixedBoundary
            io.netty.handler.codec.http.multipart.InterfaceHttpData r8 = r7.getFileUpload(r8)
            return r8
        L_0x001e:
            io.netty.handler.codec.http.multipart.InterfaceHttpData r8 = r7.findMultipartDisposition()
            return r8
        L_0x0023:
            java.lang.String r8 = r7.multipartMixedBoundary
            io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$MultiPartStatus r0 = io.netty.handler.codec.http.multipart.HttpPostRequestDecoder.MultiPartStatus.MIXEDDISPOSITION
            io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$MultiPartStatus r1 = io.netty.handler.codec.http.multipart.HttpPostRequestDecoder.MultiPartStatus.HEADERDELIMITER
            io.netty.handler.codec.http.multipart.InterfaceHttpData r8 = r7.findMultipartDelimiter(r8, r0, r1)
            return r8
        L_0x002e:
            java.lang.String r8 = r7.multipartDataBoundary
            io.netty.handler.codec.http.multipart.InterfaceHttpData r8 = r7.getFileUpload(r8)
            return r8
        L_0x0035:
            java.util.Map<java.lang.CharSequence, io.netty.handler.codec.http.multipart.Attribute> r8 = r7.currentFieldAttributes
            io.netty.util.AsciiString r0 = io.netty.handler.codec.http.HttpHeaderValues.CHARSET
            java.lang.Object r8 = r8.get(r0)
            io.netty.handler.codec.http.multipart.Attribute r8 = (io.netty.handler.codec.http.multipart.Attribute) r8
            if (r8 == 0) goto L_0x0058
            java.lang.String r8 = r8.getValue()     // Catch:{ IOException -> 0x0051, UnsupportedCharsetException -> 0x004a }
            java.nio.charset.Charset r8 = java.nio.charset.Charset.forName(r8)     // Catch:{ IOException -> 0x0051, UnsupportedCharsetException -> 0x004a }
            goto L_0x0059
        L_0x004a:
            r8 = move-exception
            io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$ErrorDataDecoderException r0 = new io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$ErrorDataDecoderException
            r0.<init>((java.lang.Throwable) r8)
            throw r0
        L_0x0051:
            r8 = move-exception
            io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$ErrorDataDecoderException r0 = new io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$ErrorDataDecoderException
            r0.<init>((java.lang.Throwable) r8)
            throw r0
        L_0x0058:
            r8 = r1
        L_0x0059:
            java.util.Map<java.lang.CharSequence, io.netty.handler.codec.http.multipart.Attribute> r0 = r7.currentFieldAttributes
            io.netty.util.AsciiString r2 = io.netty.handler.codec.http.HttpHeaderValues.NAME
            java.lang.Object r0 = r0.get(r2)
            io.netty.handler.codec.http.multipart.Attribute r0 = (io.netty.handler.codec.http.multipart.Attribute) r0
            io.netty.handler.codec.http.multipart.Attribute r2 = r7.currentAttribute
            if (r2 != 0) goto L_0x00ce
            java.util.Map<java.lang.CharSequence, io.netty.handler.codec.http.multipart.Attribute> r2 = r7.currentFieldAttributes
            io.netty.util.AsciiString r3 = io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH
            java.lang.Object r2 = r2.get(r3)
            io.netty.handler.codec.http.multipart.Attribute r2 = (io.netty.handler.codec.http.multipart.Attribute) r2
            r3 = 0
            if (r2 == 0) goto L_0x007f
            java.lang.String r2 = r2.getValue()     // Catch:{ IOException -> 0x0081, NumberFormatException -> 0x007e }
            long r5 = java.lang.Long.parseLong(r2)     // Catch:{ IOException -> 0x0081, NumberFormatException -> 0x007e }
            goto L_0x0088
        L_0x007e:
        L_0x007f:
            r5 = r3
            goto L_0x0088
        L_0x0081:
            r8 = move-exception
            io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$ErrorDataDecoderException r0 = new io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$ErrorDataDecoderException
            r0.<init>((java.lang.Throwable) r8)
            throw r0
        L_0x0088:
            int r2 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r2 <= 0) goto L_0x009f
            io.netty.handler.codec.http.multipart.HttpDataFactory r2 = r7.factory     // Catch:{ NullPointerException -> 0x00c7, IllegalArgumentException -> 0x00c0, IOException -> 0x00b9 }
            io.netty.handler.codec.http.HttpRequest r3 = r7.request     // Catch:{ NullPointerException -> 0x00c7, IllegalArgumentException -> 0x00c0, IOException -> 0x00b9 }
            java.lang.String r0 = r0.getValue()     // Catch:{ NullPointerException -> 0x00c7, IllegalArgumentException -> 0x00c0, IOException -> 0x00b9 }
            java.lang.String r0 = cleanString(r0)     // Catch:{ NullPointerException -> 0x00c7, IllegalArgumentException -> 0x00c0, IOException -> 0x00b9 }
            io.netty.handler.codec.http.multipart.Attribute r0 = r2.createAttribute((io.netty.handler.codec.http.HttpRequest) r3, (java.lang.String) r0, (long) r5)     // Catch:{ NullPointerException -> 0x00c7, IllegalArgumentException -> 0x00c0, IOException -> 0x00b9 }
            r7.currentAttribute = r0     // Catch:{ NullPointerException -> 0x00c7, IllegalArgumentException -> 0x00c0, IOException -> 0x00b9 }
            goto L_0x00b1
        L_0x009f:
            io.netty.handler.codec.http.multipart.HttpDataFactory r2 = r7.factory     // Catch:{ NullPointerException -> 0x00c7, IllegalArgumentException -> 0x00c0, IOException -> 0x00b9 }
            io.netty.handler.codec.http.HttpRequest r3 = r7.request     // Catch:{ NullPointerException -> 0x00c7, IllegalArgumentException -> 0x00c0, IOException -> 0x00b9 }
            java.lang.String r0 = r0.getValue()     // Catch:{ NullPointerException -> 0x00c7, IllegalArgumentException -> 0x00c0, IOException -> 0x00b9 }
            java.lang.String r0 = cleanString(r0)     // Catch:{ NullPointerException -> 0x00c7, IllegalArgumentException -> 0x00c0, IOException -> 0x00b9 }
            io.netty.handler.codec.http.multipart.Attribute r0 = r2.createAttribute(r3, r0)     // Catch:{ NullPointerException -> 0x00c7, IllegalArgumentException -> 0x00c0, IOException -> 0x00b9 }
            r7.currentAttribute = r0     // Catch:{ NullPointerException -> 0x00c7, IllegalArgumentException -> 0x00c0, IOException -> 0x00b9 }
        L_0x00b1:
            if (r8 == 0) goto L_0x00ce
            io.netty.handler.codec.http.multipart.Attribute r0 = r7.currentAttribute
            r0.setCharset(r8)
            goto L_0x00ce
        L_0x00b9:
            r8 = move-exception
            io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$ErrorDataDecoderException r0 = new io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$ErrorDataDecoderException
            r0.<init>((java.lang.Throwable) r8)
            throw r0
        L_0x00c0:
            r8 = move-exception
            io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$ErrorDataDecoderException r0 = new io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$ErrorDataDecoderException
            r0.<init>((java.lang.Throwable) r8)
            throw r0
        L_0x00c7:
            r8 = move-exception
            io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$ErrorDataDecoderException r0 = new io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$ErrorDataDecoderException
            r0.<init>((java.lang.Throwable) r8)
            throw r0
        L_0x00ce:
            io.netty.buffer.ByteBuf r8 = r7.undecodedChunk
            java.lang.String r0 = r7.multipartDataBoundary
            io.netty.handler.codec.http.multipart.Attribute r2 = r7.currentAttribute
            boolean r8 = loadDataMultipartOptimized(r8, r0, r2)
            if (r8 != 0) goto L_0x00db
            return r1
        L_0x00db:
            io.netty.handler.codec.http.multipart.Attribute r8 = r7.currentAttribute
            r7.currentAttribute = r1
            r7.currentFieldAttributes = r1
            io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$MultiPartStatus r0 = io.netty.handler.codec.http.multipart.HttpPostRequestDecoder.MultiPartStatus.HEADERDELIMITER
            r7.currentStatus = r0
            return r8
        L_0x00e6:
            io.netty.handler.codec.http.multipart.InterfaceHttpData r8 = r7.findMultipartDisposition()
            return r8
        L_0x00eb:
            java.lang.String r8 = r7.multipartDataBoundary
            io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$MultiPartStatus r0 = io.netty.handler.codec.http.multipart.HttpPostRequestDecoder.MultiPartStatus.DISPOSITION
            io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$MultiPartStatus r1 = io.netty.handler.codec.http.multipart.HttpPostRequestDecoder.MultiPartStatus.PREEPILOGUE
            io.netty.handler.codec.http.multipart.InterfaceHttpData r8 = r7.findMultipartDelimiter(r8, r0, r1)
            return r8
        L_0x00f6:
            io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$ErrorDataDecoderException r8 = new io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$ErrorDataDecoderException
            r8.<init>((java.lang.String) r0)
            throw r8
        L_0x00fc:
            io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$ErrorDataDecoderException r8 = new io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$ErrorDataDecoderException
            r8.<init>((java.lang.String) r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.http.multipart.HttpPostMultipartRequestDecoder.decodeMultipart(io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$MultiPartStatus):io.netty.handler.codec.http.multipart.InterfaceHttpData");
    }

    private static void skipControlCharacters(ByteBuf byteBuf) {
        if (!byteBuf.hasArray()) {
            try {
                skipControlCharactersStandard(byteBuf);
            } catch (IndexOutOfBoundsException e) {
                throw new HttpPostRequestDecoder.NotEnoughDataDecoderException((Throwable) e);
            }
        } else {
            HttpPostBodyUtil.SeekAheadOptimize seekAheadOptimize = new HttpPostBodyUtil.SeekAheadOptimize(byteBuf);
            while (seekAheadOptimize.pos < seekAheadOptimize.limit) {
                byte[] bArr = seekAheadOptimize.bytes;
                int i = seekAheadOptimize.pos;
                seekAheadOptimize.pos = i + 1;
                char c = (char) (bArr[i] & 255);
                if (!Character.isISOControl(c) && !Character.isWhitespace(c)) {
                    seekAheadOptimize.setReadPosition(1);
                    return;
                }
            }
            throw new HttpPostRequestDecoder.NotEnoughDataDecoderException("Access out of bounds");
        }
    }

    private static void skipControlCharactersStandard(ByteBuf byteBuf) {
        while (true) {
            char readUnsignedByte = (char) byteBuf.readUnsignedByte();
            if (!Character.isISOControl(readUnsignedByte) && !Character.isWhitespace(readUnsignedByte)) {
                byteBuf.readerIndex(byteBuf.readerIndex() - 1);
                return;
            }
        }
    }

    private InterfaceHttpData findMultipartDelimiter(String str, HttpPostRequestDecoder.MultiPartStatus multiPartStatus, HttpPostRequestDecoder.MultiPartStatus multiPartStatus2) {
        int readerIndex = this.undecodedChunk.readerIndex();
        try {
            skipControlCharacters(this.undecodedChunk);
            skipOneLine();
            try {
                String readDelimiterOptimized = readDelimiterOptimized(this.undecodedChunk, str, this.charset);
                if (readDelimiterOptimized.equals(str)) {
                    this.currentStatus = multiPartStatus;
                    return decodeMultipart(multiPartStatus);
                }
                if (readDelimiterOptimized.equals(str + "--")) {
                    this.currentStatus = multiPartStatus2;
                    if (multiPartStatus2 != HttpPostRequestDecoder.MultiPartStatus.HEADERDELIMITER) {
                        return null;
                    }
                    this.currentFieldAttributes = null;
                    return decodeMultipart(HttpPostRequestDecoder.MultiPartStatus.HEADERDELIMITER);
                }
                this.undecodedChunk.readerIndex(readerIndex);
                throw new HttpPostRequestDecoder.ErrorDataDecoderException("No Multipart delimiter found");
            } catch (HttpPostRequestDecoder.NotEnoughDataDecoderException unused) {
                this.undecodedChunk.readerIndex(readerIndex);
                return null;
            }
        } catch (HttpPostRequestDecoder.NotEnoughDataDecoderException unused2) {
            this.undecodedChunk.readerIndex(readerIndex);
            return null;
        }
    }

    private InterfaceHttpData findMultipartDisposition() {
        int readerIndex = this.undecodedChunk.readerIndex();
        if (this.currentStatus == HttpPostRequestDecoder.MultiPartStatus.DISPOSITION) {
            this.currentFieldAttributes = new TreeMap(CaseIgnoringComparator.INSTANCE);
        }
        while (!skipOneLine()) {
            try {
                skipControlCharacters(this.undecodedChunk);
                String[] splitMultipartHeader = splitMultipartHeader(readLineOptimized(this.undecodedChunk, this.charset));
                if (HttpHeaderNames.CONTENT_DISPOSITION.contentEqualsIgnoreCase(splitMultipartHeader[0])) {
                    if (this.currentStatus == HttpPostRequestDecoder.MultiPartStatus.DISPOSITION) {
                        if (!HttpHeaderValues.FORM_DATA.contentEqualsIgnoreCase(splitMultipartHeader[1])) {
                        }
                    } else if (!HttpHeaderValues.ATTACHMENT.contentEqualsIgnoreCase(splitMultipartHeader[1]) && !HttpHeaderValues.FILE.contentEqualsIgnoreCase(splitMultipartHeader[1])) {
                    }
                    int i = 2;
                    while (i < splitMultipartHeader.length) {
                        try {
                            Attribute contentDispositionAttribute = getContentDispositionAttribute(splitMultipartHeader[i].split("=", 2));
                            this.currentFieldAttributes.put(contentDispositionAttribute.getName(), contentDispositionAttribute);
                            i++;
                        } catch (NullPointerException e) {
                            throw new HttpPostRequestDecoder.ErrorDataDecoderException((Throwable) e);
                        } catch (IllegalArgumentException e2) {
                            throw new HttpPostRequestDecoder.ErrorDataDecoderException((Throwable) e2);
                        }
                    }
                } else if (HttpHeaderNames.CONTENT_TRANSFER_ENCODING.contentEqualsIgnoreCase(splitMultipartHeader[0])) {
                    try {
                        this.currentFieldAttributes.put(HttpHeaderNames.CONTENT_TRANSFER_ENCODING, this.factory.createAttribute(this.request, HttpHeaderNames.CONTENT_TRANSFER_ENCODING.toString(), cleanString(splitMultipartHeader[1])));
                    } catch (NullPointerException e3) {
                        throw new HttpPostRequestDecoder.ErrorDataDecoderException((Throwable) e3);
                    } catch (IllegalArgumentException e4) {
                        throw new HttpPostRequestDecoder.ErrorDataDecoderException((Throwable) e4);
                    }
                } else if (HttpHeaderNames.CONTENT_LENGTH.contentEqualsIgnoreCase(splitMultipartHeader[0])) {
                    try {
                        this.currentFieldAttributes.put(HttpHeaderNames.CONTENT_LENGTH, this.factory.createAttribute(this.request, HttpHeaderNames.CONTENT_LENGTH.toString(), cleanString(splitMultipartHeader[1])));
                    } catch (NullPointerException e5) {
                        throw new HttpPostRequestDecoder.ErrorDataDecoderException((Throwable) e5);
                    } catch (IllegalArgumentException e6) {
                        throw new HttpPostRequestDecoder.ErrorDataDecoderException((Throwable) e6);
                    }
                } else if (!HttpHeaderNames.CONTENT_TYPE.contentEqualsIgnoreCase(splitMultipartHeader[0])) {
                    continue;
                } else if (!HttpHeaderValues.MULTIPART_MIXED.contentEqualsIgnoreCase(splitMultipartHeader[1])) {
                    for (int i2 = 1; i2 < splitMultipartHeader.length; i2++) {
                        String asciiString = HttpHeaderValues.CHARSET.toString();
                        if (splitMultipartHeader[i2].regionMatches(true, 0, asciiString, 0, asciiString.length())) {
                            try {
                                this.currentFieldAttributes.put(HttpHeaderValues.CHARSET, this.factory.createAttribute(this.request, asciiString, cleanString(StringUtil.substringAfter(splitMultipartHeader[i2], '='))));
                            } catch (NullPointerException e7) {
                                throw new HttpPostRequestDecoder.ErrorDataDecoderException((Throwable) e7);
                            } catch (IllegalArgumentException e8) {
                                throw new HttpPostRequestDecoder.ErrorDataDecoderException((Throwable) e8);
                            }
                        } else if (splitMultipartHeader[i2].contains("=")) {
                            String substringBefore = StringUtil.substringBefore(splitMultipartHeader[i2], '=');
                            try {
                                this.currentFieldAttributes.put(substringBefore, this.factory.createAttribute(this.request, cleanString(substringBefore), StringUtil.substringAfter(splitMultipartHeader[i2], '=')));
                            } catch (NullPointerException e9) {
                                throw new HttpPostRequestDecoder.ErrorDataDecoderException((Throwable) e9);
                            } catch (IllegalArgumentException e10) {
                                throw new HttpPostRequestDecoder.ErrorDataDecoderException((Throwable) e10);
                            }
                        } else {
                            try {
                                Attribute createAttribute = this.factory.createAttribute(this.request, cleanString(splitMultipartHeader[0]), splitMultipartHeader[i2]);
                                this.currentFieldAttributes.put(createAttribute.getName(), createAttribute);
                            } catch (NullPointerException e11) {
                                throw new HttpPostRequestDecoder.ErrorDataDecoderException((Throwable) e11);
                            } catch (IllegalArgumentException e12) {
                                throw new HttpPostRequestDecoder.ErrorDataDecoderException((Throwable) e12);
                            }
                        }
                    }
                } else if (this.currentStatus == HttpPostRequestDecoder.MultiPartStatus.DISPOSITION) {
                    String substringAfter = StringUtil.substringAfter(splitMultipartHeader[2], '=');
                    this.multipartMixedBoundary = "--" + substringAfter;
                    this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.MIXEDDELIMITER;
                    return decodeMultipart(HttpPostRequestDecoder.MultiPartStatus.MIXEDDELIMITER);
                } else {
                    throw new HttpPostRequestDecoder.ErrorDataDecoderException("Mixed Multipart found in a previous Mixed Multipart");
                }
            } catch (HttpPostRequestDecoder.NotEnoughDataDecoderException unused) {
                this.undecodedChunk.readerIndex(readerIndex);
                return null;
            }
        }
        Attribute attribute = this.currentFieldAttributes.get(HttpHeaderValues.FILENAME);
        if (this.currentStatus == HttpPostRequestDecoder.MultiPartStatus.DISPOSITION) {
            if (attribute != null) {
                this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.FILEUPLOAD;
                return decodeMultipart(HttpPostRequestDecoder.MultiPartStatus.FILEUPLOAD);
            }
            this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.FIELD;
            return decodeMultipart(HttpPostRequestDecoder.MultiPartStatus.FIELD);
        } else if (attribute != null) {
            this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.MIXEDFILEUPLOAD;
            return decodeMultipart(HttpPostRequestDecoder.MultiPartStatus.MIXEDFILEUPLOAD);
        } else {
            throw new HttpPostRequestDecoder.ErrorDataDecoderException("Filename not found");
        }
    }

    private Attribute getContentDispositionAttribute(String... strArr) {
        String cleanString = cleanString(strArr[0]);
        String str = strArr[1];
        if (HttpHeaderValues.FILENAME.contentEquals(cleanString)) {
            int length = str.length() - 1;
            if (length > 0 && str.charAt(0) == '\"' && str.charAt(length) == '\"') {
                str = str.substring(1, length);
            }
        } else if (FILENAME_ENCODED.equals(cleanString)) {
            try {
                cleanString = HttpHeaderValues.FILENAME.toString();
                String[] split = cleanString(str).split("'", 3);
                str = QueryStringDecoder.decodeComponent(split[2], Charset.forName(split[0]));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new HttpPostRequestDecoder.ErrorDataDecoderException((Throwable) e);
            } catch (UnsupportedCharsetException e2) {
                throw new HttpPostRequestDecoder.ErrorDataDecoderException((Throwable) e2);
            }
        } else {
            str = cleanString(str);
        }
        return this.factory.createAttribute(this.request, cleanString, str);
    }

    /* access modifiers changed from: protected */
    public InterfaceHttpData getFileUpload(String str) {
        String str2;
        Attribute attribute = this.currentFieldAttributes.get(HttpHeaderNames.CONTENT_TRANSFER_ENCODING);
        Charset charset2 = this.charset;
        HttpPostBodyUtil.TransferEncodingMechanism transferEncodingMechanism = HttpPostBodyUtil.TransferEncodingMechanism.BIT7;
        if (attribute != null) {
            try {
                String lowerCase = attribute.getValue().toLowerCase();
                if (lowerCase.equals(HttpPostBodyUtil.TransferEncodingMechanism.BIT7.value())) {
                    charset2 = CharsetUtil.US_ASCII;
                } else if (lowerCase.equals(HttpPostBodyUtil.TransferEncodingMechanism.BIT8.value())) {
                    charset2 = CharsetUtil.ISO_8859_1;
                    transferEncodingMechanism = HttpPostBodyUtil.TransferEncodingMechanism.BIT8;
                } else if (lowerCase.equals(HttpPostBodyUtil.TransferEncodingMechanism.BINARY.value())) {
                    transferEncodingMechanism = HttpPostBodyUtil.TransferEncodingMechanism.BINARY;
                } else {
                    throw new HttpPostRequestDecoder.ErrorDataDecoderException("TransferEncoding Unknown: " + lowerCase);
                }
            } catch (IOException e) {
                throw new HttpPostRequestDecoder.ErrorDataDecoderException((Throwable) e);
            }
        }
        Attribute attribute2 = this.currentFieldAttributes.get(HttpHeaderValues.CHARSET);
        if (attribute2 != null) {
            try {
                charset2 = Charset.forName(attribute2.getValue());
            } catch (IOException e2) {
                throw new HttpPostRequestDecoder.ErrorDataDecoderException((Throwable) e2);
            } catch (UnsupportedCharsetException e3) {
                throw new HttpPostRequestDecoder.ErrorDataDecoderException((Throwable) e3);
            }
        }
        Charset charset3 = charset2;
        if (this.currentFileUpload == null) {
            Attribute attribute3 = this.currentFieldAttributes.get(HttpHeaderValues.FILENAME);
            Attribute attribute4 = this.currentFieldAttributes.get(HttpHeaderValues.NAME);
            Attribute attribute5 = this.currentFieldAttributes.get(HttpHeaderNames.CONTENT_TYPE);
            Attribute attribute6 = this.currentFieldAttributes.get(HttpHeaderNames.CONTENT_LENGTH);
            long j = 0;
            if (attribute6 != null) {
                try {
                    j = Long.parseLong(attribute6.getValue());
                } catch (IOException e4) {
                    throw new HttpPostRequestDecoder.ErrorDataDecoderException((Throwable) e4);
                } catch (NumberFormatException unused) {
                }
            }
            long j2 = j;
            if (attribute5 != null) {
                try {
                    str2 = attribute5.getValue();
                } catch (NullPointerException e5) {
                    throw new HttpPostRequestDecoder.ErrorDataDecoderException((Throwable) e5);
                } catch (IllegalArgumentException e6) {
                    throw new HttpPostRequestDecoder.ErrorDataDecoderException((Throwable) e6);
                } catch (IOException e7) {
                    throw new HttpPostRequestDecoder.ErrorDataDecoderException((Throwable) e7);
                }
            } else {
                str2 = HttpPostBodyUtil.DEFAULT_BINARY_CONTENT_TYPE;
            }
            String str3 = str2;
            this.currentFileUpload = this.factory.createFileUpload(this.request, cleanString(attribute4.getValue()), cleanString(attribute3.getValue()), str3, transferEncodingMechanism.value(), charset3, j2);
        }
        if (!loadDataMultipartOptimized(this.undecodedChunk, str, this.currentFileUpload) || !this.currentFileUpload.isCompleted()) {
            return null;
        }
        if (this.currentStatus == HttpPostRequestDecoder.MultiPartStatus.FILEUPLOAD) {
            this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.HEADERDELIMITER;
            this.currentFieldAttributes = null;
        } else {
            this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.MIXEDDELIMITER;
            cleanMixedAttributes();
        }
        FileUpload fileUpload = this.currentFileUpload;
        this.currentFileUpload = null;
        return fileUpload;
    }

    public void destroy() {
        cleanFiles();
        for (InterfaceHttpData next : this.bodyListHttpData) {
            if (next.refCnt() > 0) {
                next.release();
            }
        }
        this.destroyed = true;
        ByteBuf byteBuf = this.undecodedChunk;
        if (byteBuf != null && byteBuf.refCnt() > 0) {
            this.undecodedChunk.release();
            this.undecodedChunk = null;
        }
    }

    public void cleanFiles() {
        checkDestroyed();
        this.factory.cleanRequestHttpData(this.request);
    }

    public void removeHttpDataFromClean(InterfaceHttpData interfaceHttpData) {
        checkDestroyed();
        this.factory.removeHttpDataFromClean(this.request, interfaceHttpData);
    }

    private void cleanMixedAttributes() {
        this.currentFieldAttributes.remove(HttpHeaderValues.CHARSET);
        this.currentFieldAttributes.remove(HttpHeaderNames.CONTENT_LENGTH);
        this.currentFieldAttributes.remove(HttpHeaderNames.CONTENT_TRANSFER_ENCODING);
        this.currentFieldAttributes.remove(HttpHeaderNames.CONTENT_TYPE);
        this.currentFieldAttributes.remove(HttpHeaderValues.FILENAME);
    }

    private static String readLineOptimized(ByteBuf byteBuf, Charset charset2) {
        ByteBuf byteBuf2;
        int readerIndex = byteBuf.readerIndex();
        try {
            if (byteBuf.isReadable()) {
                int findLineBreak = HttpPostBodyUtil.findLineBreak(byteBuf, byteBuf.readerIndex());
                if (findLineBreak > 0) {
                    byteBuf2 = null;
                    ByteBuf heapBuffer = byteBuf.alloc().heapBuffer(findLineBreak);
                    heapBuffer.writeBytes(byteBuf, findLineBreak);
                    if (byteBuf.readByte() == 13) {
                        byteBuf.readByte();
                    }
                    String byteBuf3 = heapBuffer.toString(charset2);
                    heapBuffer.release();
                    return byteBuf3;
                }
                throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
            }
            byteBuf.readerIndex(readerIndex);
            throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
        } catch (IndexOutOfBoundsException e) {
            byteBuf.readerIndex(readerIndex);
            throw new HttpPostRequestDecoder.NotEnoughDataDecoderException((Throwable) e);
        } catch (Throwable th) {
            byteBuf2.release();
            throw th;
        }
    }

    private static String readDelimiterOptimized(ByteBuf byteBuf, String str, Charset charset2) {
        int readerIndex = byteBuf.readerIndex();
        byte[] bytes = str.getBytes(charset2);
        int length = bytes.length;
        try {
            int findDelimiter = HttpPostBodyUtil.findDelimiter(byteBuf, readerIndex, bytes, false);
            if (findDelimiter >= 0) {
                StringBuilder sb = new StringBuilder(str);
                byteBuf.readerIndex(findDelimiter + readerIndex + length);
                if (byteBuf.isReadable()) {
                    byte readByte = byteBuf.readByte();
                    if (readByte == 13) {
                        if (byteBuf.readByte() == 10) {
                            return sb.toString();
                        }
                        byteBuf.readerIndex(readerIndex);
                        throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
                    } else if (readByte == 10) {
                        return sb.toString();
                    } else {
                        if (readByte == 45) {
                            sb.append("--");
                            if (byteBuf.readByte() == 45) {
                                if (!byteBuf.isReadable()) {
                                    return sb.toString();
                                }
                                byte readByte2 = byteBuf.readByte();
                                if (readByte2 == 13) {
                                    if (byteBuf.readByte() == 10) {
                                        return sb.toString();
                                    }
                                    byteBuf.readerIndex(readerIndex);
                                    throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
                                } else if (readByte2 == 10) {
                                    return sb.toString();
                                } else {
                                    byteBuf.readerIndex(byteBuf.readerIndex() - 1);
                                    return sb.toString();
                                }
                            }
                        }
                    }
                }
                byteBuf.readerIndex(readerIndex);
                throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
            }
            byteBuf.readerIndex(readerIndex);
            throw new HttpPostRequestDecoder.NotEnoughDataDecoderException();
        } catch (IndexOutOfBoundsException e) {
            byteBuf.readerIndex(readerIndex);
            throw new HttpPostRequestDecoder.NotEnoughDataDecoderException((Throwable) e);
        }
    }

    private static void rewriteCurrentBuffer(ByteBuf byteBuf, int i) {
        if (i != 0) {
            int readerIndex = byteBuf.readerIndex();
            int readableBytes = byteBuf.readableBytes();
            if (readableBytes == i) {
                byteBuf.readerIndex(readerIndex);
                byteBuf.writerIndex(readerIndex);
                return;
            }
            byteBuf.setBytes(readerIndex, byteBuf, readerIndex + i, readableBytes - i);
            byteBuf.readerIndex(readerIndex);
            byteBuf.writerIndex((readerIndex + readableBytes) - i);
        }
    }

    private static boolean loadDataMultipartOptimized(ByteBuf byteBuf, String str, HttpData httpData) {
        if (!byteBuf.isReadable()) {
            return false;
        }
        int readerIndex = byteBuf.readerIndex();
        byte[] bytes = str.getBytes(httpData.getCharset());
        int findDelimiter = HttpPostBodyUtil.findDelimiter(byteBuf, readerIndex, bytes, true);
        if (findDelimiter < 0) {
            int readableBytes = byteBuf.readableBytes();
            int length = (readableBytes - bytes.length) - 1;
            if (length < 0) {
                length = 0;
            }
            int findLastLineBreak = HttpPostBodyUtil.findLastLineBreak(byteBuf, readerIndex + length);
            if (findLastLineBreak < 0 && httpData.definedLength() == (httpData.length() + ((long) readableBytes)) - 1 && byteBuf.getByte((readableBytes + readerIndex) - 1) == 13) {
                findLastLineBreak = readableBytes - 1;
                length = 0;
            }
            if (findLastLineBreak < 0) {
                try {
                    httpData.addContent(byteBuf.copy(), false);
                    byteBuf.readerIndex(readerIndex);
                    byteBuf.writerIndex(readerIndex);
                    return false;
                } catch (IOException e) {
                    throw new HttpPostRequestDecoder.ErrorDataDecoderException((Throwable) e);
                }
            } else {
                int i = findLastLineBreak + length;
                if (i == 0) {
                    return false;
                }
                try {
                    httpData.addContent(byteBuf.copy(readerIndex, i), false);
                    rewriteCurrentBuffer(byteBuf, i);
                    return false;
                } catch (IOException e2) {
                    throw new HttpPostRequestDecoder.ErrorDataDecoderException((Throwable) e2);
                }
            }
        } else {
            try {
                httpData.addContent(byteBuf.copy(readerIndex, findDelimiter), true);
                rewriteCurrentBuffer(byteBuf, findDelimiter);
                return true;
            } catch (IOException e3) {
                throw new HttpPostRequestDecoder.ErrorDataDecoderException((Throwable) e3);
            }
        }
    }

    private static String cleanString(String str) {
        int length = str.length();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt != 9) {
                if (charAt != '\"') {
                    if (!(charAt == ',' || charAt == '=' || charAt == ':' || charAt == ';')) {
                        sb.append(charAt);
                    }
                }
            }
            sb.append(' ');
        }
        return sb.toString().trim();
    }

    private boolean skipOneLine() {
        if (!this.undecodedChunk.isReadable()) {
            return false;
        }
        byte readByte = this.undecodedChunk.readByte();
        if (readByte == 13) {
            if (!this.undecodedChunk.isReadable()) {
                ByteBuf byteBuf = this.undecodedChunk;
                byteBuf.readerIndex(byteBuf.readerIndex() - 1);
                return false;
            } else if (this.undecodedChunk.readByte() == 10) {
                return true;
            } else {
                ByteBuf byteBuf2 = this.undecodedChunk;
                byteBuf2.readerIndex(byteBuf2.readerIndex() - 2);
                return false;
            }
        } else if (readByte == 10) {
            return true;
        } else {
            ByteBuf byteBuf3 = this.undecodedChunk;
            byteBuf3.readerIndex(byteBuf3.readerIndex() - 1);
            return false;
        }
    }

    private static String[] splitMultipartHeader(String str) {
        String[] strArr;
        ArrayList arrayList = new ArrayList(1);
        int findNonWhitespace = HttpPostBodyUtil.findNonWhitespace(str, 0);
        int i = findNonWhitespace;
        while (i < str.length() && (r4 = str.charAt(i)) != ':' && !Character.isWhitespace(r4)) {
            i++;
        }
        int i2 = i;
        while (true) {
            if (i2 >= str.length()) {
                break;
            } else if (str.charAt(i2) == ':') {
                i2++;
                break;
            } else {
                i2++;
            }
        }
        int findNonWhitespace2 = HttpPostBodyUtil.findNonWhitespace(str, i2);
        int findEndOfString = HttpPostBodyUtil.findEndOfString(str);
        arrayList.add(str.substring(findNonWhitespace, i));
        String substring = findNonWhitespace2 >= findEndOfString ? "" : str.substring(findNonWhitespace2, findEndOfString);
        if (substring.indexOf(59) >= 0) {
            strArr = splitMultipartHeaderValues(substring);
        } else {
            strArr = substring.split(AnsiRenderer.CODE_LIST_SEPARATOR);
        }
        for (String trim : strArr) {
            arrayList.add(trim.trim());
        }
        String[] strArr2 = new String[arrayList.size()];
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            strArr2[i3] = (String) arrayList.get(i3);
        }
        return strArr2;
    }

    private static String[] splitMultipartHeaderValues(String str) {
        ArrayList arrayList = InternalThreadLocalMap.get().arrayList(1);
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if (z) {
                if (z2) {
                    z2 = false;
                } else if (charAt == '\\') {
                    z2 = true;
                } else if (charAt == '\"') {
                    z = false;
                }
            } else if (charAt == '\"') {
                z = true;
            } else if (charAt == ';') {
                arrayList.add(str.substring(i, i2));
                i = i2 + 1;
            }
        }
        arrayList.add(str.substring(i));
        return (String[]) arrayList.toArray(EmptyArrays.EMPTY_STRINGS);
    }

    /* access modifiers changed from: package-private */
    public int getCurrentAllocatedCapacity() {
        return this.undecodedChunk.capacity();
    }
}
