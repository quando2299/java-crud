package com.example.crud.services;

import java.util.List;

import org.bson.Document;

public interface UserService {
  void register(Document data);
  Document findById(String id);
  List<Document> getAll();
  void UpdateOne(String id, Document data);
}
