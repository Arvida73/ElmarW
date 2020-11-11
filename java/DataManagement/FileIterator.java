package DataManagement;


import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ListIterator;

import InterFaces.ICompany;
import Company.Company;


public class FileIterator <Files extends ICompany> implements ListIterator<Files>{

	private final String ioException = "Wystąpił problem bazie I/O  do /w pliku";
	private final int icompanyTotalSize = (Company.totalSize)*2;


	private int index;
	private RandomAccessFile randomAccessFile;
	private int size;
	
	public FileIterator(RandomAccessFile randomAccessFile) {
		this.size =0;
		this.index =0;
		
		this.randomAccessFile = randomAccessFile;

		try {
			while (randomAccessFile.getFilePointer() + (size * icompanyTotalSize) < randomAccessFile.length()) {
				size++;
			}
		} catch (IOException exception) {
			System.out.println(ioException);
		}
	}
	
	
	public void transData() {
		this.size = 0;
		this.index = 0;
		try {
			randomAccessFile.setLength(0);
		} catch (IOException exception) {
			System.out.println(ioException);
		}
	}
	
	@Override
	public void add(Files e) {
		try {
			randomAccessFile.seek(randomAccessFile.length());
			e.writeCompany(randomAccessFile);
			size++;
			index = size;
		} catch (IOException exception) {
			System.out.println(ioException);
		}
		
	}

	@Override
	public boolean hasNext() {
		if (index < size) {
			return true;
		}
		return false;
	}

	@Override
	public boolean hasPrevious() {
		if (index > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Files next() {
		Files temp = null;
		if (hasNext()) {
			temp = getNextICompany();
			index++;
			return temp;
		}
		return temp;
	}

	@Override
	public int nextIndex() {
		if (hasNext()) {
			return index + 1;
		}
		return -1;
	}

	@Override
	public Files previous() {
		if(hasPrevious()) {
			try {
				index--;
				randomAccessFile.seek(index * icompanyTotalSize);
				
				Files temp = getNextICompany();
				randomAccessFile.seek(index * icompanyTotalSize);

				return temp;

			} catch (IOException exception) {
				System.out.println(ioException);
			}
		}
		return null;
	}


	@Override
	public int previousIndex() {
		if (hasPrevious()) {
			return index - 1;
		}
		return -1;
	}

	@Override
	public void remove() {
		int tempIndex = this.index - 1;
		ArrayList<Files> allADeleted = new ArrayList<Files>();
		while(hasNext()) {
			allADeleted.add(next());
		}
		try {
			randomAccessFile.setLength(tempIndex * icompanyTotalSize);
			this.index = tempIndex;
			for (Files iCompany : allADeleted) {
				add(iCompany);
			}
			
		} catch (IOException exception) {
			System.out.println(ioException);
		}
		
		
		
		
	}

	@Override
	public void set(Files e) {
//
	}
	

	
	private Files getNextICompany() {
		try {
			String name , aYearString , aMonthString , aDayString , bYearString , bMonthString , bDayString , guaranteeString;
			int aYear , aMonth , aDay , bYear , bMonth , bDay , guarantee;
			
			name = FixedLengthString.readFixedLengthString(Company.nameSize, randomAccessFile).trim();
			
			aYearString = FixedLengthString.readFixedLengthString(Company.startYearSize, randomAccessFile).trim();
			aMonthString = FixedLengthString.readFixedLengthString(Company.startMonthSize, randomAccessFile).trim();
			aDayString = FixedLengthString.readFixedLengthString(Company.startDaySize, randomAccessFile).trim();
			
			bYearString = FixedLengthString.readFixedLengthString(Company.expiredYearSize, randomAccessFile).trim();
			bMonthString = FixedLengthString.readFixedLengthString(Company.expiredMonthSize, randomAccessFile).trim();
			bDayString = FixedLengthString.readFixedLengthString(Company.expiredDaySize, randomAccessFile).trim();

			guaranteeString = FixedLengthString.readFixedLengthString(Company.guaranteeSize, randomAccessFile).trim();
			
			aYear = Integer.valueOf(aYearString);
			aMonth = Integer.valueOf(aMonthString);
			aDay = Integer.valueOf(aDayString);
			
			bYear = Integer.valueOf(bYearString);
			bMonth = Integer.valueOf(bMonthString);
			bDay = Integer.valueOf(bDayString);
			
			guarantee = Integer.valueOf(guaranteeString);
			
			LocalDate aDate = LocalDate.of(aYear, aMonth, aDay);
			LocalDate bDate = LocalDate.of(bYear, bMonth, bDay);
			
			return initCompanyWorkAround(name, aDate, bDate, guarantee);
			
		} catch (IOException e) {
			System.out.println(ioException);
		}
		return null;
	}

	
	
	@SuppressWarnings({ "unchecked" })
	private Files initCompanyWorkAround(String name , LocalDate purchaseDate , LocalDate expireDate , int warranty) {
		return (Files) new Company(name, purchaseDate, warranty);
	}
	
	

	
}



















