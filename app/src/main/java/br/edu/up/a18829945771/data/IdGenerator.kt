package br.edu.up.a18829945771.data

import java.security.SecureRandom

interface IdGenerator {
    fun generate(): String
}

class SecureIdGenerator : IdGenerator {
    private val random = SecureRandom()
    private val length = 16

    override fun generate(): String {
        val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        val randomString = StringBuilder()

        repeat(length) {
            val randomIndex = this.random.nextInt(chars.length)
            randomString.append(chars[randomIndex])
        }

        return randomString.toString()
    }

}

fun idGenerator() = SecureIdGenerator()