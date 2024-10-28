package org.videolan.vlc.webserver;

import android.content.Context;
import io.ktor.server.application.ApplicationCall;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0001H@"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/server/application/ApplicationCall;", "it"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$11", f = "RemoteAccessRouting.kt", i = {0}, l = {1497, 1508, 389}, m = "invokeSuspend", n = {"$this$post"}, s = {"L$0"})
/* compiled from: RemoteAccessRouting.kt */
final class RemoteAccessRoutingKt$setupRouting$11 extends SuspendLambda implements Function3<PipelineContext<Unit, ApplicationCall>, Unit, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $appContext;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteAccessRoutingKt$setupRouting$11(Context context, Continuation<? super RemoteAccessRoutingKt$setupRouting$11> continuation) {
        super(3, continuation);
        this.$appContext = context;
    }

    public final Object invoke(PipelineContext<Unit, ApplicationCall> pipelineContext, Unit unit, Continuation<? super Unit> continuation) {
        RemoteAccessRoutingKt$setupRouting$11 remoteAccessRoutingKt$setupRouting$11 = new RemoteAccessRoutingKt$setupRouting$11(this.$appContext, continuation);
        remoteAccessRoutingKt$setupRouting$11.L$0 = pipelineContext;
        return remoteAccessRoutingKt$setupRouting$11.invokeSuspend(Unit.INSTANCE);
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processExcHandler(RegionMaker.java:1043)
        	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:975)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x0453 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00b5  */
    public final java.lang.Object invokeSuspend(java.lang.Object r31) {
        /*
            r30 = this;
            r1 = r30
            java.lang.String r2 = "vlc options: "
            java.lang.String r0 = "Changed settings:\r\n"
            java.lang.String r3 = "PiP Allowed: "
            java.lang.String r4 = "Notifications: "
            java.lang.String r5 = "Storage ALL access: "
            java.lang.String r6 = "Can write: "
            java.lang.String r7 = "Can read: "
            java.lang.String r8 = "Device Model: "
            java.lang.String r9 = "Android version: "
            java.lang.String r10 = "vlc revision: "
            java.lang.String r11 = "libvlc revision: "
            java.lang.Object r12 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r13 = r1.label
            r14 = 1
            if (r13 == 0) goto L_0x004b
            if (r13 == r14) goto L_0x0037
            r15 = 2
            if (r13 == r15) goto L_0x0029
            r2 = 3
            if (r13 != r2) goto L_0x002f
        L_0x0029:
            kotlin.ResultKt.throwOnFailure(r31)
            r9 = r1
            goto L_0x0454
        L_0x002f:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L_0x0037:
            java.lang.Object r13 = r1.L$0
            io.ktor.util.pipeline.PipelineContext r13 = (io.ktor.util.pipeline.PipelineContext) r13
            kotlin.ResultKt.throwOnFailure(r31)     // Catch:{ Exception -> 0x0045 }
            r19 = r0
            r17 = r2
            r0 = r31
            goto L_0x007f
        L_0x0045:
            r19 = r0
            r17 = r2
            goto L_0x00ad
        L_0x004b:
            kotlin.ResultKt.throwOnFailure(r31)
            java.lang.Object r13 = r1.L$0
            io.ktor.util.pipeline.PipelineContext r13 = (io.ktor.util.pipeline.PipelineContext) r13
            java.lang.Object r15 = r13.getContext()     // Catch:{ Exception -> 0x00a8 }
            io.ktor.server.application.ApplicationCall r15 = (io.ktor.server.application.ApplicationCall) r15     // Catch:{ Exception -> 0x00a8 }
            java.lang.Class<io.ktor.http.Parameters> r17 = io.ktor.http.Parameters.class
            kotlin.reflect.KType r14 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r17)     // Catch:{ Exception -> 0x00a8 }
            r17 = r2
            java.lang.reflect.Type r2 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r14)     // Catch:{ Exception -> 0x00a5 }
            java.lang.Class<io.ktor.http.Parameters> r18 = io.ktor.http.Parameters.class
            r19 = r0
            kotlin.reflect.KClass r0 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r18)     // Catch:{ Exception -> 0x00ac }
            io.ktor.util.reflect.TypeInfo r0 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r2, r0, r14)     // Catch:{ Exception -> 0x00ac }
            r2 = r1
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2     // Catch:{ Exception -> 0x00ac }
            r1.L$0 = r13     // Catch:{ Exception -> 0x00ac }
            r14 = 1
            r1.label = r14     // Catch:{ Exception -> 0x00ac }
            java.lang.Object r0 = io.ktor.server.request.ApplicationReceiveFunctionsKt.receiveNullable(r15, r0, r2)     // Catch:{ Exception -> 0x00ac }
            if (r0 != r12) goto L_0x007f
            return r12
        L_0x007f:
            if (r0 == 0) goto L_0x0084
            io.ktor.http.Parameters r0 = (io.ktor.http.Parameters) r0     // Catch:{ Exception -> 0x00ac }
            goto L_0x00ae
        L_0x0084:
            io.ktor.server.plugins.CannotTransformContentToTypeException r0 = new io.ktor.server.plugins.CannotTransformContentToTypeException     // Catch:{ Exception -> 0x00ac }
            java.lang.Class<io.ktor.http.Parameters> r2 = io.ktor.http.Parameters.class
            kotlin.reflect.KType r2 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r2)     // Catch:{ Exception -> 0x00ac }
            java.lang.reflect.Type r14 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r2)     // Catch:{ Exception -> 0x00ac }
            java.lang.Class<io.ktor.http.Parameters> r15 = io.ktor.http.Parameters.class
            kotlin.reflect.KClass r15 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r15)     // Catch:{ Exception -> 0x00ac }
            io.ktor.util.reflect.TypeInfo r2 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r14, r15, r2)     // Catch:{ Exception -> 0x00ac }
            kotlin.reflect.KType r2 = r2.getKotlinType()     // Catch:{ Exception -> 0x00ac }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)     // Catch:{ Exception -> 0x00ac }
            r0.<init>(r2)     // Catch:{ Exception -> 0x00ac }
            throw r0     // Catch:{ Exception -> 0x00ac }
        L_0x00a5:
            r19 = r0
            goto L_0x00ac
        L_0x00a8:
            r19 = r0
            r17 = r2
        L_0x00ac:
        L_0x00ad:
            r0 = 0
        L_0x00ae:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            if (r0 == 0) goto L_0x00bf
            org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$11$logs$1$1 r14 = new org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$11$logs$1$1
            r14.<init>(r2)
            kotlin.jvm.functions.Function2 r14 = (kotlin.jvm.functions.Function2) r14
            r0.forEach(r14)
        L_0x00bf:
            java.lang.String r2 = r2.toString()
            java.lang.String r0 = "toString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r0)
            java.lang.String r0 = "yyyyMMdd_kkmmss"
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            long r14 = java.lang.System.currentTimeMillis()
            java.lang.CharSequence r0 = android.text.format.DateFormat.format(r0, r14)
            java.io.File r14 = new java.io.File
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            org.videolan.resources.AndroidDevices r18 = org.videolan.resources.AndroidDevices.INSTANCE
            r20 = r12
            java.lang.String r12 = r18.getEXTERNAL_PUBLIC_DIRECTORY()
            r15.append(r12)
            java.lang.String r12 = "/vlc_logcat_remote_access_"
            r15.append(r12)
            r15.append(r0)
            java.lang.String r0 = ".log"
            r15.append(r0)
            java.lang.String r0 = r15.toString()
            r14.<init>(r0)
            kotlin.jvm.internal.Ref$ObjectRef r12 = new kotlin.jvm.internal.Ref$ObjectRef
            r12.<init>()
            java.io.FileOutputStream r15 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException | IOException -> 0x03ea, all -> 0x03cb }
            r15.<init>(r14)     // Catch:{ FileNotFoundException | IOException -> 0x03ea, all -> 0x03cb }
            java.io.OutputStreamWriter r14 = new java.io.OutputStreamWriter     // Catch:{ FileNotFoundException | IOException -> 0x03c4, all -> 0x03bc }
            r0 = r15
            java.io.OutputStream r0 = (java.io.OutputStream) r0     // Catch:{ FileNotFoundException | IOException -> 0x03c4, all -> 0x03bc }
            r14.<init>(r0)     // Catch:{ FileNotFoundException | IOException -> 0x03c4, all -> 0x03bc }
            java.io.BufferedWriter r0 = new java.io.BufferedWriter     // Catch:{ FileNotFoundException | IOException -> 0x03b6, all -> 0x03ae }
            r31 = r15
            r15 = r14
            java.io.Writer r15 = (java.io.Writer) r15     // Catch:{ FileNotFoundException -> 0x03a8, IOException -> 0x03a4, all -> 0x03a1 }
            r0.<init>(r15)     // Catch:{ FileNotFoundException -> 0x03a8, IOException -> 0x03a4, all -> 0x03a1 }
            r12.element = r0     // Catch:{ FileNotFoundException -> 0x03a8, IOException -> 0x03a4, all -> 0x03a1 }
            android.content.Context r0 = r1.$appContext     // Catch:{ FileNotFoundException -> 0x03a8, IOException -> 0x03a4, all -> 0x03a1 }
            monitor-enter(r13)     // Catch:{ FileNotFoundException -> 0x03a8, IOException -> 0x03a4, all -> 0x03a1 }
            T r15 = r12.element     // Catch:{ all -> 0x0399 }
            java.io.BufferedWriter r15 = (java.io.BufferedWriter) r15     // Catch:{ all -> 0x0399 }
            java.lang.String r1 = "____________________________\r\n"
            r15.write(r1)     // Catch:{ all -> 0x0395 }
            T r1 = r12.element     // Catch:{ all -> 0x0395 }
            java.io.BufferedWriter r1 = (java.io.BufferedWriter) r1     // Catch:{ all -> 0x0395 }
            java.lang.String r15 = "Useful info\r\n"
            r1.write(r15)     // Catch:{ all -> 0x0395 }
            T r1 = r12.element     // Catch:{ all -> 0x0395 }
            java.io.BufferedWriter r1 = (java.io.BufferedWriter) r1     // Catch:{ all -> 0x0395 }
            java.lang.String r15 = "____________________________\r\n"
            r1.write(r15)     // Catch:{ all -> 0x0395 }
            T r1 = r12.element     // Catch:{ all -> 0x0395 }
            java.io.BufferedWriter r1 = (java.io.BufferedWriter) r1     // Catch:{ all -> 0x0395 }
            java.lang.String r15 = "App version: 3050720 / 3.6.0 Beta 2\r\n"
            r1.write(r15)     // Catch:{ all -> 0x0395 }
            T r1 = r12.element     // Catch:{ all -> 0x0395 }
            java.io.BufferedWriter r1 = (java.io.BufferedWriter) r1     // Catch:{ all -> 0x0395 }
            java.lang.String r15 = "libvlc: 3.6.0-eap12\r\n"
            r1.write(r15)     // Catch:{ all -> 0x0395 }
            T r1 = r12.element     // Catch:{ all -> 0x0395 }
            java.io.BufferedWriter r1 = (java.io.BufferedWriter) r1     // Catch:{ all -> 0x0395 }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ all -> 0x0395 }
            r15.<init>(r11)     // Catch:{ all -> 0x0395 }
            int r11 = org.videolan.vlc.R.string.build_libvlc_revision     // Catch:{ all -> 0x0395 }
            java.lang.String r11 = r0.getString(r11)     // Catch:{ all -> 0x0395 }
            r15.append(r11)     // Catch:{ all -> 0x0395 }
            java.lang.String r11 = "\r\n"
            r15.append(r11)     // Catch:{ all -> 0x0395 }
            java.lang.String r11 = r15.toString()     // Catch:{ all -> 0x0395 }
            r1.write(r11)     // Catch:{ all -> 0x0395 }
            T r1 = r12.element     // Catch:{ all -> 0x0395 }
            java.io.BufferedWriter r1 = (java.io.BufferedWriter) r1     // Catch:{ all -> 0x0395 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x0395 }
            r11.<init>(r10)     // Catch:{ all -> 0x0395 }
            int r10 = org.videolan.vlc.R.string.build_vlc_revision     // Catch:{ all -> 0x0395 }
            java.lang.String r10 = r0.getString(r10)     // Catch:{ all -> 0x0395 }
            r11.append(r10)     // Catch:{ all -> 0x0395 }
            java.lang.String r10 = "\r\n"
            r11.append(r10)     // Catch:{ all -> 0x0395 }
            java.lang.String r10 = r11.toString()     // Catch:{ all -> 0x0395 }
            r1.write(r10)     // Catch:{ all -> 0x0395 }
            T r1 = r12.element     // Catch:{ all -> 0x0395 }
            java.io.BufferedWriter r1 = (java.io.BufferedWriter) r1     // Catch:{ all -> 0x0395 }
            java.lang.String r10 = "medialibrary: 0.13.13-rc12\r\n"
            r1.write(r10)     // Catch:{ all -> 0x0395 }
            T r1 = r12.element     // Catch:{ all -> 0x0395 }
            java.io.BufferedWriter r1 = (java.io.BufferedWriter) r1     // Catch:{ all -> 0x0395 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x0395 }
            r10.<init>(r9)     // Catch:{ all -> 0x0395 }
            int r9 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0395 }
            r10.append(r9)     // Catch:{ all -> 0x0395 }
            java.lang.String r9 = "\r\n"
            r10.append(r9)     // Catch:{ all -> 0x0395 }
            java.lang.String r9 = r10.toString()     // Catch:{ all -> 0x0395 }
            r1.write(r9)     // Catch:{ all -> 0x0395 }
            T r1 = r12.element     // Catch:{ all -> 0x0395 }
            java.io.BufferedWriter r1 = (java.io.BufferedWriter) r1     // Catch:{ all -> 0x0395 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x0395 }
            r9.<init>(r8)     // Catch:{ all -> 0x0395 }
            java.lang.String r8 = android.os.Build.MANUFACTURER     // Catch:{ all -> 0x0395 }
            r9.append(r8)     // Catch:{ all -> 0x0395 }
            java.lang.String r8 = " - "
            r9.append(r8)     // Catch:{ all -> 0x0395 }
            java.lang.String r8 = android.os.Build.MODEL     // Catch:{ all -> 0x0395 }
            r9.append(r8)     // Catch:{ all -> 0x0395 }
            java.lang.String r8 = "\r\n"
            r9.append(r8)     // Catch:{ all -> 0x0395 }
            java.lang.String r8 = r9.toString()     // Catch:{ all -> 0x0395 }
            r1.write(r8)     // Catch:{ all -> 0x0395 }
            T r1 = r12.element     // Catch:{ all -> 0x0395 }
            java.io.BufferedWriter r1 = (java.io.BufferedWriter) r1     // Catch:{ all -> 0x0395 }
            java.lang.String r8 = "____________________________\r\n"
            r1.write(r8)     // Catch:{ all -> 0x0395 }
            T r1 = r12.element     // Catch:{ all -> 0x0395 }
            java.io.BufferedWriter r1 = (java.io.BufferedWriter) r1     // Catch:{ all -> 0x0395 }
            java.lang.String r8 = "Permissions\r\n"
            r1.write(r8)     // Catch:{ all -> 0x0395 }
            T r1 = r12.element     // Catch:{ all -> 0x0395 }
            java.io.BufferedWriter r1 = (java.io.BufferedWriter) r1     // Catch:{ all -> 0x0395 }
            java.lang.String r8 = "____________________________\r\n"
            r1.write(r8)     // Catch:{ all -> 0x0395 }
            T r1 = r12.element     // Catch:{ all -> 0x0395 }
            java.io.BufferedWriter r1 = (java.io.BufferedWriter) r1     // Catch:{ all -> 0x0395 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x0395 }
            r8.<init>(r7)     // Catch:{ all -> 0x0395 }
            org.videolan.vlc.util.Permissions r7 = org.videolan.vlc.util.Permissions.INSTANCE     // Catch:{ all -> 0x0395 }
            boolean r7 = r7.canReadStorage(r0)     // Catch:{ all -> 0x0395 }
            r8.append(r7)     // Catch:{ all -> 0x0395 }
            java.lang.String r7 = "\r\n"
            r8.append(r7)     // Catch:{ all -> 0x0395 }
            java.lang.String r7 = r8.toString()     // Catch:{ all -> 0x0395 }
            r1.write(r7)     // Catch:{ all -> 0x0395 }
            T r1 = r12.element     // Catch:{ all -> 0x0395 }
            java.io.BufferedWriter r1 = (java.io.BufferedWriter) r1     // Catch:{ all -> 0x0395 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x0395 }
            r7.<init>(r6)     // Catch:{ all -> 0x0395 }
            org.videolan.vlc.util.Permissions r6 = org.videolan.vlc.util.Permissions.INSTANCE     // Catch:{ all -> 0x0395 }
            boolean r6 = r6.canWriteStorage(r0)     // Catch:{ all -> 0x0395 }
            r7.append(r6)     // Catch:{ all -> 0x0395 }
            java.lang.String r6 = "\r\n"
            r7.append(r6)     // Catch:{ all -> 0x0395 }
            java.lang.String r6 = r7.toString()     // Catch:{ all -> 0x0395 }
            r1.write(r6)     // Catch:{ all -> 0x0395 }
            T r1 = r12.element     // Catch:{ all -> 0x0395 }
            java.io.BufferedWriter r1 = (java.io.BufferedWriter) r1     // Catch:{ all -> 0x0395 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0395 }
            r6.<init>(r5)     // Catch:{ all -> 0x0395 }
            org.videolan.vlc.util.Permissions r5 = org.videolan.vlc.util.Permissions.INSTANCE     // Catch:{ all -> 0x0395 }
            boolean r5 = r5.hasAllAccess(r0)     // Catch:{ all -> 0x0395 }
            r6.append(r5)     // Catch:{ all -> 0x0395 }
            java.lang.String r5 = "\r\n"
            r6.append(r5)     // Catch:{ all -> 0x0395 }
            java.lang.String r5 = r6.toString()     // Catch:{ all -> 0x0395 }
            r1.write(r5)     // Catch:{ all -> 0x0395 }
            T r1 = r12.element     // Catch:{ all -> 0x0395 }
            java.io.BufferedWriter r1 = (java.io.BufferedWriter) r1     // Catch:{ all -> 0x0395 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0395 }
            r5.<init>(r4)     // Catch:{ all -> 0x0395 }
            org.videolan.vlc.util.Permissions r4 = org.videolan.vlc.util.Permissions.INSTANCE     // Catch:{ all -> 0x0395 }
            boolean r4 = r4.canSendNotifications(r0)     // Catch:{ all -> 0x0395 }
            r5.append(r4)     // Catch:{ all -> 0x0395 }
            java.lang.String r4 = "\r\n"
            r5.append(r4)     // Catch:{ all -> 0x0395 }
            java.lang.String r4 = r5.toString()     // Catch:{ all -> 0x0395 }
            r1.write(r4)     // Catch:{ all -> 0x0395 }
            T r1 = r12.element     // Catch:{ all -> 0x0395 }
            java.io.BufferedWriter r1 = (java.io.BufferedWriter) r1     // Catch:{ all -> 0x0395 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0395 }
            r4.<init>(r3)     // Catch:{ all -> 0x0395 }
            org.videolan.vlc.util.Permissions r3 = org.videolan.vlc.util.Permissions.INSTANCE     // Catch:{ all -> 0x0395 }
            boolean r3 = r3.isPiPAllowed(r0)     // Catch:{ all -> 0x0395 }
            r4.append(r3)     // Catch:{ all -> 0x0395 }
            java.lang.String r3 = "\r\n"
            r4.append(r3)     // Catch:{ all -> 0x0395 }
            java.lang.String r3 = r4.toString()     // Catch:{ all -> 0x0395 }
            r1.write(r3)     // Catch:{ all -> 0x0395 }
            T r1 = r12.element     // Catch:{ all -> 0x0395 }
            java.io.BufferedWriter r1 = (java.io.BufferedWriter) r1     // Catch:{ all -> 0x0395 }
            java.lang.String r3 = "____________________________\r\n"
            r1.write(r3)     // Catch:{ all -> 0x0395 }
            T r1 = r12.element     // Catch:{ Exception -> 0x02a6 }
            java.io.BufferedWriter r1 = (java.io.BufferedWriter) r1     // Catch:{ Exception -> 0x02a6 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x02a6 }
            r4 = r19
            r3.<init>(r4)     // Catch:{ Exception -> 0x02a6 }
            org.videolan.vlc.gui.preferences.search.PreferenceParser r4 = org.videolan.vlc.gui.preferences.search.PreferenceParser.INSTANCE     // Catch:{ Exception -> 0x02a6 }
            java.lang.String r0 = r4.getChangedPrefsString(r0)     // Catch:{ Exception -> 0x02a6 }
            r3.append(r0)     // Catch:{ Exception -> 0x02a6 }
            java.lang.String r0 = "\r\n"
            r3.append(r0)     // Catch:{ Exception -> 0x02a6 }
            java.lang.String r0 = r3.toString()     // Catch:{ Exception -> 0x02a6 }
            r1.write(r0)     // Catch:{ Exception -> 0x02a6 }
            goto L_0x02bd
        L_0x02a6:
            r0 = move-exception
            T r1 = r12.element     // Catch:{ all -> 0x0395 }
            java.io.BufferedWriter r1 = (java.io.BufferedWriter) r1     // Catch:{ all -> 0x0395 }
            java.lang.String r3 = "Cannot retrieve changed settings\r\n"
            r1.write(r3)     // Catch:{ all -> 0x0395 }
            T r1 = r12.element     // Catch:{ all -> 0x0395 }
            java.io.BufferedWriter r1 = (java.io.BufferedWriter) r1     // Catch:{ all -> 0x0395 }
            java.lang.Throwable r0 = (java.lang.Throwable) r0     // Catch:{ all -> 0x0395 }
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)     // Catch:{ all -> 0x0395 }
            r1.write(r0)     // Catch:{ all -> 0x0395 }
        L_0x02bd:
            T r0 = r12.element     // Catch:{ all -> 0x0395 }
            java.io.BufferedWriter r0 = (java.io.BufferedWriter) r0     // Catch:{ all -> 0x0395 }
            java.lang.String r1 = "____________________________\r\n"
            r0.write(r1)     // Catch:{ all -> 0x0395 }
            T r0 = r12.element     // Catch:{ all -> 0x0395 }
            java.io.BufferedWriter r0 = (java.io.BufferedWriter) r0     // Catch:{ all -> 0x0395 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0395 }
            r3 = r17
            r1.<init>(r3)     // Catch:{ all -> 0x0395 }
            org.videolan.resources.VLCOptions r3 = org.videolan.resources.VLCOptions.INSTANCE     // Catch:{ all -> 0x0395 }
            java.util.ArrayList r3 = r3.getLibOptions()     // Catch:{ all -> 0x0395 }
            r21 = r3
            java.lang.Iterable r21 = (java.lang.Iterable) r21     // Catch:{ all -> 0x0395 }
            java.lang.String r3 = " "
            r22 = r3
            java.lang.CharSequence r22 = (java.lang.CharSequence) r22     // Catch:{ all -> 0x0395 }
            r28 = 62
            r29 = 0
            r23 = 0
            r24 = 0
            r25 = 0
            r26 = 0
            r27 = 0
            java.lang.String r3 = kotlin.collections.CollectionsKt.joinToString$default(r21, r22, r23, r24, r25, r26, r27, r28, r29)     // Catch:{ all -> 0x0395 }
            r1.append(r3)     // Catch:{ all -> 0x0395 }
            java.lang.String r3 = "\r\n"
            r1.append(r3)     // Catch:{ all -> 0x0395 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0395 }
            r0.write(r1)     // Catch:{ all -> 0x0395 }
            T r0 = r12.element     // Catch:{ all -> 0x0395 }
            java.io.BufferedWriter r0 = (java.io.BufferedWriter) r0     // Catch:{ all -> 0x0395 }
            java.lang.String r1 = "____________________________\r\n"
            r0.write(r1)     // Catch:{ all -> 0x0395 }
            r3 = r2
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3     // Catch:{ all -> 0x0395 }
            r1 = 1
            java.lang.String[] r4 = new java.lang.String[r1]     // Catch:{ all -> 0x0395 }
            java.lang.String r0 = "*"
            r1 = 0
            r4[r1] = r0     // Catch:{ all -> 0x0395 }
            r7 = 6
            r8 = 0
            r5 = 0
            r6 = 0
            java.util.List r0 = kotlin.text.StringsKt.split$default((java.lang.CharSequence) r3, (java.lang.String[]) r4, (boolean) r5, (int) r6, (int) r7, (java.lang.Object) r8)     // Catch:{ all -> 0x0395 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0395 }
        L_0x0322:
            boolean r1 = r0.hasNext()     // Catch:{ all -> 0x0395 }
            if (r1 == 0) goto L_0x033d
            java.lang.Object r1 = r0.next()     // Catch:{ all -> 0x0395 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x0395 }
            T r2 = r12.element     // Catch:{ all -> 0x0395 }
            java.io.BufferedWriter r2 = (java.io.BufferedWriter) r2     // Catch:{ all -> 0x0395 }
            r2.write(r1)     // Catch:{ all -> 0x0395 }
            T r1 = r12.element     // Catch:{ all -> 0x0395 }
            java.io.BufferedWriter r1 = (java.io.BufferedWriter) r1     // Catch:{ all -> 0x0395 }
            r1.newLine()     // Catch:{ all -> 0x0395 }
            goto L_0x0322
        L_0x033d:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0395 }
            monitor-exit(r13)     // Catch:{ FileNotFoundException -> 0x0392, IOException -> 0x038f, all -> 0x038b }
            org.videolan.tools.CloseableUtils r0 = org.videolan.tools.CloseableUtils.INSTANCE
            T r1 = r12.element
            java.io.Closeable r1 = (java.io.Closeable) r1
            boolean r0 = r0.close(r1)
            r1 = 1
            r0 = r0 & r1
            org.videolan.tools.CloseableUtils r1 = org.videolan.tools.CloseableUtils.INSTANCE
            java.io.Closeable r14 = (java.io.Closeable) r14
            boolean r1 = r1.close(r14)
            r0 = r0 & r1
            org.videolan.tools.CloseableUtils r1 = org.videolan.tools.CloseableUtils.INSTANCE
            r15 = r31
            java.io.Closeable r15 = (java.io.Closeable) r15
            boolean r1 = r1.close(r15)
            r0 = r0 & r1
            if (r0 != 0) goto L_0x0368
            r9 = r30
            r1 = r20
            goto L_0x0406
        L_0x0368:
            java.lang.Object r0 = r13.getContext()
            r1 = r0
            io.ktor.server.application.ApplicationCall r1 = (io.ktor.server.application.ApplicationCall) r1
            java.lang.String r2 = ""
            r9 = r30
            r6 = r9
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r3 = 0
            r9.L$0 = r3
            r3 = 3
            r9.label = r3
            r3 = 0
            r4 = 0
            r5 = 0
            r7 = 14
            r8 = 0
            java.lang.Object r0 = io.ktor.server.response.ApplicationResponseFunctionsKt.respondText$default(r1, r2, r3, r4, r5, r6, r7, r8)
            r1 = r20
            if (r0 != r1) goto L_0x0454
            return r1
        L_0x038b:
            r0 = move-exception
            r9 = r30
            goto L_0x03b2
        L_0x038f:
            r9 = r30
            goto L_0x03a5
        L_0x0392:
            r9 = r30
            goto L_0x03a9
        L_0x0395:
            r0 = move-exception
            r9 = r30
            goto L_0x039b
        L_0x0399:
            r0 = move-exception
            r9 = r1
        L_0x039b:
            r1 = r20
            monitor-exit(r13)     // Catch:{ FileNotFoundException | IOException -> 0x03ab, all -> 0x039f }
            throw r0     // Catch:{ FileNotFoundException | IOException -> 0x03ab, all -> 0x039f }
        L_0x039f:
            r0 = move-exception
            goto L_0x03b2
        L_0x03a1:
            r0 = move-exception
            r9 = r1
            goto L_0x03b2
        L_0x03a4:
            r9 = r1
        L_0x03a5:
            r1 = r20
            goto L_0x03ab
        L_0x03a8:
            r9 = r1
        L_0x03a9:
            r1 = r20
        L_0x03ab:
            r15 = r31
            goto L_0x03ef
        L_0x03ae:
            r0 = move-exception
            r9 = r1
            r31 = r15
        L_0x03b2:
            r16 = r31
            r15 = r14
            goto L_0x03d0
        L_0x03b6:
            r9 = r1
            r31 = r15
            r1 = r20
            goto L_0x03ef
        L_0x03bc:
            r0 = move-exception
            r9 = r1
            r31 = r15
            r16 = r31
            r15 = 0
            goto L_0x03d0
        L_0x03c4:
            r9 = r1
            r31 = r15
            r1 = r20
            r14 = 0
            goto L_0x03ef
        L_0x03cb:
            r0 = move-exception
            r9 = r1
            r15 = 0
            r16 = 0
        L_0x03d0:
            org.videolan.tools.CloseableUtils r1 = org.videolan.tools.CloseableUtils.INSTANCE
            T r2 = r12.element
            java.io.Closeable r2 = (java.io.Closeable) r2
            r1.close(r2)
            org.videolan.tools.CloseableUtils r1 = org.videolan.tools.CloseableUtils.INSTANCE
            java.io.Closeable r15 = (java.io.Closeable) r15
            r1.close(r15)
            org.videolan.tools.CloseableUtils r1 = org.videolan.tools.CloseableUtils.INSTANCE
            r2 = r16
            java.io.Closeable r2 = (java.io.Closeable) r2
            r1.close(r2)
            throw r0
        L_0x03ea:
            r9 = r1
            r1 = r20
            r14 = 0
            r15 = 0
        L_0x03ef:
            org.videolan.tools.CloseableUtils r0 = org.videolan.tools.CloseableUtils.INSTANCE
            T r2 = r12.element
            java.io.Closeable r2 = (java.io.Closeable) r2
            r0.close(r2)
            org.videolan.tools.CloseableUtils r0 = org.videolan.tools.CloseableUtils.INSTANCE
            java.io.Closeable r14 = (java.io.Closeable) r14
            r0.close(r14)
            org.videolan.tools.CloseableUtils r0 = org.videolan.tools.CloseableUtils.INSTANCE
            java.io.Closeable r15 = (java.io.Closeable) r15
            r0.close(r15)
        L_0x0406:
            java.lang.Object r0 = r13.getContext()
            io.ktor.server.application.ApplicationCall r0 = (io.ktor.server.application.ApplicationCall) r0
            io.ktor.http.HttpStatusCode$Companion r2 = io.ktor.http.HttpStatusCode.Companion
            io.ktor.http.HttpStatusCode r2 = r2.getInternalServerError()
            boolean r3 = r2 instanceof io.ktor.http.content.OutgoingContent
            if (r3 != 0) goto L_0x0435
            boolean r3 = r2 instanceof byte[]
            if (r3 != 0) goto L_0x0435
            io.ktor.server.response.ApplicationResponse r3 = r0.getResponse()
            java.lang.Class<io.ktor.http.HttpStatusCode> r4 = io.ktor.http.HttpStatusCode.class
            kotlin.reflect.KType r4 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r4)
            java.lang.reflect.Type r5 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r4)
            java.lang.Class<io.ktor.http.HttpStatusCode> r6 = io.ktor.http.HttpStatusCode.class
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            io.ktor.util.reflect.TypeInfo r4 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r5, r6, r4)
            io.ktor.server.response.ResponseTypeKt.setResponseType(r3, r4)
        L_0x0435:
            io.ktor.server.response.ApplicationResponse r3 = r0.getResponse()
            io.ktor.server.response.ApplicationSendPipeline r3 = r3.getPipeline()
            java.lang.String r4 = "null cannot be cast to non-null type kotlin.Any"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2, r4)
            java.lang.Object r2 = (java.lang.Object) r2
            r4 = r9
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r5 = 0
            r9.L$0 = r5
            r5 = 2
            r9.label = r5
            java.lang.Object r0 = r3.execute(r0, r2, r4)
            if (r0 != r1) goto L_0x0454
            return r1
        L_0x0454:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$11.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
