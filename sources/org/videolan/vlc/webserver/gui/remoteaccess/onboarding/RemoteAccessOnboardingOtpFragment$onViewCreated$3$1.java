package org.videolan.vlc.webserver.gui.remoteaccess.onboarding;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.webserver.gui.remoteaccess.onboarding.RemoteAccessOnboardingOtpFragment$onViewCreated$3$1", f = "RemoteAccessOnboardingOtpFragment.kt", i = {}, l = {102}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: RemoteAccessOnboardingOtpFragment.kt */
final class RemoteAccessOnboardingOtpFragment$onViewCreated$3$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ AnimatorSet $accessAnims;
    final /* synthetic */ ValueAnimator $colorAnimation;
    final /* synthetic */ int $green;
    final /* synthetic */ Ref.IntRef $iteration;
    final /* synthetic */ int $orange;
    final /* synthetic */ int $red;
    int label;
    final /* synthetic */ RemoteAccessOnboardingOtpFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteAccessOnboardingOtpFragment$onViewCreated$3$1(RemoteAccessOnboardingOtpFragment remoteAccessOnboardingOtpFragment, Ref.IntRef intRef, ValueAnimator valueAnimator, int i, int i2, int i3, AnimatorSet animatorSet, Continuation<? super RemoteAccessOnboardingOtpFragment$onViewCreated$3$1> continuation) {
        super(2, continuation);
        this.this$0 = remoteAccessOnboardingOtpFragment;
        this.$iteration = intRef;
        this.$colorAnimation = valueAnimator;
        this.$orange = i;
        this.$green = i2;
        this.$red = i3;
        this.$accessAnims = animatorSet;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RemoteAccessOnboardingOtpFragment$onViewCreated$3$1(this.this$0, this.$iteration, this.$colorAnimation, this.$orange, this.$green, this.$red, this.$accessAnims, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RemoteAccessOnboardingOtpFragment$onViewCreated$3$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.webserver.gui.remoteaccess.onboarding.RemoteAccessOnboardingOtpFragment$onViewCreated$3$1$1", f = "RemoteAccessOnboardingOtpFragment.kt", i = {1, 2, 3}, l = {103, 106, 108, 110, 112, 114}, m = "invokeSuspend", n = {"text", "text", "text"}, s = {"L$0", "L$0", "L$0"})
    /* renamed from: org.videolan.vlc.webserver.gui.remoteaccess.onboarding.RemoteAccessOnboardingOtpFragment$onViewCreated$3$1$1  reason: invalid class name */
    /* compiled from: RemoteAccessOnboardingOtpFragment.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(intRef, remoteAccessOnboardingOtpFragment, valueAnimator, i2, i3, i4, animatorSet, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0056, code lost:
            if ((r4.element % 2) != 0) goto L_0x006b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0058, code lost:
            r13 = r5.deviceOTP;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x005e, code lost:
            if (r13 != null) goto L_0x0066;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0060, code lost:
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("deviceOTP");
            r13 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0066, code lost:
            r13 = r13.getText();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x006b, code lost:
            r13 = org.videolan.vlc.webserver.RemoteAccessOTP.INSTANCE.generateCode();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0073, code lost:
            r1 = r13;
            r13 = r5.browserOTP;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x007a, code lost:
            if (r13 != null) goto L_0x0080;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x007c, code lost:
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("browserOTP");
            r13 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0080, code lost:
            r13.setText(r1.subSequence(0, 1) + "   ");
            r12.L$0 = r1;
            r12.label = 2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x00a6, code lost:
            if (kotlinx.coroutines.DelayKt.delay(400, r12) != r0) goto L_0x00a9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x00a8, code lost:
            return r0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x00a9, code lost:
            r13 = r5.browserOTP;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x00af, code lost:
            if (r13 != null) goto L_0x00b5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x00b1, code lost:
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("browserOTP");
            r13 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x00b5, code lost:
            r13.setText("*" + r1.subSequence(1, 2) + "  ");
            r12.L$0 = r1;
            r12.label = 3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x00dc, code lost:
            if (kotlinx.coroutines.DelayKt.delay(400, r12) != r0) goto L_0x00df;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x00de, code lost:
            return r0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x00df, code lost:
            r13 = r5.browserOTP;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x00e5, code lost:
            if (r13 != null) goto L_0x00eb;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x00e7, code lost:
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("browserOTP");
            r13 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:0x00eb, code lost:
            r13.setText("**" + r1.subSequence(2, 3) + ' ');
            r12.L$0 = r1;
            r12.label = 4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:0x0112, code lost:
            if (kotlinx.coroutines.DelayKt.delay(400, r12) != r0) goto L_0x0115;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:0x0114, code lost:
            return r0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:38:0x0115, code lost:
            r13 = r5.browserOTP;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:0x011b, code lost:
            if (r13 != null) goto L_0x0121;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:0x011d, code lost:
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("browserOTP");
            r13 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:41:0x0121, code lost:
            r13.setText("***" + r1.subSequence(3, 4));
            r12.L$0 = null;
            r12.label = 5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:42:0x0144, code lost:
            if (kotlinx.coroutines.DelayKt.delay(400, r12) != r0) goto L_0x0147;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:43:0x0146, code lost:
            return r0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:44:0x0147, code lost:
            r13 = r5.browserOTP;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:45:0x014d, code lost:
            if (r13 != null) goto L_0x0153;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:46:0x014f, code lost:
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("browserOTP");
            r13 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:47:0x0153, code lost:
            r13.setText("****");
            r12.label = 6;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:48:0x0164, code lost:
            if (kotlinx.coroutines.DelayKt.delay(400, r12) != r0) goto L_0x0167;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:49:0x0166, code lost:
            return r0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:50:0x0167, code lost:
            r13 = r5.browserOTP;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:51:0x016d, code lost:
            if (r13 != null) goto L_0x0173;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:52:0x016f, code lost:
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("browserOTP");
            r13 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:53:0x0173, code lost:
            r13.setText("");
            r13 = r5.access;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:54:0x0180, code lost:
            if (r13 != null) goto L_0x0188;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:55:0x0182, code lost:
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException("access");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:56:0x0188, code lost:
            r9 = r13;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:57:0x0189, code lost:
            r13 = r5.requireActivity();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:58:0x0196, code lost:
            if ((r4.element % 2) != 0) goto L_0x019b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:59:0x0198, code lost:
            r0 = org.videolan.vlc.webserver.R.drawable.ic_remote_access_onboarding_verified;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:60:0x019b, code lost:
            r0 = org.videolan.vlc.webserver.R.drawable.ic_remote_access_onboarding_denied;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:61:0x019d, code lost:
            r9.setImageDrawable(androidx.core.content.ContextCompat.getDrawable(r13, r0));
            r13 = r6;
            r0 = r7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:62:0x01ad, code lost:
            if ((r4.element % 2) != 0) goto L_0x01b2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:63:0x01af, code lost:
            r1 = r8;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:64:0x01b2, code lost:
            r1 = r9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:65:0x01b4, code lost:
            r13.setIntValues(new int[]{r0, r1});
            r10.start();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:66:0x01c2, code lost:
            return kotlin.Unit.INSTANCE;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object invokeSuspend(java.lang.Object r13) {
            /*
                r12 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r12.label
                r2 = 4
                r3 = 3
                r4 = 1
                java.lang.String r5 = "browserOTP"
                r6 = 2
                r7 = 400(0x190, double:1.976E-321)
                r9 = 0
                switch(r1) {
                    case 0: goto L_0x0042;
                    case 1: goto L_0x003e;
                    case 2: goto L_0x0036;
                    case 3: goto L_0x002d;
                    case 4: goto L_0x0024;
                    case 5: goto L_0x001f;
                    case 6: goto L_0x001a;
                    default: goto L_0x0012;
                }
            L_0x0012:
                java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r13.<init>(r0)
                throw r13
            L_0x001a:
                kotlin.ResultKt.throwOnFailure(r13)
                goto L_0x0167
            L_0x001f:
                kotlin.ResultKt.throwOnFailure(r13)
                goto L_0x0147
            L_0x0024:
                java.lang.Object r1 = r12.L$0
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                kotlin.ResultKt.throwOnFailure(r13)
                goto L_0x0115
            L_0x002d:
                java.lang.Object r1 = r12.L$0
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                kotlin.ResultKt.throwOnFailure(r13)
                goto L_0x00df
            L_0x0036:
                java.lang.Object r1 = r12.L$0
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                kotlin.ResultKt.throwOnFailure(r13)
                goto L_0x00a9
            L_0x003e:
                kotlin.ResultKt.throwOnFailure(r13)
                goto L_0x0051
            L_0x0042:
                kotlin.ResultKt.throwOnFailure(r13)
                r13 = r12
                kotlin.coroutines.Continuation r13 = (kotlin.coroutines.Continuation) r13
                r12.label = r4
                java.lang.Object r13 = kotlinx.coroutines.DelayKt.delay(r7, r13)
                if (r13 != r0) goto L_0x0051
                return r0
            L_0x0051:
                kotlin.jvm.internal.Ref$IntRef r13 = r4
                int r13 = r13.element
                int r13 = r13 % r6
                if (r13 != 0) goto L_0x006b
                org.videolan.vlc.webserver.gui.remoteaccess.onboarding.RemoteAccessOnboardingOtpFragment r13 = r5
                android.widget.TextView r13 = r13.deviceOTP
                if (r13 != 0) goto L_0x0066
                java.lang.String r13 = "deviceOTP"
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r13)
                r13 = r9
            L_0x0066:
                java.lang.CharSequence r13 = r13.getText()
                goto L_0x0073
            L_0x006b:
                org.videolan.vlc.webserver.RemoteAccessOTP r13 = org.videolan.vlc.webserver.RemoteAccessOTP.INSTANCE
                java.lang.String r13 = r13.generateCode()
                java.lang.CharSequence r13 = (java.lang.CharSequence) r13
            L_0x0073:
                r1 = r13
                org.videolan.vlc.webserver.gui.remoteaccess.onboarding.RemoteAccessOnboardingOtpFragment r13 = r5
                android.widget.TextView r13 = r13.browserOTP
                if (r13 != 0) goto L_0x0080
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
                r13 = r9
            L_0x0080:
                java.lang.StringBuilder r10 = new java.lang.StringBuilder
                r10.<init>()
                r11 = 0
                java.lang.CharSequence r11 = r1.subSequence(r11, r4)
                r10.append(r11)
                java.lang.String r11 = "   "
                r10.append(r11)
                java.lang.String r10 = r10.toString()
                java.lang.CharSequence r10 = (java.lang.CharSequence) r10
                r13.setText(r10)
                r13 = r12
                kotlin.coroutines.Continuation r13 = (kotlin.coroutines.Continuation) r13
                r12.L$0 = r1
                r12.label = r6
                java.lang.Object r13 = kotlinx.coroutines.DelayKt.delay(r7, r13)
                if (r13 != r0) goto L_0x00a9
                return r0
            L_0x00a9:
                org.videolan.vlc.webserver.gui.remoteaccess.onboarding.RemoteAccessOnboardingOtpFragment r13 = r5
                android.widget.TextView r13 = r13.browserOTP
                if (r13 != 0) goto L_0x00b5
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
                r13 = r9
            L_0x00b5:
                java.lang.StringBuilder r10 = new java.lang.StringBuilder
                java.lang.String r11 = "*"
                r10.<init>(r11)
                java.lang.CharSequence r4 = r1.subSequence(r4, r6)
                r10.append(r4)
                java.lang.String r4 = "  "
                r10.append(r4)
                java.lang.String r4 = r10.toString()
                java.lang.CharSequence r4 = (java.lang.CharSequence) r4
                r13.setText(r4)
                r13 = r12
                kotlin.coroutines.Continuation r13 = (kotlin.coroutines.Continuation) r13
                r12.L$0 = r1
                r12.label = r3
                java.lang.Object r13 = kotlinx.coroutines.DelayKt.delay(r7, r13)
                if (r13 != r0) goto L_0x00df
                return r0
            L_0x00df:
                org.videolan.vlc.webserver.gui.remoteaccess.onboarding.RemoteAccessOnboardingOtpFragment r13 = r5
                android.widget.TextView r13 = r13.browserOTP
                if (r13 != 0) goto L_0x00eb
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
                r13 = r9
            L_0x00eb:
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                java.lang.String r10 = "**"
                r4.<init>(r10)
                java.lang.CharSequence r10 = r1.subSequence(r6, r3)
                r4.append(r10)
                r10 = 32
                r4.append(r10)
                java.lang.String r4 = r4.toString()
                java.lang.CharSequence r4 = (java.lang.CharSequence) r4
                r13.setText(r4)
                r13 = r12
                kotlin.coroutines.Continuation r13 = (kotlin.coroutines.Continuation) r13
                r12.L$0 = r1
                r12.label = r2
                java.lang.Object r13 = kotlinx.coroutines.DelayKt.delay(r7, r13)
                if (r13 != r0) goto L_0x0115
                return r0
            L_0x0115:
                org.videolan.vlc.webserver.gui.remoteaccess.onboarding.RemoteAccessOnboardingOtpFragment r13 = r5
                android.widget.TextView r13 = r13.browserOTP
                if (r13 != 0) goto L_0x0121
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
                r13 = r9
            L_0x0121:
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                java.lang.String r10 = "***"
                r4.<init>(r10)
                java.lang.CharSequence r1 = r1.subSequence(r3, r2)
                r4.append(r1)
                java.lang.String r1 = r4.toString()
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                r13.setText(r1)
                r13 = r12
                kotlin.coroutines.Continuation r13 = (kotlin.coroutines.Continuation) r13
                r12.L$0 = r9
                r1 = 5
                r12.label = r1
                java.lang.Object r13 = kotlinx.coroutines.DelayKt.delay(r7, r13)
                if (r13 != r0) goto L_0x0147
                return r0
            L_0x0147:
                org.videolan.vlc.webserver.gui.remoteaccess.onboarding.RemoteAccessOnboardingOtpFragment r13 = r5
                android.widget.TextView r13 = r13.browserOTP
                if (r13 != 0) goto L_0x0153
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
                r13 = r9
            L_0x0153:
                java.lang.String r1 = "****"
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                r13.setText(r1)
                r13 = r12
                kotlin.coroutines.Continuation r13 = (kotlin.coroutines.Continuation) r13
                r1 = 6
                r12.label = r1
                java.lang.Object r13 = kotlinx.coroutines.DelayKt.delay(r7, r13)
                if (r13 != r0) goto L_0x0167
                return r0
            L_0x0167:
                org.videolan.vlc.webserver.gui.remoteaccess.onboarding.RemoteAccessOnboardingOtpFragment r13 = r5
                android.widget.TextView r13 = r13.browserOTP
                if (r13 != 0) goto L_0x0173
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
                r13 = r9
            L_0x0173:
                java.lang.String r0 = ""
                java.lang.CharSequence r0 = (java.lang.CharSequence) r0
                r13.setText(r0)
                org.videolan.vlc.webserver.gui.remoteaccess.onboarding.RemoteAccessOnboardingOtpFragment r13 = r5
                android.widget.ImageView r13 = r13.access
                if (r13 != 0) goto L_0x0188
                java.lang.String r13 = "access"
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r13)
                goto L_0x0189
            L_0x0188:
                r9 = r13
            L_0x0189:
                org.videolan.vlc.webserver.gui.remoteaccess.onboarding.RemoteAccessOnboardingOtpFragment r13 = r5
                androidx.fragment.app.FragmentActivity r13 = r13.requireActivity()
                android.content.Context r13 = (android.content.Context) r13
                kotlin.jvm.internal.Ref$IntRef r0 = r4
                int r0 = r0.element
                int r0 = r0 % r6
                if (r0 != 0) goto L_0x019b
                int r0 = org.videolan.vlc.webserver.R.drawable.ic_remote_access_onboarding_verified
                goto L_0x019d
            L_0x019b:
                int r0 = org.videolan.vlc.webserver.R.drawable.ic_remote_access_onboarding_denied
            L_0x019d:
                android.graphics.drawable.Drawable r13 = androidx.core.content.ContextCompat.getDrawable(r13, r0)
                r9.setImageDrawable(r13)
                android.animation.ValueAnimator r13 = r6
                int r0 = r7
                kotlin.jvm.internal.Ref$IntRef r1 = r4
                int r1 = r1.element
                int r1 = r1 % r6
                if (r1 != 0) goto L_0x01b2
                int r1 = r8
                goto L_0x01b4
            L_0x01b2:
                int r1 = r9
            L_0x01b4:
                int[] r0 = new int[]{r0, r1}
                r13.setIntValues(r0)
                android.animation.AnimatorSet r13 = r10
                r13.start()
                kotlin.Unit r13 = kotlin.Unit.INSTANCE
                return r13
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.webserver.gui.remoteaccess.onboarding.RemoteAccessOnboardingOtpFragment$onViewCreated$3$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LifecycleOwner viewLifecycleOwner = this.this$0.getViewLifecycleOwner();
            Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
            Lifecycle.State state = Lifecycle.State.STARTED;
            final Ref.IntRef intRef = this.$iteration;
            final RemoteAccessOnboardingOtpFragment remoteAccessOnboardingOtpFragment = this.this$0;
            final ValueAnimator valueAnimator = this.$colorAnimation;
            final int i2 = this.$orange;
            final int i3 = this.$green;
            final int i4 = this.$red;
            final AnimatorSet animatorSet = this.$accessAnims;
            this.label = 1;
            if (RepeatOnLifecycleKt.repeatOnLifecycle(viewLifecycleOwner, state, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) new AnonymousClass1((Continuation<? super AnonymousClass1>) null), (Continuation<? super Unit>) this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
