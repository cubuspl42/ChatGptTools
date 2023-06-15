#!/bin/sh

SCRIPT_DIR=`dirname "$0"`
SCRIPT_DIR=`cd "$SCRIPT_DIR" && pwd`
JAR_PATH="$SCRIPT_DIR/build/libs/ChatGptTools-1.0-SNAPSHOT-all.jar"

java -jar "$JAR_PATH" "$@"
