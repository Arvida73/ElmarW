package InterFaces;

import Company.CompanyData;

public interface IController {
	
	public void registerDisplay(IMyPanel iMyPanel);
	public void registerManager(IDataManager iDataManager);
	
	// Z IDataManager do controler
	public void notify(ICompany company , boolean b);

	// z view do controler
	public void notify(CompanyData companyData);
}
