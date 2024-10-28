package org.videolan.vlc.util;

import android.content.Context;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.fusesource.jansi.AnsiRenderer;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006J\u001b\u0010\f\u001a\u00020\u00062\u000e\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u000e¢\u0006\u0002\u0010\u000fJ'\u0010\f\u001a\u00020\u00062\u0016\u0010\r\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00060\u000e\"\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0004\b\u0010\u0010\u000fJ#\u0010\f\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00042\u000e\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u000e¢\u0006\u0002\u0010\u0011J/\u0010\f\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00042\u0016\u0010\r\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00060\u000e\"\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lorg/videolan/vlc/util/TextUtils;", "", "()V", "separator", "", "formatChapterTitle", "", "context", "Landroid/content/Context;", "chapterNum", "", "title", "separatedString", "pieces", "", "([Ljava/lang/String;)Ljava/lang/String;", "separatedStringArgs", "(C[Ljava/lang/String;)Ljava/lang/String;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: TextUtils.kt */
public final class TextUtils {
    public static final TextUtils INSTANCE = new TextUtils();
    public static final char separator = '·';

    private TextUtils() {
    }

    public final String separatedStringArgs(String... strArr) {
        Intrinsics.checkNotNullParameter(strArr, "pieces");
        return separatedString(183, (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    public final String separatedString(String[] strArr) {
        Intrinsics.checkNotNullParameter(strArr, "pieces");
        return separatedString(183, strArr);
    }

    public final String separatedStringArgs(char c, String... strArr) {
        Intrinsics.checkNotNullParameter(strArr, "pieces");
        return separatedString(c, (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    public final String formatChapterTitle(Context context, int i, String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        CharSequence charSequence = str;
        if (charSequence == null || StringsKt.isBlank(charSequence)) {
            String string = context.getString(R.string.current_chapter, new Object[]{String.valueOf(i)});
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            return string;
        }
        for (int i2 = 0; i2 < charSequence.length(); i2++) {
            if (!Character.isLetter(charSequence.charAt(i2))) {
                return str;
            }
        }
        String string2 = context.getString(R.string.current_chapter, new Object[]{str});
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        return string2;
    }

    public final String separatedString(char c, String[] strArr) {
        Intrinsics.checkNotNullParameter(strArr, "pieces");
        Collection arrayList = new ArrayList();
        for (String str : strArr) {
            if (str != null && (!StringsKt.isBlank(str))) {
                arrayList.add(str);
            }
        }
        return CollectionsKt.joinToString$default((List) arrayList, AnsiRenderer.CODE_TEXT_SEPARATOR + c + ' ', (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
    }
}
