package org.videolan.vlc.gui.helpers.hf;

import android.net.Uri;
import androidx.activity.ComponentActivity;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelLazy;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Reflection;
import org.videolan.vlc.util.FileUtils;

@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H@¢\u0006\u0002\u0010\u0005\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0004H@¢\u0006\u0002\u0010\u0007¨\u0006\b²\u0006\n\u0010\t\u001a\u00020\nX\u0002"}, d2 = {"getExtWritePermission", "", "Landroidx/fragment/app/Fragment;", "uri", "Landroid/net/Uri;", "(Landroidx/fragment/app/Fragment;Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroidx/fragment/app/FragmentActivity;", "(Landroidx/fragment/app/FragmentActivity;Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vlc-android_release", "model", "Lorg/videolan/vlc/gui/helpers/hf/PermissionViewmodel;"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: WriteExternalDelegate.kt */
public final class WriteExternalDelegateKt {
    public static final Object getExtWritePermission(FragmentActivity fragmentActivity, Uri uri, Continuation<? super Boolean> continuation) {
        if (!WriteExternalDelegate.Companion.needsWritePermission(uri)) {
            return Boxing.boxBoolean(true);
        }
        String mediaStorage = FileUtils.INSTANCE.getMediaStorage(uri);
        if (mediaStorage == null) {
            return Boxing.boxBoolean(false);
        }
        ComponentActivity componentActivity = fragmentActivity;
        Lazy viewModelLazy = new ViewModelLazy(Reflection.getOrCreateKotlinClass(PermissionViewmodel.class), new WriteExternalDelegateKt$getExtWritePermission$$inlined$viewModels$default$2(componentActivity), new WriteExternalDelegateKt$getExtWritePermission$$inlined$viewModels$default$1(componentActivity), new WriteExternalDelegateKt$getExtWritePermission$$inlined$viewModels$default$3((Function0) null, componentActivity));
        WriteExternalDelegate writeExternalDelegate = new WriteExternalDelegate();
        getExtWritePermission$lambda$0(viewModelLazy).setupDeferred();
        writeExternalDelegate.setArguments(BundleKt.bundleOf(TuplesKt.to(WriteExternalDelegate.KEY_STORAGE_PATH, mediaStorage)));
        fragmentActivity.getSupportFragmentManager().beginTransaction().add((Fragment) writeExternalDelegate, WriteExternalDelegate.TAG).commitAllowingStateLoss();
        return getExtWritePermission$lambda$0(viewModelLazy).getDeferredGrant().await(continuation);
    }

    private static final PermissionViewmodel getExtWritePermission$lambda$0(Lazy<PermissionViewmodel> lazy) {
        return lazy.getValue();
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object getExtWritePermission(androidx.fragment.app.Fragment r5, android.net.Uri r6, kotlin.coroutines.Continuation<? super java.lang.Boolean> r7) {
        /*
            boolean r0 = r7 instanceof org.videolan.vlc.gui.helpers.hf.WriteExternalDelegateKt$getExtWritePermission$2
            if (r0 == 0) goto L_0x0014
            r0 = r7
            org.videolan.vlc.gui.helpers.hf.WriteExternalDelegateKt$getExtWritePermission$2 r0 = (org.videolan.vlc.gui.helpers.hf.WriteExternalDelegateKt$getExtWritePermission$2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.gui.helpers.hf.WriteExternalDelegateKt$getExtWritePermission$2 r0 = new org.videolan.vlc.gui.helpers.hf.WriteExternalDelegateKt$getExtWritePermission$2
            r0.<init>(r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x0033
            if (r2 != r4) goto L_0x002b
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0045
        L_0x002b:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0033:
            kotlin.ResultKt.throwOnFailure(r7)
            androidx.fragment.app.FragmentActivity r5 = r5.getActivity()
            if (r5 == 0) goto L_0x004e
            r0.label = r4
            java.lang.Object r7 = getExtWritePermission((androidx.fragment.app.FragmentActivity) r5, (android.net.Uri) r6, (kotlin.coroutines.Continuation<? super java.lang.Boolean>) r0)
            if (r7 != r1) goto L_0x0045
            return r1
        L_0x0045:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r5 = r7.booleanValue()
            if (r5 == 0) goto L_0x004e
            r3 = 1
        L_0x004e:
            java.lang.Boolean r5 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.helpers.hf.WriteExternalDelegateKt.getExtWritePermission(androidx.fragment.app.Fragment, android.net.Uri, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
