package io.ktor.server.http.content;

import java.util.jar.JarEntry;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: JarFileContent.kt */
final class JarFileContent$isFile$2 extends Lambda implements Function0<Boolean> {
    final /* synthetic */ JarFileContent this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    JarFileContent$isFile$2(JarFileContent jarFileContent) {
        super(0);
        this.this$0 = jarFileContent;
    }

    public final Boolean invoke() {
        JarEntry access$getJarEntry = this.this$0.getJarEntry();
        boolean z = false;
        if (access$getJarEntry != null && !access$getJarEntry.isDirectory()) {
            z = true;
        }
        return Boolean.valueOf(z);
    }
}
