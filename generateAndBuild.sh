#!/usr/bin/env bash
. ./generateTools.sh

gw Clean clean
gwbld anvil Sdk
gwbld anvil-appcompat-v7 AppCompatv7
gwbld anvil-gridlayout-v7 GridLayoutv7
gwbld anvil-recyclerview-v7 RecyclerViewv7
gwbld anvil-cardview-v7 CardViewv7
gwbld anvil-design Material
gwbld anvil-support-v4 SupportCoreUiCardviewv7

# TODO: constraintlayout
# TODO: yogalayout
