package br.com.stoom.server.tests.util.builder;

import br.com.stoom.server.entities.Address;

public class AddressBuilder {
	private Long id;
	private String streetName;
	private Integer number;
	private String complement;
	private String neighbourhood;
	private String city;
	private String state;
	private String country;
	private String zipcode;
	private String latitude;
	private String longitude;
	
	public AddressBuilder setId(Long id) {
		this.id = id;
		return this;
	}
	
	public AddressBuilder setStreetName(String streetName) {
		this.streetName = streetName;
		return this;
	}
	
	public AddressBuilder setNumber(Integer number) {
		this.number = number;
		return this;
	}
	
	public AddressBuilder setComplement(String complement) {
		this.complement = complement;
		return this;
	}
	
	public AddressBuilder setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
		return this;
	}
	
	public AddressBuilder setCity(String city) {
		this.city = city;
		return this;
	}
	
	public AddressBuilder setState(String state) {
		this.state = state;
		return this;
	}
	
	public AddressBuilder setCountry(String country) {
		this.country = country;
		return this;
	}
	
	public AddressBuilder setZipcode(String zipcode) {
		this.zipcode = zipcode;
		return this;
	}
	
	public AddressBuilder setLatitude(String latitude) {
		this.latitude = latitude;
		return this;
	}
	
	public AddressBuilder setLongitude(String longitude) {
		this.longitude = longitude;
		return this;
	}
	
	public Address build() {
		return new Address(id, streetName, number, complement, neighbourhood, city, state, country, zipcode, latitude, longitude);
	}
}
