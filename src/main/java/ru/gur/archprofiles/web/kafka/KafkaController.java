package ru.gur.archprofiles.web.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.gur.archprofiles.service.kafka.Producer;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@ConditionalOnProperty(prefix = "kafka", name = "enabled", matchIfMissing = false)
@RestController
@RequiredArgsConstructor
@Slf4j
public class KafkaController {
    private final Producer producer;

    @PostMapping("/profiles/kafka")
    public void sendString(@RequestParam String topic,
                           @RequestParam int keyFrom,
                           @RequestParam int keyTo,
                           @RequestParam String data,
                           @RequestParam int count,
                           HttpServletRequest request) {

        //Логирование хедеров. Для отладки
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            log.info(key + " " + value);
        }

        producer.sendString(topic, keyFrom, keyTo, data, count);
    }
}
