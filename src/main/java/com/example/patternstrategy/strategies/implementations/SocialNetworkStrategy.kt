package com.example.patternstrategy.strategies.implementations

import com.example.patternstrategy.strategies.IAuthStrategy
import com.example.patternstrategy.strategies.IStrategyParameters
import kotlin.reflect.KType
import kotlin.reflect.typeOf

class SocialNetworkStrategy : IAuthStrategy{
    override var type: KType = typeOf<SocialNetworkParameters>()

    override fun auth(strategyParameters: IStrategyParameters) : Boolean {
        if (strategyParameters !is SocialNetworkParameters)
            throw IllegalArgumentException("Parameters has wrong type!")

        if (strategyParameters.data == null)
            throw IllegalArgumentException("data must not be empty")

        //business logic

        return true
    }
}

class SocialNetworkParameters : IStrategyParameters{
    var data: String?= String()
}
