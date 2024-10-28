package kotlin.reflect.jvm.internal.impl.types.error;

import io.ktor.http.ContentDisposition;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* compiled from: ErrorScope.kt */
public class ErrorScope implements MemberScope {
    private final String debugMessage;
    private final ErrorScopeKind kind;

    public void recordLookup(Name name, LookupLocation lookupLocation) {
        Intrinsics.checkNotNullParameter(name, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(lookupLocation, "location");
    }

    public ErrorScope(ErrorScopeKind errorScopeKind, String... strArr) {
        Intrinsics.checkNotNullParameter(errorScopeKind, "kind");
        Intrinsics.checkNotNullParameter(strArr, "formatParams");
        this.kind = errorScopeKind;
        String debugMessage2 = errorScopeKind.getDebugMessage();
        Object[] copyOf = Arrays.copyOf(strArr, strArr.length);
        String format = String.format(debugMessage2, Arrays.copyOf(copyOf, copyOf.length));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        this.debugMessage = format;
    }

    /* access modifiers changed from: protected */
    public final String getDebugMessage() {
        return this.debugMessage;
    }

    public ClassifierDescriptor getContributedClassifier(Name name, LookupLocation lookupLocation) {
        Intrinsics.checkNotNullParameter(name, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(lookupLocation, "location");
        String format = String.format(ErrorEntity.ERROR_CLASS.getDebugText(), Arrays.copyOf(new Object[]{name}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        Name special = Name.special(format);
        Intrinsics.checkNotNullExpressionValue(special, "special(...)");
        return new ErrorClassDescriptor(special);
    }

    public Set<PropertyDescriptor> getContributedVariables(Name name, LookupLocation lookupLocation) {
        Intrinsics.checkNotNullParameter(name, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(lookupLocation, "location");
        return ErrorUtils.INSTANCE.getErrorPropertyGroup();
    }

    public Set<SimpleFunctionDescriptor> getContributedFunctions(Name name, LookupLocation lookupLocation) {
        Intrinsics.checkNotNullParameter(name, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(lookupLocation, "location");
        return SetsKt.setOf(new ErrorFunctionDescriptor(ErrorUtils.INSTANCE.getErrorClass()));
    }

    public Collection<DeclarationDescriptor> getContributedDescriptors(DescriptorKindFilter descriptorKindFilter, Function1<? super Name, Boolean> function1) {
        Intrinsics.checkNotNullParameter(descriptorKindFilter, "kindFilter");
        Intrinsics.checkNotNullParameter(function1, "nameFilter");
        return CollectionsKt.emptyList();
    }

    public Set<Name> getFunctionNames() {
        return SetsKt.emptySet();
    }

    public Set<Name> getVariableNames() {
        return SetsKt.emptySet();
    }

    public Set<Name> getClassifierNames() {
        return SetsKt.emptySet();
    }

    public String toString() {
        return "ErrorScope{" + this.debugMessage + AbstractJsonLexerKt.END_OBJ;
    }
}
