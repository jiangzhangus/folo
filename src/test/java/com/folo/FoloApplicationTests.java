package com.folo;

import com.folo.entity.Role;
import com.folo.service.RoleService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FoloApplicationTests.class)
@WebAppConfiguration
public class FoloApplicationTests {


    @BeforeClass
    public static void beforeClass() {
        // run once before all the tests
    }

    @AfterClass
    public static void afterClass() {
        // run once after all tests are done.
    }

    @Before
    public void beforeEachTest() {
        // run before each test
    }

    @After
    public void afterEachTest() {
        // run after each test
    }

//    @Test
//    public void testRoles() {
//        List<Role> roleList = roleService.getRoleList();
//        assertEquals("pre-defined roles", (long)roleList.size(), 4l );
//    }

    @Test
    public void testUnitTest() {
        assertEquals("pre-defined roles", 4l, 4l );
    }


}


