- Build maven

`./mvnw clean package`

- Builde docker image

`docker build --tag=avgdima/k8s-test-springboot:local .`


- Upload local image to minikube

`minikube image load avgdima/k8s-test-springboot:local`

- Apply deployment to context
