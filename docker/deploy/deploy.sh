#!/usr/bin/env bash

set -x -e -o pipefail

# Copy docker-compose file to server.
rsync -avz -e "ssh -p $REMOTE_PORT" $SYNC_FOLDER/ $REMOTE_USER@$REMOTE_HOST:$REMOTE_DIR

ssh -t $REMOTE_USER@$REMOTE_HOST -p$REMOTE_PORT <<EOF

set -x -e -o pipefail

cd $REMOTE_DIR
# login in registry
echo $CI_REGISTRY_PASSWORD | docker login $CI_REGISTRY -u$CI_REGISTRY_USER --password-stdin
# pull images
docker-compose -f docker-compose.yml pull
docker logout $CI_REGISTRY

docker-compose -f docker-compose.yml down;

# start app
export COMPOSE_HTTP_TIMEOUT=120
docker-compose -f docker-compose.yml up -d

EOF
