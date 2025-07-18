# Glean Java API Client Examples

This directory contains examples demonstrating how to use the Glean Java API client.

## Prerequisites

- Java 11 or higher
- Gradle (wrapper included)
- Valid Glean API credentials

**Optional:** If you use [mise](https://mise.jdx.dev/), just run `mise install` to set up the correct Java and Gradle versions.

## Setup

1. Set your environment variables:
   ```bash
   export GLEAN_API_TOKEN="your_api_token"
   export GLEAN_INSTANCE="your_instance_name"
   ```

2. Build and publish the Glean library locally (from the parent directory):
   ```bash
   cd ../api-client-java
   ./gradlew publishToMavenLocal -x test -Pskip.signing
   ```

## Running Examples

### ChatTest
Demonstrates basic chat functionality with the Glean API:

```bash
gradle run
```

This example:
- Creates a chat message with the text "What are the company holidays this year?"
- Sets the author as `USER` (required for user-initiated messages)
- Sends the request to the chat endpoint
- Prints the response

## Example Structure

```
src/main/java/
├── ChatTest.java          # Basic chat example
└── [future examples]      # Additional examples will go here
```

## Notes

- All examples read credentials from environment variables for security
- Make sure your API token has the necessary permissions for the operations you're testing
- The examples use debug logging to help you see the HTTP requests being made