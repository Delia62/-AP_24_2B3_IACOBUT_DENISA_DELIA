package org.example.repositories;

import org.example.models.PublishingHouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublishingHouseRepository extends JpaRepository<PublishingHouse, Long> {
    List<PublishingHouse> findByName(String name);
}
