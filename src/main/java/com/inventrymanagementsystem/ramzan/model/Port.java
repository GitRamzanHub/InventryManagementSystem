package com.inventrymanagementsystem.ramzan.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inventrymanagementsystem.ramzan.dto.PortDTO;
import com.inventrymanagementsystem.ramzan.resource.PortResource;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
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

    //    @ManyToMany(mappedBy = "port", cascade = CascadeType.ALL)
    //    private List<Brand> brands = new ArrayList<>();
    @OneToMany(mappedBy = "port")
    @JsonIgnore
    private List<Brand> brands = new ArrayList<Brand>();

    // PortResouces to Port Entity
    public static Port toPortEntity(PortResource portResource) {
        return Port.builder()
                .port_id(portResource.getPort_id())
                .port_name(portResource.getPortName())
                .port_address(portResource.getPortAddress())
                .build();
    }

    // Port Entity to PortResource

//    public static PortResource toPortResource(Port port) {
//        return PortResource.builder()
//                .port_id(port.getPort_id())
//                .portName(port.getPort_name())
//                .portAddress(port.getPort_address())
//                .build();
//    }

    // Port Entity to PortDTO
    public static PortDTO toPortDto(Port port) {

        return PortDTO.builder()
                .portId(port.getPort_id())
                .portName(port.getPort_name())
                .portAddress(port.getPort_address())
                .brands(port.getBrands())
                .build();
    }

    // PortDTO to Port Entity
    public static Port toPortEntity(PortDTO portDTO){
        return Port.builder()
                .port_id(portDTO.getPortId())
                .port_name(portDTO.getPortName())
                .port_address(portDTO.getPortAddress())
                .brands(portDTO.getBrands())
                .build();
    }

//    public static List<PortResource> toPortResource(List<Port> port){
//        if(CollectionUtils.isEmpty(port)){
//            return new ArrayList<>();
//        }
//        else{
//
//            return port.stream().map(Port :: toPortResource).collect(Collectors.toList());
//        }
//    }
}
