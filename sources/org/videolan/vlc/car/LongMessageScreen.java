package org.videolan.vlc.car;

import androidx.car.app.CarContext;
import androidx.car.app.Screen;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\bJ\b\u0010\n\u001a\u00020\u000bH\u0016R\u000e\u0010\u0006\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u0005X\u0004¢\u0006\u0004\n\u0002\u0010\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lorg/videolan/vlc/car/LongMessageScreen;", "Landroidx/car/app/Screen;", "carContext", "Landroidx/car/app/CarContext;", "titleRes", "", "messageRes", "messageRes2", "(Landroidx/car/app/CarContext;IILjava/lang/Integer;)V", "Ljava/lang/Integer;", "onGetTemplate", "Landroidx/car/app/model/Template;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CarScreens.kt */
public final class LongMessageScreen extends Screen {
    private final int messageRes;
    private final Integer messageRes2;
    private final int titleRes;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LongMessageScreen(CarContext carContext, int i, int i2, Integer num) {
        super(carContext);
        Intrinsics.checkNotNullParameter(carContext, "carContext");
        this.titleRes = i;
        this.messageRes = i2;
        this.messageRes2 = num;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ LongMessageScreen(CarContext carContext, int i, int i2, Integer num, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(carContext, i, i2, (i3 & 8) != 0 ? null : num);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0047, code lost:
        if (r2 == null) goto L_0x0049;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.car.app.model.Template onGetTemplate() {
        /*
            r7 = this;
            org.videolan.resources.AppContextProvider r0 = org.videolan.resources.AppContextProvider.INSTANCE
            android.content.Context r0 = r0.getAppContext()
            int r1 = r7.titleRes
            java.lang.String r0 = r0.getString(r1)
            java.lang.String r1 = "getString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.lang.Integer r2 = r7.messageRes2
            if (r2 == 0) goto L_0x0049
            java.lang.Number r2 = (java.lang.Number) r2
            int r2 = r2.intValue()
            org.videolan.resources.AppContextProvider r3 = org.videolan.resources.AppContextProvider.INSTANCE
            android.content.Context r3 = r3.getAppContext()
            int r4 = r7.messageRes
            java.lang.String r3 = r3.getString(r4)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r1)
            org.videolan.resources.AppContextProvider r4 = org.videolan.resources.AppContextProvider.INSTANCE
            android.content.Context r4 = r4.getAppContext()
            java.lang.String r2 = r4.getString(r2)
            r4 = 1
            java.lang.Object[] r5 = new java.lang.Object[r4]
            r6 = 0
            r5[r6] = r2
            java.lang.Object[] r2 = java.util.Arrays.copyOf(r5, r4)
            java.lang.String r2 = java.lang.String.format(r3, r2)
            java.lang.String r3 = "format(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            if (r2 != 0) goto L_0x0058
        L_0x0049:
            org.videolan.resources.AppContextProvider r2 = org.videolan.resources.AppContextProvider.INSTANCE
            android.content.Context r2 = r2.getAppContext()
            int r3 = r7.messageRes
            java.lang.String r2 = r2.getString(r3)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r1)
        L_0x0058:
            androidx.car.app.model.LongMessageTemplate$Builder r1 = new androidx.car.app.model.LongMessageTemplate$Builder
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r1.<init>(r2)
            androidx.car.app.model.Action r2 = androidx.car.app.model.Action.BACK
            r1.setHeaderAction(r2)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r1.setTitle(r0)
            androidx.car.app.model.LongMessageTemplate r0 = r1.build()
            java.lang.String r1 = "build(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            androidx.car.app.model.Template r0 = (androidx.car.app.model.Template) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.car.LongMessageScreen.onGetTemplate():androidx.car.app.model.Template");
    }
}
