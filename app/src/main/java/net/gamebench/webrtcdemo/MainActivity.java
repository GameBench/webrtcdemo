package net.gamebench.webrtcdemo;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    ToggleButton boost;
    TextView boost_status;
    ConnectivityManager connectivityManager;
    ConnectivityManager.NetworkCallback netCallback = new ConnectivityManager.NetworkCallback() {

        @Override
        public void onAvailable(Network network) {
            completeBoost(network);
        }

        @Override
        public void onUnavailable() {
            completeBoost(null);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        boost = findViewById(R.id.boost);
        boost_status = findViewById(R.id.boost_status);
        if (Build.VERSION.SDK_INT < 34) {
            setBoostResult(false, "Android version is too old.");
            boost.setEnabled(false);
        } else {
            boost.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    boost.setEnabled(false);
                    boost_status.setText("");
                    if (isChecked) {
                        NetworkRequest request = new NetworkRequest.Builder()
                                .addCapability(NetworkCapabilities.NET_CAPABILITY_PRIORITIZE_LATENCY)
                                .build();
                        try {
                            connectivityManager.requestNetwork(request, netCallback, 15000);
                        } catch (Exception e) {
                            setBoostResult(false, e.getLocalizedMessage());
                        }
                    } else {
                        connectivityManager.bindProcessToNetwork(null);
                        connectivityManager.unregisterNetworkCallback(netCallback);
                    }
                }
            });
        }
    }


    public void xboxClicked(View view) {
        launchBrowser("https://www.xbox.com/en-us/play");
    }

    public void geforceClicked(View view) {
        launchBrowser("https://play.geforcenow.com/");
    }

    private void launchBrowser(String url) {
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.setAction(url);
        startActivity(intent);
    }

    void completeBoost(Network network) {
        connectivityManager.unregisterNetworkCallback(netCallback);
        connectivityManager.bindProcessToNetwork(network);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (network != null) {
                    setBoostResult(true, "5G Boost connected OK!");
                } else {
                    setBoostResult(false, "5G Boost is unavailable");
                }
            }
        });
    }


    public void setBoostResult(boolean success, String message) {
        if (!success) {
            boost.setChecked(false);
        }
        boost_status.setText(message);
        boost.setEnabled(true);
    }
}

