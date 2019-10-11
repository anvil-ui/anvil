#!/usr/bin/env bash
./gradlew :anvil:generateSDK15DSL --stacktrace
./gradlew :anvil:generateSDK19DSL --stacktrace
./gradlew :anvil:generateSDK21DSL --stacktrace
echo "----------- sdk done -----------"
./gradlew :anvil-appcompat-v7:generateAppCompatv7DSL --stacktrace
echo "----------- appcompat done -----------"
./gradlew :anvil-design:generateMaterialDSL --stacktrace
echo "----------- material done -----------"
./gradlew :anvil-gridlayout-v7:generateGridLayoutv7DSL --stacktrace
echo "----------- gridlayout done -----------"
./gradlew :anvil-recyclerview-v7:generateRecyclerViewv7DSL --stacktrace
echo "----------- recyclerview done -----------"
./gradlew :anvil-support-v4:generateSupportCoreUiDSL --stacktrace
echo "----------- supportv4 done -----------"
