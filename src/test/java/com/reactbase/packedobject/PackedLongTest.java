package com.reactbase.packedobject;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;

public class PackedLongTest {

	@Test
	public void test() {
		check(-123, new byte[] { -123, -1, -1, -1, -1, -1, -1, -1 });
		check(123, new byte[] { 123, 0, 0, 0, 0, 0, 0, 0 });
		check(0, new byte[] { 0, 0, 0, 0, 0, 0, 0, 0 });
		check(Long.MAX_VALUE, new byte[] { -1, -1, -1, -1, -1, -1, -1, 127 });
		check(Long.MIN_VALUE, new byte[] { 0, 0, 0, 0, 0, 0, 0, -128 });
	}
	
	private void check(long expected, byte[] serialized) {
		PackedLong pl = new PackedLong(0);
		byte[] blob = new byte[pl.sizeOf()];
		Assert.assertEquals(TypeSizes.LONG.sizeOf(), blob.length);
		pl.setLong(blob, 0, expected);
		//System.out.println(Arrays.toString(blob));
		Assert.assertTrue(Arrays.equals(blob, serialized));
		long actual = pl.getLong(blob, 0);
		Assert.assertEquals(expected, actual);
	}
	
}
