package firstExam;

public class Subclass extends Midclass {
	
	private boolean big;
	
	public Subclass(String str, int num, boolean bon){
		
		super(str, num);
		big = bon;
	}
	
	
	public String toString(){
		
		return super.toString() + ",  Big: "+ big;
	}
}
