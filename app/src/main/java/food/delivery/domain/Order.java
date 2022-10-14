package food.delivery.domain;

import food.delivery.AppApplication;
import food.delivery.domain.OrderPlaced;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Order_table")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String menuId;

    private String address;

    private String customerId;

    @ElementCollection
    private List<String> options;

    @PostPersist
    public void onPostPersist() {
        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.publishAfterCommit();
    }

    public static OrderRepository repository() {
        OrderRepository orderRepository = AppApplication.applicationContext.getBean(
            OrderRepository.class
        );
        return orderRepository;
    }
}
