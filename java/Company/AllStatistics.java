package Company;

import java.time.LocalDate;
import java.util.TreeSet;
import InterFaces.ICompany;
import InterFaces.IStatistics;

public class AllStatistics implements IStatistics {

	private TreeSet<ICompany> allCompany;

	
	public AllStatistics() {
		this.allCompany = new TreeSet<ICompany>();
	}
	
	
	@Override
	public void addCompany(ICompany iCompany) {
		this.allCompany.add(iCompany);
	}

	@Override
	public int getNumberOfCompany() {
		return this.allCompany.size();
	}

	@Override
	public void setTreeSet(TreeSet<ICompany> treeSet) {
		if(treeSet != null) {
			this.allCompany = treeSet;
		}
		
	}

	@Override
	public TreeSet<ICompany> getAllCompany() {
		return this.allCompany;
	}
	

	@Override
	public TreeSet<ICompany> getByYearCompany(int year) {
		TreeSet<ICompany> temp = new TreeSet<ICompany>();
		for (ICompany iCompany : this.allCompany) {
			if(iCompany.getExpireDate().getYear() == year) {
				temp.add(iCompany);
			}
		}
		
		return temp;
	}

	@Override
	public TreeSet<ICompany> getByYearAndMonthCompany(int year, int month) {
		TreeSet<ICompany> temp = new TreeSet<ICompany>();
		for (ICompany iCompany : this.allCompany) {
			 if(iCompany.getExpireDate().getYear() == year && iCompany.getExpireDate().getMonthValue() == month) {
				System.out.println(iCompany.getExpireDate().getYear() + " --- " + iCompany.getExpireDate().getMonthValue());
				temp.add(iCompany);
			}
		}
	
		return temp;	
	
	}


	@Override
	public void refreshCompany(LocalDate localDate) {
		TreeSet<ICompany> temp = new TreeSet<ICompany>();
		
		for (ICompany iCompany : allCompany) {
			if(iCompany.getExpireDate().compareTo(localDate) >= 0) {
				temp.add(iCompany);
			}
		}
		this.allCompany = temp;
	}
}
