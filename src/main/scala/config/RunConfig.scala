package de.portfolio
package config

object RunConfig {
  // We can start providing basic run configuration to execute any Scala project.

  // Editing Application run configuration, it is important to set to ticked this two options in Modify Options menu:

  // - Add dependecies with provided scope classpath
  /* In case you have already worked with Scala/Spark you may have already some dependencies downloaded.
  Then in build.sbt, you have to write Provided like in the next example. This will do faster your first build:
    "org.apache.spark" %% "spark-core" % VersionSpark % Provided
   */

  // - Add VM options

  /*
  In order to avoid errors like "cannot access class sun.nio.ch.DirectBuffer (in module java.base)" because of java
  restricted access to some classes you have to add in VM options all this "permissions":
   */
  /*
    --add-opens java.base/java.lang=ALL-UNNAMED
    --add-opens java.base/java.util=ALL-UNNAMED
    --add-opens java.base/java.io=ALL-UNNAMED
    --add-opens java.base/java.util.concurrent=ALL-UNNAMED
    --add-opens java.base/java.util.concurrent.atomic=ALL-UNNAMED
    --add-opens java.base/java.util.concurrent.locks=ALL-UNNAMED
    --add-opens java.base/java.util.regex=ALL-UNNAMED
    --add-opens java.base/java.util.stream=ALL-UNNAMED
    --add-opens java.base/java.util.function=ALL-UNNAMED
    --add-opens java.base/java.util.jar=ALL-UNNAMED
    --add-opens java.base/java.util.zip=ALL-UNNAMED
    --add-opens java.base/java.util.spi=ALL-UNNAMED
    --add-opens java.base/java.lang.invoke=ALL-UNNAMED
    --add-opens java.base/java.lang.reflect=ALL-UNNAMED
    --add-opens java.base/java.net=ALL-UNNAMED
    --add-opens java.base/java.nio=ALL-UNNAMED
    --add-opens java.base/sun.nio.ch=ALL-UNNAMED
    --add-opens java.base/sun.nio.cs=ALL-UNNAMED
    --add-opens java.base/sun.security.action=ALL-UNNAMED
    --add-opens java.base/sun.util.calendar=ALL-UNNAMED
    --add-opens java.security.jgss/sun.security.krb5=ALL-UNNAMED
   */

  // You can set a run configuration template with all this indications in order to optimize the times you have to write this configurations.

  /* It is also mandatory to have hadoop in order to run Spark sessions, for example. In my case, it worked just
  downloading winutils and setting HADOOP_HOME={winutils path (without bin)} and PATH={winutils path with bin}.
   */


}
