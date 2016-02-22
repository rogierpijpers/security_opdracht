###README

###TEAM
Hans van Os			1661859
Max Callaars		1663918
Nikky Bruijn		1662319
Rogier Pijpers		1673217

V2B


###BUILD
1. Import from github: https://github.com/rogierpijpers/security_opdracht.git
	or unpack the zip

2. Run mvn clean install on dir

3. Add jars from target.sample.WEB-INF.lib to Build Path

4. Add security_opdracht/src/main/java & security_opdracht/src/main/resources to Source folders

5. Run with Jetty and Enjoy!


###AUTHENTICATION:

1. SECRET

username: rest
password: rest

Method:
Spring security


-------------------------------------------------------------------------------------------------
2. VERYSECRET

authenticate with Google account (token can only be used once)

Method:
Google authentication API
Custom token service



-------------------------------------------------------------------------------------------------
3. 

username: gebruiker
password: wachtwoord1

e-mail: v2bteam1secur@gmail.com
password: tokensecurity

OR

Replace the User inside the public UserInterface() constructor with personal user information.

Method:
Custom login & token service