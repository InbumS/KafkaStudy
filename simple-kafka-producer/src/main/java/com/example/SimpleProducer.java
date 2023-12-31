package com.example;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Properties;

public class SimpleProducer {
    private final static Logger logger= LoggerFactory.getLogger(SimpleProducer.class);
    private final static String TOPIC_NAME ="test";
    private final static String BOOTSTRAP_SERVERS="my-kafka:9092";
    public static void main(String[] args) {

        Properties configs = new Properties();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,BOOTSTRAP_SERVERS);
        // 직렬화 작업
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer=new KafkaProducer< >(configs);

        String messageValue = "textMessage";
        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, "pangyo","23");
        // parameter로 들어간 record를 배치형태로 묶어서 전송
        producer.send(record);
        logger.info("{}",record);
        // producer 내부버퍼의 레코드 배치를 브로커로 전송
        producer.flush();
        producer.close();
    }
}