package com.example.demo.config;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.example.demo.model.Employee;

@Configuration
@EnableKafka
public class Kafka_Config {

	
	@Bean
	public ConsumerFactory<String, Employee> Employeeconsumerfactory(){
    Map<String, Object> configprop=new HashMap<>();
		
        configprop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092") ;
        configprop.put(ConsumerConfig.GROUP_ID_CONFIG,"consumer-group");
        configprop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configprop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        configprop.put(ConsumerConfig.GROUP_ID_CONFIG, "consumer-group");
        return new DefaultKafkaConsumerFactory<>(configprop, new StringDeserializer(),new JsonDeserializer<>(Employee.class));		
	
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Employee> EmployeeKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Employee> factory = new ConcurrentKafkaListenerContainerFactory<String, Employee>();
		factory.setConsumerFactory(Employeeconsumerfactory());
		return factory;
	}


}
