package com.inventrymanagementsystem.ramzan.resource;

import com.inventrymanagementsystem.ramzan.model.Bags;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

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

    @CreationTimestamp
    private Date incomingStockDate;

    @NotBlank(message = "Number Of Bags Should not be Empty")
    private int numberOfBags;

    @NotBlank(message = "Qty Should not Empty")
    private double quantity;

    private BagsResource bags;
}
