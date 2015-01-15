#!/bin/sh

[ -z "$ANDROID_HOME" ] && (echo "\$ANDROID_HOME is not set"; exit 1)

echo "Building Gen.java..."
javac Gen.java || exit 1

APILEVEL=4 # 10, 15, 21

while read line ; do
	if [ "x$line" = "x//////" ]; then
		java Gen $ANDROID_HOME/platforms/android-$APILEVEL/android.jar
	else
		echo $line
	fi
done < Props$APILEVEL.in.java > ../src/main/java/trikita/v/Props$APILEVEL.java

