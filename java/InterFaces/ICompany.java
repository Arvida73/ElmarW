package InterFaces;

import java.io.RandomAccessFile;
import java.time.LocalDate;

public interface ICompany {

	public void setName(String name);
	public String getName();
	
	public void setStartDate(LocalDate date);
	public LocalDate getStartDate();
	public LocalDate getExpireDate();
	
	public void setDurationMonths(int guaranteeDurationMonths);
	public int getDurationMonths();
	
	public void writeCompany(RandomAccessFile randomAccessFile);
	
	public String toString();
	public String toStringStartDate();
	public String toStringExpireDate();
	
	public int compareTo(ICompany company);
	
	
	
}
