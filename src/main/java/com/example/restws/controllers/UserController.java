package com.example.restws.controllers;

import com.example.restws.models.Post;
import com.example.restws.models.User;
import com.example.restws.repositories.PostRepository;
import com.example.restws.repositories.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List list = userRepository.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/users/{user_id}")
    public EntityModel<User> getUserById(@PathVariable("user_id") int user_id) throws Exception {
        User user = userRepository.findById(user_id).get();
        if (user != null) {
            EntityModel<User> entityModel = EntityModel.of(user);
            //HATEOAS - links to all users - next possible action
            WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).getAllUsers());
            entityModel.add(linkToUsers.withRel("All-Users"));
            return entityModel;
        } else {
            throw new NotFoundException("User not found");
        }
    }

    @PostMapping(value = "/users")
    public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(value = "/users/{user_id}")
    public void removeUser(@PathVariable("user_id") int user_id) {
        userRepository.deleteById(user_id);
    }

    @GetMapping(value = "/users/{user_id}/posts")
    public List<Post> getAllUserPost(@PathVariable("user_id") int user_id) throws Exception {
        Optional<User> optionalUser = userRepository.findById(user_id);
        if (!optionalUser.isPresent()) {
            throw new NotFoundException("User not found");
        }
        return optionalUser.get().getPosts();
    }

    @PostMapping(value = "/users/{user_id}/posts")
    public ResponseEntity<Object> savePost(@PathVariable("user_id") int user_id, @Valid @RequestBody Post post) throws Exception {
        Optional<User> fetchedUser = userRepository.findById(user_id);
        if (!fetchedUser.isPresent()) {
            throw new NotFoundException("User not found");
        }
        User user = fetchedUser.get();
        post.setUser(user);
        postRepository.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
