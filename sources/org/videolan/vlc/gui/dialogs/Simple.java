package org.videolan.vlc.gui.dialogs;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.util.ContextOption;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/Simple;", "Lorg/videolan/vlc/gui/dialogs/CtxMenuItem;", "id", "Lorg/videolan/vlc/util/ContextOption;", "title", "", "icon", "", "(Lorg/videolan/vlc/util/ContextOption;Ljava/lang/String;I)V", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ContextSheet.kt */
public final class Simple extends CtxMenuItem {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Simple(ContextOption contextOption, String str, int i) {
        super(contextOption, str, i, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(contextOption, "id");
        Intrinsics.checkNotNullParameter(str, "title");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Simple(ContextOption contextOption, String str, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(contextOption, str, (i2 & 4) != 0 ? 0 : i);
    }
}
