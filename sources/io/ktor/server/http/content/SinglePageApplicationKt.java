package io.ktor.server.http.content;

import io.ktor.server.routing.Route;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0006\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a-\u0010\u0007\u001a\u00020\u0001*\u00020\u00022!\u0010\b\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\t\u001a\u0012\u0010\u000e\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a%\u0010\u000f\u001a\u00020\u0001*\u00020\u00102\u0019\b\u0002\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\b\u0012\u001a\u0012\u0010\u0013\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0014"}, d2 = {"angular", "", "Lio/ktor/server/http/content/SPAConfig;", "filesPath", "", "backbone", "ember", "ignoreFiles", "block", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "path", "", "react", "singlePageApplication", "Lio/ktor/server/routing/Route;", "configBuilder", "Lkotlin/ExtensionFunctionType;", "vue", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: SinglePageApplication.kt */
public final class SinglePageApplicationKt {
    public static /* synthetic */ void singlePageApplication$default(Route route, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = SinglePageApplicationKt$singlePageApplication$1.INSTANCE;
        }
        singlePageApplication(route, function1);
    }

    public static final void singlePageApplication(Route route, Function1<? super SPAConfig, Unit> function1) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(function1, "configBuilder");
        SPAConfig sPAConfig = new SPAConfig((String) null, (String) null, (String) null, false, (List) null, 31, (DefaultConstructorMarker) null);
        function1.invoke(sPAConfig);
        if (sPAConfig.getUseResources()) {
            StaticContentKt.staticResources(route, sPAConfig.getApplicationRoute(), sPAConfig.getFilesPath(), sPAConfig.getDefaultPage(), new SinglePageApplicationKt$singlePageApplication$2(sPAConfig));
        } else {
            StaticContentKt.staticFiles(route, sPAConfig.getApplicationRoute(), new File(sPAConfig.getFilesPath()), sPAConfig.getDefaultPage(), new SinglePageApplicationKt$singlePageApplication$3(sPAConfig));
        }
    }

    public static final void ignoreFiles(SPAConfig sPAConfig, Function1<? super String, Boolean> function1) {
        Intrinsics.checkNotNullParameter(sPAConfig, "<this>");
        Intrinsics.checkNotNullParameter(function1, "block");
        sPAConfig.getIgnoredFiles$ktor_server_core().add(function1);
    }

    public static final void angular(SPAConfig sPAConfig, String str) {
        Intrinsics.checkNotNullParameter(sPAConfig, "<this>");
        Intrinsics.checkNotNullParameter(str, "filesPath");
        sPAConfig.setFilesPath(str);
    }

    public static final void react(SPAConfig sPAConfig, String str) {
        Intrinsics.checkNotNullParameter(sPAConfig, "<this>");
        Intrinsics.checkNotNullParameter(str, "filesPath");
        sPAConfig.setFilesPath(str);
    }

    public static final void vue(SPAConfig sPAConfig, String str) {
        Intrinsics.checkNotNullParameter(sPAConfig, "<this>");
        Intrinsics.checkNotNullParameter(str, "filesPath");
        sPAConfig.setFilesPath(str);
    }

    public static final void ember(SPAConfig sPAConfig, String str) {
        Intrinsics.checkNotNullParameter(sPAConfig, "<this>");
        Intrinsics.checkNotNullParameter(str, "filesPath");
        sPAConfig.setFilesPath(str);
    }

    public static final void backbone(SPAConfig sPAConfig, String str) {
        Intrinsics.checkNotNullParameter(sPAConfig, "<this>");
        Intrinsics.checkNotNullParameter(str, "filesPath");
        sPAConfig.setFilesPath(str);
    }
}
