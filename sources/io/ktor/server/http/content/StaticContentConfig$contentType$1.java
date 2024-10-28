package io.ktor.server.http.content;

import io.ktor.http.ContentType;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "Lio/ktor/http/ContentType;", "Resource", "", "resource", "invoke", "(Ljava/lang/Object;)Lio/ktor/http/ContentType;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: StaticContent.kt */
final class StaticContentConfig$contentType$1 extends Lambda implements Function1<Resource, ContentType> {
    final /* synthetic */ Function1<Resource, ContentType> $block;
    final /* synthetic */ StaticContentConfig<Resource> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StaticContentConfig$contentType$1(Function1<? super Resource, ContentType> function1, StaticContentConfig<Resource> staticContentConfig) {
        super(1);
        this.$block = function1;
        this.this$0 = staticContentConfig;
    }

    public final ContentType invoke(Resource resource) {
        Intrinsics.checkNotNullParameter(resource, "resource");
        ContentType invoke = this.$block.invoke(resource);
        return invoke == null ? (ContentType) this.this$0.defaultContentType.invoke(resource) : invoke;
    }
}
