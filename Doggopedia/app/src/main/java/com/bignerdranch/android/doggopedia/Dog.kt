package com.bignerdranch.android.doggopedia

import androidx.annotation.StringRes

//The data class of a dog that contains eight attributes
data class Dog (
    @StringRes val name: Int,
    @StringRes val male_height: Int,
    @StringRes val female_height: Int,
    @StringRes val male_weight: Int,
    @StringRes val female_weight: Int,
    @StringRes val life: Int,
    @StringRes val temperament: Int,
    @StringRes val fact: Int
)
