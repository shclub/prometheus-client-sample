package com.gompang.controller;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.Histogram;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@Slf4j
public class SampleController {
    private Gauge sampleGauge;
    private Histogram histogram;
    @PostConstruct
    public void init() {
        sampleGauge = Gauge.build("sample", "sample metrics").register();
        Counter.build("someCounter", "Some counter").register();
        histogram = Histogram.build("somehistogram", "somehistogram").buckets(1.0f, 2.0f, 3.0f).register();
    }

    @GetMapping
    public String sample() {
        sampleGauge.inc();
        histogram.observe(1.0f);
        return "sample";
    }
}
