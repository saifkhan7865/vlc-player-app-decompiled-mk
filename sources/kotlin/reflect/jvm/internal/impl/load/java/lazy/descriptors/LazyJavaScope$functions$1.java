package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import io.ktor.http.ContentDisposition;
import java.util.Collection;
import java.util.LinkedHashSet;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;

/* compiled from: LazyJavaScope.kt */
final class LazyJavaScope$functions$1 extends Lambda implements Function1<Name, Collection<? extends SimpleFunctionDescriptor>> {
    final /* synthetic */ LazyJavaScope this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    LazyJavaScope$functions$1(LazyJavaScope lazyJavaScope) {
        super(1);
        this.this$0 = lazyJavaScope;
    }

    public final Collection<SimpleFunctionDescriptor> invoke(Name name) {
        Intrinsics.checkNotNullParameter(name, ContentDisposition.Parameters.Name);
        LinkedHashSet linkedHashSet = new LinkedHashSet((Collection) this.this$0.declaredFunctions.invoke(name));
        this.this$0.retainMostSpecificMethods(linkedHashSet);
        Collection collection = linkedHashSet;
        this.this$0.computeNonDeclaredFunctions(collection, name);
        return CollectionsKt.toList(this.this$0.getC().getComponents().getSignatureEnhancement().enhanceSignatures(this.this$0.getC(), collection));
    }
}
