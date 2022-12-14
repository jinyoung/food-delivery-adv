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

    private String status;

    private String reason;

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

    public static void updateStatus(Accepted accepted) {
        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        */

        /** Example 2:  finding and process     */
        
        repository().findById(accepted.getOrderId()).ifPresent(order->{
            
            order.setStatus("ACCEPTED"); // do something
            repository().save(order);


         });
   

    }

    public static void updateStatus(Rejected rejected) {

        repository().findById(rejected.getOrderId()).ifPresent(order->{
            
            order.setStatus("REJECTED"); // do something
            order.setReason(rejected.getRejectionReason());
            repository().save(order);


         });
   
    }

    public static void updateStatus(Cooked cooked) {
        repository().findById(cooked.getOrderId()).ifPresent(order->{
            
            order.setStatus("COOKED"); // do something
            repository().save(order);


         });

    }
}
