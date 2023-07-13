package com.storage.controller;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestControllerAdvice(basePackageClasses = StorageItemController.class)
public class StorageItemControllerAdvise {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<String> handleValidatorException(MethodArgumentNotValidException e) {

        String errors = Arrays.stream(e.getDetailMessageArguments()).toList()
                .stream()
                .flatMap(l -> Stream.of(l.toString()))
                .toList()
                .stream()
                .map(s -> s.replace("[",""))
                .map(s -> s.replace("]",""))
                .filter(s->s.length()>0)
                .collect(Collectors.joining(System.lineSeparator()));


        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }
}
