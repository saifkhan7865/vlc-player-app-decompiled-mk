package org.videolan.resources;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import org.videolan.libvlc.FactoryManager;
import org.videolan.libvlc.interfaces.IComponentFactory;
import org.videolan.libvlc.interfaces.ILibVLC;
import org.videolan.libvlc.interfaces.ILibVLCFactory;
import org.videolan.libvlc.util.VLCUtil;
import org.videolan.resources.util.VLCCrashHandler;
import org.videolan.tools.SingletonHolder;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u000e\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0003J\u000e\u0010\f\u001a\u00020\rH@¢\u0006\u0002\u0010\u000eJ\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0003R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u00020\u00028\u0002@\u0002X.¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lorg/videolan/resources/VLCInstance;", "Lorg/videolan/tools/SingletonHolder;", "Lorg/videolan/libvlc/interfaces/ILibVLC;", "Landroid/content/Context;", "()V", "TAG", "", "libVLCFactory", "Lorg/videolan/libvlc/interfaces/ILibVLCFactory;", "sLibVLC", "init", "ctx", "restart", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "testCompatibleCPU", "", "context", "resources_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VLCInstance.kt */
public final class VLCInstance extends SingletonHolder<ILibVLC, Context> {
    public static final VLCInstance INSTANCE = new VLCInstance();
    public static final String TAG = "VLC/UiTools/VLCInstance";
    /* access modifiers changed from: private */
    public static final ILibVLCFactory libVLCFactory;
    /* access modifiers changed from: private */
    public static ILibVLC sLibVLC;

    private VLCInstance() {
        super(AnonymousClass1.INSTANCE);
    }

    static {
        IComponentFactory factory = FactoryManager.getFactory(ILibVLCFactory.factoryId);
        Intrinsics.checkNotNull(factory, "null cannot be cast to non-null type org.videolan.libvlc.interfaces.ILibVLCFactory");
        libVLCFactory = (ILibVLCFactory) factory;
    }

    public final ILibVLC init(Context context) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(context, "ctx");
        Thread.setDefaultUncaughtExceptionHandler(new VLCCrashHandler());
        if (VLCUtil.hasCompatibleCPU(context)) {
            ILibVLC fromOptions = libVLCFactory.getFromOptions(context, VLCOptions.INSTANCE.getLibOptions());
            Intrinsics.checkNotNullExpressionValue(fromOptions, "getFromOptions(...)");
            sLibVLC = fromOptions;
            if (fromOptions != null) {
                return fromOptions;
            }
            Intrinsics.throwUninitializedPropertyAccessException("sLibVLC");
            return null;
        }
        Log.e(TAG, VLCUtil.getErrorMsg());
        throw new IllegalStateException("LibVLC initialisation failed: " + VLCUtil.getErrorMsg());
    }

    public final Object restart(Continuation<? super Unit> continuation) throws IllegalStateException {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new VLCInstance$restart$2((Continuation<? super VLCInstance$restart$2>) null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    public final boolean testCompatibleCPU(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (VLCUtil.hasCompatibleCPU(context)) {
            return true;
        }
        if (context instanceof Activity) {
            Intent className = new Intent("android.intent.action.VIEW").setClassName(((Activity) context).getApplicationContext(), Constants.COMPATERROR_ACTIVITY);
            Intrinsics.checkNotNullExpressionValue(className, "setClassName(...)");
            context.startActivity(className);
        }
        return false;
    }
}
