package io.ktor.server.http.content;

import java.util.jar.JarEntry;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Ljava/util/jar/JarEntry;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: JarFileContent.kt */
final class JarFileContent$jarEntry$2 extends Lambda implements Function0<JarEntry> {
    final /* synthetic */ JarFileContent this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    JarFileContent$jarEntry$2(JarFileContent jarFileContent) {
        super(0);
        this.this$0 = jarFileContent;
    }

    public final JarEntry invoke() {
        return this.this$0.getJar().getJarEntry(this.this$0.getResourcePath());
    }
}
