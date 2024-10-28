package io.ktor.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.ktor.features.ContentNegotiation;
import io.ktor.http.ContentType;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import org.bouncycastle.cms.CMSAttributeTableGenerator;

@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a/\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u0019\b\u0002\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\b\u001a\u0018\u0010\t\u001a\u00020\n*\u00020\u000b2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\rH\u0002¨\u0006\u000e"}, d2 = {"gson", "", "Lio/ktor/features/ContentNegotiation$Configuration;", "contentType", "Lio/ktor/http/ContentType;", "block", "Lkotlin/Function1;", "Lcom/google/gson/GsonBuilder;", "Lkotlin/ExtensionFunctionType;", "isExcluded", "", "Lcom/google/gson/Gson;", "type", "Lkotlin/reflect/KClass;", "ktor-gson"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* compiled from: GsonSupport.kt */
public final class GsonSupportKt {
    public static /* synthetic */ void gson$default(ContentNegotiation.Configuration configuration, ContentType contentType, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            contentType = ContentType.Application.INSTANCE.getJson();
        }
        if ((i & 2) != 0) {
            function1 = GsonSupportKt$gson$1.INSTANCE;
        }
        gson(configuration, contentType, function1);
    }

    public static final void gson(ContentNegotiation.Configuration configuration, ContentType contentType, Function1<? super GsonBuilder, Unit> function1) {
        Intrinsics.checkNotNullParameter(configuration, "<this>");
        Intrinsics.checkNotNullParameter(contentType, CMSAttributeTableGenerator.CONTENT_TYPE);
        Intrinsics.checkNotNullParameter(function1, "block");
        GsonBuilder gsonBuilder = new GsonBuilder();
        function1.invoke(gsonBuilder);
        Gson create = gsonBuilder.create();
        Intrinsics.checkNotNullExpressionValue(create, "builder.create()");
        ContentNegotiation.Configuration.register$default(configuration, contentType, new GsonConverter(create), (Function1) null, 4, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final boolean isExcluded(Gson gson, KClass<?> kClass) {
        return gson.excluder().excludeClass(JvmClassMappingKt.getJavaClass(kClass), false);
    }
}
