package ua.nure.smirnov.finalproject.Tests;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ua.nure.smirnov.finalproject.entity.DamageBill;
import ua.nure.smirnov.finalproject.entity.Orders;

public class OrderTest {
	static Orders order;
	
	@BeforeClass
	public static void constructorTest(){
		order = new Orders();
	}


    @Test
	public void gettersSettersTest() throws Exception{

		order.setAddFunction("Personal Driver");
		order.setDateStart(LocalDate.parse("2020-04-27"));
		order.setDateFinish(LocalDate.parse("2020-05-08"));
		order.setDescripton("OK");
		order.setIdCar(1);
		order.setIdOrders(1);
		order.setIndexClient("ABC");
		order.setIndexManager("CBA");
		order.setOrderActivity("active");
		order.setPrice(1000);
		order.setStatus("paid");
		
		Assert.assertEquals("Personal Driver", order.getFunction());
		Assert.assertEquals(LocalDate.parse("2020-04-27"), order.getDateStart());
		Assert.assertEquals(LocalDate.parse("2020-05-08"), order.getDateFinish());
		Assert.assertEquals("OK", order.getDescripton());
		Assert.assertEquals(1, order.getIdCar());
		Assert.assertEquals(1, order.getIdOrders());
		Assert.assertEquals("ABC", order.getIndexClient());
		Assert.assertEquals("CBA", order.getIndexManager());
		Assert.assertEquals("active", order.getOrderActivity());
		Assert.assertEquals(1000, order.getPrice());
		Assert.assertEquals("paid", order.getStatus());
		
		
		
		
		
	}
}
