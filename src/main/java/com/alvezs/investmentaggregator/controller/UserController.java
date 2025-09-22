package com.alvezs.investmentaggregator.controller;

import com.alvezs.investmentaggregator.dto.AccountResponseDTO;
import com.alvezs.investmentaggregator.dto.CreateAccountDTO;
import com.alvezs.investmentaggregator.dto.CreateUserDTO;
import com.alvezs.investmentaggregator.dto.UpdateUserDTO;
import com.alvezs.investmentaggregator.entity.Account;
import com.alvezs.investmentaggregator.entity.User;
import com.alvezs.investmentaggregator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserDTO createUserDTO) {
        var createdUser = userService.createUser(createUserDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUserById(
            @PathVariable("userId") String userId,
            @RequestBody UpdateUserDTO updateUserDTO
    ) {
        var updatedUser = userService.updateUserById(userId, updateUserDTO);
        if (updatedUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedUser.get());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {
        var user = userService.getUserById(userId);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user.get());
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        var users = userService.getAllUsers();

        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteById(@PathVariable("userId") String userId) {
        userService.deleteById(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/accounts")
    public ResponseEntity<Account> createAccount(
            @PathVariable("userId") String userId,
            @RequestBody CreateAccountDTO createAccountDTO
    ) {
        var createdAccount = userService.createAccount(userId, createAccountDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
    }

    @GetMapping("/{userId}/accounts")
    public ResponseEntity<List<AccountResponseDTO>> getAccountsByUserId(
            @PathVariable("userId") String userId
    ) {
        var accounts = userService.listAccounts(userId);

        return ResponseEntity.ok(accounts);
    }
}
