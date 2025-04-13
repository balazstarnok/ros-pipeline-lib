def call() {
    sh '''
    bash -c "
    cd ${ROS_WS}
    source devel/setup.bash
    catkin run_tests
    catkin_test_results || true
    "
    '''
}
