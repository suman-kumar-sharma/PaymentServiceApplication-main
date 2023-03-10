package com.gfg.session18jbdl30majorproject.conf;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Consumer conf.
 */
@Configuration
public class ConsumerConf {
    /**
     * Consumer config map.
     *
     * @return the map
     */
    @Bean
    Map<Object,Object> consumerConfig(){
        Map<Object,Object> map = new HashMap<>();
        map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
        return map;
    }

    /**
     * Consumer factory consumer factory.
     *
     * @return the consumer factory
     */
    @Bean
    ConsumerFactory<String,String> consumerFactory(){
        return new DefaultKafkaConsumerFactory(consumerConfig());
    }

    /**
     * Kafka listener container factory kafka listener container factory.
     *
     * @return the kafka listener container factory
     */
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String,String>> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String,String> concurrentKafkaListenerContainerFactory
                = new ConcurrentKafkaListenerContainerFactory<>();
        concurrentKafkaListenerContainerFactory.setConsumerFactory(consumerFactory());
        return concurrentKafkaListenerContainerFactory;
    }

}
