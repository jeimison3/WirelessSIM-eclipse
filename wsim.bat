echo off
color 0a
title WirelesSIM Output
cls
java -Djava.library.path=%cd% -jar WSIM.jar
pause