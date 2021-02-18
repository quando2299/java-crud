package com.example.crud.controller;

import java.util.List;

import com.example.crud.services.UserService;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
  @Autowired
  private UserService userService;

  @PostMapping(value = "/register")
  public ResponseEntity<Object> register(@RequestBody Document data) {

    // check valid data
    // Function check valid
    // Config Output Json ObjectId
    Document response = new Document(data);
    

    // Data valid đẩy xuống service
    ObjectId objectId = new ObjectId();
    data.append("_id", objectId);

    response.append("id", objectId.toHexString());

    userService.register(data);
    System.out.println(response);

    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @GetMapping(value = "/detail/{id}")
  public ResponseEntity<Object> detail(@PathVariable String id) {
    Document res = userService.findById(id);
    System.out.println(res);
    return new ResponseEntity<>(res, HttpStatus.OK);
  }

  @GetMapping(value = "/details")
  public ResponseEntity<Object> details() {
    List<Document> listUsers = userService.getAll();

    if (listUsers == null || listUsers.isEmpty()){
      return ResponseEntity.noContent().build();
    }

    return new ResponseEntity<>(listUsers, HttpStatus.OK);
  }

  @PutMapping(value = "/update/{id}")
  public ResponseEntity<Object> update(@PathVariable String id, @RequestBody(required = false) Document data) {
    Document user = userService.findById(id);
    if (user == null || data == null){
      return ResponseEntity.noContent().build();
    }

    userService.UpdateOne(id, data);
    System.out.println("user in Controller: " + user);

    return new ResponseEntity<>(HttpStatus.OK);
  }
}