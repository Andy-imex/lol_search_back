package com.project.riot.exception

import org.springframework.http.HttpStatus

data class RestException(
        val httpStatus:HttpStatus,
        val errMsg : String
) : Exception()
