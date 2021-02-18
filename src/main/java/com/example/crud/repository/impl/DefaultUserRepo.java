package com.example.crud.repository.impl;

import com.example.crud.repository.UserRepo;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultUserRepo implements UserRepo {
  @Autowired
  private MongoDatabase mongoDatabase;

  @Override
  public void insert(Document data) {
    MongoCollection<Document> userCollectioDocument = mongoDatabase.getCollection("users");
    userCollectioDocument.insertOne(data);
  }

  @Override
  public FindIterable<Document> find(Bson filter) {
    MongoCollection<Document> userCollectioDocument = mongoDatabase.getCollection("users");

    if (filter != null){
      return userCollectioDocument.find(filter);
    }
    return userCollectioDocument.find();
  }

  @Override
  public Document findOne(Bson filter) {
    MongoCollection<Document> userCollectioDocument = mongoDatabase.getCollection("users");
    return userCollectioDocument.find(filter).first();
  }

  @Override
  public void updateOne(String id, Document data) {
    MongoCollection<Document> userCollectioDocument = mongoDatabase.getCollection("users");
    System.out.println("Data in Repo: " + data);
    userCollectioDocument.findOneAndReplace(Filters.eq("_id", new ObjectId(id)), data);
  }
}
