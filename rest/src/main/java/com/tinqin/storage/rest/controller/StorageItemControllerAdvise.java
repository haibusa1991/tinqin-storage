package com.tinqin.storage.rest.controller;

import com.tinqin.storage.core.exception.InsufficientItemQuantityException;
import com.tinqin.storage.core.exception.ItemExistsException;
import com.tinqin.storage.core.exception.ReferencedItemNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestControllerAdvice(basePackageClasses = StorageItemController.class)
public class StorageItemControllerAdvise {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<String> handleValidatorException(MethodArgumentNotValidException e) {

        String errors = Arrays.stream(e.getDetailMessageArguments()).toList()
                .stream()
                .flatMap(listError -> Stream.of(listError.toString()))
                .toList()
                .stream()
                .map(error -> error.replace("[", ""))
                .map(error -> error.replace("]", ""))
                .filter(error -> error.length() > 0)
                .collect(Collectors.joining(System.lineSeparator()));

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ReferencedItemNotFoundException.class)
    @ResponseBody
    public ResponseEntity<String> handleReferencedItemNotFoundException(ReferencedItemNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ItemExistsException.class)
    @ResponseBody
    public ResponseEntity<String> handleItemExistsException(ItemExistsException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InsufficientItemQuantityException.class)
    @ResponseBody
    public ResponseEntity<String> handleInsufficientItemQuantityExceptions(InsufficientItemQuantityException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
