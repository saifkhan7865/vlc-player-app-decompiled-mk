package io.ktor.server.util;

import io.ktor.http.ContentDisposition;
import io.ktor.http.Parameters;
import io.ktor.server.plugins.MissingRequestParameterException;
import io.ktor.server.plugins.ParameterConversionException;
import io.ktor.util.converters.DefaultConversionService;
import io.ktor.util.reflect.TypeInfo;
import io.ktor.util.reflect.TypeInfoJvmKt;
import java.lang.reflect.Type;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.KType;
import kotlin.reflect.TypesJVMKt;

@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\b\u001a&\u0010\u0000\u001a\u0002H\u0004\"\n\b\u0000\u0010\u0004\u0018\u0001*\u00020\u0005*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\b¢\u0006\u0002\u0010\u0006\u001a+\u0010\u0007\u001a\u0002H\u0004\"\b\b\u0000\u0010\u0004*\u00020\u0005*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\tH\u0001¢\u0006\u0002\u0010\n\u001a4\u0010\u000b\u001a\u0002H\u0004\"\n\b\u0000\u0010\u0004\u0018\u0001*\u00020\u0005*\u00020\u00022\b\u0010\f\u001a\u0004\u0018\u00010\u00052\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000eH\n¢\u0006\u0002\u0010\u000f¨\u0006\u0010"}, d2 = {"getOrFail", "", "Lio/ktor/http/Parameters;", "name", "R", "", "(Lio/ktor/http/Parameters;Ljava/lang/String;)Ljava/lang/Object;", "getOrFailImpl", "typeInfo", "Lio/ktor/util/reflect/TypeInfo;", "(Lio/ktor/http/Parameters;Ljava/lang/String;Lio/ktor/util/reflect/TypeInfo;)Ljava/lang/Object;", "getValue", "thisRef", "property", "Lkotlin/reflect/KProperty;", "(Lio/ktor/http/Parameters;Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Parameters.kt */
public final class ParametersKt {
    public static final /* synthetic */ <R> R getValue(Parameters parameters, Object obj, KProperty<?> kProperty) {
        Intrinsics.checkNotNullParameter(parameters, "<this>");
        Intrinsics.checkNotNullParameter(kProperty, "property");
        String name = kProperty.getName();
        Intrinsics.reifiedOperationMarker(6, "R");
        Type javaType = TypesJVMKt.getJavaType((KType) null);
        Intrinsics.reifiedOperationMarker(4, "R");
        return getOrFailImpl(parameters, name, TypeInfoJvmKt.typeInfoImpl(javaType, Reflection.getOrCreateKotlinClass(Object.class), (KType) null));
    }

    public static final String getOrFail(Parameters parameters, String str) {
        Intrinsics.checkNotNullParameter(parameters, "<this>");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        String str2 = parameters.get(str);
        if (str2 != null) {
            return str2;
        }
        throw new MissingRequestParameterException(str);
    }

    public static final <R> R getOrFailImpl(Parameters parameters, String str, TypeInfo typeInfo) {
        Intrinsics.checkNotNullParameter(parameters, "<this>");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(typeInfo, "typeInfo");
        List<String> all = parameters.getAll(str);
        if (all != null) {
            try {
                R fromValues = DefaultConversionService.INSTANCE.fromValues(all, typeInfo);
                Intrinsics.checkNotNull(fromValues, "null cannot be cast to non-null type R of io.ktor.server.util.ParametersKt.getOrFailImpl");
                return fromValues;
            } catch (Exception e) {
                String simpleName = typeInfo.getType().getSimpleName();
                if (simpleName == null) {
                    simpleName = typeInfo.getType().toString();
                }
                throw new ParameterConversionException(str, simpleName, e);
            }
        } else {
            throw new MissingRequestParameterException(str);
        }
    }
}
