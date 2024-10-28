package org.videolan.vlc.gui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\nH@¢\u0006\u0002\u0010\u000bR\u001d\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\f"}, d2 = {"Lorg/videolan/vlc/gui/LicenseModel;", "Landroidx/lifecycle/ViewModel;", "()V", "licenses", "Landroidx/lifecycle/MutableLiveData;", "", "Lorg/videolan/vlc/gui/LibraryWithLicense;", "getLicenses", "()Landroidx/lifecycle/MutableLiveData;", "refresh", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: LibrariesActivity.kt */
public final class LicenseModel extends ViewModel {
    private final MutableLiveData<List<LibraryWithLicense>> licenses = new MutableLiveData<>();

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object refresh(kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof org.videolan.vlc.gui.LicenseModel$refresh$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            org.videolan.vlc.gui.LicenseModel$refresh$1 r0 = (org.videolan.vlc.gui.LicenseModel$refresh$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.gui.LicenseModel$refresh$1 r0 = new org.videolan.vlc.gui.LicenseModel$refresh$1
            r0.<init>(r5, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r0 = r0.L$0
            org.videolan.vlc.gui.LicenseModel r0 = (org.videolan.vlc.gui.LicenseModel) r0
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0053
        L_0x002e:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlinx.coroutines.CoroutineDispatcher r6 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r6 = (kotlin.coroutines.CoroutineContext) r6
            org.videolan.vlc.gui.LicenseModel$refresh$parsedLicenses$1 r2 = new org.videolan.vlc.gui.LicenseModel$refresh$parsedLicenses$1
            r4 = 0
            r2.<init>(r4)
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r6 = kotlinx.coroutines.BuildersKt.withContext(r6, r2, r0)
            if (r6 != r1) goto L_0x0052
            return r1
        L_0x0052:
            r0 = r5
        L_0x0053:
            java.util.List r6 = (java.util.List) r6
            androidx.lifecycle.MutableLiveData<java.util.List<org.videolan.vlc.gui.LibraryWithLicense>> r0 = r0.licenses
            r0.setValue(r6)
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.LicenseModel.refresh(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final MutableLiveData<List<LibraryWithLicense>> getLicenses() {
        return this.licenses;
    }
}
