#!/usr/bin/env bash
set -e
set -x

./gradlew --stacktrace :anvil:generateSDKDSL
echo "----------- sdk done -----------"
./gradlew --stacktrace :anvil-appcompat-v7:generateAppCompatv7DSL
echo "----------- appcompat done -----------"
./gradlew --stacktrace :anvil-gridlayout-v7:generateGridLayoutv7DSL
echo "----------- gridlayout done -----------"
./gradlew --stacktrace :anvil-recyclerview-v7:generateRecyclerViewv7DSL
echo "----------- recyclerview done -----------"
./gradlew --stacktrace :anvil-cardview-v7:generateCardViewv7DSL
echo "----------- cardview done -----------"
./gradlew --stacktrace :anvil-design:generateMaterialDSL
echo "----------- design done -----------"
./gradlew --stacktrace :anvil-support-v4:generateSupportCoreUiDSL
echo "----------- supportv4 done -----------"

# TODO: constraintlayout
# TODO: yogalayout
