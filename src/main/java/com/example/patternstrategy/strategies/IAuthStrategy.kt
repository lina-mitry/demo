package com.example.patternstrategy.strategies

import kotlin.reflect.KType

interface IAuthStrategy {
    var type: KType

    fun auth(strategyParameters: IStrategyParameters) : Boolean
}