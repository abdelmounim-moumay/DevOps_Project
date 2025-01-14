pipeline {
    agent any
    tools {
        maven 'maven'
    }
    stages {

        stage('increment version') {
            steps {
                script {
                    echo 'Incrementing app version'
                    sh '''
                        mvn build-helper:parse-version versions:set \
                            -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
                            versions:commit
                    '''
                    def matcher = readFile('pom.xml') =~ '<artifactId>GestionCinema</artifactId>\\s*<version>(.+?)</version>'
                    def version = matcher[0][1]
                    env.IMAGE_NAME = "$version-$BUILD_NUMBER"
                }
            }
        }

        stage('build app') {
            steps {
                script {
                    echo "Building the application"
                    sh 'mvn clean package'
                }
            }
        }

        stage('build image') {
            steps {
                script {
                    echo "Building Docker image"
                    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                        sh '''
                            docker build -t abdelmonimmoumay059/my-repo:$IMAGE_NAME .
                            echo $PASS | docker login -u $USER --password-stdin
                            docker push abdelmonimmoumay059/my-repo:$IMAGE_NAME
                        '''
                    }
                }
            }
        }
    }
}
