package org.videolan.vlc.webserver;

import java.text.DateFormat;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "s", "", "strings", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: RemoteAccessRouting.kt */
final class RemoteAccessRoutingKt$setupRouting$11$logs$1$1 extends Lambda implements Function2<String, List<? extends String>, Unit> {
    final /* synthetic */ StringBuilder $this_buildString;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteAccessRoutingKt$setupRouting$11$logs$1$1(StringBuilder sb) {
        super(2);
        this.$this_buildString = sb;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((String) obj, (List<String>) (List) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(String str, List<String> list) {
        Intrinsics.checkNotNullParameter(str, "s");
        Intrinsics.checkNotNullParameter(list, "strings");
        CharSequence charSequence = str;
        String str2 = null;
        if (StringsKt.contains$default(charSequence, (CharSequence) "[time]", false, 2, (Object) null)) {
            StringBuilder sb = this.$this_buildString;
            DateFormat dateFormat = (DateFormat) RemoteAccessRoutingKt.getFormat().get();
            if (dateFormat != null) {
                str2 = dateFormat.format(Long.valueOf(Long.parseLong(list.get(0))));
            }
            sb.append(str2);
        } else if (StringsKt.contains$default(charSequence, (CharSequence) "[level]", false, 2, (Object) null)) {
            this.$this_buildString.append(" - ");
            this.$this_buildString.append(list.get(0));
        } else {
            StringBuilder sb2 = this.$this_buildString;
            for (String append : list) {
                sb2.append(" - ");
                sb2.append(append);
                sb2.append("*");
            }
        }
    }
}
