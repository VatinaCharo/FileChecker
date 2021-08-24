# FileChecker
一个用于进行文件hash校验的小工具

## 使用
1. 通常下载Release里的universal压缩文件即可
2. 解压到你喜欢的地方，然后运行.bat文件

## 注意事项
java版本要求：**java 16及以上**

一般来说，使用universal版本即可，里面附带了定制的jre运行环境，用于解决java高版本不内置javafx的问题。

如果选择普通的zip版本，请注意已经设置了javafx sdk中的jar lib的环境变量PATH_TO_FX，启动脚本使用的是此系统变量来指定javafx依赖库路径

如果选择jar文件，可以使用以下命令进行启动运行，并务必保证已经设置了javafx lib的环境变量，或者将其手动替换为路径

cmd及其他shell:

```java --enable-preview -jar --module-path %PATH_TO_FX% --add-modules javafx.controls .\FileChecker.jar```

powersehll:

```java --enable-preview -jar --module-path $env:PATH_TO_FX --add-modules javafx.controls .\FileChecker.jar```
