package org.videolan.vlc.providers;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.tools.AppScope;

@Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002#\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00060\u0001j\u0002`\u0007J\u0013\u0010\b\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0002¨\u0006\t"}, d2 = {"org/videolan/vlc/providers/BrowserProvider$completionHandler$1", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "", "Lkotlinx/coroutines/CompletionHandler;", "invoke", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BrowserProvider.kt */
public final class BrowserProvider$completionHandler$1 implements Function1<Throwable, Unit> {
    final /* synthetic */ BrowserProvider this$0;

    BrowserProvider$completionHandler$1(BrowserProvider browserProvider) {
        this.this$0 = browserProvider;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public void invoke(Throwable th) {
        if (this.this$0.getMediabrowser() != null) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(AppScope.INSTANCE, this.this$0.getCoroutineContextProvider().getIO(), (CoroutineStart) null, new BrowserProvider$completionHandler$1$invoke$1(this.this$0, (Continuation<? super BrowserProvider$completionHandler$1$invoke$1>) null), 2, (Object) null);
        }
    }
}
