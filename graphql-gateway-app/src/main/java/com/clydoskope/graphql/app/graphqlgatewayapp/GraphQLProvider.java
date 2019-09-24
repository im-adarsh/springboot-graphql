package com.clydoskope.graphql.app.graphqlgatewayapp;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

import com.clydoskope.graphql.cats.promote.summary.PromoteService;
import com.clydoskope.graphql.demo.DemoService;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import java.io.IOException;
import java.net.URL;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class GraphQLProvider {

  private GraphQL graphQL;

  @Bean
  public GraphQL graphQL() {
    return graphQL;
  }

  @Autowired
  DemoService demoService;

  @Autowired
  PromoteService promoteService;

  @PostConstruct
  public void init() throws IOException {
    URL url = Resources.getResource("graphql/query.graphql");
    String sdl = Resources.toString(url, Charsets.UTF_8);
    GraphQLSchema graphQLSchema = buildSchema(sdl);
    this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
  }

  private GraphQLSchema buildSchema(String sdl) {
    TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
    RuntimeWiring runtimeWiring = buildWiring();
    SchemaGenerator schemaGenerator = new SchemaGenerator();
    return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
  }

  private RuntimeWiring buildWiring() {
    return RuntimeWiring.newRuntimeWiring()
        .type(newTypeWiring("Query")
            .dataFetcher("getPromotePage", promoteService.getPromotionPage("")))
        .type(newTypeWiring("Query")
            .dataFetcher("getAllBooks", demoService.getAllBooks()))
        .type(newTypeWiring("Query")
            .dataFetcher("bookById", demoService.getBookByIdDataFetcher()))
        .type(newTypeWiring("Book")
            .dataFetcher("author", demoService.getAuthorDataFetcher()))
        .build();
  }
}