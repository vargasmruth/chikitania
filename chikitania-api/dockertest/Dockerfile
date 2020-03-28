FROM java:8

ADD chikitania-1.0-SNAPSHOT.jar /app/chikitania.jar

ADD config-remote-test-mysql.yml /app/config.yml

CMD java -jar /app/chikitania.jar server /app/config.yml

EXPOSE 9000 9001 3306