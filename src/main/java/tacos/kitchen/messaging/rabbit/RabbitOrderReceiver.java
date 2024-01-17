/*package tacos.kitchen.messaging.rabbit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import tacos.TacoOrder;


@Component
public class RabbitOrderReceiver {
 
	private static final Logger log = LoggerFactory.getLogger(RabbitOrderReceiver.class);	
	
	private RabbitTemplate rabbit;
 private MessageConverter converter;

 @Autowired
 public RabbitOrderReceiver(RabbitTemplate rabbit) {
 this.rabbit = rabbit;
 this.converter = rabbit.getMessageConverter();
 }
 
 

 public TacoOrder receiveOrder() {
	    // Use receiveAndConvert with just the queue name
	    TacoOrder order = (TacoOrder) rabbit.receiveAndConvert("tacocloud.order.queue");
	    if (order != null) {
	        // Log the details of the received order
	        log.info("Order received: {}", order);
	       
	        log.info("Order ID: {}", order.getId());
	        log.info("Taco Names: {}", order.getTacoNames());
	        log.info("Delivery Name: {}", order.getDeliveryName());
	     
	    } else {
	        log.info("No order received.");
	    }
	    return order;
	}


}*/