/*package tacos.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tacos.TacoOrder;
import tacos.data.OrderRepository;
import tacos.messaging.OrderMessagingService; // Make sure this is the interface

@RestController
@RequestMapping(path="/api/orders", produces="application/json")
@CrossOrigin(origins="*")
public class OrderApiController {
    
    private static final Logger log = LoggerFactory.getLogger(OrderApiController.class);

    private OrderRepository repo;
    private OrderMessagingService kafkaOrderMessagingService; // Kafka messaging service

    @Autowired
    public OrderApiController(OrderRepository repo, OrderMessagingService kafkaOrderMessagingService) { 
        this.repo = repo;
        this.kafkaOrderMessagingService = kafkaOrderMessagingService;
    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public TacoOrder postOrder(@RequestBody TacoOrder order) {
        log.info("Received taco order: {}", order);
        kafkaOrderMessagingService.sendOrder(order); // Send order using Kafka
        return repo.save(order);
    }

    //!!! receiveOrder method is not used with Kafka as it uses only Listeners
}
*/