package firstExam;

public class LandPlant extends Plant {

	private String location;
	
	public LandPlant(String id, String loc){
		super(id);
		location = loc;
	}
	
	public String toString(){
		return super.toString() + ", Location: " + location;
		
	}
}
