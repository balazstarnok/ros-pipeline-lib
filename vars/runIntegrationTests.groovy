def call() {
    sh '''
    bash -c "
    cd ${ROS_WS}
    source devel/setup.bash
    rostest my_package integration_test.launch
    "
    '''
    junit '**/build/test_results/**/*.xml'
}
