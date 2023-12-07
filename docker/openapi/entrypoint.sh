#!/bin/sh

LINT_OPENAPI_CONFIG_FILE=$(find docs/ -type f -name '.validaterc')
ARGS="$*"
lint-openapi -s -c "$LINT_OPENAPI_CONFIG_FILE" $ARGS
