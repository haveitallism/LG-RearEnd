package com.group8.consumer;

import com.group8.dao.OrderDao;
import com.group8.entity.LgTourOrder;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MyConsumer {

    @Autowired
    OrderDao orderDao;

    @RabbitListener(queues = "queueadd")
    public void receiveQueueadd(LgTourOrder lgTourOrder, Message message, Channel channel) throws IOException {
        System.out.println("lgTourOuder"+lgTourOrder);
        orderDao.addOrder(lgTourOrder);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);


    }


}
