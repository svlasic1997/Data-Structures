package firstExam;

public class Midclass extends Supclass {

	int id;
	
	public Midclass(String str, int num){
		
		super(str);
		id = num;
	}
	
	@Override
	public String toString(){
		
		return super.toString() + ",  ID: " + id;
	}
}
