package com.clydoskope.graphql.demo;

import graphql.schema.DataFetcher;
import org.springframework.stereotype.Service;

@Service
public interface DemoService {

  DataFetcher getBookByIdDataFetcher();

  DataFetcher getAllBooks();

  DataFetcher getAuthorDataFetcher();
}
