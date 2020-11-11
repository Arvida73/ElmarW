package Company;

import InterFaces.ICompany;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.TreeSet;

import static org.mockito.Mockito.*;

class AllStatisticsTest {
    @Mock
    TreeSet<ICompany> allCompany;
    @InjectMocks
    AllStatistics allStatistics;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddCompany() {
        allStatistics.addCompany(new Company("name", LocalDate.of(2020, Month.NOVEMBER, 11), 0));
    }

    @Test
    void testGetNumberOfCompany() {
        int result = allStatistics.getNumberOfCompany();
        Assertions.assertEquals(0, result);
    }

    @Test
    void testSetTreeSet() {
        allStatistics.setTreeSet(new TreeSet<ICompany>(Arrays.asList(new Company("name", LocalDate.of(2020, Month.NOVEMBER, 11), 0))));
    }
}

