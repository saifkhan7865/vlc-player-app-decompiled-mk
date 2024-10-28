package org.videolan.vlc.gui;

import androidx.core.graphics.Insets;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Landroidx/core/graphics/Insets;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioPlayerContainerActivity.kt */
final class AudioPlayerContainerActivity$insetListener$1 extends Lambda implements Function1<Insets, Unit> {
    public static final AudioPlayerContainerActivity$insetListener$1 INSTANCE = new AudioPlayerContainerActivity$insetListener$1();

    AudioPlayerContainerActivity$insetListener$1() {
        super(1);
    }

    public final void invoke(Insets insets) {
        Intrinsics.checkNotNullParameter(insets, "it");
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Insets) obj);
        return Unit.INSTANCE;
    }
}
