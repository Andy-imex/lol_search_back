package com.project.riot.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ServerWebInputException


@ControllerAdvice
@RestController
object GlobalExceptionHandler { // object 싱글턴 패턴 하나만생성하기위해

    @ExceptionHandler(RestException::class)
    fun handleRestException(restException: RestException) : ResponseEntity<String>{
        val errMsg = restException.errMsg.replace("\"".toRegex(), "\\\\\"")
        return ResponseEntity
                .status(restException.httpStatus)
                .header("Content-Type", "application/json")
                .body("{ \"error_msg\": \"$errMsg\"}")
    }

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception) : ResponseEntity<String>{
        var httpStatus = HttpStatus.INTERNAL_SERVER_ERROR
        if(ex is ServerWebInputException) {
            httpStatus = ex.status
        }

        ex.printStackTrace()

        return handleException(RestException(httpStatus, ex.localizedMessage))
    }



}