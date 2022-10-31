# clojure-websocket

A smallest example that demonstrates the websocket issue on Azure webb app.

# Requirements
1. [Leiningen](https://leiningen.org)
2. Maven

# Build
1. Create pom file: `lein pom`
2. Configure webapp plugin: `mvn com.microsoft.azure:azure-webapp-maven-plugin:2.5.0:config`
3. Create a uberjar: `lein uberjar`
4. Update the `<deployment>` part in `pom.xml` as follows:
```xml
<deployment>
<resources>
  <resource>
    <directory>${project.basedir}/target</directory>
    <includes>
      <include>app.jar</include>
    </includes>
  </resource>
</resources>
</deployment>
```
5. Deploy: `mvn package azure-webapp:deploy`