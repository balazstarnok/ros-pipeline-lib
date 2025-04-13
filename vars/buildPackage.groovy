def call() {
    sh '''
    cd ${ROS_WS}
    source /opt/ros/noetic/setup.bash
    catkin build --no-status
    "
    '''
}