# Cyber Security Base – Course Project I
You can find the project files from here: https://github.com/nicohartto/cybersecuritybase-project and clone the project to your own local machine. This project uses Starter Code provided. Simply run the spring-boot application, and open http://localhost:8080 to review following issues.

NOTE: You might need to configure ports depending on your own OWASP ZAP proxy settings.

## Issue: A2-Broken Authentication and Session Management
### Steps to reproduce:
1.	Open project in browser (e.g. http://localhost:8080)
2.	Create few subscribers from the initial page
3.	Open up OWASP Zed Attack Proxy
4.	Do a quick scan of the site by using ZAP Spider
5.	Right-click http://localhost:8080 on the left, and select "Attack" => "Spider"
6.	Notice that there is a URL available: http://localhost:8080/list
7.	This part of the application should be for admins only
8.	Click on any created subscribers "Subscriber details" -link
9.	You will see full details of said subscriber even though this is not supposed to be the case
### Steps to fix:
1.	Use session based access-control for admin area
2.	Use authorization with correct roles
3.	Assign correct roles to users
4.	Ensure that only admins have admin role
5.	Secure routes to admin area so, that only authorized users can view them

## Issue: A3-Cross-Site Scripting (XSS)
### Steps to reproduce:
1.	Open project in browser (e.g. http://localhost:8080)
2.	Create few subscribers from the initial page
3.	Notice that the credit-card number field is susceptible for a XSS attack
4.	Create a subscriber with malicious content in credit-card number field such as <script>console.log("hello")</script> - in real-life this could be a XHR request to malicious site that sends all the information scraped from the page
5.	Open up OWASP Zed Attack Proxy
6.	Do a quick scan of the site by using ZAP Spider
7.	Right-click http://localhost:8080 on the left, and select "Attack" => "Spider"
8.	Notice that there is a URL available: http://localhost:8080/list
9.	Go to URL http://localhost:8080/list and open up browser console
10.	See that the word "hello" is indeed printed in the console, which results in the XSS injection being successfully completed

### Steps to fix:
1.	Fix resources/templates/list.html ThymeLeaf view by removing the XSS injection subsceptible <span th:utext="…> and use the escaped version of <span th:text=" …> instead
2.	Fix resources/templates/list-subscriber.html ThymeLeaf view by removing the XSS injection subsceptible <span th:utext="…> and use the escaped version of <span th:text=" …> instead
3.	Validate that malicious user is no longer able to add XSS content via the fixed fields
4.	Malicious user is no longer able to add XSS content to this field

## Issue: A4-Insecure Direct Object References
### Steps to reproduce:
1.	Open project in browser (e.g. http://localhost:8080)
2.	Open up OWASP Zed Attack Proxy
3.	Do a quick scan of the site by using ZAP Spider
4.	Right-click http://localhost:8080 on the left, and select "Attack" => "Spider"
5.	Notice that there is a URL http://localhost:8080/debug available
6.	This shows a full print-out of the system that the application is run on

### Steps to fix:
1.	Remove this /debug path from code
2.	Only print relevant information for debug purposes (errors etc..) when in development mode
3.	Remove this path completely

## Issue: A5-Security Misconfiguration
### Steps to reproduce:
1.	Open project in browser (e.g. http://localhost:8080)
2.	Open up OWASP Zed Attack Proxy
3.	Do a quick scan of the site by using ZAP Spider
4.	Right-click http://localhost:8080 on the left, and select "Attack" => "Spider"
5.	Notice that there is /debug path available which reveals overly informative information
6.	Notice that there is no user authentication applied
7.	Service handles credit-card number but does not use HTTPS

### Steps to fix:
1.	Implement user authorization using sessions
2.	Remove debug path from production code
3.	Remove debug information and only use what is needed for debugging
4.	Implement HTTPS as layer of security when handling credit-card number information
5.	Remove unsecure way of handling listing of single subscriber (nativeQuery = true) and use spring-boot supplied JPA repository and safer methods supplied by default such as .findOne()

## Issue: A6-Sensitive Data Exposure
### Steps to reproduce:
1.	Open project in browser (e.g. http://localhost:8080)
2.	Create few subscribers on the first-page
3.	Open up OWASP Zed Attack Proxy
4.	Do a quick scan of the site by using ZAP Spider
5.	Right-click http://localhost:8080 on the left, and select "Attack" => "Spider"
6.	Notice that application exposes endpoints /list and /list/${id}
7.	Open http://localhost:8080/list
8.	Notice that all subscriber data is visible
9.	Notice that sensitive subscriber data such as credit-card numbers are visible
10.	See OWASP Zed Attack Proxy Spider results again
11.	Notice that application exposes http://localhost:8080/debug also
12.	Open up http://localhost:8080/debug 
13.	Notice that whole application system information is dumped to this view

### Steps to fix:
1.	Implement user authorization using sessions
2.	Remove debug path from production code
3.	Remove debug information and only use what is needed for debugging
4.	Remove references to /list and /debug URLs from resources/templates/form.html
5.	Do not store credit-card-numbers, use external service providers such as Stripe
6.	Implement HTTPS as layer of security when handling credit-card number information
7.	Remove unsecure way of handling listing of single subscriber (nativeQuery = true) and use spring-boot supplied JPA repository and safer methods supplied by default such as .findOne()
