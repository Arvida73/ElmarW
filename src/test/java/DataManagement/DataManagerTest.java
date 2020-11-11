package DataManagement;

import Company.AllStatistics;
import Company.Company;
import InterFaces.ICompany;
import InterFaces.IController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.TreeSet;

import static org.mockito.Mockito.*;

class DataManagerTest {
    @Mock
    RandomAccessFile randomAccessFile;
    @Mock
    AllStatistics allStats;
    @Mock
    FileIterator<ICompany> myIterator;
    @Mock
    IController controller;
    @InjectMocks
    DataManager dataManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void testNotify2() {
        when(allStats.getAllCompany()).thenReturn(new TreeSet<ICompany>(Arrays.asList(new Company("name", LocalDate.of(2020, Month.NOVEMBER, 11), 0))));

        dataManager.notify(true);
    }

    @Test
    void testNotify3() {
        when(allStats.getAllCompany()).thenReturn(new TreeSet<ICompany>(Arrays.asList(new Company("name", LocalDate.of(2020, Month.NOVEMBER, 11), 0))));
        when(allStats.getByYearCompany(anyInt())).thenReturn(new TreeSet<ICompany>(Arrays.asList(new Company("name", LocalDate.of(2020, Month.NOVEMBER, 11), 0))));
        when(allStats.getByYearAndMonthCompany(anyInt(), anyInt())).thenReturn(new TreeSet<ICompany>(Arrays.asList(new Company("name", LocalDate.of(2020, Month.NOVEMBER, 11), 0))));

        dataManager.notify(true, true, 0, 0);
    }
}

