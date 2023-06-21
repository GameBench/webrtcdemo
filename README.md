# GameBench WebRTC Stats Demo App

This is an Android app containing a WebView that connects a WebRTC stream. The GameBench SDK automatically detects usage of WebRTC and records performance stats into a GameBench session file which will be uploaded to the GameBench server once the app is sent to the background. 

## Prerequisites

- A copy of GameBench's SDK, which is generally distributed privately.
- Your GameBench credentials, which are 4 items of information:
  - GameBench server url
  - Your company ID
  - The email or username of your GameBench account
  - A hex 'token' generated in the GameBench dashboard.

## Before building

1. Open GameBench's SDK .zip and extract the file `GameBench.aar`. Copy this into the `app/lib` folder of this project.
2. Open this project's source file `MainActivity.kt` and replace the three placeholders there with your GameBench credentials.

## After the app is built

3. Deploy it to your Android phone and launch it. It should present a simple web page.
4. Press the green 'Start' button and let the test WebRTC video run for a while (e.g. 30 seconds). 
5. Send the app to background. The GameBench session data will be uploaded at this point. (NB: There is no UI indication of uploading, this is by design.)

## To get at the WebRTC stats 

The easiest method is to use our Python client API :

6. Clone our public repo at https://github.com/GameBench/api-client-python
7. As per the readme in the above repo, ensure the following environment variables contain your GameBench credentials: 
  - GB_API_BASE_URL
  - GB_COMPANY_ID
  - GB_API_EMAIL
  - GB_API_TOKEN
8. Run the sample `get_webrtc_stats.py' to get the raw JSON for the most recent session for the given app package:

    python get_webrtc_stats.py net.gamebench.webrtcdemo



![image](./app.png)

