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
}
