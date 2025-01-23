package com.udla.notifications.telegram;


import com.udla.notifications.consumer.configuration.ConsumerConfiguration;
import com.udla.notifications.models.MessageModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
@Slf4j
public class SalesMessage {

    private final RestTemplate restTemplate;

    @RabbitListener(queues = ConsumerConfiguration.QUEUE_NOTIFICATIONS_SALES)
    public void confirmSale(MessageModel  message) {

        log.info("Sale confirmation: {}", message);
        StringBuilder strMessage = new StringBuilder();
        strMessage.append("Hola ").append(message.getName()).append("\n")
                .append("Su venta se ha generado exitosamente por el producto: ").append(message.getProductName()).append("\n")
                .append("Cantidad: ").append(message.getProductQuantity());
        try {

            String telegramApiUrl = String.format(
                    "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s",
                    "7750793510:AAH4n95zLjXDaa8AQ1N92YFRY3lCKRrMlOk", message.getIdClient(), strMessage.toString()
            );

            restTemplate.getForObject(telegramApiUrl, String.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
