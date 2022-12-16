package ru.gur.archprofiles.service.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "kafka", name = "enabled", matchIfMissing = false)
public class Consumer {

    @KafkaListener(topics = "topic1", containerFactory = "kafkaListenerContainerFactoryString")
    public void listenGroupTopic1(String message) {
        log.info("Receive message {}", message);
    }
}
