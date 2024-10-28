package com.typesafe.config.impl;

interface ReplaceableMergeStack extends Container {
    AbstractConfigValue makeReplacement(ResolveContext resolveContext, int i);
}
