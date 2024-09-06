#!/bin/bash

# 检查是否提供了参数
if [ "$#" -ne 1 ]; then
    echo "Usage: $0 <process-name>"
    exit 1
fi

# 第一个参数被赋值给 processName 变量
processName="$1"

echo "processName: $processName"

# 使用提供的参数来杀掉相关进程
pkill -f "$processName"

exit 0