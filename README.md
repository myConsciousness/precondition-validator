![Build](https://img.shields.io/badge/Build-Automated-2980b9.svg?style=for-the-badge)
![Latest Version](https://img.shields.io/badge/Latest_Version-v1.1.1-27ae60.svg?style=for-the-badge)
![License](https://img.shields.io/badge/License-Apache_2.0-e74c3c.svg?style=for-the-badge)</br>
![Java CI with Gradle](https://github.com/myConsciousness/precondition-validator/workflows/Java%20CI%20with%20Gradle/badge.svg?branch=master)

# 1. Precondition Validator

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**

- [1.1. What is it?](#11-what-is-it)
- [1.2. Benefits](#12-benefits)
- [1.3. How To Use](#13-how-to-use)
  - [1.3.1. Add the dependencies](#131-add-the-dependencies)
  - [1.3.2. Add an import for **_Preconditions_**](#132-add-an-import-for-_preconditions_)
  - [1.3.3. Define preconditions](#133-define-preconditions)
- [1.4. License](#14-license)
- [1.5. More Information](#15-more-information)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## 1.1. What is it?

**_Make your software more robust by defining clear preconditions!_**

`Precondition Validator` provides several features to ensure that the parameters and data passed at the start of the process meet the preconditions.

Using the `Precondition Validator` features, you can safely and easily specify preconditions at the start of the process.

## 1.2. Benefits

- No need to write a redundant validation process for preconditions
- Tested and secure validation process
- Easy and intuitive operation

## 1.3. How To Use

### 1.3.1. Add the dependencies

> **_Note:_**<br>
> Replace version you want to use. Check the latest [Packages](https://github.com/myConsciousness/precondition-validator/packages).<br>
> Please contact me for a token to download the package.

**_Maven_**

```xml
<dependency>
  <groupId>org.thinkit.common</groupId>
  <artifactId>precondition-validator</artifactId>
  <version>v1.1.1</version>
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
    implementation 'org.thinkit.common:precondition-validator:v1.1.1'
}
```

### 1.3.2. Add an import for [**_Preconditions_**](https://myconsciousness.github.io/precondition-validator/org/thinkit/common/base/precondition/Preconditions.html)

```java
import org.thinkit.common.base.precondition.Preconditions;
```

### 1.3.3. Define preconditions

The following is an example using [Preconditions#requireNonEmpty(Object[])](<https://myconsciousness.github.io/precondition-validator/org/thinkit/common/base/precondition/Preconditions.html#requireNonEmpty(java.lang.Object[])>) and [Preconditions#requireNonEmpty(String)](<https://myconsciousness.github.io/precondition-validator/org/thinkit/common/base/precondition/Preconditions.html#requireNonEmpty(java.lang.String)>).

> **_Note:_**</br>
> The reference for all validation methods is [here](https://myconsciousness.github.io/precondition-validator/org/thinkit/common/base/precondition/Preconditions.html).

```java
import org.thinkit.common.base.precondition.Preconditions;

public class DemoPreconditions {

     /**
      * Entry point for app that are expected to be given a non-empty string...
      */
     public static void main(String[] args) {
          // Validate that args is not an empty array
          // and that args[0] is not an empty string.
          Preconditions.requireNonEmpty(args);
          Preconditions.requireNonEmpty(args[0]);
     }
}
```

## 1.4. License

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

## 1.5. More Information

`Precondition Validator` was designed and implemented by Kato Shinya, who works as a freelance developer.

Regardless of the means or content of communication, I would love to hear from you if you have any questions or concerns. I do not check my email box very often so a response may be delayed, anyway thank you for your interest!

- [Creator Profile](https://github.com/myConsciousness)
- [Creator Website](https://myconsciousness.github.io/)
- [License](https://github.com/myConsciousness/precondition-validator/blob/master/LICENSE)
- [Release Note](https://github.com/myConsciousness/precondition-validator/releases)
- [Package](https://github.com/myConsciousness/precondition-validator/packages)
- [File a Bug](https://github.com/myConsciousness/precondition-validator/issues)
- [Reference](https://myconsciousness.github.io/precondition-validator/)
