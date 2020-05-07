package ua.nure.smirnov.finalproject.Tests;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ua.nure.smirnov.finalproject.entity.DamageBill;


public class DamageBillTest {
static DamageBill damageBill;
	
	@BeforeClass
	public static void constructorTest(){
		damageBill = new DamageBill();
	}


    @Test
	public void gettersSettersTest() throws Exception{

		damageBill.setActivity("paid");
		damageBill.setDate(LocalDate.parse("2020-04-27"));
		damageBill.setDescription("Машина в хорошем состояние");
		damageBill.setIdOrder(1);
		damageBill.setIdPayment(1);
		damageBill.setIndexClient("ABC123N");
		damageBill.setIndexManager("DCA321M");
		damageBill.setPrice(1000);
		damageBill.setStatus("inactive");
		
		Assert.assertEquals(1, damageBill.getIdPayment());
		Assert.assertEquals(1, damageBill.getIdOrder());
		Assert.assertEquals("Машина в хорошем состояние", damageBill.getDescription());
		Assert.assertEquals("paid", damageBill.getActivity());
		Assert.assertEquals(1000, damageBill.getPrice());
		Assert.assertEquals(LocalDate.parse("2020-04-27"), damageBill.getDate());
		Assert.assertEquals("ABC123N", damageBill.getIndexClient());
		Assert.assertEquals("DCA321M", damageBill.getIndexManager());
		Assert.assertEquals("inactive", damageBill.getStatus());
		
		
		
		
		
		
	}
}
