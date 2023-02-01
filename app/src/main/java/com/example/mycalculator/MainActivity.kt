package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var disInput: TextView? = null
    private var insDecimal: Boolean = false
    private var lastNumeric: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        disInput = findViewById(R.id.display)
    }
        fun onDigit(view:View) {
            disInput?.append((view as Button).text)
            lastNumeric=true
        }

        fun clear(view:View) {
            disInput?.text=""
            insDecimal = false
        }

        fun onDecimalPoint(view:View) {
            if (lastNumeric && insDecimal==false) {
                disInput?.append((view as Button).text)
                insDecimal=true
                lastNumeric = false
            }
        }

        fun onOperator(view:View) {
            disInput?.text.let {
                if (lastNumeric && !isOperatorAdded(it.toString())) {
                    disInput?.append((view as Button).text)
                    lastNumeric=false
                    insDecimal=false
                }
            }
        }

        fun onEqual(view: View) {
            if(lastNumeric) {
                var disValue = disInput?.text.toString()
                var prefix =" "
                try {
                    if (disValue.startsWith("-")) {
                        prefix = "-"
                        disValue=disValue.substring(1)
                    }
                    if (disValue.contains("-")) {
                        val splitValue = disValue.split("-")
                        var one = splitValue[0]
                        var two = splitValue[1]
                        if (prefix.isNotEmpty()) {
                            one=prefix+one
                        }
                        disInput?.text = (one.toDouble() - two.toDouble()).toString()
                    }
                    else if (disValue.contains("+")) {
                        val splitValue = disValue.split("+")
                        var one = splitValue[0]
                        var two = splitValue[1]
                        if (prefix.isNotEmpty()) {
                            one=prefix+one
                        }
                        disInput?.text = (one.toDouble() + two.toDouble()).toString()
                    }
                    else if (disValue.contains("x")) {
                        val splitValue = disValue.split("x")
                        var one = splitValue[0]
                        var two = splitValue[1]
                        if (prefix.isNotEmpty()) {
                            one=prefix+one
                        }
                        disInput?.text = (one.toDouble() * two.toDouble()).toString()
                    }
                    else if (disValue.contains("/")) {
                        val splitValue = disValue.split("/")
                        var one = splitValue[0]
                        var two = splitValue[1]
                        if (prefix.isNotEmpty()) {
                            one=prefix+one
                        }
                        disInput?.text = (one.toDouble() / two.toDouble()).toString()
                    }
                }catch(e: ArithmeticException) {
                    e.printStackTrace()
                }
            }
        }

        private fun isOperatorAdded(value: String): Boolean {
            return if (value.startsWith("-")) {
                return false
            }
            else {
                value.contains("/")
                        ||value.contains("+")
                        ||value.contains("-")
                        ||value.contains("*")
            }
        }
    }