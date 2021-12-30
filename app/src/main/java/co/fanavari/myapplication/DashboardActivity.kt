package co.fanavari.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import co.fanavari.myapplication.databinding.ActivityDashboardBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /*setContentView(R.layout.activity_dashboard)

        val backB: AppCompatImageButton = findViewById(R.id.backB)
        val logoutB: AppCompatImageButton = findViewById(R.id.logoutB)

        val todoButton: MaterialButton = findViewById(R.id.todoB)

        val layoutCard: MaterialCardView = findViewById(R.id.layoutCards)

        layoutCard.setOnClickListener {
            Toast.makeText(this,"layout card is clicked",Toast.LENGTH_LONG).show()
        }

        backB.setOnClickListener {
            Toast.makeText(this,getString(R.string.class_name),Toast.LENGTH_LONG).show()
        }*/

        /*binding.layoutCards.setOnClickListener {
            showToast("this is layoutCard")

            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }*/

        binding.layoutCards.setOnClickListener {
            val mentorName: String = binding.textViewMentorName.text.toString()
            val intent = Intent(this,IntentExampleActivity::class.java)

            intent.putExtra("classNumber",17)
            intent.putExtra(Constants.MENTOR_NAME,mentorName)
            startActivity(intent)
        }

        binding.lifeCycleCard.setOnClickListener {
            val intent = Intent(this,LifeCycleActivity::class.java)
            startActivity(intent)
        }

        binding.componentsCard.setOnClickListener {
            val intent = Intent(this, ComponentExampleActivity::class.java)
            startActivity(intent)
        }

        binding.archCards.setOnClickListener {
            val intent = Intent(this, NavigationComponentExampleActivity::class.java)
            startActivity(intent)
        }

    }

    fun openLifeCycle(view: View){

    }
}
