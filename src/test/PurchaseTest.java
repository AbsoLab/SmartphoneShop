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
import structure.Order;

@RunWith(Parameterized.class)
public class PurchaseTest {

	private String name;
	private String address;
	private String phone_num;
	private String card_corporation;
	private String card_num;
	private int total_price;
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
		set.DeleteOrderRecord("test_name");
	}
		
	@Parameterized.Parameters
	public static Collection<Object[]> getParameters() {
		return Arrays.asList(new Object[][] {
			{"test_name", "test_address", "test_phone", "test_card_corp", "test_card_num", 100000, true}
		});
	}
	
	public PurchaseTest(String name, String address, String phone_num, String card_corporation, String card_num, int total_price, boolean expected) {
		this.name = name;
		this.address = address;
		this.phone_num = phone_num;
		this.card_corporation = card_corporation;
		this.card_num = card_num;
		this.total_price = total_price;
		this.expected = expected;
	}
	
	@Test
	public void test() {
		Order order = new Order(name, address, phone_num, card_corporation, card_num, total_price);
		boolean result = set.Purchase(order);
		assertEquals(expected, result);
	}

}
