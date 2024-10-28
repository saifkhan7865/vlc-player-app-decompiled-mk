package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigIncludeContext;
import com.typesafe.config.ConfigIncluder;
import com.typesafe.config.ConfigIncluderClasspath;
import com.typesafe.config.ConfigIncluderFile;
import com.typesafe.config.ConfigIncluderURL;
import com.typesafe.config.ConfigObject;
import com.typesafe.config.ConfigParseOptions;
import com.typesafe.config.ConfigParseable;
import com.typesafe.config.ConfigSyntax;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

class SimpleIncluder implements FullIncluder {
    private ConfigIncluder fallback;

    interface NameSource {
        ConfigParseable nameToParseable(String str, ConfigParseOptions configParseOptions);
    }

    SimpleIncluder(ConfigIncluder configIncluder) {
        this.fallback = configIncluder;
    }

    static ConfigParseOptions clearForInclude(ConfigParseOptions configParseOptions) {
        return configParseOptions.setSyntax((ConfigSyntax) null).setOriginDescription((String) null).setAllowMissing(true);
    }

    public ConfigObject include(ConfigIncludeContext configIncludeContext, String str) {
        ConfigObject includeWithoutFallback = includeWithoutFallback(configIncludeContext, str);
        ConfigIncluder configIncluder = this.fallback;
        return configIncluder != null ? includeWithoutFallback.withFallback(configIncluder.include(configIncludeContext, str)) : includeWithoutFallback;
    }

    static ConfigObject includeWithoutFallback(ConfigIncludeContext configIncludeContext, String str) {
        URL url;
        try {
            url = new URL(str);
        } catch (MalformedURLException unused) {
            url = null;
        }
        if (url != null) {
            return includeURLWithoutFallback(configIncludeContext, url);
        }
        return fromBasename(new RelativeNameSource(configIncludeContext), str, configIncludeContext.parseOptions());
    }

    public ConfigObject includeURL(ConfigIncludeContext configIncludeContext, URL url) {
        ConfigObject includeURLWithoutFallback = includeURLWithoutFallback(configIncludeContext, url);
        ConfigIncluder configIncluder = this.fallback;
        return (configIncluder == null || !(configIncluder instanceof ConfigIncluderURL)) ? includeURLWithoutFallback : includeURLWithoutFallback.withFallback(((ConfigIncluderURL) configIncluder).includeURL(configIncludeContext, url));
    }

    static ConfigObject includeURLWithoutFallback(ConfigIncludeContext configIncludeContext, URL url) {
        return ConfigFactory.parseURL(url, configIncludeContext.parseOptions()).root();
    }

    public ConfigObject includeFile(ConfigIncludeContext configIncludeContext, File file) {
        ConfigObject includeFileWithoutFallback = includeFileWithoutFallback(configIncludeContext, file);
        ConfigIncluder configIncluder = this.fallback;
        return (configIncluder == null || !(configIncluder instanceof ConfigIncluderFile)) ? includeFileWithoutFallback : includeFileWithoutFallback.withFallback(((ConfigIncluderFile) configIncluder).includeFile(configIncludeContext, file));
    }

    static ConfigObject includeFileWithoutFallback(ConfigIncludeContext configIncludeContext, File file) {
        return ConfigFactory.parseFileAnySyntax(file, configIncludeContext.parseOptions()).root();
    }

    public ConfigObject includeResources(ConfigIncludeContext configIncludeContext, String str) {
        ConfigObject includeResourceWithoutFallback = includeResourceWithoutFallback(configIncludeContext, str);
        ConfigIncluder configIncluder = this.fallback;
        return (configIncluder == null || !(configIncluder instanceof ConfigIncluderClasspath)) ? includeResourceWithoutFallback : includeResourceWithoutFallback.withFallback(((ConfigIncluderClasspath) configIncluder).includeResources(configIncludeContext, str));
    }

    static ConfigObject includeResourceWithoutFallback(ConfigIncludeContext configIncludeContext, String str) {
        return ConfigFactory.parseResourcesAnySyntax(str, configIncludeContext.parseOptions()).root();
    }

    public ConfigIncluder withFallback(ConfigIncluder configIncluder) {
        if (this != configIncluder) {
            ConfigIncluder configIncluder2 = this.fallback;
            if (configIncluder2 == configIncluder) {
                return this;
            }
            if (configIncluder2 != null) {
                return new SimpleIncluder(configIncluder2.withFallback(configIncluder));
            }
            return new SimpleIncluder(configIncluder);
        }
        throw new ConfigException.BugOrBroken("trying to create includer cycle");
    }

    private static class RelativeNameSource implements NameSource {
        private final ConfigIncludeContext context;

        RelativeNameSource(ConfigIncludeContext configIncludeContext) {
            this.context = configIncludeContext;
        }

        public ConfigParseable nameToParseable(String str, ConfigParseOptions configParseOptions) {
            ConfigParseable relativeTo = this.context.relativeTo(str);
            if (relativeTo != null) {
                return relativeTo;
            }
            return Parseable.newNotFound(str, "include was not found: '" + str + "'", configParseOptions);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x00d1 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0142  */
    /* JADX WARNING: Removed duplicated region for block: B:56:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.typesafe.config.ConfigObject fromBasename(com.typesafe.config.impl.SimpleIncluder.NameSource r9, java.lang.String r10, com.typesafe.config.ConfigParseOptions r11) {
        /*
            java.lang.String r0 = ".conf"
            boolean r1 = r10.endsWith(r0)
            if (r1 != 0) goto L_0x015f
            java.lang.String r1 = ".json"
            boolean r2 = r10.endsWith(r1)
            if (r2 != 0) goto L_0x015f
            java.lang.String r2 = ".properties"
            boolean r3 = r10.endsWith(r2)
            if (r3 == 0) goto L_0x001a
            goto L_0x015f
        L_0x001a:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r10)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            com.typesafe.config.ConfigParseable r0 = r9.nameToParseable(r0, r11)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r10)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            com.typesafe.config.ConfigParseable r1 = r9.nameToParseable(r1, r11)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r10)
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            com.typesafe.config.ConfigParseable r9 = r9.nameToParseable(r2, r11)
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            com.typesafe.config.ConfigSyntax r3 = r11.getSyntax()
            com.typesafe.config.impl.SimpleConfigOrigin r4 = com.typesafe.config.impl.SimpleConfigOrigin.newSimple(r10)
            com.typesafe.config.impl.SimpleConfigObject r4 = com.typesafe.config.impl.SimpleConfigObject.empty(r4)
            r5 = 1
            r6 = 0
            if (r3 == 0) goto L_0x006c
            com.typesafe.config.ConfigSyntax r7 = com.typesafe.config.ConfigSyntax.CONF
            if (r3 != r7) goto L_0x0084
        L_0x006c:
            com.typesafe.config.ConfigParseOptions r7 = r0.options()     // Catch:{ IO -> 0x0080 }
            com.typesafe.config.ConfigParseOptions r7 = r7.setAllowMissing(r6)     // Catch:{ IO -> 0x0080 }
            com.typesafe.config.ConfigSyntax r8 = com.typesafe.config.ConfigSyntax.CONF     // Catch:{ IO -> 0x0080 }
            com.typesafe.config.ConfigParseOptions r7 = r7.setSyntax(r8)     // Catch:{ IO -> 0x0080 }
            com.typesafe.config.ConfigObject r4 = r0.parse(r7)     // Catch:{ IO -> 0x0080 }
            r0 = 1
            goto L_0x0085
        L_0x0080:
            r0 = move-exception
            r2.add(r0)
        L_0x0084:
            r0 = 0
        L_0x0085:
            if (r3 == 0) goto L_0x008b
            com.typesafe.config.ConfigSyntax r7 = com.typesafe.config.ConfigSyntax.JSON
            if (r3 != r7) goto L_0x00a7
        L_0x008b:
            com.typesafe.config.ConfigParseOptions r7 = r1.options()     // Catch:{ IO -> 0x00a3 }
            com.typesafe.config.ConfigParseOptions r7 = r7.setAllowMissing(r6)     // Catch:{ IO -> 0x00a3 }
            com.typesafe.config.ConfigSyntax r8 = com.typesafe.config.ConfigSyntax.JSON     // Catch:{ IO -> 0x00a3 }
            com.typesafe.config.ConfigParseOptions r7 = r7.setSyntax(r8)     // Catch:{ IO -> 0x00a3 }
            com.typesafe.config.ConfigObject r1 = r1.parse(r7)     // Catch:{ IO -> 0x00a3 }
            com.typesafe.config.ConfigObject r4 = r4.withFallback(r1)     // Catch:{ IO -> 0x00a3 }
            r0 = 1
            goto L_0x00a7
        L_0x00a3:
            r1 = move-exception
            r2.add(r1)
        L_0x00a7:
            if (r3 == 0) goto L_0x00ad
            com.typesafe.config.ConfigSyntax r1 = com.typesafe.config.ConfigSyntax.PROPERTIES
            if (r3 != r1) goto L_0x00c8
        L_0x00ad:
            com.typesafe.config.ConfigParseOptions r1 = r9.options()     // Catch:{ IO -> 0x00c4 }
            com.typesafe.config.ConfigParseOptions r1 = r1.setAllowMissing(r6)     // Catch:{ IO -> 0x00c4 }
            com.typesafe.config.ConfigSyntax r3 = com.typesafe.config.ConfigSyntax.PROPERTIES     // Catch:{ IO -> 0x00c4 }
            com.typesafe.config.ConfigParseOptions r1 = r1.setSyntax(r3)     // Catch:{ IO -> 0x00c4 }
            com.typesafe.config.ConfigObject r9 = r9.parse(r1)     // Catch:{ IO -> 0x00c4 }
            com.typesafe.config.ConfigObject r4 = r4.withFallback(r9)     // Catch:{ IO -> 0x00c4 }
            goto L_0x00c9
        L_0x00c4:
            r9 = move-exception
            r2.add(r9)
        L_0x00c8:
            r5 = r0
        L_0x00c9:
            boolean r9 = r11.getAllowMissing()
            java.lang.String r11 = "Did not find '"
            if (r9 != 0) goto L_0x013a
            if (r5 != 0) goto L_0x013a
            boolean r9 = com.typesafe.config.impl.ConfigImpl.traceLoadsEnabled()
            if (r9 == 0) goto L_0x00ed
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>(r11)
            r9.append(r10)
            java.lang.String r11 = "' with any extension (.conf, .json, .properties); exceptions should have been logged above."
            r9.append(r11)
            java.lang.String r9 = r9.toString()
            com.typesafe.config.impl.ConfigImpl.trace(r9)
        L_0x00ed:
            boolean r9 = r2.isEmpty()
            if (r9 != 0) goto L_0x0132
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.util.Iterator r11 = r2.iterator()
        L_0x00fc:
            boolean r0 = r11.hasNext()
            if (r0 == 0) goto L_0x0115
            java.lang.Object r0 = r11.next()
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            java.lang.String r0 = r0.getMessage()
            r9.append(r0)
            java.lang.String r0 = ", "
            r9.append(r0)
            goto L_0x00fc
        L_0x0115:
            int r11 = r9.length()
            int r11 = r11 + -2
            r9.setLength(r11)
            com.typesafe.config.ConfigException$IO r11 = new com.typesafe.config.ConfigException$IO
            com.typesafe.config.impl.SimpleConfigOrigin r10 = com.typesafe.config.impl.SimpleConfigOrigin.newSimple(r10)
            java.lang.String r9 = r9.toString()
            java.lang.Object r0 = r2.get(r6)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r11.<init>(r10, r9, r0)
            throw r11
        L_0x0132:
            com.typesafe.config.ConfigException$BugOrBroken r9 = new com.typesafe.config.ConfigException$BugOrBroken
            java.lang.String r10 = "should not be reached: nothing found but no exceptions thrown"
            r9.<init>(r10)
            throw r9
        L_0x013a:
            if (r5 != 0) goto L_0x0173
            boolean r9 = com.typesafe.config.impl.ConfigImpl.traceLoadsEnabled()
            if (r9 == 0) goto L_0x0173
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>(r11)
            r9.append(r10)
            java.lang.String r11 = "' with any extension (.conf, .json, .properties); but '"
            r9.append(r11)
            r9.append(r10)
            java.lang.String r10 = "' is allowed to be missing. Exceptions from load attempts should have been logged above."
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            com.typesafe.config.impl.ConfigImpl.trace(r9)
            goto L_0x0173
        L_0x015f:
            com.typesafe.config.ConfigParseable r9 = r9.nameToParseable(r10, r11)
            com.typesafe.config.ConfigParseOptions r10 = r9.options()
            boolean r11 = r11.getAllowMissing()
            com.typesafe.config.ConfigParseOptions r10 = r10.setAllowMissing(r11)
            com.typesafe.config.ConfigObject r4 = r9.parse(r10)
        L_0x0173:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.typesafe.config.impl.SimpleIncluder.fromBasename(com.typesafe.config.impl.SimpleIncluder$NameSource, java.lang.String, com.typesafe.config.ConfigParseOptions):com.typesafe.config.ConfigObject");
    }

    private static class Proxy implements FullIncluder {
        final ConfigIncluder delegate;

        public ConfigIncluder withFallback(ConfigIncluder configIncluder) {
            return this;
        }

        Proxy(ConfigIncluder configIncluder) {
            this.delegate = configIncluder;
        }

        public ConfigObject include(ConfigIncludeContext configIncludeContext, String str) {
            return this.delegate.include(configIncludeContext, str);
        }

        public ConfigObject includeResources(ConfigIncludeContext configIncludeContext, String str) {
            ConfigIncluder configIncluder = this.delegate;
            if (configIncluder instanceof ConfigIncluderClasspath) {
                return ((ConfigIncluderClasspath) configIncluder).includeResources(configIncludeContext, str);
            }
            return SimpleIncluder.includeResourceWithoutFallback(configIncludeContext, str);
        }

        public ConfigObject includeURL(ConfigIncludeContext configIncludeContext, URL url) {
            ConfigIncluder configIncluder = this.delegate;
            if (configIncluder instanceof ConfigIncluderURL) {
                return ((ConfigIncluderURL) configIncluder).includeURL(configIncludeContext, url);
            }
            return SimpleIncluder.includeURLWithoutFallback(configIncludeContext, url);
        }

        public ConfigObject includeFile(ConfigIncludeContext configIncludeContext, File file) {
            ConfigIncluder configIncluder = this.delegate;
            if (configIncluder instanceof ConfigIncluderFile) {
                return ((ConfigIncluderFile) configIncluder).includeFile(configIncludeContext, file);
            }
            return SimpleIncluder.includeFileWithoutFallback(configIncludeContext, file);
        }
    }

    static FullIncluder makeFull(ConfigIncluder configIncluder) {
        if (configIncluder instanceof FullIncluder) {
            return (FullIncluder) configIncluder;
        }
        return new Proxy(configIncluder);
    }
}
