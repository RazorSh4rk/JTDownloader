# JTDownloader
A Java wrapper for http://www.convertmp3.io/

---

## IMPORTANT
Before downloading any file, please read http://www.convertmp3.io/tos/ . I am not responsible for any violation of the Terms of Service.

This is a simple wrapper for downloading high quality audio from Youtube. Usage:

```java
YoutubeDownloader ytd = new YoutubeDownloader("https://www.youtube.com/watch?v=totallyLegitURL", "C:/Users/notMe/");
ytd.download();
```
