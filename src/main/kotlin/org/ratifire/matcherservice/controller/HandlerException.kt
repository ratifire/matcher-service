package org.ratifire.matcherservice.controller

import org.ratifire.matcherservice.exception.InvalidParticipantDataException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.logging.Logger

@RestControllerAdvice
class HandlerException {

    private val logger: Logger = Logger.getLogger(HandlerException::class.java.name)

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(InvalidParticipantDataException::class)
    fun handleInvalidParticipantDataException(exception: InvalidParticipantDataException) {
        logger.warning("Exception caught: ${exception.message}")
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException::class)
    fun handleNoSuchElementException(exception: NoSuchElementException) {
        logger.warning("Exception caught: ${exception.message}")
    }
}