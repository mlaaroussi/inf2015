package regle;

import formationcontinue.DateUtil;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class CycleTest {

    public CycleTest() {
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

    @Test
    public void testEstDateValide() {
        System.out.println("estDateValide");
        Cycle instance = new Cycle("3030-5050", "2010-10-11", "2012-09-10");

        assertEquals(true, instance.estDateValide(DateUtil.getDate("2010-10-11")));
        assertEquals(true, instance.estDateValide(DateUtil.getDate("2012-09-10")));
        assertEquals(true, instance.estDateValide(DateUtil.getDate("2011-01-09")));
        assertEquals(false, instance.estDateValide(DateUtil.getDate("2010-10-10")));
    }

}
