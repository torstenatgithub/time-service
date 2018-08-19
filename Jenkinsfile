node {

    stage('git clone') {
        git url: 'https://github.com/torstenatgithub/time-service.git'
    }

    stage('build') {
        sh "./gradlew clean build -x test"
    }

    stage('unittest') {
        sh "./gradlew test"
    }
}