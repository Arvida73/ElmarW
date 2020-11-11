package Controller;

import Company.CompanyData;
import InterFaces.ICompany;
import InterFaces.IDataManager;
import InterFaces.IMyPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ControllerTest {

    private Controller controllerUnderTest;

    @BeforeEach
    void setUp() {
        controllerUnderTest = new Controller();
    }

    @Test
    void testRegisterDisplay() {

        final IMyPanel iMyPanel = null;
        controllerUnderTest.registerDisplay(iMyPanel);
    }

    @Test
    void testRegisterManager() {

        final IDataManager iDataManager = null;
        controllerUnderTest.registerManager(iDataManager);
    }

    @Test
    void testNotify() {

        final ICompany company = null;
        controllerUnderTest.notify(company, false);
    }

    @Test
    void testNotify1() {

        final CompanyData companyData = new CompanyData(null, false, false, false, false, false);
        controllerUnderTest.notify(companyData);
    }
}
