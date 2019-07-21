package firstExam;

public class PlantTester {

	public static void main(String[] args) {
		
		Plant plan1 = new Plant("Maple");
		LandPlant land1 = new LandPlant("Maple", "West");
		Tree tree1 = new Tree("Maple", "West", 5);
		String stri1 = new String("ACGT");
		
//		Plant planA = stri1;
//		System.out.println(planA.toString());
		
		LandPlant landB = tree1;
		Plant planB = landB;
		System.out.println(planB.toString());
		
//		LandPlant landC = plan1;
//		Tree treeC = landC;
//		System.out.println(treeC.toString());
		
//		Plant planD = tree1;
//		LandPlant landD = planD;
//		System.out.println(landD.toString());
		
//		Plant planE = tree1;
//		LandPlant landE = (LandPlant) planE;
//		Tree treeE = (Tree) landE;
//		System.out.println(treeE.toString());
//		
//		LandPlant landF = (LandPlant) plan1;
//		Tree treeF = (Tree) landF;
//		System.out.println(treeF.toString());
		
		Plant planG = land1;
		LandPlant landG = (LandPlant) planG;
		Tree treeG = (Tree) landG;
		System.out.println(treeG.toString());

	}

}
