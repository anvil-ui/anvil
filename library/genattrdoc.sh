#!/bin/sh

for api in v10 v15 ; do
	grep "public static" src/main/java/trikita/anvil/$api/Attrs.java |
		sed -e 's/public static Render.AttrNode//' \
			-e 's/final //' -e 's/ arg//' -e 's/{//' -e 's/^ *//' \
			-e 's/^/* /' | sort > /tmp/attrs.$api
done

echo "# Helper generators: attributes and listeners"
echo
echo 'Helpers are stored in `trikita.anvil.v10.Attrs` and `trikita.anvil.v15.Attrs`'

echo
echo "## Attributes (Andorid 2.3.3, API 10)"
echo

cat /tmp/attrs.v10 | grep -v "^* on"

echo
echo "## Listeners (Andorid 2.3.3, API 10)"
echo

cat /tmp/attrs.v10 | grep "^* on"

echo
echo "## Attributes (Andorid 4.0.3, API 15)"
echo

comm -23 /tmp/attrs.v15 /tmp/attrs.v10  | grep -v "^* on"

echo
echo "## Listeners (Andorid 4.0.3, API 15)"
echo

comm -23 /tmp/attrs.v15 /tmp/attrs.v10  | grep  "^* on"

