package formationcontinue;

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DateUtilTest {

    public DateUtilTest() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void testEstDateValide() {
        assertTrue(DateUtil.estDateValide("2014-11-01"));
    }

    @Test
    public void testEstDateInvalide() {
        assertFalse(DateUtil.estDateValide("334-44"));
    }

    @Test
    public void testGetDateValide() {

        Calendar cal = new GregorianCalendar();

        cal.setTime(DateUtil.getDate("2014-04-12"));

        assertEquals(cal.get(Calendar.MONTH), Calendar.APRIL);
        assertEquals(cal.get(Calendar.DAY_OF_MONTH), 12);
        assertEquals(cal.get(Calendar.YEAR), 2014);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetDateInvalide() {

        DateUtil.getDate("2014-18-12");

    }

}
