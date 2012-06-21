Since keytool does not let you import an existing private key for which there is a certificate.

So, we need to do the following to import the key and certificate into a keystore

private key file : site_privateKey.txt
certificate file : site_ee.crt

The documentation is available at : http://www.agentbob.info/agentbob/79-AB.html

openssl pkcs8 -topk8 -nocrypt -in site_privateKey.txt  -inform PEM -out key.der -outform DER
openssl x509 -in site_ee.crt -inform PEM -out cert.der -outform DER

compile "ImportKey.java" found in this folder.

Following command will import the key and certificate into keystore

$java -cp . ImportKey  key.der cert.der site_alias
Using keystore-file : /Users/snambi/keystore.ImportKey
One certificate, no chain.
Key and certificate stored.
Alias:site_alias  Password:password

