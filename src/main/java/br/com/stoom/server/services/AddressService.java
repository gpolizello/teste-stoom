package br.com.stoom.server.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.stoom.server.entities.Address;
import br.com.stoom.server.google.services.GoogleDTO;
import br.com.stoom.server.google.services.GoogleService;
import br.com.stoom.server.repositories.AddressRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class AddressService {

	AddressRepository addressRepository;
	GoogleService googleService;
	
	public Address save(Address address) {
		fillLatitudeAndLongitude(address);
		
		addressRepository.save(address);
		return address;
	}
	
	public List<Address> findAll() {
		return addressRepository.findAll();
	}
	
	public Address findById(Long id) {
		Address address = addressRepository.getById(id);
		return address;
	}
	
	public Address update(Address updateAddress) {
		Address address = this.findById(updateAddress.getId());
		fillLatitudeAndLongitude(address);
        return addressRepository.save(address);
	}

	private void fillLatitudeAndLongitude(Address address) {
		if ((address.getLatitude() == null || address.getLatitude().isBlank()) || 
				(address.getLongitude() == null || address.getLongitude().isBlank())) {
			GoogleDTO googleDTO = googleService.findLatitudeAndLongitude(address);
			address.setLatitude(googleDTO.getLatitude());
			address.setLongitude(googleDTO.getLongitute());
		}
	}
	
	public void delete(Long id) {
		Address address = this.findById(id);
		addressRepository.delete(address);
	}
}
