package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.R
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val input = findViewById<TextView>(R.id.input)
        val output = findViewById<TextView>(R.id.output)

        val clear = findViewById<Button>(R.id.C)
        val delete = findViewById<Button>(R.id.CE)

        val module = findViewById<Button>(R.id.module)
        val pluse = findViewById<Button>(R.id.sum)
        val minus = findViewById<Button>(R.id.sub)
        val mult = findViewById<Button>(R.id.mul)
        val division = findViewById<Button>(R.id.division)
        val equal = findViewById<Button>(R.id.equal)

        val zero = findViewById<Button>(R.id.b0)
        val one = findViewById<Button>(R.id.b1)
        val two = findViewById<Button>(R.id.b2)
        val tree = findViewById<Button>(R.id.b3)
        val four = findViewById<Button>(R.id.b4)
        val five = findViewById<Button>(R.id.b5)
        val six = findViewById<Button>(R.id.b6)
        val seven = findViewById<Button>(R.id.b7)
        val eight = findViewById<Button>(R.id.b8)
        val nine = findViewById<Button>(R.id.b9)
        val point = findViewById<Button>(R.id.button)

        zero.setOnClickListener { input.text = input.text.toString().plus(0) }
        one.setOnClickListener { input.text = input.text.toString().plus(1) }
        two.setOnClickListener { input.text = input.text.toString().plus(2) }
        tree.setOnClickListener { input.text = input.text.toString().plus(3) }
        four.setOnClickListener { input.text = input.text.toString().plus(4) }
        five.setOnClickListener { input.text = input.text.toString().plus(5) }
        six.setOnClickListener { input.text = input.text.toString().plus(6) }
        seven.setOnClickListener { input.text = input.text.toString().plus(7) }
        eight.setOnClickListener { input.text = input.text.toString().plus(8) }
        nine.setOnClickListener { input.text = input.text.toString().plus(9) }

        val toast = Toast.makeText(applicationContext, "Not allowed!!!", Toast.LENGTH_LONG)

        point.setOnClickListener {
            if (input.text.length != 0 && input.text[input.text.length - 1] != '.')
                input.text = input.text.toString().plus(".")
            else
                toast.show()
        }
        clear.setOnClickListener {
            if (check_input_number(input.text.toString())) {
                output.text = null
                input.text = null
            }
        }
        delete.setOnClickListener {
            var str: String = input.text.toString()

            if (!str.equals("")) {
                str = str.substring(0, str.length - 1)
                input.text = str
            } else
                toast.show()
        }
        pluse.setOnClickListener {
            if (check_last_element(input.text.toString()) && output.text.length == 0 && !check_operator_count(
                    input.text.toString()
                )
            )
                input.text = toCalculate(input.text.toString()).toString()
            if (output.text.length == 0) {
                if (check_input_number(input.text.toString()))
                    if (check_operator_count(input.text.toString()))
                        input.text = input.text.toString().plus("+")
            } else {
                input.text = output.text
                output.text = null
                input.text = input.text.toString().plus("+")
            }
        }
        minus.setOnClickListener {
            if (check_last_element(input.text.toString()) && output.text.length == 0 && !check_operator_count(
                    input.text.toString()
                )
            )
                input.text = toCalculate(input.text.toString()).toString()
            if (output.text.length == 0) {
                if (check_input_number(input.text.toString()))
                    if (check_operator_count(input.text.toString()))
                        input.text = input.text.toString().plus("−")
            } else {
                input.text = output.text
                output.text = null
                input.text = input.text.toString().plus("−")
            }
        }
        mult.setOnClickListener {
            if (check_last_element(input.text.toString()) && output.text.length == 0 && !check_operator_count(
                    input.text.toString()
                )
            )
                input.text = toCalculate(input.text.toString()).toString()
            if (output.text.length == 0) {
                if (check_input_number(input.text.toString()))
                    if (check_operator_count(input.text.toString()))
                        input.text = (input.text.toString().plus("*"))
            } else {
                input.text = output.text
                output.text = null
                input.text = input.text.toString().plus("*")
            }
        }
        division.setOnClickListener {
            if (check_last_element(input.text.toString()) && output.text.length == 0 && !check_operator_count(
                    input.text.toString()
                )
            )
                input.text = toCalculate(input.text.toString()).toString()
            if (output.text.length == 0) {
                if (check_input_number(input.text.toString()))
                    if (check_operator_count(input.text.toString()))
                        input.text = input.text.toString().plus("/")
            } else {
                input.text = output.text
                output.text = null
                input.text = input.text.toString().plus("/")
            }
        }
        module.setOnClickListener {
            if (check_input_number(input.text.toString())) {
                if (check_operator_count(input.text.toString()))
                    if (output.text.length == 0)
                        output.text = (input.text.toString().toFloat() / 100).toString()
                    else
                        output.text = (output.text.toString().toFloat() / 100).toString()
            }
        }
        equal.setOnClickListener {
            if (output.text.length != 0) {
                input.text = output.text
                output.text = null
            } else
                if (check_input_number(input.text.toString())) {
                    output.text = toCalculate(input.text.toString()).toString()
                }
        }
    }
}

fun check_input_number(num: String): Boolean {
    if (num.length != 0)
        return true
    return false
}

fun check_operator_count(text: String): Boolean {
    if (text.length != 0)
        text.forEach {
            if (it == '+' || it == '−' || it == '*' || it == '/')
                return false
        }
    return true
}

fun check_last_element(text: String): Boolean {
    if (text.length >= 2)
        if (text.last() != '+' || text.last() != '−' || text.last() != '*' || text.last() != '/')
            return true
    return false
}

fun toCalculate(text: String): Double {
    text.forEach {
        if (it == '+')
            return pluss(text)
        if (it == '−')
            return minus(text)
        if (it == '*')
            return multt(text)
        if (it == '/')
            return separr(text)
    }
    return 0.0
}

fun find_operator(text: String, element: String): Int = text.indexOf(element) // find operator

fun multt(text: String): Double {
    var first_num = ""
    var second_num = ""
    if (check_input_number(text)) {
        text.forEachIndexed { i, it ->
            if (i < find_operator(text, "*") && it != '*')
                first_num += it.toString()
            if (i > find_operator(text, "*") && it != '*')
                second_num += it.toString()
        }
        try {
            return (first_num.toFloat() * second_num.toFloat()).toString().toDouble()
        } catch (e: Exception) {
            return 0.0
        }
    }
    return 0.0
}

fun separr(text: String): Double {
    var first_num = ""
    var second_num = ""
    if (check_input_number(text)) {
        text.forEachIndexed { i, it ->
            if (i < find_operator(text, "/") && it != '/')
                first_num += it.toString()
            if (i > find_operator(text, "/") && it != '/')
                second_num += it.toString()
        }
        try {
            return (first_num.toFloat() / second_num.toFloat()).toString().toDouble()
        } catch (e: Exception) {
            return 0.0
        }
    }
    return 0.0
}

fun pluss(text: String): Double {
    var first_num = ""
    var second_num = ""
    if (check_input_number(text)) {
        text.forEachIndexed { i, it ->
            if (i < find_operator(text, "+") && it != '+')
                first_num += it.toString()
            if (i > find_operator(text, "+") && it != '+')
                second_num += it.toString()
        }
        try {
            return (first_num.toFloat() + second_num.toFloat()).toString().toDouble()
        } catch (e: Exception) {
            return 0.0
        }
    }
    return 0.0
}

fun minus(text: String): Double {
    var first_num = ""
    var second_num = ""
    if (check_input_number(text)) {
        text.forEachIndexed { i, it ->
            if (i < find_operator(text, "−") && it != '−')
                first_num += it.toString()
            if (i > find_operator(text, "−") && it != '−')
                second_num += it.toString()
        }
        try {
            return (first_num.toFloat() - second_num.toFloat()).toString().toDouble()
        } catch (e: Exception) {
            return 0.0
        }
    }
    return 0.0
}