CATALINA_OPTS="$CATALINA_OPTS -server -Dfile.encoding=UTF8 -Xms4096m -Xmx4096m -XX:MaxPermSize=768m -XX:NewSize=200m -XX:MaxNewSize=200m -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:SurvivorRatio=20 -XX:ParallelGCThreads=2 -Djava.net.preferIPv4Stack=true -Dorg.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES=false -Duser.timezone=Europe/Helsinki"