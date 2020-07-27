job('NodeJS example') {
    scm {
        git('git://github.com/drorzp/docker-demo') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('Dror Zeplovitch')
            node / gitConfigEmail('drorzp@hotmail.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('node') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        shell("echo 'hi there'")
        shell("npm install")
    }
}
