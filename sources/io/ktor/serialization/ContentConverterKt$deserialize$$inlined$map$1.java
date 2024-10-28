package io.ktor.serialization;

import io.ktor.util.reflect.TypeInfo;
import io.ktor.utils.io.ByteReadChannel;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007¸\u0006\t"}, d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$$inlined$unsafeFlow$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SafeCollector.common.kt */
public final class ContentConverterKt$deserialize$$inlined$map$1 implements Flow<Object> {
    final /* synthetic */ ByteReadChannel $body$inlined;
    final /* synthetic */ Charset $charset$inlined;
    final /* synthetic */ Flow $this_unsafeTransform$inlined;
    final /* synthetic */ TypeInfo $typeInfo$inlined;

    public ContentConverterKt$deserialize$$inlined$map$1(Flow flow, Charset charset, TypeInfo typeInfo, ByteReadChannel byteReadChannel) {
        this.$this_unsafeTransform$inlined = flow;
        this.$charset$inlined = charset;
        this.$typeInfo$inlined = typeInfo;
        this.$body$inlined = byteReadChannel;
    }

    public Object collect(final FlowCollector flowCollector, Continuation continuation) {
        Flow flow = this.$this_unsafeTransform$inlined;
        final Charset charset = this.$charset$inlined;
        final TypeInfo typeInfo = this.$typeInfo$inlined;
        final ByteReadChannel byteReadChannel = this.$body$inlined;
        Object collect = flow.collect(new FlowCollector() {
            /* JADX WARNING: Removed duplicated region for block: B:14:0x003d  */
            /* JADX WARNING: Removed duplicated region for block: B:20:0x0066 A[RETURN] */
            /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final java.lang.Object emit(java.lang.Object r9, kotlin.coroutines.Continuation r10) {
                /*
                    r8 = this;
                    boolean r0 = r10 instanceof io.ktor.serialization.ContentConverterKt$deserialize$$inlined$map$1.AnonymousClass2.AnonymousClass1
                    if (r0 == 0) goto L_0x0014
                    r0 = r10
                    io.ktor.serialization.ContentConverterKt$deserialize$$inlined$map$1$2$1 r0 = (io.ktor.serialization.ContentConverterKt$deserialize$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                    int r1 = r0.label
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r1 = r1 & r2
                    if (r1 == 0) goto L_0x0014
                    int r10 = r0.label
                    int r10 = r10 - r2
                    r0.label = r10
                    goto L_0x0019
                L_0x0014:
                    io.ktor.serialization.ContentConverterKt$deserialize$$inlined$map$1$2$1 r0 = new io.ktor.serialization.ContentConverterKt$deserialize$$inlined$map$1$2$1
                    r0.<init>(r8, r10)
                L_0x0019:
                    java.lang.Object r10 = r0.result
                    java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r2 = r0.label
                    r3 = 2
                    r4 = 1
                    if (r2 == 0) goto L_0x003d
                    if (r2 == r4) goto L_0x0035
                    if (r2 != r3) goto L_0x002d
                    kotlin.ResultKt.throwOnFailure(r10)
                    goto L_0x0067
                L_0x002d:
                    java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                    java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
                    r9.<init>(r10)
                    throw r9
                L_0x0035:
                    java.lang.Object r9 = r0.L$0
                    kotlinx.coroutines.flow.FlowCollector r9 = (kotlinx.coroutines.flow.FlowCollector) r9
                    kotlin.ResultKt.throwOnFailure(r10)
                    goto L_0x005b
                L_0x003d:
                    kotlin.ResultKt.throwOnFailure(r10)
                    kotlinx.coroutines.flow.FlowCollector r10 = r6
                    r2 = r0
                    kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                    io.ktor.serialization.ContentConverter r9 = (io.ktor.serialization.ContentConverter) r9
                    java.nio.charset.Charset r2 = r2
                    io.ktor.util.reflect.TypeInfo r5 = r3
                    io.ktor.utils.io.ByteReadChannel r6 = r4
                    r0.L$0 = r10
                    r0.label = r4
                    java.lang.Object r9 = r9.deserialize(r2, r5, r6, r0)
                    if (r9 != r1) goto L_0x0058
                    return r1
                L_0x0058:
                    r7 = r10
                    r10 = r9
                    r9 = r7
                L_0x005b:
                    r2 = 0
                    r0.L$0 = r2
                    r0.label = r3
                    java.lang.Object r9 = r9.emit(r10, r0)
                    if (r9 != r1) goto L_0x0067
                    return r1
                L_0x0067:
                    kotlin.Unit r9 = kotlin.Unit.INSTANCE
                    return r9
                */
                throw new UnsupportedOperationException("Method not decompiled: io.ktor.serialization.ContentConverterKt$deserialize$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
            }
        }, continuation);
        return collect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
    }
}
