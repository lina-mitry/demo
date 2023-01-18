package com.example.patternstrategy.strategies.implementations

import com.example.patternstrategy.strategies.IAuthStrategy
import com.example.patternstrategy.strategies.IStrategyParameters
import kotlin.reflect.KType
import kotlin.reflect.typeOf

class EmailStrategy : IAuthStrategy {
    override var type: KType = typeOf<EmailStrategyParameters>()
    override fun auth(strategyParameters: IStrategyParameters) : Boolean{
        if (strategyParameters !is EmailStrategyParameters)
            throw IllegalArgumentException("Parameters has wrong type!")

        if (strategyParameters.email == null)
            throw IllegalArgumentException("email must not be empty")

        if (strategyParameters.password == null)
            throw IllegalArgumentException("password must not be empty")

        //business logic

        return true
    }
}

class EmailStrategyParameters : IStrategyParameters {
    var email: String?= String()
    var password: String? = String()
}