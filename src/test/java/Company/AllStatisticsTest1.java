package Company;

import InterFaces.ICompany;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AllStatisticsTest1 {

    private AllStatistics allStatisticsUnderTest;

    @BeforeEach
    void setUp() {
        allStatisticsUnderTest = new AllStatistics();
    }


    @Test
    void testGetNumberOfCompany() {

        final int result = allStatisticsUnderTest.getNumberOfCompany();
        assertEquals(0, result);
    }

    @Test
    void testSetTreeSet() {

        final TreeSet<ICompany> treeSet = new TreeSet<>(Arrays.asList());
        allStatisticsUnderTest.setTreeSet(treeSet);
    }

    @Test
    void testGetByYearCompany() {
        final TreeSet<ICompany> result = allStatisticsUnderTest.getByYearCompany(0);
    }

    @Test
    void testGetByYearAndMonthCompany() {
        final TreeSet<ICompany> result = allStatisticsUnderTest.getByYearAndMonthCompany(0, 0);
    }

    @Test
    void testRefreshCompany() {
        allStatisticsUnderTest.refreshCompany(LocalDate.of(2017, 1, 1));
    }
}
