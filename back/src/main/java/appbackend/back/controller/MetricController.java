package appbackend.back.controller;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
public class MetricController {
    @Autowired
    MeterRegistry meterRegistry;

    @GetMapping("/metrics")
    public  String createCustomMetric(){
        meterRegistry.counter("myapp_let_say_order_increment_counter").increment();
        meterRegistry.timer("myapp_order_time").record((new Random().nextInt(100) +1), TimeUnit.SECONDS); // random timer
        meterRegistry.gauge("myapp_gauge_1_100",((new Random().nextInt(100) +1)) );
        return "success";
    }
}
