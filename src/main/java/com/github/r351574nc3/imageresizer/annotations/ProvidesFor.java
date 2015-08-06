package com.github.r351574nc3.imageresizer.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation that is used to describe the image format for which an {@link ImageFormatProvider} is responsible.
 * Example:
 * <pre>
 *     @ProvidesFor(format = SomeFormat.class)
 *     public class SomeFormatProvider implments ImageFormatProvider {
 *     }
 * </pre>
 *
 * @author Leo Przybylski
 */
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ProvidesFor {
    Class format();
}
