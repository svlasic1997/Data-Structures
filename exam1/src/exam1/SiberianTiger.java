package exam1;

public class SiberianTiger extends Tiger{
	
	private String diet;
	public SiberianTiger(String name, int weight)
	{
	super(name, weight);
	diet = "Wild Boar";
	}
	@Override
	public void showStripes()
	{
	System.out.println("Sparse");
	}
	public void eat()
	{
	System.out.println(diet);
	}

}
