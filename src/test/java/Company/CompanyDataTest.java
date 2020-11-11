package Company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

class CompanyDataTest {

    CompanyData companyData = new CompanyData(new Company("name", LocalDate.of(2020, Month.NOVEMBER, 11), 0), true, true, true, true, true);

    @Test
    void testSearchOnlyYear() {
        boolean result = companyData.searchOnlyYear();
        Assertions.assertEquals(true, result);
    }

    @Test
    void testUpdate() {
        boolean result = companyData.update();
        Assertions.assertEquals(true, result);
    }

    @Test
    void testCreat() {
        boolean result = companyData.creat();
        Assertions.assertEquals(true, result);
    }

    @Test
    void testSetSearch() {
        companyData.setSearch(0, 0);
    }

    @Test
    void testSearch() {
        boolean result = companyData.search();
        Assertions.assertEquals(true, result);
    }

    @Test
    void testSearchAll() {
        boolean result = companyData.searchAll();
        Assertions.assertEquals(true, result);
    }
}

