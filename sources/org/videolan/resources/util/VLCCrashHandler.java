package org.videolan.resources.util;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.text.format.DateFormat;
import android.util.Log;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.resources.AppContextProvider;
import org.videolan.tools.CloseableUtils;
import org.videolan.tools.Logcat;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lorg/videolan/resources/util/VLCCrashHandler;", "Ljava/lang/Thread$UncaughtExceptionHandler;", "()V", "defaultUEH", "uncaughtException", "", "thread", "Ljava/lang/Thread;", "ex", "", "Companion", "resources_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VLCCrashHandler.kt */
public final class VLCCrashHandler implements Thread.UncaughtExceptionHandler {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final Thread.UncaughtExceptionHandler defaultUEH = Thread.getDefaultUncaughtExceptionHandler();

    public void uncaughtException(Thread thread, Throwable th) {
        Intrinsics.checkNotNullParameter(thread, "thread");
        Intrinsics.checkNotNullParameter(th, "ex");
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.defaultUEH;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, Companion.saveLog$default(Companion, th, (String) null, 2, (Object) null));
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u0007J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0007H\u0002J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0007H\u0002¨\u0006\r"}, d2 = {"Lorg/videolan/resources/util/VLCCrashHandler$Companion;", "", "()V", "saveLog", "", "ex", "watermark", "", "writeLog", "", "log", "name", "writeLogcat", "resources_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: VLCCrashHandler.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ Throwable saveLog$default(Companion companion, Throwable th, String str, int i, Object obj) {
            if ((i & 2) != 0) {
                str = "";
            }
            return companion.saveLog(th, str);
        }

        public final Throwable saveLog(Throwable th, String str) {
            Intrinsics.checkNotNullParameter(th, "ex");
            Intrinsics.checkNotNullParameter(str, "watermark");
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            StackTraceElement[] stackTrace = th.getStackTrace();
            CharSequence charSequence = str;
            StackTraceElement[] stackTraceElementArr = new StackTraceElement[(stackTrace.length + (charSequence.length() > 0 ? 4 : 3))];
            System.arraycopy(stackTrace, 0, stackTraceElementArr, 0, stackTrace.length);
            stackTraceElementArr[stackTrace.length] = new StackTraceElement("Android", "MODEL", Build.MODEL, -1);
            stackTraceElementArr[stackTrace.length + 1] = new StackTraceElement("Android", "VERSION", Build.VERSION.RELEASE, -1);
            stackTraceElementArr[stackTrace.length + 2] = new StackTraceElement("Android", "FINGERPRINT", Build.FINGERPRINT, -1);
            if (charSequence.length() > 0) {
                stackTraceElementArr[stackTrace.length + 3] = new StackTraceElement("VLC", "Watermark", str, -1);
            }
            th.setStackTrace(stackTraceElementArr);
            th.printStackTrace(printWriter);
            String stringWriter2 = stringWriter.toString();
            Intrinsics.checkNotNullExpressionValue(stringWriter2, "toString(...)");
            printWriter.close();
            Log.e("VLC/VlcCrashHandler", stringWriter2);
            if (Intrinsics.areEqual((Object) Environment.getExternalStorageState(), (Object) "mounted")) {
                StringBuilder sb = new StringBuilder();
                File externalFilesDir = AppContextProvider.INSTANCE.getAppContext().getExternalFilesDir((String) null);
                Intrinsics.checkNotNull(externalFilesDir);
                sb.append(externalFilesDir.getAbsolutePath());
                sb.append("/vlc_crash");
                writeLog(stringWriter2, sb.toString());
                StringBuilder sb2 = new StringBuilder();
                File externalFilesDir2 = AppContextProvider.INSTANCE.getAppContext().getExternalFilesDir((String) null);
                Intrinsics.checkNotNull(externalFilesDir2);
                sb2.append(externalFilesDir2.getAbsolutePath());
                sb2.append("/vlc_logcat");
                writeLogcat(sb2.toString());
            }
            return th;
        }

        private final void writeLog(String str, String str2) {
            Object obj;
            CharSequence format = DateFormat.format("yyyyMMdd_kkmmss", System.currentTimeMillis());
            try {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(str2 + '_' + format + ".log"));
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                try {
                    PackageManager packageManager = AppContextProvider.INSTANCE.getAppContext().getPackageManager();
                    Intrinsics.checkNotNullExpressionValue(packageManager, "getPackageManager(...)");
                    String packageName = AppContextProvider.INSTANCE.getAppContext().getPackageName();
                    Intrinsics.checkNotNullExpressionValue(packageName, "getPackageName(...)");
                    obj = ExtensionsKt.getPackageInfoCompat(packageManager, packageName, 0).versionName;
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                    obj = Unit.INSTANCE;
                }
                try {
                    bufferedWriter.write("App version: " + obj + "\r\n");
                    bufferedWriter.write(str);
                    bufferedWriter.newLine();
                } catch (IOException e2) {
                    e2.printStackTrace();
                } catch (Throwable th) {
                    CloseableUtils.INSTANCE.close(bufferedWriter);
                    CloseableUtils.INSTANCE.close(outputStreamWriter);
                    throw th;
                }
                CloseableUtils.INSTANCE.close(bufferedWriter);
                CloseableUtils.INSTANCE.close(outputStreamWriter);
            } catch (FileNotFoundException e3) {
                e3.printStackTrace();
            }
        }

        private final void writeLogcat(String str) {
            CharSequence format = DateFormat.format("yyyyMMdd_kkmmss", System.currentTimeMillis());
            try {
                Logcat.Companion.writeLogcat(str + '_' + format + ".log");
            } catch (IOException unused) {
                Log.e("VLC/VlcCrashHandler", "Cannot write logcat to disk");
            }
        }
    }
}
