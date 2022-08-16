Микросервис управления профилем пользователя

Сборка и установка в minikube
1) gradle build
2) docker build -t gurok/arch_profiles .
3) docker push gurok/arch_profiles
4) kubectl create namespace arch-gur
5) helm install arch-profiles ./deployment/
   kubectl get pods -n arch-gur
   
---
helm uninstall arch-profiles
kubectl delete namespace arch-gur