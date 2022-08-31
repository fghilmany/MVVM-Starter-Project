# MVVM Starter Project

This project was created to simplify the first setup of an Android application. Kotlin as the main language and MVVM as the architecture..

## Library

Library yang digunakan dalam proyek ini adalah:
* Coroutine Flow (Reactive Programming)
* Koin (Dependency Injection)
* Retrofit + Gson (Consume Api)
* Room (SQLite DB)
* Secure Preference (Secure Shared Preference)
* Lottie
* Shimmer
* Glide
* Shape of View
* Navigation
* Timber (Log)

## ApplicationId
**First**, change `applicationId` at `build.gradle(app)` with the `applicationId` you want, like `com.fghilmany.mvvmstartterproject`.\
**Second**, change project path. select `show option menu` project bar, uncheck `compact middle packages`. change the package you want and change packages in `AndroidManifest.xml`\
**Third**, Changes application name in `strings.xml` and `settings.gradle` or 'build.gradle(project)'


## Usage
To use retrofit, you must set url at `local.properties`
```gradle
BASE_URL = "YOUR_URL"
```
or you can set url at `build.gradle(app)`
```gradle
android{
   defaultConfig{
      buildConfigField "String", "BASE_URL", "\"YOUR_URL\""
   }
}
```
## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.
