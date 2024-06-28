public class Main {
	
	static BigData data;
	
    public static void main(String args[]) {
        
    	// Soma total do tempo cronometrado (para fazer a média depois)
    	long sum = 0;
    	
    	// Repetição do programa em 10x
    	int c = 1;
    	
    	while (c <= 10) {
        	
    		// "Iniciando cronômetro"
    		long start = System.currentTimeMillis();
    		System.out.println("Iniciando rodada n" + c + " do programa com 9 threads!");
    		
        	try {
        		
        		// Todas as threads necessárias
        		
        		// OBS: Aqui não foi usado um laço de repetição para criar cada threads
        		// pois senão o procedimento seria levemente mais complicado, diferentemente
        		// da de 27 threads.
        		
        		DataCollector thread1 = new DataCollector("Thread1", 0, 2);
        		DataCollector thread2 = new DataCollector("Thread2", 3, 5);
        		DataCollector thread3 = new DataCollector("Thread3", 6, 8);
        		DataCollector thread4 = new DataCollector("Thread4", 9, 11);
        		DataCollector thread5 = new DataCollector("Thread5", 12, 14);
        		DataCollector thread6 = new DataCollector("Thread6", 15, 17);
        		DataCollector thread7 = new DataCollector("Thread7", 18, 20);
        		DataCollector thread8 = new DataCollector("Thread8", 21, 23);
        		DataCollector thread9 = new DataCollector("Thread9", 24, 26);
        		
        		thread1.join();
        		thread2.join();
        		thread3.join();
        		thread4.join();
        		thread5.join();
        		thread6.join();
        		thread7.join();
        		thread8.join();
        		thread9.join();
    			
    		} catch (Exception e) { e.printStackTrace(); }
        	
        	// "Parando crônometro"
        	long end = System.currentTimeMillis();
        	
        	System.out.println("Rodada n" + c + " finalizada em " + (end - start) + "ms!");
        	
        	// Adicionando valores à soma
        	sum += end - start;
        	c++;
    	}
    	
    	System.out.println("Todas as 10 rodadas foram finalizadas com sucesso!");
    	System.out.println("Tempo médio de execução para cada rodada: " + (sum / 10) + "ms");
    	
    }
}