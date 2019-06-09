package test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import back.Set;
import structure.Goods;

@RunWith(Parameterized.class)
public class SetKartListNumTest {

	private String name;
	private int num;
	private boolean expected;
	
	private static Set set;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		set = new Set();
		set.Login("admin", "admin");
		Goods goods = new Goods("GalaxyS9", 100000, 0, "");
		set.AddKart(goods);
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		set.DeleteKart("GalaxyS9");
	}
		
	@Parameterized.Parameters
	public static Collection<Object[]> getParameters() {
		return Arrays.asList(new Object[][] {
			{"GalaxyS9", 3, true},
			{"test_name2", 3, true}
		});
	}
	
	public SetKartListNumTest(String name, int num, boolean expected) {
		this.name = name;
		this.num = num;
		this.expected = expected;
	}
	
	@Test
	public void test() {
		boolean result = set.SetKartListNum(name, num);
		assertEquals(expected, result);
	}

}
