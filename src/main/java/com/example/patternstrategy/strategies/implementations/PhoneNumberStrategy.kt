package com.example.patternstrategy.strategies.implementations

import com.example.patternstrategy.strategies.IAuthStrategy
import com.example.patternstrategy.strategies.IStrategyParameters
import kotlin.reflect.KType
import kotlin.reflect.typeOf

class PhoneNumberStrategy : IAuthStrategy {
    override var type: KType = typeOf<PhoneNumberParameters>()
    override fun auth(strategyParameters: IStrategyParameters) : Boolean {
        if (strategyParameters !is PhoneNumberParameters)
            throw IllegalArgumentException("Parameters has wrong type!")

        if (strategyParameters.number == null)
            throw IllegalArgumentException("number must not be empty")

        if (strategyParameters.code == null)
            throw IllegalArgumentException("code must not be empty")

        //business logic

        return true
    }
}

class PhoneNumberParameters : IStrategyParameters {
    var number: String?= String()
    var code: String?= String()
}
