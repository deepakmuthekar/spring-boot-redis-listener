# spring-boot-redis-listner

<b>Redis Windows installation:</b>
1. Go To : https://github.com/MicrosoftArchive/redis/releases
2. Download latest release msi and run it to install Redis

<b>Start Redis on Windows:</b></p>
 I installed Redis at location D:\Redis

Open command prompt and goto Redis installation directory 
in our case </p>
cd D:\Redis

Then execute command :</p>
D:\Redis>redis-server.exe

Now redis is running on default port 6379

<b> Redis CLI </b></p>
Open command prompt and goto Redis installation directory 
in our case </p>
cd D:\Redis

Then execute command :</p>
D:\Redis>redis-cli.exe

On our application strtup we are createing a key SEASON with value WINTER.

to test the expiry key event listner

in CLI set the expiry for the key SEASON for e.g. 10 sec with command

EXPIRE SEASON 10

after 10 seconds SEASON key wil expire and you will get line : INSIDE EVENT EXPIRY HANDLER ---------------------------
printed on your console which is written inside lsitnet event handler.

<b>Note</b> : Tpo enable key expiry notifications we need to execute the command 

redis-cli config set notify-keyspace-events KEA






