package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.impl.ConfigString;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

final class PropertiesParser {
    PropertiesParser() {
    }

    static AbstractConfigObject parse(Reader reader, ConfigOrigin configOrigin) throws IOException {
        Properties properties = new Properties();
        properties.load(reader);
        return fromProperties(configOrigin, properties);
    }

    static String lastElement(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf < 0) {
            return str;
        }
        return str.substring(lastIndexOf + 1);
    }

    static String exceptLastElement(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf < 0) {
            return null;
        }
        return str.substring(0, lastIndexOf);
    }

    static Path pathFromPropertyKey(String str) {
        String lastElement = lastElement(str);
        String exceptLastElement = exceptLastElement(str);
        Path path = new Path(lastElement, (Path) null);
        while (exceptLastElement != null) {
            String lastElement2 = lastElement(exceptLastElement);
            exceptLastElement = exceptLastElement(exceptLastElement);
            path = new Path(lastElement2, path);
        }
        return path;
    }

    static AbstractConfigObject fromProperties(ConfigOrigin configOrigin, Properties properties) {
        return fromEntrySet(configOrigin, properties.entrySet());
    }

    private static <K, V> AbstractConfigObject fromEntrySet(ConfigOrigin configOrigin, Set<Map.Entry<K, V>> set) {
        return fromPathMap(configOrigin, getPathMap(set), true);
    }

    private static <K, V> Map<Path, Object> getPathMap(Set<Map.Entry<K, V>> set) {
        HashMap hashMap = new HashMap();
        for (Map.Entry next : set) {
            Object key = next.getKey();
            if (key instanceof String) {
                hashMap.put(pathFromPropertyKey((String) key), next.getValue());
            }
        }
        return hashMap;
    }

    static AbstractConfigObject fromStringMap(ConfigOrigin configOrigin, Map<String, String> map) {
        return fromEntrySet(configOrigin, map.entrySet());
    }

    static AbstractConfigObject fromPathMap(ConfigOrigin configOrigin, Map<?, ?> map) {
        HashMap hashMap = new HashMap();
        for (Map.Entry next : map.entrySet()) {
            Object key = next.getKey();
            if (key instanceof String) {
                hashMap.put(Path.newPath((String) key), next.getValue());
            } else {
                throw new ConfigException.BugOrBroken("Map has a non-string as a key, expecting a path expression as a String");
            }
        }
        return fromPathMap(configOrigin, hashMap, false);
    }

    private static AbstractConfigObject fromPathMap(ConfigOrigin configOrigin, Map<Path, Object> map, boolean z) {
        Object obj;
        HashSet<Path> hashSet = new HashSet<>();
        HashSet<Path> hashSet2 = new HashSet<>();
        for (Path next : map.keySet()) {
            hashSet2.add(next);
            for (Path parent = next.parent(); parent != null; parent = parent.parent()) {
                hashSet.add(parent);
            }
        }
        if (z) {
            hashSet2.removeAll(hashSet);
        } else {
            for (Path path : hashSet2) {
                if (hashSet.contains(path)) {
                    throw new ConfigException.BugOrBroken("In the map, path '" + path.render() + "' occurs as both the parent object of a value and as a value. Because Map has no defined ordering, this is a broken situation.");
                }
            }
        }
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        for (Path put : hashSet) {
            hashMap2.put(put, new HashMap());
        }
        for (Path path2 : hashSet2) {
            Path parent2 = path2.parent();
            Map map2 = parent2 != null ? (Map) hashMap2.get(parent2) : hashMap;
            String last = path2.last();
            Object obj2 = map.get(path2);
            if (z) {
                obj = obj2 instanceof String ? new ConfigString.Quoted(configOrigin, (String) obj2) : null;
            } else {
                obj = ConfigImpl.fromAnyRef(map.get(path2), configOrigin, FromMapMode.KEYS_ARE_PATHS);
            }
            if (obj != null) {
                map2.put(last, obj);
            }
        }
        ArrayList<Path> arrayList = new ArrayList<>();
        arrayList.addAll(hashSet);
        Collections.sort(arrayList, new Comparator<Path>() {
            public int compare(Path path, Path path2) {
                return path2.length() - path.length();
            }
        });
        for (Path path3 : arrayList) {
            Map map3 = (Map) hashMap2.get(path3);
            Path parent3 = path3.parent();
            (parent3 != null ? (Map) hashMap2.get(parent3) : hashMap).put(path3.last(), new SimpleConfigObject(configOrigin, map3, ResolveStatus.RESOLVED, false));
        }
        return new SimpleConfigObject(configOrigin, hashMap, ResolveStatus.RESOLVED, false);
    }
}
