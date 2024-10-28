package org.videolan.vlc.viewmodels.browser;

import android.net.Uri;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.libvlc.util.MediaBrowser;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.viewmodels.browser.FavoritesProvider$requestBrowsing$2", f = "BrowserFavoritesModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: BrowserFavoritesModel.kt */
final class FavoritesProvider$requestBrowsing$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MediaBrowser.EventListener $eventListener;
    final /* synthetic */ boolean $interact;
    final /* synthetic */ String $url;
    int label;
    final /* synthetic */ FavoritesProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FavoritesProvider$requestBrowsing$2(FavoritesProvider favoritesProvider, MediaBrowser.EventListener eventListener, String str, boolean z, Continuation<? super FavoritesProvider$requestBrowsing$2> continuation) {
        super(2, continuation);
        this.this$0 = favoritesProvider;
        this.$eventListener = eventListener;
        this.$url = str;
        this.$interact = z;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FavoritesProvider$requestBrowsing$2(this.this$0, this.$eventListener, this.$url, this.$interact, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FavoritesProvider$requestBrowsing$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0.initBrowser();
            MediaBrowser mediabrowser = this.this$0.getMediabrowser();
            if (mediabrowser == null) {
                return null;
            }
            MediaBrowser.EventListener eventListener = this.$eventListener;
            String str = this.$url;
            FavoritesProvider favoritesProvider = this.this$0;
            boolean z = this.$interact;
            mediabrowser.changeEventListener(eventListener);
            if (str != null) {
                mediabrowser.browse(Uri.parse(str), favoritesProvider.getFlags(z));
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
