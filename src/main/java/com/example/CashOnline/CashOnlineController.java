package com.example.CashOnline;

import com.example.CashOnline.models.Loans;
import com.example.CashOnline.models.User;
import com.example.CashOnline.repositories.LoansRepository;
import com.example.CashOnline.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@RestController
public class CashOnlineController {


    @Autowired
    LoansRepository loansRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/users")
    public ResponseEntity<Map<String, Object>> newUser(@RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()).isEmpty()) {
            userRepository.save(new User(user.getEmail(), user.getFirstName(), user.getLastName()));
            return new ResponseEntity<>(makeMap("Exito", "Usuario Creado! usuario: " + userRepository.findByEmail(user.getEmail()).get().getId()), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(makeMap("Error", "Mail en uso"), HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.delete(userRepository.findById(id).get());
            return new ResponseEntity<>(makeMap("Exito", "Usuario Eliminado!"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(makeMap("Error", "Usuario no encontrado!"), HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/users/{id}")
    public Map<String, Object> findUser(@PathVariable Long id) {

        Map<String, Object> dto = new LinkedHashMap<String, Object>();

        dto.put("id", userRepository.getById(id).getId());
        dto.put("email", userRepository.getById(id).getEmail());
        dto.put("firstName", userRepository.getById(id).getFirstName());
        dto.put("lastName", userRepository.getById(id).getLastName());
        dto.put("loans", userRepository.getById(id).getLoans().stream().map(this::makeLoansDTO).collect(toList()));
        return dto;

    }

    @GetMapping("/loans")
    @ResponseBody
    public Map<String, Object> findLoans(@RequestParam int page, @RequestParam int size, @RequestParam Long user_id) {

        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        if (userRepository.findById(user_id).isPresent()) {
            dto.put("items", userRepository.getById(user_id).getLoans().stream().map(this::makeLoansDTO).collect(toList()));
        }
        dto.put("paging", makePagingDTO(page, size));
        return dto;
    }

    private Map<String, Object> makePagingDTO(@RequestParam int page, @RequestParam int size) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("page", page);
        dto.put("size", size);
        dto.put("total", 1500);
        return dto;
    }

    private Map<String, Object> makeLoansDTO(Loans loans) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", loans.getId());
        dto.put("total", loans.getTotal());
        dto.put("userId", loans.getUserId().getId());
        return dto;
    }

    private Map<String, Object> makeMap(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }
}
