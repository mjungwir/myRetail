#!/bin/bash

printf "Stopping existing containers.\n"
docker-compose rm -f

printf "Removing containers.\n"
docker system prune --volumes -f

docker-compose up -d