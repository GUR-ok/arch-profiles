package ru.gur.archprofiles.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.gur.archprofiles.service.Producer;

@RestController
@RequiredArgsConstructor
public class KafkaController {
    private final Producer producer;

    @PostMapping("/profiles/kafka")
    public void sendString(@RequestParam String topic, @RequestParam int keyFrom, @RequestParam int keyTo, @RequestParam String data, @RequestParam int count) {
        producer.sendString(topic, keyFrom, keyTo, data, count);
    }
}
