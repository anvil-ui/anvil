#!/bin/sh

[ -z "$ANDROID_HOME" ] && (echo "\$ANDROID_HOME is not set"; exit 1)

echo "Building Gen.java..."
javac Gen.java || exit 1

for APILEVEL in 4 10 15 21 ; do
	echo "Generating bindings for API level $APILEVEL"
	mkdir -p ../src/main/java/trikita/anvil/v$APILEVEL
	while read line ; do
		if [ "x$line" = "xpackage" ] ; then
			echo "package trikita.anvil.v$APILEVEL;"
		elif [ "x$line" = "x}" ]; then
			java Gen $ANDROID_HOME/platforms/android-$APILEVEL/android.jar
			echo "}"
		else
			echo $line
		fi
	done < Props.in.java > ../src/main/java/trikita/anvil/v$APILEVEL/Props.java
done
