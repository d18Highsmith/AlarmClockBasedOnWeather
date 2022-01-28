package com.example.alarmclockbasedonweather

import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.net.URL


class MainActivity : AppCompatActivity() {

    val CITY: String = "dhaka,bd"
    val API: String = "722ddb33c87d7eaca6217198f1ec38fe"
    var loader: ProgressBar? = null
    val viewModelTemperature: TempViewModel by viewModels()
    val viewModelTimeDelay: DelayViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpTabBar()
        loader = findViewById<ProgressBar>(R.id.loader)
        weatherTask().execute()
    }
    // setting up fragments
    private fun setUpTabBar() {
        val adapter = TabPageAdapter(this, tabLayout.tabCount)
        viewPager.adapter = adapter

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(p0: TabLayout.Tab?) {}
            override fun onTabReselected(p0: TabLayout.Tab?) {}
        })
    }

    //getting API working
    inner class weatherTask() : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            loader?.visibility= View.VISIBLE
        }

        override fun doInBackground(vararg p0: String?): String? {
            var response: String?
            try {
                response =
                    URL("https://api.openweathermap.org/data/2.5/weather?q=$CITY&units=metric&appid=$API")
                        .readText(Charsets.UTF_8)
            } catch (e: Exception) {
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                val jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject("main")
                var temp = main.getString("temp") + "°C"
                findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
                findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.VISIBLE
                findViewById<TextView>(R.id.temp).text = temp
                var tempFloat = main.getString("temp").toFloat()
                setCurrentTemp(tempFloat)
                var timeDelay = 0
                setTimeDelay(timeDelay)


            } catch (e: java.lang.Exception) {
                findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
                findViewById<TextView>(R.id.errorText).visibility = View.VISIBLE
            }
        }

    }
     fun setCurrentTemp(currentTemp: Float) {
        viewModelTemperature.setCurrentTemp(currentTemp)
    }
    fun setTimeDelay(timeDelay: Int){
        viewModelTimeDelay.setTimeDelay(timeDelay)
    }
}


