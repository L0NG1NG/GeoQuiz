package com.longing.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

//就是为了避免来自其他应用extra冲突
private const val EXTRA_ANSWER_IS_TRUE = "com.longing.geoquiz.answer_is_true"
const val EXTRA_ANSWER_SHOWN = "com.longing.geoquiz.answer_shown"
const val EXTRA_CHEAT_COUNTS = "com.longing.geoquiz.cheat_counts"

private const val MAX_CHEAT_COUNTS = 3

class CheatActivity : AppCompatActivity() {
    private lateinit var answerTextView: TextView
    private lateinit var showAnswerButton: Button
    private lateinit var apiLevelTextView: TextView
    private lateinit var remnantCountsTextView: TextView


    private var answerIsTrue = false
    private var cheatCounts = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)
        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)
        cheatCounts = intent.getIntExtra(EXTRA_CHEAT_COUNTS, 0)

        answerTextView = findViewById(R.id.answer_text_view)
        showAnswerButton = findViewById(R.id.show_answer_button)
        apiLevelTextView = findViewById(R.id.api_level_text_view)
        remnantCountsTextView = findViewById(R.id.remnant_counts_text_view)


        showAnswerButton.isEnabled = cheatCounts < MAX_CHEAT_COUNTS
        showAnswerButton.setOnClickListener {
            val answerText = when {
                answerIsTrue -> R.string.true_button
                else -> R.string.false_button
            }
            answerTextView.setText(answerText)

            setAnswerShowResult(true)
        }

        val remnantCounts = "剩余作弊次数 ${MAX_CHEAT_COUNTS - cheatCounts}"
        remnantCountsTextView.text = remnantCounts

        val apiLevel = "API Level ${Build.VERSION.SDK_INT}"
        apiLevelTextView.text = apiLevel

    }

    private fun setAnswerShowResult(isAnswerShown: Boolean) {
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)

        }
        setResult(Activity.RESULT_OK, data)

    }


    companion object {
        fun newIntent(packetContext: Context, answerIsTrue: Boolean, cheatCounts: Int): Intent {
            return Intent(packetContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
                putExtra(EXTRA_CHEAT_COUNTS, cheatCounts)
            }

        }
    }
}