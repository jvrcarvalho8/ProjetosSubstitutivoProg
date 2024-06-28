import java.io.IOException;
import java.net.URISyntaxException;

public class BigData {
	
	// Todas as 27 capitais e seus dados climáticos
	public Capital[] capitals;
	
	public BigData() throws URISyntaxException, IOException, InterruptedException {
		
		capitals = new Capital[27];
		
		System.out.println("Coletando dados da API...");
		
		// Criando cada capital com base nas capitais em 'Locations'
		// ('Locations' possui os dados informados pelo PDF)
		for (int i = 0; i < capitals.length; i++) {
			
			System.out.println("Progresso: " + i + "/" + Locations.capitals.length);
			
			capitals[i] = new Capital(
					Locations.capitals[i],
					Locations.coords[i]
			);
			
			// Calculando a média, assim como deve
			capitals[i].getData().setDailyAverage();
		}
	}
}
