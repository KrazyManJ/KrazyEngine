package me.KrazyManJ.KrazyEngine.Core;

import java.lang.annotation.*;

/**
 * Annotation that has no functionality, it just serves as indexing and documentation annotation.
 * <p>
 * Used for specifying, that this class uses reflection
 * </p>
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
@Documented
public @interface ReflectionUsed {
}
