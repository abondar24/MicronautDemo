package org.abondar.experimental.kafka;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient
public interface PersonProducer {

    @Topic("person-topic")
    void sendPerson(@KafkaKey String id,String person);
}
