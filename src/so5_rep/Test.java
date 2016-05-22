package so5_rep;

public class Test 
{
	public static void main(String []args)
	{
		System1 s = new System1(30,45);
		s.first();
		System.out.println("First:");
		System.out.println("Average ballast: " + s.ballast()+"%");
		System.out.println("Number of ballast queries: " + s.outcome[0]);
		System.out.println("Pricesses migrations: " + s.outcome[1]+"\n");
		s.second();
		System.out.println("Second:");
		System.out.println("Average ballast: " + s.ballast1()+"%");
		System.out.println("Number of ballast queries: " + s.outcome1[0]);
		System.out.println("Pricesses migrations: " + s.outcome1[1]+"\n");
		s.third();
		System.out.println("Third:");
		System.out.println("Average ballast: " + s.ballast2()+"%");
		System.out.println("Number of ballast queries: " + s.outcome2[0]);
		System.out.println("Pricesses migrations: " + s.outcome2[1]+"\n");
	}
}
