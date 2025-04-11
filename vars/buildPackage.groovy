def call() {
    sh '''
    bash -c "
    ls -a
    pwd
    echo ${ROS_WS}
    source /opt/ros/noetic/setup.bash
    cd ${ROS_WS}
    catkin_make
    "
    '''
}