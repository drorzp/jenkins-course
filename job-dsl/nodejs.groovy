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
        conditionalSteps {
            def SOME_PARAMETER = 'pants'
            println "this is ${SOME_PARAMETER} "
            shell("echo 'starting here'")
            
            condition {
                stringsMatch('${SOME_PARAMETER}', 'pants', false)
            }
            runner('DontRun')
            steps {
                shell("echo 'just one step'")
                shell("echo 'just second  step'")
            }
        }
         conditionalSteps {
            def SOME_PARAMETER = 'pants2xx'
            condition {
                stringsMatch('${SOME_PARAMETER}', 'pants2xx', false)
            }
            runner('DontRun')
            steps {
                shell("echo 'just one step2'")
                shell("echo 'just second  step2'")
            }
        }
}
}
