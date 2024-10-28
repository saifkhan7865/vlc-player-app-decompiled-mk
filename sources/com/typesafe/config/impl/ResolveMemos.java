package com.typesafe.config.impl;

final class ResolveMemos {
    private final BadMap<MemoKey, AbstractConfigValue> memos;

    private ResolveMemos(BadMap<MemoKey, AbstractConfigValue> badMap) {
        this.memos = badMap;
    }

    ResolveMemos() {
        this(new BadMap());
    }

    /* access modifiers changed from: package-private */
    public AbstractConfigValue get(MemoKey memoKey) {
        return this.memos.get(memoKey);
    }

    /* access modifiers changed from: package-private */
    public ResolveMemos put(MemoKey memoKey, AbstractConfigValue abstractConfigValue) {
        return new ResolveMemos(this.memos.copyingPut(memoKey, abstractConfigValue));
    }
}
