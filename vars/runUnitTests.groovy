def call() {
    sh '''
    bash -c "
    cd ${ROS_WS}
    source devel/setup.bash
    catkin run_tests
    catkin_test_results || true
    mkdir -p $WORKSPACE/unit-tests-results
    # Copy gtest XML files
    /usr/bin/find /root/gem_ws/build -type f -name 'gtest-*.xml' -exec cp {} $WORKSPACE/unit-tests-results/ \\;

    # List copied test results
    ls -l $WORKSPACE/unit-tests-results/
    "
    '''
}
