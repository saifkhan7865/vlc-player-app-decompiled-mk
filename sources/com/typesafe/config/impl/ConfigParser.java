package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigIncludeContext;
import com.typesafe.config.ConfigMergeable;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigParseOptions;
import com.typesafe.config.ConfigSyntax;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

final class ConfigParser {
    ConfigParser() {
    }

    static AbstractConfigValue parse(ConfigNodeRoot configNodeRoot, ConfigOrigin configOrigin, ConfigParseOptions configParseOptions, ConfigIncludeContext configIncludeContext) {
        return new ParseContext(configParseOptions.getSyntax(), configOrigin, configNodeRoot, SimpleIncluder.makeFull(configParseOptions.getIncluder()), configIncludeContext).parse();
    }

    private static final class ParseContext {
        int arrayCount;
        private final ConfigOrigin baseOrigin;
        private final ConfigNodeRoot document;
        private final ConfigSyntax flavor;
        private final ConfigIncludeContext includeContext;
        private final FullIncluder includer;
        private int lineNumber = 1;
        private final LinkedList<Path> pathStack;

        ParseContext(ConfigSyntax configSyntax, ConfigOrigin configOrigin, ConfigNodeRoot configNodeRoot, FullIncluder fullIncluder, ConfigIncludeContext configIncludeContext) {
            this.document = configNodeRoot;
            this.flavor = configSyntax;
            this.baseOrigin = configOrigin;
            this.includer = fullIncluder;
            this.includeContext = configIncludeContext;
            this.pathStack = new LinkedList<>();
            this.arrayCount = 0;
        }

        private AbstractConfigValue parseConcatenation(ConfigNodeConcatenation configNodeConcatenation) {
            if (this.flavor != ConfigSyntax.JSON) {
                ArrayList arrayList = new ArrayList(configNodeConcatenation.children().size());
                for (AbstractConfigNode next : configNodeConcatenation.children()) {
                    if (next instanceof AbstractConfigNodeValue) {
                        arrayList.add(parseValue((AbstractConfigNodeValue) next, (List<String>) null));
                    }
                }
                return ConfigConcatenation.concatenate(arrayList);
            }
            throw new ConfigException.BugOrBroken("Found a concatenation node in JSON");
        }

        private SimpleConfigOrigin lineOrigin() {
            return ((SimpleConfigOrigin) this.baseOrigin).withLineNumber(this.lineNumber);
        }

        private ConfigException parseError(String str) {
            return parseError(str, (Throwable) null);
        }

        private ConfigException parseError(String str, Throwable th) {
            return new ConfigException.Parse(lineOrigin(), str, th);
        }

        private Path fullCurrentPath() {
            if (!this.pathStack.isEmpty()) {
                return new Path(this.pathStack.descendingIterator());
            }
            throw new ConfigException.BugOrBroken("Bug in parser; tried to get current path when at root");
        }

        private AbstractConfigValue parseValue(AbstractConfigNodeValue abstractConfigNodeValue, List<String> list) {
            AbstractConfigValue abstractConfigValue;
            int i = this.arrayCount;
            if (abstractConfigNodeValue instanceof ConfigNodeSimpleValue) {
                abstractConfigValue = ((ConfigNodeSimpleValue) abstractConfigNodeValue).value();
            } else if (abstractConfigNodeValue instanceof ConfigNodeObject) {
                abstractConfigValue = parseObject((ConfigNodeObject) abstractConfigNodeValue);
            } else if (abstractConfigNodeValue instanceof ConfigNodeArray) {
                abstractConfigValue = parseArray((ConfigNodeArray) abstractConfigNodeValue);
            } else if (abstractConfigNodeValue instanceof ConfigNodeConcatenation) {
                abstractConfigValue = parseConcatenation((ConfigNodeConcatenation) abstractConfigNodeValue);
            } else {
                throw parseError("Expecting a value but got wrong node type: " + abstractConfigNodeValue.getClass());
            }
            if (list != null && !list.isEmpty()) {
                abstractConfigValue = abstractConfigValue.withOrigin((ConfigOrigin) abstractConfigValue.origin().prependComments(new ArrayList(list)));
                list.clear();
            }
            if (this.arrayCount == i) {
                return abstractConfigValue;
            }
            throw new ConfigException.BugOrBroken("Bug in config parser: unbalanced array count");
        }

        private static AbstractConfigObject createValueUnderPath(Path path, AbstractConfigValue abstractConfigValue) {
            ArrayList arrayList = new ArrayList();
            String first = path.first();
            Path remainder = path.remainder();
            while (first != null) {
                arrayList.add(first);
                if (remainder == null) {
                    break;
                }
                first = remainder.first();
                remainder = remainder.remainder();
            }
            ListIterator listIterator = arrayList.listIterator(arrayList.size());
            SimpleConfigObject simpleConfigObject = new SimpleConfigObject(abstractConfigValue.origin().withComments((List) null), Collections.singletonMap((String) listIterator.previous(), abstractConfigValue));
            while (listIterator.hasPrevious()) {
                simpleConfigObject = new SimpleConfigObject(abstractConfigValue.origin().withComments((List) null), Collections.singletonMap(listIterator.previous(), simpleConfigObject));
            }
            return simpleConfigObject;
        }

        private void parseInclude(Map<String, AbstractConfigValue> map, ConfigNodeInclude configNodeInclude) {
            AbstractConfigObject abstractConfigObject;
            boolean isRequired = configNodeInclude.isRequired();
            ConfigIncludeContext configIncludeContext = this.includeContext;
            ConfigIncludeContext parseOptions = configIncludeContext.setParseOptions(configIncludeContext.parseOptions().setAllowMissing(!isRequired));
            int i = AnonymousClass1.$SwitchMap$com$typesafe$config$impl$ConfigIncludeKind[configNodeInclude.kind().ordinal()];
            if (i == 1) {
                try {
                    abstractConfigObject = (AbstractConfigObject) this.includer.includeURL(parseOptions, new URL(configNodeInclude.name()));
                } catch (MalformedURLException e) {
                    throw parseError("include url() specifies an invalid URL: " + configNodeInclude.name(), e);
                }
            } else if (i == 2) {
                abstractConfigObject = (AbstractConfigObject) this.includer.includeFile(parseOptions, new File(configNodeInclude.name()));
            } else if (i == 3) {
                abstractConfigObject = (AbstractConfigObject) this.includer.includeResources(parseOptions, configNodeInclude.name());
            } else if (i == 4) {
                abstractConfigObject = (AbstractConfigObject) this.includer.include(parseOptions, configNodeInclude.name());
            } else {
                throw new ConfigException.BugOrBroken("should not be reached");
            }
            if (this.arrayCount <= 0 || abstractConfigObject.resolveStatus() == ResolveStatus.RESOLVED) {
                if (!this.pathStack.isEmpty()) {
                    abstractConfigObject = abstractConfigObject.relativized(fullCurrentPath());
                }
                for (String str : abstractConfigObject.keySet()) {
                    AbstractConfigValue abstractConfigValue = abstractConfigObject.get((Object) str);
                    AbstractConfigValue abstractConfigValue2 = map.get(str);
                    if (abstractConfigValue2 != null) {
                        map.put(str, abstractConfigValue.withFallback((ConfigMergeable) abstractConfigValue2));
                    } else {
                        map.put(str, abstractConfigValue);
                    }
                }
                return;
            }
            throw parseError("Due to current limitations of the config parser, when an include statement is nested inside a list value, ${} substitutions inside the included file cannot be resolved correctly. Either move the include outside of the list value or remove the ${} statements from the included file.");
        }

        private AbstractConfigObject parseObject(ConfigNodeObject configNodeObject) {
            HashMap hashMap = new HashMap();
            SimpleConfigOrigin lineOrigin = lineOrigin();
            ArrayList arrayList = new ArrayList(configNodeObject.children());
            ArrayList arrayList2 = new ArrayList();
            int i = 0;
            boolean z = false;
            while (i < arrayList.size()) {
                AbstractConfigNode abstractConfigNode = (AbstractConfigNode) arrayList.get(i);
                if (abstractConfigNode instanceof ConfigNodeComment) {
                    arrayList2.add(((ConfigNodeComment) abstractConfigNode).commentText());
                } else if ((abstractConfigNode instanceof ConfigNodeSingleToken) && Tokens.isNewline(((ConfigNodeSingleToken) abstractConfigNode).token())) {
                    this.lineNumber++;
                    if (z) {
                        arrayList2.clear();
                    }
                    z = true;
                    i++;
                } else if (this.flavor != ConfigSyntax.JSON && (abstractConfigNode instanceof ConfigNodeInclude)) {
                    parseInclude(hashMap, (ConfigNodeInclude) abstractConfigNode);
                } else if (abstractConfigNode instanceof ConfigNodeField) {
                    ConfigNodeField configNodeField = (ConfigNodeField) abstractConfigNode;
                    Path value = configNodeField.path().value();
                    arrayList2.addAll(configNodeField.comments());
                    this.pathStack.push(value);
                    if (configNodeField.separator() == Tokens.PLUS_EQUALS) {
                        int i2 = this.arrayCount;
                        if (i2 <= 0) {
                            this.arrayCount = i2 + 1;
                        } else {
                            throw parseError("Due to current limitations of the config parser, += does not work nested inside a list. += expands to a ${} substitution and the path in ${} cannot currently refer to list elements. You might be able to move the += outside of the list and then refer to it from inside the list with ${}.");
                        }
                    }
                    AbstractConfigValue parseValue = parseValue(configNodeField.value(), arrayList2);
                    if (configNodeField.separator() == Tokens.PLUS_EQUALS) {
                        this.arrayCount--;
                        ArrayList arrayList3 = new ArrayList(2);
                        ConfigReference configReference = new ConfigReference(parseValue.origin(), new SubstitutionExpression(fullCurrentPath(), true));
                        SimpleConfigList simpleConfigList = new SimpleConfigList(parseValue.origin(), Collections.singletonList(parseValue));
                        arrayList3.add(configReference);
                        arrayList3.add(simpleConfigList);
                        parseValue = ConfigConcatenation.concatenate(arrayList3);
                    }
                    if (i < arrayList.size() - 1) {
                        while (true) {
                            i++;
                            if (i < arrayList.size()) {
                                if (!(arrayList.get(i) instanceof ConfigNodeComment)) {
                                    if (!(arrayList.get(i) instanceof ConfigNodeSingleToken)) {
                                        break;
                                    }
                                    ConfigNodeSingleToken configNodeSingleToken = (ConfigNodeSingleToken) arrayList.get(i);
                                    if (configNodeSingleToken.token() != Tokens.COMMA && !Tokens.isIgnoredWhitespace(configNodeSingleToken.token())) {
                                        break;
                                    }
                                } else {
                                    parseValue = parseValue.withOrigin((ConfigOrigin) parseValue.origin().appendComments(Collections.singletonList(((ConfigNodeComment) arrayList.get(i)).commentText())));
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                        i--;
                    }
                    this.pathStack.pop();
                    String first = value.first();
                    Path remainder = value.remainder();
                    if (remainder == null) {
                        AbstractConfigValue abstractConfigValue = (AbstractConfigValue) hashMap.get(first);
                        if (abstractConfigValue != null) {
                            if (this.flavor != ConfigSyntax.JSON) {
                                parseValue = parseValue.withFallback((ConfigMergeable) abstractConfigValue);
                            } else {
                                throw parseError("JSON does not allow duplicate fields: '" + first + "' was already seen at " + abstractConfigValue.origin().description());
                            }
                        }
                        hashMap.put(first, parseValue);
                    } else if (this.flavor != ConfigSyntax.JSON) {
                        AbstractConfigObject createValueUnderPath = createValueUnderPath(remainder, parseValue);
                        AbstractConfigValue abstractConfigValue2 = (AbstractConfigValue) hashMap.get(first);
                        if (abstractConfigValue2 != null) {
                            createValueUnderPath = createValueUnderPath.withFallback((ConfigMergeable) abstractConfigValue2);
                        }
                        hashMap.put(first, createValueUnderPath);
                    } else {
                        throw new ConfigException.BugOrBroken("somehow got multi-element path in JSON mode");
                    }
                } else {
                    continue;
                    i++;
                }
                z = false;
                i++;
            }
            return new SimpleConfigObject(lineOrigin, hashMap);
        }

        private SimpleConfigList parseArray(ConfigNodeArray configNodeArray) {
            this.arrayCount++;
            SimpleConfigOrigin lineOrigin = lineOrigin();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            AbstractConfigValue abstractConfigValue = null;
            loop0:
            while (true) {
                boolean z = false;
                for (AbstractConfigNode next : configNodeArray.children()) {
                    if (next instanceof ConfigNodeComment) {
                        arrayList2.add(((ConfigNodeComment) next).commentText());
                    } else if ((next instanceof ConfigNodeSingleToken) && Tokens.isNewline(((ConfigNodeSingleToken) next).token())) {
                        this.lineNumber++;
                        if (z && abstractConfigValue == null) {
                            arrayList2.clear();
                        } else if (abstractConfigValue != null) {
                            arrayList.add(abstractConfigValue.withOrigin((ConfigOrigin) abstractConfigValue.origin().appendComments(new ArrayList(arrayList2))));
                            arrayList2.clear();
                            abstractConfigValue = null;
                        }
                        z = true;
                    } else if (next instanceof AbstractConfigNodeValue) {
                        if (abstractConfigValue != null) {
                            arrayList.add(abstractConfigValue.withOrigin((ConfigOrigin) abstractConfigValue.origin().appendComments(new ArrayList(arrayList2))));
                            arrayList2.clear();
                        }
                        abstractConfigValue = parseValue((AbstractConfigNodeValue) next, arrayList2);
                    }
                }
                break loop0;
            }
            if (abstractConfigValue != null) {
                arrayList.add(abstractConfigValue.withOrigin((ConfigOrigin) abstractConfigValue.origin().appendComments(new ArrayList(arrayList2))));
            }
            this.arrayCount--;
            return new SimpleConfigList(lineOrigin, arrayList);
        }

        /* access modifiers changed from: package-private */
        public AbstractConfigValue parse() {
            ArrayList arrayList = new ArrayList();
            AbstractConfigValue abstractConfigValue = null;
            while (true) {
                boolean z = false;
                for (AbstractConfigNode next : this.document.children()) {
                    if (next instanceof ConfigNodeComment) {
                        arrayList.add(((ConfigNodeComment) next).commentText());
                    } else if (next instanceof ConfigNodeSingleToken) {
                        if (Tokens.isNewline(((ConfigNodeSingleToken) next).token())) {
                            this.lineNumber++;
                            if (z && abstractConfigValue == null) {
                                arrayList.clear();
                            } else if (abstractConfigValue != null) {
                                AbstractConfigValue withOrigin = abstractConfigValue.withOrigin((ConfigOrigin) abstractConfigValue.origin().appendComments(new ArrayList(arrayList)));
                                arrayList.clear();
                                return withOrigin;
                            }
                            z = true;
                        } else {
                            continue;
                        }
                    } else if (next instanceof ConfigNodeComplexValue) {
                        abstractConfigValue = parseValue((ConfigNodeComplexValue) next, arrayList);
                    }
                }
                return abstractConfigValue;
            }
        }
    }

    /* renamed from: com.typesafe.config.impl.ConfigParser$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$typesafe$config$impl$ConfigIncludeKind;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.typesafe.config.impl.ConfigIncludeKind[] r0 = com.typesafe.config.impl.ConfigIncludeKind.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$typesafe$config$impl$ConfigIncludeKind = r0
                com.typesafe.config.impl.ConfigIncludeKind r1 = com.typesafe.config.impl.ConfigIncludeKind.URL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$typesafe$config$impl$ConfigIncludeKind     // Catch:{ NoSuchFieldError -> 0x001d }
                com.typesafe.config.impl.ConfigIncludeKind r1 = com.typesafe.config.impl.ConfigIncludeKind.FILE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$typesafe$config$impl$ConfigIncludeKind     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.typesafe.config.impl.ConfigIncludeKind r1 = com.typesafe.config.impl.ConfigIncludeKind.CLASSPATH     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$typesafe$config$impl$ConfigIncludeKind     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.typesafe.config.impl.ConfigIncludeKind r1 = com.typesafe.config.impl.ConfigIncludeKind.HEURISTIC     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.typesafe.config.impl.ConfigParser.AnonymousClass1.<clinit>():void");
        }
    }
}
