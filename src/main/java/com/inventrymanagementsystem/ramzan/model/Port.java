package com.inventrymanagementsystem.ramzan.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inventrymanagementsystem.ramzan.resource.PortResource;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ports")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Port {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long port_id;

    private String port_name;

    private String port_address;

    @CreationTimestamp
    private Date incomingStockDate;

    private Date outgoingStockDate;

    @OneToMany(mappedBy = "port", cascade = CascadeType.ALL)
    private List<Brand> brands;


    // PortResouces to Port Entity
    public static Port toPortEntity(PortResource portResource){
        return Port.builder()
                .port_name(portResource.getPortName())
                .port_address(portResource.getPortAddress())
                .build();
    }

    // Port Entity to PortResource

    public static PortResource toPortResouce(Port port){
        return PortResource.builder()
                .portName(port.getPort_name())
                .portAddress(port.getPort_address())
                .incomingStockDate(port.getIncomingStockDate())
                .outgoingStockDate(port.getOutgoingStockDate())
                .build();
    }
}
