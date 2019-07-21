package firstExam;

public class Supclass {
	
	protected String name; 
	
	public Supclass(String str){
		
		name = new String(str);
	}
	
	public String toString(){
		
		return "Name: " + name;
	}
	
}
