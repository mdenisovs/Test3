package org.task3.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.task3.infrastructure.entities.User;
import org.task3.dtos.UserRequestDto;
import org.task3.dtos.UserResponseDto;
import org.task3.dtos.converters.UserConverter;
import org.task3.infrastructure.repositories.UserRepository;
import org.springframework.data.domain.Page;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.task3.dtos.constants.Formatter.formatter;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final ModelMapper modelMapper;

    @PostConstruct
    public void init() {
        var user1 = new User();
        user1.setId(1L);
        user1.setName("Jack");
        user1.setSurname("Smith");
        user1.setBirthday(LocalDate.of(1987, 1, 1));

        var user2 = new User();
        user2.setId(2L);
        user2.setName("Max");
        user2.setSurname("D");
        user2.setBirthday(LocalDate.of(1963, 5, 9));

        var user3 = new User();
        user3.setId(3L);
        user3.setName("John");
        user3.setSurname("G");
        user3.setBirthday(LocalDate.of(1999, 12, 19));

        var user4 = new User();
        user4.setId(4L);
        user4.setName("Kate");
        user4.setSurname("Kategirl");
        user4.setBirthday(LocalDate.of(2005, 7, 7));

        var user5 = new User();
        user5.setId(5L);
        user5.setName("Mike");
        user5.setSurname("Tison");
        user5.setBirthday(LocalDate.of(2015, 9, 28));

        repository.save(user1);
        repository.save(user2);
        repository.save(user3);
        repository.save(user4);
        repository.save(user5);
    }

    @Override
    public ResponseEntity<Void> save(UserRequestDto request) {
        var entity = repository.findById(request.getId());
        if(entity.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        var user = UserConverter.convert(request, modelMapper);
        repository.save(user);
        log.info("{} user saved", request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public  ResponseEntity<Void> update(Long id, UserRequestDto request) {
        var entity = repository.findById(id);
        if(entity.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        var newEntity = UserConverter.convert(request, modelMapper);
        newEntity.setId(id);
        log.info("updating {} user to {}", entity.get(), newEntity);

        repository.save(newEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserResponseDto> get(Long id) {
        var entity = repository.findById(id);
        if(entity.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        var response = UserConverter.convert(entity.get(), modelMapper);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public List<UserResponseDto> getAll(Integer pageSize, Integer pageNum, String birthday, String name, String surname) {
        var pageable = pageSize != null && pageNum != null ? Pageable.ofSize(pageSize).withPage(pageNum) : Pageable.unpaged();

        var birthDate = birthday != null ? LocalDate.parse(birthday, formatter) : null;
        return repository.getFiltered(name, surname, birthDate, pageable).stream()
                .map(it -> UserConverter.convert(it, modelMapper))
                .collect(Collectors.toList());
    }
}