package org.videolan.vlc.gui.preferences.search;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.XmlResourceParser;
import android.net.Uri;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import org.videolan.tools.Settings;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH@¢\u0006\u0002\u0010\tJ8\u0010\n\u001a*\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010\f0\u000bj\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010\f`\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0011\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u000e\u0010\u0012\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010J(\u0010\u0013\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\rH\u0002J\u001a\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00012\b\u0010\u001b\u001a\u0004\u0018\u00010\rH\u0002J(\u0010\u001c\u001a\u0012\u0012\u0004\u0012\u00020\u001d0\u000bj\b\u0012\u0004\u0012\u00020\u001d`\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u001e\u001a\u00020\u0019J*\u0010\u001c\u001a\u0012\u0012\u0004\u0012\u00020\u001d0\u000bj\b\u0012\u0004\u0012\u00020\u001d`\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0001\u0010\u001f\u001a\u00020 H\u0002J \u0010!\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020#H@¢\u0006\u0002\u0010$¨\u0006%"}, d2 = {"Lorg/videolan/vlc/gui/preferences/search/PreferenceParser;", "", "()V", "exportPreferences", "", "activity", "Landroid/app/Activity;", "dst", "Ljava/io/File;", "(Landroid/app/Activity;Ljava/io/File;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllChangedPrefs", "Ljava/util/ArrayList;", "Lkotlin/Pair;", "", "Lkotlin/collections/ArrayList;", "context", "Landroid/content/Context;", "getChangedPrefsJson", "getChangedPrefsString", "getValue", "parser", "Landroid/content/res/XmlResourceParser;", "namespace", "node", "isSame", "", "settingValue", "defaultValue", "parsePreferences", "Lorg/videolan/vlc/gui/preferences/search/PreferenceItem;", "parseUIPrefs", "id", "", "restoreSettings", "file", "Landroid/net/Uri;", "(Landroid/app/Activity;Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PreferenceParser.kt */
public final class PreferenceParser {
    public static final PreferenceParser INSTANCE = new PreferenceParser();

    private PreferenceParser() {
    }

    public static /* synthetic */ ArrayList parsePreferences$default(PreferenceParser preferenceParser, Context context, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return preferenceParser.parsePreferences(context, z);
    }

    public final ArrayList<PreferenceItem> parsePreferences(Context context, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        ArrayList<PreferenceItem> arrayList = new ArrayList<>();
        ArrayList<Number> arrayListOf = CollectionsKt.arrayListOf(Integer.valueOf(R.xml.preferences), Integer.valueOf(R.xml.preferences_adv), Integer.valueOf(R.xml.preferences_audio), Integer.valueOf(R.xml.preferences_casting), Integer.valueOf(R.xml.preferences_subtitles), Integer.valueOf(R.xml.preferences_ui), Integer.valueOf(R.xml.preferences_video), Integer.valueOf(R.xml.preferences_remote_access));
        if (z) {
            arrayListOf.add(Integer.valueOf(R.xml.preferences_video_controls));
        }
        for (Number intValue : arrayListOf) {
            arrayList.addAll(INSTANCE.parsePreferences(context, intValue.intValue()));
        }
        return arrayList;
    }

    private final ArrayList<Pair<String, Object>> getAllChangedPrefs(Context context) {
        Object value;
        ArrayList<PreferenceItem> parsePreferences = parsePreferences(context, true);
        Map<String, ?> all = ((SharedPreferences) Settings.INSTANCE.getInstance(context)).getAll();
        ArrayList<Pair<String, Object>> arrayList = new ArrayList<>();
        for (PreferenceItem preferenceItem : parsePreferences) {
            Intrinsics.checkNotNull(all);
            for (Map.Entry next : all.entrySet()) {
                if (Intrinsics.areEqual((Object) preferenceItem.getKey(), next.getKey()) && !Intrinsics.areEqual((Object) preferenceItem.getKey(), (Object) "custom_libvlc_options") && (value = next.getValue()) != null && !INSTANCE.isSame(value, preferenceItem.getDefaultValue())) {
                    arrayList.add(new Pair(preferenceItem.getKey(), value));
                }
            }
        }
        return arrayList;
    }

    private final boolean isSame(Object obj, String str) {
        if (str == null) {
            return false;
        }
        if (obj instanceof Boolean) {
            return Intrinsics.areEqual((Object) obj.toString(), (Object) str);
        }
        return Intrinsics.areEqual(obj, (Object) str);
    }

    public final String getChangedPrefsString(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        StringBuilder sb = new StringBuilder();
        for (Pair pair : INSTANCE.getAllChangedPrefs(context)) {
            sb.append("\t* " + ((String) pair.getFirst()) + " -> " + pair.getSecond() + "\r\n");
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }

    /* access modifiers changed from: private */
    public final String getChangedPrefsJson(Context context) {
        StringBuilder sb = new StringBuilder("{");
        ArrayList<Pair<String, Object>> allChangedPrefs = INSTANCE.getAllChangedPrefs(context);
        Iterator<Pair<String, Object>> it = allChangedPrefs.iterator();
        while (it.hasNext()) {
            Pair next = it.next();
            if ((next.getSecond() instanceof Boolean) || (next.getSecond() instanceof Integer) || (next.getSecond() instanceof Long)) {
                sb.append("\"" + ((String) next.getFirst()) + "\": " + next.getSecond());
            } else {
                sb.append("\"" + ((String) next.getFirst()) + "\": \"" + next.getSecond() + '\"');
            }
            if (!Intrinsics.areEqual((Object) next, CollectionsKt.last(allChangedPrefs))) {
                sb.append(", ");
            }
        }
        sb.append("}");
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0123  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.util.ArrayList<org.videolan.vlc.gui.preferences.search.PreferenceItem> parsePreferences(android.content.Context r30, int r31) {
        /*
            r29 = this;
            r0 = r29
            r1 = r30
            java.lang.String r2 = "get(...)"
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            android.content.res.Resources r4 = r30.getResources()
            r15 = r31
            android.content.res.XmlResourceParser r4 = r4.getXml(r15)
            java.lang.String r5 = "getXml(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)
            android.content.ContextWrapper r5 = new android.content.ContextWrapper
            r5.<init>(r1)
            java.lang.String r6 = "en"
            android.content.ContextWrapper r16 = org.videolan.tools.LocaleUtilsKt.wrap(r5, r6)
            java.lang.String r17 = ""
            r13 = 0
            r7 = r17
            r8 = r7
            r5 = -1
            r6 = 0
        L_0x002d:
            r9 = 1
            if (r5 == r9) goto L_0x0155
            r10 = 2
            if (r5 != r10) goto L_0x0146
            java.lang.String r5 = r4.getName()
            java.lang.String r11 = "PreferenceScreen"
            boolean r11 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r11)
            java.lang.String r12 = "title"
            java.lang.String r9 = "http://schemas.android.com/apk/res/android"
            if (r11 != 0) goto L_0x0058
            if (r6 != 0) goto L_0x0058
            java.lang.String r6 = r0.getValue(r1, r4, r9, r12)
            r7 = r16
            android.content.Context r7 = (android.content.Context) r7
            java.lang.String r7 = r0.getValue(r7, r4, r9, r12)
            r20 = r6
            r21 = r7
            r19 = 1
            goto L_0x005e
        L_0x0058:
            r19 = r6
            r20 = r7
            r21 = r8
        L_0x005e:
            java.lang.String r6 = "PreferenceCategory"
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)
            if (r6 != 0) goto L_0x013b
            java.lang.String r6 = "Preference"
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)
            if (r6 != 0) goto L_0x013b
            java.lang.String r6 = "key"
            java.lang.String r6 = r0.getValue(r1, r4, r9, r6)
            java.lang.String r8 = r0.getValue(r1, r4, r9, r12)
            r7 = r16
            android.content.Context r7 = (android.content.Context) r7
            java.lang.String r11 = r0.getValue(r7, r4, r9, r12)
            java.lang.String r12 = "summary"
            java.lang.String r28 = r0.getValue(r1, r4, r9, r12)
            java.lang.String r7 = r0.getValue(r7, r4, r9, r12)
            java.lang.String r12 = "defaultValue"
            java.lang.String r12 = r0.getValue(r1, r4, r9, r12)
            r14 = r28
            java.lang.CharSequence r14 = (java.lang.CharSequence) r14
            java.lang.String r22 = "%s"
            r0 = r22
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r15 = 0
            boolean r0 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r14, (java.lang.CharSequence) r0, (boolean) r13, (int) r10, (java.lang.Object) r15)
            if (r0 == 0) goto L_0x0114
            java.lang.String r0 = "ListPreference"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r0)
            if (r0 == 0) goto L_0x0114
            org.videolan.tools.Settings r0 = org.videolan.tools.Settings.INSTANCE     // Catch:{ Exception -> 0x0114 }
            java.lang.Object r0 = r0.getInstance(r1)     // Catch:{ Exception -> 0x0114 }
            android.content.SharedPreferences r0 = (android.content.SharedPreferences) r0     // Catch:{ Exception -> 0x0114 }
            java.lang.String r0 = r0.getString(r6, r12)     // Catch:{ Exception -> 0x0114 }
            if (r0 != 0) goto L_0x00b9
            r0 = r17
        L_0x00b9:
            java.lang.String r5 = "entries"
            r14 = -1
            int r5 = r4.getAttributeResourceValue(r9, r5, r14)     // Catch:{ Exception -> 0x0115 }
            java.lang.String r10 = "entryValues"
            int r9 = r4.getAttributeResourceValue(r9, r10, r14)     // Catch:{ Exception -> 0x0115 }
            android.content.res.Resources r10 = r30.getResources()     // Catch:{ Exception -> 0x0115 }
            java.lang.String[] r9 = r10.getStringArray(r9)     // Catch:{ Exception -> 0x0115 }
            java.lang.String r10 = "getStringArray(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r10)     // Catch:{ Exception -> 0x0115 }
            java.lang.Object[] r9 = (java.lang.Object[]) r9     // Catch:{ Exception -> 0x0115 }
            int r0 = kotlin.collections.ArraysKt.indexOf((T[]) r9, r0)     // Catch:{ Exception -> 0x0115 }
            java.lang.String r23 = "%s"
            android.content.res.Resources r9 = r30.getResources()     // Catch:{ Exception -> 0x0115 }
            java.lang.String[] r9 = r9.getStringArray(r5)     // Catch:{ Exception -> 0x0115 }
            r9 = r9[r0]     // Catch:{ Exception -> 0x0115 }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r2)     // Catch:{ Exception -> 0x0115 }
            r26 = 4
            r27 = 0
            r25 = 0
            r22 = r28
            r24 = r9
            java.lang.String r28 = kotlin.text.StringsKt.replace$default((java.lang.String) r22, (java.lang.String) r23, (java.lang.String) r24, (boolean) r25, (int) r26, (java.lang.Object) r27)     // Catch:{ Exception -> 0x0115 }
            java.lang.String r23 = "%s"
            android.content.res.Resources r9 = r16.getResources()     // Catch:{ Exception -> 0x0115 }
            java.lang.String[] r5 = r9.getStringArray(r5)     // Catch:{ Exception -> 0x0115 }
            r0 = r5[r0]     // Catch:{ Exception -> 0x0115 }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r2)     // Catch:{ Exception -> 0x0115 }
            r26 = 4
            r27 = 0
            r25 = 0
            r22 = r7
            r24 = r0
            java.lang.String r0 = kotlin.text.StringsKt.replace$default((java.lang.String) r22, (java.lang.String) r23, (java.lang.String) r24, (boolean) r25, (int) r26, (java.lang.Object) r27)     // Catch:{ Exception -> 0x0115 }
            goto L_0x0116
        L_0x0114:
            r14 = -1
        L_0x0115:
            r0 = r7
        L_0x0116:
            r9 = r28
            r5 = r6
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            boolean r5 = kotlin.text.StringsKt.isBlank(r5)
            r7 = 1
            r5 = r5 ^ r7
            if (r5 == 0) goto L_0x013b
            org.videolan.vlc.gui.preferences.search.PreferenceItem r15 = new org.videolan.vlc.gui.preferences.search.PreferenceItem
            r5 = r15
            r7 = r31
            r10 = r11
            r11 = r0
            r0 = r12
            r12 = r20
            r18 = 0
            r13 = r21
            r22 = -1
            r14 = r0
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r14)
            r3.add(r15)
            goto L_0x013f
        L_0x013b:
            r18 = 0
            r22 = -1
        L_0x013f:
            r6 = r19
            r7 = r20
            r8 = r21
            goto L_0x014a
        L_0x0146:
            r18 = 0
            r22 = -1
        L_0x014a:
            int r5 = r4.next()
            r0 = r29
            r15 = r31
            r13 = 0
            goto L_0x002d
        L_0x0155:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.preferences.search.PreferenceParser.parsePreferences(android.content.Context, int):java.util.ArrayList");
    }

    private final String getValue(Context context, XmlResourceParser xmlResourceParser, String str, String str2) {
        try {
            int attributeResourceValue = xmlResourceParser.getAttributeResourceValue(str, str2, -1);
            if (attributeResourceValue == -1) {
                String attributeValue = xmlResourceParser.getAttributeValue(str, str2);
                Intrinsics.checkNotNull(attributeValue);
                return attributeValue;
            }
            String string = context.getResources().getString(attributeResourceValue);
            Intrinsics.checkNotNull(string);
            return string;
        } catch (Exception unused) {
            return "";
        }
    }

    public final Object exportPreferences(Activity activity, File file, Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new PreferenceParser$exportPreferences$2(activity, file, (Continuation<? super PreferenceParser$exportPreferences$2>) null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    public final Object restoreSettings(Activity activity, Uri uri, Continuation<? super Unit> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new PreferenceParser$restoreSettings$2(uri, activity, (Continuation<? super PreferenceParser$restoreSettings$2>) null), continuation);
    }
}
