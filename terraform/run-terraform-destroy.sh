#!/bin/bash

echo "Destroing infrastructure..."

echo "Finding my public io address..."

MY_PUBLIC_IP="$(curl -s ipinfo.io/ip)"
echo "public ip: $MY_PUBLIC_IP"

echo "Starting terraform destroy..."
terraform destroy -var "my_public_ip=$MY_PUBLIC_IP/32" -var "password=$DATABASE_PASSWORD"