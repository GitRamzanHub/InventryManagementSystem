package com.inventrymanagementsystem.ramzan.dto;

import com.inventrymanagementsystem.ramzan.model.Port;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class BrandDTO {
    private Long brandId;

    @UniqueElements
    @NotBlank(message = "brand Name shouldn't blank.")
    private String brandName;

    private String item;

    private double numberOfBags;

    @CreationTimestamp
    private Date incomingStockDate;

    @UpdateTimestamp
    private Date outgoingStockDate;

    private double bagKg;

    private String bagType;

    private double quantity;

    private Port port = new Port();
}
