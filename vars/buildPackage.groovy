// catkin init creates .catkin_tools/profiles/ if it doesn't exist
// catkin config sets up the workspace correctly to find ROS packages (/opt/ros/noetic) and build in Release mode
def call() {
    sh '''
    bash -c "
        cd ${ROS_WS}
        source /opt/ros/noetic/setup.bash
        catkin init || true
        catkin config --extend /opt/ros/noetic --cmake-args -DCMAKE_BUILD_TYPE=Release
        catkin build --no-status
    "
    '''
}