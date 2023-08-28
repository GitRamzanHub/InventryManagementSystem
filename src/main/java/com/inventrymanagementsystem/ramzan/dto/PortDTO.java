package com.inventrymanagementsystem.ramzan.dto;

import com.inventrymanagementsystem.ramzan.model.Brand;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class PortDTO {

    private Long portId;

    private String portName;

    private String portAddress;

    private List<Brand> brands = new ArrayList<>();

}
