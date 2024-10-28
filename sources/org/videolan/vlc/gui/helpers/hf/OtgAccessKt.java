package org.videolan.vlc.gui.helpers.hf;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import androidx.documentfile.provider.DocumentFile;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.videolan.medialibrary.MLServiceLocator;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.resources.Constants;
import org.videolan.vlc.ArtworkProvider;

@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0001H\u0007\u001a\n\u0010\u000b\u001a\u00020\f*\u00020\r\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"OTG_SCHEME", "", "SAF_REQUEST", "", "TAG", "getDocumentFiles", "", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "context", "Landroid/content/Context;", "path", "requestOtgRoot", "", "Landroidx/fragment/app/FragmentActivity;", "vlc-android_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: OtgAccess.kt */
public final class OtgAccessKt {
    public static final String OTG_SCHEME = "otg";
    public static final int SAF_REQUEST = 85;
    public static final String TAG = "OtgAccess";

    public static final void requestOtgRoot(FragmentActivity fragmentActivity) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "<this>");
        fragmentActivity.getSupportFragmentManager().beginTransaction().add((Fragment) new OtgAccess(), TAG).commitAllowingStateLoss();
    }

    public static final List<MediaWrapper> getDocumentFiles(Context context, String str) {
        List<String> list;
        String name;
        int i;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        Uri value = OtgAccess.Companion.getOtgRoot().getValue();
        if (value == null) {
            return null;
        }
        DocumentFile fromTreeUri = DocumentFile.fromTreeUri(context, value);
        List<String> split = new Regex("/").split(StringsKt.substringAfterLast$default(str, (char) AbstractJsonLexerKt.COLON, (String) null, 2, (Object) null), 0);
        if (!split.isEmpty()) {
            ListIterator<String> listIterator = split.listIterator(split.size());
            while (true) {
                if (listIterator.hasPrevious()) {
                    if (listIterator.previous().length() != 0) {
                        list = CollectionsKt.take(split, listIterator.nextIndex() + 1);
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        list = CollectionsKt.emptyList();
        for (String str2 : list) {
            if (!Intrinsics.areEqual((Object) str2, (Object) "")) {
                fromTreeUri = fromTreeUri != null ? fromTreeUri.findFile(str2) : null;
            }
        }
        if (fromTreeUri == null) {
            Log.w(TAG, "Failed to find file");
            return null;
        }
        List<MediaWrapper> arrayList = new ArrayList<>();
        DocumentFile[] listFiles = fromTreeUri.listFiles();
        Intrinsics.checkNotNullExpressionValue(listFiles, "listFiles(...)");
        for (DocumentFile documentFile : listFiles) {
            if (documentFile.exists() && documentFile.canRead() && ((name = documentFile.getName()) == null || !StringsKt.startsWith$default(name, ".", false, 2, (Object) null))) {
                MediaWrapper abstractMediaWrapper = MLServiceLocator.getAbstractMediaWrapper(documentFile.getUri());
                if (documentFile.isDirectory()) {
                    i = 3;
                } else {
                    String type = documentFile.getType();
                    if (type != null) {
                        Intrinsics.checkNotNull(type);
                        if (StringsKt.startsWith$default(type, "video", false, 2, (Object) null)) {
                            i = 0;
                        }
                    }
                    String type2 = documentFile.getType();
                    if (type2 != null) {
                        Intrinsics.checkNotNull(type2);
                        if (StringsKt.startsWith$default(type2, Constants.ID_AUDIO, false, 2, (Object) null)) {
                            i = 1;
                        }
                    }
                    i = abstractMediaWrapper.getType();
                }
                abstractMediaWrapper.setType(i);
                abstractMediaWrapper.setTitle(documentFile.getName());
                Intrinsics.checkNotNull(abstractMediaWrapper);
                arrayList.add(abstractMediaWrapper);
            }
        }
        return arrayList;
    }
}
