folder('Tools')
freeStyleJob('Tools/clone-repository') {
    // Clean the workspace before running the job
    wrappers {
        preBuildCleanup()
    }
    // Define parameters
    parameters {
        stringParam('GIT_REPOSITORY_URL', '', 'Git URL of the repository to clone')
    }
    // Clone the repository
    steps {
        shell('git clone ${GIT_REPOSITORY_URL}')
    }
}
