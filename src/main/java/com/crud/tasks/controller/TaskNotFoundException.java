package com.crud.tasks.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
class TaskNotFoundException extends Exception {

    TaskNotFoundException(String message) {
        super(message);
    }
}
