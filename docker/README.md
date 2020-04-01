## preparations
- Copy service, portlets, themes (jars, wars...) to ./docker/deploy   (TODO: modify gradle build and automate this...)
- Create docker image by running script docker-build.sh, choose desired environment so that right configs is copied to image.
    - build creates different images for different envs. Image name will be: sitoumus2050 + env, for example: sitoumus2050test

## Run local docker:
- under ./local directory, create your own docker-compose.yml file
- run docker-compose up
- ./local/deploy points to liferay deploy folder in container, so you can make deploy simply copying jars or wars there

## Test
Create test docker image by running docker-build-push.sh, note! you need soikea docker registry credentials to do the push. (TODO: automate build)
    - Update the new docker image tag information to ./test/Dockerrun.aws.json
    - Deploy image to aws using console, upload Dockerrun.aws.json 
    
