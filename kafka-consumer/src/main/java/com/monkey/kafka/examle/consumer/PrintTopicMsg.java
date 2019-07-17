package com.monkey.kafka.examle.consumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static com.monkey.kafka.examle.consumer.KafkaConfig.sitConsumer;
import static com.monkey.kafka.examle.consumer.KafkaConfig.uatConsumer;

/**
 * PrintTopicMsg
 *
 * @author yong.han
 * 2019/1/15
 */
public class PrintTopicMsg {


    public static void main(String[] args) {

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(uatConsumer());
        Runtime.getRuntime().addShutdownHook(new Thread(consumer::close));


//        BUSINESS_LOG
//        CONSUME_REALITY_QUERY_EXTEND_V_1


//        consume(consumer,  "BUSINESS_LOG", "CONSUME_REALITY_QUERY_EXTEND_V_1");


//        consume(consumer,  "__consumer_offsets");
        consume(consumer,  "CONSUME_REALITY_QUERY_EXTEND_V_1");

//        consumer.subscribe(Arrays.asList("wiki-result"));

//        consumer.seekToBeginning(consumer.assignment());


//        while (true) {
//            ConsumerRecords<String, String> records = consumer.poll(100);
//            for (ConsumerRecord<String, String> record : records)
//                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
//        }
    }


    public static void consume(Consumer<String, String> consumer, String topic) {

//        for (String s : topic) {
//            System.out.println("subscribe for " + s);
//        }

        List<TopicPartition> sb = new ArrayList<>();
        sb.add(new TopicPartition(topic, 0));
        sb.add(new TopicPartition(topic, 1));

        consumer.assign(sb);


        for (TopicPartition topicPartition : consumer.assignment()) {
            System.out.println("hanyong-test: " + topicPartition);

        }

        consumer.seekToBeginning(consumer.assignment());


        consumer.seekToBeginning(consumer.assignment());

        System.out.println("subscribe: " + consumer.assignment().size());


        boolean running = true;

        int i=0;
        while (running) {
            ConsumerRecords<String, String> records = consumer.poll(1000);
            System.out.println("next  ----> " + records.count());


            for (ConsumerRecord<String, String> record : records) {
                i++;
                if (1==i)
                    System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());

//                if ("22739000000003043".equals(record.key())) {
//                if ("22739100000004097".equals(record.key())) {
                if ("22739000000003050".equals(record.key())) {
                    System.out.println("found key " + record.value());
                                    System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());

                    running = false;
                    break;
                }
            }
        }
        System.out.println("found end");


    }
}
