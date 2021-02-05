package com.project.common.util;

public class SecurityManagerDemo extends SecurityManager {

	   public static void main(String[] args) {

	      // set the policy file as the system securuty policy
	      System.setProperty("java.security.policy", "file:/C:/java.policy");

	      // create a security manager
	      SecurityManagerDemo sm = new SecurityManagerDemo();

	      // set the system security manager
	      System.setSecurityManager(sm);

	      // perform the check
	      sm.checkPrintJobAccess();

	      // print a message if we passed the check
	      System.out.println("Allowed!");
	   }
	}