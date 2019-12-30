#!/usr/bin/env bash
. ./generateTools.sh

gw Clean clean
gwloc anvil Sdk
gwloc anvil-appcompat-v7 AppCompatv7
gwloc anvil-gridlayout-v7 GridLayoutv7
gwloc anvil-recyclerview-v7 RecyclerViewv7
gwloc anvil-cardview-v7 CardViewv7
gwloc anvil-design Material
gwloc anvil-support-v4 SupportCoreUi

# TODO: constraintlayout
# TODO: yogalayout
