package io.ktor.server.http.content;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "Resource", "", "it", "invoke", "(Ljava/lang/Object;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: StaticContent.kt */
final class StaticContentConfig$exclude$2 extends Lambda implements Function1<Resource, Boolean> {
    final /* synthetic */ Function1<Resource, Boolean> $block;
    final /* synthetic */ Function1<Resource, Boolean> $oldBlock;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StaticContentConfig$exclude$2(Function1<? super Resource, Boolean> function1, Function1<? super Resource, Boolean> function12) {
        super(1);
        this.$oldBlock = function1;
        this.$block = function12;
    }

    public final Boolean invoke(Resource resource) {
        boolean z;
        Intrinsics.checkNotNullParameter(resource, "it");
        if (this.$oldBlock.invoke(resource).booleanValue()) {
            z = true;
        } else {
            z = this.$block.invoke(resource).booleanValue();
        }
        return Boolean.valueOf(z);
    }
}
