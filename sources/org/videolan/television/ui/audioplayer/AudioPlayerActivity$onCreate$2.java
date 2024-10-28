package org.videolan.television.ui.audioplayer;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "invoke", "(Ljava/lang/Float;)V"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioPlayerActivity.kt */
final class AudioPlayerActivity$onCreate$2 extends Lambda implements Function1<Float, Unit> {
    final /* synthetic */ AudioPlayerActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AudioPlayerActivity$onCreate$2(AudioPlayerActivity audioPlayerActivity) {
        super(1);
        this.this$0 = audioPlayerActivity;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Float) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Float f) {
        this.this$0.showChips();
    }
}
