node {

    stage('clone') {
        git url: 'https://github.com/torstenatgithub/time-service.git'
    }

    stage('build') {
        sh "./gradlew clean bootJar"
    }

    stage('unittest') {
        sh "./gradlew test"
    }

    //stage('upload') {
    //    sh "./gradlew upload"
    //}

    stage('os build') {
        script {
            openshift.withCluster() {
                openshift.withProject('torstens-project') {
                    echo "Hello from a non-default project: ${openshift.project()}"
                }
            }
        }
    }
}
