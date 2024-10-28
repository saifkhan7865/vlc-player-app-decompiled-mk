package org.videolan.vlc.gui;

import androidx.lifecycle.LifecycleOwnerKt;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.viewmodels.mobile.PlaylistViewModel;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "item", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "name", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: HeaderMediaListActivity.kt */
final class HeaderMediaListActivity$onCtxAction$3 extends Lambda implements Function2<MediaLibraryItem, String, Unit> {
    final /* synthetic */ HeaderMediaListActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    HeaderMediaListActivity$onCtxAction$3(HeaderMediaListActivity headerMediaListActivity) {
        super(2);
        this.this$0 = headerMediaListActivity;
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.gui.HeaderMediaListActivity$onCtxAction$3$1", f = "HeaderMediaListActivity.kt", i = {}, l = {492}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.gui.HeaderMediaListActivity$onCtxAction$3$1  reason: invalid class name */
    /* compiled from: HeaderMediaListActivity.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(headerMediaListActivity, mediaLibraryItem, str, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                PlaylistViewModel access$getViewModel$p = headerMediaListActivity.viewModel;
                if (access$getViewModel$p == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    access$getViewModel$p = null;
                }
                MediaLibraryItem mediaLibraryItem = mediaLibraryItem;
                Intrinsics.checkNotNull(mediaLibraryItem, "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper");
                this.label = 1;
                if (access$getViewModel$p.rename((MediaWrapper) mediaLibraryItem, str, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((MediaLibraryItem) obj, (String) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(final MediaLibraryItem mediaLibraryItem, final String str) {
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        final HeaderMediaListActivity headerMediaListActivity = this.this$0;
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this.this$0), (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1((Continuation<? super AnonymousClass1>) null), 3, (Object) null);
    }
}
