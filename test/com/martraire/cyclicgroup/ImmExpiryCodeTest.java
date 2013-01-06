/**
 * 
 */
package com.martraire.cyclicgroup;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Test;

import static com.martraire.cyclicgroup.ImmExpiryCode.valueOf;

public class ImmExpiryCodeTest {

	private static final ImmExpiryCode H1 = valueOf("H1");
	private static final ImmExpiryCode H2 = valueOf("H2");
	private static final ImmExpiryCode H3 = valueOf("H3");
	private static final ImmExpiryCode H4 = valueOf("H4");

	@Test
	public void valueOf_of_entity() {
		assertEquals("U8", valueOf("U8").toString());
		assertEquals("U9", valueOf("U9").toString());
		assertEquals("M9", valueOf("M9").toString());
		assertEquals("H9", valueOf("H9").toString());
	}

	@Test
	public void next_code() {
		assertEquals("Z8", valueOf("U8").next().toString());
		assertEquals("Z7", valueOf("U7").next().toString());

		// this line requires to jump to a cyclic group (40)
		assertEquals("H8", valueOf("Z7").next().toString());

		// this line would have offered to roll just the month first, leading
		// toward another approach
		assertEquals("U8", valueOf("M8").next().toString());
	}

	@Test
	public void previous_of_next_is_identity() {
		assertEquals("Z8", valueOf("Z8").next().previous().toString());
		assertEquals("Z7", valueOf("Z7").next().previous().toString());
		assertEquals("H8", valueOf("H8").next().previous().toString());
		assertEquals("U8", valueOf("U8").next().previous().toString());
	}

	@Test
	public void actual_identity() {
		assertEquals(valueOf("Z8"), valueOf("Z8"));
		assertEquals(valueOf("Z7"), valueOf("Z7"));
		assertEquals(valueOf("H8"), valueOf("H8"));
		assertEquals(valueOf("U8"), valueOf("U8"));
	}

	@Test
	public void ordering_is_only_partial() {
		final Comparator<ImmExpiryCode> c = new ImmExpiryCode.AsOfComparator(H3);
		assertEquals("ImmExpiryCode Comparator as of H3", c.toString());

		assertTrue(c.compare(H3, H3) == 0);
		assertTrue(c.compare(H3, H4) < 0);
		assertTrue(c.compare(H4, H3) > 0);

		// the tricky case as of H3
		assertTrue(c.compare(H4, H2) < 0);
	}
}
