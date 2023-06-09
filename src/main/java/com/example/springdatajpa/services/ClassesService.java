package com.example.springdatajpa.services;

import com.example.springdatajpa.entities.Classes;
import com.example.springdatajpa.entities.User;
import com.example.springdatajpa.repositories.ClassesRepository;

import com.example.springdatajpa.utils.ReturnData;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClassesService {
    private ClassesRepository classesRepository;

    public ClassesService(ClassesRepository classesRepository) {
        this.classesRepository = classesRepository;
    }

    public Classes getClassesById(int id) {
        Optional<Classes> optionalClasses = this.classesRepository.findById(id);
        if (optionalClasses.isPresent()) {
            return optionalClasses.get();
        } else {
            return Classes.builder().build();
        }
    }

    public void createClasses(Classes classes) {
        this.classesRepository.save(classes);
    }

    public ReturnData getAllClasses(int page, int pageSize) {

        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());
        Page<Classes> classes_data = this.classesRepository.findAll(pageable);
        System.out.println(classes_data);
        return ReturnData.builder()
                .content(classes_data.getContent())
                .first(classes_data.isFirst())
                .last(classes_data.isLast())
                .totalPages(classes_data.getTotalPages())
                .size(classes_data.getSize()).build();
    }
}
