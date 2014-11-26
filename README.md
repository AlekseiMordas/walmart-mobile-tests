mobile-tests
============
Setup
===

Mac OS
---

1) Clone or pull data from repo https://github.com/AlekseiMordas/walmart-mobile-tests.git


2) To run project from command line


2.1) go to project directory;
	
	
2.2) execute "mvn clean generate-resources package";
	

2.3) go to "target/walmart-mobile-tests-1.0.0" like: 
"target/walmart-mobile-tests-1.0.0" and run your tests with command:
	
	
	java -jar walmart-mobile-tests.jar -testng all.xml -host 0.0.0.0 -os ANDROID

	-host - host of Appium server. By default on mac 0.0.0.0 - optional parameter;
	-testng - option for suites (xml with tests) - optional parameter;
	-os - option for operation system. Variants: ANDROID, IOS.
	For now available suites:
	
	-all.xml (All tests in suite)
	-home_links.xml (Test verify home links)
	-signin.xml (Test Sign In)
	-signup.xml (Test Sign Up)

3) View test results in browser: target/walmart-mobile-tests-1.0.0/test-output/html/index.html    

4) Run Test in Eclipse
   TBD
   

