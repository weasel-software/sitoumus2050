#!/bin/bash
echo "=== soikea: chmod data directory ==="

chmod a+xwr -R /usr/local/liferay-ce-portal-7.0-ga5/data

echo "=== soikea: starting Liferay ==="

exec su liferay -c '/usr/local/liferay-ce-portal-7.0-ga5/tomcat-8.0.32/bin/catalina.sh run'

