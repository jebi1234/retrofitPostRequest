package com.example.loginapiretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteOrder;

public class MainActivity2 extends AppCompatActivity {

    TextView textView;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView = findViewById(R.id.text);
        btn = findViewById(R.id.button2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = getHotspotAdress();
                textView.setText(str.toString());
            }
        });
    }
    public String getHotspotAdress(){
        @SuppressLint("WifiManagerLeak")
        final WifiManager manager = (WifiManager)super.getSystemService(WIFI_SERVICE);
        final DhcpInfo dhcp = manager.getDhcpInfo();
        int ipAddress = dhcp.gateway;
        ipAddress = (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) ?
                Integer.reverseBytes(ipAddress) : ipAddress;
        byte[] ipAddressByte = BigInteger.valueOf(ipAddress).toByteArray();
        try {
            InetAddress myAddr = InetAddress.getByAddress(ipAddressByte);
            return myAddr.getHostAddress();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            Log.e("Wifi Class", "Error getting Hotspot IP address ", e);
        }
        return "null";
    }
}