package org.videolan.vlc.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.storage.StorageManager;
import androidx.documentfile.provider.DocumentFile;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.resources.AndroidDevices;
import org.videolan.resources.AppContextProvider;
import org.videolan.resources.Constants;
import org.videolan.resources.util.HelpersKt;
import org.videolan.tools.AppScope;
import org.videolan.tools.CloseableUtils;
import org.videolan.tools.Settings;
import org.videolan.tools.Strings;
import org.videolan.tools.WorkersKt;
import org.videolan.vlc.ArtworkProvider;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001IB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u0006J\u001a\u0010\u0007\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011J\u0012\u0010\u0012\u001a\u00020\u000f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0007J\u0012\u0010\u0012\u001a\u00020\u000f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0006H\u0007J\u000e\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0019J(\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u000fH\u0003J(\u0010 \u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u000fH\u0007J\u0018\u0010!\u001a\u00020\u000f2\u0006\u0010\"\u001a\u00020\n2\u0006\u0010#\u001a\u00020\nH\u0007J\u0018\u0010!\u001a\u00020\b2\u0006\u0010$\u001a\u00020\u00192\u0006\u0010%\u001a\u00020&H\u0003J\u0016\u0010'\u001a\u00020\b2\u0006\u0010(\u001a\u00020)2\u0006\u0010\u001f\u001a\u00020\u000fJ\u0016\u0010*\u001a\u00020\b2\u0006\u0010(\u001a\u00020)2\u0006\u0010\u001f\u001a\u00020\u000fJ\u0010\u0010+\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0007J\u0010\u0010+\u001a\u00020\u000f2\u0006\u0010,\u001a\u00020\nH\u0007J\u0012\u0010+\u001a\u00020\u000f2\b\u0010\r\u001a\u0004\u0018\u00010\u0006H\u0007J\u0012\u0010-\u001a\u0004\u0018\u00010.2\u0006\u0010\u0013\u001a\u00020\u0014H\u0007J\u0010\u0010/\u001a\u00020\u00062\b\u00100\u001a\u0004\u0018\u00010\u0006J\u0014\u00101\u001a\u0004\u0018\u00010\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0007J\u0012\u00102\u001a\u0004\u0018\u00010\u00062\b\u0010\r\u001a\u0004\u0018\u00010\u0006J\u0010\u00103\u001a\u00020\u00062\u0006\u00104\u001a\u00020\u0014H\u0007J\u0011\u00105\u001a\b\u0012\u0004\u0012\u00020\u000606¢\u0006\u0002\u00107J\u0012\u00108\u001a\u0004\u0018\u00010\u00062\u0006\u00109\u001a\u00020\u0006H\u0007J\u000e\u0010:\u001a\u00020\u00062\u0006\u00100\u001a\u00020\u0006J\u0014\u0010;\u001a\u0004\u0018\u00010\u00142\b\u0010<\u001a\u0004\u0018\u00010\u0014H\u0007J$\u0010=\u001a\b\u0012\u0004\u0012\u00020\u00060>2\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010?\u001a\u00020\u0006H@¢\u0006\u0002\u0010@J!\u0010A\u001a\u00020\u000f2\f\u0010B\u001a\b\u0012\u0004\u0012\u00020\u0006062\u0006\u0010C\u001a\u00020\u0006¢\u0006\u0002\u0010DJ-\u0010E\u001a\u00020\u000f2\u0018\u0010B\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060F062\u0006\u0010C\u001a\u00020\u0006¢\u0006\u0002\u0010GJ\n\u0010H\u001a\u00020\u000f*\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006J"}, d2 = {"Lorg/videolan/vlc/util/FileUtils;", "", "()V", "BUFFER", "", "TAG", "", "asyncRecursiveDelete", "", "fileOrDirectory", "Ljava/io/File;", "callback", "Lorg/videolan/vlc/util/FileUtils$Callback;", "path", "canSave", "", "mw", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "canWrite", "uri", "Landroid/net/Uri;", "writePath", "convertLocalUri", "convertStreamToString", "is", "Ljava/io/InputStream;", "copyAsset", "assetManager", "Landroid/content/res/AssetManager;", "fromAssetPath", "toPath", "force", "copyAssetFolder", "copyFile", "src", "dst", "inputStream", "out", "Ljava/io/OutputStream;", "copyHrtfs", "context", "Landroid/content/Context;", "copyLua", "deleteFile", "file", "findFile", "Landroidx/documentfile/provider/DocumentFile;", "getFileNameFromPath", "filePath", "getMediaStorage", "getParent", "getPathFromURI", "contentUri", "getSoundFontExtensions", "", "()[Ljava/lang/String;", "getStorageTag", "uuid", "getStringFromFile", "getUri", "data", "unpackZip", "Ljava/util/ArrayList;", "unzipDirectory", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "zip", "files", "zipFileName", "([Ljava/lang/String;Ljava/lang/String;)Z", "zipWithName", "Lkotlin/Pair;", "([Lkotlin/Pair;Ljava/lang/String;)Z", "isInternalStorage", "Callback", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FileUtils.kt */
public final class FileUtils {
    public static final int BUFFER = 2048;
    public static final FileUtils INSTANCE = new FileUtils();
    public static final String TAG = "VLC/FileUtils";

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lorg/videolan/vlc/util/FileUtils$Callback;", "", "onResult", "", "success", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: FileUtils.kt */
    public interface Callback {
        void onResult(boolean z);
    }

    private FileUtils() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:1:0x0002, code lost:
        r4 = kotlin.text.StringsKt.substringAfterLast$default(r4, '/', (java.lang.String) null, 2, (java.lang.Object) null);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String getFileNameFromPath(java.lang.String r4) {
        /*
            r3 = this;
            if (r4 == 0) goto L_0x000c
            r0 = 47
            r1 = 2
            r2 = 0
            java.lang.String r4 = kotlin.text.StringsKt.substringAfterLast$default((java.lang.String) r4, (char) r0, (java.lang.String) r2, (int) r1, (java.lang.Object) r2)
            if (r4 != 0) goto L_0x000e
        L_0x000c:
            java.lang.String r4 = ""
        L_0x000e:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.util.FileUtils.getFileNameFromPath(java.lang.String):java.lang.String");
    }

    public final String getParent(String str) {
        if (str == null || Intrinsics.areEqual((Object) str, (Object) "/")) {
            return str;
        }
        if (StringsKt.endsWith$default(str, "/", false, 2, (Object) null)) {
            str = str.substring(0, str.length() - 1);
            Intrinsics.checkNotNullExpressionValue(str, "substring(...)");
        }
        int lastIndexOf$default = StringsKt.lastIndexOf$default((CharSequence) str, '/', 0, false, 6, (Object) null);
        if (lastIndexOf$default > 0) {
            String substring = str.substring(0, lastIndexOf$default);
            Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
            return substring;
        } else if (lastIndexOf$default == 0) {
            return "/";
        } else {
            return str;
        }
    }

    public final Uri convertLocalUri(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, Constants.KEY_URI);
        if (!Intrinsics.areEqual((Object) uri.getScheme(), (Object) "file")) {
            return uri;
        }
        String path = uri.getPath();
        Intrinsics.checkNotNull(path);
        if (!StringsKt.startsWith$default(path, "/sdcard", false, 2, (Object) null)) {
            return uri;
        }
        String uri2 = uri.toString();
        Intrinsics.checkNotNullExpressionValue(uri2, "toString(...)");
        return Uri.parse(StringsKt.replace$default(uri2, "/sdcard", AndroidDevices.INSTANCE.getEXTERNAL_PUBLIC_DIRECTORY(), false, 4, (Object) null));
    }

    public final String getPathFromURI(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "contentUri");
        Cursor cursor = null;
        try {
            Cursor query = AppContextProvider.INSTANCE.getAppContext().getContentResolver().query(uri, new String[]{"_data"}, (String) null, (String[]) null, (String) null);
            if (query != null) {
                if (query.getCount() != 0) {
                    int columnIndexOrThrow = query.getColumnIndexOrThrow("_data");
                    query.moveToFirst();
                    String uri2 = Uri.fromFile(new File(query.getString(columnIndexOrThrow))).toString();
                    Intrinsics.checkNotNullExpressionValue(uri2, "toString(...)");
                    if (!query.isClosed()) {
                        query.close();
                    }
                    return uri2;
                }
            }
            if (query != null && !query.isClosed()) {
                query.close();
            }
            return "";
        } catch (IllegalArgumentException unused) {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            return "";
        } catch (SecurityException unused2) {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            return "";
        } catch (SQLiteException unused3) {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            return "";
        } catch (NullPointerException unused4) {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            return "";
        } catch (Throwable th) {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            throw th;
        }
    }

    public final void copyHrtfs(Context context, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        Job unused = BuildersKt__Builders_commonKt.launch$default(AppScope.INSTANCE, Dispatchers.getIO(), (CoroutineStart) null, new FileUtils$copyHrtfs$1(context, z, (Continuation<? super FileUtils$copyHrtfs$1>) null), 2, (Object) null);
    }

    public final void copyLua(Context context, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        Job unused = BuildersKt__Builders_commonKt.launch$default(AppScope.INSTANCE, Dispatchers.getIO(), (CoroutineStart) null, new FileUtils$copyLua$1(context, z, (Continuation<? super FileUtils$copyLua$1>) null), 2, (Object) null);
    }

    public final boolean copyAssetFolder(AssetManager assetManager, String str, String str2, boolean z) {
        boolean z2;
        Intrinsics.checkNotNullParameter(assetManager, "assetManager");
        Intrinsics.checkNotNullParameter(str, "fromAssetPath");
        Intrinsics.checkNotNullParameter(str2, "toPath");
        try {
            String[] list = assetManager.list(str);
            if (list != null) {
                if (list.length != 0) {
                    new File(str2).mkdirs();
                    Iterator it = ArrayIteratorKt.iterator(list);
                    boolean z3 = true;
                    while (it.hasNext()) {
                        String str3 = (String) it.next();
                        Intrinsics.checkNotNull(str3);
                        if (StringsKt.contains$default((CharSequence) str3, (CharSequence) ".", false, 2, (Object) null)) {
                            z2 = copyAsset(assetManager, str + '/' + str3, str2 + '/' + str3, z);
                        } else {
                            z2 = copyAssetFolder(assetManager, str + '/' + str3, str2 + '/' + str3, z);
                        }
                        z3 &= z2;
                    }
                    return z3;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private final boolean copyAsset(AssetManager assetManager, String str, String str2, boolean z) {
        OutputStream outputStream;
        File file = new File(str2);
        if (!z && file.exists()) {
            return true;
        }
        InputStream inputStream = null;
        try {
            InputStream open = assetManager.open(str);
            try {
                file.createNewFile();
                outputStream = new FileOutputStream(str2);
                try {
                    copyFile(open, outputStream);
                    ((FileOutputStream) outputStream).flush();
                    CloseableUtils.INSTANCE.close(open);
                    CloseableUtils.INSTANCE.close(outputStream);
                    return true;
                } catch (Exception e) {
                    e = e;
                    inputStream = open;
                    try {
                        e.printStackTrace();
                        CloseableUtils.INSTANCE.close(inputStream);
                        CloseableUtils.INSTANCE.close(outputStream);
                        return false;
                    } catch (Throwable th) {
                        th = th;
                        CloseableUtils.INSTANCE.close(inputStream);
                        CloseableUtils.INSTANCE.close(outputStream);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    inputStream = open;
                    CloseableUtils.INSTANCE.close(inputStream);
                    CloseableUtils.INSTANCE.close(outputStream);
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
                outputStream = null;
                inputStream = open;
                e.printStackTrace();
                CloseableUtils.INSTANCE.close(inputStream);
                CloseableUtils.INSTANCE.close(outputStream);
                return false;
            } catch (Throwable th3) {
                th = th3;
                outputStream = null;
                inputStream = open;
                CloseableUtils.INSTANCE.close(inputStream);
                CloseableUtils.INSTANCE.close(outputStream);
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            outputStream = null;
            e.printStackTrace();
            CloseableUtils.INSTANCE.close(inputStream);
            CloseableUtils.INSTANCE.close(outputStream);
            return false;
        } catch (Throwable th4) {
            th = th4;
            outputStream = null;
            CloseableUtils.INSTANCE.close(inputStream);
            CloseableUtils.INSTANCE.close(outputStream);
            throw th;
        }
    }

    private final void copyFile(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[1024];
        int read = inputStream.read(bArr);
        while (read != -1) {
            outputStream.write(bArr, 0, read);
            read = inputStream.read(bArr);
        }
    }

    public final boolean copyFile(File file, File file2) {
        OutputStream outputStream;
        Intrinsics.checkNotNullParameter(file, "src");
        Intrinsics.checkNotNullParameter(file2, "dst");
        boolean z = true;
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            file2.mkdirs();
            if (listFiles == null) {
                listFiles = new File[0];
            }
            for (File file3 : listFiles) {
                Intrinsics.checkNotNull(file3);
                z &= copyFile(file3, new File(file2, file3.getName()));
            }
        } else if (file.isFile()) {
            InputStream inputStream = null;
            try {
                InputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                try {
                    outputStream = new BufferedOutputStream(new FileOutputStream(file2));
                } catch (IOException unused) {
                    outputStream = null;
                    inputStream = bufferedInputStream;
                    CloseableUtils.INSTANCE.close(inputStream);
                    CloseableUtils.INSTANCE.close(outputStream);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    outputStream = null;
                    inputStream = bufferedInputStream;
                    CloseableUtils.INSTANCE.close(inputStream);
                    CloseableUtils.INSTANCE.close(outputStream);
                    throw th;
                }
                try {
                    byte[] bArr = new byte[1024];
                    for (int read = ((BufferedInputStream) bufferedInputStream).read(bArr); read > 0; read = ((BufferedInputStream) bufferedInputStream).read(bArr)) {
                        ((BufferedOutputStream) outputStream).write(bArr, 0, read);
                    }
                    CloseableUtils.INSTANCE.close(bufferedInputStream);
                    CloseableUtils.INSTANCE.close(outputStream);
                    return true;
                } catch (IOException unused2) {
                    inputStream = bufferedInputStream;
                    CloseableUtils.INSTANCE.close(inputStream);
                    CloseableUtils.INSTANCE.close(outputStream);
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    inputStream = bufferedInputStream;
                    CloseableUtils.INSTANCE.close(inputStream);
                    CloseableUtils.INSTANCE.close(outputStream);
                    throw th;
                }
            } catch (IOException unused3) {
                outputStream = null;
                CloseableUtils.INSTANCE.close(inputStream);
                CloseableUtils.INSTANCE.close(outputStream);
                return false;
            } catch (Throwable th3) {
                th = th3;
                outputStream = null;
                CloseableUtils.INSTANCE.close(inputStream);
                CloseableUtils.INSTANCE.close(outputStream);
                throw th;
            }
        }
        return z;
    }

    public final boolean deleteFile(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, Constants.KEY_URI);
        if (!HelpersKt.isExternalStorageManager() && AndroidUtil.isLolliPopOrLater) {
            String path = uri.getPath();
            Intrinsics.checkNotNull(path);
            if (!StringsKt.startsWith$default(path, AndroidDevices.INSTANCE.getEXTERNAL_PUBLIC_DIRECTORY(), false, 2, (Object) null)) {
                DocumentFile findFile = findFile(uri);
                if (findFile != null) {
                    try {
                        return findFile.delete();
                    } catch (Exception unused) {
                    }
                }
                return false;
            }
        }
        return deleteFile(uri.getPath());
    }

    public final boolean deleteFile(String str) {
        if (str != null) {
            return INSTANCE.deleteFile(new File(str));
        }
        return false;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: int} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean deleteFile(java.io.File r8) {
        /*
            r7 = this;
            java.lang.String r0 = "file"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            boolean r0 = r8.isDirectory()
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x002d
            java.io.File[] r0 = r8.listFiles()
            if (r0 != 0) goto L_0x0015
            java.io.File[] r0 = new java.io.File[r2]
        L_0x0015:
            int r3 = r0.length
        L_0x0016:
            if (r2 >= r3) goto L_0x0025
            r4 = r0[r2]
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
            boolean r4 = r7.deleteFile((java.io.File) r4)
            r1 = r1 & r4
            int r2 = r2 + 1
            goto L_0x0016
        L_0x0025:
            if (r1 == 0) goto L_0x0060
            boolean r8 = r8.delete()
            r1 = r1 & r8
            goto L_0x0060
        L_0x002d:
            org.videolan.resources.AppContextProvider r0 = org.videolan.resources.AppContextProvider.INSTANCE
            android.content.Context r0 = r0.getAppContext()
            android.content.ContentResolver r0 = r0.getContentResolver()
            java.lang.String r3 = "external"
            android.net.Uri r3 = android.provider.MediaStore.Files.getContentUri(r3)     // Catch:{ IllegalArgumentException | SecurityException -> 0x0051 }
            java.lang.String r4 = "_data=?"
            java.lang.String r5 = r8.getPath()     // Catch:{ IllegalArgumentException | SecurityException -> 0x0051 }
            java.lang.String[] r6 = new java.lang.String[r1]     // Catch:{ IllegalArgumentException | SecurityException -> 0x0051 }
            r6[r2] = r5     // Catch:{ IllegalArgumentException | SecurityException -> 0x0051 }
            int r0 = r0.delete(r3, r4, r6)     // Catch:{ IllegalArgumentException | SecurityException -> 0x0051 }
            if (r0 <= 0) goto L_0x004e
            goto L_0x004f
        L_0x004e:
            r1 = 0
        L_0x004f:
            r2 = r1
            goto L_0x0052
        L_0x0051:
        L_0x0052:
            boolean r0 = r8.exists()
            if (r0 == 0) goto L_0x005f
            boolean r8 = r8.delete()
            r1 = r2 | r8
            goto L_0x0060
        L_0x005f:
            r1 = r2
        L_0x0060:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.util.FileUtils.deleteFile(java.io.File):boolean");
    }

    public final boolean isInternalStorage(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return StringsKt.startsWith$default(str, AndroidDevices.INSTANCE.getEXTERNAL_PUBLIC_DIRECTORY(), false, 2, (Object) null);
    }

    private final void asyncRecursiveDelete(String str, Callback callback) {
        asyncRecursiveDelete(new File(str), callback);
    }

    public final void asyncRecursiveDelete(String str) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        asyncRecursiveDelete(str, (Callback) null);
    }

    private final void asyncRecursiveDelete(File file, Callback callback) {
        WorkersKt.runIO(new FileUtils$$ExternalSyntheticLambda0(file, callback));
    }

    /* access modifiers changed from: private */
    public static final void asyncRecursiveDelete$lambda$1(File file, Callback callback) {
        boolean z;
        Intrinsics.checkNotNullParameter(file, "$fileOrDirectory");
        if (file.exists() && file.canWrite()) {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles == null) {
                    listFiles = new File[0];
                }
                for (File file2 : listFiles) {
                    FileUtils fileUtils = INSTANCE;
                    Intrinsics.checkNotNull(file2);
                    fileUtils.asyncRecursiveDelete(file2, (Callback) null);
                }
                z = file.delete();
            } else {
                z = INSTANCE.deleteFile(file.getPath());
            }
            if (callback != null) {
                callback.onResult(z);
            }
        }
    }

    public final boolean canSave(MediaWrapper mediaWrapper) {
        if (mediaWrapper == null || mediaWrapper.getUri() == null) {
            return false;
        }
        return ArraysKt.contains((T[]) new String[]{"file", "smb", "nfs", "ftp", "ftps", "ftpes", "sftp", "upnp"}, mediaWrapper.getUri().getScheme());
    }

    public final boolean canWrite(Uri uri) {
        if (uri == null) {
            return false;
        }
        if (Intrinsics.areEqual((Object) uri.getScheme(), (Object) "file")) {
            return canWrite(uri.getPath());
        }
        return Intrinsics.areEqual((Object) uri.getScheme(), (Object) "content") && canWrite(getPathFromURI(uri));
    }

    public final boolean canWrite(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        return StringsKt.startsWith$default(Strings.removeFileScheme(str), "/", false, 2, (Object) null);
    }

    public final String getMediaStorage(Uri uri) {
        if (uri != null && Intrinsics.areEqual((Object) "file", (Object) uri.getScheme())) {
            String path = uri.getPath();
            CharSequence charSequence = path;
            if (!(charSequence == null || charSequence.length() == 0)) {
                for (String next : AndroidDevices.INSTANCE.getExternalStorageDirectories()) {
                    if (StringsKt.startsWith$default(path, next, false, 2, (Object) null)) {
                        return next;
                    }
                }
            }
        }
        return null;
    }

    public final DocumentFile findFile(Uri uri) {
        Context appContext;
        String mediaStorage;
        List list;
        Intrinsics.checkNotNullParameter(uri, Constants.KEY_URI);
        String path = uri.getPath();
        if (!(path == null || (appContext = AppContextProvider.INSTANCE.getAppContext()) == null || (mediaStorage = INSTANCE.getMediaStorage(uri)) == null)) {
            String string = ((SharedPreferences) Settings.INSTANCE.getInstance(appContext)).getString("tree_uri_" + mediaStorage, (String) null);
            if (string != null) {
                Intrinsics.checkNotNull(string);
                DocumentFile fromTreeUri = DocumentFile.fromTreeUri(appContext, Uri.parse(string));
                List<String> split = new Regex("/").split(path, 0);
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
                String[] strArr = (String[]) list.toArray(new String[0]);
                int length = strArr.length;
                for (int i = 3; i < length; i++) {
                    if (fromTreeUri == null) {
                        return null;
                    }
                    fromTreeUri = fromTreeUri.findFile(strArr[i]);
                }
                return fromTreeUri;
            }
        }
        return null;
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:81:0x026e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.net.Uri getUri(android.net.Uri r23) {
        /*
            r22 = this;
            r7 = r23
            java.lang.String r0 = "/Download/"
            java.lang.String r8 = "_display_name"
            java.lang.String r1 = " for "
            java.lang.String r2 = "fd://"
            org.videolan.resources.AppContextProvider r3 = org.videolan.resources.AppContextProvider.INSTANCE
            android.content.Context r9 = r3.getAppContext()
            java.lang.String r10 = " to "
            java.lang.String r11 = "FileUtils"
            java.lang.String r12 = "Expanding uri: "
            if (r7 == 0) goto L_0x02b0
            java.lang.String r3 = r23.getScheme()
            java.lang.String r4 = "content"
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r4)
            if (r3 == 0) goto L_0x02b0
            java.lang.String r3 = "com.fsck.k9.attachmentprovider"
            java.lang.String r4 = r23.getHost()
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r4)
            java.lang.String r13 = "VLC/FileUtils"
            r14 = 0
            if (r3 != 0) goto L_0x0188
            java.lang.String r3 = "gmail-ls"
            java.lang.String r4 = r23.getHost()
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r4)
            if (r3 == 0) goto L_0x0041
            goto L_0x0188
        L_0x0041:
            java.lang.String r0 = r23.getHost()
            java.lang.String r3 = "com.amaze.filemanager"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r3)
            if (r0 == 0) goto L_0x006e
            java.lang.String r0 = r23.getPath()
            if (r0 == 0) goto L_0x006e
            java.lang.String r15 = r23.getPath()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r15)
            r19 = 4
            r20 = 0
            java.lang.String r16 = "/storage_root"
            java.lang.String r17 = "file://"
            r18 = 0
            java.lang.String r0 = kotlin.text.StringsKt.replace$default((java.lang.String) r15, (java.lang.String) r16, (java.lang.String) r17, (boolean) r18, (int) r19, (java.lang.Object) r20)
            android.net.Uri r0 = android.net.Uri.parse(r0)
            goto L_0x02b1
        L_0x006e:
            java.lang.String r0 = r23.getAuthority()
            java.lang.String r3 = "media"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r3)
            if (r0 == 0) goto L_0x0082
            org.videolan.vlc.media.MediaUtils r0 = org.videolan.vlc.media.MediaUtils.INSTANCE
            android.net.Uri r0 = r0.getContentMediaUri(r7)
            goto L_0x02b1
        L_0x0082:
            java.lang.String r0 = r23.getAuthority()
            int r3 = org.videolan.vlc.R.string.tv_provider_authority
            java.lang.String r3 = r9.getString(r3)
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r3)
            if (r0 == 0) goto L_0x00b0
            org.videolan.medialibrary.interfaces.Medialibrary r0 = org.videolan.medialibrary.interfaces.Medialibrary.getInstance()
            java.lang.String r1 = "getInstance(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.lang.String r1 = r23.getLastPathSegment()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            long r1 = java.lang.Long.parseLong(r1)
            org.videolan.medialibrary.interfaces.media.MediaWrapper r0 = r0.getMedia((long) r1)
            android.net.Uri r0 = r0.getUri()
            goto L_0x02b1
        L_0x00b0:
            org.videolan.vlc.media.MediaUtils r0 = org.videolan.vlc.media.MediaUtils.INSTANCE
            android.net.Uri r0 = r0.getContentMediaUri(r7)
            if (r0 == 0) goto L_0x00bf
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r7)
            if (r3 != 0) goto L_0x00bf
            return r0
        L_0x00bf:
            android.content.ContentResolver r0 = r9.getContentResolver()     // Catch:{ FileNotFoundException -> 0x016b, IllegalArgumentException -> 0x014e, IllegalStateException -> 0x0131, NullPointerException -> 0x0114, SecurityException -> 0x00f7 }
            java.lang.String r3 = "r"
            android.os.ParcelFileDescriptor r0 = r0.openFileDescriptor(r7, r3)     // Catch:{ FileNotFoundException -> 0x016b, IllegalArgumentException -> 0x014e, IllegalStateException -> 0x0131, NullPointerException -> 0x0114, SecurityException -> 0x00f7 }
            if (r0 != 0) goto L_0x00e1
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x016b, IllegalArgumentException -> 0x014e, IllegalStateException -> 0x0131, NullPointerException -> 0x0114, SecurityException -> 0x00f7 }
            r0.<init>(r12)     // Catch:{ FileNotFoundException -> 0x016b, IllegalArgumentException -> 0x014e, IllegalStateException -> 0x0131, NullPointerException -> 0x0114, SecurityException -> 0x00f7 }
            r0.append(r7)     // Catch:{ FileNotFoundException -> 0x016b, IllegalArgumentException -> 0x014e, IllegalStateException -> 0x0131, NullPointerException -> 0x0114, SecurityException -> 0x00f7 }
            r0.append(r10)     // Catch:{ FileNotFoundException -> 0x016b, IllegalArgumentException -> 0x014e, IllegalStateException -> 0x0131, NullPointerException -> 0x0114, SecurityException -> 0x00f7 }
            r0.append(r7)     // Catch:{ FileNotFoundException -> 0x016b, IllegalArgumentException -> 0x014e, IllegalStateException -> 0x0131, NullPointerException -> 0x0114, SecurityException -> 0x00f7 }
            java.lang.String r0 = r0.toString()     // Catch:{ FileNotFoundException -> 0x016b, IllegalArgumentException -> 0x014e, IllegalStateException -> 0x0131, NullPointerException -> 0x0114, SecurityException -> 0x00f7 }
            android.util.Log.i(r11, r0)     // Catch:{ FileNotFoundException -> 0x016b, IllegalArgumentException -> 0x014e, IllegalStateException -> 0x0131, NullPointerException -> 0x0114, SecurityException -> 0x00f7 }
            return r7
        L_0x00e1:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x016b, IllegalArgumentException -> 0x014e, IllegalStateException -> 0x0131, NullPointerException -> 0x0114, SecurityException -> 0x00f7 }
            r3.<init>(r2)     // Catch:{ FileNotFoundException -> 0x016b, IllegalArgumentException -> 0x014e, IllegalStateException -> 0x0131, NullPointerException -> 0x0114, SecurityException -> 0x00f7 }
            int r0 = r0.getFd()     // Catch:{ FileNotFoundException -> 0x016b, IllegalArgumentException -> 0x014e, IllegalStateException -> 0x0131, NullPointerException -> 0x0114, SecurityException -> 0x00f7 }
            r3.append(r0)     // Catch:{ FileNotFoundException -> 0x016b, IllegalArgumentException -> 0x014e, IllegalStateException -> 0x0131, NullPointerException -> 0x0114, SecurityException -> 0x00f7 }
            java.lang.String r0 = r3.toString()     // Catch:{ FileNotFoundException -> 0x016b, IllegalArgumentException -> 0x014e, IllegalStateException -> 0x0131, NullPointerException -> 0x0114, SecurityException -> 0x00f7 }
            android.net.Uri r0 = org.videolan.libvlc.util.AndroidUtil.LocationToUri(r0)     // Catch:{ FileNotFoundException -> 0x016b, IllegalArgumentException -> 0x014e, IllegalStateException -> 0x0131, NullPointerException -> 0x0114, SecurityException -> 0x00f7 }
            goto L_0x02b1
        L_0x00f7:
            r0 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = r0.getMessage()
            r2.append(r3)
            r2.append(r1)
            r2.append(r7)
            java.lang.String r1 = r2.toString()
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            android.util.Log.e(r13, r1, r0)
            return r14
        L_0x0114:
            r0 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = r0.getMessage()
            r2.append(r3)
            r2.append(r1)
            r2.append(r7)
            java.lang.String r1 = r2.toString()
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            android.util.Log.e(r13, r1, r0)
            return r14
        L_0x0131:
            r0 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = r0.getMessage()
            r2.append(r3)
            r2.append(r1)
            r2.append(r7)
            java.lang.String r1 = r2.toString()
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            android.util.Log.e(r13, r1, r0)
            return r14
        L_0x014e:
            r0 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = r0.getMessage()
            r2.append(r3)
            r2.append(r1)
            r2.append(r7)
            java.lang.String r1 = r2.toString()
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            android.util.Log.e(r13, r1, r0)
            return r14
        L_0x016b:
            r0 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = r0.getMessage()
            r2.append(r3)
            r2.append(r1)
            r2.append(r7)
            java.lang.String r1 = r2.toString()
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            android.util.Log.e(r13, r1, r0)
            return r14
        L_0x0188:
            android.content.ContentResolver r1 = r9.getContentResolver()     // Catch:{ Exception -> 0x026b, all -> 0x0267 }
            r2 = 1
            java.lang.String[] r3 = new java.lang.String[r2]     // Catch:{ Exception -> 0x026b, all -> 0x0267 }
            r15 = 0
            r3[r15] = r8     // Catch:{ Exception -> 0x026b, all -> 0x0267 }
            r5 = 0
            r6 = 0
            r4 = 0
            r2 = r23
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x026b, all -> 0x0267 }
            if (r1 == 0) goto L_0x024f
            boolean r2 = r1.moveToFirst()     // Catch:{ Exception -> 0x024d, all -> 0x024a }
            if (r2 == 0) goto L_0x024f
            int r2 = r1.getColumnIndex(r8)     // Catch:{ Exception -> 0x024d, all -> 0x024a }
            java.lang.String r2 = r1.getString(r2)     // Catch:{ Exception -> 0x024d, all -> 0x024a }
            java.lang.String r3 = "getString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)     // Catch:{ Exception -> 0x024d, all -> 0x024a }
            java.lang.String r17 = "/"
            java.lang.String r18 = ""
            r20 = 4
            r21 = 0
            r19 = 0
            r16 = r2
            java.lang.String r2 = kotlin.text.StringsKt.replace$default((java.lang.String) r16, (java.lang.String) r17, (java.lang.String) r18, (boolean) r19, (int) r20, (java.lang.Object) r21)     // Catch:{ Exception -> 0x024d, all -> 0x024a }
            android.content.ContentResolver r3 = r9.getContentResolver()     // Catch:{ Exception -> 0x024d, all -> 0x024a }
            java.io.InputStream r3 = r3.openInputStream(r7)     // Catch:{ Exception -> 0x024d, all -> 0x024a }
            if (r3 != 0) goto L_0x01f3
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0248, all -> 0x0245 }
            r0.<init>(r12)     // Catch:{ Exception -> 0x0248, all -> 0x0245 }
            r0.append(r7)     // Catch:{ Exception -> 0x0248, all -> 0x0245 }
            r0.append(r10)     // Catch:{ Exception -> 0x0248, all -> 0x0245 }
            r0.append(r7)     // Catch:{ Exception -> 0x0248, all -> 0x0245 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0248, all -> 0x0245 }
            android.util.Log.i(r11, r0)     // Catch:{ Exception -> 0x0248, all -> 0x0245 }
            org.videolan.tools.CloseableUtils r0 = org.videolan.tools.CloseableUtils.INSTANCE
            java.io.Closeable r3 = (java.io.Closeable) r3
            r0.close(r3)
            org.videolan.tools.CloseableUtils r0 = org.videolan.tools.CloseableUtils.INSTANCE
            r0.close(r14)
            org.videolan.tools.CloseableUtils r0 = org.videolan.tools.CloseableUtils.INSTANCE
            java.io.Closeable r1 = (java.io.Closeable) r1
            r0.close(r1)
            return r7
        L_0x01f3:
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0248, all -> 0x0245 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0248, all -> 0x0245 }
            r5.<init>()     // Catch:{ Exception -> 0x0248, all -> 0x0245 }
            org.videolan.resources.AndroidDevices r6 = org.videolan.resources.AndroidDevices.INSTANCE     // Catch:{ Exception -> 0x0248, all -> 0x0245 }
            java.lang.String r6 = r6.getEXTERNAL_PUBLIC_DIRECTORY()     // Catch:{ Exception -> 0x0248, all -> 0x0245 }
            r5.append(r6)     // Catch:{ Exception -> 0x0248, all -> 0x0245 }
            r5.append(r0)     // Catch:{ Exception -> 0x0248, all -> 0x0245 }
            r5.append(r2)     // Catch:{ Exception -> 0x0248, all -> 0x0245 }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x0248, all -> 0x0245 }
            r4.<init>(r5)     // Catch:{ Exception -> 0x0248, all -> 0x0245 }
            java.io.OutputStream r4 = (java.io.OutputStream) r4     // Catch:{ Exception -> 0x0248, all -> 0x0245 }
            r5 = 1024(0x400, float:1.435E-42)
            byte[] r5 = new byte[r5]     // Catch:{ Exception -> 0x026e }
            int r6 = r3.read(r5)     // Catch:{ Exception -> 0x026e }
        L_0x021a:
            if (r6 < 0) goto L_0x0227
            r8 = r4
            java.io.FileOutputStream r8 = (java.io.FileOutputStream) r8     // Catch:{ Exception -> 0x026e }
            r8.write(r5, r15, r6)     // Catch:{ Exception -> 0x026e }
            int r6 = r3.read(r5)     // Catch:{ Exception -> 0x026e }
            goto L_0x021a
        L_0x0227:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x026e }
            r5.<init>()     // Catch:{ Exception -> 0x026e }
            org.videolan.resources.AndroidDevices r6 = org.videolan.resources.AndroidDevices.INSTANCE     // Catch:{ Exception -> 0x026e }
            java.lang.String r6 = r6.getEXTERNAL_PUBLIC_DIRECTORY()     // Catch:{ Exception -> 0x026e }
            r5.append(r6)     // Catch:{ Exception -> 0x026e }
            r5.append(r0)     // Catch:{ Exception -> 0x026e }
            r5.append(r2)     // Catch:{ Exception -> 0x026e }
            java.lang.String r0 = r5.toString()     // Catch:{ Exception -> 0x026e }
            android.net.Uri r0 = org.videolan.libvlc.util.AndroidUtil.PathToUri(r0)     // Catch:{ Exception -> 0x026e }
            r14 = r3
            goto L_0x0251
        L_0x0245:
            r0 = move-exception
            r4 = r14
            goto L_0x0299
        L_0x0248:
            r4 = r14
            goto L_0x026e
        L_0x024a:
            r0 = move-exception
            r4 = r14
            goto L_0x029a
        L_0x024d:
            r3 = r14
            goto L_0x026d
        L_0x024f:
            r0 = r7
            r4 = r14
        L_0x0251:
            org.videolan.tools.CloseableUtils r2 = org.videolan.tools.CloseableUtils.INSTANCE
            java.io.Closeable r14 = (java.io.Closeable) r14
            r2.close(r14)
            org.videolan.tools.CloseableUtils r2 = org.videolan.tools.CloseableUtils.INSTANCE
            java.io.Closeable r4 = (java.io.Closeable) r4
            r2.close(r4)
            org.videolan.tools.CloseableUtils r2 = org.videolan.tools.CloseableUtils.INSTANCE
            java.io.Closeable r1 = (java.io.Closeable) r1
            r2.close(r1)
            goto L_0x02b1
        L_0x0267:
            r0 = move-exception
            r1 = r14
            r4 = r1
            goto L_0x029a
        L_0x026b:
            r1 = r14
            r3 = r1
        L_0x026d:
            r4 = r3
        L_0x026e:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0298 }
            r0.<init>()     // Catch:{ all -> 0x0298 }
            java.lang.String r2 = "Couldn't download file from mail URI: "
            r0.append(r2)     // Catch:{ all -> 0x0298 }
            r0.append(r7)     // Catch:{ all -> 0x0298 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0298 }
            android.util.Log.e(r13, r0)     // Catch:{ all -> 0x0298 }
            org.videolan.tools.CloseableUtils r0 = org.videolan.tools.CloseableUtils.INSTANCE
            java.io.Closeable r3 = (java.io.Closeable) r3
            r0.close(r3)
            org.videolan.tools.CloseableUtils r0 = org.videolan.tools.CloseableUtils.INSTANCE
            java.io.Closeable r4 = (java.io.Closeable) r4
            r0.close(r4)
            org.videolan.tools.CloseableUtils r0 = org.videolan.tools.CloseableUtils.INSTANCE
            java.io.Closeable r1 = (java.io.Closeable) r1
            r0.close(r1)
            return r14
        L_0x0298:
            r0 = move-exception
        L_0x0299:
            r14 = r3
        L_0x029a:
            org.videolan.tools.CloseableUtils r2 = org.videolan.tools.CloseableUtils.INSTANCE
            java.io.Closeable r14 = (java.io.Closeable) r14
            r2.close(r14)
            org.videolan.tools.CloseableUtils r2 = org.videolan.tools.CloseableUtils.INSTANCE
            java.io.Closeable r4 = (java.io.Closeable) r4
            r2.close(r4)
            org.videolan.tools.CloseableUtils r2 = org.videolan.tools.CloseableUtils.INSTANCE
            java.io.Closeable r1 = (java.io.Closeable) r1
            r2.close(r1)
            throw r0
        L_0x02b0:
            r0 = r7
        L_0x02b1:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r12)
            r1.append(r7)
            r1.append(r10)
            r1.append(r0)
            java.lang.String r1 = r1.toString()
            android.util.Log.i(r11, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.util.FileUtils.getUri(android.net.Uri):android.net.Uri");
    }

    public final String getStorageTag(String str) {
        Intrinsics.checkNotNullParameter(str, "uuid");
        if (!AndroidUtil.isMarshMallowOrLater) {
            return null;
        }
        try {
            StorageManager storageManager = (StorageManager) AppContextProvider.INSTANCE.getAppContext().getSystemService(StorageManager.class);
            Class<?> cls = storageManager.getClass();
            Method declaredMethod = cls.getDeclaredMethod("findVolumeByUuid", new Class[]{str.getClass()});
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(storageManager, new Object[]{str});
            Method declaredMethod2 = cls.getDeclaredMethod("getBestVolumeDescription", new Class[]{Class.forName("android.os.storage.VolumeInfo")});
            declaredMethod2.setAccessible(true);
            Object invoke2 = declaredMethod2.invoke(storageManager, new Object[]{invoke});
            Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type kotlin.String");
            return (String) invoke2;
        } catch (Throwable unused) {
            return null;
        }
    }

    public final Object unpackZip(String str, String str2, Continuation<? super ArrayList<String>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new FileUtils$unpackZip$2(str2, str, (Continuation<? super FileUtils$unpackZip$2>) null), continuation);
    }

    public final boolean zip(String[] strArr, String str) {
        Throwable th;
        String[] strArr2 = strArr;
        String str2 = str;
        Intrinsics.checkNotNullParameter(strArr2, "files");
        Intrinsics.checkNotNullParameter(str2, "zipFileName");
        try {
            Closeable zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(str2)));
            try {
                ZipOutputStream zipOutputStream2 = (ZipOutputStream) zipOutputStream;
                byte[] bArr = new byte[2048];
                int length = strArr2.length;
                int i = 0;
                while (i < length) {
                    th = new BufferedInputStream(new FileInputStream(strArr2[i]), 2048);
                    try {
                        BufferedInputStream bufferedInputStream = (BufferedInputStream) th;
                        String str3 = strArr2[i];
                        String substring = str3.substring(StringsKt.lastIndexOf$default((CharSequence) str3, "/", 0, false, 6, (Object) null) + 1);
                        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
                        zipOutputStream2.putNextEntry(new ZipEntry(substring));
                        for (int read = bufferedInputStream.read(bArr, 0, 2048); read != -1; read = bufferedInputStream.read(bArr, 0, 2048)) {
                            zipOutputStream2.write(bArr, 0, read);
                        }
                        Unit unit = Unit.INSTANCE;
                        CloseableKt.closeFinally(th, (Throwable) null);
                        i++;
                    } catch (Throwable th2) {
                        Throwable th3 = th2;
                        CloseableKt.closeFinally(th, th);
                        throw th3;
                    }
                }
                Unit unit2 = Unit.INSTANCE;
                CloseableKt.closeFinally(zipOutputStream, (Throwable) null);
                return true;
            } finally {
                Closeable closeable = th;
                try {
                } catch (Throwable th4) {
                    Throwable th5 = th4;
                    CloseableKt.closeFinally(zipOutputStream, closeable);
                    throw th5;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x007c, code lost:
        r13 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r8, r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0080, code lost:
        throw r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x008a, code lost:
        r13 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r1, r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x008e, code lost:
        throw r13;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zipWithName(kotlin.Pair<java.lang.String, java.lang.String>[] r12, java.lang.String r13) {
        /*
            r11 = this;
            java.lang.String r0 = "files"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)
            java.lang.String r0 = "zipFileName"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch:{ Exception -> 0x008f }
            r1.<init>(r13)     // Catch:{ Exception -> 0x008f }
            java.io.File r1 = r1.getParentFile()     // Catch:{ Exception -> 0x008f }
            if (r1 == 0) goto L_0x0019
            r1.mkdirs()     // Catch:{ Exception -> 0x008f }
        L_0x0019:
            java.util.zip.ZipOutputStream r1 = new java.util.zip.ZipOutputStream     // Catch:{ Exception -> 0x008f }
            java.io.BufferedOutputStream r2 = new java.io.BufferedOutputStream     // Catch:{ Exception -> 0x008f }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x008f }
            r3.<init>(r13)     // Catch:{ Exception -> 0x008f }
            java.io.OutputStream r3 = (java.io.OutputStream) r3     // Catch:{ Exception -> 0x008f }
            r2.<init>(r3)     // Catch:{ Exception -> 0x008f }
            java.io.OutputStream r2 = (java.io.OutputStream) r2     // Catch:{ Exception -> 0x008f }
            r1.<init>(r2)     // Catch:{ Exception -> 0x008f }
            java.io.Closeable r1 = (java.io.Closeable) r1     // Catch:{ Exception -> 0x008f }
            r13 = r1
            java.util.zip.ZipOutputStream r13 = (java.util.zip.ZipOutputStream) r13     // Catch:{ all -> 0x0088 }
            r2 = 2048(0x800, float:2.87E-42)
            byte[] r3 = new byte[r2]     // Catch:{ all -> 0x0088 }
            int r4 = r12.length     // Catch:{ all -> 0x0088 }
            r5 = 0
        L_0x0037:
            r6 = 0
            if (r5 >= r4) goto L_0x0081
            java.io.FileInputStream r7 = new java.io.FileInputStream     // Catch:{ all -> 0x0088 }
            r8 = r12[r5]     // Catch:{ all -> 0x0088 }
            java.lang.Object r8 = r8.getFirst()     // Catch:{ all -> 0x0088 }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ all -> 0x0088 }
            r7.<init>(r8)     // Catch:{ all -> 0x0088 }
            java.io.BufferedInputStream r8 = new java.io.BufferedInputStream     // Catch:{ all -> 0x0088 }
            java.io.InputStream r7 = (java.io.InputStream) r7     // Catch:{ all -> 0x0088 }
            r8.<init>(r7, r2)     // Catch:{ all -> 0x0088 }
            java.io.Closeable r8 = (java.io.Closeable) r8     // Catch:{ all -> 0x0088 }
            r7 = r8
            java.io.BufferedInputStream r7 = (java.io.BufferedInputStream) r7     // Catch:{ all -> 0x007a }
            java.util.zip.ZipEntry r9 = new java.util.zip.ZipEntry     // Catch:{ all -> 0x007a }
            r10 = r12[r5]     // Catch:{ all -> 0x007a }
            java.lang.Object r10 = r10.getSecond()     // Catch:{ all -> 0x007a }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ all -> 0x007a }
            r9.<init>(r10)     // Catch:{ all -> 0x007a }
            r13.putNextEntry(r9)     // Catch:{ all -> 0x007a }
            int r9 = r7.read(r3, r0, r2)     // Catch:{ all -> 0x007a }
        L_0x0067:
            r10 = -1
            if (r9 == r10) goto L_0x0072
            r13.write(r3, r0, r9)     // Catch:{ all -> 0x007a }
            int r9 = r7.read(r3, r0, r2)     // Catch:{ all -> 0x007a }
            goto L_0x0067
        L_0x0072:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x007a }
            kotlin.io.CloseableKt.closeFinally(r8, r6)     // Catch:{ all -> 0x0088 }
            int r5 = r5 + 1
            goto L_0x0037
        L_0x007a:
            r12 = move-exception
            throw r12     // Catch:{ all -> 0x007c }
        L_0x007c:
            r13 = move-exception
            kotlin.io.CloseableKt.closeFinally(r8, r12)     // Catch:{ all -> 0x0088 }
            throw r13     // Catch:{ all -> 0x0088 }
        L_0x0081:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0088 }
            kotlin.io.CloseableKt.closeFinally(r1, r6)     // Catch:{ Exception -> 0x008f }
            r0 = 1
            goto L_0x009b
        L_0x0088:
            r12 = move-exception
            throw r12     // Catch:{ all -> 0x008a }
        L_0x008a:
            r13 = move-exception
            kotlin.io.CloseableKt.closeFinally(r1, r12)     // Catch:{ Exception -> 0x008f }
            throw r13     // Catch:{ Exception -> 0x008f }
        L_0x008f:
            r12 = move-exception
            java.lang.String r13 = r12.getMessage()
            java.lang.Throwable r12 = (java.lang.Throwable) r12
            java.lang.String r1 = "VLC/FileUtils"
            android.util.Log.e(r1, r13, r12)
        L_0x009b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.util.FileUtils.zipWithName(kotlin.Pair[], java.lang.String):boolean");
    }

    public final String convertStreamToString(InputStream inputStream) throws Exception {
        Intrinsics.checkNotNullParameter(inputStream, "is");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
            sb.append(readLine);
            sb.append("\n");
        }
        bufferedReader.close();
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }

    public final String getStringFromFile(String str) throws Exception {
        Intrinsics.checkNotNullParameter(str, "filePath");
        FileInputStream fileInputStream = new FileInputStream(new File(str));
        String convertStreamToString = convertStreamToString(fileInputStream);
        fileInputStream.close();
        return convertStreamToString;
    }

    public final String[] getSoundFontExtensions() {
        return new String[]{"sf2", "sf3"};
    }
}
