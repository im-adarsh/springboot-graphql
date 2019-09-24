package com.clydoskope.graphql.demo;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

import com.clydoskope.graphql.provider.common.ProviderRegistrar;
import graphql.schema.idl.RuntimeWiring;
import java.io.IOException;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

public class DemoProvider extends ProviderRegistrar {

  @Autowired
  DemoService demoService;

  @PostConstruct
  public void init() throws IOException {
    this.setGraphPath("graphql/demo/schema.graphql");
    this.Register();
  }

  @Override
  protected RuntimeWiring buildWiring() {
    return RuntimeWiring.newRuntimeWiring()
        .type(newTypeWiring("Query")
            .dataFetcher("getAllBooks", demoService.getAllBooks()))
        .type(newTypeWiring("Query")
            .dataFetcher("bookById", demoService.getBookByIdDataFetcher()))
        .type(newTypeWiring("Book")
            .dataFetcher("author", demoService.getAuthorDataFetcher()))
        .build();
  }
}
