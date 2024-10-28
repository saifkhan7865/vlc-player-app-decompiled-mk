package com.typesafe.config.impl;

import com.typesafe.config.ConfigValue;

interface Container extends ConfigValue {
    boolean hasDescendant(AbstractConfigValue abstractConfigValue);

    AbstractConfigValue replaceChild(AbstractConfigValue abstractConfigValue, AbstractConfigValue abstractConfigValue2);
}
