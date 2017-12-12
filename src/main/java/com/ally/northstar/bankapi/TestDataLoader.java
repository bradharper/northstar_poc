package com.ally.northstar.bankapi;

import com.ally.northstar.bankapi.domain.repository.TestDataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.crnk.core.engine.transaction.TransactionRunner;
import com.ally.northstar.bankapi.domain.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;


@Configuration
public class TestDataLoader {


	@Autowired
	private ObjectMapper objectMapper;

	@PostConstruct
	public void setup() {

				for (int i = 0; i < 10; i++) {
					Schedule toCreate = new Schedule();
					toCreate.setId((long) i);
					toCreate.setName("schedule" + i);
					TestDataRepository.create(toCreate);
				}

	}

	@PostConstruct
	public void configureJackson() {
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
	}
}
