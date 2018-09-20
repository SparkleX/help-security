keytool -genkey -v -alias server_cert -keyalg RSA -storetype PKCS12 -keystore server.p12 SAN=dns:localhost,dns:*.spk,ip:127.0.0.1 -dname "CN=na,OU=na,O=na,L=na,ST=na,C=na" -validity 3650 -storepass 12345678 -keypass 12345678

rem keytool -genkey -v -alias client_auth -keyalg RSA -storetype PKCS12 -keystore trust.p12 -dname "CN=localhost,OU=localhost,O=na,L=na,ST=na,C=na" -validity 3650 -storepass 12345678 -keypass 12345678
rem keytool -export -alias client_auth -keystore  trust.p12 -storetype PKCS12 -storepass 12345678 -rfc -file client_auth.cer
keytool -import -alias client_auth -v -file client_auth.cer -keystore server.p12 -storepass 12345678