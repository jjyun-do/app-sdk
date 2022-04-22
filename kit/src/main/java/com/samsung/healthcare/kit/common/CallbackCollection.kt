package com.samsung.healthcare.kit.common

open class CallbackCollection {
    open fun prev(): Unit = Unit
    open fun next(): Unit = Unit

    // NOTE: 개발자들이 필요한 callback을 자유롭게 추가하여 쓸 수 있도록 했습니다..
    open fun setEligibility(value: Boolean): Unit = Unit
    open fun getEligibility(): Boolean = true
}
