package ua.nure.smirnov.finalproject.Tests;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ua.nure.smirnov.finalproject.entity.Admins;

public class AdminTest {
static Admins admin;
	
	@BeforeClass
	public static void constructorTest(){
		admin = new Admins();
	}


    @Test
	public void gettersSettersTest() throws Exception{

		admin.setAgeAdmin("20");
		admin.setIdAdmin(1);
		admin.setLoginAdmin("annatovm@gmail.com");
		admin.setNameAdmin("Anna");
		admin.setNumberAdmin("050 345 0998");
		admin.setNumberOfPhone("050 345 0998");
		admin.setPassport("AB6578L5");
		admin.setPasswordAdmin("123456");
		admin.setRole("admin");
		admin.setSex("W");
		admin.setSurnameAdmin("Tovmasyan");
		
		Assert.assertEquals(1, admin.getIdAdmin());
		Assert.assertEquals("20", admin.getAgeAdmin());
		Assert.assertEquals("annatovm@gmail.com", admin.getLoginAdmin());
		Assert.assertEquals("Anna", admin.getNameAdmin());
		Assert.assertEquals("050 345 0998", admin.getNumberAdmin());
		Assert.assertEquals("050 345 0998", admin.getNumberOfPhone());
		Assert.assertEquals("AB6578L5", admin.getPassport());
		Assert.assertEquals("123456", admin.getPasswordAdmin());
		Assert.assertEquals("123456", admin.getPasswordAdmin());
		Assert.assertEquals("admin", admin.getRole());
		Assert.assertEquals("W", admin.getSex());
		Assert.assertEquals("Tovmasyan", admin.getSurnameAdmin());
		
	}
}
