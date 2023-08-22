package com.inventrymanagementsystem.ramzan.resource;

import com.inventrymanagementsystem.ramzan.model.Brand;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class BagsResource {

    private Long bagsId;

    @NotBlank(message = "Bags Type is Mandatory")
    private String bagsType;

    @NotBlank(message = "Storage Size is Mandatory")
    private int storage_size;
}
