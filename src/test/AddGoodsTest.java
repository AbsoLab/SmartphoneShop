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
public class AddGoodsTest {

	private String name;
	private int price;
	private int category;
	private String explanation;
	private String img_url;
	
	private boolean expected;
	
	private static Set set;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		set = new Set();
		set.Login("admin", "admin");
	}
		
	@AfterClass
	public static void tearDownAfterClass() {
		set.DeleteGoods(new Goods("test_name", 1000000, 1, "test_explanation"));
	}
	
	@Parameterized.Parameters
	public static Collection<Object[]> getParameters() {
		return Arrays.asList(new Object[][] {
			{"test_name", 1000000, 1, "test_explanation", ".\\src\\test\\iphoneX.png", true},
			{"test_name", 1000000, 1, "test_explanation", null, false}
		});
	}
	
	public AddGoodsTest(String name, int price, int category, String explanation, String img_url, boolean expected) {
		this.name = name;
		this.price = price;
		this.category = category;
		this.explanation = explanation;
		this.img_url = img_url;
		this.expected = expected;
	}
	
	@Test
	public void test() {
		Goods goods = new Goods(name, price, category, explanation);
		boolean result = set.AddGoods(goods, img_url);
		assertEquals(expected, result);
	}

}
