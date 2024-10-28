package io.ktor.server.routing;

import io.ktor.http.ContentDisposition;
import io.ktor.http.ContentType;
import io.ktor.http.HttpMethod;
import io.ktor.server.application.ApplicationCall;
import io.ktor.util.KtorDsl;
import io.ktor.util.pipeline.PipelineContext;
import java.util.Arrays;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.bouncycastle.cms.CMSAttributeTableGenerator;
import org.videolan.vlc.ArtworkProvider;

@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000f\u001a-\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007H\u0007\u001a>\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0012\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\t\"\u00020\u00032\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007H\u0007¢\u0006\u0002\u0010\n\u001a-\u0010\u0002\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007H\u0007\u001a\u0012\u0010\u000b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\f\u001a\u00020\r\u001aO\u0010\u000e\u001a\u00020\u0001*\u00020\u000129\u0010\u000f\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0010¢\u0006\u0002\b\u0007H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001aW\u0010\u000e\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\f\u001a\u00020\r29\u0010\u000f\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0010¢\u0006\u0002\b\u0007H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0016\u001aO\u0010\u0017\u001a\u00020\u0001*\u00020\u000129\u0010\u000f\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0010¢\u0006\u0002\b\u0007H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001aW\u0010\u0017\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\f\u001a\u00020\r29\u0010\u000f\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0010¢\u0006\u0002\b\u0007H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0016\u001aO\u0010\u0018\u001a\u00020\u0001*\u00020\u000129\u0010\u000f\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0010¢\u0006\u0002\b\u0007H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001aW\u0010\u0018\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\f\u001a\u00020\r29\u0010\u000f\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0010¢\u0006\u0002\b\u0007H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0016\u001a5\u0010\u0019\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\r2\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007H\u0007\u001a-\u0010\u001c\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u001d2\u0017\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007H\u0007\u001a-\u0010\u001e\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u001a\u001a\u00020\r2\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007H\u0007\u001aO\u0010\u001f\u001a\u00020\u0001*\u00020\u000129\u0010\u000f\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0010¢\u0006\u0002\b\u0007H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001aW\u0010\u001f\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\f\u001a\u00020\r29\u0010\u000f\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0010¢\u0006\u0002\b\u0007H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0016\u001a-\u0010 \u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u001a\u001a\u00020\r2\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007H\u0007\u001a5\u0010 \u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\r2\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007H\u0007\u001aO\u0010!\u001a\u00020\u0001*\u00020\u000129\u0010\u000f\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0010¢\u0006\u0002\b\u0007H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001aW\u0010!\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\f\u001a\u00020\r29\u0010\u000f\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0010¢\u0006\u0002\b\u0007H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0016\u001ah\u0010!\u001a\u00020\u0001\"\n\b\u0000\u0010\"\u0018\u0001*\u00020\u0014*\u00020\u00012\u0006\u0010\f\u001a\u00020\r2;\b\u0004\u0010\u000f\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u0002H\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0010¢\u0006\u0002\b\u0007H\bø\u0001\u0000¢\u0006\u0004\b#\u0010\u0016\u001a`\u0010!\u001a\u00020\u0001\"\n\b\u0000\u0010\"\u0018\u0001*\u00020\u0014*\u00020\u00012;\b\u0004\u0010\u000f\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u0002H\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0010¢\u0006\u0002\b\u0007H\bø\u0001\u0000¢\u0006\u0004\b$\u0010\u0015\u001aO\u0010%\u001a\u00020\u0001*\u00020\u000129\u0010\u000f\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0010¢\u0006\u0002\b\u0007H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001aW\u0010%\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\f\u001a\u00020\r29\u0010\u000f\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0010¢\u0006\u0002\b\u0007H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0016\u001ah\u0010%\u001a\u00020\u0001\"\n\b\u0000\u0010\"\u0018\u0001*\u00020\u0014*\u00020\u00012\u0006\u0010\f\u001a\u00020\r2;\b\u0004\u0010\u000f\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u0002H\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0010¢\u0006\u0002\b\u0007H\bø\u0001\u0000¢\u0006\u0004\b&\u0010\u0016\u001a`\u0010%\u001a\u00020\u0001\"\n\b\u0000\u0010\"\u0018\u0001*\u00020\u0014*\u00020\u00012;\b\u0004\u0010\u000f\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u0002H\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0010¢\u0006\u0002\b\u0007H\bø\u0001\u0000¢\u0006\u0004\b'\u0010\u0015\u001aO\u0010(\u001a\u00020\u0001*\u00020\u000129\u0010\u000f\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0010¢\u0006\u0002\b\u0007H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001aW\u0010(\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\f\u001a\u00020\r29\u0010\u000f\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0010¢\u0006\u0002\b\u0007H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0016\u001ah\u0010(\u001a\u00020\u0001\"\n\b\u0000\u0010\"\u0018\u0001*\u00020\u0014*\u00020\u00012\u0006\u0010\f\u001a\u00020\r2;\b\u0004\u0010\u000f\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u0002H\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0010¢\u0006\u0002\b\u0007H\bø\u0001\u0000¢\u0006\u0004\b)\u0010\u0016\u001a`\u0010(\u001a\u00020\u0001\"\n\b\u0000\u0010\"\u0018\u0001*\u00020\u0014*\u00020\u00012;\b\u0004\u0010\u000f\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u0002H\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0010¢\u0006\u0002\b\u0007H\bø\u0001\u0000¢\u0006\u0004\b*\u0010\u0015\u001a5\u0010+\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\u001d2\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007H\u0007\u001a-\u0010+\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007H\u0007\u0002\u0004\n\u0002\b\u0019¨\u0006,"}, d2 = {"accept", "Lio/ktor/server/routing/Route;", "contentType", "Lio/ktor/http/ContentType;", "build", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "contentTypes", "", "(Lio/ktor/server/routing/Route;[Lio/ktor/http/ContentType;Lkotlin/jvm/functions/Function1;)Lio/ktor/server/routing/Route;", "createRouteFromPath", "path", "", "delete", "body", "Lkotlin/Function3;", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/server/application/ApplicationCall;", "Lkotlin/coroutines/Continuation;", "", "(Lio/ktor/server/routing/Route;Lkotlin/jvm/functions/Function3;)Lio/ktor/server/routing/Route;", "(Lio/ktor/server/routing/Route;Ljava/lang/String;Lkotlin/jvm/functions/Function3;)Lio/ktor/server/routing/Route;", "get", "head", "header", "name", "value", "method", "Lio/ktor/http/HttpMethod;", "optionalParam", "options", "param", "patch", "R", "patchTypedPath", "patchTyped", "post", "postTypedPath", "postTyped", "put", "putTypedPath", "putTyped", "route", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: RoutingBuilder.kt */
public final class RoutingBuilderKt {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: RoutingBuilder.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                io.ktor.server.routing.RoutingPathSegmentKind[] r0 = io.ktor.server.routing.RoutingPathSegmentKind.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                io.ktor.server.routing.RoutingPathSegmentKind r1 = io.ktor.server.routing.RoutingPathSegmentKind.Parameter     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                io.ktor.server.routing.RoutingPathSegmentKind r1 = io.ktor.server.routing.RoutingPathSegmentKind.Constant     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.routing.RoutingBuilderKt.WhenMappings.<clinit>():void");
        }
    }

    @KtorDsl
    public static final Route route(Route route, String str, Function1<? super Route, Unit> function1) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        Intrinsics.checkNotNullParameter(function1, "build");
        Route createRouteFromPath = createRouteFromPath(route, str);
        function1.invoke(createRouteFromPath);
        return createRouteFromPath;
    }

    @KtorDsl
    public static final Route route(Route route, String str, HttpMethod httpMethod, Function1<? super Route, Unit> function1) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        Intrinsics.checkNotNullParameter(httpMethod, "method");
        Intrinsics.checkNotNullParameter(function1, "build");
        Route createChild = createRouteFromPath(route, str).createChild(new HttpMethodRouteSelector(httpMethod));
        function1.invoke(createChild);
        return createChild;
    }

    @KtorDsl
    public static final Route method(Route route, HttpMethod httpMethod, Function1<? super Route, Unit> function1) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(httpMethod, "method");
        Intrinsics.checkNotNullParameter(function1, "body");
        Route createChild = route.createChild(new HttpMethodRouteSelector(httpMethod));
        function1.invoke(createChild);
        return createChild;
    }

    @KtorDsl
    public static final Route param(Route route, String str, String str2, Function1<? super Route, Unit> function1) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(str2, "value");
        Intrinsics.checkNotNullParameter(function1, "build");
        Route createChild = route.createChild(new ConstantParameterRouteSelector(str, str2));
        function1.invoke(createChild);
        return createChild;
    }

    @KtorDsl
    public static final Route param(Route route, String str, Function1<? super Route, Unit> function1) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(function1, "build");
        Route createChild = route.createChild(new ParameterRouteSelector(str));
        function1.invoke(createChild);
        return createChild;
    }

    @KtorDsl
    public static final Route optionalParam(Route route, String str, Function1<? super Route, Unit> function1) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(function1, "build");
        Route createChild = route.createChild(new OptionalParameterRouteSelector(str));
        function1.invoke(createChild);
        return createChild;
    }

    @KtorDsl
    public static final Route header(Route route, String str, String str2, Function1<? super Route, Unit> function1) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(str2, "value");
        Intrinsics.checkNotNullParameter(function1, "build");
        Route createChild = route.createChild(new HttpHeaderRouteSelector(str, str2));
        function1.invoke(createChild);
        return createChild;
    }

    @KtorDsl
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Please use method with vararg parameter")
    public static final /* synthetic */ Route accept(Route route, ContentType contentType, Function1 function1) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(contentType, CMSAttributeTableGenerator.CONTENT_TYPE);
        Intrinsics.checkNotNullParameter(function1, "build");
        return accept(route, new ContentType[]{contentType}, (Function1<? super Route, Unit>) new RoutingBuilderKt$accept$1(function1));
    }

    @KtorDsl
    public static final Route accept(Route route, ContentType[] contentTypeArr, Function1<? super Route, Unit> function1) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(contentTypeArr, "contentTypes");
        Intrinsics.checkNotNullParameter(function1, "build");
        Route createChild = route.createChild(new HttpMultiAcceptRouteSelector(CollectionsKt.listOf(Arrays.copyOf(contentTypeArr, contentTypeArr.length))));
        function1.invoke(createChild);
        return createChild;
    }

    @KtorDsl
    public static final Route contentType(Route route, ContentType contentType, Function1<? super Route, Unit> function1) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(contentType, CMSAttributeTableGenerator.CONTENT_TYPE);
        Intrinsics.checkNotNullParameter(function1, "build");
        Route createChild = route.createChild(new ContentTypeHeaderRouteSelector(contentType));
        function1.invoke(createChild);
        return createChild;
    }

    @KtorDsl
    public static final Route get(Route route, String str, Function3<? super PipelineContext<Unit, ApplicationCall>, ? super Unit, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        Intrinsics.checkNotNullParameter(function3, "body");
        return route(route, str, HttpMethod.Companion.getGet(), new RoutingBuilderKt$get$1(function3));
    }

    @KtorDsl
    public static final Route get(Route route, Function3<? super PipelineContext<Unit, ApplicationCall>, ? super Unit, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(function3, "body");
        return method(route, HttpMethod.Companion.getGet(), new RoutingBuilderKt$get$2(function3));
    }

    @KtorDsl
    public static final Route post(Route route, String str, Function3<? super PipelineContext<Unit, ApplicationCall>, ? super Unit, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        Intrinsics.checkNotNullParameter(function3, "body");
        return route(route, str, HttpMethod.Companion.getPost(), new RoutingBuilderKt$post$1(function3));
    }

    @KtorDsl
    public static final /* synthetic */ <R> Route postTyped(Route route, Function3<? super PipelineContext<Unit, ApplicationCall>, ? super R, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(function3, "body");
        Intrinsics.needClassReification();
        return post(route, new RoutingBuilderKt$post$2(function3, (Continuation<? super RoutingBuilderKt$post$2>) null));
    }

    @KtorDsl
    public static final /* synthetic */ <R> Route postTypedPath(Route route, String str, Function3<? super PipelineContext<Unit, ApplicationCall>, ? super R, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        Intrinsics.checkNotNullParameter(function3, "body");
        Intrinsics.needClassReification();
        return post(route, str, new RoutingBuilderKt$post$3(function3, (Continuation<? super RoutingBuilderKt$post$3>) null));
    }

    @KtorDsl
    public static final Route post(Route route, Function3<? super PipelineContext<Unit, ApplicationCall>, ? super Unit, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(function3, "body");
        return method(route, HttpMethod.Companion.getPost(), new RoutingBuilderKt$post$4(function3));
    }

    @KtorDsl
    public static final Route head(Route route, String str, Function3<? super PipelineContext<Unit, ApplicationCall>, ? super Unit, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        Intrinsics.checkNotNullParameter(function3, "body");
        return route(route, str, HttpMethod.Companion.getHead(), new RoutingBuilderKt$head$1(function3));
    }

    @KtorDsl
    public static final Route head(Route route, Function3<? super PipelineContext<Unit, ApplicationCall>, ? super Unit, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(function3, "body");
        return method(route, HttpMethod.Companion.getHead(), new RoutingBuilderKt$head$2(function3));
    }

    @KtorDsl
    public static final Route put(Route route, String str, Function3<? super PipelineContext<Unit, ApplicationCall>, ? super Unit, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        Intrinsics.checkNotNullParameter(function3, "body");
        return route(route, str, HttpMethod.Companion.getPut(), new RoutingBuilderKt$put$1(function3));
    }

    @KtorDsl
    public static final Route put(Route route, Function3<? super PipelineContext<Unit, ApplicationCall>, ? super Unit, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(function3, "body");
        return method(route, HttpMethod.Companion.getPut(), new RoutingBuilderKt$put$2(function3));
    }

    @KtorDsl
    public static final /* synthetic */ <R> Route putTyped(Route route, Function3<? super PipelineContext<Unit, ApplicationCall>, ? super R, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(function3, "body");
        Intrinsics.needClassReification();
        return put(route, new RoutingBuilderKt$put$3(function3, (Continuation<? super RoutingBuilderKt$put$3>) null));
    }

    @KtorDsl
    public static final /* synthetic */ <R> Route putTypedPath(Route route, String str, Function3<? super PipelineContext<Unit, ApplicationCall>, ? super R, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        Intrinsics.checkNotNullParameter(function3, "body");
        Intrinsics.needClassReification();
        return put(route, str, new RoutingBuilderKt$put$4(function3, (Continuation<? super RoutingBuilderKt$put$4>) null));
    }

    @KtorDsl
    public static final Route patch(Route route, String str, Function3<? super PipelineContext<Unit, ApplicationCall>, ? super Unit, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        Intrinsics.checkNotNullParameter(function3, "body");
        return route(route, str, HttpMethod.Companion.getPatch(), new RoutingBuilderKt$patch$1(function3));
    }

    @KtorDsl
    public static final Route patch(Route route, Function3<? super PipelineContext<Unit, ApplicationCall>, ? super Unit, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(function3, "body");
        return method(route, HttpMethod.Companion.getPatch(), new RoutingBuilderKt$patch$2(function3));
    }

    @KtorDsl
    public static final /* synthetic */ <R> Route patchTyped(Route route, Function3<? super PipelineContext<Unit, ApplicationCall>, ? super R, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(function3, "body");
        Intrinsics.needClassReification();
        return patch(route, new RoutingBuilderKt$patch$3(function3, (Continuation<? super RoutingBuilderKt$patch$3>) null));
    }

    @KtorDsl
    public static final /* synthetic */ <R> Route patchTypedPath(Route route, String str, Function3<? super PipelineContext<Unit, ApplicationCall>, ? super R, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        Intrinsics.checkNotNullParameter(function3, "body");
        Intrinsics.needClassReification();
        return patch(route, str, new RoutingBuilderKt$patch$4(function3, (Continuation<? super RoutingBuilderKt$patch$4>) null));
    }

    @KtorDsl
    public static final Route delete(Route route, String str, Function3<? super PipelineContext<Unit, ApplicationCall>, ? super Unit, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        Intrinsics.checkNotNullParameter(function3, "body");
        return route(route, str, HttpMethod.Companion.getDelete(), new RoutingBuilderKt$delete$1(function3));
    }

    @KtorDsl
    public static final Route delete(Route route, Function3<? super PipelineContext<Unit, ApplicationCall>, ? super Unit, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(function3, "body");
        return method(route, HttpMethod.Companion.getDelete(), new RoutingBuilderKt$delete$2(function3));
    }

    @KtorDsl
    public static final Route options(Route route, String str, Function3<? super PipelineContext<Unit, ApplicationCall>, ? super Unit, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        Intrinsics.checkNotNullParameter(function3, "body");
        return route(route, str, HttpMethod.Companion.getOptions(), new RoutingBuilderKt$options$1(function3));
    }

    @KtorDsl
    public static final Route options(Route route, Function3<? super PipelineContext<Unit, ApplicationCall>, ? super Unit, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(function3, "body");
        return method(route, HttpMethod.Companion.getOptions(), new RoutingBuilderKt$options$2(function3));
    }

    public static final Route createRouteFromPath(Route route, String str) {
        RouteSelector routeSelector;
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        List<RoutingPathSegment> parts = RoutingPath.Companion.parse(str).getParts();
        int size = parts.size();
        for (int i = 0; i < size; i++) {
            RoutingPathSegment routingPathSegment = parts.get(i);
            String component1 = routingPathSegment.component1();
            int i2 = WhenMappings.$EnumSwitchMapping$0[routingPathSegment.component2().ordinal()];
            if (i2 == 1) {
                routeSelector = PathSegmentSelectorBuilder.INSTANCE.parseParameter(component1);
            } else if (i2 == 2) {
                routeSelector = PathSegmentSelectorBuilder.INSTANCE.parseConstant(component1);
            } else {
                throw new NoWhenBranchMatchedException();
            }
            route = route.createChild(routeSelector);
        }
        return StringsKt.endsWith$default(str, "/", false, 2, (Object) null) ? route.createChild(TrailingSlashRouteSelector.INSTANCE) : route;
    }
}
