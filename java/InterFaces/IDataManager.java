package InterFaces;

public interface IDataManager {

	public void notify(String[] data , boolean b);
	public void notify(boolean update);
	public void notify(boolean sortingAll , boolean sortingYear , int year , int month);
	public void loadExistingData();
}
