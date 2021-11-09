package com.example.loginapiretrofit

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.DhcpInfo
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.Formatter.formatIpAddress
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.apache.commons.codec.binary.Base64
import java.net.Inet4Address
import java.net.InetAddress
import java.net.NetworkInterface
import java.net.SocketException
import java.nio.ByteOrder
import java.util.*

class MainActivity : AppCompatActivity() {


    var Edittext: EditText? = null

    var Textview:TextView? = null
    var Textvie:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Edittext = findViewById<EditText>(R.id.number)

        Textview = findViewById<TextView>(R.id.textView)
        Textvie = findViewById<TextView>(R.id.textView2)

        val Submit = findViewById<Button>(R.id.button)

        Submit.setOnClickListener {

            var edit = Edittext.toString()
            if (edit.isEmpty()) {
                Toast.makeText(this,"Please enter your MobileNumber",Toast.LENGTH_SHORT).show()
            }
            login()
            val ipsddress:String? =  getLocalIpAddress()
            Toast.makeText(this,ipsddress.toString(),Toast.LENGTH_LONG).show()
            intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)

        }

    }
    fun getLocalIpAddress(): String? {
        try {
            val en = NetworkInterface.getNetworkInterfaces()
            while (en.hasMoreElements()) {
                val intf = en.nextElement()
                val enumIpAddr: Enumeration<InetAddress> = intf.inetAddresses
                while (enumIpAddr.hasMoreElements()) {
                    val inetAddress: InetAddress = enumIpAddr.nextElement()
                    if (!inetAddress.isLoopbackAddress() && inetAddress is Inet4Address) {
                        return inetAddress.getHostAddress()
                    }
                }
            }
        } catch (ex: SocketException) {
            ex.printStackTrace()
        }
        return null
    }

    private fun login() {

        val request =
            LoginRequest(
                "Android",
                "f6f2d0e2c2fcc9eg",
                "1.0",
                "en",
                "1.0",
                "12.23.23",
                "125.25325",
                "125.25328",
                "NA",
                "NA",
                "SmasungG2",
                "125.25.325",
                "1.0.5",
                "RIDER",
                "LOGINWITHOTP"
            )
        val service = RetrofitClient().webService
        CoroutineScope(Dispatchers.IO).launch {
            try {

                var auth = Edittext?.text.toString()

                val basicAuth = "Basic " + String(Base64().encode(auth.toByteArray()))

                val response = service.login(request,basicAuth )

                withContext(Dispatchers.Main) {
                    when {
                        response!!.isSuccessful -> {
                            // doSome(response.body())

                            doSome2(response.body())

                            println("==loginresponse==${response.body()}")

                        }
                        else -> {

                            Toast.makeText(this@MainActivity,"server error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            } finally {
                Log.e("TAG", "sendRequest: ")
            }
        }
    }


    private fun doSome2(body: LoginResponse?) {
        Log.e("TAG Ranjith", "doSome: ${body!!.status}")

        if (body.status.equals("1013") ){
            Textview?.setText(
                body.status.toString()+
                        "\n"+body.timeStamp.toString()+
                        "\n"+body.token.toString()+
                        "\n"+body.type.toString())
        }
        else {
            Textview?.setText(
                body.status.toString()+
                        "\n"+body.timeStamp.toString()+
                        "\n"+body.token.toString()+
                        "\n"+body.type.toString())
        }
    }
}