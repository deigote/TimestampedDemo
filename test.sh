#!/bin/bash
cd "`dirname $0`
find grails-app/domain/ -type f -exec touch {} \; ; grails test-app -echoOut -integration --stacktrace
