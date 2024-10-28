package org.videolan.vlc.util;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Service;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.resources.AppContextProvider;
import org.videolan.resources.VLCInstance;
import org.videolan.tools.CloseableUtils;
import org.videolan.tools.WorkersKt;
import org.videolan.vlc.gui.video.VideoPlayerActivity;
import retrofit2.Platform$$ExternalSyntheticApiModelOutline0;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nJ\u0016\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lorg/videolan/vlc/util/Util;", "", "()V", "TAG", "", "checkCpuCompatibility", "", "ctx", "Landroid/content/Context;", "getFullScreenBundle", "Landroid/os/Bundle;", "kotlin.jvm.PlatformType", "readAsset", "assetName", "defaultS", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Util.kt */
public final class Util {
    public static final Util INSTANCE = new Util();
    public static final String TAG = "VLC/Util";

    private Util() {
    }

    public final String readAsset(String str, String str2) {
        BufferedReader bufferedReader;
        Intrinsics.checkNotNullParameter(str, "assetName");
        Intrinsics.checkNotNullParameter(str2, "defaultS");
        InputStream inputStream = null;
        try {
            InputStream open = AppContextProvider.INSTANCE.getAppResources().getAssets().open(str);
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(open, "UTF8"));
                try {
                    StringBuilder sb = new StringBuilder();
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        sb.append(readLine);
                        for (String readLine2 = bufferedReader.readLine(); readLine2 != null; readLine2 = bufferedReader.readLine()) {
                            sb.append(10);
                            sb.append(readLine2);
                        }
                    }
                    String sb2 = sb.toString();
                    Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
                    CloseableUtils.INSTANCE.close(open);
                    CloseableUtils.INSTANCE.close(bufferedReader);
                    return sb2;
                } catch (IOException unused) {
                    inputStream = open;
                    CloseableUtils.INSTANCE.close(inputStream);
                    CloseableUtils.INSTANCE.close(bufferedReader);
                    return str2;
                } catch (Throwable th) {
                    th = th;
                    inputStream = open;
                    CloseableUtils.INSTANCE.close(inputStream);
                    CloseableUtils.INSTANCE.close(bufferedReader);
                    throw th;
                }
            } catch (IOException unused2) {
                bufferedReader = null;
                inputStream = open;
                CloseableUtils.INSTANCE.close(inputStream);
                CloseableUtils.INSTANCE.close(bufferedReader);
                return str2;
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = null;
                inputStream = open;
                CloseableUtils.INSTANCE.close(inputStream);
                CloseableUtils.INSTANCE.close(bufferedReader);
                throw th;
            }
        } catch (IOException unused3) {
            bufferedReader = null;
            CloseableUtils.INSTANCE.close(inputStream);
            CloseableUtils.INSTANCE.close(bufferedReader);
            return str2;
        } catch (Throwable th3) {
            th = th3;
            bufferedReader = null;
            CloseableUtils.INSTANCE.close(inputStream);
            CloseableUtils.INSTANCE.close(bufferedReader);
            throw th;
        }
    }

    public final void checkCpuCompatibility(Context context) {
        Intrinsics.checkNotNullParameter(context, "ctx");
        WorkersKt.runBackground(new Util$$ExternalSyntheticLambda3(context));
    }

    /* access modifiers changed from: private */
    public static final void checkCpuCompatibility$lambda$1(Context context) {
        Intrinsics.checkNotNullParameter(context, "$ctx");
        if (!VLCInstance.INSTANCE.testCompatibleCPU(context)) {
            WorkersKt.runOnMainThread(new Util$$ExternalSyntheticLambda2(context));
        }
    }

    /* access modifiers changed from: private */
    public static final void checkCpuCompatibility$lambda$1$lambda$0(Context context) {
        Intrinsics.checkNotNullParameter(context, "$ctx");
        if (context instanceof Service) {
            ((Service) context).stopSelf();
        } else if (context instanceof VideoPlayerActivity) {
            ((VideoPlayerActivity) context).exit(0);
        } else if (context instanceof Activity) {
            ((Activity) context).finish();
        }
    }

    public final Bundle getFullScreenBundle() {
        if (Build.VERSION.SDK_INT < 24) {
            return new Bundle();
        }
        ActivityOptions m = Platform$$ExternalSyntheticApiModelOutline0.m();
        ActivityOptions unused = m.setLaunchBounds((Rect) null);
        return m.toBundle();
    }
}
