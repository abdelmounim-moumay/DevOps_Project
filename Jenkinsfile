def gv
pipeline {
    agent any
    tools {
        maven 'maven'
    }
    stages {
        stage("build jar") {
            steps {
                script {
                   echo "building the applications"
                   sh 'mvn package'
                }

            }
        }

        stage("build image") {
            steps {
                script {
                    echo "bulding docker image"
                    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                        sh 'docker build -t abdelmonimmoumay059/my-repo:jma-2.0 .'
                        sh "echo $PASS | docker login -u $USER --password-stdin"
                        sh 'docker push abdelmonimmoumay059/my-repo:jma-2.0'
                    }
                }
            }
        }
      
        


            }
        }
    }
}
      
