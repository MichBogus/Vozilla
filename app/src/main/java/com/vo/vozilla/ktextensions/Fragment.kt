package com.vo.vozilla.ktextensions

import android.support.v4.app.Fragment

inline fun <reified T : Any> Fragment.argument(key: String): Lazy<T> =
        @Suppress("UNCHECKED_CAST") (lazy { arguments?.get(key) as T })