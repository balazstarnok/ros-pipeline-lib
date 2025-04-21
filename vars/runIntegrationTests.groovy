def call() {
    sh '''
    bash -c "
        cd ${ROS_WS}
        source devel/setup.bash
        rostest gem_gazebo gem_simulation_integration.test || true
        mkdir -p $WORKSPACE/test-artifacts/log
        mkdir -p $WORKSPACE/test-artifacts/test_results
        cp -r /root/.ros/log/* $WORKSPACE/test-artifacts/log/ || true
        cp -r /root/.ros/test_results/* $WORKSPACE/test-artifacts/test_results/ || true

        # List copied test results
        ls -l $WORKSPACE/test-artifacts/test_results/
    "
    '''
}    
