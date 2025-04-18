def call() {
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

    TEST_RESULTS=$(find $WORKSPACE/test-artifacts/unit-tests-results -name "*.xml")
    UNIT_PASSED=0
    UNIT_FAILED=0

    for file in $TEST_RESULTS; do
        if grep -q 'failures="0"' "$file"; then
            UNIT_PASSED=$((UNIT_PASSED+1))
        else
            UNIT_FAILED=$((UNIT_FAILED+1))
        fi
    done

    # Correctly generate the HTML
    cat <<EOF > $WORKSPACE/test-artifacts/summary.html
<html>
<head>
<title>Test Report</title>
<style>
body { font-family: Arial, sans-serif; margin: 20px; }
.pass { color: green; font-weight: bold; }
.fail { color: red; font-weight: bold; }
ul { list-style-type: none; padding: 0; }
li { padding: 5px 0; }
</style>
</head>
<body>
<h1>Unit Test Summary</h1>
<p><span class="pass">Passed Tests: $UNIT_PASSED</span></p>
<p><span class="fail">Failed Tests: $UNIT_FAILED</span></p>
<h1>Simulation Test Summary</h1>
<p><span class="pass">Passed Tests: $PASSED</span></p>
<p><span class="fail">Failed Tests: $FAILED</span></p>
<h2>Details</h2>
<ul>
EOF

    # Append list of test files
    TEST_RESULTS=$(find $WORKSPACE/test-artifacts/unit-tests-results -name "*.xml")
    for file in $TEST_RESULTS; do
        BASENAME=$(basename "$file")
        echo "<li>$BASENAME</li>" >> $WORKSPACE/test-artifacts/summary.html
    done
    
    TEST_RESULTS=$(find $WORKSPACE/test-artifacts/test_results -name "*.xml")
    for file in $TEST_RESULTS; do
        BASENAME=$(basename "$file")
        echo "<li>$BASENAME</li>" >> $WORKSPACE/test-artifacts/summary.html
    done

    # Close HTML
    echo "</ul></body></html>" >> $WORKSPACE/test-artifacts/summary.html
    '''
}
