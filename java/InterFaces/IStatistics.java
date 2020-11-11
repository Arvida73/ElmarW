package InterFaces;

import java.time.LocalDate;
import java.util.TreeSet;

public interface IStatistics {

	public void addCompany(ICompany iCompany);
	public int getNumberOfCompany();
	public void setTreeSet(TreeSet<ICompany> treeSet);
	public TreeSet<ICompany> getAllCompany();
	public TreeSet<ICompany> getByYearAndMonthCompany(int year , int month);
	public TreeSet<ICompany> getByYearCompany(int year);
	public void refreshCompany(LocalDate localDate);
}
