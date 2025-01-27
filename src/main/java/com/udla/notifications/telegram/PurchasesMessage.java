package com.udla.notifications.telegram;


import com.udla.notifications.consumer.configuration.ConsumerConfiguration;
import com.udla.notifications.models.MessageModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
@Slf4j
public class PurchasesMessage {

    private final RestTemplate restTemplate;

    @RabbitListener(queues = ConsumerConfiguration.QUEUE_NOTIFICATIONS_PURCHASES)
    public void confirmSale(MessageModel  message) {

        StringBuilder strMessage = new StringBuilder();
        strMessage.append("Hola ").append(message.getName()).append("\n")
                .append("Su compra se ha ").append(message.getDescription()).append("\n")
                .append("con éxito por el producto: ").append(message.getProductName()).append("\n")
                .append("Cantidad: ").append(message.getProductQuantity());
        try {

            String telegramApiUrl = String.format(
                    "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s",
                    " t", message.getIdClient(), strMessage.toString()
            );
            restTemplate.getForObject(telegramApiUrl, String.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}