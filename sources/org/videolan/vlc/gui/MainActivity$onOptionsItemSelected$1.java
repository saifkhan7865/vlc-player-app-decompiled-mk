package org.videolan.vlc.gui;

import android.content.SharedPreferences;
import android.view.MenuItem;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.gui.helpers.UiTools;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.MainActivity$onOptionsItemSelected$1", f = "MainActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MainActivity.kt */
final class MainActivity$onOptionsItemSelected$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MenuItem $item;
    int label;
    final /* synthetic */ MainActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MainActivity$onOptionsItemSelected$1(MainActivity mainActivity, MenuItem menuItem, Continuation<? super MainActivity$onOptionsItemSelected$1> continuation) {
        super(2, continuation);
        this.this$0 = mainActivity;
        this.$item = menuItem;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MainActivity$onOptionsItemSelected$1(this.this$0, this.$item, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MainActivity$onOptionsItemSelected$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (UiTools.INSTANCE.showPinIfNeeded(this.this$0)) {
                return Unit.INSTANCE;
            }
            SettingsKt.putSingle((SharedPreferences) Settings.INSTANCE.getInstance(this.this$0), SettingsKt.KEY_INCOGNITO, Boxing.boxBoolean(!((SharedPreferences) Settings.INSTANCE.getInstance(this.this$0)).getBoolean(SettingsKt.KEY_INCOGNITO, false)));
            MenuItem menuItem = this.$item;
            menuItem.setChecked(!menuItem.isChecked());
            this.this$0.updateIncognitoModeIcon();
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
