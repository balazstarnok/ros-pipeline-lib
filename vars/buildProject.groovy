def call() {
    sh '''
    cd ${CATKIN_WS}
    source /opt/ros/noetic/setup.bash
    catkin_make
    '''
}