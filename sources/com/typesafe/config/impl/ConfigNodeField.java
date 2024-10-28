package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

final class ConfigNodeField extends AbstractConfigNode {
    private final ArrayList<AbstractConfigNode> children;

    public ConfigNodeField(Collection<AbstractConfigNode> collection) {
        this.children = new ArrayList<>(collection);
    }

    /* access modifiers changed from: protected */
    public Collection<Token> tokens() {
        ArrayList arrayList = new ArrayList();
        Iterator<AbstractConfigNode> it = this.children.iterator();
        while (it.hasNext()) {
            arrayList.addAll(it.next().tokens());
        }
        return arrayList;
    }

    public ConfigNodeField replaceValue(AbstractConfigNodeValue abstractConfigNodeValue) {
        ArrayList arrayList = new ArrayList(this.children);
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i) instanceof AbstractConfigNodeValue) {
                arrayList.set(i, abstractConfigNodeValue);
                return new ConfigNodeField(arrayList);
            }
        }
        throw new ConfigException.BugOrBroken("Field node doesn't have a value");
    }

    public AbstractConfigNodeValue value() {
        for (int i = 0; i < this.children.size(); i++) {
            if (this.children.get(i) instanceof AbstractConfigNodeValue) {
                return (AbstractConfigNodeValue) this.children.get(i);
            }
        }
        throw new ConfigException.BugOrBroken("Field node doesn't have a value");
    }

    public ConfigNodePath path() {
        for (int i = 0; i < this.children.size(); i++) {
            if (this.children.get(i) instanceof ConfigNodePath) {
                return (ConfigNodePath) this.children.get(i);
            }
        }
        throw new ConfigException.BugOrBroken("Field node doesn't have a path");
    }

    /* access modifiers changed from: protected */
    public Token separator() {
        Token token;
        Iterator<AbstractConfigNode> it = this.children.iterator();
        while (it.hasNext()) {
            AbstractConfigNode next = it.next();
            if ((next instanceof ConfigNodeSingleToken) && ((token = ((ConfigNodeSingleToken) next).token()) == Tokens.PLUS_EQUALS || token == Tokens.COLON || token == Tokens.EQUALS)) {
                return token;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public List<String> comments() {
        ArrayList arrayList = new ArrayList();
        Iterator<AbstractConfigNode> it = this.children.iterator();
        while (it.hasNext()) {
            AbstractConfigNode next = it.next();
            if (next instanceof ConfigNodeComment) {
                arrayList.add(((ConfigNodeComment) next).commentText());
            }
        }
        return arrayList;
    }
}
