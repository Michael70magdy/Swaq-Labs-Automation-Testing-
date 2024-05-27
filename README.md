Test Swaq Lab Website Using Selenium With Java Covering The following Test Cases 

This automation task build by using TestNg, POM Design Pattern, Data Providers and Assertions

    1. Check if the username and password fields are on the main screen of the application:
        - Username input has id equal to user-name
        - Password input has id equal to password
        - Login button has id equal to login-button
        
    2. Check if the given valid credentials work:
        - Use credentials (Username: standard_user ,Password: secret_sauce).
        - After a successful login attempt, a div containing text: Swag Labs is visible.

    3. Check if the given wrong credentials work:
        - Use invalid credentials
        - After an unsuccessful login attempt, a div with class error-message-container error and
          containing a message Epic sadface: Username and password do not match any user in
          this service is visible.

    4. Check for empty credentials:
        - After an unsuccessful login attempt, a div with class error-message-container error and
        containing a message Epic sadface: Username is required is visible.
        - After an unsuccessful login attempt, a div with class error-message-container error and
        containing a message Epic sadface: Password is required is visible.
