package org.videolan.vlc.gui;

import androidx.fragment.app.FragmentActivity;
import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.util.KextensionsKt;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: DebugLogActivity.kt */
final class DebugLogActivity$onSaved$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ String $path;
    final /* synthetic */ DebugLogActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DebugLogActivity$onSaved$1(DebugLogActivity debugLogActivity, String str) {
        super(0);
        this.this$0 = debugLogActivity;
        this.$path = str;
    }

    public final void invoke() {
        KextensionsKt.share((FragmentActivity) this.this$0, new File(this.$path));
    }
}
