package com.clydoskope.graphql.cats.promote.summary;

import graphql.schema.DataFetcher;
import org.springframework.stereotype.Service;

@Service
public interface PromoteService {

  DataFetcher getPromotionPage(String userId);
}
