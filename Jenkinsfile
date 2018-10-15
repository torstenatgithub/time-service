node('') {
  stage('git clone') {
    git url: 'https://github.com/torstenatgithub/time-service.git'
  }

  stage('app build') {
    sh "./gradlew clean bootJar"
  }

  stage('unittest') {
    sh "./gradlew test"
  }
  
  stage('os build') {
    script {
      openshift.withCluster() {
        openshift.withProject('torstens-project') {
          openshift.selector("bc", "time-service").startBuild("--from-dir=build/libs", "--wait=true")
        }
      }
    }
  }
}
