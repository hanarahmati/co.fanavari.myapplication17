package co.fanavari.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.fanavari.myapplication.databinding.ActivityIntentForresultExampleBinding

class IntentForResultExampleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIntentForresultExampleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityIntentForresultExampleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSendResult.setOnClickListener {
            val intent = Intent()
            val message = binding.editTextResult.text.toString()
            intent.putExtra("message", message)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}