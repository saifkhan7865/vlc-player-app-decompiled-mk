package org.videolan.vlc.gui;

import android.text.format.Formatter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.databinding.InfoActivityBinding;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "invoke", "(Ljava/lang/Long;)V"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: InfoActivity.kt */
final class InfoActivity$onCreate$3 extends Lambda implements Function1<Long, Unit> {
    final /* synthetic */ InfoActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    InfoActivity$onCreate$3(InfoActivity infoActivity) {
        super(1);
        this.this$0 = infoActivity;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Long) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Long l) {
        this.this$0.getBinding$vlc_android_release().fileSizeViews.setVisibility((l != null && l.longValue() == -1) ? 8 : 0);
        InfoActivityBinding binding$vlc_android_release = this.this$0.getBinding$vlc_android_release();
        Intrinsics.checkNotNull(l);
        binding$vlc_android_release.setSizeValueText(Formatter.formatFileSize(this.this$0, l.longValue()));
    }
}
