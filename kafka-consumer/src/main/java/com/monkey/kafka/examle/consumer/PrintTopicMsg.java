package com.monkey.kafka.examle.consumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.Arrays;
import java.util.Properties;

import static com.monkey.kafka.examle.consumer.KafkaConfig.sitConsumer;

/**
 * PrintTopicMsg
 *
 * @author yong.han
 * 2019/1/15
 */
public class PrintTopicMsg {


    public static void main(String[] args) {

        KafkaConsumer<Object, Object> consumer = new KafkaConsumer<>(sitConsumer());
        Runtime.getRuntime().addShutdownHook(new Thread(consumer::close));


//        BUSINESS_LOG
//        CONSUME_REALITY_QUERY_EXTEND_V_1


//        consume(consumer,  "BUSINESS_LOG", "CONSUME_REALITY_QUERY_EXTEND_V_1");


        consume(consumer,  "__consumer_offsets");

//        consumer.subscribe(Arrays.asList("wiki-result"));

//        consumer.seekToBeginning(consumer.assignment());


//        while (true) {
//            ConsumerRecords<String, String> records = consumer.poll(100);
//            for (ConsumerRecord<String, String> record : records)
//                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
//        }
    }


    public static void consume(Consumer<Object, Object> consumer, String... topic) {

        for (String s : topic) {
            System.out.println("subscribe for " + s);
        }
        consumer.subscribe(Arrays.asList(topic));

//        consumer.seekToBeginning(consumer.assignment());

        for (TopicPartition topicPartition : consumer.assignment()) {
            System.out.println("hanyong-test: " + topicPartition);

        }
        System.out.println("subscribe: " + consumer.assignment().size());



        while (true) {
            ConsumerRecords<Object, Object> records = consumer.poll(100);
            for (ConsumerRecord<Object, Object> record : records)
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
        }


    }
}
