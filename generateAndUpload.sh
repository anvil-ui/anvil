#!/usr/bin/env bash
set -e
set -x

./gradlew --stacktrace \
    :anvil:generateSDKDSL \
    :anvil:assembleRelease && \
    ./gradlew --stacktrace :anvil:bintrayUpload
echo "----------- sdk done -----------"
./gradlew --stacktrace \
    :anvil-appcompat-v7:generateAppCompatv7DSL \
    :anvil-appcompat-v7:assembleRelease && \
    ./gradlew --stacktrace :anvil-appcompat-v7:bintrayUpload
echo "----------- appcompat done -----------"
./gradlew --stacktrace \
    :anvil-design:generateMaterialDSL \
    :anvil-design:assembleRelease && \
    ./gradlew --stacktrace :anvil-design:bintrayUpload
echo "----------- design done -----------"
./gradlew --stacktrace \
    :anvil-gridlayout-v7:generateGridLayoutv7DSL \
    :anvil-gridlayout-v7:assembleRelease && \
    ./gradlew --stacktrace :anvil-gridlayout-v7:bintrayUpload
echo "----------- gridlayout done -----------"
./gradlew --stacktrace \
    :anvil-recyclerview-v7:generateRecyclerViewv7DSL \
    :anvil-recyclerview-v7:assembleRelease && \
    ./gradlew --stacktrace :anvil-recyclerview-v7:bintrayUpload
echo "----------- recyclerview done -----------"
./gradlew --stacktrace \
    :anvil-cardview-v7:generateCardViewv7DSL \
    :anvil-cardview-v7:assembleRelease && \
    ./gradlew --stacktrace :anvil-cardview-v7:bintrayUpload
echo "----------- cardview done -----------"
./gradlew --stacktrace \
    :anvil-support-v4:generateSupportCoreUiDSL \
    :anvil-support-v4:assembleRelease && \
    ./gradlew --stacktrace :anvil-support-v4:bintrayUpload
echo "----------- supportv4 done -----------"

# TODO: constraintlayout
# TODO: yogalayout
