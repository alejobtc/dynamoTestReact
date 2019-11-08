package com.contactabilidad.push;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.blockhound.BlockHound;
import reactor.core.publisher.Mono;

@RestController
public class UserController {

    @Autowired
    dynamoRepo dynamoRepo;


    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<User>> save(@RequestBody User user) {

        return dynamoRepo.saveUser(user)
                .map( saved ->
                ResponseEntity.status(HttpStatus.CREATED).body(saved)
                )

                .onErrorResume ( (t) -> {
                            System.out.println(t);
                            return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                        }
                );
    }


    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<User>> findUser(@PathVariable String id){
        return dynamoRepo.getUser(id)
                .filter(this::validate)
                .map(u->{
                    return ResponseEntity.status(HttpStatus.OK).body(u);
                });
    }

    @GetMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public void findAll(){
         //dynamoRepo.findAll();

    }


    public boolean validate(User user){
        if (user.getName().length()>5) {
            return true;
        }
        return false;

    }

}
