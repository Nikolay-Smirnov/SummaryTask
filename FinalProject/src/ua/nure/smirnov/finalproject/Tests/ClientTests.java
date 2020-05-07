package ua.nure.smirnov.finalproject.Tests;

import org.junit.BeforeClass;
import org.junit.Test;

import org.junit.Assert;

import ua.nure.smirnov.finalproject.entity.Client;

public class ClientTests {
static Client client;
	
	@BeforeClass
	public static void constructorTest(){
		client = new Client();
	}


    @Test
	public void gettersSettersTest() throws Exception{

		client.setIdClient(1);;
		client.setAge("20");
		client.setLoginClient("nik202709@gmail.com");
		client.setName("Nikolay");
		client.setSurname("Smirnov");
		client.setNumberClient("095 340 18 31");
		client.setPassport("AB555BB");
		client.setPassword("123456");
		client.setRole("client");
		client.setSex("M");
		client.setStatus("unblock");
		
		Assert.assertEquals(1, client.getIdClient());
		Assert.assertEquals("20", client.getAge());
		Assert.assertEquals("nik202709@gmail.com",client.getLoginClient());
		Assert.assertEquals("Nikolay",client.getNameClient());
		Assert.assertEquals("Smirnov",client.getSurname());
		Assert.assertEquals("095 340 18 31",client.getNumberClient());
		Assert.assertEquals("AB555BB",client.getPassport());
		Assert.assertEquals("123456",client.getPassword());
		Assert.assertEquals("client",client.getRole());
		Assert.assertEquals("M",client.getSex());
		Assert.assertEquals("unblock",client.getStatus());
	}
}
