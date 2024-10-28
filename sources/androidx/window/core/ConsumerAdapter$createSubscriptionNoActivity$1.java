package androidx.window.core;

import androidx.window.core.ConsumerAdapter;
import java.lang.reflect.Method;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"androidx/window/core/ConsumerAdapter$createSubscriptionNoActivity$1", "Landroidx/window/core/ConsumerAdapter$Subscription;", "dispose", "", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ConsumerAdapter.kt */
public final class ConsumerAdapter$createSubscriptionNoActivity$1 implements ConsumerAdapter.Subscription {
    final /* synthetic */ Object $javaConsumer;
    final /* synthetic */ Object $obj;
    final /* synthetic */ Method $removeMethod;

    ConsumerAdapter$createSubscriptionNoActivity$1(Method method, Object obj, Object obj2) {
        this.$removeMethod = method;
        this.$obj = obj;
        this.$javaConsumer = obj2;
    }

    public void dispose() {
        this.$removeMethod.invoke(this.$obj, new Object[]{this.$javaConsumer});
    }
}
