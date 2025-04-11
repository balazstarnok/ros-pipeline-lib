def call() {
    sh '''
    cd ${CATKIN_WS}
    ls -a
    pwd
    echo ${CATKIN_WS}
    '''
}