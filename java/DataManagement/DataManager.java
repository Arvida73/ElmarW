package DataManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.util.TreeSet;

import InterFaces.IController;
import InterFaces.ICompany;
import Company.AllStatistics;
import Company.Company;
import InterFaces.IDataManager;



public class DataManager implements IDataManager{

	private final String dataName = "Data (Nie dotykaj i nie usuwaj)";
	private final String rw = "rw";
	private final String fileNotFoundException = "Plik nie istnieje lub jest zla ścieżka";
	private final String ioException = "Wystąpił problem bazie I/O  do /w pliku";
	
	private RandomAccessFile randomAccessFile;
	
	private AllStatistics allStats;

	private FileIterator<ICompany> myIterator;
	
	private String fileName;
	
	private IController controller;
	
	public DataManager(String fileName , IController iController) {
		
		String data = this.dataName;
		File file = new File(data);
		file.mkdir();


		this.fileName = fileName;
		this.controller = iController;
		allStats = new AllStatistics();

		try {
			
			randomAccessFile = new RandomAccessFile(file.getAbsolutePath() + "/" +this.fileName, rw);
			myIterator = new FileIterator<ICompany>(randomAccessFile);
			
			
			
		} catch (FileNotFoundException e) {
			System.out.println(fileNotFoundException);
		}
	}

	public void loadExistingData() {
		try {
			while(myIterator.hasPrevious()) {
				myIterator.previous();
			}
			
			while(randomAccessFile.getFilePointer() < randomAccessFile.length()) {
				ICompany temp = myIterator.next();
				if(temp != null) {
					allStats.addCompany(temp);
					
				}
			}
		} catch (IOException e) {
			System.out.println(ioException);
		}
		
		for (ICompany iCompany : allStats.getAllCompany()) {
			controller.notify(iCompany , false);
		}
	}

	@Override
	public void notify(String[] data, boolean b) {
		ICompany iCompany = null;
		if(b) {
			int year = Integer.valueOf(data[1].trim());
			int month = Integer.valueOf(data[2].trim());
			int day = Integer.valueOf(data[3].trim());
			
			LocalDate aDate = LocalDate.of(year, month , day);
			
			int guarantee = Integer.valueOf(data[4].trim());
			iCompany = new Company(data[0], aDate , guarantee);
			myIterator.add(iCompany);
			this.allStats.addCompany(iCompany);
		}
		else {
			deleteCompanyFromDataBase(data);
		}
		
		if(iCompany != null) {
			this.controller.notify(iCompany , false);
		}
	}
	

	@Override
	public void notify(boolean update) {
//		LocalDate now = LocalDate.now();
//		this.allStats.refreshCompany(now);
		resetSystem();
	}
	
	
	private void deleteCompanyFromDataBase(String data[]) {
		for (ICompany iCompany : this.allStats.getAllCompany()) {
			if(compareCompanyByData(data, iCompany)) {
				this.allStats.getAllCompany().remove(iCompany);
				break;
			}
		}
		resetSystem();
		
	}
	
	private boolean compareCompanyByData(String data[] , ICompany iCompany) {
		LocalDate iCompanyStartDate = iCompany.getStartDate();
		
		if(data[0].trim().compareTo(iCompany.getName().trim()) != 0) {
			return false;
		}
		else if((Integer.valueOf(data[1])) != iCompanyStartDate.getYear()) {
			return false;
		}
		else if(Double.valueOf(data[2]) != iCompanyStartDate.getMonthValue()){
			return false;
		}
		else if(Integer.valueOf(data[3]) != iCompanyStartDate.getDayOfMonth()) {
			return false;
		}
		else if(Integer.valueOf(data[4]) != iCompany.getDurationMonths()) {
			return false;
		}
		else {
			return true;
		}
	}
	
	private void resetSystem() {
		myIterator.transData();
		this.controller.notify(null, true);
		
		
		for (ICompany iCompany : this.allStats.getAllCompany()) {
			myIterator.add(iCompany);
			this.controller.notify(iCompany, false);
		}	
	}

	@Override
	public void notify(boolean sortingAll, boolean sortingYear, int year , int month) {
		TreeSet<ICompany> temp = new TreeSet<ICompany>();
		if(sortingAll) {
			temp = this.allStats.getAllCompany();
		}
		else {
			if(sortingYear) {
				temp = this.allStats.getByYearCompany(year);
			}
			else {
				temp = this.allStats.getByYearAndMonthCompany(year, month);
			}
		}
		this.controller.notify(null, true);
		
		for (ICompany iCompany : temp) {
			this.controller.notify(iCompany, false);
		}

		
	}
		
}






