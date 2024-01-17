/*package tacos.kitchen.messaging.jms;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tacos.TacoOrder;
//import tacos.messaging.JmsOrderMessagingService;


@Component
public class JmsOrderReceiver implements OrderReceiver {
 private JmsTemplate jms;
 private MessageConverter converter;
 
 
 @Autowired
 public JmsOrderReceiver(JmsTemplate jms, MessageConverter converter) {
 this.jms = jms;
 this.converter = converter;
 }
 
 private static final Logger log = LoggerFactory.getLogger(JmsOrderMessagingService.class);
 
 /*public TacoOrder receiveOrder() {
 return (TacoOrder) jms.receiveAndConvert("tacocloud.order.queue");
 }*/
 
 /*public TacoOrder receiveOrder() {
     Message message = jms.receive("tacocloud.order.queue");
     try {
         if (message != null) {
             TacoOrder order = (TacoOrder) converter.fromMessage(message);
             log.info("Received taco order: {}", order);
             return order;
         } else {
             log.info("No message received");
         }
     } catch (JMSException e) {
         log.error("JMSException in receiveOrder", e);
     } catch (MessageConversionException e) {
         log.error("MessageConversionException in receiveOrder", e);
     }
     return null;
 }

}*/