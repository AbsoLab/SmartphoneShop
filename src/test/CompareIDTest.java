package test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import back.Set;

@RunWith(Parameterized.class)
public class CompareIDTest {

	private String input_value;
	private boolean expected;
	private static Set set;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		set = new Set();
	}
	
	@Parameterized.Parameters
	public static Collection<Object[]> getParameters() {
		return Arrays.asList(new Object[][] {
			{"admin", true},
			{"", false}
		});
	}
	
	public CompareIDTest(String input_value, boolean expected) {
		this.input_value = input_value;
		this.expected = expected;
	}
	
	@Test
	public void test() {
		boolean result = set.CompareID(input_value);
		assertEquals(expected, result);
	}

}
