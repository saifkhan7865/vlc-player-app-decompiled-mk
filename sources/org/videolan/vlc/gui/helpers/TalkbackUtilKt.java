package org.videolan.vlc.gui.helpers;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.fusesource.jansi.AnsiRenderer;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"talkbackAppend", "", "other", "longPause", "", "vlc-android_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: TalkbackUtil.kt */
public final class TalkbackUtilKt {
    public static final String talkbackAppend(String str, String str2, boolean z) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        CharSequence charSequence = str2;
        if (charSequence == null || StringsKt.isBlank(charSequence)) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(z ? "." : AnsiRenderer.CODE_LIST_SEPARATOR);
        sb.append(' ');
        sb.append(str2);
        return sb.toString();
    }

    public static /* synthetic */ String talkbackAppend$default(String str, String str2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return talkbackAppend(str, str2, z);
    }
}
