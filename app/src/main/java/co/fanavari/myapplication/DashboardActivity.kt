package co.fanavari.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val backB: AppCompatImageButton = findViewById(R.id.backB)
        val logoutB: AppCompatImageButton = findViewById(R.id.logoutB)

        val todoButton: MaterialButton = findViewById(R.id.todoB)

        val layoutCard: MaterialCardView = findViewById(R.id.layoutCards)

        layoutCard.setOnClickListener {
            Toast.makeText(this,"layout card is clicked",Toast.LENGTH_LONG).show()
        }

        backB.setOnClickListener {
            Toast.makeText(this,getString(R.string.class_name),Toast.LENGTH_LONG).show()
        }



    }
}