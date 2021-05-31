package br.com.stoom.server.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	Long id;
	
	@Column(name = "STREETNAME")
	@NotBlank
	String streetName;
	
	@Column(name = "NUMBER")
	@NotNull
	Integer number;
	
	@Column(name = "COMPLEMENT")
	String complement;
	
	@Column(name = "NEIGHBOURHOOD")
	@NotBlank
	String neighbourhood;
	
	@Column(name = "CITY")
	@NotBlank
	String city;
	
	@Column(name = "STATE")
	@NotBlank
	String state;
	
	@Column(name = "COUNTRY")
	@NotBlank
	String country;
	
	@Column(name = "ZIPCODE")
	@NotBlank
	String zipcode;
	
	@Column(name = "LATITUDE")
	String latitude;
	
	@Column(name = "LONGITUDE")
	String longitude;
}
