import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;

public class Capital {
	
	private String name;
	
	// Guardando todos os dados climáticos de tal capital
	private WheatherData data;
	
	private double lat; // latitude
	private double lon; // longitude
	
	public Capital(String name, double[] latAndLon) throws URISyntaxException, IOException, InterruptedException {
		
		this.name = name;
		this.lat = latAndLon[0];
		this.lon = latAndLon[1];
		
		// URL para consultar a API
		// Substituimos a latitude e longitude pela as da capital na URL, para achar os dados climáticos dela
		URI uri = new URI("https://historical-forecast-api.open-meteo.com/v1/forecast?latitude=" + lat + "&longitude=" + lon
        		+ "&start_date=2024-01-01&end_date=2024-01-31&hourly=temperature_2m&daily=temperature_2m_max,temperature_2m_min&timezone=America%2FSao_Paulo"); 
        
		// Montando uma request bem básica
		// (A API não exigia um JSON como request)
        HttpRequest getRequest = HttpRequest.newBuilder()
            .uri(uri)
            .GET()
            .build();
        
        // Gerando um cliente para acessar a API
        HttpClient httpClient = HttpClient.newHttpClient();
        
        // Resposta gerada pela API
        HttpResponse<String> response = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        
        // Classe para fazer conversões de JSON para Objeto e vice-versa
        Gson parser = new Gson();
        
        // Serializando a resposta JSON em um objeto WheatherData
        this.data = parser.fromJson(response.body(), WheatherData.class);
	}
	
	public double getLat() { return lat; }

	public double getLon() { return lon; }

	public String getName() { return name; }

	public WheatherData getData() { return data; }

}
