package com.tdi.sensorservice.repository;

import com.tdi.sensorservice.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface SensorRepository extends JpaRepository<Sensor, UUID> {

    @Query(value = """
        SELECT * FROM sensor
        WHERE search_vector @@ TO_TSQUERY('english', :query)
        ORDER BY TS_RANK(search_vector, TO_TSQUERY('english', :query)) DESC
        """, nativeQuery = true)
    List<Sensor> fullTextSearch(@Param("query") String query);

}