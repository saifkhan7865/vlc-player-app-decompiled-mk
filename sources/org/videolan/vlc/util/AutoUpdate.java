package org.videolan.vlc.util;

import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.Okio;
import org.videolan.vlc.FileProviderKt;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J:\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\u0018\u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00060\fH@¢\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\bH@¢\u0006\u0002\u0010\u0010J\u0018\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0004H\u0002J2\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u00042\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00060\u0016H@¢\u0006\u0002\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lorg/videolan/vlc/util/AutoUpdate;", "", "()V", "TAG", "", "checkUpdate", "", "context", "Landroid/app/Application;", "skipChecks", "", "listener", "Lkotlin/Function2;", "Ljava/util/Date;", "(Landroid/app/Application;ZLkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clean", "(Landroid/app/Application;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "download", "url", "downloadAndInstall", "updateURL", "loading", "Lkotlin/Function1;", "(Landroid/app/Application;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "installAPK", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AutoUpdate.kt */
public final class AutoUpdate {
    public static final AutoUpdate INSTANCE = new AutoUpdate();
    private static final String TAG = "AutoUpdate";

    private AutoUpdate() {
    }

    public static /* synthetic */ Object checkUpdate$default(AutoUpdate autoUpdate, Application application, boolean z, Function2 function2, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return autoUpdate.checkUpdate(application, z, function2, continuation);
    }

    public final Object checkUpdate(Application application, boolean z, Function2<? super String, ? super Date, Unit> function2, Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new AutoUpdate$checkUpdate$2(z, application, function2, (Continuation<? super AutoUpdate$checkUpdate$2>) null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    public final Object downloadAndInstall(Application application, String str, Function1<? super Boolean, Unit> function1, Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new AutoUpdate$downloadAndInstall$2(application, str, function1, (Continuation<? super AutoUpdate$downloadAndInstall$2>) null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public final void download(Application application, String str) throws IOException {
        Response execute = new OkHttpClient.Builder().readTimeout(10, TimeUnit.SECONDS).connectTimeout(5, TimeUnit.SECONDS).build().newCall(new Request.Builder().url(str).build()).execute();
        BufferedSink buffer = Okio.buffer(Okio.sink$default(new File(application.getCacheDir(), "update.apk"), false, 1, (Object) null));
        ResponseBody body = execute.body();
        Intrinsics.checkNotNull(body);
        buffer.writeAll(body.source());
        buffer.close();
    }

    /* access modifiers changed from: private */
    public final void installAPK(Application application) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(FileProviderKt.getUpdateUri(), "application/vnd.android.package-archive");
        intent.addFlags(268435456);
        intent.addFlags(1);
        try {
            application.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    public final Object clean(Application application, Continuation<Object> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AutoUpdate$clean$2(application, (Continuation<? super AutoUpdate$clean$2>) null), continuation);
    }
}
