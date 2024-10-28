package org.videolan.television.ui;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.television.util.HelpersKt;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n\u0018\u00010\u0003j\u0004\u0018\u0001`\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaScrapingTvFragment.kt */
final class MediaScrapingTvFragment$onCreate$3 extends Lambda implements Function1<Exception, Unit> {
    final /* synthetic */ MediaScrapingTvFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaScrapingTvFragment$onCreate$3(MediaScrapingTvFragment mediaScrapingTvFragment) {
        super(1);
        this.this$0 = mediaScrapingTvFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Exception) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Exception exc) {
        if (exc != null) {
            MediaScrapingTvFragment mediaScrapingTvFragment = this.this$0;
            FragmentActivity requireActivity = mediaScrapingTvFragment.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            HelpersKt.manageHttpException(requireActivity, exc);
            LifecycleOwnerKt.getLifecycleScope(mediaScrapingTvFragment).launchWhenStarted(new MediaScrapingTvFragment$onCreate$3$1$1(mediaScrapingTvFragment, (Continuation<? super MediaScrapingTvFragment$onCreate$3$1$1>) null));
        }
    }
}
