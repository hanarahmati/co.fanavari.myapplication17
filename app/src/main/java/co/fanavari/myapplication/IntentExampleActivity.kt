package co.fanavari.myapplication

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import co.fanavari.myapplication.databinding.ActivityIntentExampleBinding

class IntentExampleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIntentExampleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityIntentExampleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle: Bundle? = intent.extras

        bundle?.let {
            val mentorName = bundle.getString(Constants.MENTOR_NAME) + " class number " +
                    bundle.getInt("classNumber")

            binding.textViewMentorNameClassNumber.text = mentorName
            showToast(mentorName)

        }

        val openIntentForResultExampleActivity =
           registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
               if( it.resultCode == Activity.RESULT_OK){
                   showToast(it.data?.getStringExtra("message").toString())
               }
               else
                   showToast("empty data")
           }
        binding.buttonStartActivityWithResult.setOnClickListener {
            openIntentForResultExampleActivity.launch(
                Intent(this, IntentForResultExampleActivity::class.java).apply {
                    putExtra("ID", "1")
                }
            )
        }

        binding.buttonOpenUrl.setOnClickListener {
            openURL()
        }

        binding.buttonCreateAlarm.setOnClickListener {
            createAlarm("new alarm",6,25)
        }

        binding.buttonSendEmail.setOnClickListener {
            composeEmail(arrayOf("hana.rahmati@gmail.com","fanavari.co@gamil.com",
                "hanane.rahmatzehi@gmail.com"),"connect with us")
        }
    }

    private fun openURL(){
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://fanavari.co/android-course/"))
        //if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        //}
    }

    fun createAlarm(message: String, hour: Int, minutes: Int) {
        val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, message)
            putExtra(AlarmClock.EXTRA_HOUR, hour)
            putExtra(AlarmClock.EXTRA_MINUTES, minutes)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun composeEmail(addresses: Array<String>, subject: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, addresses)
            putExtra(Intent.EXTRA_SUBJECT, subject)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}