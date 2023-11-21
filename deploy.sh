pid=$(pgrep -f jenkins | pgrep java)
if [ -n "${pid}" ]
then
        sudo kill -9 ${pid}
        echo kill process ${pid}
        sleep 10
else
        echo no process
fi

echo "Deployment Start..."

JAR_PATH=$(ls -t /home/ubuntu/*.jar | head -1)
sudo chmod +x ${JAR_PATH}
sudo nohup java -jar -DSpring.profiles.active=prod ${JAR_PATH} >> /home/ubuntu/application.log &

sleep 10

echo "Done!"