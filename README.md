

# Error History

1.  07.18 해결

Installed Build Tools revision 31.0.0 is corrupted. Remove and install again using the SDK Manager.

Sol)
  1. Gradle Scripts --> build.gradle file(module)
  2. Change 3 places: compileSdkVersion, buildToolsVersion, targetSdkVersion from 31 to 30
