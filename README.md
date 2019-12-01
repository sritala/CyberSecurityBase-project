# Cyber Security Base - project

This is project for Cyber Security Base - MOOC course. You can find project template from here https://github.com/cybersecuritybase/cybersecuritybase-project. 

## Issue: Broken authentication and session management

Task: Visitors can get access for information about subscribers by modifying the url. 

### Instructions:
1. Run the program in browser locally (http://localhost:8080/). 
2. Create some subscribers in form page. 
3. Log in by using the username "admin" and the password "admin". 
4. After you have successfully logged in, you're transfered to admin site. 
5. Click the subscribers list and you see all of your previously made subscribers. 
6. Now go back to the form page (http://localhost:8080/). As you can see the program has logged you off. 
7. Modify the url and go to (http://localhost:8080/list). You can access all the subscriber information even though you're not logged in. This has high vulnerability for attackers to see all of the subscriber informations including credit card numbers. 

### How to avoid:
Program should use session based access control for admins. You should use authorization and give right roles to right people. 

## Issue: Cross-site scripting (XSS)

Task: Visitors can send malicious push up messages for admins.

### Instructions:
1. Run the program in browser locally (http://localhost:8080/). 
2. Fill out the form, except write malicious message on the "NAME" field. For example: <script>alert("trolling you")</script>. 
3. Press submit. 
4. Open http://localhost:8080/list view and see the results as your message pops up. 

### How to avoid: 

This problem can be found from templates->list. This program uses Thymeleaf template where the vulnerability is in the code "span th:utext="${subscriber.name}". Th:utex stands for unescaped text and should be avoided as it can cause security problems. You can avoid this by using "th:text" instead. 

## Issue: Cross-Site Request Forgery (CSRF)

This program has disabled the CSRF-protection. Anyone can load content into user's browsers and force them to submit a request to this program.

### How to avoid: 

Go to SecurityConfiguration file and remove http.csrf().disable(); from configure().

## Issue: Sensitive Data Exposure

This program doesn't have the following methdods in the SecurityConfiguration class:

 @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

### How to avoid

You can fix that issue by adding that previous code in the SecurityConfiguration class.

## Issue: Security misconfiguration


### Instructions:
1. Open the pom.xml file. Notice that the program has outdated version of Spring Framework (1.4.2). 

<version>1.4.2.RELEASE</version>

### How to avoid:
You can fix the issue by modifying the 1.4.2 RELEASE to the newest update. 




