package net.gamebench.webrtcdemo;

import android.app.Application;

import com.gamebench.sdk.GameBench;

public class App extends Application {

    static {
        //GameBench.setUrl("https://web.staging.gamebench.net/");
        //GameBench.setUser("reuben@gamebench.net");
        //GameBench.setToken("2392be70e408ffb656ed8557111c9c0a");

        GameBench.setUrl("https://web.gamebench.net/");
        GameBench.setUser("aaaagb01@gmail.com");
        GameBench.setToken("ce1660d6c2ee4de742178c0e7306ba6f");



        //GameBench.setUrl("https://web.gamebench.net/");
        //GameBench.setUser("project_76af5a4f-7861-4b6b-8e84-809a67e24506");
        //GameBench.setToken("5c886757528e6d616a24b36127e3e947");

        GameBench.setAutoSessionEnabled(true);
        GameBench.setVerboseLoggingEnabled(true);
        GameBench.setWebRtcStatsEnabled(true);
        GameBench.setCellNetworkInfoEnabled(true);
        GameBench.setAllowUx(true);
    }


}
