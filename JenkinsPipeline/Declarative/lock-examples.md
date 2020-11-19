**lock-examples**:

**Example-1**:

* Copy the below code and paste it in your Jenkins file or Pipeline scrpit section in Jenkins job.

    node('master'){
      stage('Build') {

          lock('myResource') {
            echo "locked build"
            sleep 60 // just to wait this job for 60 sec
          }
      }
    }

* Run the Jenkins job, observe the console output, actually lock needs to be created under `Manage Jenkins >> Configure System >> under 'Lockable Resources Manager' section`. But I have not created a lock resource. Jenkins automatically created one as shown below.

This is build#1

![image](https://user-images.githubusercontent.com/24622526/99692997-cdb15c80-2a82-11eb-91dc-54db8f2674aa.png)

While this build is in sleep for 60 sec (i.e., 1 min.), run another build immidiately

build#2 : observe this build#2, already build#1 is running AND locked with the resource 'myResource'. Untill build#1 released this lock, this build#2 can't proceed. 

![image](https://user-images.githubusercontent.com/24622526/99693224-09e4bd00-2a83-11eb-81fc-a48e462b03ed.png)

**Example-1**:

* Copy the below code and paste it in your Jenkins file or Pipeline scrpit section in Jenkins job.

        node('master'){
          stage('Build') {

              lock('pipelinejob_build_resource') {
                echo "locked build"
                sleep 60
              }
          }
          stage('test deployment') {

              lock('pipelinejob_test_deploy_resource') {
                echo "locked build"
                sleep 60
              }
          }
        }
        
* Best practice is to create the lock resources before run the pipeline script with lock resources. Create two lock resouces in `Manage Jenkins >> Configure System >> under 'Lockable Resources Manager' section`.

![image](https://user-images.githubusercontent.com/24622526/99693694-8ecfd680-2a83-11eb-92dd-f7b499dbffe5.png)

* Trigger `Build#1` , build stage acquired the lock `pipelinejob_build_resource` and runing the build.

![image](https://user-images.githubusercontent.com/24622526/99693907-c8a0dd00-2a83-11eb-8dcb-eb1e7c241c9e.png)


* While running `Build#1`, trigger `Build#2`. As the `Build#1` is already acquired the lock and running the build stage, this `Build#2` will have to wait untill the `Build-1` released this lock `pipelinejob_build_resource`

![image](https://user-images.githubusercontent.com/24622526/99694025-dfdfca80-2a83-11eb-80f7-4ce3668380e0.png)

* While running `Build#2`, observer `Build#1`. As the `Build#1` is already acquired the lock and running the build stage, this `Build#2` will have to wait untill the `Build-1` released this lock `pipelinejob_build_resource`, the same thing will have to other lock and other stage as well. Observe the below images.


![image](https://user-images.githubusercontent.com/24622526/99694168-07369780-2a84-11eb-806c-bd1fd389e7e0.png)

* Buld#2

![image](https://user-images.githubusercontent.com/24622526/99694292-2df4ce00-2a84-11eb-8185-e9590e459017.png)

![image](https://user-images.githubusercontent.com/24622526/99694350-3e0cad80-2a84-11eb-8cbf-8cb98825b7e3.png)

Build#1
![image](https://user-images.githubusercontent.com/24622526/99694490-67c5d480-2a84-11eb-98c3-6ac11faf7a97.png)
