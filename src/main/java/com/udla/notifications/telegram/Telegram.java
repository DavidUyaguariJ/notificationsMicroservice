package com.udla.notifications.telegram;

import com.udla.notifications.consumer.configuration.ConsumerConfiguration;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j
public class Telegram {
    @RabbitListener(queues = ConsumerConfiguration.QUEUE_NOTIFICATIONS)
    public void justificationSuccess(String attendance) {
        log.info(attendance);
    }
}
