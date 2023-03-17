package com.my.api.spring;

import com.my.api.spring.mqtt.MqttClientWrapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		MqttClientWrapper clientWrapper = new MqttClientWrapper();

		SpringApplication.run(Application.class, args);
	}

}
