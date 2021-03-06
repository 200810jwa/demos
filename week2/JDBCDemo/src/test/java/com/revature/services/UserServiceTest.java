package com.revature.services;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.repositories.IUserDAO;

public class UserServiceTest {
	
	@Mock
	private IUserDAO mockedDao;
	
	private UserService testInstance = new UserService(mockedDao);
	private User matt;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		testInstance = new UserService(mockedDao);
		
		matt = new User(1, "moberlies", "password", Role.Admin);
		
		when(mockedDao.findByUsername("moberlies")).thenReturn(matt);
		when(mockedDao.findByUsername(anyString())).thenReturn(null);
		when(mockedDao.findById(1)).thenReturn(matt);
		when(mockedDao.findById(anyInt())).thenReturn(null);
		// Regardless of what input value is provided, the fake DAO will return this specific
		// User object when the findByUsername method is invoked
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLoginSuccessful() {
		assertEquals(testInstance.login("moberlies", "password"), matt);
//		verify(mockedDao, times(1)).findByUsername("moberlies");
	}
	
	@Test
	public void testLoginFailure() {
		assertEquals(testInstance.login("moberlies", "wrongpw"), null);
	}

	@Test
	public void testRegister() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testChangePasswordSuccessful() {
		assertTrue(testInstance.changePassword(1, "otherpw"));
	}
	
	@Test
	public void testChangePasswordFailure() {
		assertFalse(testInstance.changePassword(0, anyString()));
	}
}
