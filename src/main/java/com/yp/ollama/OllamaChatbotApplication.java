package com.yp.ollama;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OllamaChatbotApplication {
	public static void main(String[] args) {
		System.out.println("Ollama Chatbot Application is started!!");
		SpringApplication.run(OllamaChatbotApplication.class, args);
	}
}
