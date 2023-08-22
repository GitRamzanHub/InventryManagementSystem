package com.inventrymanagementsystem.ramzan.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inventrymanagementsystem.ramzan.resource.BagsResource;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "bags")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Bags {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bags_id;

    private String bagsType;

    private int storage_size;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    @JsonIgnore
    private Brand brand;

    // BagsResource to Bags
    public static Bags toBagsEntity(BagsResource bagsResource){
        return Bags.builder()
                .bagsType(bagsResource.getBagsType())
                .storage_size(bagsResource.getStorage_size())
                .build();
    }

    // Bags to BagsResource
    public static BagsResource toBagsResource(Bags bags){
            return BagsResource.builder()
                    .bagsId(bags.getBags_id())
                    .bagsType(bags.getBagsType())
                    .storage_size(bags.getStorage_size())
                    .build();
    }

}
