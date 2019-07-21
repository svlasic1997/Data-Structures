package exam1;

public class LionPride implements Cloneable{
	
	private String name;
	private int size;
	private Lion[] lions;
	public LionPride(String name, Lion[] lions)
	{
	this.name = name;
	size = lions.length;
	this.lions = new Lion[size];
	for (int i = 0; i < size; i++)
	 {
	this.lions[i] = lions[i].makeClone();
	 }
	}
	public String getName()
	{
	return name;
	}
	
	public void setName(String name)
	{
	this.name = name;
	}
	public int getSize()
	{
	return size;
	}
	public Lion getLion(int i)
	{
	if (i < 0 || i >= size)
	return null;
	return lions[i]; 
	}
	
	@Override
	public Object clone(){
		try{
			LionPride copy = (LionPride)super.clone();
			
			copy.lions = new Lion[lions.length];
			for(int i=0; i < lions.length; i++){
				copy.lions[i] = lions[i].makeClone();
			}
			
			return copy;
			
		}
		catch(CloneNotSupportedException e){
			return null;
		}
	}
}
