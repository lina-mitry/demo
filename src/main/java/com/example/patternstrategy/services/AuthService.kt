package com.example.patternstrategy.services

import com.example.patternstrategy.strategies.IAuthStrategy
import com.example.patternstrategy.strategies.IStrategyParameters
import com.example.patternstrategy.strategies.implementations.EmailStrategyParameters
import com.example.patternstrategy.strategies.implementations.PhoneNumberParameters
import com.example.patternstrategy.strategies.implementations.SocialNetworkParameters
import org.apache.logging.log4j.LogManager
import java.lang.Exception
import java.util.*
import kotlin.reflect.KType
import kotlin.reflect.full.createType

class AuthService(strategies: List<IAuthStrategy>) {
    private val log = LogManager.getLogger();
    private val strategyDictionary: Map<KType, IAuthStrategy> = strategies.associateBy { it.type }

    fun auth(authParameters : IStrategyParameters) : Boolean {
        val strategy = Optional.ofNullable(getStrategy(authParameters))
            .orElseGet {
                log.error("Strategy not exist")
                throw Exception()
            }
        log.info("Strategy type is ${strategy.javaClass.simpleName}")
        return strategy.auth(authParameters)
    }

    private fun getStrategiesParameters(): List<IStrategyParameters> {
        return listOf(
            EmailStrategyParameters(),
            PhoneNumberParameters(),
            SocialNetworkParameters()
        )
    }

    private fun getStrategy(authParameters: IStrategyParameters) : IAuthStrategy? {
        val paramsDictionary =  getStrategiesParameters().associateBy { strategyDictionary[it::class.createType()]!! }
        paramsDictionary.forEach { (strategy, parameters) ->
            val result = parameters::class == authParameters::class
            if (result) return strategy
        }
       return null
    }
}
