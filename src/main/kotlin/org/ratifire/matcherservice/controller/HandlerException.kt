package org.ratifire.matcherservice.controller

import org.ratifire.matcherservice.exception.ParticipantNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.logging.Logger

@RestControllerAdvice
class HandlerException {

    private val logger: Logger = Logger.getLogger(HandlerException::class.java.name)

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ParticipantNotFoundException::class)
    fun handleNoResourceFoundException(exception: ParticipantNotFoundException) {
        logger.warning("Exception caught: ${exception.message}")
    }
}