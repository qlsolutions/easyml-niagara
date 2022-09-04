# easyml-niagara
EasyML - Data Provider for Niagara Framework

This project is intended to demonstrate how to integrate the [easyml-provider-api](https://github.com/qlsolutions/easyml-provider-api) 
in the Niagara Framework to create a new EasyML Data Provider for the integration with the EasyML Application.

## Getting Started

`easymlProvider-rt` is a standard Niagara module built with Gradle. To build the module please
download the source code, change niagara_home and niagara_user_home inside the `environment.gradle`
as needed to point to the correct path to your desired Niagara install and its associated user home
then open system console and type 'gradlew clean build'.

For more information on how to build Niagara modules please refer to the Niagara development documentation.

Once the module is built, start up a station and use the `easymlProvider` palette to add
a `EasyMLProvider` to your station. This will enable the Data Provider API for the communication
with the EasyML Application. Don't forget to enable the AuthenticationScheme `HTTPBasicScheme` and
create a user to access the API from the Application.

The version 1.0.0-alpha of the easyml-niagara .jar can be downloaded [here](https://github.com/qlsolutions/easyml-niagara/releases/download/v1.0.0-alpha/easymlProvider-rt.jar).

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://github.com/qlsolutions/easyml-niagara/blob/main/LICENSE)
