package ua.nure.smirnov.finalproject.Tests;

import org.junit.BeforeClass;
import org.junit.Test;

import org.junit.Assert;

import ua.nure.smirnov.finalproject.entity.Manager;

public class ManagerTest {
static Manager manager;
	
	@BeforeClass
	public static void constructorTest(){
		manager = new Manager();
	}


    @Test
	public void gettersSettersTest() throws Exception{

		manager.setId(1);
		manager.setAge("20");
		manager.setIdAdmin(1);
		manager.setLogin("alex.kaplin@gmail.com");
		manager.setName("Alex");
		manager.setSurname("Kaplin");
		manager.setNumberManager("066 764 45 35");
		manager.setPassport("ABN787V");
		manager.setPassword("123456");
		manager.setRole("manager");
		manager.setSex("M");
		manager.setStatus("work");
		
		Assert.assertEquals(1, manager.getId());
		Assert.assertEquals("20", manager.getAge());
		Assert.assertEquals(1, manager.getIdAdmin());
		Assert.assertEquals("alex.kaplin@gmail.com", manager.getLogin());
		Assert.assertEquals("Alex", manager.getName());
		Assert.assertEquals("Kaplin", manager.getSurname());
		Assert.assertEquals("066 764 45 35", manager.getNumberManager());
		Assert.assertEquals("ABN787V", manager.getPassport());
		Assert.assertEquals("123456", manager.getPassword());
		Assert.assertEquals("manager", manager.getRole());
		Assert.assertEquals("M", manager.getSex());
		Assert.assertEquals("work", manager.getStatus());
		
	}
}