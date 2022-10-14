package food.delivery.domain;

import food.delivery.StoreApplication;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Cooking_table")
@Data
public class Cooking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long orderId;

    @ElementCollection
    private List<String> options;

    private String status;

    @PostPersist
    public void onPostPersist() {}

    public static CookingRepository repository() {
        CookingRepository cookingRepository = StoreApplication.applicationContext.getBean(
            CookingRepository.class
        );
        return cookingRepository;
    }

    public void accept(AcceptCommand acceptCommand) {
        if(acceptCommand.getAcceptOrNot()){
            Accepted accepted = new Accepted(this);
            accepted.publishAfterCommit();
            
            setStatus("ACCEPTED");
    
        }else{
            Rejected rejected = new Rejected(this);
            rejected.setRejectionReason(acceptCommand.getRejectionReason());
            rejected.publishAfterCommit();

            setStatus("REJECTED");

        }

    }

    public void finish() {
        Cooked cooked = new Cooked(this);
        cooked.publishAfterCommit();

        setStatus("COOKED");

    }

    public static void loadToOrderList(OrderPlaced orderPlaced) {
        /** Example 1:  new item         */

        Cooking cooking = new Cooking();

        cooking.setOrderId(orderPlaced.getId());
        cooking.setStatus("READY");
        cooking.setOptions(orderPlaced.getOptions());

        repository().save(cooking);


        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.get???()).ifPresent(cooking->{
            
            cooking // do something
            repository().save(cooking);


         });
        */

    }
}
