package com.example.crud.repository;

import com.mongodb.client.FindIterable;

import org.bson.Document;
import org.bson.conversions.Bson;

public interface UserRepo{
  void insert(Document data);

  Document findOne(Bson filter);

  FindIterable<Document> find(Bson filter); // Get detail filter = null limit == 100
  
  void updateOne(String id, Document data);
}
