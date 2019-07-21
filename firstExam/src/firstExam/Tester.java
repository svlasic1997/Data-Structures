package firstExam;

public class Tester {

	public static void main(String[] args) {
		
		Supclass sup = new Supclass("Mike");
		Midclass mid = new Midclass("Mike", 123);
		Subclass sub = new Subclass("Mike", 123, true);
		
		
//		Midclass midA = sub;
//		Supclass supA = midA;
//		System.out.println(supA.toString());
		
//		Midclass midB = sup;
//		Subclass subB = midB;
//		System.out.println(subB.toString());
		
//		Supclass supC = sub;
//		Midclass midC = supC;
//		System.out.println(supC.toString());
		
//		Supclass supD = mid;
//		Subclass subD = (Subclass) supD;
//		System.out.println(subD.toString());
		
//		Supclass supE = mid;
//		Midclass midE = (Midclass) supE;
//		System.out.println(midE.toString());
		
//		Subclass subF = new Subclass("John");
//		System.out.println(subF.toString());
		
//		System.out.println(sup.name);
		
//		System.out.println(sub.big);

	}

}
