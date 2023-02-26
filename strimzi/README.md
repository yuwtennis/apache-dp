Instruction based on strimzi official doc.
https://strimzi.io/quickstarts/

## Operator setup

```shell
kubectl create namespace kafka
kubectl create -f 'https://strimzi.io/install/latest?namespace=kafka' -n kafka
```

## Teardown

```shell
kubectl -n kafka delete -f 'https://strimzi.io/install/latest?namespace=kafka'
kubectl delete ns kafka
```