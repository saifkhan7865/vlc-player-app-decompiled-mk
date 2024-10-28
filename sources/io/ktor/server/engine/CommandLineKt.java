package io.ktor.server.engine;

import io.ktor.server.config.ApplicationConfig;
import io.ktor.server.config.ApplicationConfigKt;
import io.ktor.server.config.ApplicationConfigValue;
import io.ktor.server.config.ConfigLoader;
import io.ktor.server.config.MapApplicationConfig;
import io.ktor.server.config.MergedApplicationConfigKt;
import io.ktor.server.engine.BaseApplicationEngine;
import io.ktor.util.logging.KtorSimpleLoggerJvmKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.slf4j.Logger;

@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0000\u001a\u001b\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0000¢\u0006\u0002\u0010\u0005\u001a6\u0010\u0006\u001a\u00020\u00072\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0019\b\u0002\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\u0002\b\fH\u0000¢\u0006\u0002\u0010\r\u001a\u0019\u0010\u000e\u001a\u00020\u00072\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u000f\u001a\u0012\u0010\u0010\u001a\u00020\u000b*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0001\u001a\"\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0014*\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0016H\u0000¨\u0006\u0017"}, d2 = {"buildApplicationConfig", "Lio/ktor/server/config/ApplicationConfig;", "args", "", "", "([Ljava/lang/String;)Lio/ktor/server/config/ApplicationConfig;", "buildCommandLineEnvironment", "Lio/ktor/server/engine/ApplicationEngineEnvironment;", "environmentBuilder", "Lkotlin/Function1;", "Lio/ktor/server/engine/ApplicationEngineEnvironmentBuilder;", "", "Lkotlin/ExtensionFunctionType;", "([Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Lio/ktor/server/engine/ApplicationEngineEnvironment;", "commandLineEnvironment", "([Ljava/lang/String;)Lio/ktor/server/engine/ApplicationEngineEnvironment;", "loadCommonConfiguration", "Lio/ktor/server/engine/BaseApplicationEngine$Configuration;", "deploymentConfig", "splitPair", "Lkotlin/Pair;", "ch", "", "ktor-server-host-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: CommandLine.kt */
public final class CommandLineKt {
    public static /* synthetic */ ApplicationEngineEnvironment buildCommandLineEnvironment$default(String[] strArr, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = CommandLineKt$buildCommandLineEnvironment$1.INSTANCE;
        }
        return buildCommandLineEnvironment(strArr, function1);
    }

    public static final ApplicationEngineEnvironment commandLineEnvironment(String[] strArr) {
        Intrinsics.checkNotNullParameter(strArr, "args");
        return buildCommandLineEnvironment(strArr, CommandLineKt$commandLineEnvironment$1.INSTANCE);
    }

    public static final void loadCommonConfiguration(BaseApplicationEngine.Configuration configuration, ApplicationConfig applicationConfig) {
        String string;
        String string2;
        String string3;
        String string4;
        String string5;
        Intrinsics.checkNotNullParameter(configuration, "<this>");
        Intrinsics.checkNotNullParameter(applicationConfig, "deploymentConfig");
        ApplicationConfigValue propertyOrNull = applicationConfig.propertyOrNull("callGroupSize");
        if (!(propertyOrNull == null || (string5 = propertyOrNull.getString()) == null)) {
            configuration.setCallGroupSize(Integer.parseInt(string5));
        }
        ApplicationConfigValue propertyOrNull2 = applicationConfig.propertyOrNull("connectionGroupSize");
        if (!(propertyOrNull2 == null || (string4 = propertyOrNull2.getString()) == null)) {
            configuration.setConnectionGroupSize(Integer.parseInt(string4));
        }
        ApplicationConfigValue propertyOrNull3 = applicationConfig.propertyOrNull("workerGroupSize");
        if (!(propertyOrNull3 == null || (string3 = propertyOrNull3.getString()) == null)) {
            configuration.setWorkerGroupSize(Integer.parseInt(string3));
        }
        ApplicationConfigValue propertyOrNull4 = applicationConfig.propertyOrNull("shutdownGracePeriod");
        if (!(propertyOrNull4 == null || (string2 = propertyOrNull4.getString()) == null)) {
            configuration.setShutdownGracePeriod(Long.parseLong(string2));
        }
        ApplicationConfigValue propertyOrNull5 = applicationConfig.propertyOrNull("shutdownTimeout");
        if (propertyOrNull5 != null && (string = propertyOrNull5.getString()) != null) {
            configuration.setShutdownTimeout(Long.parseLong(string));
        }
    }

    public static final Pair<String, String> splitPair(String str, char c) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        int indexOf$default = StringsKt.indexOf$default((CharSequence) str, c, 0, false, 6, (Object) null);
        if (indexOf$default == -1) {
            return null;
        }
        return new Pair<>(StringsKt.take(str, indexOf$default), StringsKt.drop(str, indexOf$default + 1));
    }

    public static final ApplicationEngineEnvironment buildCommandLineEnvironment(String[] strArr, Function1<? super ApplicationEngineEnvironmentBuilder, Unit> function1) {
        Intrinsics.checkNotNullParameter(strArr, "args");
        Intrinsics.checkNotNullParameter(function1, "environmentBuilder");
        Collection arrayList = new ArrayList();
        for (String splitPair : strArr) {
            Pair<String, String> splitPair2 = splitPair(splitPair, '=');
            if (splitPair2 != null) {
                arrayList.add(splitPair2);
            }
        }
        Map map = MapsKt.toMap((List) arrayList);
        ApplicationConfig buildApplicationConfig = buildApplicationConfig(strArr);
        String tryGetString = ApplicationConfigKt.tryGetString(buildApplicationConfig, ConfigKeys.applicationIdPath);
        if (tryGetString == null) {
            tryGetString = "Application";
        }
        Logger KtorSimpleLogger = KtorSimpleLoggerJvmKt.KtorSimpleLogger(tryGetString);
        String str = (String) map.get("-path");
        if (str == null && (str = ApplicationConfigKt.tryGetString(buildApplicationConfig, ConfigKeys.rootPathPath)) == null) {
            str = "";
        }
        return ApplicationEngineEnvironmentKt.applicationEngineEnvironment(new CommandLineKt$buildCommandLineEnvironment$environment$1(KtorSimpleLogger, strArr, buildApplicationConfig, str, map, function1));
    }

    public static final ApplicationConfig buildApplicationConfig(String[] strArr) {
        ApplicationConfig applicationConfig;
        Intrinsics.checkNotNullParameter(strArr, "args");
        Collection arrayList = new ArrayList();
        for (String splitPair : strArr) {
            Pair<String, String> splitPair2 = splitPair(splitPair, '=');
            if (splitPair2 != null) {
                arrayList.add(splitPair2);
            }
        }
        Iterable iterable = (List) arrayList;
        Collection arrayList2 = new ArrayList();
        for (Object next : iterable) {
            if (StringsKt.startsWith$default((String) ((Pair) next).getFirst(), "-P:", false, 2, (Object) null)) {
                arrayList2.add(next);
            }
        }
        Iterable<Pair> iterable2 = (List) arrayList2;
        Collection arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
        for (Pair pair : iterable2) {
            arrayList3.add(TuplesKt.to(StringsKt.removePrefix((String) pair.getFirst(), (CharSequence) "-P:"), pair.getSecond()));
        }
        List list = (List) arrayList3;
        Collection arrayList4 = new ArrayList();
        for (Object next2 : iterable) {
            if (Intrinsics.areEqual(((Pair) next2).getFirst(), (Object) "-config")) {
                arrayList4.add(next2);
            }
        }
        Iterable<Pair> iterable3 = (List) arrayList4;
        Collection arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable3, 10));
        for (Pair second : iterable3) {
            arrayList5.add((String) second.getSecond());
        }
        List list2 = (List) arrayList5;
        MapApplicationConfig mapApplicationConfig = new MapApplicationConfig((List<Pair<String, String>>) list);
        ApplicationConfig configFromEnvironment = EnvironmentUtilsJvmKt.getConfigFromEnvironment();
        int size = list2.size();
        if (size == 0) {
            applicationConfig = ConfigLoader.Companion.load$default(ConfigLoader.Companion, ConfigLoader.Companion, (String) null, 1, (Object) null);
        } else if (size != 1) {
            Iterable<String> iterable4 = list2;
            Collection arrayList6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable4, 10));
            for (String load : iterable4) {
                arrayList6.add(ConfigLoader.Companion.load(ConfigLoader.Companion, load));
            }
            Iterator it = ((List) arrayList6).iterator();
            if (it.hasNext()) {
                Object next3 = it.next();
                while (it.hasNext()) {
                    next3 = MergedApplicationConfigKt.mergeWith((ApplicationConfig) next3, (ApplicationConfig) it.next());
                }
                applicationConfig = (ApplicationConfig) next3;
            } else {
                throw new UnsupportedOperationException("Empty collection can't be reduced.");
            }
        } else {
            applicationConfig = ConfigLoader.Companion.load(ConfigLoader.Companion, (String) CollectionsKt.single(list2));
        }
        return MergedApplicationConfigKt.mergeWith(MergedApplicationConfigKt.mergeWith(applicationConfig, configFromEnvironment), mapApplicationConfig);
    }
}
