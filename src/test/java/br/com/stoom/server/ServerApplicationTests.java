package br.com.stoom.server;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.stoom.server.entities.Address;
import br.com.stoom.server.repositories.AddressRepository;
import br.com.stoom.server.services.AddressService;
import br.com.stoom.server.tests.util.builder.AddressBuilder;

@SpringBootTest
@RunWith(SpringRunner.class)
class ServerApplicationTests {
	
	@Autowired
	private AddressService addressService;
	
	@MockBean
	private AddressRepository addressRepository;
	
	private Address address1;
	private Address address2;
	private Address address3;
	private Address address4;
	private List<Address> addressList;
	
	@BeforeEach
	public void setup() {
		address1 = new AddressBuilder().setId(1L)
				.setStreetName("Rua Senador José Bonifácio")
				.setNumber(120)
				.setComplement("Casa 2")
				.setNeighbourhood("Centro")
				.setCity("Mogi-Mirim")
				.setState("São Paulo")
				.setCountry("Brasil")
				.setZipcode("13800-970")
				.setLatitude("-22.4323512")
				.setLongitude("-46.9566159")
				.build();
				
		address2 = new AddressBuilder().setId(2L)
				.setStreetName("Rua José Mathias")
				.setNumber(499)
				.setNeighbourhood("Tucura")
				.setCity("Mogi-Mirim")
				.setState("São Paulo")
				.setCountry("Brasil")
				.setZipcode("13807-020")
				.setLatitude("-22.4203812")
				.setLongitude("-46.9570878")
				.build();
		
		address3 = new AddressBuilder().setId(3L)
				.setStreetName("Rua Bandeirantes")
				.setNumber(65)
				.setNeighbourhood("Jardim Sumaré")
				.setCity("Araçatuba")
				.setState("São Paulo")
				.setCountry("Brasil")
				.setZipcode("16015-250")
				.setLatitude("-21.2019623")
				.setLongitude("-50.4357623")
				.build();
		
		address4 = new AddressBuilder().setId(4L)
				.setStreetName("Rua Ângelo Martins Melero")
				.setNumber(409)
				.setNeighbourhood("Caneleira")
				.setCity("Santos")
				.setState("São Paulo")
				.setCountry("Brasil")
				.setZipcode("11085-580")
				.setLatitude("-23.9439643")
				.setLongitude("-46.3663001")
				.build();
		
		addressList = new ArrayList<>();
		addressList.add(address1);
		addressList.add(address2);
		addressList.add(address3);
		addressList.add(address4);
	}
	
	@Test
	public void deveRetornarTodosOsEnderecos(){
		when(addressService.findAll()).thenReturn(addressList);

		List<Address> addressListResult = addressService.findAll();
		Assert.assertNotNull(addressListResult);
		Assert.assertEquals(4, addressListResult.size());
	}

	@Test
	public void deveRetornarEnderecoPeloId(){
		Long id = 1L;
		when(addressService.findById(id)).thenReturn(Optional.ofNullable(address1));

		Address addressResult = addressService.findById(id).orElse(null);
		Assert.assertNotNull(addressResult);
		Assert.assertEquals(id, addressResult.getId());
		Assert.assertEquals("Rua Senador José Bonifácio", addressResult.getStreetName());
	}

	@Test
	public void deveAdicionarUmNovoEndereco(){
		Address newAddress = address4;
		when(addressService.save(newAddress)).thenReturn(address4);

		newAddress.setLongitude(null);
		newAddress.setLatitude(null);
		Address addressResult = addressService.save(newAddress);

		Assert.assertNotNull(addressResult);
		Assert.assertEquals("-23.9439643", addressResult.getLatitude());
		Assert.assertEquals("-46.3663001", addressResult.getLongitude());
	}

	@Test
	public void deveAtualizarEndereco(){
		Long id = 3L;
		Address newAddress = address3;
		when(addressService.findById(id)).thenReturn(Optional.ofNullable(address3));
		when(addressService.update(newAddress)).thenReturn(address3);

		newAddress.setLongitude(null);
		newAddress.setLatitude(null);
		Address addressResult = addressService.update(newAddress);

		Assert.assertNotNull(addressResult);
		Assert.assertEquals("Rua Bandeirantes", addressResult.getStreetName());
		Assert.assertEquals("São Paulo", addressResult.getState());
	}

	@Test
	public void deveDeletarEndereco(){
		Long id = 2L;
		when(addressService.findById(id)).thenReturn(Optional.ofNullable(address2));

		addressService.delete(id);
		verify(addressRepository, times(0)).deleteById(id);
	}

	@Test
	void contextLoads() {
	}

}
