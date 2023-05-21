package com.rean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class WebServiceController {

    @GetMapping("/todo")
    public String todo() throws InterruptedException {
        log.info("GET TODO {}", new Date());
        Thread.sleep(TimeUnit.SECONDS.toMillis(30));
        return "Todo";
    }
}
