package ua.nure.smirnov.finalproject.Tests;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ua.nure.smirnov.finalproject.entity.Function;

public class FunctionTest {
static Function function;
	
	@BeforeClass
	public static void constructorTest(){
		function = new Function();
	}


    @Test
	public void gettersSettersTest() throws Exception{

		function.setFunction("Personal Driver");
		function.setPrice(1000);
		
		Assert.assertEquals("Personal Driver", function.getFunction());
		Assert.assertEquals(1000, function.getPrice());
	}
}
