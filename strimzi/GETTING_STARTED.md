This directory includes manifests for [strimzi](https://strimzi.io/).  

## Manifests

* kafka
  * [ephemeral-single-internal-listener-only.yaml](kafka/ephemeral-single-internal-listener-only.yaml) is an example of broker with only internal listeners.
  * [ephemeral-single-with-external-listener.yaml](kafka/ephemeral-single-with-external-listener.yaml) is an example of broker also exposing loadbalancer type external listener.
* mm2
  * [plain-source-tls-target.yaml](mm2/plain-source-tls-target.yaml) is an example of mirror maker 2 which uses plain text connection for internal connection and tls for external connection. See the example [architecture](https://github.com/yuwtennis/iac-samples/blob/main/terraform/google_cloud/multi_region_private_container_clusters/README.md).

## How tos

### Pre-requisite

* [kubectl](https://kubernetes.io/docs/reference/kubectl/) and credential to access [Kubernetes API](https://kubernetes.io/docs/concepts/overview/kubernetes-api/) is properly configured.
* k8s cluster is deployed

### Deploy
Instruction based on strimzi official doc.
https://strimzi.io/quickstarts/

Set up operator.

```shell
kubectl create namespace kafka
kubectl create -f 'https://strimzi.io/install/latest?namespace=kafka' -n kafka
```

Apply resources.

```markdown
# Apply broker with only internal listeners
kubectl apply kafka/ephemeral-single-internal-listener-only.yaml
```

## Teardown

```shell
kubectl -n kafka delete -f 'https://strimzi.io/install/latest?namespace=kafka'
```