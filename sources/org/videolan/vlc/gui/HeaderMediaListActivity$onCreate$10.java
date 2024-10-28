package org.videolan.vlc.gui;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.HeaderMediaListActivityBinding;
import org.videolan.vlc.gui.helpers.UiTools;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.HeaderMediaListActivity$onCreate$10", f = "HeaderMediaListActivity.kt", i = {0}, l = {204, 220}, m = "invokeSuspend", n = {"showBackground"}, s = {"L$0"})
/* compiled from: HeaderMediaListActivity.kt */
final class HeaderMediaListActivity$onCreate$10 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ HeaderMediaListActivity $context;
    final /* synthetic */ MediaLibraryItem $playlist;
    Object L$0;
    int label;
    final /* synthetic */ HeaderMediaListActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    HeaderMediaListActivity$onCreate$10(HeaderMediaListActivity headerMediaListActivity, HeaderMediaListActivity headerMediaListActivity2, MediaLibraryItem mediaLibraryItem, Continuation<? super HeaderMediaListActivity$onCreate$10> continuation) {
        super(2, continuation);
        this.this$0 = headerMediaListActivity;
        this.$context = headerMediaListActivity2;
        this.$playlist = mediaLibraryItem;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new HeaderMediaListActivity$onCreate$10(this.this$0, this.$context, this.$playlist, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HeaderMediaListActivity$onCreate$10) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Ref.BooleanRef booleanRef;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            booleanRef = new Ref.BooleanRef();
            booleanRef.element = true;
            this.L$0 = booleanRef;
            this.label = 1;
            obj = BuildersKt.withContext(Dispatchers.getIO(), new HeaderMediaListActivity$onCreate$10$cover$1(this.this$0, this.$context, this.$playlist, booleanRef, (Continuation<? super HeaderMediaListActivity$onCreate$10$cover$1>) null), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            booleanRef = (Ref.BooleanRef) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else if (i == 2) {
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Bitmap bitmap = (Bitmap) obj;
        if (bitmap != null) {
            HeaderMediaListActivityBinding access$getBinding$p = this.this$0.binding;
            if (access$getBinding$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                access$getBinding$p = null;
            }
            access$getBinding$p.setCover(new BitmapDrawable(this.this$0.getResources(), bitmap));
            HeaderMediaListActivityBinding access$getBinding$p2 = this.this$0.binding;
            if (access$getBinding$p2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                access$getBinding$p2 = null;
            }
            access$getBinding$p2.appbar.setExpanded(true, true);
            if (booleanRef.element) {
                float f = this.this$0.isPlaylist ? 25.0f : 15.0f;
                UiTools uiTools = UiTools.INSTANCE;
                HeaderMediaListActivityBinding access$getBinding$p3 = this.this$0.binding;
                if (access$getBinding$p3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    access$getBinding$p3 = null;
                }
                ImageView imageView = access$getBinding$p3.backgroundView;
                Intrinsics.checkNotNullExpressionValue(imageView, "backgroundView");
                this.L$0 = null;
                this.label = 2;
                if (uiTools.blurView(imageView, bitmap, f, UiTools.INSTANCE.getColorFromAttribute(this.$context, R.attr.audio_player_background_tint), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        }
        return Unit.INSTANCE;
    }
}
