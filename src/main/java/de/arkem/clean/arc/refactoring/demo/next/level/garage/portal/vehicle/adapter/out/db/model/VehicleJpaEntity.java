package de.arkem.clean.arc.refactoring.demo.next.level.garage.portal.vehicle.adapter.out.db.model;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "vehicle_tbl")
public class VehicleJpaEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String vin;
    @Column(unique = true)
    private String licensePlate;

    @ElementCollection
    @CollectionTable(name = "mileage_records_tbl")
    private List<MileageRecordJpaEntity> mileageRecords;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public List<MileageRecordJpaEntity> getMileageRecords() {
        return mileageRecords;
    }

    public void setMileageRecords(List<MileageRecordJpaEntity> mileageRecords) {
        this.mileageRecords = mileageRecords;
    }
}
