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

	@Test
	public void next_code() {
		assertEquals("Z8", ImmExpiryCode.valueOf("U8").next().toString());
		assertEquals("Z7", ImmExpiryCode.valueOf("U7").next().toString());

		// this line requires to jump to a cyclic group (40)
		assertEquals("H8", ImmExpiryCode.valueOf("Z7").next().toString());

		// this line would have offered to roll just the month first, leading
		// toward another approach
		assertEquals("U8", ImmExpiryCode.valueOf("M8").next().toString());
	}

	@Test
	public void previous_code() {
		assertEquals("U8", ImmExpiryCode.valueOf("Z8").previous().toString());
		assertEquals("U7", ImmExpiryCode.valueOf("Z7").previous().toString());
		assertEquals("Z7", ImmExpiryCode.valueOf("H8").previous().toString());
		assertEquals("M8", ImmExpiryCode.valueOf("U8").previous().toString());
	}

}
