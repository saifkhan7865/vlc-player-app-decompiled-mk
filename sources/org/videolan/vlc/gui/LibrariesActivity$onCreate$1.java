package org.videolan.vlc.gui;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.gui.dialogs.LicenseDialog;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lorg/videolan/vlc/gui/LibraryWithLicense;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: LibrariesActivity.kt */
final class LibrariesActivity$onCreate$1 extends Lambda implements Function1<LibraryWithLicense, Unit> {
    final /* synthetic */ LibrariesActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    LibrariesActivity$onCreate$1(LibrariesActivity librariesActivity) {
        super(1);
        this.this$0 = librariesActivity;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((LibraryWithLicense) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(LibraryWithLicense libraryWithLicense) {
        Intrinsics.checkNotNullParameter(libraryWithLicense, "it");
        LicenseDialog.Companion.newInstance(libraryWithLicense).show(this.this$0.getSupportFragmentManager(), "LicenseDialog");
    }
}
