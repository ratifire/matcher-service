package org.ratifire.matcherservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MatcherServiceApplication

fun main(args: Array<String>) {
    runApplication<MatcherServiceApplication>(*args)
}
