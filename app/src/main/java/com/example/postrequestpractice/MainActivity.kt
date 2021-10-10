package com.example.postrequestpractice

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //declare UI elements in main activity
        val addButton = findViewById<Button>(R.id.btAdd)

        createApiInterface()

        addButton.setOnClickListener{
            val intent = Intent(this, AddUserActivity::class.java)
            startActivity(intent)
        }

    }

    fun createApiInterface()
    {
        //show progress Dialog
        val progressDialog = ProgressDialog(this@MainActivity)
        progressDialog.setMessage("Please wait")
        progressDialog.show()

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        val call: Call<Users?>? = apiInterface!!.getUsersInfo()

        call?.enqueue(object : Callback<Users?> {
            override fun onResponse(
                call: Call<Users?>?,
                response: Response<Users?>
            ) {
                progressDialog.dismiss()
                val resource: Users? = response.body()

                var displayResponse=""
                for (user in resource!!) {
                    displayResponse += user.name +"\n"+user.location+"\n\n\n"

                }
                val  dataTextView= findViewById<TextView>(R.id.tvData)
                //println(displayResponse)
                dataTextView.text = displayResponse
            }

            override fun onFailure(call: Call<Users?>, t: Throwable?) {
                Toast.makeText(applicationContext,"Unable to load data!", Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()
                call.cancel()
            }
        })
    }
}