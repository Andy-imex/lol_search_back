package com.project.riot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RiotApplication

fun main(args: Array<String>) {
	runApplication<RiotApplication>(*args)
}

