package de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.controller;

import de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.controller.resource.OrderPositionResource;
import de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.controller.resource.OrderResource;
import de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.database.OrderDataDbo;
import de.arkem.clean.arc.refactoring.demo.legacy.garage.portal.database.OrderPositionDataDbo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;
@Component
public class OrderResourceToDboMapper {

    public OrderDataDbo mapResourceToDbo(OrderResource resource) {
        OrderDataDbo dbo = new OrderDataDbo();
        dbo.setOrderNumber(resource.getOrderNumber());
        dbo.setEditorId(resource.getEditorId());
        dbo.setCustomerName(resource.getCustomerName());
        dbo.setLastName(resource.getLastName());
        dbo.setStreet(resource.getStreet());
        dbo.setPostalCode(resource.getPostalCode());
        dbo.setCreationDate(resource.getCreationDate());
        dbo.setStartDate(resource.getStartDate());
        dbo.setEndDate(resource.getEndDate());
        if(resource.getOrderPositionResources() != null) {
            dbo.setOrderPositionDataDboList(resource.getOrderPositionResources().stream()
                    .map(r -> new OrderPositionDataDbo(r.getPositionId(), r.getPositionDescription(), r.getQuantity()))
                    .collect(Collectors.toList()));
        } else {
            dbo.setOrderPositionDataDboList(new ArrayList<>());
        }
        return dbo;
    }

    public OrderResource mapDboToResource(OrderDataDbo dbo) {
        OrderResource resource = new OrderResource();
        resource.setOrderNumber(dbo.getOrderNumber());
        resource.setEditorId(dbo.getEditorId());
        resource.setCustomerName(dbo.getCustomerName());
        resource.setLastName(dbo.getLastName());
        resource.setStreet(dbo.getStreet());
        resource.setPostalCode(dbo.getPostalCode());
        resource.setCreationDate(dbo.getCreationDate());
        resource.setStartDate(dbo.getStartDate());
        resource.setEndDate(dbo.getEndDate());
        if(dbo.getOrderPositionDataDboList() != null) {
            resource.setOrderPositionResources(dbo.getOrderPositionDataDboList().stream()
                    .map(d -> {
                        OrderPositionResource r = new OrderPositionResource();
                        r.setPositionDescription(d.getPositionDescription());
                        r.setQuantity(d.getQuantity());
                        return r;
                    })
                    .collect(Collectors.toList()));
        } else {
            resource.setOrderPositionResources(new ArrayList<>());
        }
        return resource;
    }
}
