package org.videolan.television.ui;

import androidx.leanback.widget.ArrayObjectAdapter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "invoke", "(Ljava/lang/Boolean;)V"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: MainTvFragment.kt */
final class MainTvFragment$onViewCreated$1 extends Lambda implements Function1<Boolean, Unit> {
    final /* synthetic */ GenericCardItem $remoteAccessCard;
    final /* synthetic */ MainTvFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MainTvFragment$onViewCreated$1(MainTvFragment mainTvFragment, GenericCardItem genericCardItem) {
        super(1);
        this.this$0 = mainTvFragment;
        this.$remoteAccessCard = genericCardItem;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Boolean) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Boolean bool) {
        Intrinsics.checkNotNull(bool);
        ArrayObjectAdapter arrayObjectAdapter = null;
        if (bool.booleanValue()) {
            ArrayObjectAdapter access$getOtherAdapter$p = this.this$0.otherAdapter;
            if (access$getOtherAdapter$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("otherAdapter");
                access$getOtherAdapter$p = null;
            }
            ArrayObjectAdapter access$getOtherAdapter$p2 = this.this$0.otherAdapter;
            if (access$getOtherAdapter$p2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("otherAdapter");
            } else {
                arrayObjectAdapter = access$getOtherAdapter$p2;
            }
            access$getOtherAdapter$p.add(arrayObjectAdapter.size() - 2, this.$remoteAccessCard);
            return;
        }
        ArrayObjectAdapter access$getOtherAdapter$p3 = this.this$0.otherAdapter;
        if (access$getOtherAdapter$p3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("otherAdapter");
        } else {
            arrayObjectAdapter = access$getOtherAdapter$p3;
        }
        arrayObjectAdapter.remove(this.$remoteAccessCard);
    }
}
