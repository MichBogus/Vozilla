package com.vo.vozilla.ktextensions

fun String.endsWithForwardSlash() =
        if (this.endsWith("/").not()) this + "/" else this