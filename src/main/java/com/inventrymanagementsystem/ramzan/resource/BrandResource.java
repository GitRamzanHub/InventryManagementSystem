package com.inventrymanagementsystem.ramzan.resource;

import com.inventrymanagementsystem.ramzan.model.Port;
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
public class BrandResource {

    private Long brandId;

    @NotBlank(message = "Brand Name Should not be Empty")
    private String brandName;

    @NotBlank(message = "Item Name Should not be Empty")
    private String item; // (name of the rice)

    @NotBlank(message = "Number Of Bags Should not be Empty")
    private double numberOfBags;

    @CreationTimestamp
    private Date incomingStockDate;

    private Date outgoingStockDate;

    private double bagKg;

    private String bagType; // (PP, BOPP)

    @NotBlank(message = "Qty Should not Empty")
    private double quantity;

    private List<PortResource> ports;
}
