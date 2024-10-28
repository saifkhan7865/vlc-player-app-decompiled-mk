package org.videolan.vlc.webserver.gui.remoteaccess;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import com.google.android.material.snackbar.Snackbar;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.vlc.util.KextensionsKt;
import org.videolan.vlc.util.UrlUtils;
import org.videolan.vlc.webserver.R;
import org.videolan.vlc.webserver.RemoteAccessServer;
import org.videolan.vlc.webserver.ServerStatus;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "serverStatus", "Lorg/videolan/vlc/webserver/ServerStatus;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: RemoteAccessShareActivity.kt */
final class RemoteAccessShareActivity$onCreate$1 extends Lambda implements Function1<ServerStatus, Unit> {
    final /* synthetic */ RemoteAccessServer $remoteAccessServer;
    final /* synthetic */ RemoteAccessShareActivity this$0;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: RemoteAccessShareActivity.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(15:0|1|2|3|4|5|6|7|8|9|10|11|12|13|15) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0034 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
        static {
            /*
                org.videolan.vlc.webserver.ServerStatus[] r0 = org.videolan.vlc.webserver.ServerStatus.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                org.videolan.vlc.webserver.ServerStatus r1 = org.videolan.vlc.webserver.ServerStatus.NOT_INIT     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                org.videolan.vlc.webserver.ServerStatus r1 = org.videolan.vlc.webserver.ServerStatus.STARTED     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                org.videolan.vlc.webserver.ServerStatus r1 = org.videolan.vlc.webserver.ServerStatus.STOPPED     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                org.videolan.vlc.webserver.ServerStatus r1 = org.videolan.vlc.webserver.ServerStatus.CONNECTING     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                org.videolan.vlc.webserver.ServerStatus r1 = org.videolan.vlc.webserver.ServerStatus.ERROR     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                org.videolan.vlc.webserver.ServerStatus r1 = org.videolan.vlc.webserver.ServerStatus.STOPPING     // Catch:{ NoSuchFieldError -> 0x003d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003d }
            L_0x003d:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.webserver.gui.remoteaccess.RemoteAccessShareActivity$onCreate$1.WhenMappings.<clinit>():void");
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteAccessShareActivity$onCreate$1(RemoteAccessShareActivity remoteAccessShareActivity, RemoteAccessServer remoteAccessServer) {
        super(1);
        this.this$0 = remoteAccessShareActivity;
        this.$remoteAccessServer = remoteAccessServer;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ServerStatus) obj);
        return Unit.INSTANCE;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0023, code lost:
        r5 = "";
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void invoke(org.videolan.vlc.webserver.ServerStatus r17) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            org.videolan.vlc.webserver.gui.remoteaccess.RemoteAccessShareActivity r2 = r0.this$0
            org.videolan.vlc.webserver.databinding.RemoteAccessShareActivityBinding r2 = r2.binding
            java.lang.String r4 = "binding"
            if (r2 != 0) goto L_0x0012
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r2 = 0
        L_0x0012:
            android.widget.TextView r2 = r2.serverStatus
            if (r1 != 0) goto L_0x0018
            r5 = -1
            goto L_0x0020
        L_0x0018:
            int[] r5 = org.videolan.vlc.webserver.gui.remoteaccess.RemoteAccessShareActivity$onCreate$1.WhenMappings.$EnumSwitchMapping$0
            int r6 = r17.ordinal()
            r5 = r5[r6]
        L_0x0020:
            switch(r5) {
                case 1: goto L_0x005f;
                case 2: goto L_0x0054;
                case 3: goto L_0x0049;
                case 4: goto L_0x003e;
                case 5: goto L_0x0033;
                case 6: goto L_0x0028;
                default: goto L_0x0023;
            }
        L_0x0023:
            java.lang.String r5 = ""
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            goto L_0x0069
        L_0x0028:
            org.videolan.vlc.webserver.gui.remoteaccess.RemoteAccessShareActivity r5 = r0.this$0
            int r6 = org.videolan.vlc.webserver.R.string.remote_access_notification_stopping
            java.lang.String r5 = r5.getString(r6)
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            goto L_0x0069
        L_0x0033:
            org.videolan.vlc.webserver.gui.remoteaccess.RemoteAccessShareActivity r5 = r0.this$0
            int r6 = org.videolan.vlc.webserver.R.string.remote_access_notification_error
            java.lang.String r5 = r5.getString(r6)
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            goto L_0x0069
        L_0x003e:
            org.videolan.vlc.webserver.gui.remoteaccess.RemoteAccessShareActivity r5 = r0.this$0
            int r6 = org.videolan.vlc.webserver.R.string.remote_access_notification_connecting
            java.lang.String r5 = r5.getString(r6)
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            goto L_0x0069
        L_0x0049:
            org.videolan.vlc.webserver.gui.remoteaccess.RemoteAccessShareActivity r5 = r0.this$0
            int r6 = org.videolan.vlc.webserver.R.string.remote_access_notification_stopped
            java.lang.String r5 = r5.getString(r6)
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            goto L_0x0069
        L_0x0054:
            org.videolan.vlc.webserver.gui.remoteaccess.RemoteAccessShareActivity r5 = r0.this$0
            int r6 = org.videolan.vlc.webserver.R.string.remote_access_active
            java.lang.String r5 = r5.getString(r6)
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            goto L_0x0069
        L_0x005f:
            org.videolan.vlc.webserver.gui.remoteaccess.RemoteAccessShareActivity r5 = r0.this$0
            int r6 = org.videolan.vlc.webserver.R.string.remote_access_notification_not_init
            java.lang.String r5 = r5.getString(r6)
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
        L_0x0069:
            r2.setText(r5)
            r2 = 5
            android.view.View[] r5 = new android.view.View[r2]
            org.videolan.vlc.webserver.gui.remoteaccess.RemoteAccessShareActivity r6 = r0.this$0
            org.videolan.vlc.webserver.databinding.RemoteAccessShareActivityBinding r6 = r6.binding
            if (r6 != 0) goto L_0x007b
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r6 = 0
        L_0x007b:
            android.widget.TextView r6 = r6.connectionTitle
            java.lang.String r7 = "connectionTitle"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)
            r7 = 0
            r5[r7] = r6
            org.videolan.vlc.webserver.gui.remoteaccess.RemoteAccessShareActivity r6 = r0.this$0
            org.videolan.vlc.webserver.databinding.RemoteAccessShareActivityBinding r6 = r6.binding
            if (r6 != 0) goto L_0x0091
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r6 = 0
        L_0x0091:
            androidx.recyclerview.widget.RecyclerView r6 = r6.connectionList
            java.lang.String r8 = "connectionList"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r8)
            r8 = 1
            r5[r8] = r6
            org.videolan.vlc.webserver.gui.remoteaccess.RemoteAccessShareActivity r6 = r0.this$0
            org.videolan.vlc.webserver.databinding.RemoteAccessShareActivityBinding r6 = r6.binding
            if (r6 != 0) goto L_0x00a7
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r6 = 0
        L_0x00a7:
            android.widget.TextView r6 = r6.linksTitle
            java.lang.String r9 = "linksTitle"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r9)
            r9 = 2
            r5[r9] = r6
            org.videolan.vlc.webserver.gui.remoteaccess.RemoteAccessShareActivity r6 = r0.this$0
            org.videolan.vlc.webserver.databinding.RemoteAccessShareActivityBinding r6 = r6.binding
            if (r6 != 0) goto L_0x00bd
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r6 = 0
        L_0x00bd:
            android.widget.ImageView r6 = r6.remoteAccessQrCode
            java.lang.String r10 = "remoteAccessQrCode"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r10)
            r10 = 3
            r5[r10] = r6
            org.videolan.vlc.webserver.gui.remoteaccess.RemoteAccessShareActivity r6 = r0.this$0
            org.videolan.vlc.webserver.databinding.RemoteAccessShareActivityBinding r6 = r6.binding
            if (r6 != 0) goto L_0x00d3
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r6 = 0
        L_0x00d3:
            androidx.gridlayout.widget.GridLayout r6 = r6.linksGrid
            java.lang.String r10 = "linksGrid"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r10)
            r10 = 4
            r5[r10] = r6
            r6 = 0
        L_0x00de:
            if (r6 >= r2) goto L_0x00f0
            r10 = r5[r6]
            org.videolan.vlc.webserver.ServerStatus r11 = org.videolan.vlc.webserver.ServerStatus.STARTED
            if (r1 != r11) goto L_0x00ea
            org.videolan.tools.KotlinExtensionsKt.setVisible(r10)
            goto L_0x00ed
        L_0x00ea:
            org.videolan.tools.KotlinExtensionsKt.setGone(r10)
        L_0x00ed:
            int r6 = r6 + 1
            goto L_0x00de
        L_0x00f0:
            org.videolan.vlc.webserver.gui.remoteaccess.RemoteAccessShareActivity r2 = r0.this$0
            org.videolan.vlc.webserver.databinding.RemoteAccessShareActivityBinding r2 = r2.binding
            if (r2 != 0) goto L_0x00fc
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r2 = 0
        L_0x00fc:
            android.widget.Button r2 = r2.statusButton
            org.videolan.vlc.webserver.ServerStatus[] r5 = new org.videolan.vlc.webserver.ServerStatus[r9]
            org.videolan.vlc.webserver.ServerStatus r6 = org.videolan.vlc.webserver.ServerStatus.STARTED
            r5[r7] = r6
            org.videolan.vlc.webserver.ServerStatus r6 = org.videolan.vlc.webserver.ServerStatus.STOPPED
            r5[r8] = r6
            boolean r5 = kotlin.collections.ArraysKt.contains((T[]) r5, r1)
            r2.setEnabled(r5)
            org.videolan.vlc.webserver.gui.remoteaccess.RemoteAccessShareActivity r2 = r0.this$0
            org.videolan.vlc.webserver.databinding.RemoteAccessShareActivityBinding r2 = r2.binding
            if (r2 != 0) goto L_0x011b
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r2 = 0
        L_0x011b:
            android.widget.Button r2 = r2.statusButton
            org.videolan.vlc.webserver.gui.remoteaccess.RemoteAccessShareActivity r5 = r0.this$0
            org.videolan.vlc.webserver.ServerStatus r6 = org.videolan.vlc.webserver.ServerStatus.STARTED
            if (r1 != r6) goto L_0x0126
            int r1 = org.videolan.vlc.webserver.R.string.stop
            goto L_0x0128
        L_0x0126:
            int r1 = org.videolan.vlc.webserver.R.string.start
        L_0x0128:
            java.lang.String r1 = r5.getString(r1)
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r2.setText(r1)
            org.videolan.vlc.webserver.gui.remoteaccess.RemoteAccessShareActivity r1 = r0.this$0
            org.videolan.vlc.webserver.databinding.RemoteAccessShareActivityBinding r1 = r1.binding
            if (r1 != 0) goto L_0x013d
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r1 = 0
        L_0x013d:
            androidx.gridlayout.widget.GridLayout r1 = r1.linksGrid
            r1.removeAllViews()
            org.videolan.vlc.webserver.RemoteAccessServer r1 = r0.$remoteAccessServer
            java.util.List r1 = r1.getServerAddresses()
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            org.videolan.vlc.webserver.gui.remoteaccess.RemoteAccessShareActivity r2 = r0.this$0
            java.util.Iterator r1 = r1.iterator()
        L_0x0150:
            boolean r5 = r1.hasNext()
            if (r5 == 0) goto L_0x0299
            java.lang.Object r5 = r1.next()
            java.lang.String r5 = (java.lang.String) r5
            android.widget.TextView r6 = new android.widget.TextView
            r7 = r2
            android.content.Context r7 = (android.content.Context) r7
            r6.<init>(r7)
            r9 = r5
            java.lang.CharSequence r9 = (java.lang.CharSequence) r9
            r6.setText(r9)
            android.widget.ImageView r9 = new android.widget.ImageView
            r9.<init>(r7)
            int r10 = org.videolan.vlc.webserver.R.drawable.ic_copy
            android.graphics.drawable.Drawable r10 = androidx.core.content.ContextCompat.getDrawable(r7, r10)
            r9.setImageDrawable(r10)
            org.videolan.vlc.webserver.gui.remoteaccess.RemoteAccessShareActivity$onCreate$1$$ExternalSyntheticLambda0 r10 = new org.videolan.vlc.webserver.gui.remoteaccess.RemoteAccessShareActivity$onCreate$1$$ExternalSyntheticLambda0
            r10.<init>(r2, r5)
            r9.setOnClickListener(r10)
            android.util.TypedValue r10 = new android.util.TypedValue
            r10.<init>()
            android.content.res.Resources$Theme r11 = r2.getTheme()
            int r12 = org.videolan.vlc.webserver.R.attr.selectableItemBackgroundBorderless
            r11.resolveAttribute(r12, r10, r8)
            int r11 = r10.resourceId
            r9.setBackgroundResource(r11)
            r11 = 8
            int r12 = org.videolan.tools.KotlinExtensionsKt.getDp(r11)
            int r13 = org.videolan.tools.KotlinExtensionsKt.getDp(r11)
            int r14 = org.videolan.tools.KotlinExtensionsKt.getDp(r11)
            int r15 = org.videolan.tools.KotlinExtensionsKt.getDp(r11)
            r9.setPadding(r12, r13, r14, r15)
            android.widget.ImageView r12 = new android.widget.ImageView
            r12.<init>(r7)
            int r13 = org.videolan.vlc.webserver.R.drawable.ic_share
            android.graphics.drawable.Drawable r13 = androidx.core.content.ContextCompat.getDrawable(r7, r13)
            r12.setImageDrawable(r13)
            org.videolan.vlc.webserver.gui.remoteaccess.RemoteAccessShareActivity$onCreate$1$$ExternalSyntheticLambda1 r13 = new org.videolan.vlc.webserver.gui.remoteaccess.RemoteAccessShareActivity$onCreate$1$$ExternalSyntheticLambda1
            r13.<init>(r2, r5)
            r12.setOnClickListener(r13)
            int r13 = r10.resourceId
            r12.setBackgroundResource(r13)
            int r13 = org.videolan.tools.KotlinExtensionsKt.getDp(r11)
            int r14 = org.videolan.tools.KotlinExtensionsKt.getDp(r11)
            int r15 = org.videolan.tools.KotlinExtensionsKt.getDp(r11)
            int r3 = org.videolan.tools.KotlinExtensionsKt.getDp(r11)
            r12.setPadding(r13, r14, r15, r3)
            android.widget.ImageView r3 = new android.widget.ImageView
            r3.<init>(r7)
            int r13 = org.videolan.vlc.webserver.R.drawable.ic_qr_code
            android.graphics.drawable.Drawable r7 = androidx.core.content.ContextCompat.getDrawable(r7, r13)
            r3.setImageDrawable(r7)
            int r7 = r10.resourceId
            r3.setBackgroundResource(r7)
            int r7 = org.videolan.tools.KotlinExtensionsKt.getDp(r11)
            int r10 = org.videolan.tools.KotlinExtensionsKt.getDp(r11)
            int r13 = org.videolan.tools.KotlinExtensionsKt.getDp(r11)
            int r11 = org.videolan.tools.KotlinExtensionsKt.getDp(r11)
            r3.setPadding(r7, r10, r13, r11)
            org.videolan.vlc.webserver.gui.remoteaccess.RemoteAccessShareActivity$onCreate$1$$ExternalSyntheticLambda2 r7 = new org.videolan.vlc.webserver.gui.remoteaccess.RemoteAccessShareActivity$onCreate$1$$ExternalSyntheticLambda2
            r7.<init>(r2, r5)
            r3.setOnClickListener(r7)
            org.videolan.vlc.webserver.databinding.RemoteAccessShareActivityBinding r5 = r2.binding
            if (r5 != 0) goto L_0x020e
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r5 = 0
        L_0x020e:
            androidx.gridlayout.widget.GridLayout r5 = r5.linksGrid
            r7 = r6
            android.view.View r7 = (android.view.View) r7
            r5.addView(r7)
            org.videolan.vlc.webserver.databinding.RemoteAccessShareActivityBinding r5 = r2.binding
            if (r5 != 0) goto L_0x0220
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r5 = 0
        L_0x0220:
            androidx.gridlayout.widget.GridLayout r5 = r5.linksGrid
            r7 = r3
            android.view.View r7 = (android.view.View) r7
            r5.addView(r7)
            org.videolan.vlc.webserver.databinding.RemoteAccessShareActivityBinding r5 = r2.binding
            if (r5 != 0) goto L_0x0232
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r5 = 0
        L_0x0232:
            androidx.gridlayout.widget.GridLayout r5 = r5.linksGrid
            r7 = r12
            android.view.View r7 = (android.view.View) r7
            r5.addView(r7)
            org.videolan.vlc.webserver.databinding.RemoteAccessShareActivityBinding r5 = r2.binding
            if (r5 != 0) goto L_0x0244
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r5 = 0
        L_0x0244:
            androidx.gridlayout.widget.GridLayout r5 = r5.linksGrid
            r7 = r9
            android.view.View r7 = (android.view.View) r7
            r5.addView(r7)
            android.view.ViewGroup$LayoutParams r3 = r3.getLayoutParams()
            java.lang.String r5 = "null cannot be cast to non-null type androidx.gridlayout.widget.GridLayout.LayoutParams"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3, r5)
            androidx.gridlayout.widget.GridLayout$LayoutParams r3 = (androidx.gridlayout.widget.GridLayout.LayoutParams) r3
            r7 = 16
            r3.setGravity(r7)
            android.view.ViewGroup$LayoutParams r3 = r9.getLayoutParams()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3, r5)
            androidx.gridlayout.widget.GridLayout$LayoutParams r3 = (androidx.gridlayout.widget.GridLayout.LayoutParams) r3
            r3.setGravity(r7)
            android.view.ViewGroup$LayoutParams r3 = r12.getLayoutParams()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3, r5)
            androidx.gridlayout.widget.GridLayout$LayoutParams r3 = (androidx.gridlayout.widget.GridLayout.LayoutParams) r3
            r3.setGravity(r7)
            android.view.ViewGroup$LayoutParams r3 = r6.getLayoutParams()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3, r5)
            androidx.gridlayout.widget.GridLayout$LayoutParams r3 = (androidx.gridlayout.widget.GridLayout.LayoutParams) r3
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r9 = 1065353216(0x3f800000, float:1.0)
            androidx.gridlayout.widget.GridLayout$Spec r5 = androidx.gridlayout.widget.GridLayout.spec((int) r5, (float) r9)
            r3.columnSpec = r5
            r5 = 48
            int r5 = org.videolan.tools.KotlinExtensionsKt.getDp(r5)
            r3.height = r5
            r6.setGravity(r7)
            android.view.ViewGroup$LayoutParams r3 = (android.view.ViewGroup.LayoutParams) r3
            r6.setLayoutParams(r3)
            goto L_0x0150
        L_0x0299:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.webserver.gui.remoteaccess.RemoteAccessShareActivity$onCreate$1.invoke(org.videolan.vlc.webserver.ServerStatus):void");
    }

    /* access modifiers changed from: private */
    public static final void invoke$lambda$4$lambda$1(RemoteAccessShareActivity remoteAccessShareActivity, String str, View view) {
        Intrinsics.checkNotNullParameter(remoteAccessShareActivity, "this$0");
        Intrinsics.checkNotNullParameter(str, "$link");
        KotlinExtensionsKt.copy(remoteAccessShareActivity, "VLC for Android Remote Access link", str);
        Snackbar.make(remoteAccessShareActivity.getWindow().getDecorView().findViewById(16908290), R.string.url_copied_to_clipboard, 0).show();
    }

    /* access modifiers changed from: private */
    public static final void invoke$lambda$4$lambda$2(RemoteAccessShareActivity remoteAccessShareActivity, String str, View view) {
        Intrinsics.checkNotNullParameter(remoteAccessShareActivity, "this$0");
        Intrinsics.checkNotNullParameter(str, "$link");
        String string = remoteAccessShareActivity.getString(R.string.remote_access);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        KextensionsKt.share((FragmentActivity) remoteAccessShareActivity, string, str);
    }

    /* access modifiers changed from: private */
    public static final void invoke$lambda$4$lambda$3(RemoteAccessShareActivity remoteAccessShareActivity, String str, View view) {
        Intrinsics.checkNotNullParameter(remoteAccessShareActivity, "this$0");
        Intrinsics.checkNotNullParameter(str, "$link");
        Context context = remoteAccessShareActivity;
        ImageView imageView = new ImageView(context);
        imageView.setPadding(KotlinExtensionsKt.getDp(8), KotlinExtensionsKt.getDp(8), KotlinExtensionsKt.getDp(8), KotlinExtensionsKt.getDp(8));
        imageView.setImageBitmap(UrlUtils.INSTANCE.generateQRCode(str, 512));
        new AlertDialog.Builder(context).setTitle((CharSequence) remoteAccessShareActivity.getResources().getString(R.string.remote_access_notification, new Object[]{str})).setView((View) imageView).setPositiveButton(R.string.ok, (DialogInterface.OnClickListener) null).show();
    }
}
