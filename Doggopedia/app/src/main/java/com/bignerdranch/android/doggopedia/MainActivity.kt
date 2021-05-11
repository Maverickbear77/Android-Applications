package com.bignerdranch.android.doggopedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders

private const val KEY_INDEX = "index"

class MainActivity : AppCompatActivity() {

    private lateinit var name: TextView
    private lateinit var male_height: TextView
    private lateinit var male_weight: TextView
    private lateinit var female_height: TextView
    private lateinit var female_weight: TextView
    private lateinit var life: TextView
    private lateinit var temperament: TextView
    private lateinit var fact: TextView
    private lateinit var image: ImageView
    private lateinit var previous_button: Button
    private lateinit var next_button: Button

    //Initialize the list of dog
    private val dogViewModel: DogViewModel by lazy {
        ViewModelProviders.of(this).get(DogViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Find current index
        val currentIndex = savedInstanceState?.getInt(KEY_INDEX, 0)?:0
        dogViewModel.currentIndex = currentIndex

        //Link Views
        name = findViewById(R.id.name)
        male_height = findViewById(R.id.male_height)
        male_weight = findViewById(R.id.male_weight)
        female_height = findViewById(R.id.female_height)
        female_weight = findViewById(R.id.female_weight)
        life = findViewById(R.id.dog_life)
        temperament = findViewById(R.id.dog_temperament)
        fact = findViewById(R.id.fact)
        image = findViewById(R.id.image)
        previous_button = findViewById(R.id.previous_button)
        next_button = findViewById(R.id.next_button)

        //Set click listener
        next_button.setOnClickListener {
            //Find next index
            dogViewModel.nextDog()
            //Update dog information
            update()
        }

        //Set click listener
        previous_button.setOnClickListener {
            //Find previous index
            dogViewModel.previousDog()
            //Update dog information
            update()
        }
        update()
    }

    //Store current index
    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        (super.onSaveInstanceState(savedInstanceState))
        savedInstanceState.putInt(KEY_INDEX, dogViewModel.currentIndex)
    }

    private fun update(){
        //Set name to text view
        val nameText = dogViewModel.findNameText
        name.setText(nameText)

        //Set corresponding image to each dog
        when(nameText)
        {
            R.string.breed1 -> image.setImageResource(R.mipmap.breed1)
            R.string.breed2 -> image.setImageResource(R.mipmap.breed2)
            R.string.breed3 -> image.setImageResource(R.mipmap.breed3)
            R.string.breed4 -> image.setImageResource(R.mipmap.breed4)
            R.string.breed5 -> image.setImageResource(R.mipmap.breed5)
        }

        //Set male height
        val maleHeightText = dogViewModel.findMaleHeightText
        male_height.setText(maleHeightText)

        //Set male weight
        val maleWeightText = dogViewModel.findMaleWeightText
        male_weight.setText(maleWeightText)

        //Set female height
        val femaleHeightText = dogViewModel.findFemaleHeightText
        female_height.setText(femaleHeightText)

        //Set female weight
        val femaleWeightText = dogViewModel.findFemaleWeightText
        female_weight.setText(femaleWeightText)

        //Set life expectancy
        val lifeText = dogViewModel.findLifeText
        life.setText(lifeText)

        //Set temperament
        val temperamentText = dogViewModel.findTemperamentText
        temperament.setText(temperamentText)

        //Set fun fact
        val factText = dogViewModel.findFactText
        fact.setText(factText)

    }
}