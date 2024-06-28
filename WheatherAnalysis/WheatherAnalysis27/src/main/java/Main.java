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
    		System.out.println("Iniciando rodada n" + c + " do programa com 27 threads!");
    		
        	try {

        		// Todas as threads que serão executadas
        		DataCollector[] collectors = new DataCollector[27];
        		
        		// Iniciando 27 threads para cada capital
        		for (int i = 0; i < collectors.length; i++) {
        			
        			collectors[i] = new DataCollector("Thread" + i, i, i);
        					
        		}
        		
        		// Esperando cada uma delas terminar de executar
        		for (int i = 0; i < collectors.length; i++) {
        			
        			collectors[i].join();
        			
        		}
    			
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