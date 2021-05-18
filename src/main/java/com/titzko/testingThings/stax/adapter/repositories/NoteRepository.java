package com.titzko.testingThings.stax.adapter.repositories;

import com.titzko.testingThings.stax.application.model.Example;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Example,Long> {
}
