package com.titzko.testingThings.stax.adapter.repositories;

import com.titzko.testingThings.stax.application.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long > {
}
