package com.example.producer.controller;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.consumer.model.MessageItem;
import com.example.producer.service.KafkaProducerService;

@Controller
public class WebController {

	@Autowired
	private KafkaProducerService kafkaProducerService;

	@RequestMapping(value = "/publish_kafka", method = RequestMethod.POST)
	public String sendMessageToKafkaConsumer(@RequestParam String message) {
		MessageItem messageItem = createCurrentMessage(message);
		kafkaProducerService.send(messageItem);
		return "redirect:kafka_producer.html";
	}

	private MessageItem createCurrentMessage(String message) {
		MessageItem messageItem = new MessageItem();
		messageItem.setMessage(message);
		messageItem.setUuid(UUID.randomUUID().toString());
		messageItem.setTime(LocalDateTime.now().toString());
		return messageItem;
	}
}