package com.longing.geoquiz

import androidx.annotation.StringRes

/**
 * 模型类都是用来保存数据的
 * 对于数据类，编译器就会定义像，equals(),hashCode(),toString()这样的方法
 */
data class Question(@StringRes val textResId: Int, val answer: Boolean)