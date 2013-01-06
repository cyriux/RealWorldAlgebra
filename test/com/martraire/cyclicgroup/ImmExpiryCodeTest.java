/**
 * 
 */
package com.martraire.cyclicgroup;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ImmExpiryCodeTest {

	@Test
	public void valueOf_of_toString_is_identity() {
		assertEquals("U8", ImmExpiryCode.valueOf("U8").toString());
		assertEquals("U9", ImmExpiryCode.valueOf("U9").toString());
		assertEquals("M9", ImmExpiryCode.valueOf("M9").toString());
	}

}
