package com.example.patternstrategy

import com.example.patternstrategy.services.AuthService
import com.example.patternstrategy.strategies.IStrategyParameters
import com.example.patternstrategy.strategies.implementations.*
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class StrategyTest {
    private val authService = AuthService(
        listOf(
            SocialNetworkStrategy(),
            PhoneNumberStrategy(),
            EmailStrategy()
        )
    )

    @Test
    fun authPhoneNumberStrategyShouldSuccess() {
        //Arrange
        val parameters = PhoneNumberParameters().also {
            it.number = "+79649956685"
            it.code = "666"
        }

        //Act
        val result = authService.auth(parameters)

        //Assert
        assert(result)
    }

    @Test
    fun authSocialNetworkStrategyShouldSuccess() {
        //Arrange
        val parameters = SocialNetworkParameters().also {
            it.data = "telegram"
        }

        //Act
        val result = authService.auth(parameters)

        //Assert
        assert(result)
    }

    @Test
    fun authEmailStrategyShouldSuccess() {
        //Arrange
        val parameters = EmailStrategyParameters().also {
            it.email = "pes@mail.ru"
            it.password = "666"
        }

        //Act
        val result = authService.auth(parameters)

        //Assert
        assert(result)
    }

    @Test
    fun authPhoneNumberStrategyShouldFail() {
        //Arrange
        val parameters = PhoneNumberParameters().also {
            it.number = "+79649956685"
            it.code = null
        }

        //Act

        //Assert
        assertThrows<IllegalArgumentException> {
            authService.auth(parameters)
        }
    }

    @Test
    fun authSocialNetworkStrategyShouldFail() {
        //Arrange
        val parameters = SocialNetworkParameters().also {
            it.data = null
        }

        //Act

        //Assert
        assertThrows<IllegalArgumentException> {
            authService.auth(parameters)
        }
    }

    @Test
    fun authEmailStrategyShouldFail() {
        //Arrange
        val parameters = EmailStrategyParameters().also {
            it.email = null
            it.password = null
        }

        //Act

        //Assert
        assertThrows<IllegalArgumentException> {
            authService.auth(parameters)
        }
    }

    @Test
    fun authStrategyNotExistShouldSuccess() {
        //Arrange
        val parameters = TestParameters().also { it.field = "fail" }

        //Act

        //Assert
        assertThrows<Exception> {
            authService.auth(parameters)
        }
    }

    class TestParameters : IStrategyParameters {
        var field : String = String()
    }
}