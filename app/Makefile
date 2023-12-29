.DEFAULT_GOAL := build-run
.PHONY: build setup clean install run run-dist test lint check-deps

setup:
	./gradlew wrapper --gradle-version 8.3

clean:
	./gradlew clean

build:
	./gradlew clean build

install:
	./gradlew clean install

run-dist:
	./build/install/app/bin/app

run:
	./gradlew run

test:
	./gradlew jacocoTestReport

lint:
	./gradlew chechstyleMain

check-deps:
	./gradlew dependencyUpdates -Drevision=release


build-run: build run

