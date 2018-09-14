keytool -genkey -alias client -keyalg RSA -keystore client.jks
keytool -exportcert -alias tomcat  -file localhost.pem -keystore tomcat.jks -rfc
keytool -import -file localhost.pem -keystore client.jks -alias tomcat

