package me.KrazyManJ.KrazyEngine.Core;

import java.lang.annotation.*;

/**
 * Annotation that has no functionality, it just serves as indexing and documentation annotation.
 * <p>
 * Used for specifying object that needs to be checked, when new minecraft version is released for updating.
 * </p>
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.PACKAGE, ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
@Documented
public @interface NeedsUpdateEachMinecraftVersion {
    /**
     * Specifies on which version was the last check done
     * @return Minecraft version e.g. 1.19.4
     */
    String lastUpdated();
}
