Микросервис управления профилем пользователя

Сборка и установка в minikube
1) `gradle build`
2) `docker build -t gurok/arch_profiles_2 .`
3) `docker push gurok/arch_profiles_2`
4) `kubectl create namespace arch-gur`
5) `helm install arch-profiles ./deployment/app/`
   `kubectl get pods -n arch-gur`
6) `helm install gorelov-kafka ./deployment/kafka/`

Для локального поднятия кафки: `docker-compose up`

---
### Очистка пространства:

- `helm uninstall arch-profiles`
- `kubectl delete namespace arch-gur`
- `helm uninstall gorelov-kafka`