package io.netty.handler.codec.http.websocketx.extensions;

import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpHeaders;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class WebSocketExtensionUtil {
    private static final String EXTENSION_SEPARATOR = ",";
    private static final Pattern PARAMETER = Pattern.compile("^([^=]+)(=[\\\"]?([^\\\"]+)[\\\"]?)?$");
    private static final char PARAMETER_EQUAL = '=';
    private static final String PARAMETER_SEPARATOR = ";";

    static boolean isWebsocketUpgrade(HttpHeaders httpHeaders) {
        if (!httpHeaders.contains((CharSequence) HttpHeaderNames.UPGRADE) || !httpHeaders.containsValue(HttpHeaderNames.CONNECTION, HttpHeaderValues.UPGRADE, true) || !httpHeaders.contains((CharSequence) HttpHeaderNames.UPGRADE, (CharSequence) HttpHeaderValues.WEBSOCKET, true)) {
            return false;
        }
        return true;
    }

    public static List<WebSocketExtensionData> extractExtensions(String str) {
        Map map;
        String[] split = str.split(",");
        if (split.length <= 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(split.length);
        for (String split2 : split) {
            String[] split3 = split2.split(PARAMETER_SEPARATOR);
            String trim = split3[0].trim();
            if (split3.length > 1) {
                map = new HashMap(split3.length - 1);
                for (int i = 1; i < split3.length; i++) {
                    Matcher matcher = PARAMETER.matcher(split3[i].trim());
                    if (matcher.matches() && matcher.group(1) != null) {
                        map.put(matcher.group(1), matcher.group(3));
                    }
                }
            } else {
                map = Collections.emptyMap();
            }
            arrayList.add(new WebSocketExtensionData(trim, map));
        }
        return arrayList;
    }

    static String computeMergeExtensionsHeaderValue(String str, List<WebSocketExtensionData> list) {
        List<WebSocketExtensionData> list2;
        WebSocketExtensionData webSocketExtensionData;
        if (str != null) {
            list2 = extractExtensions(str);
        } else {
            list2 = Collections.emptyList();
        }
        for (WebSocketExtensionData next : list2) {
            int i = 0;
            while (true) {
                if (i >= list.size()) {
                    webSocketExtensionData = null;
                    break;
                }
                webSocketExtensionData = list.get(i);
                if (webSocketExtensionData.name().equals(next.name())) {
                    break;
                }
                i++;
            }
            if (webSocketExtensionData == null) {
                list.add(next);
            } else {
                HashMap hashMap = new HashMap(webSocketExtensionData.parameters());
                hashMap.putAll(next.parameters());
                list.set(i, new WebSocketExtensionData(webSocketExtensionData.name(), hashMap));
            }
        }
        StringBuilder sb = new StringBuilder(150);
        for (WebSocketExtensionData next2 : list) {
            sb.append(next2.name());
            for (Map.Entry next3 : next2.parameters().entrySet()) {
                sb.append(PARAMETER_SEPARATOR);
                sb.append((String) next3.getKey());
                if (next3.getValue() != null) {
                    sb.append(PARAMETER_EQUAL);
                    sb.append((String) next3.getValue());
                }
            }
            sb.append(",");
        }
        if (!list.isEmpty()) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    private WebSocketExtensionUtil() {
    }
}
