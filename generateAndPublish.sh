#!/usr/bin/env bash
. ./generateTools.sh

gw Clean clean
gwpub anvil Sdk
gwpub anvil-appcompat-v7 AppCompatv7
gwpub anvil-gridlayout-v7 GridLayoutv7
gwpub anvil-recyclerview-v7 RecyclerViewv7
gwpub anvil-cardview-v7 CardViewv7
gwpub anvil-design Material
gwpub anvil-support-v4 SupportCoreUi

# TODO: constraintlayout
# TODO: yogalayout
