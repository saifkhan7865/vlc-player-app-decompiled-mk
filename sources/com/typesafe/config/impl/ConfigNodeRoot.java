package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigSyntax;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

final class ConfigNodeRoot extends ConfigNodeComplexValue {
    private final ConfigOrigin origin;

    ConfigNodeRoot(Collection<AbstractConfigNode> collection, ConfigOrigin configOrigin) {
        super(collection);
        this.origin = configOrigin;
    }

    /* access modifiers changed from: protected */
    public ConfigNodeRoot newNode(Collection<AbstractConfigNode> collection) {
        throw new ConfigException.BugOrBroken("Tried to indent the root object");
    }

    /* access modifiers changed from: protected */
    public ConfigNodeComplexValue value() {
        Iterator it = this.children.iterator();
        while (it.hasNext()) {
            AbstractConfigNode abstractConfigNode = (AbstractConfigNode) it.next();
            if (abstractConfigNode instanceof ConfigNodeComplexValue) {
                return (ConfigNodeComplexValue) abstractConfigNode;
            }
        }
        throw new ConfigException.BugOrBroken("ConfigNodeRoot did not contain a value");
    }

    /* access modifiers changed from: protected */
    public ConfigNodeRoot setValue(String str, AbstractConfigNodeValue abstractConfigNodeValue, ConfigSyntax configSyntax) {
        ArrayList arrayList = new ArrayList(this.children);
        for (int i = 0; i < arrayList.size(); i++) {
            AbstractConfigNode abstractConfigNode = (AbstractConfigNode) arrayList.get(i);
            if (abstractConfigNode instanceof ConfigNodeComplexValue) {
                if (abstractConfigNode instanceof ConfigNodeArray) {
                    throw new ConfigException.WrongType(this.origin, "The ConfigDocument had an array at the root level, and values cannot be modified inside an array.");
                } else if (abstractConfigNode instanceof ConfigNodeObject) {
                    if (abstractConfigNodeValue == null) {
                        arrayList.set(i, ((ConfigNodeObject) abstractConfigNode).removeValueOnPath(str, configSyntax));
                    } else {
                        arrayList.set(i, ((ConfigNodeObject) abstractConfigNode).setValueOnPath(str, abstractConfigNodeValue, configSyntax));
                    }
                    return new ConfigNodeRoot(arrayList, this.origin);
                }
            }
        }
        throw new ConfigException.BugOrBroken("ConfigNodeRoot did not contain a value");
    }

    /* access modifiers changed from: protected */
    public boolean hasValue(String str) {
        Path parsePath = PathParser.parsePath(str);
        ArrayList arrayList = new ArrayList(this.children);
        for (int i = 0; i < arrayList.size(); i++) {
            AbstractConfigNode abstractConfigNode = (AbstractConfigNode) arrayList.get(i);
            if (abstractConfigNode instanceof ConfigNodeComplexValue) {
                if (abstractConfigNode instanceof ConfigNodeArray) {
                    throw new ConfigException.WrongType(this.origin, "The ConfigDocument had an array at the root level, and values cannot be modified inside an array.");
                } else if (abstractConfigNode instanceof ConfigNodeObject) {
                    return ((ConfigNodeObject) abstractConfigNode).hasValue(parsePath);
                }
            }
        }
        throw new ConfigException.BugOrBroken("ConfigNodeRoot did not contain a value");
    }
}
