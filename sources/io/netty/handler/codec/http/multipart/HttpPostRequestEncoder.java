package io.netty.handler.codec.http.multipart;

import io.ktor.http.ContentDisposition;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.DefaultHttpContent;
import io.netty.handler.codec.http.EmptyHttpHeaders;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpConstants;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.codec.http.multipart.HttpPostBodyUtil;
import io.netty.handler.stream.ChunkedInput;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.regex.Pattern;

public class HttpPostRequestEncoder implements ChunkedInput<HttpContent> {
    private static final Map.Entry[] percentEncodings = {new AbstractMap.SimpleImmutableEntry(Pattern.compile("\\*"), "%2A"), new AbstractMap.SimpleImmutableEntry(Pattern.compile("\\+"), "%20"), new AbstractMap.SimpleImmutableEntry(Pattern.compile("~"), "%7E")};
    private final List<InterfaceHttpData> bodyListDatas;
    private final Charset charset;
    private ByteBuf currentBuffer;
    private InterfaceHttpData currentData;
    private FileUpload currentFileUpload;
    private boolean duringMixedMode;
    private final EncoderMode encoderMode;
    private final HttpDataFactory factory;
    private long globalBodySize;
    private long globalProgress;
    private boolean headerFinalized;
    private boolean isChunked;
    private boolean isKey;
    private boolean isLastChunk;
    private boolean isLastChunkSent;
    private final boolean isMultipart;
    private ListIterator<InterfaceHttpData> iterator;
    String multipartDataBoundary;
    final List<InterfaceHttpData> multipartHttpDatas;
    String multipartMixedBoundary;
    private final HttpRequest request;

    public enum EncoderMode {
        RFC1738,
        RFC3986,
        HTML5
    }

    public void close() throws Exception {
    }

    public HttpPostRequestEncoder(HttpRequest httpRequest, boolean z) throws ErrorDataEncoderException {
        this(new DefaultHttpDataFactory(16384), httpRequest, z, HttpConstants.DEFAULT_CHARSET, EncoderMode.RFC1738);
    }

    public HttpPostRequestEncoder(HttpDataFactory httpDataFactory, HttpRequest httpRequest, boolean z) throws ErrorDataEncoderException {
        this(httpDataFactory, httpRequest, z, HttpConstants.DEFAULT_CHARSET, EncoderMode.RFC1738);
    }

    public HttpPostRequestEncoder(HttpDataFactory httpDataFactory, HttpRequest httpRequest, boolean z, Charset charset2, EncoderMode encoderMode2) throws ErrorDataEncoderException {
        this.isKey = true;
        this.request = (HttpRequest) ObjectUtil.checkNotNull(httpRequest, "request");
        this.charset = (Charset) ObjectUtil.checkNotNull(charset2, "charset");
        this.factory = (HttpDataFactory) ObjectUtil.checkNotNull(httpDataFactory, "factory");
        if (!HttpMethod.TRACE.equals(httpRequest.method())) {
            this.bodyListDatas = new ArrayList();
            this.isLastChunk = false;
            this.isLastChunkSent = false;
            this.isMultipart = z;
            this.multipartHttpDatas = new ArrayList();
            this.encoderMode = encoderMode2;
            if (z) {
                initDataMultipart();
                return;
            }
            return;
        }
        throw new ErrorDataEncoderException("Cannot create a Encoder if request is a TRACE");
    }

    public void cleanFiles() {
        this.factory.cleanRequestHttpData(this.request);
    }

    public boolean isMultipart() {
        return this.isMultipart;
    }

    private void initDataMultipart() {
        this.multipartDataBoundary = getNewMultipartDelimiter();
    }

    private void initMixedMultipart() {
        this.multipartMixedBoundary = getNewMultipartDelimiter();
    }

    private static String getNewMultipartDelimiter() {
        return Long.toHexString(PlatformDependent.threadLocalRandom().nextLong());
    }

    public List<InterfaceHttpData> getBodyListAttributes() {
        return this.bodyListDatas;
    }

    public void setBodyHttpDatas(List<InterfaceHttpData> list) throws ErrorDataEncoderException {
        ObjectUtil.checkNotNull(list, "datas");
        this.globalBodySize = 0;
        this.bodyListDatas.clear();
        this.currentFileUpload = null;
        this.duringMixedMode = false;
        this.multipartHttpDatas.clear();
        for (InterfaceHttpData addBodyHttpData : list) {
            addBodyHttpData(addBodyHttpData);
        }
    }

    public void addBodyAttribute(String str, String str2) throws ErrorDataEncoderException {
        if (str2 == null) {
            str2 = "";
        }
        addBodyHttpData(this.factory.createAttribute(this.request, (String) ObjectUtil.checkNotNull(str, ContentDisposition.Parameters.Name), str2));
    }

    public void addBodyFileUpload(String str, File file, String str2, boolean z) throws ErrorDataEncoderException {
        addBodyFileUpload(str, file.getName(), file, str2, z);
    }

    public void addBodyFileUpload(String str, String str2, File file, String str3, boolean z) throws ErrorDataEncoderException {
        ObjectUtil.checkNotNull(str, ContentDisposition.Parameters.Name);
        ObjectUtil.checkNotNull(file, "file");
        if (str2 == null) {
            str2 = "";
        }
        String str4 = str2;
        if (str3 == null) {
            if (z) {
                str3 = HttpPostBodyUtil.DEFAULT_TEXT_CONTENT_TYPE;
            } else {
                str3 = HttpPostBodyUtil.DEFAULT_BINARY_CONTENT_TYPE;
            }
        }
        FileUpload createFileUpload = this.factory.createFileUpload(this.request, str, str4, str3, !z ? HttpPostBodyUtil.TransferEncodingMechanism.BINARY.value() : null, (Charset) null, file.length());
        try {
            createFileUpload.setContent(file);
            addBodyHttpData(createFileUpload);
        } catch (IOException e) {
            throw new ErrorDataEncoderException((Throwable) e);
        }
    }

    public void addBodyFileUploads(String str, File[] fileArr, String[] strArr, boolean[] zArr) throws ErrorDataEncoderException {
        if (fileArr.length == strArr.length || fileArr.length == zArr.length) {
            for (int i = 0; i < fileArr.length; i++) {
                addBodyFileUpload(str, fileArr[i], strArr[i], zArr[i]);
            }
            return;
        }
        throw new IllegalArgumentException("Different array length");
    }

    /* JADX WARNING: Removed duplicated region for block: B:52:0x0308  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0374  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0450  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0471  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addBodyHttpData(io.netty.handler.codec.http.multipart.InterfaceHttpData r17) throws io.netty.handler.codec.http.multipart.HttpPostRequestEncoder.ErrorDataEncoderException {
        /*
            r16 = this;
            r1 = r16
            r0 = r17
            boolean r2 = r1.headerFinalized
            if (r2 != 0) goto L_0x04b9
            java.util.List<io.netty.handler.codec.http.multipart.InterfaceHttpData> r2 = r1.bodyListDatas
            java.lang.String r3 = "data"
            java.lang.Object r3 = io.netty.util.internal.ObjectUtil.checkNotNull(r0, r3)
            r2.add(r3)
            boolean r2 = r1.isMultipart
            r3 = 1
            if (r2 != 0) goto L_0x009b
            boolean r2 = r0 instanceof io.netty.handler.codec.http.multipart.Attribute
            r4 = 1
            if (r2 == 0) goto L_0x005e
            io.netty.handler.codec.http.multipart.Attribute r0 = (io.netty.handler.codec.http.multipart.Attribute) r0
            java.lang.String r2 = r0.getName()     // Catch:{ IOException -> 0x0057 }
            java.nio.charset.Charset r6 = r1.charset     // Catch:{ IOException -> 0x0057 }
            java.lang.String r2 = r1.encodeAttribute(r2, r6)     // Catch:{ IOException -> 0x0057 }
            java.lang.String r0 = r0.getValue()     // Catch:{ IOException -> 0x0057 }
            java.nio.charset.Charset r6 = r1.charset     // Catch:{ IOException -> 0x0057 }
            java.lang.String r0 = r1.encodeAttribute(r0, r6)     // Catch:{ IOException -> 0x0057 }
            io.netty.handler.codec.http.multipart.HttpDataFactory r6 = r1.factory     // Catch:{ IOException -> 0x0057 }
            io.netty.handler.codec.http.HttpRequest r7 = r1.request     // Catch:{ IOException -> 0x0057 }
            io.netty.handler.codec.http.multipart.Attribute r0 = r6.createAttribute((io.netty.handler.codec.http.HttpRequest) r7, (java.lang.String) r2, (java.lang.String) r0)     // Catch:{ IOException -> 0x0057 }
            java.util.List<io.netty.handler.codec.http.multipart.InterfaceHttpData> r2 = r1.multipartHttpDatas     // Catch:{ IOException -> 0x0057 }
            r2.add(r0)     // Catch:{ IOException -> 0x0057 }
            long r6 = r1.globalBodySize     // Catch:{ IOException -> 0x0057 }
            java.lang.String r2 = r0.getName()     // Catch:{ IOException -> 0x0057 }
            int r2 = r2.length()     // Catch:{ IOException -> 0x0057 }
            int r2 = r2 + r3
            long r2 = (long) r2     // Catch:{ IOException -> 0x0057 }
            long r8 = r0.length()     // Catch:{ IOException -> 0x0057 }
            long r2 = r2 + r8
            long r2 = r2 + r4
            long r6 = r6 + r2
            r1.globalBodySize = r6     // Catch:{ IOException -> 0x0057 }
            goto L_0x009a
        L_0x0057:
            r0 = move-exception
            io.netty.handler.codec.http.multipart.HttpPostRequestEncoder$ErrorDataEncoderException r2 = new io.netty.handler.codec.http.multipart.HttpPostRequestEncoder$ErrorDataEncoderException
            r2.<init>((java.lang.Throwable) r0)
            throw r2
        L_0x005e:
            boolean r2 = r0 instanceof io.netty.handler.codec.http.multipart.FileUpload
            if (r2 == 0) goto L_0x009a
            io.netty.handler.codec.http.multipart.FileUpload r0 = (io.netty.handler.codec.http.multipart.FileUpload) r0
            java.lang.String r2 = r0.getName()
            java.nio.charset.Charset r6 = r1.charset
            java.lang.String r2 = r1.encodeAttribute(r2, r6)
            java.lang.String r0 = r0.getFilename()
            java.nio.charset.Charset r6 = r1.charset
            java.lang.String r0 = r1.encodeAttribute(r0, r6)
            io.netty.handler.codec.http.multipart.HttpDataFactory r6 = r1.factory
            io.netty.handler.codec.http.HttpRequest r7 = r1.request
            io.netty.handler.codec.http.multipart.Attribute r0 = r6.createAttribute((io.netty.handler.codec.http.HttpRequest) r7, (java.lang.String) r2, (java.lang.String) r0)
            java.util.List<io.netty.handler.codec.http.multipart.InterfaceHttpData> r2 = r1.multipartHttpDatas
            r2.add(r0)
            long r6 = r1.globalBodySize
            java.lang.String r2 = r0.getName()
            int r2 = r2.length()
            int r2 = r2 + r3
            long r2 = (long) r2
            long r8 = r0.length()
            long r2 = r2 + r8
            long r2 = r2 + r4
            long r6 = r6 + r2
            r1.globalBodySize = r6
        L_0x009a:
            return
        L_0x009b:
            boolean r2 = r0 instanceof io.netty.handler.codec.http.multipart.Attribute
            r4 = 61
            r5 = 0
            java.lang.String r6 = "\"\r\n"
            r7 = 0
            java.lang.String r8 = "=\""
            java.lang.String r9 = "--"
            java.lang.String r10 = "; "
            java.lang.String r11 = ": "
            java.lang.String r12 = "\r\n"
            if (r2 == 0) goto L_0x019c
            boolean r2 = r1.duringMixedMode
            if (r2 == 0) goto L_0x00db
            io.netty.handler.codec.http.multipart.InternalAttribute r2 = new io.netty.handler.codec.http.multipart.InternalAttribute
            java.nio.charset.Charset r3 = r1.charset
            r2.<init>(r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r13 = "\r\n--"
            r3.<init>(r13)
            java.lang.String r13 = r1.multipartMixedBoundary
            r3.append(r13)
            r3.append(r9)
            java.lang.String r3 = r3.toString()
            r2.addValue(r3)
            java.util.List<io.netty.handler.codec.http.multipart.InterfaceHttpData> r3 = r1.multipartHttpDatas
            r3.add(r2)
            r1.multipartMixedBoundary = r5
            r1.currentFileUpload = r5
            r1.duringMixedMode = r7
        L_0x00db:
            io.netty.handler.codec.http.multipart.InternalAttribute r2 = new io.netty.handler.codec.http.multipart.InternalAttribute
            java.nio.charset.Charset r3 = r1.charset
            r2.<init>(r3)
            java.util.List<io.netty.handler.codec.http.multipart.InterfaceHttpData> r3 = r1.multipartHttpDatas
            boolean r3 = r3.isEmpty()
            if (r3 != 0) goto L_0x00ed
            r2.addValue(r12)
        L_0x00ed:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r9)
            java.lang.String r5 = r1.multipartDataBoundary
            r3.append(r5)
            r3.append(r12)
            java.lang.String r3 = r3.toString()
            r2.addValue(r3)
            r3 = r0
            io.netty.handler.codec.http.multipart.Attribute r3 = (io.netty.handler.codec.http.multipart.Attribute) r3
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            io.netty.util.AsciiString r7 = io.netty.handler.codec.http.HttpHeaderNames.CONTENT_DISPOSITION
            r5.append(r7)
            r5.append(r11)
            io.netty.util.AsciiString r7 = io.netty.handler.codec.http.HttpHeaderValues.FORM_DATA
            r5.append(r7)
            r5.append(r10)
            io.netty.util.AsciiString r7 = io.netty.handler.codec.http.HttpHeaderValues.NAME
            r5.append(r7)
            r5.append(r8)
            java.lang.String r7 = r3.getName()
            r5.append(r7)
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r2.addValue(r5)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            io.netty.util.AsciiString r6 = io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH
            r5.append(r6)
            r5.append(r11)
            long r6 = r3.length()
            r5.append(r6)
            r5.append(r12)
            java.lang.String r5 = r5.toString()
            r2.addValue(r5)
            java.nio.charset.Charset r5 = r3.getCharset()
            if (r5 == 0) goto L_0x017e
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            io.netty.util.AsciiString r7 = io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE
            r6.append(r7)
            java.lang.String r7 = ": text/plain; "
            r6.append(r7)
            io.netty.util.AsciiString r7 = io.netty.handler.codec.http.HttpHeaderValues.CHARSET
            r6.append(r7)
            r6.append(r4)
            java.lang.String r4 = r5.name()
            r6.append(r4)
            r6.append(r12)
            java.lang.String r4 = r6.toString()
            r2.addValue(r4)
        L_0x017e:
            r2.addValue(r12)
            java.util.List<io.netty.handler.codec.http.multipart.InterfaceHttpData> r4 = r1.multipartHttpDatas
            r4.add(r2)
            java.util.List<io.netty.handler.codec.http.multipart.InterfaceHttpData> r4 = r1.multipartHttpDatas
            r4.add(r0)
            long r4 = r1.globalBodySize
            long r6 = r3.length()
            int r0 = r2.size()
            long r2 = (long) r0
            long r6 = r6 + r2
            long r4 = r4 + r6
            r1.globalBodySize = r4
            goto L_0x04b8
        L_0x019c:
            boolean r2 = r0 instanceof io.netty.handler.codec.http.multipart.FileUpload
            if (r2 == 0) goto L_0x04b8
            r2 = r0
            io.netty.handler.codec.http.multipart.FileUpload r2 = (io.netty.handler.codec.http.multipart.FileUpload) r2
            io.netty.handler.codec.http.multipart.InternalAttribute r13 = new io.netty.handler.codec.http.multipart.InternalAttribute
            java.nio.charset.Charset r14 = r1.charset
            r13.<init>(r14)
            java.util.List<io.netty.handler.codec.http.multipart.InterfaceHttpData> r14 = r1.multipartHttpDatas
            boolean r14 = r14.isEmpty()
            if (r14 != 0) goto L_0x01b5
            r13.addValue(r12)
        L_0x01b5:
            boolean r14 = r1.duringMixedMode
            if (r14 == 0) goto L_0x01f8
            io.netty.handler.codec.http.multipart.FileUpload r14 = r1.currentFileUpload
            if (r14 == 0) goto L_0x01cd
            java.lang.String r14 = r14.getName()
            java.lang.String r15 = r2.getName()
            boolean r14 = r14.equals(r15)
            if (r14 == 0) goto L_0x01cd
            goto L_0x0306
        L_0x01cd:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r9)
            java.lang.String r14 = r1.multipartMixedBoundary
            r3.append(r14)
            r3.append(r9)
            java.lang.String r3 = r3.toString()
            r13.addValue(r3)
            java.util.List<io.netty.handler.codec.http.multipart.InterfaceHttpData> r3 = r1.multipartHttpDatas
            r3.add(r13)
            r1.multipartMixedBoundary = r5
            io.netty.handler.codec.http.multipart.InternalAttribute r13 = new io.netty.handler.codec.http.multipart.InternalAttribute
            java.nio.charset.Charset r3 = r1.charset
            r13.<init>(r3)
            r13.addValue(r12)
            r1.currentFileUpload = r2
            r1.duringMixedMode = r7
            goto L_0x0305
        L_0x01f8:
            io.netty.handler.codec.http.multipart.HttpPostRequestEncoder$EncoderMode r5 = r1.encoderMode
            io.netty.handler.codec.http.multipart.HttpPostRequestEncoder$EncoderMode r14 = io.netty.handler.codec.http.multipart.HttpPostRequestEncoder.EncoderMode.HTML5
            if (r5 == r14) goto L_0x02ff
            io.netty.handler.codec.http.multipart.FileUpload r5 = r1.currentFileUpload
            if (r5 == 0) goto L_0x02ff
            java.lang.String r5 = r5.getName()
            java.lang.String r14 = r2.getName()
            boolean r5 = r5.equals(r14)
            if (r5 == 0) goto L_0x02ff
            r16.initMixedMultipart()
            java.util.List<io.netty.handler.codec.http.multipart.InterfaceHttpData> r5 = r1.multipartHttpDatas
            int r7 = r5.size()
            r14 = 2
            int r7 = r7 - r14
            java.lang.Object r5 = r5.get(r7)
            io.netty.handler.codec.http.multipart.InternalAttribute r5 = (io.netty.handler.codec.http.multipart.InternalAttribute) r5
            long r3 = r1.globalBodySize
            int r7 = r5.size()
            long r14 = (long) r7
            long r3 = r3 - r14
            r1.globalBodySize = r3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = r1.multipartDataBoundary
            int r4 = r4.length()
            int r4 = r4 + 139
            java.lang.String r7 = r1.multipartMixedBoundary
            int r7 = r7.length()
            r14 = 2
            int r7 = r7 * 2
            int r4 = r4 + r7
            java.lang.String r7 = r2.getFilename()
            int r7 = r7.length()
            int r4 = r4 + r7
            java.lang.String r7 = r2.getName()
            int r7 = r7.length()
            int r4 = r4 + r7
            r3.<init>(r4)
            r3.append(r9)
            java.lang.String r4 = r1.multipartDataBoundary
            r3.append(r4)
            r3.append(r12)
            io.netty.util.AsciiString r4 = io.netty.handler.codec.http.HttpHeaderNames.CONTENT_DISPOSITION
            r3.append(r4)
            r3.append(r11)
            io.netty.util.AsciiString r4 = io.netty.handler.codec.http.HttpHeaderValues.FORM_DATA
            r3.append(r4)
            r3.append(r10)
            io.netty.util.AsciiString r4 = io.netty.handler.codec.http.HttpHeaderValues.NAME
            r3.append(r4)
            r3.append(r8)
            java.lang.String r4 = r2.getName()
            r3.append(r4)
            r3.append(r6)
            io.netty.util.AsciiString r4 = io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE
            r3.append(r4)
            r3.append(r11)
            io.netty.util.AsciiString r4 = io.netty.handler.codec.http.HttpHeaderValues.MULTIPART_MIXED
            r3.append(r4)
            r3.append(r10)
            io.netty.util.AsciiString r4 = io.netty.handler.codec.http.HttpHeaderValues.BOUNDARY
            r3.append(r4)
            r4 = 61
            r3.append(r4)
            java.lang.String r4 = r1.multipartMixedBoundary
            r3.append(r4)
            java.lang.String r4 = "\r\n\r\n--"
            r3.append(r4)
            java.lang.String r4 = r1.multipartMixedBoundary
            r3.append(r4)
            r3.append(r12)
            io.netty.util.AsciiString r4 = io.netty.handler.codec.http.HttpHeaderNames.CONTENT_DISPOSITION
            r3.append(r4)
            r3.append(r11)
            io.netty.util.AsciiString r4 = io.netty.handler.codec.http.HttpHeaderValues.ATTACHMENT
            r3.append(r4)
            java.lang.String r4 = r2.getFilename()
            boolean r4 = r4.isEmpty()
            if (r4 != 0) goto L_0x02dd
            r3.append(r10)
            io.netty.util.AsciiString r4 = io.netty.handler.codec.http.HttpHeaderValues.FILENAME
            r3.append(r4)
            r3.append(r8)
            io.netty.handler.codec.http.multipart.FileUpload r4 = r1.currentFileUpload
            java.lang.String r4 = r4.getFilename()
            r3.append(r4)
            r4 = 34
            r3.append(r4)
        L_0x02dd:
            r3.append(r12)
            java.lang.String r3 = r3.toString()
            r4 = 1
            r5.setValue(r3, r4)
            java.lang.String r3 = ""
            r7 = 2
            r5.setValue(r3, r7)
            r3 = r13
            long r13 = r1.globalBodySize
            int r5 = r5.size()
            long r4 = (long) r5
            long r13 = r13 + r4
            r1.globalBodySize = r13
            r4 = 1
            r1.duringMixedMode = r4
            r13 = r3
            r3 = 1
            goto L_0x0306
        L_0x02ff:
            r3 = r13
            r1.currentFileUpload = r2
            r1.duringMixedMode = r7
            r13 = r3
        L_0x0305:
            r3 = 0
        L_0x0306:
            if (r3 == 0) goto L_0x0374
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r9)
            java.lang.String r4 = r1.multipartMixedBoundary
            r3.append(r4)
            r3.append(r12)
            java.lang.String r3 = r3.toString()
            r13.addValue(r3)
            java.lang.String r3 = r2.getFilename()
            boolean r3 = r3.isEmpty()
            if (r3 == 0) goto L_0x0344
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            io.netty.util.AsciiString r4 = io.netty.handler.codec.http.HttpHeaderNames.CONTENT_DISPOSITION
            r3.append(r4)
            r3.append(r11)
            io.netty.util.AsciiString r4 = io.netty.handler.codec.http.HttpHeaderValues.ATTACHMENT
            r3.append(r4)
            r3.append(r12)
            java.lang.String r3 = r3.toString()
            r13.addValue(r3)
            goto L_0x0403
        L_0x0344:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            io.netty.util.AsciiString r4 = io.netty.handler.codec.http.HttpHeaderNames.CONTENT_DISPOSITION
            r3.append(r4)
            r3.append(r11)
            io.netty.util.AsciiString r4 = io.netty.handler.codec.http.HttpHeaderValues.ATTACHMENT
            r3.append(r4)
            r3.append(r10)
            io.netty.util.AsciiString r4 = io.netty.handler.codec.http.HttpHeaderValues.FILENAME
            r3.append(r4)
            r3.append(r8)
            java.lang.String r4 = r2.getFilename()
            r3.append(r4)
            r3.append(r6)
            java.lang.String r3 = r3.toString()
            r13.addValue(r3)
            goto L_0x0403
        L_0x0374:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r9)
            java.lang.String r4 = r1.multipartDataBoundary
            r3.append(r4)
            r3.append(r12)
            java.lang.String r3 = r3.toString()
            r13.addValue(r3)
            java.lang.String r3 = r2.getFilename()
            boolean r3 = r3.isEmpty()
            if (r3 == 0) goto L_0x03c1
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            io.netty.util.AsciiString r4 = io.netty.handler.codec.http.HttpHeaderNames.CONTENT_DISPOSITION
            r3.append(r4)
            r3.append(r11)
            io.netty.util.AsciiString r4 = io.netty.handler.codec.http.HttpHeaderValues.FORM_DATA
            r3.append(r4)
            r3.append(r10)
            io.netty.util.AsciiString r4 = io.netty.handler.codec.http.HttpHeaderValues.NAME
            r3.append(r4)
            r3.append(r8)
            java.lang.String r4 = r2.getName()
            r3.append(r4)
            r3.append(r6)
            java.lang.String r3 = r3.toString()
            r13.addValue(r3)
            goto L_0x0403
        L_0x03c1:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            io.netty.util.AsciiString r4 = io.netty.handler.codec.http.HttpHeaderNames.CONTENT_DISPOSITION
            r3.append(r4)
            r3.append(r11)
            io.netty.util.AsciiString r4 = io.netty.handler.codec.http.HttpHeaderValues.FORM_DATA
            r3.append(r4)
            r3.append(r10)
            io.netty.util.AsciiString r4 = io.netty.handler.codec.http.HttpHeaderValues.NAME
            r3.append(r4)
            r3.append(r8)
            java.lang.String r4 = r2.getName()
            r3.append(r4)
            java.lang.String r4 = "\"; "
            r3.append(r4)
            io.netty.util.AsciiString r4 = io.netty.handler.codec.http.HttpHeaderValues.FILENAME
            r3.append(r4)
            r3.append(r8)
            java.lang.String r4 = r2.getFilename()
            r3.append(r4)
            r3.append(r6)
            java.lang.String r3 = r3.toString()
            r13.addValue(r3)
        L_0x0403:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            io.netty.util.AsciiString r4 = io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH
            r3.append(r4)
            r3.append(r11)
            long r4 = r2.length()
            r3.append(r4)
            r3.append(r12)
            java.lang.String r3 = r3.toString()
            r13.addValue(r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            io.netty.util.AsciiString r4 = io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE
            r3.append(r4)
            r3.append(r11)
            java.lang.String r4 = r2.getContentType()
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r13.addValue(r3)
            java.lang.String r3 = r2.getContentTransferEncoding()
            java.lang.String r4 = "\r\n\r\n"
            if (r3 == 0) goto L_0x0471
            io.netty.handler.codec.http.multipart.HttpPostBodyUtil$TransferEncodingMechanism r5 = io.netty.handler.codec.http.multipart.HttpPostBodyUtil.TransferEncodingMechanism.BINARY
            java.lang.String r5 = r5.value()
            boolean r3 = r3.equals(r5)
            if (r3 == 0) goto L_0x0471
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r12)
            io.netty.util.AsciiString r5 = io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TRANSFER_ENCODING
            r3.append(r5)
            r3.append(r11)
            io.netty.handler.codec.http.multipart.HttpPostBodyUtil$TransferEncodingMechanism r5 = io.netty.handler.codec.http.multipart.HttpPostBodyUtil.TransferEncodingMechanism.BINARY
            java.lang.String r5 = r5.value()
            r3.append(r5)
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r13.addValue(r3)
            goto L_0x049f
        L_0x0471:
            java.nio.charset.Charset r3 = r2.getCharset()
            if (r3 == 0) goto L_0x049c
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r10)
            io.netty.util.AsciiString r5 = io.netty.handler.codec.http.HttpHeaderValues.CHARSET
            r3.append(r5)
            r5 = 61
            r3.append(r5)
            java.nio.charset.Charset r5 = r2.getCharset()
            java.lang.String r5 = r5.name()
            r3.append(r5)
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r13.addValue(r3)
            goto L_0x049f
        L_0x049c:
            r13.addValue(r4)
        L_0x049f:
            java.util.List<io.netty.handler.codec.http.multipart.InterfaceHttpData> r3 = r1.multipartHttpDatas
            r3.add(r13)
            java.util.List<io.netty.handler.codec.http.multipart.InterfaceHttpData> r3 = r1.multipartHttpDatas
            r3.add(r0)
            long r3 = r1.globalBodySize
            long r5 = r2.length()
            int r0 = r13.size()
            long r7 = (long) r0
            long r5 = r5 + r7
            long r3 = r3 + r5
            r1.globalBodySize = r3
        L_0x04b8:
            return
        L_0x04b9:
            io.netty.handler.codec.http.multipart.HttpPostRequestEncoder$ErrorDataEncoderException r0 = new io.netty.handler.codec.http.multipart.HttpPostRequestEncoder$ErrorDataEncoderException
            java.lang.String r2 = "Cannot add value once finalized"
            r0.<init>((java.lang.String) r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.http.multipart.HttpPostRequestEncoder.addBodyHttpData(io.netty.handler.codec.http.multipart.InterfaceHttpData):void");
    }

    public HttpRequest finalizeRequest() throws ErrorDataEncoderException {
        if (!this.headerFinalized) {
            if (this.isMultipart) {
                InternalAttribute internalAttribute = new InternalAttribute(this.charset);
                if (this.duringMixedMode) {
                    internalAttribute.addValue("\r\n--" + this.multipartMixedBoundary + "--");
                }
                internalAttribute.addValue("\r\n--" + this.multipartDataBoundary + "--\r\n");
                this.multipartHttpDatas.add(internalAttribute);
                this.multipartMixedBoundary = null;
                this.currentFileUpload = null;
                this.duringMixedMode = false;
                this.globalBodySize += (long) internalAttribute.size();
            }
            this.headerFinalized = true;
            HttpHeaders headers = this.request.headers();
            List<String> all = headers.getAll((CharSequence) HttpHeaderNames.CONTENT_TYPE);
            List<String> all2 = headers.getAll((CharSequence) HttpHeaderNames.TRANSFER_ENCODING);
            if (all != null) {
                headers.remove((CharSequence) HttpHeaderNames.CONTENT_TYPE);
                for (String next : all) {
                    String lowerCase = next.toLowerCase();
                    if (!lowerCase.startsWith(HttpHeaderValues.MULTIPART_FORM_DATA.toString()) && !lowerCase.startsWith(HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())) {
                        headers.add((CharSequence) HttpHeaderNames.CONTENT_TYPE, (Object) next);
                    }
                }
            }
            if (this.isMultipart) {
                headers.add((CharSequence) HttpHeaderNames.CONTENT_TYPE, (Object) HttpHeaderValues.MULTIPART_FORM_DATA + "; " + HttpHeaderValues.BOUNDARY + '=' + this.multipartDataBoundary);
            } else {
                headers.add((CharSequence) HttpHeaderNames.CONTENT_TYPE, (Object) HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED);
            }
            long j = this.globalBodySize;
            if (!this.isMultipart) {
                j--;
            }
            this.iterator = this.multipartHttpDatas.listIterator();
            headers.set((CharSequence) HttpHeaderNames.CONTENT_LENGTH, (Object) String.valueOf(j));
            if (j > 8096 || this.isMultipart) {
                this.isChunked = true;
                if (all2 != null) {
                    headers.remove((CharSequence) HttpHeaderNames.TRANSFER_ENCODING);
                    for (CharSequence next2 : all2) {
                        if (!HttpHeaderValues.CHUNKED.contentEqualsIgnoreCase(next2)) {
                            headers.add((CharSequence) HttpHeaderNames.TRANSFER_ENCODING, (Object) next2);
                        }
                    }
                }
                HttpUtil.setTransferEncodingChunked(this.request, true);
                return new WrappedHttpRequest(this.request);
            }
            HttpContent nextChunk = nextChunk();
            HttpRequest httpRequest = this.request;
            if (!(httpRequest instanceof FullHttpRequest)) {
                return new WrappedFullHttpRequest(httpRequest, nextChunk);
            }
            FullHttpRequest fullHttpRequest = (FullHttpRequest) httpRequest;
            ByteBuf content = nextChunk.content();
            if (fullHttpRequest.content() != content) {
                fullHttpRequest.content().clear().writeBytes(content);
                content.release();
            }
            return fullHttpRequest;
        }
        throw new ErrorDataEncoderException("Header already encoded");
    }

    public boolean isChunked() {
        return this.isChunked;
    }

    private String encodeAttribute(String str, Charset charset2) throws ErrorDataEncoderException {
        if (str == null) {
            return "";
        }
        try {
            String encode = URLEncoder.encode(str, charset2.name());
            if (this.encoderMode == EncoderMode.RFC3986) {
                for (Map.Entry entry : percentEncodings) {
                    encode = ((Pattern) entry.getKey()).matcher(encode).replaceAll((String) entry.getValue());
                }
            }
            return encode;
        } catch (UnsupportedEncodingException e) {
            throw new ErrorDataEncoderException(charset2.name(), e);
        }
    }

    private ByteBuf fillByteBuf() {
        if (this.currentBuffer.readableBytes() > 8096) {
            return this.currentBuffer.readRetainedSlice(HttpPostBodyUtil.chunkSize);
        }
        ByteBuf byteBuf = this.currentBuffer;
        this.currentBuffer = null;
        return byteBuf;
    }

    private HttpContent encodeNextChunkMultipart(int i) throws ErrorDataEncoderException {
        ByteBuf byteBuf;
        InterfaceHttpData interfaceHttpData = this.currentData;
        if (interfaceHttpData == null) {
            return null;
        }
        if (interfaceHttpData instanceof InternalAttribute) {
            byteBuf = ((InternalAttribute) interfaceHttpData).toByteBuf();
            this.currentData = null;
        } else {
            try {
                byteBuf = ((HttpData) interfaceHttpData).getChunk(i);
                if (byteBuf.capacity() == 0) {
                    this.currentData = null;
                    return null;
                }
            } catch (IOException e) {
                throw new ErrorDataEncoderException((Throwable) e);
            }
        }
        ByteBuf byteBuf2 = this.currentBuffer;
        if (byteBuf2 == null) {
            this.currentBuffer = byteBuf;
        } else {
            this.currentBuffer = Unpooled.wrappedBuffer(byteBuf2, byteBuf);
        }
        if (this.currentBuffer.readableBytes() >= 8096) {
            return new DefaultHttpContent(fillByteBuf());
        }
        this.currentData = null;
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0099  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00c6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private io.netty.handler.codec.http.HttpContent encodeNextChunkUrlEncoded(int r11) throws io.netty.handler.codec.http.multipart.HttpPostRequestEncoder.ErrorDataEncoderException {
        /*
            r10 = this;
            io.netty.handler.codec.http.multipart.InterfaceHttpData r0 = r10.currentData
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            boolean r2 = r10.isKey
            r3 = 3
            r4 = 8096(0x1fa0, float:1.1345E-41)
            r5 = 2
            r6 = 0
            r7 = 1
            if (r2 == 0) goto L_0x006d
            java.lang.String r0 = r0.getName()
            java.nio.charset.Charset r2 = r10.charset
            byte[] r0 = r0.getBytes(r2)
            io.netty.buffer.ByteBuf r0 = io.netty.buffer.Unpooled.wrappedBuffer((byte[]) r0)
            r10.isKey = r6
            io.netty.buffer.ByteBuf r2 = r10.currentBuffer
            java.lang.String r8 = "="
            if (r2 != 0) goto L_0x003d
            io.netty.buffer.ByteBuf[] r2 = new io.netty.buffer.ByteBuf[r5]
            r2[r6] = r0
            java.nio.charset.Charset r9 = r10.charset
            byte[] r8 = r8.getBytes(r9)
            io.netty.buffer.ByteBuf r8 = io.netty.buffer.Unpooled.wrappedBuffer((byte[]) r8)
            r2[r7] = r8
            io.netty.buffer.ByteBuf r2 = io.netty.buffer.Unpooled.wrappedBuffer((io.netty.buffer.ByteBuf[]) r2)
            r10.currentBuffer = r2
            goto L_0x0055
        L_0x003d:
            io.netty.buffer.ByteBuf[] r9 = new io.netty.buffer.ByteBuf[r3]
            r9[r6] = r2
            r9[r7] = r0
            java.nio.charset.Charset r2 = r10.charset
            byte[] r2 = r8.getBytes(r2)
            io.netty.buffer.ByteBuf r2 = io.netty.buffer.Unpooled.wrappedBuffer((byte[]) r2)
            r9[r5] = r2
            io.netty.buffer.ByteBuf r2 = io.netty.buffer.Unpooled.wrappedBuffer((io.netty.buffer.ByteBuf[]) r9)
            r10.currentBuffer = r2
        L_0x0055:
            int r0 = r0.readableBytes()
            int r0 = r0 + r7
            int r11 = r11 - r0
            io.netty.buffer.ByteBuf r0 = r10.currentBuffer
            int r0 = r0.readableBytes()
            if (r0 < r4) goto L_0x006d
            io.netty.buffer.ByteBuf r11 = r10.fillByteBuf()
            io.netty.handler.codec.http.DefaultHttpContent r0 = new io.netty.handler.codec.http.DefaultHttpContent
            r0.<init>(r11)
            return r0
        L_0x006d:
            io.netty.handler.codec.http.multipart.InterfaceHttpData r0 = r10.currentData     // Catch:{ IOException -> 0x0110 }
            io.netty.handler.codec.http.multipart.HttpData r0 = (io.netty.handler.codec.http.multipart.HttpData) r0     // Catch:{ IOException -> 0x0110 }
            io.netty.buffer.ByteBuf r0 = r0.getChunk(r11)     // Catch:{ IOException -> 0x0110 }
            int r2 = r0.readableBytes()
            if (r2 >= r11) goto L_0x0092
            r10.isKey = r7
            java.util.ListIterator<io.netty.handler.codec.http.multipart.InterfaceHttpData> r11 = r10.iterator
            boolean r11 = r11.hasNext()
            if (r11 == 0) goto L_0x0092
            java.lang.String r11 = "&"
            java.nio.charset.Charset r2 = r10.charset
            byte[] r11 = r11.getBytes(r2)
            io.netty.buffer.ByteBuf r11 = io.netty.buffer.Unpooled.wrappedBuffer((byte[]) r11)
            goto L_0x0093
        L_0x0092:
            r11 = r1
        L_0x0093:
            int r2 = r0.capacity()
            if (r2 != 0) goto L_0x00c6
            r10.currentData = r1
            io.netty.buffer.ByteBuf r0 = r10.currentBuffer
            if (r0 != 0) goto L_0x00a5
            if (r11 != 0) goto L_0x00a2
            return r1
        L_0x00a2:
            r10.currentBuffer = r11
            goto L_0x00b3
        L_0x00a5:
            if (r11 == 0) goto L_0x00b3
            io.netty.buffer.ByteBuf[] r2 = new io.netty.buffer.ByteBuf[r5]
            r2[r6] = r0
            r2[r7] = r11
            io.netty.buffer.ByteBuf r11 = io.netty.buffer.Unpooled.wrappedBuffer((io.netty.buffer.ByteBuf[]) r2)
            r10.currentBuffer = r11
        L_0x00b3:
            io.netty.buffer.ByteBuf r11 = r10.currentBuffer
            int r11 = r11.readableBytes()
            if (r11 < r4) goto L_0x00c5
            io.netty.buffer.ByteBuf r11 = r10.fillByteBuf()
            io.netty.handler.codec.http.DefaultHttpContent r0 = new io.netty.handler.codec.http.DefaultHttpContent
            r0.<init>(r11)
            return r0
        L_0x00c5:
            return r1
        L_0x00c6:
            io.netty.buffer.ByteBuf r2 = r10.currentBuffer
            if (r2 != 0) goto L_0x00dc
            if (r11 == 0) goto L_0x00d9
            io.netty.buffer.ByteBuf[] r2 = new io.netty.buffer.ByteBuf[r5]
            r2[r6] = r0
            r2[r7] = r11
            io.netty.buffer.ByteBuf r11 = io.netty.buffer.Unpooled.wrappedBuffer((io.netty.buffer.ByteBuf[]) r2)
            r10.currentBuffer = r11
            goto L_0x00f9
        L_0x00d9:
            r10.currentBuffer = r0
            goto L_0x00f9
        L_0x00dc:
            if (r11 == 0) goto L_0x00ed
            io.netty.buffer.ByteBuf[] r3 = new io.netty.buffer.ByteBuf[r3]
            r3[r6] = r2
            r3[r7] = r0
            r3[r5] = r11
            io.netty.buffer.ByteBuf r11 = io.netty.buffer.Unpooled.wrappedBuffer((io.netty.buffer.ByteBuf[]) r3)
            r10.currentBuffer = r11
            goto L_0x00f9
        L_0x00ed:
            io.netty.buffer.ByteBuf[] r11 = new io.netty.buffer.ByteBuf[r5]
            r11[r6] = r2
            r11[r7] = r0
            io.netty.buffer.ByteBuf r11 = io.netty.buffer.Unpooled.wrappedBuffer((io.netty.buffer.ByteBuf[]) r11)
            r10.currentBuffer = r11
        L_0x00f9:
            io.netty.buffer.ByteBuf r11 = r10.currentBuffer
            int r11 = r11.readableBytes()
            if (r11 >= r4) goto L_0x0106
            r10.currentData = r1
            r10.isKey = r7
            return r1
        L_0x0106:
            io.netty.buffer.ByteBuf r11 = r10.fillByteBuf()
            io.netty.handler.codec.http.DefaultHttpContent r0 = new io.netty.handler.codec.http.DefaultHttpContent
            r0.<init>(r11)
            return r0
        L_0x0110:
            r11 = move-exception
            io.netty.handler.codec.http.multipart.HttpPostRequestEncoder$ErrorDataEncoderException r0 = new io.netty.handler.codec.http.multipart.HttpPostRequestEncoder$ErrorDataEncoderException
            r0.<init>((java.lang.Throwable) r11)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.http.multipart.HttpPostRequestEncoder.encodeNextChunkUrlEncoded(int):io.netty.handler.codec.http.HttpContent");
    }

    @Deprecated
    public HttpContent readChunk(ChannelHandlerContext channelHandlerContext) throws Exception {
        return readChunk(channelHandlerContext.alloc());
    }

    public HttpContent readChunk(ByteBufAllocator byteBufAllocator) throws Exception {
        if (this.isLastChunkSent) {
            return null;
        }
        HttpContent nextChunk = nextChunk();
        this.globalProgress += (long) nextChunk.content().readableBytes();
        return nextChunk;
    }

    private HttpContent nextChunk() throws ErrorDataEncoderException {
        HttpContent httpContent;
        HttpContent httpContent2;
        if (this.isLastChunk) {
            this.isLastChunkSent = true;
            return LastHttpContent.EMPTY_LAST_CONTENT;
        }
        int calculateRemainingSize = calculateRemainingSize();
        if (calculateRemainingSize <= 0) {
            return new DefaultHttpContent(fillByteBuf());
        }
        if (this.currentData != null) {
            if (this.isMultipart) {
                httpContent2 = encodeNextChunkMultipart(calculateRemainingSize);
            } else {
                httpContent2 = encodeNextChunkUrlEncoded(calculateRemainingSize);
            }
            if (httpContent2 != null) {
                return httpContent2;
            }
            calculateRemainingSize = calculateRemainingSize();
        }
        if (!this.iterator.hasNext()) {
            return lastChunk();
        }
        while (calculateRemainingSize > 0 && this.iterator.hasNext()) {
            this.currentData = this.iterator.next();
            if (this.isMultipart) {
                httpContent = encodeNextChunkMultipart(calculateRemainingSize);
            } else {
                httpContent = encodeNextChunkUrlEncoded(calculateRemainingSize);
            }
            if (httpContent != null) {
                return httpContent;
            }
            calculateRemainingSize = calculateRemainingSize();
        }
        return lastChunk();
    }

    private int calculateRemainingSize() {
        ByteBuf byteBuf = this.currentBuffer;
        if (byteBuf != null) {
            return HttpPostBodyUtil.chunkSize - byteBuf.readableBytes();
        }
        return HttpPostBodyUtil.chunkSize;
    }

    private HttpContent lastChunk() {
        this.isLastChunk = true;
        ByteBuf byteBuf = this.currentBuffer;
        if (byteBuf == null) {
            this.isLastChunkSent = true;
            return LastHttpContent.EMPTY_LAST_CONTENT;
        }
        this.currentBuffer = null;
        return new DefaultHttpContent(byteBuf);
    }

    public boolean isEndOfInput() throws Exception {
        return this.isLastChunkSent;
    }

    public long length() {
        return this.isMultipart ? this.globalBodySize : this.globalBodySize - 1;
    }

    public long progress() {
        return this.globalProgress;
    }

    public static class ErrorDataEncoderException extends Exception {
        private static final long serialVersionUID = 5020247425493164465L;

        public ErrorDataEncoderException() {
        }

        public ErrorDataEncoderException(String str) {
            super(str);
        }

        public ErrorDataEncoderException(Throwable th) {
            super(th);
        }

        public ErrorDataEncoderException(String str, Throwable th) {
            super(str, th);
        }
    }

    private static class WrappedHttpRequest implements HttpRequest {
        private final HttpRequest request;

        WrappedHttpRequest(HttpRequest httpRequest) {
            this.request = httpRequest;
        }

        public HttpRequest setProtocolVersion(HttpVersion httpVersion) {
            this.request.setProtocolVersion(httpVersion);
            return this;
        }

        public HttpRequest setMethod(HttpMethod httpMethod) {
            this.request.setMethod(httpMethod);
            return this;
        }

        public HttpRequest setUri(String str) {
            this.request.setUri(str);
            return this;
        }

        public HttpMethod getMethod() {
            return this.request.method();
        }

        public HttpMethod method() {
            return this.request.method();
        }

        public String getUri() {
            return this.request.uri();
        }

        public String uri() {
            return this.request.uri();
        }

        public HttpVersion getProtocolVersion() {
            return this.request.protocolVersion();
        }

        public HttpVersion protocolVersion() {
            return this.request.protocolVersion();
        }

        public HttpHeaders headers() {
            return this.request.headers();
        }

        public DecoderResult decoderResult() {
            return this.request.decoderResult();
        }

        @Deprecated
        public DecoderResult getDecoderResult() {
            return this.request.getDecoderResult();
        }

        public void setDecoderResult(DecoderResult decoderResult) {
            this.request.setDecoderResult(decoderResult);
        }
    }

    private static final class WrappedFullHttpRequest extends WrappedHttpRequest implements FullHttpRequest {
        private final HttpContent content;

        private WrappedFullHttpRequest(HttpRequest httpRequest, HttpContent httpContent) {
            super(httpRequest);
            this.content = httpContent;
        }

        public FullHttpRequest setProtocolVersion(HttpVersion httpVersion) {
            super.setProtocolVersion(httpVersion);
            return this;
        }

        public FullHttpRequest setMethod(HttpMethod httpMethod) {
            super.setMethod(httpMethod);
            return this;
        }

        public FullHttpRequest setUri(String str) {
            super.setUri(str);
            return this;
        }

        public FullHttpRequest copy() {
            return replace(content().copy());
        }

        public FullHttpRequest duplicate() {
            return replace(content().duplicate());
        }

        public FullHttpRequest retainedDuplicate() {
            return replace(content().retainedDuplicate());
        }

        public FullHttpRequest replace(ByteBuf byteBuf) {
            DefaultFullHttpRequest defaultFullHttpRequest = new DefaultFullHttpRequest(protocolVersion(), method(), uri(), byteBuf);
            defaultFullHttpRequest.headers().set(headers());
            defaultFullHttpRequest.trailingHeaders().set(trailingHeaders());
            return defaultFullHttpRequest;
        }

        public FullHttpRequest retain(int i) {
            this.content.retain(i);
            return this;
        }

        public FullHttpRequest retain() {
            this.content.retain();
            return this;
        }

        public FullHttpRequest touch() {
            this.content.touch();
            return this;
        }

        public FullHttpRequest touch(Object obj) {
            this.content.touch(obj);
            return this;
        }

        public ByteBuf content() {
            return this.content.content();
        }

        public HttpHeaders trailingHeaders() {
            HttpContent httpContent = this.content;
            if (httpContent instanceof LastHttpContent) {
                return ((LastHttpContent) httpContent).trailingHeaders();
            }
            return EmptyHttpHeaders.INSTANCE;
        }

        public int refCnt() {
            return this.content.refCnt();
        }

        public boolean release() {
            return this.content.release();
        }

        public boolean release(int i) {
            return this.content.release(i);
        }
    }
}
