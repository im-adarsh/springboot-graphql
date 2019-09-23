package com.clydoskope.graphql.schema.demo;

import java.util.Map;

public class Types {
  
  
  public static class QueryBookByIdArgs {
    private Object _id;
  
    public QueryBookByIdArgs(Map<String, Object> args) {
      if (args != null) {
        this._id = (Object) args.get("id");
      }
    }
  
    public Object getId() { return this._id; }
  }
}
