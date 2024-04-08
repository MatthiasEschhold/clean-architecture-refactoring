package de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.database;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

//rearc
//eliminate the vehicle and customer properties, that are parts of the new domain model of vehicle and customer
//reference the vehicle and customer by their id (ddd aggregate design rules)
@Entity(name = "ord_table")
public class OrderDataDbo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String orderNumber;
    private String editorId;
    private String vehicleId;
    private int customerId;
    private LocalDate creationDate;
    private LocalDate startDate;
    private LocalDate endDate;
    @ElementCollection
    @CollectionTable(name = "order_positions")
    private List<OrderPositionDataDbo> orderPositionDataDboList;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
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
}
