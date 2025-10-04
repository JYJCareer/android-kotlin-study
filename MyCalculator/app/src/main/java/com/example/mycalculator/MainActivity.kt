package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

     private var tvInput : TextView?=null
    var lastNumberic: Boolean = false
    var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput = findViewById(R.id.tvInput)


    }

    /**
     * 숫자 입력
     */
    fun OnDigit(view: View) {
        tvInput?.append((view as Button).text)
        lastNumberic = true
        lastDot =false
    }

    /**
     * 다 지우기
     */
    fun onClear(view: View) {
        tvInput?.text = ""    }

    /**
     * 소수점 체크
     */
    fun onDecimalPoint(view: View) {
        if (lastNumberic && !lastDot) { //앞에 숫자가 있고, 연속적인 ...을 방지하기 위함
            tvInput?.append(".")
            lastNumberic = false
            lastDot = true


        }
    }

    /**
     * 연산자
     */
    fun onOperator(view: View) {
        /*vInput 와text 가 비어있지 않으면 실행 it 실행*/
        /*let : 두개 nullable체크  */
        tvInput?.text?.let {
            if (lastNumberic && !isOperatorAdded(it.toString())) { //연산자
             tvInput?.append((view as  Button).text)
                lastNumberic = false
                lastDot = false
            }

        }
    }

    fun onEqual( view: View) {
        if (lastNumberic) {
            var tvValue = tvInput?.text.toString()
            var prefix = ""

            try {
                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                if (tvValue.contains("-")) {
                    val splitValue  =tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()) {
                        one = prefix+one // -99
                    }

                    tvInput?.text = removeZeroAfterDot((one.toDouble() -two.toDouble()).toString())

                }
                else if (tvValue.contains("+")) {
                    val splitValue  =tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()) {
                        one = prefix+one // -99
                    }

                    tvInput?.text = removeZeroAfterDot((one.toDouble() +  two.toDouble()).toString())

                }
               else if (tvValue.contains("*")) {
                    val splitValue  =tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()) {
                        one = prefix+one // -99
                    }

                    tvInput?.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())

                } else  if (tvValue.contains("/")) {
                    val splitValue  =tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()) {
                        one = prefix+one // -99
                    }

                    tvInput?.text = removeZeroAfterDot((one.toDouble() /two.toDouble()).toString())

                }





            } catch (e: ArithmeticException) {
             e.printStackTrace()
            }
        }

    }

    private fun removeZeroAfterDot(result: String): String {
        var value = result
        if (result.contains(".0")) {
            value = result.substring(0, result.length - 2)
        }
        return value
    }


    /**
     * 연산자가 추가될 수 있는가
     */
    private fun isOperatorAdded(value: String): Boolean {
//        -4
        return if (value.startsWith("-")) { // 음수
            false
        } else { //Oper
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")

        }


    }
}