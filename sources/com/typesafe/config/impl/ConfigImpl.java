package com.typesafe.config.impl;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigIncluder;
import com.typesafe.config.ConfigMemorySize;
import com.typesafe.config.ConfigObject;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigParseOptions;
import com.typesafe.config.ConfigParseable;
import com.typesafe.config.ConfigValue;
import com.typesafe.config.impl.ConfigString;
import com.typesafe.config.impl.SimpleIncluder;
import j$.time.Duration;
import java.io.File;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Callable;
import org.fusesource.jansi.AnsiRenderer;

public class ConfigImpl {
    private static final String ENV_VAR_OVERRIDE_PREFIX = "CONFIG_FORCE_";
    private static final SimpleConfigList defaultEmptyList;
    private static final SimpleConfigObject defaultEmptyObject;
    private static final ConfigBoolean defaultFalseValue;
    private static final ConfigNull defaultNullValue;
    private static final ConfigBoolean defaultTrueValue;
    private static final ConfigOrigin defaultValueOrigin;

    private static class LoaderCache {
        private Map<String, Config> cache = new HashMap();
        private WeakReference<ClassLoader> currentLoader = new WeakReference<>((Object) null);
        private Config currentSystemProperties = null;

        LoaderCache() {
        }

        /* access modifiers changed from: package-private */
        public synchronized Config getOrElseUpdate(ClassLoader classLoader, String str, Callable<Config> callable) {
            Config config;
            if (classLoader != this.currentLoader.get()) {
                this.cache.clear();
                this.currentLoader = new WeakReference<>(classLoader);
            }
            Config systemPropertiesAsConfig = ConfigImpl.systemPropertiesAsConfig();
            if (systemPropertiesAsConfig != this.currentSystemProperties) {
                this.cache.clear();
                this.currentSystemProperties = systemPropertiesAsConfig;
            }
            config = this.cache.get(str);
            if (config == null) {
                try {
                    config = callable.call();
                    if (config != null) {
                        this.cache.put(str, config);
                    } else {
                        throw new ConfigException.BugOrBroken("null config from cache updater");
                    }
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e2) {
                    throw new ConfigException.Generic(e2.getMessage(), e2);
                }
            }
            return config;
        }
    }

    private static class LoaderCacheHolder {
        static final LoaderCache cache = new LoaderCache();

        private LoaderCacheHolder() {
        }
    }

    public static Config computeCachedConfig(ClassLoader classLoader, String str, Callable<Config> callable) {
        try {
            return LoaderCacheHolder.cache.getOrElseUpdate(classLoader, str, callable);
        } catch (ExceptionInInitializerError e) {
            throw ConfigImplUtil.extractInitializerError(e);
        }
    }

    static class FileNameSource implements SimpleIncluder.NameSource {
        FileNameSource() {
        }

        public ConfigParseable nameToParseable(String str, ConfigParseOptions configParseOptions) {
            return Parseable.newFile(new File(str), configParseOptions);
        }
    }

    static class ClasspathNameSource implements SimpleIncluder.NameSource {
        ClasspathNameSource() {
        }

        public ConfigParseable nameToParseable(String str, ConfigParseOptions configParseOptions) {
            return Parseable.newResources(str, configParseOptions);
        }
    }

    static class ClasspathNameSourceWithClass implements SimpleIncluder.NameSource {
        private final Class<?> klass;

        public ClasspathNameSourceWithClass(Class<?> cls) {
            this.klass = cls;
        }

        public ConfigParseable nameToParseable(String str, ConfigParseOptions configParseOptions) {
            return Parseable.newResources(this.klass, str, configParseOptions);
        }
    }

    public static ConfigObject parseResourcesAnySyntax(Class<?> cls, String str, ConfigParseOptions configParseOptions) {
        return SimpleIncluder.fromBasename(new ClasspathNameSourceWithClass(cls), str, configParseOptions);
    }

    public static ConfigObject parseResourcesAnySyntax(String str, ConfigParseOptions configParseOptions) {
        return SimpleIncluder.fromBasename(new ClasspathNameSource(), str, configParseOptions);
    }

    public static ConfigObject parseFileAnySyntax(File file, ConfigParseOptions configParseOptions) {
        return SimpleIncluder.fromBasename(new FileNameSource(), file.getPath(), configParseOptions);
    }

    static AbstractConfigObject emptyObject(String str) {
        return emptyObject((ConfigOrigin) str != null ? SimpleConfigOrigin.newSimple(str) : null);
    }

    public static Config emptyConfig(String str) {
        return emptyObject(str).toConfig();
    }

    static AbstractConfigObject empty(ConfigOrigin configOrigin) {
        return emptyObject(configOrigin);
    }

    static {
        SimpleConfigOrigin newSimple = SimpleConfigOrigin.newSimple("hardcoded value");
        defaultValueOrigin = newSimple;
        defaultTrueValue = new ConfigBoolean(newSimple, true);
        defaultFalseValue = new ConfigBoolean(newSimple, false);
        defaultNullValue = new ConfigNull(newSimple);
        defaultEmptyList = new SimpleConfigList(newSimple, Collections.emptyList());
        defaultEmptyObject = SimpleConfigObject.empty(newSimple);
    }

    private static SimpleConfigList emptyList(ConfigOrigin configOrigin) {
        if (configOrigin == null || configOrigin == defaultValueOrigin) {
            return defaultEmptyList;
        }
        return new SimpleConfigList(configOrigin, Collections.emptyList());
    }

    private static AbstractConfigObject emptyObject(ConfigOrigin configOrigin) {
        if (configOrigin == defaultValueOrigin) {
            return defaultEmptyObject;
        }
        return SimpleConfigObject.empty(configOrigin);
    }

    private static ConfigOrigin valueOrigin(String str) {
        if (str == null) {
            return defaultValueOrigin;
        }
        return SimpleConfigOrigin.newSimple(str);
    }

    public static ConfigValue fromAnyRef(Object obj, String str) {
        return fromAnyRef(obj, valueOrigin(str), FromMapMode.KEYS_ARE_KEYS);
    }

    public static ConfigObject fromPathMap(Map<String, ? extends Object> map, String str) {
        return (ConfigObject) fromAnyRef(map, valueOrigin(str), FromMapMode.KEYS_ARE_PATHS);
    }

    static AbstractConfigValue fromAnyRef(Object obj, ConfigOrigin configOrigin, FromMapMode fromMapMode) {
        if (configOrigin == null) {
            throw new ConfigException.BugOrBroken("origin not supposed to be null");
        } else if (obj == null) {
            if (configOrigin != defaultValueOrigin) {
                return new ConfigNull(configOrigin);
            }
            return defaultNullValue;
        } else if (obj instanceof AbstractConfigValue) {
            return (AbstractConfigValue) obj;
        } else {
            if (obj instanceof Boolean) {
                if (configOrigin != defaultValueOrigin) {
                    return new ConfigBoolean(configOrigin, ((Boolean) obj).booleanValue());
                }
                if (((Boolean) obj).booleanValue()) {
                    return defaultTrueValue;
                }
                return defaultFalseValue;
            } else if (obj instanceof String) {
                return new ConfigString.Quoted(configOrigin, (String) obj);
            } else {
                if (obj instanceof Number) {
                    if (obj instanceof Double) {
                        return new ConfigDouble(configOrigin, ((Double) obj).doubleValue(), (String) null);
                    }
                    if (obj instanceof Integer) {
                        return new ConfigInt(configOrigin, ((Integer) obj).intValue(), (String) null);
                    }
                    if (obj instanceof Long) {
                        return new ConfigLong(configOrigin, ((Long) obj).longValue(), (String) null);
                    }
                    return ConfigNumber.newNumber(configOrigin, ((Number) obj).doubleValue(), (String) null);
                } else if (obj instanceof Duration) {
                    return new ConfigLong(configOrigin, ((Duration) obj).toMillis(), (String) null);
                } else {
                    if (obj instanceof Map) {
                        Map map = (Map) obj;
                        if (map.isEmpty()) {
                            return emptyObject(configOrigin);
                        }
                        if (fromMapMode != FromMapMode.KEYS_ARE_KEYS) {
                            return PropertiesParser.fromPathMap(configOrigin, map);
                        }
                        HashMap hashMap = new HashMap();
                        for (Map.Entry entry : map.entrySet()) {
                            Object key = entry.getKey();
                            if (key instanceof String) {
                                hashMap.put((String) key, fromAnyRef(entry.getValue(), configOrigin, fromMapMode));
                            } else {
                                throw new ConfigException.BugOrBroken("bug in method caller: not valid to create ConfigObject from map with non-String key: " + key);
                            }
                        }
                        return new SimpleConfigObject(configOrigin, hashMap);
                    } else if (obj instanceof Iterable) {
                        Iterator it = ((Iterable) obj).iterator();
                        if (!it.hasNext()) {
                            return emptyList(configOrigin);
                        }
                        ArrayList arrayList = new ArrayList();
                        while (it.hasNext()) {
                            arrayList.add(fromAnyRef(it.next(), configOrigin, fromMapMode));
                        }
                        return new SimpleConfigList(configOrigin, arrayList);
                    } else if (obj instanceof ConfigMemorySize) {
                        return new ConfigLong(configOrigin, ((ConfigMemorySize) obj).toBytes(), (String) null);
                    } else {
                        throw new ConfigException.BugOrBroken("bug in method caller: not valid to create ConfigValue from: " + obj);
                    }
                }
            }
        }
    }

    private static class DefaultIncluderHolder {
        static final ConfigIncluder defaultIncluder = new SimpleIncluder((ConfigIncluder) null);

        private DefaultIncluderHolder() {
        }
    }

    static ConfigIncluder defaultIncluder() {
        try {
            return DefaultIncluderHolder.defaultIncluder;
        } catch (ExceptionInInitializerError e) {
            throw ConfigImplUtil.extractInitializerError(e);
        }
    }

    private static Properties getSystemProperties() {
        Properties properties = System.getProperties();
        Properties properties2 = new Properties();
        synchronized (properties) {
            for (Map.Entry entry : properties.entrySet()) {
                if (!entry.getKey().toString().startsWith("java.version.")) {
                    properties2.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return properties2;
    }

    /* access modifiers changed from: private */
    public static AbstractConfigObject loadSystemProperties() {
        return (AbstractConfigObject) Parseable.newProperties(getSystemProperties(), ConfigParseOptions.defaults().setOriginDescription("system properties")).parse();
    }

    private static class SystemPropertiesHolder {
        static volatile AbstractConfigObject systemProperties = ConfigImpl.loadSystemProperties();

        private SystemPropertiesHolder() {
        }
    }

    static AbstractConfigObject systemPropertiesAsConfigObject() {
        try {
            return SystemPropertiesHolder.systemProperties;
        } catch (ExceptionInInitializerError e) {
            throw ConfigImplUtil.extractInitializerError(e);
        }
    }

    public static Config systemPropertiesAsConfig() {
        return systemPropertiesAsConfigObject().toConfig();
    }

    public static void reloadSystemPropertiesConfig() {
        SystemPropertiesHolder.systemProperties = loadSystemProperties();
    }

    /* access modifiers changed from: private */
    public static AbstractConfigObject loadEnvVariables() {
        return PropertiesParser.fromStringMap(newSimpleOrigin("env variables"), System.getenv());
    }

    private static class EnvVariablesHolder {
        static volatile AbstractConfigObject envVariables = ConfigImpl.loadEnvVariables();

        private EnvVariablesHolder() {
        }
    }

    static AbstractConfigObject envVariablesAsConfigObject() {
        try {
            return EnvVariablesHolder.envVariables;
        } catch (ExceptionInInitializerError e) {
            throw ConfigImplUtil.extractInitializerError(e);
        }
    }

    public static Config envVariablesAsConfig() {
        return envVariablesAsConfigObject().toConfig();
    }

    public static void reloadEnvVariablesConfig() {
        EnvVariablesHolder.envVariables = loadEnvVariables();
    }

    /* access modifiers changed from: private */
    public static AbstractConfigObject loadEnvVariablesOverrides() {
        HashMap hashMap = new HashMap(System.getenv());
        HashMap hashMap2 = new HashMap();
        for (String str : hashMap.keySet()) {
            if (str.startsWith(ENV_VAR_OVERRIDE_PREFIX)) {
                hashMap2.put(ConfigImplUtil.envVariableAsProperty(str, ENV_VAR_OVERRIDE_PREFIX), hashMap.get(str));
            }
        }
        return PropertiesParser.fromStringMap(newSimpleOrigin("env variables overrides"), hashMap2);
    }

    private static class EnvVariablesOverridesHolder {
        static volatile AbstractConfigObject envVariables = ConfigImpl.loadEnvVariablesOverrides();

        private EnvVariablesOverridesHolder() {
        }
    }

    static AbstractConfigObject envVariablesOverridesAsConfigObject() {
        try {
            return EnvVariablesOverridesHolder.envVariables;
        } catch (ExceptionInInitializerError e) {
            throw ConfigImplUtil.extractInitializerError(e);
        }
    }

    public static Config envVariablesOverridesAsConfig() {
        return envVariablesOverridesAsConfigObject().toConfig();
    }

    public static void reloadEnvVariablesOverridesConfig() {
        EnvVariablesOverridesHolder.envVariables = loadEnvVariablesOverrides();
    }

    public static Config defaultReference(final ClassLoader classLoader) {
        return computeCachedConfig(classLoader, "defaultReference", new Callable<Config>() {
            public Config call() {
                return ConfigImpl.systemPropertiesAsConfig().withFallback(ConfigImpl.unresolvedReference(classLoader)).resolve();
            }
        });
    }

    /* access modifiers changed from: private */
    public static Config unresolvedReference(final ClassLoader classLoader) {
        return computeCachedConfig(classLoader, "unresolvedReference", new Callable<Config>() {
            public Config call() {
                return Parseable.newResources("reference.conf", ConfigParseOptions.defaults().setClassLoader(classLoader)).parse().toConfig();
            }
        });
    }

    public static Config defaultReferenceUnresolved(ClassLoader classLoader) {
        try {
            defaultReference(classLoader);
            return unresolvedReference(classLoader);
        } catch (ConfigException.UnresolvedSubstitution e) {
            throw e.addExtraDetail("Could not resolve substitution in reference.conf to a value: %s. All reference.conf files are required to be fully, independently resolvable, and should not require the presence of values for substitutions from further up the hierarchy.");
        }
    }

    private static class DebugHolder {
        private static String LOADS = "loads";
        private static String SUBSTITUTIONS = "substitutions";
        private static final Map<String, Boolean> diagnostics;
        private static final boolean traceLoadsEnabled;
        private static final boolean traceSubstitutionsEnabled;

        private DebugHolder() {
        }

        private static Map<String, Boolean> loadDiagnostics() {
            HashMap hashMap = new HashMap();
            hashMap.put(LOADS, false);
            hashMap.put(SUBSTITUTIONS, false);
            String property = System.getProperty("config.trace");
            if (property == null) {
                return hashMap;
            }
            for (String str : property.split(AnsiRenderer.CODE_LIST_SEPARATOR)) {
                if (str.equals(LOADS)) {
                    hashMap.put(LOADS, true);
                } else if (str.equals(SUBSTITUTIONS)) {
                    hashMap.put(SUBSTITUTIONS, true);
                } else {
                    System.err.println("config.trace property contains unknown trace topic '" + str + "'");
                }
            }
            return hashMap;
        }

        static {
            Map<String, Boolean> loadDiagnostics = loadDiagnostics();
            diagnostics = loadDiagnostics;
            traceLoadsEnabled = loadDiagnostics.get(LOADS).booleanValue();
            traceSubstitutionsEnabled = loadDiagnostics.get(SUBSTITUTIONS).booleanValue();
        }

        static boolean traceLoadsEnabled() {
            return traceLoadsEnabled;
        }

        static boolean traceSubstitutionsEnabled() {
            return traceSubstitutionsEnabled;
        }
    }

    public static boolean traceLoadsEnabled() {
        try {
            return DebugHolder.traceLoadsEnabled();
        } catch (ExceptionInInitializerError e) {
            throw ConfigImplUtil.extractInitializerError(e);
        }
    }

    public static boolean traceSubstitutionsEnabled() {
        try {
            return DebugHolder.traceSubstitutionsEnabled();
        } catch (ExceptionInInitializerError e) {
            throw ConfigImplUtil.extractInitializerError(e);
        }
    }

    public static void trace(String str) {
        System.err.println(str);
    }

    public static void trace(int i, String str) {
        while (i > 0) {
            System.err.print("  ");
            i--;
        }
        System.err.println(str);
    }

    static ConfigException.NotResolved improveNotResolved(Path path, ConfigException.NotResolved notResolved) {
        String str = path.render() + " has not been resolved, you need to call Config#resolve(), see API docs for Config#resolve()";
        if (str.equals(notResolved.getMessage())) {
            return notResolved;
        }
        return new ConfigException.NotResolved(str, notResolved);
    }

    public static ConfigOrigin newSimpleOrigin(String str) {
        if (str == null) {
            return defaultValueOrigin;
        }
        return SimpleConfigOrigin.newSimple(str);
    }

    public static ConfigOrigin newFileOrigin(String str) {
        return SimpleConfigOrigin.newFile(str);
    }

    public static ConfigOrigin newURLOrigin(URL url) {
        return SimpleConfigOrigin.newURL(url);
    }
}
