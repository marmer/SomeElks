package com.example.someelks;

import static net.logstash.logback.argument.StructuredArguments.keyValue;
import static net.logstash.logback.argument.StructuredArguments.value;

import java.time.Year;
import java.util.UUID;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@Slf4j
public class SomeElksApplication {

  public static void main(final String[] args) {
    SpringApplication.run(SomeElksApplication.class, args);
  }

//  https://howtodoinjava.com/spring-cloud/elk-stack-tutorial-example/#elk-configuration
//  https://www.innoq.com/en/blog/structured-logging/

  @GetMapping("/elks")
  public String makeNoise() {
    try (final var ignored = MDC.putCloseable("tracingID", UUID.randomUUID().toString())) {

      log.info("Made some Noise {} with the size {}",
          keyValue("noise", "mooh"),
          value("size", "pretty big"),
          keyValue("supprze", "easterEgg"),
          keyValue("Guest Bunny", new SuprisedBunny()));

    }
    return "moooh";
  }

  @Data
  public static class SuprisedBunny {

    private final String name = "Bugs";
    private final int age = Year.now().getValue() - 1983;
  }

}
