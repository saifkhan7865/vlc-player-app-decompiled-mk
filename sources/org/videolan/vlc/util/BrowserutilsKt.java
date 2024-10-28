package org.videolan.vlc.util;

import android.net.Uri;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.videolan.medialibrary.MLServiceLocator;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.mediadb.models.BrowserFav;

@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u001a\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a$\u0010\u0004\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00010\u00010\u00052\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005\u001a\u0010\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u001a\u0010\u0010\f\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u001a\u0010\u0010\r\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u001a\n\u0010\u000e\u001a\u00020\t*\u00020\u000b\u001a\n\u0010\u000f\u001a\u00020\t*\u00020\u0010\u001a\n\u0010\u0011\u001a\u00020\t*\u00020\u0010\u001a\f\u0010\u0012\u001a\u00020\t*\u0004\u0018\u00010\u000b\u001a\f\u0010\u0013\u001a\u00020\t*\u0004\u0018\u00010\u000b\u001a\f\u0010\u0014\u001a\u00020\t*\u0004\u0018\u00010\u000b\u001a\f\u0010\u0015\u001a\u00020\t*\u0004\u0018\u00010\u000b\u001a\f\u0010\u0016\u001a\u00020\t*\u0004\u0018\u00010\u000b\u001a\f\u0010\u0017\u001a\u00020\t*\u0004\u0018\u00010\u000b¨\u0006\u0018"}, d2 = {"convertFavorite", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "browserFav", "Lorg/videolan/vlc/mediadb/models/BrowserFav;", "convertFavorites", "", "kotlin.jvm.PlatformType", "browserFavs", "isSchemeHttpOrHttps", "", "scheme", "", "isSchemeStreaming", "isSchemeSupported", "isMissing", "isOTG", "Landroid/net/Uri;", "isSD", "isSchemeDistant", "isSchemeFD", "isSchemeFavoriteEditable", "isSchemeFile", "isSchemeNetwork", "isSchemeSMB", "vlc-android_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: Browserutils.kt */
public final class BrowserutilsKt {
    public static final boolean isSchemeStreaming(String str) {
        CharSequence charSequence = str;
        if (charSequence == null || charSequence.length() == 0) {
            return false;
        }
        if (!isSchemeHttpOrHttps(str) && !StringsKt.startsWith$default(str, "mms", false, 2, (Object) null) && !StringsKt.startsWith$default(str, "rtsp", false, 2, (Object) null)) {
            return false;
        }
        return true;
    }

    public static final boolean isSchemeHttpOrHttps(String str) {
        return str != null && StringsKt.startsWith$default(str, "http", false, 2, (Object) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0054 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean isSchemeSupported(java.lang.String r1) {
        /*
            if (r1 == 0) goto L_0x0054
            int r0 = r1.hashCode()
            switch(r0) {
                case 101730: goto L_0x0049;
                case 108987: goto L_0x0040;
                case 113992: goto L_0x0037;
                case 114184: goto L_0x002e;
                case 3143036: goto L_0x0025;
                case 3153745: goto L_0x001c;
                case 97765776: goto L_0x0013;
                case 951530617: goto L_0x000a;
                default: goto L_0x0009;
            }
        L_0x0009:
            goto L_0x0054
        L_0x000a:
            java.lang.String r0 = "content"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0054
            goto L_0x0052
        L_0x0013:
            java.lang.String r0 = "ftpes"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x0052
            goto L_0x0054
        L_0x001c:
            java.lang.String r0 = "ftps"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x0052
            goto L_0x0054
        L_0x0025:
            java.lang.String r0 = "file"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x0052
            goto L_0x0054
        L_0x002e:
            java.lang.String r0 = "ssh"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x0052
            goto L_0x0054
        L_0x0037:
            java.lang.String r0 = "smb"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x0052
            goto L_0x0054
        L_0x0040:
            java.lang.String r0 = "nfs"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x0052
            goto L_0x0054
        L_0x0049:
            java.lang.String r0 = "ftp"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x0052
            goto L_0x0054
        L_0x0052:
            r1 = 1
            goto L_0x0055
        L_0x0054:
            r1 = 0
        L_0x0055:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.util.BrowserutilsKt.isSchemeSupported(java.lang.String):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x004b A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean isSchemeNetwork(java.lang.String r1) {
        /*
            if (r1 == 0) goto L_0x004b
            int r0 = r1.hashCode()
            switch(r0) {
                case 101730: goto L_0x0040;
                case 108987: goto L_0x0037;
                case 113992: goto L_0x002e;
                case 114184: goto L_0x0025;
                case 3153745: goto L_0x001c;
                case 3596701: goto L_0x0013;
                case 97765776: goto L_0x000a;
                default: goto L_0x0009;
            }
        L_0x0009:
            goto L_0x004b
        L_0x000a:
            java.lang.String r0 = "ftpes"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x0049
            goto L_0x004b
        L_0x0013:
            java.lang.String r0 = "upnp"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x004b
            goto L_0x0049
        L_0x001c:
            java.lang.String r0 = "ftps"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x0049
            goto L_0x004b
        L_0x0025:
            java.lang.String r0 = "ssh"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x0049
            goto L_0x004b
        L_0x002e:
            java.lang.String r0 = "smb"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x0049
            goto L_0x004b
        L_0x0037:
            java.lang.String r0 = "nfs"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x0049
            goto L_0x004b
        L_0x0040:
            java.lang.String r0 = "ftp"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x0049
            goto L_0x004b
        L_0x0049:
            r1 = 1
            goto L_0x004c
        L_0x004b:
            r1 = 0
        L_0x004c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.util.BrowserutilsKt.isSchemeNetwork(java.lang.String):boolean");
    }

    public static final boolean isSchemeFavoriteEditable(String str) {
        return ArraysKt.contains((T[]) new String[]{"ftp", "ftps", "ftpes", "sftp", "smb", "nfs"}, str);
    }

    public static final boolean isSchemeFile(String str) {
        return Intrinsics.areEqual((Object) str, (Object) "file") || str == null;
    }

    public static final boolean isOTG(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "<this>");
        String path = uri.getPath();
        return path != null && StringsKt.startsWith$default(path, "/mnt", false, 2, (Object) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000c, code lost:
        r0 = r5.getPath();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001d, code lost:
        r5 = r5.getPath();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean isSD(android.net.Uri r5) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r0 = r5.getPath()
            r1 = 0
            if (r0 == 0) goto L_0x0030
            java.lang.String r0 = r5.getPath()
            if (r0 == 0) goto L_0x0030
            java.lang.String r2 = "/storage"
            r3 = 2
            r4 = 0
            boolean r0 = kotlin.text.StringsKt.startsWith$default(r0, r2, r1, r3, r4)
            r2 = 1
            if (r0 != r2) goto L_0x0030
            java.lang.String r5 = r5.getPath()
            if (r5 == 0) goto L_0x0030
            org.videolan.resources.AndroidDevices r0 = org.videolan.resources.AndroidDevices.INSTANCE
            java.lang.String r0 = r0.getEXTERNAL_PUBLIC_DIRECTORY()
            boolean r5 = kotlin.text.StringsKt.startsWith$default(r5, r0, r1, r3, r4)
            if (r5 != 0) goto L_0x0030
            r1 = 1
        L_0x0030:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.util.BrowserutilsKt.isSD(android.net.Uri):boolean");
    }

    public static final boolean isSchemeSMB(String str) {
        return Intrinsics.areEqual((Object) str, (Object) "smb");
    }

    public static final boolean isSchemeFD(String str) {
        return Intrinsics.areEqual((Object) str, (Object) "fd");
    }

    public static final boolean isSchemeDistant(String str) {
        return !isSchemeFile(str);
    }

    public static final boolean isMissing(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return Intrinsics.areEqual((Object) str, (Object) "missing://");
    }

    public static final List<MediaWrapper> convertFavorites(List<BrowserFav> list) {
        if (list == null) {
            return CollectionsKt.emptyList();
        }
        Collection arrayList = new ArrayList();
        for (Object next : list) {
            BrowserFav browserFav = (BrowserFav) next;
            if (!Intrinsics.areEqual((Object) browserFav.getUri().getScheme(), (Object) "file") || new File(browserFav.getUri().getPath()).exists()) {
                arrayList.add(next);
            }
        }
        Iterable<BrowserFav> iterable = (List) arrayList;
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (BrowserFav browserFav2 : iterable) {
            Uri component1 = browserFav2.component1();
            String component3 = browserFav2.component3();
            String component4 = browserFav2.component4();
            MediaWrapper abstractMediaWrapper = MLServiceLocator.getAbstractMediaWrapper(component1);
            abstractMediaWrapper.setDisplayTitle(Uri.decode(component3));
            abstractMediaWrapper.setType(3);
            if (component4 != null) {
                abstractMediaWrapper.setArtworkURL(Uri.decode(component4));
            }
            abstractMediaWrapper.setStateFlags(2);
            arrayList2.add(abstractMediaWrapper);
        }
        return (List) arrayList2;
    }

    public static final MediaWrapper convertFavorite(BrowserFav browserFav) {
        Intrinsics.checkNotNullParameter(browserFav, "browserFav");
        MediaWrapper abstractMediaWrapper = MLServiceLocator.getAbstractMediaWrapper(browserFav.getUri());
        abstractMediaWrapper.setDisplayTitle(Uri.decode(browserFav.getTitle()));
        abstractMediaWrapper.setType(3);
        String iconUrl = browserFav.getIconUrl();
        if (iconUrl != null) {
            abstractMediaWrapper.setArtworkURL(Uri.decode(iconUrl));
        }
        abstractMediaWrapper.setStateFlags(2);
        return abstractMediaWrapper;
    }
}
