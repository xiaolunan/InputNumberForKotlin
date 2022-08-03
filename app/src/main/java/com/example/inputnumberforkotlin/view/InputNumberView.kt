package com.example.inputnumberforkotlin.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import com.example.inputnumberforkotlin.R
import com.example.inputnumberforkotlin.lintener.InputNumListener

/**
 *  Created by renchunlin on 2022/8/3
 */
class InputNumberView : LinearLayout {

    var inputNumListener: InputNumListener? = null
    private var inputNumber = 0
    private var edit_input: EditText? = null
    private var numberMin = 0
    private var numberMax = 0
    private var defaultNumber = 0
    private var viewPackage: LinearLayout? = null
    private var disable = false
    private lateinit var lessBtn: Button
    private lateinit var plusBtn: Button
    private var step = 0

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        LayoutInflater.from(context).inflate(R.layout.input_number_layout, this)
        initView()
        initAttrs(context, attrs)
    }

    private fun initAttrs(context: Context?, attrs: AttributeSet?) {
        val typedArray =
            context?.obtainStyledAttributes(attrs, R.styleable.InputNumberView)
        numberMin = typedArray!!.getInt(R.styleable.InputNumberView_numberMin, 0)
        numberMax = typedArray.getInt(R.styleable.InputNumberView_numberMax, 10)

        defaultNumber = typedArray.getInt(R.styleable.InputNumberView_defaultNumber, 0)
        setNumber()

        disable = typedArray.getBoolean(R.styleable.InputNumberView_btnDisable, false)
        initDisable()

        step = typedArray.getInt(R.styleable.InputNumberView_step, 1)
    }

    private fun initView() {
        viewPackage = findViewById(R.id.viewPackage)
        lessBtn = findViewById(R.id.LessBtn)
        plusBtn = findViewById(R.id.plusBtn)
        edit_input = findViewById(R.id.edit_input)

        //减号事件

        //减号事件
        lessBtn.setOnClickListener(OnClickListener { //最小值
            inputNumber -= step
            if (inputNumber <= numberMin) {
                inputNumber = numberMin
            }
            setInputNumber(inputNumber)
            initGetNum()
        })

        //加号事件

        //加号事件
        plusBtn.setOnClickListener(OnClickListener { //最大值
            inputNumber += step
            if (inputNumber >= numberMax) {
                inputNumber = numberMax
            }
            setInputNumber(inputNumber)
            initGetNum()
        })
    }

    private fun getInputNumber(): Int {
        return inputNumber
    }

    private fun setInputNumber(inputNumber: Int) {
        this.inputNumber = inputNumber
    }

    private fun initGetNum() {
        edit_input!!.setText(getInputNumber().toString())
        inputNumListener?.inputNumber(getInputNumber())
    }

    fun setNumberMin(numberMin: Int) {
        this.numberMin = numberMin
    }

    fun setNumberMax(numberMax: Int) {
        this.numberMax = numberMax
    }

    fun setDefaultNumber(defaultNumber: Int) {
        this.defaultNumber = defaultNumber
        setNumber()
    }

    private fun setNumber() {
        //设置默认值
        setInputNumber(defaultNumber)
        //刷线ui
        initGetNum()
    }

    fun setDisable(disable: Boolean) {
        this.disable = disable
        initDisable()
    }

    //设置禁用状态
    private fun initDisable() {
        if (disable) {
            lessBtn.setBackgroundResource(R.drawable.less_disable_bg)
            lessBtn.isEnabled = false
            plusBtn.setBackgroundResource(R.drawable.plus_disable_bg)
            plusBtn.isEnabled = false
            edit_input!!.setBackgroundResource(R.drawable.edit_disable_bg)
            edit_input!!.isEnabled = false
        }
    }

    fun setStep(step: Int) {
        this.step = step
    }
}