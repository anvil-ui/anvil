#!/usr/bin/env bash
set -e
set -x

./gradlew --stacktrace \
    :anvil:generateSDKDSL \
    :anvil:assembleRelease
echo "----------- sdk done -----------"
./gradlew --stacktrace \
    :anvil-appcompat-v7:generateAppCompatv7DSL \
    :anvil-appcompat-v7:assembleRelease 
echo "----------- appcompat done -----------"
./gradlew --stacktrace \
    :anvil-gridlayout-v7:generateGridLayoutv7DSL \
    :anvil-gridlayout-v7:assembleRelease
echo "----------- gridlayout done -----------"
./gradlew --stacktrace \
    :anvil-recyclerview-v7:generateRecyclerViewv7DSL \
    :anvil-recyclerview-v7:assembleRelease
echo "----------- recyclerview done -----------"
./gradlew --stacktrace \
    :anvil-cardview-v7:generateCardViewv7DSL \
    :anvil-cardview-v7:assembleRelease
echo "----------- cardview done -----------"
./gradlew --stacktrace \
    :anvil-design:generateMaterialDSL \
    :anvil-design:assembleRelease
echo "----------- design done -----------"
./gradlew --stacktrace \
    :anvil-support-v4:generateSupportCoreUiDSL \
    :anvil-support-v4:assembleRelease
echo "----------- supportv4 done -----------"

# TODO: constraintlayout
# TODO: yogalayout
