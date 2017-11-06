#!/bin/bash
mvn clean compile assembly:single
java -jar target/pdfTextExtractor-jar-with-dependencies.jar