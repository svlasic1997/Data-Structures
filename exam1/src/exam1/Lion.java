package exam1;

public class Lion extends BigCat implements Sound{
	
	public Lion(String name, int weight)
	 {
	 super(name, weight);
	 }

	 @Override
	 public void speak()
	 {
	 System.out.println("Roar!");
	 }
	 public Lion makeClone()
	 {
	 return new Lion(name, weight);
	 }

}
