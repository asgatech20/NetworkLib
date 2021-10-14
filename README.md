# NetworkLib
An Android module to handle network calls.

Built using Retrofit, OkHttp and Gson.

### Usage

Add the Jitpack maven repository if you don't have it yet:

``` gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Reference the library from your module's build.gradle:

``` gradle
dependencies {
    [...]
    implementation 'com.github.asgatech20:NetworkLib:1.0'
}
```

Add source and target compatibility to your module's build.gradle:

``` gradle
android {
    ...
    compileOptions {
            sourceCompatibility JavaVersion.VERSION_1_8
            targetCompatibility JavaVersion.VERSION_1_8
        }
}
```
You can now get the Retrofit Instance and use it like bellow:

``` kotlin
    private fun createRetrofit() {
        val retrofit =
            RetrofitModule.provideRetrofit(Constants.Lang.EN, null, AppConstants.BASE_URL)
        val apiInterface = retrofit!!.create(ApiInterface::class.java)
    }
```

### Happy Coding

## Authors

* [Ibrahim Ali](ibrahimali354@gmail.com)
* [Muhammad Noamany](https://github.com/muhammadnomany25)


## Owner

* [AsgaTech Company](https://github.com/asgatech20)


### License

    Copyright 2021 AsgaTech Company.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
