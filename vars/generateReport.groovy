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

    # Now properly generate the HTML using cat <<EOF
    cat <<EOF > $WORKSPACE/test-artifacts/summary.html
    <html>
    <head>
    <title>Simulation Test Report</title>
    <style>
    body { font-family: Arial, sans-serif; margin: 20px; }
    .pass { color: green; font-weight: bold; }
    .fail { color: red; font-weight: bold; }
    ul { list-style-type: none; padding: 0; }
    li { padding: 5px 0; }
    </style>
    </head>
    <body>
    <h1>Simulation Test Summary</h1>
    <p><span class="pass">Passed Tests: $PASSED</span></p>
    <p><span class="fail">Failed Tests: $FAILED</span></p>
    <h2>Details</h2>
    <ul>
    EOF

    # Append list of test files
    for file in $TEST_RESULTS; do
        BASENAME=$(basename "$file")
        echo "<li>$BASENAME</li>" >> $WORKSPACE/test-artifacts/summary.html
    done

    # Close HTML
    echo "</ul></body></html>" >> $WORKSPACE/test-artifacts/summary.html
        '''
}

