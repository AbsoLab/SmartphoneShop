package test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import back.Set;
import structure.Goods;

@RunWith(Parameterized.class)
public class DeleteKartTest {

	private String name;
	private boolean expected;
	
	private static Set set;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		set = new Set();
		set.Login("admin", "admin");
		Goods goods = new Goods("GalaxyS9", 100000, 0, "");
		set.AddKart(goods);
	}
		
	@Parameterized.Parameters
	public static Collection<Object[]> getParameters() {
		return Arrays.asList(new Object[][] {
			{"GalaxyS9", true},
			{"test_name2", true}
		});
	}
	
	public DeleteKartTest(String name, boolean expected) {
		this.name = name;
		this.expected = expected;
	}
	
	@Test
	public void test() {
		boolean result = set.DeleteKart(name);
		assertEquals(expected, result);
	}
}
