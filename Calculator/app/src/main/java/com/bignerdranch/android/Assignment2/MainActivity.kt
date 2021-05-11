package com.bignerdranch.android.Assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity()
{
    //Declare variables for number buttons
    private lateinit var zero: Button
    private lateinit var one: Button
    private lateinit var two: Button
    private lateinit var three: Button
    private lateinit var four: Button
    private lateinit var five: Button
    private lateinit var six: Button
    private lateinit var seven: Button
    private lateinit var eight: Button
    private lateinit var nine: Button

    //Declare variables for operator and decimal point buttons
    private lateinit var plus: Button
    private lateinit var minus: Button
    private lateinit var mult: Button
    private lateinit var divide: Button
    private lateinit var dot: Button
    private lateinit var equal: Button

    //Declare variables for functional keys
    private lateinit var delete: Button
    private lateinit var clear: Button

    //Declare variable for screen
    private lateinit var display: TextView

    //Declare global variables
    private var result = 0.0
    private var expression = ""
    private var allowDot = true
    private var needBracket = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Find button by id
        zero = findViewById(R.id.zero_button)
        one = findViewById(R.id.one_button)
        two = findViewById(R.id.two_button)
        three = findViewById(R.id.three_button)
        four = findViewById(R.id.four_button)
        five = findViewById(R.id.five_button)
        six = findViewById(R.id.six_button)
        seven = findViewById(R.id.seven_button)
        eight = findViewById(R.id.eight_button)
        nine = findViewById(R.id.nine_button)

        plus = findViewById(R.id.plus_button)
        minus = findViewById(R.id.minus_button)
        mult = findViewById(R.id.mult_button)
        divide = findViewById(R.id.divide_button)
        dot = findViewById(R.id.decimal_button)
        equal = findViewById(R.id.equal_button)

        delete = findViewById(R.id.delete_button)
        clear = findViewById(R.id.clear_button)

        display = findViewById(R.id.result)

        //Set click listener for buttons
        zero.setOnClickListener {
            displayText("0")
        }
        one.setOnClickListener {
            displayText("1")}
        two.setOnClickListener {
            displayText("2")}
        three.setOnClickListener {
            displayText("3")}
        four.setOnClickListener {
            displayText("4")}
        five.setOnClickListener {
            displayText("5")}
        six.setOnClickListener {
            displayText("6")}
        seven.setOnClickListener {
            displayText("7")}
        eight.setOnClickListener {
            displayText("8")}
        nine.setOnClickListener {
            displayText("9")}
        plus.setOnClickListener {
            displayText("+")}
        minus.setOnClickListener {
            displayText("-")}
        mult.setOnClickListener {
            displayText("*")}
        divide.setOnClickListener {
            displayText("/")}
        dot.setOnClickListener {
            displayText(".")}
        equal.setOnClickListener {
            calculate()}
        delete.setOnClickListener {
            deleteChar()}
        clear.setOnClickListener {
            clearExpression()}
    }

    override fun onStart(){
        super.onStart()
        Log.d(TAG,"onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    //This function is used to display user input and system output
    private fun displayText(message: String)
    {
        //Check if the maximum characters reached
        if (expression.length >= 20)
        {
            //Drop the characters that over the limit
            expression = expression.dropLast(expression.length - 20)
            //Set needBracket to false to omit the closing bracket
            needBracket = false
            //Terminate call earlier as the screen cannot display more user input
            return
        }

        //It is to check mathematics error that a number divided by zero
        if (expression == "Infinity")
        {
            //Call function to reset memory and screen to initial status
            clearExpression()
            return
        }

        //Declare variables for different types of input
        var isOperand = false
        var isOperator = false
        var isDot = false

        //When statement to check input type
        when (message)
        {
            "." -> isDot = true
            "+", "-", "*", "/" -> isOperator = true
            else -> isOperand = true
        }

        //This statement is to prevent any user input errors
        if (expression == "")
        {
            /**************************************************************************
             * If the equation is empty, do following input check and output to screen
             * a. the input is an operator, print 0 followed by the operator
             * b. the input is a decimal print, print 0. to screen
             * c. the input is a number, print that number
             **************************************************************************/
            if (isOperator)
            {
                expression = "0" + message
            }
            else if (isDot)
            {
                expression = "0."
                allowDot = false
            }
            else
            {
                //If the number entered is 0, omit it
                if (message == "0")
                {
                    return
                }
                else
                {
                    expression = message
                }
            }
        }
        else
        {
            //Find the last character of the equation
            var lastChar: (Char) = expression.last()

            //If the last character is a special case, do following check
            //If it is not a number and the input is not a number
            if ((lastChar < '0' || lastChar > '9') && !isOperand)
            {
                //The statement is to output brackets with minus sign or not
                if (message == "-" && lastChar != '.')
                {
                    expression += if (lastChar == '(') "-" else "(-"
                    //Set bracket flag to true
                    needBracket = true
                    //Display equation on screen
                    display.setText(expression)
                    return
                }
                else
                {
                    return
                }
            }

            //Check if the decimal point can be displayed
            if (!allowDot && isDot)
            {
                return
            }

            if (isOperator)
            {
                //Allow user to input dot
                allowDot = true
                //Check if a closing bracket needed
                if (needBracket)
                {
                    expression += ")"
                    needBracket = false
                }
            }

            //Set decimal point flag to false if the input is the dot
            if (isDot)
            {
                allowDot = false
            }
            //Expand current equation with new input
            expression += message
        }

        //Output equation
        display.setText(expression)

    }

    //This function is used to delete the last character of equation
    private fun deleteChar()
    {
        //Check some special cases first
        //If last character is a decimal point, update allowDot status
        if (expression.last() == '.')
        {
            allowDot = true
        }
        //Drop the last character
        expression = expression.dropLast(1)
        //Log.d(TAG, "Now is $expression")
        //IF the equation is empty or contains only zero, set it to empty and display 0 on screen
        if (expression == "" || expression == "0")
        {
            expression = ""
            display.setText("0")
        }
        else
        {
            //Output the updated equation
            display.setText(expression)
        }
    }

    //This function is used to perform calculations
    private fun calculate()
    {
        //Check if the equation is valid for calculation
        //If not, do nothing until user correct it
        if ((expression.last() > '9' || expression.last() < '0') && expression.last() != ')')
        {
            return
        }
        //Declare two lists to store operators and operands
        val operand = mutableListOf<Double>()
        val operator = mutableListOf<Char>()

        //Function call to read operators and operands into lists
        readExpression(operand, operator)

        //If the equation contains error after reading data, output error on screen and reset global variables
        if (operator.size + 1 != operand.size)
        {
            display.setText("ERROR")
            expression = ""
            result = 0.0
            allowDot = true
            needBracket = false
            return
        }

        //For debugging purpose
        Log.d(TAG, "test1 $operand")
        Log.d(TAG, "test1 $operator")

        //Perform calculation until everything in the list are used
        //Do multiplication and division first
        while (operator.contains('*') || operator.contains('/'))
        {
            //Perform each multiplication and division from left to the right
            for (i in 0 until operator.size)
            {
                if (operator[i] == '*' || operator[i] == '/')
                {
                    if (operator[i] == '*')
                    {
                        //Store result
                        operand[i] = operand[i] * operand[i+1]
                    }
                    else
                    {
                        //Store result
                        operand[i] = operand[i] / operand[i+1]
                    }

                    //Remove the operands and operator
                    operand.removeAt(i+1)
                    operator.removeAt(i)
                    break
                }
            }
        }

        //Index i
        var i = 0

        //Perform addition and substruction from left to the right
        while (operator.size > 0)
        {
            if (operator[i] == '+' || operator[i] == '-')
            {
                //Store result
                if (operator[i] == '+')
                {

                    operand[i] = operand[i] + operand[i+1]
                }
                else
                {
                    //Store result
                    operand[i] = operand[i] - operand[i+1]
                }
            }
            //Remove the operands and operator
            operand.removeAt(i+1)
            operator.removeAt(i)
            //Reset index as the operator and operands have been removed
            i = 0
        }
        //Store final result in global variable
        result = operand[0]
        //If the result is zero, set equation to empty and convert it to int, then string, and display it
        if (operand[0] == 0.0)
        {
            expression = ""
            display.setText(result.toInt().toString())
        }
        else
        {
            //If the result is a decimal number, convert it to float, then strring, and display it
            if (result > result.toInt() || result < result.toInt())
            {
                display.setText(result.toFloat().toString())
                expression = operand[0].toFloat().toString()
            }
            //If the result is an integer, convert it to int, then string, and display it
            else
            {
                display.setText(result.toInt().toString())
                expression = operand[0].toInt().toString()
            }
        }
        //Set bracket flag to false
        needBracket = false
        //Check if the decimal point is allowed
        allowDot = if (expression.contains(".")) false else true

        //For debugging
        Log.d(TAG, "test $operand")
        //Reset global variable result for future use.
        result = 0.0
    }

    //This function is to separate the operator and operand from equation
    private fun readExpression(operand: MutableList<Double>, operator: MutableList<Char>)
    {
        //If the equation starts with a negative number, add a opening bracket to this number
        if (expression[0] == '-')
        {
            expression = "(" + expression
        }
        //Temp variable to store operand
        var tempOperand = ""
        //Index for equation string
        var i = 0

        while (i < expression.length)
        {
            //Check if the number is negative
            if (expression[i] == '(')
            {
                //Read first character of the negative number into temp variable
                tempOperand += expression[i+1]
                //Increment counter by two to skip the bracket and first character
                i += 2
                continue
            }

            //If the current index is the end of negative number, increment index and continue
            if (expression[i] == ')')
            {
                i++
                continue
            }

            //If the current index is a number or decimal point
            if ((expression[i] >= '0' && expression[i] <= '9') || expression[i] == '.')
            {
                //Read it into temp variable
                tempOperand += expression[i]
            }
            //Else it is a operator
            else
            {
                //Read the operator into list
                operator.add(expression[i])
                //Read the operand into list and convert it into double type
                operand.add(tempOperand.toDouble())
                //Set temp variable to empty
                tempOperand = ""
            }
            //Increment  index
            i++
        }
        //Read the operand into list and convert it into double type
        operand.add(tempOperand.toDouble())
    }

    //This funtion is to clear everything on screen and memory
    private fun clearExpression()
    {
        //Set equation to empty
        expression = ""
        //Update globle variables to initial values
        result = 0.0
        allowDot = true
        needBracket = false
        //Display zero in screen
        display.setText("0")
    }
}