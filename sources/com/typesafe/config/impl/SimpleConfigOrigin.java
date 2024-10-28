package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.impl.SerializedConfigValue;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.fusesource.jansi.AnsiRenderer;

final class SimpleConfigOrigin implements ConfigOrigin {
    static final String MERGE_OF_PREFIX = "merge of ";
    private final List<String> commentsOrNull;
    private final String description;
    private final int endLineNumber;
    private final int lineNumber;
    private final OriginType originType;
    private final String resourceOrNull;
    private final String urlOrNull;

    protected SimpleConfigOrigin(String str, int i, int i2, OriginType originType2, String str2, String str3, List<String> list) {
        if (str != null) {
            this.description = str;
            this.lineNumber = i;
            this.endLineNumber = i2;
            this.originType = originType2;
            this.urlOrNull = str2;
            this.resourceOrNull = str3;
            this.commentsOrNull = list;
            return;
        }
        throw new ConfigException.BugOrBroken("description may not be null");
    }

    static SimpleConfigOrigin newSimple(String str) {
        return new SimpleConfigOrigin(str, -1, -1, OriginType.GENERIC, (String) null, (String) null, (List<String>) null);
    }

    static SimpleConfigOrigin newFile(String str) {
        String str2;
        try {
            str2 = new File(str).toURI().toURL().toExternalForm();
        } catch (MalformedURLException unused) {
            str2 = null;
        }
        return new SimpleConfigOrigin(str, -1, -1, OriginType.FILE, str2, (String) null, (List<String>) null);
    }

    static SimpleConfigOrigin newURL(URL url) {
        String externalForm = url.toExternalForm();
        return new SimpleConfigOrigin(externalForm, -1, -1, OriginType.URL, externalForm, (String) null, (List<String>) null);
    }

    static SimpleConfigOrigin newResource(String str, URL url) {
        String str2;
        if (url != null) {
            str2 = str + " @ " + url.toExternalForm();
        } else {
            str2 = str;
        }
        return new SimpleConfigOrigin(str2, -1, -1, OriginType.RESOURCE, url != null ? url.toExternalForm() : null, str, (List<String>) null);
    }

    static SimpleConfigOrigin newResource(String str) {
        return newResource(str, (URL) null);
    }

    public SimpleConfigOrigin withLineNumber(int i) {
        if (i == this.lineNumber && i == this.endLineNumber) {
            return this;
        }
        return new SimpleConfigOrigin(this.description, i, i, this.originType, this.urlOrNull, this.resourceOrNull, this.commentsOrNull);
    }

    /* access modifiers changed from: package-private */
    public SimpleConfigOrigin addURL(URL url) {
        return new SimpleConfigOrigin(this.description, this.lineNumber, this.endLineNumber, this.originType, url != null ? url.toExternalForm() : null, this.resourceOrNull, this.commentsOrNull);
    }

    public SimpleConfigOrigin withComments(List<String> list) {
        if (ConfigImplUtil.equalsHandlingNull(list, this.commentsOrNull)) {
            return this;
        }
        return new SimpleConfigOrigin(this.description, this.lineNumber, this.endLineNumber, this.originType, this.urlOrNull, this.resourceOrNull, list);
    }

    /* access modifiers changed from: package-private */
    public SimpleConfigOrigin prependComments(List<String> list) {
        if (ConfigImplUtil.equalsHandlingNull(list, this.commentsOrNull) || list == null) {
            return this;
        }
        if (this.commentsOrNull == null) {
            return withComments((List) list);
        }
        ArrayList arrayList = new ArrayList(list.size() + this.commentsOrNull.size());
        arrayList.addAll(list);
        arrayList.addAll(this.commentsOrNull);
        return withComments((List) arrayList);
    }

    /* access modifiers changed from: package-private */
    public SimpleConfigOrigin appendComments(List<String> list) {
        if (ConfigImplUtil.equalsHandlingNull(list, this.commentsOrNull) || list == null) {
            return this;
        }
        if (this.commentsOrNull == null) {
            return withComments((List) list);
        }
        ArrayList arrayList = new ArrayList(list.size() + this.commentsOrNull.size());
        arrayList.addAll(this.commentsOrNull);
        arrayList.addAll(list);
        return withComments((List) arrayList);
    }

    public String description() {
        int i = this.lineNumber;
        if (i < 0) {
            return this.description;
        }
        if (this.endLineNumber == i) {
            return this.description + ": " + this.lineNumber;
        }
        return this.description + ": " + this.lineNumber + "-" + this.endLineNumber;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SimpleConfigOrigin)) {
            return false;
        }
        SimpleConfigOrigin simpleConfigOrigin = (SimpleConfigOrigin) obj;
        if (!this.description.equals(simpleConfigOrigin.description) || this.lineNumber != simpleConfigOrigin.lineNumber || this.endLineNumber != simpleConfigOrigin.endLineNumber || this.originType != simpleConfigOrigin.originType || !ConfigImplUtil.equalsHandlingNull(this.urlOrNull, simpleConfigOrigin.urlOrNull) || !ConfigImplUtil.equalsHandlingNull(this.resourceOrNull, simpleConfigOrigin.resourceOrNull)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode = (((((((this.description.hashCode() + 41) * 41) + this.lineNumber) * 41) + this.endLineNumber) * 41) + this.originType.hashCode()) * 41;
        String str = this.urlOrNull;
        if (str != null) {
            hashCode = (hashCode + str.hashCode()) * 41;
        }
        String str2 = this.resourceOrNull;
        return str2 != null ? (hashCode + str2.hashCode()) * 41 : hashCode;
    }

    public String toString() {
        return "ConfigOrigin(" + this.description + ")";
    }

    public String filename() {
        if (this.originType == OriginType.FILE) {
            return this.description;
        }
        if (this.urlOrNull != null) {
            try {
                URL url = new URL(this.urlOrNull);
                if (url.getProtocol().equals("file")) {
                    return url.getFile();
                }
            } catch (MalformedURLException unused) {
            }
        }
        return null;
    }

    public URL url() {
        if (this.urlOrNull == null) {
            return null;
        }
        try {
            return new URL(this.urlOrNull);
        } catch (MalformedURLException unused) {
            return null;
        }
    }

    public String resource() {
        return this.resourceOrNull;
    }

    public int lineNumber() {
        return this.lineNumber;
    }

    public List<String> comments() {
        List<String> list = this.commentsOrNull;
        if (list != null) {
            return Collections.unmodifiableList(list);
        }
        return Collections.emptyList();
    }

    private static SimpleConfigOrigin mergeTwo(SimpleConfigOrigin simpleConfigOrigin, SimpleConfigOrigin simpleConfigOrigin2) {
        int i;
        int i2;
        String str;
        List<String> list;
        OriginType originType2 = simpleConfigOrigin.originType;
        if (originType2 != simpleConfigOrigin2.originType) {
            originType2 = OriginType.GENERIC;
        }
        OriginType originType3 = originType2;
        String str2 = simpleConfigOrigin.description;
        String str3 = simpleConfigOrigin2.description;
        if (str2.startsWith(MERGE_OF_PREFIX)) {
            str2 = str2.substring(9);
        }
        if (str3.startsWith(MERGE_OF_PREFIX)) {
            str3 = str3.substring(9);
        }
        if (str2.equals(str3)) {
            int i3 = simpleConfigOrigin.lineNumber;
            if (i3 < 0) {
                i3 = simpleConfigOrigin2.lineNumber;
            } else {
                int i4 = simpleConfigOrigin2.lineNumber;
                if (i4 >= 0) {
                    i3 = Math.min(i3, i4);
                }
            }
            i2 = i3;
            i = Math.max(simpleConfigOrigin.endLineNumber, simpleConfigOrigin2.endLineNumber);
            str = str2;
        } else {
            String description2 = simpleConfigOrigin.description();
            String description3 = simpleConfigOrigin2.description();
            if (description2.startsWith(MERGE_OF_PREFIX)) {
                description2 = description2.substring(9);
            }
            if (description3.startsWith(MERGE_OF_PREFIX)) {
                description3 = description3.substring(9);
            }
            str = MERGE_OF_PREFIX + description2 + AnsiRenderer.CODE_LIST_SEPARATOR + description3;
            i2 = -1;
            i = -1;
        }
        String str4 = ConfigImplUtil.equalsHandlingNull(simpleConfigOrigin.urlOrNull, simpleConfigOrigin2.urlOrNull) ? simpleConfigOrigin.urlOrNull : null;
        String str5 = ConfigImplUtil.equalsHandlingNull(simpleConfigOrigin.resourceOrNull, simpleConfigOrigin2.resourceOrNull) ? simpleConfigOrigin.resourceOrNull : null;
        if (ConfigImplUtil.equalsHandlingNull(simpleConfigOrigin.commentsOrNull, simpleConfigOrigin2.commentsOrNull)) {
            list = simpleConfigOrigin.commentsOrNull;
        } else {
            ArrayList arrayList = new ArrayList();
            List<String> list2 = simpleConfigOrigin.commentsOrNull;
            if (list2 != null) {
                arrayList.addAll(list2);
            }
            List<String> list3 = simpleConfigOrigin2.commentsOrNull;
            if (list3 != null) {
                arrayList.addAll(list3);
            }
            list = arrayList;
        }
        return new SimpleConfigOrigin(str, i2, i, originType3, str4, str5, list);
    }

    private static int similarity(SimpleConfigOrigin simpleConfigOrigin, SimpleConfigOrigin simpleConfigOrigin2) {
        int i = simpleConfigOrigin.originType == simpleConfigOrigin2.originType ? 1 : 0;
        if (!simpleConfigOrigin.description.equals(simpleConfigOrigin2.description)) {
            return i;
        }
        int i2 = i + 1;
        if (simpleConfigOrigin.lineNumber == simpleConfigOrigin2.lineNumber) {
            i2 = i + 2;
        }
        if (simpleConfigOrigin.endLineNumber == simpleConfigOrigin2.endLineNumber) {
            i2++;
        }
        if (ConfigImplUtil.equalsHandlingNull(simpleConfigOrigin.urlOrNull, simpleConfigOrigin2.urlOrNull)) {
            i2++;
        }
        int i3 = i2;
        return ConfigImplUtil.equalsHandlingNull(simpleConfigOrigin.resourceOrNull, simpleConfigOrigin2.resourceOrNull) ? i3 + 1 : i3;
    }

    private static SimpleConfigOrigin mergeThree(SimpleConfigOrigin simpleConfigOrigin, SimpleConfigOrigin simpleConfigOrigin2, SimpleConfigOrigin simpleConfigOrigin3) {
        if (similarity(simpleConfigOrigin, simpleConfigOrigin2) >= similarity(simpleConfigOrigin2, simpleConfigOrigin3)) {
            return mergeTwo(mergeTwo(simpleConfigOrigin, simpleConfigOrigin2), simpleConfigOrigin3);
        }
        return mergeTwo(simpleConfigOrigin, mergeTwo(simpleConfigOrigin2, simpleConfigOrigin3));
    }

    static ConfigOrigin mergeOrigins(ConfigOrigin configOrigin, ConfigOrigin configOrigin2) {
        return mergeTwo((SimpleConfigOrigin) configOrigin, (SimpleConfigOrigin) configOrigin2);
    }

    static ConfigOrigin mergeOrigins(List<? extends AbstractConfigValue> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (AbstractConfigValue origin : list) {
            arrayList.add(origin.origin());
        }
        return mergeOrigins((Collection<? extends ConfigOrigin>) arrayList);
    }

    static ConfigOrigin mergeOrigins(Collection<? extends ConfigOrigin> collection) {
        if (collection.isEmpty()) {
            throw new ConfigException.BugOrBroken("can't merge empty list of origins");
        } else if (collection.size() == 1) {
            return (ConfigOrigin) collection.iterator().next();
        } else {
            if (collection.size() == 2) {
                Iterator<? extends ConfigOrigin> it = collection.iterator();
                return mergeTwo((SimpleConfigOrigin) it.next(), (SimpleConfigOrigin) it.next());
            }
            ArrayList arrayList = new ArrayList(collection.size());
            for (ConfigOrigin configOrigin : collection) {
                arrayList.add((SimpleConfigOrigin) configOrigin);
            }
            while (arrayList.size() > 2) {
                arrayList.remove(arrayList.size() - 1);
                SimpleConfigOrigin simpleConfigOrigin = (SimpleConfigOrigin) arrayList.get(arrayList.size() - 1);
                arrayList.remove(arrayList.size() - 1);
                SimpleConfigOrigin simpleConfigOrigin2 = (SimpleConfigOrigin) arrayList.get(arrayList.size() - 1);
                arrayList.remove(arrayList.size() - 1);
                arrayList.add(mergeThree(simpleConfigOrigin2, simpleConfigOrigin, (SimpleConfigOrigin) arrayList.get(arrayList.size() - 1)));
            }
            return mergeOrigins((Collection<? extends ConfigOrigin>) arrayList);
        }
    }

    /* access modifiers changed from: package-private */
    public Map<SerializedConfigValue.SerializedField, Object> toFields() {
        EnumMap enumMap = new EnumMap(SerializedConfigValue.SerializedField.class);
        enumMap.put(SerializedConfigValue.SerializedField.ORIGIN_DESCRIPTION, this.description);
        if (this.lineNumber >= 0) {
            enumMap.put(SerializedConfigValue.SerializedField.ORIGIN_LINE_NUMBER, Integer.valueOf(this.lineNumber));
        }
        if (this.endLineNumber >= 0) {
            enumMap.put(SerializedConfigValue.SerializedField.ORIGIN_END_LINE_NUMBER, Integer.valueOf(this.endLineNumber));
        }
        enumMap.put(SerializedConfigValue.SerializedField.ORIGIN_TYPE, Integer.valueOf(this.originType.ordinal()));
        if (this.urlOrNull != null) {
            enumMap.put(SerializedConfigValue.SerializedField.ORIGIN_URL, this.urlOrNull);
        }
        if (this.resourceOrNull != null) {
            enumMap.put(SerializedConfigValue.SerializedField.ORIGIN_RESOURCE, this.resourceOrNull);
        }
        if (this.commentsOrNull != null) {
            enumMap.put(SerializedConfigValue.SerializedField.ORIGIN_COMMENTS, this.commentsOrNull);
        }
        return enumMap;
    }

    /* access modifiers changed from: package-private */
    public Map<SerializedConfigValue.SerializedField, Object> toFieldsDelta(SimpleConfigOrigin simpleConfigOrigin) {
        Map<SerializedConfigValue.SerializedField, Object> map;
        if (simpleConfigOrigin != null) {
            map = simpleConfigOrigin.toFields();
        } else {
            map = Collections.emptyMap();
        }
        return fieldsDelta(map, toFields());
    }

    static Map<SerializedConfigValue.SerializedField, Object> fieldsDelta(Map<SerializedConfigValue.SerializedField, Object> map, Map<SerializedConfigValue.SerializedField, Object> map2) {
        EnumMap enumMap = new EnumMap(map2);
        for (Map.Entry next : map.entrySet()) {
            SerializedConfigValue.SerializedField serializedField = (SerializedConfigValue.SerializedField) next.getKey();
            if (enumMap.containsKey(serializedField) && ConfigImplUtil.equalsHandlingNull(next.getValue(), enumMap.get(serializedField))) {
                enumMap.remove(serializedField);
            } else if (!enumMap.containsKey(serializedField)) {
                switch (AnonymousClass1.$SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField[serializedField.ordinal()]) {
                    case 1:
                        throw new ConfigException.BugOrBroken("origin missing description field? " + map2);
                    case 2:
                        enumMap.put(SerializedConfigValue.SerializedField.ORIGIN_LINE_NUMBER, -1);
                        break;
                    case 3:
                        enumMap.put(SerializedConfigValue.SerializedField.ORIGIN_END_LINE_NUMBER, -1);
                        break;
                    case 4:
                        throw new ConfigException.BugOrBroken("should always be an ORIGIN_TYPE field");
                    case 5:
                        enumMap.put(SerializedConfigValue.SerializedField.ORIGIN_NULL_URL, "");
                        break;
                    case 6:
                        enumMap.put(SerializedConfigValue.SerializedField.ORIGIN_NULL_RESOURCE, "");
                        break;
                    case 7:
                        enumMap.put(SerializedConfigValue.SerializedField.ORIGIN_NULL_COMMENTS, "");
                        break;
                    case 8:
                    case 9:
                    case 10:
                        throw new ConfigException.BugOrBroken("computing delta, base object should not contain " + serializedField + AnsiRenderer.CODE_TEXT_SEPARATOR + map);
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                        throw new ConfigException.BugOrBroken("should not appear here: " + serializedField);
                }
            } else {
                continue;
            }
        }
        return enumMap;
    }

    /* renamed from: com.typesafe.config.impl.SimpleConfigOrigin$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField;

        /* JADX WARNING: Can't wrap try/catch for region: R(32:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|(3:31|32|34)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(34:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|34) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0090 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x009c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x00a8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x00b4 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.typesafe.config.impl.SerializedConfigValue$SerializedField[] r0 = com.typesafe.config.impl.SerializedConfigValue.SerializedField.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField = r0
                com.typesafe.config.impl.SerializedConfigValue$SerializedField r1 = com.typesafe.config.impl.SerializedConfigValue.SerializedField.ORIGIN_DESCRIPTION     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField     // Catch:{ NoSuchFieldError -> 0x001d }
                com.typesafe.config.impl.SerializedConfigValue$SerializedField r1 = com.typesafe.config.impl.SerializedConfigValue.SerializedField.ORIGIN_LINE_NUMBER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.typesafe.config.impl.SerializedConfigValue$SerializedField r1 = com.typesafe.config.impl.SerializedConfigValue.SerializedField.ORIGIN_END_LINE_NUMBER     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.typesafe.config.impl.SerializedConfigValue$SerializedField r1 = com.typesafe.config.impl.SerializedConfigValue.SerializedField.ORIGIN_TYPE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField     // Catch:{ NoSuchFieldError -> 0x003e }
                com.typesafe.config.impl.SerializedConfigValue$SerializedField r1 = com.typesafe.config.impl.SerializedConfigValue.SerializedField.ORIGIN_URL     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.typesafe.config.impl.SerializedConfigValue$SerializedField r1 = com.typesafe.config.impl.SerializedConfigValue.SerializedField.ORIGIN_RESOURCE     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.typesafe.config.impl.SerializedConfigValue$SerializedField r1 = com.typesafe.config.impl.SerializedConfigValue.SerializedField.ORIGIN_COMMENTS     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.typesafe.config.impl.SerializedConfigValue$SerializedField r1 = com.typesafe.config.impl.SerializedConfigValue.SerializedField.ORIGIN_NULL_URL     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField     // Catch:{ NoSuchFieldError -> 0x006c }
                com.typesafe.config.impl.SerializedConfigValue$SerializedField r1 = com.typesafe.config.impl.SerializedConfigValue.SerializedField.ORIGIN_NULL_RESOURCE     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.typesafe.config.impl.SerializedConfigValue$SerializedField r1 = com.typesafe.config.impl.SerializedConfigValue.SerializedField.ORIGIN_NULL_COMMENTS     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField     // Catch:{ NoSuchFieldError -> 0x0084 }
                com.typesafe.config.impl.SerializedConfigValue$SerializedField r1 = com.typesafe.config.impl.SerializedConfigValue.SerializedField.END_MARKER     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r0 = $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField     // Catch:{ NoSuchFieldError -> 0x0090 }
                com.typesafe.config.impl.SerializedConfigValue$SerializedField r1 = com.typesafe.config.impl.SerializedConfigValue.SerializedField.ROOT_VALUE     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                int[] r0 = $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField     // Catch:{ NoSuchFieldError -> 0x009c }
                com.typesafe.config.impl.SerializedConfigValue$SerializedField r1 = com.typesafe.config.impl.SerializedConfigValue.SerializedField.ROOT_WAS_CONFIG     // Catch:{ NoSuchFieldError -> 0x009c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009c }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009c }
            L_0x009c:
                int[] r0 = $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField     // Catch:{ NoSuchFieldError -> 0x00a8 }
                com.typesafe.config.impl.SerializedConfigValue$SerializedField r1 = com.typesafe.config.impl.SerializedConfigValue.SerializedField.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x00a8 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a8 }
                r2 = 14
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00a8 }
            L_0x00a8:
                int[] r0 = $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField     // Catch:{ NoSuchFieldError -> 0x00b4 }
                com.typesafe.config.impl.SerializedConfigValue$SerializedField r1 = com.typesafe.config.impl.SerializedConfigValue.SerializedField.VALUE_DATA     // Catch:{ NoSuchFieldError -> 0x00b4 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b4 }
                r2 = 15
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00b4 }
            L_0x00b4:
                int[] r0 = $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField     // Catch:{ NoSuchFieldError -> 0x00c0 }
                com.typesafe.config.impl.SerializedConfigValue$SerializedField r1 = com.typesafe.config.impl.SerializedConfigValue.SerializedField.VALUE_ORIGIN     // Catch:{ NoSuchFieldError -> 0x00c0 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c0 }
                r2 = 16
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00c0 }
            L_0x00c0:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.typesafe.config.impl.SimpleConfigOrigin.AnonymousClass1.<clinit>():void");
        }
    }

    static SimpleConfigOrigin fromFields(Map<SerializedConfigValue.SerializedField, Object> map) throws IOException {
        int i;
        if (map.isEmpty()) {
            return null;
        }
        String str = (String) map.get(SerializedConfigValue.SerializedField.ORIGIN_DESCRIPTION);
        Integer num = (Integer) map.get(SerializedConfigValue.SerializedField.ORIGIN_LINE_NUMBER);
        Integer num2 = (Integer) map.get(SerializedConfigValue.SerializedField.ORIGIN_END_LINE_NUMBER);
        Number number = (Number) map.get(SerializedConfigValue.SerializedField.ORIGIN_TYPE);
        if (number != null) {
            OriginType originType2 = OriginType.values()[number.byteValue()];
            String str2 = (String) map.get(SerializedConfigValue.SerializedField.ORIGIN_URL);
            String str3 = (String) map.get(SerializedConfigValue.SerializedField.ORIGIN_RESOURCE);
            List list = (List) map.get(SerializedConfigValue.SerializedField.ORIGIN_COMMENTS);
            String str4 = (originType2 == OriginType.RESOURCE && str3 == null) ? str : str3;
            int intValue = num != null ? num.intValue() : -1;
            if (num2 != null) {
                i = num2.intValue();
            } else {
                i = -1;
            }
            return new SimpleConfigOrigin(str, intValue, i, originType2, str2, str4, list);
        }
        throw new IOException("Missing ORIGIN_TYPE field");
    }

    static Map<SerializedConfigValue.SerializedField, Object> applyFieldsDelta(Map<SerializedConfigValue.SerializedField, Object> map, Map<SerializedConfigValue.SerializedField, Object> map2) throws IOException {
        EnumMap enumMap = new EnumMap(map2);
        for (Map.Entry<SerializedConfigValue.SerializedField, Object> key : map.entrySet()) {
            SerializedConfigValue.SerializedField serializedField = (SerializedConfigValue.SerializedField) key.getKey();
            if (!map2.containsKey(serializedField)) {
                switch (AnonymousClass1.$SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField[serializedField.ordinal()]) {
                    case 1:
                        enumMap.put(serializedField, map.get(serializedField));
                        break;
                    case 2:
                    case 3:
                    case 4:
                        enumMap.put(serializedField, map.get(serializedField));
                        break;
                    case 5:
                        if (!map2.containsKey(SerializedConfigValue.SerializedField.ORIGIN_NULL_URL)) {
                            enumMap.put(serializedField, map.get(serializedField));
                            break;
                        } else {
                            enumMap.remove(SerializedConfigValue.SerializedField.ORIGIN_NULL_URL);
                            break;
                        }
                    case 6:
                        if (!map2.containsKey(SerializedConfigValue.SerializedField.ORIGIN_NULL_RESOURCE)) {
                            enumMap.put(serializedField, map.get(serializedField));
                            break;
                        } else {
                            enumMap.remove(SerializedConfigValue.SerializedField.ORIGIN_NULL_RESOURCE);
                            break;
                        }
                    case 7:
                        if (!map2.containsKey(SerializedConfigValue.SerializedField.ORIGIN_NULL_COMMENTS)) {
                            enumMap.put(serializedField, map.get(serializedField));
                            break;
                        } else {
                            enumMap.remove(SerializedConfigValue.SerializedField.ORIGIN_NULL_COMMENTS);
                            break;
                        }
                    case 8:
                    case 9:
                    case 10:
                        throw new ConfigException.BugOrBroken("applying fields, base object should not contain " + serializedField + AnsiRenderer.CODE_TEXT_SEPARATOR + map);
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                        throw new ConfigException.BugOrBroken("should not appear here: " + serializedField);
                }
            }
        }
        return enumMap;
    }

    static SimpleConfigOrigin fromBase(SimpleConfigOrigin simpleConfigOrigin, Map<SerializedConfigValue.SerializedField, Object> map) throws IOException {
        Map<SerializedConfigValue.SerializedField, Object> map2;
        if (simpleConfigOrigin != null) {
            map2 = simpleConfigOrigin.toFields();
        } else {
            map2 = Collections.emptyMap();
        }
        return fromFields(applyFieldsDelta(map2, map));
    }
}
