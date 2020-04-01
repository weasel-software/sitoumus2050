FROM mdelapenya/liferay-portal:7-ce-ga5-tomcat-hsql

ARG DOCKER_CONFIGS_ENV_DIR
COPY ./configs/${DOCKER_CONFIGS_ENV_DIR}/portal-ext.properties $LIFERAY_HOME/portal-ext.properties
COPY ./configs/${DOCKER_CONFIGS_ENV_DIR}/system-ext.* $LIFERAY_HOME/tomcat-8.0.32/webapps/ROOT/WEB-INF/classes/system-ext.properties
COPY ./configs/${DOCKER_CONFIGS_ENV_DIR}/osgi/configs/* $LIFERAY_HOME/osgi/configs/
COPY ./docker/setenv.sh $LIFERAY_HOME/tomcat-8.0.32/bin/setenv.sh

COPY ./docker/deploy/* $LIFERAY_HOME/deploy/

USER root
RUN chown -R liferay:liferay $LIFERAY_HOME/deploy

COPY ./docker/init.sh /init.sh
RUN chmod +x /init.sh

#USER liferay
CMD ["/init.sh"]
