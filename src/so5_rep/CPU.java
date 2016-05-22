package so5_rep;

import java.util.*;
public class CPU 
{
	ArrayList<Process> processes;
	int load;
	int size;
	public CPU()
	{
		processes = new ArrayList<Process>();
		load = 0;
		size = 0;
	}
	public void updateLoad()
	{
		int sum = 0; 
		if(processes.isEmpty()!=true)
		{
			for(int i=0; i < size; i++)
			{
				sum += processes.get(i).weight;
			}
		}
		load = sum;
	}
}

