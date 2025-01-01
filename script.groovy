def buildApp() {
    echo 'building app'
}

def testApp() {
    echo 'testin app'
}

def deployApp() {
    echo 'deploying app'
    echo "deploying version ${params.VERSION}"
}

return this





