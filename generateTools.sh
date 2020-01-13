#!/usr/bin/env bash
set -e
set -x

gw() {
  name="$1"
  shift 1
  echo "----------- building $name -----------"
  ./gradlew --stacktrace "$@"
  echo "----------- $name done -----------"
}

gwbld() {
  gw "$2" ":$1:assemble" #"$1:bundle${3}ReleaseAar"
}

gwloc() {
  gw "$2" ":$1:publishToMavenLocal"
}

gwpub() {
  gw "$2" ":$1:publish"
}
