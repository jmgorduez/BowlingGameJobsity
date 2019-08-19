gradle= sh gradlew clean
#This is the name of argument file name
file=src/main/resources/inputFile/inputJeffJohnScore.txt

clean:
	@ $(gradle)

refresh:
	@ sh gradlew --refresh-dependencies

run:
	@ $(gradle) run --args='$(file)'

jar:
	@ $(gradle) jar

test:
	@ $(gradle) test

it:
	@ $(gradle) integrationTest

coverage:
	@ $(gradle) test integrationTest jacocoTestReport