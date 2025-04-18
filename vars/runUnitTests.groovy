def call() {
    sh '''
    bash -c "
    cd ${ROS_WS}
    source devel/setup.bash
    catkin run_tests
    catkin_test_results || true
    mkdir -p $WORKSPACE/test-artifacts/unit-test-results
    # Copy gtest XML files
    /usr/bin/find /root/gem_ws/build -type f -name 'gtest-*.xml' -exec cp {} $WORKSPACE/test-artifacts/unit-tests-results/ \\;

    # List copied test results
    ls -l $WORKSPACE/test-artifacts/unit-tests-results/
    "
    '''
}
