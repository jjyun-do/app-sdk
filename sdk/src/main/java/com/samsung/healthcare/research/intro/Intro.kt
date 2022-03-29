package com.samsung.healthcare.research.intro

import com.samsung.healthcare.research.R

class Intro(
    var title: String = "SleepCare",
    var drawableId: Int = R.drawable.sample_image4,
    var logoDrawableId: Int = R.drawable.ic_sample_icon,
    var summaryList: List<Pair<Int, String>> = listOf(
        Pair(R.drawable.ic_watch, "Wear your watch"),
        Pair(R.drawable.ic_clock, "10 min a day"),
        Pair(R.drawable.ic_alarm, "2 surveys a week")
    ),
    var descriptionList: List<Pair<String, String>> = listOf(
        Pair(
            "Description",
            "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia"
        ),
        Pair(
            "Description2",
            "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia"
        )
    ),
)
