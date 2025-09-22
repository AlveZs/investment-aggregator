package com.alvezs.investmentaggregator.service;

import com.alvezs.investmentaggregator.dto.AccountResponseDTO;
import com.alvezs.investmentaggregator.dto.CreateAccountDTO;
import com.alvezs.investmentaggregator.dto.CreateUserDTO;
import com.alvezs.investmentaggregator.dto.UpdateUserDTO;
import com.alvezs.investmentaggregator.entity.Account;
import com.alvezs.investmentaggregator.entity.BillingAddress;
import com.alvezs.investmentaggregator.entity.User;
import com.alvezs.investmentaggregator.repository.AccountRepository;
import com.alvezs.investmentaggregator.repository.BillingAddressRepository;
import com.alvezs.investmentaggregator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.isNull;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private BillingAddressRepository billingAddressRepository;

    public User createUser(CreateUserDTO createUserDTO) {
        var entity = new User(
                UUID.randomUUID(),
                createUserDTO.username(),
                createUserDTO.email(),
                createUserDTO.password(),
                Instant.now(),
                null
        );

        return userRepository.save(entity);
    }

    public Optional<User> updateUserById(String userId, UpdateUserDTO updateUserDTO) {
        var id = UUID.fromString(userId);

        var userEntity = userRepository.findById(id);

        if (userEntity.isPresent()) {
            var user = userEntity.get();

            if (updateUserDTO.username() != null) {
                user.setUsername(updateUserDTO.username());
            }
            if (updateUserDTO.password() != null) {
                user.setPassword(updateUserDTO.password());
            }

            return Optional.of(userRepository.save(user));
        }

        return Optional.empty();
    }

    public Optional<User> getUserById(String userId) {
        return userRepository.findById(UUID.fromString(userId));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteById(String userId) {
        var id = UUID.fromString(userId);

        var userExists = userRepository.existsById(id);

        if (userExists) {
            userRepository.deleteById(id);
        }
    }

    @Transactional
    public Account createAccount(String userId, CreateAccountDTO createAccountDTO) {
        var user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (isNull(user.getAccounts())) {
            user.setAccounts(new ArrayList<>());
        }

        var account = new Account(
                UUID.randomUUID(),
                createAccountDTO.description(),
                user,
                null,
                new ArrayList<>()
        );

        var accountCreated = accountRepository.save(account);

        var billingAddress = new BillingAddress(
                accountCreated,
                createAccountDTO.street(),
                createAccountDTO.number()
        );

        billingAddressRepository.save(billingAddress);
        accountCreated.setBillingAddress(billingAddress);

        return accountCreated;
    }

    public List<AccountResponseDTO> listAccounts(String userId) {
        var user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return user.getAccounts()
                .stream()
                .map(account -> new AccountResponseDTO(
                                account.getAccountId(),
                                account.getDescription()
                        )
                ).toList();
    }
}
