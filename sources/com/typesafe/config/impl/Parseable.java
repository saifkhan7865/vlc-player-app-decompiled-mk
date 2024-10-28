package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigIncludeContext;
import com.typesafe.config.ConfigMergeable;
import com.typesafe.config.ConfigObject;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigParseOptions;
import com.typesafe.config.ConfigParseable;
import com.typesafe.config.ConfigSyntax;
import com.typesafe.config.ConfigValue;
import com.typesafe.config.parser.ConfigDocument;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilterReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Properties;

public abstract class Parseable implements ConfigParseable {
    private static final int MAX_INCLUDE_DEPTH = 50;
    private static final String hoconContentType = "application/hocon";
    private static final String jsonContentType = "application/json";
    private static final ThreadLocal<LinkedList<Parseable>> parseStack = new ThreadLocal<LinkedList<Parseable>>() {
        /* access modifiers changed from: protected */
        public LinkedList<Parseable> initialValue() {
            return new LinkedList<>();
        }
    };
    private static final String propertiesContentType = "text/x-java-properties";
    private ConfigIncludeContext includeContext;
    private ConfigParseOptions initialOptions;
    private ConfigOrigin initialOrigin;

    protected interface Relativizer {
        ConfigParseable relativeTo(String str);
    }

    /* access modifiers changed from: package-private */
    public ConfigSyntax contentType() {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract ConfigOrigin createOrigin();

    /* access modifiers changed from: package-private */
    public ConfigSyntax guessSyntax() {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract Reader reader() throws IOException;

    protected Parseable() {
    }

    private ConfigParseOptions fixupOptions(ConfigParseOptions configParseOptions) {
        ConfigSyntax syntax = configParseOptions.getSyntax();
        if (syntax == null) {
            syntax = guessSyntax();
        }
        if (syntax == null) {
            syntax = ConfigSyntax.CONF;
        }
        ConfigParseOptions appendIncluder = configParseOptions.setSyntax(syntax).appendIncluder(ConfigImpl.defaultIncluder());
        return appendIncluder.setIncluder(SimpleIncluder.makeFull(appendIncluder.getIncluder()));
    }

    /* access modifiers changed from: protected */
    public void postConstruct(ConfigParseOptions configParseOptions) {
        this.initialOptions = fixupOptions(configParseOptions);
        this.includeContext = new SimpleIncludeContext(this);
        if (this.initialOptions.getOriginDescription() != null) {
            this.initialOrigin = SimpleConfigOrigin.newSimple(this.initialOptions.getOriginDescription());
        } else {
            this.initialOrigin = createOrigin();
        }
    }

    /* access modifiers changed from: protected */
    public Reader reader(ConfigParseOptions configParseOptions) throws IOException {
        return reader();
    }

    protected static void trace(String str) {
        if (ConfigImpl.traceLoadsEnabled()) {
            ConfigImpl.trace(str);
        }
    }

    /* access modifiers changed from: package-private */
    public ConfigParseable relativeTo(String str) {
        if (str.startsWith("/")) {
            str = str.substring(1);
        }
        return newResources(str, options().setOriginDescription((String) null));
    }

    /* access modifiers changed from: package-private */
    public ConfigIncludeContext includeContext() {
        return this.includeContext;
    }

    static AbstractConfigObject forceParsedToObject(ConfigValue configValue) {
        if (configValue instanceof AbstractConfigObject) {
            return (AbstractConfigObject) configValue;
        }
        throw new ConfigException.WrongType(configValue.origin(), "", "object at file root", configValue.valueType().name());
    }

    public ConfigObject parse(ConfigParseOptions configParseOptions) {
        ThreadLocal<LinkedList<Parseable>> threadLocal = parseStack;
        LinkedList linkedList = threadLocal.get();
        if (linkedList.size() < 50) {
            linkedList.addFirst(this);
            try {
                AbstractConfigObject forceParsedToObject = forceParsedToObject(parseValue(configParseOptions));
                linkedList.removeFirst();
                if (linkedList.isEmpty()) {
                    threadLocal.remove();
                }
                return forceParsedToObject;
            } catch (Throwable th) {
                linkedList.removeFirst();
                if (linkedList.isEmpty()) {
                    parseStack.remove();
                }
                throw th;
            }
        } else {
            ConfigOrigin configOrigin = this.initialOrigin;
            throw new ConfigException.Parse(configOrigin, "include statements nested more than 50 times, you probably have a cycle in your includes. Trace: " + linkedList);
        }
    }

    /* access modifiers changed from: package-private */
    public final AbstractConfigValue parseValue(ConfigParseOptions configParseOptions) {
        ConfigOrigin configOrigin;
        ConfigParseOptions fixupOptions = fixupOptions(configParseOptions);
        if (fixupOptions.getOriginDescription() != null) {
            configOrigin = SimpleConfigOrigin.newSimple(fixupOptions.getOriginDescription());
        } else {
            configOrigin = this.initialOrigin;
        }
        return parseValue(configOrigin, fixupOptions);
    }

    private final AbstractConfigValue parseValue(ConfigOrigin configOrigin, ConfigParseOptions configParseOptions) {
        try {
            return rawParseValue(configOrigin, configParseOptions);
        } catch (IOException e) {
            if (configParseOptions.getAllowMissing()) {
                trace(e.getMessage() + ". Allowing Missing File, this can be turned off by setting ConfigParseOptions.allowMissing = false");
                return SimpleConfigObject.emptyMissing(configOrigin);
            }
            trace("exception loading " + configOrigin.description() + ": " + e.getClass().getName() + ": " + e.getMessage());
            throw new ConfigException.IO(configOrigin, e.getClass().getName() + ": " + e.getMessage(), e);
        }
    }

    /* access modifiers changed from: package-private */
    public final ConfigDocument parseDocument(ConfigParseOptions configParseOptions) {
        ConfigOrigin configOrigin;
        ConfigParseOptions fixupOptions = fixupOptions(configParseOptions);
        if (fixupOptions.getOriginDescription() != null) {
            configOrigin = SimpleConfigOrigin.newSimple(fixupOptions.getOriginDescription());
        } else {
            configOrigin = this.initialOrigin;
        }
        return parseDocument(configOrigin, fixupOptions);
    }

    private final ConfigDocument parseDocument(ConfigOrigin configOrigin, ConfigParseOptions configParseOptions) {
        try {
            return rawParseDocument(configOrigin, configParseOptions);
        } catch (IOException e) {
            if (configParseOptions.getAllowMissing()) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new ConfigNodeObject(new ArrayList()));
                return new SimpleConfigDocument(new ConfigNodeRoot(arrayList, configOrigin), configParseOptions);
            }
            trace("exception loading " + configOrigin.description() + ": " + e.getClass().getName() + ": " + e.getMessage());
            throw new ConfigException.IO(configOrigin, e.getClass().getName() + ": " + e.getMessage(), e);
        }
    }

    /* access modifiers changed from: protected */
    public AbstractConfigValue rawParseValue(ConfigOrigin configOrigin, ConfigParseOptions configParseOptions) throws IOException {
        Reader reader = reader(configParseOptions);
        ConfigSyntax contentType = contentType();
        if (contentType != null) {
            if (ConfigImpl.traceLoadsEnabled() && configParseOptions.getSyntax() != null) {
                trace("Overriding syntax " + configParseOptions.getSyntax() + " with Content-Type which specified " + contentType);
            }
            configParseOptions = configParseOptions.setSyntax(contentType);
        }
        try {
            return rawParseValue(reader, configOrigin, configParseOptions);
        } finally {
            reader.close();
        }
    }

    private AbstractConfigValue rawParseValue(Reader reader, ConfigOrigin configOrigin, ConfigParseOptions configParseOptions) throws IOException {
        if (configParseOptions.getSyntax() == ConfigSyntax.PROPERTIES) {
            return PropertiesParser.parse(reader, configOrigin);
        }
        return ConfigParser.parse(ConfigDocumentParser.parse(Tokenizer.tokenize(configOrigin, reader, configParseOptions.getSyntax()), configOrigin, configParseOptions), configOrigin, configParseOptions, includeContext());
    }

    /* access modifiers changed from: protected */
    public ConfigDocument rawParseDocument(ConfigOrigin configOrigin, ConfigParseOptions configParseOptions) throws IOException {
        Reader reader = reader(configParseOptions);
        ConfigSyntax contentType = contentType();
        if (contentType != null) {
            if (ConfigImpl.traceLoadsEnabled() && configParseOptions.getSyntax() != null) {
                trace("Overriding syntax " + configParseOptions.getSyntax() + " with Content-Type which specified " + contentType);
            }
            configParseOptions = configParseOptions.setSyntax(contentType);
        }
        try {
            return rawParseDocument(reader, configOrigin, configParseOptions);
        } finally {
            reader.close();
        }
    }

    private ConfigDocument rawParseDocument(Reader reader, ConfigOrigin configOrigin, ConfigParseOptions configParseOptions) throws IOException {
        return new SimpleConfigDocument(ConfigDocumentParser.parse(Tokenizer.tokenize(configOrigin, reader, configParseOptions.getSyntax()), configOrigin, configParseOptions), configParseOptions);
    }

    public ConfigObject parse() {
        return forceParsedToObject(parseValue(options()));
    }

    public ConfigDocument parseConfigDocument() {
        return parseDocument(options());
    }

    /* access modifiers changed from: package-private */
    public AbstractConfigValue parseValue() {
        return parseValue(options());
    }

    public final ConfigOrigin origin() {
        return this.initialOrigin;
    }

    public ConfigParseOptions options() {
        return this.initialOptions;
    }

    public String toString() {
        return getClass().getSimpleName();
    }

    /* access modifiers changed from: private */
    public static Reader readerFromStream(InputStream inputStream) {
        return readerFromStream(inputStream, "UTF-8");
    }

    private static Reader readerFromStream(InputStream inputStream, String str) {
        try {
            return new BufferedReader(new InputStreamReader(inputStream, str));
        } catch (UnsupportedEncodingException e) {
            throw new ConfigException.BugOrBroken("Java runtime does not support UTF-8", e);
        }
    }

    private static Reader doNotClose(Reader reader) {
        return new FilterReader(reader) {
            public void close() {
            }
        };
    }

    static URL relativeTo(URL url, String str) {
        if (new File(str).isAbsolute()) {
            return null;
        }
        try {
            return url.toURI().resolve(new URI(str)).toURL();
        } catch (IllegalArgumentException | MalformedURLException | URISyntaxException unused) {
            return null;
        }
    }

    static File relativeTo(File file, String str) {
        File parentFile;
        if (!new File(str).isAbsolute() && (parentFile = file.getParentFile()) != null) {
            return new File(parentFile, str);
        }
        return null;
    }

    private static final class ParseableNotFound extends Parseable {
        private final String message;
        private final String what;

        ParseableNotFound(String str, String str2, ConfigParseOptions configParseOptions) {
            this.what = str;
            this.message = str2;
            postConstruct(configParseOptions);
        }

        /* access modifiers changed from: protected */
        public Reader reader() throws IOException {
            throw new FileNotFoundException(this.message);
        }

        /* access modifiers changed from: protected */
        public ConfigOrigin createOrigin() {
            return SimpleConfigOrigin.newSimple(this.what);
        }
    }

    public static Parseable newNotFound(String str, String str2, ConfigParseOptions configParseOptions) {
        return new ParseableNotFound(str, str2, configParseOptions);
    }

    private static final class ParseableReader extends Parseable {
        private final Reader reader;

        ParseableReader(Reader reader2, ConfigParseOptions configParseOptions) {
            this.reader = reader2;
            postConstruct(configParseOptions);
        }

        /* access modifiers changed from: protected */
        public Reader reader() {
            if (ConfigImpl.traceLoadsEnabled()) {
                trace("Loading config from reader " + this.reader);
            }
            return this.reader;
        }

        /* access modifiers changed from: protected */
        public ConfigOrigin createOrigin() {
            return SimpleConfigOrigin.newSimple("Reader");
        }
    }

    public static Parseable newReader(Reader reader, ConfigParseOptions configParseOptions) {
        return new ParseableReader(doNotClose(reader), configParseOptions);
    }

    private static final class ParseableString extends Parseable {
        private final String input;

        ParseableString(String str, ConfigParseOptions configParseOptions) {
            this.input = str;
            postConstruct(configParseOptions);
        }

        /* access modifiers changed from: protected */
        public Reader reader() {
            if (ConfigImpl.traceLoadsEnabled()) {
                trace("Loading config from a String " + this.input);
            }
            return new StringReader(this.input);
        }

        /* access modifiers changed from: protected */
        public ConfigOrigin createOrigin() {
            return SimpleConfigOrigin.newSimple("String");
        }

        public String toString() {
            return getClass().getSimpleName() + "(" + this.input + ")";
        }
    }

    public static Parseable newString(String str, ConfigParseOptions configParseOptions) {
        return new ParseableString(str, configParseOptions);
    }

    private static class ParseableURL extends Parseable {
        private String contentType;
        protected final URL input;

        protected ParseableURL(URL url) {
            this.contentType = null;
            this.input = url;
        }

        ParseableURL(URL url, ConfigParseOptions configParseOptions) {
            this(url);
            postConstruct(configParseOptions);
        }

        /* access modifiers changed from: protected */
        public Reader reader() throws IOException {
            throw new ConfigException.BugOrBroken("reader() without options should not be called on ParseableURL");
        }

        private static String acceptContentType(ConfigParseOptions configParseOptions) {
            if (configParseOptions.getSyntax() == null) {
                return null;
            }
            int i = AnonymousClass3.$SwitchMap$com$typesafe$config$ConfigSyntax[configParseOptions.getSyntax().ordinal()];
            if (i == 1) {
                return "application/json";
            }
            if (i == 2) {
                return Parseable.hoconContentType;
            }
            if (i != 3) {
                return null;
            }
            return Parseable.propertiesContentType;
        }

        /* access modifiers changed from: protected */
        public Reader reader(ConfigParseOptions configParseOptions) throws IOException {
            try {
                if (ConfigImpl.traceLoadsEnabled()) {
                    trace("Loading config from a URL: " + this.input.toExternalForm());
                }
                URLConnection openConnection = this.input.openConnection();
                String acceptContentType = acceptContentType(configParseOptions);
                if (acceptContentType != null) {
                    openConnection.setRequestProperty("Accept", acceptContentType);
                }
                openConnection.connect();
                String contentType2 = openConnection.getContentType();
                this.contentType = contentType2;
                if (contentType2 != null) {
                    if (ConfigImpl.traceLoadsEnabled()) {
                        trace("URL sets Content-Type: '" + this.contentType + "'");
                    }
                    String trim = this.contentType.trim();
                    this.contentType = trim;
                    int indexOf = trim.indexOf(59);
                    if (indexOf >= 0) {
                        this.contentType = this.contentType.substring(0, indexOf);
                    }
                }
                return Parseable.readerFromStream(openConnection.getInputStream());
            } catch (FileNotFoundException e) {
                throw e;
            } catch (IOException e2) {
                throw new ConfigException.BugOrBroken("Cannot load config from URL: " + this.input.toExternalForm(), e2);
            }
        }

        /* access modifiers changed from: package-private */
        public ConfigSyntax guessSyntax() {
            return ConfigImplUtil.syntaxFromExtension(this.input.getPath());
        }

        /* access modifiers changed from: package-private */
        public ConfigSyntax contentType() {
            String str = this.contentType;
            if (str != null) {
                if (str.equals("application/json")) {
                    return ConfigSyntax.JSON;
                }
                if (this.contentType.equals(Parseable.propertiesContentType)) {
                    return ConfigSyntax.PROPERTIES;
                }
                if (this.contentType.equals(Parseable.hoconContentType)) {
                    return ConfigSyntax.CONF;
                }
                if (ConfigImpl.traceLoadsEnabled()) {
                    trace("'" + this.contentType + "' isn't a known content type");
                }
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public ConfigParseable relativeTo(String str) {
            URL relativeTo = relativeTo(this.input, str);
            if (relativeTo == null) {
                return null;
            }
            return newURL(relativeTo, options().setOriginDescription((String) null));
        }

        /* access modifiers changed from: protected */
        public ConfigOrigin createOrigin() {
            return SimpleConfigOrigin.newURL(this.input);
        }

        public String toString() {
            return getClass().getSimpleName() + "(" + this.input.toExternalForm() + ")";
        }
    }

    /* renamed from: com.typesafe.config.impl.Parseable$3  reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$typesafe$config$ConfigSyntax;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.typesafe.config.ConfigSyntax[] r0 = com.typesafe.config.ConfigSyntax.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$typesafe$config$ConfigSyntax = r0
                com.typesafe.config.ConfigSyntax r1 = com.typesafe.config.ConfigSyntax.JSON     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$typesafe$config$ConfigSyntax     // Catch:{ NoSuchFieldError -> 0x001d }
                com.typesafe.config.ConfigSyntax r1 = com.typesafe.config.ConfigSyntax.CONF     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$typesafe$config$ConfigSyntax     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.typesafe.config.ConfigSyntax r1 = com.typesafe.config.ConfigSyntax.PROPERTIES     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.typesafe.config.impl.Parseable.AnonymousClass3.<clinit>():void");
        }
    }

    public static Parseable newURL(URL url, ConfigParseOptions configParseOptions) {
        if (url.getProtocol().equals("file")) {
            return newFile(ConfigImplUtil.urlToFile(url), configParseOptions);
        }
        return new ParseableURL(url, configParseOptions);
    }

    private static final class ParseableFile extends Parseable {
        private final File input;

        ParseableFile(File file, ConfigParseOptions configParseOptions) {
            this.input = file;
            postConstruct(configParseOptions);
        }

        /* access modifiers changed from: protected */
        public Reader reader() throws IOException {
            if (ConfigImpl.traceLoadsEnabled()) {
                trace("Loading config from a file: " + this.input);
            }
            return Parseable.readerFromStream(new FileInputStream(this.input));
        }

        /* access modifiers changed from: package-private */
        public ConfigSyntax guessSyntax() {
            return ConfigImplUtil.syntaxFromExtension(this.input.getName());
        }

        /* access modifiers changed from: package-private */
        public ConfigParseable relativeTo(String str) {
            File file;
            if (new File(str).isAbsolute()) {
                file = new File(str);
            } else {
                file = relativeTo(this.input, str);
            }
            if (file == null) {
                return null;
            }
            if (file.exists()) {
                trace(file + " exists, so loading it as a file");
                return newFile(file, options().setOriginDescription((String) null));
            }
            trace(file + " does not exist, so trying it as a classpath resource");
            return Parseable.super.relativeTo(str);
        }

        /* access modifiers changed from: protected */
        public ConfigOrigin createOrigin() {
            return SimpleConfigOrigin.newFile(this.input.getPath());
        }

        public String toString() {
            return getClass().getSimpleName() + "(" + this.input.getPath() + ")";
        }
    }

    public static Parseable newFile(File file, ConfigParseOptions configParseOptions) {
        return new ParseableFile(file, configParseOptions);
    }

    private static final class ParseableResourceURL extends ParseableURL {
        private final Relativizer relativizer;
        private final String resource;

        ParseableResourceURL(URL url, ConfigParseOptions configParseOptions, String str, Relativizer relativizer2) {
            super(url);
            this.relativizer = relativizer2;
            this.resource = str;
            postConstruct(configParseOptions);
        }

        /* access modifiers changed from: protected */
        public ConfigOrigin createOrigin() {
            return SimpleConfigOrigin.newResource(this.resource, this.input);
        }

        /* access modifiers changed from: package-private */
        public ConfigParseable relativeTo(String str) {
            return this.relativizer.relativeTo(str);
        }
    }

    /* access modifiers changed from: private */
    public static Parseable newResourceURL(URL url, ConfigParseOptions configParseOptions, String str, Relativizer relativizer) {
        return new ParseableResourceURL(url, configParseOptions, str, relativizer);
    }

    private static final class ParseableResources extends Parseable implements Relativizer {
        private final String resource;

        ParseableResources(String str, ConfigParseOptions configParseOptions) {
            this.resource = str;
            postConstruct(configParseOptions);
        }

        /* access modifiers changed from: protected */
        public Reader reader() throws IOException {
            throw new ConfigException.BugOrBroken("reader() should not be called on resources");
        }

        /* access modifiers changed from: protected */
        public AbstractConfigObject rawParseValue(ConfigOrigin configOrigin, ConfigParseOptions configParseOptions) throws IOException {
            ClassLoader classLoader = configParseOptions.getClassLoader();
            if (classLoader != null) {
                Enumeration<URL> resources = classLoader.getResources(this.resource);
                if (!resources.hasMoreElements()) {
                    if (ConfigImpl.traceLoadsEnabled()) {
                        trace("Loading config from class loader " + classLoader + " but there were no resources called " + this.resource);
                    }
                    throw new IOException("resource not found on classpath: " + this.resource);
                }
                AbstractConfigObject empty = SimpleConfigObject.empty(configOrigin);
                while (resources.hasMoreElements()) {
                    URL nextElement = resources.nextElement();
                    if (ConfigImpl.traceLoadsEnabled()) {
                        trace("Loading config from resource '" + this.resource + "' URL " + nextElement.toExternalForm() + " from class loader " + classLoader);
                    }
                    empty = empty.withFallback((ConfigMergeable) Parseable.newResourceURL(nextElement, configParseOptions, this.resource, this).parseValue());
                }
                return empty;
            }
            throw new ConfigException.BugOrBroken("null class loader; pass in a class loader or use Thread.currentThread().setContextClassLoader()");
        }

        /* access modifiers changed from: package-private */
        public ConfigSyntax guessSyntax() {
            return ConfigImplUtil.syntaxFromExtension(this.resource);
        }

        static String parent(String str) {
            int lastIndexOf = str.lastIndexOf(47);
            if (lastIndexOf < 0) {
                return null;
            }
            return str.substring(0, lastIndexOf);
        }

        public ConfigParseable relativeTo(String str) {
            if (str.startsWith("/")) {
                return newResources(str.substring(1), options().setOriginDescription((String) null));
            }
            String parent = parent(this.resource);
            if (parent == null) {
                return newResources(str, options().setOriginDescription((String) null));
            }
            return newResources(parent + "/" + str, options().setOriginDescription((String) null));
        }

        /* access modifiers changed from: protected */
        public ConfigOrigin createOrigin() {
            return SimpleConfigOrigin.newResource(this.resource);
        }

        public String toString() {
            return getClass().getSimpleName() + "(" + this.resource + ")";
        }
    }

    public static Parseable newResources(Class<?> cls, String str, ConfigParseOptions configParseOptions) {
        return newResources(convertResourceName(cls, str), configParseOptions.setClassLoader(cls.getClassLoader()));
    }

    private static String convertResourceName(Class<?> cls, String str) {
        if (str.startsWith("/")) {
            return str.substring(1);
        }
        String name = cls.getName();
        int lastIndexOf = name.lastIndexOf(46);
        if (lastIndexOf < 0) {
            return str;
        }
        String replace = name.substring(0, lastIndexOf).replace('.', '/');
        return replace + "/" + str;
    }

    public static Parseable newResources(String str, ConfigParseOptions configParseOptions) {
        if (configParseOptions.getClassLoader() != null) {
            return new ParseableResources(str, configParseOptions);
        }
        throw new ConfigException.BugOrBroken("null class loader; pass in a class loader or use Thread.currentThread().setContextClassLoader()");
    }

    private static final class ParseableProperties extends Parseable {
        private final Properties props;

        ParseableProperties(Properties properties, ConfigParseOptions configParseOptions) {
            this.props = properties;
            postConstruct(configParseOptions);
        }

        /* access modifiers changed from: protected */
        public Reader reader() throws IOException {
            throw new ConfigException.BugOrBroken("reader() should not be called on props");
        }

        /* access modifiers changed from: protected */
        public AbstractConfigObject rawParseValue(ConfigOrigin configOrigin, ConfigParseOptions configParseOptions) {
            if (ConfigImpl.traceLoadsEnabled()) {
                trace("Loading config from properties " + this.props);
            }
            return PropertiesParser.fromProperties(configOrigin, this.props);
        }

        /* access modifiers changed from: package-private */
        public ConfigSyntax guessSyntax() {
            return ConfigSyntax.PROPERTIES;
        }

        /* access modifiers changed from: protected */
        public ConfigOrigin createOrigin() {
            return SimpleConfigOrigin.newSimple("properties");
        }

        public String toString() {
            return getClass().getSimpleName() + "(" + this.props.size() + " props)";
        }
    }

    public static Parseable newProperties(Properties properties, ConfigParseOptions configParseOptions) {
        return new ParseableProperties(properties, configParseOptions);
    }
}
