package org.fusesource.jansi.internal;

import androidx.core.os.EnvironmentCompat;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import kotlin.UInt$$ExternalSyntheticBackport0;
import org.fusesource.jansi.AnsiConsole;

public class JansiLoader {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static boolean loaded = false;
    private static String nativeLibraryPath;
    private static String nativeLibrarySourceUrl;

    public static synchronized boolean initialize() {
        boolean z;
        synchronized (JansiLoader.class) {
            if (!loaded) {
                cleanup();
            }
            try {
                loadJansiNativeLibrary();
            } catch (Exception e) {
                if (!Boolean.parseBoolean(System.getProperty(AnsiConsole.JANSI_GRACEFUL, "true"))) {
                    throw new RuntimeException("Unable to load jansi native library. You may want set the `jansi.graceful` system property to true to be able to use Jansi on your platform", e);
                }
            }
            z = loaded;
        }
        return z;
    }

    public static String getNativeLibraryPath() {
        return nativeLibraryPath;
    }

    public static String getNativeLibrarySourceUrl() {
        return nativeLibrarySourceUrl;
    }

    private static File getTempDir() {
        return new File(System.getProperty("jansi.tmpdir", System.getProperty("java.io.tmpdir")));
    }

    static void cleanup() {
        File[] listFiles = new File(getTempDir().getAbsolutePath()).listFiles(new FilenameFilter() {
            private final String searchPattern = ("jansi-" + JansiLoader.getVersion());

            public boolean accept(File file, String str) {
                return str.startsWith(this.searchPattern) && !str.endsWith(".lck");
            }
        });
        if (listFiles != null) {
            for (File file : listFiles) {
                if (!new File(file.getAbsolutePath() + ".lck").exists()) {
                    try {
                        file.delete();
                    } catch (SecurityException e) {
                        System.err.println("Failed to delete old native lib" + e.getMessage());
                    }
                }
            }
        }
    }

    private static int readNBytes(InputStream inputStream, byte[] bArr) throws IOException {
        int length = bArr.length;
        int i = 0;
        while (i < length) {
            int read = inputStream.read(bArr, i, length - i);
            if (read <= 0) {
                break;
            }
            i += read;
        }
        return i;
    }

    private static String contentsEquals(InputStream inputStream, InputStream inputStream2) throws IOException {
        byte[] bArr = new byte[8192];
        byte[] bArr2 = new byte[8192];
        do {
            int readNBytes = readNBytes(inputStream, bArr);
            int readNBytes2 = readNBytes(inputStream2, bArr2);
            if (readNBytes > 0) {
                if (readNBytes2 <= 0) {
                    return "EOF on second stream but not first";
                }
                if (readNBytes2 != readNBytes) {
                    return "Read size different (" + readNBytes + " vs " + readNBytes2 + ")";
                }
            } else if (readNBytes2 > 0) {
                return "EOF on first stream but not second";
            } else {
                return null;
            }
        } while (Arrays.equals(bArr, bArr2));
        return "Content differs";
    }

    private static boolean extractAndLoadLibraryFile(String str, String str2, String str3) {
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream;
        Class<JansiLoader> cls = JansiLoader.class;
        String str4 = str + "/" + str2;
        String format = String.format("jansi-%s-%s-%s", new Object[]{getVersion(), randomUUID(), str2});
        File file = new File(str3, format);
        File file2 = new File(str3, format + ".lck");
        try {
            InputStream resourceAsStream = cls.getResourceAsStream(str4);
            try {
                if (!file2.exists()) {
                    new FileOutputStream(file2).close();
                }
                fileOutputStream = new FileOutputStream(file);
                copy(resourceAsStream, fileOutputStream);
                fileOutputStream.close();
                if (resourceAsStream != null) {
                    resourceAsStream.close();
                }
                throw th;
                throw th;
            } catch (Throwable th) {
                if (resourceAsStream != null) {
                    resourceAsStream.close();
                }
                throw th;
            }
            try {
                file.deleteOnExit();
                file2.deleteOnExit();
                file.setReadable(true);
                file.setWritable(true);
                file.setExecutable(true);
                InputStream resourceAsStream2 = cls.getResourceAsStream(str4);
                try {
                    fileInputStream = new FileInputStream(file);
                    String contentsEquals = contentsEquals(resourceAsStream2, fileInputStream);
                    if (contentsEquals == null) {
                        fileInputStream.close();
                        if (resourceAsStream2 != null) {
                            resourceAsStream2.close();
                        }
                        if (loadNativeLibrary(file)) {
                            nativeLibrarySourceUrl = cls.getResource(str4).toExternalForm();
                            return true;
                        }
                        return false;
                    }
                    throw new RuntimeException(String.format("Failed to write a native library file at %s because %s", new Object[]{file, contentsEquals}));
                } catch (Throwable th2) {
                    if (resourceAsStream2 != null) {
                        resourceAsStream2.close();
                    }
                    throw th2;
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            } catch (Throwable th3) {
                UInt$$ExternalSyntheticBackport0.m(th2, th3);
            }
        } catch (Throwable th4) {
            file.deleteOnExit();
            file2.deleteOnExit();
            throw th4;
        }
    }

    private static String randomUUID() {
        return Long.toHexString(new Random().nextLong());
    }

    private static void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[8192];
        while (true) {
            int read = inputStream.read(bArr);
            if (read > 0) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    private static boolean loadNativeLibrary(File file) {
        if (file.exists()) {
            try {
                String absolutePath = file.getAbsolutePath();
                System.load(absolutePath);
                nativeLibraryPath = absolutePath;
                return true;
            } catch (UnsatisfiedLinkError e) {
                if (!file.canExecute()) {
                    System.err.printf("Failed to load native library:%s. The native library file at %s is not executable, make sure that the directory is mounted on a partition without the noexec flag, or set the jansi.tmpdir system property to point to a proper location.  osinfo: %s%n", new Object[]{file.getName(), file, OSInfo.getNativeLibFolderPathForCurrentOS()});
                } else {
                    System.err.printf("Failed to load native library:%s. osinfo: %s%n", new Object[]{file.getName(), OSInfo.getNativeLibFolderPathForCurrentOS()});
                }
                System.err.println(e);
            }
        }
        return false;
    }

    private static void loadJansiNativeLibrary() throws Exception {
        if (!loaded) {
            LinkedList linkedList = new LinkedList();
            String property = System.getProperty("library.jansi.path");
            String property2 = System.getProperty("library.jansi.name");
            if (property2 == null) {
                property2 = System.mapLibraryName("jansi");
                if (property2.endsWith(".dylib")) {
                    property2 = property2.replace(".dylib", ".jnilib");
                }
            }
            if (property != null) {
                String str = property + "/" + OSInfo.getNativeLibFolderPathForCurrentOS();
                if (loadNativeLibrary(new File(str, property2))) {
                    loaded = true;
                    return;
                }
                linkedList.add(str);
                if (loadNativeLibrary(new File(property, property2))) {
                    loaded = true;
                    return;
                }
                linkedList.add(property);
            }
            String format = String.format("/%s/native/%s", new Object[]{JansiLoader.class.getPackage().getName().replace('.', '/'), OSInfo.getNativeLibFolderPathForCurrentOS()});
            if (hasResource(format + "/" + property2)) {
                if (extractAndLoadLibraryFile(format, property2, getTempDir().getAbsolutePath())) {
                    loaded = true;
                    return;
                }
                linkedList.add(format);
            }
            for (String str2 : System.getProperty("java.library.path", "").split(File.pathSeparator)) {
                if (!str2.isEmpty()) {
                    if (loadNativeLibrary(new File(str2, property2))) {
                        loaded = true;
                        return;
                    }
                    linkedList.add(str2);
                }
            }
            throw new Exception(String.format("No native library found for os.name=%s, os.arch=%s, paths=[%s]", new Object[]{OSInfo.getOSName(), OSInfo.getArchName(), join(linkedList, File.pathSeparator)}));
        }
    }

    private static boolean hasResource(String str) {
        return JansiLoader.class.getResource(str) != null;
    }

    public static int getMajorVersion() {
        String[] split = getVersion().split("\\.");
        if (split.length > 0) {
            return Integer.parseInt(split[0]);
        }
        return 1;
    }

    public static int getMinorVersion() {
        String[] split = getVersion().split("\\.");
        if (split.length > 1) {
            return Integer.parseInt(split[1]);
        }
        return 0;
    }

    public static String getVersion() {
        URL resource = JansiLoader.class.getResource("/org/fusesource/jansi/jansi.properties");
        if (resource == null) {
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
        try {
            Properties properties = new Properties();
            properties.load(resource.openStream());
            return properties.getProperty("version", EnvironmentCompat.MEDIA_UNKNOWN).trim().replaceAll("[^0-9.]", "");
        } catch (IOException e) {
            System.err.println(e);
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
    }

    private static String join(List<String> list, String str) {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (String next : list) {
            if (z) {
                z = false;
            } else {
                sb.append(str);
            }
            sb.append(next);
        }
        return sb.toString();
    }
}
