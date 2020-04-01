#!/bin/bash
set -ex

echo Hello and welcome to create docker image manually. Make sure you have build all packages: npm run build-all and deployable modules resides in ./docker/deploy folder. After that please give environment, make sure you have right configs under ./config, options: test, prod
read dENV


CURRENT_DIR="${PWD##*/}"
IMAGE_NAME="sitoumus2050"${dENV}
TAG="$(date +"%Y%m%d")"

#TODO
REGISTRY=

docker build --build-arg DOCKER_CONFIGS_ENV_DIR=${dENV} -t ${REGISTRY}/${IMAGE_NAME}:${TAG} .
docker push ${REGISTRY}/${IMAGE_NAME}:${TAG}


