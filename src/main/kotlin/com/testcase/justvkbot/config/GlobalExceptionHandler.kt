package com.testcase.justvkbot.config

import lombok.RequiredArgsConstructor
import org.slf4j.Logger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.ErrorResponse
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
@Component
@RequiredArgsConstructor
class GlobalExceptionHandler(private val logger: Logger) {


    @ExceptionHandler(Exception::class)
    fun handleExceptions(ex: Exception): ResponseEntity<ErrorResponse> {
        logger.error("Error during processing files: ", ex)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            ErrorResponse.builder(
                ex,
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.message ?: HttpStatus.BAD_REQUEST.reasonPhrase
            ).build()
        )
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentExceptions(ex: Exception): ResponseEntity<ErrorResponse> {
        logger.error("Error during file processing: ", ex)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ErrorResponse.builder(ex, HttpStatus.BAD_REQUEST, ex.message ?: HttpStatus.BAD_REQUEST.reasonPhrase).build()
        )
    }

}
