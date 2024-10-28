package io.ktor.server.engine;

import io.ktor.http.ContentDisposition;
import io.ktor.http.HttpHeaders;
import io.ktor.http.content.OutgoingContent;
import io.ktor.server.engine.BaseApplicationResponse;
import io.ktor.server.response.ResponseHeaders;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "name", "", "values", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: BaseApplicationResponse.kt */
final class BaseApplicationResponse$commitHeaders$2 extends Lambda implements Function2<String, List<? extends String>, Unit> {
    final /* synthetic */ OutgoingContent $content;
    final /* synthetic */ Ref.BooleanRef $transferEncodingSet;
    final /* synthetic */ BaseApplicationResponse this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseApplicationResponse$commitHeaders$2(Ref.BooleanRef booleanRef, OutgoingContent outgoingContent, BaseApplicationResponse baseApplicationResponse) {
        super(2);
        this.$transferEncodingSet = booleanRef;
        this.$content = outgoingContent;
        this.this$0 = baseApplicationResponse;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((String) obj, (List<String>) (List) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(String str, List<String> list) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(list, "values");
        if (Intrinsics.areEqual((Object) str, (Object) HttpHeaders.INSTANCE.getTransferEncoding())) {
            this.$transferEncodingSet.element = true;
        } else if (Intrinsics.areEqual((Object) str, (Object) HttpHeaders.INSTANCE.getUpgrade())) {
            if (this.$content instanceof OutgoingContent.ProtocolUpgrade) {
                for (String append : list) {
                    this.this$0.getHeaders().append(str, append, false);
                }
                return;
            }
            throw new BaseApplicationResponse.InvalidHeaderForContent(HttpHeaders.INSTANCE.getUpgrade(), "non-upgrading response");
        }
        for (String append$default : list) {
            ResponseHeaders.append$default(this.this$0.getHeaders(), str, append$default, false, 4, (Object) null);
        }
    }
}
