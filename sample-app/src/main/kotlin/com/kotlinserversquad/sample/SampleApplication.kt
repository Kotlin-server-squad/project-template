package com.kotlinserversquad.sample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * The [SampleApplication] class is the entry point of the Spring Boot application.
 */
@SpringBootApplication
class SampleApplication

/**
 * The [main] function is the entry point of the Kotlin application.
 * It bootstraps the Spring Boot application.
 *
 * @param args Command-line arguments passed to the application.
 */
fun main(args: Array<String>) {
    runApplication<SampleApplication>(args = args)
}
