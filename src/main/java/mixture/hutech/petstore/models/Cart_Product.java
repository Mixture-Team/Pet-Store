package mixture.hutech.petstore.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "_cart_product")
public class Cart_Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;
    private double total_price;

    @ManyToOne
    @JoinColumn(name ="product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name ="service_detail_id")
    private Service_Detail serviceDetail;


    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;
}
