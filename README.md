Микросервис управления профилем пользователя

Сборка и установка в minikube
1) gradle build
2) docker build -t gurok/arch_profiles_2 .
3) docker push gurok/arch_profiles_2
4) kubectl create namespace arch-gur
5) helm install arch-profiles ./deployment/
   kubectl get pods -n arch-gur
   
Для локального поднятия кафки: docker compose up

---
helm uninstall arch-profiles
kubectl delete namespace arch-gur