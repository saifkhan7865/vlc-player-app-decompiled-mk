package com.typesafe.config;

import com.typesafe.config.ConfigException;
import com.typesafe.config.impl.ConfigImpl;
import com.typesafe.config.impl.Parseable;
import j$.util.Optional;
import java.io.File;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Callable;

public final class ConfigFactory {
    private static final String OVERRIDE_WITH_ENV_PROPERTY_NAME = "config.override_with_env_vars";
    private static final String STRATEGY_PROPERTY_NAME = "config.strategy";

    private ConfigFactory() {
    }

    public static Config load(String str) {
        return load(str, ConfigParseOptions.defaults(), ConfigResolveOptions.defaults());
    }

    public static Config load(ClassLoader classLoader, String str) {
        return load(str, ConfigParseOptions.defaults().setClassLoader(classLoader), ConfigResolveOptions.defaults());
    }

    public static Config load(String str, ConfigParseOptions configParseOptions, ConfigResolveOptions configResolveOptions) {
        ConfigParseOptions ensureClassLoader = ensureClassLoader(configParseOptions, "load");
        return load(ensureClassLoader.getClassLoader(), parseResourcesAnySyntax(str, ensureClassLoader), configResolveOptions);
    }

    public static Config load(ClassLoader classLoader, String str, ConfigParseOptions configParseOptions, ConfigResolveOptions configResolveOptions) {
        return load(str, configParseOptions.setClassLoader(classLoader), configResolveOptions);
    }

    private static ClassLoader checkedContextClassLoader(String str) {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        if (contextClassLoader != null) {
            return contextClassLoader;
        }
        throw new ConfigException.BugOrBroken("Context class loader is not set for the current thread; if Thread.currentThread().getContextClassLoader() returns null, you must pass a ClassLoader explicitly to ConfigFactory." + str);
    }

    private static ConfigParseOptions ensureClassLoader(ConfigParseOptions configParseOptions, String str) {
        return configParseOptions.getClassLoader() == null ? configParseOptions.setClassLoader(checkedContextClassLoader(str)) : configParseOptions;
    }

    public static Config load(Config config) {
        return load(checkedContextClassLoader("load"), config);
    }

    public static Config load(ClassLoader classLoader, Config config) {
        return load(classLoader, config, ConfigResolveOptions.defaults());
    }

    public static Config load(Config config, ConfigResolveOptions configResolveOptions) {
        return load(checkedContextClassLoader("load"), config, configResolveOptions);
    }

    public static Config load(ClassLoader classLoader, Config config, ConfigResolveOptions configResolveOptions) {
        return defaultOverrides(classLoader).withFallback(config).withFallback(ConfigImpl.defaultReferenceUnresolved(classLoader)).resolve(configResolveOptions);
    }

    public static Config load() {
        return load(checkedContextClassLoader("load"));
    }

    public static Config load(ConfigParseOptions configParseOptions) {
        return load(configParseOptions, ConfigResolveOptions.defaults());
    }

    public static Config load(final ClassLoader classLoader) {
        final ConfigParseOptions classLoader2 = ConfigParseOptions.defaults().setClassLoader(classLoader);
        return ConfigImpl.computeCachedConfig(classLoader, "load", new Callable<Config>() {
            public Config call() {
                return ConfigFactory.load(classLoader, ConfigFactory.defaultApplication(classLoader2));
            }
        });
    }

    public static Config load(ClassLoader classLoader, ConfigParseOptions configParseOptions) {
        return load(configParseOptions.setClassLoader(classLoader));
    }

    public static Config load(ClassLoader classLoader, ConfigResolveOptions configResolveOptions) {
        return load(classLoader, ConfigParseOptions.defaults(), configResolveOptions);
    }

    public static Config load(ClassLoader classLoader, ConfigParseOptions configParseOptions, ConfigResolveOptions configResolveOptions) {
        return load(classLoader, defaultApplication(ensureClassLoader(configParseOptions, "load")), configResolveOptions);
    }

    public static Config load(ConfigParseOptions configParseOptions, ConfigResolveOptions configResolveOptions) {
        return load(defaultApplication(ensureClassLoader(configParseOptions, "load")), configResolveOptions);
    }

    public static Config defaultReference() {
        return defaultReference(checkedContextClassLoader("defaultReference"));
    }

    public static Config defaultReference(ClassLoader classLoader) {
        return ConfigImpl.defaultReference(classLoader);
    }

    public static Config defaultReferenceUnresolved() {
        return defaultReferenceUnresolved(checkedContextClassLoader("defaultReferenceUnresolved"));
    }

    public static Config defaultReferenceUnresolved(ClassLoader classLoader) {
        return ConfigImpl.defaultReferenceUnresolved(classLoader);
    }

    public static Config defaultOverrides() {
        if (getOverrideWithEnv().booleanValue()) {
            return systemEnvironmentOverrides().withFallback(systemProperties());
        }
        return systemProperties();
    }

    public static Config defaultOverrides(ClassLoader classLoader) {
        return defaultOverrides();
    }

    public static Config defaultApplication() {
        return defaultApplication(ConfigParseOptions.defaults());
    }

    public static Config defaultApplication(ClassLoader classLoader) {
        return defaultApplication(ConfigParseOptions.defaults().setClassLoader(classLoader));
    }

    public static Config defaultApplication(ConfigParseOptions configParseOptions) {
        return getConfigLoadingStrategy().parseApplicationConfig(ensureClassLoader(configParseOptions, "defaultApplication"));
    }

    public static void invalidateCaches() {
        ConfigImpl.reloadSystemPropertiesConfig();
        ConfigImpl.reloadEnvVariablesConfig();
        ConfigImpl.reloadEnvVariablesOverridesConfig();
    }

    public static Config empty() {
        return empty((String) null);
    }

    public static Config empty(String str) {
        return ConfigImpl.emptyConfig(str);
    }

    public static Config systemProperties() {
        return ConfigImpl.systemPropertiesAsConfig();
    }

    public static Config systemEnvironmentOverrides() {
        return ConfigImpl.envVariablesOverridesAsConfig();
    }

    public static Config systemEnvironment() {
        return ConfigImpl.envVariablesAsConfig();
    }

    public static Config parseProperties(Properties properties, ConfigParseOptions configParseOptions) {
        return Parseable.newProperties(properties, configParseOptions).parse().toConfig();
    }

    public static Config parseProperties(Properties properties) {
        return parseProperties(properties, ConfigParseOptions.defaults());
    }

    public static Config parseReader(Reader reader, ConfigParseOptions configParseOptions) {
        return Parseable.newReader(reader, configParseOptions).parse().toConfig();
    }

    public static Config parseReader(Reader reader) {
        return parseReader(reader, ConfigParseOptions.defaults());
    }

    public static Config parseURL(URL url, ConfigParseOptions configParseOptions) {
        return Parseable.newURL(url, configParseOptions).parse().toConfig();
    }

    public static Config parseURL(URL url) {
        return parseURL(url, ConfigParseOptions.defaults());
    }

    public static Config parseFile(File file, ConfigParseOptions configParseOptions) {
        return Parseable.newFile(file, configParseOptions).parse().toConfig();
    }

    public static Config parseFile(File file) {
        return parseFile(file, ConfigParseOptions.defaults());
    }

    public static Config parseFileAnySyntax(File file, ConfigParseOptions configParseOptions) {
        return ConfigImpl.parseFileAnySyntax(file, configParseOptions).toConfig();
    }

    public static Config parseFileAnySyntax(File file) {
        return parseFileAnySyntax(file, ConfigParseOptions.defaults());
    }

    public static Config parseResources(Class<?> cls, String str, ConfigParseOptions configParseOptions) {
        return Parseable.newResources(cls, str, configParseOptions).parse().toConfig();
    }

    public static Config parseResources(Class<?> cls, String str) {
        return parseResources(cls, str, ConfigParseOptions.defaults());
    }

    public static Config parseResourcesAnySyntax(Class<?> cls, String str, ConfigParseOptions configParseOptions) {
        return ConfigImpl.parseResourcesAnySyntax(cls, str, configParseOptions).toConfig();
    }

    public static Config parseResourcesAnySyntax(Class<?> cls, String str) {
        return parseResourcesAnySyntax(cls, str, ConfigParseOptions.defaults());
    }

    public static Config parseResources(ClassLoader classLoader, String str, ConfigParseOptions configParseOptions) {
        return parseResources(str, configParseOptions.setClassLoader(classLoader));
    }

    public static Config parseResources(ClassLoader classLoader, String str) {
        return parseResources(classLoader, str, ConfigParseOptions.defaults());
    }

    public static Config parseResourcesAnySyntax(ClassLoader classLoader, String str, ConfigParseOptions configParseOptions) {
        return ConfigImpl.parseResourcesAnySyntax(str, configParseOptions.setClassLoader(classLoader)).toConfig();
    }

    public static Config parseResourcesAnySyntax(ClassLoader classLoader, String str) {
        return parseResourcesAnySyntax(classLoader, str, ConfigParseOptions.defaults());
    }

    public static Config parseResources(String str, ConfigParseOptions configParseOptions) {
        return Parseable.newResources(str, ensureClassLoader(configParseOptions, "parseResources")).parse().toConfig();
    }

    public static Config parseResources(String str) {
        return parseResources(str, ConfigParseOptions.defaults());
    }

    public static Config parseResourcesAnySyntax(String str, ConfigParseOptions configParseOptions) {
        return ConfigImpl.parseResourcesAnySyntax(str, configParseOptions).toConfig();
    }

    public static Config parseResourcesAnySyntax(String str) {
        return parseResourcesAnySyntax(str, ConfigParseOptions.defaults());
    }

    public static Optional<Config> parseApplicationReplacement() {
        return parseApplicationReplacement(ConfigParseOptions.defaults());
    }

    public static Optional<Config> parseApplicationReplacement(ClassLoader classLoader) {
        return parseApplicationReplacement(ConfigParseOptions.defaults().setClassLoader(classLoader));
    }

    public static Optional<Config> parseApplicationReplacement(ConfigParseOptions configParseOptions) {
        ClassLoader classLoader = ensureClassLoader(configParseOptions, "parseApplicationReplacement").getClassLoader();
        String property = System.getProperty("config.resource");
        int i = property != null ? 1 : 0;
        String property2 = System.getProperty("config.file");
        if (property2 != null) {
            i++;
        }
        String property3 = System.getProperty("config.url");
        if (property3 != null) {
            i++;
        }
        if (i == 0) {
            return Optional.empty();
        }
        if (i <= 1) {
            ConfigParseOptions allowMissing = configParseOptions.setAllowMissing(false);
            if (property != null) {
                if (property.startsWith("/")) {
                    property = property.substring(1);
                }
                return Optional.of(parseResources(classLoader, property, allowMissing));
            } else if (property2 != null) {
                return Optional.of(parseFile(new File(property2), allowMissing));
            } else {
                try {
                    return Optional.of(parseURL(new URL(property3), allowMissing));
                } catch (MalformedURLException e) {
                    throw new ConfigException.Generic("Bad URL in config.url system property: '" + property3 + "': " + e.getMessage(), e);
                }
            }
        } else {
            throw new ConfigException.Generic("You set more than one of config.file='" + property2 + "', config.url='" + property3 + "', config.resource='" + property + "'; don't know which one to use!");
        }
    }

    public static Config parseString(String str, ConfigParseOptions configParseOptions) {
        return Parseable.newString(str, configParseOptions).parse().toConfig();
    }

    public static Config parseString(String str) {
        return parseString(str, ConfigParseOptions.defaults());
    }

    public static Config parseMap(Map<String, ? extends Object> map, String str) {
        return ConfigImpl.fromPathMap(map, str).toConfig();
    }

    public static Config parseMap(Map<String, ? extends Object> map) {
        return parseMap(map, (String) null);
    }

    private static ConfigLoadingStrategy getConfigLoadingStrategy() {
        String property = System.getProperties().getProperty(STRATEGY_PROPERTY_NAME);
        if (property == null) {
            return new DefaultConfigLoadingStrategy();
        }
        try {
            return (ConfigLoadingStrategy) Class.forName(property).asSubclass(ConfigLoadingStrategy.class).getDeclaredConstructor((Class[]) null).newInstance((Object[]) null);
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (cause == null) {
                throw new ConfigException.BugOrBroken("Failed to load strategy: " + property, e);
            }
            throw new ConfigException.BugOrBroken("Failed to load strategy: " + property, cause);
        } catch (Throwable th) {
            throw new ConfigException.BugOrBroken("Failed to load strategy: " + property, th);
        }
    }

    private static Boolean getOverrideWithEnv() {
        return Boolean.valueOf(Boolean.parseBoolean(System.getProperties().getProperty(OVERRIDE_WITH_ENV_PROPERTY_NAME)));
    }
}
