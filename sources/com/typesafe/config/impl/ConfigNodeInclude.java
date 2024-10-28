package com.typesafe.config.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

final class ConfigNodeInclude extends AbstractConfigNode {
    private final ArrayList<AbstractConfigNode> children;
    private final boolean isRequired;
    private final ConfigIncludeKind kind;

    ConfigNodeInclude(Collection<AbstractConfigNode> collection, ConfigIncludeKind configIncludeKind, boolean z) {
        this.children = new ArrayList<>(collection);
        this.kind = configIncludeKind;
        this.isRequired = z;
    }

    public final Collection<AbstractConfigNode> children() {
        return this.children;
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

    /* access modifiers changed from: protected */
    public ConfigIncludeKind kind() {
        return this.kind;
    }

    /* access modifiers changed from: protected */
    public boolean isRequired() {
        return this.isRequired;
    }

    /* access modifiers changed from: protected */
    public String name() {
        Iterator<AbstractConfigNode> it = this.children.iterator();
        while (it.hasNext()) {
            AbstractConfigNode next = it.next();
            if (next instanceof ConfigNodeSimpleValue) {
                return (String) Tokens.getValue(((ConfigNodeSimpleValue) next).token()).unwrapped();
            }
        }
        return null;
    }
}
