package com.chikitania;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Ruth on 27/03/2020.
 */
public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException {
        String uri = System.getenv("CLOUDAMQP_URL");
        if (uri == null) uri = "amqp://nehvmuzt:v-eN1QJeh4AWnZiYVHLs1_NJTT4DLocG@spider.rmq.cloudamqp.com/nehvmuzt";

        ConnectionFactory factory = new ConnectionFactory();

        factory.setUri(uri);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("q-event", false, false, false, null);
        channel.basicConsume("q-event", true, (consumerTag, message) -> {
            String msg = new String(message.getBody(), "UTF-8");
            System.out.println("I just recived a message: " + msg);
        }, consumerTag -> {});


    }
}
