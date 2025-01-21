package com.udla.notifications.telegram;

import java.nio.charset.StandardCharsets;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.udla.notifications.consumer.configuration.ConsumerConfiguration;
import com.udla.notifications.models.MessageModel;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalesMessage {
    private final RestTemplate restTemplate;

    @RabbitListener(queues = ConsumerConfiguration.QUEUE_NOTIFICATIONS_SALES)
    public void sendSaleMessage(MessageModel message){
        
        StringBuilder strMessage = new StringBuilder();
        strMessage.append("Hola").append("\n").append("Estimado: ")
        .append(message.getName()).append("Su venta se ha realizado con exito por el producto ")
        .append(message.getProductName()).append(" Cantidad: ")
        .append(message.getProductQuantity());

        try {
        
            String telegramApiUrl = String.format(
                "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s",
                "7750793510:AAH4n95zLjXDaa8AQ1N92YFRY3lCKRrMlOk", message.getIdClient(), strMessage.toString()
            );
            restTemplate.getForObject(telegramApiUrl, String.class);
        } catch (Exception e) {
        }
    }
}
