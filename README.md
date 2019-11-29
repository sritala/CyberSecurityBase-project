# Cyber Security Base - project

This is project for Cyber Security Base - MOOC course. You can find project template from here https://github.com/cybersecuritybase/cybersecuritybase-project. 

Too see the issues, you have to clone or download this project into your computer and run the program in browser. Also you need to download OWASP ZAP to explore the issues. 

## Issue I: Broken authentication and session management

Task: Visitors get access for information about subscribers by modifying the url. 

### Introductions: 
1. Run the program in browser locally (http://localhost:8080/). 
2. Create some subscribers in form page. 
3. Open OWASP ZAP and scan site by using ZAP spider. 
4. While scanning you see url http://localhost:8080/list, which should be hidden and for admins only. However, after opening the list view it's actually open for everyone.
5. You can see information about the FOOD BOX subscribers in a list view. 

### How to avoid this:
Program should use session based access control for admins. You should use authorization and give right roles to right people. Especially admins should only have admin roles. 

## Issue II: Cross-site scripting (XSS)

Task: Visitors can send malicious push up messages for admins.

### Introductions: 
1. Run the program in browser locally (http://localhost:8080/). 
2. Fill out the form, except write malicious message on the "NAME" field. For example: <script>alert("trolling you")</script>. 
3. Press submit. 
4. Open http://localhost:8080/list view and see the results as your message pops up. 

### How to avoid this: 

This problem can be found from templates->list. This program uses Thymeleaf template where the vulnerability is in the code "span th:utext="${subscriber.name}". Th:utex stands for unescaped text and should be avoided as it can cause security problems. You can avoid this by using "th:text" instead. 

## Issue III: Security misconfiguration

1. Open the pom.xml file. Notice that the program has outdated version of Spring Framework (1.4.2). 

<version>1.4.2.RELEASE</version>

### How to avoid this:
You can fix the issue by modifying the 1.4.2 RELEASE to the newest update. 




