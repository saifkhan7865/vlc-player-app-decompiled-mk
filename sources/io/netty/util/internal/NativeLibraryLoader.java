package io.netty.util.internal;

import io.netty.util.CharsetUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.security.AccessController;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Enumeration;
import java.util.Set;
import kotlin.io.path.LinkFollowing$$ExternalSyntheticApiModelOutline0;
import org.fusesource.jansi.AnsiRenderer;

public final class NativeLibraryLoader {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final boolean DELETE_NATIVE_LIB_AFTER_LOADING;
    private static final boolean DETECT_NATIVE_LIBRARY_DUPLICATES;
    private static final String NATIVE_RESOURCE_HOME = "META-INF/native/";
    private static final boolean TRY_TO_PATCH_SHADED_ID;
    private static final byte[] UNIQUE_ID_BYTES = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".getBytes(CharsetUtil.US_ASCII);
    private static final File WORKDIR;
    private static final InternalLogger logger;

    static {
        InternalLogger instance = InternalLoggerFactory.getInstance((Class<?>) NativeLibraryLoader.class);
        logger = instance;
        String str = SystemPropertyUtil.get("io.netty.native.workdir");
        if (str != null) {
            File file = new File(str);
            file.mkdirs();
            try {
                file = file.getAbsoluteFile();
            } catch (Exception unused) {
            }
            WORKDIR = file;
            InternalLogger internalLogger = logger;
            internalLogger.debug("-Dio.netty.native.workdir: " + file);
        } else {
            File tmpdir = PlatformDependent.tmpdir();
            WORKDIR = tmpdir;
            instance.debug("-Dio.netty.native.workdir: " + tmpdir + " (io.netty.tmpdir)");
        }
        boolean z = SystemPropertyUtil.getBoolean("io.netty.native.deleteLibAfterLoading", true);
        DELETE_NATIVE_LIB_AFTER_LOADING = z;
        InternalLogger internalLogger2 = logger;
        internalLogger2.debug("-Dio.netty.native.deleteLibAfterLoading: {}", (Object) Boolean.valueOf(z));
        boolean z2 = SystemPropertyUtil.getBoolean("io.netty.native.tryPatchShadedId", true);
        TRY_TO_PATCH_SHADED_ID = z2;
        internalLogger2.debug("-Dio.netty.native.tryPatchShadedId: {}", (Object) Boolean.valueOf(z2));
        boolean z3 = SystemPropertyUtil.getBoolean("io.netty.native.detectNativeLibraryDuplicates", true);
        DETECT_NATIVE_LIBRARY_DUPLICATES = z3;
        internalLogger2.debug("-Dio.netty.native.detectNativeLibraryDuplicates: {}", (Object) Boolean.valueOf(z3));
    }

    public static void loadFirstAvailable(ClassLoader classLoader, String... strArr) {
        ArrayList arrayList = new ArrayList();
        int length = strArr.length;
        int i = 0;
        while (i < length) {
            String str = strArr[i];
            try {
                load(str, classLoader);
                logger.debug("Loaded library with name '{}'", (Object) str);
                return;
            } catch (Throwable th) {
                arrayList.add(th);
                i++;
            }
        }
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Failed to load any of the given libraries: " + Arrays.toString(strArr));
        ThrowableUtil.addSuppressedAndClear(illegalArgumentException, arrayList);
        throw illegalArgumentException;
    }

    private static String calculateMangledPackagePrefix() {
        String name = NativeLibraryLoader.class.getName();
        String replace = "io!netty!util!internal!NativeLibraryLoader".replace('!', '.');
        if (name.endsWith(replace)) {
            return name.substring(0, name.length() - replace.length()).replace("_", "_1").replace('.', '_');
        }
        throw new UnsatisfiedLinkError(String.format("Could not find prefix added to %s to get %s. When shading, only adding a package prefix is supported", new Object[]{replace, name}));
    }

    /* JADX WARNING: type inference failed for: r8v0 */
    /* JADX WARNING: type inference failed for: r8v1, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r8v2 */
    /* JADX WARNING: type inference failed for: r8v3, types: [java.lang.Object, java.io.File] */
    /* JADX WARNING: type inference failed for: r8v4 */
    /* JADX WARNING: type inference failed for: r8v5 */
    /* JADX WARNING: type inference failed for: r8v6 */
    /* JADX WARNING: type inference failed for: r8v7 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0125 A[SYNTHETIC, Splitter:B:75:0x0125] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void load(java.lang.String r10, java.lang.ClassLoader r11) {
        /*
            java.lang.String r0 = ".jnilib"
            java.lang.String r1 = calculateMangledPackagePrefix()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r1)
            r2.append(r10)
            java.lang.String r2 = r2.toString()
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            r4 = 0
            loadLibrary(r11, r2, r4)     // Catch:{ all -> 0x001f }
            return
        L_0x001f:
            r5 = move-exception
            r3.add(r5)
            java.lang.String r5 = java.lang.System.mapLibraryName(r2)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "META-INF/native/"
            r6.<init>(r7)
            r6.append(r5)
            java.lang.String r6 = r6.toString()
            java.net.URL r7 = getResource(r6, r11)
            r8 = 0
            if (r7 != 0) goto L_0x0084
            boolean r7 = io.netty.util.internal.PlatformDependent.isOsx()     // Catch:{ UnsatisfiedLinkError -> 0x0120, Exception -> 0x00fe, all -> 0x00fa }
            if (r7 == 0) goto L_0x007b
            boolean r6 = r6.endsWith(r0)     // Catch:{ UnsatisfiedLinkError -> 0x0120, Exception -> 0x00fe, all -> 0x00fa }
            java.lang.String r7 = "META-INF/native/lib"
            if (r6 == 0) goto L_0x005c
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ UnsatisfiedLinkError -> 0x0120, Exception -> 0x00fe, all -> 0x00fa }
            r0.<init>(r7)     // Catch:{ UnsatisfiedLinkError -> 0x0120, Exception -> 0x00fe, all -> 0x00fa }
            r0.append(r2)     // Catch:{ UnsatisfiedLinkError -> 0x0120, Exception -> 0x00fe, all -> 0x00fa }
            java.lang.String r6 = ".dynlib"
            r0.append(r6)     // Catch:{ UnsatisfiedLinkError -> 0x0120, Exception -> 0x00fe, all -> 0x00fa }
            java.lang.String r0 = r0.toString()     // Catch:{ UnsatisfiedLinkError -> 0x0120, Exception -> 0x00fe, all -> 0x00fa }
            goto L_0x006b
        L_0x005c:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ UnsatisfiedLinkError -> 0x0120, Exception -> 0x00fe, all -> 0x00fa }
            r6.<init>(r7)     // Catch:{ UnsatisfiedLinkError -> 0x0120, Exception -> 0x00fe, all -> 0x00fa }
            r6.append(r2)     // Catch:{ UnsatisfiedLinkError -> 0x0120, Exception -> 0x00fe, all -> 0x00fa }
            r6.append(r0)     // Catch:{ UnsatisfiedLinkError -> 0x0120, Exception -> 0x00fe, all -> 0x00fa }
            java.lang.String r0 = r6.toString()     // Catch:{ UnsatisfiedLinkError -> 0x0120, Exception -> 0x00fe, all -> 0x00fa }
        L_0x006b:
            java.net.URL r7 = getResource(r0, r11)     // Catch:{ UnsatisfiedLinkError -> 0x0120, Exception -> 0x00fe, all -> 0x00fa }
            if (r7 == 0) goto L_0x0072
            goto L_0x0084
        L_0x0072:
            java.io.FileNotFoundException r10 = new java.io.FileNotFoundException     // Catch:{ UnsatisfiedLinkError -> 0x0120, Exception -> 0x00fe, all -> 0x00fa }
            r10.<init>(r0)     // Catch:{ UnsatisfiedLinkError -> 0x0120, Exception -> 0x00fe, all -> 0x00fa }
            io.netty.util.internal.ThrowableUtil.addSuppressedAndClear(r10, r3)     // Catch:{ UnsatisfiedLinkError -> 0x0120, Exception -> 0x00fe, all -> 0x00fa }
            throw r10     // Catch:{ UnsatisfiedLinkError -> 0x0120, Exception -> 0x00fe, all -> 0x00fa }
        L_0x007b:
            java.io.FileNotFoundException r10 = new java.io.FileNotFoundException     // Catch:{ UnsatisfiedLinkError -> 0x0120, Exception -> 0x00fe, all -> 0x00fa }
            r10.<init>(r6)     // Catch:{ UnsatisfiedLinkError -> 0x0120, Exception -> 0x00fe, all -> 0x00fa }
            io.netty.util.internal.ThrowableUtil.addSuppressedAndClear(r10, r3)     // Catch:{ UnsatisfiedLinkError -> 0x0120, Exception -> 0x00fe, all -> 0x00fa }
            throw r10     // Catch:{ UnsatisfiedLinkError -> 0x0120, Exception -> 0x00fe, all -> 0x00fa }
        L_0x0084:
            r0 = 46
            int r0 = r5.lastIndexOf(r0)     // Catch:{ UnsatisfiedLinkError -> 0x0120, Exception -> 0x00fe, all -> 0x00fa }
            java.lang.String r6 = r5.substring(r4, r0)     // Catch:{ UnsatisfiedLinkError -> 0x0120, Exception -> 0x00fe, all -> 0x00fa }
            java.lang.String r0 = r5.substring(r0)     // Catch:{ UnsatisfiedLinkError -> 0x0120, Exception -> 0x00fe, all -> 0x00fa }
            java.io.File r5 = WORKDIR     // Catch:{ UnsatisfiedLinkError -> 0x0120, Exception -> 0x00fe, all -> 0x00fa }
            java.io.File r0 = io.netty.util.internal.PlatformDependent.createTempFile(r6, r0, r5)     // Catch:{ UnsatisfiedLinkError -> 0x0120, Exception -> 0x00fe, all -> 0x00fa }
            java.io.InputStream r5 = r7.openStream()     // Catch:{ UnsatisfiedLinkError -> 0x00f5, Exception -> 0x00f2, all -> 0x00ee }
            java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch:{ UnsatisfiedLinkError -> 0x00eb, Exception -> 0x00e7, all -> 0x00e3 }
            r6.<init>(r0)     // Catch:{ UnsatisfiedLinkError -> 0x00eb, Exception -> 0x00e7, all -> 0x00e3 }
            r7 = 8192(0x2000, float:1.14794E-41)
            byte[] r7 = new byte[r7]     // Catch:{ UnsatisfiedLinkError -> 0x00e1, Exception -> 0x00df, all -> 0x00dc }
        L_0x00a5:
            int r9 = r5.read(r7)     // Catch:{ UnsatisfiedLinkError -> 0x00e1, Exception -> 0x00df, all -> 0x00dc }
            if (r9 <= 0) goto L_0x00af
            r6.write(r7, r4, r9)     // Catch:{ UnsatisfiedLinkError -> 0x00e1, Exception -> 0x00df, all -> 0x00dc }
            goto L_0x00a5
        L_0x00af:
            r6.flush()     // Catch:{ UnsatisfiedLinkError -> 0x00e1, Exception -> 0x00df, all -> 0x00dc }
            boolean r1 = shouldShadedLibraryIdBePatched(r1)     // Catch:{ UnsatisfiedLinkError -> 0x00e1, Exception -> 0x00df, all -> 0x00dc }
            if (r1 == 0) goto L_0x00bb
            tryPatchShadedLibraryIdAndSign(r0, r10)     // Catch:{ UnsatisfiedLinkError -> 0x00e1, Exception -> 0x00df, all -> 0x00dc }
        L_0x00bb:
            closeQuietly(r6)     // Catch:{ UnsatisfiedLinkError -> 0x00e1, Exception -> 0x00df, all -> 0x00dc }
            java.lang.String r10 = r0.getPath()     // Catch:{ UnsatisfiedLinkError -> 0x00eb, Exception -> 0x00e7, all -> 0x00e3 }
            r1 = 1
            loadLibrary(r11, r10, r1)     // Catch:{ UnsatisfiedLinkError -> 0x00eb, Exception -> 0x00e7, all -> 0x00e3 }
            closeQuietly(r5)
            closeQuietly(r8)
            if (r0 == 0) goto L_0x00db
            boolean r10 = DELETE_NATIVE_LIB_AFTER_LOADING
            if (r10 == 0) goto L_0x00d8
            boolean r10 = r0.delete()
            if (r10 != 0) goto L_0x00db
        L_0x00d8:
            r0.deleteOnExit()
        L_0x00db:
            return
        L_0x00dc:
            r10 = move-exception
            goto L_0x0156
        L_0x00df:
            r10 = move-exception
            goto L_0x00e9
        L_0x00e1:
            r10 = move-exception
            goto L_0x00f8
        L_0x00e3:
            r10 = move-exception
            r6 = r8
            goto L_0x0156
        L_0x00e7:
            r10 = move-exception
            r6 = r8
        L_0x00e9:
            r8 = r5
            goto L_0x0101
        L_0x00eb:
            r10 = move-exception
            r6 = r8
            goto L_0x00f8
        L_0x00ee:
            r10 = move-exception
            r6 = r8
            goto L_0x0157
        L_0x00f2:
            r10 = move-exception
            r6 = r8
            goto L_0x0101
        L_0x00f5:
            r10 = move-exception
            r5 = r8
            r6 = r5
        L_0x00f8:
            r8 = r0
            goto L_0x0123
        L_0x00fa:
            r10 = move-exception
            r0 = r8
            r6 = r0
            goto L_0x0157
        L_0x00fe:
            r10 = move-exception
            r0 = r8
            r6 = r0
        L_0x0101:
            java.lang.UnsatisfiedLinkError r11 = new java.lang.UnsatisfiedLinkError     // Catch:{ all -> 0x011e }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x011e }
            r1.<init>()     // Catch:{ all -> 0x011e }
            java.lang.String r4 = "could not load a native library: "
            r1.append(r4)     // Catch:{ all -> 0x011e }
            r1.append(r2)     // Catch:{ all -> 0x011e }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x011e }
            r11.<init>(r1)     // Catch:{ all -> 0x011e }
            r11.initCause(r10)     // Catch:{ all -> 0x011e }
            io.netty.util.internal.ThrowableUtil.addSuppressedAndClear(r11, r3)     // Catch:{ all -> 0x011e }
            throw r11     // Catch:{ all -> 0x011e }
        L_0x011e:
            r10 = move-exception
            goto L_0x0157
        L_0x0120:
            r10 = move-exception
            r5 = r8
            r6 = r5
        L_0x0123:
            if (r8 == 0) goto L_0x0150
            boolean r11 = r8.isFile()     // Catch:{ all -> 0x0145 }
            if (r11 == 0) goto L_0x0150
            boolean r11 = r8.canRead()     // Catch:{ all -> 0x0145 }
            if (r11 == 0) goto L_0x0150
            boolean r11 = io.netty.util.internal.NativeLibraryLoader.NoexecVolumeDetector.canExecuteExecutable(r8)     // Catch:{ all -> 0x0145 }
            if (r11 != 0) goto L_0x0150
            io.netty.util.internal.logging.InternalLogger r11 = logger     // Catch:{ all -> 0x0145 }
            java.lang.String r0 = "{} exists but cannot be executed even when execute permissions set; check volume for \"noexec\" flag; use -D{}=[path] to set native working directory separately."
            java.lang.String r1 = r8.getPath()     // Catch:{ all -> 0x0145 }
            java.lang.String r2 = "io.netty.native.workdir"
            r11.info(r0, r1, r2)     // Catch:{ all -> 0x0145 }
            goto L_0x0150
        L_0x0145:
            r11 = move-exception
            r3.add(r11)     // Catch:{ all -> 0x0154 }
            io.netty.util.internal.logging.InternalLogger r0 = logger     // Catch:{ all -> 0x0154 }
            java.lang.String r1 = "Error checking if {} is on a file store mounted with noexec"
            r0.debug(r1, r8, r11)     // Catch:{ all -> 0x0154 }
        L_0x0150:
            io.netty.util.internal.ThrowableUtil.addSuppressedAndClear(r10, r3)     // Catch:{ all -> 0x0154 }
            throw r10     // Catch:{ all -> 0x0154 }
        L_0x0154:
            r10 = move-exception
            r0 = r8
        L_0x0156:
            r8 = r5
        L_0x0157:
            closeQuietly(r8)
            closeQuietly(r6)
            if (r0 == 0) goto L_0x016c
            boolean r11 = DELETE_NATIVE_LIB_AFTER_LOADING
            if (r11 == 0) goto L_0x0169
            boolean r11 = r0.delete()
            if (r11 != 0) goto L_0x016c
        L_0x0169:
            r0.deleteOnExit()
        L_0x016c:
            goto L_0x016e
        L_0x016d:
            throw r10
        L_0x016e:
            goto L_0x016d
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.util.internal.NativeLibraryLoader.load(java.lang.String, java.lang.ClassLoader):void");
    }

    private static URL getResource(String str, ClassLoader classLoader) {
        Enumeration<URL> enumeration;
        if (classLoader == null) {
            try {
                enumeration = ClassLoader.getSystemResources(str);
            } catch (IOException e) {
                throw new RuntimeException("An error occurred while getting the resources for " + str, e);
            }
        } else {
            enumeration = classLoader.getResources(str);
        }
        ArrayList<T> list = Collections.list(enumeration);
        int size = list.size();
        if (size == 0) {
            return null;
        }
        int i = 1;
        if (size == 1) {
            return (URL) list.get(0);
        }
        if (DETECT_NATIVE_LIBRARY_DUPLICATES) {
            try {
                MessageDigest instance = MessageDigest.getInstance("SHA-256");
                URL url = (URL) list.get(0);
                byte[] digest = digest(instance, url);
                if (digest != null) {
                    while (i < size) {
                        byte[] digest2 = digest(instance, (URL) list.get(i));
                        if (digest2 != null && Arrays.equals(digest, digest2)) {
                            i++;
                        }
                    }
                    return url;
                }
            } catch (NoSuchAlgorithmException e2) {
                logger.debug("Don't support SHA-256, can't check if resources have same content.", (Throwable) e2);
            }
            throw new IllegalStateException("Multiple resources found for '" + str + "' with different content: " + list);
        }
        InternalLogger internalLogger = logger;
        internalLogger.warn("Multiple resources found for '" + str + "' with different content: " + list + ". Please fix your dependency graph.");
        return (URL) list.get(0);
    }

    private static byte[] digest(MessageDigest messageDigest, URL url) {
        InputStream inputStream;
        InputStream inputStream2 = null;
        try {
            inputStream = url.openStream();
            try {
                byte[] bArr = new byte[8192];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read != -1) {
                        messageDigest.update(bArr, 0, read);
                    } else {
                        byte[] digest = messageDigest.digest();
                        closeQuietly(inputStream);
                        return digest;
                    }
                }
            } catch (IOException e) {
                e = e;
                try {
                    logger.debug("Can't read resource.", (Throwable) e);
                    closeQuietly(inputStream);
                    return null;
                } catch (Throwable th) {
                    th = th;
                    inputStream2 = inputStream;
                    closeQuietly(inputStream2);
                    throw th;
                }
            }
        } catch (IOException e2) {
            e = e2;
            inputStream = null;
            logger.debug("Can't read resource.", (Throwable) e);
            closeQuietly(inputStream);
            return null;
        } catch (Throwable th2) {
            th = th2;
            closeQuietly(inputStream2);
            throw th;
        }
    }

    static void tryPatchShadedLibraryIdAndSign(File file, String str) {
        if (!new File("/Library/Developer/CommandLineTools").exists()) {
            logger.debug("Can't patch shaded library id as CommandLineTools are not installed. Consider installing CommandLineTools with 'xcode-select --install'");
            return;
        }
        String str2 = new String(generateUniqueId(str.length()), CharsetUtil.UTF_8);
        if (tryExec("install_name_tool -id " + str2 + AnsiRenderer.CODE_TEXT_SEPARATOR + file.getAbsolutePath())) {
            tryExec("codesign -s - " + file.getAbsolutePath());
        }
    }

    private static boolean tryExec(String str) {
        try {
            int waitFor = Runtime.getRuntime().exec(str).waitFor();
            if (waitFor != 0) {
                logger.debug("Execution of '{}' failed: {}", str, Integer.valueOf(waitFor));
                return false;
            }
            logger.debug("Execution of '{}' succeed: {}", str, Integer.valueOf(waitFor));
            return true;
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            return false;
        } catch (IOException e) {
            logger.info("Execution of '{}' failed.", str, e);
            return false;
        } catch (SecurityException e2) {
            logger.error("Execution of '{}' failed.", str, e2);
            return false;
        }
    }

    private static boolean shouldShadedLibraryIdBePatched(String str) {
        return TRY_TO_PATCH_SHADED_ID && PlatformDependent.isOsx() && !str.isEmpty();
    }

    private static byte[] generateUniqueId(int i) {
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            byte[] bArr2 = UNIQUE_ID_BYTES;
            bArr[i2] = bArr2[PlatformDependent.threadLocalRandom().nextInt(bArr2.length)];
        }
        return bArr;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0026, code lost:
        if (r1 != null) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0028, code lost:
        io.netty.util.internal.ThrowableUtil.addSuppressed((java.lang.Throwable) r3, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002b, code lost:
        rethrowWithMoreDetailsIfPossible(r4, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0012, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0014, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0017, code lost:
        r1 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:?, code lost:
        io.netty.util.internal.NativeLibraryUtil.loadLibrary(r4, r5);
        logger.debug("Successfully loaded the library {}", (java.lang.Object) r4);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:4:0x0012 A[ExcHandler: NoSuchMethodError (r3v4 'e' java.lang.NoSuchMethodError A[CUSTOM_DECLARE]), PHI: r1 
      PHI: (r1v2 java.lang.Throwable) = (r1v0 java.lang.Throwable), (r1v1 java.lang.Throwable), (r1v1 java.lang.Throwable), (r1v0 java.lang.Throwable) binds: [B:1:0x0005, B:7:0x0018, B:8:?, B:2:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:1:0x0005] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void loadLibrary(java.lang.ClassLoader r3, java.lang.String r4, boolean r5) {
        /*
            java.lang.String r0 = "Successfully loaded the library {}"
            r1 = 0
            java.lang.Class<io.netty.util.internal.NativeLibraryUtil> r2 = io.netty.util.internal.NativeLibraryUtil.class
            java.lang.Class r3 = tryToLoadClass(r3, r2)     // Catch:{ UnsatisfiedLinkError -> 0x0016, Exception -> 0x0014, NoSuchMethodError -> 0x0012 }
            loadLibraryByHelper(r3, r4, r5)     // Catch:{ UnsatisfiedLinkError -> 0x0016, Exception -> 0x0014, NoSuchMethodError -> 0x0012 }
            io.netty.util.internal.logging.InternalLogger r3 = logger     // Catch:{ UnsatisfiedLinkError -> 0x0016, Exception -> 0x0014, NoSuchMethodError -> 0x0012 }
            r3.debug((java.lang.String) r0, (java.lang.Object) r4)     // Catch:{ UnsatisfiedLinkError -> 0x0016, Exception -> 0x0014, NoSuchMethodError -> 0x0012 }
            return
        L_0x0012:
            r3 = move-exception
            goto L_0x0026
        L_0x0014:
            r3 = move-exception
            goto L_0x0017
        L_0x0016:
            r3 = move-exception
        L_0x0017:
            r1 = r3
            io.netty.util.internal.NativeLibraryUtil.loadLibrary(r4, r5)     // Catch:{ NoSuchMethodError -> 0x0012, UnsatisfiedLinkError -> 0x0021 }
            io.netty.util.internal.logging.InternalLogger r3 = logger     // Catch:{ NoSuchMethodError -> 0x0012, UnsatisfiedLinkError -> 0x0021 }
            r3.debug((java.lang.String) r0, (java.lang.Object) r4)     // Catch:{ NoSuchMethodError -> 0x0012, UnsatisfiedLinkError -> 0x0021 }
            goto L_0x002e
        L_0x0021:
            r3 = move-exception
            io.netty.util.internal.ThrowableUtil.addSuppressed((java.lang.Throwable) r3, (java.lang.Throwable) r1)
            throw r3
        L_0x0026:
            if (r1 == 0) goto L_0x002b
            io.netty.util.internal.ThrowableUtil.addSuppressed((java.lang.Throwable) r3, (java.lang.Throwable) r1)
        L_0x002b:
            rethrowWithMoreDetailsIfPossible(r4, r3)
        L_0x002e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.util.internal.NativeLibraryLoader.loadLibrary(java.lang.ClassLoader, java.lang.String, boolean):void");
    }

    private static void rethrowWithMoreDetailsIfPossible(String str, NoSuchMethodError noSuchMethodError) {
        if (PlatformDependent.javaVersion() >= 7) {
            throw new LinkageError("Possible multiple incompatible native libraries on the classpath for '" + str + "'?", noSuchMethodError);
        }
        throw noSuchMethodError;
    }

    private static void loadLibraryByHelper(final Class<?> cls, final String str, final boolean z) throws UnsatisfiedLinkError {
        Object doPrivileged = AccessController.doPrivileged(new PrivilegedAction<Object>() {
            public Object run() {
                try {
                    Method method = cls.getMethod("loadLibrary", new Class[]{String.class, Boolean.TYPE});
                    method.setAccessible(true);
                    return method.invoke((Object) null, new Object[]{str, Boolean.valueOf(z)});
                } catch (Exception e) {
                    return e;
                }
            }
        });
        if (doPrivileged instanceof Throwable) {
            Throwable th = (Throwable) doPrivileged;
            Throwable cause = th.getCause();
            if (cause instanceof UnsatisfiedLinkError) {
                throw ((UnsatisfiedLinkError) cause);
            }
            UnsatisfiedLinkError unsatisfiedLinkError = new UnsatisfiedLinkError(th.getMessage());
            unsatisfiedLinkError.initCause(th);
            throw unsatisfiedLinkError;
        }
    }

    private static Class<?> tryToLoadClass(final ClassLoader classLoader, final Class<?> cls) throws ClassNotFoundException {
        try {
            return Class.forName(cls.getName(), false, classLoader);
        } catch (ClassNotFoundException e) {
            if (classLoader != null) {
                try {
                    final byte[] classToByteArray = classToByteArray(cls);
                    return (Class) AccessController.doPrivileged(new PrivilegedAction<Class<?>>() {
                        public Class<?> run() {
                            Class<ClassLoader> cls = ClassLoader.class;
                            try {
                                Class cls2 = Integer.TYPE;
                                Method declaredMethod = cls.getDeclaredMethod("defineClass", new Class[]{String.class, byte[].class, cls2, cls2});
                                declaredMethod.setAccessible(true);
                                return (Class) declaredMethod.invoke(classLoader, new Object[]{cls.getName(), classToByteArray, 0, Integer.valueOf(classToByteArray.length)});
                            } catch (Exception e) {
                                throw new IllegalStateException("Define class failed!", e);
                            }
                        }
                    });
                } catch (ClassNotFoundException e2) {
                    ThrowableUtil.addSuppressed((Throwable) e2, (Throwable) e);
                    throw e2;
                } catch (RuntimeException e3) {
                    ThrowableUtil.addSuppressed((Throwable) e3, (Throwable) e);
                    throw e3;
                } catch (Error e4) {
                    ThrowableUtil.addSuppressed((Throwable) e4, (Throwable) e);
                    throw e4;
                }
            } else {
                throw e;
            }
        }
    }

    private static byte[] classToByteArray(Class<?> cls) throws ClassNotFoundException {
        String name = cls.getName();
        int lastIndexOf = name.lastIndexOf(46);
        if (lastIndexOf > 0) {
            name = name.substring(lastIndexOf + 1);
        }
        URL resource = cls.getResource(name + ".class");
        if (resource != null) {
            byte[] bArr = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4096);
            try {
                InputStream openStream = resource.openStream();
                while (true) {
                    int read = openStream.read(bArr);
                    if (read != -1) {
                        byteArrayOutputStream.write(bArr, 0, read);
                    } else {
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        closeQuietly(openStream);
                        closeQuietly(byteArrayOutputStream);
                        return byteArray;
                    }
                }
            } catch (IOException e) {
                throw new ClassNotFoundException(cls.getName(), e);
            } catch (Throwable th) {
                closeQuietly((Closeable) null);
                closeQuietly(byteArrayOutputStream);
                throw th;
            }
        } else {
            throw new ClassNotFoundException(cls.getName());
        }
    }

    private static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    private NativeLibraryLoader() {
    }

    private static final class NoexecVolumeDetector {
        /* access modifiers changed from: private */
        public static boolean canExecuteExecutable(File file) throws IOException {
            if (PlatformDependent.javaVersion() < 7 || file.canExecute()) {
                return true;
            }
            Set m = LinkFollowing$$ExternalSyntheticApiModelOutline0.m(file.toPath(), new LinkOption[0]);
            EnumSet of = EnumSet.of(LinkFollowing$$ExternalSyntheticApiModelOutline0.m(), LinkFollowing$$ExternalSyntheticApiModelOutline0.m$1(), LinkFollowing$$ExternalSyntheticApiModelOutline0.m$2());
            if (m.containsAll(of)) {
                return false;
            }
            EnumSet copyOf = EnumSet.copyOf(m);
            copyOf.addAll(of);
            Path unused = Files.setPosixFilePermissions(file.toPath(), copyOf);
            return file.canExecute();
        }

        private NoexecVolumeDetector() {
        }
    }
}
