package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AddGoodsTest.class, AddKartTest.class, CheckGoodsNameTest.class, CompareIDTest.class,
		DeleteGoodsTest.class, DeleteKartTest.class, LoginTest.class, PurchaseTest.class, RegisterAccountTest.class,
		SetKartListNumTest.class })
public class AllTests {

}
