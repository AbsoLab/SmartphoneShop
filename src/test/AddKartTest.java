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
public class AddKartTest {

	private String name;
	private int price;
	private int category;
	private String explanation;
	private boolean expected;
	
	private static Set set;
	
	@BeforeClass
	public static void setUp() {
		set = new Set();
		set.Login("admin", "admin");
	}
	
	@AfterClass
	public static void tearDown() {
		set.DeleteKart("GalaxyS9");
	}
		
	@Parameterized.Parameters
	public static Collection<Object[]> getParameters() {
		return Arrays.asList(new Object[][] {
			{"GalaxyS9", 100000, 0, "", true},
			{"GalaxyS9", 100000, 0, "", false}
		});
	}
	
	public AddKartTest(String name, int price, int category, String explanation, boolean expected) {
		this.name = name;
		this.price = price;
		this.category = category;
		this.explanation = explanation;
		this.expected = expected;
	}
	
	@Test
	public void test() {
		Goods goods = new Goods(name, price, category, explanation);
		boolean result = set.AddKart(goods);
		assertEquals(expected, result);
	}

}
