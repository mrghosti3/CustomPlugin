GRADLEW=./gradlew

JAVA_DEBUGGER=jdb
JAVA_DEBUGGER_OPTS=-attach 5005

all:
	$(GRADLEW) build

status:
	$(GRADLEW) --status

stop:
	$(GRADLEW) --stop

jar: GRADLEW_OPTS=--no-daemon
jar: build/libs/CustomPlugin-0.1-dev.jar
	$(GRADLEW) $(GRADLEW_OPTS) clean
	$(GRADLEW) $(GRADLEW_OPTS) jar

debug:
	$(JAVA_DEBUGGER) $(JAVA_DEBUGGER_OPTS)

clean:
	$(GRADLEW) --no-daemon clean
