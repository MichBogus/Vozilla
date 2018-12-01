package com.vo.vozilla.ktextensions

fun createData(vararg parameters: () -> Any): Collection<Any> =
        mutableListOf<Any>().apply { parameters.forEach { this.add(it.invoke()) } }