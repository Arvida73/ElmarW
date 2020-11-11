package Company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

class CompanyTest {

    Company company = new Company("name", LocalDate.of(2020, Month.NOVEMBER, 11), 0);

    @Test
    void testToString() {
        String result = company.toString();
        Assertions.assertEquals("Company [name=name, startDate=2020-11-11, expireDate=2020-11-11, warrantyDurationMonths=0]", result);
    }

    @Test
    void testCompareTo() {
        int result = company.compareTo(new Company("name", LocalDate.of(2020, Month.NOVEMBER, 11), 0));
        Assertions.assertEquals(0, result);
    }

    @Test
    void testToStringStartDate() {
        String result = company.toStringStartDate();
        Assertions.assertEquals("11.11.2020", result);
    }

    @Test
    void testToStringExpireDate() {
        String result = company.toStringExpireDate();
        Assertions.assertEquals("11.11.2020", result);
    }
}

