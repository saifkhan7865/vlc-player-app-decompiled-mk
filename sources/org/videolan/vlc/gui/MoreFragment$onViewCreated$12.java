package org.videolan.vlc.gui;

import android.content.Intent;
import android.view.View;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: MoreFragment.kt */
final class MoreFragment$onViewCreated$12 extends Lambda implements Function1<View, Unit> {
    final /* synthetic */ MoreFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MoreFragment$onViewCreated$12(MoreFragment moreFragment) {
        super(1);
        this.this$0 = moreFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((View) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(View view) {
        Intrinsics.checkNotNullParameter(view, "it");
        Intent intent = new Intent(this.this$0.requireActivity(), SecondaryActivity.class);
        intent.putExtra(SecondaryActivity.KEY_FRAGMENT, "history");
        this.this$0.requireActivity().startActivityForResult(intent, 3);
    }
}
