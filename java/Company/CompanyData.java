package Company;

import java.time.LocalDate;

import InterFaces.ICompany;

public class CompanyData {
	
	
	private String name;
	
	private LocalDate startDate;
	private LocalDate expiredDate;
	
	private int guaranteeInMonths;
	
	private int yearSearch;
	private int monthSearch;

	private boolean search;
	private boolean searchAll;
	private boolean searchOnlyYear;
	private boolean update;
	private boolean creat;

	
	public CompanyData(ICompany company , boolean creat , boolean update , boolean search , boolean searchAll , boolean searchOnlyYear ) {
		if(company !=null) {
			this.name = company.getName();
			this.startDate = company.getStartDate();
			this.expiredDate = company.getExpireDate();
			this.guaranteeInMonths = company.getDurationMonths();
			this.yearSearch = this.startDate.getYear();
			this.monthSearch =  this.startDate.getMonthValue();
		}
		this.creat = creat;
		this.update = update;
		this.search = search;
		this.searchAll = searchAll;
		this.searchOnlyYear = searchOnlyYear;
		
		
	}
	
	public boolean searchOnlyYear() {
		return searchOnlyYear;
	}

	public boolean update() {
		return update;
	}

	public boolean creat() {
		return creat;
	}
	
	public void setCreat(boolean create) {
		this.creat = create;
	}

	public void setSearch(int year , int month) {
		this.yearSearch = year;
		this.monthSearch = month;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getExpiredDate() {
		return expiredDate;
	}

	public int getGuaranteeInMonths() {
		return guaranteeInMonths;
	}

	public int getYearSearch() {
		return yearSearch;
	}

	public int getMonthSearch() {
		return monthSearch;
	}

	public boolean search() {
		return search;
	}

	public boolean searchAll() {
		return searchAll;
	}

	public void setSearch(boolean isSearching) {
		this.search = isSearching;
	}

	public String getName() {
		return name;
	}


	
	
		
}
