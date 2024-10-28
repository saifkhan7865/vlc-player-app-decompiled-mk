package org.videolan.vlc.gui;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.Spanned;
import android.text.format.DateFormat;
import android.text.format.Formatter;
import androidx.core.content.FileProvider;
import androidx.core.text.HtmlCompat;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.resources.AppContextProvider;
import org.videolan.tools.AppUtils;
import org.videolan.vlc.DebugLogService;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.SendCrashActivityBinding;
import org.videolan.vlc.util.FileUtils;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "Landroid/content/Intent;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.SendCrashActivity$onSaved$1$emailIntent$1", f = "SendCrashActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: SendCrashActivity.kt */
final class SendCrashActivity$onSaved$1$emailIntent$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Intent>, Object> {
    final /* synthetic */ String $path;
    int label;
    final /* synthetic */ SendCrashActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SendCrashActivity$onSaved$1$emailIntent$1(SendCrashActivity sendCrashActivity, String str, Continuation<? super SendCrashActivity$onSaved$1$emailIntent$1> continuation) {
        super(2, continuation);
        this.this$0 = sendCrashActivity;
        this.$path = str;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SendCrashActivity$onSaved$1$emailIntent$1(this.this$0, this.$path, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Intent> continuation) {
        return ((SendCrashActivity$onSaved$1$emailIntent$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        String str;
        String str2;
        String absolutePath;
        File[] listFiles;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            DebugLogService.Client access$getClient$p = this.this$0.client;
            String str3 = null;
            if (access$getClient$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("client");
                access$getClient$p = null;
            }
            access$getClient$p.stop();
            if (this.this$0.logcatZipPath == null) {
                File externalFilesDir = AppContextProvider.INSTANCE.getAppContext().getExternalFilesDir((String) null);
                String absolutePath2 = externalFilesDir != null ? externalFilesDir.getAbsolutePath() : null;
                if (absolutePath2 == null) {
                    return null;
                }
                this.this$0.logcatZipPath = absolutePath2 + "/logcat.zip";
            }
            List<String> mutableListOf = CollectionsKt.mutableListOf(this.$path);
            try {
                File externalFilesDir2 = AppContextProvider.INSTANCE.getAppContext().getExternalFilesDir((String) null);
                if (!(externalFilesDir2 == null || (absolutePath = externalFilesDir2.getAbsolutePath()) == null || (listFiles = new File(absolutePath).listFiles()) == null)) {
                    Intrinsics.checkNotNull(listFiles);
                    for (File file : listFiles) {
                        if (file.isFile()) {
                            String name = file.getName();
                            Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
                            if (!StringsKt.contains$default((CharSequence) name, (CharSequence) "crash", false, 2, (Object) null)) {
                                String name2 = file.getName();
                                Intrinsics.checkNotNullExpressionValue(name2, "getName(...)");
                                if (!StringsKt.contains$default((CharSequence) name2, (CharSequence) "logcat", false, 2, (Object) null)) {
                                }
                            }
                            String path = file.getPath();
                            Intrinsics.checkNotNullExpressionValue(path, "getPath(...)");
                            mutableListOf.add(path);
                        }
                    }
                }
            } catch (IOException unused) {
            }
            FileUtils fileUtils = FileUtils.INSTANCE;
            String[] strArr = (String[]) mutableListOf.toArray(new String[0]);
            String access$getLogcatZipPath$p = this.this$0.logcatZipPath;
            if (access$getLogcatZipPath$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logcatZipPath");
                access$getLogcatZipPath$p = null;
            }
            fileUtils.zip(strArr, access$getLogcatZipPath$p);
            try {
                for (String deleteFile : mutableListOf) {
                    FileUtils.INSTANCE.deleteFile(deleteFile);
                }
            } catch (IOException unused2) {
            }
            Intent intent = new Intent("android.intent.action.SEND_MULTIPLE");
            intent.setType("message/rfc822");
            ArrayList arrayList = new ArrayList();
            SendCrashActivityBinding access$getBinding$p = this.this$0.binding;
            if (access$getBinding$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                access$getBinding$p = null;
            }
            if (access$getBinding$p.includeMedialibSwitch.isChecked()) {
                if (this.this$0.dbPath == null) {
                    File externalFilesDir3 = AppContextProvider.INSTANCE.getAppContext().getExternalFilesDir((String) null);
                    String absolutePath3 = externalFilesDir3 != null ? externalFilesDir3.getAbsolutePath() : null;
                    if (absolutePath3 == null) {
                        return null;
                    }
                    this.this$0.dbPath = absolutePath3 + "//vlc_media.db";
                    this.this$0.dbZipPath = absolutePath3 + "/db.zip";
                }
                File file2 = new File(this.this$0.getDir("db", 0).toString() + Medialibrary.VLC_MEDIA_DB_NAME);
                String access$getDbPath$p = this.this$0.dbPath;
                if (access$getDbPath$p == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("dbPath");
                    access$getDbPath$p = null;
                }
                File file3 = new File(access$getDbPath$p);
                FileUtils.INSTANCE.copyFile(file2, file3);
                FileUtils fileUtils2 = FileUtils.INSTANCE;
                String access$getDbPath$p2 = this.this$0.dbPath;
                if (access$getDbPath$p2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("dbPath");
                    access$getDbPath$p2 = null;
                }
                String[] strArr2 = {access$getDbPath$p2};
                String access$getDbZipPath$p = this.this$0.dbZipPath;
                if (access$getDbZipPath$p == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("dbZipPath");
                    access$getDbZipPath$p = null;
                }
                fileUtils2.zip(strArr2, access$getDbZipPath$p);
                FileUtils.INSTANCE.deleteFile(file3);
                Context context = this.this$0;
                String str4 = this.this$0.getApplicationContext().getPackageName() + ".provider";
                String access$getDbZipPath$p2 = this.this$0.dbZipPath;
                if (access$getDbZipPath$p2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("dbZipPath");
                    access$getDbZipPath$p2 = null;
                }
                arrayList.add(FileProvider.getUriForFile(context, str4, new File(access$getDbZipPath$p2)));
            }
            StringBuilder sb = new StringBuilder();
            try {
                sb.append("App version: 3.6.0 Beta 2<br/>App version code: 3050720<br/>");
            } catch (PackageManager.NameNotFoundException unused3) {
            }
            sb.append("Time: " + DateFormat.format("MM/dd/yyyy kk:mm:ss", System.currentTimeMillis()) + "<br/>");
            sb.append("Device model: " + Build.MANUFACTURER + ' ' + Build.MODEL + "<br/>");
            sb.append("Android version: " + Build.VERSION.SDK_INT + "<br/>");
            sb.append("System name: " + Build.DISPLAY + "<br/>");
            sb.append("Memory free: " + Formatter.formatFileSize(this.this$0, AppUtils.INSTANCE.freeMemory()) + " on " + Formatter.formatFileSize(this.this$0, AppUtils.INSTANCE.totalMemory()));
            Context context2 = this.this$0;
            String str5 = this.this$0.getApplicationContext().getPackageName() + ".provider";
            String access$getLogcatZipPath$p2 = this.this$0.logcatZipPath;
            if (access$getLogcatZipPath$p2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logcatZipPath");
                access$getLogcatZipPath$p2 = null;
            }
            arrayList.add(FileProvider.getUriForFile(context2, str5, new File(access$getLogcatZipPath$p2)));
            intent.putExtra("android.intent.extra.STREAM", arrayList);
            intent.setType("application/zip");
            if (this.this$0.errMsg != null) {
                StringBuilder sb2 = new StringBuilder();
                String access$getErrCtx$p = this.this$0.errCtx;
                if (access$getErrCtx$p == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("errCtx");
                    access$getErrCtx$p = null;
                }
                sb2.append(access$getErrCtx$p);
                sb2.append(":\n");
                String access$getErrMsg$p = this.this$0.errMsg;
                if (access$getErrMsg$p == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("errMsg");
                } else {
                    str3 = access$getErrMsg$p;
                }
                sb2.append(str3);
                str = sb2.toString();
            } else {
                str = this.this$0.getString(R.string.describe_crash);
                Intrinsics.checkNotNull(str);
            }
            Spanned fromHtml = HtmlCompat.fromHtml("<p>Here are my crash logs for VLC</strong></p><p style=3D\"color:#16171A;\"> [" + str + "]</p><p>" + sb + "</p>", 0);
            Intrinsics.checkNotNullExpressionValue(fromHtml, "fromHtml(...)");
            intent.putExtra("android.intent.extra.EMAIL", new String[]{"vlc.crashreport+androidcrash@gmail.com"});
            if (this.this$0.errMsg != null) {
                str2 = "[3.6.0 Beta 2] Medialibrary uncaught exception!";
            } else {
                str2 = "[3.6.0 Beta 2] Crash logs for VLC";
            }
            intent.putExtra("android.intent.extra.SUBJECT", str2);
            intent.putExtra("android.intent.extra.TEXT", fromHtml);
            intent.addFlags(268435456);
            return intent;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
