package org.videolan.vlc.widget;

import android.content.Context;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.mediadb.models.Widget;
import org.videolan.vlc.repository.WidgetRepository;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0012B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0005H@¢\u0006\u0002\u0010\u0011R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lorg/videolan/vlc/widget/WidgetViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "context", "Landroid/content/Context;", "id", "", "(Landroid/content/Context;I)V", "widget", "Landroidx/lifecycle/LiveData;", "Lorg/videolan/vlc/mediadb/models/Widget;", "getWidget", "()Landroidx/lifecycle/LiveData;", "widgetRepository", "Lorg/videolan/vlc/repository/WidgetRepository;", "create", "", "appWidgetId", "(Landroid/content/Context;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Factory", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: WidgetViewModel.kt */
public final class WidgetViewModel extends AndroidViewModel {
    private final LiveData<Widget> widget;
    private final WidgetRepository widgetRepository;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public WidgetViewModel(android.content.Context r7, int r8) {
        /*
            r6 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            android.content.Context r0 = r7.getApplicationContext()
            java.lang.String r1 = "null cannot be cast to non-null type android.app.Application"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0, r1)
            android.app.Application r0 = (android.app.Application) r0
            r6.<init>(r0)
            org.videolan.vlc.repository.WidgetRepository$Companion r0 = org.videolan.vlc.repository.WidgetRepository.Companion
            java.lang.Object r7 = r0.getInstance(r7)
            org.videolan.vlc.repository.WidgetRepository r7 = (org.videolan.vlc.repository.WidgetRepository) r7
            r6.widgetRepository = r7
            kotlinx.coroutines.flow.Flow r0 = r7.getWidgetFlow(r8)
            r7 = r6
            androidx.lifecycle.ViewModel r7 = (androidx.lifecycle.ViewModel) r7
            kotlinx.coroutines.CoroutineScope r7 = androidx.lifecycle.ViewModelKt.getViewModelScope(r7)
            kotlin.coroutines.CoroutineContext r1 = r7.getCoroutineContext()
            r4 = 2
            r5 = 0
            r2 = 0
            androidx.lifecycle.LiveData r7 = androidx.lifecycle.FlowLiveDataConversions.asLiveData$default((kotlinx.coroutines.flow.Flow) r0, (kotlin.coroutines.CoroutineContext) r1, (long) r2, (int) r4, (java.lang.Object) r5)
            r6.widget = r7
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.widget.WidgetViewModel.<init>(android.content.Context, int):void");
    }

    public final LiveData<Widget> getWidget() {
        return this.widget;
    }

    public final Object create(Context context, int i, Continuation<? super Unit> continuation) {
        Object createNew = this.widgetRepository.createNew(context, i, continuation);
        return createNew == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? createNew : Unit.INSTANCE;
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J%\u0010\t\u001a\u0002H\n\"\b\b\u0000\u0010\n*\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\n0\rH\u0016¢\u0006\u0002\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lorg/videolan/vlc/widget/WidgetViewModel$Factory;", "Landroidx/lifecycle/ViewModelProvider$NewInstanceFactory;", "context", "Landroid/content/Context;", "id", "", "(Landroid/content/Context;I)V", "getContext", "()Landroid/content/Context;", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: WidgetViewModel.kt */
    public static final class Factory extends ViewModelProvider.NewInstanceFactory {
        private final Context context;
        private final int id;

        public Factory(Context context2, int i) {
            Intrinsics.checkNotNullParameter(context2, "context");
            this.context = context2;
            this.id = i;
        }

        public final Context getContext() {
            return this.context;
        }

        public <T extends ViewModel> T create(Class<T> cls) {
            Intrinsics.checkNotNullParameter(cls, "modelClass");
            Context applicationContext = this.context.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            return (ViewModel) new WidgetViewModel(applicationContext, this.id);
        }
    }
}
