package com.example.demo.controller;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;

@RestController
public class Kafka_Controller {
	
	
	Employee fromTopic=null;
	
	
	@GetMapping("/ConsumeTheProducerMessage")
	public Employee consumerMsg(){
		return fromTopic;
	}
	
	@KafkaListener(groupId="consumer-group",topics="Topic1", containerFactory="EmployeeKafkaListenerContainerFactory")
	public Employee getMsgFromProducer(Employee emp){
		fromTopic=emp;
		return fromTopic;
	}

}
