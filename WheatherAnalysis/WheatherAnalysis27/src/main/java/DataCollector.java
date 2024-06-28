import java.io.IOException;
import java.net.URISyntaxException;

public class DataCollector extends Thread {
	
	private BigData data;
	
	private String name;
	private int startIndex;
	private int endIndex;
	
	public DataCollector(String name, int startIndex, int endIndex) throws URISyntaxException, IOException, InterruptedException {
		
		this.name = name;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		
		this.start();
	}
	
    public void run() {
    	
    	try {
			// Dados refentes as capitais selecionadas pelos índices
			data = new BigData(this.name, this.startIndex, this.endIndex);
			
			// Imprimindo dados das capitais
			for (Capital capital : data.capitals) {
	
				System.out.println("---------------" + this.name + "---------------");
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
    }
}