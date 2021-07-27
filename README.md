

# History

1.  07.18 해결

Installed Build Tools revision 31.0.0 is corrupted. Remove and install again using the SDK Manager.

Sol)
  1. Gradle Scripts --> build.gradle file(module)
  2. Change 3 places: compileSdkVersion, buildToolsVersion, targetSdkVersion from 31 to 30

--------------------------------------------

2. 카카오톡 오픈 API 도중...
    -> 카카오톡 로그인 기능 -> 오픈 api 기능 사용해야 메시지 링크 기능 구현됨
    -> 카카오 로그인 기능을 또 따로 추가할 필요는 없을 듯하여,, 
    -> 카카오 알림톡 api 알아보았는데 이또한 비효율
    
    
    --> 결론: fcm 푸시 알림(클라우드 서비스) 을 이용한 기능으로 카카오톡 오픈 api 기능을 대체하고자,,로 결론이 남...!
    
    
 -------------------------------------------------------------------------------------------------

  



