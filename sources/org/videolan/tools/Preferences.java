package org.videolan.tools;

import android.content.SharedPreferences;
import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004J\u001e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lorg/videolan/tools/Preferences;", "", "()V", "TAG", "", "getFloatArray", "", "pref", "Landroid/content/SharedPreferences;", "key", "putFloatArray", "", "editor", "Landroid/content/SharedPreferences$Editor;", "array", "tools_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Preferences.kt */
public final class Preferences {
    public static final Preferences INSTANCE = new Preferences();
    public static final String TAG = "VLC/UiTools/Preferences";

    private Preferences() {
    }

    public final float[] getFloatArray(SharedPreferences sharedPreferences, String str) {
        Intrinsics.checkNotNullParameter(sharedPreferences, "pref");
        Intrinsics.checkNotNullParameter(str, LeanbackPreferenceDialogFragment.ARG_KEY);
        float[] fArr = null;
        String string = sharedPreferences.getString(str, (String) null);
        if (string != null) {
            try {
                JSONArray jSONArray = new JSONArray(string);
                int length = jSONArray.length();
                fArr = new float[length];
                for (int i = 0; i < length; i++) {
                    fArr[i] = (float) jSONArray.getDouble(i);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return fArr;
    }

    public final void putFloatArray(SharedPreferences.Editor editor, String str, float[] fArr) {
        Intrinsics.checkNotNullParameter(editor, "editor");
        Intrinsics.checkNotNullParameter(str, LeanbackPreferenceDialogFragment.ARG_KEY);
        Intrinsics.checkNotNullParameter(fArr, "array");
        try {
            JSONArray jSONArray = new JSONArray();
            for (float f : fArr) {
                jSONArray.put((double) f);
            }
            editor.putString(str, jSONArray.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
