package org.videolan.vlc.util;

import android.app.Application;
import java.util.Date;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.util.AutoUpdate$checkUpdate$2", f = "AutoUpdate.kt", i = {0, 0, 0, 0, 0}, l = {115}, m = "invokeSuspend", n = {"abi", "webFormat", "buildDate", "m", "found"}, s = {"L$0", "L$1", "L$2", "L$5", "L$6"})
/* compiled from: AutoUpdate.kt */
final class AutoUpdate$checkUpdate$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Application $context;
    final /* synthetic */ Function2<String, Date, Unit> $listener;
    final /* synthetic */ boolean $skipChecks;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AutoUpdate$checkUpdate$2(boolean z, Application application, Function2<? super String, ? super Date, Unit> function2, Continuation<? super AutoUpdate$checkUpdate$2> continuation) {
        super(2, continuation);
        this.$skipChecks = z;
        this.$context = application;
        this.$listener = function2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AutoUpdate$checkUpdate$2(this.$skipChecks, this.$context, this.$listener, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AutoUpdate$checkUpdate$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v35, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v26, resolved type: kotlin.jvm.internal.Ref$BooleanRef} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v36, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v19, resolved type: java.util.regex.Matcher} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v37, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v15, resolved type: kotlin.jvm.functions.Function2<java.lang.String, java.util.Date, kotlin.Unit>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v38, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v23, resolved type: java.io.Closeable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v39, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v31, resolved type: java.util.Date} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v40, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v18, resolved type: java.text.SimpleDateFormat} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v41, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v13, resolved type: java.lang.String} */
    /* JADX WARNING: Can't wrap try/catch for region: R(12:51|(1:52)|53|54|55|56|(11:58|(2:60|61)(1:64)|65|(13:67|68|69|70|71|72|73|74|75|76|77|(1:79)(10:80|81|82|83|93|102|103|46|47|(0)(0))|79)|92|93|102|103|46|47|(0)(0))|94|103|46|47|(0)(0)) */
    /* JADX WARNING: Can't wrap try/catch for region: R(13:67|68|69|70|71|72|73|74|75|76|77|(1:79)(10:80|81|82|83|93|102|103|46|47|(0)(0))|79) */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0235, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0237, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x0239, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x023a, code lost:
        r12 = r26;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x023d, code lost:
        r15 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x024a, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x024b, code lost:
        r12 = r13;
        r11 = r15;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x025c A[Catch:{ all -> 0x0045 }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0194 A[Catch:{ all -> 0x0045 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r26) {
        /*
            r25 = this;
            r1 = r25
            java.lang.String r0 = "x86_64"
            java.lang.String r2 = "x86"
            java.lang.String r3 = "http://artifacts.videolan.org/vlc-android/nightly-"
            java.lang.String r4 = "Checking for update for abi! "
            java.lang.Object r5 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r6 = r1.label
            r9 = 0
            java.lang.String r10 = "AutoUpdate"
            r11 = 1
            if (r6 == 0) goto L_0x005b
            if (r6 != r11) goto L_0x0053
            java.lang.Object r0 = r1.L$6
            r2 = r0
            kotlin.jvm.internal.Ref$BooleanRef r2 = (kotlin.jvm.internal.Ref.BooleanRef) r2
            java.lang.Object r0 = r1.L$5
            r3 = r0
            java.util.regex.Matcher r3 = (java.util.regex.Matcher) r3
            java.lang.Object r0 = r1.L$4
            r4 = r0
            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
            java.lang.Object r0 = r1.L$3
            r6 = r0
            java.io.Closeable r6 = (java.io.Closeable) r6
            java.lang.Object r0 = r1.L$2
            r12 = r0
            java.util.Date r12 = (java.util.Date) r12
            java.lang.Object r0 = r1.L$1
            r13 = r0
            java.text.SimpleDateFormat r13 = (java.text.SimpleDateFormat) r13
            java.lang.Object r0 = r1.L$0
            r14 = r0
            java.lang.String r14 = (java.lang.String) r14
            kotlin.ResultKt.throwOnFailure(r26)     // Catch:{ Exception -> 0x0049 }
            r11 = r13
            r8 = 0
            r13 = 1
            r18 = 2
            goto L_0x022b
        L_0x0045:
            r0 = move-exception
            r2 = r0
            goto L_0x026c
        L_0x0049:
            r0 = move-exception
            r7 = r12
            r15 = r13
            r12 = r14
            r8 = 0
        L_0x004e:
            r13 = 1
            r18 = 2
            goto L_0x0254
        L_0x0053:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L_0x005b:
            kotlin.ResultKt.throwOnFailure(r26)
            boolean r6 = r1.$skipChecks
            if (r6 != 0) goto L_0x0065
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0065:
            org.videolan.tools.Settings r6 = org.videolan.tools.Settings.INSTANCE
            android.app.Application r12 = r1.$context
            java.lang.Object r6 = r6.getInstance(r12)
            android.content.SharedPreferences r6 = (android.content.SharedPreferences) r6
            boolean r12 = r1.$skipChecks
            java.lang.String r13 = "last_update_time"
            if (r12 != 0) goto L_0x0091
            r14 = 0
            long r14 = r6.getLong(r13, r14)
            long r16 = java.lang.System.currentTimeMillis()
            r12 = 21600000(0x1499700, float:3.7026207E-38)
            long r7 = (long) r12
            long r16 = r16 - r7
            int r7 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r7 <= 0) goto L_0x0091
            java.lang.String r0 = "Last update is less than 6 hours"
            android.util.Log.i(r10, r0)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0091:
            long r7 = java.lang.System.currentTimeMillis()
            java.lang.Long r7 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r7)
            org.videolan.tools.SettingsKt.putSingle(r6, r13, r7)
            int r6 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x027b }
            r7 = 21
            if (r6 < r7) goto L_0x00a9
            java.lang.String[] r6 = org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0.m()     // Catch:{ Exception -> 0x027b }
            r6 = r6[r9]     // Catch:{ Exception -> 0x027b }
            goto L_0x00ab
        L_0x00a9:
            java.lang.String r6 = android.os.Build.CPU_ABI     // Catch:{ Exception -> 0x027b }
        L_0x00ab:
            r7 = 4
            kotlin.Pair[] r7 = new kotlin.Pair[r7]     // Catch:{ Exception -> 0x027b }
            kotlin.Pair r8 = new kotlin.Pair     // Catch:{ Exception -> 0x027b }
            java.lang.String r12 = "armeabi-v7a"
            java.lang.String r13 = "armv7"
            r8.<init>(r12, r13)     // Catch:{ Exception -> 0x027b }
            r7[r9] = r8     // Catch:{ Exception -> 0x027b }
            kotlin.Pair r8 = new kotlin.Pair     // Catch:{ Exception -> 0x027b }
            java.lang.String r12 = "arm64-v8a"
            java.lang.String r13 = "arm64"
            r8.<init>(r12, r13)     // Catch:{ Exception -> 0x027b }
            r7[r11] = r8     // Catch:{ Exception -> 0x027b }
            kotlin.Pair r8 = new kotlin.Pair     // Catch:{ Exception -> 0x027b }
            r8.<init>(r2, r2)     // Catch:{ Exception -> 0x027b }
            r2 = 2
            r7[r2] = r8     // Catch:{ Exception -> 0x027b }
            kotlin.Pair r2 = new kotlin.Pair     // Catch:{ Exception -> 0x027b }
            r2.<init>(r0, r0)     // Catch:{ Exception -> 0x027b }
            r0 = 3
            r7[r0] = r2     // Catch:{ Exception -> 0x027b }
            java.util.Map r0 = kotlin.collections.MapsKt.mapOf(r7)     // Catch:{ Exception -> 0x027b }
            boolean r2 = r0.containsKey(r6)     // Catch:{ Exception -> 0x027b }
            if (r2 == 0) goto L_0x0273
            java.lang.Object r0 = r0.get(r6)     // Catch:{ Exception -> 0x027b }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Exception -> 0x027b }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x027b }
            r2.<init>(r4)     // Catch:{ Exception -> 0x027b }
            r2.append(r0)     // Catch:{ Exception -> 0x027b }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x027b }
            android.util.Log.i(r10, r2)     // Catch:{ Exception -> 0x027b }
            boolean r2 = r1.$skipChecks     // Catch:{ Exception -> 0x027b }
            if (r2 == 0) goto L_0x00fa
            java.lang.String r2 = "2000-01-01"
            goto L_0x0107
        L_0x00fa:
            android.app.Application r2 = r1.$context     // Catch:{ Exception -> 0x027b }
            int r4 = org.videolan.vlc.R.string.build_time     // Catch:{ Exception -> 0x027b }
            java.lang.String r2 = r2.getString(r4)     // Catch:{ Exception -> 0x027b }
            java.lang.String r4 = "getString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)     // Catch:{ Exception -> 0x027b }
        L_0x0107:
            java.text.SimpleDateFormat r4 = new java.text.SimpleDateFormat     // Catch:{ Exception -> 0x027b }
            java.lang.String r6 = "yyyy-MM-dd"
            java.util.Locale r7 = java.util.Locale.US     // Catch:{ Exception -> 0x027b }
            r4.<init>(r6, r7)     // Catch:{ Exception -> 0x027b }
            java.text.SimpleDateFormat r6 = new java.text.SimpleDateFormat     // Catch:{ Exception -> 0x027b }
            java.lang.String r7 = "yyyyMMdd"
            java.util.Locale r8 = java.util.Locale.US     // Catch:{ Exception -> 0x027b }
            r6.<init>(r7, r8)     // Catch:{ Exception -> 0x027b }
            java.util.Date r2 = r4.parse(r2)     // Catch:{ Exception -> 0x027b }
            java.net.URL r4 = new java.net.URL     // Catch:{ Exception -> 0x027b }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x027b }
            r7.<init>(r3)     // Catch:{ Exception -> 0x027b }
            r7.append(r0)     // Catch:{ Exception -> 0x027b }
            r3 = 47
            r7.append(r3)     // Catch:{ Exception -> 0x027b }
            java.lang.String r3 = r7.toString()     // Catch:{ Exception -> 0x027b }
            r4.<init>(r3)     // Catch:{ Exception -> 0x027b }
            okhttp3.OkHttpClient$Builder r3 = new okhttp3.OkHttpClient$Builder     // Catch:{ Exception -> 0x027b }
            r3.<init>()     // Catch:{ Exception -> 0x027b }
            java.util.concurrent.TimeUnit r7 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ Exception -> 0x027b }
            r12 = 10
            okhttp3.OkHttpClient$Builder r3 = r3.readTimeout(r12, r7)     // Catch:{ Exception -> 0x027b }
            java.util.concurrent.TimeUnit r7 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ Exception -> 0x027b }
            r12 = 5
            okhttp3.OkHttpClient$Builder r3 = r3.connectTimeout(r12, r7)     // Catch:{ Exception -> 0x027b }
            okhttp3.OkHttpClient r3 = r3.build()     // Catch:{ Exception -> 0x027b }
            okhttp3.Request$Builder r7 = new okhttp3.Request$Builder     // Catch:{ Exception -> 0x027b }
            r7.<init>()     // Catch:{ Exception -> 0x027b }
            okhttp3.Request$Builder r4 = r7.url((java.net.URL) r4)     // Catch:{ Exception -> 0x027b }
            okhttp3.Request r4 = r4.build()     // Catch:{ Exception -> 0x027b }
            okhttp3.Call r3 = r3.newCall(r4)     // Catch:{ Exception -> 0x027b }
            okhttp3.Response r3 = r3.execute()     // Catch:{ Exception -> 0x027b }
            java.io.Closeable r3 = (java.io.Closeable) r3     // Catch:{ Exception -> 0x027b }
            kotlin.jvm.functions.Function2<java.lang.String, java.util.Date, kotlin.Unit> r4 = r1.$listener     // Catch:{ Exception -> 0x027b }
            r7 = r3
            okhttp3.Response r7 = (okhttp3.Response) r7     // Catch:{ all -> 0x0269 }
            okhttp3.ResponseBody r7 = r7.body()     // Catch:{ all -> 0x0269 }
            if (r7 == 0) goto L_0x0262
            java.lang.String r7 = r7.string()     // Catch:{ all -> 0x0269 }
            if (r7 != 0) goto L_0x0176
            goto L_0x0262
        L_0x0176:
            java.util.regex.Pattern r8 = android.util.Patterns.WEB_URL     // Catch:{ all -> 0x0269 }
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7     // Catch:{ all -> 0x0269 }
            java.util.regex.Matcher r7 = r8.matcher(r7)     // Catch:{ all -> 0x0269 }
            kotlin.jvm.internal.Ref$BooleanRef r8 = new kotlin.jvm.internal.Ref$BooleanRef     // Catch:{ all -> 0x0269 }
            r8.<init>()     // Catch:{ all -> 0x0269 }
            r13 = r0
            r15 = r6
            r6 = r3
            r3 = r7
            r7 = r2
            r2 = r8
            r8 = 0
        L_0x018a:
            boolean r0 = r3.find()     // Catch:{ all -> 0x0045 }
            if (r0 == 0) goto L_0x025c
            boolean r0 = r2.element     // Catch:{ all -> 0x0045 }
            if (r0 != 0) goto L_0x025c
            java.lang.String r0 = r3.group()     // Catch:{ all -> 0x0045 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)     // Catch:{ all -> 0x0045 }
            r19 = r0
            java.lang.CharSequence r19 = (java.lang.CharSequence) r19     // Catch:{ all -> 0x0045 }
            char[] r12 = new char[r11]     // Catch:{ all -> 0x0045 }
            r14 = 45
            r12[r9] = r14     // Catch:{ all -> 0x0045 }
            r23 = 6
            r24 = 0
            r21 = 0
            r22 = 0
            r20 = r12
            java.util.List r12 = kotlin.text.StringsKt.split$default((java.lang.CharSequence) r19, (char[]) r20, (boolean) r21, (int) r22, (int) r23, (java.lang.Object) r24)     // Catch:{ all -> 0x0045 }
            int r14 = r12.size()     // Catch:{ Exception -> 0x024f }
            r18 = 2
            int r14 = r14 + -2
            java.lang.Object r12 = r12.get(r14)     // Catch:{ Exception -> 0x024a }
            java.lang.String r12 = (java.lang.String) r12     // Catch:{ Exception -> 0x024a }
            java.util.Date r16 = r15.parse(r12)     // Catch:{ Exception -> 0x024a }
            if (r16 == 0) goto L_0x0245
            long r19 = r16.getTime()     // Catch:{ Exception -> 0x024a }
            if (r7 == 0) goto L_0x01d6
            long r21 = r7.getTime()     // Catch:{ Exception -> 0x01d2 }
            goto L_0x01db
        L_0x01d2:
            r0 = move-exception
            r12 = r13
            goto L_0x024d
        L_0x01d6:
            r21 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
        L_0x01db:
            int r12 = (r19 > r21 ? 1 : (r19 == r21 ? 0 : -1))
            if (r12 <= 0) goto L_0x023f
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x024a }
            r12.<init>()     // Catch:{ Exception -> 0x024a }
            java.lang.String r14 = "Found update: "
            r12.append(r14)     // Catch:{ Exception -> 0x024a }
            r12.append(r0)     // Catch:{ Exception -> 0x024a }
            java.lang.String r12 = r12.toString()     // Catch:{ Exception -> 0x024a }
            android.util.Log.i(r10, r12)     // Catch:{ Exception -> 0x024a }
            kotlinx.coroutines.MainCoroutineDispatcher r12 = kotlinx.coroutines.Dispatchers.getMain()     // Catch:{ Exception -> 0x024a }
            r14 = r12
            kotlin.coroutines.CoroutineContext r14 = (kotlin.coroutines.CoroutineContext) r14     // Catch:{ Exception -> 0x024a }
            org.videolan.vlc.util.AutoUpdate$checkUpdate$2$1$1$1 r19 = new org.videolan.vlc.util.AutoUpdate$checkUpdate$2$1$1$1     // Catch:{ Exception -> 0x024a }
            r17 = 0
            r12 = r19
            r26 = r13
            r13 = r4
            r9 = r14
            r14 = r26
            r11 = r15
            r15 = r0
            r12.<init>(r13, r14, r15, r16, r17)     // Catch:{ Exception -> 0x0239 }
            r0 = r19
            kotlin.jvm.functions.Function2 r0 = (kotlin.jvm.functions.Function2) r0     // Catch:{ Exception -> 0x0239 }
            r12 = r26
            r1.L$0 = r12     // Catch:{ Exception -> 0x0237 }
            r1.L$1 = r11     // Catch:{ Exception -> 0x0237 }
            r1.L$2 = r7     // Catch:{ Exception -> 0x0237 }
            r1.L$3 = r6     // Catch:{ Exception -> 0x0237 }
            r1.L$4 = r4     // Catch:{ Exception -> 0x0237 }
            r1.L$5 = r3     // Catch:{ Exception -> 0x0237 }
            r1.L$6 = r2     // Catch:{ Exception -> 0x0237 }
            r13 = 1
            r1.label = r13     // Catch:{ Exception -> 0x0235 }
            java.lang.Object r0 = kotlinx.coroutines.BuildersKt.withContext(r9, r0, r1)     // Catch:{ Exception -> 0x0235 }
            if (r0 != r5) goto L_0x0229
            return r5
        L_0x0229:
            r14 = r12
            r12 = r7
        L_0x022b:
            r2.element = r13     // Catch:{ Exception -> 0x0230 }
            r15 = r11
            r7 = r12
            goto L_0x0243
        L_0x0230:
            r0 = move-exception
            r15 = r11
            r7 = r12
            r12 = r14
            goto L_0x0254
        L_0x0235:
            r0 = move-exception
            goto L_0x023d
        L_0x0237:
            r0 = move-exception
            goto L_0x023c
        L_0x0239:
            r0 = move-exception
            r12 = r26
        L_0x023c:
            r13 = 1
        L_0x023d:
            r15 = r11
            goto L_0x0254
        L_0x023f:
            r12 = r13
            r11 = r15
            r13 = 1
            r14 = r12
        L_0x0243:
            r13 = r14
            goto L_0x0258
        L_0x0245:
            r12 = r13
            r11 = r15
            r13 = 1
            r13 = r12
            goto L_0x0259
        L_0x024a:
            r0 = move-exception
            r12 = r13
            r11 = r15
        L_0x024d:
            r13 = 1
            goto L_0x0254
        L_0x024f:
            r0 = move-exception
            r12 = r13
            r11 = r15
            goto L_0x004e
        L_0x0254:
            r0.printStackTrace()     // Catch:{ all -> 0x0045 }
            r13 = r12
        L_0x0258:
            r9 = 0
        L_0x0259:
            r11 = 1
            goto L_0x018a
        L_0x025c:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0045 }
            kotlin.io.CloseableKt.closeFinally(r6, r8)     // Catch:{ Exception -> 0x027b }
            goto L_0x027f
        L_0x0262:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0269 }
            r2 = 0
            kotlin.io.CloseableKt.closeFinally(r3, r2)     // Catch:{ Exception -> 0x027b }
            return r0
        L_0x0269:
            r0 = move-exception
            r2 = r0
            r6 = r3
        L_0x026c:
            throw r2     // Catch:{ all -> 0x026d }
        L_0x026d:
            r0 = move-exception
            r3 = r0
            kotlin.io.CloseableKt.closeFinally(r6, r2)     // Catch:{ Exception -> 0x027b }
            throw r3     // Catch:{ Exception -> 0x027b }
        L_0x0273:
            java.lang.Exception r0 = new java.lang.Exception     // Catch:{ Exception -> 0x027b }
            java.lang.String r2 = "Unsupported architecture"
            r0.<init>(r2)     // Catch:{ Exception -> 0x027b }
            throw r0     // Catch:{ Exception -> 0x027b }
        L_0x027b:
            r0 = move-exception
            r0.printStackTrace()
        L_0x027f:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.util.AutoUpdate$checkUpdate$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
