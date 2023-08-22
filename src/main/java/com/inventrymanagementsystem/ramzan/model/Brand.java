package com.inventrymanagementsystem.ramzan.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inventrymanagementsystem.ramzan.resource.BrandResource;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "brand")
@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long brand_id;

    private String brandName;

    private String item; // (name of the rice)

    private int numberOfBags;

    private double quantity;

    @CreationTimestamp
    private Date incomingStockDate;

    private Date outgoingStockDate;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Bags> bags = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "port_id")
    private Port port;





    // BrandResource to Brand Entity
    public static Brand toBrandEntity(BrandResource brandResource){

        return Brand.builder()
                .brandName(brandResource.getBrandName())
                .item(brandResource.getItem())
                .incomingStockDate(brandResource.getIncomingStockDate())
                .numberOfBags(brandResource.getNumberOfBags())
                .quantity(brandResource.getQuantity())
                .build();
    }

    // Brand Entity to Brand Resources
    // todo with brand & brand resources

    public static BrandResource toBrand(Brand brand){
        return BrandResource.builder()
                .brandName(brand.getBrandName())
                .item(brand.getItem())
                .numberOfBags(brand.getNumberOfBags())
                .quantity(brand.getQuantity())
                .incomingStockDate(brand.getIncomingStockDate())
                .build();

    }


}
