#!/bin/bash

echo "Provisioning infrastructure..."

echo "Finding my public io address..."

MY_PUBLIC_IP="$(curl -s ipinfo.io/ip)"
echo "public ip: $MY_PUBLIC_IP"

echo "Starting terraform..."
terraform init
terraform apply -var "my_public_ip=$MY_PUBLIC_IP/32" -var "password=$DATABASE_PASSWORD"