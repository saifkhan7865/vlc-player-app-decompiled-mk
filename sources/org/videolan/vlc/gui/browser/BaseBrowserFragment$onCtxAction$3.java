package org.videolan.vlc.gui.browser;

import androidx.lifecycle.LifecycleOwnerKt;
import io.ktor.http.ContentDisposition;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.viewmodels.browser.BrowserModel;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "item", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "name", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: BaseBrowserFragment.kt */
final class BaseBrowserFragment$onCtxAction$3 extends Lambda implements Function2<MediaLibraryItem, String, Unit> {
    final /* synthetic */ BaseBrowserFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseBrowserFragment$onCtxAction$3(BaseBrowserFragment baseBrowserFragment) {
        super(2);
        this.this$0 = baseBrowserFragment;
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.gui.browser.BaseBrowserFragment$onCtxAction$3$1", f = "BaseBrowserFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.gui.browser.BaseBrowserFragment$onCtxAction$3$1  reason: invalid class name */
    /* compiled from: BaseBrowserFragment.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(mediaLibraryItem, baseBrowserFragment, str, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            String parent;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                MediaLibraryItem mediaLibraryItem = mediaLibraryItem;
                Intrinsics.checkNotNull(mediaLibraryItem, "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper");
                String path = ((MediaWrapper) mediaLibraryItem).getUri().getPath();
                if (path != null) {
                    File file = new File(path);
                    String str = str;
                    if (file.exists() && (parent = file.getParent()) != null) {
                        Intrinsics.checkNotNull(parent);
                        Boxing.boxBoolean(file.renameTo(new File(parent + '/' + str)));
                    }
                }
                ((BrowserModel) baseBrowserFragment.getViewModel()).refresh();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((MediaLibraryItem) obj, (String) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(final MediaLibraryItem mediaLibraryItem, final String str) {
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        final BaseBrowserFragment baseBrowserFragment = this.this$0;
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this.this$0), Dispatchers.getIO(), (CoroutineStart) null, new AnonymousClass1((Continuation<? super AnonymousClass1>) null), 2, (Object) null);
    }
}
