package org.videolan.vlc.gui.browser;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "", "invoke", "(Ljava/lang/String;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: StorageFragmentDelegate.kt */
final class StorageFragmentDelegate$checkBoxAction$2 extends Lambda implements Function1<String, Boolean> {
    final /* synthetic */ String $path;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StorageFragmentDelegate$checkBoxAction$2(String str) {
        super(1);
        this.$path = str;
    }

    public final Boolean invoke(String str) {
        Intrinsics.checkNotNullParameter(str, "it");
        return Boolean.valueOf(StringsKt.startsWith$default(str, this.$path, false, 2, (Object) null));
    }
}