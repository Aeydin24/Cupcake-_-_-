/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataTest;

import entity.Users;
import mapper.DataMapperUsers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author ndupo
 */
public class DataMapperUsersTest {
    
    public DataMapperUsersTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getUser method, of class UserDataMapper.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetUser() throws Exception {
        System.out.println("getUser");
        String userName = "test";
        DataMapperUsers instance = new DataMapperUsers();
        Users expResult = new Users("Test", "Test", 0, true, "Test@Test.com");
        Users result = instance.getUser(userName);
        assertEquals(expResult.toString(), result.toString());
    }
}
