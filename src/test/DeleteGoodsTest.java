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
public class DeleteGoodsTest {

	private String name;
	private int price;
	private int category;
	private String explanation;
	private boolean expected;
	
	private static Set set;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		set = new Set();
		set.Login("admin", "admin");
		set.AddGoods(new Goods("test_name", 1000000, 1, "test_explanation"), ".\\src\\test\\iphoneX.png");
	}
		
	@AfterClass
	public static void tearDownAfterClass() {
		set.DeleteGoods(new Goods("test_name", 1000000, 1, "test_explanation"));
	}
	
	@Parameterized.Parameters
	public static Collection<Object[]> getParameters() {
		return Arrays.asList(new Object[][] {
			{"test_name", 1000000, 1, "test_explanation", true},
			{"test_name2", 1000000, 1, "test_explanation", true}
		});
	}
	
	public DeleteGoodsTest(String name, int price, int category, String explanation, boolean expected) {
		this.name = name;
		this.price = price;
		this.category = category;
		this.explanation = explanation;
		this.expected = expected;
	}
	
	@Test
	public void test() {
		boolean result = set.DeleteGoods(new Goods(name, price, category, explanation));
		assertEquals(expected, result);
	}

}
