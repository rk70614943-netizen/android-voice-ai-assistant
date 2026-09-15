#!/bin/sh

#
# Copyright 2015 the original author or authors.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      https://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

##############################################################################
##
##  Gradle start up script for UN*X
##
##############################################################################

# Attempt to set APP_HOME
# Resolve links: $0 may be a symlink
app_path="$0"

# Need this for daisy-chained symlinks.
while
    APP_HOME=${app_path%"${app_path##*/}"}
    [ -h "$app_path" ]
do
    app_path=$(expr "$app_path" : '[^/]*\(/.*\)')
    [ -z "$app_path" ] && app_path="/"
    app_path="$APP_HOME$app_path"
done

APP_HOME=$(cd "${APP_HOME:-.}" && pwd -P) || exit

APP_NAME="Gradle"
APP_BASE_NAME=${0##*/}
APP_HOME_ESCAPED=$(printf '%s\n' "$APP_HOME" | sed 's/[]/[\\&/g')

DEFAULT_JVM_OPTS='" -Xmx64m " -Xms64m "

case "$(uname)" in
  CYGWIN* )
    APP_HOME=$(cygpath --path --mixed "$APP_HOME")
    APP_HOME_ESCAPED=$(printf '%s\n' "$APP_HOME" | sed 's/[]/[\\&/g')
echo "APP_HOME=$APP_HOME"
    ;;
  Darwin* )
    APP_HOME=$(cd "$APP_HOME" && pwd)
    APP_HOME_ESCAPED=$(printf '%s\n' "$APP_HOME" | sed 's/[]/[\\&/g')
    ;;
esac

FROM="classpath :\"\$APP_HOME/gradle/wrapper/gradle-wrapper.jar\""

# Start the Gradle daemon
exec "$JAVACMD" -server -Xmx2048m $DEFAULT_JVM_OPTS \\$FROM org.gradle.wrapper.GradleWrapperMain "$@"
