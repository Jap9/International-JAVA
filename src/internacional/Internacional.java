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
                        
	System.out.println(java.text.MessageFormat.format(java.util.ResourceBundle.getBundle("internacional/Bundle").getString("THREADID: {0}, CALCULA DESDE {1} HASTA {2}"), Thread.currentThread().getId(), data[0], data[1]));
        System.out.println(java.util.ResourceBundle.getBundle("internacional/Bundle").getString("SUMA PARCIAL: ")+sum);
	}
	
	static public void main(String[] args) 
	{

		if(args.length != 2)
	        {
			System.out.println(java.util.ResourceBundle.getBundle("internacional/Bundle").getString("ERROR EN LOS PARAMETROS DE ENTRADA."));
			System.out.println(java.util.ResourceBundle.getBundle("internacional/Bundle").getString("USO: JAVA -CP '.' SUMATORIOTHREADS [MAX NUM] [THREADS]"));
			System.exit(-1);
	        }

		// Obtener argumentos y comprobar  que son validos
		int NUM_PRIME = Integer.parseInt(args[0]);
		int NUM_THREADS = Integer.parseInt(args[1]);
		int NUM_WORKLOAD = NUM_PRIME / NUM_THREADS;

		if(NUM_THREADS > NUM_PRIME){
			System.out.println(java.util.ResourceBundle.getBundle("internacional/Bundle").getString("EL NUMERO DE THREADS NO PUEDE SER MAYOR QUE EL DE PRIMEROS."));
			System.exit(-1);
		}

		System.out.println(java.util.ResourceBundle.getBundle("internacional/Bundle").getString("SUMATORIO DE LOS ") +NUM_PRIME+ java.util.ResourceBundle.getBundle("internacional/Bundle").getString(" NATURALES USANDO ")+NUM_THREADS+java.util.ResourceBundle.getBundle("internacional/Bundle").getString(" HILOS."));

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
		System.out.println(java.util.ResourceBundle.getBundle("internacional/Bundle").getString("RESULTADO: ")+result);
                System.out.println(java.util.ResourceBundle.getBundle("internacional/Bundle").getString("FINAL DE LA EJECUCIÓN DEL PROGRAMA."));
	}
}