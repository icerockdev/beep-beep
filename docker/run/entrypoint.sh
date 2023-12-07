#!/bin/bash

IS_JMX=${IS_JMX:-false}
PUID=${PUID:-911}
PGID=${PGID:-911}
MAX_MEMORY=${MAX_MEMORY:-256m}
MIN_MEMORY=${MIN_MEMORY:-128m}
THREAD_MEMORY=${THREAD_MEMORY:-16m}

grep -qE ^"$PGID:x|:$PGID:" /etc/group || addgroup -g"$PGID" "$PGID"
grep -qE ^"$PUID:x|x:$PUID:" /etc/passwd || adduser "$PGID" -u"$PUID" -G"$PGID" -D

echo '
-------------------------------------
GID/UID
-------------------------------------'
echo "
User uid:          $(id -u $PUID)
User gid:          $(id -g $PGID)
JMX:               $IS_JMX
Java Min memory    $MIN_MEMORY
Java Max memory    $MAX_MEMORY
Java Thread memory $THREAD_MEMORY
-------------------------------------
"

chown -R $PUID:$PGID /app

case "$IS_JMX" in
true) exec sudo -E -u$PUID $JAVA_HOME/bin/java \
  -Xmx$MAX_MEMORY \
  -Xms$MIN_MEMORY \
  -Xss$THREAD_MEMORY \
  -Denv=$ENV \
  -XX:+UseContainerSupport \
  -Dcom.sun.management.jmxremote \
  -Dcom.sun.management.jmxremote.port=1099 \
  -Dcom.sun.management.jmxremote.rmi.port=1099 \
  -Dcom.sun.management.jmxremote.ssl=false \
  -Dcom.sun.management.jmxremote.authenticate=false \
  -Dcom.sun.management.jmxremote.local.only=false \
  -Djava.rmi.server.hostname=127.0.0.1 \
  -jar app.jar ;;

false) exec sudo -E -u$PUID $JAVA_HOME/bin/java \
  -Xmx$MAX_MEMORY \
  -Xms$MIN_MEMORY \
  -Xss$THREAD_MEMORY \
  -Denv=$ENV \
  -XX:+UseContainerSupport \
  -jar app.jar ;;
esac
