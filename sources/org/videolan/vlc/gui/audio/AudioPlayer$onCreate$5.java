package org.videolan.vlc.gui.audio;

import java.util.Calendar;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Ljava/util/Calendar;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioPlayer.kt */
final class AudioPlayer$onCreate$5 extends Lambda implements Function1<Calendar, Unit> {
    final /* synthetic */ AudioPlayer this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AudioPlayer$onCreate$5(AudioPlayer audioPlayer) {
        super(1);
        this.this$0 = audioPlayer;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Calendar) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Calendar calendar) {
        this.this$0.showChips();
    }
}