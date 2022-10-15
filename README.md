# ElasticCloud

The ElasticCloud system takes into consideration three main actors which are: the client, the user and the
Cloud provider.
– Client: represents the entity that needs to manage the resources of his web application, which he pays
only for his use.
- User: represents the entity that wants to communicate with the client’s application through a Load
Balancing (LB) by initiating HTTP requests. A set of users perform the workload of the application.

– Cloud provider: is the entity providing physical computing resources as a service to clients in the form
of VMs. Doing so, we use a Cloud management system (CMS) to perform the operations needed to deploy the VMs hosted on the Cloud provider’s infrastructure.
Furthermore, we have introduced a new layer called Elastic Interface (EI). This layer is put on
each Cloud provider wanting to monitor its resources.
