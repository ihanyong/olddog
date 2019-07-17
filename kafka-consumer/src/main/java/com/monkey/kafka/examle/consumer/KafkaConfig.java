package com.monkey.kafka.examle.consumer;

import java.util.Properties;

/**
 * KafkaConfig
 *
 * @author yong.han
 * 2019/1/18
 */
public class KafkaConfig {
    public static Properties sitConsumer() {
        Properties props = new Properties();
//        props.put("bootstrap.servers", "192.168.6.21:9092,192.168.6.22:9092,192.168.6.23:9092"); // dev
        props.put("bootstrap.servers", "192.168.6.65:9092,192.168.6.66:9092,192.168.6.67:9093"); // sit
//        props.put("group.id", "test-2");
        props.put("group.id", "test-2");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return props;
    }
    public static Properties uatConsumer() {
        Properties props = new Properties();
//        props.put("bootstrap.servers", "192.168.6.21:9092,192.168.6.22:9092,192.168.6.23:9092"); // dev
        props.put("bootstrap.servers", "172.16.7.27:29092,172.16.7.28:29093,172.16.7.29:29094"); // sit
//        props.put("group.id", "test-2");
        props.put("group.id", "han-test1");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return props;
    }
}
