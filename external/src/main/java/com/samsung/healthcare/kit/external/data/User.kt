package com.samsung.healthcare.kit.external.data

data class User(
    val idToken: String,
    val info: Map<String, Any> = emptyMap()
)
