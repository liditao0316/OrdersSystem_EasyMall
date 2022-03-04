package easymall.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import easymall.po.Orders;
import easymall.po.RabbitmqData;
import easymall.service.OrderService;
import lombok.SneakyThrows;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Component
public class TestServiceImpl implements ChannelAwareMessageListener {

    @Autowired
    private OrderService orderService;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        MessageProperties messageProperties = message.getMessageProperties();
        try {
            String msg = new String(message.getBody(), Charset.defaultCharset());

            //TODO 接收到消息业务处理
            String json = "下单成功"; //业务返回的结果
            System.out.println(message);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            ObjectMapper objectMapper = new ObjectMapper();
            RabbitmqData rabbitmqData = objectMapper.readValue(message.getBody(), RabbitmqData.class);
            System.out.println(rabbitmqData);
            String orderId=UUID.randomUUID().toString();
            SimpleDateFormat df =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time=df.format(new Date());
            Timestamp timeStamp= Timestamp.valueOf(time);
            Orders myOrder = new Orders(orderId,0.0,rabbitmqData.getReceiverinfo(),0,timeStamp, rabbitmqData.getUser().getId(),0,0);
            orderService.addOrder(rabbitmqData.getCartIds(),myOrder);
            AMQP.BasicProperties props = new AMQP.BasicProperties
                    .Builder()
                    .correlationId(messageProperties.getCorrelationId()) //重要
                    .build();
            String replyTo = messageProperties.getReplyTo(); //重要
            channel.basicPublish("", replyTo, props, json.getBytes(Charset.defaultCharset())); //这里的交换机为空
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
