package com.ergo.time;

import org.junit.Assert;
import org.junit.Test;

public class TimeTests {

	@Test
	public void testTime() {
		String expectedTimestamp = "xxx";
		Time cut = new Time(expectedTimestamp);
		Assert.assertEquals(expectedTimestamp, cut.getTimestmap());
	}
}
