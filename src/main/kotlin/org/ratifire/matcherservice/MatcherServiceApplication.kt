package org.ratifire.matcherservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
class MatcherServiceApplication

fun main(args: Array<String>) {
    runApplication<MatcherServiceApplication>(*args)
}
