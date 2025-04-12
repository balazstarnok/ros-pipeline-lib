def call() {
    sh '''
    bash -c "
    cd ${ROS_WS}
    source devel/setup.bash
    rostest gem_gazebo gem_simulation_integration.test
    "
    '''
    junit '**/build/test_results/**/*.xml'
}
