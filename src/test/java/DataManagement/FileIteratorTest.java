package DataManagement;

import InterFaces.ICompany;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.RandomAccessFile;
import java.nio.file.Files;

import static org.mockito.Mockito.*;

class FileIteratorTest {
    @Mock
    RandomAccessFile randomAccessFile;
    @InjectMocks
    FileIterator fileIterator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testTransData() {
        fileIterator.transData();
    }



    @Test
    void testHasNext() {
        boolean result = fileIterator.hasNext();
        Assertions.assertEquals(false, result);
    }

    @Test
    void testHasPrevious() {
        boolean result = fileIterator.hasPrevious();
        Assertions.assertEquals(false, result);
    }

    @Test
    void testNextIndex() {
        int result = fileIterator.nextIndex();
        Assertions.assertEquals(-1, result);
    }

    @Test
    void testPreviousIndex() {
        int result = fileIterator.previousIndex();
        Assertions.assertEquals(-1, result);
    }

    @Test
    void testRemove() {
        fileIterator.remove();
    }

}

