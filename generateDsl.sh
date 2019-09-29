#!/usr/bin/env bash
./gradlew :anvil:generateSDK15DSL
./gradlew :anvil:generateSDK19DSL
./gradlew :anvil:generateSDK21DSL
echo "----------- sdk done -----------"
./gradlew :anvil-appcompat-v7:generateAppCompatv7DSL
echo "----------- appcompat done -----------"
./gradlew :anvil-design:generateMaterialDSL
echo "----------- material done -----------"
./gradlew :anvil-gridlayout-v7:generateGridLayoutv7DSL
echo "----------- gridlayout done -----------"
./gradlew :anvil-recyclerview-v7:generateRecyclerViewv7DSL
echo "----------- recyclerview done -----------"
./gradlew :anvil-support-v4:generateSupportCoreUiDSL
echo "----------- supportv4 done -----------"
