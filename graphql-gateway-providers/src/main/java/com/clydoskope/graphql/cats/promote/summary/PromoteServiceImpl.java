package com.clydoskope.graphql.cats.promote.summary;

import graphql.schema.DataFetcher;
import org.springframework.stereotype.Service;

@Service
public class PromoteServiceImpl implements PromoteService {

  @Override
  public DataFetcher getPromotionPage(String userId) {
    return null;
  }
}
