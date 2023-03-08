GRADLEW=./gradlew

JAVA_DEBUGGER=jdb
JAVA_DEBUGGER_OPTS=-attach 5005

serv:
	$(GRADLEW) runServ

jar: GRADLEW_OPTS=--no-daemon
jar: clean
	$(GRADLEW) $(GRADLEW_OPTS) jar

all:
	$(GRADLEW) build

status:
	$(GRADLEW) --status

stop:
	$(GRADLEW) --stop

debug:
	$(JAVA_DEBUGGER) $(JAVA_DEBUGGER_OPTS)

clean:
	$(GRADLEW) --no-daemon clean
