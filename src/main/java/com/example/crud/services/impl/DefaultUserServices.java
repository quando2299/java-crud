package com.example.crud.services.impl;

import java.util.LinkedList;
import java.util.List;

import com.example.crud.repository.UserRepo;
import com.example.crud.services.UserService;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserServices implements UserService {
  @Autowired
  private UserRepo userRepo;

  @Override
  public void register(Document data) {
    // mã hóa tại đây
    userRepo.insert(data);
  }

  @Override
  public Document findById(String id) {
    return userRepo.findOne(Filters.eq(new ObjectId(id)));
  }

  @Override
  public List<Document> getAll() {
    FindIterable<Document> listUsers = userRepo.find(null);

    if (listUsers != null) {
      List<Document> response = new LinkedList<>();

      listUsers.forEach(response::add);
      return response;
    }

    return null;
  }

  @Override
  public void UpdateOne(String id, Document data) {
    userRepo.updateOne(id, data);
  }

}
