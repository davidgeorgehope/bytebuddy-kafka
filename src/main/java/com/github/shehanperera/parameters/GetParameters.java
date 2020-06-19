package com.github.shehanperera.parameters;

import net.bytebuddy.asm.Advice;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class GetParameters {

    @Advice.OnMethodExit
    public static void getParametrs(@Advice.Origin String method, @Advice.AllArguments Object[] para) throws Exception {

        try {
            Properties properties = new Properties();

            properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "pkc-ep9mm.us-east-2.aws.confluent.cloud:9092");
            properties.setProperty("security.protocol", "SASL_SSL");
            properties.setProperty("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule  ";");
            properties.setProperty("ssl.endpoint.identification.algorithm", "https");
            properties.setProperty("sasl.mechanism", "PLAIN");

            properties.setProperty("key.serializer", StringSerializer.class.getName());
            properties.setProperty("value.serializer", StringSerializer.class.getName());

            StringBuilder sb = new StringBuilder();

            for (Object object : para) {
                sb.append(object);
            }


            //create the producer
            KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

            //send data
            ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("promo_details", sb.toString());
            producer.send(producerRecord);

            producer.flush();
            producer.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
