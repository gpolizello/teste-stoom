package br.com.stoom.server.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.stoom.server.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

	Optional<Address> findById(Long id);
}
