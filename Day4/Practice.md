
**Practice-1**: Configure two linux nodes with jenkins master and try this below.

**Jenkins master** labels - java, master, build

**Linux Node-1** labels - maven, build, wait, sonar

**Linux Node-2** labels - gradle, docker, k8s, java

**Linux Node-3** labels - ansible, docker, deploy


* Confogure Jenkins Job with label *build* and then execute the job.
* Stop **Linux node-1** server in the middle of the job execution and observer what will happen.

-------

**Practice-2**: Copy the artifacts from one node to another node.

-------

**Practice-3**: Run the each stage in each available node(s).




