package so5_rep;

import java.util.Random;
public class Process 
{
	int weight;
	public Process()
	{
		Random r = new Random();
		weight = 1 + r.nextInt(5);
	}
}