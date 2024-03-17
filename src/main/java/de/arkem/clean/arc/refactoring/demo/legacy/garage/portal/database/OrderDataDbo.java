package de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.database;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "ord_table")
public class OrderDataDbo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String orderNumber;
    private String editorId;
    private String vehicleId;
    private String licensePlate;
    private double mileage;
    private String customerName;
    private String lastName;
    private String street;
    private String postalCode;
    private LocalDate creationDate;
    private LocalDate startDate;
    private LocalDate endDate;

    @ElementCollection
    @CollectionTable(name = "order_positions")
    private List<OrderPositionDataDbo> orderPositionDataDboList;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getEditorId() {
        return editorId;
    }

    public void setEditorId(String editorId) {
        this.editorId = editorId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<OrderPositionDataDbo> getOrderPositionDataDboList() {
        return orderPositionDataDboList;
    }

    public void setOrderPositionDataDboList(List<OrderPositionDataDbo> orderPositionDataDboList) {
        this.orderPositionDataDboList = orderPositionDataDboList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
