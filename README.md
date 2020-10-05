# Precondition Validator

## What is it?

**_Make your software more robust by defining clear preconditions!_**

`Precondition Validator` provides several features to ensure that the parameters and data passed at the start of the process meet the preconditions.

Using the `Precondition Validator` features, you can safely and easily specify preconditions at the start of the process.

## Benefits

- No need to write a redundant checking process for preconditions
- It can be implemented intuitively
- Tested secure precondition checking process
- Easy and straightforward

## How To Use

### 1. Add the dependencies

> **_Note:_**<br>
> Replace version you want to use. Check the latest [Packages](https://github.com/myConsciousness/precondition-validator/packages).<br>
> Please contact me for a token to download the package.

**_Maven_**

```xml
<dependency>
  <groupId>org.thinkit.common</groupId>
  <artifactId>precondition-validator</artifactId>
  <version>v1.0.3</version>
</dependency>

<servers>
  <server>
    <id>github</id>
    <username>myConsciousness</username>
    <password>xxxxxxxxxxxxxxxxxx</password>
  </server>
</servers>
```

**_Gradle_**

```gradle
repositories {
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/myConsciousness/precondition-validator")
        credentials {
          username = "myConsciousness"
          password = "xxxxxxxxxxxxxxxxxx"
        }
    }
}

dependencies {
    implementation 'org.thinkit.common:precondition-validator:v1.0.3'
}
```

### 2. Add an import for [**_Preconditions_**](https://github.com/myConsciousness/precondition-validator/blob/master/src/main/java/org/thinkit/common/Preconditions.java)

```java
import org.thinkit.common.Preconditions;
```

### 3. Define preconditions

The following is an example using [Preconditions#requireNonEmpty(Object[])](<https://myconsciousness.github.io/precondition-validator/org/thinkit/common/Preconditions.html#requireNonEmpty(java.lang.Object[])>) and [Preconditions#requireNonEmpty(String)](<https://myconsciousness.github.io/precondition-validator/org/thinkit/common/Preconditions.html#requireNonEmpty(java.lang.String)>).

> **_Note:_**</br>
> The reference for all validation methods is [here](https://myconsciousness.github.io/precondition-validator/org/thinkit/common/Preconditions.html).

```java
import org.thinkit.common.Preconditions;

public class DemoPreconditions {

     /**
      * Entry point for apps that are expected to be passed a non-empty string...
      */
     public static void main(String[] args) {
          // Validate that args is not an empty array
          // and that args[0] is not an empty string.
          Preconditions.requireNonEmpty(args);
          Preconditions.requireNonEmpty(args[0]);
     }
}
```

## License

```
Copyright 2020 Kato Shinya.

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
in compliance with the License. You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License
is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
or implied. See the License for the specific language governing permissions and limitations under
the License.
```

## More Information

`Precondition Validator` was designed and implemented by Kato Shinya, who works as a freelance developer from Japan.

Regardless of the means or content of communication, I would love to hear from you if you have any questions or concerns. I do not check my email box very often so a response may be delayed, anyway thank you for your interest!

- [Creator Profile](https://github.com/myConsciousness)
- [License](https://github.com/myConsciousness/precondition-validator/blob/master/LICENSE)
- [Release Note](https://github.com/myConsciousness/precondition-validator/releases)
- [Package](https://github.com/myConsciousness/precondition-validator/packages)
- [File a Bug](https://github.com/myConsciousness/precondition-validator/issues)
- [Reference](https://myconsciousness.github.io/precondition-validator/org/thinkit/common/Preconditions.html)

```

```
