package com.vo.vozilla.ktextensions

fun String.endsWithForwardSlash() =
        if (this.endsWith("/").not() && this.isNotBlank()) this + "/" else this