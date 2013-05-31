#!/bin/bash

find grails-app/domain/ -type f -exec touch {} \; ; grails test-app -echoOut -integration --stacktrace
