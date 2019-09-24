package com.clydoskope.graphql.app.graphqlgatewayapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.clydoskope.graphql"})
public class GraphqlGatewayAppApplication {

  public static void main(String[] args) {
    SpringApplication.run(GraphqlGatewayAppApplication.class, args);
  }

}
