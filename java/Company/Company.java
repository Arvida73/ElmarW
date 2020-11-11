package Company;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;

import DataManagement.FixedLengthString;

import InterFaces.ICompany;

public class Company implements ICompany, Comparable<ICompany> {
	
	public static final int nameSize = 50;
	
	public static final int expiredYearSize = 4;
	public static final int expiredMonthSize = 2;
	public static final int expiredDaySize = 2;
	
	public static final int startYearSize = 4;
	public static final int startMonthSize = 2;
	public static final int startDaySize = 2;
	
	public static final int guaranteeSize = 3;
	
	public static final int totalSize = expiredYearSize + expiredMonthSize + expiredDaySize +
			startYearSize + startMonthSize + startDaySize
										 + guaranteeSize;
	
	private String name;
	private LocalDate startDate;
	private LocalDate expireDate;
	private int durationMonths;
	
	
	public Company(String name , LocalDate startDate, int durationMonths) {

		this.name = name;
		this.startDate = startDate;
		this.durationMonths = durationMonths;
		

		int expiredYears = durationMonths/12 + this.startDate.getYear();
		int expiredMonth = durationMonths%12 + this.startDate.getMonthValue();
		if(expiredMonth > 12) {
			expiredMonth %= 12;
			expiredYears++;
		}
		
		 this.expireDate = LocalDate.of(expiredYears, expiredMonth, this.startDate.getDayOfMonth());
		
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	
	@Override
	public String toString() {
		return "Company [name=" + name + ", startDate=" + startDate + ", expireDate=" + expireDate
				+ ", warrantyDurationMonths=" + durationMonths + "]";
	}

	
	
	@Override
	public LocalDate getStartDate() {
		return startDate;
	}
	
	@Override
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	@Override
	
	public LocalDate getExpireDate() {
		return expireDate;
	}

	@Override
	public int getDurationMonths() {
		return durationMonths;
	}

	@Override
	public void setDurationMonths(int guaranteeDurationMonths) {
		this.durationMonths = guaranteeDurationMonths;
	}

	@Override
	public void writeCompany(RandomAccessFile randomAccessFile) {
		try {
			FixedLengthString.writeFixedLengthString(this.name, nameSize, randomAccessFile);
			
			FixedLengthString.writeFixedLengthString("" + this.startDate.getYear(), startYearSize, randomAccessFile);
			FixedLengthString.writeFixedLengthString("" + this.startDate.getMonthValue(), startMonthSize, randomAccessFile);
			FixedLengthString.writeFixedLengthString("" + this.startDate.getDayOfMonth(), startDaySize, randomAccessFile);
			
			
			FixedLengthString.writeFixedLengthString("" + this.expireDate.getYear(), expiredYearSize, randomAccessFile);
			FixedLengthString.writeFixedLengthString("" + this.expireDate.getMonthValue(), expiredMonthSize, randomAccessFile);
			FixedLengthString.writeFixedLengthString("" + this.expireDate.getDayOfMonth(), expiredDaySize, randomAccessFile);
			
			
			FixedLengthString.writeFixedLengthString("" + this.durationMonths, guaranteeSize, randomAccessFile);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	

	@Override
	public int compareTo(ICompany company) {
		
		if(this.name.toLowerCase().compareTo(company.getName().toLowerCase()) == 0) {
			if(this.startDate.compareTo(company.getStartDate()) == 0) {
				if(this.durationMonths == company.getDurationMonths()) {
					return 0;
				}
				else if(this.durationMonths > company.getDurationMonths()) {
					return 1;
				}
				else {
					return -1;
				}
			}
			else {
				return this.startDate.compareTo(company.getStartDate());
			}
		}
		else {
			return this.name.toLowerCase().compareTo(company.getName().toLowerCase());
		}

		
	}

	@Override
	public String toStringStartDate() {
		String year , month ,day;
		year = "" + this.startDate.getYear();
		
		
		if(this.startDate.getMonthValue() < 10) {
			month = "0" + this.startDate.getMonthValue();
		}
		else {
			month = "" + this.startDate.getMonthValue();
		}
		if(this.startDate.getDayOfMonth() < 10) {
			day = "0" + this.startDate.getDayOfMonth();
		}
		else {
			day = "" + this.startDate.getDayOfMonth();
		}
		
		
		
		String startDate = day + "." + month + "." + year;
		return startDate;
	}

	@Override
	public String toStringExpireDate() {
		String year , month ,day;
		year = "" + this.expireDate.getYear();
		
		
		if(this.expireDate.getMonthValue() < 10) {
			month = "0" + this.expireDate.getMonthValue();
		}
		else {
			month = "" + this.expireDate.getMonthValue();
		}
		if(this.expireDate.getDayOfMonth() < 10) {
			day = "0" + this.expireDate.getDayOfMonth();
		}
		else {
			day = "" + this.expireDate.getDayOfMonth();
		}
		
		
		
		String expireDate = day + "." + month + "." + year;
		return expireDate;
	}
}
