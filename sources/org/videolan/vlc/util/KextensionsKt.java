package org.videolan.vlc.util;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.FileProvider;
import androidx.core.text.PrecomputedTextCompat;
import androidx.core.view.WindowInsetsCompat$$ExternalSyntheticApiModelOutline0;
import androidx.core.widget.TextViewCompat;
import androidx.databinding.BindingAdapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleCoroutineScope;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.WindowIdApi18$$ExternalSyntheticApiModelOutline0;
import androidx.viewpager2.widget.ViewPager2;
import io.ktor.http.ContentDisposition;
import io.ktor.server.auth.OAuth2RequestParameters;
import java.io.File;
import java.lang.ref.WeakReference;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.SecureRandom;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.text.CharsKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import org.fusesource.jansi.AnsiRenderer;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.medialibrary.MLServiceLocator;
import org.videolan.medialibrary.Tools;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.Playlist;
import org.videolan.medialibrary.interfaces.media.VideoGroup;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.resources.Constants;
import org.videolan.tools.AppScope;
import org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.SecondaryActivity;
import org.videolan.vlc.gui.browser.BaseBrowserFragmentKt;

@Metadata(d1 = {"\u0000þ\u0001\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010!\n\u0002\b\u0002\u001a\u001a\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0007\u001a\u001a\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0007\u001a\u001a\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u0007\u001a\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017\u001a\u001a\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u0007\u001a\u0018\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u0017H\u0007\u001a \u0010\u001c\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002\u001a\u0012\u0010\u001f\u001a\u00020 *\u00020!H@¢\u0006\u0002\u0010\"\u001a\f\u0010#\u001a\u00020 *\u0004\u0018\u00010$\u001a\u0010\u0010%\u001a\u00020\u0017*\b\u0012\u0004\u0012\u00020\u00120&\u001a\u0014\u0010'\u001a\u0004\u0018\u00010(*\u00020)2\u0006\u0010*\u001a\u00020+\u001a\u001c\u0010,\u001a\u0004\u0018\u00010(*\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010-\u001a\u00020\u0017\u001a\u0012\u0010.\u001a\u00020/*\u00020!2\u0006\u00100\u001a\u00020\u0001\u001a\u0012\u00101\u001a\u000202*\u00020\u000f2\u0006\u00103\u001a\u00020!\u001a\f\u00104\u001a\u00020\u0017*\u0004\u0018\u00010\u000f\u001a\f\u00105\u001a\u00020\u0017*\u0004\u0018\u00010\u000f\u001a\u001e\u00106\u001a\u0002H7\"\n\b\u0000\u00107\u0018\u0001*\u000208*\u00020(H\b¢\u0006\u0002\u00109\u001a\u001e\u00106\u001a\u0002H7\"\n\b\u0000\u00107\u0018\u0001*\u000208*\u00020:H\b¢\u0006\u0002\u0010;\u001a\u001e\u0010<\u001a\u0002H7\"\n\b\u0000\u00107\u0018\u0001*\u000208*\u00020(H\b¢\u0006\u0002\u00109\u001a\u0014\u0010=\u001a\u00020>*\u00020!2\u0006\u0010?\u001a\u00020@H\u0007\u001a\n\u0010A\u001a\u00020\u0001*\u00020\u0012\u001a\u0012\u0010B\u001a\u000202*\u00020\u000f2\u0006\u00103\u001a\u00020!\u001a\n\u0010C\u001a\u00020\u0017*\u00020D\u001a\n\u0010E\u001a\u00020\u0017*\u00020D\u001a\u0011\u0010F\u001a\u0004\u0018\u00010\u0017*\u00020\u0001¢\u0006\u0002\u0010G\u001a\n\u0010H\u001a\u00020 *\u00020D\u001a\f\u0010I\u001a\u00020 *\u0004\u0018\u00010J\u001a\f\u0010K\u001a\u00020 *\u0004\u0018\u00010J\u001a\u001e\u0010L\u001a\u00020M\"\u0004\b\u0000\u00107*\b\u0012\u0004\u0012\u0002H70N2\u0006\u0010\u0005\u001a\u00020O\u001ad\u0010P\u001a\b\u0012\u0004\u0012\u0002HR0Q\"\u0004\b\u0000\u0010S\"\u0004\b\u0001\u0010R*\u00020\u00062\f\u0010T\u001a\b\u0012\u0004\u0012\u0002HS0Q23\u0010U\u001a/\b\u0001\u0012\u0015\u0012\u0013\u0018\u0001HS¢\u0006\f\bW\u0012\b\b0\u0012\u0004\b\b(X\u0012\n\u0012\b\u0012\u0004\u0012\u0002HR0Y\u0012\u0006\u0012\u0004\u0018\u00010/0V¢\u0006\u0002\u0010Z\u001a\n\u0010[\u001a\u00020\u0017*\u00020\u0017\u001a\u001c\u0010\\\u001a\u00020]*\u0006\u0012\u0002\b\u00030^2\f\u0010_\u001a\b\u0012\u0004\u0012\u00020\u000b0`\u001a\n\u0010a\u001a\u00020b*\u00020b\u001a\u0016\u0010c\u001a\u0004\u0018\u00010\u0001*\u0004\u0018\u00010\u00012\u0006\u0010d\u001a\u00020\u0017\u001a\u0012\u0010e\u001a\u00020 *\u00020\u0001H@¢\u0006\u0002\u0010f\u001a\u001a\u0010g\u001a\u00020\u000b*\u00020h2\u0006\u0010i\u001a\u00020JH@¢\u0006\u0002\u0010j\u001a\u0012\u0010g\u001a\u00020\u000b*\u00020:2\u0006\u0010k\u001a\u00020l\u001a\u001a\u0010g\u001a\u00020\u000b*\u00020:2\u0006\u0010m\u001a\u00020\u00012\u0006\u0010n\u001a\u00020\u0001\u001a\u0018\u0010g\u001a\u00020M*\u00020:2\f\u0010o\u001a\b\u0012\u0004\u0012\u00020J0&\u001a\u0012\u0010p\u001a\u00020\u000b*\u00020(2\u0006\u0010i\u001a\u00020J\u001a\u0014\u0010q\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010r\u001a\u00020\u0001\u001a\n\u0010s\u001a\u00020\u0017*\u00020\u0017\u001a\u0018\u0010t\u001a\b\u0012\u0004\u0012\u00020J0u*\b\u0012\u0004\u0012\u00020J0&H\u0007\u001a\n\u0010v\u001a\u00020 *\u00020\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006w"}, d2 = {"fileReplacementMarker", "", "folderReplacementMarker", "missingReplacementMarker", "presentReplacementMarker", "scope", "Lkotlinx/coroutines/CoroutineScope;", "Landroid/view/View;", "getScope", "(Landroid/view/View;)Lkotlinx/coroutines/CoroutineScope;", "asyncText", "", "view", "Landroid/widget/TextView;", "text", "", "asyncTextItem", "item", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "browserDescription", "description", "generateResolutionClass", "width", "", "height", "presenceDescription", "setLayoutMarginTop", "dimen", "setTextAsync", "params", "Landroidx/core/text/PrecomputedTextCompat$Params;", "awaitMedialibraryStarted", "", "Landroid/content/Context;", "(Landroid/content/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "canExpand", "Lorg/videolan/libvlc/Media;", "determineMaxNbOfDigits", "", "findCurrentFragment", "Landroidx/fragment/app/Fragment;", "Landroidx/viewpager2/widget/ViewPager2;", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "findFragmentAt", "position", "getAppSystemService", "", "name", "getDescriptionSpan", "Landroid/text/SpannableString;", "context", "getFilesNumber", "getFolderNumber", "getModel", "T", "Landroidx/lifecycle/ViewModel;", "(Landroidx/fragment/app/Fragment;)Landroidx/lifecycle/ViewModel;", "Landroidx/fragment/app/FragmentActivity;", "(Landroidx/fragment/app/FragmentActivity;)Landroidx/lifecycle/ViewModel;", "getModelWithActivity", "getPendingIntent", "Landroid/app/PendingIntent;", "iPlay", "Landroid/content/Intent;", "getPresenceDescription", "getPresenceDescriptionSpan", "getScreenHeight", "Landroid/app/Activity;", "getScreenWidth", "getStartingNumber", "(Ljava/lang/String;)Ljava/lang/Integer;", "hasNotch", "isBrowserMedia", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "isMedia", "launchWhenStarted", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/lifecycle/LifecycleCoroutineScope;", "map", "Landroidx/lifecycle/LiveData;", "Y", "X", "source", "f", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "value", "Lkotlin/coroutines/Continuation;", "(Lkotlinx/coroutines/CoroutineScope;Landroidx/lifecycle/LiveData;Lkotlin/jvm/functions/Function2;)Landroidx/lifecycle/LiveData;", "numberOfDigits", "onAnyChange", "Landroidx/recyclerview/widget/RecyclerView$AdapterDataObserver;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "listener", "Lkotlin/Function0;", "random", "", "sanitizeStringForAlphaCompare", "nbOfDigits", "scanAllowed", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "share", "Landroidx/appcompat/app/AppCompatActivity;", "media", "(Landroidx/appcompat/app/AppCompatActivity;Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "file", "Ljava/io/File;", "title", "content", "medias", "showParentFolder", "slugify", "replacement", "toPixel", "updateWithMLMeta", "", "validateLocation", "vlc-android_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: Kextensions.kt */
public final class KextensionsKt {
    public static final String fileReplacementMarker = "*§*";
    public static final String folderReplacementMarker = "§*§";
    public static final String missingReplacementMarker = "*§*";
    public static final String presentReplacementMarker = "§*§";

    public static final boolean validateLocation(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        if (!new Regex("\\w+://.+").matches(str)) {
            str = "file://" + str;
        }
        Locale locale = Locale.ENGLISH;
        Intrinsics.checkNotNullExpressionValue(locale, "ENGLISH");
        String lowerCase = str.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        if (!StringsKt.startsWith$default(lowerCase, "file://", false, 2, (Object) null)) {
            return true;
        }
        try {
            if (!new File(new URI(str)).isFile()) {
                return false;
            }
            return true;
        } catch (IllegalArgumentException | URISyntaxException unused) {
        }
    }

    public static final /* synthetic */ <T extends ViewModel> T getModelWithActivity(Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        FragmentActivity requireActivity = fragment.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        ViewModelProvider viewModelProvider = new ViewModelProvider(requireActivity);
        Intrinsics.reifiedOperationMarker(4, "T");
        Class<ViewModel> cls = ViewModel.class;
        Class cls2 = cls;
        return viewModelProvider.get(cls);
    }

    public static final /* synthetic */ <T extends ViewModel> T getModel(Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        ViewModelProvider viewModelProvider = new ViewModelProvider(fragment);
        Intrinsics.reifiedOperationMarker(4, "T");
        Class<ViewModel> cls = ViewModel.class;
        Class cls2 = cls;
        return viewModelProvider.get(cls);
    }

    public static final /* synthetic */ <T extends ViewModel> T getModel(FragmentActivity fragmentActivity) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "<this>");
        ViewModelProvider viewModelProvider = new ViewModelProvider(fragmentActivity);
        Intrinsics.reifiedOperationMarker(4, "T");
        Class<ViewModel> cls = ViewModel.class;
        Class cls2 = cls;
        return viewModelProvider.get(cls);
    }

    public static final boolean canExpand(Media media) {
        return media != null && (media.getType() == 2 || media.getType() == 5);
    }

    public static final void share(FragmentActivity fragmentActivity, File file) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "<this>");
        Intrinsics.checkNotNullParameter(file, "file");
        Intent intent = new Intent("android.intent.action.SEND");
        File file2 = new File(file.getPath());
        if (KotlinExtensionsKt.isStarted(fragmentActivity)) {
            intent.setType("*/*");
            intent.putExtra("android.intent.extra.STREAM", FileProvider.getUriForFile(fragmentActivity, fragmentActivity.getPackageName() + ".provider", file2));
            intent.putExtra("android.intent.extra.SUBJECT", file.getName());
            intent.putExtra("android.intent.extra.TEXT", fragmentActivity.getString(R.string.share_message, new Object[]{file.getName()}));
            fragmentActivity.startActivity(Intent.createChooser(intent, fragmentActivity.getString(R.string.share_file, new Object[]{file.getName()})));
        }
    }

    public static final void share(FragmentActivity fragmentActivity, String str, String str2) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "<this>");
        Intrinsics.checkNotNullParameter(str, "title");
        Intrinsics.checkNotNullParameter(str2, "content");
        Intent intent = new Intent("android.intent.action.SEND");
        if (KotlinExtensionsKt.isStarted(fragmentActivity)) {
            intent.setType(HttpPostBodyUtil.DEFAULT_TEXT_CONTENT_TYPE);
            intent.putExtra("android.intent.extra.SUBJECT", str);
            intent.putExtra("android.intent.extra.TEXT", str2);
            fragmentActivity.startActivity(Intent.createChooser(intent, fragmentActivity.getString(R.string.share_file, new Object[]{str})));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object share(androidx.appcompat.app.AppCompatActivity r7, org.videolan.medialibrary.interfaces.media.MediaWrapper r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            boolean r0 = r9 instanceof org.videolan.vlc.util.KextensionsKt$share$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            org.videolan.vlc.util.KextensionsKt$share$1 r0 = (org.videolan.vlc.util.KextensionsKt$share$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.util.KextensionsKt$share$1 r0 = new org.videolan.vlc.util.KextensionsKt$share$1
            r0.<init>(r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0044
            if (r2 != r3) goto L_0x003c
            java.lang.Object r7 = r0.L$3
            java.io.File r7 = (java.io.File) r7
            java.lang.Object r8 = r0.L$2
            android.content.Intent r8 = (android.content.Intent) r8
            java.lang.Object r1 = r0.L$1
            org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r1
            java.lang.Object r0 = r0.L$0
            androidx.appcompat.app.AppCompatActivity r0 = (androidx.appcompat.app.AppCompatActivity) r0
            kotlin.ResultKt.throwOnFailure(r9)
            r2 = r7
            r7 = r0
            goto L_0x007d
        L_0x003c:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0044:
            kotlin.ResultKt.throwOnFailure(r9)
            android.content.Intent r9 = new android.content.Intent
            java.lang.String r2 = "android.intent.action.SEND"
            r9.<init>(r2)
            java.io.File r2 = new java.io.File
            android.net.Uri r4 = r8.getUri()
            java.lang.String r4 = r4.getPath()
            r2.<init>(r4)
            kotlinx.coroutines.CoroutineDispatcher r4 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r4 = (kotlin.coroutines.CoroutineContext) r4
            org.videolan.vlc.util.KextensionsKt$share$validFile$1 r5 = new org.videolan.vlc.util.KextensionsKt$share$validFile$1
            r6 = 0
            r5.<init>(r2, r6)
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
            r0.L$0 = r7
            r0.L$1 = r8
            r0.L$2 = r9
            r0.L$3 = r2
            r0.label = r3
            java.lang.Object r0 = kotlinx.coroutines.BuildersKt.withContext(r4, r5, r0)
            if (r0 != r1) goto L_0x007a
            return r1
        L_0x007a:
            r1 = r8
            r8 = r9
            r9 = r0
        L_0x007d:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            r0 = r7
            androidx.lifecycle.LifecycleOwner r0 = (androidx.lifecycle.LifecycleOwner) r0
            boolean r0 = org.videolan.tools.KotlinExtensionsKt.isStarted(r0)
            if (r0 == 0) goto L_0x0104
            r0 = 0
            if (r9 == 0) goto L_0x00f4
            int r9 = r1.getType()
            if (r9 != 0) goto L_0x0098
            java.lang.String r9 = "video/*"
            goto L_0x009a
        L_0x0098:
            java.lang.String r9 = "audio/*"
        L_0x009a:
            r8.setType(r9)
            r9 = r7
            android.content.Context r9 = (android.content.Context) r9
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = r7.getPackageName()
            r4.append(r5)
            java.lang.String r5 = ".provider"
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            android.net.Uri r9 = androidx.core.content.FileProvider.getUriForFile(r9, r4, r2)
            android.os.Parcelable r9 = (android.os.Parcelable) r9
            java.lang.String r2 = "android.intent.extra.STREAM"
            r8.putExtra(r2, r9)
            java.lang.String r9 = "android.intent.extra.SUBJECT"
            java.lang.String r2 = r1.getTitle()
            r8.putExtra(r9, r2)
            int r9 = org.videolan.vlc.R.string.share_message
            java.lang.String r2 = r1.getTitle()
            java.lang.Object[] r4 = new java.lang.Object[r3]
            r4[r0] = r2
            java.lang.String r9 = r7.getString(r9, r4)
            java.lang.String r2 = "android.intent.extra.TEXT"
            r8.putExtra(r2, r9)
            int r9 = org.videolan.vlc.R.string.share_file
            java.lang.String r1 = r1.getTitle()
            java.lang.Object[] r2 = new java.lang.Object[r3]
            r2[r0] = r1
            java.lang.String r9 = r7.getString(r9, r2)
            java.lang.CharSequence r9 = (java.lang.CharSequence) r9
            android.content.Intent r8 = android.content.Intent.createChooser(r8, r9)
            r7.startActivity(r8)
            goto L_0x0104
        L_0x00f4:
            r8 = 16908290(0x1020002, float:2.3877235E-38)
            android.view.View r7 = r7.findViewById(r8)
            int r8 = org.videolan.vlc.R.string.invalid_file
            com.google.android.material.snackbar.Snackbar r7 = com.google.android.material.snackbar.Snackbar.make((android.view.View) r7, (int) r8, (int) r0)
            r7.show()
        L_0x0104:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.util.KextensionsKt.share(androidx.appcompat.app.AppCompatActivity, org.videolan.medialibrary.interfaces.media.MediaWrapper, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final Job share(FragmentActivity fragmentActivity, List<? extends MediaWrapper> list) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "<this>");
        Intrinsics.checkNotNullParameter(list, "medias");
        return BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(fragmentActivity), (CoroutineContext) null, (CoroutineStart) null, new KextensionsKt$share$2(list, fragmentActivity, (Continuation<? super KextensionsKt$share$2>) null), 3, (Object) null);
    }

    public static final boolean isMedia(MediaWrapper mediaWrapper) {
        return mediaWrapper != null && (mediaWrapper.getType() == 1 || mediaWrapper.getType() == 0);
    }

    public static final boolean isBrowserMedia(MediaWrapper mediaWrapper) {
        return mediaWrapper != null && (isMedia(mediaWrapper) || mediaWrapper.getType() == 3 || mediaWrapper.getType() == 5);
    }

    public static final Object getAppSystemService(Context context, String str) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Object systemService = context.getApplicationContext().getSystemService(str);
        Intrinsics.checkNotNull(systemService);
        return systemService;
    }

    public static final long random(long j) {
        return (long) (new SecureRandom().nextFloat() * ((float) j));
    }

    public static final List<MediaWrapper> updateWithMLMeta(List<? extends MediaWrapper> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Medialibrary instance = Medialibrary.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(...)");
        List<MediaWrapper> arrayList = new ArrayList<>();
        for (MediaWrapper mediaWrapper : list) {
            MediaWrapper findMedia = instance.findMedia(mediaWrapper);
            if (findMedia.getType() == -1) {
                findMedia.setType(mediaWrapper.getType());
            }
            Intrinsics.checkNotNullExpressionValue(findMedia, "apply(...)");
            arrayList.add(findMedia);
        }
        return arrayList;
    }

    public static final Object scanAllowed(String str, Continuation<? super Boolean> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new KextensionsKt$scanAllowed$2(str, (Continuation<? super KextensionsKt$scanAllowed$2>) null), continuation);
    }

    public static final <X, Y> LiveData<Y> map(CoroutineScope coroutineScope, LiveData<X> liveData, Function2<? super X, ? super Continuation<? super Y>, ? extends Object> function2) {
        Intrinsics.checkNotNullParameter(coroutineScope, "<this>");
        Intrinsics.checkNotNullParameter(liveData, "source");
        Intrinsics.checkNotNullParameter(function2, "f");
        MediatorLiveData mediatorLiveData = new MediatorLiveData();
        mediatorLiveData.addSource(liveData, new KextensionsKt$sam$androidx_lifecycle_Observer$0(new KextensionsKt$map$1$1(coroutineScope, mediatorLiveData, function2)));
        return mediatorLiveData;
    }

    @BindingAdapter(requireAll = false, value = {"app:asyncText"})
    public static final void asyncText(TextView textView, CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(textView, "view");
        if (charSequence == null || charSequence.length() == 0) {
            textView.setVisibility(8);
            return;
        }
        textView.setVisibility(0);
        PrecomputedTextCompat.Params textMetricsParams = TextViewCompat.getTextMetricsParams(textView);
        Intrinsics.checkNotNullExpressionValue(textMetricsParams, "getTextMetricsParams(...)");
        setTextAsync(textView, charSequence, textMetricsParams);
    }

    @BindingAdapter(requireAll = false, value = {"app:asyncText"})
    public static final void asyncTextItem(TextView textView, MediaLibraryItem mediaLibraryItem) {
        String str;
        Intrinsics.checkNotNullParameter(textView, "view");
        if (mediaLibraryItem == null) {
            textView.setVisibility(8);
            return;
        }
        if (mediaLibraryItem instanceof Playlist) {
            Playlist playlist = (Playlist) mediaLibraryItem;
            if (playlist.getDuration() != 0) {
                String millisToString = Tools.millisToString(playlist.getDuration());
                TextUtils textUtils = TextUtils.INSTANCE;
                String string = textView.getContext().getString(R.string.track_number, new Object[]{Integer.valueOf(playlist.getTracksCount())});
                if (playlist.getNbDurationUnknown() > 0) {
                    millisToString = millisToString + '+';
                }
                str = textUtils.separatedStringArgs(string, millisToString);
            } else {
                str = textView.getContext().getString(R.string.track_number, new Object[]{Integer.valueOf(playlist.getTracksCount())});
                Intrinsics.checkNotNullExpressionValue(str, "getString(...)");
            }
        } else {
            str = mediaLibraryItem.getDescription();
        }
        CharSequence charSequence = str;
        if (charSequence == null || charSequence.length() == 0) {
            textView.setVisibility(8);
            return;
        }
        textView.setVisibility(0);
        PrecomputedTextCompat.Params textMetricsParams = TextViewCompat.getTextMetricsParams(textView);
        Intrinsics.checkNotNullExpressionValue(textMetricsParams, "getTextMetricsParams(...)");
        Intrinsics.checkNotNull(str);
        setTextAsync(textView, charSequence, textMetricsParams);
    }

    @BindingAdapter({"layoutMarginTop"})
    public static final void setLayoutMarginTop(View view, int i) {
        Intrinsics.checkNotNullParameter(view, "view");
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        marginLayoutParams.topMargin = i;
        view.setLayoutParams(marginLayoutParams);
    }

    private static final void setTextAsync(TextView textView, CharSequence charSequence, PrecomputedTextCompat.Params params) {
        Job unused = BuildersKt__Builders_commonKt.launch$default(AppScope.INSTANCE, Dispatchers.getDefault(), (CoroutineStart) null, new KextensionsKt$setTextAsync$1(charSequence, params, new WeakReference(textView), (Continuation<? super KextensionsKt$setTextAsync$1>) null), 2, (Object) null);
    }

    @BindingAdapter(requireAll = false, value = {"app:browserDescription"})
    public static final void browserDescription(TextView textView, String str) {
        SpannableString spannableString;
        Intrinsics.checkNotNullParameter(textView, "view");
        AppCompatTextView appCompatTextView = (AppCompatTextView) textView;
        if (str != null) {
            Context context = appCompatTextView.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            spannableString = getDescriptionSpan(str, context);
        } else {
            spannableString = null;
        }
        appCompatTextView.setText(spannableString);
    }

    public static final SpannableString getDescriptionSpan(CharSequence charSequence, Context context) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        SpannableString spannableString = new SpannableString(charSequence);
        if (StringsKt.contains$default(charSequence, (CharSequence) "§*§", false, 2, (Object) null)) {
            CharSequence charSequence2 = charSequence;
            spannableString.setSpan(new ImageSpan(context, R.drawable.ic_emoji_folder, 1), StringsKt.indexOf$default(charSequence2, "§*§", 0, false, 6, (Object) null), StringsKt.indexOf$default(charSequence2, "§*§", 0, false, 6, (Object) null) + 3, 33);
        }
        if (StringsKt.contains$default(charSequence, (CharSequence) "*§*", false, 2, (Object) null)) {
            spannableString.setSpan(new ImageSpan(context, R.drawable.ic_emoji_file, 1), StringsKt.indexOf$default(charSequence, "*§*", 0, false, 6, (Object) null), StringsKt.indexOf$default(charSequence, "*§*", 0, false, 6, (Object) null) + 3, 33);
        }
        return spannableString;
    }

    public static final int getFolderNumber(CharSequence charSequence) {
        if (charSequence == null || StringsKt.isBlank(charSequence) || !StringsKt.contains$default(charSequence, (CharSequence) "§*§", false, 2, (Object) null)) {
            return 0;
        }
        return Integer.parseInt((String) StringsKt.split$default((CharSequence) StringsKt.trim((CharSequence) new Regex("[^0-9 ]").replace(charSequence, "")).toString(), new String[]{AnsiRenderer.CODE_TEXT_SEPARATOR}, false, 0, 6, (Object) null).get(0));
    }

    public static final int getFilesNumber(CharSequence charSequence) {
        if (charSequence == null || StringsKt.isBlank(charSequence) || !StringsKt.contains$default(charSequence, (CharSequence) "*§*", false, 2, (Object) null)) {
            return 0;
        }
        List split$default = StringsKt.split$default((CharSequence) StringsKt.trim((CharSequence) new Regex("[^0-9 ]").replace(charSequence, "")).toString(), new String[]{AnsiRenderer.CODE_TEXT_SEPARATOR}, false, 0, 6, (Object) null);
        return Integer.parseInt((String) split$default.get(split$default.size() - 1));
    }

    public static /* synthetic */ String slugify$default(String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = "-";
        }
        return slugify(str, str2);
    }

    public static final String slugify(String str, String str2) {
        String str3;
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(str2, "replacement");
        if (Build.VERSION.SDK_INT >= 29) {
            str3 = AppUtils$$ExternalSyntheticApiModelOutline0.m("Any-Latin; Lower; Latin-ASCII").transliterate(str);
        } else {
            str3 = Normalizer.normalize(str, Normalizer.Form.NFD);
        }
        Intrinsics.checkNotNull(str3);
        Regex regex = new Regex("[^a-zA-Z0-9\\s]+");
        return new Regex("\\s+").replace((CharSequence) StringsKt.trim((CharSequence) regex.replace((CharSequence) str3, "")).toString(), str2);
    }

    public static final String getPresenceDescription(MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "<this>");
        if (!(mediaLibraryItem instanceof VideoGroup)) {
            return "";
        }
        TextUtils textUtils = TextUtils.INSTANCE;
        StringBuilder sb = new StringBuilder();
        VideoGroup videoGroup = (VideoGroup) mediaLibraryItem;
        sb.append(videoGroup.getPresentCount());
        sb.append(" §*§");
        String sb2 = sb.toString();
        return textUtils.separatedStringArgs(sb2, (videoGroup.mediaCount() - videoGroup.getPresentCount()) + " *§*");
    }

    @BindingAdapter(requireAll = false, value = {"app:presenceDescription"})
    public static final void presenceDescription(TextView textView, String str) {
        SpannableString spannableString;
        Intrinsics.checkNotNullParameter(textView, "view");
        AppCompatTextView appCompatTextView = (AppCompatTextView) textView;
        if (str != null) {
            Context context = appCompatTextView.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            spannableString = getPresenceDescriptionSpan(str, context);
        } else {
            spannableString = null;
        }
        appCompatTextView.setText(spannableString);
    }

    public static final SpannableString getPresenceDescriptionSpan(CharSequence charSequence, Context context) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        SpannableString spannableString = new SpannableString(charSequence);
        if (StringsKt.contains$default(charSequence, (CharSequence) "§*§", false, 2, (Object) null)) {
            CharSequence charSequence2 = charSequence;
            spannableString.setSpan(new ImageSpan(context, R.drawable.ic_emoji_media_present, 2), StringsKt.indexOf$default(charSequence2, "§*§", 0, false, 6, (Object) null), StringsKt.indexOf$default(charSequence2, "§*§", 0, false, 6, (Object) null) + 3, 33);
        }
        if (StringsKt.contains$default(charSequence, (CharSequence) "*§*", false, 2, (Object) null)) {
            CharSequence charSequence3 = charSequence;
            spannableString.setSpan(new ImageSpan(context, R.drawable.ic_emoji_media_absent, 2), StringsKt.indexOf$default(charSequence3, "*§*", 0, false, 6, (Object) null), StringsKt.indexOf$default(charSequence3, "*§*", 0, false, 6, (Object) null) + 3, 33);
        }
        return spannableString;
    }

    public static final int toPixel(int i) {
        return MathKt.roundToInt(((float) i) * (((float) Resources.getSystem().getDisplayMetrics().densityDpi) / 160.0f));
    }

    public static final int getScreenWidth(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static final int getScreenHeight(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static final boolean hasNotch(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        return Build.VERSION.SDK_INT >= 28 && WindowInsetsCompat$$ExternalSyntheticApiModelOutline0.m(WindowIdApi18$$ExternalSyntheticApiModelOutline0.m(activity.getWindow().getDecorView())) != null;
    }

    public static final PendingIntent getPendingIntent(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(intent, "iPlay");
        if (AndroidUtil.isOOrLater) {
            PendingIntent m = PendingIntent.getForegroundService(context.getApplicationContext(), 0, intent, 201326592);
            Intrinsics.checkNotNullExpressionValue(m, "getForegroundService(...)");
            return m;
        }
        PendingIntent service = PendingIntent.getService(context.getApplicationContext(), 0, intent, 201326592);
        Intrinsics.checkNotNullExpressionValue(service, "getService(...)");
        return service;
    }

    public static final RecyclerView.AdapterDataObserver onAnyChange(RecyclerView.Adapter<?> adapter, Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(adapter, "<this>");
        Intrinsics.checkNotNullParameter(function0, "listener");
        RecyclerView.AdapterDataObserver kextensionsKt$onAnyChange$dataObserver$1 = new KextensionsKt$onAnyChange$dataObserver$1(function0);
        adapter.registerAdapterDataObserver(kextensionsKt$onAnyChange$dataObserver$1);
        return kextensionsKt$onAnyChange$dataObserver$1;
    }

    public static final String generateResolutionClass(int i, int i2) {
        if (i <= 0 || i2 <= 0) {
            return null;
        }
        int min = Math.min(i2, i);
        int max = Math.max(i2, i);
        if (min < 4320) {
            double d = (double) max;
            if (d < 7680.0d) {
                if (min >= 2160 || d >= 3840.0d) {
                    return "4K";
                }
                if (min >= 1440 || d >= 2560.0d) {
                    return "1440p";
                }
                if (min >= 1080 || d >= 1920.0d) {
                    return "1080p";
                }
                if (min >= 720 || d >= 1280.0d) {
                    return "720p";
                }
                return "SD";
            }
        }
        return "8K";
    }

    public static final CoroutineScope getScope(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Context context = view.getContext();
        if (context instanceof CoroutineScope) {
            return (CoroutineScope) context;
        }
        if (context instanceof LifecycleOwner) {
            return LifecycleOwnerKt.getLifecycleScope((LifecycleOwner) context);
        }
        return AppScope.INSTANCE;
    }

    public static final <T> Job launchWhenStarted(Flow<? extends T> flow, LifecycleCoroutineScope lifecycleCoroutineScope) {
        Intrinsics.checkNotNullParameter(flow, "<this>");
        Intrinsics.checkNotNullParameter(lifecycleCoroutineScope, OAuth2RequestParameters.Scope);
        return lifecycleCoroutineScope.launchWhenStarted(new KextensionsKt$launchWhenStarted$1(flow, (Continuation<? super KextensionsKt$launchWhenStarted$1>) null));
    }

    public static final String sanitizeStringForAlphaCompare(String str, int i) {
        CharSequence charSequence = str;
        if (charSequence == null || charSequence.length() == 0 || !Character.isDigit(StringsKt.first(charSequence))) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        for (int i3 = 0; i3 < str.length(); i3++) {
            char charAt = str.charAt(i3);
            if (!Character.isDigit(charAt) || CharsKt.digitToInt(charAt) != 0) {
                break;
            }
            i2++;
        }
        int i4 = i - i2;
        Integer startingNumber = getStartingNumber(str);
        int numberOfDigits = i4 - (startingNumber != null ? numberOfDigits(startingNumber.intValue()) : 0);
        for (int i5 = 0; i5 < numberOfDigits; i5++) {
            sb.append(Constants.GROUP_VIDEOS_FOLDER);
        }
        sb.append(str);
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }

    public static final int numberOfDigits(int i) {
        if (-9 > i || i >= 10) {
            return 1 + numberOfDigits(i / 10);
        }
        return 1;
    }

    public static final Integer getStartingNumber(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        try {
            StringBuilder sb = new StringBuilder();
            int length = str.length();
            for (int i = 0; i < length; i++) {
                char charAt = str.charAt(i);
                if (!Character.isDigit(charAt)) {
                    break;
                }
                if (sb.length() != 0 || CharsKt.digitToInt(charAt) != 0) {
                    sb.append(charAt);
                }
            }
            String sb2 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
            return Integer.valueOf(Integer.parseInt(sb2));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    public static final int determineMaxNbOfDigits(List<? extends MediaLibraryItem> list) {
        int i;
        String fileName;
        Intrinsics.checkNotNullParameter(list, "<this>");
        int i2 = 0;
        for (MediaLibraryItem mediaLibraryItem : list) {
            MediaWrapper mediaWrapper = mediaLibraryItem instanceof MediaWrapper ? (MediaWrapper) mediaLibraryItem : null;
            if (!(mediaWrapper == null || (fileName = mediaWrapper.getFileName()) == null)) {
                Intrinsics.checkNotNull(fileName);
                Integer startingNumber = getStartingNumber(fileName);
                if (startingNumber != null) {
                    i = numberOfDigits(startingNumber.intValue());
                    i2 = Math.max(i, i2);
                }
            }
            i = 0;
            i2 = Math.max(i, i2);
        }
        return i2;
    }

    public static final void showParentFolder(Fragment fragment, MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        Intrinsics.checkNotNullParameter(mediaWrapper, "media");
        MediaWrapper abstractMediaWrapper = MLServiceLocator.getAbstractMediaWrapper(KotlinExtensionsKt.retrieveParent(mediaWrapper.getUri()));
        abstractMediaWrapper.setType(3);
        Intent intent = new Intent(fragment.requireActivity().getApplicationContext(), SecondaryActivity.class);
        intent.putExtra(BaseBrowserFragmentKt.KEY_MEDIA, abstractMediaWrapper);
        intent.putExtra(SecondaryActivity.KEY_FRAGMENT, SecondaryActivity.FILE_BROWSER);
        fragment.startActivity(intent);
    }

    public static final Fragment findCurrentFragment(ViewPager2 viewPager2, FragmentManager fragmentManager) {
        Intrinsics.checkNotNullParameter(viewPager2, "<this>");
        Intrinsics.checkNotNullParameter(fragmentManager, "fragmentManager");
        return fragmentManager.findFragmentByTag("f" + viewPager2.getCurrentItem());
    }

    public static final Fragment findFragmentAt(ViewPager2 viewPager2, FragmentManager fragmentManager, int i) {
        Intrinsics.checkNotNullParameter(viewPager2, "<this>");
        Intrinsics.checkNotNullParameter(fragmentManager, "fragmentManager");
        return fragmentManager.findFragmentByTag("f" + i);
    }

    public static final Object awaitMedialibraryStarted(Context context, Continuation<? super Boolean> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new KextensionsKt$awaitMedialibraryStarted$$inlined$getFromMl$1(context, (Continuation) null), continuation);
    }
}
