/**
 * 
 */
package com.martraire.eggfarm;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Represents a value object (immutable and with no identity)
 * 
 * @see <a href="http://martinfowler.com/bliki/ValueObject.html">ValueObject</a>
 */
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface ValueObject {
}
