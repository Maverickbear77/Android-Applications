package com.bignerdranch.android.doggopedia

import androidx.lifecycle.ViewModel

class DogViewModel : ViewModel() {
    //Set current index to 0
    var currentIndex = 0

    //Create a list of dogs
    private val dogCollection = listOf(
        Dog(R.string.breed1, R.string.breed1_male_height, R.string.breed1_female_height, R.string.breed1_male_weight,
            R.string.breed1_female_weight, R.string.breed1_life, R.string.breed1_temperament, R.string.breed1_fact),
        Dog(R.string.breed2, R.string.breed2_male_height, R.string.breed2_female_height, R.string.breed2_male_weight,
            R.string.breed2_female_weight, R.string.breed2_life, R.string.breed2_temperament, R.string.breed2_fact),
        Dog(R.string.breed3, R.string.breed3_male_height, R.string.breed3_female_height, R.string.breed3_male_weight,
            R.string.breed3_female_weight, R.string.breed3_life, R.string.breed3_temperament, R.string.breed3_fact),
        Dog(R.string.breed4, R.string.breed4_male_height, R.string.breed4_female_height, R.string.breed4_male_weight,
            R.string.breed4_female_weight, R.string.breed4_life, R.string.breed4_temperament, R.string.breed4_fact),
        Dog(R.string.breed5, R.string.breed5_male_height, R.string.breed5_female_height, R.string.breed5_male_weight,
            R.string.breed5_female_weight, R.string.breed5_life, R.string.breed5_temperament, R.string.breed5_fact)
    )

    //Increase the current index by 1
    fun nextDog(){
        currentIndex = (currentIndex + 1) % dogCollection.size
    }

    //Decrease the current index by 1
    fun previousDog(){
        //If the index is 0, set to the size of list
        if (currentIndex == 0)
        {
            currentIndex = dogCollection.size
        }
        currentIndex = (currentIndex - 1) % dogCollection.size
    }

    //Find name
    val findNameText: Int
        get() = dogCollection[currentIndex].name

    //find male height
    val findMaleHeightText: Int
        get() = dogCollection[currentIndex].male_height

    //Find male weight
    val findMaleWeightText: Int
        get() = dogCollection[currentIndex].male_weight

    //Find female height
    val findFemaleHeightText: Int
        get() = dogCollection[currentIndex].female_height

    //Find female weight
    val findFemaleWeightText: Int
        get() = dogCollection[currentIndex].female_weight

    //Find life expectancy
    val findLifeText: Int
        get() = dogCollection[currentIndex].life

    //Find temperament
    val findTemperamentText: Int
        get() = dogCollection[currentIndex].temperament

    //Find fact
    val findFactText: Int
        get() = dogCollection[currentIndex].fact
}