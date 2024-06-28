# Config README

This is a README file in order to sumarize how we can deal with config settings using library `TypeSafe.Config`.

## Implementation structure

It is neccesary to have two different block of files:

- `ConfigParser`: It is a package located inside main/scala/ folder, it contains the class ConfigParser and the object
we will use through all the code.
- `Resources`: It is a folder of type resources where the .conf files will be located.

### ConfigParser

We need to import the `TypeSafe.Config` library here. Then, we define a val called config of type Config 
(from TypeSafe.config) that will contain the configuration information. Then, a function
called getConfig that will load the config from ConfigFactory to the variable we've just set .
And now we can implement whatever method we need in order to return configuration items using this two.


### Resources

In the folder resources there are some hierarchy:
- `reference.conf`: It's the first config file that it's visited. Using include and the name of another config file
we can use its config information too.
- `application.conf`: It's another config file that it's usually used for more specific configuration.
For example, if you're working at the moment in a different environment, it can be used to overwrite config from
reference.conf if we don't want to modify reference.conf.
Be aware that the folder resources must be created in main/scala and its name has to be "resources". The name of the
other files must be correctly spelled too because if not, the configuration won't be found.
Error like: Exception in thread "main" com.typesafe.config.ConfigException$Missing: system properties: No configuration setting found for key 'title'
Over all, using this Config good practice, you will have your configuration outside
your code, so you can change it if necessary easily and your code will be cleaner.

