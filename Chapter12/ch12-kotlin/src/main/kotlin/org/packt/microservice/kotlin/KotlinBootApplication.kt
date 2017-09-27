package org.packt.microservice.kotlin

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
open class KotlinBootApplication {
	 companion object {
        @JvmStatic
		fun main(args: Array<String>) {
           SpringApplication.run(KotlinBootApplication::class.java, *args)
        }
	 }
}