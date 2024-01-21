/*package tacos.messaging;
import jakarta.jms.Destination;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Session;
import tacos.TacoOrder;

import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




@Service
public class JmsOrderMessagingService implements OrderMessagingService {
    private static final Logger log = LoggerFactory.getLogger(JmsOrderMessagingService.class);
    private JmsTemplate jms;

    @Autowired
    public JmsOrderMessagingService(JmsTemplate jms) {
        this.jms = jms;
    }

   

    
    
   
    public void sendOrder(TacoOrder order) {
        // Log before sending the order
        log.info("Sending order with ID: {}", order.getId());
        jms.convertAndSend("tacocloud.order.queue", order, message -> {
            message.setStringProperty("X_ORDER_SOURCE", "WEB");
            // Log after setting the property
            log.info("Set X_ORDER_SOURCE property to 'WEB' for order with ID: {}", order.getId());
            return message;
        });
        // Log after the message has been sent
        log.info("Order with ID: {} has been sent to the queue", order.getId());
    }

}*/