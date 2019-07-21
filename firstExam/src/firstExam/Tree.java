package firstExam;

public class Tree extends LandPlant {

	private int age;
	
	public Tree(String id, String loc, int num){
		super(id, loc);
		age = num;
	}
	
	public String toString(){
		return super.toString() + ", Age: " + age;
	}
}
