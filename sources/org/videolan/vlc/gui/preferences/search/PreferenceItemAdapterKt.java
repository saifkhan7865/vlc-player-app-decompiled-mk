package org.videolan.vlc.gui.preferences.search;

import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;
import androidx.databinding.BindingAdapter;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002*\u0001\u0001\u001a \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0007\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\u0004\n\u0002\u0010\u0002¨\u0006\n"}, d2 = {"cb", "org/videolan/vlc/gui/preferences/search/PreferenceItemAdapterKt$cb$1", "Lorg/videolan/vlc/gui/preferences/search/PreferenceItemAdapterKt$cb$1;", "searchText", "", "view", "Landroid/widget/TextView;", "text", "", "query", "vlc-android_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: PreferenceItemAdapter.kt */
public final class PreferenceItemAdapterKt {
    /* access modifiers changed from: private */
    public static final PreferenceItemAdapterKt$cb$1 cb = new PreferenceItemAdapterKt$cb$1();

    @BindingAdapter({"searchText", "searchQueryString"})
    public static final void searchText(TextView textView, String str, String str2) {
        Intrinsics.checkNotNullParameter(textView, "view");
        Intrinsics.checkNotNullParameter(str, "text");
        Intrinsics.checkNotNullParameter(str2, "query");
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        Locale locale = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
        String lowerCase = str.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        Locale locale2 = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale2, "getDefault(...)");
        String lowerCase2 = str2.toLowerCase(locale2);
        Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
        int indexOf$default = StringsKt.indexOf$default((CharSequence) lowerCase, lowerCase2, 0, false, 6, (Object) null);
        if (indexOf$default != -1) {
            spannableStringBuilder.setSpan(new StyleSpan(1), indexOf$default, str2.length() + indexOf$default, 33);
        }
        if (indexOf$default != -1) {
            spannableStringBuilder.setSpan(new UnderlineSpan(), indexOf$default, str2.length() + indexOf$default, 33);
        }
        textView.setText(spannableStringBuilder);
    }
}
