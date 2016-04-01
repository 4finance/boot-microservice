import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy
import ch.qos.logback.core.status.OnConsoleStatusListener
import com.ofg.infrastructure.property.LogbackConfiguration

import static ch.qos.logback.classic.Level.DEBUG
import static ch.qos.logback.classic.Level.INFO
import static com.ofg.config.BasicProfiles.PRODUCTION
import static org.springframework.core.env.AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME

LogbackConfiguration logbackConfig = new LogbackConfiguration()

//for more details about groovy conf, see http://logback.qos.ch/manual/groovy.html
statusListener(OnConsoleStatusListener)
String rollingFile = "FILE"
String console = "CONSOLE"
List whereToLog = [rollingFile, console]
String logPattern = logbackConfig.getLogPattern()
String scanTime = logbackConfig.getScanTime()
String currentLogFile = logbackConfig.getLoggerFilename()
String rollingFileNamePattern = logbackConfig.getRollingFilenamePattern()
int rollingFileMaxHistory = logbackConfig.getRollingMaxHistory()

println """Starting logback
To set your own log file, start it with
  java -Dlogging.config=/path/to/my-logback.groovy
ScanTime is set to $scanTime
Logging to $whereToLog
with pattern $logPattern
Current log file is $currentLogFile
Rolling file name pattern is $rollingFileNamePattern
Max number of rolling files is $rollingFileMaxHistory
"""

scan(scanTime)

appender(rollingFile, RollingFileAppender) {
    file = currentLogFile
    append = true
    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = rollingFileNamePattern
        maxHistory = rollingFileMaxHistory
    }
    encoder(PatternLayoutEncoder) {
        pattern = logPattern
    }
}

appender(console, ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = logPattern
    }
}

root(INFO, whereToLog)

String activeProfiles = System.getProperty(ACTIVE_PROFILES_PROPERTY_NAME)

if (activeProfiles && activeProfiles.contains(PRODUCTION)) {
    logger("com.ofg", INFO)
} else {
    logger("com.ofg", DEBUG)
}