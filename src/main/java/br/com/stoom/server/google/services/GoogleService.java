package br.com.stoom.server.google.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.stoom.server.entities.Address;
import br.com.stoom.server.services.exceptions.GoogleApiAddressNotFoundException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GoogleService {

	private String URL = "https://maps.googleapis.com/maps/api/geocode/json?";
	private String apiKey = "AIzaSyCj0cY2yEvVfYhAaTz3-P2MW-YRKmhz5Uw";
	
	public GoogleDTO findLatitudeAndLongitude(Address address) {
		String response = executeGoogleAPIRequest(address);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = null;

		try {
			rootNode = mapper.readTree(response);
		} catch (JsonProcessingException err) {
			throw new GoogleApiAddressNotFoundException();
		}

		JsonNode jsonNode = Optional.ofNullable(rootNode).orElseThrow(GoogleApiAddressNotFoundException::new);
		GoogleDTO googleDto = new GoogleDTO();
		googleDto.setLatitude(jsonNode.get("results").get(0).get("geometry").get("location").get("lat").asText());
		googleDto.setLongitute(jsonNode.get("results").get(0).get("geometry").get("location").get("lng").asText());

		return googleDto;
	}

	private String executeGoogleAPIRequest(Address address) {
			URI create = URI.create(createURL(address));
			var request = HttpRequest.newBuilder().uri(create).GET().build();
			var client = HttpClient.newHttpClient();
			HttpResponse<String> response = null;

			try {
				response = client.send(request, HttpResponse.BodyHandlers.ofString());
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}

			return response.body();
	}
	
	private String createURL(Address address) {
		StringBuffer sb = new StringBuffer();
		sb.append(URL);
		sb.append(createParams(address));
		sb.append(apiKey);

		return sb.toString();
	}
	
	private String createParams(Address address) {
		StringBuffer sb = new StringBuffer();
		sb.append("address=");
		sb.append(address.getStreetName());
		sb.append("+");
		sb.append(address.getNumber());
		sb.append("+");
		sb.append(address.getCity());
		sb.append("+");
		sb.append(address.getState());
		sb.append("+");
		sb.append(address.getCountry());
		sb.append("+");
		sb.append(address.getZipcode());
		sb.append("&key=");

		return sb.toString().replaceAll(" ", "+");
	}
}
