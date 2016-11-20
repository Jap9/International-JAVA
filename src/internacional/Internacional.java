/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internacional;


public class Internacional implements Runnable 
{
	int[] DataThread;
	static int result = 0;
	
	public Internacional(int[] data){
		DataThread = data;
	}

	public int[] GetDataSumatorioThread(){
		return DataThread;
	}

	@Override
	public void run() 
	{
		int[] data = GetDataSumatorioThread();
		long sum = 0;

		for(int i = data[0]; i <= data[1]; i++){
			sum+=i;
		}
			result += sum;
	
	System.out.println("ThreadId: " + Thread.currentThread().getId()+", calcula desde "+data[0]+" hasta "+data[1]);
        System.out.println("Suma parcial: "+sum);
	}
	
	static public void main(String[] args) 
	{

		if(args.length != 2)
	        {
			System.out.println("Error en los parametros de entrada.");
			System.out.println("Uso: java -cp '.' SumatorioThreads [Max num] [Threads]");
			System.exit(-1);
	        }

		// Obtener argumentos y comprobar  que son validos
		int NUM_PRIME = Integer.parseInt(args[0]);
		int NUM_THREADS = Integer.parseInt(args[1]);
		int NUM_WORKLOAD = NUM_PRIME / NUM_THREADS;

		if(NUM_THREADS > NUM_PRIME){
			System.out.println("El numero de threads no puede ser mayor que el de primeros.");
			System.exit(-1);
		}

		System.out.println("Sumatorio de los " +NUM_PRIME+ " naturales usando "+NUM_THREADS+" hilos.");

		Thread[] threads = new Thread[NUM_THREADS];

		// Crear 'M' hilos y calcular el rango de trabajo
		for (int i = 0; i < NUM_THREADS; i++) {
			int inicio = 1 + (NUM_WORKLOAD * i);
			int fin = NUM_WORKLOAD + (NUM_WORKLOAD * i);
		
			if(i == (NUM_THREADS-1)) 
			{
				fin = NUM_PRIME;
			}

			int[] data = { inicio, fin };
			threads[i] = new Thread(new Internacional(data));
			threads[i].start();
		}

		for(int i=0; i<NUM_THREADS; i++) {
		    try {
			threads[i].join(); 
		    } catch(InterruptedException e) {
			e.printStackTrace();
		    }
		}
		System.out.println("Resultado: "+result);
                System.out.println("Final de la ejecución del programa.");
	}
}