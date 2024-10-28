package org.videolan.tools;

import android.content.Context;
import android.content.res.Resources;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Integer;"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: DrawableCache.kt */
final class DrawableCache$getDrawableFromMemCache$1 extends Lambda implements Function0<Integer> {
    final /* synthetic */ Context $ctx;
    final /* synthetic */ int $defaultDrawable;
    final /* synthetic */ String $name;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DrawableCache$getDrawableFromMemCache$1(Context context, String str, int i) {
        super(0);
        this.$ctx = context;
        this.$name = str;
        this.$defaultDrawable = i;
    }

    public final Integer invoke() {
        Resources resources = this.$ctx.getResources();
        Intrinsics.checkNotNullExpressionValue(resources, "getResources(...)");
        String str = this.$name;
        String packageName = this.$ctx.getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName, "getPackageName(...)");
        return Integer.valueOf(KotlinExtensionsKt.getDrawableOrDefault(resources, str, packageName, this.$defaultDrawable));
    }
}
