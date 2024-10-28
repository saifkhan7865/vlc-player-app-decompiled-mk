package io.ktor.server.http.content;

import io.ktor.http.CacheControl;
import io.ktor.http.ContentType;
import io.ktor.server.application.ApplicationCall;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0006\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\u0007\b\u0000¢\u0006\u0002\u0010\u0003J \u0010\n\u001a\u00020)2\u0018\u00103\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000bJ\u001c\u0010\u0012\u001a\u00020)2\u0014\u00103\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u000bJ\u0010\u00104\u001a\u00020)2\b\u00105\u001a\u0004\u0018\u00010\u0018J\u0006\u00106\u001a\u00020)J\u001a\u0010\u001d\u001a\u00020)2\u0012\u00103\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00050\u000bJ\u001f\u0010 \u001a\u00020)2\u0012\u0010 \u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001807\"\u00020\u0018¢\u0006\u0002\u00108J8\u00109\u001a\u00020)2(\u00103\u001a$\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020'\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0(\u0012\u0006\u0012\u0004\u0018\u00010\u00020&ø\u0001\u0000¢\u0006\u0002\u0010-J\u001f\u0010:\u001a\u00020)2\u0012\u0010;\u001a\n\u0012\u0006\b\u0001\u0012\u00020007\"\u000200¢\u0006\u0002\u0010<R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR,\u0010\n\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R&\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00130\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000f\"\u0004\b\u0015\u0010\u0011R\u001a\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00130\u000bX\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR&\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00050\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u000f\"\u0004\b\u001f\u0010\u0011R \u0010 \u001a\b\u0012\u0004\u0012\u00020\u00180\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$RA\u0010%\u001a$\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020'\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0(\u0012\u0006\u0012\u0004\u0018\u00010\u00020&X\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010.\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R \u0010/\u001a\b\u0012\u0004\u0012\u0002000\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\"\"\u0004\b2\u0010$\u0002\u0004\n\u0002\b\u0019¨\u0006="}, d2 = {"Lio/ktor/server/http/content/StaticContentConfig;", "Resource", "", "()V", "autoHeadResponse", "", "getAutoHeadResponse$ktor_server_core", "()Z", "setAutoHeadResponse$ktor_server_core", "(Z)V", "cacheControl", "Lkotlin/Function1;", "", "Lio/ktor/http/CacheControl;", "getCacheControl$ktor_server_core", "()Lkotlin/jvm/functions/Function1;", "setCacheControl$ktor_server_core", "(Lkotlin/jvm/functions/Function1;)V", "contentType", "Lio/ktor/http/ContentType;", "getContentType$ktor_server_core", "setContentType$ktor_server_core", "defaultContentType", "defaultPath", "", "getDefaultPath$ktor_server_core", "()Ljava/lang/String;", "setDefaultPath$ktor_server_core", "(Ljava/lang/String;)V", "exclude", "getExclude$ktor_server_core", "setExclude$ktor_server_core", "extensions", "getExtensions$ktor_server_core", "()Ljava/util/List;", "setExtensions$ktor_server_core", "(Ljava/util/List;)V", "modifier", "Lkotlin/Function3;", "Lio/ktor/server/application/ApplicationCall;", "Lkotlin/coroutines/Continuation;", "", "getModifier$ktor_server_core", "()Lkotlin/jvm/functions/Function3;", "setModifier$ktor_server_core", "(Lkotlin/jvm/functions/Function3;)V", "Lkotlin/jvm/functions/Function3;", "preCompressedFileTypes", "Lio/ktor/server/http/content/CompressedFileType;", "getPreCompressedFileTypes$ktor_server_core", "setPreCompressedFileTypes$ktor_server_core", "block", "default", "path", "enableAutoHeadResponse", "", "([Ljava/lang/String;)V", "modify", "preCompressed", "types", "([Lio/ktor/server/http/content/CompressedFileType;)V", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: StaticContent.kt */
public final class StaticContentConfig<Resource> {
    private boolean autoHeadResponse;
    private Function1<? super Resource, ? extends List<? extends CacheControl>> cacheControl = StaticContentConfig$cacheControl$1.INSTANCE;
    private Function1<? super Resource, ContentType> contentType;
    /* access modifiers changed from: private */
    public final Function1<Resource, ContentType> defaultContentType;
    private String defaultPath;
    private Function1<? super Resource, Boolean> exclude = StaticContentConfig$exclude$1.INSTANCE;
    private List<String> extensions = CollectionsKt.emptyList();
    private Function3<? super Resource, ? super ApplicationCall, ? super Continuation<? super Unit>, ? extends Object> modifier = new StaticContentConfig$modifier$1((Continuation<? super StaticContentConfig$modifier$1>) null);
    private List<? extends CompressedFileType> preCompressedFileTypes = CollectionsKt.emptyList();

    public StaticContentConfig() {
        Function1<Resource, ContentType> function1 = StaticContentConfig$defaultContentType$1.INSTANCE;
        this.defaultContentType = function1;
        this.contentType = function1;
    }

    public final Function1<Resource, ContentType> getContentType$ktor_server_core() {
        return this.contentType;
    }

    public final void setContentType$ktor_server_core(Function1<? super Resource, ContentType> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.contentType = function1;
    }

    public final Function1<Resource, List<CacheControl>> getCacheControl$ktor_server_core() {
        return this.cacheControl;
    }

    public final void setCacheControl$ktor_server_core(Function1<? super Resource, ? extends List<? extends CacheControl>> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.cacheControl = function1;
    }

    public final Function3<Resource, ApplicationCall, Continuation<? super Unit>, Object> getModifier$ktor_server_core() {
        return this.modifier;
    }

    public final void setModifier$ktor_server_core(Function3<? super Resource, ? super ApplicationCall, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(function3, "<set-?>");
        this.modifier = function3;
    }

    public final Function1<Resource, Boolean> getExclude$ktor_server_core() {
        return this.exclude;
    }

    public final void setExclude$ktor_server_core(Function1<? super Resource, Boolean> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.exclude = function1;
    }

    public final List<String> getExtensions$ktor_server_core() {
        return this.extensions;
    }

    public final void setExtensions$ktor_server_core(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.extensions = list;
    }

    public final String getDefaultPath$ktor_server_core() {
        return this.defaultPath;
    }

    public final void setDefaultPath$ktor_server_core(String str) {
        this.defaultPath = str;
    }

    public final List<CompressedFileType> getPreCompressedFileTypes$ktor_server_core() {
        return this.preCompressedFileTypes;
    }

    public final void setPreCompressedFileTypes$ktor_server_core(List<? extends CompressedFileType> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.preCompressedFileTypes = list;
    }

    public final boolean getAutoHeadResponse$ktor_server_core() {
        return this.autoHeadResponse;
    }

    public final void setAutoHeadResponse$ktor_server_core(boolean z) {
        this.autoHeadResponse = z;
    }

    public final void preCompressed(CompressedFileType... compressedFileTypeArr) {
        Intrinsics.checkNotNullParameter(compressedFileTypeArr, "types");
        this.preCompressedFileTypes = ArraysKt.toList((T[]) compressedFileTypeArr);
    }

    public final void enableAutoHeadResponse() {
        this.autoHeadResponse = true;
    }

    /* renamed from: default  reason: not valid java name */
    public final void m1475default(String str) {
        this.defaultPath = str;
    }

    public final void contentType(Function1<? super Resource, ContentType> function1) {
        Intrinsics.checkNotNullParameter(function1, "block");
        this.contentType = new StaticContentConfig$contentType$1(function1, this);
    }

    public final void cacheControl(Function1<? super Resource, ? extends List<? extends CacheControl>> function1) {
        Intrinsics.checkNotNullParameter(function1, "block");
        this.cacheControl = function1;
    }

    public final void modify(Function3<? super Resource, ? super ApplicationCall, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(function3, "block");
        this.modifier = function3;
    }

    public final void exclude(Function1<? super Resource, Boolean> function1) {
        Intrinsics.checkNotNullParameter(function1, "block");
        this.exclude = new StaticContentConfig$exclude$2(this.exclude, function1);
    }

    public final void extensions(String... strArr) {
        Intrinsics.checkNotNullParameter(strArr, "extensions");
        this.extensions = ArraysKt.toList((T[]) strArr);
    }
}
