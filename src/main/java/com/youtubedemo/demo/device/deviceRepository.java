package com.youtubedemo.demo.device;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public interface deviceRepository
        extends JpaRepository<device, Serializable>{

    @Query("Select s FROM device s WHERE s.brand = ?1 AND s.model =?2 AND s.osVersion =?3")
    Optional<device> findIfExists(String brand, String model, String osVersion);


    @Query("SELECT s FROM device s WHERE ((:brand is null or s.brand = :brand) " +
            "AND (:model is null or s.model = :model) " +
            "AND (:os is null or s.os = :os) " +
            "AND (:osVersion is null or s.osVersion = :osVersion))")
    List<device> findByEverything(@Param("brand") String brand,
                                  @Param("model") String model,
                                  @Param("os") String os,
                                  @Param("osVersion") String osVersion);

}
