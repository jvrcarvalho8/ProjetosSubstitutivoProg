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
    		System.out.println("Iniciando rodada n" + c + " do programa sem threads");
    		
        	try {
    			data = new BigData(); // Todos os dados necessários sobre dados climáticos de cada região
    			
    			System.out.println("\nResultado:");
    			
    			// Imprimindo dados das capitais
    			for (Capital capital : data.capitals) {

    				System.out.println("------------------------------");
    				System.out.println(capital.getName() + ": \n");
    				
    				WheatherData d = capital.getData();
    				for (String day : d.getDailyAverage().time) {
    					
    					int i = Integer.parseInt(day);
    					
    					System.out.println("    Dia " + day + ":");
    					System.out.println("        Média: " + d.getDailyAverage().temperature_2m[i-1]);
    					System.out.println("        Mínima: " + d.getDaily().temperature_2m_min[i-1]);
    					System.out.println("        Máxima: " + d.getDaily().temperature_2m_max[i-1]);
    					System.out.println();
    					
    				}
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