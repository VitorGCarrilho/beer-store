Para container com o bd local execute:
```
docker run -p 5432:5432 --name beerdb -e POSTGRES_USER=beerstore -e POSTGRES_PASSWORD=beerstore -e POSTGRES_DB=beerstore -d postgres:10.5-alpine

```

Para gerar uma chave 
```
ssh-keygen -t rsa -b 4096 -o -a 100 -f key/beerstore_key
```

Acessar maquina via ssh
```
ssh -i key/beerstore_key ec2-user@100.26.251.111
```