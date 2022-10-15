cd /home/cloud/workspace/Cloud-Federation/src;

javac -classpath /home/cloud/workspace/Cloud-Federation/lib/jade.jar:/home/cloud/workspace/Cloud-Federation/lib/commons-codec-1.3.jar org/cloud/federation/agents/BookBuyerAgent.java;

java -classpath /home/cloud/workspace/Cloud-Federation/lib/jade.jar:/home/cloud/workspace/Cloud-Federation/lib/commons-codec-1.3.jar:. jade.Boot -gui -agents BookBuyerAgent:org.cloud.federation.agents.BookBuyerAgent;
