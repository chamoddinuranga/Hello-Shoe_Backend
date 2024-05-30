package lk.helloshoe.hsplbackend.entity;

import lk.helloshoe.hsplbackend.Enum.Gender;
import lk.helloshoe.hsplbackend.Enum.Level;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "customer")
@Data
public class CustomerEntity {

    @Id
    private String customerId;

    private String customerName;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Level level;

    @Temporal(TemporalType.DATE)
    private Date joinDate;
    private Integer totalPoint;

    @Temporal(TemporalType.DATE)
    private Date dob;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String postalCode;
    private String contactNo;
    private String email;
    private Timestamp recentPurchasedDate;

    @OneToMany(mappedBy = "customerEntity",cascade = CascadeType.ALL)
    private List<OrderEntity> orderEntities;

}
