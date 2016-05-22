package so5_rep;

import java.util.Random;

public class System1
{
	public final static int outcomeHOLD = 90;
	public final static int outcomeHOLD2 = 30;
	public final static int Z_time = 3;
	public CPU[] cpu;
	public int CPUQuantity;
	public int maxTime;
	public double[] results;
	public int[] outcome;
	public int[] outcome1;
	public int[] outcome2;
	public double[] results1;
	public double[] results2;
	public int processesNumber;
	
	public System1(int CPUNumber, int maxTime)
	{
		//Random r= new Random();
		cpu = new CPU[CPUNumber];
		this.CPUQuantity = 0;
		this.maxTime = maxTime;
		results = new double[maxTime];
		outcome = new int[2];
		outcome1 = new int[2];
		outcome2 = new int[2];
		results1 = new double[maxTime];
		results2 = new double[maxTime];
		processesNumber = 1000;
		for(int i=0; i < CPUNumber; i++)
		{
			cpu[i] = new CPU();
			CPUQuantity++;
		}
	}
	public void first()
	{
		int ballastQuery = 0;
		int processMigration= 0;
		for(int i=1; i <= maxTime; i++)
		{
			Random rand= new Random();
			//int liczbaProcesow = 1+rand.nextInt(500);
			for(int j=0; j < processesNumber; j++)
			{
				int rCPU = rand.nextInt(cpu.length);
				Process newProcess = new Process();
				int chooseCPU = rand.nextInt(cpu.length);
				int z_time = 1;
				while(chooseCPU == rCPU || cpu[chooseCPU].load > outcomeHOLD )
				{
					chooseCPU = rand.nextInt(cpu.length);
					z_time++;
					ballastQuery++;
					if(z_time == (Z_time + 1))
					{
						break;
					}
				}
				if(cpu[chooseCPU].load > outcomeHOLD)
				{
					if(cpu[rCPU].load + newProcess.weight <= 100)
					{
						cpu[rCPU].processes.add(newProcess);
						cpu[rCPU].size++;
						cpu[rCPU].updateLoad();
					}
					ballastQuery++;
				}
				else
				{
					if(cpu[chooseCPU].load + newProcess.weight <= 100)
					{
						cpu[chooseCPU].processes.add(newProcess);
						cpu[chooseCPU].size++;
						cpu[chooseCPU].updateLoad();
					}
					processMigration++;
					ballastQuery++;
				}
			}
			int ballast = 0;
			for(int k = 0; k < cpu.length; k++)
			{
				ballast += cpu[k].load;
			}
			results[i-1] = ballast/cpu.length;
			for(int k=0; k < cpu.length; k++)
			{
				cpu[k].processes.clear();
				cpu[k].size = 0;
				cpu[k].updateLoad();
			}
			outcome[0] = ballastQuery;
			outcome[1] = processMigration;
		}
	}
	public void second()
	{
		int ballastQuery = 0;
		int processMigration = 0;
		for(int i=1; i <= maxTime; i++)
		{
			Random rand= new Random();
			//int liczbaProcesow = 1+rand.nextInt(500);
			for(int j=0; j < processesNumber; j++)
			{
				int rCPU = rand.nextInt(cpu.length);
				Process newProcess = new Process();
				int chooseCPU = rand.nextInt(cpu.length);
				int z_time = 1;
				if(cpu[rCPU].load < outcomeHOLD)
				{
					if(cpu[rCPU].load + newProcess.weight <= 100)
					{
						cpu[rCPU].processes.add(newProcess);
						cpu[rCPU].size++;
						cpu[rCPU].updateLoad();
					}
					ballastQuery++;
				}
				else{
					while(chooseCPU == rCPU || cpu[chooseCPU].load > outcomeHOLD )
					{
						chooseCPU = rand.nextInt(cpu.length);
						z_time++;
						ballastQuery++;
						if(z_time == (Z_time+100))
						{
							break;
						}
					}
					if(cpu[chooseCPU].load + newProcess.weight <= 100)
					{
						cpu[chooseCPU].processes.add(newProcess);
						cpu[chooseCPU].size++;
						cpu[chooseCPU].updateLoad();
						processMigration++;
					}
				}
			}
			int ballast = 0;
			for(int k = 0; k < cpu.length; k++)
			{
				ballast += cpu[k].load;
			}
			results1[i-1] = ballast/cpu.length;
			for(int k=0; k < cpu.length; k++){
				cpu[k].processes.clear();
				cpu[k].size = 0;
				cpu[k].updateLoad();
			}
		}
		outcome1[0] = ballastQuery;
		outcome1[1] = processMigration;
	}
	
	public void third()
	{
		int ballastQuery = 0;
		int processMigration = 0;
		for(int i=1; i <= maxTime; i++)
		{
			Random rand= new Random();
			//int liczbaProcesow = 1+rand.nextInt(500);
			for(int j=0; j < processesNumber; j++)
			{
				int rCPU = rand.nextInt(cpu.length);
				Process newProcess = new Process();
				int chooseCPU = rand.nextInt(cpu.length);
				int z_time = 1;
				if(cpu[rCPU].load < outcomeHOLD)
				{
					if(cpu[rCPU].load + newProcess.weight <= 100)
					{
						cpu[rCPU].processes.add(newProcess);
						cpu[rCPU].size++;
						cpu[rCPU].updateLoad();
						ballastQuery++;
					}
					if(cpu[rCPU].load < outcomeHOLD2)
					{
						while(cpu[chooseCPU].load < outcomeHOLD)
						{
							chooseCPU = rand.nextInt(cpu.length);
							z_time++;
							if(z_time == (Z_time + 10)){
								break;
							}
							ballastQuery++;
						}
						if(cpu[chooseCPU].load > outcomeHOLD)
						{
							int division = cpu[chooseCPU].processes.size()/2;
							for(int k=0; k < division; k++){
								cpu[rCPU].processes.add(cpu[chooseCPU].processes.get(0));
								cpu[chooseCPU].processes.remove(0);
								cpu[rCPU].size++;
								cpu[chooseCPU].size--;
								cpu[rCPU].updateLoad();
								cpu[chooseCPU].updateLoad();
								processMigration += division;
							}
						}
					}
				}
				else{
					while(chooseCPU == rCPU || cpu[chooseCPU].load > outcomeHOLD )
					{
						chooseCPU = rand.nextInt(cpu.length);
						z_time++;
						if(z_time == (Z_time + 20)){
							break;
						}
						ballastQuery++;
					}
					if(cpu[chooseCPU].load + newProcess.weight <= 100)
					{
						cpu[chooseCPU].processes.add(newProcess);
						cpu[chooseCPU].size++;
						cpu[chooseCPU].updateLoad();
						processMigration++;
					}
				}
			}
			if(i%3 == 0){
				for(int k=0; k < cpu.length; k++)
				{
					cpu[k].processes.remove(0);
					cpu[k].size--;
					cpu[k].updateLoad();
				}
			}
			int ballast = 0;
			for(int k=0; k < cpu.length; k++)
			{
				ballast += cpu[k].load;
			}
			results2[i-1] = ballast/cpu.length;
		}
		outcome2[0] = ballastQuery;
		outcome2[1] = processMigration*100;
	}
	public double ballast()
	{
		double ballast = 0;
		for(int i=0; i < results.length; i++)
		{
			ballast += results[i];
		}
		return ballast/results.length;
	}
	public double ballast1()
	{
		double ballast = 0;
		for(int i=0; i < results1.length; i++)
		{
			ballast += results1[i];
		}
		return ballast/results1.length;
	}
	public double ballast2()
	{
		double ballast = 0;
		for(int i=0; i < results2.length; i++)
		{
			ballast += results2[i];
		}
		return ballast/results2.length;
	}
}
