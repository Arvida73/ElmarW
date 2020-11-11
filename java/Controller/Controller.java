package Controller;

import java.util.ArrayList;

import InterFaces.IController;
import InterFaces.ICompany;
import Company.CompanyData;
import InterFaces.IDataManager;
import InterFaces.IMyPanel;

public class Controller implements IController {

	private ArrayList<IMyPanel> iMyPanels;
	private ArrayList<IDataManager> iDataManagers;
	
	public Controller() {
		iMyPanels = new ArrayList<IMyPanel>();
		iDataManagers = new ArrayList<IDataManager>();
	}
	
	@Override
	public void registerDisplay(IMyPanel iMyPanel) {
		if(!iMyPanels.contains(iMyPanel)) {
			iMyPanels.add(iMyPanel);
		}
	}

	@Override
	public void registerManager(IDataManager iDataManager) {
		if(!iDataManagers.contains(iDataManager)) {
			iDataManagers.add(iDataManager);
		}
	}

	@Override
	public void notify(ICompany company, boolean b) {
		if(b) {
			for (IMyPanel myPanel : iMyPanels) {
				myPanel.notify(b);
			}
		}
		else {
			if(company != null) {
				for (IMyPanel myPanel : iMyPanels) {
					myPanel.notify(company);
				} 
			}
		}
	}

	@Override
	public void notify(CompanyData companyData) {
		if(companyData.search()) {
			searchRequest(companyData);
		}
		else if(companyData.update()) {
			for (IDataManager iDataManager : iDataManagers) {
				iDataManager.notify(companyData.update());
			}
		}
		else {
			createRequest(companyData);
		}
		
	}
	
	private void searchRequest(CompanyData companyData) {
		for (IDataManager iDataManager : iDataManagers) {
			iDataManager.notify(companyData.searchAll(), companyData.searchOnlyYear() , companyData.getYearSearch() , companyData.getMonthSearch());
		}
		
	}

	private String[] companyDataString(CompanyData companyData) {
		String[] data = new String[5];
		data[0] = companyData.getName();
		data[1] = String.valueOf(companyData.getStartDate().getYear()).trim();
		data[2] = String.valueOf(companyData.getStartDate().getMonthValue()).trim();
		data[3] = String.valueOf(companyData.getStartDate().getDayOfMonth()).trim();
		data[4] = String.valueOf(companyData.getGuaranteeInMonths()).trim();
	
		return data;
	}
	
	private void createRequest(CompanyData companyData) {
		if(companyData.getStartDate() != null) {
			String[] data = companyDataString(companyData);
			
			for (IDataManager iDataManager : iDataManagers) {
				iDataManager.notify(data, companyData.creat());
			}		
		}
	
	}


	

}
