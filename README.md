# fitbitAPI20-scribe-java
Fitbit's OAuth2.0 basing on scribe-java library

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.scribejava/scribejava/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.scribejava/scribejava)

## Summary
This is my FitbitAPI20 client's implmentation basing on scribe-java library. To be able to use [Fitbit](https://dev.fitbit.com/)'s API 2.0, it's not very difficult, but at many points, they use some details which is not OAuth2.0 standard, thus, you might need to override the default implementation given in [scribe-java](https://github.com/scribejava/scribejava) library.


## How to use
It's very to use. Just include two files `FitbitApi20.java` and `Fitbit20ServiceImpl` in your projects. Then you can easily setup the Fitbit client like this:

```java
final OAuth20Service service = new ServiceBuilder()
                .apiKey(clientId)
                .apiSecret(clientSecret)
                .scope("activity%20profile") // replace with desired scope
                .callback("http://example.com")  //your callback URL to store and handle the authorization code sent by Fitbit
                .build(FitbitApi20.instance());
```

Of course, you need to include scribe-java as a library. It's very easy if you use Maven:

```xml
 <dependency>
          <groupId>com.github.scribejava</groupId>
          <artifactId>scribejava-core</artifactId>
          <version>3.2.0</version>
  </dependency>
```

## Questions
Feel free to drop me an email or create an issue right here. Thanks.
