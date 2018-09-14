keytool -certreq -alias jwtkey -keyalg RSA -dname "CN=sunxufei, OU=B1, O=SAP, L=Pudong, ST=Shanghai, C=CN" -keypass keypassword -keystore server.jks -storepass 12345678

keytool -genkeypair -alias jwt -keyalg RSA -keystore keystore.jks
keytool -list -v -keystore keystore.jks
keytool -exportcert -alias domain  -file domain.pem -keystore keystore.jks -rfc
keytool -exportcert -alias domain  -file domain.der -keystore keystore.jks