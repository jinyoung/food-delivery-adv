package food.delivery.infra;

import food.delivery.domain.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Configuration
public class PolicyHandler {

    @Bean
    public DomainEventDispatcher domainEventDispatcher(
        DomainEventDispatcherFactory domainEventDispatcherFactory
    ) {
        return domainEventDispatcherFactory.make(
            "OrderEvents",
            DomainEventHandlersBuilder
                .forAggregateType("Order")
                .onEvent(
                    OrderPlaced.class,
                    PolicyHandler::wheneverOrderPlaced_LoadToOrderList
                )
                .build()
        );
    }

    @Autowired
    CookingRepository cookingRepository;

    public void whatever(@Payload String eventString) {}

    public void wheneverOrderPlaced_LoadToOrderList(
        @Payload OrderPlaced orderPlaced
    ) {
        OrderPlaced event = orderPlaced;
        System.out.println(
            "\n\n##### listener LoadToOrderList : " + orderPlaced + "\n\n"
        );

        // Sample Logic //
        Cooking.loadToOrderList(event);
    }
}
