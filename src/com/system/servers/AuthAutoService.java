package com.system.servers;
public class AuthAutoService extends AutoServer {
  public AuthAutoService() {
    setIntervalSeconds(60 * 60 * 3);
    setPause(false);
  }

  public void doTask() {
	  System.out.println("dddddd");
  }
  

}
