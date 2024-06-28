
// Essa classe representará nossa resposta da API, com umas coisas a mais e a menos
public class WheatherData { 

	private Hourly hourly;             	// Temperatura em determinada hora em um determinado dia
	private Daily daily;				// Temperaturas máximas e mínimas de cada dia
	private DailyAverage dailyAverage;  // Média da temperatura de cada dia
	
	public class Daily {
		public String[] time;
		public float[] temperature_2m_max;
		public float[] temperature_2m_min;
	}

	public class Hourly {
		public String[] time;
		public float[] temperature_2m;
	}
	
	public class DailyAverage {
		public String[] time;
		public float[] temperature_2m;
	}

	public Hourly getHourly() {
		return this.hourly;
	}

	public Daily getDaily() {
		return this.daily;
	}
	
	// Essa função tem que ser chamada imediatamente após um objeto ser criado
	// Ela serve para definir a média de temperatura para cada dia (pois a API em si não retorna a média)
	public void setDailyAverage() {
		
		// O motivo desta função existir, ao invés de um método construtor, é que objetos formados
		// através de uma "incorporação" de um json não podem possuir métodos construtores
		
		dailyAverage = new DailyAverage();
		
		dailyAverage.time = new String[31];
		dailyAverage.temperature_2m = new float[31];
		
		float sum = 0;
		
		int currentDay = 1;
		int counter = 0;
		
		// De hora em hora iremos acrescentar a temperatura à soma
		for (String time : hourly.time) {

			// O dado retornado em 'time' será algo como: '2024-01-02T02:00'
			// Ou seja: 'ANO-MÊS-DIATHORA'
			// Queremos o 'DIA', por isso iremos:
			// Separar tudo por '-', ficando: [0: ANO, 1: MÊS, 2: DIATHORA]
			// Pegar o terceiro elemento (index 2) e separá-lo por 'T'
			// Ficando: [0: DIA, 1: HORA]. E vamos guardar o primeiro
			// elemento (index 0) no valor 'day'
			int day = Integer.parseInt(time.split("-")[2].split("T")[0]);
			
			// Se ainda estivermos vendo o mesmo dia, vamos continuar acescentando à soma
			if (day == currentDay) {
				
				sum += hourly.temperature_2m[counter];
					
			} 
			
			// Se o dia virou, vamos guardar a média daquele dia que acabou de virar
			else {
				
				// Usando 'String.format' para garantir duas casas decimais, assim '1' ficará como '01', '2' como '02' etc. 
				dailyAverage.time[currentDay - 1] = String.format("%02d", currentDay);
				
				// Usando 'String.format' para impedir que o valor da média da temperatura tenha mais de duas casas decimais ao calculá-la 
				dailyAverage.temperature_2m[currentDay - 1] = Float.parseFloat(String.format("%.2f", sum / 24f).replace(",", "."));
				sum = 0;
				
			}
			
			currentDay = day;
			counter++;
			
		}
		
		// O último dia não pode ser ignorado
		dailyAverage.time[currentDay - 1] = String.format("%02d", currentDay);
		dailyAverage.temperature_2m[currentDay - 1] = Float.parseFloat(String.format("%.2f", sum / 24f).replace(",", "."));
		
	}
	
	public DailyAverage getDailyAverage() {
		return this.dailyAverage;
	}
	
}

