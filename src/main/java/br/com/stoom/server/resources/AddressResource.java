package br.com.stoom.server.resources;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.stoom.server.entities.Address;
import br.com.stoom.server.services.AddressService;
import br.com.stoom.server.services.exceptions.AddressNotFoundException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping(value = "/address", produces=MediaType.APPLICATION_JSON_VALUE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class AddressResource {

	AddressService addressService;
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/getAddresses")
	public ResponseEntity<List<Address>> getAddresses() {
		return ResponseEntity.status(HttpStatus.OK).body(addressService.findAll());
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/getAddress/{id}")
	public ResponseEntity<Address> getAddress(@PathVariable("id") Long id) {
		try {
			Address address = addressService.findById(id).orElse(null);
			if (address == null) throw new AddressNotFoundException("Endereço não foi encontrado.");
			new ResponseEntity<Address>(HttpStatus.OK);
			return ResponseEntity.ok(address);
		} catch (AddressNotFoundException err) {
			return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/save")
	public ResponseEntity<Address> saveAddress(@RequestBody Address address) {
		try {
			addressService.save(address);
			return new ResponseEntity<Address>(HttpStatus.CREATED);
		} catch (AddressNotFoundException err) {
			return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Address>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/update")
	public ResponseEntity<Address> updateAddress(@RequestBody Address address) {
		try {
			addressService.update(address);
			return new ResponseEntity<Address>(HttpStatus.OK);
		} catch (AddressNotFoundException err) {
			return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Address>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		addressService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
