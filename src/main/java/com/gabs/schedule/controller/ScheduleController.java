package com.gabs.schedule.controller;

import com.gabs.schedule.dto.Fact;
import com.gabs.schedule.util.Util;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class ScheduleController {

    static final Logger LOGGER =
            Logger.getLogger(ScheduleController.class.getName());

    RestClient restClient = RestClient.create();

    @Scheduled(fixedRate = 5000)
    @GetMapping("/facts")
    public ResponseEntity<Fact> helloWorldSchedule() {
        ResponseEntity<Fact> result= restClient.get()
                .uri(Util.URI_CATS + "/fact")
                .retrieve()
                .toEntity(Fact.class);
        LOGGER.log(Level.INFO,result.getBody().getFact());
        return result;
    }

}
