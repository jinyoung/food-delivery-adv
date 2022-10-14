# 

## Model
www.msaez.io/#/storming/qtB9CPMbh2V8ijyHTPbGIMVt3ql1/46dc65ca62ca72877073eebdea376b0f

## Before Running Services
### Make sure there is a Kafka server running
```
cd kafka
docker-compose up
```
- Check the Kafka messages:
```
cd kafka
docker-compose exec -it kafka /bin/bash
cd /bin
./kafka-console-consumer --bootstrap-server localhost:9092 --topic 
```

## Run the backend micro-services
See the README.md files inside the each microservices directory:

- app
- store


## Run API Gateway (Spring Gateway)
```
cd gateway
mvn spring-boot:run
```

## Test by API
- app
```
 http :8088/orders menuId=1 address="address" customerId="customerId" 

 http :8088/cookings 
 http PUT "http://localhost:8082/cookings/1/accept" acceptOrNot=true
 http :8088/orders/1

 http PUT "http://localhost:8082/cookings/1/finish"
 http :8088/orders/1

# 두번째 주문
 http :8088/orders menuId=2 address="address" customerId="customerId" 
 http PUT "http://localhost:8082/cookings/2/accept" acceptOrNot=false rejectionReason="재료소진"
 http :8088/orders/2
```


## Run the frontend
```
cd frontend
npm i
npm run serve
```

## Test by UI
Open a browser to localhost:8088

## Required Utilities

- httpie (alternative for curl / POSTMAN) and network utils
```
sudo apt-get update
sudo apt-get install net-tools
sudo apt install iputils-ping
pip install httpie
```

- kubernetes utilities (kubectl)
```
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl
```

- aws cli (aws)
```
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install
```

- eksctl 
```
curl --silent --location "https://github.com/weaveworks/eksctl/releases/latest/download/eksctl_$(uname -s)_amd64.tar.gz" | tar xz -C /tmp
sudo mv /tmp/eksctl /usr/local/bin
```

