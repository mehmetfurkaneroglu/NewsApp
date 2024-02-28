package com.eroglu.newsapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.eroglu.newsapp.R


class NoInternetActivity : AppCompatActivity() {

    private lateinit var internetLayout: RelativeLayout
    private lateinit var noInternetLayout: RelativeLayout

    private lateinit var tryAgainButton: Button

    private lateinit var goNewsButton: Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_internet)
        supportActionBar?.hide()

        internetLayout = findViewById(R.id.internetLayout)
        noInternetLayout = findViewById(R.id.noInternetLayout)
        tryAgainButton = findViewById(R.id.try_again_button)
        goNewsButton = findViewById(R.id.go_news_button)

        drawLayout()

        tryAgainButton.setOnClickListener {
            drawLayout()
        }

        if (isNetworkAvailable() == true){
            val intent = Intent(this, NewsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)

        return (capabilities != null && capabilities.hasCapability(NET_CAPABILITY_INTERNET))

    }

    private fun drawLayout() {
        if (isNetworkAvailable()) {
            internetLayout.visibility = VISIBLE
            noInternetLayout.visibility = GONE
            goNewsButton.setOnClickListener {
                val intent = Intent(this, NewsActivity::class.java)
                startActivity(intent)
                this.finish()
            }
            val intent = Intent(this, NewsActivity::class.java)
            startActivity(intent)
            this.finish()

        } else {
            noInternetLayout.visibility = VISIBLE
            internetLayout.visibility = GONE
        }
    }


}