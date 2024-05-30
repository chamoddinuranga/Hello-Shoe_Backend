package lk.helloshoe.hsplbackend.entity;

import lk.helloshoe.hsplbackend.Enum.Category;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Supplier")
@Data
public class SupplierEntity {

    @Id
    private String supplierCode;
    private String supplierName;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String postalCode;
    private String country;
    private String contactNo1;
    private String contactNo2;
    private String email;
    @OneToMany(mappedBy = "supplierEntity",cascade = CascadeType.ALL)
    private List<StockEntity> stockEntities;
}
