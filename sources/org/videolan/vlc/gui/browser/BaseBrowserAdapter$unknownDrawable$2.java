package org.videolan.vlc.gui.browser;

import android.graphics.drawable.BitmapDrawable;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.helpers.BitmapUtilKt;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroid/graphics/drawable/BitmapDrawable;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: BaseBrowserAdapter.kt */
final class BaseBrowserAdapter$unknownDrawable$2 extends Lambda implements Function0<BitmapDrawable> {
    final /* synthetic */ BaseBrowserAdapter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseBrowserAdapter$unknownDrawable$2(BaseBrowserAdapter baseBrowserAdapter) {
        super(0);
        this.this$0 = baseBrowserAdapter;
    }

    public final BitmapDrawable invoke() {
        return new BitmapDrawable(this.this$0.getBrowserContainer().containerActivity().getResources(), BitmapUtilKt.getBitmapFromDrawable$default(this.this$0.getBrowserContainer().containerActivity(), R.drawable.ic_unknown, 0, 0, 6, (Object) null));
    }
}
