import java.io.IOException;
import java.net.URISyntaxException;

public class BigData {
	
	// Todas as 27 capitais e seus dados climáticos
	public Capital[] capitals;
	
	// Índices inclusivos? Sim.
	// Se pedir pra pegar do 0 ao 3, vai pegar: 0, 1, 2, 3.
	public BigData(String thread, int startIndex, int endIndex) throws URISyntaxException, IOException, InterruptedException {
		
		capitals = new Capital[(endIndex - startIndex) + 1];
		
		System.out.println(thread + ": coletando dados da API...");
		
		// Criando cada capital com base nas capitais em 'Locations'
		// ('Locations' possui os dados informados pelo PDF)
		for (int i = 0; i < capitals.length; i++) {
			
			int newI = i + startIndex;
			
			System.out.println(thread + ": (progresso: " + i + "/" + capitals.length + ")");
			
			capitals[i] = new Capital(
					Locations.capitals[newI],
					Locations.coords[newI]
			);
			
			// Calculando a média, assim como deve
			capitals[i].getData().setDailyAverage();
			
		}
	}
}
