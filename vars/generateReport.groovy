def call() {
    container('ros') {
        sh '''
        mkdir -p $WORKSPACE/test-artifacts

        TEST_RESULTS=$(find $WORKSPACE/test-artifacts/test_results -name "*.xml")
        PASSED=0
        FAILED=0

        for file in $TEST_RESULTS; do
            if grep -q 'failures="0"' "$file"; then
                PASSED=$((PASSED+1))
            else
                FAILED=$((FAILED+1))
            fi
        done

        {
            echo "<html><head><title>Simulation Test Report</title>"
            echo "<style>"
            echo "body { font-family: Arial, sans-serif; margin: 20px; }"
            echo ".pass { color: green; font-weight: bold; }"
            echo ".fail { color: red; font-weight: bold; }"
            echo "ul { list-style-type: none; padding: 0; }"
            echo "li { padding: 5px 0; }"
            echo "</style>"
            echo "</head><body>"
            echo "<h1>Simulation Test Summary</h1>"
            echo "<p><span class='pass'>Passed Tests: $PASSED</span></p>"
            echo "<p><span class='fail'>Failed Tests: $FAILED</span></p>"
            echo "<h2>Details</h2><ul>"
            for file in $TEST_RESULTS; do
                BASENAME=$(basename "$file")
                echo "<li>$BASENAME</li>"
            done
            echo "</ul></body></html>"
        } > $WORKSPACE/test-artifacts/summary.html
        '''
    }
}
