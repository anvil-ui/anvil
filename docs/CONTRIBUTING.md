# Building

1. You should have Android SDK installed.
2. Make sure you have Android SDK Platforms 15, 19, 21, 28 installed.
3. Provide either environment variable called `ANDROID_HOME`
   or `local.properties` with `sdk.dir` pointing to Android SDK
   root.
4. Run `./generateDsl.sh`

If you have `PATH` set up properly, you can run the following commands
to ensure Android SDK is ready:

```sh
sdkmanager --licenses
# Accept everything needed if you agree with terms
sdkmanager \
    "platforms;android-15" \
    "platforms;android-19" \
    "platforms;android-21" \
    "platforms;android-28" \
    "build-tools;28.0.3" \
    "extras;android;m2repository" \
    "platform-tools" \
    "tools"
```
