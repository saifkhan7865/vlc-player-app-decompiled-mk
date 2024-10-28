package org.videolan.vlc;

import android.net.Uri;
import android.os.Build;
import android.util.Log;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import org.videolan.resources.util.ExtensionsKt;
import org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0;
import org.videolan.tools.Strings;
import org.videolan.vlc.gui.helpers.NotificationHelper;
import org.videolan.vlc.util.TextUtils;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.MediaParsingService$showNotification$discovery$1", f = "MediaParsingService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MediaParsingService.kt */
final class MediaParsingService$showNotification$discovery$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
    final /* synthetic */ int $done;
    final /* synthetic */ float $parsing;
    final /* synthetic */ int $scheduled;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ MediaParsingService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaParsingService$showNotification$discovery$1(MediaParsingService mediaParsingService, float f, int i, int i2, Continuation<? super MediaParsingService$showNotification$discovery$1> continuation) {
        super(2, continuation);
        this.this$0 = mediaParsingService;
        this.$parsing = f;
        this.$done = i;
        this.$scheduled = i2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MediaParsingService$showNotification$discovery$1 mediaParsingService$showNotification$discovery$1 = new MediaParsingService$showNotification$discovery$1(this.this$0, this.$parsing, this.$done, this.$scheduled, continuation);
        mediaParsingService$showNotification$discovery$1.L$0 = obj;
        return mediaParsingService$showNotification$discovery$1;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
        return ((MediaParsingService$showNotification$discovery$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        String str;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            if (this.this$0.inDiscovery) {
                StringBuilder sb = new StringBuilder();
                sb.append(this.this$0.getString(R.string.ml_discovering));
                sb.append(' ');
                String access$getCurrentDiscovery$p = this.this$0.currentDiscovery;
                sb.append(Uri.decode(access$getCurrentDiscovery$p != null ? Strings.removeFileScheme(access$getCurrentDiscovery$p) : null));
                str = sb.toString();
            } else if (this.$parsing > 0.0f) {
                TextUtils textUtils = TextUtils.INSTANCE;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(this.this$0.getString(R.string.ml_parse_media));
                sb2.append(' ');
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format("%.02f", Arrays.copyOf(new Object[]{Boxing.boxFloat(this.$parsing)}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                sb2.append(format);
                sb2.append('%');
                String sb3 = sb2.toString();
                StringBuilder sb4 = new StringBuilder();
                sb4.append(this.$done);
                sb4.append('/');
                sb4.append(this.$scheduled);
                str = textUtils.separatedStringArgs(sb3, sb4.toString());
            } else {
                str = this.this$0.getString(R.string.ml_parse_media);
                Intrinsics.checkNotNullExpressionValue(str, "getString(...)");
            }
            if (!CoroutineScopeKt.isActive(coroutineScope)) {
                return "";
            }
            if (this.this$0.lastNotificationTime == -1) {
                return "";
            }
            try {
                try {
                    ExtensionsKt.startForegroundCompat(this.this$0, 43, NotificationHelper.INSTANCE.createScanNotification(this.this$0.getApplicationContext(), str, this.this$0.scanPaused, this.$scheduled, this.$done), 1);
                    return str;
                } catch (Exception e) {
                    if (Build.VERSION.SDK_INT < 31 || !AppUtils$$ExternalSyntheticApiModelOutline0.m$1((Object) e)) {
                        return str;
                    }
                    Log.w("MediaParsingService", "ForegroundServiceStartNotAllowedException caught!");
                    return str;
                }
            } catch (IllegalArgumentException unused) {
                return str;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
