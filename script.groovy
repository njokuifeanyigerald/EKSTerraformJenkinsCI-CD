def dockerbuild(String project, String ImageTag, String hubUser){
    sh """
        docker image build -t ${hubUser}/${project} .
        docker image tag ${hubUser}/${project} ${hubUser}/${project}:${ImageTag}
        docker image tag ${hubUser}/${project} ${hubUser}/${project}:latest
       """
}

def dockerScan(String project, String ImageTag, String hubUser){
    sh """
        trivy image ${hubUser}/${project}:latest > scan.txt
        cat scan.txt
       """
}

def dockerPush(String project, String ImageTag, String hubUser){
    withCredentials([usernamePassword(
        credentialsId: 'dockerhub_credentials', 
        passwordVariable: 'password', usernameVariable: 'User')]) {
        sh " docker login  -u '$User' -p '$password' "
    }
    sh """
        docker image build -t ${hubUser}/${project} .
        docker image push  ${hubUser}/${project}:${ImageTag}
        docker image push ${hubUser}/${project}:latest
       """

    
    
}

return this