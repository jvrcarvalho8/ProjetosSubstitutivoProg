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
    		System.out.println("Iniciando rodada n" + c + " do programa com 3 threads!");
    		
        	try {
        		
        		DataCollector thread1 = new DataCollector("Thread1", 0, 8);
        		DataCollector thread2 = new DataCollector("Thread2", 9, 17);
        		DataCollector thread3 = new DataCollector("Thread3", 18, 26);
        		
        		thread1.join();
        		thread2.join();
        		thread3.join();
    			
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