package com.UberReviewService.repositories;

import com.UberReviewService.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    Optional<Driver> findByIdAndLicenseNumber(Long id, String licenseNumber);

    @Query(nativeQuery = true,value = "SELECT * FROM Driver WHERE id=:id AND license_number=:license")
    Optional<Driver> rawfindByIdAndLicenseNumber(Long id, String license);

    @Query( "SELECT d FROM Driver d WHERE d.id=:id AND d.licenseNumber=:ln")
    Optional<Driver> hqlfindByIdAndLicenseNumber(Long id, String ln);
}
