FROM java:8

ADD target/chikitania-1.0-SNAPSHOT.jar /app/chikitania.jar

ADD config-remote-mysql.yml /app/config.yml

CMD java -jar /app/chikitania.jar server /app/config.yml

EXPOSE 9090 9091 3306