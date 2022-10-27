package food.delivery.domain;

import food.delivery.domain.*;
import io.eventuate.tram.events.common.DomainEvent;
import java.util.*;
//import food.delivery.infra.AbstractEvent;
import lombok.*;

@Data
@ToString
public class OrderPlaced implements DomainEvent {

    private Long id;
    private String menuId;
    private String address;
    private String customerId;
    private Object options;
    private String status;
    private String reason;
}
