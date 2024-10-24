package mixture.hutech.petstore.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "phone")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be numeric and exactly 10 digits")
    private String phone;
    @Column(name = "notes")
    private String notes;

    @NotNull
    @Column(name = "date_begin")
    private LocalDateTime dateBegin;

    @NotNull
    @Column(name = "date_end")
    private LocalDateTime dateEnd;

    @NotBlank
    @Column(name = "payment_methods")
    private String paymentMethods;

    @NotBlank
    @Column(name = "order_status")
    private String orderStatus;

    @NotNull
    @Column(name = "total_price")
    private Double totalPrice;

    @NotNull
    @Column(name = "total_shippingprice")
    private Double totalshippingprice;

    @NotBlank
    @Column(name = "address")
    private String address;


    @Column(name = "trading_code")
    private String tradingCode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "shipping_id")
    private District district;

    @JsonManagedReference
    @OneToMany(mappedBy = "cart")
    private List<Cart_Product> cartProducts;

    public List<Cart_Product> getCartProducts() {
        return cartProducts;
    }


}
