package io.ktor.network.tls.certificates;

import io.ktor.util.CharsetKt;
import io.ktor.utils.io.core.CloseableJVMKt;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.security.KeyStore;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u001f\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006\u001a\u001a\u0010\u0007\u001a\u00020\u0005*\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b¨\u0006\f"}, d2 = {"buildKeyStore", "Ljava/security/KeyStore;", "block", "Lkotlin/Function1;", "Lio/ktor/network/tls/certificates/KeyStoreBuilder;", "", "Lkotlin/ExtensionFunctionType;", "saveToFile", "output", "Ljava/io/File;", "password", "", "ktor-network-tls-certificates"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: builders.kt */
public final class BuildersKt {
    public static final KeyStore buildKeyStore(Function1<? super KeyStoreBuilder, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "block");
        KeyStoreBuilder keyStoreBuilder = new KeyStoreBuilder();
        function1.invoke(keyStoreBuilder);
        return keyStoreBuilder.build$ktor_network_tls_certificates();
    }

    public static final void saveToFile(KeyStore keyStore, File file, String str) {
        Intrinsics.checkNotNullParameter(keyStore, "<this>");
        Intrinsics.checkNotNullParameter(file, "output");
        Intrinsics.checkNotNullParameter(str, "password");
        File parentFile = file.getParentFile();
        if (parentFile != null) {
            parentFile.mkdirs();
        }
        Closeable fileOutputStream = new FileOutputStream(file);
        try {
            keyStore.store((FileOutputStream) fileOutputStream, CharsetKt.toCharArray(str));
            Unit unit = Unit.INSTANCE;
            fileOutputStream.close();
            return;
        } catch (Throwable th) {
            CloseableJVMKt.addSuppressedInternal(th, th);
        }
        throw th;
    }
}
