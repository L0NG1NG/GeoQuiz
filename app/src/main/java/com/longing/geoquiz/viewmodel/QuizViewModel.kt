package com.longing.geoquiz.viewmodel

import androidx.lifecycle.ViewModel
import com.longing.geoquiz.Question
import com.longing.geoquiz.R

class QuizViewModel : ViewModel() {

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_ocean, false),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    var currentIndex = 0
    var isCheater = false
    var cheatCounts = 0


    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
        isCheater = false
        if (currentIndex == 0) {
            cheatCounts = 0
        }
    }
}