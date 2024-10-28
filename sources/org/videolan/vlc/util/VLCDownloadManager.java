package org.videolan.vlc.util;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ProcessLifecycleOwner;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableDeferred;
import org.videolan.resources.AppContextProvider;
import org.videolan.resources.util.ExtensionsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.dialogs.SubtitleItem;
import org.videolan.vlc.repository.ExternalSubRepository;

@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H@¢\u0006\u0002\u0010\u0011J\u0018\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\u0015H\u0002J.\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000eH@¢\u0006\u0002\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u001c\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00050\u001b2\u0006\u0010\u001d\u001a\u00020\u0014H\u0002J \u0010\u001e\u001a\u0004\u0018\u00010\u00052\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H@¢\u0006\u0002\u0010\u0011J\u0010\u0010\u001f\u001a\u00020\f2\u0006\u0010 \u001a\u00020!H\u0016J\u001a\u0010\"\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00152\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\u0010\u0010%\u001a\u00020\f2\u0006\u0010 \u001a\u00020!H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lorg/videolan/vlc/util/VLCDownloadManager;", "Landroid/content/BroadcastReceiver;", "Landroidx/lifecycle/DefaultLifecycleObserver;", "()V", "defaultSubsDirectory", "", "dlDeferred", "Lkotlinx/coroutines/CompletableDeferred;", "Lorg/videolan/vlc/util/SubDlResult;", "downloadManager", "Landroid/app/DownloadManager;", "download", "", "context", "Landroidx/fragment/app/FragmentActivity;", "subtitleItem", "Lorg/videolan/vlc/gui/dialogs/SubtitleItem;", "(Landroidx/fragment/app/FragmentActivity;Lorg/videolan/vlc/gui/dialogs/SubtitleItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "downloadFailed", "id", "", "Landroid/content/Context;", "downloadSuccessful", "localUri", "(JLorg/videolan/vlc/gui/dialogs/SubtitleItem;Ljava/lang/String;Landroidx/fragment/app/FragmentActivity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDownloadPath", "getDownloadState", "Lkotlin/Pair;", "", "downloadId", "getFinalDirectory", "onDestroy", "owner", "Landroidx/lifecycle/LifecycleOwner;", "onReceive", "intent", "Landroid/content/Intent;", "onStart", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VLCDownloadManager.kt */
public final class VLCDownloadManager extends BroadcastReceiver implements DefaultLifecycleObserver {
    public static final VLCDownloadManager INSTANCE;
    private static String defaultSubsDirectory;
    private static CompletableDeferred<SubDlResult> dlDeferred;
    /* access modifiers changed from: private */
    public static final DownloadManager downloadManager;

    public /* synthetic */ void onCreate(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onCreate(this, lifecycleOwner);
    }

    public /* synthetic */ void onPause(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onPause(this, lifecycleOwner);
    }

    public /* synthetic */ void onResume(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onResume(this, lifecycleOwner);
    }

    public /* synthetic */ void onStop(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onStop(this, lifecycleOwner);
    }

    private VLCDownloadManager() {
    }

    static {
        VLCDownloadManager vLCDownloadManager = new VLCDownloadManager();
        INSTANCE = vLCDownloadManager;
        Object systemService = ContextCompat.getSystemService(AppContextProvider.INSTANCE.getAppContext(), DownloadManager.class);
        Intrinsics.checkNotNull(systemService);
        downloadManager = (DownloadManager) systemService;
        ProcessLifecycleOwner.Companion.get().getLifecycle().addObserver(vLCDownloadManager);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0007, code lost:
        r0 = r6.getLongExtra("extra_download_id", 0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onReceive(android.content.Context r5, android.content.Intent r6) {
        /*
            r4 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            if (r6 == 0) goto L_0x0055
            java.lang.String r0 = "extra_download_id"
            r1 = 0
            long r0 = r6.getLongExtra(r0, r1)
            org.videolan.vlc.repository.ExternalSubRepository$Companion r6 = org.videolan.vlc.repository.ExternalSubRepository.Companion
            java.lang.Object r5 = r6.getInstance(r5)
            org.videolan.vlc.repository.ExternalSubRepository r5 = (org.videolan.vlc.repository.ExternalSubRepository) r5
            org.videolan.vlc.gui.dialogs.SubtitleItem r5 = r5.getDownloadingSubtitle(r0)
            if (r5 == 0) goto L_0x0055
            org.videolan.vlc.util.VLCDownloadManager r6 = INSTANCE
            kotlin.Pair r6 = r6.getDownloadState(r0)
            java.lang.Object r2 = r6.component1()
            java.lang.Number r2 = (java.lang.Number) r2
            int r2 = r2.intValue()
            java.lang.Object r6 = r6.component2()
            java.lang.String r6 = (java.lang.String) r6
            r3 = 8
            if (r2 == r3) goto L_0x0049
            r5 = 16
            if (r2 == r5) goto L_0x003c
            return
        L_0x003c:
            kotlinx.coroutines.CompletableDeferred<org.videolan.vlc.util.SubDlResult> r5 = dlDeferred
            if (r5 == 0) goto L_0x0055
            org.videolan.vlc.util.SubDlFailure r6 = new org.videolan.vlc.util.SubDlFailure
            r6.<init>(r0)
            r5.complete(r6)
            goto L_0x0055
        L_0x0049:
            kotlinx.coroutines.CompletableDeferred<org.videolan.vlc.util.SubDlResult> r2 = dlDeferred
            if (r2 == 0) goto L_0x0055
            org.videolan.vlc.util.SubDlSuccess r3 = new org.videolan.vlc.util.SubDlSuccess
            r3.<init>(r0, r5, r6)
            r2.complete(r3)
        L_0x0055:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.util.VLCDownloadManager.onReceive(android.content.Context, android.content.Intent):void");
    }

    public void onStart(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
        Context applicationContext = AppContextProvider.INSTANCE.getAppContext().getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        ExtensionsKt.registerReceiverCompat(applicationContext, this, new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"), true);
    }

    public void onDestroy(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
        ((ExternalSubRepository) ExternalSubRepository.Companion.getInstance(AppContextProvider.INSTANCE.getAppContext())).getDownloadingSubtitles().observeForever(new VLCDownloadManager$sam$androidx_lifecycle_Observer$0(VLCDownloadManager$onDestroy$1.INSTANCE));
        AppContextProvider.INSTANCE.getAppContext().getApplicationContext().unregisterReceiver(this);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00af  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00bb  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object download(androidx.fragment.app.FragmentActivity r11, org.videolan.vlc.gui.dialogs.SubtitleItem r12, kotlin.coroutines.Continuation<? super kotlin.Unit> r13) {
        /*
            r10 = this;
            boolean r0 = r13 instanceof org.videolan.vlc.util.VLCDownloadManager$download$1
            if (r0 == 0) goto L_0x0014
            r0 = r13
            org.videolan.vlc.util.VLCDownloadManager$download$1 r0 = (org.videolan.vlc.util.VLCDownloadManager$download$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.util.VLCDownloadManager$download$1 r0 = new org.videolan.vlc.util.VLCDownloadManager$download$1
            r0.<init>(r10, r13)
        L_0x0019:
            r7 = r0
            java.lang.Object r13 = r7.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r2 = 2
            r3 = 1
            r4 = 0
            if (r1 == 0) goto L_0x0046
            if (r1 == r3) goto L_0x0038
            if (r1 != r2) goto L_0x0030
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x00dd
        L_0x0030:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x0038:
            java.lang.Object r11 = r7.L$1
            androidx.fragment.app.FragmentActivity r11 = (androidx.fragment.app.FragmentActivity) r11
            java.lang.Object r12 = r7.L$0
            org.videolan.vlc.util.VLCDownloadManager r12 = (org.videolan.vlc.util.VLCDownloadManager) r12
            kotlin.ResultKt.throwOnFailure(r13)
            r6 = r11
            r1 = r12
            goto L_0x00a9
        L_0x0046:
            kotlin.ResultKt.throwOnFailure(r13)
            android.app.DownloadManager$Request r13 = new android.app.DownloadManager$Request
            java.lang.String r1 = r12.getZipDownloadLink()
            android.net.Uri r1 = android.net.Uri.parse(r1)
            r13.<init>(r1)
            java.lang.String r1 = r12.getMovieReleaseName()
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r13.setDescription(r1)
            android.content.res.Resources r1 = r11.getResources()
            int r5 = org.videolan.vlc.R.string.download_subtitle_title
            java.lang.String r1 = r1.getString(r5)
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r13.setTitle(r1)
            r1 = r11
            android.content.Context r1 = (android.content.Context) r1
            java.lang.String r5 = r10.getDownloadPath(r12)
            java.lang.String r6 = ""
            r13.setDestinationInExternalFilesDir(r1, r5, r6)
            android.app.DownloadManager r1 = downloadManager
            long r5 = r1.enqueue(r13)
            kotlinx.coroutines.CompletableDeferred r13 = kotlinx.coroutines.CompletableDeferredKt.CompletableDeferred$default(r4, r3, r4)
            dlDeferred = r13
            org.videolan.vlc.repository.ExternalSubRepository$Companion r1 = org.videolan.vlc.repository.ExternalSubRepository.Companion
            android.content.Context r8 = r11.getApplicationContext()
            java.lang.String r9 = "getApplicationContext(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r9)
            java.lang.Object r1 = r1.getInstance(r8)
            org.videolan.vlc.repository.ExternalSubRepository r1 = (org.videolan.vlc.repository.ExternalSubRepository) r1
            r1.addDownloadingItem(r5, r12)
            r7.L$0 = r10
            r7.L$1 = r11
            r7.label = r3
            java.lang.Object r13 = r13.await(r7)
            if (r13 != r0) goto L_0x00a7
            return r0
        L_0x00a7:
            r1 = r10
            r6 = r11
        L_0x00a9:
            org.videolan.vlc.util.SubDlResult r13 = (org.videolan.vlc.util.SubDlResult) r13
            boolean r11 = r13 instanceof org.videolan.vlc.util.SubDlFailure
            if (r11 == 0) goto L_0x00bb
            org.videolan.vlc.util.SubDlFailure r13 = (org.videolan.vlc.util.SubDlFailure) r13
            long r11 = r13.getId()
            android.content.Context r6 = (android.content.Context) r6
            r1.downloadFailed(r11, r6)
            goto L_0x00e0
        L_0x00bb:
            boolean r11 = r13 instanceof org.videolan.vlc.util.SubDlSuccess
            if (r11 == 0) goto L_0x00e0
            org.videolan.vlc.util.SubDlSuccess r13 = (org.videolan.vlc.util.SubDlSuccess) r13
            long r11 = r13.getId()
            org.videolan.vlc.gui.dialogs.SubtitleItem r5 = r13.getSubtitleItem()
            java.lang.String r13 = r13.getLocalUri()
            r7.L$0 = r4
            r7.L$1 = r4
            r7.label = r2
            r2 = r11
            r4 = r5
            r5 = r13
            java.lang.Object r11 = r1.downloadSuccessful(r2, r4, r5, r6, r7)
            if (r11 != r0) goto L_0x00dd
            return r0
        L_0x00dd:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L_0x00e0:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.util.VLCDownloadManager.download(androidx.fragment.app.FragmentActivity, org.videolan.vlc.gui.dialogs.SubtitleItem, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x008f  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00c3  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x015f A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object downloadSuccessful(long r23, org.videolan.vlc.gui.dialogs.SubtitleItem r25, java.lang.String r26, androidx.fragment.app.FragmentActivity r27, kotlin.coroutines.Continuation<? super kotlin.Unit> r28) {
        /*
            r22 = this;
            r0 = r22
            r1 = r25
            r2 = r27
            r3 = r28
            boolean r4 = r3 instanceof org.videolan.vlc.util.VLCDownloadManager$downloadSuccessful$1
            if (r4 == 0) goto L_0x001c
            r4 = r3
            org.videolan.vlc.util.VLCDownloadManager$downloadSuccessful$1 r4 = (org.videolan.vlc.util.VLCDownloadManager$downloadSuccessful$1) r4
            int r5 = r4.label
            r6 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r5 & r6
            if (r5 == 0) goto L_0x001c
            int r3 = r4.label
            int r3 = r3 - r6
            r4.label = r3
            goto L_0x0021
        L_0x001c:
            org.videolan.vlc.util.VLCDownloadManager$downloadSuccessful$1 r4 = new org.videolan.vlc.util.VLCDownloadManager$downloadSuccessful$1
            r4.<init>(r0, r3)
        L_0x0021:
            java.lang.Object r3 = r4.result
            java.lang.Object r5 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r6 = r4.label
            r7 = 3
            r8 = 2
            r9 = 1
            if (r6 == 0) goto L_0x0070
            if (r6 == r9) goto L_0x0053
            if (r6 == r8) goto L_0x0041
            if (r6 != r7) goto L_0x0039
            kotlin.ResultKt.throwOnFailure(r3)
            goto L_0x0160
        L_0x0039:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x0041:
            long r1 = r4.J$0
            java.lang.Object r6 = r4.L$2
            androidx.fragment.app.FragmentActivity r6 = (androidx.fragment.app.FragmentActivity) r6
            java.lang.Object r8 = r4.L$1
            java.lang.String r8 = (java.lang.String) r8
            java.lang.Object r10 = r4.L$0
            org.videolan.vlc.gui.dialogs.SubtitleItem r10 = (org.videolan.vlc.gui.dialogs.SubtitleItem) r10
            kotlin.ResultKt.throwOnFailure(r3)
            goto L_0x00aa
        L_0x0053:
            long r1 = r4.J$0
            java.lang.Object r6 = r4.L$2
            androidx.fragment.app.FragmentActivity r6 = (androidx.fragment.app.FragmentActivity) r6
            java.lang.Object r10 = r4.L$1
            java.lang.String r10 = (java.lang.String) r10
            java.lang.Object r11 = r4.L$0
            org.videolan.vlc.gui.dialogs.SubtitleItem r11 = (org.videolan.vlc.gui.dialogs.SubtitleItem) r11
            kotlin.ResultKt.throwOnFailure(r3)
            r19 = r6
            r6 = r3
            r3 = r10
            r20 = r1
            r2 = r19
            r1 = r11
            r10 = r20
            goto L_0x0088
        L_0x0070:
            kotlin.ResultKt.throwOnFailure(r3)
            r4.L$0 = r1
            r3 = r26
            r4.L$1 = r3
            r4.L$2 = r2
            r10 = r23
            r4.J$0 = r10
            r4.label = r9
            java.lang.Object r6 = r0.getFinalDirectory(r2, r1, r4)
            if (r6 != r5) goto L_0x0088
            return r5
        L_0x0088:
            java.lang.String r6 = (java.lang.String) r6
            if (r6 != 0) goto L_0x008f
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        L_0x008f:
            org.videolan.vlc.util.FileUtils r12 = org.videolan.vlc.util.FileUtils.INSTANCE
            r4.L$0 = r1
            r4.L$1 = r3
            r4.L$2 = r2
            r4.J$0 = r10
            r4.label = r8
            java.lang.Object r6 = r12.unpackZip(r3, r6, r4)
            if (r6 != r5) goto L_0x00a2
            return r5
        L_0x00a2:
            r8 = r3
            r3 = r6
            r6 = r2
            r19 = r10
            r10 = r1
            r1 = r19
        L_0x00aa:
            java.util.ArrayList r3 = (java.util.ArrayList) r3
            org.videolan.vlc.repository.ExternalSubRepository$Companion r11 = org.videolan.vlc.repository.ExternalSubRepository.Companion
            java.lang.Object r11 = r11.getInstance(r6)
            org.videolan.vlc.repository.ExternalSubRepository r11 = (org.videolan.vlc.repository.ExternalSubRepository) r11
            r11.removeDownloadingItem(r1)
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            java.util.Iterator r1 = r3.iterator()
        L_0x00bd:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0143
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            java.util.HashSet<java.lang.String> r3 = org.videolan.libvlc.util.Extensions.SUBTITLES
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r12 = "."
            r11.<init>(r12)
            r12 = r2
            java.lang.CharSequence r12 = (java.lang.CharSequence) r12
            char[] r13 = new char[r9]
            r14 = 46
            r15 = 0
            r13[r15] = r14
            r14 = 6
            r16 = 0
            r17 = 0
            r18 = 0
            r23 = r12
            r24 = r13
            r25 = r17
            r26 = r18
            r27 = r14
            r28 = r16
            java.util.List r12 = kotlin.text.StringsKt.split$default((java.lang.CharSequence) r23, (char[]) r24, (boolean) r25, (int) r26, (int) r27, (java.lang.Object) r28)
            java.lang.Object r12 = kotlin.collections.CollectionsKt.last(r12)
            java.lang.String r12 = (java.lang.String) r12
            r11.append(r12)
            java.lang.String r11 = r11.toString()
            boolean r3 = r3.contains(r11)
            if (r3 == 0) goto L_0x0135
            org.videolan.vlc.repository.ExternalSubRepository$Companion r3 = org.videolan.vlc.repository.ExternalSubRepository.Companion
            java.lang.Object r3 = r3.getInstance(r6)
            org.videolan.vlc.repository.ExternalSubRepository r3 = (org.videolan.vlc.repository.ExternalSubRepository) r3
            java.lang.String r11 = r10.getIdSubtitle()
            android.net.Uri r12 = r10.getMediaUri()
            java.lang.String r12 = r12.getPath()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r12)
            java.lang.String r13 = r10.getSubLanguageID()
            java.lang.String r14 = r10.getMovieReleaseName()
            r23 = r3
            r24 = r11
            r25 = r2
            r26 = r12
            r27 = r13
            r28 = r14
            r23.saveDownloadedSubtitle(r24, r25, r26, r27, r28)
            goto L_0x00bd
        L_0x0135:
            r2 = r6
            android.content.Context r2 = (android.content.Context) r2
            int r3 = org.videolan.vlc.R.string.subtitles_download_failed
            android.widget.Toast r2 = android.widget.Toast.makeText(r2, r3, r15)
            r2.show()
            goto L_0x00bd
        L_0x0143:
            kotlinx.coroutines.CoroutineDispatcher r1 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r1 = (kotlin.coroutines.CoroutineContext) r1
            org.videolan.vlc.util.VLCDownloadManager$downloadSuccessful$2$2 r2 = new org.videolan.vlc.util.VLCDownloadManager$downloadSuccessful$2$2
            r3 = 0
            r2.<init>(r8, r3)
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            r4.L$0 = r3
            r4.L$1 = r3
            r4.L$2 = r3
            r4.label = r7
            java.lang.Object r3 = kotlinx.coroutines.BuildersKt.withContext(r1, r2, r4)
            if (r3 != r5) goto L_0x0160
            return r5
        L_0x0160:
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            r3.booleanValue()
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.util.VLCDownloadManager.downloadSuccessful(long, org.videolan.vlc.gui.dialogs.SubtitleItem, java.lang.String, androidx.fragment.app.FragmentActivity, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00bf, code lost:
        if (((java.lang.Boolean) r10).booleanValue() == false) goto L_0x00c2;
     */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getFinalDirectory(androidx.fragment.app.FragmentActivity r8, org.videolan.vlc.gui.dialogs.SubtitleItem r9, kotlin.coroutines.Continuation<? super java.lang.String> r10) {
        /*
            r7 = this;
            boolean r0 = r10 instanceof org.videolan.vlc.util.VLCDownloadManager$getFinalDirectory$1
            if (r0 == 0) goto L_0x0014
            r0 = r10
            org.videolan.vlc.util.VLCDownloadManager$getFinalDirectory$1 r0 = (org.videolan.vlc.util.VLCDownloadManager$getFinalDirectory$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.util.VLCDownloadManager$getFinalDirectory$1 r0 = new org.videolan.vlc.util.VLCDownloadManager$getFinalDirectory$1
            r0.<init>(r7, r10)
        L_0x0019:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            java.lang.String r3 = "defaultSubsDirectory"
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L_0x0041
            if (r2 != r4) goto L_0x0039
            java.lang.Object r8 = r0.L$1
            java.lang.String r8 = (java.lang.String) r8
            java.lang.Object r9 = r0.L$0
            androidx.fragment.app.FragmentActivity r9 = (androidx.fragment.app.FragmentActivity) r9
            kotlin.ResultKt.throwOnFailure(r10)
            r6 = r9
            r9 = r8
            r8 = r6
            goto L_0x00b9
        L_0x0039:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0041:
            kotlin.ResultKt.throwOnFailure(r10)
            java.lang.String r10 = defaultSubsDirectory
            if (r10 != 0) goto L_0x006a
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            android.content.Context r2 = r8.getApplicationContext()
            java.io.File r2 = r2.getExternalFilesDir(r5)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            java.lang.String r2 = r2.getAbsolutePath()
            r10.append(r2)
            java.lang.String r2 = "/subtitles"
            r10.append(r2)
            java.lang.String r10 = r10.toString()
            defaultSubsDirectory = r10
        L_0x006a:
            android.net.Uri r10 = r9.getMediaUri()
            java.lang.String r10 = r10.getScheme()
            java.lang.String r2 = "file"
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r10, (java.lang.Object) r2)
            if (r10 != 0) goto L_0x0084
            java.lang.String r8 = defaultSubsDirectory
            if (r8 != 0) goto L_0x0082
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            goto L_0x0083
        L_0x0082:
            r5 = r8
        L_0x0083:
            return r5
        L_0x0084:
            android.net.Uri r9 = r9.getMediaUri()
            java.lang.String r9 = r9.getPath()
            java.lang.String r9 = org.videolan.vlc.util.FileUtilsKt.getParentFolder(r9)
            if (r9 != 0) goto L_0x009f
            java.lang.String r9 = "subs"
            java.io.File r8 = r8.getExternalFilesDir(r9)
            if (r8 == 0) goto L_0x009e
            java.lang.String r5 = r8.getAbsolutePath()
        L_0x009e:
            return r5
        L_0x009f:
            r10 = r8
            androidx.lifecycle.LifecycleOwner r10 = (androidx.lifecycle.LifecycleOwner) r10
            boolean r10 = org.videolan.tools.KotlinExtensionsKt.isStarted(r10)
            if (r10 == 0) goto L_0x00c2
            android.net.Uri r10 = android.net.Uri.parse(r9)
            r0.L$0 = r8
            r0.L$1 = r9
            r0.label = r4
            java.lang.Object r10 = org.videolan.vlc.gui.helpers.hf.WriteExternalDelegateKt.getExtWritePermission((androidx.fragment.app.FragmentActivity) r8, (android.net.Uri) r10, (kotlin.coroutines.Continuation<? super java.lang.Boolean>) r0)
            if (r10 != r1) goto L_0x00b9
            return r1
        L_0x00b9:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            if (r10 == 0) goto L_0x00c2
            goto L_0x00c3
        L_0x00c2:
            r4 = 0
        L_0x00c3:
            if (r4 == 0) goto L_0x00c7
            r5 = r9
            goto L_0x00e2
        L_0x00c7:
            android.content.Context r8 = r8.getApplicationContext()
            java.io.File r8 = r8.getExternalFilesDir(r5)
            if (r8 == 0) goto L_0x00d6
            java.lang.String r8 = r8.getAbsolutePath()
            goto L_0x00d7
        L_0x00d6:
            r8 = r5
        L_0x00d7:
            if (r8 != 0) goto L_0x00e1
            java.lang.String r8 = defaultSubsDirectory
            if (r8 != 0) goto L_0x00e1
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            goto L_0x00e2
        L_0x00e1:
            r5 = r8
        L_0x00e2:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.util.VLCDownloadManager.getFinalDirectory(androidx.fragment.app.FragmentActivity, org.videolan.vlc.gui.dialogs.SubtitleItem, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void downloadFailed(long j, Context context) {
        Toast.makeText(context, R.string.subtitles_download_failed, 0).show();
        ((ExternalSubRepository) ExternalSubRepository.Companion.getInstance(context)).removeDownloadingItem(j);
    }

    private final String getDownloadPath(SubtitleItem subtitleItem) {
        return "VLC/" + subtitleItem.getMovieReleaseName() + '_' + subtitleItem.getIdSubtitle() + ".zip";
    }

    private final Pair<Integer, String> getDownloadState(long j) {
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(new long[]{j});
        Cursor query2 = downloadManager.query(query);
        query2.moveToFirst();
        int columnIndex = query2.getColumnIndex(NotificationCompat.CATEGORY_STATUS);
        int i = columnIndex != -1 ? query2.getInt(columnIndex) : 16;
        int columnIndex2 = query2.getColumnIndex("local_uri");
        String str = "";
        String string = columnIndex2 != -1 ? query2.getString(columnIndex2) : str;
        Integer valueOf = Integer.valueOf(i);
        if (string != null) {
            str = Uri.parse(string).getPath();
            Intrinsics.checkNotNull(str);
        }
        Intrinsics.checkNotNull(str);
        return new Pair<>(valueOf, str);
    }
}
