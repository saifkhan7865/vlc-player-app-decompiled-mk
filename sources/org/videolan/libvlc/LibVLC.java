package org.videolan.libvlc;

import android.content.Context;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import org.videolan.libvlc.interfaces.AbstractVLCEvent;
import org.videolan.libvlc.interfaces.ILibVLC;
import org.videolan.libvlc.util.HWDecoderUtil;

public class LibVLC extends VLCObject<ILibVLC.Event> implements ILibVLC {
    private static final String TAG = "VLC/LibVLC";
    private static boolean sLoaded = false;
    final Context mAppContext;

    public static native String changeset();

    public static native String compiler();

    public static native int majorVersion();

    private native void nativeNew(String[] strArr, String str);

    private native void nativeRelease();

    private native void nativeSetUserAgent(String str, String str2);

    public static native String version();

    /* access modifiers changed from: protected */
    public ILibVLC.Event onEventNative(int i, long j, long j2, float f, String str) {
        return null;
    }

    public /* bridge */ /* synthetic */ long getInstance() {
        return super.getInstance();
    }

    public /* bridge */ /* synthetic */ ILibVLC getLibVLC() {
        return super.getLibVLC();
    }

    public /* bridge */ /* synthetic */ boolean isReleased() {
        return super.isReleased();
    }

    public static class Event extends AbstractVLCEvent {
        protected Event(int i) {
            super(i);
        }
    }

    public LibVLC(Context context, List<String> list) {
        this.mAppContext = context.getApplicationContext();
        loadLibraries();
        list = list == null ? new ArrayList<>() : list;
        boolean z = true;
        boolean z2 = true;
        for (String next : list) {
            z = next.startsWith("--aout=") ? false : z;
            z2 = next.startsWith("--android-display-chroma") ? false : z2;
            if (!z && !z2) {
                break;
            }
        }
        if (z || z2) {
            if (z) {
                if (HWDecoderUtil.getAudioOutputFromDevice() == HWDecoderUtil.AudioOutput.OPENSLES) {
                    list.add("--aout=opensles");
                } else {
                    list.add("--aout=android_audiotrack");
                }
            }
            if (z2) {
                list.add("--android-display-chroma");
                list.add("RV16");
            }
        }
        nativeNew((String[]) list.toArray(new String[list.size()]), context.getDir("vlc", 0).getAbsolutePath());
    }

    public LibVLC(Context context) {
        this(context, (List<String>) null);
    }

    public Context getAppContext() {
        return this.mAppContext;
    }

    /* access modifiers changed from: protected */
    public void onReleaseNative() {
        nativeRelease();
    }

    public void setUserAgent(String str, String str2) {
        nativeSetUserAgent(str, str2);
    }

    public static synchronized void loadLibraries() {
        synchronized (LibVLC.class) {
            if (!sLoaded) {
                sLoaded = true;
                try {
                    System.loadLibrary("c++_shared");
                    System.loadLibrary("vlc");
                    System.loadLibrary("vlcjni");
                } catch (UnsatisfiedLinkError e) {
                    Log.e(TAG, "Can't load vlcjni library: " + e);
                    System.exit(1);
                } catch (SecurityException e2) {
                    Log.e(TAG, "Encountered a security issue when loading vlcjni library: " + e2);
                    System.exit(1);
                }
            }
        }
    }
}
