package me.KrazyManJ.KrazyEngine.Core;

import java.lang.annotation.*;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.PACKAGE, ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
@Documented
public @interface NeedsUpdateEachMinecraftVersion {
    String lastUpdated();
}
