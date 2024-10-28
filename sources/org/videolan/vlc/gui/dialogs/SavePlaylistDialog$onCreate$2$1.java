package org.videolan.vlc.gui.dialogs;

import android.content.res.Resources;
import java.util.Collection;
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
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.R;
import org.videolan.vlc.providers.BrowserProvider;
import org.videolan.vlc.providers.FileBrowserProvider;
import org.videolan.vlc.viewmodels.browser.BrowserModel;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.dialogs.SavePlaylistDialog$onCreate$2$1", f = "SavePlaylistDialog.kt", i = {}, l = {120}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: SavePlaylistDialog.kt */
final class SavePlaylistDialog$onCreate$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $folder;
    final /* synthetic */ BrowserModel $viewModel;
    int label;
    final /* synthetic */ SavePlaylistDialog this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SavePlaylistDialog$onCreate$2$1(SavePlaylistDialog savePlaylistDialog, BrowserModel browserModel, String str, Continuation<? super SavePlaylistDialog$onCreate$2$1> continuation) {
        super(2, continuation);
        this.this$0 = savePlaylistDialog;
        this.$viewModel = browserModel;
        this.$folder = str;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SavePlaylistDialog$onCreate$2$1(this.this$0, this.$viewModel, this.$folder, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SavePlaylistDialog$onCreate$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.gui.dialogs.SavePlaylistDialog$onCreate$2$1$1", f = "SavePlaylistDialog.kt", i = {}, l = {121}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.gui.dialogs.SavePlaylistDialog$onCreate$2$1$1  reason: invalid class name */
    /* compiled from: SavePlaylistDialog.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(savePlaylistDialog, browserModel, str, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            SavePlaylistDialog savePlaylistDialog;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SavePlaylistDialog savePlaylistDialog2 = savePlaylistDialog;
                BrowserProvider provider = browserModel.getProvider();
                Intrinsics.checkNotNull(provider, "null cannot be cast to non-null type org.videolan.vlc.providers.FileBrowserProvider");
                String str = str;
                Intrinsics.checkNotNullExpressionValue(str, "$folder");
                this.L$0 = savePlaylistDialog2;
                this.label = 1;
                Object browseByUrl = ((FileBrowserProvider) provider).browseByUrl(str, this);
                if (browseByUrl == coroutine_suspended) {
                    return coroutine_suspended;
                }
                savePlaylistDialog = savePlaylistDialog2;
                obj = browseByUrl;
            } else if (i == 1) {
                savePlaylistDialog = (SavePlaylistDialog) this.L$0;
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            savePlaylistDialog.newTracks = (MediaWrapper[]) ((Collection) obj).toArray(new MediaWrapper[0]);
            savePlaylistDialog.setLoading(false);
            SavePlaylistDialog savePlaylistDialog3 = savePlaylistDialog;
            Resources resources = savePlaylistDialog3.getResources();
            int i2 = R.plurals.media_quantity;
            MediaWrapper[] access$getNewTracks$p = savePlaylistDialog.newTracks;
            MediaWrapper[] mediaWrapperArr = null;
            if (access$getNewTracks$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("newTracks");
                access$getNewTracks$p = null;
            }
            int length = access$getNewTracks$p.length;
            MediaWrapper[] access$getNewTracks$p2 = savePlaylistDialog.newTracks;
            if (access$getNewTracks$p2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("newTracks");
            } else {
                mediaWrapperArr = access$getNewTracks$p2;
            }
            String quantityString = resources.getQuantityString(i2, length, new Object[]{Boxing.boxInt(mediaWrapperArr.length)});
            Intrinsics.checkNotNullExpressionValue(quantityString, "getQuantityString(...)");
            savePlaylistDialog3.setFilesText(quantityString);
            return Unit.INSTANCE;
        }
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final SavePlaylistDialog savePlaylistDialog = this.this$0;
            final BrowserModel browserModel = this.$viewModel;
            final String str = this.$folder;
            this.label = 1;
            if (BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass1((Continuation<? super AnonymousClass1>) null), this) == coroutine_suspended) {
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
