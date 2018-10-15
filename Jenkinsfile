pipeline {
  
  script {
    def version = VersionNumber(versionPrefix: '', versionNumberString: '${BUILD_DATE_FORMATTED, "yyyyMMddHHmmss"}')
    echo "Building version: ${version}"
  }

  agent {
    node {
      label ''
    }
  }
  
  options {
    timeout(time: 20, unit: 'MINUTES')
  }
  
  stages {

    stage('preamble') {
      steps {
        script {
          openshift.withCluster() {
            openshift.withProject() {
              echo "Using project: ${openshift.project()}"
            }
          }
        }
      }
    }
    
    stage('clone') {
      steps {
        script {
          git url: 'https://github.com/torstenatgithub/time-service.git'
        }
      }
    }

    stage('build') {
      steps {
        script {
          sh "./gradlew clean bootJar"
        }
      }
    }

    stage('unittest') {
      steps {
        script {
          sh "./gradlew test"
        }
      }
    }

    stage('os build') {
      steps {
        script {
          openshift.withCluster() {
            openshift.withProject() {
              openshift.selector("bc", "time-service").startBuild("--from-dir=build/libs", "--wait=true")
            }
          }
        }
      }
    }
    
    stage('verify deployment') {
      steps {
        script {
          openshift.withCluster() {
            openshift.withProject() {
              def latestDeploymentVersion = openshift.selector("dc", "time-service").object().status.latestVersion
              def rc = openshift.selector("rc", "time-service-${latestDeploymentVersion}")
              rc.untilEach(1) {
                def rcMap = it.object()
                return (rcMap.status.replicas.equals(rcMap.status.readyReplicas))
              }
            }
          }
        }
      }
    }
    
    stage('tag image') {
      steps {
        script {
          openshift.withCluster() {
            openshift.withProject() {
              openshift.tag("${openshift.project()}/time-service:latest", "${openshift.project()}/time-service:${version}")
            }
          }
        }
      }
    }
    
  }
}
