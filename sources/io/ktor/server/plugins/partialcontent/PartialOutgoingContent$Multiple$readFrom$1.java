package io.ktor.server.plugins.partialcontent;

import io.ktor.server.plugins.partialcontent.PartialOutgoingContent;
import io.ktor.utils.io.ByteReadChannel;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.ranges.LongRange;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lio/ktor/utils/io/ByteReadChannel;", "range", "Lkotlin/ranges/LongRange;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: PartialOutgoingContent.kt */
final class PartialOutgoingContent$Multiple$readFrom$1 extends Lambda implements Function1<LongRange, ByteReadChannel> {
    final /* synthetic */ PartialOutgoingContent.Multiple this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PartialOutgoingContent$Multiple$readFrom$1(PartialOutgoingContent.Multiple multiple) {
        super(1);
        this.this$0 = multiple;
    }

    public final ByteReadChannel invoke(LongRange longRange) {
        Intrinsics.checkNotNullParameter(longRange, "range");
        return this.this$0.getOriginal().readFrom(longRange);
    }
}
