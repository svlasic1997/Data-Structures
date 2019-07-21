package exam1;

import java.util.Objects;

public abstract class Tiger extends BigCat implements Sound {
	
	public Tiger(String name, int weight)
	{
	 super(name, weight);
	}
	
	@Override
	public void speak()
	{
	System.out.println("Growl!");
	}
	
	public abstract void showStripes(); 
	
	@Override
	public boolean equals(Object o){
		if(this.getClass() == o.getClass()){
			Tiger t = (Tiger)o;
			if(this.name.equals(t.name) && t.weight == this.weight){
				return true;
			}
		}
		
		return false;
	}
	
}
