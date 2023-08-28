package com.inventrymanagementsystem.ramzan.resource;

import com.inventrymanagementsystem.ramzan.model.Brand;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class PortResource {

    private Long port_id;

    @NotBlank(message = "Port Name Should not Blank")
    private String portName;

    @NotBlank(message = "Port Address Should not Blank")
    private String portAddress;

    private List<Brand> brands;


}
